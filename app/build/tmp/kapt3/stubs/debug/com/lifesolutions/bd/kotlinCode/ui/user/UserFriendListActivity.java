package com.lifesolutions.bd.kotlinCode.ui.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u00020\u000e2\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0017j\b\u0012\u0004\u0012\u00020\u0004`\u0018H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/user/UserFriendListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "currentUserID", "", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "memberAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/UserFriendListsAdapter;", "userPreferences", "Landroid/content/SharedPreferences;", "getCurrentUserInfo", "", "getFriendList", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setUserAdapter", "friendsKey", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "app_debug"})
public final class UserFriendListActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.lifesolutions.bd.kotlinCode.ui.adapter.UserFriendListsAdapter memberAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private com.google.firebase.database.FirebaseDatabase db;
    private android.content.SharedPreferences userPreferences;
    private java.lang.String currentUserID;
    private java.util.HashMap _$_findViewCache;
    
    public UserFriendListActivity() {
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
    
    private final void getCurrentUserInfo() {
    }
    
    private final void getFriendList() {
    }
    
    private final void setUserAdapter(java.util.ArrayList<java.lang.String> friendsKey) {
    }
}