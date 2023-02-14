package com.lifesolutions.bd.kotlinCode.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010&\u001a\u00020\'H\u0002J\b\u0010(\u001a\u00020\'H\u0002J\u0012\u0010)\u001a\u00020\'2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J&\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u0001012\b\u0010*\u001a\u0004\u0018\u00010+H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/fragments/ChatListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "allConversation", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/Conversation;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "authId", "c", "", "getC", "()I", "setC", "(I)V", "conversationsAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/ConversationsAdapter;", "currentUserFIre", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "isLoading", "", "isScrolling", "loadCount", "loadItem", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mReference", "Lcom/google/firebase/database/DatabaseReference;", "getMReference", "()Lcom/google/firebase/database/DatabaseReference;", "setMReference", "(Lcom/google/firebase/database/DatabaseReference;)V", "uID", "userPathRef", "getConversationLists", "", "getUnreadConversationCount", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "app_debug"})
public final class ChatListFragment extends androidx.fragment.app.Fragment {
    private final java.lang.String TAG = "ChatListFragment";
    private com.google.firebase.database.FirebaseDatabase db;
    private com.google.firebase.auth.FirebaseAuth auth;
    private com.lifesolutions.bd.kotlinCode.ui.adapter.ConversationsAdapter conversationsAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.Conversation> allConversation;
    private int loadItem = 25;
    private int loadCount = 1;
    private boolean isScrolling = false;
    private boolean isLoading = false;
    private com.google.firebase.database.DatabaseReference userPathRef;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.database.DatabaseReference mReference;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUserFIre;
    private java.lang.String uID;
    private java.lang.String authId;
    private int c = 0;
    private java.util.HashMap _$_findViewCache;
    
    public ChatListFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.database.DatabaseReference getMReference() {
        return null;
    }
    
    public final void setMReference(@org.jetbrains.annotations.Nullable()
    com.google.firebase.database.DatabaseReference p0) {
    }
    
    public final int getC() {
        return 0;
    }
    
    public final void setC(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void getConversationLists() {
    }
    
    private final void getUnreadConversationCount() {
    }
}