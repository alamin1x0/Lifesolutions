package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u0012\u0010\u001a\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020\fH\u0016J\b\u0010!\u001a\u00020\u0015H\u0002J\b\u0010\"\u001a\u00020\u0015H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/CourseSingleActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "course", "Lcom/lifesolutions/bd/kotlinCode/data/model/Course;", "courseVideoAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/adapter/CourseVideoAdapter;", "currentUser", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "isEnrolled", "", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "playList", "Ljava/util/ArrayList;", "Lcom/lifesolutions/bd/kotlinCode/data/model/CourseVideo;", "userPreferences", "Landroid/content/SharedPreferences;", "enrollCourse", "", "enrolledUi", "getEnrolledInfo", "getUserInfo", "getVideoLists", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onEnroll", "view", "Landroid/view/View;", "onSupportNavigateUp", "setCourseData", "showDialog", "app_debug"})
public final class CourseSingleActivity extends androidx.appcompat.app.AppCompatActivity {
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private com.lifesolutions.bd.kotlinCode.ui.adapter.CourseVideoAdapter courseVideoAdapter;
    private java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.CourseVideo> playList;
    private boolean isEnrolled = false;
    private com.lifesolutions.bd.kotlinCode.data.model.Course course;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUser;
    private com.google.firebase.database.FirebaseDatabase database;
    private android.content.SharedPreferences userPreferences;
    private java.util.HashMap _$_findViewCache;
    
    public CourseSingleActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setCourseData() {
    }
    
    private final void enrolledUi() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    public final void onEnroll(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void showDialog() {
    }
    
    private final void getVideoLists() {
    }
    
    private final void getEnrolledInfo() {
    }
    
    private final void getUserInfo() {
    }
    
    private final void enrollCourse() {
    }
}