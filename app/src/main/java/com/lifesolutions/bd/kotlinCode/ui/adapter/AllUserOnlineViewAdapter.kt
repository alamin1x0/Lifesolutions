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
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.ActiveUser
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import kotlinx.android.synthetic.main.all_online_user_item_view.view.*


class AllUserOnlineViewAdapter(
    private val context: Context,
    private val users: List<ActiveUser>
) : RecyclerView.Adapter<AllUserOnlineViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_online_user_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = users[position]

        if (user.imageThumbnail != null && user.imageThumbnail != "none") {
            Glide.with(context)
                .load(user.imageThumbnail)
                .into(holder.imageView)
        } else {
            Glide.with(context)
                .load(R.drawable.user_low)
                .into(holder.imageView)
        }

        when {
            user.firstName.isNullOrEmpty() -> {
                holder.firstName.text = "Unnamed"
            }
            else -> {
                holder.firstName.text = user.firstName
            }
        }


        // Onclick Item...
        holder.itemView.setOnClickListener {
           Intent(context, PersonalMessageActivity::class.java).apply {
               var mReference: DatabaseReference? = null
               mReference = FirebaseDatabase.getInstance().reference.child("CustomCalling")
               this.putExtra("receiverID", user.id)
               mReference.child(user.id.toString()).setValue("lds")
               context.startActivity(this)
           }

        }
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.tv_name_online_user
        var imageView: ImageView = itemView.iv_online_user
        var mainLayout: ConstraintLayout = itemView.user_item_layout
    }
}