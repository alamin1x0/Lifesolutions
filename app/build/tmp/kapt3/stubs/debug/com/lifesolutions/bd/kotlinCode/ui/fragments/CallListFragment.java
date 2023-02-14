package com.lifesolutions.bd.kotlinCode.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u000208H\u0002J\b\u0010:\u001a\u000208H\u0002J\u0016\u0010;\u001a\u0002082\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00150=H\u0002J\b\u0010>\u001a\u000208H\u0002J\u0012\u0010?\u001a\u0002082\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J&\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010G2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\b\u0010H\u001a\u000208H\u0016J-\u0010I\u001a\u0002082\u0006\u0010J\u001a\u00020\u00042\u000e\u0010K\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060L2\u0006\u0010M\u001a\u00020NH\u0016\u00a2\u0006\u0002\u0010OJ\b\u0010P\u001a\u000208H\u0002J\u0016\u0010Q\u001a\u0002082\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00150=H\u0002J\u0010\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R&\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\'X\u0082.\u00a2\u0006\u0002\n\u0000R&\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001d\"\u0004\b*\u0010\u001fR\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u000102X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106\u00a8\u0006U"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/fragments/CallListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "READ_CONTACT_PERMISSION_REQUEST_CODE", "", "TAG", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "callLogAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/CallLogAdapter;", "client", "Lcom/algolia/search/saas/Client;", "getClient", "()Lcom/algolia/search/saas/Client;", "setClient", "(Lcom/algolia/search/saas/Client;)V", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "finalContacts", "Ljava/util/ArrayList;", "Lcom/github/tamir7/contacts/Contact;", "getFinalContacts", "()Ljava/util/ArrayList;", "finalContactsID", "getFinalContactsID", "ids", "Ljava/util/HashMap;", "getIds", "()Ljava/util/HashMap;", "setIds", "(Ljava/util/HashMap;)V", "index", "Lcom/algolia/search/saas/Index;", "getIndex", "()Lcom/algolia/search/saas/Index;", "setIndex", "(Lcom/algolia/search/saas/Index;)V", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "nos", "getNos", "setNos", "progressBar", "Landroid/widget/ProgressBar;", "getProgressBar", "()Landroid/widget/ProgressBar;", "setProgressBar", "(Landroid/widget/ProgressBar;)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "AlgoliaSearch", "", "checkPermission", "deleteCallLogs", "doSync", "contacts", "", "getCallLists", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "openAlertDialog", "sendInvites", "syncContract", "", "no", "app_debug"})
public final class CallListFragment extends androidx.fragment.app.Fragment {
    private final java.lang.String TAG = "CallListFragment";
    private com.google.firebase.database.FirebaseDatabase db;
    private com.google.firebase.auth.FirebaseAuth auth;
    private com.lifesolutions.bd.kotlinCode.ui.adapter.CallLogAdapter callLogAdapter;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    @org.jetbrains.annotations.NotNull()
    private com.algolia.search.saas.Client client;
    @org.jetbrains.annotations.NotNull()
    private com.algolia.search.saas.Index index;
    @org.jetbrains.annotations.NotNull()
    private java.util.HashMap<java.lang.String, java.lang.String> nos;
    @org.jetbrains.annotations.NotNull()
    private java.util.HashMap<java.lang.String, java.lang.String> ids;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<com.github.tamir7.contacts.Contact> finalContacts = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<java.lang.String> finalContactsID = null;
    @org.jetbrains.annotations.Nullable()
    private android.widget.ProgressBar progressBar;
    @org.jetbrains.annotations.Nullable()
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private final int READ_CONTACT_PERMISSION_REQUEST_CODE = 76;
    private java.util.HashMap _$_findViewCache;
    
    public CallListFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.algolia.search.saas.Client getClient() {
        return null;
    }
    
    public final void setClient(@org.jetbrains.annotations.NotNull()
    com.algolia.search.saas.Client p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.algolia.search.saas.Index getIndex() {
        return null;
    }
    
    public final void setIndex(@org.jetbrains.annotations.NotNull()
    com.algolia.search.saas.Index p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<java.lang.String, java.lang.String> getNos() {
        return null;
    }
    
    public final void setNos(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<java.lang.String, java.lang.String> getIds() {
        return null;
    }
    
    public final void setIds(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.github.tamir7.contacts.Contact> getFinalContacts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.String> getFinalContactsID() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.ProgressBar getProgressBar() {
        return null;
    }
    
    public final void setProgressBar(@org.jetbrains.annotations.Nullable()
    android.widget.ProgressBar p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.recyclerview.widget.RecyclerView getRecyclerView() {
        return null;
    }
    
    public final void setRecyclerView(@org.jetbrains.annotations.Nullable()
    androidx.recyclerview.widget.RecyclerView p0) {
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
    
    private final void checkPermission() {
    }
    
    private final void sendInvites(java.util.List<com.github.tamir7.contacts.Contact> contacts) {
    }
    
    private final void doSync(java.util.List<com.github.tamir7.contacts.Contact> contacts) {
    }
    
    private final boolean syncContract(java.lang.String no) {
        return false;
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void AlgoliaSearch() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
}