package com.lifesolutions.bd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.gmail.samehadar.iosdialog.IOSDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Adapters.ProfilePostListAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Models.PostItem;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.data.model.CallHistory;
import com.lifesolutions.bd.kotlinCode.data.model.CloudToken;
import com.lifesolutions.bd.kotlinCode.data.model.NotificationData;
import com.lifesolutions.bd.kotlinCode.data.model.PushNotification;
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity;
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity;
import com.lifesolutions.bd.kotlinCode.utils.FirebaseAuthorNotification;
import com.lifesolutions.bd.kotlinCode.utils.Notify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {

    TextView tvName,tvAddress,tvStudentInfo,tvBlood,tvUserPostCount,tvJoined,tvBirthDate,tvfReq,tvFollowers,tvBio,minTalk;
    private String firstName,lastName,bio,address,studentInfo = "none",
            blood = "none",coverImageUrlThumb,imageUrlNormal,userId,uID,imageUrlThumb;
    long birthDate,joined;
    private ProfilePostListAdapter mAdapter;
    DatabaseReference postReference,fReqReference,userReference;
    CircleImageView imageView;
    ImageView coverPhoto,fReqIcon;
    RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<PostItem> list;
    private int count = 0,userPostCount = 0;
    private IOSDialog dialog1;
    RecyclerView recyclerView;
    LinearLayout addFriend,sendMessage;
    private boolean notify = false,verified = false;
    private RequestQueue requestQueue;
    private ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvName = findViewById(R.id.name_user_profile);
        tvAddress = findViewById(R.id.address_user_profile);
        tvStudentInfo = findViewById(R.id.studentInfo_user_profile);
        tvBlood = findViewById(R.id.blood_group_user_profile);
        tvJoined = findViewById(R.id.joined_user_profile);
        tvBirthDate = findViewById(R.id.birth_date_user_profile);
        tvUserPostCount = findViewById(R.id.post_count_user_profile);
        imageView = findViewById(R.id.profile_picture_user_profile);
        coverPhoto = findViewById(R.id.cover_photo_user_profile);
        recyclerView = findViewById(R.id.post_recyclerView_user_profile);
        addFriend = findViewById(R.id.add_friend_user_profile);
        tvfReq = findViewById(R.id.txt_add_fiend_user_profile);
        fReqIcon = findViewById(R.id.ic_add_friend_user_profile);
        tvFollowers = findViewById(R.id.followers_user_profile);
        tvBio = findViewById(R.id.designation_user_profile);
        sendMessage = findViewById(R.id.message_user_profile);
        progressBar = findViewById(R.id.progress_bar_user_profile);
        minTalk = findViewById(R.id.talk_time_count);

        list = new ArrayList<>();

        /*mLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mLayoutManager);*/

        Intent myIntent = getIntent();
        if (myIntent != null) {
            userId = getIntent().getStringExtra("uID");
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            finish();
        }

        mAuth = FirebaseAuth.getInstance();
        if(mAuth != null){
            uID = mAuth.getUid();
        } else {
            Intent intent = new Intent(UserProfileActivity.this, LoginKtActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            UserProfileActivity.this.finish();
        }

        userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        // postReference = FirebaseDatabase.getInstance().getReference().child("Posts");
        postReference = FirebaseDatabase.getInstance().getReference().child("Feeds");
        fReqReference = FirebaseDatabase.getInstance().getReference().child("friendRequest");
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        dialog1 = new IOSDialog.Builder(UserProfileActivity.this)
                .setDimAmount(3)
                .setSpinnerColorRes(R.color.white)
                .setMessageColorRes(R.color.white)
                .setTitleColorRes(R.color.colorPrimary)
                .setMessageContent("Please Wait")
                .setCancelable(false)
                .setMessageContentGravity(Gravity.CENTER)
                .build();


        if (InternetCheck.checkConnection(UserProfileActivity.this)){
            dialog1.show();
            userReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    firstName = dataSnapshot.child("firstName").getValue(String.class);
                    lastName = dataSnapshot.child("lastName").getValue(String.class);
                    address = dataSnapshot.child("address").getValue(String.class);
                    imageUrlThumb = dataSnapshot.child("imageThumbnail").getValue(String.class);
                    imageUrlNormal = dataSnapshot.child("imageHD").getValue(String.class);
                    if (dataSnapshot.child("birthDate").exists()){
                        birthDate = dataSnapshot.child("birthDate").getValue(Long.class);
                    } else {
                        tvBirthDate.setVisibility(View.GONE);
                    }
                    joined = dataSnapshot.child("registeredTime").getValue(Long.class);

                    if (dataSnapshot.child("coverImageThumb").exists()){
                        coverImageUrlThumb = dataSnapshot.child("coverImageThumb").getValue(String.class);
                        Picasso.get().load(coverImageUrlThumb).into(coverPhoto);
                        coverPhoto.setOnClickListener(view -> startActivity(new Intent(UserProfileActivity.this, ViewImageActivity.class).putExtra("imageUrl",coverImageUrlThumb)));
                    }

                    if (dataSnapshot.child("studyInfo").exists()){
                        studentInfo = dataSnapshot.child("studyInfo").getValue(String.class);
                    } else {
                        tvStudentInfo.setVisibility(View.GONE);
                    }

                    if (dataSnapshot.child("isVerified").exists()){
                        verified = dataSnapshot.child("isVerified").getValue(Boolean.class);
                    }

                    if (verified){
                        tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_new_badge, 0, 0, 0);
                    } else {
                        tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }

                    if (dataSnapshot.child("bio").exists()){
                        bio = dataSnapshot.child("bio").getValue(String.class);
                    }
                    if (!TextUtils.isEmpty(bio)){
                        tvBio.setVisibility(View.VISIBLE);
                        tvBio.setText(bio);
                    }else {
                        tvBio.setVisibility(View.GONE);
                    }

                    if (dataSnapshot.child("bloodGroup").exists()){
                        blood = dataSnapshot.child("bloodGroup").getValue(String.class);
                    } else {
                        tvBlood.setVisibility(View.GONE);
                    }

                    tvName.setText(firstName+" "+lastName);
                    if (!TextUtils.isEmpty(address)){
                        tvAddress.setText(address);
                    } else {
                        tvAddress.setVisibility(View.GONE);
                    }
                    tvJoined.setText(getJoinedDate(joined));
                    tvBirthDate.setText(getBirthDate(birthDate));
                    if (blood.equals("none")){
                        tvBlood.setVisibility(View.GONE);
                    } else {
                        tvBlood.setText(blood);
                    }
                    if (studentInfo.equals("none")){
                        tvStudentInfo.setVisibility(View.GONE);
                    }else {
                        tvStudentInfo.setText(studentInfo);
                    }
                    if (!imageUrlThumb.equals("none")){
                        Picasso.get().load(imageUrlThumb).into(imageView);
                        imageView.setOnClickListener(view -> startActivity(new Intent(UserProfileActivity.this, ViewImageActivity.class).putExtra("imageUrl",imageUrlNormal)));
                    }

                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(UserProfileActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
                }

            });

            //getAllPost();
            getTalkTime();
            getPostCount();

        }else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        getFriendFollowerCount(userId,tvFollowers);

        fReqReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(userId).child(uID).exists()){
                    if (dataSnapshot.child(userId).child(uID).child("type").getValue(String.class).equals("request")){
                        addFriend.setEnabled(true);
                        fReqIcon.setImageResource(R.drawable.ic_new_user);
                        fReqIcon.setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.colorMain), android.graphics.PorterDuff.Mode.SRC_IN);
                        tvfReq.setText("Cancel Request");
                        tvfReq.setTextColor(getResources().getColor(R.color.colorMain));

                        addFriend.setOnClickListener(view -> {
                            fReqReference.child(userId).child(uID).removeValue().addOnSuccessListener(aVoid -> Toast.makeText(UserProfileActivity.this, "Friend Request cancelled succefully", Toast.LENGTH_SHORT).show());
                        });

                    }else {
                        addFriend.setEnabled(false);
                        fReqIcon.setImageResource(R.drawable.ic_group_black_24dp);
                        fReqIcon.setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.colorMain), android.graphics.PorterDuff.Mode.SRC_IN);
                        tvfReq.setText("Friends");
                        tvfReq.setTextColor(getResources().getColor(R.color.colorMain));
                    }

                } else {

                    addFriend.setEnabled(true);
                    fReqIcon.setImageResource(R.drawable.ic_new_user);
                    fReqIcon.setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.text_color_dark), android.graphics.PorterDuff.Mode.SRC_IN);
                    tvfReq.setText(getResources().getString(R.string.add_friend));
                    tvfReq.setTextColor(getResources().getColor(R.color.text_color_light));

                    notify = true;

                    addFriend.setOnClickListener(view -> {
                        fReqReference.child(userId).child(uID).child("type").setValue("request").addOnSuccessListener(aVoid ->{
                            fReqReference.child(userId).child(uID).child("uid").setValue(uID).addOnSuccessListener(aVoid1 -> {

                                FirebaseAuthorNotification.INSTANCE.sendNotificationToAuthor("",userId,uID,"request","Sent you a friend request");
                                Toast.makeText(UserProfileActivity.this, "Friend Request Sent", Toast.LENGTH_SHORT).show();
                                userReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                        User user = dataSnapshot1.getValue(User.class);
                                        if (notify){
                                            sendNotification(userId,user.getFirstName()+" "+user.getLastName(),"Sent you a friend request");
                                        }
                                        notify = false;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }).addOnFailureListener(e -> Toast.makeText(UserProfileActivity.this, "Failed to send friend request", Toast.LENGTH_SHORT).show());
                        }).addOnFailureListener(e -> Toast.makeText(UserProfileActivity.this, "Failed to send friend request", Toast.LENGTH_SHORT).show());
                    });
                }

                dialog1.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sendMessage.setOnClickListener(view -> {
            startActivity(new Intent(UserProfileActivity.this, PersonalMessageActivity.class).putExtra("receiverID",userId));
        });

    }

    private void getTalkTime() {
        DatabaseReference  ref = FirebaseDatabase.getInstance().getReference("UsersCallData").child(uID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    CallHistory history = dataSnapshot.getValue(CallHistory.class);
                    minTalk.setText(history.component1().toString() + "Minutes");

                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getPostCount() {
        Query query = postReference.orderByChild("authorID").equalTo(userId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    tvUserPostCount.setText(""+dataSnapshot.getChildrenCount());
                } else {
                    tvUserPostCount.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void backToHome(View view) {
        finish();
    }

    private void getAllPost(){

        if (InternetCheck.checkConnection(this)) {

            postReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list.clear();
                    count = 0;
                    userPostCount = 0;

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                        PostItem postItem = dataSnapshot1.getValue(PostItem.class);
                        if (postItem.getAuthorID().equals(userId) && !postItem.getPostImage().equals("none") && count < 10){
                            list.add(postItem);
                            count++;
                        }

                        if (postItem.getAuthorID().equals(userId)){
                            userPostCount++;
                        }

                    }
                    tvUserPostCount.setText(""+userPostCount);

                    mAdapter = new ProfilePostListAdapter(UserProfileActivity.this,list);
                    recyclerView.setAdapter(mAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(UserProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getJoinedDate(long joined){
        String date;
        SimpleDateFormat formatter = new SimpleDateFormat(" MMMM, yyyy", Locale.getDefault());
        date = formatter.format(joined);
        return date;
    }

    public static String getBirthDate(long birthDate){
        String date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd - MMMM - yyyy",Locale.getDefault());
        date = formatter.format(birthDate);
        return date;
    }

    public static void getFriendFollowerCount(String userId,TextView follower){

        DatabaseReference fReference = FirebaseDatabase.getInstance().getReference("friendRequest");
        fReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userId).exists()){
                    int count = 0;
                    follower.setText(""+dataSnapshot.child(userId).getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                            "Friend Request",
                            receiverID,
                            "request",
                            uID,
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
