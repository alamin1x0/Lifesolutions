package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.FriendData
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt
import kotlinx.android.synthetic.main.friend_card_layout.view.*
import kotlinx.android.synthetic.main.teacher_card_view_hz.view.*


class FriendGridViewAdapter(
    private val context: Context,
    private val users: List<FriendData>
) : RecyclerView.Adapter<FriendGridViewAdapter.ViewHolder>() {

    private val TAG = "FriendGridViewAdapter"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = users[position]


        if (!user.profileImg.isNullOrEmpty() && user.profileImg != "none") {
            Glide.with(context)
                .load(user.profileImg)
                .into(holder.image)
        } else {
            Glide.with(context)
                .load(R.drawable.user_low)
                .into(holder.image)
        }

        holder.name.text = "${user.firstName}"

        holder.itemView.setOnClickListener {
            Intent(context, UserProfileActivityKt::class.java).apply {
                this.putExtra("uID", user._id)
                context.startActivity(this)
            }
        }

    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tv_name_of_friend
        val image: ImageView = itemView.iv_profile_picture
    }


}