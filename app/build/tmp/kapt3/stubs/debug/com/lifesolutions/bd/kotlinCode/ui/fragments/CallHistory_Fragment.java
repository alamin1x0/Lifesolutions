package com.lifesolutions.bd.kotlinCode.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0012\u0010\u0016\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/fragments/CallHistory_Fragment;", "Landroidx/fragment/app/Fragment;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "callLogAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/CallLogAdapter;", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "finalContacts", "", "Lcom/github/tamir7/contacts/Contact;", "getFinalContacts", "()Ljava/util/List;", "finalContactsID", "", "getFinalContactsID", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "deleteCallLogs", "", "getCallLists", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "openAlertDialog", "app_debug"})
public final class CallHistory_Fragment extends androidx.fragment.app.Fragment {
    private com.google.firebase.database.FirebaseDatabase db;
    private com.google.firebase.auth.FirebaseAuth auth;
    private com.lifesolutions.bd.kotlinCode.ui.adapter.CallLogAdapter callLogAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.github.tamir7.contacts.Contact> finalContacts = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> finalContactsID = null;
    private java.util.HashMap _$_findViewCache;
    
    public CallHistory_Fragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.github.tamir7.contacts.Contact> getFinalContacts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getFinalContactsID() {
        return null;
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
    
    private final void getCallLists() {
    }
    
    private final void openAlertDialog() {
    }
    
    private final void deleteCallLogs() {
    }
}