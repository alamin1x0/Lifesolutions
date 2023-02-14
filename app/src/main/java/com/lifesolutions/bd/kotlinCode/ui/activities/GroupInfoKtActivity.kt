package com.lifesolutions.bd.kotlinCode.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.data.model.GroupMember
import com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMemberAdapter
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_group_info_kt.*
import java.util.*
import kotlin.collections.ArrayList

class GroupInfoKtActivity : AppCompatActivity() {
    private val TAG = "GroupInfoKtActivity"

    private var groupId: String? = null

    // DB Ref
    private lateinit var db: FirebaseDatabase

    // Shared Pref
    private lateinit var userPreferences: SharedPreferences

    // Group Info
    private var groupInfo: GroupConversation? = null
    private var currentMember: GroupMember? = null
    private var adminCount = 0
    private var currentUserRole: String? = "member"

    //Notification Topic
    private var TOPIC: String? = null

    // Current User Info
    private var currentUserID: String? = null
    private var currentUserFirstName: String? = null
    private var currentUserLastName: String? = null
    private var currentUserImage: String? = null


    private lateinit var memberAdapter: GroupMemberAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    private val groupMemberList = ArrayList<GroupMember>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info_kt)

        // Set Actionbar
        setSupportActionBar(toolbar_group_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Group Details"

        // Initialize Shared Preferences for User Data
        userPreferences =
            getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!
        // DB Init
        db = FirebaseDatabase.getInstance()

        if (intent != null) {
            groupId = intent.getStringExtra("groupId")
            TOPIC = "/topics/$groupId"
        }


        getGroupInfo()
        getCurrentUserInfo()
        countAdminRole()


        // Feed Adapter
        mLayoutManager = LinearLayoutManager(this)
        //memberAdapter = GroupMemberAdapter(this)

        recycler_view_group_members.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
        }

        //getGroupMemberList()
        checkGroupAdmin()

        btn_edit_group_pen.setOnClickListener {
            Intent(this, EditGroupActivity::class.java).apply {
                this.putExtra("groupId", groupId)
                startActivity(this)
            }
        }

        ed_search_group_info.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    searchGroupMember(s.toString())
                } else {
                    //memberAdapter.removeAllItem()
                    //memberAdapter.addAllFriends(groupMemberList, groupId!!)
                    memberAdapter =
                        GroupMemberAdapter(
                            this@GroupInfoKtActivity,
                            currentUserRole!!,
                            TOPIC!!,
                            groupMemberList,
                            groupId
                        )
                    recycler_view_group_members.adapter = memberAdapter
                    memberAdapter.notifyDataSetChanged()
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun searchGroupMember(str: String) {
        if (!groupMemberList.isNullOrEmpty()) {
            val myList: ArrayList<GroupMember> = ArrayList()
            for (obj in groupMemberList) {
                if (obj.firstName!!.toLowerCase(Locale.ROOT)
                        .contains(str.toLowerCase(Locale.getDefault())) || obj.lastName!!.toLowerCase(
                        Locale.ROOT
                    ).contains(str.toLowerCase(Locale.getDefault()))
                ) {
                    myList.add(obj)
                }
            }
            //memberAdapter.removeAllItem()
            //memberAdapter.addAllFriends(myList, groupId!!)
            memberAdapter =
                GroupMemberAdapter(
                    this@GroupInfoKtActivity,
                    currentUserRole!!,
                    TOPIC!!,
                    myList,
                    groupId
                )
            recycler_view_group_members.adapter = memberAdapter
            memberAdapter.notifyDataSetChanged()
        }
    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getGroupInfo() {
        val ref = db.getReference("GroupConversations").child(groupId!!)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    groupInfo = dataSnapshot.getValue(GroupConversation::class.java)
                    updateReceiverUI()
                } else {
                    toast("No Group info.")
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.w(TAG, p0.message)
            }


        })
    }


    @SuppressLint("SetTextI18n")
    private fun updateReceiverUI() {
        tv_group_name.text = groupInfo?.groupName
        tv_group_created_at.text = "Since ${Utils.getNormalDate(groupInfo?.createdTime!!)}"

        if (groupInfo?.groupImage != null && groupInfo?.groupImage != "none") {
            Picasso.get().load(groupInfo?.groupImage).into(iv_group_image)
        } else {
            Picasso.get().load(R.drawable.ic_group_placeholder).into(iv_group_image)
        }

    }

    private fun getCurrentUserInfo() {
        currentUserID = userPreferences.getString("uID", null)
        currentUserFirstName = userPreferences.getString("firstName", "")
        currentUserLastName = userPreferences.getString("lastName", "")
        currentUserImage = userPreferences.getString("imageThumbnail", null)

    }

    private fun getGroupMemberList() {
        db.getReference("GroupConversations").child(groupId!!).child("Members")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    groupMemberList.clear()
                    dataSnapshot.children.forEach {
                        val member = it.getValue(GroupMember::class.java)
                        groupMemberList.add(member!!)
                    }

                    groupMemberList.reverse()
                    memberAdapter =
                        GroupMemberAdapter(
                            this@GroupInfoKtActivity,
                            currentUserRole!!,
                            TOPIC!!,
                            groupMemberList,
                            groupId
                        )
                    recycler_view_group_members.adapter = memberAdapter
                    //memberAdapter.removeAllItem()
                    //memberAdapter.addAllFriends(groupMemberList, groupId!!)
                    //memberAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }

    fun onClickAddMember(view: View) {
        Intent(this, AddMemberActivity::class.java).apply {
            this.putExtra("groupId", groupId)
            this.putExtra("adminId", currentUserID)
            startActivity(this)
        }
    }

    private fun checkGroupAdmin() {
        val ref = db.getReference("GroupConversations").child(groupId!!).child("Members")
        ref.child(currentUserID!!).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentMember = dataSnapshot.getValue(GroupMember::class.java)

                    currentUserRole = currentMember?.role

                    if (currentMember?.role != "admin") {
                        btn_add_member.visibility = View.GONE
                        btn_edit_group_pen.visibility = View.GONE
                    } else {
                        btn_add_member.visibility = View.VISIBLE
                        btn_edit_group_pen.visibility = View.VISIBLE
                    }
                }

                getGroupMemberList()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun countAdminRole() {
        val ref = db.getReference("GroupConversations").child(groupId!!).child("Members")
        ref.orderByChild("role").equalTo("admin")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val c = dataSnapshot.childrenCount
                        adminCount = c.toInt()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun onLeaveGroup(view: View) {
        if (currentMember?.role == "admin" && adminCount == 1) {
            toast("You can't leave this group. Please make another admin to leave")
        } else {
            leaveFromGroup()
        }
    }

    private fun leaveFromGroup() {
        val ref = db.getReference("GroupConversations").child(groupId!!)
        FirebaseMessaging.getInstance().unsubscribeFromTopic(TOPIC!!).addOnFailureListener {
            toast(it.localizedMessage!!)
        }
        ref.child("Members").child(currentUserID!!)
            .removeValue().addOnSuccessListener {
                toast("Leave Success")
                Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                    finish()
                }
            }
    }

}