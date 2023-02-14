package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.Teacher
import com.lifesolutions.bd.kotlinCode.ui.adapter.AllTeacherViewAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.activity_all_teachers.*

class AllTeachersActivity : AppCompatActivity() {
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var animatedLoading: AnimatedLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_teachers)

        // Set Actionbar
        setSupportActionBar(toolbar_all_teachers_list)
        supportActionBar?.title = "All Teachers"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        animatedLoading = AnimatedLoading(this)

        mLayoutManager = LinearLayoutManager(this)
        getTeachersLists()
    }


    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getTeachersLists() {

        animatedLoading.showAnimatedLoading()

        val ref = FirebaseDatabase.getInstance().getReference("Teachers").orderByKey().limitToLast(20)
        val teachers = ArrayList<Teacher>()


        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    teachers.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(Teacher::class.java)
                        teachers.add(user!!)


                    }
                    initTeachers(teachers)
                }
                animatedLoading.hideAnimatedLoading()
            }

            override fun onCancelled(p0: DatabaseError) {
                animatedLoading.hideAnimatedLoading()
            }


        })
    }


    private fun initTeachers(users: List<Teacher>) {
        if (users.isNotEmpty()) {
            rv_all_teachers_list.apply {
                setHasFixedSize(true)
                layoutManager = mLayoutManager
                adapter = AllTeacherViewAdapter(this@AllTeachersActivity, users)
            }

        }


    }

}