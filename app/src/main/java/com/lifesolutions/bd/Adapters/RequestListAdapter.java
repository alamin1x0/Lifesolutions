package com.lifesolutions.bd.Adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Models.UserId;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;


public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.RequestViewHolder>{

    private String TAG = "RequestListAdapter";

    private Context mContext;
    private ArrayList<UserId> users;
    private String firstName,lastName,address,imageUrl;
    private Boolean request;

    public RequestListAdapter(Context mContext, ArrayList<UserId> users,Boolean request) {
        this.mContext = mContext;
        this.users = users;
        this.request = request;
    }


    @NonNull
    @Override
    public RequestListAdapter.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestListAdapter.RequestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RequestListAdapter.RequestViewHolder holder, final int position) {

        getUserInfo(holder.userName,holder.address,holder.user_image,users.get(position).getUid(),holder.item);

        holder.item.setOnClickListener(view -> {
            mContext.startActivity(new Intent(mContext, UserProfileActivityKt.class).putExtra("uID", users.get(position).getUid()));
        });

        holder.onlineStatus.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + users.size());
        return users.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder{

        TextView userName,address;
        CircleImageView user_image,onlineStatus;
        RelativeLayout item;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.profile_image_user);
            userName = itemView.findViewById(R.id.name_users_item);
            address = itemView.findViewById(R.id.address_users_item);
            item = itemView.findViewById(R.id.users_item);
            onlineStatus = itemView.findViewById(R.id.online_status_user_layout);
        }
    }

    private void getUserInfo(TextView name,TextView tvAddress, CircleImageView image,String uid,RelativeLayout relativeLayout){
        try {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child("firstName").exists() && dataSnapshot.child("lastName").exists() && dataSnapshot.child("address").exists()){

                        firstName = dataSnapshot.child("firstName").getValue(String.class);
                        lastName = dataSnapshot.child("lastName").getValue(String.class);
                        address = dataSnapshot.child("address").getValue(String.class);
                        imageUrl = dataSnapshot.child("imageThumbnail").getValue(String.class);

                        name.setText(firstName+" "+lastName);
                        tvAddress.setText(address);
                        if (!imageUrl.equals("none")){
                            Picasso.get().load(imageUrl).into(image);
                        }
                    } else {
                        relativeLayout.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e){

        }

    }
}
