package com.lifesolutions.bd.Notifications;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.Services.SinchService;

public class FirebaseMessaging extends FirebaseMessagingService {
    Intent intent;

    private SinchService.SinchServiceInterface mSinchServiceInterface;
    Context context;
    FirebaseAuth mAuth;
    String currentUser;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


//        mAuth = FirebaseAuth.getInstance();
//        currentUser = mAuth.getUid();
//
//        String sent = remoteMessage.getData().get("sent");
//        String user = remoteMessage.getData().get("user");
//
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        context = this;
//
//        /*if (SinchHelpers.isSinchPushPayload(remoteMessage.getData())) {
//            NotificationResult result = mSinchClient.relayRemotePushNotificationPayload(remoteMessage.getData());
//            CallNotificationResult callResult = result.getCallResult();
//            Map<String, String> customHeaders = callResult.getHeaders();
//            if (result.isValid() && result.isCall()) {
//                if (!callResult.isCallCanceled() || !callResult.isTimedOut()) {
//
//                    if (callResult.isVideoOffered()) {
//                        Intent intent = new Intent(this, IncomingVideoCallScreenActivity.class);
//                        intent.putExtra(SinchService.CALL_ID, callResult.getRemoteUserId());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
//                        startActivity(intent);
//                    } else {
//                        Intent intent = new Intent(this, IncomingCallScreenActivity.class);
//                        intent.putExtra(SinchService.CALL_ID, callResult.getRemoteUserId());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
//                        startActivity(intent);
//                    }
//                }
//            }
//        }*/
//
//        Map data = remoteMessage.getData();
//        if (SinchHelpers.isSinchPushPayload(data)) {
//            new ServiceConnection() {
//                private Map payload;
//
//                @Override
//                public void onServiceConnected(ComponentName name, IBinder service) {
//                    if (payload != null) {
//                        SinchService.SinchServiceInterface sinchService = (SinchService.SinchServiceInterface) service;
//                        if (sinchService != null) {
//                            NotificationResult result = sinchService.relayRemotePushNotificationPayload(payload);
//                            // handle result, e.g. show a notification or similar
//
//                            CallNotificationResult callResult = result.getCallResult();
//                            if (result.isValid() && result.isCall()) {
//                                if (!callResult.isCallCanceled() || !callResult.isTimedOut()) {
//
////                                    if (callResult.isVideoOffered()) {
////                                        Intent intent = new Intent(FirebaseMessaging.this,IncomingVideoCallScreenActivity.class);
////                                        intent.putExtra(SinchService.CALL_ID, callResult.getRemoteUserId());
////                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
////                                        startActivity(intent);
////
////                                    } else {
////                                        Intent intent = new Intent(FirebaseMessaging.this, IncomingCallScreenActivity.class);
////                                        intent.putExtra(SinchService.CALL_ID, callResult.getRemoteUserId());
////                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
////                                        startActivity(intent);
////                                    }
//                                }
//                            }
//
//                        }
//                    }
//                    payload = null;
//                }
//
//                @Override
//                public void onServiceDisconnected(ComponentName name) {}
//
//                public void relayMessageData(Map<String, String> data) {
//                    payload = data;
//                    getApplicationContext().bindService(new Intent(getApplicationContext(), SinchService.class), this, BIND_AUTO_CREATE);
//                }
//            }.relayMessageData(data);
//        } else {
//            if (firebaseUser != null && sent.equals(firebaseUser.getUid())){
//                if (!currentUser.equals(user)){
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                        sendOandAboveNotification(remoteMessage);
//                    } else {
//                        sendNormalNotification(remoteMessage);
//                    }
//                }
//            }
//        }
//    }

//    private void sendNormalNotification(RemoteMessage remoteMessage) {
//        String user = remoteMessage.getData().get("user");
//        String icon = remoteMessage.getData().get("icon");
//        String title = remoteMessage.getData().get("title");
//        String body = remoteMessage.getData().get("body");
//        String type = remoteMessage.getData().get("type");
//        String extra = remoteMessage.getData().get("extra");
//
//        RemoteMessage.Notification notification = remoteMessage.getNotification();
//
//        int i = Integer.parseInt(user.replaceAll("[\\D]",""));
//        switch (type) {
//            case "message": {
//                intent = new Intent(this, PersonalMessageActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("receiverID", user);
//                intent.putExtras(bundle);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                break;
//            }
//            case "comment": {
//                intent = new Intent(this, FullPostActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("Id", extra);
//                intent.putExtras(bundle);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                break;
//            }
//            case "request": {
//                intent = new Intent(this, RequestAcceptActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("uID", extra);
//                intent.putExtras(bundle);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                break;
//            }
//        }
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,i,intent,PendingIntent.FLAG_ONE_SHOT);
//
//        //Uri dfSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Uri dfSoundUri = Uri.parse("android.resource://"
//                + this.getPackageName() + "/" + R.raw.notification);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(Integer.parseInt(icon))
//                .setContentText(body)
//                .setContentTitle(title)
//                .setAutoCancel(true)
//                .setSound(dfSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        int j = 0;
//        if (i>0){
//            j =i;
//        }
//        notificationManager.notify(j,builder.build());
//
//
//    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void sendOandAboveNotification(RemoteMessage remoteMessage) {
//        String user = remoteMessage.getData().get("user");
//        String icon = remoteMessage.getData().get("icon");
//        String title = remoteMessage.getData().get("title");
//        String body = remoteMessage.getData().get("body");
//        String type = remoteMessage.getData().get("type");
//        String extra = remoteMessage.getData().get("extra");
//
//        RemoteMessage.Notification notification = remoteMessage.getNotification();
//        int i = Integer.parseInt(user.replaceAll("[\\D]",""));
//        switch (type) {
//            case "message": {
//                intent = new Intent(this, PersonalMessageActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("receiverID", user);
//                intent.putExtras(bundle);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                break;
//            }
//            case "comment": {
//                intent = new Intent(this, FullPostActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("Id", extra);
//                intent.putExtras(bundle);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                break;
//            }
//            case "request": {
//                intent = new Intent(this, RequestAcceptActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("uID", extra);
//                intent.putExtras(bundle);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                break;
//            }
//        }
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,i,intent,PendingIntent.FLAG_ONE_SHOT);
//
//        //Uri dfSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        Uri dfSoundUri = Uri.parse("android.resource://"
//                + this.getPackageName() + "/" + R.raw.notification);
//
//        OreoAndAboveNotification notification1 = new OreoAndAboveNotification(this);
//        Notification.Builder builder = notification1.getNotifications(title,body,pendingIntent,dfSoundUri,icon);
//
//        int j = 0;
//        if (i>0){
//            j =i;
//        }
//        notification1.getNotificationManager().notify(j,builder.build());
//    }
//
//    @Override
//    public void onNewToken(@NonNull String s) {
//        super.onNewToken(s);
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null){
//            updateToken(s);
//        }
//    }
//
//    private void updateToken(String tokenRefresh) {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
//        Token token = new Token(tokenRefresh);
//        reference.child(user.getUid()).setValue(token);
//    }
//
//    public static boolean foregrounded() {
//        ActivityManager.RunningAppProcessInfo appProcessInfo =
//                new ActivityManager.RunningAppProcessInfo();
//        ActivityManager.getMyMemoryState(appProcessInfo);
//        return (appProcessInfo.importance == IMPORTANCE_FOREGROUND
//                || appProcessInfo.importance == IMPORTANCE_VISIBLE);
//    }
    }
}
