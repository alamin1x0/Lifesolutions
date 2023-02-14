package com.lifesolutions.bd.Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.R;

import java.util.ArrayList;
import java.util.Collections;

public class StreamViewList extends AppCompatActivity {

    DatabaseReference myRefDB = FirebaseDatabase.getInstance().getReference("LiveVideos");
    AllLiveVideoViewAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_view_list);
        ArrayList<String> titles = new ArrayList<String>();
        layoutManager = new LinearLayoutManager(this);

        adapter = new AllLiveVideoViewAdapter(getApplicationContext());
        recyclerView = findViewById(R.id.live_video_list);
        final int[] j = {0};




        myRefDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<LiveVideo> liveVideos = new ArrayList<>();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    LiveVideo liveVideo = userSnapshot.getValue(LiveVideo.class);

                    String  title =  liveVideo.getPostDescription();
                    //UpdateStringArray(title);
                    if(title.isEmpty()){
                        title = "Untitled Video";
                    }
                    titles.add(title);

                    liveVideos.add(liveVideo);
                    //Toast.makeText(getApplicationContext(),title,Toast.LENGTH_SHORT).show();

                }

                // Toast.makeText(StreamViewList.this, "" + liveVideos.size(), Toast.LENGTH_SHORT).show();
                Collections.reverse(liveVideos);
                adapter.removeAllItem();
                adapter.addAllPosts(liveVideos);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}