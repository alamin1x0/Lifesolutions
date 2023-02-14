package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@android.annotation.SuppressLint(value = {"SetTextI18n"})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00e6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010B\u001a\u00020CH\u0002J\b\u0010D\u001a\u00020CH\u0002J\b\u0010E\u001a\u00020CH\u0002J\b\u0010F\u001a\u00020CH\u0002J\b\u0010G\u001a\u00020CH\u0002J\u0010\u0010H\u001a\u00020C2\u0006\u0010I\u001a\u00020\u0007H\u0002J\u0018\u0010J\u001a\u00020C2\u0006\u0010K\u001a\u00020\u00072\u0006\u0010L\u001a\u00020\u0007H\u0002J\u0012\u0010M\u001a\u00020C2\b\u0010N\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010O\u001a\u00020CH\u0002J\b\u0010P\u001a\u00020CH\u0002J\b\u0010Q\u001a\u00020CH\u0002J\u0010\u0010R\u001a\u00020C2\u0006\u0010S\u001a\u00020\u0007H\u0002J\u0010\u0010T\u001a\u00020C2\u0006\u0010S\u001a\u00020\u0007H\u0002J\b\u0010U\u001a\u00020CH\u0002J\"\u0010V\u001a\u00020C2\u0006\u0010W\u001a\u00020\n2\u0006\u0010X\u001a\u00020\n2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0014J\u000e\u0010[\u001a\u00020C2\u0006\u0010\\\u001a\u00020]J\u000e\u0010^\u001a\u00020C2\u0006\u0010\\\u001a\u00020]J\u000e\u0010_\u001a\u00020C2\u0006\u0010\\\u001a\u00020]J\u0012\u0010`\u001a\u00020C2\b\u0010a\u001a\u0004\u0018\u00010bH\u0014J\u0012\u0010c\u001a\u00020$2\b\u0010d\u001a\u0004\u0018\u00010eH\u0016J \u0010f\u001a\u00020C2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020i0h2\b\u0010j\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010k\u001a\u00020CH\u0014J-\u0010l\u001a\u00020C2\u0006\u0010W\u001a\u00020\n2\u000e\u0010m\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010n\u001a\u00020oH\u0016\u00a2\u0006\u0002\u0010pJ\u000e\u0010q\u001a\u00020C2\u0006\u0010\\\u001a\u00020]J\b\u0010r\u001a\u00020CH\u0014J\u0012\u0010s\u001a\u00020C2\b\u0010t\u001a\u0004\u0018\u00010uH\u0016J\b\u0010v\u001a\u00020CH\u0016J\b\u0010w\u001a\u00020$H\u0016J\b\u0010x\u001a\u00020CH\u0002J\b\u0010y\u001a\u00020CH\u0002J\b\u0010z\u001a\u00020CH\u0002J\b\u0010{\u001a\u00020CH\u0002J(\u0010|\u001a\u00020C2\u0006\u0010}\u001a\u00020\u00072\u0006\u0010L\u001a\u00020\u00072\u0006\u0010~\u001a\u00020\u00072\u0006\u0010\u007f\u001a\u00020\u0007H\u0002J\t\u0010\u0080\u0001\u001a\u00020CH\u0002J\u0012\u0010\u0081\u0001\u001a\u00020C2\u0007\u0010\u0082\u0001\u001a\u00020\u0007H\u0002J\t\u0010\u0083\u0001\u001a\u00020CH\u0002J#\u0010\u0084\u0001\u001a\u00020C2\u0006\u00102\u001a\u00020\u00072\u0007\u0010\u0085\u0001\u001a\u00020\u00072\u0007\u0010\u0086\u0001\u001a\u00020\u0007H\u0002J\t\u0010\u0087\u0001\u001a\u00020CH\u0002J\t\u0010\u0088\u0001\u001a\u00020CH\u0002J\t\u0010\u0089\u0001\u001a\u00020CH\u0002J\t\u0010\u008a\u0001\u001a\u00020CH\u0002J\t\u0010\u008b\u0001\u001a\u00020CH\u0002J\t\u0010\u008c\u0001\u001a\u00020CH\u0002J\t\u0010\u008d\u0001\u001a\u00020CH\u0002R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0010j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\'\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u00100\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u00101R\u0010\u00102\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u00105\u001a\u0004\u0018\u000106X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u00107R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020;X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\"X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u008e\u0001"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/PersonalMessageActivity;", "Lcom/lifesolutions/bd/Activities/BaseActivity;", "Lcom/lifesolutions/bd/Services/SinchService$StartFailedListener;", "Lcom/kroegerama/imgpicker/BottomSheetImagePicker$OnImagesSelectedListener;", "()V", "PERMISSIONS", "", "", "[Ljava/lang/String;", "PERMISSION_AUDIO_CALL", "", "PReqCode", "REQUESTCODE", "REQUEST_RECORD_AUDIO_PERMISSION", "TAG", "allFriendsList", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/FriendData;", "allMessages", "Lcom/lifesolutions/bd/Models/Message;", "Lkotlin/collections/ArrayList;", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "checker", "compressedImage", "Ljava/io/File;", "currentUserFirstName", "currentUserID", "currentUserImage", "currentUserLastName", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "fileName", "fileRef", "Lcom/google/firebase/storage/StorageReference;", "isCallable", "", "listener", "Lcom/google/firebase/database/ValueEventListener;", "listenerConversation", "mAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/ChatMessageAdapter;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mStart", "notify", "progressBar", "Landroid/widget/ProgressBar;", "receiverActiveStatus", "Ljava/lang/Boolean;", "receiverID", "receiverInfo", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "receiverLastSeen", "", "Ljava/lang/Long;", "recorder", "Landroid/media/MediaRecorder;", "seenConversationRef", "Lcom/google/firebase/database/DatabaseReference;", "seenListenerRef", "userBlockMe", "userPreferences", "Landroid/content/SharedPreferences;", "voiceRef", "youBlockedUser", "blockAnUser", "", "callUserAudio", "callUserVideo", "checkBlockByUser", "checkPermissionForAudioRecord", "createCallLogConversation", "callType", "createConversation", "messageType", "textMessage", "customCompressImage", "actualImage", "getCurrentUserBlockedStatus", "getCurrentUserInfo", "getReceiverInfo", "goCallScreenActivity", "callId", "goVideoCallScreenActivity", "inputMessageWatcher", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAttachFile", "view", "Landroid/view/View;", "onClickImageBtn", "onClickVoiceBtn", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onImagesSelected", "uris", "", "Landroid/net/Uri;", "tag", "onPause", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onSendPersonalMessage", "onServiceConnected", "onStartFailed", "error", "Lcom/sinch/android/rtc/SinchError;", "onStarted", "onSupportNavigateUp", "pickImage", "pickPdfFile", "pickSingle", "pickWordFile", "pushMessageToDB", "mgsType", "lastMessage", "imageUrl", "recordVoiceMessage", "seenMessage", "userId", "seenOnConversation", "sendNotification", "fullname", "message", "sendVoiceMessage", "sentTextMessage", "showAllMessage", "startRecording", "stopRecording", "unBlockAnUser", "updateReceiverUI", "app_debug"})
public final class PersonalMessageActivity extends com.lifesolutions.bd.Activities.BaseActivity implements com.lifesolutions.bd.Services.SinchService.StartFailedListener, com.kroegerama.imgpicker.BottomSheetImagePicker.OnImagesSelectedListener {
    private final java.lang.String TAG = "MessageActivity";
    private java.util.ArrayList<com.lifesolutions.bd.Models.Message> allMessages;
    private boolean notify = false;
    private java.lang.String currentUserID;
    private java.lang.String currentUserFirstName;
    private java.lang.String currentUserLastName;
    private java.lang.String currentUserImage;
    private java.lang.String receiverID;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt receiverInfo;
    private java.lang.Long receiverLastSeen;
    private java.lang.Boolean receiverActiveStatus = false;
    private com.google.firebase.database.FirebaseDatabase db;
    private android.content.SharedPreferences userPreferences;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private com.lifesolutions.bd.kotlinCode.ui.adapter.ChatMessageAdapter mAdapter;
    private com.google.firebase.database.DatabaseReference seenListenerRef;
    private com.google.firebase.database.ValueEventListener listener;
    private com.google.firebase.database.DatabaseReference seenConversationRef;
    private com.google.firebase.database.ValueEventListener listenerConversation;
    private java.lang.String checker = "";
    private java.lang.String fileName;
    private final int PReqCode = 2;
    private final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
    private final int PERMISSION_AUDIO_CALL = 102;
    private final int REQUESTCODE = 2;
    private boolean mStart = true;
    private android.media.MediaRecorder recorder;
    private com.google.firebase.storage.StorageReference fileRef;
    private com.google.firebase.storage.StorageReference voiceRef;
    private java.io.File compressedImage;
    private boolean userBlockMe = false;
    private boolean youBlockedUser = false;
    private boolean isCallable = false;
    private android.widget.ProgressBar progressBar;
    private final java.lang.String[] PERMISSIONS = {"android.permission.READ_PHONE_STATE", "android.permission.RECORD_AUDIO"};
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private final java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.FriendData> allFriendsList = null;
    private java.util.HashMap _$_findViewCache;
    
    public PersonalMessageActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    private final void getCurrentUserInfo() {
    }
    
    private final void getReceiverInfo() {
    }
    
    private final void updateReceiverUI() {
    }
    
    private final void inputMessageWatcher() {
    }
    
    private final void createCallLogConversation(java.lang.String callType) {
    }
    
    private final void createConversation(java.lang.String messageType, java.lang.String textMessage) {
    }
    
    /**
     * Main Message Send Method
     */
    public final void onSendPersonalMessage(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void showAllMessage() {
    }
    
    private final void seenMessage(java.lang.String userId) {
    }
    
    private final void seenOnConversation() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    public final void onClickVoiceBtn(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void checkPermissionForAudioRecord() {
    }
    
    private final void recordVoiceMessage() {
    }
    
    private final void sentTextMessage() {
    }
    
    private final void sendVoiceMessage() {
    }
    
    private final void startRecording() {
    }
    
    private final void stopRecording() {
    }
    
    public final void onClickImageBtn(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void pickImage() {
    }
    
    private final void pickSingle() {
    }
    
    @java.lang.Override()
    public void onImagesSelected(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris, @org.jetbrains.annotations.Nullable()
    java.lang.String tag) {
    }
    
    private final void customCompressImage(java.io.File actualImage) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public final void onAttachFile(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void pickPdfFile() {
    }
    
    private final void pickWordFile() {
    }
    
    /**
     * Push Message to database
     */
    private final void pushMessageToDB(java.lang.String mgsType, java.lang.String textMessage, java.lang.String lastMessage, java.lang.String imageUrl) {
    }
    
    private final void callUserAudio() {
    }
    
    private final void callUserVideo() {
    }
    
    private final void goCallScreenActivity(java.lang.String callId) {
    }
    
    private final void goVideoCallScreenActivity(java.lang.String callId) {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @java.lang.Override()
    public void onStarted() {
    }
    
    @java.lang.Override()
    public void onStartFailed(@org.jetbrains.annotations.Nullable()
    com.sinch.android.rtc.SinchError error) {
    }
    
    @java.lang.Override()
    protected void onServiceConnected() {
    }
    
    /**
     * BlackList
     */
    private final void blockAnUser() {
    }
    
    private final void unBlockAnUser() {
    }
    
    private final void checkBlockByUser() {
    }
    
    private final void getCurrentUserBlockedStatus() {
    }
    
    /**
     * Main Toolbar Menu and Call
     */
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    /**
     * Push Notification
     */
    private final void sendNotification(java.lang.String receiverID, java.lang.String fullname, java.lang.String message) {
    }
}