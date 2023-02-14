package com.lifesolutions.bd.kotlinCode.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.VideoPlayerRecyclerAdapter;
import com.lifesolutions.bd.kotlinCode.VideoPlayerRecyclerView;
import com.lifesolutions.bd.kotlinCode.data.model.VideoPost;
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading;
import com.lifesolutions.bd.kotlinCode.utils.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.Collections;

public class VideoViewActivity extends AppCompatActivity {

    private static final String TAG = "VideoViewActivity";
    private AnimatedLoading animatedLoading;

    private VideoPlayerRecyclerView mRecyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
     //   VideoViewActivity.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        animatedLoading = new AnimatedLoading(this);

        toolbar = findViewById(R.id.toolbar_all_video_feed);
        mRecyclerView = findViewById(R.id.recycler_view_vid);

        // Set Actionbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Video Posts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  initRecyclerView();
        getInitPosts();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getInitPosts() {
        animatedLoading.showAnimatedLoading();
        Query databaseReference = FirebaseDatabase.getInstance().getReference("VideoFeeds").orderByKey().limitToLast(100);

        ArrayList<VideoPost> postKts = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        VideoPost postKt = child.getValue(VideoPost.class);
                        postKts.add(postKt);
                    }
                    Collections.reverse(postKts);
                    initRecyclerView(postKts);
                    animatedLoading.hideAnimatedLoading();
                    Log.w(TAG, "onDataChange: " + postKts.size() );

                } else {
                    animatedLoading.hideAnimatedLoading();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                animatedLoading.hideAnimatedLoading();
            }
        });
    }

    private void initRecyclerView(ArrayList<VideoPost> postKts){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);



        mRecyclerView.setMediaObjects(postKts);
        VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(postKts, initGlide(), this);
        mRecyclerView.setAdapter(adapter);
    }

    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.video_thumbnail_placeholder)
                .error(R.drawable.video_thumbnail_placeholder);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }


    @Override
    protected void onDestroy() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onPause();
    }


    @Override
    protected void onRestart() {
        //Toast.makeText(getApplicationContext(),"sdasdafsdfsdfsdfs",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, VideoViewActivity.class));
        super.onRestart();
    }

}