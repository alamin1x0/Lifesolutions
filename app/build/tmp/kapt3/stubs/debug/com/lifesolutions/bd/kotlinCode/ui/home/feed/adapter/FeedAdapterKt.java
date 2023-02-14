package com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u000689:;<=B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010H\u0016J \u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0016H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0016J\u0018\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0018\u0010$\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\tH\u0002J \u0010&\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010\'\u001a\u00020\t2\u0006\u0010(\u001a\u00020)H\u0002J\"\u0010*\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\t2\b\u0010+\u001a\u0004\u0018\u00010\t2\u0006\u0010,\u001a\u00020\u0014H\u0002J\u0018\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u001eH\u0017J\u0018\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u001eH\u0016J\b\u00103\u001a\u00020\u000eH\u0016J(\u00104\u001a\u00020\u000e2\u0006\u00105\u001a\u00020\t2\u0006\u00106\u001a\u00020\t2\u0006\u00107\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\tH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006>"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "listener", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/FeedItemOnClickListener;", "context", "Landroid/content/Context;", "(Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/FeedItemOnClickListener;Landroid/content/Context;)V", "TAG", "", "feeds", "", "Lcom/lifesolutions/bd/kotlinCode/data/model/PostKt;", "addAllPosts", "", "newPosts", "Ljava/util/ArrayList;", "getAuthorInfo", "uid", "imageProfile", "Landroid/widget/ImageView;", "authorName", "Landroid/widget/TextView;", "getCommentsCount", "postID", "comments", "getDateTime", "timeInMilli", "", "getItemCount", "", "getItemViewType", "position", "getLikesCount", "postId", "likeCountView", "getReactCount", "getUserInfo", "isLiked", "userID", "likeButton", "Lcom/like/LikeButton;", "isReacted", "uID", "likeBtn", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeAllItem", "sendNotification", "receiverID", "firstName", "message", "ViewHolderBigText", "ViewHolderWithImage", "ViewHolderWithLink", "ViewHolderWithMultiImage", "ViewHolderWithOnlyText", "ViewHolderWithVideo", "app_debug"})
public class FeedAdapterKt extends androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private final com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener listener = null;
    private final android.content.Context context = null;
    private final java.lang.String TAG = "FeedAdapterKt";
    private java.util.List<com.lifesolutions.bd.kotlinCode.data.model.PostKt> feeds;
    
    public FeedAdapterKt(@org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener listener, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    private final void isReacted(java.lang.String postID, java.lang.String uID, android.widget.ImageView likeBtn) {
    }
    
    private final void getReactCount(java.lang.String postId, android.widget.TextView likeCountView) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    /**
     * Essential Feed Data Binding Method
     */
    public void addAllPosts(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.PostKt> newPosts) {
    }
    
    public void removeAllItem() {
    }
    
    /**
     * External Function
     * Get Author Info
     * Get Liked by User
     * Get Like Count
     * Get Normal Date
     * Get Share Function
     */
    private final void getAuthorInfo(java.lang.String uid, android.widget.ImageView imageProfile, android.widget.TextView authorName) {
    }
    
    private final void isLiked(java.lang.String postId, java.lang.String userID, com.like.LikeButton likeButton) {
    }
    
    private final void getLikesCount(java.lang.String postId, android.widget.TextView likeCountView) {
    }
    
    private final void getCommentsCount(java.lang.String postID, android.widget.TextView comments) {
    }
    
    private final java.lang.String getDateTime(java.lang.Object timeInMilli) {
        return null;
    }
    
    private final void sendNotification(java.lang.String receiverID, java.lang.String firstName, java.lang.String message, java.lang.String postId) {
    }
    
    private final void getUserInfo(java.lang.String uid) {
    }
    
    /**
     * Different View Holder Type
     */
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0011\u0010\u001b\u001a\u00020\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\fR\u0011\u0010!\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u0011\u0010#\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0012\u00a8\u0006%"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt$ViewHolderWithMultiImage;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;Landroid/view/View;)V", "authorImage", "Landroid/widget/ImageView;", "getAuthorImage", "()Landroid/widget/ImageView;", "authorName", "Landroid/widget/TextView;", "getAuthorName", "()Landroid/widget/TextView;", "commentsCountView", "getCommentsCountView", "commentsLayout", "Landroid/widget/LinearLayout;", "getCommentsLayout", "()Landroid/widget/LinearLayout;", "gridView", "Landroidx/recyclerview/widget/RecyclerView;", "getGridView", "()Landroidx/recyclerview/widget/RecyclerView;", "likeBtn", "getLikeBtn", "likeCountView", "getLikeCountView", "optionBtn", "Landroid/widget/ImageButton;", "getOptionBtn", "()Landroid/widget/ImageButton;", "postDesc", "getPostDesc", "postTime", "getPostTime", "shareLayout", "getShareLayout", "app_debug"})
    public final class ViewHolderWithMultiImage extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postDesc = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postTime = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView authorImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView authorName = null;
        @org.jetbrains.annotations.NotNull()
        private final androidx.recyclerview.widget.RecyclerView gridView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout commentsLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView commentsCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout shareLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView likeCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView likeBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton optionBtn = null;
        
        public ViewHolderWithMultiImage(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostDesc() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getAuthorImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getAuthorName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.RecyclerView getGridView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getCommentsLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCommentsCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getShareLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLikeCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getLikeBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getOptionBtn() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0011\u0010\u001b\u001a\u00020\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\fR\u0011\u0010!\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u0011\u0010#\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR\u0011\u0010%\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0012\u00a8\u0006\'"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt$ViewHolderWithImage;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;Landroid/view/View;)V", "authorImage", "Landroid/widget/ImageView;", "getAuthorImage", "()Landroid/widget/ImageView;", "authorName", "Landroid/widget/TextView;", "getAuthorName", "()Landroid/widget/TextView;", "commentsCountView", "getCommentsCountView", "commentsLayout", "Landroid/widget/LinearLayout;", "getCommentsLayout", "()Landroid/widget/LinearLayout;", "feedImage", "getFeedImage", "heartView", "getHeartView", "likeBtn", "getLikeBtn", "likeCountView", "getLikeCountView", "optionBtn", "Landroid/widget/ImageButton;", "getOptionBtn", "()Landroid/widget/ImageButton;", "postDesc", "getPostDesc", "postImage", "getPostImage", "postTime", "getPostTime", "shareLayout", "getShareLayout", "app_debug"})
    public final class ViewHolderWithImage extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView postImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postDesc = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView authorImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView authorName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postTime = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout commentsLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView commentsCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout shareLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView likeCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView likeBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView feedImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView heartView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton optionBtn = null;
        
        public ViewHolderWithImage(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getPostImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostDesc() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getAuthorImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getAuthorName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getCommentsLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCommentsCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getShareLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLikeCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getLikeBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getFeedImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getHeartView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getOptionBtn() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\fR\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u0011\u0010#\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0012\u00a8\u0006%"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt$ViewHolderWithVideo;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;Landroid/view/View;)V", "authorImage", "Landroid/widget/ImageView;", "getAuthorImage", "()Landroid/widget/ImageView;", "authorName", "Landroid/widget/TextView;", "getAuthorName", "()Landroid/widget/TextView;", "commentsCountView", "getCommentsCountView", "commentsLayout", "Landroid/widget/LinearLayout;", "getCommentsLayout", "()Landroid/widget/LinearLayout;", "likeBtn", "getLikeBtn", "likeCountView", "getLikeCountView", "optionBtn", "Landroid/widget/ImageButton;", "getOptionBtn", "()Landroid/widget/ImageButton;", "postDesc", "getPostDesc", "postImage", "Lfm/jiecao/jcvideoplayer_lib/JCVideoPlayerStandard;", "getPostImage", "()Lfm/jiecao/jcvideoplayer_lib/JCVideoPlayerStandard;", "postTime", "getPostTime", "shareLayout", "getShareLayout", "app_debug"})
    public final class ViewHolderWithVideo extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard postImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postDesc = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView authorImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView authorName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postTime = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout commentsLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView commentsCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout shareLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView likeCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView likeBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton optionBtn = null;
        
        public ViewHolderWithVideo(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard getPostImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostDesc() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getAuthorImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getAuthorName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getCommentsLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCommentsCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getShareLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLikeCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getLikeBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getOptionBtn() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020,R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R\u0011\u0010\u001f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\fR\u0011\u0010!\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u0011\u0010#\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0012R\u0011\u0010%\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\fR\u0011\u0010\'\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0012R\u0011\u0010)\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0012\u00a8\u0006."}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt$ViewHolderWithOnlyText;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;Landroid/view/View;)V", "authorImage", "Landroid/widget/ImageView;", "getAuthorImage", "()Landroid/widget/ImageView;", "authorName", "Landroid/widget/TextView;", "getAuthorName", "()Landroid/widget/TextView;", "commentsCountView", "getCommentsCountView", "commentsLayout", "Landroid/widget/LinearLayout;", "getCommentsLayout", "()Landroid/widget/LinearLayout;", "heartView", "getHeartView", "likeBtn", "getLikeBtn", "likeCountView", "getLikeCountView", "optionBtn", "Landroid/widget/ImageButton;", "getOptionBtn", "()Landroid/widget/ImageButton;", "postColorLayout", "getPostColorLayout", "postDesc", "getPostDesc", "postTime", "getPostTime", "rawTextLayout", "getRawTextLayout", "rawTextView", "getRawTextView", "shareLayout", "getShareLayout", "textImageLayout", "getTextImageLayout", "initClick", "", "viewAnimatedHeart", "app_debug"})
    public final class ViewHolderWithOnlyText extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout postColorLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postDesc = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout rawTextLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView rawTextView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView authorImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView authorName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postTime = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout commentsLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView commentsCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout shareLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView likeCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView likeBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView heartView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout textImageLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton optionBtn = null;
        
        public ViewHolderWithOnlyText(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getPostColorLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostDesc() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getRawTextLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getRawTextView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getAuthorImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getAuthorName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getCommentsLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCommentsCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getShareLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLikeCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getLikeBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getHeartView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getTextImageLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getOptionBtn() {
            return null;
        }
        
        public final void initClick() {
        }
        
        public final void viewAnimatedHeart() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0011\u0010\u0017\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0011\u0010\u001b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\fR\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u0011\u0010#\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR\u0011\u0010%\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0012R\u0011\u0010\'\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0012R\u0011\u0010)\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0012\u00a8\u0006+"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt$ViewHolderWithLink;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;Landroid/view/View;)V", "authorImage", "Landroid/widget/ImageView;", "getAuthorImage", "()Landroid/widget/ImageView;", "authorName", "Landroid/widget/TextView;", "getAuthorName", "()Landroid/widget/TextView;", "commentsCountView", "getCommentsCountView", "commentsLayout", "Landroid/widget/LinearLayout;", "getCommentsLayout", "()Landroid/widget/LinearLayout;", "likeBtn", "getLikeBtn", "likeCountView", "getLikeCountView", "linkImage", "getLinkImage", "linkSource", "getLinkSource", "linkTitle", "getLinkTitle", "optionBtn", "Landroid/widget/ImageButton;", "getOptionBtn", "()Landroid/widget/ImageButton;", "postDesc", "getPostDesc", "postTime", "getPostTime", "rawTextLayout", "getRawTextLayout", "shareLayout", "getShareLayout", "viewLayout", "getViewLayout", "app_debug"})
    public final class ViewHolderWithLink extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout viewLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postDesc = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout rawTextLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView authorImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView authorName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postTime = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout commentsLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView commentsCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout shareLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView likeCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView likeBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView linkImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView linkTitle = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView linkSource = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton optionBtn = null;
        
        public ViewHolderWithLink(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getViewLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostDesc() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getRawTextLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getAuthorImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getAuthorName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getCommentsLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCommentsCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getShareLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLikeCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getLikeBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getLinkImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLinkTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLinkSource() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getOptionBtn() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\fR\u0011\u0010\u001d\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\fR\u0011\u0010\u001f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\fR\u0011\u0010!\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u0011\u0010#\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0012R\u0011\u0010%\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0012\u00a8\u0006\'"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt$ViewHolderBigText;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;Landroid/view/View;)V", "authorImage", "Landroid/widget/ImageView;", "getAuthorImage", "()Landroid/widget/ImageView;", "authorName", "Landroid/widget/TextView;", "getAuthorName", "()Landroid/widget/TextView;", "commentsCountView", "getCommentsCountView", "commentsLayout", "Landroid/widget/LinearLayout;", "getCommentsLayout", "()Landroid/widget/LinearLayout;", "likeBtn", "getLikeBtn", "likeCountView", "getLikeCountView", "optionBtn", "Landroid/widget/ImageButton;", "getOptionBtn", "()Landroid/widget/ImageButton;", "postDesc", "getPostDesc", "postDesc2", "getPostDesc2", "postTime", "getPostTime", "seeMore", "getSeeMore", "shareLayout", "getShareLayout", "viewLayout", "getViewLayout", "app_debug"})
    public final class ViewHolderBigText extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout viewLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postDesc = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postDesc2 = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView seeMore = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView authorImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView authorName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView postTime = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout commentsLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView commentsCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout shareLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView likeCountView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView likeBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton optionBtn = null;
        
        public ViewHolderBigText(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getViewLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostDesc() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostDesc2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getSeeMore() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getAuthorImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getAuthorName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPostTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getCommentsLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCommentsCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getShareLayout() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLikeCountView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getLikeBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getOptionBtn() {
            return null;
        }
    }
}