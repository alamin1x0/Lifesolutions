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
import android.view.View
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Helpers.FileUtils
import com.lifesolutions.bd.Helpers.ImageUtils
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.ImageUpload.ImageServerClient
import com.lifesolutions.bd.ImageUpload.ImageUpload
import com.lifesolutions.bd.Notifications.RetrofitInstance
import com.lifesolutions.bd.Notifications.Sender
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.data.model.GroupMember
import com.lifesolutions.bd.kotlinCode.data.model.GroupMessage
import com.lifesolutions.bd.kotlinCode.data.model.NotificationData
import com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMessageAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_group_message.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


class GroupMessageActivity : AppCompatActivity() {
    private val TAG = "GroupMessageActivity"

    private var groupId: String? = null

    // DB Ref
    private lateinit var db: FirebaseDatabase

    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    private lateinit var mLayoutManager: LinearLayoutManager
    private var mAdapter: GroupMessageAdapter? = null

    // Group Info
    private var groupInfo: GroupConversation? = null

    private var allMessages: ArrayList<GroupMessage> = ArrayList()

    // Current User Info
    private var currentUserID: String? = null
    private var currentUserFirstName: String? = null
    private var currentUserLastName: String? = null
    private var currentUserImage: String? = null

    //Notification Topic
    private var TOPIC : String? = null

    private var groupMember: GroupMember? = null

    private val PReqCode = 2
    private val REQUEST_RECORD_AUDIO_PERMISSION = 1
    private val REQUESTCODE = 2
    private var checker = ""
    private var fileName: String? = null
    private var mStart = true
    private var recorder: MediaRecorder? = null

    private lateinit var fileRef: StorageReference
    private lateinit var voiceRef: StorageReference

    var pickedImgUri: Uri? = null

    private lateinit var progressBar: ProgressBar

    private lateinit var animatedLoading: AnimatedLoading


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lifesolutions.bd.R.layout.activity_group_message)

        // Set Actionbar
        setSupportActionBar(toolbar_group_message)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize Shared Preferences for User Data
        userPreferences =
            getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!
        // DB Init
        db = FirebaseDatabase.getInstance()
        fileRef = FirebaseStorage.getInstance().reference.child("MessageFiles")
        voiceRef = FirebaseStorage.getInstance().reference.child("MessageAudios")

        animatedLoading = AnimatedLoading(this)

        if (intent != null) {
            groupId = intent.getStringExtra("groupId")
            TOPIC = "/topics/$groupId"
        }

        progressBar = progressbar_group_message
        progressBar.max = 100

        // Input Change Detector
        inputMessageWatcher()

        // Recycler View
        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.reverseLayout = false
        mLayoutManager.stackFromEnd = true

        recycler_view_group_message.apply {
            layoutManager = mLayoutManager
        }

        getCurrentUserInfo()
        getGroupInfo()
        showAllMessage()
        checkCurrentUserRole()

        btn_request_info.setOnClickListener {
            Intent(this, AcceptMemberActivity::class.java).apply {
                this.putExtra("groupId", groupId)
                startActivity(this)
            }
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC!!).addOnFailureListener {
            toast(it.localizedMessage!!)
        }

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


    private fun getGroupInfo() {
        val ref = db.getReference("GroupConversations").child(groupId!!)

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    groupInfo = dataSnapshot.getValue(GroupConversation::class.java)
                    updateGroupInfoUI()
                } else {
                    toast("No Group info.")
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.w(TAG, p0.message)
            }


        })
    }


    @SuppressLint("SetTextI18n")
    private fun updateGroupInfoUI() {

        val groupName = groupInfo?.groupName

        if (groupName?.length!! > 25) {
            group_name_message.text = "${groupName.take(23)}.."
        } else {
            group_name_message.text = groupName
        }

        group_last_message.text =
            "Last message " + Utils.getDateTimeAgo(groupInfo?.lastTimestamps as Long)

        if (groupInfo?.groupImage != null && groupInfo?.groupImage != "none") {
            Picasso.get().load(groupInfo?.groupImage).into(profile_picture_message)
        } else {
            Picasso.get().load(com.lifesolutions.bd.R.drawable.ic_group_placeholder).into(profile_picture_message)
        }

    }


    private fun showAllMessage() {
        if (!InternetCheck.checkConnection(this)) {
            toast("No Internet Connection")
            return
        }

        // Loading
        animatedLoading.showAnimatedLoading()

        val ref = db.getReference("GroupChatMessages").child(groupId!!)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val f: ArrayList<GroupMessage> = ArrayList()
                for (dataSnapshot1 in dataSnapshot.children) {
                    val message = dataSnapshot1.getValue(GroupMessage::class.java)
                    if (message != null) {
                        f.add(message)
                    }
                }
                allMessages = f
                mAdapter = GroupMessageAdapter(
                    this@GroupMessageActivity,
                    applicationContext,
                    allMessages,
                    groupId!!
                )
                recycler_view_group_message.adapter = mAdapter

                // Loading
                animatedLoading.hideAnimatedLoading()

                // Make Seen
                // messageSeen()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Loading
                animatedLoading.hideAnimatedLoading()

                Snackbar.make(
                    group_message_layout_parent,
                    "Something went wrong",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }


    private fun sentTextMessage() {
        var textMessage = edit_text_personal_message.text.toString()

        if (textMessage.isEmpty()) {
            edit_text_personal_message.error = "Please enter message here"
            edit_text_personal_message.requestFocus()
            return
        }

        pushMessageToDB("text", textMessage, textMessage, "none")
        textMessage = ""

    }

    /**
     * Push Message to database
     */
    private fun pushMessageToDB(
        mgsType: String,
        textMessage: String,
        lastMessage: String,
        imageUrl: String
    ) {
        val groupRef = db.getReference("GroupChatMessages").child(groupId!!)

        val timestamp = System.currentTimeMillis()
        val pushMessage = groupRef.push()
        val messageId = pushMessage.key
        val fullName = "$currentUserFirstName $currentUserLastName"

        val groupMessage = GroupMessage(
            currentUserID,
            fullName,
            currentUserImage,
            groupId,
            messageId,
            textMessage,
            mgsType,
            imageUrl,
            false,
            timestamp
        )

        groupRef.child(messageId!!).setValue(groupMessage)
            .addOnFailureListener {
                Snackbar.make(
                    group_message_layout_parent,
                    "Something went wrong",
                    Snackbar.LENGTH_SHORT
                ).show()
            }


        // Create Conversation
        updateConversation(lastMessage)
        edit_text_personal_message.text = null

        sendNotification(groupId!!,"$currentUserFirstName $currentUserLastName",textMessage,groupInfo!!.groupName!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(com.lifesolutions.bd.R.menu.menu_group_message, menu)

        val item = menu?.findItem(com.lifesolutions.bd.R.id.action_group_info)

        item?.setOnMenuItemClickListener {
            Intent(this, GroupInfoKtActivity::class.java).apply {
                this.putExtra("groupId", groupId)
                startActivity(this)
            }
            return@setOnMenuItemClickListener true
        }

        return super.onCreateOptionsMenu(menu)
    }


    /**
     * Update Conversation
     */
    private fun updateConversation(lastMessage: String) {
        val groupRef = db.getReference("GroupConversations").child(groupId!!)

        groupRef.child("lastConversation").setValue(lastMessage)
        groupRef.child("lastTimestamps").setValue(ServerValue.TIMESTAMP)
    }


    private fun checkCurrentUserRole() {
        val authId = userPreferences.getString("uID", null)
        val ref = FirebaseDatabase.getInstance().getReference("GroupConversations").child(groupId!!)

        ref.child("Members").child(authId!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    groupMember = dataSnapshot.getValue(GroupMember::class.java)
                    if (groupMember?.role == "admin") {
                        checkRequestList()
                    }
                } else {
                    btn_request_info.visibility = View.GONE
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    private fun checkRequestList() {
        val ref = FirebaseDatabase.getInstance().getReference("GroupConversations").child(groupId!!)

        ref.child("Requests").addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val reqCount = dataSnapshot.childrenCount
                    if (reqCount > 0) {
                        btn_request_info.visibility = View.VISIBLE
                        btn_request_info.text = "$reqCount member requested to join"
                    }
                } else {
                    btn_request_info.visibility = View.GONE
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {


            if (checker == "image") {

                pickedImgUri = data?.data
                // notify = true

                progressBar.visibility = View.VISIBLE


                val hdBitmab = ImageUtils.getInstant()
                    .getCompressedBitmap(Utils.getRealPathFromURI(pickedImgUri, this), 40)

                val baos = ByteArrayOutputStream()
                hdBitmab.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val hqData = baos.toByteArray()

                val imageFile: File
                try {
                    val imageUri: Uri = Utils.getImageUri(this, hdBitmab)!!
                    imageFile =
                        FileUtils.uriToFile(this, imageUri)
                    val folderPathPart =
                        RequestBody.create(MultipartBody.FORM, "group_images")
                    val imageFilePart = RequestBody.create(
                        MediaType.parse("image/jpeg"),
                        imageFile
                    )
                    val file = MultipartBody.Part.createFormData(
                        "imageFile",
                        System.currentTimeMillis().toString() + imageFile.name,
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
                            Log.w(TAG, "onFailure: ${t.message}")
                        }
                    })
                } catch (e: IOException) {
                    progressBar.visibility = View.GONE
                    e.printStackTrace()
                }

            } else {
                pickedImgUri = data?.data

                progressBar.visibility = View.VISIBLE

                val filePath = fileRef.child("${System.currentTimeMillis()}.$checker")

                filePath.putFile(pickedImgUri!!)
                    .addOnCompleteListener { task: Task<UploadTask.TaskSnapshot?> ->
                        if (task.isSuccessful) {
                            filePath.downloadUrl
                                .addOnSuccessListener { uri: Uri ->
                                    progressBar.visibility = View.GONE
                                    val fileUrl = uri.toString()
                                    pushMessageToDB(checker, fileUrl, "File Attachment", "none")

                                }
                                .addOnFailureListener { e: Exception ->
                                    progressBar.visibility = View.GONE
                                    toast(e.message.toString())
                                }
                        }
                    }
                    .addOnProgressListener { taskSnapshot: UploadTask.TaskSnapshot ->
                        val p =
                            (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                        progressBar.progress = p
                    }.addOnFailureListener { e: Exception ->
                        progressBar.visibility = View.GONE
                    }
            }


        }
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

    private fun sendVoiceMessage() {
        progressBar.visibility = View.VISIBLE
        // progressBar.setVisibility(View.VISIBLE)
        // notify = true

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
                val p =
                    (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                progressBar.progress = p
            }.addOnFailureListener { e: Exception ->
                progressBar.visibility = View.GONE
                toast(e.message.toString())
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
            Log.w(TAG, e.message.toString())
        }
        recorder?.start()
    }

    private fun stopRecording() {
        recorder?.stop()
        recorder?.release()
        recorder = null
    }


    /**
     * Main Message Send Method
     */
    fun onSendGroupMessage(view: View) {
        sentTextMessage()
    }

    fun onSendImageMessage(view: View) {
        pickImage()
    }

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
        ) { _: DialogInterface?, i: Int ->
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

    fun onSendVoiceMessage(view: View) {
        checker = "voice"
        checkPermissionForAudioRecord()
    }

    private fun sendNotification(groupId: String, fullName: String, message: String, groupName: String){

        val data = NotificationData(
            groupId,
            "$fullName : $message",
            "New message from $groupName",
            "",
            "groupMessage",
            currentUserID,
            com.lifesolutions.bd.R.drawable.app_icon
        )

        Sender(data,TOPIC).also {
            sendNotificationToMembers(it)
        }
    }

    private fun sendNotificationToMembers(notificationData: Sender) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.notificationApi.postNotification(notificationData)
                if(!response.isSuccessful){
                    toast(response.message().toString())
                }
            } catch (e: Exception) {
                toast("Failed to send notification for $e")
                e.printStackTrace()
            }
        }

}