package com.lifesolutions.bd.Activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinch.android.rtc.AudioController;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallEndCause;
import com.sinch.android.rtc.calling.CallState;
import com.sinch.android.rtc.video.VideoCallListener;
import com.sinch.android.rtc.video.VideoController;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Helpers.RingtonePlayer;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.Services.SinchService;
import com.lifesolutions.bd.kotlinCode.data.model.CallHistory;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoCallScreenActivity extends BaseActivity {

    static final String TAG = VideoCallScreenActivity.class.getSimpleName();
    static final String CALL_START_TIME = "callStartTime";
    static final String ADDED_LISTENER = "addedListener";

    private RingtonePlayer mAudioPlayer;
    private Timer mTimer;
    private VideoCallScreenActivity.UpdateCallDurationTask mDurationTask;

    private String mCallId;
    private long mCallStart = 0;
    private boolean mAddedListener = false;
    private boolean mVideoViewsAdded = false;
    ImageButton btnEndCall, btnSpeaker, btnMute;
    TextView tvName, tvStatus, tvTimer, tvNameBig;
    private Boolean isMuted = false, speaker = true;
    private CircleImageView profileImage;
    LinearLayout userDetailsLayout;
    private PowerManager mPowerManager;
    private PowerManager.WakeLock mWakeLock;
    AudioController audioController;

    AudioManager audioManager;



    // Shared Pref
    private Boolean isCallConnected = false;
    SharedPreferences userPreferences;


    //Admob
       InterstitialAd mInterstitialAd;


    private class UpdateCallDurationTask extends TimerTask {

        @Override
        public void run() {
            VideoCallScreenActivity.this.runOnUiThread(() -> updateCallDuration());
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong(CALL_START_TIME, mCallStart);
        savedInstanceState.putBoolean(ADDED_LISTENER, mAddedListener);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mCallStart = savedInstanceState.getLong(CALL_START_TIME);
        mAddedListener = savedInstanceState.getBoolean(ADDED_LISTENER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE);


        tvName = findViewById(R.id.remoteUser);
        tvStatus = findViewById(R.id.callState_video);
        tvTimer = findViewById(R.id.callDuration);
        btnEndCall = findViewById(R.id.decline_video_call_screen);
        btnSpeaker = findViewById(R.id.loud_video_call_screen);
        btnMute = findViewById(R.id.mute_video_call_screen);
        tvNameBig = findViewById(R.id.name_video_call_screen);
        profileImage = findViewById(R.id.profile_image_video_call_screen);
        userDetailsLayout = findViewById(R.id.user_details_layout_video_call_screen);
        tvTimer.setVisibility(View.GONE);


        mAudioPlayer = new RingtonePlayer(this);

        btnEndCall.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              VideoCallScreenActivity.this.endCall();
                                              if (mInterstitialAd != null) {
                                                  mInterstitialAd.show(VideoCallScreenActivity.this);
                                              } else {
                                                  Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                              }
                                          }
                                      }


        );

        mCallId = getIntent().getStringExtra(SinchService.CALL_ID);
        if (savedInstanceState == null) {
            mCallStart = System.currentTimeMillis();
        }
 /*       AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioController = getSinchServiceInterface().getAudioController();
        audioController.disableSpeaker();
*/

//for headphone on
         audioManager =  (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        //========================Admob Ads =============================


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.admob_interstitia_ads_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

        //========================Admob Ads =============================


        //========================Facebook Ads =============================


       /* interstitialAd = new InterstitialAd(getApplicationContext(), "283994403477081_284100626799792");


        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("TAG", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("TAG", "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAG", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("TAG", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("TAG", "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());*/


        //========================Facebook Ads =============================


        btnSpeaker.setOnClickListener(view -> {

            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            audioController = getSinchServiceInterface().getAudioController();

            if (speaker) {

                audioController.disableSpeaker();
                btnSpeaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker_call_white));
                speaker = false;

            } else {

                audioController.enableSpeaker();
                btnSpeaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker_call_red));
                speaker = true;
            }
        });

        btnMute.setOnClickListener(view -> {

            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            AudioController audioController = getSinchServiceInterface().getAudioController();

            if (!isMuted) {
                btnMute.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_call_red));
                audioController.mute();
                isMuted = true;

            } else {
                btnMute.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_call_white));
                audioController.unmute();
                isMuted = false;
            }
        });


        startCamera();

    }


    @Override
    public void onServiceConnected() {
        com.sinch.android.rtc.calling.Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            if (!mAddedListener) {
                call.addCallListener(new SinchCallListener());
                mAddedListener = true;
            }
        } else {
            Log.e(TAG, "Started with invalid callId, aborting.");
            finish();
        }


        updateUI();
    }

    //method to update video feeds in the UI
    private void updateUI() {
        if (getSinchServiceInterface() == null) {
            return; // early
        }

        com.sinch.android.rtc.calling.Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            getCallerInfo(tvName, call.getRemoteUserId(), tvNameBig, profileImage);
            tvStatus.setText(call.getState().toString());
            if (call.getState() == CallState.ESTABLISHED) {
                //when the call is established, addVideoViews configures the video to  be shown
                userDetailsLayout.setVisibility(View.GONE);
                addVideoViews();
            }
        }
    }

    //stop the timer when call is ended
    @Override
    public void onStop() {
        super.onStop();
        mDurationTask.cancel();
        mTimer.cancel();
        removeVideoViews();
    }

    //start the timer for the call duration here
    @Override
    public void onStart() {
        super.onStart();
        mTimer = new Timer();
        mDurationTask = new UpdateCallDurationTask();
        mTimer.schedule(mDurationTask, 0, 500);
        updateUI();
    }

    @Override
    public void onBackPressed() {
        // User should exit activity by ending call, not by going back.
    }

    //method to end the call
    private void endCall() {
        long sec = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - mCallStart);
        mAudioPlayer.stopProgressTone();
        com.sinch.android.rtc.calling.Call call = getSinchServiceInterface().getCall(mCallId);
        if (isCallConnected) {
            setUserCallHistory(sec, "videoCall");
        }
        if (call != null) {
            call.hangup();
        }



        finish();

    }

    private String formatTimespan(long timespan) {
        long totalSeconds = timespan / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format(Locale.US, "%02d:%02d", minutes, seconds);
    }

    //method to update live duration of the call
    private void updateCallDuration() {
        if (mCallStart > 0) {
            tvTimer.setText(formatTimespan(System.currentTimeMillis() - mCallStart));
        }
    }

    //method which sets up the video feeds from the server to the UI of the activity
    private void addVideoViews() {
        if (mVideoViewsAdded || getSinchServiceInterface() == null) {
            return; //early
        }

        final VideoController vc = getSinchServiceInterface().getVideoController();
        if (vc != null) {
            userDetailsLayout.setVisibility(View.GONE);
//            RelativeLayout localView = findViewById(R.id.localVideo);
//            localView.addView(vc.getLocalView());
//
//            localView.setOnClickListener(v -> {
//                //this toggles the front camera to rear camera and vice versa
//                vc.toggleCaptureDevicePosition();
//            });

            LinearLayout view = findViewById(R.id.remoteVideo);
            view.addView(vc.getRemoteView());
            mVideoViewsAdded = true;
        }
    }

    //removes video feeds from the app once the call is terminated
    private void removeVideoViews() {
        if (getSinchServiceInterface() == null) {
            return; // early
        }

        VideoController vc = getSinchServiceInterface().getVideoController();
        if (vc != null) {
            userDetailsLayout.setVisibility(View.GONE);
            LinearLayout view = (LinearLayout) findViewById(R.id.remoteVideo);
            view.removeView(vc.getRemoteView());

            RelativeLayout localView = (RelativeLayout) findViewById(R.id.localVideo);
            localView.removeView(vc.getLocalView());
            mVideoViewsAdded = false;
        }
    }

    private class SinchCallListener implements VideoCallListener {

        @Override
        public void onCallEnded(com.sinch.android.rtc.calling.Call call) {
            CallEndCause cause = call.getDetails().getEndCause();
            Log.d(TAG, "Call ended. Reason: " + cause.toString());
            mAudioPlayer.stopProgressTone();
            getAllItemInDefault();
            String endMsg = "Call ended: " + call.getDetails().toString();
            Toast.makeText(VideoCallScreenActivity.this, cause.toString(), Toast.LENGTH_LONG).show();

            endCall();
        }

        @Override
        public void onCallEstablished(com.sinch.android.rtc.calling.Call call) {
            Log.d(TAG, "Call established");

            Log.d(TAG, "Call offered video: " + call.getDetails().isVideoOffered());

            if (audioManager.isWiredHeadsetOn()) {
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                audioManager.setSpeakerphoneOn(false);

            }else {
                audioManager.setSpeakerphoneOn(true);
                isCallConnected = true;
                mAudioPlayer.stopProgressTone();
                userDetailsLayout.setVisibility(View.GONE);
                tvStatus.setText(call.getState().toString());
                tvTimer.setVisibility(View.VISIBLE);
                mCallStart = System.currentTimeMillis();
                setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
                AudioController audioController = getSinchServiceInterface().getAudioController();
                audioController.enableSpeaker();
                speaker = true;
                btnSpeaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker_call_red));
            }
        }

        @Override
        public void onCallProgressing(com.sinch.android.rtc.calling.Call call) {
            Log.d(TAG, "Call progressing");
            mAudioPlayer.playProgressTone();
        }

        @Override
        public void onShouldSendPushNotification(com.sinch.android.rtc.calling.Call call, List pushPairs) {
            // Send a push through your push provider here, e.g. GCM
        }

        @Override
        public void onVideoTrackAdded(com.sinch.android.rtc.calling.Call call) {
            Log.d(TAG, "Video track added");
            addVideoViews();
        }

        @Override
        public void onVideoTrackPaused(com.sinch.android.rtc.calling.Call call) {

        }

        @Override
        public void onVideoTrackResumed(Call call) {

        }
    }

    private void getCallerInfo(TextView callerName, String callerId, TextView nameBig, CircleImageView profileImage) {
        DatabaseReference callerReference = FirebaseDatabase.getInstance().getReference("Users").child(callerId);
        callerReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                callerName.setText(dataSnapshot.child("firstName").getValue(String.class) + " " + dataSnapshot.child("lastName").getValue(String.class));
                nameBig.setText(dataSnapshot.child("firstName").getValue(String.class) + " " + dataSnapshot.child("lastName").getValue(String.class));
                if (!dataSnapshot.child("imageThumbnail").getValue(String.class).equals("none")) {
                    Picasso.get().load(dataSnapshot.child("imageThumbnail").getValue(String.class)).into(profileImage);
                } else {
                    profileImage.setImageResource(R.drawable.user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getAllItemInDefault() {

        setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        AudioController audioController = getSinchServiceInterface().getAudioController();
        audioController.disableSpeaker();

        btnSpeaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker_call_white));
        speaker = false;

        btnMute.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_call_white));
        audioController.unmute();
        isMuted = false;

        setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * Camera Preview
     */

    private void startCamera() {
//        if (mVideoViewsAdded || getSinchServiceInterface() == null) {
//            return; //early
//        }

        final VideoController vc = getSinchServiceInterface().getVideoController();
        if (vc != null) {
            // userDetailsLayout.setVisibility(View.GONE);
            RelativeLayout localView = findViewById(R.id.localVideo);
            localView.addView(vc.getLocalView());

            localView.setOnClickListener(v -> {
                //this toggles the front camera to rear camera and vice versa
                vc.toggleCaptureDevicePosition();
            });

//            LinearLayout view = findViewById(R.id.remoteVideo);
//            view.addView(vc.getRemoteView());
//            mVideoViewsAdded = true;
        }
    }


    private void setUserCallHistory(long minute, String callChild) {
        String authId = userPreferences.getString("uID", null);
        if (authId != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UsersCallData").child(authId).child(callChild);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        CallHistory history = dataSnapshot.getValue(CallHistory.class);

                        long oldDuration = history.getDuration();
                        CallHistory newData = new CallHistory(oldDuration + minute, System.currentTimeMillis());
                        ref.setValue(newData);

                    } else {
                        CallHistory newData = new CallHistory(minute, System.currentTimeMillis());
                        ref.setValue(newData);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Toast.makeText(this, "Something Went wrong with call history", Toast.LENGTH_SHORT).show();
        }
    }


}
