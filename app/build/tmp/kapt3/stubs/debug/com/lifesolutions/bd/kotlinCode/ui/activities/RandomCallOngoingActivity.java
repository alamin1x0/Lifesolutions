package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\u0012\u0010\u001b\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0014H\u0014J\u000e\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010 \u001a\u00020\u0014H\u0014J\b\u0010!\u001a\u00020\u0014H\u0002J\b\u0010\"\u001a\u00020\u0014H\u0014J\b\u0010#\u001a\u00020\u0014H\u0014J\u0012\u0010$\u001a\u00020\u00142\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010\'\u001a\u00020\u0014H\u0016J\b\u0010(\u001a\u00020\u0014H\u0014J\u000e\u0010)\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/RandomCallOngoingActivity;", "Lcom/lifesolutions/bd/kotlinCode/ui/base/BaseActivityWithSinch;", "Lcom/lifesolutions/bd/Services/SinchService$StartFailedListener;", "()V", "TAG", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "isCallStarted", "", "randomCallBannerContainer", "Landroid/widget/LinearLayout;", "randomDbRef", "Lcom/google/firebase/database/DatabaseReference;", "ringtonePlayer", "Lcom/lifesolutions/bd/Helpers/RingtonePlayer;", "uID", "connectWithRandomPerson", "", "userItems", "", "onBackBtnClick", "view", "Landroid/view/View;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onLocalAudioMuteClicked", "onPause", "onProgressRandomCall", "onServiceConnected", "onStart", "onStartFailed", "error", "Lcom/sinch/android/rtc/SinchError;", "onStarted", "onStop", "onSwitchSpeakerphoneClicked", "app_debug"})
public final class RandomCallOngoingActivity extends com.lifesolutions.bd.kotlinCode.ui.base.BaseActivityWithSinch implements com.lifesolutions.bd.Services.SinchService.StartFailedListener {
    private final java.lang.String TAG = "RandomCallActivity";
    private com.lifesolutions.bd.Helpers.RingtonePlayer ringtonePlayer;
    private com.google.firebase.database.FirebaseDatabase db;
    private com.google.firebase.database.DatabaseReference randomDbRef;
    private com.google.firebase.auth.FirebaseAuth auth;
    private java.lang.String uID;
    private android.widget.LinearLayout randomCallBannerContainer;
    private boolean isCallStarted = false;
    private java.util.HashMap _$_findViewCache;
    
    public RandomCallOngoingActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Lifecycle
     */
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    /**
     * On Start Progress Random Call
     */
    private final void onProgressRandomCall() {
    }
    
    /**
     * On Connect Logic with Random Person
     */
    private final void connectWithRandomPerson(java.util.List<java.lang.String> userItems) {
    }
    
    /**
     * Sinch Override Methods
     */
    @java.lang.Override()
    protected void onServiceConnected() {
    }
    
    @java.lang.Override()
    public void onStarted() {
    }
    
    @java.lang.Override()
    public void onStartFailed(@org.jetbrains.annotations.Nullable()
    com.sinch.android.rtc.SinchError error) {
    }
    
    public final void onBackBtnClick(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onSwitchSpeakerphoneClicked(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onLocalAudioMuteClicked(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
}