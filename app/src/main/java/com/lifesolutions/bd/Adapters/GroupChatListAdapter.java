//package com.stardigiinternational.starnotee.Adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Activities.GroupChatActivity;
//import com.stardigiinternational.starnotee.Models.GroupChat;
//import com.stardigiinternational.starnotee.Models.Message;
//import com.stardigiinternational.starnotee.Models.User;
//import com.stardigiinternational.starnotee.R;
//import java.util.ArrayList;
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class GroupChatListAdapter extends RecyclerView.Adapter<GroupChatListAdapter.GChatListViewHolder>{
//
//    private Context mContext;
//    private ArrayList<GroupChat> groupChats;
//    private String lastMessage,messageType;
//    private Boolean isSeen = false;
//
//    public GroupChatListAdapter(Context mContext, ArrayList<GroupChat> groupChats) {
//        this.mContext = mContext;
//        this.groupChats = groupChats;
//    }
//
//
//    @NonNull
//    @Override
//    public GroupChatListAdapter.GChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new GroupChatListAdapter.GChatListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.user_layout,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final GroupChatListAdapter.GChatListViewHolder holder, final int position) {
//
//        holder.userName.setText(groupChats.get(position).getGroupTitle());
//        holder.onlineStatus.setVisibility(View.GONE);
//        if (groupChats.get(position).getGroupIcon().equals("none")){
//            holder.user_image.setImageResource(R.drawable.user_low);
//        } else {
//            Picasso.get().load(groupChats.get(position).getGroupIcon()).into(holder.user_image);
//        }
//
//        holder.address.setText(groupChats.get(position).getGroupDescription());
//
//        holder.item.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, GroupChatActivity.class).putExtra("groupId",groupChats.get(position).getGroupId())));
//        lastMessage(groupChats.get(position).getGroupId(),holder.address);
//    }
//
//    @Override
//    public int getItemCount() {
//        return groupChats.size();
//    }
//
//    public class GChatListViewHolder extends RecyclerView.ViewHolder{
//
//        TextView userName,address;
//        CircleImageView user_image,onlineStatus;
//        RelativeLayout item;
//
//        public GChatListViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            user_image = itemView.findViewById(R.id.profile_image_user);
//            userName = itemView.findViewById(R.id.name_users_item);
//            address = itemView.findViewById(R.id.address_users_item);
//            item = itemView.findViewById(R.id.users_item);
//            onlineStatus = itemView.findViewById(R.id.online_status_user_layout);
//        }
//    }
//
//    private void lastMessage(String groupId,TextView last_msg){
//        lastMessage = "default";
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//
//
//        reference.child("GroupMessages").child(groupId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Message message = snapshot.getValue(Message.class);
//                    lastMessage = message.getMessage();
//                    messageType = message.getType();
//                    isSeen = message.isSeen();
//                }
//
//                if ("default".equals(lastMessage)) {
//
//                    last_msg.setText("No messages");
//
//                } else {
//                    if (messageType.equals("image")) {
//
//                        last_msg.setText("Image");
//                        if (isSeen){
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }else {
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }
//
//                    } else if (messageType. equals("pdf")){
//
//                        last_msg.setText("PDF File");
//                        if (isSeen){
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }else {
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }
//
//                    }else if (messageType.equals("docx")){
//
//                        last_msg.setText("DOCX File");
//                        if (isSeen){
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }else {
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }
//
//                    }else if (messageType.equals("voice")){
//
//                        last_msg.setText("Voice Message");
//                        if (isSeen){
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }else {
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }
//
//                    }else {
//
//                        last_msg.setText(lastMessage);
//                        if (isSeen){
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }else {
//                            last_msg.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
//                        }
//                    }
//                }
//
//                lastMessage = "default";
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
