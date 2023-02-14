//package com.stardigiinternational.starnotee.Adapters;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Activities.MessageActivity;
//import com.stardigiinternational.starnotee.Models.User;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.kotlinCode.data.model.UserKt;
//
//import java.util.ArrayList;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class HorizontalUserListAdapter extends RecyclerView.Adapter<HorizontalUserListAdapter.ChatViewHolder>{
//
//    private Context mContext;
//    private ArrayList<UserKt> users;
//    private static final int PReqCode = 2 ;
//
//
//    public HorizontalUserListAdapter(Context mContext, ArrayList<UserKt> users) {
//        this.mContext = mContext;
//        this.users = users;
//    }
//
//    @NonNull
//    @Override
//    public HorizontalUserListAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new HorizontalUserListAdapter.ChatViewHolder(LayoutInflater.from(mContext).inflate(R.layout.horizontal_list_item,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final HorizontalUserListAdapter.ChatViewHolder holder, final int position) {
//
//        holder.userName.setText(users.get(position).getFirstName()+" "+users.get(position).getLastName());
//        if (users.get(position).getImageThumbnail().equals("none")){
//        } else {
//            Picasso.get().load(users.get(position).getImageThumbnail()).into(holder.user_image);
//        }
//
//        holder.item.setOnClickListener(view -> {
//            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE)
//                    != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, Manifest.permission.READ_PHONE_STATE)) {
//                    Toast.makeText(mContext,"Please go to app info and accept for required permission",Toast.LENGTH_SHORT).show();
//                } else {
//                    ActivityCompat.requestPermissions((Activity) mContext,
//                            new String[]{Manifest.permission.READ_PHONE_STATE}, PReqCode);
//                }
//
//            } else {
//                mContext.startActivity(new Intent(mContext, MessageActivity.class).putExtra("receiverID",users.get(position).getId()));
//            }
//        });
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return users.size();
//    }
//
//    public class ChatViewHolder extends RecyclerView.ViewHolder{
//
//        TextView userName;
//        CircleImageView user_image;
//        LinearLayout item;
//
//        public ChatViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            user_image = itemView.findViewById(R.id.profile_image_user_horizontal);
//            userName = itemView.findViewById(R.id.name_users_horizontal);
//            item = itemView.findViewById(R.id.users_item_horizontal);
//        }
//    }
//
//
//}
