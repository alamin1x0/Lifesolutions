package com.lifesolutions.bd.kotlinCode.ui.adapter;

import android.annotation.SuppressLint;
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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lifesolutions.bd.Models.SearchUserModel;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewSearchAdapter extends RecyclerView.Adapter<NewSearchAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SearchUserModel> users;
    private String uID = FirebaseAuth.getInstance().getUid();
    private String userId;
    private RequestQueue requestQueue;
    private Boolean notify = false;
    private String requestType = "none";
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    private DatabaseReference fReqReference = FirebaseDatabase.getInstance().getReference().child("friendRequest");

    public NewSearchAdapter(Context mContext) {
        this.mContext = mContext;
        this.users = new ArrayList<>();
    }

    public void addAllUsers(ArrayList<SearchUserModel> newUsers) {
        int initSize = users.size();
        users.addAll(newUsers);
        notifyItemRangeChanged(initSize, newUsers.size());
    }

    public void removeAllItem() {
        users.clear();
    }


    @NonNull
    @Override
    public NewSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewSearchAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.new_search_list, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final NewSearchAdapter.ViewHolder holder, final int position) {

        requestQueue = Volley.newRequestQueue(mContext);

        holder.userName.setText(users.get(position).getName());

        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = users.get(position).getId();
                mContext.startActivity(new Intent(mContext, UserProfileActivityKt.class).putExtra("uID", userId));
            }
        });


//        holder.item.setOnClickListener(view -> {
//            mContext.startActivity(new Intent(mContext, UserProfileActivityKt.class).putExtra("uID", users.get(position).getId()));
//        });

    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName, address;
        CircleImageView user_image;
        RelativeLayout item;
        ImageView requestButton;

        public  ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.profile_image_user_find_friends);
            userName = itemView.findViewById(R.id.name_user_find_friends);
            address = itemView.findViewById(R.id.address_user_find_friends);
            item = itemView.findViewById(R.id.users_item_find_friends);
            requestButton = itemView.findViewById(R.id.request_button_find_friends_item);

        }
    }




}
