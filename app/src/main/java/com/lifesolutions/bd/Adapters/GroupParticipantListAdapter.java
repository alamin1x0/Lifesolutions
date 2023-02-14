package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupParticipantListAdapter extends RecyclerView.Adapter<GroupParticipantListAdapter.ParticipantViewHolder> {
    private Context mContext;
    private ArrayList<User> users;
    private DatabaseReference mReference;
    private String groupId,uID;
    private FirebaseAuth mAuth;
    private Boolean admin = false;

    public GroupParticipantListAdapter(Context mContext, ArrayList<User> users,String groupId) {
        this.mContext = mContext;
        this.users = users;
        this.groupId = groupId;
    }

    public void setAdmin (Boolean admin){
        this.admin = admin;
    }

    @NonNull
    @Override
    public GroupParticipantListAdapter.ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupParticipantListAdapter.ParticipantViewHolder(LayoutInflater.from(mContext).inflate(R.layout.user_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupParticipantListAdapter.ParticipantViewHolder holder, final int position) {

        mReference = FirebaseDatabase.getInstance().getReference("ChatGroups").child(groupId).child("Participants");
        mAuth = FirebaseAuth.getInstance();
        uID = mAuth.getUid();

        holder.userName.setText(users.get(position).getFirstName()+" "+users.get(position).getLastName());
        if (!users.get(position).getImageThumbnail().equals("none")){
            Picasso.get().load(users.get(position).getImageThumbnail()).into(holder.user_image);
        } else {
            holder.user_image.setImageResource(R.drawable.user_low);
        }


        holder.onlineStatus.setVisibility(View.GONE);

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (admin && !uID.equals(users.get(position).getuID())){
                    PopupMenu popupMenu = new PopupMenu(mContext,holder.userName);
                    popupMenu.inflate(R.menu.menu_remove);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        if (item.getItemId() == R.id.remove_menu_remove) {
                            mReference.child(users.get(position).getuID()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(mContext, "Member removed successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return false;
                    });

                    popupMenu.show();
                }
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ParticipantViewHolder extends RecyclerView.ViewHolder{

        TextView userName,address;
        CircleImageView user_image,onlineStatus;
        RelativeLayout item;

        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.profile_image_user);
            userName = itemView.findViewById(R.id.name_users_item);
            address = itemView.findViewById(R.id.address_users_item);
            item = itemView.findViewById(R.id.users_item);
            onlineStatus = itemView.findViewById(R.id.online_status_user_layout);
        }
    }
}
