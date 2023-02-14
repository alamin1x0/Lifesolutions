package com.lifesolutions.bd.kotlinCode.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.FirebaseDatabase
import com.lifesolutions.bd.Helpers.FileUtils
import com.lifesolutions.bd.Helpers.ImageUtils
import com.lifesolutions.bd.ImageUpload.ImageServerClient
import com.lifesolutions.bd.ImageUpload.ImageUpload
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.data.model.GroupMember
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_create_chat_group.*
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

class CreateChatGroupActivity : AppCompatActivity() {

    private val TAG = "CChatGroupActivity"

    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    // Progress Dialog...
    private lateinit var progress: ViewProgressDialog

    // Selectable Data..
    private val privacyList = arrayOf("Public", "Private")
    private val categoryList =
        arrayOf("Education", "Practice English", "Entertainment", "Lifestyle", "Chatting", "Others")

    // DB Ref
    private lateinit var db: FirebaseDatabase

    // User Input Data..
    private var groupName: String? = null
    private var groupCat: String? = null
    private var groupPrivacy: String? = null

    private val PReqCode = 2
    private var pickedImgUri: Uri? = null
    private var bitmapThumbnail: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_chat_group)

        // Set Actionbar
        setSupportActionBar(toolbar_crete_group)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize Progress Dialog
        progress = ViewProgressDialog(this)
        // Initialize Shared Preferences for User Data
        userPreferences =
            getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!
        // DB Init
        db = FirebaseDatabase.getInstance()

        val mAdapter = ArrayAdapter(
            this,
            R.layout.s_gender_dropdown_view,
            privacyList
        )

        val catAdapter = ArrayAdapter(
            this,
            R.layout.s_gender_dropdown_view,
            categoryList
        )

        // Material Dropdown Spinner
        val editTextDropdownGender: AutoCompleteTextView =
            findViewById(R.id.et_dropdown_group_privacy)
        val editTextDropdownCat: AutoCompleteTextView =
            findViewById(R.id.et_dropdown_group_category)
        editTextDropdownGender.inputType = InputType.TYPE_NULL
        editTextDropdownCat.inputType = InputType.TYPE_NULL
        editTextDropdownGender.setAdapter(mAdapter)
        editTextDropdownCat.setAdapter(catAdapter)


    }


    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun onBtnNextClick(view: View) {
        groupName = et_group_name.text.toString()
        groupCat = et_dropdown_group_category.text.toString()
        groupPrivacy = et_dropdown_group_privacy.text.toString()


        if (groupName!!.isEmpty()) {
            et_group_name.error = "Group name is required"
            et_group_name.requestFocus()
            return
        }

        if (groupPrivacy!!.isEmpty()) {
            et_dropdown_group_privacy.error = "Group privacy is required"
            et_dropdown_group_privacy.requestFocus()
            return
        }

        progress.showLoadingBar("Creating new group...")
        // Save to Database..
        if (pickedImgUri != null) {
            createGroupWithImage()
        } else {
            createGroupInit("none")
        }

    }


    private fun createGroupInit(imageUrl: String?) {
        // val authId = firebaseAuth.currentUser?.uid
        val authId = userPreferences.getString("uID", null)
        val firstName = userPreferences.getString("firstName", null)
        val lastName = userPreferences.getString("lastName", null)
        val profileImage = userPreferences.getString("imageThumbnail", null)
        val timestamp = System.currentTimeMillis()
        val groupId = timestamp.toString()

        val member =
            GroupMember(authId, firstName, lastName, profileImage, timestamp, "admin", timestamp, false)
        val initGroup = GroupConversation(
            groupId,
            groupName,
            groupCat,
            timestamp,
            imageUrl,
            groupPrivacy,
            authId,
            timestamp,
            false,
            "No messages yet"
        )

        val groupRef = db.getReference("GroupConversations").child(groupId)


        if (authId != null) {
            groupRef.setValue(initGroup).addOnSuccessListener {

                groupRef.child("Members").child(authId).setValue(member)
                    .addOnSuccessListener {
                        toast("Group created successfully")
                        progress.dismissLoadingBar()
                        // Navigate...
                        Intent(this, AddMemberActivity::class.java).apply {
                            this.putExtra("groupId", groupId)
                            this.putExtra("adminId", authId)
                            finish()
                            startActivity(this)
                        }
                    }
            }.addOnFailureListener {
                toast("Something went wrong")
                progress.dismissLoadingBar()
            }
        } else {
            progress.dismissLoadingBar()
            toast("Authentication Error!!")
        }

    }


    private fun createGroupWithImage() {
        val bitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri!!.path, 45)

        val baos1 = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos1)

        val imageFile: File

        try {
            val imageUri: Uri? = getImageUri(this, bitmap)
            imageFile = FileUtils.uriToFile(this, imageUri)

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

            val client = retrofit.create(ImageServerClient::class.java)

            val call = client.uploadImage(folderPathPart, file)


            call.enqueue(object : Callback<ImageUpload> {
                override fun onResponse(call: Call<ImageUpload>, response: Response<ImageUpload>) {
                    if (response.isSuccessful) {
                        val imageUrlThumb = response.body()?.downloadUrlRes
                        createGroupInit(imageUrlThumb)
                    }
                }

                override fun onFailure(call: Call<ImageUpload>, t: Throwable) {
                    Log.w(TAG, t.message.toString())
                }
            })

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    //  Image Pick On Click Button...

    fun onPickImage(view: View) {
        // Log.d(TAG, "Iam Top at  selectImage")
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
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .start(this)
        }
    }


    // Image Pick On Activity Result...
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Log.d(TAG, "Iam Top at  onActivityResult")
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                pickedImgUri = result.uri
                bitmapThumbnail =
                    ImageUtils.getInstant().getCompressedBitmap(pickedImgUri?.path, 20)
                group_image_view.setImageBitmap(bitmapThumbnail)
                Log.d(TAG, bitmapThumbnail.toString())
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                toast(result.error.message.toString())
            }
        }
    }

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "starnote_${System.currentTimeMillis()}",
            null
        )
        return Uri.parse(path)
    }


}