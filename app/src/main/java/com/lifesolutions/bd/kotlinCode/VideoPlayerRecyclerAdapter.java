package com.lifesolutions.bd.kotlinCode;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.data.model.VideoPost;

import java.util.ArrayList;


public class VideoPlayerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<VideoPost> mediaObjects2;
    private RequestManager requestManager;


    public VideoPlayerRecyclerAdapter(ArrayList<VideoPost> mediaObjects, RequestManager requestManager, Context context) {
        this.mediaObjects2 = mediaObjects;
        this.requestManager = requestManager;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VideoPlayerViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_video_list_item, viewGroup, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((VideoPlayerViewHolder) viewHolder).onBind(mediaObjects2.get(i), requestManager);
    }

    @Override
    public int getItemCount() {
        return mediaObjects2.size();
    }

}














