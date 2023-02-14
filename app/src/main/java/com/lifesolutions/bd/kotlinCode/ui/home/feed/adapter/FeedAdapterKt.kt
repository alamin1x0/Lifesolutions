package com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.github.pgreze.reactions.ReactionPopup
import com.github.pgreze.reactions.ReactionsConfigBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.like.LikeButton
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.ViewImageActivity
import com.lifesolutions.bd.Helpers.TimeAgo
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.*
import com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener
import com.lifesolutions.bd.kotlinCode.utils.DoubleClickListener
import com.lifesolutions.bd.kotlinCode.utils.FirebaseAuthorNotification
import com.lifesolutions.bd.kotlinCode.utils.Notify
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.post_item_big_text_layout_kt.view.*
import kotlinx.android.synthetic.main.post_item_layout_kt.view.*
import kotlinx.android.synthetic.main.post_item_link_layout_kt.view.*
import kotlinx.android.synthetic.main.post_item_multi_image_layout.view.*
import kotlinx.android.synthetic.main.post_item_only_text_layout_kt.view.*
import kotlinx.android.synthetic.main.post_video_layout.view.*
import java.util.*


// View Type CONST....
const val TYPE_POST_WITH_IMAGE = 0
const val TYPE_POST_ONLY_TEXT = 1
const val TYPE_POST_LINK = 2
const val TYPE_POST_TEXT_BIG = 3

const val TYPE_POST_WITH_VIDEO = 4
const val TYPE_POST_WITH_MULTI_IMAGE = 5
private var currentUser: UserKt? = null

open class FeedAdapterKt(
    private val listener: FeedItemOnClickListener,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "FeedAdapterKt"
    private var feeds = mutableListOf<PostKt>()


    // Declare Different View Type..
    override fun getItemViewType(position: Int): Int {
        return when {
            feeds[position].postImage != null && feeds[position].postImage != "none" -> {
                TYPE_POST_WITH_IMAGE
            }
            feeds[position].hasLink != null && feeds[position].hasLink == true -> {
                TYPE_POST_LINK
            }
            feeds[position].hasLink == false && feeds[position].postImage == "none" && feeds[position].videoUrl == null && feeds[position].postDescription?.length!! < 150 -> {
                TYPE_POST_ONLY_TEXT
            }

            feeds[position].hasLink == false && feeds[position].postImage == "none" && feeds[position].postDescription?.length!! >= 150 -> {
                TYPE_POST_TEXT_BIG
            }
            feeds[position].videoUrl != null && feeds[position].postImage == "none" -> {
                Log.w(TAG, "getItemViewType: -> Iam Here..." )
                TYPE_POST_WITH_VIDEO
            }
            feeds[position].multiImage != null && feeds[position].postImage == "none" -> {
                TYPE_POST_WITH_MULTI_IMAGE
            }

            else -> {
                Log.w(TAG, "getItemViewType: ->>>>>>>>>>>>>>> ELSE VIEW")
                TYPE_POST_TEXT_BIG
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_POST_WITH_IMAGE -> ViewHolderWithImage(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item_layout_kt, parent, false)
            )

            TYPE_POST_LINK -> ViewHolderWithLink(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item_link_layout_kt, parent, false)
            )


            TYPE_POST_ONLY_TEXT -> ViewHolderWithOnlyText(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item_only_text_layout_kt, parent, false)
            )

            TYPE_POST_TEXT_BIG -> ViewHolderBigText(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item_big_text_layout_kt, parent, false)
            )
            TYPE_POST_WITH_VIDEO -> ViewHolderWithVideo(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_video_layout, parent, false)
            )
            TYPE_POST_WITH_MULTI_IMAGE -> ViewHolderWithMultiImage(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item_multi_image_layout, parent, false)
            )

            else -> ViewHolderBigText(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item_big_text_layout_kt, parent, false)
            )
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uID = FirebaseAuth.getInstance().currentUser?.uid
        val feed = feeds[position]

        when {
            feeds[position].postImage != null && feeds[position].postImage != "none" -> {
                (holder as ViewHolderWithImage)

                // holder.initClick()

                holder.optionBtn.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.optionBtn,
                        feed
                    )
                }

                Picasso.get().load(feed.postThumbnail).into(holder.postImage)

                if (feed.postDescription?.trim()!!.isEmpty()) {
                    holder.postDesc.visibility = View.GONE
                } else {
                    holder.postDesc.visibility = View.VISIBLE
                    holder.postDesc.text = feed.postDescription
                }

                // Set Author Info From Post

                if (feed.authorImage != null && feed.authorImage != "none") {
                    Picasso.get().load(feed.authorImage).into(holder.authorImage)
                } else {
                    Picasso.get().load(R.drawable.user_low).into(holder.authorImage)
                }
                holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"

                // Post Time
                holder.postTime.text = getDateTime(feed.date!!)

                // Count Likes...
                getLikesCount(feed.postID!!, holder.likeCountView)

                // Check Post Like or Not by the user..
//                if (uID != null) {
//                    isLiked(feed.postID, uID, holder.likeBtn)
//                }

                // Count Comments...
                getCommentsCount(feed.postID, holder.commentsCountView)

                // On Like Button Click..
//                holder.likeBtn.setOnLikeListener(object : OnLikeListener {
//                    override fun liked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).setValue(true)
//
//                            // Like Notification...
//                            FirebaseAuthorNotification.sendNotificationToAuthor(
//                                feed.postID,
//                                feed.authorID,
//                                uID,
//                                "like",
//                                "Liked your post"
//                            )
//                        }
//
//                    }
//
//                    override fun unLiked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).removeValue()
//                        }
//                    }
//
//                })
                getUserInfo(uID!!)
                isReacted(feed.postID,uID,holder.likeBtn)
                val strings = arrayOf(
                    "Unlike", "like", "love", "laugh", "wow", "sad", "angry"
                )

                getReactCount(feed.postID!!, holder.likeCountView)

                val config = ReactionsConfigBuilder(context)
                    .withReactions(
                        intArrayOf(
                            R.drawable.ic_star_outline,
                            R.drawable.ic_star_full_color,
                            R.drawable.ic_fb_love,
                            R.drawable.ic_fb_laugh,
                            R.drawable.ic_fb_wow,
                            R.drawable.ic_fb_sad,
                            R.drawable.ic_fb_angry

                        )
                    ).withReactionTexts { position -> strings[position] }
                    .build()



                val popup =
                    ReactionPopup(context, config) { position: Int? ->
                        true // true is closing popup, false is requesting a new selection
                    }


                holder.likeBtn.setOnTouchListener(popup)
                popup.reactionSelectedListener = { position: Int ->
                    Log.i("Reactions", "Selection position=$position")
                    if (position == 2) {
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("love")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Loved your post"
                        )

                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!!  ,"Loved your post",feed.postID);

                        holder.likeBtn.setImageResource(R.drawable.ic_fb_love)
                    }
                    if (position == 1) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_like)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("like")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Liked your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Liked your post",feed.postID);
                        holder.likeBtn.setImageResource(R.drawable.ic_star_full_color)
                    }
                    if (position == 3) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_laugh)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("haha")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Laughing at your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Laughed at your post",feed.postID);

                    }
                    if (position == 4) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_wow)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("wow")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Wowed your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Wowed at your post",feed.postID);

                    }
                    if (position == 5) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_sad)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("sad")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Sad about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Sad about your post",feed.postID);
                    }
                    if (position == 6) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("angry")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Angry about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Angry about your post",feed.postID);
                    }
                    if (position == 0) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).removeValue()
                    }


                    position != 3
                }


                holder.postImage.setOnClickListener {
                    Intent(context, ViewImageActivity::class.java).apply {
                        putExtra("imageUrl", feed.postImage)
                        putExtra("authorId", feed.authorID)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(this)
                    }
                }

                // On Author Image Click Event
                holder.authorImage.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorImage,
                        feed
                    )
                }

                // On Author Name Click Event
                holder.authorName.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorName,
                        feed
                    )
                }

                // On Comments Layout Click Event
                holder.commentsLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.commentsLayout,
                        feed
                    )
                }

                // On Share Layout Click Event
                holder.shareLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.shareLayout,
                        feed
                    )
                }

            }

            feeds[position].hasLink != null && feeds[position].hasLink == true -> {
                (holder as ViewHolderWithLink)

                // Set Author Info From Post
                if (feed.authorImage != null && feed.authorImage != "none") {
                    Picasso.get().load(feed.authorImage).into(holder.authorImage)
                } else {
                    Picasso.get().load(R.drawable.user_low).into(holder.authorImage)
                }
                holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"
//                if (!feed.authorFirstName.isNullOrEmpty()) {
//                    if (feed.authorImage != null && feed.authorImage != "none") {
//                        Picasso.get().load(feed.authorImage).into(holder.authorImage)
//                    }
//                    holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"
//                } else {
//                    // Set Author Info From Author ID
//                    getAuthorInfo(feed.authorID!!, holder.authorImage, holder.authorName)
//                }

                holder.optionBtn.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.optionBtn,
                        feed
                    )
                }

                holder.postDesc.text = feed.postDescription
                Picasso.get().load(feed.linkThumb)
                    .into(holder.linkImage)
                holder.linkTitle.text = feed.linkTitle
                holder.linkSource.text = feed.linkSource

                holder.viewLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.viewLayout,
                        feed
                    )
                }


                // Post Time
                holder.postTime.text = getDateTime(feed.date!!)

//                // Count Likes...
                getLikesCount(feed.postID!!, holder.likeCountView)

                // Check Post Like or Not by the user..
//                if (uID != null) {
//                    isLiked(feed.postID, uID, holder.likeBtn)
//                }

                // Count Comments...
                getCommentsCount(feed.postID, holder.commentsCountView)

                // On Like Button Click..
//                holder.likeBtn.setOnLikeListener(object : OnLikeListener {
//                    override fun liked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).setValue(true)
//
//                            // Like Notification...
//                            FirebaseAuthorNotification.sendNotificationToAuthor(
//                                feed.postID,
//                                feed.authorID,
//                                uID,
//                                "like",
//                                "Liked your post"
//                            )
//                        }
//
//                    }
//
//                    override fun unLiked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).removeValue()
//                        }
//                    }
//
//                })

                //new reaction
                isReacted(feed.postID,uID,holder.likeBtn)
                val strings = arrayOf(
                    "Unlike", "like", "love", "laugh", "wow", "sad", "angry"
                )

                getReactCount(feed.postID!!, holder.likeCountView)

                val config = ReactionsConfigBuilder(context)
                    .withReactions(
                        intArrayOf(
                            R.drawable.ic_star_outline,
                            R.drawable.ic_star_full_color,
                            R.drawable.ic_fb_love,
                            R.drawable.ic_fb_laugh,
                            R.drawable.ic_fb_wow,
                            R.drawable.ic_fb_sad,
                            R.drawable.ic_fb_angry

                        )
                    ).withReactionTexts { position -> strings[position] }
                    .build()



                val popup =
                    ReactionPopup(context, config) { position: Int? ->
                        true // true is closing popup, false is requesting a new selection
                    }


                holder.likeBtn.setOnTouchListener(popup)
                popup.reactionSelectedListener = { position: Int ->
                    Log.i("Reactions", "Selection position=$position")
                    if (position == 2) {
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("love")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Loved your post"
                        )

                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!!  ,"Loved your post",feed.postID);

                        holder.likeBtn.setImageResource(R.drawable.ic_fb_love)
                    }
                    if (position == 1) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_like)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("like")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Liked your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Liked your post",feed.postID);
                        holder.likeBtn.setImageResource(R.drawable.ic_star_full_color)
                    }
                    if (position == 3) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_laugh)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("haha")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Laughing at your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Laughed at your post",feed.postID);

                    }
                    if (position == 4) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_wow)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("wow")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Wowed your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Wowed at your post",feed.postID);

                    }
                    if (position == 5) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_sad)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("sad")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Sad about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Sad about your post",feed.postID);
                    }
                    if (position == 6) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("angry")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Angry about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Angry about your post",feed.postID);
                    }
                    if (position == 0) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).removeValue()
                    }


                    position != 3
                }




                // On Author Image Click Event
                holder.authorImage.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorImage,
                        feed
                    )
                }

                // On Author Name Click Event
                holder.authorName.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorName,
                        feed
                    )
                }

                // On Comments Layout Click Event
                holder.commentsLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.commentsLayout,
                        feed
                    )
                }

                // On Share Layout Click Event
                holder.shareLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.shareLayout,
                        feed
                    )
                }

            }

            // Only Text View
            feeds[position].hasLink == false && feeds[position].postImage == "none" && feed.videoUrl == null && feeds[position].postDescription?.length!! < 150 -> {
                (holder as ViewHolderWithOnlyText)
                // holder.postColorLayout.setBackgroundColor(Color.parseColor(feed.postTextColor))
                // Initialize Double click..
                holder.initClick()

                holder.postColorLayout.setBackgroundColor(Color.parseColor(feed.postTextColor))
                holder.postDesc.text = feed.postDescription

                holder.optionBtn.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.optionBtn,
                        feed
                    )
                }
//                val postDescLength = feed.postDescription?.length
//
//                holder.postDesc.text = postDescLength.toString()
//
//                if (postDescLength!! <= 150) {
//                    holder.postColorLayout.setBackgroundColor(Color.parseColor(feed.postTextColor))
//                    holder.postDesc.text = feed.postDescription
//                }

//                else {
//                    holder.postColorLayout.visibility = View.GONE
//                    holder.rawTextLayout.visibility = View.VISIBLE
//                    holder.rawTextView.text = feed.postDescription
//                }

//                if (postDescLength!! <= 150) {
//                    if (!feed.postTextColor.isNullOrEmpty()) {
//                        holder.postColorLayout.setBackgroundColor(Color.parseColor(feed.postTextColor))
//                    }
//                    holder.postDesc.text = feed.postDescription
//                    holder.rawTextLayout.visibility = View.GONE
//                } else {
//                    holder.postColorLayout.visibility = View.GONE
//                    holder.rawTextView.text = feed.postDescription
//                    holder.rawTextLayout.visibility = View.VISIBLE
//                }

                // Set Author Info From Post
                if (feed.authorImage != null && feed.authorImage != "none") {
                    Picasso.get().load(feed.authorImage).into(holder.authorImage)
                } else {
                    Picasso.get().load(R.drawable.user_low).into(holder.authorImage)
                }
                holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"
//                if (!feed.authorFirstName.isNullOrEmpty()) {
//                    if (feed.authorImage != null && feed.authorImage != "none") {
//                        Picasso.get().load(feed.authorImage).into(holder.authorImage)
//                    }
//                    holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"
//                }

//                else {
//                    // Set Author Info From Author ID
//                    getAuthorInfo(feed.authorID!!, holder.authorImage, holder.authorName)
//                }
//
                // Post Time
                holder.postTime.text = getDateTime(feed.date!!)

                // Count Likes...
                getLikesCount(feed.postID!!, holder.likeCountView)

                // Check Post Like or Not by the user..
//                if (uID != null) {
//                    isLiked(feed.postID, uID, holder.likeBtn)
//                }

                // Count Comments...
                getCommentsCount(feed.postID, holder.commentsCountView)

                // On Like Button Click..
//                holder.likeBtn.setOnLikeListener(object : OnLikeListener {
//                    override fun liked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).setValue(true)
//
//                            // Like Notification...
//                            FirebaseAuthorNotification.sendNotificationToAuthor(
//                                feed.postID,
//                                feed.authorID,
//                                uID,
//                                "like",
//                                "Liked your post"
//                            )
//                        }
//
//                    }
//
//                    override fun unLiked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).removeValue()
//                        }
//                    }
//
//                })


                isReacted(feed.postID,uID,holder.likeBtn)
                val strings = arrayOf(
                   "Unlike", "like", "love", "laugh", "wow", "sad", "angry"
                )

                getReactCount(feed.postID!!, holder.likeCountView)

                val config = ReactionsConfigBuilder(context)
                    .withReactions(
                        intArrayOf(
                            R.drawable.ic_star_outline,
                            R.drawable.ic_star_full_color,
                            R.drawable.ic_fb_love,
                            R.drawable.ic_fb_laugh,
                            R.drawable.ic_fb_wow,
                            R.drawable.ic_fb_sad,
                            R.drawable.ic_fb_angry

                        )
                    ).withReactionTexts { position -> strings[position] }
                    .build()



                val popup =
                    ReactionPopup(context, config) { position: Int? ->
                        true // true is closing popup, false is requesting a new selection
                    }


                holder.likeBtn.setOnTouchListener(popup)
                popup.reactionSelectedListener = { position: Int ->
                    Log.i("Reactions", "Selection position=$position")
                    if (position == 2) {
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("love")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Loved your post"
                        )

                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!!  ,"Loved your post",feed.postID);

                        holder.likeBtn.setImageResource(R.drawable.ic_fb_love)
                    }
                    if (position == 1) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_like)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("like")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Liked your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Liked your post",feed.postID);
                        holder.likeBtn.setImageResource(R.drawable.ic_star_full_color)
                    }
                    if (position == 3) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_laugh)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("haha")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Laughing at your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Laughed at your post",feed.postID);

                    }
                    if (position == 4) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_wow)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("wow")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Wowed your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Wowed at your post",feed.postID);

                    }
                    if (position == 5) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_sad)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("sad")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Sad about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Sad about your post",feed.postID);
                    }
                    if (position == 6) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("angry")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Angry about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Angry about your post",feed.postID);
                    }
                    if (position == 0) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).removeValue()
                    }


                    position != 3
                }


                // On Author Image Click Event
                holder.authorImage.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorImage,
                        feed
                    )
                }

                // On Author Name Click Event
                holder.authorName.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorName,
                        feed
                    )
                }

                // On Comments Layout Click Event
                holder.commentsLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.commentsLayout,
                        feed
                    )
                }

                // On Share Layout Click Event
                holder.shareLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.shareLayout,
                        feed
                    )
                }
            }

            // Big Text...
            feeds[position].hasLink == false && feeds[position].postImage == "none" && feeds[position].postDescription?.length!! >= 150 -> {
                (holder as ViewHolderBigText)

                holder.postDesc2.text = feed.postDescription

                var x = 0

                holder.optionBtn.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.optionBtn,
                        feed
                    )
                }

                holder.seeMore.setOnClickListener{
                        if(x==0){
                            holder.postDesc2.visibility = View.GONE
                            holder.postDesc.visibility = View.VISIBLE
                            holder.postDesc.text = feed.postDescription
                            holder.seeMore.text = "See Less"
                            x = 1
                        }else{
                            holder.postDesc.visibility = View.GONE
                            holder.postDesc2.visibility = View.VISIBLE
                            holder.postDesc2.text = feed.postDescription
                            holder.seeMore.text = "See More"
                            x = 0
                        }



                }







                // Set Author Info From Post
                if (feed.authorImage != null && feed.authorImage != "none") {
                    Picasso.get().load(feed.authorImage).into(holder.authorImage)
                } else {
                    Picasso.get().load(R.drawable.user_low).into(holder.authorImage)
                }
                holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"
//                if (!feed.authorFirstName.isNullOrEmpty()) {
//                    if (feed.authorImage != null && feed.authorImage != "none") {
//                        Picasso.get().load(feed.authorImage).into(holder.authorImage)
//                    }
//                    holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"
//                }

//                else {
//                    // Set Author Info From Author ID
//                    getAuthorInfo(feed.authorID!!, holder.authorImage, holder.authorName)
//                }
//
                // Post Time
                holder.postTime.text = getDateTime(feed.date!!)

                // Count Likes...
                getLikesCount(feed.postID!!, holder.likeCountView)

                // Check Post Like or Not by the user..
//                if (uID != null) {
//                    isLiked(feed.postID, uID, holder.likeBtn)
//                }

                // Count Comments...
                getCommentsCount(feed.postID, holder.commentsCountView)

                // On Like Button Click..
//                holder.likeBtn.setOnLikeListener(object : OnLikeListener {
//                    override fun liked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).setValue(true)
//
//                            // Like Notification...
//                            FirebaseAuthorNotification.sendNotificationToAuthor(
//                                feed.postID,
//                                feed.authorID,
//                                uID,
//                                "like",
//                                "Liked your post"
//                            )
//                        }
//
//                    }
//
//                    override fun unLiked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).removeValue()
//                        }
//                    }
//
//                })

                isReacted(feed.postID,uID,holder.likeBtn)
                val strings = arrayOf(
                    "Unlike", "like", "love", "laugh", "wow", "sad", "angry"
                )

                getReactCount(feed.postID!!, holder.likeCountView)

                val config = ReactionsConfigBuilder(context)
                    .withReactions(
                        intArrayOf(
                            R.drawable.ic_star_outline,
                            R.drawable.ic_star_full_color,
                            R.drawable.ic_fb_love,
                            R.drawable.ic_fb_laugh,
                            R.drawable.ic_fb_wow,
                            R.drawable.ic_fb_sad,
                            R.drawable.ic_fb_angry

                        )
                    ).withReactionTexts { position -> strings[position] }
                    .build()



                val popup =
                    ReactionPopup(context, config) { position: Int? ->
                        true // true is closing popup, false is requesting a new selection
                    }


                holder.likeBtn.setOnTouchListener(popup)
                popup.reactionSelectedListener = { position: Int ->
                    Log.i("Reactions", "Selection position=$position")
                    if (position == 2) {
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("love")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Loved your post"
                        )

                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!!  ,"Loved your post",feed.postID);

                        holder.likeBtn.setImageResource(R.drawable.ic_fb_love)
                    }
                    if (position == 1) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_like)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("like")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Liked your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Liked your post",feed.postID);
                        holder.likeBtn.setImageResource(R.drawable.ic_star_full_color)
                    }
                    if (position == 3) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_laugh)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("haha")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Laughing at your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Laughed at your post",feed.postID);

                    }
                    if (position == 4) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_wow)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("wow")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Wowed your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Wowed at your post",feed.postID);

                    }
                    if (position == 5) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_sad)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("sad")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Sad about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Sad about your post",feed.postID);
                    }
                    if (position == 6) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("angry")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Angry about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Angry about your post",feed.postID);
                    }
                    if (position == 0) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).removeValue()
                    }


                    position != 3
                }




                // On Author Image Click Event
                holder.authorImage.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorImage,
                        feed
                    )
                }

                // On Author Name Click Event
                holder.authorName.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorName,
                        feed
                    )
                }

                // On Comments Layout Click Event
                holder.commentsLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.commentsLayout,
                        feed
                    )
                }

                // On Share Layout Click Event
                holder.shareLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.shareLayout,
                        feed
                    )
                }
            }

            feeds[position].videoUrl != null && feeds[position].postImage == "none" ->{
                (holder as ViewHolderWithVideo)

                Log.w(TAG, "onBindViewHolder: -> VIDEO VIEW")


                holder.optionBtn.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.optionBtn,
                        feed
                    )
                }

                holder.postImage.setUp(feeds[position].videoUrl,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"")


                if(feeds[position].postDescription.isNullOrEmpty()) {
                    holder.postDesc.visibility = View.GONE
                } else {
                    holder.postDesc.visibility = View.VISIBLE
                    holder.postDesc.text = feeds[position].postDescription
                }


                // Set Author Info From Post

                if (feed.authorImage != null && feed.authorImage != "none") {
                    Picasso.get().load(feed.authorImage).into(holder.authorImage)
                } else {
                    Picasso.get().load(R.drawable.user_low).into(holder.authorImage)
                }
                holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"

                // Post Time
                holder.postTime.text = getDateTime(feed.date!!)

                // Count Likes...
                getLikesCount(feed.postID!!, holder.likeCountView)

                // Check Post Like or Not by the user..
//                if (uID != null) {
//                    isLiked(feed.postID, uID, holder.likeBtn)
//                }

                // Count Comments...
                getCommentsCount(feed.postID, holder.commentsCountView)

                // On Like Button Click..
//                holder.likeBtn.setOnLikeListener(object : OnLikeListener {
//                    override fun liked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).setValue(true)
//
//                            // Like Notification...
//                            FirebaseAuthorNotification.sendNotificationToAuthor(
//                                feed.postID,
//                                feed.authorID,
//                                uID,
//                                "like",
//                                "Liked your post"
//                            )
//                        }
//
//                    }
//
//                    override fun unLiked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).removeValue()
//                        }
//                    }
//
//                })


                isReacted(feed.postID,uID,holder.likeBtn)
                val strings = arrayOf(
                    "Unlike", "like", "love", "laugh", "wow", "sad", "angry"
                )

                getReactCount(feed.postID!!, holder.likeCountView)

                val config = ReactionsConfigBuilder(context)
                    .withReactions(
                        intArrayOf(
                            R.drawable.ic_star_outline,
                            R.drawable.ic_star_full_color,
                            R.drawable.ic_fb_love,
                            R.drawable.ic_fb_laugh,
                            R.drawable.ic_fb_wow,
                            R.drawable.ic_fb_sad,
                            R.drawable.ic_fb_angry

                        )
                    ).withReactionTexts { position -> strings[position] }
                    .build()



                val popup =
                    ReactionPopup(context, config) { position: Int? ->
                        true // true is closing popup, false is requesting a new selection
                    }


                holder.likeBtn.setOnTouchListener(popup)
                popup.reactionSelectedListener = { position: Int ->
                    Log.i("Reactions", "Selection position=$position")
                    if (position == 2) {
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("love")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Loved your post"
                        )

                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!!  ,"Loved your post",feed.postID);

                        holder.likeBtn.setImageResource(R.drawable.ic_fb_love)
                    }
                    if (position == 1) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_like)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("like")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Liked your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Liked your post",feed.postID);
                        holder.likeBtn.setImageResource(R.drawable.ic_star_full_color)
                    }
                    if (position == 3) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_laugh)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("haha")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Laughing at your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Laughed at your post",feed.postID);

                    }
                    if (position == 4) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_wow)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("wow")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Wowed your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Wowed at your post",feed.postID);

                    }
                    if (position == 5) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_sad)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("sad")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Sad about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Sad about your post",feed.postID);
                    }
                    if (position == 6) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("angry")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Angry about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Angry about your post",feed.postID);
                    }
                    if (position == 0) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).removeValue()
                    }


                    position != 3
                }



                // On Author Image Click Event
                holder.authorImage.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorImage,
                        feed
                    )
                }

                // On Author Name Click Event
                holder.authorName.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorName,
                        feed
                    )
                }

                // On Comments Layout Click Event
                holder.commentsLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.commentsLayout,
                        feed
                    )
                }

                // On Share Layout Click Event
                holder.shareLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.shareLayout,
                        feed
                    )
                }
            }

            //multi image
            feeds[position].multiImage != null && feeds[position].postImage == "none" -> {
                (holder as ViewHolderWithMultiImage)

                holder.optionBtn.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.optionBtn,
                        feed
                    )
                }


                if(feeds[position].postDescription.isNullOrEmpty()) {
                    holder.postDesc.visibility = View.GONE
                } else {
                    holder.postDesc.visibility = View.VISIBLE
                    holder.postDesc.text = feeds[position].postDescription
                }

                val gridLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val viewPool = RecyclerView.RecycledViewPool()
                gridLayoutManager.initialPrefetchItemCount = 2

                val gridAdapter = GridImageViewAdapter(context, feeds[position].multiImage!!)

                holder.gridView.apply {
                    layoutManager = gridLayoutManager
                    setHasFixedSize(true)
                    adapter = gridAdapter
                    setRecycledViewPool(viewPool)
                }


                if (!feed.authorFirstName.isNullOrEmpty()) {
                    if (feed.authorImage != null && feed.authorImage != "none") {
                        Picasso.get().load(feed.authorImage).into(holder.authorImage)
                    }
                    holder.authorName.text = "${feed.authorFirstName} ${feed.authorLastName ?: ""}"
                } else {
                    // Set Author Info From Author ID
                    getAuthorInfo(feed.authorID!!, holder.authorImage, holder.authorName)
                }

                // Post Time
                holder.postTime.text = getDateTime(feed.date!!)

                // Count Likes...
                getLikesCount(feed.postID!!, holder.likeCountView)

                // Check Post Like or Not by the user..
//                if (uID != null) {
//                    isLiked(feed.postID, uID, holder.likeBtn)
//                }

                // Count Comments...
                getCommentsCount(feed.postID, holder.commentsCountView)

                // On Like Button Click..
//                holder.likeBtn.setOnLikeListener(object : OnLikeListener {
//                    override fun liked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).setValue(true)
//
//                            // Like Notification...
//                            FirebaseAuthorNotification.sendNotificationToAuthor(
//                                feed.postID,
//                                feed.authorID,
//                                uID,
//                                "like",
//                                "Liked your post"
//                            )
//                        }
//
//                    }
//
//                    override fun unLiked(likeButton: LikeButton?) {
//                        if (uID != null) {
//                            FirebaseDatabase.getInstance().reference.child("Likes")
//                                .child(feed.postID).child(uID).removeValue()
//                        }
//                    }
//
//                })

                isReacted(feed.postID,uID,holder.likeBtn)
                val strings = arrayOf(
                    "Unlike", "like", "love", "laugh", "wow", "sad", "angry"
                )

                getReactCount(feed.postID!!, holder.likeCountView)

                val config = ReactionsConfigBuilder(context)
                    .withReactions(
                        intArrayOf(
                            R.drawable.ic_star_outline,
                            R.drawable.ic_star_full_color,
                            R.drawable.ic_fb_love,
                            R.drawable.ic_fb_laugh,
                            R.drawable.ic_fb_wow,
                            R.drawable.ic_fb_sad,
                            R.drawable.ic_fb_angry

                        )
                    ).withReactionTexts { position -> strings[position] }
                    .build()



                val popup =
                    ReactionPopup(context, config) { position: Int? ->
                        true // true is closing popup, false is requesting a new selection
                    }


                holder.likeBtn.setOnTouchListener(popup)
                popup.reactionSelectedListener = { position: Int ->
                    Log.i("Reactions", "Selection position=$position")
                    if (position == 2) {
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("love")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Loved your post"
                        )

                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!!  ,"Loved your post",feed.postID);

                        holder.likeBtn.setImageResource(R.drawable.ic_fb_love)
                    }
                    if (position == 1) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_like)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("like")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Liked your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Liked your post",feed.postID);
                        holder.likeBtn.setImageResource(R.drawable.ic_star_full_color)
                    }
                    if (position == 3) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_laugh)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("haha")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Laughing at your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Laughed at your post",feed.postID);

                    }
                    if (position == 4) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_wow)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("wow")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Wowed your post"
                        )
                        sendNotification(feed.authorID!!,currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Wowed at your post",feed.postID);

                    }
                    if (position == 5) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_sad)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("sad")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Sad about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Sad about your post",feed.postID);
                    }
                    if (position == 6) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).setValue("angry")
                        FirebaseAuthorNotification.sendNotificationToAuthor(
                            feed.postID,
                            feed.authorID,
                            uID,
                            "like",
                            "Angry about your post"
                        )
                        sendNotification(feed.authorID!!, currentUser!!.firstName!! + " " +  currentUser!!.lastName!! ,"Angry about your post",feed.postID);
                    }
                    if (position == 0) {
                        holder.likeBtn.setImageResource(R.drawable.ic_fb_angry)
                        FirebaseDatabase.getInstance().reference.child("Reactions")
                            .child(feed.postID).child(uID!!).removeValue()
                    }


                    position != 3
                }





                // On Author Image Click Event
                holder.authorImage.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorImage,
                        feed
                    )
                }

                // On Author Name Click Event
                holder.authorName.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.authorName,
                        feed
                    )
                }

                // On Comments Layout Click Event
                holder.commentsLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.commentsLayout,
                        feed
                    )
                }

                // On Share Layout Click Event
                holder.shareLayout.setOnClickListener {
                    listener.onRecyclerViewItemClicked(
                        holder.shareLayout,
                        feed
                    )
                }


            }
        }
    }

    private fun isReacted(postID: String, uID: String?, likeBtn: ImageView) {
        val ref = FirebaseDatabase.getInstance().reference.child("Reactions").child(postID!!)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child(uID!!).exists()) {
                    var reaction: String = dataSnapshot.child(uID!!).getValue(String::class.java)!!
                    if (reaction.equals("like")) {
                        likeBtn.setImageResource(R.drawable.ic_star_full_color)
                    }
                    if (reaction.equals("love")) {
                        likeBtn.setImageResource(R.drawable.ic_fb_love)
                    }
                    if (reaction.equals("haha")) {
                        likeBtn.setImageResource(R.drawable.ic_fb_laugh)
                    }
                    if (reaction.equals("wow")) {
                        likeBtn.setImageResource(R.drawable.ic_fb_wow)
                    }
                    if (reaction.equals("sad")) {
                        likeBtn.setImageResource(R.drawable.ic_fb_sad)
                    }
                    if (reaction.equals("angry")) {
                        likeBtn.setImageResource(R.drawable.ic_fb_angry)
                    }


                }else {
                    likeBtn.setImageResource(R.drawable.ic_star_outline)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d(TAG, "Something went wrong ):")
            }


        })


    }

    private fun getReactCount(postId: String, likeCountView: TextView) {
        val reference = FirebaseDatabase.getInstance().reference.child("Reactions").child(postId)

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                likeCountView.text = "${dataSnapshot.childrenCount}"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, databaseError.message)
            }
        })
    }











    override fun getItemCount(): Int {
        return feeds.size
    }


    /**
     * Essential Feed Data Binding Method
     *
     */

    open fun addAllPosts(newPosts: ArrayList<PostKt>) {
        feeds = newPosts
        notifyDataSetChanged()
        // val initSize: Int = feeds.size
        // feeds.addAll(newPosts)
        // notifyItemRangeChanged(initSize, newPosts.size)
    }

    open fun removeAllItem() {
        feeds.clear()
    }


    /**
     * Different View Holder Type
     *
     */

    //multi image
    inner class ViewHolderWithMultiImage(view: View) : RecyclerView.ViewHolder(view) {
        //  val postImage: ImageView = view.iv_post_image_post_item_kt
        val postDesc: TextView = view.post_title_post_item_multi
        val postTime: TextView = view.date_post_item_multi

        // Author..
        val authorImage: ImageView = view.user_image_post_item_multi
        val authorName: TextView = view.user_name_post_item_multi

        val gridView: RecyclerView = view.rv_multi_image_view

        val commentsLayout: LinearLayout = view.layout_btn_comment_multi_image
        val commentsCountView: TextView = view.tv_comment_count_multi_image

        //
        val shareLayout: LinearLayout = view.share_layout_multi_image

        //
        val likeCountView: TextView = view.tv_like_count_multi_image
        val likeBtn: ImageView = view.like_btn_multi_image

        val optionBtn: ImageButton = view.btn_more_on_feed_multi_image

    }


    // Post with Image Layout View Holder...
    inner class ViewHolderWithImage(view: View) : RecyclerView.ViewHolder(view) {
        val postImage: ImageView = view.iv_post_image_post_item_kt
        val postDesc: TextView = view.tv_post_title_post_item_kt

        // Author..
        val authorImage: ImageView = view.iv_author_with_image_kt
        val authorName: TextView = view.tv_author_name_with_image_kt
        val postTime: TextView = view.tv_date_post_image_item_kt

        val commentsLayout: LinearLayout = view.layout_btn_comment_with_image
        val commentsCountView: TextView = view.tv_comment_count_post_image_item_kt

        val shareLayout: LinearLayout = view.share_layout_with_image

        val likeCountView: TextView = view.tv_like_count_post_image_item_kt
        val likeBtn: ImageView = view.like_btn_border_image_view

        val feedImage: ImageView = view.iv_post_image_post_item_kt
        val heartView: ImageView = view.heart_big_view
        val optionBtn: ImageButton = view.btn_more_on_feed_image

//
//        fun initClick() {
//            feedImage.setOnClickListener(
//                DoubleClickListener(
//                    callback = object : DoubleClickListener.Callback {
//                        override fun doubleClicked() {
//                            viewAnimatedHeart()
//                            likeBtn.performClick()
//                        }
//                    }
//                )
//            )
//
//        }
//
//        // Love Animation View..
//        fun viewAnimatedHeart() {
//            val avd: AnimatedVectorDrawableCompat
//            val avd2: AnimatedVectorDrawable
//            val drawable: Drawable = heartView.drawable
//
//            heartView.alpha = 0.99f
//
//            if (drawable is AnimatedVectorDrawableCompat) {
//                avd = drawable
//                avd.start()
//            } else if (drawable is AnimatedVectorDrawable) {
//                avd2 = drawable
//                avd2.start()
//            }
//        }
    }

    //video
    inner class ViewHolderWithVideo(view: View) : RecyclerView.ViewHolder(view) {
        val postImage: JCVideoPlayerStandard = view.iv_post_image_post_video
        val postDesc: TextView = view.tv_post_title_post_video

        // Author..
        val authorImage: ImageView = view.iv_author_with_video
        val authorName: TextView = view.tv_author_name_with_video
        val postTime: TextView = view.tv_date_post_video_view

        val commentsLayout: LinearLayout = view.layout_btn_comment_with_video
        val commentsCountView: TextView = view.tv_comment_count_post_video_count

        val shareLayout: LinearLayout = view.share_layout_with_video

        val likeCountView: TextView = view.tv_like_count_video_image
        val likeBtn: ImageView = view.like_btn_video_view

        val optionBtn: ImageButton = view.btn_more_on_feed_video

    }


    // Only Text View Layout View Holder...
    inner class ViewHolderWithOnlyText(view: View) : RecyclerView.ViewHolder(view) {
        val postColorLayout: LinearLayout = view.background_layout_post_item_kt
        val postDesc: TextView = view.tv_small_text_kt

        val rawTextLayout: LinearLayout = view.raw_text_layout
        val rawTextView: TextView = view.tv_raw_text

        // Author..
        val authorImage: ImageView = view.iv_author_without_image_kt
        val authorName: TextView = view.tv_author_name_without_image_kt

        val postTime: TextView = view.tv_date_without_image_item_kt

        val commentsLayout: LinearLayout = view.layout_btn_comment_without_image
        val commentsCountView: TextView = view.tv_comment_count_without_image_item_kt

        val shareLayout: LinearLayout = view.share_layout_without_image

        val likeCountView: TextView = view.tv_like_count_without_image_item_kt
    //    val likeBtn: LikeButton = view.like_btn_without_image_border
        val likeBtn: ImageView = view.like_btn_without_image_border

        val heartView: ImageView = view.heart_big_view_without_image

        val textImageLayout: LinearLayout = view.background_layout_post_item_kt
        val optionBtn: ImageButton = view.btn_more_on_feed_color_text




        fun initClick() {
            textImageLayout.setOnClickListener(
                DoubleClickListener(
                    callback = object : DoubleClickListener.Callback {
                        override fun doubleClicked() {
                            viewAnimatedHeart()
                            likeBtn.performClick()
                        }
                    }
                )
            )

        }

        // Love Animation View..
        fun viewAnimatedHeart() {
            val avd: AnimatedVectorDrawableCompat
            val avd2: AnimatedVectorDrawable
            val drawable: Drawable = heartView.drawable

            heartView.alpha = 0.85f

            if (drawable is AnimatedVectorDrawableCompat) {
                avd = drawable
                avd.start()
            } else if (drawable is AnimatedVectorDrawable) {
                avd2 = drawable
                avd2.start()
            }
        }

    }


    // Only Text View Layout View Holder link...
    inner class ViewHolderWithLink(view: View) : RecyclerView.ViewHolder(view) {
        val viewLayout: LinearLayout = view.layout_post_link_img_item
        val postDesc: TextView = view.tv_raw_text_link_post
        val rawTextLayout: LinearLayout = view.raw_text_layout_link_view

        // Author..
        val authorImage: ImageView = view.iv_author_with_link_kt
        val authorName: TextView = view.tv_author_name_with_link_kt

        val postTime: TextView = view.tv_date_with_link_item_kt

        val commentsLayout: LinearLayout = view.layout_btn_comment_with_link
        val commentsCountView: TextView = view.tv_comment_count_with_link_item_kt

        val shareLayout: LinearLayout = view.share_layout_with_link

        val likeCountView: TextView = view.tv_like_count_with_link_item_kt
        val likeBtn: ImageView = view.like_btn_with_link

        // Link Preview...
        val linkImage: ImageView = view.iv_link_image_post_item_kt
        val linkTitle: TextView = view.tv_link_title_post_item
        val linkSource: TextView = view.tv_link_raw_post_item
        val optionBtn: ImageButton = view.btn_more_on_feed_link


    }


    // Only Text View Layout View Holder...
    inner class ViewHolderBigText(view: View) : RecyclerView.ViewHolder(view) {
        val viewLayout: LinearLayout = view.raw_text_layout_big_text
        val postDesc: TextView = view.tv_raw_big_text
        val postDesc2: TextView = view.tv_raw_big_text2
        val seeMore: TextView = view.see_more

        // Author..
        val authorImage: ImageView = view.iv_author_image_with_big_text
        val authorName: TextView = view.tv_author_name_with_big_text

        val postTime: TextView = view.tv_date_with_big_text

        val commentsLayout: LinearLayout = view.layout_btn_comment_with_big_text
        val commentsCountView: TextView = view.tv_comment_count_with_big_text

        val shareLayout: LinearLayout = view.share_layout_with_big_text

        val likeCountView: TextView = view.tv_like_count_with_big_text
        val likeBtn: ImageView = view.like_btn_with_big_text
        val optionBtn: ImageButton = view.btn_more_on_feed_big_text

    }

    /**
     *External Function
     * Get Author Info
     * Get Liked by User
     * Get Like Count
     * Get Normal Date
     * Get Share Function
     */

    private fun getAuthorInfo(uid: String, imageProfile: ImageView, authorName: TextView) {
        val databaseReference = FirebaseDatabase.getInstance().reference

        var imageLink = "none"
        var firstName = ""
        var lastName = ""

        databaseReference.child("Users").child(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.child("imageThumbnail").exists()) {
                        imageLink =
                            dataSnapshot.child("imageThumbnail").getValue(String::class.java)!!
                    }

                    firstName = dataSnapshot.child("firstName").getValue(String::class.java)!!
                    lastName = dataSnapshot.child("lastName").getValue(String::class.java)!!

                    // Set View..
                    authorName.text = "$firstName $lastName"
                    if (imageLink != "none") {
                        Picasso.get().load(imageLink).into(imageProfile)
                    } else {
                        imageProfile.setImageResource(R.drawable.user_low)
                    }
                }


                override fun onCancelled(p0: DatabaseError) {
                    Log.d(TAG, "Something went wrong")
                }


            })
    }

    private fun isLiked(postId: String, userID: String, likeButton: LikeButton) {
        val ref = FirebaseDatabase.getInstance().reference.child("Likes").child(postId)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                likeButton.isLiked = dataSnapshot.child(userID).exists()
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d(TAG, "Something went wrong ):")
            }


        })

    }

    private fun getLikesCount(postId: String, likeCountView: TextView) {
        val reference = FirebaseDatabase.getInstance().reference.child("Likes").child(postId)

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                likeCountView.text = "${dataSnapshot.childrenCount}"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, databaseError.message)
            }
        })
    }


    private fun getCommentsCount(postID: String, comments: TextView) {
        val commentReference = FirebaseDatabase.getInstance().reference.child("Comments").child(postID)
        val commentReplyReference = FirebaseDatabase.getInstance().reference.child("CommentsReplyCount").child(postID)
        var cCount : Long = 0;
        commentReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
               // comments.text = "${dataSnapshot.childrenCount}"
                cCount = dataSnapshot.childrenCount
                commentReplyReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        cCount += dataSnapshot.childrenCount
                        comments.text = "$cCount"
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })


    }


    private fun getDateTime(timeInMilli: Any): String? {
        val cDate = System.currentTimeMillis()
        return TimeAgo.toDuration(cDate - timeInMilli as Long)
    }


    private fun sendNotification(receiverID: String, firstName: String, message: String,postId: String) {
        val tokenRef = FirebaseDatabase.getInstance().getReference("CloudTokens")
        tokenRef.child(receiverID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val (token) = dataSnapshot.getValue(CloudToken::class.java)!!
                    val notificationData = NotificationData(
                        FirebaseAuth.getInstance().currentUser?.uid,
                        "$firstName $message",
                        "Post Reaction",
                        receiverID,
                        "postReaction",
                         postId,
                        R.drawable.app_icon
                    )
                    val pushNotification =
                        PushNotification(notificationData, token!!)
                    Notify.sendMgsNotification(pushNotification)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //TODO....
            }
        })
    }


    //test
    private fun getUserInfo(uid: String) {
//        val uID = FirebaseAuth.getInstance().currentUser?.uid
        val ref =  FirebaseDatabase.getInstance().getReference("Users").child(uid)

        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentUser = dataSnapshot.getValue(UserKt::class.java)

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


}