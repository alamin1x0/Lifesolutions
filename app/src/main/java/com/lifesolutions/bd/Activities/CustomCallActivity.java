package com.lifesolutions.bd.Activities;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.activities.AllActiverUserActivity;

public class CustomCallActivity extends Activity {

    private final String user_id = FirebaseAuth.getInstance().getUid();
    DatabaseReference mReference;
    Button callButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_call);
        mReference = FirebaseDatabase.getInstance().getReference().child("CustomCalling");
        callButton = findViewById(R.id.call_button);

        mReference.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    Log.w("TAG", "Data Exists");
                    Toast.makeText(getApplicationContext(), "New Calling Incoming", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CustomCallRecieve.class));

                } else {

                    Log.w("TAG", "No Data");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CustomCallActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllActiverUserActivity.class));
            }
        });

    }
}