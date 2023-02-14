package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.Conversation
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import kotlinx.android.synthetic.main.user_conversation_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ConversationsAdapter(
    private val context: Context
): RecyclerView.Adapter<ConversationsAdapter.ListViewAdapter>() {
    private val db = FirebaseDatabase.getInstance()

    private var conversationList = mutableListOf<Conversation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_conversation_list_item, parent, false)
        )
    }

    override fun getItemCount() = conversationList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val conversation = conversationList[position]

        holder.userName.text = conversation.username

        holder.lastMessage.text = conversation.lastMessage?.take(35)

        if (conversation.seen == false) {
            holder.lastMessage.setTypeface(null, Typeface.NORMAL)
            holder.lastMessage.setTextColor(ContextCompat.getColor(context,
                R.color.color_post_bg_one
            ))
        } else {
            holder.lastMessage.setTypeface(null, Typeface.NORMAL)
            holder.lastMessage.setTextColor(ContextCompat.getColor(context, R.color.color_mgs_seen))
        }

         val sdf = SimpleDateFormat("hh:mm aa")
       // val sdf = SimpleDateFormat("dd/MM/yy")
        val netDate = Date(conversation.lastTimestamps as Long)
        val date =sdf.format(netDate)

        holder.dateConversation.text = date


        if (conversation.userProfileThumb != null && conversation.userProfileThumb != "none") {
            Picasso.get().load(conversation.userProfileThumb).into(holder.profileImage)
        } else {
            Picasso.get().load(R.drawable.user_low).into(holder.profileImage)
        }

        // Get Status
        getOnlineStatus(conversation.uid!!, holder.activeBadge)

        // Click..
        holder.conversationLayout.setOnClickListener {
            Intent(context, PersonalMessageActivity::class.java).apply {
                putExtra("receiverID", conversationList[position].uid)
                context.startActivity(this)
            }
        }



    }

    /**
     * Extra Functions
     */
    fun addAllConversation(newConversation: ArrayList<Conversation>) {
        conversationList = newConversation
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        conversationList.clear()
    }

    private fun getOnlineStatus(userId: String, view: View) {
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    if ( dataSnapshot.child("userActivity").exists()) {
                        val isOnline = dataSnapshot.child("userActivity").child("online").value as Boolean
                        if (isOnline) {
                            view.visibility = View.VISIBLE
                        } else {
                            view.visibility = View.GONE
                        }
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    inner class ListViewAdapter(itemView: View): RecyclerView.ViewHolder(itemView) {
        val conversationLayout : ConstraintLayout = itemView.constraint_layout_conversation
        val profileImage : ImageView = itemView.iv_profile_user_conversation
        val userName : TextView = itemView.tv_user_name_conversation
        val lastMessage : TextView = itemView.tv_last_msg_conversation
        val dateConversation : TextView = itemView.tv_date_conversation
        val activeBadge : View = itemView.active_view_over_conversation
    }

}