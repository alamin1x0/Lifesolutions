package com.lifesolutions.bd.kotlinCode.ui.home.feed;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00ca\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u0002032\u0006\u0010>\u001a\u00020\bH\u0002J\b\u0010?\u001a\u00020<H\u0002J\u001e\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\b0DH\u0002J\u0012\u0010E\u001a\u00020<2\b\u0010F\u001a\u0004\u0018\u00010\u0014H\u0002J\u0010\u0010G\u001a\u00020<2\u0006\u0010=\u001a\u000203H\u0002J\b\u0010H\u001a\u00020<H\u0002J\b\u0010I\u001a\u00020<H\u0002J\b\u0010J\u001a\u00020<H\u0002J\b\u0010K\u001a\u00020<H\u0002J\b\u0010L\u001a\u00020<H\u0002J\u0010\u0010M\u001a\u00020<2\u0006\u0010N\u001a\u00020\bH\u0002J\u0016\u0010O\u001a\u00020<2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020Q0DH\u0002J0\u0010R\u001a\b\u0012\u0004\u0012\u0002HS0D\"\u0004\b\u0000\u0010S2\f\u0010T\u001a\b\u0012\u0004\u0012\u0002HS0D2\f\u0010U\u001a\b\u0012\u0004\u0012\u0002HS0DH\u0002J\u0012\u0010V\u001a\u00020<2\b\u0010W\u001a\u0004\u0018\u00010XH\u0016J&\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010[\u001a\u00020\\2\b\u0010]\u001a\u0004\u0018\u00010^2\b\u0010W\u001a\u0004\u0018\u00010XH\u0016J \u0010_\u001a\u00020<2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020a0D2\b\u0010b\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010c\u001a\u00020<2\u0006\u0010d\u001a\u00020Z2\u0006\u0010e\u001a\u000203H\u0016J\b\u0010f\u001a\u00020<H\u0002J\b\u0010g\u001a\u00020<H\u0002J\u0018\u0010h\u001a\u00020<2\u0006\u0010A\u001a\u00020B2\u0006\u0010i\u001a\u00020jH\u0002J\u0018\u0010k\u001a\u00020<2\u0006\u0010A\u001a\u00020B2\u0006\u0010i\u001a\u00020jH\u0002J\u0006\u0010l\u001a\u00020<J\u0010\u0010m\u001a\u00020<2\u0006\u0010n\u001a\u00020\bH\u0002J\b\u0010o\u001a\u00020<H\u0002J\b\u0010p\u001a\u00020<H\u0002J\u0010\u0010q\u001a\u00020<2\u0006\u0010=\u001a\u000203H\u0002J\u0018\u0010r\u001a\u00020<2\u0006\u0010s\u001a\u00020Z2\u0006\u0010=\u001a\u000203H\u0002J\b\u0010t\u001a\u00020<H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0016j\n\u0012\u0004\u0012\u00020\u0014\u0018\u0001`\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010%\u001a\u0016\u0012\u0004\u0012\u00020$\u0018\u00010\u0016j\n\u0012\u0004\u0012\u00020$\u0018\u0001`\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\'\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010(R\u000e\u0010)\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010/\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0016j\b\u0012\u0004\u0012\u00020\b`\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u00102\u001a\u0016\u0012\u0004\u0012\u000203\u0018\u00010\u0016j\n\u0012\u0004\u0012\u000203\u0018\u0001`\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u00108\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0010\"\u0004\b:\u0010\u0012\u00a8\u0006u"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/FeedFragment;", "Lcom/lifesolutions/bd/kotlinCode/ui/home/base/BaseFragment;", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/FeedItemOnClickListener;", "Lcom/kroegerama/imgpicker/BottomSheetImagePicker$OnImagesSelectedListener;", "()V", "POST_COUNT", "", "TAG", "", "activeUserRef", "Lcom/google/firebase/database/DatabaseReference;", "animatedLoading", "Lcom/lifesolutions/bd/kotlinCode/utils/AnimatedLoading;", "authId", "c", "getC", "()I", "setC", "(I)V", "compressedImage", "Ljava/io/File;", "compressedImageMulti", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "currentUser", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "currentUserFIre", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "dbPostRef", "feedAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/adapter/FeedAdapterKt;", "finalLastKey", "hasAccessToCreatePost", "", "imageViewBitmap", "Landroid/graphics/Bitmap;", "imageViewBitmapList", "isFinishedPosts", "isLoading", "Ljava/lang/Boolean;", "isScrolling", "listenerActiveList", "Lcom/google/firebase/database/ValueEventListener;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mReference", "pickedImgUris", "postDesc", "postLastKey", "postsContainer", "Lcom/lifesolutions/bd/kotlinCode/data/model/PostKt;", "uID", "userPathRef", "viewModel", "Lcom/lifesolutions/bd/kotlinCode/ui/home/feed/FeedViewModel;", "x", "getX", "setX", "EditUserPost", "", "feed", "txt", "checkInfoList", "createMultiImagePost", "epicDialog", "Landroid/app/Dialog;", "multiImageUrl", "", "customCompressImage", "actualImage", "deleteUserPost", "getActiveUserLists", "getInitPosts", "getMorePosts", "getUser", "getUserInfo", "givePostBonus", "authorId", "initActiveUser", "users", "Lcom/lifesolutions/bd/kotlinCode/data/model/ActiveUser;", "merge", "T", "first", "second", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onImagesSelected", "uris", "Landroid/net/Uri;", "tag", "onRecyclerViewItemClicked", "view", "postItem", "openProfileCompleteAlertDialog", "pickSingle", "postWithMultiImageUpload", "btnUpload", "Landroid/widget/Button;", "postWithSingleImageUpload", "refresh", "removeServerImage", "imageLink", "showComingDialog", "showDialog", "showDialogEdit", "showMenu", "v", "showNotificationBadges", "app_debug"})
public final class FeedFragment extends com.lifesolutions.bd.kotlinCode.ui.home.base.BaseFragment implements com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener, com.kroegerama.imgpicker.BottomSheetImagePicker.OnImagesSelectedListener {
    private final java.lang.String TAG = "FeedFragment";
    private com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedViewModel viewModel;
    private com.google.firebase.database.FirebaseDatabase database;
    private com.google.firebase.database.DatabaseReference dbPostRef;
    private com.google.firebase.database.DatabaseReference mReference;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter.FeedAdapterKt feedAdapter;
    private java.lang.String authId;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUser;
    private boolean hasAccessToCreatePost = false;
    private final int POST_COUNT = 6;
    private java.util.ArrayList<com.lifesolutions.bd.kotlinCode.data.model.PostKt> postsContainer;
    private java.lang.String postLastKey;
    private java.lang.String finalLastKey;
    private boolean isScrolling = false;
    private boolean isFinishedPosts = false;
    private java.lang.Boolean isLoading;
    private java.io.File compressedImage;
    private java.util.ArrayList<java.io.File> compressedImageMulti;
    private android.graphics.Bitmap imageViewBitmap;
    private java.util.ArrayList<android.graphics.Bitmap> imageViewBitmapList;
    private java.util.ArrayList<java.lang.String> pickedImgUris;
    private java.lang.String postDesc = "";
    private int c = 0;
    private com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading animatedLoading;
    private com.google.firebase.database.DatabaseReference activeUserRef;
    private com.google.firebase.database.ValueEventListener listenerActiveList;
    private com.google.firebase.database.DatabaseReference userPathRef;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentUserFIre;
    private java.lang.String uID;
    private int x = 0;
    private java.util.HashMap _$_findViewCache;
    
    public FeedFragment() {
        super();
    }
    
    public final int getC() {
        return 0;
    }
    
    public final void setC(int p0) {
    }
    
    public final int getX() {
        return 0;
    }
    
    public final void setX(int p0) {
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
    
    private final void checkInfoList() {
    }
    
    private final void getUser() {
    }
    
    private final void getInitPosts() {
    }
    
    private final void getMorePosts() {
    }
    
    /**
     * Get Current User Data
     */
    private final void getUserInfo() {
    }
    
    /**
     * On Recycler view Item Click
     */
    @java.lang.Override()
    public void onRecyclerViewItemClicked(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.data.model.PostKt postItem) {
    }
    
    private final void showNotificationBadges() {
    }
    
    /**
     * Essential Methods
     * Merge Two Array
     * Material Alert Dialog
     */
    private final <T extends java.lang.Object>java.util.List<T> merge(java.util.List<? extends T> first, java.util.List<? extends T> second) {
        return null;
    }
    
    private final void openProfileCompleteAlertDialog() {
    }
    
    /**
     * Bottom Sheet Image Picker
     */
    private final void customCompressImage(java.io.File actualImage) {
    }
    
    @java.lang.Override()
    public void onImagesSelected(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris, @org.jetbrains.annotations.Nullable()
    java.lang.String tag) {
    }
    
    private final void pickSingle() {
    }
    
    private final void postWithMultiImageUpload(android.app.Dialog epicDialog, android.widget.Button btnUpload) {
    }
    
    private final void postWithSingleImageUpload(android.app.Dialog epicDialog, android.widget.Button btnUpload) {
    }
    
    private final void createMultiImagePost(android.app.Dialog epicDialog, java.util.List<java.lang.String> multiImageUrl) {
    }
    
    private final void showDialog() {
    }
    
    public final void refresh() {
    }
    
    /**
     * Remove Post
     */
    private final void showDialogEdit(com.lifesolutions.bd.kotlinCode.data.model.PostKt feed) {
    }
    
    private final void EditUserPost(com.lifesolutions.bd.kotlinCode.data.model.PostKt feed, java.lang.String txt) {
    }
    
    private final void showMenu(android.view.View v, com.lifesolutions.bd.kotlinCode.data.model.PostKt feed) {
    }
    
    private final void deleteUserPost(com.lifesolutions.bd.kotlinCode.data.model.PostKt feed) {
    }
    
    private final void removeServerImage(java.lang.String imageLink) {
    }
    
    /**
     * Give Bonus
     */
    private final void givePostBonus(java.lang.String authorId) {
    }
    
    /**
     * Additional Dialog
     */
    private final void showComingDialog() {
    }
    
    private final void initActiveUser(java.util.List<com.lifesolutions.bd.kotlinCode.data.model.ActiveUser> users) {
    }
    
    private final void getActiveUserLists() {
    }
}