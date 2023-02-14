package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.ui.activities.GroupMessageActivity
import kotlinx.android.synthetic.main.activity_join_group.*
import kotlinx.android.synthetic.main.group_chat_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class GroupConversationListAdapter(
    private val context: Context
) : RecyclerView.Adapter<GroupConversationListAdapter.ListViewAdapter>() {

    private var groupList = mutableListOf<GroupConversation>()
    val  userPreferences = context.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.group_chat_list_item, parent, false)
        )
    }

    override fun getItemCount() = groupList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val group = groupList[position]

        holder.groupName.text = group.groupName

        // val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
        val sdf = SimpleDateFormat("dd/MM/yy")
        val netDate = Date(group.createdTime!!)
        val date = sdf.format(netDate)
        holder.lastConversation.text = group.lastConversation.take(35)

        // Set Profile Image
        if (group.groupImage != null && group.groupImage != "none") {
            Picasso.get().load(group.groupImage).into(holder.groupImage)
        } else {
            Picasso.get().load(R.drawable.ic_group_placeholder).into(holder.groupImage)
        }



        // Click..
        holder.conversationLayout.setOnClickListener {
            Intent(context, GroupMessageActivity::class.java).apply {
                putExtra("groupId", group.groupId)
                context.startActivity(this)
            }
        }

        // checkCurrentUserRole(group.groupId!!, holder)

    }

    /**
     * Extra Functions
     */
    fun addAllConversation(newConversation: ArrayList<GroupConversation>) {
        groupList = newConversation
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        groupList.clear()
    }


//    private fun checkRequestList(groupId: String, holder: ListViewAdapter) {
//        val ref = FirebaseDatabase.getInstance().getReference("GroupConversation").child(groupId)
//
//            ref.child("Requests").addValueEventListener(object: ValueEventListener{
//                @SuppressLint("SetTextI18n")
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                       val reqCount = dataSnapshot.childrenCount
//                        holder.requestCount.text = reqCount.toString()
//                    } else {
//                        holder.requestCount.visibility = View.GONE
//                    }
//                }
//
//                override fun onCancelled(p0: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//
//            })
//    }

//    private fun checkCurrentUserRole(groupId: String, holder: ListViewAdapter) {
//        val authId = userPreferences.getString("uID", null)
//        val ref = FirebaseDatabase.getInstance().getReference("GroupConversation").child(groupId)
//
//        ref.child("Members").child(authId!!).addListenerForSingleValueEvent(object: ValueEventListener{
//            @SuppressLint("SetTextI18n")
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    val member = dataSnapshot.getValue(GroupMember::class.java)
//                    if (member?.role == "admin") {
//                        checkRequestList(groupId, holder)
//                    }
//                } else {
//                    holder.requestCount.visibility = View.GONE
//                }
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//
//        })
//    }


    inner class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val conversationLayout: ConstraintLayout = itemView.constraint_layout_group_list
        val groupImage: ImageView = itemView.iv_profile_user_group_list
        val groupName: TextView = itemView.tv_group_name_group_list
        val requestCount: TextView = itemView.tv_request_count
        val lastConversation: TextView = itemView.tv_last_msg_group_list
    }

}