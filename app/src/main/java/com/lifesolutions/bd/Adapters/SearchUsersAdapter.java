package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Activities.ProfileActivity;
import com.lifesolutions.bd.Activities.UserProfileActivity;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchUsersAdapter extends RecyclerView.Adapter<SearchUsersAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<User> users;
    private String uID = FirebaseAuth.getInstance().getUid();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

    public SearchUsersAdapter(Context mContext, ArrayList<User> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @NonNull
    @Override
    public SearchUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchUsersAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.user_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchUsersAdapter.ViewHolder holder, final int position) {

        holder.userName.setText(users.get(position).getFirstName()+" "+users.get(position).getLastName());
        holder.address.setText(users.get(position).getAddress());
        if (users.get(position).getImageThumbnail().equals("none")){
            holder.user_image.setImageResource(R.drawable.user_low);
        } else {
            Picasso.get().load(users.get(position).getImageThumbnail()).into(holder.user_image);
        }

        holder.item.setOnClickListener(view -> {
            if (!users.get(position).getuID().equals(uID)){
                mContext.startActivity(new Intent(mContext, UserProfileActivity.class).putExtra("uID",users.get(position).getuID()));
            } else {
                mContext.startActivity(new Intent(mContext, ProfileActivity.class).putExtra("uID",users.get(position).getuID()));
            }

        });



    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView userName,address;
        CircleImageView user_image,onlineStatus;
        RelativeLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.profile_image_user);
            userName = itemView.findViewById(R.id.name_users_item);
            address = itemView.findViewById(R.id.address_users_item);
            item = itemView.findViewById(R.id.users_item);
            onlineStatus = itemView.findViewById(R.id.online_status_user_layout);
        }
    }
}
