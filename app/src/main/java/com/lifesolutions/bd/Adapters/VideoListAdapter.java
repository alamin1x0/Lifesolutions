package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Activities.VideoPlayActivity;
import com.lifesolutions.bd.Models.VideoItem;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>{

    private Context mContext;
    private ArrayList<VideoItem> items;

    public VideoListAdapter(Context mContext, ArrayList<VideoItem> items) {
        this.mContext = mContext;
        this.items = items;
    }


    @NonNull
    @Override
    public VideoListAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoListAdapter.VideoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.video_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoListAdapter.VideoViewHolder holder, final int position) {

        holder.title.setText(items.get(position).getTitle());
        Picasso.get().load(items.get(position).getImageUrl()).into(holder.imageView);
        holder.item.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, VideoPlayActivity.class)
                .putExtra("title",items.get(position).getTitle())
                .putExtra("description",items.get(position).getDescription())
                .putExtra("videoId",items.get(position).getVideoId())));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageView;
        RelativeLayout item;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_video_item);
            imageView = itemView.findViewById(R.id.image_view_video_item);
            item = itemView.findViewById(R.id.item_video_item);
        }
    }
}
