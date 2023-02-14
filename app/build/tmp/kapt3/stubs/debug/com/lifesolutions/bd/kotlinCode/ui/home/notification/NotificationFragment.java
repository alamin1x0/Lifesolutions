package com.lifesolutions.bd.kotlinCode.ui.home.notification;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0012\u0010\u0018\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010!\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/notification/NotificationFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "databaseReference", "Lcom/google/firebase/database/DatabaseReference;", "list", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/Models/NotificationInApp;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "notificationAdapter", "Lcom/lifesolutions/bd/Adapters/NotificationListAdapter;", "getNotificationAdapter", "()Lcom/lifesolutions/bd/Adapters/NotificationListAdapter;", "setNotificationAdapter", "(Lcom/lifesolutions/bd/Adapters/NotificationListAdapter;)V", "uID", "viewModel", "Lcom/lifesolutions/bd/kotlinCode/ui/home/notification/NotificationViewModel;", "clearAllNotifications", "", "getNotificationList", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "openAlertDialog", "app_debug"})
public final class NotificationFragment extends androidx.fragment.app.Fragment {
    private final java.lang.String TAG = "NotificationFragment";
    private com.lifesolutions.bd.kotlinCode.ui.home.notification.NotificationViewModel viewModel;
    private java.util.ArrayList<com.lifesolutions.bd.Models.NotificationInApp> list;
    @org.jetbrains.annotations.Nullable()
    private com.lifesolutions.bd.Adapters.NotificationListAdapter notificationAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private com.google.firebase.database.DatabaseReference databaseReference;
    private java.lang.String uID;
    private java.util.HashMap _$_findViewCache;
    
    public NotificationFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lifesolutions.bd.Adapters.NotificationListAdapter getNotificationAdapter() {
        return null;
    }
    
    public final void setNotificationAdapter(@org.jetbrains.annotations.Nullable()
    com.lifesolutions.bd.Adapters.NotificationListAdapter p0) {
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
    
    private final void getNotificationList() {
    }
    
    private final void clearAllNotifications() {
    }
    
    private final void openAlertDialog() {
    }
}