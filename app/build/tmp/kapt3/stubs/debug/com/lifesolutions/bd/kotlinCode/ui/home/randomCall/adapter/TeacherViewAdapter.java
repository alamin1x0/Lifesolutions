package com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001(B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\"\u0010\u0016\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\"\u0010\u001a\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001c\u0010\u001b\u001a\u00020\f2\n\u0010\u001c\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0011H\u0017J\u001c\u0010\u001e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0011H\u0016J\u0017\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010#H\u0002\u00a2\u0006\u0002\u0010%J\u0010\u0010&\u001a\u00020\f2\u0006\u0010\'\u001a\u00020\u0007H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/randomCall/adapter/TeacherViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lifesolutions/bd/kotlinCode/ui/home/randomCall/adapter/TeacherViewAdapter$ViewHolder;", "context", "Landroid/content/Context;", "users", "", "Lcom/lifesolutions/bd/kotlinCode/data/model/Teacher;", "(Landroid/content/Context;Ljava/util/List;)V", "TAG", "", "callUserAudio", "", "receiverId", "dialog", "Landroid/app/Dialog;", "getItemCount", "", "getOnlineStatus", "uId", "badge", "Landroid/view/View;", "getUserCallDuration", "callChild", "tvCallDuration", "Landroid/widget/TextView;", "getUserCallDurationForRaindomCallFragment", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "secondToMin", "", "sec", "(Ljava/lang/Long;)J", "showDialog", "teacher", "ViewHolder", "app_debug"})
public final class TeacherViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter.TeacherViewAdapter.ViewHolder> {
    private final android.content.Context context = null;
    private final java.util.List<com.lifesolutions.bd.kotlinCode.data.model.Teacher> users = null;
    private final java.lang.String TAG = "TeachersViewAdapter";
    
    public TeacherViewAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.lifesolutions.bd.kotlinCode.data.model.Teacher> users) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter.TeacherViewAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter.TeacherViewAdapter.ViewHolder holder, int position) {
    }
    
    private final void showDialog(com.lifesolutions.bd.kotlinCode.data.model.Teacher teacher) {
    }
    
    private final void callUserAudio(java.lang.String receiverId, android.app.Dialog dialog) {
    }
    
    private final void getOnlineStatus(java.lang.String uId, android.view.View badge) {
    }
    
    private final void getUserCallDuration(java.lang.String uId, java.lang.String callChild, android.widget.TextView tvCallDuration) {
    }
    
    private final void getUserCallDurationForRaindomCallFragment(java.lang.String uId, java.lang.String callChild, android.widget.TextView tvCallDuration) {
    }
    
    private final long secondToMin(java.lang.Long sec) {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/randomCall/adapter/TeacherViewAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/home/randomCall/adapter/TeacherViewAdapter;Landroid/view/View;)V", "call_duration", "Landroid/widget/TextView;", "getCall_duration", "()Landroid/widget/TextView;", "image", "Landroid/widget/ImageView;", "getImage", "()Landroid/widget/ImageView;", "name", "getName", "onlineBadge", "getOnlineBadge", "()Landroid/view/View;", "rating", "Landroid/widget/RatingBar;", "getRating", "()Landroid/widget/RatingBar;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView name = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView image = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.RatingBar rating = null;
        @org.jetbrains.annotations.NotNull()
        private final android.view.View onlineBadge = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView call_duration = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.RatingBar getRating() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.view.View getOnlineBadge() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCall_duration() {
            return null;
        }
    }
}