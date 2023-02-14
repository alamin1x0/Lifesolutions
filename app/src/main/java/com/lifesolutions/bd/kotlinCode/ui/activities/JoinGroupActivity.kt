package com.lifesolutions.bd.kotlinCode.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.data.model.GroupMember
import com.lifesolutions.bd.kotlinCode.data.model.GroupMemberRequest
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_join_group.*

class JoinGroupActivity : AppCompatActivity() {
    private val TAG = "JoinGroupActivity"

    // Group Info
    private var groupId: String? = null
    private var groupInfo: GroupConversation? = null

    // Current User Info
    private var currentUserID: String? = null
    private var currentUserFirstName: String? = null
    private var currentUserLastName: String? = null
    private var currentUserImage: String? = null

    // DB Ref
    private lateinit var db: FirebaseDatabase

    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_group)

        // Set Actionbar
        setSupportActionBar(toolbar_join_group)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Join Group"

        if (intent != null) {
            groupId = intent.getStringExtra("groupId")
        }

        // Initialize Shared Preferences for User Data
        userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)!!
        // DB Init
        db = FirebaseDatabase.getInstance()

        getCurrentUserInfo()
        getGroupInfo()
        checkRequestList()
        checkMemberList()
        countMemberList()
    }


    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getGroupInfo() {
        loading_bar_on_join_group.visibility = View.VISIBLE

        val ref = db.getReference("GroupConversations").child(groupId!!)

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                loading_bar_on_join_group.visibility = View.GONE
                join_group_view_layout.visibility = View.VISIBLE
                if (dataSnapshot.exists()) {
                    groupInfo = dataSnapshot.getValue(GroupConversation::class.java)
                    updateReceiverUI()
                } else {
                    toast("No Group info.")
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                loading_bar_on_join_group.visibility = View.GONE
                Log.w(TAG, p0.message)
            }


        })
    }


    @SuppressLint("SetTextI18n")
    private fun updateReceiverUI() {
        tv_group_name_join.text = "${groupInfo?.groupName}"
        tv_group_type_join.text = "Privacy: ${groupInfo?.privacy}"
        tv_group_cat_join.text = "Category: ${groupInfo?.groupCat}"

        if (groupInfo?.groupImage != null && groupInfo?.groupImage != "none") {
            Picasso.get().load(groupInfo?.groupImage).into(group_image_view_thumb)
        } else {
            Picasso.get().load(R.drawable.user_low).into(group_image_view_thumb)
        }

    }

    private fun getCurrentUserInfo() {
        currentUserID = userPreferences.getString("uID", null)
        currentUserFirstName = userPreferences.getString("firstName", "")
        currentUserLastName = userPreferences.getString("lastName", "")
        currentUserImage = userPreferences.getString("imageThumbnail", null)

    }


    @SuppressLint("SetTextI18n")
    private fun joinRequestToGroup() {
        val timestamp = System.currentTimeMillis()
        //val fullName = "${currentUserFirstName.toString().trim()} ${currentUserLastName.toString().trim()}"

        val member = GroupMemberRequest(currentUserID, currentUserFirstName,currentUserLastName, currentUserImage, timestamp)

        val ref = db.getReference("GroupConversations").child(groupId!!)
        ref.child("Requests").child(currentUserID!!)
            .setValue(member).addOnSuccessListener {
                btn_join_group_action.text = "Cancel Join"
            }
    }

    @SuppressLint("SetTextI18n")
    private fun joinPublicGroup() {
        val timestamp = System.currentTimeMillis()
        val member = GroupMember(
            currentUserID,
            currentUserFirstName,
            currentUserLastName,
            currentUserImage,
            timestamp,
            "member",
            timestamp,
            false
        )

        val ref = db.getReference("GroupConversations").child(groupId!!)
        ref.child("Members").child(currentUserID!!)
            .setValue(member).addOnSuccessListener {
                btn_join_group_action.text = "View Group"
            }
    }

    @SuppressLint("SetTextI18n")
    private fun cancelRequestToGroup() {
        db.getReference("GroupConversations").child(groupId!!).child("Requests")
            .child(currentUserID!!)
            .removeValue().addOnSuccessListener {
                btn_join_group_action.text = "Join Group"
            }
    }

    fun onJoinBtnClick(view: View) {
        when (btn_join_group_action.text) {
            "Join Group" -> {
                if (groupInfo?.privacy == "Private") {
                    joinRequestToGroup()
                } else {
                    joinPublicGroup()
                }
            }
            "Cancel Join" -> {
                cancelRequestToGroup()
            }
            "View Group" -> {
                Intent(this, GroupMessageActivity::class.java).apply {
                    putExtra("groupId", groupId)
                    startActivity(this)
                }
            }
        }
    }

    private fun checkRequestList() {
        db.getReference("GroupConversations").child(groupId!!).child("Requests")
            .child(currentUserID!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        btn_join_group_action.text = "Cancel Join"
                    } else {
                        btn_join_group_action.text = "Join Group"
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })
    }

    private fun checkMemberList() {
        db.getReference("GroupConversations").child(groupId!!).child("Members")
            .child(currentUserID!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        btn_join_group_action.text = "View Group"
                    } else {
                        btn_join_group_action.text = "Join Group"
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })
    }

    private fun countMemberList() {
        db.getReference("GroupConversations").child(groupId!!).child("Members")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val reqCount = dataSnapshot.childrenCount
                        tv_group_member_join.text = "$reqCount Members"

                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })
    }


}