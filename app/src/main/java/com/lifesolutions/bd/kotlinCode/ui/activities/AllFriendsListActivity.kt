package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_all_friends_list.*


class AllFriendsListActivity : AppCompatActivity() {

    private var authId: String? = null
    // Firebase DB
    private lateinit var database: FirebaseDatabase
    private var friendsCount = 0

    private lateinit var animatedLoading: AnimatedLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_friends_list)

        // Set Actionbar
        setSupportActionBar(toolbar_all_friend_list)
        supportActionBar?.title = "All Friends List"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

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

        val ref = FirebaseDatabase.getInstance().getReference("FriendsList").child(authId!!).orderByKey()
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
                }
                animatedLoading.hideAnimatedLoading()
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

            rv_friend_list_on_profile.apply {
                layoutManager = gridLayoutManager
                setHasFixedSize(true)
                adapter = FriendGridViewAdapter(this@AllFriendsListActivity, users)
            }
        }
    }


}