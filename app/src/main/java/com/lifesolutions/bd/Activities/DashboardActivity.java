//package com.stardigiinternational.starnotee.Activities;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.R;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class DashboardActivity extends AppCompatActivity {
//
//    CircleImageView profileImage;
//    TextView tvName,tvAddress,tvRefer,tvPoint;
//    private DatabaseReference mReference;
//    private String uID,firstName,lastName,imageUrl,address;
//    private int refer,point;
//    Toolbar toolbar;
//    RelativeLayout btn_myProfile;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//
//        profileImage = findViewById(R.id.profile_Image_dashboard);
//        tvName = findViewById(R.id.name_dashboard);
//        tvAddress = findViewById(R.id.address_dashboard);
//        tvRefer = findViewById(R.id.refer_dashboard);
//        tvPoint = findViewById(R.id.point_dashboard);
//        toolbar = findViewById(R.id.toolbar_dashboard);
//        btn_myProfile = findViewById(R.id.my_profile_dashboard);
//
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("My Dashboard");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        mReference = FirebaseDatabase.getInstance().getReference().child("Users");
//        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
//        uID = sharedPreferences.getString("uID",null);
//
//
//        mReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                firstName = dataSnapshot.child(uID).child("firstName").getValue(String.class);
//                lastName = dataSnapshot.child(uID).child("lastName").getValue(String.class);
//                address = dataSnapshot.child(uID).child("address").getValue(String.class);
//                imageUrl = dataSnapshot.child(uID).child("imageMedium").getValue(String.class);
//                point = dataSnapshot.child(uID).child("points").getValue(Integer.class);
//                refer = dataSnapshot.child(uID).child("referred").getValue(Integer.class);
//
//                tvName.setText(firstName+" "+lastName);
//                tvAddress.setText(address);
//                tvPoint.setText(""+point);
//                tvRefer.setText(""+refer);
//                if (imageUrl.equals("none")){
//
//                }else {
//                    Picasso.get().load(imageUrl).into(profileImage);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                Toast.makeText(DashboardActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btn_myProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DashboardActivity.this,ProfileActivity.class));
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
//}
