package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00ca\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0015\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u00020=H\u0002J\u0012\u0010?\u001a\u00020=2\b\u0010@\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010A\u001a\u00020=H\u0002J\u0012\u0010B\u001a\u0004\u0018\u00010\u00052\u0006\u0010;\u001a\u00020\u001aH\u0002J\u001a\u0010C\u001a\u0004\u0018\u00010\u001a2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0002J\u0010\u0010H\u001a\u00020\u00052\u0006\u0010I\u001a\u00020JH\u0002J\u001c\u0010K\u001a\u0004\u0018\u00010\u00052\u0006\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u001aH\u0002J\u0010\u0010O\u001a\u00020=2\u0006\u0010P\u001a\u00020\u0005H\u0002J\u0018\u0010Q\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001f2\u0006\u0010R\u001a\u00020\u0005H\u0002J\b\u0010S\u001a\u00020=H\u0002J\b\u0010T\u001a\u00020=H\u0002J\b\u0010U\u001a\u00020=H\u0002J\"\u0010V\u001a\u00020=2\u0006\u0010W\u001a\u00020\b2\u0006\u0010X\u001a\u00020\b2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0014J\u0012\u0010[\u001a\u00020=2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0014J\b\u0010^\u001a\u00020=H\u0014J\u000e\u0010_\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010b\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010c\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010d\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010e\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010f\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010g\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010h\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010i\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010j\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ\u000e\u0010k\u001a\u00020=2\u0006\u0010`\u001a\u00020aJ-\u0010l\u001a\u00020=2\u0006\u0010W\u001a\u00020\b2\u000e\u0010m\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010n\u001a\u00020oH\u0016\u00a2\u0006\u0002\u0010pJ\b\u0010q\u001a\u00020\u001cH\u0016J\b\u0010r\u001a\u00020=H\u0002J\b\u0010s\u001a\u00020=H\u0002J\b\u0010t\u001a\u00020=H\u0002J\b\u0010u\u001a\u00020=H\u0002J\b\u0010v\u001a\u00020=H\u0002J\b\u0010w\u001a\u00020=H\u0002J\b\u0010x\u001a\u00020=H\u0002J\u0012\u0010y\u001a\u00020=2\b\u0010z\u001a\u0004\u0018\u00010\u001aH\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010#\u001a\u00020$\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\'\u001a\u00020(\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u00101\u001a\u0004\u0018\u000102X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u0016\u00107\u001a\n\u0012\u0004\u0012\u000209\u0018\u000108X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020(X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006{"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/CreatePostKtActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PERMISSIONS", "", "", "[Ljava/lang/String;", "PICK_VIDEO_REQUEST", "", "REQUESTCODE", "STORAGE_REQUEST_CODE", "TAG", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "colorCode", "compressedImage", "Ljava/io/File;", "currentUser", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "dowlThumbUri", "dowlvideoUri", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "imuri", "Landroid/net/Uri;", "isImagePicked", "", "isVideoPicked", "linkList", "", "linkSource", "linkThumb", "linkTitle", "myRef", "Lcom/google/firebase/storage/StorageReference;", "getMyRef", "()Lcom/google/firebase/storage/StorageReference;", "myRefDB", "Lcom/google/firebase/database/DatabaseReference;", "getMyRefDB", "()Lcom/google/firebase/database/DatabaseReference;", "postDesc", "privacy", "privacyAdapter", "Lcom/lifesolutions/bd/Adapters/PrivacyAdapterSpinner;", "progress", "Lcom/lifesolutions/bd/kotlinCode/utils/ViewProgressDialog;", "runnable", "Ljava/lang/Runnable;", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "spinnerItems", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/Models/SpinnerItem;", "userPathRef", "videoUri", "chooseImage", "", "chooseVideo", "customCompressImage", "actualImage", "decreaseLayoutHeight", "getFileExtension", "getImageUri", "inContext", "Landroid/content/Context;", "inImage", "Landroid/graphics/Bitmap;", "getReadableFileSize", "size", "", "getRealPathFromURI", "context", "Landroid/app/Activity;", "contentURI", "givePostBonus", "authorId", "hasUrl", "postDescription", "initPrivacyList", "initUserInfo", "navigateActi", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPickColorFive", "view", "Landroid/view/View;", "onPickColorFour", "onPickColorOne", "onPickColorSeven", "onPickColorSix", "onPickColorThree", "onPickColorTwo", "onPickImage", "onPickVideo", "onPublishPost", "onRemovePickImage", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onSupportNavigateUp", "postWithImageUpload", "postWithLink", "postWithOnlyText", "postWithVideoUpload", "removeLinkPreview", "resumeOldState", "showLinkPreview", "uploadVideo", "uri", "app_debug"})
public final class CreatePostKtActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "CreatePostKtActivity";
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUser;
    private com.google.firebase.database.DatabaseReference userPathRef;
    private java.lang.String postDesc = "";
    private java.lang.String colorCode = "#E63434";
    private boolean isImagePicked = false;
    private boolean isVideoPicked = false;
    private com.lifesolutions.bd.Adapters.PrivacyAdapterSpinner privacyAdapter;
    private java.util.ArrayList<com.lifesolutions.bd.Models.SpinnerItem> spinnerItems;
    private java.lang.String privacy = "public";
    @org.jetbrains.annotations.Nullable()
    private final android.os.Handler handler = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Runnable runnable;
    private com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog progress;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private final int REQUESTCODE = 2;
    private final int STORAGE_REQUEST_CODE = 101;
    private java.lang.String[] PERMISSIONS = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private java.io.File compressedImage;
    private android.net.Uri imuri;
    private final int PICK_VIDEO_REQUEST = 1;
    private android.net.Uri videoUri;
    private java.lang.String dowlvideoUri;
    private java.lang.String dowlThumbUri;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.storage.StorageReference myRef = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference myRefDB = null;
    private java.util.List<java.lang.String> linkList;
    private java.lang.String linkThumb;
    private java.lang.String linkTitle;
    private java.lang.String linkSource;
    private java.util.HashMap _$_findViewCache;
    
    public CreatePostKtActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.os.Handler getHandler() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Runnable getRunnable() {
        return null;
    }
    
    public final void setRunnable(@org.jetbrains.annotations.Nullable()
    java.lang.Runnable p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.storage.StorageReference getMyRef() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.database.DatabaseReference getMyRefDB() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * On Back Pressed
     */
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    /**
     * Aerialist of Spinner
     */
    private final void initPrivacyList() {
    }
    
    /**
     * On Click Color Picker
     */
    public final void onPickColorOne(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onPickColorTwo(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onPickColorThree(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onPickColorFour(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onPickColorFive(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onPickColorSix(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onPickColorSeven(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void decreaseLayoutHeight() {
    }
    
    private final void resumeOldState() {
    }
    
    /**
     * On Click Image Pick Button
     * On Remove Image Button
     */
    public final void onPickImage(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onPickVideo(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void uploadVideo(android.net.Uri uri) {
    }
    
    private final java.lang.String getFileExtension(android.net.Uri videoUri) {
        return null;
    }
    
    private final void chooseVideo() {
    }
    
    public final void onRemovePickImage(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Init User
     */
    private final void initUserInfo() {
    }
    
    /**
     * Image Upload and Permission
     */
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void chooseImage() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void customCompressImage(java.io.File actualImage) {
    }
    
    private final java.lang.String getReadableFileSize(long size) {
        return null;
    }
    
    private final java.lang.String getRealPathFromURI(android.app.Activity context, android.net.Uri contentURI) {
        return null;
    }
    
    private final android.net.Uri getImageUri(android.content.Context inContext, android.graphics.Bitmap inImage) {
        return null;
    }
    
    /**
     * On Publish Post
     */
    public final void onPublishPost(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Post Database Upadate
     * Post with Image
     * Post With Only Text
     * Post with LInk
     */
    private final void postWithImageUpload() {
    }
    
    private final void navigateActi() {
    }
    
    private final void postWithVideoUpload() {
    }
    
    private final void postWithOnlyText() {
    }
    
    private final void postWithLink() {
    }
    
    /**
     * Give Bonus
     */
    private final void givePostBonus(java.lang.String authorId) {
    }
    
    /**
     * URL System
     */
    private final java.util.List<java.lang.String> hasUrl(java.lang.String postDescription) {
        return null;
    }
    
    private final void showLinkPreview() {
    }
    
    private final void removeLinkPreview() {
    }
}