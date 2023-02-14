package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u00052\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u001cH\u0002J\b\u0010$\u001a\u00020\u001cH\u0002J\b\u0010%\u001a\u00020\u001cH\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010\u00052\u0006\u0010\'\u001a\u00020\"H\u0002J\u0006\u0010(\u001a\u00020\u001cJ\b\u0010)\u001a\u00020\u001cH\u0002J\u000e\u0010*\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010+\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010,\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010-\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0012\u0010.\u001a\u00020\u001c2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\u0018\u00101\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u00020\u001cH\u0002J\b\u00105\u001a\u00020\u001cH\u0002J \u00106\u001a\u00020\u001c2\u0006\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0002J\b\u0010:\u001a\u00020\u001cH\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/UserProfileActivityKt;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/FeedItemOnClickListener;", "()V", "TAG", "", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "authId", "coverImage", "currentUser", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "feedAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;", "friendsCount", "", "isFriend", "", "isVerified", "postsCount", "userId", "userPreferences", "Landroid/content/SharedPreferences;", "viewProgressDialog", "Lcom/lifesolutions/bd/kotlinCode/utils/ViewProgressDialog;", "acceptFriendRequest", "", "backToHome", "view", "Landroid/view/View;", "getBirthDate", "birthDate", "", "getFriendStatus", "getFriendsCount", "getInitPosts", "getJoinedDate", "joined", "getTalkTime", "getUserInfo", "onClickAcceptReqBtn", "onClickAddBtn", "onClickCancelReqBtn", "onClickUnFriendBtn", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRecyclerViewItemClicked", "postItem", "Lcom/lifesolutions/bd/kotlinCode/data/model/PostKt;", "removeFriendRequest", "sendFriendRequest", "sendNotification", "receiverID", "firstName", "message", "updateUserUi", "app_debug"})
public final class UserProfileActivityKt extends androidx.appcompat.app.AppCompatActivity implements com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener {
    private final java.lang.String TAG = "UserProfileActivityKt";
    private java.lang.String userId;
    private boolean isVerified = false;
    private java.lang.String coverImage = "";
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUser;
    private com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog viewProgressDialog;
    private com.google.firebase.database.FirebaseDatabase database;
    private java.lang.String authId;
    private android.content.SharedPreferences userPreferences;
    private boolean isFriend = false;
    private com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter.FeedAdapterKt feedAdapter;
    private int friendsCount = 0;
    private int postsCount = 0;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private java.util.HashMap _$_findViewCache;
    
    public UserProfileActivityKt() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Click Method
     */
    public final void backToHome(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onClickAddBtn(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onClickUnFriendBtn(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onClickCancelReqBtn(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void onClickAcceptReqBtn(@org.jetbrains.annotations.NotNull()
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
    
    /**
     * Friend Lifecycle
     */
    private final void getFriendStatus() {
    }
    
    private final void getFriendsCount() {
    }
    
    /**
     * Get Feed Post
     */
    private final void getInitPosts() {
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
    
    /**
     * Friend Request
     */
    private final void sendFriendRequest() {
    }
    
    private final void acceptFriendRequest() {
    }
    
    private final void removeFriendRequest() {
    }
    
    /**
     * Notification
     */
    private final void sendNotification(java.lang.String receiverID, java.lang.String firstName, java.lang.String message) {
    }
    
    /**
     * On Recycler view Item Click
     */
    @java.lang.Override()
    public void onRecyclerViewItemClicked(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.data.model.PostKt postItem) {
    }
}