package com.lifesolutions.bd.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallEndCause;
import com.sinch.android.rtc.video.VideoCallListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Helpers.RingtonePlayer;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.Services.SinchService;
import com.lifesolutions.bd.kotlinCode.common.CommonNotification;
import com.lifesolutions.bd.kotlinCode.data.model.CallLog;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IncomingVideoCallScreenActivity extends BaseActivity {

    static final String TAG = IncomingVideoCallScreenActivity.class.getSimpleName();
    private String mCallId, fullNameOfCaller, callerProfileImg, callerUID;;
    private RingtonePlayer mAudioPlayer;
    ImageButton btnAnswer,btnReject;
    CircleImageView profileImage;
    TextView tvName,tvAddress;
    ImageView videoCallBgProfile;

    // Shared Pref
    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_video_call_screen);

        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE);

        // SAZIB..
        videoCallBgProfile = findViewById(R.id.video_call_bg_profile);

        btnAnswer = findViewById(R.id.call_accept_incoming_video_call);
        btnAnswer.setOnClickListener(mClickListener);
        btnReject = findViewById(R.id.call_reject_incoming_video_call);
        profileImage = findViewById(R.id.profile_image_incoming_video_call);
        tvName = findViewById(R.id.name_incoming_video_call);
        tvAddress = findViewById(R.id.address_incoming_video_call);
        btnReject.setOnClickListener(mClickListener);

        mAudioPlayer = new RingtonePlayer(this);
        mAudioPlayer.playRingtone();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON| WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mCallId = getIntent().getStringExtra(SinchService.CALL_ID);
    }

    @Override
    protected void onServiceConnected() {
        Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            call.addCallListener(new SinchCallListener());
           getCallerInfo(tvName,profileImage, videoCallBgProfile, call.getRemoteUserId());
        } else {
            Log.e(TAG, "Started with invalid callId, aborting");
            //finish();
            finishActivity();
        }
    }

    /**
     * On Volume
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            mAudioPlayer.stopRingtone();
        }
        return true;
    }

    private void answerClicked() {
        mAudioPlayer.stopRingtone();
        Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            call.answer();
            createCallLogConversation("video", "incoming");
            Intent intent = new Intent(this, VideoCallScreenActivity.class);
            intent.putExtra(SinchService.CALL_ID, mCallId);
            startActivity(intent);
        } else {
            //finish();
            finishActivity();
        }
    }

    private void declineClicked() {
        mAudioPlayer.stopRingtone();
        Call call = getSinchServiceInterface().getCall(mCallId);
        if (call != null) {
            call.hangup();
        }
       // finish();
        finishActivity();
    }

    private class SinchCallListener implements VideoCallListener {

        @Override
        public void onCallEnded(Call call) {
            CallEndCause cause = call.getDetails().getEndCause();
            // Missed Call Notification
            if (cause.toString().equals("CANCELED")) {
                sendMissedCallNotification();
                createCallLogConversation("video", "missed");
            }
            Log.d(TAG, "Call ended, cause: " + cause.toString());
            mAudioPlayer.stopRingtone();
            //finish();
            finishActivity();
        }

        @Override
        public void onCallEstablished(Call call) {
            Log.d(TAG, "Call established");
        }

        @Override
        public void onCallProgressing(Call call) {
            Log.d(TAG, "Call progressing");
        }

        @Override
        public void onShouldSendPushNotification(Call call, List pushPairs) {
            // Send a push through your push provider here, e.g. GCM
        }

        @Override
        public void onVideoTrackAdded(Call call) {
            // Display some kind of icon showing it's a video call
        }

        @Override
        public void onVideoTrackPaused(Call call) {

        }

        @Override
        public void onVideoTrackResumed(Call call) {

        }
    }

    private View.OnClickListener mClickListener = v -> {
        switch (v.getId()) {
            case R.id.call_accept_incoming_video_call:
                answerClicked();
                break;
            case R.id.call_reject_incoming_video_call:
                declineClicked();
                break;
        }
    };

    private void getCallerInfo(TextView callerName,CircleImageView callerImage, ImageView videoCallBgProfile, String callerId) {
        DatabaseReference callerReference = FirebaseDatabase.getInstance().getReference("Users").child(callerId);
        callerReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fullNameOfCaller = dataSnapshot.child("firstName").getValue(String.class) + " " + dataSnapshot.child("lastName").getValue(String.class);
                callerProfileImg = dataSnapshot.child("imageThumbnail").getValue(String.class);
                callerUID = callerId;
                callerName.setText(fullNameOfCaller);
                if (!dataSnapshot.child("imageThumbnail").getValue(String.class).equals("none")){
                    String imgUrl = dataSnapshot.child("imageThumbnail").getValue(String.class);
                    Picasso.get().load(imgUrl).into(callerImage);
                    Picasso.get().load(imgUrl).into(videoCallBgProfile);

                } else {
                    profileImage.setImageResource(R.drawable.user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void finishActivity(){
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
        IncomingVideoCallScreenActivity.this.finish();
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
            ref.child(pushRef.getKey()).setValue(callerLog);
        }
    }

    private void sendMissedCallNotification() {
        Call call = getSinchServiceInterface().getCall(mCallId);
        CommonNotification notification = new CommonNotification(this, "MISSED_CALL", "Video", call.getRemoteUserId(), fullNameOfCaller);
        notification.createNotification();
    }

}
