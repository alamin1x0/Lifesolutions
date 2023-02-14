//package com.stardigiinternational.starnotee.Adapters;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.sinch.android.rtc.SinchError;
//import com.sinch.android.rtc.calling.Call;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Activities.CallScreenActivity;
//import com.stardigiinternational.starnotee.Activities.MessageActivity;
//import com.stardigiinternational.starnotee.Activities.VideoCallScreenActivity;
//import com.stardigiinternational.starnotee.Models.Message;
//import com.stardigiinternational.starnotee.Models.User;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.Services.SinchService;
//import com.stardigiinternational.starnotee.kotlinCode.data.model.UserKt;
//import com.stardigiinternational.starnotee.kotlinCode.utils.Utils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//import static com.stardigiinternational.starnotee.Activities.BaseActivity.getSinchServiceInterface;
//
//public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> implements SinchService.StartFailedListener{
//
//    private Context mContext;
//    private ArrayList<UserKt> users;
//    private String lastMessage,messageType,uID;
//    private Boolean isSeen = false;
//    private static final int PReqCode = 2 ;
//    private String[] PERMISSIONS_VIDEO_CALL = {
//            android.Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.CAMERA
//    };
//    private String[] PERMISSIONS = {
//            android.Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.RECORD_AUDIO
//    };
//    FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//
//    public ChatListAdapter(Context mContext, ArrayList<UserKt> users) {
//        this.mContext = mContext;
//        this.users = users;
//    }
//
//
//    @NonNull
//    @Override
//    public ChatListAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ChatListAdapter.ChatViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chat_item_layout,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final ChatListAdapter.ChatViewHolder holder, final int position) {
//
//        uID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
//
//        holder.userName.setText(users.get(position).getFirstName()+" "+users.get(position).getLastName());
//
//        lastMessage(users.get(position).getUID(),uID,holder.address);
//
//        if (users.get(position).getImageThumbnail().equals("none")){
//        } else {
//            Picasso.get().load(users.get(position).getImageThumbnail()).into(holder.user_image);
//        }
//
//        holder.item.setOnClickListener(view -> {
//            mContext.startActivity(new Intent(mContext, MessageActivity.class).putExtra("receiverID",users.get(position).getUID()));
//
//        });
//
//        holder.btnAudioCall.setOnClickListener(view -> {
//            if (!Utils.hasPermissions(mContext, PERMISSIONS)) {
//                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS_VIDEO_CALL, PReqCode);
//            } else {
//                if (getSinchServiceInterface().isStarted()){
//                    FirebaseDatabase.getInstance().getReference().child("Users").child(users.get(position).getUID()).child("userActivity").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.exists()){
//                                if (dataSnapshot.child("online").getValue(Boolean.class)){
//                                    Call call = getSinchServiceInterface().callUserAudio(users.get(position).getUID());
//                                    String callId = call.getCallId();
//
//                                    Intent callScreen = new Intent(mContext, CallScreenActivity.class);
//                                    callScreen.putExtra(SinchService.CALL_ID, callId);
//                                    mContext.startActivity(callScreen);
//                                }
//                            }else {
//                                Toast.makeText(mContext, "This user is offline", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                            Toast.makeText(mContext, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    Toast.makeText(mContext, "Just a moment", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        holder.btnVideoCall.setOnClickListener(view -> {
//            if (!Utils.hasPermissions(mContext, PERMISSIONS_VIDEO_CALL)) {
//                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS_VIDEO_CALL, PReqCode);
//            } else {
//                if (getSinchServiceInterface().isStarted()){
//
//                    FirebaseDatabase.getInstance().getReference().child("Users").child(users.get(position).getUID()).child("userActivity").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.exists()){
//                                if (dataSnapshot.child("online").getValue(Boolean.class)){
//                                    Call call = getSinchServiceInterface().callUserVideo(users.get(position).getUID());
//                                    String callId = call.getCallId();
//
//                                    Intent callScreen = new Intent(mContext, VideoCallScreenActivity.class);
//                                    callScreen.putExtra(SinchService.CALL_ID, callId);
//                                    mContext.startActivity(callScreen);
//                                }
//                            }else {
//                                Toast.makeText(mContext, "This user is offline", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                            Toast.makeText(mContext, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } else {
//                    Toast.makeText(mContext, "Just a moment", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        // getUserOnlineStatus(users.get(position).getUID(),holder.onlineStatus);
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
//        TextView userName,address;
//        CircleImageView user_image,onlineStatus;
//        RelativeLayout item;
//        ImageButton btnVideoCall,btnAudioCall;
//
//        public ChatViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            user_image = itemView.findViewById(R.id.profile_image_user_chat_list);
//            userName = itemView.findViewById(R.id.name_user_chat_list);
//            address = itemView.findViewById(R.id.address_user_chat_list);
//            item = itemView.findViewById(R.id.users_item_chat_list);
//            onlineStatus = itemView.findViewById(R.id.online_status_user_chat_list);
//            btnVideoCall = itemView.findViewById(R.id.video_call_user_chat_list);
//            btnAudioCall = itemView.findViewById(R.id.audio_call_user_chat_list);
//
//        }
//    }
//
//
//    private void lastMessage(String userId,String uID,TextView last_msg){
//        lastMessage = "default";
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//
//
//        reference.child("Messages").child(uID).child(userId).addValueEventListener(new ValueEventListener() {
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
//
//    private void getUserOnlineStatus(String userID,CircleImageView imageView){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(userID);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child("userStat").exists()){
//
//                    if (dataSnapshot.child("userStat").child("online").getValue(Boolean.class)){
//
//                        imageView.setVisibility(View.VISIBLE);
//
//                    } else {
//
//                        imageView.setVisibility(View.GONE);
//                    }
//                }else {
//                    imageView.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    };
//
//    @Override
//    public void onStartFailed(SinchError error) {
//        Toast.makeText(mContext, ""+error.getMessage(), Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onStarted() {
//
//    }
//
//}
//
