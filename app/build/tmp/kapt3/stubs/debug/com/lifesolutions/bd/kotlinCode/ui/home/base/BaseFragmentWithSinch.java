package com.lifesolutions.bd.kotlinCode.ui.home.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\b\u0018\u00010\u0005R\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0014J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0014J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u001a\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0014\u0010\u0004\u001a\b\u0018\u00010\u0005R\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/home/base/BaseFragmentWithSinch;", "Landroidx/fragment/app/Fragment;", "Landroid/content/ServiceConnection;", "()V", "mSinchServiceInterface", "Lcom/lifesolutions/bd/Services/SinchService$SinchServiceInterface;", "Lcom/lifesolutions/bd/Services/SinchService;", "getSinchServiceInterface", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", "iBinder", "Landroid/os/IBinder;", "onServiceDisconnected", "componentName", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public abstract class BaseFragmentWithSinch extends androidx.fragment.app.Fragment implements android.content.ServiceConnection {
    private com.lifesolutions.bd.Services.SinchService.SinchServiceInterface mSinchServiceInterface;
    private java.util.HashMap _$_findViewCache;
    
    public BaseFragmentWithSinch() {
        super();
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onServiceConnected(@org.jetbrains.annotations.NotNull()
    android.content.ComponentName name, @org.jetbrains.annotations.Nullable()
    android.os.IBinder iBinder) {
    }
    
    @java.lang.Override()
    public void onServiceDisconnected(@org.jetbrains.annotations.NotNull()
    android.content.ComponentName componentName) {
    }
    
    protected void onServiceConnected() {
    }
    
    protected void onServiceDisconnected() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.lifesolutions.bd.Services.SinchService.SinchServiceInterface getSinchServiceInterface() {
        return null;
    }
}