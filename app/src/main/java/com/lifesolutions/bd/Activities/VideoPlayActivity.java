package com.lifesolutions.bd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.lifesolutions.bd.R;

public class VideoPlayActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    TextView tvTitle,tvDescription;
    String videoId,description = " ",title = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        youTubePlayerView = findViewById(R.id.youtube_player_view);
        tvTitle = findViewById(R.id.title_video_play);
        tvDescription = findViewById(R.id.description_video_play);
        getLifecycle().addObserver(youTubePlayerView);

        Toast.makeText(this, "Please rotate your phone for full screen video", Toast.LENGTH_SHORT).show();

        Intent myIntent = getIntent();
        if (myIntent != null){
            videoId = myIntent.getStringExtra("videoId");
            description = myIntent.getStringExtra("description");
            title = myIntent.getStringExtra("title");
        }


        playVideo(videoId);

        tvTitle.setText(title);
        tvDescription.setText(description);
    }

    private void playVideo(String videoID) {
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoID, 0);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }

}
