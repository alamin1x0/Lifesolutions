package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/SearchFriendActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allFriends", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/FriendData;", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "authId", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "displayLists", "friendsAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/FriendListConversationAdapter;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "getFriendList", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "app_debug"})
public final class SearchFriendActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "SearchFriendActivityKt";
    private com.lifesolutions.bd.kotlinCode.ui.adapter.FriendListConversationAdapter friendsAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private com.google.firebase.database.FirebaseDatabase db;
    private java.lang.String authId;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private final java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.FriendData> allFriends = null;
    private final java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.FriendData> displayLists = null;
    private java.util.HashMap _$_findViewCache;
    
    public SearchFriendActivity() {
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
    
    private final void getFriendList() {
    }
}