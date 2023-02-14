package com.lifesolutions.bd.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.like.LikeButton
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.ui.activities.ViewStream
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.live_video_item_layout.view.*


class AllLiveVideoViewAdapter(
    private val context: Context

) : RecyclerView.Adapter<AllLiveVideoViewAdapter.ViewHolder>() {

    private var allVideos = mutableListOf<LiveVideo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.live_video_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return allVideos.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val userPreferences = context.getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)
        val video = allVideos[position]
        val currentUserId = userPreferences.getString("uID", null)


        holder.postThumb.setOnClickListener {

            val myIntent = Intent(context, ViewStream::class.java)
            myIntent.putExtra("index", position)
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent)
        }

        holder.name.text = "${video.authorFirstName} ${video.authorLastName}"

        if(video.postDescription.isNullOrEmpty()){
            holder.title.text = "Untitled Video"
        }else {
            holder.title.text = video.postDescription
        }

        // Set Value
        if (video.authorImage != null && video.authorImage != "none") {
            Glide.with(context)
                .load(video.authorImage)
                .into(holder.authorImg)
        } else {
            Glide.with(context)
                .load(R.drawable.user_low)
                .into(holder.authorImg)
        }


        // Check Post Like or Not by the user..

        // Check Post Like or Not by the user..

    }



    /**
     * Essential Feed Data Binding Method
     *
     */

    open fun addAllPosts(newPosts: ArrayList<LiveVideo>) {
        allVideos = newPosts
        notifyDataSetChanged()
        // val initSize: Int = feeds.size
        // feeds.addAll(newPosts)
        // notifyItemRangeChanged(initSize, newPosts.size)
    }

    open fun removeAllItem() {
        allVideos.clear()
    }

    private fun isLiked(
        postId: String,
        userID: String,
        likeButton: LikeButton
    ) {
        val ref =
            FirebaseDatabase.getInstance().reference.child("Likes").child(postId)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                likeButton.isLiked = dataSnapshot.child(userID).exists()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.user_name_post_item
        val authorImg: CircleImageView = itemView.user_image_post_item
        val title: TextView = itemView.post_title_post_item
        val date: TextView = itemView.date_post_item
        val postThumb: ImageView = itemView.post_image_post_item
//        val linkBtn: RelativeLayout = itemView.like_button_post_item
//        val cmdBtn: RelativeLayout = itemView.comment_button_post_item
//        val shareBtn: RelativeLayout = itemView.share_button_post_item
    }



}