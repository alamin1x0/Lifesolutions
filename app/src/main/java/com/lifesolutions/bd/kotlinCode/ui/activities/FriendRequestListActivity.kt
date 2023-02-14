package com.lifesolutions.bd.kotlinCode.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.FriendData
import com.lifesolutions.bd.kotlinCode.ui.adapter.FriendGridViewAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.activity_friend_request_list.*
import kotlinx.android.synthetic.main.activity_friend_request_list.tv_friends_count

class FriendRequestListActivity : AppCompatActivity() {

    private var authId: String? = null

    // Firebase DB
    private lateinit var database: FirebaseDatabase
    private var friendsCount = 0

    private lateinit var animatedLoading: AnimatedLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_request_list)

        // Set Actionbar
        setSupportActionBar(toolbar_friend_req_list)
        supportActionBar?.title = "All Friends Request"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // DB Initialize...
        database = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid

        animatedLoading = AnimatedLoading(this)

        getFriendLists()
    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getFriendLists() {
        animatedLoading.showAnimatedLoading()

        val ref = FirebaseDatabase.getInstance().getReference("FriendsRequest").child(authId!!).orderByKey()
        val users = ArrayList<FriendData>()

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    friendsCount = dataSnapshot.childrenCount.toInt()
                    tv_friends_count.text = friendsCount.toString()
                    users.clear()
                    for (usersData in dataSnapshot.children) {
                        if (users.size >= 100) {
                            break
                        }
                        val user = usersData.getValue(FriendData::class.java)
                        users.add(user!!)
                    }
                    initFriendsList(users)
                    animatedLoading.hideAnimatedLoading()
                } else {
                    animatedLoading.hideAnimatedLoading()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                animatedLoading.hideAnimatedLoading()
            }


        })
    }


    private fun initFriendsList(users: List<FriendData>) {
        if (users.isNotEmpty()) {
            val gridLayoutManager = GridLayoutManager(
                this, 3, RecyclerView.VERTICAL, false
            )

            rv_friend_request_list.apply {
                layoutManager = gridLayoutManager
                setHasFixedSize(true)
                adapter = FriendGridViewAdapter(this@FriendRequestListActivity, users)
            }
        }
    }

}