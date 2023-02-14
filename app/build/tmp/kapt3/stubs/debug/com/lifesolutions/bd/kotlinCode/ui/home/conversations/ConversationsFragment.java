package com.lifesolutions.bd.kotlinCode.ui.home.conversations;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0016\u0010\u001c\u001a\u00020\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0002J\u0012\u0010 \u001a\u00020\u001b2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J&\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010(2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0016\u0010)\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/conversations/ConversationsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "activeUserRef", "Lcom/google/firebase/database/DatabaseReference;", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "authId", "badgeDrawable", "Lcom/google/android/material/badge/BadgeDrawable;", "callHistory_Fragment", "Lcom/lifesolutions/bd/kotlinCode/ui/fragments/CallHistory_Fragment;", "callListFragment", "Lcom/lifesolutions/bd/kotlinCode/ui/fragments/CallListFragment;", "chatLisFragment", "Lcom/lifesolutions/bd/kotlinCode/ui/fragments/ChatListFragment;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "groupListFragment", "Lcom/lifesolutions/bd/kotlinCode/ui/fragments/GroupListFragment;", "listenerActiveList", "Lcom/google/firebase/database/ValueEventListener;", "viewModel", "Lcom/lifesolutions/bd/kotlinCode/ui/home/conversations/ConversationsViewModel;", "getActiveUserLists", "", "initActiveUser", "users", "", "Lcom/lifesolutions/bd/kotlinCode/data/model/ActiveUser;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "showCountBadgeOnSingleChat", "count", "", "visibility", "", "app_debug"})
public final class ConversationsFragment extends androidx.fragment.app.Fragment {
    private final java.lang.String TAG = "ConversationsFragment";
    private com.lifesolutions.bd.kotlinCode.ui.home.conversations.ConversationsViewModel viewModel;
    private com.lifesolutions.bd.kotlinCode.ui.fragments.ChatListFragment chatLisFragment;
    private com.lifesolutions.bd.kotlinCode.ui.fragments.GroupListFragment groupListFragment;
    private com.lifesolutions.bd.kotlinCode.ui.fragments.CallListFragment callListFragment;
    private com.lifesolutions.bd.kotlinCode.ui.fragments.CallHistory_Fragment callHistory_Fragment;
    private com.google.android.material.badge.BadgeDrawable badgeDrawable;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private com.google.firebase.database.DatabaseReference activeUserRef;
    private com.google.firebase.database.ValueEventListener listenerActiveList;
    private com.google.firebase.database.FirebaseDatabase database;
    private java.lang.String authId;
    private java.util.HashMap _$_findViewCache;
    
    public ConversationsFragment() {
        super();
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
    
    public final void showCountBadgeOnSingleChat(int count, boolean visibility) {
    }
    
    private final void getActiveUserLists() {
    }
    
    private final void initActiveUser(java.util.List<com.lifesolutions.bd.kotlinCode.data.model.ActiveUser> users) {
    }
}