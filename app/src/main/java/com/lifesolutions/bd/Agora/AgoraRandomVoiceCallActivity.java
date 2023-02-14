package com.lifesolutions.bd.Agora;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lifesolutions.bd.Api.ServerApi;
import com.lifesolutions.bd.Helpers.RandomCallCheck;
import com.lifesolutions.bd.Models.AgoraAccessToken;
import com.lifesolutions.bd.Models.AgoraSendUserDetails;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgoraRandomVoiceCallActivity extends AppCompatActivity {

    private static final String LOG_TAG = "agora";
    private boolean isConnected = false;

    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private ImageView declineImg;
    private ConstraintLayout mainLayout;
    private ConstraintLayout layoutCalling;
    private TextView tvTimerCount;
    private Timer mTimer;
    private long mCallStart = 0;
    private UpdateCallDurationTask mDurationTask;
    static final String KEY_CALL_START_TIME = "CALL_START_TIME";
    private Boolean isCallConnected = false;

    String uID;
    private int points=0;

    private ImageView muteImage;
    private ImageView LoudImage;
    private ImageView endCallImage;
    private Boolean isMuted = false, speaker = false;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference ref;
    private RtcEngine mRtcEngine;
    private String channelName = "";
    private String CHANNEL = "channel";
    private Map<String, Object> reg_entry = new HashMap<>();
    private final String user_id = FirebaseAuth.getInstance().getUid();
    private InterstitialAd mInterstitialAd;

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() { // Tutorial Step 1

        /**
         * Occurs when a remote user (Communication)/host (Live Broadcast) leaves the channel.
         *
         * There are two reasons for users to become offline:
         *
         *     Leave the channel: When the user/host leaves the channel, the user/host sends a goodbye message. When this message is received, the SDK determines that the user/host leaves the channel.
         *     Drop offline: When no data packet of the user or host is received for a certain period of time (20 seconds for the communication profile, and more for the live broadcast profile), the SDK assumes that the user/host drops offline. A poor network connection may lead to false detections, so we recommend using the Agora RTM SDK for reliable offline detection.
         *
         * @param uid ID of the user or host who
         * leaves
         * the channel or goes offline.
         * @param reason Reason why the user goes offline:
         *
         *     USER_OFFLINE_QUIT(0): The user left the current channel.
         *     USER_OFFLINE_DROPPED(1): The SDK timed out and the user dropped offline because no data packet was received within a certain period of time. If a user quits the call and the message is not passed to the SDK (due to an unreliable channel), the SDK assumes the user dropped offline.
         *     USER_OFFLINE_BECOME_AUDIENCE(2): (Live broadcast only.) The client role switched from the host to the audience.
         */
        @Override
        public void onUserOffline(final int uid, final int reason) { // Tutorial Step 4
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserLeft(uid, reason);
                }
            });
        }

        /**
         * Occurs when a remote user stops/resumes sending the audio stream.
         * The SDK triggers this callback when the remote user stops or resumes sending the audio stream by calling the muteLocalAudioStream method.
         *
         * @param uid ID of the remote user.
         * @param muted Whether the remote user's audio stream is muted/unmuted:
         *
         *     true: Muted.
         *     false: Unmuted.
         */
        @Override
        public void onUserMuteAudio(final int uid, final boolean muted) { // Tutorial Step 6
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserVoiceMuted(uid, muted);
                }
            });
        }

        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
            Log.d(LOG_TAG, "onJoinChannelSuccess: " + uid);
        }


        @Override
        public void onError(int err) {
            super.onError(err);
            Log.d(LOG_TAG, "err: " + err);
        }

        @Override
        public void onUserJoined(int uid, int elapsed) {
            super.onUserJoined(uid, elapsed);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    isConnected = true;
                    mCallStart = System.currentTimeMillis();
//                    saveCallStartTimeOnShared();
                    Log.d(LOG_TAG, "onUserJoined: " + uid);
                    layoutCalling.setVisibility(View.VISIBLE);
                    mainLayout.setVisibility(View.GONE);
                    endCall();
                    mTimer = new Timer();
                    mDurationTask = new UpdateCallDurationTask();
                    mTimer.schedule(mDurationTask, 0, 1000);
//                    tvTimerCount.setText(">>>RUNNING<<<<");
                }
            });

        }
    };

    @Override
    public void onStart() {
        super.onStart();
//        mTimer = new Timer();
//        mDurationTask = new UpdateCallDurationTask();
//        mTimer.schedule(mDurationTask, 0, 1000);
        // Toast.makeText(this, "OnStart() ->" + mCallId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d("OnResume", getSaveCallTimeData().toString());
//        mCallStart = getSaveCallTimeData();
//        mTimer = new Timer();
//        mDurationTask = new UpdateCallDurationTask();
//        mTimer.schedule(mDurationTask, 0, 1000);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop", "On stop Call");
//        removeSavedCallData();
//        if (mDurationTask != null) {
//            mDurationTask.cancel();
//            mTimer.cancel();
//        }
    }

    private String formatTimespan(long timespan) {
        long totalSeconds = timespan / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format(Locale.US, "%02d:%02d", minutes, seconds);
    }

    private void updateCallDuration() {
        if (mCallStart > 0) {
            tvTimerCount.setText(formatTimespan(System.currentTimeMillis() - mCallStart));
        }
    }

    private class UpdateCallDurationTask extends TimerTask {

        @Override
        public void run() {
            AgoraRandomVoiceCallActivity.this.runOnUiThread(AgoraRandomVoiceCallActivity.this::updateCallDuration);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_agora_random_voice_call);
        initComponents();
        layoutCalling.setVisibility(View.GONE);
        mCallStart = 0;


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, getString(R.string.admob_interstitia_ads_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAG", "onAdLoaded");
                       // Toast.makeText(getApplicationContext(), "Ads Ready!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("TAG", loadAdError.getMessage());
                       // Toast.makeText(getApplicationContext(), "Ads Failed to load!", Toast.LENGTH_SHORT).show();
                        mInterstitialAd = null;
                    }
                });


        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)) {
            //initAgoraEngineAndJoinChannel();
            scanChannel();
        }


    }

//    @Override
//    public boolean onKeyDown(int key_code, KeyEvent key_event) {
//        if (key_code== KeyEvent.KEYCODE_BACK) {
//            super.onKeyDown(key_code, key_event);
//            return true;
//        }
//        return false;
//    }

    @Override
    public void onBackPressed() {

        if (isConnected == true) {
            Toast.makeText(getApplicationContext(), "Can not back when call is connected", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
    }

    public void backToHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void scanChannel() {
        Log.d("SCAN_CH", "Scan Channel start--> " + channelName);

        FirebaseFirestore.getInstance().collection(CHANNEL).get().addOnFailureListener(runnable -> {
            Log.d("chk", "failed" + runnable.getMessage());
        }).addOnSuccessListener(runnable -> {
            List<DocumentSnapshot> documentReference = runnable.getDocuments();
            if (documentReference.size() > 0) {
                //retrieve channel name , delete and join channel
                channelName = documentReference.get(0).getId();
                Log.d("SCAN_CH_IF", " IF doc size > 0 " +  channelName);
                deleteChannel(channelName); //delete channel
                initAgoraEngineAndJoinChannel(channelName); //join channel

            } else {
                Log.d("SCAN_CH_ELSE", " Else " +  user_id);
                channelName = user_id;
                Map<String, String> channelMap = new HashMap<>();
                channelMap.put("user", user_id);
                FirebaseFirestore.getInstance().collection(CHANNEL).document(user_id).set(channelMap).addOnFailureListener(runnable1 -> {

                }).addOnSuccessListener(runnable1 -> {
                    //join channel
                    initAgoraEngineAndJoinChannel(user_id);
                });
            }
        });
    }

    /**
     * @param id to be delete channel id
     */
    private void deleteChannel(String id) {
        Log.d("DELETE_CH", "IAM HERE --> " + id);
        FirebaseFirestore.getInstance().collection(CHANNEL).document(id).delete().addOnSuccessListener(runnable ->
        {

        }).addOnFailureListener(runnable -> {

        });

    }

    private void initComponents() {

        tvTimerCount = findViewById(R.id.timer_call_screen);
        declineImg = findViewById(R.id.decline_random_call_kt);
        endCallImage = findViewById(R.id.decline_call_screen);

        declineImg.setOnClickListener(View -> {
            isConnected = false;
            deleteChannel(channelName);
            onBackPressed();
            if (mInterstitialAd != null) {
                mInterstitialAd.show(AgoraRandomVoiceCallActivity.this);
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }
        });
        endCallImage.setOnClickListener(View -> {

            isConnected = false;
            deleteChannel(channelName);
            onBackPressed();

            if (mInterstitialAd != null) {
                mInterstitialAd.show(AgoraRandomVoiceCallActivity.this);
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }

        });
        muteImage = findViewById(R.id.mute_call_screen);
        LoudImage = findViewById(R.id.loud_call_screen);
        layoutCalling = findViewById(R.id.layoutCallOnGoing);
        mainLayout = findViewById(R.id.layoutMain);


    }


    private void initAgoraEngineAndJoinChannel(String channelName) {
        Random r = new Random();
        int randomNumber = r.nextInt(1000);
        int uid = randomNumber;
        postData(channelName, uid);
    }

    private void postData(String name, int uid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.starnotesocial.com/api/v1/agora/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi retrofitAPI = retrofit.create(ServerApi.class);
        AgoraSendUserDetails modal = new AgoraSendUserDetails(name, uid);
        Call<AgoraAccessToken> call = retrofitAPI.postAccess(modal);
        call.enqueue(new Callback<AgoraAccessToken>() {
            @Override
            public void onResponse(Call<AgoraAccessToken> call, Response<AgoraAccessToken> response) {
                Log.d("access", "onResponse: " + response);
                String accessToken = response.body().getToken();
                if (response.body() != null) {
                    Log.d("access", "onResponse: " + response.body().getToken());
                    initializeAgoraEngine();     // Tutorial Step 1
                    joinChannel(accessToken, name, uid);
                }
            }

            @Override
            public void onFailure(Call<AgoraAccessToken> call, Throwable t) {

            }

        });
    }

    public boolean checkSelfPermission(String permission, int requestCode) {
        Log.i(LOG_TAG, "checkSelfPermission " + permission + " " + requestCode);
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        Log.i(LOG_TAG, "onRequestPermissionsResult " + grantResults[0] + " " + requestCode);

        switch (requestCode) {
            case PERMISSION_REQ_ID_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //      initAgoraEngineAndJoinChannel();
                    scanChannel();
                } else {
                    showLongToast("No permission for " + Manifest.permission.RECORD_AUDIO);
                    finish();
                }
                break;
            }
        }
    }

    public final void showLongToast(final String msg) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        leaveChannel();
        RtcEngine.destroy();
        mRtcEngine = null;
     // removeSavedCallData();
    }

    // Tutorial Step 7
    public void onLocalAudioMuteClicked(View view) {

        if (!isMuted) {
            muteImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_call_red));
            mRtcEngine.muteLocalAudioStream(true);
            isMuted = true;

        } else {
            muteImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_call_white));
            isMuted = false;
            mRtcEngine.muteLocalAudioStream(false);
        }

        // Stops/Resumes sending the local audio stream.

    }

    // Tutorial Step 5
    public void onSwitchSpeakerphoneClicked(View view) {
        if (speaker) {
            LoudImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker_call_white));
            speaker = false;
            mRtcEngine.setEnableSpeakerphone(false);
        } else {
            LoudImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker_call_red));
            speaker = true;
            mRtcEngine.setEnableSpeakerphone(true);
        }

    }

    // Tutorial Step 3
    public void onEncCallClicked(View view) {
        finish();
    }


    // Tutorial Step 1
    private void initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(getBaseContext(), getString(R.string.agora_app_id), mRtcEventHandler);
        } catch (Exception e) {
            Log.e(LOG_TAG, Log.getStackTraceString(e));

            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }

    }

    // Tutorial Step 2
    private void joinChannel(String accessToken, String channelName, int uid) {

        Log.d("agora", "joinChannel: ");
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
        mRtcEngine.joinChannel(accessToken, channelName, "Extra Optional Data", uid);
    }

    // Tutorial Step 3
    private void leaveChannel() {
        try {
            mRtcEngine.leaveChannel();
        } catch (Exception e) {
            Log.d("agora", "leaveChannel Error: " + e);

        }
    }

    // Tutorial Step 4
    private void onRemoteUserLeft(int uid, int reason) {
        isConnected = false;
        deleteChannel(channelName);
        onBackPressed();
    }

    // Tutorial Step 6
    private void onRemoteUserVoiceMuted(int uid, boolean muted) {
    }


    private void saveCallStartTimeOnShared() {
        SharedPreferences prefs = getSharedPreferences("AgoraRandomCallActivity.Starnote", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Long startTime = prefs.getLong(KEY_CALL_START_TIME, 0);
        if (startTime == 0) {
            // editor.putLong(KEY_CALL_START_TIME, mCallStart);
            editor.putLong(KEY_CALL_START_TIME, System.currentTimeMillis());
        }
        isCallConnected = true;
        editor.apply();
    }

    private Long getSaveCallTimeData() {
        SharedPreferences prefs = getSharedPreferences("AgoraRandomCallActivity.Starnote", MODE_PRIVATE);
        return prefs.getLong(KEY_CALL_START_TIME, 0);
    }

    private void removeSavedCallData() {
        SharedPreferences.Editor editor = getSharedPreferences("AgoraRandomCallActivity.Starnote", MODE_PRIVATE).edit();
        editor.clear().apply();
    }


    private void endCall() {
        //call end automatically after 1 minutes
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), "Times Up!", Toast.LENGTH_LONG).show();
                isConnected = false;
                deleteChannel(channelName);
                giveRandomCallBonus();////////////////
                onBackPressed();
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(AgoraRandomVoiceCallActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                   // Toast.makeText(getApplicationContext(), "The interstitial ad wasn't ready yet!", Toast.LENGTH_SHORT).show();
                }
            }
        }, 300000);

    }
    private void giveRandomCallBonus() {

       // Log.w(TAG, "showBonusDialog: --->>>>>>>>>> Iam Here -->>>>>>>> Minuties is = " + minutes);
        uID = FirebaseAuth.getInstance().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uID);
       // Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.animation_dialog;

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    points = dataSnapshot.child("points").getValue(Integer.class);
                    points += 2;
                    reference.child("points").setValue(points).addOnSuccessListener(aVoid -> {
                        RandomCallCheck.setCallRunning(false);
                        Toast.makeText(getApplicationContext(), "Congratulations!!\nYou have got 2 points!!", Toast.LENGTH_SHORT).show();

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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(AgoraRandomVoiceCallActivity.this, "Something went wrong, Failed to sent bonus points", Toast.LENGTH_SHORT).show();
            }
        });
    }

}