package com.lifesolutions.bd.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.Adapters.RequestListAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Models.UserId;
import com.lifesolutions.bd.R;

import java.util.ArrayList;
import java.util.Objects;

public class FriendListActivity extends AppCompatActivity {

    private String TAG = "FriendListActivity";

    Toolbar toolbar;
    private ArrayList<UserId> list;
    private RequestListAdapter mAdapter;
    DatabaseReference mReference, requestRef;
    private String uID;
    private RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        toolbar = findViewById(R.id.toolbar_friend_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView_friend_list);
        progressBar = findViewById(R.id.progressbar_friend_list);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(false);
        mLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(mLayoutManager);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        uID = sharedPreferences.getString("uID", null);

        list = new ArrayList<>();

        if (InternetCheck.checkConnection(this)) {

            mReference = FirebaseDatabase.getInstance().getReference("Users");
            requestRef = FirebaseDatabase.getInstance().getReference("friendRequest").child(uID);

            requestRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            if (Objects.equals(dataSnapshot1.child("type").getValue(String.class), "friend")) {
                                UserId userId = dataSnapshot1.getValue(UserId.class);
                                list.add(userId);
                                Log.w(TAG, userId.toString());
                            }
                        }

                        if (list.size() == 0) {
                            Toast.makeText(FriendListActivity.this, "No Friends", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(FriendListActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            mAdapter = new RequestListAdapter(FriendListActivity.this, list, false);
                            recyclerView.setAdapter(mAdapter);
                        }
                        Log.w(TAG, "Data Exists");
                    } else {
                        Log.w(TAG, "No Data");
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(FriendListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
