package com.lifesolutions.bd.kotlinCode.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Index;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddDataActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mReference;
    Api.Client client;
    Index index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        List<JSONObject> array = new ArrayList<JSONObject>();

//        client = new Api.Client("PUXDD507IK", "6d00aae639c4899d9876dd677194f09a");
//        index = client.getIndex("UserDatabase");


        mReference = FirebaseDatabase.getInstance().getReference().child("Users");
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    ArrayList<User> users = new ArrayList<>();

                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        if (!TextUtils.isEmpty(user.getFirstName())) {
                            users.add(user);
                            String searchname = user.getFirstName() + " " + user.getLastName();
                            Log.d("name:", searchname);

                            try {
                                array.add(
                                        new JSONObject().put("searchName", searchname).put("id", user.getuID())
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //  Log.d("name: " , String.valueOf(array.size()));
                        }
                    }

                    Log.d("Size", "Sizess: " +array.size());
                   // index.addObjectsAsync(new JSONArray(array), null);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(getApplicationContext(), "getting data done", Toast.LENGTH_SHORT).show();

    }
}