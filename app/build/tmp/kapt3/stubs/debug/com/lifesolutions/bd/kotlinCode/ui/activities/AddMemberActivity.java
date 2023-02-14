package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0012\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/AddMemberActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "adminId", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "db", "Lcom/google/firebase/database/DatabaseReference;", "friendReference", "groupID", "groupReference", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "memberAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/AddGroupMemberAdapter;", "userReference", "getFriendList", "", "onClickDone", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class AddMemberActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "AddMemberActivity";
    private java.lang.String groupID;
    private java.lang.String adminId;
    private com.google.firebase.database.DatabaseReference db;
    private com.google.firebase.database.DatabaseReference groupReference;
    private com.google.firebase.database.DatabaseReference userReference;
    private com.google.firebase.database.DatabaseReference friendReference;
    private com.lifesolutions.bd.kotlinCode.ui.adapter.AddGroupMemberAdapter memberAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private java.util.HashMap _$_findViewCache;
    
    public AddMemberActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void getFriendList() {
    }
    
    public final void onClickDone(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
}