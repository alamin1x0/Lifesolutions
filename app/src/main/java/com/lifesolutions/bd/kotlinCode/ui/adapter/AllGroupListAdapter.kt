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
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.ui.activities.JoinGroupActivity
import kotlinx.android.synthetic.main.group_list_item_vertical.view.*
import java.util.*

class AllGroupListAdapter(
    private val context: Context
) : RecyclerView.Adapter<AllGroupListAdapter.ListViewAdapter>() {

    private var groupList = mutableListOf<GroupConversation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewAdapter {
        return ListViewAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.group_list_item_vertical, parent, false)
        )
    }

    override fun getItemCount() = groupList.size

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewAdapter, position: Int) {
        val group = groupList[position]

        holder.groupName.text = group.groupName
        holder.groupCat.text = group.groupCat

        // Set Profile Image
        if (group.groupImage != null && group.groupImage != "none") {
            Picasso.get().load(group.groupImage).into(holder.groupImage)
        } else {
            Picasso.get().load(R.drawable.ic_group_placeholder).into(holder.groupImage)
        }


        // Click..
        holder.btnJoin.setOnClickListener {
            Intent(context, JoinGroupActivity::class.java).apply {
                putExtra("groupId", group.groupId)
                context.startActivity(this)
            }
        }

    }

    /**
     * Extra Functions
     */
    fun addAllConversation(newConversation: ArrayList<GroupConversation>) {
        groupList = newConversation
        notifyDataSetChanged()
    }

    fun removeAllItem() {
        groupList.clear()
    }



    inner class ListViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // val conversationLayout: ConstraintLayout = itemView.constraint_layout_group_list
        val groupImage: ImageView = itemView.group_image_view_on_random
        val groupName: TextView = itemView.tv_group_name
        val groupCat: TextView = itemView.tv_group_cat
        val btnJoin: Button = itemView.btn_group_join
    }

}