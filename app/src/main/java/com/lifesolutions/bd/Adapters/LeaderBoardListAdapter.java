package com.lifesolutions.bd.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Activities.ProfileActivity;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaderBoardListAdapter extends RecyclerView.Adapter<LeaderBoardListAdapter.LeaderBoardViewHolder> {

    private Context mContext;
    private ArrayList<User> users;
    private static final int PReqCode = 2;
    private int rank = 0, usePostCount = 0;


    public LeaderBoardListAdapter(Context mContext, ArrayList<User> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @NonNull
    @Override
    public LeaderBoardListAdapter.LeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderBoardListAdapter.LeaderBoardViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_leader_board, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final LeaderBoardListAdapter.LeaderBoardViewHolder holder, final int position) {

        rank = position + 4;

        holder.userName.setText(users.get(position).getFirstName() + " " + users.get(position).getLastName());
        holder.rank.setText("" + rank);

        if (!users.get(position).getImageThumbnail().equals("none")) {
            Picasso.get().load(users.get(position).getImageThumbnail()).into(holder.user_image);
        } else {
            holder.user_image.setImageResource(R.drawable.user_low);
        }


        holder.coin.setText("" + users.get(position).getPoints());
        holder.userItem.setOnClickListener(view -> showDialog(users.get(position).getuID(), users.get(position).getFirstName() + " " + users.get(position).getLastName(), users.get(position).getAddress(), users.get(position).getImageThumbnail()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class LeaderBoardViewHolder extends RecyclerView.ViewHolder {

        TextView userName, coin, rank;
        CircleImageView user_image;
        RelativeLayout userItem;

        public LeaderBoardViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.profile_image_leader_board_item);
            userName = itemView.findViewById(R.id.name_leader_board_item);
            coin = itemView.findViewById(R.id.coin_leader_board_item);
            rank = itemView.findViewById(R.id.rank_leader_board_item);
            userItem = itemView.findViewById(R.id.user_item_leader_board);
        }
    }

    private void showDialog(String userId, String name, String address, String imageUrl) {
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.profile_dialog_layout);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView nameDialog = dialog.findViewById(R.id.name_dialog_leader_board);
        TextView addressDialog = dialog.findViewById(R.id.address_dialog_leader_board);
        TextView followersDialog = dialog.findViewById(R.id.friends_dialog_leader_board);
        TextView friendsDialog = dialog.findViewById(R.id.followers_dialog_leader_board);
        TextView postsDialog = dialog.findViewById(R.id.posts_dialog_leader_board);
        CircleImageView userImage = dialog.findViewById(R.id.profile_image_dialog_leader_board);
        nameDialog.setText(name);
        addressDialog.setText(address);
        ProfileActivity.getFriendFollowerCount(userId, friendsDialog, followersDialog);
        getPostCount(userId, postsDialog);
        if (!imageUrl.equals("none")) {
            Picasso.get().load(imageUrl).into(userImage);
        }
        dialog.show();
    }

    private void getPostCount(String userId, TextView postCount) {

        Query query = FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("authorID").equalTo(userId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    postCount.setText("" + dataSnapshot.getChildrenCount());
                } else {
                    postCount.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
