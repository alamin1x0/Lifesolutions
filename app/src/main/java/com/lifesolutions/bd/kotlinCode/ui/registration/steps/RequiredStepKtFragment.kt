package com.lifesolutions.bd.kotlinCode.ui.registration.steps

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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Helpers.FileUtils
import com.lifesolutions.bd.Helpers.ImageUtils
import com.lifesolutions.bd.ImageUpload.ImageServerClient
import com.lifesolutions.bd.ImageUpload.ImageUpload
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.intro.IntroKtActivity
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_required_step_kt.*
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
import java.util.*

class RequiredStepKtFragment : Fragment() {
    private val TAG = "RequiredStepKtFragment"

    // Progress Dialog...
    private lateinit var progress: ViewProgressDialog

    private lateinit var currentAuthUser: UserKt

    // Selectable Data..
    private val GENDERS = arrayOf("Male", "Female", "Others")


    private val PReqCode = 2
    private var pickedImgUri: Uri? = null
    private var bitmapThumbnail: Bitmap? = null

    // private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var authId: String
    // Firebase DB
    private lateinit var database: FirebaseDatabase

    // User Input Data..
    private var firstName: String? = null
    private var lastName: String? = null
    private var gender: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_required_step_kt, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize Intent Put Extra..
        currentAuthUser = activity?.intent!!.getSerializableExtra("Auth_User_Info") as UserKt

        // Initialize Progress Dialog
        progress = ViewProgressDialog(requireContext())

        // Initialize Auth and Firebase..
        // firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference.child("Users")
        authId = FirebaseAuth.getInstance().currentUser?.uid!!

        val mAdapter = ArrayAdapter(
            requireContext(),
            R.layout.s_gender_dropdown_view,
            GENDERS
        )

        // Material Dropdown Spinner
        val editTextDropdownGender: AutoCompleteTextView = requireView().findViewById(R.id.et_dropdown_gender_reg_kt)
        editTextDropdownGender.inputType = InputType.TYPE_NULL
        editTextDropdownGender.setAdapter(mAdapter)

        // On Image Pick Button Click..
        btn_pick_image.setOnClickListener {
            selectImage()
        }

        setCurrentUserInfo()
        setUserReferCode(authId, currentAuthUser.referCode!!)

        //On Next Button Click...
        btn_reg_next_page.setOnClickListener {
            firstName = et_first_name_reg_kt.text.toString()
            lastName = et_last_name_reg_kt.text.toString()
            gender = et_dropdown_gender_reg_kt.text.toString()


            if (firstName!!.isEmpty()) {
                et_first_name_reg_kt.error = "First name is required"
                et_first_name_reg_kt.requestFocus()
                return@setOnClickListener
            }

            if (gender!!.isEmpty()) {
                et_dropdown_gender_reg_kt.error = "Gender name is required"
                et_dropdown_gender_reg_kt.requestFocus()
                return@setOnClickListener
            }


            progress.showLoadingBar("Update your information...")
            // Save to Database..
            if (pickedImgUri != null) {
                registerUserWithImage()
            } else {
                updateUserInfo(null)
            }
        }

        // On Exit...
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                openAlertDialog()
                // isEnabled = false
                // activity?.onBackPressed()
            }
        })

    }


    // Set Current USer Data..
    private fun setCurrentUserInfo() {
        if (!currentAuthUser.firstName.isNullOrEmpty()) {
            et_first_name_reg_kt.setText(currentAuthUser.firstName)
        }

        // Image..
        if (currentAuthUser.imageThumbnail != "none") {
            Picasso.get().load(currentAuthUser.imageThumbnail).into(profile_image_view_reg_kt)
//            Glide.with(this)
//                .load(currentAuthUser.imageThumbnail)
//                .into(profile_image_view_reg_kt)
        }

    }


    //  Image Pick On Click Button...
    private fun selectImage() {
        // Log.d(TAG, "Iam Top at  selectImage")
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(
                    requireContext(),
                    "Please accept for required permission",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PReqCode
                )
            }
        } else {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                    // For Activity use this
                .start(requireContext(), this)
        }
    }


    private fun updateUserInfo(imageUrl: String?) {
        // val authId = firebaseAuth.currentUser?.uid
        val authId = currentAuthUser.id

        val userFieldMap = mutableMapOf<String, Any>()


        userFieldMap["firstName"] = firstName!!
        userFieldMap["lastName"] = lastName!!
        userFieldMap["gender"] = gender!!
        userFieldMap["searchName"] = "${firstName!!.toLowerCase(Locale.ROOT)} ${lastName!!.toLowerCase(Locale.ROOT)}"

        // Image
        if (imageUrl != null) {
            userFieldMap["imageThumbnail"] = imageUrl
            userFieldMap["imageMedium"] = imageUrl
            userFieldMap["imageHD"] = imageUrl
        }

        if (authId != null) {
            dbRef.child(authId).updateChildren(userFieldMap).addOnSuccessListener {
                // activity?.toast("Information Added ($authId)")
                progress.dismissLoadingBar()
                // Navigate...
                navigateToIntroActivity()
//                requiredFragmentToAdditionalFragment()
            }
        } else {
            progress.dismissLoadingBar()
            activity?.toast("Authentication Error!!")
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
                bitmapThumbnail = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri?.path, 20)
                profile_image_view_reg_kt.setImageBitmap(bitmapThumbnail)
                Log.d(TAG, bitmapThumbnail.toString())
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                requireContext().toast(result.error.message.toString())
            }
        }
    }


    private fun registerUserWithImage() {
        val bitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri!!.path, 45)

        val baos1 = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos1)

        val imageFile: File

        try {
            val imageUri: Uri? = getImageUri(requireContext(), bitmap)
            imageFile = FileUtils.uriToFile(requireContext(), imageUri)

            val folderPathPart =
                RequestBody.create(MultipartBody.FORM, "profile_images")

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

                        updateUserInfo(imageUrlThumb)
                        // requireContext().toast(imageUrlThumb.toString())
                    }
                }

                override fun onFailure(call: Call<ImageUpload>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }
            })

        } catch (e: IOException) {
            e.printStackTrace();
        }
    }


    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
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



    private fun openAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Are you want to exit?")
            .setMessage("This required information is need for complete your registration")
            .setNeutralButton("Cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("Exit") { dialog, which ->
                activity?.finish()
                requireContext().toast("Exit")
            }
            .show()
    }

    private fun requiredFragmentToAdditionalFragment() {
        val additionalStepKtFragment = AdditionalStepKtFragment()
        val manager: FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = manager!!.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, additionalStepKtFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    private fun setUserReferCode(uID: String, refCode: String) {
        database.getReference("ReferArea").child(refCode)
            .setValue(uID)
    }

    private fun navigateToIntroActivity() {
        Intent(requireContext(), IntroKtActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }


}