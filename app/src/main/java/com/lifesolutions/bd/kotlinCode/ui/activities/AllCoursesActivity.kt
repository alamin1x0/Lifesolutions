package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.Course
import com.lifesolutions.bd.kotlinCode.ui.adapter.AllCourseViewAdapter
import kotlinx.android.synthetic.main.activity_all_courses.*

class AllCoursesActivity : AppCompatActivity() {

    private lateinit var mLayoutManager: LinearLayoutManager
    // Firebase DB
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_courses)

        // Set Actionbar
        setSupportActionBar(toolbar_all_courses_list)
        supportActionBar?.title = "All Courses"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // DB Initialize...
        database = FirebaseDatabase.getInstance()

        mLayoutManager = GridLayoutManager(this, 2)
        // mLayoutManager = LinearLayoutManager(this)

        getCoursesLists()

    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getCoursesLists() {

        val ref = database.getReference("Courses").orderByKey().limitToLast(5)
        val courses = ArrayList<Course>()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    courses.clear()
                    for (courseData in dataSnapshot.children) {
                        val course = courseData.getValue(Course::class.java)
                        courses.add(course!!)
                    }

                    initCourseView(courses)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


    private fun initCourseView(courses: List<Course>) {
        if (courses.isNotEmpty()) {
            rv_all_courses_list.apply {
                setHasFixedSize(true)
                layoutManager = mLayoutManager
                adapter = AllCourseViewAdapter(this@AllCoursesActivity, courses)
            }

        }


    }

}