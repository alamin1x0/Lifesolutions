package com.lifesolutions.bd.kotlinCode.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.FullPostActivity
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.*
import com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener
import com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter.FeedAdapterKt
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import com.lifesolutions.bd.kotlinCode.utils.FirebaseAuthorNotification
import com.lifesolutions.bd.kotlinCode.utils.Notify.sendMgsNotification
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import kotlinx.android.synthetic.main.activity_own_profile.*
import kotlinx.android.synthetic.main.activity_user_profile_kt.*
import kotlinx.android.synthetic.main.activity_user_profile_kt.iv_verified_badge
import kotlinx.android.synthetic.main.activity_user_profile_kt.rv_user_post_profile
import kotlinx.android.synthetic.main.activity_user_profile_kt.talk_time_count
import kotlinx.android.synthetic.main.activity_user_profile_kt.user_bio_profile
import kotlinx.android.synthetic.main.activity_user_profile_kt.last_call_time
import java.text.SimpleDateFormat
import java.util.*

class UserProfileActivityKt : AppCompatActivity(), FeedItemOnClickListener {
    private val TAG = "UserProfileActivityKt"

    private var userId: String? = null
    private var isVerified = false
    private var coverImage = ""
    private var currentUser: UserKt? = null

    // Progress Dialog
    private lateinit var viewProgressDialog: ViewProgressDialog

    // Firebase DB
    private lateinit var database: FirebaseDatabase
    private var authId: String? = null

    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    // Temp..
    private var isFriend = false

    // Feed
    private var feedAdapter: FeedAdapterKt? = null

    // Count..
    private var friendsCount = 0
    private var postsCount = 0

    private lateinit var animatedLoading: AnimatedLoading


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile_kt)

        val myIntent = intent
        if (myIntent != null) {
            userId = intent.getStringExtra("uID")
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            finish()
        }

        viewProgressDialog = ViewProgressDialog(this)
        animatedLoading = AnimatedLoading(this)
        // Initialize Shared Preferences for User Data
        userPreferences =
            getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!

        // DB Initialize...
        database = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid

        feedAdapter = FeedAdapterKt(this, applicationContext)


        rv_user_post_profile.apply {
            setHasFixedSize(true)
            adapter = feedAdapter
        }


        getTalkTime()
        getUserInfo()
        getFriendStatus()
        getFriendsCount()
        // Get Initial Posts
        getInitPosts()

        // On Sent Message
        message_user_profile.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PersonalMessageActivity::class.java
                ).putExtra("receiverID", userId)
            )
        }

    }

    /**
     * Click Method
     */

    fun backToHome(view: View) {
        finish()
    }

    fun onClickAddBtn(view: View) {
        sendFriendRequest()
    }

    fun onClickUnFriendBtn(view: View) {}
    fun onClickCancelReqBtn(view: View) {
        removeFriendRequest()
    }

    fun onClickAcceptReqBtn(view: View) {
        acceptFriendRequest()
    }

    //getting talk time
    fun getTalkTime(): Unit {
        val ref =
            FirebaseDatabase.getInstance().getReference("UsersCallData").child(userId!!)
                .child("audioCall").child("duration")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val time = Integer.parseInt( dataSnapshot.value.toString())


                    if (time < 60) {
                        Log.d("TAGss", "time < 60 : ")

                        talk_time_count.setText("" + time + " Seconds")
                    } else {
                        Log.d("TAGss", "ELSE NORMAL: ")

                        val min = time / 60;
                        val sec = time % 60;
                        talk_time_count.setText("" + min + " Minutes" + " " + sec + " Seconds")

                    }
                    /*else if (time >= 3600) {
                        Log.d("TAGss", "time >= 3600 : ")
                        val min = time / 3600;
                        val sec = time % 3600;
                        talk_time_count.setText("" + min + " Hours" + " " + sec + " Minutes")
                    */
                } else {
                    talk_time_count.setText("No Calls Yet")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })


        FirebaseDatabase.getInstance().getReference("UsersCallData").child(userId!!)
            .child("lastCallDuratiuon").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val time = Integer.parseInt(dataSnapshot.value.toString())

                        if (time < 60) {
                            last_call_time.setText("Last Call : " + time + " Seconds")
                        } else if (time >= 3600) {
                            val min = time / 3600;
                            val sec = time % 3600;
                            last_call_time.setText("Last Call : " + min + " Hours" + " " + sec + " Minutes")
                        } else {
                            val min = time / 60;
                            val sec = time % 60;
                            last_call_time.setText("Last Call : " + min + " Minutes" + " " + sec + " Seconds")
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

        val ref = database.getReference("Users").child(userId!!)

        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("isVerified").exists()) {
                        isVerified =
                            dataSnapshot.child("isVerified").getValue(Boolean::class.java)!!
                    }
                    if (dataSnapshot.child("coverImageHD").exists()) {
                        coverImage =
                            dataSnapshot.child("coverImageHD").getValue(String()::class.java)!!
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

        tv_user_name_info.text = "${currentUser?.firstName} ${currentUser?.lastName}"

        // Bio
        if (!currentUser?.bio.isNullOrEmpty()) {
            user_bio_profile.text = currentUser?.bio
        } else {
            user_bio_profile.visibility = View.GONE
        }

        // Image Profile
        if (currentUser?.imageThumbnail != "none") {
            Picasso.get().load(currentUser?.imageThumbnail).into(profile_picture_user_profile)
        }

        // Address
        if (!currentUser?.address.isNullOrEmpty()) {
            address_user_profile.text = currentUser?.address
        } else {
            address_user_profile.text = "No Address"
        }

        // Phone
//        if (!currentUser?.phone.isNullOrEmpty()) {
//            phone_profile.text = currentUser?.phone
//        } else {
//            phone_profile.visibility = View.GONE
//        }

        // Email
//        if (!currentUser?.email.isNullOrEmpty()) {
//            email_profile.text = currentUser?.email
//        } else {
//            email_profile.visibility = View.GONE
//        }

        // Study Info
        if (!currentUser?.studyInfo.isNullOrEmpty()) {
            studentInfo_user_profile.text = currentUser?.email
        } else {
            studentInfo_user_profile.text = "No study info yet"
        }

        // Blood Group
        if (!currentUser?.bloodGroup.isNullOrEmpty()) {
            blood_group_user_profile.text = currentUser?.bloodGroup
        } else {
            blood_group_user_profile.visibility = View.GONE
        }

        // BirthDate
        if (currentUser?.birthDate != null) {
            birth_date_user_profile.text = getBirthDate(currentUser?.birthDate!!)
        } else {
            birth_date_user_profile.visibility = View.GONE
        }

        // Join Date
        if (currentUser?.registeredTime != null) {
            joined_user_profile.text = getJoinedDate(currentUser?.registeredTime!!)
        } else {
            joined_user_profile.text = "No Joined info"
        }

        // Verified Badge
        if (isVerified) {
            iv_verified_badge.visibility = View.VISIBLE
        } else {
            iv_verified_badge.visibility = View.GONE
        }

        // Cover Image
        if (!coverImage.isNullOrEmpty()) {
            Picasso.get().load(coverImage).into(cover_photo_user_profile)
        }

        // Apply Refer Code Button
//        if (userReferCode) {
//            btn_apply_refer_code.visibility = View.GONE
//        } else {
//            btn_apply_refer_code.visibility = View.VISIBLE
//        }

    }

    /**
     * Friend Lifecycle
     */

    private fun getFriendStatus() {
        val refFriend = database.getReference("FriendsList")
        val refRequest = database.getReference("FriendsRequest")
        refFriend.child(authId!!).child(userId!!)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // On Friend View
                        layout_accept_request.visibility = View.GONE
                        layout_add_friend.visibility = View.GONE
                        layout_cancel_request.visibility = View.GONE
                        layout_friend_view.visibility = View.VISIBLE
                    } else {
                        refRequest.child(authId!!).child(userId!!)
                            .addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(reqSnapshot: DataSnapshot) {
                                    if (reqSnapshot.exists()) {
                                        // On Request
                                        val friendReqData =
                                            reqSnapshot.getValue(FriendData::class.java)
                                        if (friendReqData?.type == "sent") {
                                            layout_accept_request.visibility = View.GONE
                                            layout_add_friend.visibility = View.GONE
                                            layout_cancel_request.visibility = View.VISIBLE
                                            layout_friend_view.visibility = View.GONE
                                        }
                                        if (friendReqData?.type == "received") {
                                            layout_accept_request.visibility = View.VISIBLE
                                            layout_add_friend.visibility = View.GONE
                                            layout_cancel_request.visibility = View.VISIBLE
                                            layout_friend_view.visibility = View.GONE
                                        }
                                    } else {
                                        // On No Status
                                        layout_accept_request.visibility = View.GONE
                                        layout_add_friend.visibility = View.VISIBLE
                                        layout_cancel_request.visibility = View.GONE
                                        layout_friend_view.visibility = View.GONE
                                    }
                                }

                                override fun onCancelled(p0: DatabaseError) {
                                    TODO("Not yet implemented")
                                }


                            })
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun getFriendsCount() {
        val refFriend = database.getReference("FriendsList")
        refFriend.child(userId!!).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    // Count
                    friendsCount = dataSnapshot.childrenCount.toInt()
                    friends_user_profile.text = friendsCount.toString()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    /**
     * Get Feed Post
     */

    private fun getInitPosts() {

        val postRef = database.getReference("UserPosts").child(userId!!)

        postRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    postsCount = dataSnapshot.childrenCount.toInt()
                    post_count_user_profile.text = postsCount.toString()
                    var count = 0
                    val postItems = ArrayList<PostKt>()
                    for (postSnapshot in dataSnapshot.children) {
                        if (count == 5) {
                            break
                        }
                        postItems.add(postSnapshot.getValue(PostKt::class.java)!!)
                        count++
                    }
                    postItems.reverse()

                    // Recycler Adapter Changed..
                    feedAdapter?.removeAllItem()
                    feedAdapter?.addAllPosts(postItems)
                    feedAdapter?.notifyDataSetChanged()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                // TODO
            }

        })
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

    /**
     * Friend Request
     */

    private fun sendFriendRequest() {
        // Auth User Data
        val firstName = userPreferences.getString("firstName", "")
        val lastName = userPreferences.getString("lastName", "")
        val profileThumb = userPreferences.getString("imageThumbnail", "")

        val ref = database.getReference("FriendsRequest")

        // Jar ID te Dhukci
        val reqDataSent =
            FriendData(
                userId,
                currentUser?.firstName,
                currentUser?.lastName,
                "sent",
                currentUser?.imageThumbnail
            )
        // Je Req Pathabe
        val reqDataReceive =
            FriendData(
                authId,
                firstName,
                lastName,
                "received",
                profileThumb
            )
        ref.child(authId!!).child(userId!!).setValue(reqDataSent).addOnSuccessListener {
            sendNotification(
                userId!!,
                "$firstName $lastName",
                "Sent you a friend request"
            )
            Snackbar.make(user_profile_main_layout, "Friend Request sent", Snackbar.LENGTH_LONG)
                .show()
        }

        // Place
        ref.child(userId!!).child(authId!!).setValue(reqDataReceive).addOnSuccessListener {
            // Like Notification...
            FirebaseAuthorNotification.sendNotificationToAuthor(
                authId,
                userId,
                authId,
                "friendRequest",
                "$firstName $lastName sent a friend request"
            )
        }
    }


    private fun acceptFriendRequest() {
        // Auth Data
        val firstName = userPreferences.getString("firstName", "")
        val lastName = userPreferences.getString("lastName", "")
        val profileThumb = userPreferences.getString("imageThumbnail", "")

        val ref = database.getReference("FriendsList")

        val friendDataOne =
            FriendData(
                userId,
                currentUser?.firstName,
                currentUser?.lastName,
                "friend",
                currentUser?.imageThumbnail
            )
        val friendDataTwo =
            FriendData(
                authId,
                firstName,
                lastName,
                "friend",
                profileThumb
            )


        val refData = database.getReference("FriendsRequest")
        refData.child(authId!!).child(userId!!).removeValue()

        refData.child(userId!!).child(authId!!).removeValue().addOnSuccessListener {
            ref.child(authId!!).child(userId!!).setValue(friendDataOne).addOnSuccessListener {
                sendNotification(
                    userId!!,
                    "$firstName $lastName",
                    "accept your friend request"
                )
                Snackbar.make(
                    user_profile_main_layout,
                    "Friend Request Accept",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            // Place
            ref.child(userId!!).child(authId!!).setValue(friendDataTwo)
        }

    }


    private fun removeFriendRequest() {

        val ref = database.getReference("FriendsRequest")

        ref.child(authId!!).child(userId!!).removeValue().addOnSuccessListener {
            Snackbar.make(user_profile_main_layout, "Friend Request Canceled", Snackbar.LENGTH_LONG)
                .show()
        }
        // Place
        ref.child(userId!!).child(authId!!).removeValue()
    }


    /**
     * Notification
     */

    private fun sendNotification(receiverID: String, firstName: String, message: String) {
        val tokenRef = FirebaseDatabase.getInstance().getReference("CloudTokens")
        tokenRef.child(receiverID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val (token) = dataSnapshot.getValue(CloudToken::class.java)!!
                    val notificationData = NotificationData(
                        userId,
                        "$firstName $message",
                        "Friend Request",
                        receiverID,
                        "request",
                        authId,
                        R.drawable.app_icon
                    )
                    val pushNotification =
                        PushNotification(notificationData, token!!)
                    sendMgsNotification(pushNotification)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //TODO....
            }
        })
    }

    /**
     * On Recycler view Item Click
     */
    override fun onRecyclerViewItemClicked(view: View, postItem: PostKt) {
        when (view.id) {

            // Comment Button Event
            R.id.layout_btn_comment_with_image,
            R.id.layout_btn_comment_without_image,
            R.id.layout_btn_comment_with_link,
            R.id.layout_btn_comment_with_big_text -> {
                Intent(this, FullPostActivity::class.java).apply {
                    this.putExtra("Id", postItem.postID)
                    startActivity(this)
                }
            }
            // Share Button Event..
            R.id.share_layout_with_image,
            R.id.share_layout_without_image,
            R.id.share_layout_with_link,
            R.id.share_layout_with_big_text -> {
                try {
                    val i = Intent(Intent.ACTION_SEND)
                    i.type = "text/plain"
                    i.putExtra(
                        Intent.EXTRA_SUBJECT,
                        resources.getString(R.string.app_name)
                    )
                    val shareHint = "https://starnotesocial.com/shared_post/${postItem.postID}"

                    i.putExtra(Intent.EXTRA_TEXT, shareHint)
                    startActivity(Intent.createChooser(i, "Choose one"))
                } catch (e: Exception) {
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                }
            }

            R.id.layout_post_link_img_item -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(postItem.linkRaw)))
            }

        }
    }


}