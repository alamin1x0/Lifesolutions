package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0003J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0012\u0010*\u001a\u00020&2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020&H\u0014J\b\u0010.\u001a\u00020&H\u0014J\b\u0010/\u001a\u00020&H\u0014J\b\u00100\u001a\u00020&H\u0014J\b\u00101\u001a\u00020(H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u00062"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/StreamActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "APPLICATION_ID", "", "getAPPLICATION_ID", "()Ljava/lang/String;", "activity", "broadcaster", "Lcom/bambuser/broadcaster/Broadcaster;", "getBroadcaster", "()Lcom/bambuser/broadcaster/Broadcaster;", "broadcaster$delegate", "Lkotlin/Lazy;", "broadcasterObserver", "Lcom/bambuser/broadcaster/Broadcaster$Observer;", "livegif", "Landroid/widget/ImageView;", "getLivegif", "()Landroid/widget/ImageView;", "setLivegif", "(Landroid/widget/ImageView;)V", "title", "Landroid/widget/EditText;", "getTitle", "()Landroid/widget/EditText;", "setTitle", "(Landroid/widget/EditText;)V", "uID", "userPreferences", "Landroid/content/SharedPreferences;", "viewer", "Landroid/widget/TextView;", "getViewer", "()Landroid/widget/TextView;", "setViewer", "(Landroid/widget/TextView;)V", "getUserInfo", "", "hasPermission", "", "permission", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onStart", "onSupportNavigateUp", "app_debug"})
public final class StreamActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String APPLICATION_ID = "aCJqRXQ4xqSNUfjvPhi8Ew";
    private android.content.SharedPreferences userPreferences;
    private java.lang.String uID;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView viewer;
    @org.jetbrains.annotations.Nullable()
    private android.widget.EditText title;
    @org.jetbrains.annotations.Nullable()
    private android.widget.ImageView livegif;
    private final com.lifesolutions.bd.kotlinCode.ui.activities.StreamActivity activity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy broadcaster$delegate = null;
    private final com.bambuser.broadcaster.Broadcaster.Observer broadcasterObserver = null;
    private java.util.HashMap _$_findViewCache;
    
    public StreamActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAPPLICATION_ID() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.TextView getViewer() {
        return null;
    }
    
    public final void setViewer(@org.jetbrains.annotations.Nullable()
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.EditText getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable()
    android.widget.EditText p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.ImageView getLivegif() {
        return null;
    }
    
    public final void setLivegif(@org.jetbrains.annotations.Nullable()
    android.widget.ImageView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.bambuser.broadcaster.Broadcaster getBroadcaster() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final boolean hasPermission(java.lang.String permission) {
        return false;
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void getUserInfo() {
    }
}