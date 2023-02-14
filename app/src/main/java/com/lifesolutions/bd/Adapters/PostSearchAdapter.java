package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Activities.FullPostActivity;
import com.lifesolutions.bd.Activities.ProfileActivity;
import com.lifesolutions.bd.Activities.UserProfileActivity;
import com.lifesolutions.bd.Helpers.TimeAgo;
import com.lifesolutions.bd.Models.NotificationInApp;
import com.lifesolutions.bd.Models.PostItem;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostSearchAdapter extends RecyclerView.Adapter<PostSearchAdapter.PostViewHolder>{
    private Context mContext;
    private ArrayList<PostItem> postItems;
    private String imageLink;
    private DatabaseReference databaseReference;
    private String uID;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public PostSearchAdapter(Context mContext, ArrayList<PostItem> postItems) {
        this.mContext = mContext;
        this.postItems = postItems;
    }

    @NonNull
    @Override
    public PostSearchAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostSearchAdapter.PostViewHolder(LayoutInflater.from(mContext).inflate(R.layout.post_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PostSearchAdapter.PostViewHolder holder, final int position) {

        uID = mAuth.getUid();

        if (postItems.get(position).getPostDescription().equals("  ")){
            holder.title.setVisibility(View.GONE);
            holder.backgroundLayout.setVisibility(View.GONE);
        } else {
            if (postItems.get(position).getPostDescription().length() < 150 && postItems.get(position).getPostThumbnail().equals("none")){
                holder.title.setVisibility(View.GONE);
                holder.post_image.setVisibility(View.GONE);
                holder.backgroundLayout.setVisibility(View.VISIBLE);
                holder.textBackground.setText(postItems.get(position).getPostDescription());
            } else {
                holder.backgroundLayout.setVisibility(View.GONE);
                holder.title.setVisibility(View.VISIBLE);
                holder.title.setText(postItems.get(position).getPostDescription());
            }
        }

        holder.date.setText(getDateTime(postItems.get(position).getDate()));
        getAuthorInfo(postItems.get(position).getAuthorID(),holder.userName,holder.user_image);
        getLikesCount(postItems.get(position).getPostID(),holder.like,holder.likeLayout);
        isLikes(holder.tvLikeStatus,postItems.get(position).getPostID(),holder.likeStatus,uID);
        getCommentsCount(postItems.get(position).getPostID(),holder.comment);

        if (!postItems.get(position).getPostThumbnail().equals("none")){
            holder.post_image.setVisibility(View.VISIBLE);
            Picasso.get().load(postItems.get(position).getPostThumbnail()).into(holder.post_image);
        } else {
            holder.post_image.setVisibility(View.GONE);
        }

        holder.item.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, FullPostActivity.class).putExtra("Id",postItems.get(position).getPostID())));

        holder.btnLike.setOnClickListener(view -> {
            if (holder.tvLikeStatus.getText().equals("Star")){
                FirebaseDatabase.getInstance().getReference().child("Likes").child(postItems.get(position).getPostID()).child(uID).setValue(true);
                sendNotificationToAuthor(postItems.get(position).getPostID(),postItems.get(position).getAuthorID(),uID,"like","Liked your post");
            }
            else {
                FirebaseDatabase.getInstance().getReference().child("Likes").child(postItems.get(position).getPostID()).child(uID).removeValue();
            }
        });

        holder.btnComment.setOnClickListener(view -> mContext.startActivity(new Intent(mContext, FullPostActivity.class).putExtra("Id",postItems.get(position).getPostID())));

        holder.userName.setOnClickListener(view -> {
            if (postItems.get(position).getAuthorID().equals(uID)){
                mContext.startActivity(new Intent(mContext, ProfileActivity.class));
            }else {
                mContext.startActivity(new Intent(mContext, UserProfileActivity.class).putExtra("uID",postItems.get(position).getAuthorID()));
            }
        });

        holder.user_image.setOnClickListener(view -> {
            if (postItems.get(position).getAuthorID().equals(uID)){
                mContext.startActivity(new Intent(mContext, ProfileActivity.class));
            }else {
                mContext.startActivity(new Intent(mContext, UserProfileActivity.class).putExtra("uID",postItems.get(position).getAuthorID()));
            }
        });

        holder.btnShare.setOnClickListener(view -> {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, mContext.getResources().getString(R.string.app_name));
                String shareHint = postItems.get(position).getPostDescription()+"\n for more info download :  "+mContext.getResources().getString(R.string.app_name)+" Application\n\n"+"https://play.google.com/store/apps/details?id=" + mContext.getPackageName();
                i.putExtra(Intent.EXTRA_TEXT, shareHint);
                mContext.startActivity(Intent.createChooser(i, "Choose one"));
            } catch(Exception e) {
                //e.toString();
                Toast.makeText(mContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView userName,date,title,like,comment,tvLikeStatus,textBackground;
        CircleImageView user_image;
        ImageView post_image,likeStatus;
        RelativeLayout btnLike,btnComment,btnShare;
        LinearLayout item,likeLayout,backgroundLayout;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.user_image_post_item);
            date = itemView.findViewById(R.id.date_post_item);
            title = itemView.findViewById(R.id.post_title_post_item);
            userName = itemView.findViewById(R.id.user_name_post_item);
            post_image = itemView.findViewById(R.id.post_image_post_item);
            like = itemView.findViewById(R.id.tv_like_count_post_item);
            comment = itemView.findViewById(R.id.tv_comment_count_post_item);
            btnLike = itemView.findViewById(R.id.like_button_post_item);
            btnComment = itemView.findViewById(R.id.comment_button_post_item);
            btnShare = itemView.findViewById(R.id.share_button_post_item);
            item = itemView.findViewById(R.id.post_item);
            likeStatus = itemView.findViewById(R.id.like_pic_post_item);
            tvLikeStatus = itemView.findViewById(R.id.like_text_post_item);
            likeLayout = itemView.findViewById(R.id.like_count_layout_post_item);
            backgroundLayout = itemView.findViewById(R.id.background_layout_post_item);
            textBackground = itemView.findViewById(R.id.text_background_post_item);
        }
    }

    private void getAuthorInfo(final String uID, final TextView userName, final CircleImageView user_image){

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                imageLink = "none";
                if (dataSnapshot.child(uID).child("imageThumbnail").exists()){
                    imageLink = dataSnapshot.child(uID).child("imageThumbnail").getValue(String.class);
                }
                String firstName,lastName;
                firstName = dataSnapshot.child(uID).child("firstName").getValue(String.class);
                lastName = dataSnapshot.child(uID).child("lastName").getValue(String.class);
                userName.setText(firstName+" "+lastName);
                if (!imageLink.equals("none")){
                    Picasso.get().load(imageLink).into(user_image);
                } else {
                    user_image.setImageResource(R.drawable.user_low);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLikesCount(String postId,final TextView likes,LinearLayout likeLayout){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount()<1){
                    likeLayout.setVisibility(View.GONE);
                } else {
                    likeLayout.setVisibility(View.VISIBLE);
                    likes.setText(""+dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getCommentsCount(String postID, final TextView comments){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Comments").child(postID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount()>1){
                    comments.setVisibility(View.VISIBLE);
                    comments.setText(dataSnapshot.getChildrenCount()+" Comments");
                } else {
                    comments.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String getDateTime(long timeInMilli){
        long cDate = System.currentTimeMillis();
        String date = TimeAgo.toDuration(cDate-timeInMilli);
        return date;
    }

    private void isLikes(final TextView textView, String postId, final ImageView imageView, final String userID){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userID).exists()){
                    imageView.setImageResource(R.drawable.ic_star_full_color);
                    textView.setText("Star ");
                    textView.setTextColor(mContext.getResources().getColor(R.color.colorMain));
                } else {
                    imageView.setImageResource(R.drawable.ic_star_outline);
                    textView.setText("Star");
                    textView.setTextColor(mContext.getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void sendNotificationToAuthor(String postId, String authorId,String userId,String type,String message){

        DatabaseReference notificationReference = FirebaseDatabase.getInstance().getReference("Notifications");
        DatabaseReference pushRef = notificationReference.child(authorId).push();
        pushRef.push();
        String notificationId = pushRef.getKey();
        long time = System.currentTimeMillis();

        NotificationInApp notification = new NotificationInApp(postId,type,userId,authorId,message,notificationId,time,false);
        pushRef.setValue(notification);
    }
}
