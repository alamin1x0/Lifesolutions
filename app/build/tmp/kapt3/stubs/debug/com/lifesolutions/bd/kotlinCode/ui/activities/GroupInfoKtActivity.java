package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002J\u000e\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020%J\u0012\u0010&\u001a\u00020\u001d2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\u000e\u0010)\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020%J\b\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u0004H\u0002J\b\u0010.\u001a\u00020\u001dH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/GroupInfoKtActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "TOPIC", "adminCount", "", "currentMember", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupMember;", "currentUserFirstName", "currentUserID", "currentUserImage", "currentUserLastName", "currentUserRole", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "groupId", "groupInfo", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupConversation;", "groupMemberList", "Ljava/util/ArrayList;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "memberAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMemberAdapter;", "userPreferences", "Landroid/content/SharedPreferences;", "checkGroupAdmin", "", "countAdminRole", "getCurrentUserInfo", "getGroupInfo", "getGroupMemberList", "leaveFromGroup", "onClickAddMember", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLeaveGroup", "onSupportNavigateUp", "", "searchGroupMember", "str", "updateReceiverUI", "app_debug"})
public final class GroupInfoKtActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "GroupInfoKtActivity";
    private java.lang.String groupId;
    private com.google.firebase.database.FirebaseDatabase db;
    private android.content.SharedPreferences userPreferences;
    private com.lifesolutions.bd.kotlinCode.data.model.GroupConversation groupInfo;
    private com.lifesolutions.bd.kotlinCode.data.model.GroupMember currentMember;
    private int adminCount = 0;
    private java.lang.String currentUserRole = "member";
    private java.lang.String TOPIC;
    private java.lang.String currentUserID;
    private java.lang.String currentUserFirstName;
    private java.lang.String currentUserLastName;
    private java.lang.String currentUserImage;
    private com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMemberAdapter memberAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private final java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.GroupMember> groupMemberList = null;
    private java.util.HashMap _$_findViewCache;
    
    public GroupInfoKtActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void searchGroupMember(java.lang.String str) {
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
    
    private final void getGroupMemberList() {
    }
    
    public final void onClickAddMember(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void checkGroupAdmin() {
    }
    
    private final void countAdminRole() {
    }
    
    public final void onLeaveGroup(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void leaveFromGroup() {
    }
}