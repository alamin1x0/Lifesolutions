package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0003J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0011H\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0003J\b\u0010\u0018\u001a\u00020\u0011H\u0003J\u0012\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u000e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0011H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/JoinGroupActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "currentUserFirstName", "currentUserID", "currentUserImage", "currentUserLastName", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "groupId", "groupInfo", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupConversation;", "userPreferences", "Landroid/content/SharedPreferences;", "cancelRequestToGroup", "", "checkMemberList", "checkRequestList", "countMemberList", "getCurrentUserInfo", "getGroupInfo", "joinPublicGroup", "joinRequestToGroup", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onJoinBtnClick", "view", "Landroid/view/View;", "onSupportNavigateUp", "", "updateReceiverUI", "app_debug"})
public final class JoinGroupActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "JoinGroupActivity";
    private java.lang.String groupId;
    private com.lifesolutions.bd.kotlinCode.data.model.GroupConversation groupInfo;
    private java.lang.String currentUserID;
    private java.lang.String currentUserFirstName;
    private java.lang.String currentUserLastName;
    private java.lang.String currentUserImage;
    private com.google.firebase.database.FirebaseDatabase db;
    private android.content.SharedPreferences userPreferences;
    private java.util.HashMap _$_findViewCache;
    
    public JoinGroupActivity() {
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
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void updateReceiverUI() {
    }
    
    private final void getCurrentUserInfo() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void joinRequestToGroup() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void joinPublicGroup() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void cancelRequestToGroup() {
    }
    
    public final void onJoinBtnClick(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void checkRequestList() {
    }
    
    private final void checkMemberList() {
    }
    
    private final void countMemberList() {
    }
}