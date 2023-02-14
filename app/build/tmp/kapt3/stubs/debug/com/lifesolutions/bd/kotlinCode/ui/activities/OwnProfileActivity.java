package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0016\u0010\'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0*H\u0002J\u000e\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020-J\u0010\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020\u0007H\u0002J\u0010\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020\u0007H\u0002J\u0012\u00102\u001a\u00020(2\b\u00103\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u00104\u001a\u00020(2\u0006\u00105\u001a\u00020\nH\u0002J\u0012\u00106\u001a\u0004\u0018\u00010\u00072\u0006\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u00020(H\u0002J\b\u0010:\u001a\u00020(H\u0002J\b\u0010;\u001a\u00020(H\u0002J\u0012\u0010<\u001a\u0004\u0018\u00010\u00072\u0006\u0010=\u001a\u000208H\u0002J\u0006\u0010>\u001a\u00020(J\b\u0010?\u001a\u00020(H\u0002J\u0010\u0010@\u001a\u00020(2\u0006\u0010A\u001a\u00020\u0007H\u0002J\u0016\u0010B\u001a\u00020(2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0*H\u0002J\"\u0010E\u001a\u00020(2\u0006\u0010F\u001a\u00020\u00052\u0006\u0010G\u001a\u00020\u00052\b\u0010H\u001a\u0004\u0018\u00010IH\u0014J\u000e\u0010J\u001a\u00020(2\u0006\u0010,\u001a\u00020-J\u000e\u0010K\u001a\u00020(2\u0006\u0010,\u001a\u00020-J\u000e\u0010L\u001a\u00020(2\u0006\u0010,\u001a\u00020-J\u000e\u0010M\u001a\u00020(2\u0006\u0010,\u001a\u00020-J\u000e\u0010N\u001a\u00020(2\u0006\u0010,\u001a\u00020-J\u0012\u0010O\u001a\u00020(2\b\u0010P\u001a\u0004\u0018\u00010QH\u0014J\u0018\u0010R\u001a\u00020(2\u0006\u0010,\u001a\u00020-2\u0006\u0010S\u001a\u00020\nH\u0016J\b\u0010T\u001a\u00020(H\u0002J\b\u0010U\u001a\u00020(H\u0002J\b\u0010V\u001a\u00020(H\u0002J\u0010\u0010W\u001a\u00020(2\u0006\u0010X\u001a\u00020\u0007H\u0002J\u0012\u0010Y\u001a\u00020(2\b\u0010Z\u001a\u0004\u0018\u00010\u0007H\u0002J\b\u0010[\u001a\u00020(H\u0002J\u0018\u0010\\\u001a\u00020(2\u0006\u0010]\u001a\u00020-2\u0006\u00105\u001a\u00020\nH\u0002J\b\u0010^\u001a\u00020(H\u0003J\u0018\u0010_\u001a\u00020(2\u0006\u0010`\u001a\u00020\u00072\u0006\u0010a\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006b"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/OwnProfileActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/FeedItemOnClickListener;", "()V", "PReqCode", "", "TAG", "", "allPosts", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/PostKt;", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "authId", "compressedImage", "Ljava/io/File;", "coverImage", "currentUser", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "editor", "Landroid/content/SharedPreferences$Editor;", "feedAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;", "friendsCount", "imageUrlThumb", "imageViewBitmap", "Landroid/graphics/Bitmap;", "isCoverImage", "", "isVerified", "pickedImgUri", "Landroid/net/Uri;", "userPreferences", "Landroid/content/SharedPreferences;", "userReferCode", "viewProgressDialog", "Lcom/lifesolutions/bd/kotlinCode/utils/ViewProgressDialog;", "addPostToDB", "", "posts", "", "backToHome", "view", "Landroid/view/View;", "checkReferCode", "referCode", "createNewPost", "postDesc", "customCompressImage", "actualImage", "deleteUserPost", "feed", "getBirthDate", "birthDate", "", "getBonus", "getFriendLists", "getInitPosts", "getJoinedDate", "joined", "getTalkTime", "getUserInfo", "giveBonus", "refererId", "initFriendsList", "users", "Lcom/lifesolutions/bd/kotlinCode/data/model/FriendData;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClickApplyReferCode", "onClickEditProfile", "onClickPrivacy", "onClickSeeAllFriends", "onClickSeeAllPosts", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRecyclerViewItemClicked", "postItem", "openApplyReferDialog", "openImageViewDialog", "refresh", "removeServerImage", "imageLink", "saveDataToSharedPref", "imageThumbnail", "selectImage", "showMenu", "v", "updateUserUi", "uploadPhoto", "content", "childPath", "app_debug"})
public final class OwnProfileActivity extends androidx.appcompat.app.AppCompatActivity implements com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener {
    private final java.lang.String TAG = "OwnProfileActivity";
    private com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog viewProgressDialog;
    private com.google.firebase.database.FirebaseDatabase database;
    private android.content.SharedPreferences userPreferences;
    private android.content.SharedPreferences.Editor editor;
    private java.lang.String authId;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUser;
    private boolean isVerified = false;
    private boolean userReferCode = false;
    private java.lang.String coverImage = "";
    private int friendsCount = 0;
    private com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter.FeedAdapterKt feedAdapter;
    private final java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.PostKt> allPosts = null;
    private boolean isCoverImage = false;
    private final int PReqCode = 2;
    private android.net.Uri pickedImgUri;
    private java.lang.String imageUrlThumb;
    private java.io.File compressedImage;
    private android.graphics.Bitmap imageViewBitmap;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private java.util.HashMap _$_findViewCache;
    
    public OwnProfileActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Click Methods
     */
    public final void onClickEditProfile(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onClickPrivacy(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void backToHome(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onClickApplyReferCode(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onClickSeeAllFriends(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onClickSeeAllPosts(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void getTalkTime() {
    }
    
    /**
     * Get Current User Data
     */
    private final void getUserInfo() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void updateUserUi() {
    }
    
    private final void getFriendLists() {
    }
    
    private final void getInitPosts() {
    }
    
    private final void initFriendsList(java.util.List<com.lifesolutions.bd.kotlinCode.data.model.FriendData> users) {
    }
    
    /**
     * On Recycler view Item Click
     */
    @java.lang.Override()
    public void onRecyclerViewItemClicked(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.data.model.PostKt postItem) {
    }
    
    /**
     * Remove Post
     * Delete from Server
     * Image Remove
     * Refresh
     */
    private final void showMenu(android.view.View v, com.lifesolutions.bd.kotlinCode.data.model.PostKt feed) {
    }
    
    private final void deleteUserPost(com.lifesolutions.bd.kotlinCode.data.model.PostKt feed) {
    }
    
    private final void removeServerImage(java.lang.String imageLink) {
    }
    
    private final void refresh() {
    }
    
    /**
     * Image Upload
     * Cover Image Upload
     * Profile Image Upload
     */
    private final void selectImage() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void openImageViewDialog() {
    }
    
    private final void customCompressImage(java.io.File actualImage) {
    }
    
    private final void uploadPhoto(java.lang.String content, java.lang.String childPath) {
    }
    
    private final void createNewPost(java.lang.String postDesc) {
    }
    
    /**
     * Functions
     * Get Birthdate
     * Get Join Date
     */
    private final java.lang.String getBirthDate(long birthDate) {
        return null;
    }
    
    private final java.lang.String getJoinedDate(long joined) {
        return null;
    }
    
    private final void addPostToDB(java.util.List<com.lifesolutions.bd.kotlinCode.data.model.PostKt> posts) {
    }
    
    private final void saveDataToSharedPref(java.lang.String imageThumbnail) {
    }
    
    /**
     * Refer Code Apply
     */
    private final void openApplyReferDialog() {
    }
    
    private final void checkReferCode(java.lang.String referCode) {
    }
    
    private final void giveBonus(java.lang.String refererId) {
    }
    
    private final void getBonus() {
    }
}