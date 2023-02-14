package com.lifesolutions.bd.kotlinCode.ui.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001!B?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0002J\u001c\u0010\u0015\u001a\u00020\u00132\n\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0011H\u0017J\u001c\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0011H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0003J0\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\n2\n\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010\u00062\b\u0010 \u001a\u0004\u0018\u00010\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMemberAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMemberAdapter$ListViewAdapter;", "context", "Landroid/content/Context;", "currentUserRole", "", "topic", "memberList", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/GroupMember;", "Lkotlin/collections/ArrayList;", "groupId", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;)V", "groupReference", "Lcom/google/firebase/database/DatabaseReference;", "getItemCount", "", "makeGroupAdmin", "", "memberId", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeMemberFromGroup", "setUI", "member", "since", "currentUserID", "ListViewAdapter", "app_debug"})
public final class GroupMemberAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMemberAdapter.ListViewAdapter> {
    private final android.content.Context context = null;
    private final java.lang.String currentUserRole = null;
    private final java.lang.String topic = null;
    private final java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.GroupMember> memberList = null;
    private final java.lang.String groupId = null;
    private final com.google.firebase.database.DatabaseReference groupReference = null;
    
    public GroupMemberAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String currentUserRole, @org.jetbrains.annotations.NotNull()
    java.lang.String topic, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.GroupMember> memberList, @org.jetbrains.annotations.Nullable()
    java.lang.String groupId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMemberAdapter.ListViewAdapter onCreateViewHolder(@org.jetbrains.annotations.NotNull()
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
    com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMemberAdapter.ListViewAdapter holder, int position) {
    }
    
    private final void setUI(com.lifesolutions.bd.kotlinCode.data.model.GroupMember member, com.lifesolutions.bd.kotlinCode.ui.adapter.GroupMemberAdapter.ListViewAdapter holder, java.lang.String since, java.lang.String currentUserID) {
    }
    
    /**
     * Extra Functions
     */
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void removeMemberFromGroup(java.lang.String memberId) {
    }
    
    private final void makeGroupAdmin(java.lang.String memberId) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\b\u00a8\u0006\u0013"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMemberAdapter$ListViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/adapter/GroupMemberAdapter;Landroid/view/View;)V", "memberSince", "Landroid/widget/TextView;", "getMemberSince", "()Landroid/widget/TextView;", "optionBtn", "Landroid/widget/ImageButton;", "getOptionBtn", "()Landroid/widget/ImageButton;", "profileImage", "Landroid/widget/ImageView;", "getProfileImage", "()Landroid/widget/ImageView;", "userName", "getUserName", "app_debug"})
    public final class ListViewAdapter extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton optionBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView profileImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView userName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView memberSince = null;
        
        public ListViewAdapter(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getOptionBtn() {
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
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getMemberSince() {
            return null;
        }
    }
}