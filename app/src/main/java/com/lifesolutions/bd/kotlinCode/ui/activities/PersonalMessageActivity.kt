package com.lifesolutions.bd.kotlinCode.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.kroegerama.imgpicker.BottomSheetImagePicker
import com.kroegerama.imgpicker.ButtonType
import com.sinch.android.rtc.SinchError
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.BaseActivity
import com.lifesolutions.bd.Activities.CallScreenActivity
import com.lifesolutions.bd.Activities.VideoCallScreenActivity
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.ImageUpload.ImageServerClient
import com.lifesolutions.bd.ImageUpload.ImageUpload
import com.lifesolutions.bd.Models.Message
import com.lifesolutions.bd.Services.SinchService
import com.lifesolutions.bd.Services.SinchService.StartFailedListener
import com.lifesolutions.bd.kotlinCode.data.model.*
import com.lifesolutions.bd.kotlinCode.ui.adapter.ChatMessageAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import com.lifesolutions.bd.kotlinCode.utils.Notify
import com.lifesolutions.bd.kotlinCode.utils.UserData.friendList
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.Utils.hasPermissions
import com.lifesolutions.bd.kotlinCode.utils.toast
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.android.synthetic.main.activity_all_friends_list.*
import kotlinx.android.synthetic.main.activity_personal_message.*
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException

@SuppressLint("SetTextI18n")
class PersonalMessageActivity : BaseActivity(), StartFailedListener, BottomSheetImagePicker.OnImagesSelectedListener {
    private val TAG = "MessageActivity"

    private var allMessages: ArrayList<Message> = ArrayList()

    private var notify = false

    // Current User Info
    private var currentUserID: String? = null
    private var currentUserFirstName: String? = null
    private var currentUserLastName: String? = null
    private var currentUserImage: String? = null

    // Receiver Info
    private var receiverID: String? = null
    private var receiverInfo: UserKt? = null
    private var receiverLastSeen: Long? = null
    private var receiverActiveStatus: Boolean? = false

    // DB Ref
    private lateinit var db: FirebaseDatabase

    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    // Recycler View
    private lateinit var mLayoutManager: LinearLayoutManager
    private var mAdapter: ChatMessageAdapter? = null

    // Message seen Listener
    private lateinit var seenListenerRef: DatabaseReference
    private var listener: ValueEventListener? = null
    // Message typing Listener
    private lateinit var seenConversationRef: DatabaseReference
    private var listenerConversation: ValueEventListener? = null

    private var checker = ""
    private var fileName: String? = null
    private val PReqCode = 2
    private val REQUEST_RECORD_AUDIO_PERMISSION = 1
    private val PERMISSION_AUDIO_CALL = 102
    private val REQUESTCODE = 2
    private var mStart = true
    private var recorder: MediaRecorder? = null
    private lateinit var fileRef: StorageReference
    private lateinit var voiceRef: StorageReference

    // private var pickedImgUri: Uri? = null
    private var compressedImage: File? = null

    private var userBlockMe = false
    private var youBlockedUser = false
    private var isCallable: Boolean = false

    private lateinit var progressBar: ProgressBar

    private val PERMISSIONS = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.RECORD_AUDIO
    )

    private lateinit var animatedLoading: AnimatedLoading

    private val allFriendsList = ArrayList<FriendData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lifesolutions.bd.R.layout.activity_personal_message)

        // Set Actionbar
        setSupportActionBar(toolbar_personal_message)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize Shared Preferences for User Data
        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!
        // DB Init
        db = FirebaseDatabase.getInstance()
        // Init Receiver Id from Intent
        receiverID = intent?.getStringExtra("receiverID")


        Log.d("IKKKKDKDK", "ID"+receiverID)
        animatedLoading = AnimatedLoading(this)

        fileRef = FirebaseStorage.getInstance().reference.child("MessageFiles")
        voiceRef = FirebaseStorage.getInstance().reference.child("MessageAudios")

        progressBar = progressbar_personal_message
        progressBar.max = 100


        // Get User Info
        getReceiverInfo()
        getCurrentUserInfo()

        // Input Change Detector
        inputMessageWatcher()

        // Recycler View
        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.reverseLayout = false
        mLayoutManager.stackFromEnd = true

        recycler_view_personal_message.apply {
            layoutManager = mLayoutManager
        }


        recycler_view_personal_message.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.w(TAG, dy.toString())
            }
        })

        // Seen Listener Ref
        Log.w(TAG, "onCreate: -> $receiverID")
        seenListenerRef = db.getReference("Messages").child(receiverID!!).child(currentUserID!!)
        seenConversationRef = db.getReference("StarnoteConversation").child(currentUserID!!).child(receiverID!!)

        showAllMessage()

        seenMessage(receiverID!!)

        seenOnConversation()

        checkBlockByUser()
        getCurrentUserBlockedStatus()

    }


    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getCurrentUserInfo() {
        currentUserID = userPreferences.getString("uID", null)
        currentUserFirstName = userPreferences.getString("firstName", "")
        currentUserLastName = userPreferences.getString("lastName", "")
        currentUserImage = userPreferences.getString("imageThumbnail", null)

    //    Log.d("UIDD", "getCurrentUserInfo: " +currentUserID)


    }

    private fun getReceiverInfo() {
        val ref = db.getReference("Users").child(receiverID!!)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    receiverInfo = dataSnapshot.getValue(UserKt::class.java)
                    receiverLastSeen = dataSnapshot.child("userActivity").child("lastOnline")
                        .getValue(Long::class.java)
                    receiverActiveStatus = dataSnapshot.child("userActivity").child("online")
                        .getValue(Boolean::class.java)
                    updateReceiverUI()
                } else {
                    toast("No User info.")
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.w(TAG, p0.message)
            }


        })
    }

    private fun updateReceiverUI() {
        val fullName = receiverInfo?.firstName + " " + receiverInfo?.lastName

        if (fullName.length > 12) {
            user_name_personal_message.text = fullName.take(12) + ".."
        } else {
            user_name_personal_message.text = fullName
        }

        if (receiverActiveStatus != null) {
            if (receiverActiveStatus == true) {
                user_status_message.text = "Online"
            } else {
                user_status_message.text = "Seen ${Utils.getDateTimeAgo(receiverLastSeen!!)}"
            }
        } else {
            Log.w(TAG, "No Active Status" )
        }

        if (receiverInfo?.imageThumbnail != null && receiverInfo?.imageThumbnail != "none") {
            Picasso.get().load(receiverInfo?.imageThumbnail).into(profile_picture_message)
        } else {
            Picasso.get().load(com.lifesolutions.bd.R.drawable.user_low).into(profile_picture_message)
        }


    }

    private fun inputMessageWatcher() {
        edit_text_personal_message.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.isNotEmpty()) {
                    action_attach_layout_personal.visibility = View.GONE
                } else {
                    action_attach_layout_personal.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }


    private fun createCallLogConversation(callType: String) {
        var fullReceiverName: String = receiverInfo?.firstName!!
        // var fullSenderName = currentUserFirstName
        if (receiverInfo?.lastName!!.trim() != "") {
            fullReceiverName = receiverInfo?.firstName + " " + receiverInfo?.lastName
        }


        val ref = db.getReference("CallLogs").child(currentUserID!!)
        val pushRef = ref.push()
        // Sender...
        val callerLog =
            CallLog(
                pushRef.key,
                receiverID,
                fullReceiverName,
                receiverInfo?.imageThumbnail,
                callType,
                "outgoing",
                0,
                ServerValue.TIMESTAMP
            )
        ref.child(pushRef.key!!).setValue(callerLog)
    }


    private fun createConversation(messageType: String, textMessage: String) {

        // val fullReceiverName = "${receiverInfo?.firstName.toString()} ${receiverInfo?.lastName.toString().trim()}"
        // val fullSenderName = "${currentUserFirstName.toString()} ${currentUserLastName.toString().trim()}"

        var fullReceiverName = ""
        var fullSenderName = currentUserFirstName
        if (receiverInfo?.firstName != null) {
            fullReceiverName = "${receiverInfo?.firstName} ${receiverInfo?.lastName}"
        }

        if (currentUserFirstName != "") {
            fullSenderName = "$currentUserFirstName $currentUserLastName"
        }
        // Sender...
        val senderConversation = Conversation(
            receiverID,
            fullReceiverName,
            receiverInfo?.imageThumbnail,
            textMessage,
            messageType,
            ServerValue.TIMESTAMP,
            false,
            false
        )
        // Receiver...
        val receiverConversation = Conversation(
            currentUserID,
            fullSenderName,
            currentUserImage,
            textMessage,
            messageType,
            ServerValue.TIMESTAMP,
            false,
            false
        )

        val ref: DatabaseReference =
            db.getReference("StarnoteConversation").child(currentUserID!!).child(receiverID!!)
        val ref2: DatabaseReference =
            db.getReference("StarnoteConversation").child(receiverID!!).child(currentUserID!!)

        ref.setValue(senderConversation)
        ref2.setValue(receiverConversation)
    }

    /**
     * Main Message Send Method
     */
    fun onSendPersonalMessage(view: View) {
        sentTextMessage()
    }

    private fun showAllMessage() {

        if (!InternetCheck.checkConnection(this)) {
            toast("No Internet Connection")
            return
        }

        // Loading
        animatedLoading.showAnimatedLoading()

        val ref = db.getReference("Messages").child(currentUserID!!).child(receiverID!!).limitToLast(350)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val f: ArrayList<Message> = ArrayList()
                for (dataSnapshot1 in dataSnapshot.children) {
                    val message = dataSnapshot1.getValue(Message::class.java)!!
                    f.add(message)
                }
                allMessages = f
                mAdapter = ChatMessageAdapter(
                    this@PersonalMessageActivity,
                    applicationContext,
                    allMessages,
                    receiverID!!
                )
                recycler_view_personal_message.adapter = mAdapter

                // Loading
                animatedLoading.hideAnimatedLoading()

                // Make Seen
                // messageSeen()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Loading
                animatedLoading.hideAnimatedLoading()

                Snackbar.make(
                    personal_message_layout_parent,
                    "Something went wrong",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }

    // Message Seen...
    private fun seenMessage(userId: String) {


        listener = seenListenerRef.orderByKey().limitToLast(10).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach{
                    val chat = it.getValue(Message::class.java)
                    if (chat?.receiverId.equals(currentUserID) && chat?.senderId.equals(userId)) {
                        val hashMap = HashMap<String, Any>()
                        hashMap["seen"] = true
                        it.ref.updateChildren(hashMap)
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        //Clear Notification After Seen!
        NotificationManagerCompat.from(applicationContext).cancelAll()

    }

    private fun seenOnConversation() {
        listenerConversation = seenConversationRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.ref.child("seen").setValue(true)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


    override fun onPause() {
        super.onPause()
        seenListenerRef.removeEventListener(listener!!)
        seenConversationRef.removeEventListener(listenerConversation!!)
    }

    // On Send Voice Sms
    fun onClickVoiceBtn(view: View) {
        checker = "voice"
        checkPermissionForAudioRecord()
    }


    private fun checkPermissionForAudioRecord() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
                val permissions =
                    arrayOf(Manifest.permission.RECORD_AUDIO)
                requestPermissions(permissions, REQUEST_RECORD_AUDIO_PERMISSION)
            } else {
                recordVoiceMessage()
            }
        } else {
            recordVoiceMessage()
        }
    }


    private fun recordVoiceMessage() {
        fileName = externalCacheDir!!.absolutePath
        fileName += "/star_note.3gp"
        val dialogBuilder =
            AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(com.lifesolutions.bd.R.layout.voice_record_dialog, null)
        dialogBuilder.setView(dialogView)
        val chronometer =
            dialogView.findViewById<Chronometer>(com.lifesolutions.bd.R.id.chronometer_recorder_dialog)
        val recordButton =
            dialogView.findViewById<ImageView>(com.lifesolutions.bd.R.id.record_button_recorder_dialog)
        recordButton.setOnClickListener { view: View? ->
            if (mStart) {
                startRecording()
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
                recordButton.setImageResource(com.lifesolutions.bd.R.drawable.ic_recording_switch_start)
                mStart = false
            } else {
                stopRecording()
                chronometer.stop()
                recordButton.setImageResource(com.lifesolutions.bd.R.drawable.ic_recording_switch_stop)
                mStart = true
            }
        }
        dialogBuilder.setPositiveButton(
            "Send"
        ) { dialogInterface: DialogInterface?, i: Int ->
            if (!mStart) {
                stopRecording()
                mStart = true
            }
            sendVoiceMessage()
        }.setNegativeButton(
            "Cancel"
        ) { dialogInterface: DialogInterface?, i: Int ->
            if (!mStart) {
                stopRecording()
                mStart = true
            }
        }
        val alertDialog = dialogBuilder.create()
        alertDialog.window!!.attributes.windowAnimations = com.lifesolutions.bd.R.style.animation_dialog
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    private fun sentTextMessage() {
        notify = true

        var textMessage = edit_text_personal_message.text.toString()

        if (textMessage.isEmpty()) {
            edit_text_personal_message.error = "Please enter message here"
            edit_text_personal_message.requestFocus()
            return
        }

        pushMessageToDB("text", textMessage, textMessage, "none")

        textMessage = ""


        //Clear Notification After Seen!
        NotificationManagerCompat.from(applicationContext).cancelAll()

    }

    private fun sendVoiceMessage() {
        progressBar.visibility = View.VISIBLE
        notify = true

        val filePath: StorageReference = voiceRef.child("${System.currentTimeMillis()}.3gp")
        val uri = Uri.fromFile(File(fileName!!))
        filePath.putFile(uri)
            .addOnSuccessListener {
                filePath.downloadUrl.addOnSuccessListener { uri1: Uri ->
                    progressBar.visibility = View.GONE
                    pushMessageToDB("voice", uri1.toString(), "Voice Message", "none")

                }

            }
            .addOnProgressListener { taskSnapshot: UploadTask.TaskSnapshot ->
                val p = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                progressBar.progress = p
            }.addOnFailureListener {
                progressBar.visibility = View.GONE
            }
    }


    private fun startRecording() {
        recorder = MediaRecorder()
        recorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder?.setOutputFile(fileName)
        recorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        try {
            recorder?.prepare()
        } catch (e: IOException) {
            Log.w(TAG, e.message.toString() )
        }
        recorder?.start()
    }

    private fun stopRecording() {
        recorder?.stop()
        recorder?.release()
        recorder = null
    }

    fun onClickImageBtn(view: View) {
        pickSingle()
    }


    private fun pickImage() {
        checker = "image"

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(
                    this,
                    "Please accept for required permission",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PReqCode
                )
            }
        } else {
            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, REQUESTCODE)
        }
    }

    private fun pickSingle() {
        checker = "image"
        BottomSheetImagePicker.Builder(getString(com.lifesolutions.bd.R.string.file_provider))
            .cameraButton(ButtonType.Button)
            .galleryButton(ButtonType.Button)
            .singleSelectTitle(com.lifesolutions.bd.R.string.pick_single)
            .peekHeight(com.lifesolutions.bd.R.dimen.peekHeight)
            .requestTag("single")
            .show(supportFragmentManager)
    }

    override fun onImagesSelected(uris: List<Uri>, tag: String?) {
        uris.forEach { uri ->
            try {
                val actualImage: File = FileUtil.from(this, uri)
                customCompressImage(actualImage)
            } catch (e: IOException) {
                toast("Failed to read picture data!")
                e.printStackTrace()
            }
        }
    }

    private fun customCompressImage(actualImage: File?) {
        animatedLoading.showAnimatedLoading()
        actualImage?.let { imageFile ->
            lifecycleScope.launch {
                // Full custom
                compressedImage = Compressor.compress(this@PersonalMessageActivity, imageFile) {
                    resolution(450, 450)
                    quality(30)
                    format(Bitmap.CompressFormat.JPEG)
                    size(1_097_152) // 1 MB
                }

                // Set Image View
                compressedImage?.let {
                    animatedLoading.hideAnimatedLoading()
                    if (checker == "image") {
                        notify = true
                        progressBar.visibility = View.VISIBLE

                        // pickedImgUri = data?.data
                        // notify = true
                        // val hdBitmab = ImageUtils.getInstant().getCompressedBitmap(Utils.getRealPathFromURI(pickedImgUri, this), 40)

                        // val baos = ByteArrayOutputStream()
                        // hdBitmab.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                        // val hqData = baos.toByteArray()

                        // val imageFile: File
                        try {
                            // val imageUri: Uri = Utils.getImageUri(this, hdBitmab)!!
                            // imageFile = FileUtils.uriToFile(this, imageUri)
                            val folderPathPart =
                                RequestBody.create(MultipartBody.FORM, "message_images")
                            val imageFilePart = RequestBody.create(
                                MediaType.parse("image/jpeg"),
                                compressedImage!!
                            )
                            val file = MultipartBody.Part.createFormData(
                                "imageFile",
                                System.currentTimeMillis().toString() + compressedImage!!.name,
                                imageFilePart
                            )
                            val builder = Retrofit.Builder()
                                .baseUrl("https://ftp.starnotesocial.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                            val retrofit = builder.build()
                            val client =
                                retrofit.create(ImageServerClient::class.java)
                            val call = client.uploadImage(folderPathPart, file)
                            call.enqueue(object : Callback<ImageUpload> {
                                override fun onResponse(
                                    call: Call<ImageUpload>,
                                    response: Response<ImageUpload>
                                ) {
                                    if (response.isSuccessful) {
                                        progressBar.visibility = View.GONE
                                        val imgUrl = response.body()?.downloadUrlRes
                                        if (imgUrl != null) {
                                            pushMessageToDB("image", imgUrl, "Image", imgUrl)
                                        }

                                    }
                                }

                                override fun onFailure(
                                    call: Call<ImageUpload>,
                                    t: Throwable
                                ) {
                                    progressBar.visibility = View.GONE
                                }
                            })
                        } catch (e: IOException) {
                            progressBar.visibility = View.GONE
                        }

                    }
                }
            }
        }?: animatedLoading.hideAnimatedLoading()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            if (checker != "image") {


                val selectedData = data?.data
                progressBar.visibility = View.VISIBLE
                notify = true

                val filePath = fileRef.child("${System.currentTimeMillis()}.$checker")

                filePath.putFile(selectedData!!)
                    .addOnCompleteListener { task: Task<UploadTask.TaskSnapshot?> ->
                        if (task.isSuccessful) {
                            filePath.downloadUrl
                                .addOnSuccessListener { uri: Uri ->
                                    progressBar.visibility = View.VISIBLE
                                    val url = uri.toString()
                                    pushMessageToDB(checker, url, "File Attach", "none")

                                }
                                .addOnFailureListener {
                                    progressBar.visibility = View.GONE
                                }
                        }
                    }
                    .addOnProgressListener { taskSnapshot: UploadTask.TaskSnapshot ->
                        val p = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                        progressBar.progress = p
                    }.addOnFailureListener { e: Exception ->
                        progressBar.visibility = View.GONE
                    }

//                notify = true
//                progressBar.visibility = View.VISIBLE
//
//                pickedImgUri = data?.data
//                // notify = true
//                val hdBitmab = ImageUtils.getInstant()
//                    .getCompressedBitmap(Utils.getRealPathFromURI(pickedImgUri, this), 40)
//
//                val baos = ByteArrayOutputStream()
//                hdBitmab.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                val hqData = baos.toByteArray()
//
//                val imageFile: File
//                try {
//                    val imageUri: Uri = Utils.getImageUri(this, hdBitmab)!!
//                    imageFile =
//                        FileUtils.uriToFile(this, imageUri)
//                    val folderPathPart =
//                        RequestBody.create(MultipartBody.FORM, "message_images")
//                    val imageFilePart = RequestBody.create(
//                        MediaType.parse("image/jpeg"),
//                        imageFile
//                    )
//                    val file = MultipartBody.Part.createFormData(
//                        "imageFile",
//                        System.currentTimeMillis().toString() + imageFile.name,
//                        imageFilePart
//                    )
//                    val builder = Retrofit.Builder()
//                        .baseUrl("https://ftp.starnotesocial.com/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                    val retrofit = builder.build()
//                    val client =
//                        retrofit.create(ImageServerClient::class.java)
//                    val call = client.uploadImage(folderPathPart, file)
//                    call.enqueue(object : Callback<ImageUpload> {
//                        override fun onResponse(
//                            call: Call<ImageUpload>,
//                            response: Response<ImageUpload>
//                        ) {
//                            if (response.isSuccessful) {
//                                progressBar.visibility = View.GONE
//                                val imgUrl = response.body()?.downloadUrlRes
//                                if (imgUrl != null) {
//                                    pushMessageToDB("image", imgUrl, "Image", imgUrl)
//                                }
//
//                            }
//                        }
//
//                        override fun onFailure(
//                            call: Call<ImageUpload>,
//                            t: Throwable
//                        ) {
//                            progressBar.visibility = View.GONE
//                        }
//                    })
//                } catch (e: IOException) {
//                    progressBar.visibility = View.GONE
//                }

            }

//            else {
//                pickedImgUri = data?.data
//                progressBar.visibility = View.VISIBLE
//                notify = true
//
//                val filePath = fileRef.child("${System.currentTimeMillis()}.$checker")
//
//                filePath.putFile(pickedImgUri!!)
//                    .addOnCompleteListener { task: Task<UploadTask.TaskSnapshot?> ->
//                        if (task.isSuccessful) {
//                            filePath.downloadUrl
//                                .addOnSuccessListener { uri: Uri ->
//                                    progressBar.visibility = View.VISIBLE
//                                    val url = uri.toString()
//                                    pushMessageToDB(checker, url, "File Attach", "none")
//
//                                }
//                                .addOnFailureListener {
//                                    progressBar.visibility = View.GONE
//                                }
//                        }
//                    }
//                    .addOnProgressListener { taskSnapshot: UploadTask.TaskSnapshot ->
//                        val p = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
//                        progressBar.progress = p
//                    }.addOnFailureListener { e: Exception ->
//                        progressBar.visibility = View.GONE
//                    }
//            }


        }
    }

    // Attach
    fun onAttachFile(view: View) {
        val options = arrayOf<CharSequence>(
            "PDF files",
            "DOCX files"
        )

        val builder =
            AlertDialog.Builder(this)
        builder.setTitle("Select Option")
        builder.setItems(
            options
        ) { dialogInterface: DialogInterface?, i: Int ->
            if (i == 0) {
                checker = "pdf"
                pickPdfFile()
            } else if (i == 1) {
                checker = "docx"
                pickWordFile()
            }
        }
        builder.show()
    }

    private fun pickPdfFile() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(
                    this,
                    "Please accept for required permission",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PReqCode
                )
            }
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            startActivityForResult(
                Intent.createChooser(intent, "Select MS Word file"),
                REQUESTCODE
            )
        }
    }

    private fun pickWordFile() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(
                    this,
                    "Please accept for required permission",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PReqCode
                )
            }
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/msword"
            startActivityForResult(
                Intent.createChooser(intent, "Select MS Word file"),
                REQUESTCODE
            )
        }
    }

    /**
     * Push Message to database
     */
    private fun pushMessageToDB(mgsType: String, textMessage: String, lastMessage: String, imageUrl: String) {
        val messageRef = db.getReference("Messages").child(currentUserID!!).child(receiverID!!)

        val timestamp = System.currentTimeMillis()
        val pushMessage = messageRef.push()
        val messageId = pushMessage.key

        val message = Message(
            currentUserID,
            receiverID,
            textMessage,
            messageId,
            mgsType,
            imageUrl,
            false,
            timestamp
        )

        db.getReference("Messages").child("$currentUserID/$receiverID").child(messageId!!)
            .setValue(message)
            .addOnFailureListener {
                Snackbar.make(
                    personal_message_layout_parent,
                    "Something went wrong",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        db.getReference("Messages").child("$receiverID/$currentUserID").child(messageId)
            .setValue(message)
            .addOnFailureListener {
                Log.w(TAG, "sentTextMessage: ${it.message}")
            }

        // Create Conversation
        createConversation(mgsType, lastMessage)
        edit_text_personal_message.text = null
        if (notify) {
            sendNotification(
                receiverID!!,
                "$currentUserFirstName $currentUserLastName",
                lastMessage
            )
        }
        notify = false
    }



    private fun callUserAudio() {
        if (getSinchServiceInterface().isStarted) {

            val headers: MutableMap<String, String> = HashMap()
            headers["Content-Type"] = "application/json"
            headers["Authorization"] = "key=AAAAH3KuD3I:APA91bEn9xaE0KLfvVsLHxudo_SR3lRsDI4K15Mu-I0BWxQNTPvHzTG4kKofAm979uXiwvZL6UtL5zIwcErSxOTKS3QDZ5UmBWYS4W5SK0iDLLimdvqQ8bgneAkTr1jMWAVBM0qhscc6"
            // headers["Authorization"] = "key=AAAAHQM4oAg:APA91bG8vdBeI3UxkkH2srgxVHTA6iAuUJyf-V0NdF5v6pAv7SliDhxlt8EJVs_CmbwH7-NFHUyEitAKLP7c8CUH1yIPzCqTZH23BPmNRMRQ_wacBuxG4IqX1NOR__bQ2R4j2kV_XIo3"
            val call = getSinchServiceInterface().callUserAudio(receiverID, headers)
            val callId = call.callId
            goCallScreenActivity(callId)
            createCallLogConversation("voice")


//            db.getReference("Users").child(receiverID!!)
//                .child("userActivity").addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            if (dataSnapshot.child("online").getValue(Boolean::class.java)!!) {
//                                val headers: MutableMap<String, String> = HashMap()
//                                headers["Content-Type"] = "application/json"
//                                // headers["Authorization"] = "key=AAAAH3KuD3I:APA91bEn9xaE0KLfvVsLHxudo_SR3lRsDI4K15Mu-I0BWxQNTPvHzTG4kKofAm979uXiwvZL6UtL5zIwcErSxOTKS3QDZ5UmBWYS4W5SK0iDLLimdvqQ8bgneAkTr1jMWAVBM0qhscc6"
//                                headers["Authorization"] = "key=AAAAHQM4oAg:APA91bG8vdBeI3UxkkH2srgxVHTA6iAuUJyf-V0NdF5v6pAv7SliDhxlt8EJVs_CmbwH7-NFHUyEitAKLP7c8CUH1yIPzCqTZH23BPmNRMRQ_wacBuxG4IqX1NOR__bQ2R4j2kV_XIo3"
//                                val call = getSinchServiceInterface().callUserAudio(receiverID, headers)
//                                val callId = call.callId
//                                goCallScreenActivity(callId)
//                                createCallLogConversation("voice")
//
//                            } else {
//                               toast("This user is offline")
//                            }
//                        } else {
//                            toast("This user is offline")
//                        }
//                    }
//
//                    override fun onCancelled(databaseError: DatabaseError) {
//                        //TODO
//                    }
//                })
        } else {
            Toast.makeText(this, "Just a moment", Toast.LENGTH_SHORT).show()
        }
    }

    private fun callUserVideo() {
        if (getSinchServiceInterface().isStarted) {

            val headers: MutableMap<String, String> = HashMap()
            headers["Content-Type"] = "application/json"
            headers["Authorization"] = "key=AAAAH3KuD3I:APA91bEn9xaE0KLfvVsLHxudo_SR3lRsDI4K15Mu-I0BWxQNTPvHzTG4kKofAm979uXiwvZL6UtL5zIwcErSxOTKS3QDZ5UmBWYS4W5SK0iDLLimdvqQ8bgneAkTr1jMWAVBM0qhscc6"
            // headers["Authorization"] = "key=AAAAHQM4oAg:APA91bG8vdBeI3UxkkH2srgxVHTA6iAuUJyf-V0NdF5v6pAv7SliDhxlt8EJVs_CmbwH7-NFHUyEitAKLP7c8CUH1yIPzCqTZH23BPmNRMRQ_wacBuxG4IqX1NOR__bQ2R4j2kV_XIo3"

            val call = getSinchServiceInterface().callUserVideo(receiverID, headers)
            val callId = call.callId
            goVideoCallScreenActivity(callId)

            createCallLogConversation("video")

//            FirebaseDatabase.getInstance().reference.child("Users").child(receiverID!!)
//                .child("userActivity").addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            if (dataSnapshot.child("online")
//                                    .getValue(Boolean::class.java)!!
//                            ) {
//                                val call = getSinchServiceInterface().callUserVideo(receiverID)
//                                val callId = call.callId
//                                goVideoCallScreenActivity(callId)
//
//                                createCallLogConversation("video")
//                            } else {
//                                toast("This user is offline")
//                            }
//                        }
//                    }
//
//                    override fun onCancelled(databaseError: DatabaseError) {
//                        Log.w(TAG, databaseError.message )
//                    }
//                })
        } else {
            Toast.makeText(this, "Just a moment", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goCallScreenActivity(callId: String) {
        Intent(this, CallScreenActivity::class.java).apply {
            this.putExtra(SinchService.CALL_ID, callId)
            startActivity(this)
        }
    }

    private fun goVideoCallScreenActivity(callId: String) {
        Intent(this, VideoCallScreenActivity::class.java).apply {
            this.putExtra(SinchService.CALL_ID, callId)
            startActivity(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_RECORD_AUDIO_PERMISSION -> {
                run {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        recordVoiceMessage()
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    }
                }
                run {
                    if (grantResults.isNotEmpty()) {
                        val callRequestAccepted =
                            grantResults[0] == PackageManager.PERMISSION_GRANTED
                        val recordRequestAccepted =
                            grantResults[1] == PackageManager.PERMISSION_GRANTED
                        if (callRequestAccepted && recordRequestAccepted) {
                            callUserAudio()
                        } else {
                            Toast.makeText(
                                this,
                                "Phone call and Record audio permissions are required",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            PERMISSION_AUDIO_CALL -> {
                if (grantResults.isNotEmpty()) {
                    val callRequestAccepted =
                        grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val recordRequestAccepted =
                        grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if (callRequestAccepted && recordRequestAccepted) {
                        callUserAudio()
                    } else {
                        Toast.makeText(
                            this,
                            "Phone call and Record audio permissions are required",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    override fun onStarted() {

        //TODSO("Not yet implemented")

    }

    override fun onStartFailed(error: SinchError?) {
        toast(error?.message!!.toString())
    }

    override fun onServiceConnected() {
        getSinchServiceInterface().setStartListener(this)

        //Register user
        if (getSinchServiceInterface() != null && !getSinchServiceInterface()
                .isStarted
        ) {
            getSinchServiceInterface().startClient(currentUserID)
        }
    }

    /**
     * BlackList
     */

    private fun blockAnUser() {
        val userData = HashMap<String, String?>()

        userData["uid"] = receiverID!!
        userData["firstName"] = receiverInfo?.firstName
        userData["lastName"] =  receiverInfo?.lastName
        userData["profileImage"] =  receiverInfo?.imageThumbnail

        val dbRef = db.getReference("BlackList").child(currentUserID!!)
        dbRef.child(receiverID!!).setValue(userData)
            .addOnSuccessListener {
                finish()
                toast("Blocked! This user add to your blacklist.")
            }
    }

    private fun unBlockAnUser() {
        val dbRef =
            FirebaseDatabase.getInstance().getReference("BlackList").child(currentUserID!!)
        dbRef.child(receiverID!!).removeValue()
            .addOnSuccessListener {
                toast("Success! Successfully unblock this user.")
            }
    }

    private fun checkBlockByUser() {
        val dbRef = db.getReference("BlackList")
        dbRef.child(receiverID!!).child(currentUserID!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    userBlockMe = true
                    toast("You are BLOCK by this user.")
                    finish()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, "onCancelled: Something Wrong")
            }
        })
    }

    private fun getCurrentUserBlockedStatus() {
        val dbRef = db.getReference("BlackList")
        dbRef.child(currentUserID!!).child(receiverID!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                youBlockedUser = dataSnapshot.exists()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, "onCancelled: Something Wrong")
            }
        })
    }


    /**
     * Main Toolbar Menu and Call
     */

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.lifesolutions.bd.R.menu.menu_call, menu)

        val item = menu!!.findItem(com.lifesolutions.bd.R.id.action_audio_call)
        val item1 = menu.findItem(com.lifesolutions.bd.R.id.action_video_call)
        val itemBlock = menu.findItem(com.lifesolutions.bd.R.id.action_user_block)
        val itemUserInfo = menu.findItem(com.lifesolutions.bd.R.id.action_user_info)
        val itemUserBlock = menu.findItem(com.lifesolutions.bd.R.id.action_user_unblock)

        when(youBlockedUser) {
            false -> {
                itemUserBlock.isVisible = false
            }
            true -> {
                itemBlock.isVisible = false
            }
        }

        if (!friendList.contains(receiverID)){
            item.isVisible = false
            item1.isVisible = false
            tv_call_disabled_personal_message.visibility = View.VISIBLE
        }

        item.setOnMenuItemClickListener { menuItem: MenuItem? ->
            if (!hasPermissions(
                    this,
                    PERMISSIONS
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS,
                    PERMISSION_AUDIO_CALL
                )
            } else {
                if (userBlockMe) {
                    Toast.makeText(
                        this,
                        "You are BLOCK by this user.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    callUserAudio()
                }
            }
            true
        }

        item1.setOnMenuItemClickListener { menuItem: MenuItem? ->
            if (userBlockMe) {
                Toast.makeText(
                    this,
                    "You are BLOCK by this user.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                callUserVideo()
            }
            true
        }

        itemBlock.setOnMenuItemClickListener { MenuItem: MenuItem? ->
            if (receiverID == currentUserID) {
                Toast.makeText(this, "You can not block yourself", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Block User", Toast.LENGTH_SHORT).show()
                blockAnUser()
            }
            true
        }

        itemUserInfo.setOnMenuItemClickListener { MenuItem: MenuItem? ->
            if (receiverID == currentUserID) {
                startActivity(Intent(this, OwnProfileActivity::class.java))
            } else {
                startActivity(
                    Intent(this, UserProfileActivityKt::class.java).putExtra(
                        "uID",
                        receiverID
                    )
                )
            }
            true
        }

        itemUserBlock.setOnMenuItemClickListener { menuItem: MenuItem? ->
            unBlockAnUser()
            true
        }

        return super.onCreateOptionsMenu(menu)
    }


    /**
     * Push Notification
     */

    private fun sendNotification(receiverID: String, fullname: String, message: String) {
        val tokenRef = FirebaseDatabase.getInstance().getReference("CloudTokens")

        tokenRef.child(receiverID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.w(TAG, ">>>>>>>>>>>>>>>>>>> sendNotification() <<<<<<<<<<<<<<<<<<<<")
                    val cloudToken = dataSnapshot.getValue(CloudToken::class.java)
                    Log.w(TAG, "Token " + cloudToken.toString())

                    val data = NotificationData(
                        currentUserID,
                        "$fullname : $message",
                        "New message",
                        receiverID,
                        "message",
                        "none",
                        com.lifesolutions.bd.R.drawable.app_icon
                    )

                    Log.w(TAG, "DATA ----> $data")
                    PushNotification(data, cloudToken?.token!!).also {
                        Notify.sendMgsNotification(it)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

}
