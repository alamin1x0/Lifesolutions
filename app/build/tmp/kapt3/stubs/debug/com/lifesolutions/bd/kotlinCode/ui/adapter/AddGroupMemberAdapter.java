package com.lifesolutions.bd.kotlinCode.ui.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001(B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u001c\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00132\u0006\u0010\u000b\u001a\u00020\u0007J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0003J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001b\u001a\u00020\u00112\n\u0010\u001c\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001aH\u0017J\u001c\u0010\u001e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001aH\u0016J\u0006\u0010\"\u001a\u00020\u0011J\u0018\u0010#\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0003J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0002J\u001c\u0010&\u001a\u00020\u00112\u0006\u0010\'\u001a\u00020\n2\n\u0010\u001c\u001a\u00060\u0002R\u00020\u0000H\u0003R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/AddGroupMemberAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/AddGroupMemberAdapter$ListViewAdapter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "friendsList", "", "Lcom/lifesolutions/bd/kotlinCode/data/model/FriendData;", "groupId", "groupReference", "Lcom/google/firebase/database/DatabaseReference;", "userPreferences", "Landroid/content/SharedPreferences;", "addAllFriends", "", "friendsData", "Ljava/util/ArrayList;", "addMemberToGroup", "user", "textBtn", "Landroid/widget/TextView;", "friendGroupMemberStatus", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeAllItem", "removeMemberFromGroup", "sendNotification", "receiverID", "setUI", "friend", "ListViewAdapter", "app_debug"})
public final class AddGroupMemberAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.lifesolutions.bd.kotlinCode.ui.adapter.AddGroupMemberAdapter.ListViewAdapter> {
    private final android.content.Context context = null;
    private final java.lang.String TAG = "AddGroupMemberAdapter";
    private java.lang.String groupId;
    private java.util.List<com.lifesolutions.bd.kotlinCode.data.model.FriendData> friendsList;
    private final com.google.firebase.database.DatabaseReference groupReference = null;
    private final android.content.SharedPreferences userPreferences = null;
    
    public AddGroupMemberAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.lifesolutions.bd.kotlinCode.ui.adapter.AddGroupMemberAdapter.ListViewAdapter onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @android.annotation.SuppressLint(value = {"SimpleDateFormat"})
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.ui.adapter.AddGroupMemberAdapter.ListViewAdapter holder, int position) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void setUI(com.lifesolutions.bd.kotlinCode.data.model.FriendData friend, com.lifesolutions.bd.kotlinCode.ui.adapter.AddGroupMemberAdapter.ListViewAdapter holder) {
    }
    
    /**
     * Extra Functions
     */
    public final void addAllFriends(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.FriendData> friendsData, @org.jetbrains.annotations.NotNull()
    java.lang.String groupId) {
    }
    
    public final void removeAllItem() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void addMemberToGroup(com.lifesolutions.bd.kotlinCode.data.model.FriendData user, android.widget.TextView textBtn) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void removeMemberFromGroup(com.lifesolutions.bd.kotlinCode.data.model.FriendData user, android.widget.TextView textBtn) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void friendGroupMemberStatus(com.lifesolutions.bd.kotlinCode.data.model.FriendData user, android.widget.TextView textBtn) {
    }
    
    /**
     * Notification
     */
    private final void sendNotification(java.lang.String groupId, java.lang.String receiverID) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b\u00a8\u0006\u000f"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/AddGroupMemberAdapter$ListViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/adapter/AddGroupMemberAdapter;Landroid/view/View;)V", "addUserBtn", "Landroid/widget/TextView;", "getAddUserBtn", "()Landroid/widget/TextView;", "profileImage", "Landroid/widget/ImageView;", "getProfileImage", "()Landroid/widget/ImageView;", "userName", "getUserName", "app_debug"})
    public final class ListViewAdapter extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView addUserBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView profileImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView userName = null;
        
        public ListViewAdapter(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getAddUserBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getProfileImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getUserName() {
            return null;
        }
    }
}