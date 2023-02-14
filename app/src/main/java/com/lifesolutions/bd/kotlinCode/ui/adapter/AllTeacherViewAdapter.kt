package com.lifesolutions.bd.kotlinCode.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
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
import com.lifesolutions.bd.kotlinCode.data.model.CallHistory
import com.lifesolutions.bd.kotlinCode.data.model.Teacher
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.teacher_details_card.view.*


class AllTeacherViewAdapter(
    private val context: Context,
    private val users: List<Teacher>
) : RecyclerView.Adapter<AllTeacherViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.teacher_details_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val teacher = users[position]

        // Set Value
        if (teacher.profileImage != null && teacher.profileImage != "none") {
            Glide.with(context)
                .load(teacher.profileImage)
                .into(holder.imageView)
        } else {
            Glide.with(context)
                .load(R.drawable.user_low)
                .into(holder.imageView)
        }
        holder.name.text =  "${teacher.firstName} ${teacher.lastName}"
        val rate = (teacher.ratings?.div(teacher.rated!!))
        holder.rateView.rating = rate?.toFloat()!!

        holder.btnCall.setOnClickListener {
            callUserAudio(teacher._id!!)
        }
        holder.btnChat.setOnClickListener {
            Intent(context, PersonalMessageActivity::class.java).apply {
                this.putExtra("receiverID", teacher._id)
                context.startActivity(this)
            }
        }
        holder.btnViewProfile.setOnClickListener {
            Intent(context, UserProfileActivityKt::class.java).apply {
                this.putExtra("uID", teacher._id)
                context.startActivity(this)
            }
        }

     //   holder.callDuration.text = "Total Call Duration ${teacher.talktime} Minuets"
        getUserCallDuration(teacher._id, "audioCall", holder.callDuration)


        getOnlineStatus(teacher._id!!, holder.activeBadge)

    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tv_name
        val callDuration: TextView = itemView.tv_call_duration
        val activeBadge: View = itemView.active_badge_view
        var imageView: ImageView = itemView.iv_profile_img
        var rateView: RatingBar = itemView.ratings
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


    private fun getUserCallDuration(uId: String?, callChild: String, tvCallDuration: TextView) {
        if (uId != null) {
            val ref = FirebaseDatabase.getInstance().getReference("UsersCallData").child(uId).child(callChild)

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val data: CallHistory? = dataSnapshot.getValue(CallHistory::class.java)
                        tvCallDuration.text = "Total Call Duration ${secondToMin(data?.duration)} Minuets"
                    } else {
                        tvCallDuration.text = "No Call Duration Found"
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }
    }

    private fun secondToMin(sec: Long?): Long {
        return if (sec != null) {
            sec / 60
        } else {
            0
        }
    }


}