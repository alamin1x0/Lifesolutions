package com.lifesolutions.bd.Activities;

import android.annotation.SuppressLint;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinch.android.rtc.AudioController;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallEndCause;
import com.sinch.android.rtc.calling.CallListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Helpers.RandomCallCheck;
import com.lifesolutions.bd.Helpers.RingtonePlayer;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.Services.SinchService;
import com.lifesolutions.bd.kotlinCode.data.model.CallHistory;
import com.lifesolutions.bd.kotlinCode.services.OngoingAlertService;
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity;


import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class CallScreenActivity extends BaseActivity implements SensorEventListener {


    private static final String TAG = "CallScreenActivity";
    static final String CALL_START_TIME = "callStartTime";
    static final String ADDED_LISTENER = "addedListener";
    static final String KEY_CALL_ID = "CALL_ID_DATA";
    static final String KEY_CALL_START_TIME = "CALL_START_TIME";
    private RingtonePlayer mAudioPlayer;
    private Timer mTimer;
    private UpdateCallDurationTask mDurationTask;
    private String mCallId, fullNameOfCaller;
    private long mCallStart = 0;
    private boolean mAddedListener = false;
    private SensorManager mSensorManager;
    private Sensor mProximity;
    private PowerManager mPowerManager;
    private PowerManager.WakeLock mWakeLock;
    private ImageButton btnEndCall, btnSpeaker, btnMute;
    CircleImageView profileImage;
    ImageView mainCallSentBgProfile, backButtonCallScreen;
    TextView tvName, tvStatus, tvTimer, tvCallHeading;
    private Boolean isMuted = false, speaker = false;
    String uID;
    private int count = 0, points = 0, minute = 0;
    RelativeLayout rootLayout;
    private Dialog dialog;
    private Boolean isGifted = false;

    //Admob ADS
    AdView mAdView;
    private InterstitialAd mInterstitialAd;




    //facebook ads
/*
    com.facebook.ads.AdView fbAdview;
    com.facebook.ads.InterstitialAd fbInterstitialAds;
*/


    // Layout
    private ImageView fixedCallBgColor;
    private LinearLayout layoutRandomCallTopView, layoutCallTopView;
    private TextView textViewOnRandomCallConnect;
    private LottieAnimationView lottieAnimationUser;

    // Shared Pref
    private static boolean activeActivity = false;
    private Boolean isCallConnected = false;
    SharedPreferences userPreferences;


    private class UpdateCallDurationTask extends TimerTask {

        @Override
        public void run() {
            CallScreenActivity.this.runOnUiThread(CallScreenActivity.this::updateCallDuration);
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

    private void saveCallIdOnShared() {
        SharedPreferences prefs = getSharedPreferences("CallScreenActivity.Starnote", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_CALL_ID, mCallId);
        editor.apply();
    }

    private void saveCallStartTimeOnShared() {
        SharedPreferences prefs = getSharedPreferences("CallScreenActivity.Starnote", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Long startTime = prefs.getLong(KEY_CALL_START_TIME, 0);
        if (startTime == 0) {
            // editor.putLong(KEY_CALL_START_TIME, mCallStart);
            editor.putLong(KEY_CALL_START_TIME, System.currentTimeMillis());
        }
        isCallConnected = true;
        editor.apply();
    }


    private String getSaveData() {
        SharedPreferences prefs = getSharedPreferences("CallScreenActivity.Starnote", MODE_PRIVATE);
        return prefs.getString(KEY_CALL_ID, "none");
    }

    private Long getSaveCallTimeData() {
        SharedPreferences prefs = getSharedPreferences("CallScreenActivity.Starnote", MODE_PRIVATE);
        return prefs.getLong(KEY_CALL_START_TIME, 0);
    }

    private void removeSavedCallData() {
        SharedPreferences.Editor editor = getSharedPreferences("CallScreenActivity.Starnote", MODE_PRIVATE).edit();
        editor.clear().apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        AdRequest adRequest = new AdRequest.Builder().build();


//        String d = getIntent().getExtras().getString("USER_CALL_ID", "null");
//        Toast.makeText(this, "onCreate(TOP) ->" + d + " --> " + savedCallId, Toast.LENGTH_SHORT).show();




        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE);


        // SAZIB..
        fixedCallBgColor = findViewById(R.id.fixed_bg_color);
        layoutCallTopView = findViewById(R.id.layout_call_top_text_view);
        layoutRandomCallTopView = findViewById(R.id.layout_random_call_top_text_view);
        textViewOnRandomCallConnect = findViewById(R.id.tv_on_random_call_connect);
        lottieAnimationUser = findViewById(R.id.lottie_animation_user);
        mainCallSentBgProfile = findViewById(R.id.main_call_sent_bg_profile);
        backButtonCallScreen = findViewById(R.id.back_button_call_screen);

        profileImage = findViewById(R.id.profile_image_call_screen);
        tvName = findViewById(R.id.name_call_screen);
        tvStatus = findViewById(R.id.status_call_screen);
        tvTimer = findViewById(R.id.timer_call_screen);
        btnEndCall = findViewById(R.id.decline_call_screen);
        btnSpeaker = findViewById(R.id.loud_call_screen);
        btnMute = findViewById(R.id.mute_call_screen);
        tvCallHeading = findViewById(R.id.call_heading_call_screen);
        rootLayout = findViewById(R.id.root_layout_call_screen);


        tvTimer.setVisibility(View.GONE);
        dialog = new Dialog(CallScreenActivity.this);
        dialog.setContentView(R.layout.dialog_bonus_layout);
        dialog.setCancelable(false);

        mAudioPlayer = new RingtonePlayer(this);

        setupProximitySensor();

        btnEndCall.setOnClickListener(v -> {
            endCall();
        });




        // mCallId = getIntent().getStringExtra(SinchService.CALL_ID);
        if (getIntent().getStringExtra(SinchService.CALL_ID) == null) {
            mCallId = getSaveData();
            mCallStart = getSaveCallTimeData();
            tvStatus.setText("Connected");
            tvTimer.setVisibility(View.VISIBLE);
        } else {
            mCallId = getIntent().getStringExtra(SinchService.CALL_ID);
            // Toast.makeText(this, "onCreate(ELSE) ->" + mCallId, Toast.LENGTH_SHORT).show();
        }

        if (savedInstanceState == null) {
            // mCallStart = System.currentTimeMillis();
        }


        //===============================================ADMOB INIT============================================

        bannerAd_Admob();

        InterstitialAd.load(this, getString(R.string.admob_interstitia_ads_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAGS", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("TAGS", loadAdError.getMessage() + "  " + loadAdError.getResponseInfo().toString());
                        mInterstitialAd = null;
                    }
                });

/*        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when fullscreen content is dismissed.
                Log.d("TAG", "The ad was dismissed.");
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when fullscreen content failed to show.
                Log.d("TAG", "The ad failed to show.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when fullscreen content is shown.
                // Make sure to set your reference to null so you don't
                // show it a second time.
                mInterstitialAd = null;
                Log.d("TAG", "The ad was shown.");
            }
        });*/
        //===============================================ADMOB INIT============================================


        //===============================================FB INTERSTITIAL AD============================================
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
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage() + adError.getErrorCode());
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
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());*/


        //===============================================FB Banner AD============================================



/*
        fbAdview = new AdView(this, "CAROUSEL_IMG_SQUARE_LINK#507447880502563_507476950499656", AdSize.BANNER_HEIGHT_50);

// Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.call_screen_facebook_banner_container);

// Add the ad view to your activity layout
        adContainer.addView(fbAdview);

// Request an ad
        fbAdview.loadAd();*/


        //==============Facebook Ads===================================



        //===============================================FB Banner AD============================================


        btnSpeaker.setOnClickListener(view -> {

            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            AudioController audioController = getSinchServiceInterface().getAudioController();

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


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.values[0] < event.sensor.getMaximumRange() /*face near phone*/) {

            if (!mWakeLock.isHeld()) {
                mWakeLock.acquire();
            }
        } else {
            if (mWakeLock.isHeld()) {
                mWakeLock.release();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void setupProximitySensor() {

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (mProximity != null) {
            mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                    mPowerManager.isPowerSaveMode()) {
                // Toast.makeText(CallScreenActivity.this, "If you experience any problems in the call, turn off device power saving mode and try again", Toast.LENGTH_LONG).show();
            }
            int field = 0x00000020;
            try {
                // Yeah, this is hidden field.
                field = PowerManager.class.getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").getInt(null);
            } catch (Throwable ignored) {
            }
            mWakeLock = mPowerManager.newWakeLock(field, getLocalClassName());
        }
    }

    @Override
    public void onServiceConnected() {
        Call call = getSinchServiceInterface().getCall(mCallId);
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

    private void startNotificationService() {
        //stopService(new Intent(this, OngoingAlertService.class));
        Intent serviceIntent = new Intent(this, OngoingAlertService.class);
        serviceIntent.putExtra(SinchService.CALL_ID, mCallId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
    }

    private void stopNotificationService() {
        Intent serviceIntent = new Intent(this, OngoingAlertService.class);
        stopService(serviceIntent);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(CallScreenActivity.this, TempService.class));
        } else startService(new Intent(CallScreenActivity.this, TempService.class));*/
    }

    //method to update video feeds in the UI

    private void updateUI() {
        if (getSinchServiceInterface() == null) {
            return;
        }

        Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            // Toast.makeText(this, call.getCallId(), Toast.LENGTH_SHORT).show();
            if (RandomCallCheck.getCallRunning()) {
                backButtonCallScreen.setVisibility(View.GONE);
                layoutCallTopView.setVisibility(View.GONE);
                fixedCallBgColor.setVisibility(View.GONE);
                mainCallSentBgProfile.setVisibility(View.GONE);
                profileImage.setVisibility(View.GONE);
                tvName.setVisibility(View.GONE);
                layoutRandomCallTopView.setVisibility(View.VISIBLE);
                lottieAnimationUser.setVisibility(View.VISIBLE);

                profileImage.setImageResource(R.drawable.user);
                rootLayout.setBackground(ContextCompat.getDrawable(CallScreenActivity.this, R.drawable.random_call_gradient_bg));
                saveCallIdOnShared();
                // rootLayout.setAlpha(1.0f);

            } else {
//                startNotificationService();
                saveCallIdOnShared();
                backButtonCallScreen.setVisibility(View.VISIBLE);
                layoutRandomCallTopView.setVisibility(View.GONE);
                lottieAnimationUser.setVisibility(View.GONE);

                getCallerInfo(tvName, profileImage, mainCallSentBgProfile, call.getRemoteUserId());
            }

        }
    }

    private void getCallerInfo(TextView callerName, CircleImageView callerImage, ImageView callBgProfile, String callerId) {
        DatabaseReference callerReference = FirebaseDatabase.getInstance().getReference("Users").child(callerId);
        callerReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fullNameOfCaller = dataSnapshot.child("firstName").getValue(String.class) + " " + dataSnapshot.child("lastName").getValue(String.class);
                callerName.setText(fullNameOfCaller);
                if (!dataSnapshot.child("imageThumbnail").getValue(String.class).equals("none")) {
                    String imageUrl = dataSnapshot.child("imageThumbnail").getValue(String.class);
                    Picasso.get().load(imageUrl).into(callerImage);
                    Picasso.get().load(imageUrl).into(callBgProfile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //stop the timer when call is ended

    @Override
    public void onStop() {
        super.onStop();
        activeActivity = false;
        mDurationTask.cancel();
        mTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show();
    }

    //start the timer for the call duration here

    @Override
    public void onStart() {
        super.onStart();
        activeActivity = true;
        mTimer = new Timer();
        mDurationTask = new UpdateCallDurationTask();
        mTimer.schedule(mDurationTask, 0, 500);
        updateUI();
        // Toast.makeText(this, "OnStart() ->" + mCallId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mProximity != null) {
            mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onBackPressed() {
        // User should exit activity by ending call, not by going back.
        navigateToHome();
    }

    // On Back Pressed

    public void onBackBtnClick(View view) {
        navigateToHome();
    }

    private void navigateToHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProximity != null) {
            if (mWakeLock.isHeld()) {

                mWakeLock.release();
            }
        }
        // mSensorManager.unregisterListener(this);

        // Toast.makeText(this, "onDestroy() ->", Toast.LENGTH_SHORT).show();
    }

    //method to end the call

    private void endCall() {
        Toast.makeText(getApplicationContext(), "End Called", Toast.LENGTH_SHORT).show();

        Log.w("endCall", "END CALL");

        minute = (int) TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - mCallStart);
        long sec = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - mCallStart);

        if (RandomCallCheck.getCallRunning()) {
            if (isCallConnected) {
                setUserCallHistory(sec, "randomCall");

            }
            if (minute > 0 && minute < 100) {
                Log.w(TAG, "minute > 0");
               // giveRandomCallBonus(2);
                finish();

            } else {
                Log.w(TAG, "MINUTE IS LESS THAN 0 MIN");
                RandomCallCheck.setCallRunning(false);

           /*     if (fbInterstitialAds.isAdInvalidated()) {
                    fbInterstitialAds.loadAd();
                    Log.w(TAG, "mInterstitialAd LOADED");
                } else {
                    Log.w(TAG, "FINISH ACTIVITY ON RandomCallCheck RUNNING");
                    finish();
                }*/
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(CallScreenActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    Log.w(TAG, "FINISH ACTIVITY ON RandomCallCheck RUNNING");
                    finish();
                }





        /*        if (interstitialAd.isAdInvalidated()) {
                    interstitialAd.loadAd();
                    Log.w(TAG, "mInterstitialAd LOADED");
                } else {
                    Log.w(TAG, "FINISH ACTIVITY ON RandomCallCheck RUNNING");
                    finish();
                }*/
                // finish();

            }
        } else {
            RandomCallCheck.setCallRunning(false);
            if (isCallConnected) {
                setUserCallHistory(sec, "audioCall");
            }
            // createCallLogConversation("voice", sec);


            //Facebook ADS

                 /*   if (fbInterstitialAds.isAdInvalidated()) {
                        fbInterstitialAds.loadAd();
                        Log.w(TAG, "RandomCallCheck NOT RUNNING mInterstitialAd LOADED");
                    } else {
                        Log.w(TAG, "FINISH ACTIVITY ON RandomCallCheck NOT RUNNING");
                        finish();
                    }
*/

            if (mInterstitialAd != null) {
                mInterstitialAd.show(CallScreenActivity.this);
                Log.w(TAG, "RandomCallCheck NOT RUNNING mInterstitialAd LOADED");
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                Log.w(TAG, "FINISH ACTIVITY ON RandomCallCheck NOT RUNNING");
                finish();
            }


            finish();

        }

        removeSavedCallData();
        stopNotificationService();
        mAudioPlayer.stopProgressTone();
        Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            Log.w(TAG, "CALL IS NOT NULL AND HANGUP");
            call.hangup();
        }
        if (mProximity != null) {
            Log.w(TAG, "PROXIMITY IS NOT NULL AND UNREGISTERED");
            mSensorManager.unregisterListener(this);
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

    //removes video feeds from the app once the call is terminate
    private class SinchCallListener implements CallListener {

        @Override
        public void onCallEnded(Call call) {
            CallEndCause cause = call.getDetails().getEndCause();
            // Toast.makeText(CallScreenActivity.this, cause.toString(), Toast.LENGTH_SHORT).show();
            if (cause.toString().equals("TIMEOUT"))
                Toast.makeText(CallScreenActivity.this, "This User Is Offline", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(CallScreenActivity.this, "" + cause.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Call ended. Reason: " + cause.toString());
            mAudioPlayer.stopProgressTone();
            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
            String endMsg = "Call ended: " + call.getDetails().toString();
            if (mProximity != null) {
                mSensorManager.unregisterListener(CallScreenActivity.this);
            }
            getAllItemInDefault();
            endCall();
        }

        @Override
        public void onCallEstablished(Call call) {
            Log.d(TAG, "Call established");
            // Toast.makeText(CallScreenActivity.this, "onCallEstablished()", Toast.LENGTH_SHORT).show();
            mAudioPlayer.stopProgressTone();
            if (RandomCallCheck.getCallRunning()) {
                textViewOnRandomCallConnect.setText("Connected");
                startNotificationService();
                saveCallStartTimeOnShared();
                // tvName.setVisibility(View.GONE);
                // tvName.setText("Connected with your partner");
            } else {
                Log.w(TAG, "onCallEstablished: ------->>>>> Iam on Call Establish ELSE");
                saveCallStartTimeOnShared();
                startNotificationService();
            }

            if (call.getState().toString().equals("ESTABLISHED")) {
                isCallConnected = true;
                tvStatus.setText("Connected");
            } else {
                tvStatus.setText(call.getState().toString().toLowerCase());
            }
            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            tvTimer.setVisibility(View.VISIBLE);
            mCallStart = System.currentTimeMillis();
            //mInterstitialAd.(new AdRequest.Builder().build());
        }

        @Override
        public void onCallProgressing(Call call) {
            Log.d(TAG, "Call progressing");
            tvStatus.setText(call.getState().toString().toLowerCase());
            mAudioPlayer.playProgressTone();
        }

        @Override
        public void onShouldSendPushNotification(Call call, List pushPairs) {
            // Send a push through your push provider here, e.g. GCM
        }

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

    private void setUserCallHistory(long minute, String callChild) {
        Log.w(TAG, "setUserCallHistory() ........... " + isCallConnected);
        String authId = userPreferences.getString("uID", null);
        if (authId != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UsersCallData").child(authId).child(callChild);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        CallHistory history = dataSnapshot.getValue(CallHistory.class);

                        long oldDuration = history.getDuration();
                        Log.w(TAG, "Old Duration" + oldDuration);
                        Log.w(TAG, "New Duration" + minute);
                        CallHistory newData = new CallHistory(oldDuration + minute, System.currentTimeMillis());
                        FirebaseDatabase.getInstance().getReference("UsersCallData").child(authId).child("lastCallDuratiuon").setValue(minute);
                        ref.setValue(newData);

                    } else {
                        CallHistory newData = new CallHistory(minute, System.currentTimeMillis());
                        FirebaseDatabase.getInstance().getReference("UsersCallData").child(authId).child("lastCallDuratiuon").setValue(minute);
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


    @SuppressLint("SetTextI18n")
    private void showBonusDialog(int minutes) {

        Log.w(TAG, "showBonusDialog: --->>>>>>>>>> Iam Here -->>>>>>>> Minuties is = " + minutes);
        uID = FirebaseAuth.getInstance().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uID);
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.animation_dialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView collect = dialog.findViewById(R.id.refresh_dialog_bonus);
        TextView description = dialog.findViewById(R.id.description_dialog_bonus);
        description.setText("You got " + minutes + " points");
        collect.setOnClickListener(v -> {
            isGifted = true;
            dialog.dismiss();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (count == 0) {
                        points = dataSnapshot.child("points").getValue(Integer.class);
                        points += minutes;
                        reference.child("points").setValue(points).addOnSuccessListener(aVoid -> {
                            RandomCallCheck.setCallRunning(false);
                          /*  if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                dialog.dismiss();
                                finish();
                            }*/
                        /*    if (interstitialAd.isAdInvalidated()) {
                                interstitialAd.loadAd();
                            } else {
                                dialog.dismiss();
                                finish();
                            }*/


                        }).addOnFailureListener(e -> {
                            RandomCallCheck.setCallRunning(false);
                        /*    if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                dialog.dismiss();
                                finish();
                            }*/

                /*            if (interstitialAd.isAdInvalidated()) {
                                interstitialAd.loadAd();
                            } else {
                                dialog.dismiss();
                                finish();
                            }*/


                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(CallScreenActivity.this, "Something went wrong, Failed to sent bonus points", Toast.LENGTH_SHORT).show();
                }
            });
        });

        if (!isGifted) {
            dialog.show();
        }

    }


    private void giveRandomCallBonus(int minutes) {

        Log.w(TAG, "showBonusDialog: --->>>>>>>>>> Iam Here -->>>>>>>> Minuties is = " + minutes);
        uID = FirebaseAuth.getInstance().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uID);
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.animation_dialog;

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (count == 0) {
                    points = dataSnapshot.child("points").getValue(Integer.class);
                    points += minutes;
                    reference.child("points").setValue(points).addOnSuccessListener(aVoid -> {
                        RandomCallCheck.setCallRunning(false);




                    /*    if (interstitialAd.isAdInvalidated()) {
                            interstitialAd.loadAd();
                        } else {
                            Toast.makeText(CallScreenActivity.this, "You got " + minutes + " bonus points", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            finish();
                        }*/


                    }).addOnFailureListener(e -> {
                        RandomCallCheck.setCallRunning(false);

                     /*   if (mInterstitialAd != null) {
                            mInterstitialAd.show();
                        } else {
                            dialog.dismiss();
                            finish();
                        }*/
                   /*     if (interstitialAd.isAdInvalidated()) {
                            interstitialAd.loadAd();
                        } else {
                            dialog.dismiss();
                            finish();
                        }*/
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(CallScreenActivity.this, "Something went wrong, Failed to sent bonus points", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void bannerAd_Admob() {


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }
}
