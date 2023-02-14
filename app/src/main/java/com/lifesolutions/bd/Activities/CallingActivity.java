package com.lifesolutions.bd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.lifesolutions.bd.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CallingActivity extends AppCompatActivity {


    private String receiverID,senderId;
    TextView tvName,tvAddress,tvTimer;
    ImageButton btn_cancel,btn_speaker,btn_accept,btn_decline;
    CircleImageView profile_Image;
    RelativeLayout receive,calling;
    DatabaseReference mReference;
    private String firstName,lastName,address,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        /*tvName = findViewById(R.id.name_call);
        tvAddress = findViewById(R.id.address_call);
        tvTimer = findViewById(R.id.timer_call);
        btn_cancel = findViewById(R.id.call_reject_call);
        btn_speaker = findViewById(R.id.loud_call);
        btn_accept = findViewById(R.id.call_accept_call);
        btn_decline = findViewById(R.id.decline_call);
        profile_Image = findViewById(R.id.profile_image_call);
        receive = findViewById(R.id.bottom_layout_receive);
        calling = findViewById(R.id.bottom_layout_calling);


        Intent myIntent = getIntent();
        if (myIntent != null)
        {
            receiverID = myIntent.getStringExtra("receiverId");
        }


        mReference = FirebaseDatabase.getInstance().getReference("Users").child(receiverID);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                firstName = dataSnapshot.child("firstName").getValue(String.class);
                lastName = dataSnapshot.child("lastName").getValue(String.class);
                address = dataSnapshot.child("address").getValue(String.class);
                image = dataSnapshot.child("imageThumbnail").getValue(String.class);

                tvName.setText(firstName+""+lastName);
                tvAddress.setText(address);
                Picasso.get().load(image).into(profile_Image);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(CallingActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
            }
        });


        btn_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/


    }

}
