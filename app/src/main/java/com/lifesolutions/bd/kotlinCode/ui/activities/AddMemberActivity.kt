package com.lifesolutions.bd.kotlinCode.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.lifesolutions.bd.kotlinCode.data.model.FriendData
import com.lifesolutions.bd.kotlinCode.ui.adapter.AddGroupMemberAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.activity_add_member.*

class AddMemberActivity : AppCompatActivity() {

    private val TAG = "AddMemberActivity"
    private var groupID: String? = null
    private var adminId: String? = null
    private lateinit var db: DatabaseReference
    private lateinit var groupReference: DatabaseReference
    private lateinit var userReference: DatabaseReference
    private lateinit var friendReference: DatabaseReference

    private lateinit var memberAdapter: AddGroupMemberAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    private lateinit var animatedLoading: AnimatedLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lifesolutions.bd.R.layout.activity_add_member)

        // Set Actionbar
        setSupportActionBar(toolbar_add_member_group)
        supportActionBar?.title = "Add Group Members"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        animatedLoading = AnimatedLoading(this)

        if (intent != null) {
            groupID = intent.getStringExtra("groupId")
            adminId = intent.getStringExtra("adminId")
        }

        groupReference = FirebaseDatabase.getInstance().getReference("ChatGroups").child(groupID!!)
        userReference = FirebaseDatabase.getInstance().getReference("Users")
        // friendReference = FirebaseDatabase.getInstance().getReference("friendRequest").child(adminId!!)
        friendReference =
            FirebaseDatabase.getInstance().getReference("FriendsList").child(adminId!!)


        // Feed Adapter
        mLayoutManager = LinearLayoutManager(this)
        memberAdapter = AddGroupMemberAdapter(this)

        recycler_view_member_list.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = memberAdapter
        }

        getFriendList()

    }


    private fun getFriendList() {
        animatedLoading.showAnimatedLoading()

        val users = ArrayList<FriendData>()

        friendReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    users.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(FriendData::class.java)
                        users.add(user!!)
                    }

                    // Recycler Adapter Changed..
                    memberAdapter.removeAllItem()
                    memberAdapter.addAllFriends(users, groupID!!)
                    memberAdapter.notifyDataSetChanged()

                    animatedLoading.hideAnimatedLoading()

                } else {
                    animatedLoading.hideAnimatedLoading()
                    // TODO
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


    // On Done Click
    fun onClickDone(view: View) {
        Intent(this, GroupMessageActivity::class.java).apply {
            this.putExtra("groupId", groupID)
            finish()
            // flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }


}