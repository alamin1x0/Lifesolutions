package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Activities.FullPostActivity;
import com.lifesolutions.bd.Models.PostItem;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

public class ProfilePostListAdapter extends RecyclerView.Adapter<ProfilePostListAdapter.MyPostViewHolder> {
    private Context mContext;
    private ArrayList<PostItem> postItems;

    public ProfilePostListAdapter(Context mContext, ArrayList<PostItem> postItems) {
        this.mContext = mContext;
        this.postItems = postItems;
    }

    @NonNull
    @Override
    public MyPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyPostViewHolder(LayoutInflater.from(mContext).inflate(R.layout.profile_post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPostViewHolder holder, final int position) {

        Picasso.get().load(postItems.get(position).getPostThumbnail()).into(holder.post_image);
        holder.post_image.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, FullPostActivity.class).putExtra("Id",postItems.get(position).getPostID())));

    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public class MyPostViewHolder extends RecyclerView.ViewHolder {

        ImageView post_image;

        public MyPostViewHolder(@NonNull View itemView) {
            super(itemView);
            post_image = itemView.findViewById(R.id.image_profile_post_item);
        }
    }
}
