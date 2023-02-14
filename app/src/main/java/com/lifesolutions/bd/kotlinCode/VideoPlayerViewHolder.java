package com.lifesolutions.bd.kotlinCode;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.data.model.VideoPost;
import com.lifesolutions.bd.kotlinCode.ui.activities.OwnProfileActivity;
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt;
import com.lifesolutions.bd.kotlinCode.utils.Utils;


public class VideoPlayerViewHolder extends RecyclerView.ViewHolder {

    FrameLayout media_container;
    TextView title, authorName,postTime, likeCount, commentCount;
    ImageView thumbnail, volumeControl, authorImage;
    ProgressBar progressBar;
    View parent;
    RequestManager requestManager;
    ImageButton btnMore;
    LikeButton likeButton;
    LinearLayout shareLayout, commentLayout;

    Context appContext;

    public VideoPlayerViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        parent = itemView;
        appContext = context;
        media_container = itemView.findViewById(R.id.media_container);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        // title = itemView.findViewById(R.id.title);
        progressBar = itemView.findViewById(R.id.progressBar);
        volumeControl = itemView.findViewById(R.id.volume_control);

        // Extra Data
        authorImage = itemView.findViewById(R.id.iv_author_with_video_feed);
        authorName = itemView.findViewById(R.id.tv_author_name_with_video_feed);
        postTime = itemView.findViewById(R.id.tv_date_video_feed);
        btnMore = itemView.findViewById(R.id.btn_more_video_feed);

        // Bottom Extra
        likeButton = itemView.findViewById(R.id.like_btn_border_video_feed);
        likeCount = itemView.findViewById(R.id.tv_like_count_post_video_feed);
        shareLayout = itemView.findViewById(R.id.share_layout_video_feed);
        commentLayout = itemView.findViewById(R.id.layout_btn_comment_video_feed);
        commentCount = itemView.findViewById(R.id.tv_comment_count_video_feed);
    }

    @SuppressLint("SetTextI18n")
    public void onBind(VideoPost mediaObject, RequestManager requestManager) {
        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        this.requestManager = requestManager;
        parent.setTag(this);
        // title.setText(mediaObject.getPostDescription());
        authorName.setText(mediaObject.getAuthorFirstName() + mediaObject.getAuthorLastName());
        postTime.setText(Utils.INSTANCE.getDateTime(mediaObject.getDate()));
        if (mediaObject.getAuthorImage() != "none" && mediaObject.getAuthorImage() != null) {
            Picasso.get().load(mediaObject.getAuthorImage()).into(authorImage);
        } else {
            Picasso.get().load(R.drawable.user_low).into(authorImage);
        }

        if(mediaObject.getPostThumbnail() != null ){
        this.requestManager
                .load(mediaObject.getPostThumbnail()).fitCenter()
                .into(thumbnail);
        }

        // Count Likes...
        getLikesCount(mediaObject.getPostID(), likeCount);

        // Check Post Like or Not by the user..
        if (uID != null) {
            isLiked(mediaObject.getPostID(), uID, likeButton);
        }

        // Count Comments...
        getCommentsCount(mediaObject.getPostID(), commentCount);

        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                if (uID != null) {
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(mediaObject.getPostID()).child(uID).setValue(true);
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                if (uID != null) {
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(mediaObject.getPostID()).child(uID).removeValue();
                }
            }
        });

        authorImage.setOnClickListener(v -> {
            if (mediaObject.getAuthorID() == uID) {
                appContext.startActivity(new Intent(appContext, OwnProfileActivity.class));
            } else {
                Intent i = new Intent(appContext, UserProfileActivityKt.class);
                i.putExtra("uID", mediaObject.getAuthorID());
                appContext.startActivity(i);
            }

        });

        authorName.setOnClickListener(v -> {
            if (mediaObject.getAuthorID() == uID) {
                appContext.startActivity(new Intent(appContext, OwnProfileActivity.class));
            } else {
                Intent i = new Intent(appContext, UserProfileActivityKt.class);
                i.putExtra("uID", mediaObject.getAuthorID());
                appContext.startActivity(i);
            }

        });

        commentLayout.setOnClickListener(v -> {
            Toast.makeText(appContext, "Comments is disabled for this post", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(appContext, FullPostActivity.class);
//            i.putExtra("Id", mediaObject.getPostID());
//            appContext.startActivity(i);
        });

        shareLayout.setOnClickListener(v -> {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(
                        Intent.EXTRA_SUBJECT,
                        appContext.getResources().getString(R.string.app_name)
                );
                String shareHint = "https://starnotesocial.com/shared_post/" + mediaObject.getPostID();
                i.putExtra(Intent.EXTRA_TEXT, shareHint);
                appContext.startActivity(Intent.createChooser(i, "Choose one"));
            } catch (Exception e) {
                Toast.makeText(appContext, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void isLiked(String postId, String userID, LikeButton likeButton) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Likes").child(postId);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                likeButton.setLiked(dataSnapshot.child(userID).exists());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getLikesCount(String postId, TextView likeCountView) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                likeCountView.setText("" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO
            }
        });
    }

    private void getCommentsCount(String postID, TextView comments) {
        DatabaseReference commentReference = FirebaseDatabase.getInstance().getReference().child("Comments").child(postID);
        commentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comments.setText("" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}














