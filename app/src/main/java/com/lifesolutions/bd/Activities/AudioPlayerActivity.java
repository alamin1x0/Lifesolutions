package com.lifesolutions.bd.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.lifesolutions.bd.R;

public class AudioPlayerActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    TextView timer,duration;
    private String musicUrl;
    SeekBar seekBar;
    ImageButton playPause;
    ProgressDialog progressDialog;
    Toolbar toolbar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        timer = findViewById(R.id.timer_music_player_layout);
        seekBar = findViewById(R.id.seekbar_music_player_layout);
        playPause = findViewById(R.id.play_pause_button_music_player_layout);
        duration = findViewById(R.id.duration_music_player_layout);
        toolbar = findViewById(R.id.toolbar_music_player);

        setSupportActionBar(toolbar);
        setTitle("Playing voice message");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        seekBar.setMax(99);
        Intent myIntent = getIntent();



                if (myIntent != null) {
            musicUrl = myIntent.getStringExtra("musicUrl");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        mediaPlayer = new MediaPlayer();


        playPause.setOnClickListener(view1 -> {

            if (mediaPlayer.isPlaying()){

                handler.removeCallbacks(updater);
                mediaPlayer.pause();
                playPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);

            } else {
                mediaPlayer.start();
                playPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                updateSeekBar();
            }
        });

        prepareMediaPlayer();

        seekBar.setOnTouchListener((view, motionEvent) -> {
            SeekBar seekBar1 = (SeekBar)view;
            int position  = (mediaPlayer.getDuration()/100)*seekBar1.getProgress();
            mediaPlayer.seekTo(position);
            return false;
        });
    }

    private void prepareMediaPlayer(){

        try {

            mediaPlayer.setDataSource(musicUrl);
            mediaPlayer.prepare();
            duration.setText(milliSecondsToTime(mediaPlayer.getDuration()));
            mediaPlayer.start();
            playPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
            updateSeekBar();

        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Runnable updater  = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            timer.setText(milliSecondsToTime(currentDuration));
        }
    };

    private void updateSeekBar(){
        if (mediaPlayer.isPlaying()){
            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        finish();
        return true;
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        playPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
    }

    private String milliSecondsToTime(long milliseconds){
        String timerString = "",secondString;

        int hours = (int) (milliseconds / (1000 * 60 *60));
        int minutes  = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds  = (int) (milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000;


        if (hours > 0){
            timerString = hours + ":";
        }

        if (seconds < 10){
            secondString = "0" + seconds;
        } else {
            secondString = "" + seconds;
        }

        timerString = timerString + minutes + ":" + secondString;

        return timerString;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

}
