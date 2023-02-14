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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.UserProfileActivity
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import kotlinx.android.synthetic.main.user_list_view_one.view.*
import java.util.*

class UserFriendListsAdapter(
    private val context: Context
) : RecyclerView.Adapter<UserFriendListsAdapter.ListViewAdapter>() {

    private var currentUserId: String? = null
    private var friendsList = mutableListOf<String>()
    private val userReference = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_view_one, parent, false)
        )
    }

    override fun getItemCount() = friendsList.size

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val friendsKey = friendsList[position]

        holder.addUserBtn.text = "Message"

        setUI(friendsKey, holder)

    }

    private fun setUI(friendsKey: String, holder: UserFriendListsAdapter.ListViewAdapter) {
        userReference.child(friendsKey).addListenerForSingleValueEvent(object: ValueEventListener{

            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
               if (dataSnapshot.exists()) {
                   val user = dataSnapshot.getValue(UserKt::class.java)
                   holder.userName.text = user?.firstName + " " + user?.lastName

                   if (user?.imageThumbnail != null && user.imageThumbnail != "none") {
                       Picasso.get().load(user.imageThumbnail).into(holder.profileImage)
                   } else {
                       Picasso.get().load(R.drawable.user_low).into(holder.profileImage)
                   }

                   // Click..
                   holder.addUserBtn.setOnClickListener {
                       Intent(context, PersonalMessageActivity::class.java).apply {
                           this.putExtra("receiverID", user?.uID)
                           context.startActivity(this)
                       }
                   }

                   holder.itemView.setOnClickListener{
                       Intent(context, UserProfileActivity::class.java).apply {
                           this.putExtra("uID", user?.uID)
                           context.startActivity(this)
                       }
                   }
               }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }

    /**
     * Extra Functions
     */
    fun addAllFriends(friendsKey: ArrayList<String>, userId: String) {
        friendsList = friendsKey
        this.currentUserId = userId
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        friendsList.clear()
    }


    inner class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val addUserBtn: TextView = itemView.add_user_btn
        val profileImage: ImageView = itemView.user_profile_pic
        val userName: TextView = itemView.user_full_name
    }

}