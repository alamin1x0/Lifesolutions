package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupMember
import com.lifesolutions.bd.kotlinCode.data.model.GroupTopic
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.user_list_view_group_member.view.*
import java.util.*
import kotlin.collections.ArrayList

class GroupMemberAdapter(
    private val context: Context,
    private val currentUserRole: String,
    private val topic: String,
    private val memberList: ArrayList<GroupMember>,
    private val groupId: String?
) : RecyclerView.Adapter<GroupMemberAdapter.ListViewAdapter>() {

    //private var groupId: String? = null

    //private var currentUserRole: String? = null
    // Shared Pref Current User data..
    //private var userPreferences: SharedPreferences = context.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)!!
    //private var memberList = mutableListOf<GroupMember>()

    //private val userReference = FirebaseDatabase.getInstance().getReference("Users")
    private val groupReference = FirebaseDatabase.getInstance().getReference("GroupConversations")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_view_group_member, parent, false)
        )
    }

    override fun getItemCount() = memberList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val member = memberList[position]
        val since = Utils.getNormalDate(member.joinAt!!)
        val currentUserID = FirebaseAuth.getInstance().uid

        /*if (currentUserID == member.memberId) {
            currentUserRole = member.role
        }*/

        setUI(member, holder, since, currentUserID)

    }

    private fun setUI(
        member: GroupMember,
        holder: ListViewAdapter,
        since: String?,
        currentUserID: String?
    ) {
        /*userReference.child(member.memberId!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val user = dataSnapshot.getValue(UserKt::class.java)

                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })*/

        val fullName = member.firstName + " " + member.lastName
        holder.userName.text = fullName
        val memberSinceText = "Since $since (${member.role})"
        holder.memberSince.text = memberSinceText

        if (member.profileImage != null && member.profileImage != "none") {
            Picasso.get().load(member.profileImage).into(holder.profileImage)
        } else {
            Picasso.get().load(R.drawable.ic_group_placeholder)
                .into(holder.profileImage)
        }


        if (currentUserRole != "admin") {
            holder.optionBtn.visibility = View.GONE
        }

        // Click..
        holder.optionBtn.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.optionBtn)
            popupMenu.inflate(R.menu.menu_group_member_option)


            if (member.memberId == currentUserID || member.role == "admin") {
                popupMenu.menu.removeItem(R.id.menu_make_admin)
                popupMenu.menu.removeItem(R.id.menu_remove)
            }

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->

                when (item.itemId) {
                    R.id.menu_make_admin -> {
                        makeGroupAdmin(member.memberId!!)
                    }

                    R.id.menu_remove -> {
                        removeMemberFromGroup(member.memberId!!)
                    }

                    R.id.menu_view_profile -> {
                        context.toast("View Profile")
                    }
                }
                false
            }
            popupMenu.show()
            // removeMemberFromGroup(user!!, holder.addUserBtn)
        }

    }

    /**
     * Extra Functions
     */
    /*fun addAllFriends(friendsKey: ArrayList<GroupMember>, groupId: String) {
        memberList = friendsKey
        this.groupId = groupId
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        memberList.clear()
    }*/

    @SuppressLint("SetTextI18n")
    private fun removeMemberFromGroup(memberId: String) {
        groupReference.child(groupId!!).child("Members").child(memberId)
            .removeValue().addOnSuccessListener {
                context.toast("Member has been removed")
                FirebaseDatabase.getInstance().getReference("TopicToRemove")
                    .child(memberId)
                    .child(System.currentTimeMillis().toString())
                    .setValue(GroupTopic(topic)).addOnFailureListener {
                        context.toast("This user can be see notification from this group")
                    }
            }
    }

    private fun makeGroupAdmin(memberId: String) {
        groupReference.child(groupId!!).child("Members").child(memberId).child("role")
            .setValue("admin").addOnSuccessListener {
                context.toast("Success")
            }
    }


    inner class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionBtn: ImageButton = itemView.option_member
        val profileImage: ImageView = itemView.user_profile_pic
        val userName: TextView = itemView.user_full_name
        val memberSince: TextView = itemView.tv_member_since
    }

}