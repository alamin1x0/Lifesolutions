package com.lifesolutions.bd.kotlinCode.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.FullPostActivity
import com.lifesolutions.bd.Api.ResType
import com.lifesolutions.bd.Api.RetrofitClient
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.ImageUpload.ImageServerClient
import com.lifesolutions.bd.ImageUpload.ImageUpload
import com.lifesolutions.bd.kotlinCode.data.model.FriendData
import com.lifesolutions.bd.kotlinCode.data.model.PostKt
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.adapter.FriendGridViewAdapter
import com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener
import com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter.FeedAdapterKt
import com.lifesolutions.bd.kotlinCode.ui.user.EditProfileInfoKtActivity
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.android.synthetic.main.activity_own_profile.*
import kotlinx.android.synthetic.main.activity_own_profile.iv_verified_badge
import kotlinx.android.synthetic.main.activity_own_profile.rv_user_post_profile
import kotlinx.android.synthetic.main.activity_own_profile.talk_time_count
import kotlinx.android.synthetic.main.activity_own_profile.user_bio_profile
import kotlinx.android.synthetic.main.activity_own_profile.last_call_time
import kotlinx.android.synthetic.main.activity_user_profile_kt.*
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class OwnProfileActivity : AppCompatActivity(), FeedItemOnClickListener {
    private val TAG = "OwnProfileActivity"

    // Progress Dialog
    private lateinit var viewProgressDialog: ViewProgressDialog
    // Firebase DB
    private lateinit var database: FirebaseDatabase
    // Shared Preferences
    private lateinit var userPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var authId: String? = null
    private var currentUser: UserKt? = null
    private var isVerified = false
    private var userReferCode = false
    private var coverImage = ""
    private var friendsCount = 0

    // Feed
    private var feedAdapter: FeedAdapterKt? = null
    private val allPosts = ArrayList<PostKt>()

    // Upload..
    private var isCoverImage = false
    private val PReqCode = 2
    private var pickedImgUri: Uri? = null
    private var imageUrlThumb: String? = null

    private var compressedImage: File? = null
    private var imageViewBitmap: Bitmap? = null

    private lateinit var animatedLoading: AnimatedLoading


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lifesolutions.bd.R.layout.activity_own_profile)

        // Initialize Shared Preferences for User Data
        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)
        animatedLoading = AnimatedLoading(this)

        // DB Initialize...
        database = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid

        feedAdapter = FeedAdapterKt(this, applicationContext)

        viewProgressDialog = ViewProgressDialog(this)

        rv_user_post_profile.apply {
            setHasFixedSize(true)
            adapter = feedAdapter
        }

        getTalkTime()
        getUserInfo()
        getFriendLists()
        // Get Initial Posts
        getInitPosts()

        // Inti Click Events
        btn_upload_profile_photo.setOnClickListener{
            isCoverImage = false
            selectImage()
        }
        btn_upload_cover_photo.setOnClickListener {
            isCoverImage = true
            selectImage()
        }

    }

    /**
     * Click Methods
     */

    fun onClickEditProfile(view: View) {
        startActivity(Intent(this, EditProfileInfoKtActivity::class.java))
    }

    fun onClickPrivacy(view: View) {
        Snackbar.make(layout_own_profile, "Coming Soon!", Snackbar.LENGTH_SHORT).show()

    }

    fun backToHome(view: View) {
        finish()
    }

    fun onClickApplyReferCode(view: View) {
        openApplyReferDialog()
    }

    fun onClickSeeAllFriends(view: View) {
        startActivity(Intent(this, AllFriendsListActivity::class.java))
    }

    fun onClickSeeAllPosts(view: View) {
        startActivity(Intent(this, AllUserPostActivity::class.java))

    }


    //get talk time
    fun getTalkTime(): Unit {
        val ref =
            FirebaseDatabase.getInstance().getReference("UsersCallData").child(authId!!).child("audioCall").child("duration")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    //  val (duration) = dataSnapshot.getValue(CallHistory::class.java)!!
                    val time = Integer.parseInt( dataSnapshot.value.toString())

                    if(time < 60) {
                        talk_time_count.setText(""+time + " Seconds")
                    }
                    else{
                        val min = time/ 60;
                        val sec = time % 60;
                        talk_time_count.setText(""+min + " Minutes" +" "+sec + " Seconds")
                    }

                    /*         else if(time >= 3600){
                                 val min = time/ 3600;
                                 val sec = time % 3600;
                                 talk_time_count.setText(""+min + " Hours" +" "+sec + " Minutes")
                             }*/


                } else {
                    talk_time_count.setText("No Calls Yet")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        FirebaseDatabase.getInstance().getReference("UsersCallData").child(authId!!).child("lastCallDuratiuon").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val time = Integer.parseInt( dataSnapshot.value.toString())

                    if(time < 60) {
                        last_call_time.setText("Last Call : "+time + " Seconds")
                    }
                    else if(time >= 3600){
                        val min = time/ 3600;
                        val sec = time % 3600;
                        last_call_time.setText("Last Call : "+min + " Hours" +" "+sec + " Minutes")
                    }
                    else{
                        val min = time/ 60;
                        val sec = time % 60;
                        last_call_time.setText("Last Call : "+min + " Minutes" +" "+sec + " Seconds")
                    }
                } else {
                    last_call_time.setText("Last call data not available")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }










    /**
     * Get Current User Data
     */

    private fun getUserInfo() {
        animatedLoading.showAnimatedLoading()
        val ref = database.getReference("Users").child(authId!!)

        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("isVerified").exists()) {
                        isVerified = dataSnapshot.child("isVerified").getValue(Boolean::class.java)!!
                    }
                    if (dataSnapshot.child("useReferCode").exists()) {
                        userReferCode = dataSnapshot.child("useReferCode").getValue(Boolean::class.java)!!
                    }
                    if (dataSnapshot.child("coverImageHD").exists()) {
                        coverImage = dataSnapshot.child("coverImageHD").getValue(String()::class.java)!!
                    }
                    currentUser = dataSnapshot.getValue(UserKt::class.java)
                    updateUserUi()
                }
                animatedLoading.hideAnimatedLoading()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                animatedLoading.hideAnimatedLoading()
            }
        }

        ref.addValueEventListener(userListener)
    }

    @SuppressLint("SetTextI18n")
    private fun updateUserUi() {

        tv_full_name.text = "${currentUser?.firstName} ${currentUser?.lastName}"

        if (currentUser?.imageThumbnail != "none") {
            Picasso.get().load(currentUser?.imageThumbnail).into(profile_image_profile)
        }

        // Address
        if (!currentUser?.address.isNullOrEmpty()) {
            address_profile.text = currentUser?.address
        } else {
            address_profile.text = "No Address"
        }

        // Phone
        if (!currentUser?.phone.isNullOrEmpty()) {
            phone_profile.text = currentUser?.phone
        } else {
            phone_profile.visibility = View.GONE
        }

        // Email
        if (!currentUser?.email.isNullOrEmpty()) {
            email_profile.text = currentUser?.email
        } else {
            email_profile.visibility = View.GONE
        }

        // Study Info
        if (!currentUser?.bio.isNullOrEmpty()) {
            user_bio_profile.text = currentUser?.bio
        } else {
            user_bio_profile.text = "No bio added yet"
        }

        // Study Info
        if (!currentUser?.studyInfo.isNullOrEmpty()) {
            studentInfo_profile.text = currentUser?.studyInfo
        } else {
            studentInfo_profile.text = "No study info yet"
        }

        // Blood Group
        if (!currentUser?.bloodGroup.isNullOrEmpty()) {
            blood_group_profile.text = currentUser?.bloodGroup
        } else {
            blood_group_profile.visibility = View.GONE
        }

        // BirthDate
        if (currentUser?.birthDate != null) {
            birth_date_profile.text = getBirthDate(currentUser?.birthDate!!)
        } else {
            birth_date_profile.visibility = View.GONE
        }

        // Join Date
        if (currentUser?.registeredTime != null) {
            joined_profile.text = getJoinedDate(currentUser?.registeredTime!!)
        } else {
            joined_profile.text = "No birthdate added"
        }

        // Verified Badge
        if (isVerified) {
            iv_verified_badge.visibility = View.VISIBLE
        } else {
            iv_verified_badge.visibility = View.GONE
        }

        // Cover Image
        if (!coverImage.isNullOrEmpty()) {
            Picasso.get().load(coverImage).into(cover_picture_profile)
        } else {
            iv_verified_badge.visibility = View.GONE
        }

        // Apply Refer Code Button
        if (userReferCode) {
            btn_apply_refer_code.visibility = View.GONE
        } else {
            btn_apply_refer_code.visibility = View.VISIBLE
        }

    }


    private fun getFriendLists() {

        val ref = FirebaseDatabase.getInstance().getReference("FriendsList").child(authId!!).orderByKey()
        val users = ArrayList<FriendData>()

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    friendsCount = dataSnapshot.childrenCount.toInt()
                    tv_friends_count.text = friendsCount.toString()
                    users.clear()
                    for (usersData in dataSnapshot.children) {
                        if (users.size >= 12) {
                            break
                        }
                        val user = usersData.getValue(FriendData::class.java)
                        users.add(user!!)
                    }
                    initFriendsList(users)
                } else {
                    btn_see_all_friend.visibility = View.GONE
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun getInitPosts() {

        if (!InternetCheck.checkConnection(this)) {
            return
        }

        val postRef = database.getReference("UserPosts").child(authId!!).limitToLast(6)


        // val query: Query = database.getReference("Feeds").orderByChild("authorID").equalTo(authId).limitToLast(5)

        postRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    val postItems = ArrayList<PostKt>()
                    for (postSnapshot in dataSnapshot.children) {
                        postItems.add(postSnapshot.getValue(PostKt::class.java)!!)
                    }
                    postItems.reverse()
                    allPosts.addAll(postItems)

                    // Recycler Adapter Changed..
                    feedAdapter?.removeAllItem()
                    feedAdapter?.addAllPosts(postItems)
                    feedAdapter?.notifyDataSetChanged()
                } else {
                    btn_see_all_posts.visibility = View.GONE
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                // TODO
            }

        })
    }



    private fun initFriendsList(users: List<FriendData>) {
        if (users.isNotEmpty()) {
            val gridLayoutManager = GridLayoutManager(
                this, 3, RecyclerView.VERTICAL, false
            )

            rv_friend_list_on_profile.apply {
                layoutManager = gridLayoutManager
                setHasFixedSize(true)
                adapter = FriendGridViewAdapter(this@OwnProfileActivity, users)
//                setRecycledViewPool(viewPool)
            }
        }
    }


    /**
     * On Recycler view Item Click
     */
    override fun onRecyclerViewItemClicked(view: View, postItem: PostKt) {

        when (view.id) {

            // Comment Button Event
            com.lifesolutions.bd.R.id.layout_btn_comment_with_image,
            com.lifesolutions.bd.R.id.layout_btn_comment_without_image,
            com.lifesolutions.bd.R.id.layout_btn_comment_with_link,
            com.lifesolutions.bd.R.id.layout_btn_comment_with_big_text -> {
                Intent(this, FullPostActivity::class.java).apply {
                    this.putExtra("Id",postItem.postID)
                    startActivity(this)
                }
            }
            // Share Button Event..
            com.lifesolutions.bd.R.id.share_layout_with_image,
            com.lifesolutions.bd.R.id.share_layout_without_image,
            com.lifesolutions.bd.R.id.share_layout_with_link,
            com.lifesolutions.bd.R.id.share_layout_with_big_text-> {
                try {
                    val i = Intent(Intent.ACTION_SEND)
                    i.type = "text/plain"
                    i.putExtra(
                        Intent.EXTRA_SUBJECT,
                        resources.getString(com.lifesolutions.bd.R.string.app_name)
                    )
                    val shareHint = "https://starnotesocial.com/shared_post/${postItem.postID}"

                    i.putExtra(Intent.EXTRA_TEXT, shareHint)
                    startActivity(Intent.createChooser(i, "Choose one"))
                } catch (e: Exception) {
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                }
            }

            com.lifesolutions.bd.R.id.layout_post_link_img_item -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(postItem.linkRaw)))
            }

            com.lifesolutions.bd.R.id.btn_more_on_feed_link,
            com.lifesolutions.bd.R.id.btn_more_on_feed_color_text,
            com.lifesolutions.bd.R.id.btn_more_on_feed_big_text,
            com.lifesolutions.bd.R.id.btn_more_on_feed_image -> {
                showMenu(view, postItem)
            }

        }
    }


    /**
     * Remove Post
     * Delete from Server
     * Image Remove
     * Refresh
     */

    private fun showMenu(v: View, feed: PostKt) {
        val popup = PopupMenu(this, v)
        popup.inflate(com.lifesolutions.bd.R.menu.menu_feed)
        val mAuthInfo = popup.menu.findItem(com.lifesolutions.bd.R.id.item_info)

        mAuthInfo.isVisible = false

        popup.setOnMenuItemClickListener{
            when(it.itemId) {
                com.lifesolutions.bd.R.id.item_menu_delete -> {
                    deleteUserPost(feed)
                }
            }
            false
        }
        popup.show()
    }


    private fun deleteUserPost(feed: PostKt) {
        val databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("Feeds").child(feed.postID!!).removeValue().addOnSuccessListener {
            if (feed.postImage != "none") {
                removeServerImage(feed.postImage!!)
            }
            refresh()
            Snackbar.make(layout_own_profile, "Post Deleted Successfully", Snackbar.LENGTH_LONG).show()
            database.getReference("UserPosts").child(authId!!).child(feed.postID).removeValue()
        }
    }

    private fun removeServerImage(imageLink: String) {

        val filUrl = ResType(imageLink)

        // Retrofit API Call..
        val call = RetrofitClient
            .getInstance()
            .serverApi
            .removeImage(filUrl)

        call.enqueue(object : Callback<ResType?> {
            override fun onFailure(call: Call<ResType?>, t: Throwable) {
                // TODO
            }

            override fun onResponse(call: Call<ResType?>, response: Response<ResType?>) {
                if (response.isSuccessful) {
                    val serverResponse: ResType = response.body()!!
                    Log.e(TAG, "$serverResponse")
                    // requireContext().toast(serverResponse.message)
                } else {
                    Log.e(TAG, "onResponse: Error")
                }
            }
        })

    }

    private fun refresh() {
        feedAdapter!!.removeAllItem()
        feedAdapter!!.notifyDataSetChanged()
        getInitPosts()
    }


    /**
     * Image Upload
     * Cover Image Upload
     * Profile Image Upload
     */

    private fun selectImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                toast("Please accept for required permission")
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
                .start(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {

                if (data == null) {
                    toast("Failed to open picture!")
                    return
                }
                try {
                    pickedImgUri = result.uri
                    val actualImage: File = FileUtil.from(this, pickedImgUri)
                    customCompressImage(actualImage)
                } catch (e: IOException) {
                    toast("Failed to read picture data!")
                    e.printStackTrace()
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

    private fun openImageViewDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(com.lifesolutions.bd.R.layout.image_upload_dialog)
        dialog.setCancelable(false)
        dialog.window?.attributes?.windowAnimations = com.lifesolutions.bd.R.style.animation_dialog
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // ID
        val imageViewCover = dialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.cover_image_image_upload_dialog)
        val imageViewProfile = dialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.profile_image_image_upload_dialog)
        val btnOk = dialog.findViewById<TextView>(com.lifesolutions.bd.R.id.upload_button_image_dialog)
        val btnCancel = dialog.findViewById<TextView>(com.lifesolutions.bd.R.id.quit_button_image_dialog)

        // Visibility & View
        if (isCoverImage) {
            imageViewProfile.visibility = View.GONE
            imageViewCover.visibility = View.VISIBLE
            imageViewCover.setImageBitmap(imageViewBitmap)
        } else {
            imageViewProfile.visibility = View.VISIBLE
            imageViewCover.visibility = View.GONE
            imageViewProfile.setImageBitmap(imageViewBitmap)
        }

        btnOk.setOnClickListener { v: View? ->
            if (InternetCheck.checkConnection(this)) {
                if (isCoverImage) {
                    uploadPhoto("cover_images_oct", "coverImageHD")
                    dialog.dismiss()
                } else {
                    uploadPhoto("profile_images_oct", "imageThumbnail")
                    dialog.dismiss()
                }
            } else {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnCancel.setOnClickListener {
            compressedImage = null
            imageViewBitmap = null
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun customCompressImage(actualImage: File?) {
        animatedLoading.showAnimatedLoading()
        actualImage?.let { imageFile ->
            lifecycleScope.launch {
                // Full custom
                compressedImage = Compressor.compress(this@OwnProfileActivity, imageFile) {
                    resolution(500, 500)
                    quality(45)
                    format(Bitmap.CompressFormat.JPEG)
                    size(1_097_152) // 1 MB
                }

                // Set Image View
                compressedImage?.let {
                    imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                    animatedLoading.hideAnimatedLoading()
                    openImageViewDialog()
                }
            }
        } ?: toast("Please choose an image!")
    }

    private fun uploadPhoto(content: String, childPath: String) {
        viewProgressDialog.showLoadingBar()

        val date = System.currentTimeMillis()

        try {
            val folderPathPart = RequestBody.create(MultipartBody.FORM, content)
            val imageFilePart = RequestBody.create(
                MediaType.parse("image/jpeg"),
                compressedImage!!
            )
            val file = MultipartBody.Part.createFormData(
                "imageFile",
                date.toString() + compressedImage!!.name,
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
                        imageUrlThumb = response.body()?.downloadUrlRes
                        database.getReference("Users").child(authId!!).child(childPath).setValue(imageUrlThumb)
                            .addOnSuccessListener {
                                if(!isCoverImage) {
                                    saveDataToSharedPref(imageUrlThumb)
                                }
                                createNewPost("This is my new profile picture")
                                Snackbar.make(layout_own_profile, "Profile Updated Successfully", Snackbar.LENGTH_LONG).show()
                                viewProgressDialog.dismissLoadingBar()
                            }
                            .addOnFailureListener {
                                viewProgressDialog.dismissLoadingBar()
                            }
                    }
                }

                override fun onFailure(
                    call: Call<ImageUpload>,
                    t: Throwable
                ) {
                    viewProgressDialog.dismissLoadingBar()
                    toast(t.message.toString())
                }
            })
        } catch (e: IOException) {
            viewProgressDialog.dismissLoadingBar()
            e.printStackTrace()
        }
    }

    private fun createNewPost(postDesc: String) {

        val permission = true
        val hasLink = false

        val myRef = FirebaseDatabase.getInstance().getReference("Feeds").push()
        val postId = myRef.key

        val newPost = PostKt(
            currentUser?.uID,
            currentUser?.firstName,
            currentUser?.lastName,
            currentUser?.imageThumbnail,
            "post",
            "public",
            ServerValue.TIMESTAMP,
            postId,
            postDesc,
            imageUrlThumb,
            imageUrlThumb,
            "",
            permission,
            hasLink
        )

        myRef.setValue(newPost).addOnSuccessListener {
            // refresh()
            Utils.addPostToDB(newPost, currentUser?.uID!!)
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong to create post", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * Functions
     * Get Birthdate
     * Get Join Date
     */
    private fun getBirthDate(birthDate: Long): String? {
        val date: String
        val formatter =
            SimpleDateFormat("dd - MMMM - yyyy", Locale.getDefault())
        date = formatter.format(birthDate)
        return date
    }

    private fun getJoinedDate(joined: Long): String? {
        val date: String
        val formatter =
            SimpleDateFormat(" MMMM, yyyy", Locale.getDefault())
        date = formatter.format(joined)
        return date
    }

    private fun addPostToDB(posts: List<PostKt>) {
        posts.forEach {
            database.reference.child("UserPosts").child(authId!!).child(it.postID!!)
                .setValue(it)
        }
    }

    private fun saveDataToSharedPref(imageThumbnail: String?) {
        Log.w(TAG, "saveDataToSharedPref: ...........")
        Log.w(TAG, "$imageThumbnail")
        editor = userPreferences.edit()
        editor.putString("imageThumbnail", imageThumbnail)
        editor.apply()
    }


    /**
     * Refer Code Apply
     */

    private fun openApplyReferDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Refer code")
        builder.setCancelable(false)
        val inflater = this.layoutInflater
        val viewInflated = inflater.inflate(com.lifesolutions.bd.R.layout.apply_refer_code_dialog, null)
        val input = viewInflated.findViewById<View>(com.lifesolutions.bd.R.id.et_input_refer_code) as EditText
        builder.setView(viewInflated)
        builder.setPositiveButton(
            "Apply"
        ) { dialog, _ ->
            val referCode = input.text.trim().toString()
            dialog.dismiss()
            checkReferCode(referCode)
        }
        builder.setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.cancel() }
        builder.show()
    }


    private fun checkReferCode(referCode: String) {
        viewProgressDialog.showLoadingBar()

        if (referCode == currentUser?.referCode) {
            viewProgressDialog.dismissLoadingBar()
            Snackbar.make(layout_own_profile, "Oops! You can\'t apply your own code", Snackbar.LENGTH_LONG).show()
            return
        }

        val ref = database.getReference("ReferArea").child(referCode)

        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val referId = dataSnapshot.getValue(String::class.java)
                    giveBonus(referId!!)
                    getBonus()
                    viewProgressDialog.dismissLoadingBar()
                } else {
                    viewProgressDialog.dismissLoadingBar()
                    Snackbar.make(layout_own_profile, "This refer code is not valid", Snackbar.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                viewProgressDialog.dismissLoadingBar()
            }

        })
    }


    private fun giveBonus(refererId: String) {
        val dbRef = database.getReference("Users")
        var points = 0
        var referred = 0
        dbRef.child(refererId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    points = dataSnapshot.child("points").getValue(Int::class.java)!!
                    referred = dataSnapshot.child("referred").getValue(Int::class.java)!!

                    points += 25
                    referred += 1
                    dbRef.child(refererId).child("points").setValue(points)
                    dbRef.child(refererId).child("referred").setValue(referred)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                viewProgressDialog.dismissLoadingBar()
                toast("Something went wrong, Failed to sent bonus points")
            }
        })
    }

    private fun getBonus() {
        val dbRef = database.getReference("Users")
        var points = 0
        dbRef.child(authId!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    points = dataSnapshot.child("points").getValue(Int::class.java)!!
                    points += 50
                    dbRef.child(authId!!).child("points").setValue(points)
                    dbRef.child(authId!!).child("useReferCode").setValue(true)
                    Snackbar.make(layout_own_profile, "You got 50 coin bonus", Snackbar.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                toast("Something went wrong, Failed to sent bonus points")
                viewProgressDialog.dismissLoadingBar()
            }
        })
    }



}