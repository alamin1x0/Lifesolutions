package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.ActiveUser
import com.lifesolutions.bd.kotlinCode.ui.adapter.AllUserOnlineViewAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.activity_all_activer_user.*


class AllActiverUserActivity : AppCompatActivity() {

    private val TAG = "UserOnlineViewAdapter"
    private lateinit var animatedLoading: AnimatedLoading

    private lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_activer_user)

        // Set Actionbar
        setSupportActionBar(toolbar_all_active_list)
        supportActionBar?.title = "All Active Users"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        animatedLoading = AnimatedLoading(this)

        mLayoutManager = LinearLayoutManager(this)

        getActiveUserLists()
    }


    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getActiveUserLists() {

        animatedLoading.showAnimatedLoading()

        val ref = FirebaseDatabase.getInstance().getReference("ActiveNow").orderByKey().limitToLast(100)
        val users = ArrayList<ActiveUser>()

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    users.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(ActiveUser::class.java)
                        users.add(user!!)
                    }

                    initActiveUser(users)
                    animatedLoading.hideAnimatedLoading()
                } else {
                    animatedLoading.hideAnimatedLoading()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                animatedLoading.hideAnimatedLoading()
                TODO("Not yet implemented")
            }


        })
    }


    private fun initActiveUser(users: List<ActiveUser>) {
        if (users.isNotEmpty()) {
            recycler_view_all_active_list.apply {
                setHasFixedSize(true)
                layoutManager = mLayoutManager
                adapter = AllUserOnlineViewAdapter(this@AllActiverUserActivity, users)
            }

        }


    }

}