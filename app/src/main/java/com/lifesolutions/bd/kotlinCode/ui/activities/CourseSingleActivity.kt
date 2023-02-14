package com.lifesolutions.bd.kotlinCode.ui.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.Course
import com.lifesolutions.bd.kotlinCode.data.model.CourseVideo
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.adapter.CourseVideoAdapter
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_course_single.*
import kotlinx.android.synthetic.main.feed_fragment.*

class CourseSingleActivity : AppCompatActivity() {

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var courseVideoAdapter: CourseVideoAdapter
    private var playList = ArrayList<CourseVideo>()
    private var isEnrolled = false

    private lateinit var course: Course
    private var currentUser: UserKt? = null
    // Firebase DB
    private lateinit var database: FirebaseDatabase
    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_single)

        // Set Actionbar
        setSupportActionBar(toolbar_course_single)
        supportActionBar?.title = "Course View"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // DB Initialize...
        database = FirebaseDatabase.getInstance()
        // Initialize Shared Preferences for User Data
        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!


        course = intent.getSerializableExtra("COURSE_DATA") as Course

        mLayoutManager = LinearLayoutManager(this)
        courseVideoAdapter = CourseVideoAdapter(this)

        rv_course_video.apply {
            setHasFixedSize(true)
            isFocusable = false
            layoutManager = mLayoutManager
            adapter = courseVideoAdapter
        }

        getUserInfo()
        getEnrolledInfo()
        setCourseData()
        getVideoLists()
    }

    private fun setCourseData() {
        tv_title.text = course.name
        course_desc.text = course.description
        course_price.text = course.price.toString()
    }

    private fun enrolledUi() {
        btn_view_enrolled.visibility = View.VISIBLE
        btn_enroll_course.visibility = View.GONE
    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onEnroll(view: View) {
        showDialog()
    }


    @SuppressLint("SetTextI18n")
    private fun showDialog() {
        val epicDialog = Dialog(this)
        epicDialog.setContentView(R.layout.course_enroll_dialog)

        // ID....
        val closeBtn = epicDialog.findViewById<Button>(R.id.close_dialog)
        val title: TextView = epicDialog.findViewById<Button>(R.id.tv_title)
        val playlistSize: TextView = epicDialog.findViewById<Button>(R.id.tv_playlist_size)
        val price: TextView = epicDialog.findViewById<Button>(R.id.iv_price)
        val userCoin: TextView = epicDialog.findViewById<Button>(R.id.user_coin_view)
        val tvInsufficientCoin: TextView = epicDialog.findViewById<Button>(R.id.tv_insufficent_coin)
        val image: ImageView = epicDialog.findViewById(R.id.iv_profile_img)
        val rating: RatingBar = epicDialog.findViewById(R.id.ratings)
        val btnCancel: Button = epicDialog.findViewById(R.id.btn_enroll_cancel)
        val btnConfirm: Button = epicDialog.findViewById(R.id.btn_enroll_confirm)
        val btnBuy: Button = epicDialog.findViewById(R.id.btn_buy_coin)


        // Visibility
        if (currentUser?.points!! >= course.price!!) {
            btnBuy.visibility = View.GONE
            btnConfirm.visibility = View.VISIBLE
            tvInsufficientCoin.visibility = View.GONE
        } else {
            btnBuy.visibility = View.VISIBLE
            btnConfirm.visibility = View.GONE
            tvInsufficientCoin.visibility = View.VISIBLE
        }

        // Set Value
        userCoin.text = "You have ${currentUser?.points} coins"
        title.text = course.name
        price.text = course.price.toString()
        playlistSize.text = playList.size.toString()
        

        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        epicDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        epicDialog.setCancelable(true)

        btnCancel.setOnClickListener {
            epicDialog.dismiss()
        }
        closeBtn.setOnClickListener {
            epicDialog.dismiss()
        }

        btnBuy.setOnClickListener {
            startActivity(Intent(this, BuyCoinActivity::class.java))
            epicDialog.dismiss()
        }

        btnConfirm.setOnClickListener {
            enrollCourse()
            epicDialog.dismiss()
        }



        epicDialog.show()
    }


    private fun getVideoLists() {
        val ref = database.getReference("Courses").child(course._id!!).child("Playlist")
        val videoList = ArrayList<CourseVideo>()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    videoList.clear()
                    for (data in dataSnapshot.children) {
                        val video = data.getValue(CourseVideo::class.java)
                        videoList.add(video!!)
                    }

                    tv_playlist_size?.text = "${videoList.size} Videos"

                    playList.addAll(videoList)

                    courseVideoAdapter.removeAllItem()
                    courseVideoAdapter.addAllVideos(videoList, isEnrolled)
                    courseVideoAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


    private fun getEnrolledInfo() {
        val uID = userPreferences.getString("uID", null)
        val ref = database.getReference("Users").child(uID!!).child("Courses").child(course._id!!)
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                isEnrolled = if (dataSnapshot.exists()) {
                    enrolledUi()
                    true
                } else {
                    false
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    private fun getUserInfo() {
        val uID = userPreferences.getString("uID", null)

        val ref = database.getReference("Users").child(uID!!)

        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentUser = dataSnapshot.getValue(UserKt::class.java)

                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun enrollCourse() {

        val currentCoin = currentUser?.points
        val newCoin = currentCoin!! - course.price!!

        val uID = userPreferences.getString("uID", null)

        val ref = database.getReference("Users").child(uID!!).child("Courses").child(course._id!!)
        val coinRef = database.getReference("Users").child(uID).child("points")

        val data = HashMap<String, Any>()
        data["_id"] = course._id!!
        data["name"] = course.name!!

        ref.setValue(data).addOnSuccessListener {
            coinRef.setValue(newCoin).addOnSuccessListener {
                toast("Congratulation. Enrolled Successfully")
                isEnrolled = true
                courseVideoAdapter.removeAllItem()
                courseVideoAdapter.addAllVideos(playList, isEnrolled)
                courseVideoAdapter.notifyDataSetChanged()
                enrolledUi()
            }
        }
    }


}