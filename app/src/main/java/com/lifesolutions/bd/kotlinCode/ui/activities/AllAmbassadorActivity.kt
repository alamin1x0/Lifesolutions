package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.Ambassador
import com.lifesolutions.bd.kotlinCode.ui.adapter.AllAmbassadorViewAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.activity_all_ambassador.*

class AllAmbassadorActivity : AppCompatActivity() {

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var animatedLoading: AnimatedLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_ambassador)

        // Set Actionbar
        setSupportActionBar(toolbar_all_ambassador_list)
        supportActionBar?.title = "All Ambassador"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        animatedLoading = AnimatedLoading(this)

        mLayoutManager = LinearLayoutManager(this)
        getAmbassadorsLists()
    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getAmbassadorsLists() {

        animatedLoading.showAnimatedLoading()

        val ref = FirebaseDatabase.getInstance().getReference("Ambassadors").orderByKey().limitToLast(20)
        val ambassadors = ArrayList<Ambassador>()

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    ambassadors.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(Ambassador::class.java)
                        ambassadors.add(user!!)
                    }
                    initAmbassadors(ambassadors)
                }
                animatedLoading.hideAnimatedLoading()
            }

            override fun onCancelled(p0: DatabaseError) {
                animatedLoading.hideAnimatedLoading()
            }


        })
    }

    private fun initAmbassadors(users: List<Ambassador>) {
        if (users.isNotEmpty()) {
            rv_all_ambassador_list.apply {
                setHasFixedSize(true)
                layoutManager = mLayoutManager
                adapter = AllAmbassadorViewAdapter(this@AllAmbassadorActivity, users)
            }

        }


    }

}