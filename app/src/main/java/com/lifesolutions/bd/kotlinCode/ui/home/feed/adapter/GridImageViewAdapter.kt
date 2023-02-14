package com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lifesolutions.bd.Activities.ViewImageActivity
import com.lifesolutions.bd.R
import kotlinx.android.synthetic.main.image_view__on_feed.view.*

class GridImageViewAdapter(
    val context: Context,
    private val imageUrls: List<String>
): RecyclerView.Adapter<GridImageViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_view__on_feed, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = imageUrls[position]

        Glide.with(context)
            .load(url)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            // Toast.makeText(context, url, Toast.LENGTH_SHORT).show()

            Intent(context, ViewImageActivity::class.java).apply {
                putExtra("imageUrl", url)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(this)
            }
        }
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.post_image_post_item_single_multi
    }

}