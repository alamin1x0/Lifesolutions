package com.lifesolutions.bd.kotlinCode.ui.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020 H\u0016J\u0012\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0015J\b\u0010$\u001a\u00020 H\u0016J\b\u0010%\u001a\u00020&H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/activities/WebViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "()V", "mSwipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "getMSwipeRefreshLayout", "()Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "setMSwipeRefreshLayout", "(Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;)V", "mWebView", "Landroid/webkit/WebView;", "getMWebView", "()Landroid/webkit/WebView;", "setMWebView", "(Landroid/webkit/WebView;)V", "progressBar", "Landroid/widget/ProgressBar;", "getProgressBar", "()Landroid/widget/ProgressBar;", "setProgressBar", "(Landroid/widget/ProgressBar;)V", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "getToolbar", "()Landroidx/appcompat/widget/Toolbar;", "setToolbar", "(Landroidx/appcompat/widget/Toolbar;)V", "webName", "", "webUrl", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onScrollChanged", "onSupportNavigateUp", "", "app_debug"})
public final class WebViewActivity extends androidx.appcompat.app.AppCompatActivity implements android.view.ViewTreeObserver.OnScrollChangedListener {
    private java.lang.String webUrl;
    private java.lang.String webName;
    @org.jetbrains.annotations.Nullable()
    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout mSwipeRefreshLayout;
    @org.jetbrains.annotations.Nullable()
    private android.widget.ProgressBar progressBar;
    @org.jetbrains.annotations.Nullable()
    private android.webkit.WebView mWebView;
    @org.jetbrains.annotations.Nullable()
    private androidx.appcompat.widget.Toolbar toolbar;
    private java.util.HashMap _$_findViewCache;
    
    public WebViewActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.swiperefreshlayout.widget.SwipeRefreshLayout getMSwipeRefreshLayout() {
        return null;
    }
    
    public final void setMSwipeRefreshLayout(@org.jetbrains.annotations.Nullable()
    androidx.swiperefreshlayout.widget.SwipeRefreshLayout p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.ProgressBar getProgressBar() {
        return null;
    }
    
    public final void setProgressBar(@org.jetbrains.annotations.Nullable()
    android.widget.ProgressBar p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.webkit.WebView getMWebView() {
        return null;
    }
    
    public final void setMWebView(@org.jetbrains.annotations.Nullable()
    android.webkit.WebView p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.appcompat.widget.Toolbar getToolbar() {
        return null;
    }
    
    public final void setToolbar(@org.jetbrains.annotations.Nullable()
    androidx.appcompat.widget.Toolbar p0) {
    }
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @java.lang.Override()
    public void onScrollChanged() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
}