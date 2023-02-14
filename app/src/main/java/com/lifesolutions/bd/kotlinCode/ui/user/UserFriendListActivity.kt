package com.lifesolutions.bd.kotlinCode.ui.user

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.ui.adapter.UserFriendListsAdapter
import kotlinx.android.synthetic.main.activity_user_friend_list.*

class UserFriendListActivity : AppCompatActivity() {

    private lateinit var memberAdapter: UserFriendListsAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    // DB Ref
    private lateinit var db: FirebaseDatabase
    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    // Current User Info
    private var currentUserID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_friend_list)

        // Set Actionbar
        setSupportActionBar(toolbar_friend_list_kt)
        supportActionBar?.title = "My Friends List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize Shared Preferences for User Data
        userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)!!
        // DB Init
        db = FirebaseDatabase.getInstance()

        // Feed Adapter
        mLayoutManager = LinearLayoutManager(this)
        memberAdapter = UserFriendListsAdapter(this)

        getCurrentUserInfo()

        recycler_view_friend_lists.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = memberAdapter
        }

        getFriendList()

    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getCurrentUserInfo() {
        currentUserID = userPreferences.getString("uID", null)

    }



    private fun getFriendList() {
        val ref = db.getReference("friendRequest").child(currentUserID!!)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val friendsKey = ArrayList<String>()
                dataSnapshot.children.forEach {
                    it.key?.let { key -> friendsKey.add(key) }
                }
                setUserAdapter(friendsKey)
                lottie_animation_friends_lists?.visibility = View.GONE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                lottie_animation_friends_lists?.visibility = View.GONE
            }
        })
    }


    private fun setUserAdapter(friendsKey: ArrayList<String>) {
        // Recycler Adapter Changed..
        memberAdapter.removeAllItem()
        memberAdapter.addAllFriends(friendsKey, currentUserID!!)
        memberAdapter.notifyDataSetChanged()
    }


}