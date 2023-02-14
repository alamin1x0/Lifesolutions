package com.lifesolutions.bd.kotlinCode.ui.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.ui.adapter.AllGroupListAdapter
import kotlinx.android.synthetic.main.activity_all_group_list.*

class AllGroupListActivity : AppCompatActivity() {

    private val TAG = "AllGroupListActivity"
    // Firebase DB
    private lateinit var database: FirebaseDatabase
    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    private lateinit var groupChatListAdapter: AllGroupListAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_group_list)

        // Set Actionbar
        setSupportActionBar(toolbar_all_group_list)
        supportActionBar?.title = "All Groups"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize Shared Preferences for User Data
        userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)!!

        // DB Initialize...
        database = FirebaseDatabase.getInstance()

        mLayoutManager = LinearLayoutManager(this)
        groupChatListAdapter = AllGroupListAdapter(this)


        recycler_view_all_group_list.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = groupChatListAdapter
        }

        getGroupList()
    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    /**
     * Group List
     */

    private fun getGroupList() {
        val uID = userPreferences.getString("uID", null)
        database.getReference("GroupConversation").orderByChild("lastTimestamps").limitToLast(100).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listOfConversation = ArrayList<GroupConversation>()
                for (ds in dataSnapshot.children) {
                    if (!ds.child("Members").child(uID!!).exists()) {
                        val groupChat = ds.getValue(GroupConversation::class.java)!!
                        listOfConversation.add(groupChat)
                    }
                }

                Log.w(TAG, listOfConversation.toString() )
                listOfConversation.reverse()

                // Recycler Adapter Changed..
                groupChatListAdapter.removeAllItem()
                groupChatListAdapter.addAllConversation(listOfConversation)
                groupChatListAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

}