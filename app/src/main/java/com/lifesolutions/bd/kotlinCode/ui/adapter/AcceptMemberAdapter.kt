package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupMember
import com.lifesolutions.bd.kotlinCode.data.model.GroupMemberRequest
import kotlinx.android.synthetic.main.user_list_view_one.view.*
import java.util.*

class AcceptMemberAdapter(
    private val context: Context
) : RecyclerView.Adapter<AcceptMemberAdapter.ListViewAdapter>() {

    private var groupId: String? = null
    private var requestList = mutableListOf<GroupMemberRequest>()
    private val userReference = FirebaseDatabase.getInstance().getReference("Users")
    private val groupReference = FirebaseDatabase.getInstance().getReference("GroupConversations")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_view_one, parent, false)
        )
    }

    override fun getItemCount() = requestList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val member = requestList[position]

        setUI(member, holder)

    }

    private fun setUI(member: GroupMemberRequest, holder: AcceptMemberAdapter.ListViewAdapter) {
        holder.userName.text = "${member.firstName} ${member.lastName}"

        if (member.image != null && member.image != "none") {
            Picasso.get().load(member.image).into(holder.profileImage)
        } else {
            Picasso.get().load(R.drawable.user_low).into(holder.profileImage)
        }

        // Click..
        holder.addUserBtn.setOnClickListener {
            addMemberToGroup(member, holder.addUserBtn)
        }

    }

    /**
     * Extra Functions
     */
    fun addAllFriends(requests: ArrayList<GroupMemberRequest>, groupId: String) {
        requestList = requests
        this.groupId = groupId
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        requestList.clear()
    }

    @SuppressLint("SetTextI18n")
    private fun addMemberToGroup(user: GroupMemberRequest, textBtn: TextView) {
        val timestamp = System.currentTimeMillis()
        val member = GroupMember(
            user.userId,
            user.firstName,
            user.lastName,
            user.image,
            timestamp,
            "member",
            user.timestamp,
            false
        )
        groupReference.child(groupId!!).child("Members").child(user.userId!!)
            .setValue(member).addOnSuccessListener {
                groupReference.child(groupId!!).child("Requests").child(user.userId)
                    .removeValue().addOnSuccessListener {
                        textBtn.text = "Added"
                        textBtn.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.color_mgs_seen
                            )
                        );
                    }
            }
    }


    inner class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val addUserBtn: TextView = itemView.add_user_btn
        val profileImage: ImageView = itemView.user_profile_pic
        val userName: TextView = itemView.user_full_name
    }

}