package com.lifesolutions.bd.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Adapters.CommentsReplyListAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Helpers.TimeAgo;
import com.lifesolutions.bd.Models.Comments;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.data.model.CloudToken;
import com.lifesolutions.bd.kotlinCode.data.model.NotificationData;
import com.lifesolutions.bd.kotlinCode.data.model.PushNotification;
import com.lifesolutions.bd.kotlinCode.data.model.UserKt;
import com.lifesolutions.bd.kotlinCode.utils.FirebaseAuthorNotification;
import com.lifesolutions.bd.kotlinCode.utils.Notify;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentReplyActivity extends AppCompatActivity {

    ArrayList<Comments> list;
    LinearLayoutManager mLayoutManager;
    String uID, comment, replyId, commentId, userId, parentComment, currentUserImageLink, postId;
    RecyclerView recyclerView;
    CommentsReplyListAdapter mAdapter;
    ProgressBar progressBar;
    ImageButton add;
    EditText commentField;
    long commentDateTime, dateTime;
    Toolbar toolbar;
    DatabaseReference myRef, mReference, database, currentRef;
    TextView commentText, commenterName, cDatetime;
    CircleImageView currentUserImage, commenterPhoto;


    // Shared Pref
    private SharedPreferences userPreferences;
    // Current User Info
    private String currentUserID;
    private String currentUserFirstName;
    private String currentUserLastName;
    private String currentUserProfileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_reply);

        toolbar = findViewById(R.id.toolbar_comments_reply);
        recyclerView = findViewById(R.id.recyclerView_comment_reply);
        progressBar = findViewById(R.id.progressbar_comments_reply);
        add = findViewById(R.id.comment_btn__reply);
        commentField = findViewById(R.id.comment_field_reply);
        commentText = findViewById(R.id.message_comment_reply);
        cDatetime = findViewById(R.id.comment_reply_date_time);
        commenterName = findViewById(R.id.name_comment_reply);
        currentUserImage = findViewById(R.id.commenter_photo_reply);
        commenterPhoto = findViewById(R.id.image_comment_reply);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mReference = FirebaseDatabase.getInstance().getReference("Users");
        database = FirebaseDatabase.getInstance().getReference();

        // Initialize Shared Preferences for User Data
        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE);


        Intent myIntent = getIntent();
        if (myIntent != null) {
            commentId = myIntent.getStringExtra("commentId");
            userId = myIntent.getStringExtra("userId");
            parentComment = myIntent.getStringExtra("comment");
            dateTime = myIntent.getLongExtra("dateTime", 0);
            postId = myIntent.getStringExtra("parentPostID");

            commentText.setText(parentComment);
            cDatetime.setText(TimeAgo.toDuration(System.currentTimeMillis() - dateTime));
            CommentsReplyListAdapter.getUserInfo(commenterPhoto, commenterName, userId);

        }

        getCurrentUserInfo();

        list = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(false);
        mLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);


        add.setOnClickListener(v -> {

            comment = commentField.getText().toString();

            if (!InternetCheck.checkConnection(CommentReplyActivity.this)) {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                return;
            }

            if (comment.isEmpty()) {
                commentField.setError("This field can't be empty");
                commentField.requestFocus();
                return;
            }

            String fName = currentUserFirstName + currentUserLastName;
            commentDateTime = System.currentTimeMillis();
            myRef = database.child("CommentsReply").child(commentId).push();
            DatabaseReference cR = database.child("CommentsReplyCount").child(postId).push();
            replyId = myRef.getKey();

            Comments notice = new Comments(comment, uID, replyId, commentId, commentDateTime, true);

            if (!fName.isEmpty()) {
                commentField.setText("");
                myRef.setValue(notice).addOnSuccessListener(aVoid -> {
                    cR.child(String.valueOf(System.currentTimeMillis())).setValue("1");
                    commentField.setText("");
                    Snackbar.make(findViewById(android.R.id.content), "Reply added successfully", Snackbar.LENGTH_SHORT).show();
                    add.setEnabled(true);
                }).addOnFailureListener(e -> {
                    Snackbar.make(findViewById(android.R.id.content), "Failed to add comment", Snackbar.LENGTH_SHORT).show();
                    add.setEnabled(true);
                });


                if (!uID.equals(userId)) {
                    FirebaseAuthorNotification.INSTANCE.sendNotificationToAuthor(postId,userId,uID,"comment","Replied on Post Comment");
                    mReference.child(uID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            UserKt user = dataSnapshot.getValue(UserKt.class);
                            sendNotification(userId,user.getFirstName()+" "+user.getLastName(),"replied on your comment");

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
//                PostListAdapter.sendNotificationToAuthor(commentId, userId, uID, "commentReply", "Replied your comment");

        } else{
            Toast.makeText(this, "Your name is empty", Toast.LENGTH_SHORT).show();
        }


    });

    getCommentsList();

    getCurrentUserImage();

}

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void getCurrentUserImage() {
        currentRef = FirebaseDatabase.getInstance().getReference().child("Users");
        currentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                currentUserImageLink = dataSnapshot.child(uID).child("imageThumbnail").getValue(String.class);
                if (!currentUserImageLink.equals("none")) {
                    Picasso.get().load(currentUserImageLink).into(currentUserImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getCommentsList() {

        DatabaseReference commentRef = database.child("CommentsReply").child(commentId);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Comments comnt = dataSnapshot1.getValue(Comments.class);
                    list.add(comnt);
                }

                if (list.size() == 0) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    mAdapter = new CommentsReplyListAdapter(CommentReplyActivity.this, list);
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }


    private void getCurrentUserInfo() {
        currentUserID = userPreferences.getString("uID", null);
        currentUserFirstName = userPreferences.getString("firstName", "");
        currentUserLastName = userPreferences.getString("lastName", "");
        currentUserProfileImg = userPreferences.getString("imageThumbnail", null);

    }


    private void sendNotification(String receiverID, String firstName, String message) {
        DatabaseReference tokenRef = FirebaseDatabase.getInstance().getReference("CloudTokens");
        tokenRef.child(receiverID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    CloudToken cloudToken = dataSnapshot.getValue(CloudToken.class);
                    NotificationData notificationData = new NotificationData(
                            userId,
                            firstName + " " + message,
                            "Post Comment Reply",
                            receiverID,
                            "comment",
                            postId,
                            R.drawable.app_icon
                    );

                    PushNotification pushNotification = new PushNotification(notificationData, cloudToken.getToken());
                    Notify.sendMgsNotification(pushNotification);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO....
            }
        });
    }


}
