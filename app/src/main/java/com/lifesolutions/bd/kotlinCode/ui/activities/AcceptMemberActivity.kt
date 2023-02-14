package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupMemberRequest
import com.lifesolutions.bd.kotlinCode.ui.adapter.AcceptMemberAdapter
import kotlinx.android.synthetic.main.activity_accept_member.*

class AcceptMemberActivity : AppCompatActivity() {

    private lateinit var db: FirebaseDatabase
    private lateinit var memberAdapter: AcceptMemberAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    private var groupId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accept_member)

        // Set Actionbar
        setSupportActionBar(toolbar_accept_member_group)
        supportActionBar?.title = "Group Join Request"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null) {
            groupId = intent.getStringExtra("groupId")
        }

        db = FirebaseDatabase.getInstance()


        // Feed Adapter
        mLayoutManager = LinearLayoutManager(this)
        memberAdapter = AcceptMemberAdapter(this)

        recycler_view_request_list.apply {
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


    private fun getFriendList() {
        val ref =  db.getReference("GroupConversation").child(groupId!!).child("Requests")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val members = ArrayList<GroupMemberRequest>()
                dataSnapshot.children.forEach {
                    val m = it.getValue(GroupMemberRequest::class.java)
                    if (m != null) {
                        members.add(m)
                    }
                }

                memberAdapter.removeAllItem()
                memberAdapter.addAllFriends(members, groupId!!)
                memberAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

}