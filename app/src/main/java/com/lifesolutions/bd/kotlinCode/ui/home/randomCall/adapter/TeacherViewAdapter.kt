package com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.lifesolutions.bd.Activities.BaseActivity
import com.lifesolutions.bd.Activities.CallScreenActivity
import com.lifesolutions.bd.Services.SinchService
import com.lifesolutions.bd.kotlinCode.data.model.CallHistory
import com.lifesolutions.bd.kotlinCode.data.model.Teacher
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.teacher_card_view_hz.view.*


class TeacherViewAdapter(
    private val context: Context,
    private val users: List<Teacher>
) : RecyclerView.Adapter<TeacherViewAdapter.ViewHolder>() {

    private val TAG = "TeachersViewAdapter"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(com.lifesolutions.bd.R.layout.teacher_card_view_hz, parent, false)
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
                .load(com.lifesolutions.bd.R.drawable.user_low)
                .into(holder.image)
        }

        holder.name.text = "${user.firstName} ${user.lastName}"
        getOnlineStatus(user._id!!, holder.onlineBadge)


        // context.toast(rate.toString())

//
//        // Onclick Item...
//        holder.itemView.setOnClickListener {
//           Intent(context, PersonalMessageActivity::class.java).apply {
//               this.putExtra("receiverID", usecom.lifesolutions.bd.R.id)
//               context.startActivity(this)
//           }
//
//        }


        holder.itemView.setOnClickListener {
            showDialog(user)
//            Intent(context, TeacherSingleActivity::class.java).apply {
//                // putExtra("receiverID", callLogList[position].uid)
//                context.startActivity(this)
//            }
        }
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.teacher_name
        val image: ImageView = itemView.iv_profile_img
        val rating: RatingBar = itemView.teacher_rating
        val onlineBadge: View = itemView.iv_online_badge
        val call_duration: TextView =itemView.tv_talkTime


    }


    private fun showDialog(teacher: Teacher) {
        val epicDialog = Dialog(context)
        epicDialog.setContentView(com.lifesolutions.bd.R.layout.teacher_details_card_dialog)
        // ID....
        val closeBtn = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.close_dialog)
        val btnViewProfile = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.btn_view_profile)
        val name: TextView = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.tv_name)
        val callDuration: TextView = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.tv_call_time)
        val image: ImageView = epicDialog.findViewById(com.lifesolutions.bd.R.id.iv_profile_img)
        val rating: RatingBar = epicDialog.findViewById(com.lifesolutions.bd.R.id.ratings)
        val btnCall: Button = epicDialog.findViewById(com.lifesolutions.bd.R.id.btn_call_user)
        val btnChat: Button = epicDialog.findViewById(com.lifesolutions.bd.R.id.btn_message_user)

        // Set Value
        if (teacher.profileImage != null && teacher.profileImage != "none") {
            Glide.with(context)
                .load(teacher.profileImage)
                .into(image)
        } else {
            Glide.with(context)
                .load(com.lifesolutions.bd.R.drawable.user_low)
                .into(image)
        }
        name.text =  "${teacher.firstName} ${teacher.lastName}"
        rating.rating = teacher.ratings?.toFloat()!!
        getUserCallDuration(teacher._id, "audioCall", callDuration)



        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        epicDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        epicDialog.setCancelable(true)
        closeBtn.setOnClickListener {
            epicDialog.dismiss()
        }
        btnCall.setOnClickListener {
            callUserAudio(teacher._id!!, epicDialog)
        }
        btnChat.setOnClickListener {
            Intent(context, PersonalMessageActivity::class.java).apply {
                this.putExtra("receiverID", teacher._id)
                context.startActivity(this)
                epicDialog.dismiss()
            }
        }
        btnViewProfile.setOnClickListener {
            Intent(context, UserProfileActivityKt::class.java).apply {
                this.putExtra("uID", teacher._id)
                context.startActivity(this)
                epicDialog.dismiss()
            }
        }
        epicDialog.show()
    }


    private fun callUserAudio(receiverId: String, dialog: Dialog) {
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
                dialog.dismiss()
            }
        } else {
            context.toast("Just a moment")
        }
    }

    private fun getOnlineStatus(uId: String, badge: View) {
        val ref = FirebaseDatabase.getInstance().getReference("ActiveNow").child(uId)
        ref.addValueEventListener(object: ValueEventListener{

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
            val mDatabaseRef = FirebaseDatabase.getInstance().getReference("Teachers")

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val data: CallHistory? = dataSnapshot.getValue(CallHistory::class.java)
                        tvCallDuration.text = "Total Call Duration ${secondToMin(data?.duration)} Minuets"
                        val toMinute=secondToMin(data?.duration)
                        mDatabaseRef.child(uId).child("talktime").setValue(toMinute)

                    } else {
                        tvCallDuration.text = "No Call Duration Found"
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }
    }


    private fun getUserCallDurationForRaindomCallFragment(uId: String?, callChild: String, tvCallDuration: TextView) {
        if (uId != null) {
            val ref = FirebaseDatabase.getInstance().getReference("UsersCallData").child(uId).child(callChild)



            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val data: CallHistory? = dataSnapshot.getValue(CallHistory::class.java)
                        tvCallDuration.text = "${secondToMin(data?.duration)} min"
                    } else {
                        tvCallDuration.text = "0m"
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