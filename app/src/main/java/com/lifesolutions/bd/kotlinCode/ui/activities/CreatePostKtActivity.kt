package com.lifesolutions.bd.kotlinCode.ui.activities

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Adapters.PrivacyAdapterSpinner
import com.lifesolutions.bd.Api.RetrofitClient
import com.lifesolutions.bd.Api.UrlType
import com.lifesolutions.bd.ImageUpload.ImageServerClient
import com.lifesolutions.bd.ImageUpload.ImageUpload
import com.lifesolutions.bd.Models.SpinnerItem
import com.lifesolutions.bd.kotlinCode.data.model.PostKt
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.data.model.VideoPost
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.Utils.hasPermissions
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.android.synthetic.main.activity_create_post_kt.*
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
import java.text.DecimalFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.math.pow


class CreatePostKtActivity : AppCompatActivity() {

    private val TAG = "CreatePostKtActivity"

    // User
    private var currentUser: UserKt? = null
    private lateinit var userPathRef: DatabaseReference

    private var postDesc: String = ""

    private var colorCode = "#E63434"
    private var isImagePicked = false
    private var isVideoPicked = false

    private lateinit var privacyAdapter: PrivacyAdapterSpinner
    private var spinnerItems: ArrayList<SpinnerItem>? = null
    private var privacy = "public"

    // Timer...
    val handler: Handler? = Handler()
    var runnable: Runnable? = null

    // Progress Dialog...
    private lateinit var progress: ViewProgressDialog
    private lateinit var animatedLoading: AnimatedLoading

    // Permission

    private val REQUESTCODE = 2
    private val STORAGE_REQUEST_CODE = 101
    private var PERMISSIONS: Array<String> = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // Image Upload
    private var compressedImage: File? = null
    private var imuri: Uri? = null
    // private var pickedImgUri: Uri? = null
    // private var hdBitmap: Bitmap? = null


    //video
    private val PICK_VIDEO_REQUEST = 1
    private var videoUri: Uri? = null
    private var dowlvideoUri: String? = null
    private var dowlThumbUri: String? = null
    val myRef = FirebaseStorage.getInstance().getReference("Videos")
    val myRefDB = FirebaseDatabase.getInstance().getReference("VideoUri")


    // Links..
    private var linkList: MutableList<String>? = null
    private var linkThumb: String? = null
    private var linkTitle: String? = null
    private var linkSource: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lifesolutions.bd.R.layout.activity_create_post_kt)




        // Toolbar..
        setSupportActionBar(toolbar_add_post_kt)
        supportActionBar?.title = "Add Post"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        userPathRef = FirebaseDatabase.getInstance().getReference("Users")

        // Initialize Progress Dialog
        progress = ViewProgressDialog(this)
        animatedLoading = AnimatedLoading(this)

        // Initialize User from Intent
//        val intentExtra = intent.getSerializableExtra("USER_FULL_DATA")
//        if (intentExtra != null) {
//            currentUser = intentExtra as UserKt
//        }


        // Init Spinner
        initPrivacyList()
        privacyAdapter = object : PrivacyAdapterSpinner(this, spinnerItems) {}

        spinner_add_post_kt.apply {
            adapter = privacyAdapter
        }
        spinner_add_post_kt.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                privacy = when (i) {
                    0 -> {
                        "public"
                    }
                    1 -> {
                        "friends"
                    }
                    else -> {
                        "onlyMe"
                    }
                }

                Log.d(TAG, privacy)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        // Edit Text Watcher...
        et_post_desc_kt.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (runnable != null) {
                    handler?.removeCallbacks(runnable!!)
                }

                postDesc = s.toString()

                when {
                    s.length > 150 -> {
                        decreaseLayoutHeight()
                    }
                    isImagePicked -> {
                        decreaseLayoutHeight()
                    }
                    isVideoPicked -> {
                        decreaseLayoutHeight()
                    }
                    !linkList.isNullOrEmpty() -> {
                        decreaseLayoutHeight()
                    }
                    else -> {
                        resumeOldState()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {

                // For Link preview...
                runnable = Runnable {
                    Log.w(TAG, "Iam Here")
                    linkList = hasUrl(postDesc)
                    if (!linkList.isNullOrEmpty()) {
                        showLinkPreview()
                    } else {
                        removeLinkPreview()
                    }
                }
                handler?.postDelayed(runnable!!, 1000)
            }
        }
        )

        initUserInfo()

        if (intent != null) {
            val imageUri =
                intent.extras!![Intent.EXTRA_STREAM] as Uri?
            if (imageUri != null) {
                val actualImage: File = FileUtil.from(this, imageUri)
                customCompressImage(actualImage)
            }
        }
    }

    /**
     * On Back Pressed
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()

        // Remove Callback
        if (runnable != null) {
            handler?.removeCallbacks(runnable!!)
        }
    }

    /**
     * Aerialist of Spinner
     */
    private fun initPrivacyList() {
        spinnerItems = ArrayList()
        spinnerItems?.add(SpinnerItem("Public", com.lifesolutions.bd.R.drawable.ic_public_black_24dp))
        spinnerItems?.add(SpinnerItem("Friends", com.lifesolutions.bd.R.drawable.ic_group_black_24dp))
        spinnerItems?.add(SpinnerItem("Only me", com.lifesolutions.bd.R.drawable.ic_person_black_24dp))
    }

    /**
     * On Click Color Picker
     */
    fun onPickColorOne(view: View) {
        layout_text_view_area.background =
            ContextCompat.getDrawable(this, com.lifesolutions.bd.R.color.color_post_bg_one)

        colorCode = "#E63434"
        text_view_aa_one.visibility = View.GONE
        icon_check_mark_one.visibility = View.VISIBLE

        // Hide Another
        text_view_aa_two.visibility = View.VISIBLE
        icon_check_mark_two.visibility = View.GONE
        text_view_aa_three.visibility = View.VISIBLE
        icon_check_mark_three.visibility = View.GONE
        text_view_aa_four.visibility = View.VISIBLE
        icon_check_mark_four.visibility = View.GONE
        text_view_aa_five.visibility = View.VISIBLE
        icon_check_mark_five.visibility = View.GONE
        text_view_aa_six.visibility = View.VISIBLE
        icon_check_mark_six.visibility = View.GONE
        text_view_aa_seven.visibility = View.VISIBLE
        icon_check_mark_seven.visibility = View.GONE
    }

    fun onPickColorTwo(view: View) {
        layout_text_view_area.background =
            ContextCompat.getDrawable(this, com.lifesolutions.bd.R.color.color_post_bg_two)

        colorCode = "#8F2CF9"
        text_view_aa_two.visibility = View.GONE
        icon_check_mark_two.visibility = View.VISIBLE

        // Hide Another
        text_view_aa_one.visibility = View.VISIBLE
        icon_check_mark_one.visibility = View.GONE
        text_view_aa_three.visibility = View.VISIBLE
        icon_check_mark_three.visibility = View.GONE
        text_view_aa_four.visibility = View.VISIBLE
        icon_check_mark_four.visibility = View.GONE
        text_view_aa_five.visibility = View.VISIBLE
        icon_check_mark_five.visibility = View.GONE
        text_view_aa_six.visibility = View.VISIBLE
        icon_check_mark_six.visibility = View.GONE
        text_view_aa_seven.visibility = View.VISIBLE
        icon_check_mark_seven.visibility = View.GONE
    }

    fun onPickColorThree(view: View) {
        layout_text_view_area.background =
            ContextCompat.getDrawable(this, com.lifesolutions.bd.R.color.color_post_bg_three)

        colorCode = "#3FB9A4"
        text_view_aa_three.visibility = View.GONE
        icon_check_mark_three.visibility = View.VISIBLE

        text_view_aa_one.visibility = View.VISIBLE
        icon_check_mark_one.visibility = View.GONE
        text_view_aa_two.visibility = View.VISIBLE
        icon_check_mark_two.visibility = View.GONE
        text_view_aa_four.visibility = View.VISIBLE
        icon_check_mark_four.visibility = View.GONE
        text_view_aa_five.visibility = View.VISIBLE
        icon_check_mark_five.visibility = View.GONE
        text_view_aa_six.visibility = View.VISIBLE
        icon_check_mark_six.visibility = View.GONE
        text_view_aa_seven.visibility = View.VISIBLE
        icon_check_mark_seven.visibility = View.GONE
    }

    fun onPickColorFour(view: View) {
        layout_text_view_area.background =
            ContextCompat.getDrawable(this, com.lifesolutions.bd.R.color.color_post_bg_four)

        colorCode = "#FC6A31"
        text_view_aa_four.visibility = View.GONE
        icon_check_mark_four.visibility = View.VISIBLE

        text_view_aa_one.visibility = View.VISIBLE
        icon_check_mark_one.visibility = View.GONE
        text_view_aa_two.visibility = View.VISIBLE
        icon_check_mark_two.visibility = View.GONE
        text_view_aa_three.visibility = View.VISIBLE
        icon_check_mark_three.visibility = View.GONE
        text_view_aa_five.visibility = View.VISIBLE
        icon_check_mark_five.visibility = View.GONE
        text_view_aa_six.visibility = View.VISIBLE
        icon_check_mark_six.visibility = View.GONE
        text_view_aa_seven.visibility = View.VISIBLE
        icon_check_mark_seven.visibility = View.GONE
    }

    fun onPickColorFive(view: View) {
        layout_text_view_area.background =
            ContextCompat.getDrawable(this, com.lifesolutions.bd.R.color.color_post_bg_five)

        colorCode = "#FC1587"
        text_view_aa_five.visibility = View.GONE
        icon_check_mark_five.visibility = View.VISIBLE

        text_view_aa_one.visibility = View.VISIBLE
        icon_check_mark_one.visibility = View.GONE
        text_view_aa_two.visibility = View.VISIBLE
        icon_check_mark_two.visibility = View.GONE
        text_view_aa_three.visibility = View.VISIBLE
        icon_check_mark_three.visibility = View.GONE
        text_view_aa_four.visibility = View.VISIBLE
        icon_check_mark_four.visibility = View.GONE
        text_view_aa_six.visibility = View.VISIBLE
        icon_check_mark_six.visibility = View.GONE
        text_view_aa_seven.visibility = View.VISIBLE
        icon_check_mark_seven.visibility = View.GONE
    }

    fun onPickColorSix(view: View) {
        layout_text_view_area.background =
            ContextCompat.getDrawable(this, com.lifesolutions.bd.R.color.color_post_bg_six)

        colorCode = "#293042"
        text_view_aa_six.visibility = View.GONE
        icon_check_mark_six.visibility = View.VISIBLE

        text_view_aa_one.visibility = View.VISIBLE
        icon_check_mark_one.visibility = View.GONE
        text_view_aa_two.visibility = View.VISIBLE
        icon_check_mark_two.visibility = View.GONE
        text_view_aa_three.visibility = View.VISIBLE
        icon_check_mark_three.visibility = View.GONE
        text_view_aa_four.visibility = View.VISIBLE
        icon_check_mark_four.visibility = View.GONE
        text_view_aa_five.visibility = View.VISIBLE
        icon_check_mark_five.visibility = View.GONE
        icon_check_mark_seven.visibility = View.VISIBLE
        icon_check_mark_seven.visibility = View.GONE
    }

    fun onPickColorSeven(view: View) {
        layout_text_view_area.background =
            ContextCompat.getDrawable(this, com.lifesolutions.bd.R.color.color_post_bg_seven)

        colorCode = "#3575D3"
        text_view_aa_seven.visibility = View.GONE
        icon_check_mark_seven.visibility = View.VISIBLE

        text_view_aa_one.visibility = View.VISIBLE
        icon_check_mark_one.visibility = View.GONE
        text_view_aa_two.visibility = View.VISIBLE
        icon_check_mark_two.visibility = View.GONE
        text_view_aa_three.visibility = View.VISIBLE
        icon_check_mark_three.visibility = View.GONE
        text_view_aa_four.visibility = View.VISIBLE
        icon_check_mark_four.visibility = View.GONE
        text_view_aa_five.visibility = View.VISIBLE
        icon_check_mark_five.visibility = View.GONE
        text_view_aa_six.visibility = View.VISIBLE
        icon_check_mark_six.visibility = View.GONE
    }


    private fun decreaseLayoutHeight() {
        // Gets linearlayout
        val layout: LinearLayout = layout_text_view_area
        val params: ViewGroup.LayoutParams = layout.layoutParams
        // params.width = 100
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        // params.height = 400
        // params.height = resources.getDimensionPixelSize(R.dimen.post_layout_height)
        // params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, resources.displayMetrics).toInt()
        layout.background = ContextCompat.getDrawable(this, com.lifesolutions.bd.R.color.colorPrimary)
        horizontal_color_picker.visibility = View.GONE
        // Scroll
        et_post_desc_kt.setScroller(Scroller(this))
        et_post_desc_kt.isVerticalScrollBarEnabled = true

        et_post_desc_kt.gravity = Gravity.START
        et_post_desc_kt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        et_post_desc_kt.setTextColor(
            ContextCompat.getColor(
                applicationContext,
                com.lifesolutions.bd.R.color.color_post_black
            )
        )
        et_post_desc_kt.setHintTextColor(
            ContextCompat.getColor(
                applicationContext,
                com.lifesolutions.bd.R.color.colorPrimaryDark
            )
        )
        view_border_kt.visibility = View.VISIBLE
        layout.layoutParams = params
    }


    private fun resumeOldState() {
        // Gets linearlayout
        val layout: LinearLayout = layout_text_view_area
        val params: ViewGroup.LayoutParams = layout.layoutParams

        params.height = resources.getDimensionPixelSize(com.lifesolutions.bd.R.dimen.post_layout_color_height)
        layout_text_view_area.setBackgroundColor(Color.parseColor(colorCode))
        et_post_desc_kt.gravity = Gravity.CENTER
        horizontal_color_picker.visibility = View.VISIBLE
        // layout.background =  ContextCompat.getDrawable(this, Color.parseColor(colorCode))
        et_post_desc_kt.isVerticalScrollBarEnabled = false
        et_post_desc_kt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23f)
        et_post_desc_kt.setTextColor(
            ContextCompat.getColor(
                applicationContext,
                com.lifesolutions.bd.R.color.colorPrimary
            )
        )
        et_post_desc_kt.setHintTextColor(
            ContextCompat.getColor(
                applicationContext,
                com.lifesolutions.bd.R.color.color_post_hint
            )
        )
        view_border_kt.visibility = View.GONE
        layout.layoutParams = params
    }

    /**
     * On Click Image Pick Button
     * On Remove Image Button
     */
    fun onPickImage(view: View) {
        chooseImage()
    }
    fun onPickVideo(view: View) {
        chooseVideo()

    }

    private fun uploadVideo(uri: Uri?) {
        progress.showLoadingBar("Uploading...")
        if (uri != null) {
            val reference: StorageReference = myRef.child(
                System.currentTimeMillis().toString() +
                        "." + getFileExtension(uri!!)
            )
            reference.putFile(uri!!)
                .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    // progressBar.setVisibility(View.INVISIBLE)
                    progress.dismissLoadingBar()
                    Toast.makeText(
                        applicationContext,
                        "Upload successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    val uri: Task<Uri> = taskSnapshot.storage.downloadUrl
                    while (!uri.isComplete());
                    val url2: Uri? = uri.getResult()

                    dowlvideoUri = url2.toString()

                    // toast(dowlvideoUri!!)
                    postWithVideoUpload()
                })
                .addOnFailureListener(OnFailureListener { e ->
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT)
                        .show()
                })
        } else {
            Toast.makeText(applicationContext, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFileExtension(videoUri: Uri): String? {
        val cR: ContentResolver = contentResolver
        val mime: MimeTypeMap = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(videoUri))
    }


    private fun chooseVideo() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "video/*"
        startActivityForResult(galleryIntent, PICK_VIDEO_REQUEST)
    }

    fun onRemovePickImage(view: View) {
        compressedImage = null
        isImagePicked = false
        layout_image_view_area_kt.visibility = View.GONE

        if (postDesc.length > 150) {
            decreaseLayoutHeight()
        } else if (!linkList.isNullOrEmpty()) {
            decreaseLayoutHeight()
            layout_link_preview.visibility = View.VISIBLE
        } else {
            resumeOldState()
        }
    }

    /**
     * Init User
     */

    private fun initUserInfo() {
        val authId  = FirebaseAuth.getInstance().currentUser?.uid



        val ref = userPathRef.child(authId!!)

        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.w(TAG, "User Info From database" )
                if (dataSnapshot.exists()) {
                    currentUser = dataSnapshot.getValue(UserKt::class.java)
                    if (currentUser?.imageThumbnail!!.isNotEmpty() && currentUser?.imageThumbnail != "none") {
                        Glide.with(this@CreatePostKtActivity)
                            .load(currentUser?.imageThumbnail)
                            .into(iv_profile_author)
                    }
                    tv_profile_author_name?.text = currentUser?.firstName
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })

    }


    /**
     * Image Upload and Permission
     */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    toast("Failed to open picture!")
                    return
                }
                try {
                    val actualImage: File = FileUtil.from(this, data.data)
                    imuri = data.data
                    customCompressImage(actualImage)
                } catch (e: IOException) {
                    toast("Failed to read picture data!")
                    e.printStackTrace()
                }
            }
        } else if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK
            && data != null && data.getData() != null) {

            if (resultCode == Activity.RESULT_OK) {
                // pro.setVisibility(View.VISIBLE)
                isVideoPicked = true
                videoUri = data.data!!
                btn_pick_video_for_post.text = "Video Selected"
                layout_link_preview.visibility = View.GONE
                decreaseLayoutHeight()
                et_post_desc_kt.visibility = View.GONE
                // UploadVideo(videoUri)


            }
        }
    }

    private fun chooseImage() {
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, STORAGE_REQUEST_CODE)
        } else {
            if (Build.VERSION.SDK_INT > 29) {
                CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this)
            } else {
                val galleryIntent = Intent(Intent.ACTION_PICK)
                galleryIntent.type = "image/*"
                startActivityForResult(galleryIntent, REQUESTCODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    val storageRequestAccepted =
                        grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (storageRequestAccepted) {
                        chooseImage()
                    } else {
                        Toast.makeText(this, "Storage permission required", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }


    private fun customCompressImage(actualImage: File?) {
        animatedLoading.showAnimatedLoading()
        actualImage?.let { imageFile ->
            lifecycleScope.launch {
                // Full custom
                compressedImage = Compressor.compress(this@CreatePostKtActivity, imageFile) {
                    resolution(500, 500)
                    quality(30)
                    format(Bitmap.CompressFormat.JPEG)
                    size(1_097_152) // 1 MB
                }

                // Set Image View
                compressedImage?.let {
                    isImagePicked = true
                    layout_link_preview.visibility = View.GONE
                    decreaseLayoutHeight()
                    layout_image_view_area_kt.visibility = View.VISIBLE
                    post_picked_image_view_kt.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
                    animatedLoading.hideAnimatedLoading()
                    // Toast.makeText(this@CreatePostKtActivity, getReadableFileSize(it.length()), Toast.LENGTH_LONG).show()
                }
            }
        } ?: toast("Please choose an image!")
    }


    private fun getReadableFileSize(size: Long): String {
        if (size <= 0) {
            return "0"
        }
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (kotlin.math.log10(size.toDouble()) / kotlin.math.log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }


    private fun getRealPathFromURI(context: Activity, contentURI: Uri?): String? {
        val projection =
            arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.managedQuery(
            contentURI, projection, null,
            null, null
        )
            ?: return null
        val column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        return if (cursor.moveToFirst()) {
            // cursor.close();
            cursor.getString(column_index)
        } else null
        // cursor.close();
    }


    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }


    /**
     * On Publish Post
     */
    fun onPublishPost(view: View) {

        if (postDesc.trim().isEmpty() && compressedImage == null && videoUri == null) {
            toast("Invalid! No text or image on your post")
            return
        }
        // Show Prgoress
        progress.showLoadingBar("Please wait...")

        when {
            compressedImage != null -> {
                postWithImageUpload()
            }
            compressedImage == null && postDesc.isNotEmpty() && linkList.isNullOrEmpty() -> {
                postWithOnlyText()
            }
            compressedImage == null && !linkList.isNullOrEmpty() -> {
                postWithLink()
            }
            videoUri != null && isVideoPicked -> {
                uploadVideo(videoUri)
            }
            else -> {
                postWithOnlyText()
            }
        }

    }

    /**
     * Post Database Upadate
     * Post with Image
     * Post With Only Text
     * Post with LInk
     */
    // Post with Image
    private fun postWithImageUpload() {

        val date = System.currentTimeMillis()
        val permission = true
        val hasLink = false

        try {
            // compressedImage?.name?.let { toast(it) }
            val folderPathPart = RequestBody.create(MultipartBody.FORM, "post_images_from_oct")
            val imageFilePart = RequestBody.create(
                MediaType.parse("image/jpeg"),
                compressedImage!!
            )
            val file = MultipartBody.Part.createFormData(
                "imageFile",
                date.toString() +  compressedImage!!.name,
                imageFilePart
            )
            val builder = Retrofit.Builder()
                .baseUrl("https://ftp.starnotesocial.com/")
                .addConverterFactory(GsonConverterFactory.create())
            val retrofit = builder.build()
            val client = retrofit.create(ImageServerClient::class.java)
            val call = client.uploadImage(folderPathPart, file)

            call.enqueue(object : Callback<ImageUpload> {
                override fun onResponse(
                    call: Call<ImageUpload>,
                    response: Response<ImageUpload>
                ) {
                    if (response.isSuccessful) {
                        val imageUrlThumb = response.body()?.downloadUrlRes
                        Log.w(TAG, "onResponse: -> $imageUrlThumb")
                        // val myRef = FirebaseDatabase.getInstance().getReference("Posts").push()
                        val myRef = FirebaseDatabase.getInstance().getReference("Feeds").push()
                        val postId = myRef.key

                        val newPost = PostKt(
                            currentUser?.uID,
                            currentUser?.firstName,
                            currentUser?.lastName,
                            currentUser?.imageThumbnail,
                            "post",
                            privacy,
                            ServerValue.TIMESTAMP,
                            postId,
                            postDesc,
                            imageUrlThumb,
                            imageUrlThumb,
                            "",
                            permission,
                            hasLink
                        )

                        myRef.setValue(newPost).addOnSuccessListener { aVoid: Void? ->
                            progress.dismissLoadingBar()
                            toast("Post Added Successfully")

                            // givePostBonus(currentUser?.uID!!)
                            Utils.addPostToDB(newPost, currentUser?.uID!!)
                            navigateActi()
                        }.addOnFailureListener {
                            progress.dismissLoadingBar()
                            toast("Something went wrong")
                        }
                    }
                }

                override fun onFailure(call: Call<ImageUpload>, t: Throwable) {
                    progress.dismissLoadingBar()
                    Log.d(TAG, t.message.toString())
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun navigateActi() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }

    //post with video upload
    private fun postWithVideoUpload() {

        val date = System.currentTimeMillis()
        val permission = true
        val hasLink = false
        val myRef = FirebaseDatabase.getInstance().getReference("VideoFeeds").push()
        val postId = myRef.key

        val rp =   getRealPathFromURI(this,videoUri)

        val thumb = ThumbnailUtils.createVideoThumbnail(rp!!, MediaStore.Images.Thumbnails.MINI_KIND)

        val requestOptions = RequestOptions()


        val turi =  getImageUri(applicationContext,thumb!!)


        val tRef = FirebaseStorage.getInstance().getReference("VideoThumbs")

        val uploadTask = tRef.child(System.currentTimeMillis().toString() +
                "." + getFileExtension(videoUri!!)).putFile(turi!!)

        uploadTask.addOnFailureListener {

        }.addOnSuccessListener { taskSnapshot ->

            val uri: Task<Uri> = taskSnapshot.storage.downloadUrl
            while (!uri.isComplete());
            val url2: Uri? = uri.getResult()
          //  Log.d("link","worked")
            dowlThumbUri = url2.toString()

            val newPost = VideoPost(
                currentUser?.uID,
                currentUser?.firstName,
                currentUser?.lastName,
                currentUser?.imageThumbnail,
                "post",
                privacy,
                ServerValue.TIMESTAMP,
                postId,
                postDesc,
                dowlThumbUri,
                dowlvideoUri
            )

            myRef.setValue(newPost).addOnSuccessListener { aVoid: Void? ->
                progress.dismissLoadingBar()
                toast("Post Added Successfully")
                givePostBonus(currentUser?.uID!!)
                // Utils.addPostToDB(newPost, currentUser?.uID!!)
            }.addOnFailureListener {
                progress.dismissLoadingBar()
                toast("Something went wrong")
            }



        }










    }



    private fun postWithOnlyText() {
        val date = System.currentTimeMillis()
        val permission = true
        val hasLink = false

        // val myRef = FirebaseDatabase.getInstance().getReference("Posts").push()
        val myRef = FirebaseDatabase.getInstance().getReference("Feeds").push()
        val postId = myRef.key

        val newPost = PostKt(
            currentUser?.uID,
            currentUser?.firstName,
            currentUser?.lastName,
            currentUser?.imageThumbnail,
            "post",
            privacy,
            ServerValue.TIMESTAMP,
            postId,
            postDesc,
            "none",
            "none",
            colorCode,
            permission,
            hasLink
        )

        myRef.setValue(newPost).addOnSuccessListener { aVoid: Void? ->
            progress.dismissLoadingBar()
            toast("Post Added Successfully")
            givePostBonus(currentUser?.uID!!)
            Utils.addPostToDB(newPost, currentUser?.uID!!)
        }

            .addOnFailureListener {
                progress.dismissLoadingBar()
                toast("Something went wrong")
            }
    }

    private fun postWithLink() {
        val linkRaw = linkList?.get(0)
        val date = System.currentTimeMillis()
        val permission = true
        val hasLink = true

        // val myRef = FirebaseDatabase.getInstance().getReference("Posts").push()
        val myRef = FirebaseDatabase.getInstance().getReference("Feeds").push()
        val postId = myRef.key

        val newPost = PostKt(
            currentUser?.uID,
            currentUser?.firstName,
            currentUser?.lastName,
            currentUser?.imageThumbnail,
            "post",
            privacy,
            ServerValue.TIMESTAMP,
            postId,
            postDesc,
            "none",
            "none",
            "",
            permission,
            hasLink,
            linkThumb,
            linkTitle,
            linkRaw,
            linkSource
        )

        myRef.setValue(newPost).addOnSuccessListener { aVoid: Void? ->
            progress.dismissLoadingBar()
            toast("Post Added Successfully")
            givePostBonus(currentUser?.uID!!)
            Utils.addPostToDB(newPost, currentUser?.uID!!)
        }

            .addOnFailureListener {
                progress.dismissLoadingBar()
                toast("Something went wrong")
            }
    }


    /**
     * Give Bonus
     */

    private fun givePostBonus(authorId: String) {
        var points = 0
        val pointRefrence = FirebaseDatabase.getInstance().reference.child("Users").child(authorId)
        val dialog = Dialog(this)
        dialog.setContentView(com.lifesolutions.bd.R.layout.dialog_bonus_layout)
        dialog.setCancelable(false)
        dialog.window!!.attributes.windowAnimations = com.lifesolutions.bd.R.style.animation_dialog
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val collect = dialog.findViewById<TextView>(com.lifesolutions.bd.R.id.refresh_dialog_bonus)
        collect.setOnClickListener { view1: View? ->
            dialog.dismiss()
            pointRefrence.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    points = dataSnapshot.child("points").getValue(Int::class.java)!!
                    points += 1
                    pointRefrence.child("points").setValue(points)
                        .addOnSuccessListener { aVoid: Void? -> finish() }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    toast("Something went wrong, Failed to sent bonus points")
                }
            })
        }
        dialog.show()
    }

    /**
     * URL System
     */

    // SAZIB..
    private fun hasUrl(postDescription: String): MutableList<String>? {
        val links: MutableList<String> = ArrayList()

        val urlRegex =
            "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)"
        val pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE)
        val urlMatcher = pattern.matcher(postDescription)
        while (urlMatcher.find()) {
            links.add(
                postDescription.substring(
                    urlMatcher.start(0),
                    urlMatcher.end(0)
                )
            )
        }
        return links
    }

    private fun showLinkPreview() {
        // UI Update
        decreaseLayoutHeight()

        val urlType = UrlType(linkList?.get(0))

        // Retrofit API Call..
        val call = RetrofitClient
            .getInstance()
            .serverApi
            .urlEncode(urlType)

        call.enqueue(object : Callback<UrlType?> {
            override fun onResponse(
                call: Call<UrlType?>,
                response: Response<UrlType?>
            ) {
                val serverResponse = response.body()

                // Set Data...
                linkThumb = serverResponse?.image
                linkTitle = serverResponse?.title
                linkSource = serverResponse?.source

                // Set Preview...
                layout_link_preview.visibility = View.VISIBLE
                Picasso.get().load(serverResponse?.image).into(iv_link_image)
                tv_link_title.text = serverResponse?.title
                tv_link_raw.text = serverResponse?.source
            }

            override fun onFailure(call: Call<UrlType?>, t: Throwable) {
                toast("Something went wrong when link preview")
            }
        })
    }

    private fun removeLinkPreview() {
        linkList?.clear()
        layout_link_preview.visibility = View.GONE

        if (compressedImage == null) {
            resumeOldState()
        }
    }


}


