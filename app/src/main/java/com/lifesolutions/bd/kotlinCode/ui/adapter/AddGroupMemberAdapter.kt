package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.*
import com.lifesolutions.bd.kotlinCode.utils.Notify
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.user_list_view_one.view.*
import java.util.*

class AddGroupMemberAdapter(
    private val context: Context
) : RecyclerView.Adapter<AddGroupMemberAdapter.ListViewAdapter>() {
    private val TAG = "AddGroupMemberAdapter"
    private var groupId: String? = null
    private var friendsList = mutableListOf<FriendData>()
    private val groupReference = FirebaseDatabase.getInstance().getReference("GroupConversations")
    private val userPreferences =
        context.getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_view_one, parent, false)
        )
    }

    override fun getItemCount() = friendsList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val friend = friendsList[position]

        Log.w(TAG, "onBindViewHolder: ............")
        Log.w(TAG, "${friend}")

        setUI(friend, holder)
        friendGroupMemberStatus(friend, holder.addUserBtn)

    }

    @SuppressLint("SetTextI18n")
    private fun setUI(friend: FriendData, holder: AddGroupMemberAdapter.ListViewAdapter) {

        holder.userName.text = "${friend.firstName} ${friend.lastName}"

        if (friend.profileImg != null && friend.profileImg != "none") {
            Log.w(TAG, "setUI: -> ....... -> ${friend.profileImg}")
            Picasso.get().load(friend.profileImg).into(holder.profileImage)
        } else {
            Picasso.get().load(R.drawable.user_low).into(holder.profileImage)
        }

        // Click..
        holder.addUserBtn.setOnClickListener {
            when (holder.addUserBtn.text) {
                "Add" -> {
                    addMemberToGroup(friend, holder.addUserBtn)
                }
                "Cancel" -> {
                    removeMemberFromGroup(friend, holder.addUserBtn)
                }
                "Member" -> {
                    context.toast("Already added in this group")
                }
            }
        }

    }

    /**
     * Extra Functions
     */
    fun addAllFriends(friendsData: ArrayList<FriendData>, groupId: String) {
        friendsList = friendsData
        this.groupId = groupId
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        friendsList.clear()
    }

    @SuppressLint("SetTextI18n")
    private fun addMemberToGroup(user: FriendData, textBtn: TextView) {
        val timestamp = System.currentTimeMillis()
        val member = GroupMember(
            user._id,
            user.firstName,
            user.lastName,
            user.profileImg,
            timestamp,
            "member",
            timestamp,
            false
        )
        groupReference.child(groupId!!).child("Members").child(user._id!!)
            .setValue(member).addOnSuccessListener {
                sendNotification(groupId!!, user._id)
                textBtn.text = "Cancel"
            }
    }

    @SuppressLint("SetTextI18n")
    private fun removeMemberFromGroup(user: FriendData, textBtn: TextView) {
        groupReference.child(groupId!!).child("Members").child(user._id!!)
            .removeValue().addOnSuccessListener {
                textBtn.text = "Add"
            }
    }

    @SuppressLint("SetTextI18n")
    private fun friendGroupMemberStatus(user: FriendData, textBtn: TextView) {
        val ref = groupReference.child(groupId!!).child("Members").child(user._id!!)

        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    textBtn.text = "Member"
                    textBtn.setTextColor(ContextCompat.getColor(context, R.color.green))
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }


    inner class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val addUserBtn: TextView = itemView.add_user_btn
        val profileImage: ImageView = itemView.user_profile_pic
        val userName: TextView = itemView.user_full_name
    }


    /**
     * Notification
     */

    private fun sendNotification(groupId: String, receiverID: String) {
        val currentUserID = userPreferences.getString("uID", null)

        val tokenRef = FirebaseDatabase.getInstance().getReference("CloudTokens")
        tokenRef.child(receiverID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val (token) = dataSnapshot.getValue(CloudToken::class.java)!!
                    val notificationData = NotificationData(
                        groupId,
                        "You added to a new group",
                        "New Group Add",
                        "",
                        "groupMessage",
                        currentUserID,
                        R.drawable.app_icon
                    )
                    val pushNotification =
                        PushNotification(notificationData, token!!)
                    Notify.sendMgsNotification(pushNotification)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //TODO....
            }
        })
    }

}