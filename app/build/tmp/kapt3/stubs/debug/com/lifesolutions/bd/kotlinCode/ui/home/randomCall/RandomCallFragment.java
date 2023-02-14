package com.lifesolutions.bd.kotlinCode.ui.home.randomCall;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001bJ\b\u0010\u001d\u001a\u00020\u001bH\u0002J\u0016\u0010\u001e\u001a\u00020\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\u0012\u0010\"\u001a\u00020\u001b2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J&\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010+\u001a\u00020\u001bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/randomCall/RandomCallFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "activeUserRef", "Lcom/google/firebase/database/DatabaseReference;", "adapter", "Lcom/lifesolutions/bd/Adapters/CourseAdapterJava;", "getAdapter", "()Lcom/lifesolutions/bd/Adapters/CourseAdapterJava;", "setAdapter", "(Lcom/lifesolutions/bd/Adapters/CourseAdapterJava;)V", "allCourseUrl", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "authId", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "firstName", "lastName", "listenerActiveList", "Lcom/google/firebase/database/ValueEventListener;", "uId", "viewModel", "Lcom/lifesolutions/bd/kotlinCode/ui/home/randomCall/RandomCallViewModel;", "addTeacherData", "", "addTestData", "getActiveUserLists", "initActiveUser", "users", "", "Lcom/lifesolutions/bd/kotlinCode/data/model/ActiveUser;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "app_debug"})
public final class RandomCallFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.lifesolutions.bd.Adapters.CourseAdapterJava adapter;
    private java.lang.String firstName;
    private java.lang.String lastName;
    private final java.lang.String TAG = "RandomCallFragment";
    private com.lifesolutions.bd.kotlinCode.ui.home.randomCall.RandomCallViewModel viewModel;
    private com.google.firebase.database.FirebaseDatabase database;
    private java.lang.String authId;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private java.lang.String uId;
    private com.google.firebase.database.DatabaseReference activeUserRef;
    private com.google.firebase.database.ValueEventListener listenerActiveList;
    private java.lang.String allCourseUrl;
    private java.util.HashMap _$_findViewCache;
    
    public RandomCallFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lifesolutions.bd.Adapters.CourseAdapterJava getAdapter() {
        return null;
    }
    
    public final void setAdapter(@org.jetbrains.annotations.Nullable()
    com.lifesolutions.bd.Adapters.CourseAdapterJava p0) {
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
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void getActiveUserLists() {
    }
    
    private final void initActiveUser(java.util.List<com.lifesolutions.bd.kotlinCode.data.model.ActiveUser> users) {
    }
    
    /**
     * Group List
     */
    public final void addTeacherData() {
    }
    
    public final void addTestData() {
    }
}