package com.lifesolutions.bd.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Activities.FullPostActivity;
import com.lifesolutions.bd.Helpers.TimeAgo;
import com.lifesolutions.bd.Models.NotificationInApp;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.data.model.FriendData;
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt;
import com.lifesolutions.bd.kotlinCode.utils.NotificationUtils;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder> {
    private final Context mContext;
    private final ArrayList<NotificationInApp> notifications;
    private String uID;

    public NotificationListAdapter(Context mContext, ArrayList<NotificationInApp> notifications) {
        this.mContext = mContext;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.notification_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationListAdapter.MyViewHolder myViewHolder, int i) {

        final int position;
        position = i;

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("com.starnote.CurrentAuthUser", MODE_PRIVATE);
        uID = sharedPreferences.getString("uID", null);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications").child(uID);

        if (!notifications.get(position).isSeen()) {
            myViewHolder.item.setAlpha((float) 1);
            myViewHolder.item.setBackgroundResource(R.drawable.comment_layout_background_new);
        } else {
            myViewHolder.item.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            myViewHolder.item.setAlpha((float) 0.5);
        }

        if (notifications.get(position).getType().equals("friendRequest")) {
            myViewHolder.requestAction.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.requestAction.setVisibility(View.GONE);
        }

        myViewHolder.btnAccept.setOnClickListener(v ->
                acceptFriendRequest(notifications.get(position).getSender(), uID, notifications.get(position).getNotificationId()));

        myViewHolder.btnDelete.setOnClickListener(v -> {
            removeFriendRequest(uID, notifications.get(position).getSender());
            deleteItem(notifications.get(position).getNotificationId());
        });


        getUserInfo(myViewHolder.image, myViewHolder.name, notifications.get(i).getSender());
        myViewHolder.comment.setText(notifications.get(position).getNotification());
        myViewHolder.dateTime.setText(TimeAgo.toDuration(System.currentTimeMillis() - notifications.get(position).getTime()));
        myViewHolder.item.setOnClickListener(view -> {
            if (notifications.get(position).getType().equals("like") || notifications.get(position).getType().equals("comment")) {
                mContext.startActivity(new Intent(mContext, FullPostActivity.class).putExtra("Id", notifications.get(position).getPostId()));
                reference.child(notifications.get(position).getNotificationId()).child("seen").setValue(true);
            } else if (notifications.get(position).getType().equals("request")) {
                reference.child(notifications.get(position).getNotificationId()).child("seen").setValue(true);
                mContext.startActivity(new Intent(mContext, UserProfileActivityKt.class).putExtra("uID", notifications.get(position).getSender()));
            } else if (notifications.get(position).getType().equals("friendRequest")) {
                reference.child(notifications.get(position).getNotificationId()).child("seen").setValue(true);
                mContext.startActivity(new Intent(mContext, UserProfileActivityKt.class).putExtra("uID", notifications.get(position).getSender()));
            }
        });

        myViewHolder.more.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(mContext, myViewHolder.more);
            popupMenu.inflate(R.menu.menu_delete);
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.delete_menu_delete) {
                    reference.child(notifications.get(position).getNotificationId()).removeValue().addOnSuccessListener(aVoid -> Toast.makeText(mContext, "Notification Successfully deleted", Toast.LENGTH_SHORT).show());
                }
                return false;
            });
            popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView comment;
        private final TextView dateTime;
        private final CircleImageView image;
        private final RelativeLayout item;
        ImageView more;
        LinearLayout requestAction;
        MaterialButton btnAccept, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_notification);
            comment = itemView.findViewById(R.id.message_notification);
            image = itemView.findViewById(R.id.image_notification);
            dateTime = itemView.findViewById(R.id.notification_date_time);
            item = itemView.findViewById(R.id.item_notification);
            more = itemView.findViewById(R.id.more_button_notification);
            requestAction = itemView.findViewById(R.id.request_accept_action);
            btnAccept = itemView.findViewById(R.id.btn_accept_notification_item);
            btnDelete = itemView.findViewById(R.id.btn_delete_notification_item);

        }
    }

    private void getUserInfo(final ImageView imageView, final TextView userName, String userId) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                userName.setText(Objects.requireNonNull(user).getFirstName() + " " + user.getLastName());
                Picasso.get().load(user.getImageThumbnail()).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void deleteItem(String notificationId) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications").child(uID);
        reference.child(notificationId).removeValue();
    }

    private void removeFriendRequest(String uID, String senderID) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FriendsRequest");
        databaseReference.child(uID).child(senderID).removeValue();
        databaseReference.child(senderID).child(uID).removeValue();
        Toast.makeText(mContext, "Request has been deleted", Toast.LENGTH_SHORT).show();
    }

    private void acceptFriendRequest(String senderId, String receiverID, String notificationId) {
        // Auth Data
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference friendReference = FirebaseDatabase.getInstance().getReference("FriendsList");
        databaseReference.child(senderId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    FriendData friendDataOne = new FriendData(senderId, Objects.requireNonNull(user).getFirstName(), user.getLastName(), "friend", user.getImageThumbnail());

                    databaseReference.child(receiverID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                User userTwo = dataSnapshot.getValue(User.class);
                                FriendData friendDataTwo = new FriendData(receiverID, Objects.requireNonNull(userTwo).getFirstName(), userTwo.getLastName(), "friend", userTwo.getImageThumbnail());

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FriendsRequest");
                                databaseReference.child(receiverID).child(senderId).removeValue();
                                databaseReference.child(senderId).child(receiverID).removeValue();

                                friendReference.child(senderId).child(receiverID).setValue(friendDataTwo).addOnSuccessListener(aVoid ->
                                        NotificationUtils.sendRequestAcceptNotification(receiverID, senderId, Objects.requireNonNull(friendDataTwo.getFirstName()), "Your friend request has been accepted"));
                                friendReference.child(receiverID).child(senderId).setValue(friendDataOne);
                                deleteItem(notificationId);
                                Toast.makeText(mContext, "Request has been accepted", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(mContext, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mContext, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
