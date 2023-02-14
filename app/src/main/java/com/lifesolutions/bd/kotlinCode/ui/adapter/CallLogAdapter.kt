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
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.CallLog
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import kotlinx.android.synthetic.main.call_log_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class CallLogAdapter(
    private val context: Context
) : RecyclerView.Adapter<CallLogAdapter.ListViewAdapter>() {

    private var callLogList = mutableListOf<CallLog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.call_log_list_item, parent, false)
        )
    }

    override fun getItemCount() = callLogList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val callLog = callLogList[position]

        holder.userName.text = callLog.username

        val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
        // val sdf = SimpleDateFormat("dd/MM/yy")
        val netDate = Date(callLog.lastTimestamps as Long)
        val date = sdf.format(netDate)
        holder.dateConversation.text = date

        // Set Profile Image
        if (callLog.userProfileThumb != null && callLog.userProfileThumb != "none") {
            Picasso.get().load(callLog.userProfileThumb).into(holder.profileImage)
        } else {
            Picasso.get().load(R.drawable.user_low).into(holder.profileImage)
        }

        // Set Call Type Icon
        if (callLog.callType?.trim() == "video") {
            Glide.with(context)
                .load(R.drawable.ic_video_call_icon)
                .into(holder.callTypeIcon)
        } else {
            Glide.with(context)
                .load(R.drawable.ic_call_black_24dp)
                .into(holder.callTypeIcon)
        }

        // Set Call Status Icon
        when(callLog.callStatus?.trim()) {
            "incoming" -> {
                Glide.with(context)
                    .load(R.drawable.ic_incomming_call_new)
                    .into(holder.callStatusIcon)
            }
            "outgoing" -> {
                Glide.with(context)
                    .load(R.drawable.ic_outgoing_call_new)
                    .into(holder.callStatusIcon)
            }
            "missed" -> {
                Glide.with(context)
                    .load(R.drawable.ic_missed_call_new)
                    .into(holder.callStatusIcon)
            }
            else -> {
                Glide.with(context)
                    .load(R.drawable.ic_incoming_arrow)
                    .into(holder.callStatusIcon)
            }
        }



        // Click..
        holder.conversationLayout.setOnClickListener {
            Intent(context, PersonalMessageActivity::class.java).apply {
                putExtra("receiverID", callLogList[position].uid)
                context.startActivity(this)
            }
        }

    }

    /**
     * Extra Functions
     */
    fun addAllConversation(newConversation: ArrayList<CallLog>) {
        callLogList = newConversation
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        callLogList.clear()
    }


    inner class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val conversationLayout: ConstraintLayout = itemView.constraint_layout_call_log
        val profileImage: ImageView = itemView.iv_profile_user_call_log
        val userName: TextView = itemView.tv_user_name_call_log
        val dateConversation: TextView = itemView.tv_date_call_log
        val callTypeIcon: ImageView = itemView.iv_icon_call_log
        val callStatusIcon: ImageView = itemView.iv_call_status_call_log
    }

}