package com.lifesolutions.bd.kotlinCode.ui.activities;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bambuser.broadcaster.BroadcastPlayer;
import com.bambuser.broadcaster.PlayerState;
import com.bambuser.broadcaster.SurfaceViewWithAutoAR;
import com.lifesolutions.bd.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewStream extends AppCompatActivity {

    // private static final String APPLICATION_ID = "eEIk69e5p4h7VA7Kbn4J2w";
    private static final String APPLICATION_ID = "aCJqRXQ4xqSNUfjvPhi8Ew";
    //  private static final String API_KEY = "Vav7EUL4mz1FzPYgxXHwiR";
    private static final String API_KEY = "5UiAhEnzXShcgeK8JdH5a8";
    int number ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stream);
        mDefaultDisplay = getWindowManager().getDefaultDisplay();
        mVideoSurface = findViewById(R.id.VideoSurfaceView);
        mPlayerStatusTextView = findViewById(R.id.PlayerStatusTextView);
        mPlayerContentView = findViewById(R.id.PlayerContentView);
        // next_video = findViewById(R.id.next_live_video);
        //  prev_video = findViewById(R.id.prev_live_video);
        number = getIntent().getIntExtra("index",0);
//        next_video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getApplicationContext(),number,Toast.LENGTH_SHORT).show();
//                getLatestResourceUri(number);
//                number++;
//            }
//        });


//        prev_video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                number--;
//                getLatestResourceUri(number);
//              //  Toast.makeText(getApplicationContext(),number,Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        mOkHttpClient.dispatcher().cancelAll();
        mVideoSurface = null;
        if (mBroadcastPlayer != null)
            mBroadcastPlayer.close();
        mBroadcastPlayer = null;
        if (mMediaController != null)
            mMediaController.hide();
        mMediaController = null;

    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoSurface = findViewById(R.id.VideoSurfaceView);
        mPlayerStatusTextView.setText("Loading latest broadcast");
        getLatestResourceUri(number);
        number++;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_UP && mBroadcastPlayer != null && mMediaController != null) {
            PlayerState state = mBroadcastPlayer.getState();
            if (state == PlayerState.PLAYING ||
                    state == PlayerState.BUFFERING ||
                    state == PlayerState.PAUSED ||
                    state == PlayerState.COMPLETED) {
                if (mMediaController.isShowing())
                    mMediaController.hide();
                else
                    mMediaController.show();
            } else {
                mMediaController.hide();
            }
        }
        return false;
    }

    void getLatestResourceUri(int a) {
        Request request = new Request.Builder()
                .url("https://api.bambuser.com/broadcasts")
                .addHeader("Accept", "application/vnd.bambuser.v1+json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .get()
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mPlayerStatusTextView != null)
                            mPlayerStatusTextView.setText("Http exception: " + e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                String body = response.body().string();
                String resourceUri = null;
                try {
                    JSONObject json = new JSONObject(body);
                    JSONArray results = json.getJSONArray("results");
                    JSONObject latestBroadcast = results.optJSONObject(a);
                    resourceUri = latestBroadcast.optString("resourceUri");
                } catch (Exception ignored) {
                }
                final String uri = resourceUri;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initPlayer(uri);
                    }
                });
            }
        });
    }

    void initPlayer(String resourceUri) {
        if (resourceUri == null) {
            if (mPlayerStatusTextView != null)
                mPlayerStatusTextView.setText("Could not get info about latest broadcast");
            return;
        }
        if (mVideoSurface == null) {
            // UI no longer active
            return;
        }
        if (mBroadcastPlayer != null)
            mBroadcastPlayer.close();
        mBroadcastPlayer = new BroadcastPlayer(this, resourceUri, APPLICATION_ID, mBroadcastPlayerObserver);
        mBroadcastPlayer.setSurfaceView(mVideoSurface);
        mBroadcastPlayer.load();

    }

    BroadcastPlayer.Observer mBroadcastPlayerObserver = new BroadcastPlayer.Observer() {
        @Override
        public void onStateChange(PlayerState playerState) {
            if (mPlayerStatusTextView != null)
                mPlayerStatusTextView.setText("Status: " + playerState);
            if (playerState == PlayerState.PLAYING || playerState == PlayerState.PAUSED || playerState == PlayerState.COMPLETED) {
                if (mMediaController == null && mBroadcastPlayer != null && !mBroadcastPlayer.isTypeLive()) {
                    mMediaController = new MediaController(ViewStream.this);
                    mMediaController.setAnchorView(mPlayerContentView);
                    mMediaController.setMediaPlayer(mBroadcastPlayer);
                }
                if (mMediaController != null) {
                    mMediaController.setEnabled(true);
                    mMediaController.show();
                }
            } else if (playerState == PlayerState.ERROR || playerState == PlayerState.CLOSED) {
                if (mMediaController != null) {
                    mMediaController.setEnabled(false);
                    mMediaController.hide();
                }
                mMediaController = null;
            }
        }

        @Override
        public void onBroadcastLoaded(boolean live, int width, int height) {
            Point size = getScreenSize();
            float screenAR = size.x / (float) size.y;
            float videoAR = width / (float) height;
            float arDiff = screenAR - videoAR;
            mVideoSurface.setCropToParent(Math.abs(arDiff) < 0.2);

        }
    };

    private Point getScreenSize() {
        if (mDefaultDisplay == null)
            mDefaultDisplay = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        try {
            // this is officially supported since SDK 17 and said to work down to SDK 14 through reflection,
            // so it might be everything we need.
            mDefaultDisplay.getClass().getMethod("getRealSize", Point.class).invoke(mDefaultDisplay, size);
        } catch (Exception e) {
            // fallback to approximate size.
            mDefaultDisplay.getSize(size);
        }
        return size;
    }


    final OkHttpClient mOkHttpClient = new OkHttpClient();
    BroadcastPlayer mBroadcastPlayer;
    MediaController mMediaController = null;
    SurfaceViewWithAutoAR mVideoSurface;
    TextView mPlayerStatusTextView;
    View mPlayerContentView;
    Display mDefaultDisplay;
    Button next_video;
    Button prev_video;
}