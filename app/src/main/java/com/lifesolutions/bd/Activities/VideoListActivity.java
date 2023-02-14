package com.lifesolutions.bd.Activities;

import android.content.Intent;
import android.os.Bundle;
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
import com.lifesolutions.bd.Adapters.VideoListAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Models.VideoItem;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

public class VideoListActivity extends AppCompatActivity {

    Toolbar toolbar;
    private ArrayList<VideoItem> list;
    private VideoListAdapter mAdapter;
    DatabaseReference mReference;
    private RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    ProgressBar progressBar;
    private String refId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        toolbar = findViewById(R.id.toolbar_video_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView_video_list);
        progressBar = findViewById(R.id.progressbar_video_list);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(false);
        mLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(mLayoutManager);

        list = new ArrayList<>();

        Intent myIntent = getIntent();
        if (myIntent != null){
           refId = myIntent.getStringExtra("reference");
           setTitle(refId);
        }

        mReference = FirebaseDatabase.getInstance().getReference().child("DigitalClassroom").child(refId);

        if (InternetCheck.checkConnection(this)) {

            mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        VideoItem videoItem = dataSnapshot1.getValue(VideoItem.class);
                        list.add(videoItem);
                    }

                    if (list.size()==0) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(VideoListActivity.this, "No Classes", Toast.LENGTH_SHORT).show();

                    } else {
                        progressBar.setVisibility(View.GONE);
                        mAdapter = new VideoListAdapter(VideoListActivity.this, list);
                        recyclerView.setAdapter(mAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(VideoListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
