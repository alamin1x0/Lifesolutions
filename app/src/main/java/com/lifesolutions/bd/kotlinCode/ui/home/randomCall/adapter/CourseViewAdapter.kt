package com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.Course
import com.lifesolutions.bd.kotlinCode.ui.activities.CourseSingleActivity
import kotlinx.android.synthetic.main.course_card_view_hz.view.*


class CourseViewAdapter(
    private val context: Context,
    private val courses: List<Course>
) : RecyclerView.Adapter<CourseViewAdapter.ViewHolder>() {

    private val TAG = "TeachersViewAdapter"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_card_view_hz, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return courses.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = courses[position]

        holder.courseName.text = course.name

        // holder.courseRatings


//        if (user.imageThumbnail != null && user.imageThumbnail != "none") {
//            Glide.with(context)
//                .load(user.imageThumbnail)
//                .into(holder.imageView)
//        } else {
//            Glide.with(context)
//                .load(R.drawable.user_low)
//                .into(holder.imageView)
//        }
//
//        when {
//            user.firstName.isNullOrEmpty() -> {
//                holder.firstName.text = "Unnamed"
//            }
//            user.firstName.length > 10 -> {
//                holder.firstName.text = user.firstName.take(10) + ".."
//            }
//            else -> {
//                holder.firstName.text = user.firstName
//            }
//        }
//
//
//        // Onclick Item...
//        holder.itemView.setOnClickListener {
//           Intent(context, PersonalMessageActivity::class.java).apply {
//               this.putExtra("receiverID", user.id)
//               context.startActivity(this)
//           }
//
//        }


        holder.itemView.setOnClickListener {
            Intent(context, CourseSingleActivity::class.java).apply {
                 putExtra("COURSE_DATA", course)
                context.startActivity(this)
            }
        }
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseName: TextView = itemView.tv_course_name
        val courseRatings: RatingBar = itemView.course_rating
    }
}