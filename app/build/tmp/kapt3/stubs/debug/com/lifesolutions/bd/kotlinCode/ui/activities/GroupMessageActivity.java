package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00b2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u000203H\u0002J\b\u00105\u001a\u000203H\u0002J\b\u00106\u001a\u000203H\u0002J\b\u00107\u001a\u000203H\u0002J\b\u00108\u001a\u000203H\u0002J\"\u00109\u001a\u0002032\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00042\b\u0010<\u001a\u0004\u0018\u00010=H\u0014J\u000e\u0010>\u001a\u0002032\u0006\u0010?\u001a\u00020@J\u0012\u0010A\u001a\u0002032\b\u0010B\u001a\u0004\u0018\u00010CH\u0014J\u0012\u0010D\u001a\u00020$2\b\u0010E\u001a\u0004\u0018\u00010FH\u0016J\u000e\u0010G\u001a\u0002032\u0006\u0010?\u001a\u00020@J\u000e\u0010H\u001a\u0002032\u0006\u0010?\u001a\u00020@J\u000e\u0010I\u001a\u0002032\u0006\u0010?\u001a\u00020@J\b\u0010J\u001a\u00020$H\u0016J\b\u0010K\u001a\u000203H\u0002J\b\u0010L\u001a\u000203H\u0002J\b\u0010M\u001a\u000203H\u0002J(\u0010N\u001a\u0002032\u0006\u0010O\u001a\u00020\b2\u0006\u0010P\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\b2\u0006\u0010R\u001a\u00020\bH\u0002J\b\u0010S\u001a\u000203H\u0002J(\u0010T\u001a\u0002032\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010U\u001a\u00020\b2\u0006\u0010V\u001a\u00020\b2\u0006\u0010W\u001a\u00020\bH\u0002J\u0010\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[H\u0002J\b\u0010\\\u001a\u000203H\u0002J\b\u0010]\u001a\u000203H\u0002J\b\u0010^\u001a\u000203H\u0002J\b\u0010_\u001a\u000203H\u0002J\b\u0010`\u001a\u000203H\u0002J\u0010\u0010a\u001a\u0002032\u0006\u0010Q\u001a\u00020\bH\u0002J\b\u0010b\u001a\u000203H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006c"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/GroupMessageActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PReqCode", "", "REQUESTCODE", "REQUEST_RECORD_AUDIO_PERMISSION", "TAG", "", "TOPIC", "allMessages", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupMessage;", "Lkotlin/collections/ArrayList;", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "checker", "currentUserFirstName", "currentUserID", "currentUserImage", "currentUserLastName", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "fileName", "fileRef", "Lcom/google/firebase/storage/StorageReference;", "groupId", "groupInfo", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupConversation;", "groupMember", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupMember;", "mAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMessageAdapter;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mStart", "", "pickedImgUri", "Landroid/net/Uri;", "getPickedImgUri", "()Landroid/net/Uri;", "setPickedImgUri", "(Landroid/net/Uri;)V", "progressBar", "Landroid/widget/ProgressBar;", "recorder", "Landroid/media/MediaRecorder;", "userPreferences", "Landroid/content/SharedPreferences;", "voiceRef", "checkCurrentUserRole", "", "checkPermissionForAudioRecord", "checkRequestList", "getCurrentUserInfo", "getGroupInfo", "inputMessageWatcher", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAttachFile", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onSendGroupMessage", "onSendImageMessage", "onSendVoiceMessage", "onSupportNavigateUp", "pickImage", "pickPdfFile", "pickWordFile", "pushMessageToDB", "mgsType", "textMessage", "lastMessage", "imageUrl", "recordVoiceMessage", "sendNotification", "fullName", "message", "groupName", "sendNotificationToMembers", "Lkotlinx/coroutines/Job;", "notificationData", "Lcom/lifesolutions/bd/Notifications/Sender;", "sendVoiceMessage", "sentTextMessage", "showAllMessage", "startRecording", "stopRecording", "updateConversation", "updateGroupInfoUI", "app_debug"})
public final class GroupMessageActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "GroupMessageActivity";
    private java.lang.String groupId;
    private com.google.firebase.database.FirebaseDatabase db;
    private android.content.SharedPreferences userPreferences;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMessageAdapter mAdapter;
    private com.lifesolutions.bd.kotlinCode.data.model.GroupConversation groupInfo;
    private java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.GroupMessage> allMessages;
    private java.lang.String currentUserID;
    private java.lang.String currentUserFirstName;
    private java.lang.String currentUserLastName;
    private java.lang.String currentUserImage;
    private java.lang.String TOPIC;
    private com.lifesolutions.bd.kotlinCode.data.model.GroupMember groupMember;
    private final int PReqCode = 2;
    private final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
    private final int REQUESTCODE = 2;
    private java.lang.String checker = "";
    private java.lang.String fileName;
    private boolean mStart = true;
    private android.media.MediaRecorder recorder;
    private com.google.firebase.storage.StorageReference fileRef;
    private com.google.firebase.storage.StorageReference voiceRef;
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri pickedImgUri;
    private android.widget.ProgressBar progressBar;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private java.util.HashMap _$_findViewCache;
    
    public GroupMessageActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getPickedImgUri() {
        return null;
    }
    
    public final void setPickedImgUri(@org.jetbrains.annotations.Nullable()
    android.net.Uri p0) {
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
    
    private final void inputMessageWatcher() {
    }
    
    private final void getGroupInfo() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void updateGroupInfoUI() {
    }
    
    private final void showAllMessage() {
    }
    
    private final void sentTextMessage() {
    }
    
    /**
     * Push Message to database
     */
    private final void pushMessageToDB(java.lang.String mgsType, java.lang.String textMessage, java.lang.String lastMessage, java.lang.String imageUrl) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    /**
     * Update Conversation
     */
    private final void updateConversation(java.lang.String lastMessage) {
    }
    
    private final void checkCurrentUserRole() {
    }
    
    private final void checkRequestList() {
    }
    
    private final void pickImage() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void pickPdfFile() {
    }
    
    private final void pickWordFile() {
    }
    
    private final void checkPermissionForAudioRecord() {
    }
    
    private final void recordVoiceMessage() {
    }
    
    private final void sendVoiceMessage() {
    }
    
    private final void startRecording() {
    }
    
    private final void stopRecording() {
    }
    
    /**
     * Main Message Send Method
     */
    public final void onSendGroupMessage(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onSendImageMessage(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onAttachFile(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onSendVoiceMessage(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void sendNotification(java.lang.String groupId, java.lang.String fullName, java.lang.String message, java.lang.String groupName) {
    }
    
    private final kotlinx.coroutines.Job sendNotificationToMembers(com.lifesolutions.bd.Notifications.Sender notificationData) {
        return null;
    }
}