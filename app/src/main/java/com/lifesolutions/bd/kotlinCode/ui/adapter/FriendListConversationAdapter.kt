package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.FriendData
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import kotlinx.android.synthetic.main.friend_list_conversation.view.*
import java.util.*

class FriendListConversationAdapter(
    private val context: Context
) : RecyclerView.Adapter<FriendListConversationAdapter.ListViewAdapter>() {

    private var authId: String? = null
    private var friendsList = mutableListOf<FriendData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.friend_list_conversation, parent, false)
        )
    }

    override fun getItemCount() = friendsList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val friend = friendsList[position]

        holder.userName.text = "${friend.firstName} ${friend.lastName}"

        if (friend.profileImg != null && friend.profileImg != "none") {
            Picasso.get().load(friend.profileImg).into(holder.profileImage)
        } else {
            Picasso.get().load(R.drawable.user_low).into(holder.profileImage)
        }

        holder.btnChatUser.setOnClickListener {
            Intent(context, PersonalMessageActivity::class.java).apply {
                this.putExtra("receiverID", friend._id)
                context.startActivity(this)
            }
        }

        holder.itemView.setOnClickListener {
            Intent(context, PersonalMessageActivity::class.java).apply {
                this.putExtra("receiverID", friend._id)
                context.startActivity(this)
            }
        }

    }

    /**
     * Extra Functions
     */
    fun addAllFriends(friendsData: ArrayList<FriendData>, authId: String) {
        friendsList = friendsData
        this.authId = authId
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        friendsList.clear()
    }


    inner class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnChatUser: ImageView = itemView.btn_chat_user
        val profileImage: ImageView = itemView.user_profile_pic
        val userName: TextView = itemView.user_full_name
    }

}