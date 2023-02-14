package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Models.GroupParticipant;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupAddMemberAdapter extends RecyclerView.Adapter<GroupAddMemberAdapter.MemberViewHolder>{

    private Context mContext;
    private ArrayList<User> users;
    private String groupId;
    private DatabaseReference reference;

    public GroupAddMemberAdapter(Context mContext, ArrayList<User> users,String groupId) {
        this.mContext = mContext;
        this.users = users;
        this.groupId = groupId;
    }


    @NonNull
    @Override
    public GroupAddMemberAdapter.MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupAddMemberAdapter.MemberViewHolder(LayoutInflater.from(mContext).inflate(R.layout.user_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupAddMemberAdapter.MemberViewHolder holder, final int position) {

        reference = FirebaseDatabase.getInstance().getReference("ChatGroups").child(groupId).child("Participants");

        holder.userName.setText(users.get(position).getFirstName()+" "+users.get(position).getLastName());
        if (!users.get(position).getImageThumbnail().equals("none")){
            Picasso.get().load(users.get(position).getImageThumbnail()).into(holder.user_image);
        }

        holder.onlineStatus.setVisibility(View.GONE);

        holder.item.setOnClickListener(view -> {
            GroupParticipant participant = new GroupParticipant(users.get(position).getuID(),"member",System.currentTimeMillis());
            reference.child(users.get(position).getuID()).setValue(participant).addOnCompleteListener(task -> Toast.makeText(mContext, "Member added successfully", Toast.LENGTH_SHORT).show());
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class MemberViewHolder extends RecyclerView.ViewHolder{

        TextView userName,address;
        CircleImageView user_image,onlineStatus;
        RelativeLayout item;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.profile_image_user);
            userName = itemView.findViewById(R.id.name_users_item);
            address = itemView.findViewById(R.id.address_users_item);
            item = itemView.findViewById(R.id.users_item);
            onlineStatus = itemView.findViewById(R.id.online_status_user_layout);
        }
    }
}
