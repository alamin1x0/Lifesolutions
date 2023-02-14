package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\u001b\u001a\u00020\u0019H\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0002J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006H\u0002J\"\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\u000e\u0010&\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020(J\u0012\u0010)\u001a\u00020\u00192\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\u000e\u0010,\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020(J\b\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/EditGroupActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PReqCode", "", "bitmapThumbnail", "Landroid/graphics/Bitmap;", "categoryList", "", "", "[Ljava/lang/String;", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "groupCat", "groupId", "groupInfo", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupConversation;", "groupName", "groupPrivacy", "pickedImgUri", "Landroid/net/Uri;", "privacyList", "progress", "Lcom/lifesolutions/bd/kotlinCode/utils/ViewProgressDialog;", "createGroupInit", "", "imageUrl", "createGroupWithImage", "getGroupInfo", "getImageUri", "inContext", "Landroid/content/Context;", "inImage", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBtnNextClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPickImage", "onSupportNavigateUp", "", "updateUI", "app_debug"})
public final class EditGroupActivity extends androidx.appcompat.app.AppCompatActivity {
    private java.lang.String groupId;
    private com.google.firebase.database.FirebaseDatabase db;
    private com.lifesolutions.bd.kotlinCode.data.model.GroupConversation groupInfo;
    private com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog progress;
    private final java.lang.String[] privacyList = {"Public", "Private"};
    private final java.lang.String[] categoryList = {"Education", "Practice English", "Entertainment", "Lifestyle", "Chatting", "Others"};
    private java.lang.String groupName;
    private java.lang.String groupCat;
    private java.lang.String groupPrivacy;
    private final int PReqCode = 2;
    private android.net.Uri pickedImgUri;
    private android.graphics.Bitmap bitmapThumbnail;
    private java.util.HashMap _$_findViewCache;
    
    public EditGroupActivity() {
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
    
    private final void getGroupInfo() {
    }
    
    private final void updateUI() {
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