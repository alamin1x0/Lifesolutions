package com.lifesolutions.bd.kotlinCode.ui.adapter

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
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.CourseVideo
import com.lifesolutions.bd.kotlinCode.ui.activities.YoutubePlayActivity
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.all_online_user_item_view.view.*
import kotlinx.android.synthetic.main.course_video_card.view.*
import java.util.ArrayList


class CourseVideoAdapter(
    private val context: Context
) : RecyclerView.Adapter<CourseVideoAdapter.ViewHolder>() {

    private var videos = mutableListOf<CourseVideo>()
    private var isEnrolled: Boolean = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_video_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return videos.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val video = videos[position]

        if (video.thumbnail != null && video.thumbnail != "none") {
            Glide.with(context)
                .load(video.thumbnail)
                .into(holder.imageThumb)
        } else {
            Glide.with(context)
                .load(R.drawable.user_low)
                .into(holder.imageThumb)
        }

        holder.videoTitle.text = video.title

//        when {
//            user.firstName.isNullOrEmpty() -> {
//                holder.firstName.text = "Unnamed"
//            }
//            else -> {
//                holder.firstName.text = user.firstName
//            }
//        }
//
//
        // Onclick Item...
        holder.itemView.setOnClickListener {
           if (isEnrolled) {
               Intent(context, YoutubePlayActivity::class.java).apply {
                   this.putExtra("PLAYBACK_DATA", video)
                   context.startActivity(this)
               }
           } else {
               context.toast("Please enroll this course for view")
           }

        }
    }


    fun addAllVideos(videoPlaylist: ArrayList<CourseVideo>, enrolled: Boolean) {
        videos = videoPlaylist
        this.isEnrolled = enrolled
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        videos.clear()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoTitle: TextView = itemView.tv_video_title
        var imageThumb: ImageView = itemView.iv_thumb_img
    }
}