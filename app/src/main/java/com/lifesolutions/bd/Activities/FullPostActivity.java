package com.lifesolutions.bd.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Adapters.CommentsAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Models.Comments;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.data.model.CloudToken;
import com.lifesolutions.bd.kotlinCode.data.model.NotificationData;
import com.lifesolutions.bd.kotlinCode.data.model.PushNotification;
import com.lifesolutions.bd.kotlinCode.data.model.UserKt;
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity;
import com.lifesolutions.bd.kotlinCode.utils.FirebaseAuthorNotification;
import com.lifesolutions.bd.kotlinCode.utils.Notify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FullPostActivity extends AppCompatActivity {

    private String TAG = "FullPostActivity";
    private TextView description,dateTime;
    private RecyclerView recyclerView;
    FloatingActionButton downloadButton;
    private ImageView picture,a_image,commenter_photo;
    private EditText commentField;
    CommentsAdapter mAdapter;
    ArrayList<Comments> list;
    private long commentDateTime,date;
    LinearLayoutManager mLayoutManager;
    private ImageButton add;
    private int commentCount = 0;
    private String Description,imageLink,postId,author_id,userId,comment,commentId;
    private String postDate,currentUserImageLink;
    private DatabaseReference commentReference;
    DatabaseReference databaseReference,myRef,currentRef,commentRef;
    DatabaseReference mReference;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private boolean notify = false;
    private RequestQueue requestQueue;
    FirebaseAuth mAuth;
    LinearLayout commentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post);

        description = findViewById(R.id.description_full_post);
        picture = findViewById(R.id.main_image_full_post);
        dateTime = findViewById(R.id.date_time_full_post);
        a_image = findViewById(R.id.author_image_full_post);
        commentField = findViewById(R.id.comment_full_post);
        add = findViewById(R.id.comment_btn_full_post);
        commenter_photo = findViewById(R.id.commenter_Photo_full_post);
        recyclerView = findViewById(R.id.recyclerview_comment);
        downloadButton = findViewById(R.id.download_button_full_post);
        commentLayout = findViewById(R.id.comment_layout_full_post);


        Intent intent = getIntent();
        if (intent !=null) postId = intent.getStringExtra("Id");

        Log.w(TAG, "onCreate: -> " + postId);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth != null){
            userId = mAuth.getUid();
        } else {
            Intent intent1 = new Intent(FullPostActivity.this, LoginKtActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
            FullPostActivity.this.finish();
        }

        mReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        commentReference = FirebaseDatabase.getInstance().getReference().child("Comments").child(postId);
        list = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        // databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts").child(postId);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Feeds").child(postId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Description = dataSnapshot.child("postDescription").getValue(String.class);
                    imageLink = dataSnapshot.child("postImage").getValue(String.class);
                    try {
                        author_id = dataSnapshot.child("authorID").getValue(String.class);
                        date = dataSnapshot.child("date").getValue(Long.class);
                        getPostDateTime(date,author_id);
                    } catch (Exception e){

                    }

                    Toolbar toolbar = findViewById(R.id.toolbar_full_post);
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    collapsingToolbarLayout = findViewById(R.id.collapsing_layout_full_post);

                    collapsingToolbarLayout.setTitle("Full Post");
                    collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));


                    description.setText(Description);

                    if (imageLink.equals("none")) {
                        picture.setVisibility(View.GONE);
                        downloadButton.setVisibility(View.GONE);
                        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.text_color_dark));
                    } else {
                        downloadButton.setVisibility(View.VISIBLE);
                        Picasso.get().load(imageLink).into(picture);
                    /*picture.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(FullPostActivity.this,ViewImageActivity.class)
                            .putExtra("imageUrl",imageLink));
                        }
                    });*/

                        downloadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                        requestPermissions(permissions,1000);
                                    } else {
                                        startDownloading(imageLink);
                                    }
                                } else {

                                    startDownloading(imageLink);
                                }
                            }
                        });
                    }

                    getCommenterImage();
                    getCommentsList();
                } else {
                    Toast.makeText(FullPostActivity.this, "This post not exits", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(FullPostActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(v -> {
            notify = true;
            commentCount = 0; //
            if (InternetCheck.checkConnection(FullPostActivity.this)){
                comment = commentField.getText().toString();
                if (comment.isEmpty()) {
                    commentField.setError("This field can't be empty");
                    commentField.requestFocus();
                } else {
                    add.setEnabled(false);
                    commentField.setText("");

                    if (commentCount == 0){
                        commentDateTime = System.currentTimeMillis();
                        myRef = commentReference.push();
                        commentId = myRef.getKey();

                        Comments comments = new Comments(comment,userId,commentId,postId,commentDateTime,true);

                        myRef.setValue(comments).addOnSuccessListener(aVoid -> {
                            Snackbar.make(findViewById(android.R.id.content),"Comment added successfully",Snackbar.LENGTH_SHORT).show();
                            commentCount = 1;
                            add.setEnabled(true);
                        }).addOnFailureListener(e -> {
                            Snackbar.make(findViewById(android.R.id.content),"Failed to add comment",Snackbar.LENGTH_SHORT).show();
                            add.setEnabled(true);
                        });

                        if (!author_id.equals(userId)){
                            FirebaseAuthorNotification.INSTANCE.sendNotificationToAuthor(postId,author_id,userId,"comment","Commented your post");
                            mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    UserKt user = dataSnapshot.getValue(UserKt.class);
                                    if (notify){
                                        sendNotification(author_id,user.getFirstName()+" "+user.getLastName(),"commented on your post");
                                    }
                                    notify = false;
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void getCommenterImage() {

        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("firstName").exists() && dataSnapshot.child("lastName").exists()){
                    commentLayout.setVisibility(View.VISIBLE);
                }

                currentUserImageLink = dataSnapshot.child("imageThumbnail").getValue(String.class);
                if (!currentUserImageLink.equals("none")){
                    Picasso.get().load(currentUserImageLink).into(commenter_photo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getCommentsList() {
        commentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Comments comnt = dataSnapshot1.getValue(Comments.class);
                    list.add(comnt);
                }

                mAdapter = new CommentsAdapter(FullPostActivity.this,list);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

    private void getPostDateTime(long dateTimeInMilli, final String author_id){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy - hh:mm a",Locale.getDefault());
        postDate = formatter.format(dateTimeInMilli);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(author_id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String firstName = dataSnapshot.child("firstName").getValue(String.class);
                String lastName = dataSnapshot.child("lastName").getValue(String.class);
                String imageUrl = dataSnapshot.child("imageThumbnail").getValue(String.class);

                String c_Date = postDate+" by "+firstName+" "+lastName;
                dateTime.setText(c_Date);

                if (imageUrl != null && !imageUrl.equals("none")){
                    Picasso.get().load(imageUrl).into(a_image);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(FullPostActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startDownloading(String url) {

        Toast.makeText(this, "Download Started", Toast.LENGTH_SHORT).show();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("file downloading ... ");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis()+".jpg");

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1000:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startDownloading(imageLink);
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
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
                            "Post Comment",
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
