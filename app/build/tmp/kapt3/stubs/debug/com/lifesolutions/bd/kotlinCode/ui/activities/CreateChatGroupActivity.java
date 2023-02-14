package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010\u001b\u001a\u00020\u0019H\u0002J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\bH\u0002J\"\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u000e\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\'J\u0012\u0010(\u001a\u00020\u00192\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\u000e\u0010+\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\'J\b\u0010,\u001a\u00020-H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/CreateChatGroupActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PReqCode", "", "TAG", "", "bitmapThumbnail", "Landroid/graphics/Bitmap;", "categoryList", "", "[Ljava/lang/String;", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "groupCat", "groupName", "groupPrivacy", "pickedImgUri", "Landroid/net/Uri;", "privacyList", "progress", "Lcom/lifesolutions/bd/kotlinCode/utils/ViewProgressDialog;", "userPreferences", "Landroid/content/SharedPreferences;", "createGroupInit", "", "imageUrl", "createGroupWithImage", "getImageUri", "inContext", "Landroid/content/Context;", "inImage", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBtnNextClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPickImage", "onSupportNavigateUp", "", "app_debug"})
public final class CreateChatGroupActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "CChatGroupActivity";
    private android.content.SharedPreferences userPreferences;
    private com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog progress;
    private final java.lang.String[] privacyList = {"Public", "Private"};
    private final java.lang.String[] categoryList = {"Education", "Practice English", "Entertainment", "Lifestyle", "Chatting", "Others"};
    private com.google.firebase.database.FirebaseDatabase db;
    private java.lang.String groupName;
    private java.lang.String groupCat;
    private java.lang.String groupPrivacy;
    private final int PReqCode = 2;
    private android.net.Uri pickedImgUri;
    private android.graphics.Bitmap bitmapThumbnail;
    private java.util.HashMap _$_findViewCache;
    
    public CreateChatGroupActivity() {
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
    
    public final void onBtnNextClick(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void createGroupInit(java.lang.String imageUrl) {
    }
    
    private final void createGroupWithImage() {
    }
    
    public final void onPickImage(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final android.net.Uri getImageUri(android.content.Context inContext, android.graphics.Bitmap inImage) {
        return null;
    }
}