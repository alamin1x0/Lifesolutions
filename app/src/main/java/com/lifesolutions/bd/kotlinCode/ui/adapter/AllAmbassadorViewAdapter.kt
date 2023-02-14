package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.Activities.BaseActivity
import com.lifesolutions.bd.Activities.CallScreenActivity
import com.lifesolutions.bd.R
import com.lifesolutions.bd.Services.SinchService
import com.lifesolutions.bd.kotlinCode.data.model.Ambassador
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.ambassador_details_card.view.*


class AllAmbassadorViewAdapter(
    private val context: Context,
    private val users: List<Ambassador>
) : RecyclerView.Adapter<AllAmbassadorViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.ambassador_details_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        if (user.profileImage != null && user.profileImage != "none") {
            Glide.with(context)
                .load(user.profileImage)
                .into(holder.image)
        } else {
            Glide.with(context)
                .load(R.drawable.user_low)
                .into(holder.image)
        }

        holder.name.text = "${user.firstName} ${user.lastName}"
        holder.designation.text = user.designation


        holder.btnCall.setOnClickListener {
            callUserAudio(user._id!!)
        }
        holder.btnChat.setOnClickListener {
            Intent(context, PersonalMessageActivity::class.java).apply {
                this.putExtra("receiverID", user._id)
                context.startActivity(this)
            }
        }
        holder.btnViewProfile.setOnClickListener {
            Intent(context, UserProfileActivityKt::class.java).apply {
                this.putExtra("uID", user._id)
                context.startActivity(this)
            }
        }

        getOnlineStatus(user._id!!, holder.activeBadge)

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.ambassador_name
        val designation: TextView = itemView.ambassador_designation
        val activeBadge: View = itemView.active_badge_view
        val image: ImageView = itemView.iv_profile_img
        val btnViewProfile: Button = itemView.btn_view_profile
        val btnCall: Button = itemView.btn_call_user
        val btnChat: Button = itemView.btn_message_user
    }

    private fun callUserAudio(receiverId: String) {
        if (BaseActivity.getSinchServiceInterface().isStarted) {
            val headers: MutableMap<String, String> = HashMap()
            headers["Content-Type"] = "application/json"
            headers["Authorization"] =
                "key=AAAAH3KuD3I:APA91bEn9xaE0KLfvVsLHxudo_SR3lRsDI4K15Mu-I0BWxQNTPvHzTG4kKofAm979uXiwvZL6UtL5zIwcErSxOTKS3QDZ5UmBWYS4W5SK0iDLLimdvqQ8bgneAkTr1jMWAVBM0qhscc6"
            val call = BaseActivity.getSinchServiceInterface()
                .callUserAudio(receiverId, headers)
            val callId = call.callId
            Intent(context, CallScreenActivity::class.java).apply {
                this.putExtra(SinchService.CALL_ID, callId)
                context.startActivity(this)
            }
        } else {
            context.toast("Just a moment")
        }
    }


    private fun getOnlineStatus(uId: String, badge: View) {
        val ref = FirebaseDatabase.getInstance().getReference("ActiveNow").child(uId)
        ref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    badge.visibility = View.VISIBLE
                } else {
                    badge.visibility = View.GONE
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }



}