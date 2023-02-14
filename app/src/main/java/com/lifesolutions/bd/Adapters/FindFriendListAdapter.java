package com.lifesolutions.bd.Adapters;

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
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FindFriendListAdapter extends RecyclerView.Adapter<FindFriendListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<User> users;
    private String uID = FirebaseAuth.getInstance().getUid();
    private String userId;
    private RequestQueue requestQueue;
    private Boolean notify = false;
    private String requestType = "none";
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    private DatabaseReference fReqReference = FirebaseDatabase.getInstance().getReference().child("friendRequest");

    public FindFriendListAdapter(Context mContext) {
        this.mContext = mContext;
        this.users = new ArrayList<>();
    }

    public void addAllUsers(ArrayList<User> newUsers) {
        int initSize = users.size();
        users.addAll(newUsers);
        notifyItemRangeChanged(initSize, newUsers.size());
    }

    public void removeAllItem() {
        users.clear();
    }


    @NonNull
    @Override
    public FindFriendListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FindFriendListAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.find_friends_item_layout, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final FindFriendListAdapter.ViewHolder holder, final int position) {

        requestQueue = Volley.newRequestQueue(mContext);

        holder.userName.setText(users.get(position).getFirstName() + " " + users.get(position).getLastName());
        holder.address.setText(users.get(position).getAddress());
        if (users.get(position).getImageThumbnail().equals("none")) {
            holder.user_image.setImageResource(R.drawable.user_low);
        } else {
            Picasso.get().load(users.get(position).getImageThumbnail()).into(holder.user_image);
        }

        holder.requestButton.setVisibility(View.GONE);

       /* updateRequestButton(holder.requestButton,users.get(position).getuID());

        holder.requestButton.setOnClickListener(view -> sendRequest(users.get(position).getuID()));*/

        holder.item.setOnClickListener(view -> {
            mContext.startActivity(new Intent(mContext, UserProfileActivityKt.class).putExtra("uID", users.get(position).getuID()));
        });

    }

    /* private void sendRequest(String userId) {
         switch (requestType) {
             case "friends":
                 Toast.makeText(mContext, "Your friends", Toast.LENGTH_SHORT).show();
                 break;
             case "requested":
                 fReqReference.child(userId).child(uID).removeValue().addOnSuccessListener(aVoid -> Toast.makeText(mContext, "Friend Request cancelled successfully", Toast.LENGTH_SHORT).show());
                 requestType ="notRequested";
                 break;
             case "notRequested":
                 notify = true;
                 fReqReference.child(userId).child(uID).child("type").setValue("request").addOnSuccessListener(aVoid -> {
                     fReqReference.child(userId).child(uID).child("uid").setValue(uID).addOnSuccessListener(aVoid1 -> {

                         PostListAdapter.sendNotificationToAuthor("", userId, uID, "request", "Sent you a friend request");
                         Toast.makeText(mContext, "Friend Request Sent", Toast.LENGTH_SHORT).show();
                         requestType ="requested";
                         reference.child(uID).addValueEventListener(new ValueEventListener() {
                             @Override
                             public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                 User user = dataSnapshot1.getValue(User.class);
                                 if (notify) {
                                     sendNotification(userId, user.getFirstName() + " " + user.getLastName(), "Sent you a friend request");
                                 }
                                 notify = false;
                             }

                             @Override
                             public void onCancelled(@NonNull DatabaseError databaseError) {

                             }
                         });
                     }).addOnFailureListener(e -> Toast.makeText(mContext, "Failed to send friend request", Toast.LENGTH_SHORT).show());
                 }).addOnFailureListener(e -> Toast.makeText(mContext, "Failed to send friend request", Toast.LENGTH_SHORT).show());
                 break;

             default:
                 Toast.makeText(mContext, "Please wait checking user data", Toast.LENGTH_SHORT).show();
                 break;
         }
     }

     private void updateRequestButton(ImageView requestButton, String userId) {
         fReqReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 if (dataSnapshot.child(userId).child(uID).exists()) {
                     if (dataSnapshot.child(userId).child(uID).child("type").getValue(String.class).equals("request")) {
                         requestButton.setEnabled(true);
                         requestButton.setImageResource(R.drawable.ic_friend_request_sent_done);
                         requestType = "requested";
                     } else {
                         requestButton.setEnabled(false);
                         requestButton.setImageResource(R.drawable.ic_group_black_24dp);
                         requestType = "friends";
                     }

                 } else {
                     requestButton.setEnabled(true);
                     requestButton.setImageResource(R.drawable.ic_friend_request_sent_white);
                     requestType = "notRequested";
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
     }
 */
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


   /* private void sendNotification(String receiverID, String firstName, String message) {
        DatabaseReference allTokens = FirebaseDatabase.getInstance().getReference("Tokens");
        Query query = allTokens.orderByKey().equalTo(receiverID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Token token = dataSnapshot1.getValue(Token.class);
                    Data data = new Data(uID,firstName+" "+message,"Friend Request",receiverID,"request",uID,R.drawable.app_icon);
                    Sender sender = new Sender(data,token.getToken());

                    try {
                        JSONObject senderJsonObject = new JSONObject(new Gson().toJson(sender));
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://fcm.googleapis.com/fcm/send", senderJsonObject,
                                response -> Log.d("JSON_RESPONSE","onResponse"+response.toString()), error -> Log.d("JSON_RESPONSE","onResponse"+error.toString())){
                            @Override
                            public Map<String, String> getHeaders() {
                                Map<String,String> headers = new HashMap<>();
                                headers.put("Content-Type","application/json");
                                headers.put("Authorization","key=AAAAH3KuD3I:APA91bEn9xaE0KLfvVsLHxudo_SR3lRsDI4K15Mu-I0BWxQNTPvHzTG4kKofAm979uXiwvZL6UtL5zIwcErSxOTKS3QDZ5UmBWYS4W5SK0iDLLimdvqQ8bgneAkTr1jMWAVBM0qhscc6");

                                return headers;
                            }
                        };

                        requestQueue.add(jsonObjectRequest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

}
