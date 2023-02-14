package com.lifesolutions.bd.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.gmail.samehadar.iosdialog.IOSDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestAcceptActivity extends AppCompatActivity {

    TextView tvName,tvAddress,tvStudentInfo,tvBlood,tvJoined,tvBirthDate,tvAccept,tvDelete;
    private String firstName,lastName,address,studentInfo = "none",blood = "none",coverImageUrlThumb,imageUrlNormal,userId;
    String uID;
    long birthDate,joined;
    DatabaseReference mReference,fReqReference,layoutReference;
    CircleImageView imageView;
    ImageButton exit;
    ImageView coverPhoto;
    private IOSDialog dialog1;
    LinearLayout reqLayout;
    FirebaseAuth mAuth;
    private Boolean verified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_accept);

        tvName = findViewById(R.id.name_friend_request);
        tvAddress = findViewById(R.id.address_friend_request);
        tvStudentInfo = findViewById(R.id.studentInfo_friend_request);
        tvBlood = findViewById(R.id.blood_group_friend_request);
        tvJoined = findViewById(R.id.joined_friend_request);
        tvBirthDate = findViewById(R.id.birth_date_friend_request);
        imageView = findViewById(R.id.profile_picture_friend_request);
        coverPhoto = findViewById(R.id.cover_photo_friend_request);
        tvAccept = findViewById(R.id.accept_friend_request);
        tvDelete = findViewById(R.id.delete_friend_request);
        reqLayout = findViewById(R.id.activity_layout_friend_request);
        exit = findViewById(R.id.back_button_friend_request_accept);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth != null){
            uID = mAuth.getUid();
        } else {
            Intent intent = new Intent(RequestAcceptActivity.this, LoginKtActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            RequestAcceptActivity.this.finish();
        }

        Intent myIntent = getIntent();
        if (myIntent != null) {
            userId = getIntent().getStringExtra("uID");
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            finish();
        }

        mReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        fReqReference = FirebaseDatabase.getInstance().getReference().child("friendRequest");
        layoutReference = FirebaseDatabase.getInstance().getReference().child("friendRequest").child(uID);

        dialog1 = new IOSDialog.Builder(RequestAcceptActivity.this)
                .setSpinnerColorRes(R.color.white)
                .setMessageColorRes(R.color.white)
                .setTitleColorRes(R.color.colorPrimary)
                .setMessageContent("Please Wait")
                .setCancelable(false)
                .setMessageContentGravity(Gravity.CENTER)
                .build();


        if (InternetCheck.checkConnection(RequestAcceptActivity.this)){
            dialog1.show();

            layoutReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(userId).exists()){
                        if (dataSnapshot.child(userId).child("type").getValue().equals("request")){
                            reqLayout.setVisibility(View.VISIBLE);
                        } else {
                            reqLayout.setVisibility(View.GONE);
                        }
                    } else {
                        reqLayout.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            mReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    firstName = dataSnapshot.child("firstName").getValue(String.class);
                    lastName = dataSnapshot.child("lastName").getValue(String.class);
                    address = dataSnapshot.child("address").getValue(String.class);
                    imageUrlNormal = dataSnapshot.child("imageThumbnail").getValue(String.class);

                    if (dataSnapshot.child("birthDate").exists()) {
                        birthDate = dataSnapshot.child("birthDate").getValue(Long.class);
                    }

                    if (dataSnapshot.child("registeredTime").exists()) {
                        joined = dataSnapshot.child("registeredTime").getValue(Long.class);
                    }

                    if (dataSnapshot.child("coverImageThumb").exists()){
                        coverImageUrlThumb = dataSnapshot.child("coverImageThumb").getValue(String.class);
                        Picasso.get().load(coverImageUrlThumb).into(coverPhoto);
                    }

                    if (dataSnapshot.child("studyInfo").exists()){
                        studentInfo = dataSnapshot.child("studyInfo").getValue(String.class);
                    }
                    if (dataSnapshot.child("bloodGroup").exists()){
                        blood = dataSnapshot.child("bloodGroup").getValue(String.class);
                    }

                    if (dataSnapshot.child("isVerified").exists()){
                        verified = dataSnapshot.child("isVerified").getValue(Boolean.class);
                    }

                    if (verified){
                        tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_new_badge, 0, 0, 0);
                    } else {
                        tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }

                    dialog1.dismiss();

                    tvName.setText(firstName+" "+lastName);
                    tvAddress.setText(address);
                    if (tvJoined != null) {
                        tvJoined.setText(UserProfileActivity.getJoinedDate(joined));
                    }
                    if (tvBirthDate != null) {
                        tvBirthDate.setText(UserProfileActivity.getBirthDate(birthDate));
                    }
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
                    if (!imageUrlNormal.equals("none")){
                        Picasso.get().load(imageUrlNormal).into(imageView);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(RequestAcceptActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
                }
            });


        }else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        tvAccept.setOnClickListener(view -> fReqReference.child(uID).child(userId).child("type").setValue("friend").addOnSuccessListener(aVoid -> fReqReference.child(userId).child(uID).child("type").setValue("friend").addOnSuccessListener(aVoid1 -> fReqReference.child(userId).child(uID).child("uid").setValue(uID).addOnSuccessListener(aVoid11 -> {
            reqLayout.setVisibility(View.GONE);
            Toast.makeText(RequestAcceptActivity.this, "Request accepted", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> Toast.makeText(RequestAcceptActivity.this, "Failed to accept request", Toast.LENGTH_SHORT).show())).addOnFailureListener(e -> Toast.makeText(RequestAcceptActivity.this, "Failed to accept request", Toast.LENGTH_SHORT).show())).addOnFailureListener(e -> Toast.makeText(RequestAcceptActivity.this, "Failed to accept request", Toast.LENGTH_SHORT).show()));

        tvDelete.setOnClickListener(view -> fReqReference.child(uID).child(userId).removeValue().addOnSuccessListener(aVoid -> {
            reqLayout.setVisibility(View.GONE);
            Toast.makeText(RequestAcceptActivity.this, "Request Deleted", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> Toast.makeText(RequestAcceptActivity.this, "Failed to delete request", Toast.LENGTH_SHORT).show()));

        exit.setOnClickListener(view -> finish());
    }
}
