package com.lifesolutions.bd.kotlinCode.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Helpers.FileUtils
import com.lifesolutions.bd.Helpers.ImageUtils
import com.lifesolutions.bd.ImageUpload.ImageServerClient
import com.lifesolutions.bd.ImageUpload.ImageUpload
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_edit_group.*
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

class EditGroupActivity : AppCompatActivity() {

    private var groupId: String? = null

    // DB Ref
    private lateinit var db: FirebaseDatabase

    // Group Info
    private var groupInfo: GroupConversation? = null

    // Progress Dialog...
    private lateinit var progress: ViewProgressDialog

    // Selectable Data..
    private val privacyList = arrayOf("Public", "Private")
    private val categoryList = arrayOf("Education", "Practice English", "Entertainment", "Lifestyle", "Chatting", "Others")

    // User Input Data..
    private var groupName: String? = null
    private var groupCat: String? = null
    private var groupPrivacy: String? = null

    private val PReqCode = 2
    private var pickedImgUri: Uri? = null
    private var bitmapThumbnail: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_group)

        // Set Actionbar
        setSupportActionBar(toolbar_edit_group)
        supportActionBar?.title = "Edit Group"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Initialize Progress Dialog
        progress = ViewProgressDialog(this)


        // DB Init
        db = FirebaseDatabase.getInstance()


        if (intent != null) {
            groupId = intent.getStringExtra("groupId")
        }

        getGroupInfo()

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
            findViewById(R.id.et_dropdown_group_privacy_edit)
        val editTextDropdownCat: AutoCompleteTextView =
            findViewById(R.id.et_dropdown_group_category_edit)
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


    private fun getGroupInfo() {
        val ref = db.getReference("GroupConversations").child(groupId!!)

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    groupInfo = dataSnapshot.getValue(GroupConversation::class.java)
                    updateUI()
                } else {
                    toast("No Group info.")
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                // Log.w(TAG, p0.message)
            }


        })
    }

    private fun updateUI() {
        if (groupInfo?.groupImage != null && groupInfo?.groupImage != "none") {
            Picasso.get().load(groupInfo?.groupImage).into(group_image_view_edit)
        } else {
            Picasso.get().load(R.drawable.ic_group_placeholder).into(group_image_view_edit)
        }

        et_group_name_edit.setText(groupInfo?.groupName)
        et_dropdown_group_category_edit.setText(groupInfo?.groupCat, false)
        et_dropdown_group_privacy_edit.setText(groupInfo?.privacy, false)
    }

    fun onBtnNextClick(view: View) {
        groupName = et_group_name_edit.text.toString()
        groupCat = et_dropdown_group_category_edit.text.toString()
        groupPrivacy = et_dropdown_group_privacy_edit.text.toString()


        if (groupName!!.isEmpty()) {
            et_group_name_edit.error = "Group name is required"
            et_group_name_edit.requestFocus()
            return
        }

        if (groupPrivacy!!.isEmpty()) {
            et_dropdown_group_privacy_edit.error = "Group privacy is required"
            et_dropdown_group_privacy_edit.requestFocus()
            return
        }

        progress.showLoadingBar("Updating group...")
        // Save to Database..
        if (pickedImgUri != null) {
            createGroupWithImage()
        } else {
            createGroupInit("none")
        }
    }


    private fun createGroupInit(imageUrl: String?) {

        val updateFbDb = HashMap<String, Any?>()
        updateFbDb["groupName"] = groupName
        updateFbDb["groupCat"] = groupCat
        updateFbDb["privacy"] = groupPrivacy

        if (imageUrl != "none") {
            updateFbDb["groupImage"] = imageUrl
        } else {
            updateFbDb["groupImage"] = groupInfo?.groupImage
        }

        db.getReference("GroupConversations").child(groupId!!).updateChildren(updateFbDb)
            .addOnSuccessListener {
                progress.dismissLoadingBar()
                toast("Updated successfully")
                finish()
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
                    // Log.w(TAG, t.message.toString())
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
                group_image_view_edit.setImageBitmap(bitmapThumbnail)
               //  Log.d(TAG, bitmapThumbnail.toString())
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