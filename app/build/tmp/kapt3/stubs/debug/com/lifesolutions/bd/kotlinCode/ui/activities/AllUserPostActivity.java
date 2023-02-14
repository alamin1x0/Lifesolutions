package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\u0012\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0018\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0015H\u0002J\u0010\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0005H\u0002J\u0018\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/AllUserPostActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/FeedItemOnClickListener;", "()V", "TAG", "", "allPosts", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/PostKt;", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "authId", "currentUser", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "feedAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;", "userPreferences", "Landroid/content/SharedPreferences;", "deleteUserPost", "", "feed", "getInitPosts", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRecyclerViewItemClicked", "view", "Landroid/view/View;", "postItem", "onSupportNavigateUp", "", "refresh", "removeServerImage", "imageLink", "showMenu", "v", "app_debug"})
public final class AllUserPostActivity extends androidx.appcompat.app.AppCompatActivity implements com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener {
    private final java.lang.String TAG = "AllUserPostActivity";
    private com.google.firebase.database.FirebaseDatabase database;
    private android.content.SharedPreferences userPreferences;
    private java.lang.String authId;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUser;
    private com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter.FeedAdapterKt feedAdapter;
    private final java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.PostKt> allPosts = null;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private java.util.HashMap _$_findViewCache;
    
    public AllUserPostActivity() {
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
    
    private final void getInitPosts() {
    }
    
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
}