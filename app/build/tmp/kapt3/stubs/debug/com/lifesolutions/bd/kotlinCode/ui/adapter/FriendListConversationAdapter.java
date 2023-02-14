package com.lifesolutions.bd.kotlinCode.ui.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u001c\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\f2\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0010H\u0017J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0006\u0010\u0018\u001a\u00020\fR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/FriendListConversationAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/FriendListConversationAdapter$ListViewAdapter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "authId", "", "friendsList", "", "Lcom/lifesolutions/bd/kotlinCode/data/model/FriendData;", "addAllFriends", "", "friendsData", "Ljava/util/ArrayList;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeAllItem", "ListViewAdapter", "app_debug"})
public final class FriendListConversationAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.lifesolutions.bd.kotlinCode.ui.adapter.FriendListConversationAdapter.ListViewAdapter> {
    private final android.content.Context context = null;
    private java.lang.String authId;
    private java.util.List<com.lifesolutions.bd.kotlinCode.data.model.FriendData> friendsList;
    
    public FriendListConversationAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.lifesolutions.bd.kotlinCode.ui.adapter.FriendListConversationAdapter.ListViewAdapter onCreateViewHolder(@org.jetbrains.annotations.NotNull()
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
    com.lifesolutions.bd.kotlinCode.ui.adapter.FriendListConversationAdapter.ListViewAdapter holder, int position) {
    }
    
    /**
     * Extra Functions
     */
    public final void addAllFriends(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.FriendData> friendsData, @org.jetbrains.annotations.NotNull()
    java.lang.String authId) {
    }
    
    public final void removeAllItem() {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/FriendListConversationAdapter$ListViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/adapter/FriendListConversationAdapter;Landroid/view/View;)V", "btnChatUser", "Landroid/widget/ImageView;", "getBtnChatUser", "()Landroid/widget/ImageView;", "profileImage", "getProfileImage", "userName", "Landroid/widget/TextView;", "getUserName", "()Landroid/widget/TextView;", "app_debug"})
    public final class ListViewAdapter extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView btnChatUser = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView profileImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView userName = null;
        
        public ListViewAdapter(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getBtnChatUser() {
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