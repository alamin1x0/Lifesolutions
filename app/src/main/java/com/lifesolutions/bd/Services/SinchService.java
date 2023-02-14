package com.lifesolutions.bd.Services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.sinch.android.rtc.AudioController;
import com.sinch.android.rtc.Beta;
import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.ManagedPush;
import com.sinch.android.rtc.NotificationResult;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.video.VideoController;
import com.sinch.android.rtc.video.VideoScalingType;
import com.lifesolutions.bd.Activities.CallScreenActivity;
import com.lifesolutions.bd.Activities.IncomingCallScreenActivity;
import com.lifesolutions.bd.Activities.IncomingVideoCallScreenActivity;
import com.lifesolutions.bd.Helpers.RandomCallCheck;

import java.util.Map;

public class SinchService extends Service{

    // Unique API key
    private static final String APP_KEY = "998a0f22-d52c-4cc5-8240-f52eae198a17";
    private static final String APP_SECRET = "+cmOtep2GUO6mTiohP9hBw==";
    private static final String ENVIRONMENT = "clientapi.sinch.com";



    public static final String CALL_ID = "CALL_ID";
    static final String TAG = SinchService.class.getSimpleName();

    private SinchServiceInterface mSinchServiceInterface = new SinchServiceInterface();
    private SinchClient mSinchClient;
    private String mUserId;
    private String uID = FirebaseAuth.getInstance().getUid();

    private StartFailedListener mListener;

    @Override
    public void onCreate() {
        super.onCreate();

        if (uID != null) {
            start(uID);
        }
    }

    @Override
    public void onDestroy() {
        if (mSinchClient != null && mSinchClient.isStarted()) {
            mSinchClient.terminate();
        }
        // long dateTime;
        // dateTime = System.currentTimeMillis();
        // DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("userStat");
        // reference.child("online").setValue(false);
        // reference.child("date").setValue(dateTime);
        super.onDestroy();
    }

    private void start(String userName) {
        if (mSinchClient == null) {
            mUserId = userName;
            mSinchClient = Sinch.getSinchClientBuilder().context(getApplicationContext()).userId(userName)
                    .applicationKey(APP_KEY)
                    .applicationSecret(APP_SECRET)
                    .environmentHost(ENVIRONMENT).build();

            mSinchClient.setSupportCalling(true);
            mSinchClient.startListeningOnActiveConnection();
            mSinchClient.setSupportManagedPush(true);
            mSinchClient.addSinchClientListener(new MySinchClientListener());
            mSinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
            mSinchClient.getVideoController().setResizeBehaviour(VideoScalingType.ASPECT_FILL);
            mSinchClient.start();
        }
    }

    private void stop() {
        if (mSinchClient != null) {
            mSinchClient.terminate();
            mSinchClient = null;
        }
    }

    private boolean isStarted() {
        return (mSinchClient != null && mSinchClient.isStarted());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mSinchServiceInterface;
    }

    public class SinchServiceInterface extends Binder {

        public Call callUserAudio(String userId, Map<String, String> name) {
            if (mSinchClient == null) {
                return null;
            }
            return mSinchClient.getCallClient().callUser(userId, name);
        }

        public Call callUserVideo(String userId, Map<String, String> name) {
            return mSinchClient.getCallClient().callUserVideo(userId, name);
        }

        public Call callUserAudio(String userId) {
            if (mSinchClient == null) {
                return null;
            }
            return mSinchClient.getCallClient().callUser(userId);
        }

        public Call callUserVideo(String userId) {
            return mSinchClient.getCallClient().callUserVideo(userId);
        }

        public ManagedPush getManagedPush(String username) {
            // create client, but you don't need to start it
            if (mSinchClient == null ){
                startClient(username);
            }
            // retrieve ManagedPush
            return Beta.createManagedPush(mSinchClient);
        }

        public String getUserName() {
            return mUserId;
        }

        public boolean isStarted() {
            return SinchService.this.isStarted();
        }

        public void startClient(String userName) {
            start(userName);
        }

        public void stopClient() {
            stop();
        }

        public void setStartListener(StartFailedListener listener) {
            mListener = listener;
        }

        public Call getCall(String callId) {
            return mSinchClient.getCallClient().getCall(callId);
        }

        public VideoController getVideoController() {
            if (!isStarted()) {
                return null;
            }
            return mSinchClient.getVideoController();
        }

        public AudioController getAudioController() {
            if (!isStarted()) {
                return null;
            }
            return mSinchClient.getAudioController();
        }

        public NotificationResult relayRemotePushNotificationPayload(final Map payload) {

            if (mSinchClient == null && !mUserId.isEmpty()) {
                start(mUserId);
            } else if (mSinchClient == null && mUserId.isEmpty()) {
                Log.e(TAG, "Can't start a SinchClient as no username is available, unable to relay push.");
                return null;
            }
            return mSinchClient.relayRemotePushNotificationPayload(payload);
        }
    }

    public interface StartFailedListener {

        void onStartFailed(SinchError error);

        void onStarted();
    }

    private class MySinchClientListener implements SinchClientListener {

        @Override
        public void onClientFailed(SinchClient client, SinchError error) {
            if (mListener != null) {
                mListener.onStartFailed(error);
            }
            mSinchClient.terminate();
            mSinchClient = null;
        }

        @Override
        public void onClientStarted(SinchClient client) {
            Log.d(TAG, "SinchClient started");
            if (mListener != null) {
                mListener.onStarted();
            }
        }

        @Override
        public void onClientStopped(SinchClient client) {
            Log.d(TAG, "SinchClient stopped");
        }

        @Override
        public void onLogMessage(int level, String area, String message) {
            switch (level) {
                case Log.DEBUG:
                    Log.d(area, message);
                    break;
                case Log.ERROR:
                    Log.e(area, message);
                    break;
                case Log.INFO:
                    Log.i(area, message);
                    break;
                case Log.VERBOSE:
                    Log.v(area, message);
                    break;
                case Log.WARN:
                    Log.w(area, message);
                    break;
            }
        }

        @Override
        public void onRegistrationCredentialsRequired(SinchClient client,
                                                      ClientRegistration clientRegistration) {
        }
    }

    private class SinchCallClientListener implements CallClientListener {

        @Override
        public void onIncomingCall(CallClient callClient, Call call) {

            Log.d(TAG, "Incoming call");
            if (call.getDetails().isVideoOffered()){
                Intent intent = new Intent(SinchService.this, IncomingVideoCallScreenActivity.class);
                intent.putExtra(CALL_ID, call.getCallId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SinchService.this.startActivity(intent);
            } else {
                if (RandomCallCheck.getCheck()){
                    call.answer();
                    RandomCallCheck.setCallRunning(true);
                    Intent intent = new Intent(SinchService.this, CallScreenActivity.class);
                    intent.putExtra(CALL_ID, call.getCallId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    SinchService.this.startActivity(intent);
                } else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                        // Toast.makeText(SinchService.this, "Sinch Service > O", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(SinchService.this, IncomingCallScreenActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(CALL_ID, call.getCallId());
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        // PendingIntent pendingIntent = PendingIntent.getActivity(SinchService.this, 0, intent, 0);
                        PendingIntent pendingIntent = PendingIntent.getActivity(SinchService.this, 2,intent,PendingIntent.FLAG_ONE_SHOT);

                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }

                        } else {
                        Intent intent = new Intent(SinchService.this, IncomingCallScreenActivity.class);
                        intent.putExtra(CALL_ID, call.getCallId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        SinchService.this.startActivity(intent);
                        // Toast.makeText(SinchService.this, "Sinch Service", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        }
    }


}
