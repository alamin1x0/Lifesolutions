package com.lifesolutions.bd.kotlinCode.ui.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\n2\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000eH\u0017J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\u0006\u0010\u0016\u001a\u00020\nR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/CallLogAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/CallLogAdapter$ListViewAdapter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "callLogList", "", "Lcom/lifesolutions/bd/kotlinCode/data/model/CallLog;", "addAllConversation", "", "newConversation", "Ljava/util/ArrayList;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeAllItem", "ListViewAdapter", "app_debug"})
public final class CallLogAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.lifesolutions.bd.kotlinCode.ui.adapter.CallLogAdapter.ListViewAdapter> {
    private final android.content.Context context = null;
    private java.util.List<com.lifesolutions.bd.kotlinCode.data.model.CallLog> callLogList;
    
    public CallLogAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.lifesolutions.bd.kotlinCode.ui.adapter.CallLogAdapter.ListViewAdapter onCreateViewHolder(@org.jetbrains.annotations.NotNull()
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
    com.lifesolutions.bd.kotlinCode.ui.adapter.CallLogAdapter.ListViewAdapter holder, int position) {
    }
    
    /**
     * Extra Functions
     */
    public final void addAllConversation(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.CallLog> newConversation) {
    }
    
    public final void removeAllItem() {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012\u00a8\u0006\u0017"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/adapter/CallLogAdapter$ListViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/adapter/CallLogAdapter;Landroid/view/View;)V", "callStatusIcon", "Landroid/widget/ImageView;", "getCallStatusIcon", "()Landroid/widget/ImageView;", "callTypeIcon", "getCallTypeIcon", "conversationLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getConversationLayout", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "dateConversation", "Landroid/widget/TextView;", "getDateConversation", "()Landroid/widget/TextView;", "profileImage", "getProfileImage", "userName", "getUserName", "app_debug"})
    public final class ListViewAdapter extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final androidx.constraintlayout.widget.ConstraintLayout conversationLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView profileImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView userName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView dateConversation = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView callTypeIcon = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView callStatusIcon = null;
        
        public ListViewAdapter(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.constraintlayout.widget.ConstraintLayout getConversationLayout() {
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
        public final android.widget.TextView getDateConversation() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getCallTypeIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getCallStatusIcon() {
            return null;
        }
    }
}