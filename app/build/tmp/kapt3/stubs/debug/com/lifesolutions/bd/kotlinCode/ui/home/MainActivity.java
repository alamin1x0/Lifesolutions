package com.lifesolutions.bd.kotlinCode.ui.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020%H\u0002J\b\u0010\'\u001a\u00020%H\u0002J\b\u0010(\u001a\u00020%H\u0002J\b\u0010)\u001a\u00020%H\u0002J\u0012\u0010*\u001a\u0004\u0018\u00010\u00072\u0006\u0010+\u001a\u00020\u0007H\u0002J\b\u0010,\u001a\u00020%H\u0002J\b\u0010-\u001a\u00020%H\u0002J\u0010\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u000200H\u0003J\b\u00101\u001a\u00020%H\u0003J\u0010\u00102\u001a\u00020%2\u0006\u0010/\u001a\u000200H\u0007J\u0006\u00103\u001a\u00020\u0019J\u0006\u00104\u001a\u00020\u0019J\u0012\u00105\u001a\u00020%2\b\u00106\u001a\u0004\u0018\u000107H\u0015J\b\u00108\u001a\u00020%H\u0014J\b\u00109\u001a\u00020%H\u0014J-\u0010:\u001a\u00020%2\u0006\u0010;\u001a\u00020\n2\u000e\u0010<\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010=\u001a\u00020>H\u0016\u00a2\u0006\u0002\u0010?J\b\u0010@\u001a\u00020%H\u0014J\u0010\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020CH\u0016J\b\u0010D\u001a\u00020%H\u0016J\b\u0010E\u001a\u00020%H\u0003J\u0010\u0010F\u001a\u00020%2\u0006\u0010G\u001a\u00020\u0010H\u0002J\b\u0010H\u001a\u00020%H\u0002J\u0018\u0010I\u001a\u00020%2\u0006\u0010J\u001a\u00020\u00072\u0006\u0010K\u001a\u00020\u0007H\u0002J$\u0010L\u001a\u00020%2\u0006\u0010J\u001a\u00020\u00072\b\u0010M\u001a\u0004\u0018\u00010\u00072\b\u0010N\u001a\u0004\u0018\u00010\u0007H\u0002J\u000e\u0010O\u001a\u00020%2\u0006\u0010P\u001a\u00020\nJ\u0016\u0010Q\u001a\u00020%2\u0006\u0010P\u001a\u00020\n2\u0006\u0010R\u001a\u00020\u0019J\b\u0010S\u001a\u00020%H\u0007J\u0010\u0010T\u001a\u00020%2\u0006\u0010/\u001a\u000200H\u0007J\b\u0010U\u001a\u00020%H\u0016J\u0012\u0010V\u001a\u00020%2\b\u0010W\u001a\u0004\u0018\u00010CH\u0016J\u0010\u0010X\u001a\u00020%2\u0006\u0010Y\u001a\u00020\u0007H\u0002J$\u0010Z\u001a\u00020%2\u0006\u0010 \u001a\u00020\u00072\b\u0010M\u001a\u0004\u0018\u00010\u00072\b\u0010[\u001a\u0004\u0018\u00010\u0007H\u0002R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\\"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/MainActivity;", "Lcom/lifesolutions/bd/Activities/BaseActivity;", "Lcom/lifesolutions/bd/Services/SinchService$StartFailedListener;", "Lcom/sinch/android/rtc/PushTokenRegistrationCallback;", "()V", "PERMISSIONS", "", "", "[Ljava/lang/String;", "PERMISSION_ALL", "", "TAG", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "authId", "currentUser", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "currentUserFIre", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "editor", "Landroid/content/SharedPreferences$Editor;", "listenerUserPermission", "Lcom/google/firebase/database/ValueEventListener;", "mPushTokenIsRegistered", "", "mReference", "Lcom/google/firebase/database/DatabaseReference;", "getMReference", "()Lcom/google/firebase/database/DatabaseReference;", "setMReference", "(Lcom/google/firebase/database/DatabaseReference;)V", "uID", "userPathRef", "userPreferences", "Landroid/content/SharedPreferences;", "checkInfoList", "", "checkUpdateVersion", "checkUserBlock", "getFriendLists", "getOverlayPermission", "getSystemProperty", "propName", "getUser", "getUserInfo", "goMiOverlayActivity", "context", "Landroid/content/Context;", "goNormalOverlayActivity", "goToXiaomiPermissions", "isMiUi", "isMiuiWithApi28OrMore", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onServiceConnected", "onStartFailed", "error", "Lcom/sinch/android/rtc/SinchError;", "onStarted", "overlayPermission", "saveUserIdToSharedPref", "user", "setPermissionCount", "setUserReferCode", "uid", "referCode", "setUserSearchName", "firstName", "lastName", "showMessageNotificationBadge", "count", "showNotificationBadge", "visibility", "showPermissionGifDialog", "showPermissionGifDialogMi", "tokenRegistered", "tokenRegistrationFailed", "sinchError", "updateToken", "token", "userActivity", "imageThumbnail", "app_debug"})
public final class MainActivity extends com.lifesolutions.bd.Activities.BaseActivity implements com.lifesolutions.bd.Services.SinchService.StartFailedListener, com.sinch.android.rtc.PushTokenRegistrationCallback {
    private final java.lang.String TAG = "MainActivity";
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private java.lang.String authId;
    private com.google.firebase.database.FirebaseDatabase database;
    private android.content.SharedPreferences userPreferences;
    private android.content.SharedPreferences.Editor editor;
    private java.lang.String uID;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUser;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUserFIre;
    private boolean mPushTokenIsRegistered = false;
    private final int PERMISSION_ALL = 1;
    private final java.lang.String[] PERMISSIONS = {"android.permission.READ_PHONE_STATE", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
    private com.google.firebase.database.DatabaseReference userPathRef;
    private com.google.firebase.database.ValueEventListener listenerUserPermission;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.database.DatabaseReference mReference;
    private java.util.HashMap _$_findViewCache;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.database.DatabaseReference getMReference() {
        return null;
    }
    
    public final void setMReference(@org.jetbrains.annotations.Nullable()
    com.google.firebase.database.DatabaseReference p0) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void checkInfoList() {
    }
    
    private final void getUser() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    private final void getOverlayPermission() {
    }
    
    public final void showNotificationBadge(int count, boolean visibility) {
    }
    
    public final void showMessageNotificationBadge(int count) {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @java.lang.Override()
    public void onStartFailed(@org.jetbrains.annotations.NotNull()
    com.sinch.android.rtc.SinchError error) {
    }
    
    @java.lang.Override()
    public void onStarted() {
    }
    
    @java.lang.Override()
    protected void onServiceConnected() {
    }
    
    @java.lang.Override()
    public void tokenRegistered() {
    }
    
    @java.lang.Override()
    public void tokenRegistrationFailed(@org.jetbrains.annotations.Nullable()
    com.sinch.android.rtc.SinchError sinchError) {
    }
    
    /**
     * User Online Offline
     */
    private final void userActivity(java.lang.String uID, java.lang.String firstName, java.lang.String imageThumbnail) {
    }
    
    /**
     * Token For Cloud Messaging
     */
    private final void updateToken(java.lang.String token) {
    }
    
    public final boolean isMiUi() {
        return false;
    }
    
    public final boolean isMiuiWithApi28OrMore() {
        return false;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void goToXiaomiPermissions(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final java.lang.String getSystemProperty(java.lang.String propName) {
        return null;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    private final void overlayPermission() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void showPermissionGifDialogMi(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void showPermissionGifDialog() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    private final void goNormalOverlayActivity() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    private final void goMiOverlayActivity(android.content.Context context) {
    }
    
    /**
     * Get Current User Data
     * Check User Block
     * Save User Data to Shared Pref
     */
    private final void getUserInfo() {
    }
    
    private final void checkUserBlock() {
    }
    
    /**
     * Save to Shared Preference
     * Set User Refer Code
     */
    private final void saveUserIdToSharedPref(com.lifesolutions.bd.kotlinCode.data.model.UserKt user) {
    }
    
    private final void setUserReferCode(java.lang.String uid, java.lang.String referCode) {
    }
    
    private final void setUserSearchName(java.lang.String uid, java.lang.String firstName, java.lang.String lastName) {
    }
    
    private final void setPermissionCount() {
    }
    
    private final void checkUpdateVersion() {
    }
    
    private final void getFriendLists() {
    }
}