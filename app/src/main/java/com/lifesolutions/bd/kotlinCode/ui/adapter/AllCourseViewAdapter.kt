package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.course_details_card.view.*


class AllCourseViewAdapter(
    private val context: Context,
    private val courses: List<Course>
) : RecyclerView.Adapter<AllCourseViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_details_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return courses.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = courses[position]

        holder.courseName.text = course.name

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