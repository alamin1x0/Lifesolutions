package com.lifesolutions.bd.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Helpers.RingtonePlayer;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.Services.SinchService;
import com.lifesolutions.bd.kotlinCode.data.model.CallLog;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomCallRecieve extends AppCompatActivity {

    static final String TAG = IncomingCallScreenActivity.class.getSimpleName();
    private String mCallId, fullNameOfCaller, callerProfileImg, callerUID;
    private RingtonePlayer mAudioPlayer;
    ImageButton btnAnswer, btnReject;
    CircleImageView profileImage;
    ImageView mainCallBgProfile;
    TextView tvName, tvAddress;
    Vibrator vibrator;
    private InterstitialAd mInterstitialAd;
    AdView mAdView;
    SharedPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_call_recieve);
        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE);

        // notification = new Notification(this);
        mainCallBgProfile = findViewById(R.id.main_call_bg_profile);

        btnAnswer = findViewById(R.id.call_accept_incoming_call);
        btnReject = findViewById(R.id.call_reject_incoming_call);
        profileImage = findViewById(R.id.profile_image_incoming_call);
        tvName = findViewById(R.id.name_incoming_call);
        tvAddress = findViewById(R.id.address_incoming_call);
        btnAnswer.setOnClickListener(mClickListener);
        btnReject.setOnClickListener(mClickListener);

        mAudioPlayer = new RingtonePlayer(this);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mAudioPlayer.playRingtone();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mCallId = getIntent().getStringExtra(SinchService.CALL_ID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(1000);
        }

        //Admob Init
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
        //Admob ads
        mAdView = findViewById(R.id.adView_incoming_call);
        mAdView.loadAd(adRequest);
    }



    /**
     * On Volume
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            mAudioPlayer.stopRingtone();
            //for vibration
            vibrator.cancel();
        }
        return true;
    }


    private void answerClicked() {
        mAudioPlayer.stopRingtone();
        //for vibration
        vibrator.cancel();

    }

    private void declineClicked() {

        mAudioPlayer.stopRingtone();
        vibrator.cancel();
        //finish();
        finishActivity();
    }


    @SuppressLint("NonConstantResourceId")
    private final View.OnClickListener mClickListener = v -> {
        switch (v.getId()) {
            case R.id.call_accept_incoming_call:
                answerClicked();
                break;
            case R.id.call_reject_incoming_call:
                declineClicked();
                break;
        }
    };

    private void getCallerInfo(TextView callerName, CircleImageView callerImage, ImageView callBgProfile, String callerId) {
        DatabaseReference callerReference = FirebaseDatabase.getInstance().getReference("Users").child(callerId);
        callerReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fullNameOfCaller = dataSnapshot.child("firstName").getValue(String.class) + " " + dataSnapshot.child("lastName").getValue(String.class);
                callerProfileImg = dataSnapshot.child("imageThumbnail").getValue(String.class);
                callerUID = callerId;
                callerName.setText(fullNameOfCaller);
                if (!Objects.requireNonNull(dataSnapshot.child("imageThumbnail").getValue(String.class)).equals("none")) {
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


    private void createCallLogConversation(String callType, String callStatus) {
        String currentUserID = userPreferences.getString("uID", null);
        if (currentUserID != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CallLogs").child(currentUserID);
            DatabaseReference pushRef = ref.push();
            // Sender...
            CallLog callerLog = new CallLog(
                    pushRef.getKey(),
                    callerUID,
                    fullNameOfCaller,
                    callerProfileImg,
                    callType,
                    callStatus,
                    (long) 0,
                    ServerValue.TIMESTAMP
            );
            ref.child(Objects.requireNonNull(pushRef.getKey())).setValue(callerLog);
          //  Utils.INSTANCE.sendNotification(userPreferences.getString("uID", null),fullNameOfCaller,"Missed a call",userPreferences.getString("uID", null));
        }
    }


    private void finishActivity() {
        CustomCallRecieve.this.finish();
    }

}