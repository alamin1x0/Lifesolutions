package com.lifesolutions.bd.kotlinCode.ui.home.feed

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kroegerama.imgpicker.BottomSheetImagePicker
import com.kroegerama.imgpicker.ButtonType
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.FindFriendsActivity
import com.lifesolutions.bd.Activities.FullPostActivity
import com.lifesolutions.bd.Api.ResType
import com.lifesolutions.bd.Api.RetrofitClient
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.ImageUpload.ImageServerClient
import com.lifesolutions.bd.ImageUpload.ImageUpload
import com.lifesolutions.bd.Models.NotificationInApp
import com.lifesolutions.bd.kotlinCode.data.model.ActiveUser
import com.lifesolutions.bd.kotlinCode.data.model.PostKt
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.activities.*
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity
import com.lifesolutions.bd.kotlinCode.ui.home.base.BaseFragment
import com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter.FeedAdapterKt
import com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter.UserOnlineViewAdapter
import com.lifesolutions.bd.kotlinCode.ui.user.EditProfileInfoKtActivity
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.toast
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_fragment.rv_active_online_user
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

class FeedFragment : BaseFragment(), FeedItemOnClickListener, BottomSheetImagePicker.OnImagesSelectedListener {

    private val TAG = "FeedFragment"

    private lateinit var viewModel: FeedViewModel
    // Shared Preferences
//    private lateinit var userPreferences: SharedPreferences
//    private lateinit var editor: SharedPreferences.Editor

    // Firebase DB
    private lateinit var database: FirebaseDatabase

    private lateinit var dbPostRef: DatabaseReference
    private lateinit var mReference: DatabaseReference

    private lateinit var mLayoutManager: LinearLayoutManager
    private var feedAdapter: FeedAdapterKt? = null

    // User Info..
    private var authId: String? = null
    private var currentUser: UserKt? = null
    private var hasAccessToCreatePost = false

    // Posts
    private val POST_COUNT = 6
    private var postsContainer: ArrayList<PostKt>? = null
    private var postLastKey: String? = null
    private var finalLastKey: String? = null
    private var isScrolling = false
    private var isFinishedPosts = false
    private var isLoading: Boolean? = null

    // Image Upload
    private var compressedImage: File? = null
    private var compressedImageMulti: ArrayList<File>? = ArrayList()
    private var imageViewBitmap: Bitmap? = null
    private var imageViewBitmapList: ArrayList<Bitmap>? = ArrayList()
    private var pickedImgUris: ArrayList<String> = ArrayList()
    private var postDesc: String = ""
    var c: Int = 0
    private lateinit var animatedLoading: AnimatedLoading


    private lateinit var activeUserRef: DatabaseReference
    private var listenerActiveList: ValueEventListener? = null


//add
private lateinit var userPathRef: DatabaseReference
    private var currentUserFIre: UserKt? = null
    private var uID: String? = null
    var x: Int = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(com.lifesolutions.bd.R.layout.feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)





        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        // Initialize Shared Preferences for User Data
        // userPreferences = activity?.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)!!
        animatedLoading = AnimatedLoading(requireContext())

        swipe_refresh_layout_home_kt.isEnabled = true
        swipe_refresh_layout_home_kt.isRefreshing = true


        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid
        // dbPostRef = FirebaseDatabase.getInstance().reference.child("Posts")
        dbPostRef = FirebaseDatabase.getInstance().reference.child("Feeds")
        userPathRef = database.getReference("Users")
        activeUserRef = database.getReference("ActiveNow")
        uID = FirebaseAuth.getInstance().currentUser?.uid

        getUserInfo()
        getUser()


        // Feed Adapter
        mLayoutManager = LinearLayoutManager(requireContext())
        feedAdapter = FeedAdapterKt(this, requireContext())

        // getActiveUserLists()


        recycler_view_home_kt.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = feedAdapter
        }

        // Get Initial Posts
        getActiveUserLists()
        getInitPosts()
        showNotificationBadges()




        recycler_view_home_kt.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItems = mLayoutManager.childCount
                val totalItem = mLayoutManager.itemCount
                // val scrollOutItems = mLayoutManager.findFirstVisibleItemPosition()
                val lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()


                if (isLoading == false && totalItem == lastVisibleItem + currentItems) {
                    getMorePosts()
                }
            }

        })


        swipe_refresh_layout_home_kt.setOnRefreshListener {

            feedAdapter!!.removeAllItem()
            feedAdapter!!.notifyDataSetChanged()
            shimmer_layout_home_kt.startShimmer()
            shimmer_layout_home_kt.visibility = View.VISIBLE
            getInitPosts()
        }


        // On Crete new Post Click Event
        add_post_layout_home_kt.setOnClickListener {
            if (hasAccessToCreatePost) {
                Intent(requireContext(), CreatePostKtActivity::class.java).apply {
                    this.putExtra("USER_FULL_DATA", currentUser)
                    startActivity(this)
                }
            } else {
                openProfileCompleteAlertDialog()
            }
        }

        // On Search..
        search_button_home_kt.setOnClickListener{
            Intent(requireContext(), FindFriendsActivity::class.java).apply {
                startActivity(this)
            }
        }











        // On Profile Pic Click Event
        profile_image_home_toolbar.setOnClickListener {
            Intent(requireContext(), OwnProfileActivity::class.java).apply {
                startActivity(this)
            }
        }

        // On Live Click
//        ib_live.setOnClickListener {
//            val intent = Intent(context, ChooseLive::class.java)
//            startActivity(intent)
//        }
//
//        // On Video Click
//        video_section.setOnClickListener {
//            val intent = Intent(context, VideoViewActivity::class.java)
//            startActivity(intent)
//
//        }

        ib_pick_image.setOnClickListener {
            pickSingle()
        }

    }

    //address taking field
    private fun checkInfoList() {

        val epicDialog = context?.let { Dialog(it) }
        epicDialog!!.setContentView(com.lifesolutions.bd.R.layout.address_dialog)
        // ID....
        val btnCancel = epicDialog.findViewById<MaterialButton>(com.lifesolutions.bd.R.id.btn_upload_cancel)
        val btnUpload = epicDialog.findViewById<MaterialButton>(com.lifesolutions.bd.R.id.btn_upload_confirm)
        val add =   epicDialog.findViewById<EditText>(com.lifesolutions.bd.R.id.enter_address)
        val name =  epicDialog.findViewById<EditText>(com.lifesolutions.bd.R.id.enter_name)

        Log.d(TAG, "C in dialog = "+ c)

        if(x == 1){
            add.visibility = View.GONE
            name.visibility = View.VISIBLE
        }
        if(x ==2){
            add.visibility = View.VISIBLE
            name.visibility = View.GONE
        }
        if(x==3){
            //add.visibility = View.GONE
            // name.visibility = View.GONE
        }



        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        epicDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        epicDialog.setCancelable(false)



        //Events
        btnCancel.setOnClickListener {
            epicDialog.hide()
        }



        btnUpload.setOnClickListener {
            val addre  = add.text.toString()
            val nam = name.text.toString()

            if(x == 1){
                if (nam.isNullOrEmpty()){
                    name.error = "Enter your name"
                    name.requestFocus()
                    return@setOnClickListener
                }
                userPathRef.child(uID!!).child("searchName")
                    .setValue(nam)

            }
            if(x == 3){
                if (nam.isNullOrEmpty()){
                    name.error = "Enter your name"
                    name.requestFocus()
                    return@setOnClickListener
                } else if (addre.isNullOrEmpty()){
                    add.error = "Enter your address"
                    add.requestFocus()
                    return@setOnClickListener
                }
                userPathRef.child(uID!!).child("searchName")
                    .setValue(nam)
                userPathRef.child(uID!!).child("address")
                    .setValue(addre)
            }
            if(x == 2){
                if (addre.isNullOrEmpty()){
                    add.error = "Enter your address"
                    add.requestFocus()
                    return@setOnClickListener
                }
                userPathRef.child(uID!!).child("address")
                    .setValue(addre)
            }

            btnUpload.isClickable = false
            epicDialog.hide()

        }
        if(x != 0){
            epicDialog.show()
        }



    }

    private fun getUser() {
        val ref = userPathRef.child(authId!!)
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.w(TAG, "User Info From database" )
                if (dataSnapshot.child("address").exists()){
                    currentUserFIre = dataSnapshot.getValue(UserKt::class.java)

                    if(currentUserFIre!!.address!!.isNullOrEmpty()){
                        x = 2
                    }

                    /*if(currentUserFIre!!.address!!.isNullOrEmpty() && currentUserFIre!!.searchName!!.isNullOrEmpty()){
                        c = 3
                    }
                    else if(currentUserFIre!!.address!!.isNullOrEmpty()){
                        c = 2
                    }
                    else if(currentUserFIre!!.searchName!!.isNullOrEmpty()){
                        c = 1
                    }
                   // Toast.makeText(context, "ada "+currentUserFIre!!.searchName, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "C = "+ c)*/
                } else {
                    /*if (dataSnapshot.child("searchName").exists()){
                        c = 2
                    } else {
                        c = 3
                    }*/

                    x = 2
                }

                checkInfoList()

            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })






    }











    private fun getInitPosts() {

        if (!InternetCheck.checkConnection(requireContext())) {
            requireContext().toast("No Internet Connection")
            return
        }

        isLoading = true

        val postReference: Query = dbPostRef.orderByKey().limitToLast(POST_COUNT)

        postReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    val postItems = ArrayList<PostKt>()
                    for (postSnapshot in dataSnapshot.children) {
                        postItems.add(postSnapshot.getValue(PostKt::class.java)!!)
                    }

                    postLastKey = postItems[0].postID
                    postItems.reverse()
                    postItems.removeAt(postItems.size - 1)

                    // Push Post item to Post Container
                    postsContainer = postItems

                    // Recycler Adapter Changed..
                    feedAdapter?.removeAllItem()
                    feedAdapter?.addAllPosts(postsContainer!!)
                    feedAdapter?.notifyDataSetChanged()

                    isLoading = false

                    // Swipe to refresh
                    swipe_refresh_layout_home_kt?.isRefreshing = false

                    // Shimmer Effects
                    shimmer_layout_home_kt?.stopShimmer()
                    shimmer_layout_home_kt?.visibility = View.GONE
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                swipe_refresh_layout_home_kt.isRefreshing = false
                requireContext().toast("Something went wrong while loading posts")
            }

        })

       tv_view_all_active_list.setOnClickListener {
           Intent(requireContext(), AllActiverUserActivity::class.java).apply {
               startActivity(this)
           }
       }

    }



    private fun getMorePosts() {

        // Swipe to refresh loading...
        isLoading = true
        swipe_refresh_layout_home_kt.isEnabled = true
        swipe_refresh_layout_home_kt.isRefreshing = true

        if (!InternetCheck.checkConnection(requireContext())) {
            requireContext().toast("No Internet Connection")
            return
        }

        val postReference: Query = dbPostRef.orderByKey().endAt(postLastKey).limitToLast(POST_COUNT)

        postReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    val postItems = ArrayList<PostKt>()
                    for (postSnapshot in dataSnapshot.children) {
                        postItems.add(postSnapshot.getValue(PostKt::class.java)!!)
                    }
                    postLastKey = postItems[0].postID

                    // If the last post is come
//                    if (postLastKey == finalLastKey) {
//                        isFinishedPosts = true
//                        requireContext().toast("No more Posts")
//                        Log.d(TAG, "FINISHED!!!!")
//                        swipe_refresh_layout_home_kt.isRefreshing = false
//                        return
//                    }

                    finalLastKey = postLastKey
                    postItems.reverse()
                    postItems.removeAt(postItems.size - 1)

                    val newMergePosts = postsContainer?.let { merge(it, postItems) }
                    postsContainer = newMergePosts as ArrayList<PostKt>?

                    // Recycler Adapter...
                    feedAdapter?.removeAllItem()
                    feedAdapter?.addAllPosts(newMergePosts as java.util.ArrayList<PostKt>)
                    feedAdapter?.notifyDataSetChanged()
                    isLoading = false

                    // Swipe to refresh
                    swipe_refresh_layout_home_kt?.isRefreshing = false
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                swipe_refresh_layout_home_kt?.isRefreshing = false
            }

        })
    }

    /**
     * Current User Info
     */

    /**
     * Get Current User Data
     */

    private fun getUserInfo() {
//        val uID = FirebaseAuth.getInstance().currentUser?.uid
        val ref = database.getReference("Users").child(authId!!)

        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentUser = dataSnapshot.getValue(UserKt::class.java)
                    // Set User Image Thumbnail
                    if (currentUser?.imageThumbnail != null && currentUser?.imageThumbnail != "none") {
                        if (profile_image_home_toolbar != null) {
                            Picasso.get().load(currentUser?.imageThumbnail)
                                .into(profile_image_home_toolbar)
                        }

                        if (iv_user_thumb != null) {
                            Picasso.get().load(currentUser?.imageThumbnail)
                                .into(iv_user_thumb)
                        }
                    }

                    // Permission to Create post..
                    if (!currentUser?.firstName.isNullOrEmpty()) {
                        hasAccessToCreatePost = true

                        // Save to Shared Pref...
                        val preference = activity?.getSharedPreferences("com.user.permission", Context.MODE_PRIVATE)
                        val mEditor = preference?.edit()
                        mEditor?.putBoolean("hasFirstName", hasAccessToCreatePost)
                        mEditor?.apply()
                    }

                    // Save Locally...
                    // currentUser?.let { saveUserIdToSharedPref(it) }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        ref.addValueEventListener(userListener)
    }



    /**
     * On Recycler view Item Click
     */
    override fun onRecyclerViewItemClicked(view: View, postItem: PostKt) {

        when (view.id) {

            // User Profile Click Event
            com.lifesolutions.bd.R.id.iv_author_with_image_kt,
            com.lifesolutions.bd.R.id.iv_author_without_image_kt,
            com.lifesolutions.bd.R.id.tv_author_name_with_image_kt,
            com.lifesolutions.bd.R.id.tv_author_name_without_image_kt,
            com.lifesolutions.bd.R.id.user_image_post_item_multi,
            com.lifesolutions.bd.R.id.iv_author_with_video,
            com.lifesolutions.bd.R.id.iv_author_with_link_kt,
            com.lifesolutions.bd.R.id.tv_author_name_with_link_kt,
            com.lifesolutions.bd.R.id.iv_author_image_with_big_text,
            com.lifesolutions.bd.R.id.user_name_post_item_multi,
            com.lifesolutions.bd.R.id.tv_author_name_with_video,
            com.lifesolutions.bd.R.id.tv_author_name_with_big_text-> {

                if (postItem.authorID == authId) {
                    Intent(requireContext(), OwnProfileActivity::class.java).apply {
                        startActivity(this)
                    }
                } else {
                    Intent(requireContext(), UserProfileActivityKt::class.java).apply {
                        this.putExtra("uID", postItem.authorID)
                        startActivity(this)
                    }
                }
            }

            // Comment Button Event
            com.lifesolutions.bd.R.id.layout_btn_comment_with_image,
            com.lifesolutions.bd.R.id.layout_btn_comment_without_image,
            com.lifesolutions.bd.R.id.layout_btn_comment_with_link,
            com.lifesolutions.bd.R.id.layout_btn_comment_multi_image,
            com.lifesolutions.bd.R.id.layout_btn_comment_with_video,
            com.lifesolutions.bd.R.id.layout_btn_comment_with_big_text -> {
                Intent(requireContext(), FullPostActivity::class.java).apply {
                    this.putExtra("Id",postItem.postID)
                    startActivity(this)
                }
            }
            // Share Button Event..
            com.lifesolutions.bd.R.id.share_layout_with_image,
            com.lifesolutions.bd.R.id.share_layout_without_image,
            com.lifesolutions.bd.R.id.share_layout_with_link,
            com.lifesolutions.bd.R.id.share_layout_multi_image,
            com.lifesolutions.bd.R.id.share_layout_with_video,
            com.lifesolutions.bd.R.id.share_layout_with_big_text-> {
                try {
                    val i = Intent(Intent.ACTION_SEND)
                    i.type = "text/plain"
                    i.putExtra(
                        Intent.EXTRA_SUBJECT,
                        requireContext().resources.getString(com.lifesolutions.bd.R.string.app_name)
                    )
                    val shareHint = "https://starnotesocial.com/shared_post/${postItem.postID}"

                    i.putExtra(Intent.EXTRA_TEXT, shareHint)
                    requireContext().startActivity(Intent.createChooser(i, "Choose one"))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "" + e.message, Toast.LENGTH_SHORT).show()
                }
            }

            com.lifesolutions.bd.R.id.layout_post_link_img_item -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(postItem.linkRaw)))
            }

            com.lifesolutions.bd.R.id.btn_more_on_feed_link,
            com.lifesolutions.bd.R.id.btn_more_on_feed_color_text,
            com.lifesolutions.bd.R.id.btn_more_on_feed_big_text,
            com.lifesolutions.bd.R.id.btn_more_on_feed_video,
            com.lifesolutions.bd.R.id.btn_more_on_feed_multi_image,
            com.lifesolutions.bd.R.id.btn_more_on_feed_image -> {
                showMenu(view, postItem)
            }

        }
    }


    private fun showNotificationBadges() {
        val reference = FirebaseDatabase.getInstance().getReference("Notifications").child(authId!!)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var notificationCount = 0
                for (snapshot in dataSnapshot.children) {
                    val notificationInApp = snapshot.getValue(
                        NotificationInApp::class.java
                    )!!
                    if (!notificationInApp.isSeen) {
                        notificationCount++
                    }
                }
                if (notificationCount > 0) {
                    (activity as MainActivity?)?.showNotificationBadge(notificationCount, true)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }


    /**
     * Essential Methods
     * Merge Two Array
     * Material Alert Dialog
     */
    // Merge two array List...
    private fun <T> merge(first: List<T>, second: List<T>): List<T> {
        return first.plus(second)
    }

    private fun openProfileCompleteAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Your Profile is incomplete!")
            .setMessage("Please add required profile information")
            .setNeutralButton("Cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("Update Profile") { dialog, which ->

                Intent(requireContext(), EditProfileInfoKtActivity::class.java).apply {
                    startActivity(this)
                }
            }
            .show()
    }



//
//    private fun getActiveUserLists() {
//
//        val ref = FirebaseDatabase.getInstance().getReference("ActiveNow").orderByKey().limitToLast(10)
//        val users = ArrayList<ActiveUser>()
//
//        ref.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    users.clear()
//                    for (usersData in dataSnapshot.children) {
//                        val user = usersData.getValue(ActiveUser::class.java)
//                        users.add(user!!)
//                    }
//                    initActiveUser(users)
//                }
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//
//        })
//    }


//    private fun initActiveUser(users: List<ActiveUser>) {
//        Log.d(TAG, users.toString())
//        if (users.isNotEmpty()) {
//            if (context != null) {
//                horizontalLayoutManager = LinearLayoutManager(
//                    context, RecyclerView.HORIZONTAL, false
//                )
//
//                horizontalLayoutManager?.initialPrefetchItemCount = 4
//                rv_active_online_user?.apply {
//                    layoutManager = horizontalLayoutManager
//                    setHasFixedSize(true)
//                    adapter = UserOnlineViewAdapter(requireContext(), users)
//                    setRecycledViewPool(viewPool)
//                }
//            }
//        }
//
//
//    }




    /**
     * Bottom Sheet Image Picker
     */

    private fun customCompressImage(actualImage: File?) {
        var cSize = 0
        // animatedLoading.showAnimatedLoading()
        actualImage?.let { imageFile ->
            lifecycleScope.launch {
                // Full custom
                compressedImage = Compressor.compress(requireContext(), imageFile) {
                    resolution(500, 500)
                    quality(40)
                    format(Bitmap.CompressFormat.JPEG)
                    size(1_097_152) // 1 MB
                }
                compressedImageMulti?.add(compressedImage!!)
                if (compressedImageMulti == null) {
                    cSize = 0
                } else {
                    cSize = compressedImageMulti!!.size
                }
                if (c == cSize) {
                    showDialog()
                }

            }
        }

    }

    // On Select Image...
    override fun onImagesSelected(uris: List<Uri>, tag: String?) {
        val actualImageList: ArrayList<File> = ArrayList()
        c = uris.size
        uris.forEach { uri ->
            try {
                pickedImgUris.add(uri.toString())
                val actualImage: File = FileUtil.from(requireContext(), uri)
                customCompressImage(actualImage)
                // actualImageList.add(actualImage)
            } catch (e: IOException) {
                requireContext().toast("Failed to read picture data!")
                e.printStackTrace()
            }
        }

        // Toast.makeText(requireContext(), " --> $$$$$$ ${compressedImageMulti?.size}", Toast.LENGTH_SHORT).show()
    }

    private fun pickSingle() {
        BottomSheetImagePicker.Builder(getString(com.lifesolutions.bd.R.string.file_provider))
            .cameraButton(ButtonType.Button)
            .galleryButton(ButtonType.Button)
            .multiSelect(1, 4)                  //user has to select 3 to 6 images
            .multiSelectTitles(
                com.lifesolutions.bd.R.plurals.pick_multi,           //"you have selected <count> images
                com.lifesolutions.bd.R.plurals.pick_multi_more,      //"You have to select <min-count> more images"
                com.lifesolutions.bd.R.string.pick_multi_limit       //"You cannot select more than <max> images"
            )
            .peekHeight(com.lifesolutions.bd.R.dimen.peekHeight)
            .columnSize(com.lifesolutions.bd.R.dimen.columnSize) //peek height of the bottom sheet
            .requestTag("multi")                //tag can be used if multiple pickers are used
            .show(childFragmentManager)
    }




    // Post with Image
    private fun postWithMultiImageUpload(
        epicDialog: Dialog,
        btnUpload: Button
    ) {

        btnUpload.setBackgroundColor(ContextCompat.getColor(requireContext(), com.lifesolutions.bd.R.color.green))
        btnUpload.text = "Please wait..."

        val date = System.currentTimeMillis()
        val urlList: ArrayList<String> = ArrayList()

        try {

            val folderPathPart = RequestBody.create(MultipartBody.FORM, "post_images_from_oct")
            compressedImageMulti?.forEach {cImage ->
                val imageFilePart = RequestBody.create(
                    MediaType.parse("image/jpeg"),
                    cImage
                )
                val file = MultipartBody.Part.createFormData(
                    "imageFile",
                    date.toString() + cImage.name,
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
                            urlList.add(imageUrlThumb!!)
                            if (c == urlList.size) {
                                createMultiImagePost(epicDialog, urlList)
                            }
                            Log.w(TAG, "onResponse: -> $imageUrlThumb")
                        }
                    }

                    override fun onFailure(call: Call<ImageUpload>, t: Throwable) {
                        // progress.dismissLoadingBar()
                        Log.d(TAG, t.message.toString())
                    }
                })
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


    private fun postWithSingleImageUpload(
        epicDialog: Dialog,
        btnUpload: Button
    ) {

        btnUpload.setBackgroundColor(ContextCompat.getColor(requireContext(), com.lifesolutions.bd.R.color.green))
        btnUpload.text = "Please wait..."

        val date = System.currentTimeMillis()

        try {
            val folderPathPart = RequestBody.create(MultipartBody.FORM, "post_images_from_oct")
            val imageFilePart = RequestBody.create(
                MediaType.parse("image/jpeg"),
                compressedImageMulti!![0]
            )
            val file = MultipartBody.Part.createFormData(
                "imageFile",
                date.toString() + compressedImageMulti!![0].name,
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
                            "public",
                            ServerValue.TIMESTAMP,
                            postId,
                            postDesc,
                            imageUrlThumb,
                            imageUrlThumb,
                            "",
                            true,
                            null
                        )

                        myRef.setValue(newPost).addOnSuccessListener { aVoid: Void? ->
                            compressedImageMulti?.clear()
                            c = 0
                            compressedImage = null
                            imageViewBitmap = null
                            epicDialog.hide()
                            refresh()
                            givePostBonus(currentUser?.uID!!)
                            Utils.addPostToDB(newPost, currentUser?.uID!!)
                        }.addOnFailureListener {
                            epicDialog.hide()
                        }
                    }
                }
                override fun onFailure(call: Call<ImageUpload>, t: Throwable) {
                    // progress.dismissLoadingBar()
                    Log.d(TAG, t.message.toString())
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun createMultiImagePost(epicDialog: Dialog, multiImageUrl: List<String>) {

        val permission = true

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
            "none",
            "none",
            "",
            permission,
            null,
            null,
            null,
            null,
            null,
            null,
            multiImageUrl
        )

        myRef.setValue(newPost).addOnSuccessListener {
            compressedImageMulti?.clear()
            c = 0
            compressedImage = null
            imageViewBitmap = null
            epicDialog.hide()
            refresh()
            givePostBonus(currentUser?.uID!!)
            Utils.addPostToDB(newPost, currentUser?.uID!!)
        }.addOnFailureListener {
            epicDialog.hide()
        }
    }


    private fun showDialog() {
        val epicDialog = Dialog(requireContext())
        epicDialog.setContentView(com.lifesolutions.bd.R.layout.image_view_dialog)
        // ID....
        val btnCancel = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.btn_upload_cancel)
        val btnUpload = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.btn_upload_confirm)
        val imageContainer = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image)
        val imageContainer21 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image2)
        val imageContainer22 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image22)
        val imageContainer31 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image3)
        val imageContainer32 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image32)
        val imageContainer33 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image33)
        val imageContainer41 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image41)
        val imageContainer42 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image42)
        val imageContainer43 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image43)
        val imageContainer44 = epicDialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.iv_picked_image44)

        //lin layouts
        val picture1 = epicDialog.findViewById<LinearLayout>(com.lifesolutions.bd.R.id.singleview)
        val picture2 = epicDialog.findViewById<LinearLayout>(com.lifesolutions.bd.R.id.singleview2)
        val picture3 = epicDialog.findViewById<LinearLayout>(com.lifesolutions.bd.R.id.singleview3)
        val picture4 = epicDialog.findViewById<LinearLayout>(com.lifesolutions.bd.R.id.singleview4)

        val pDesc = epicDialog.findViewById<EditText>(com.lifesolutions.bd.R.id.et_post_desc)

        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        epicDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        epicDialog.setCancelable(false)


        //Events
        btnCancel.setOnClickListener {
            c = 0
            compressedImageMulti?.clear()
            epicDialog.hide()
        }

        //checking images count
        if(c==1){
            compressedImageMulti!![0].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer.setImageBitmap(imageViewBitmap)
            }
        }
        if(c==2){
            picture1.visibility = View.GONE
            picture2.visibility = View.VISIBLE

            compressedImageMulti!![0].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer21.setImageBitmap(imageViewBitmap)
            }

            compressedImageMulti!![1].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer22.setImageBitmap(imageViewBitmap)
            }


        }
        if(c==3){
            picture1.visibility = View.GONE
            picture3.visibility = View.VISIBLE


            compressedImageMulti!![0].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer31.setImageBitmap(imageViewBitmap)
            }

            compressedImageMulti!![1].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer32.setImageBitmap(imageViewBitmap)
            }

            compressedImageMulti!![2].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer33.setImageBitmap(imageViewBitmap)
            }

        }
        if(c==4){
            picture1.visibility = View.GONE
            picture4.visibility = View.VISIBLE

            compressedImageMulti!![0].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer41.setImageBitmap(imageViewBitmap)
            }

            compressedImageMulti!![1].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer42.setImageBitmap(imageViewBitmap)
            }

            compressedImageMulti!![2].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer43.setImageBitmap(imageViewBitmap)
            }

            compressedImageMulti!![3].let {
                imageViewBitmap = BitmapFactory.decodeFile(it.absolutePath)
                imageContainer44.setImageBitmap(imageViewBitmap)
            }

        }

        btnUpload.setOnClickListener {
            postDesc = pDesc.text.toString()
            btnUpload.isClickable = false

            when (c) {
                1 -> {
                    postWithSingleImageUpload(epicDialog, btnUpload)
                }
                in 2..4 -> {
                    postWithMultiImageUpload(epicDialog, btnUpload)
                }
                else -> {
                    Toast.makeText(requireContext(), "Something Went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        epicDialog.show()
    }

    fun refresh() {
        feedAdapter!!.removeAllItem()
        feedAdapter!!.notifyDataSetChanged()
        shimmer_layout_home_kt.startShimmer()
        shimmer_layout_home_kt.visibility = View.VISIBLE
        getInitPosts()
    }
    /**
     * Remove Post
     */


    private fun showDialogEdit(feed: PostKt) {
        val epicDialog = Dialog(requireContext())
        epicDialog.setContentView(com.lifesolutions.bd.R.layout.edit_post_dailog)
        // ID....
        val btnCancel = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.btn_upload_cancel)
        val btnUpload = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.btn_upload_confirm)


        val pDesc = epicDialog.findViewById<EditText>(com.lifesolutions.bd.R.id.et_post_desc)

        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        epicDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        epicDialog.setCancelable(false)


        //Events
        btnCancel.setOnClickListener {
            c = 0
            compressedImageMulti?.clear()
            epicDialog.hide()
        }



        btnUpload.setOnClickListener {
            postDesc = pDesc.text.toString()

            btnUpload.isClickable = false
            EditUserPost(feed,postDesc)
            epicDialog.hide()
            refresh()
        }

        epicDialog.show()
    }



    //edit method
    private fun EditUserPost(feed: PostKt,txt: String) {
        val databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("Feeds").child(feed.postID!!).child("postDescription").setValue(txt).addOnSuccessListener {
            Snackbar.make(feed_main_layout, "Post Edited Successfully", Snackbar.LENGTH_LONG).show()
            database.getReference("UserPosts").child(authId!!).child(feed.postID).child("postDescription").setValue(txt)
        }




    }








    private fun showMenu(v: View, feed: PostKt) {
        val popup = PopupMenu(context, v)
        popup.inflate(com.lifesolutions.bd.R.menu.menu_feed)
        val mDelete = popup.menu.findItem(com.lifesolutions.bd.R.id.item_menu_delete)
        val mEdit = popup.menu.findItem(com.lifesolutions.bd.R.id.edit_post)

        if (currentUser?.uID != feed.authorID) {
            mDelete.isVisible = false
            mEdit.isVisible = false
        }

        popup.setOnMenuItemClickListener{
            when(it.itemId) {
                com.lifesolutions.bd.R.id.item_menu_delete -> {
                    deleteUserPost(feed)
                }
                com.lifesolutions.bd.R.id.item_info -> {
                    if (currentUser?.uID == feed.authorID) {
                        Intent(requireContext(), OwnProfileActivity::class.java).apply {
                            startActivity(this)
                        }
                    } else {
                        Intent(requireContext(), UserProfileActivityKt::class.java).apply {
                            this.putExtra("uID", feed.authorID)
                            startActivity(this)
                        }
                    }
                }
                com.lifesolutions.bd.R.id.edit_post ->{
                        showDialogEdit(feed)
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
            Snackbar.make(feed_main_layout, "Post Deleted Successfully", Snackbar.LENGTH_LONG).show()

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
                Log.d(TAG, "onFailure: ${t.message}")
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

    /**
     * Give Bonus
     */

    private fun givePostBonus(authorId: String) {
        var points = 0
        val pointRefrence = FirebaseDatabase.getInstance().reference.child("Users").child(authorId)
        pointRefrence.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                points = dataSnapshot.child("points").getValue(Int::class.java)!!
                points += 1
                pointRefrence.child("points").setValue(points)
                    .addOnSuccessListener {
                        Snackbar.make(
                            feed_main_layout,
                            "You got 1 bonus points",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                requireContext().toast("Something went wrong, Failed to sent bonus points")
            }
        })
    }


    /**
     * Additional Dialog
     */

    private fun showComingDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(com.lifesolutions.bd.R.layout.dialog_coming_soon_layout)
        dialog.setCancelable(true)
        dialog.window!!.attributes.windowAnimations = com.lifesolutions.bd.R.style.animation_dialog
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }


    private fun initActiveUser(users: List<ActiveUser>) {
        if (users.isNotEmpty()) {
            if (context != null) {
                // Toast.makeText(requireContext(), "${users.size}", Toast.LENGTH_SHORT).show()
                val activeLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val activeViewPool = RecyclerView.RecycledViewPool()

                activeLayoutManager.initialPrefetchItemCount = 4
                rv_active_online_user?.apply {
                    layoutManager = activeLayoutManager
                    setHasFixedSize(true)
                    adapter = UserOnlineViewAdapter(requireContext(), users)
                    setRecycledViewPool(activeViewPool)
                }
            }
        }


    }


    private fun getActiveUserLists() {
        animatedLoading.showAnimatedLoading()
//        val refLimit = activeUserRef.orderByKey().limitToFirst(8)
        val refCount = activeUserRef.orderByKey()

        val users = ArrayList<ActiveUser>()

        listenerActiveList = refCount.addValueEventListener(object : ValueEventListener {

            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val activeUserCount = dataSnapshot.childrenCount
                   // txt_view_online_count_user?.text = "($activeUserCount)"
                    users.clear()
                    for (usersData in dataSnapshot.children) {

                        if (users.size == 8) {
                            break
                        }
                        val user = usersData.getValue(ActiveUser::class.java)

                        if (user?.firstName.isNullOrEmpty()) {
                            continue
                        }
                        users.add(user!!)
                    }
                    initActiveUser(users)
                    animatedLoading.hideAnimatedLoading()
                } else {
                    animatedLoading.hideAnimatedLoading()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                animatedLoading.hideAnimatedLoading()
                TODO("Not yet implemented")
            }


        })

//        refCount.addValueEventListener(object: ValueEventListener {
//
//            @SuppressLint("SetTextI18n")
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    val activeUserCount = dataSnapshot.childrenCount
//                    txt_view_title_with_online_count?.text = "Online ($activeUserCount)"
//                }
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })

    }


}