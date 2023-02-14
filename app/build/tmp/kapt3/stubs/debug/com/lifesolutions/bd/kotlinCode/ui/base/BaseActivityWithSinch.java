package com.lifesolutions.bd.kotlinCode.ui.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u000f2\u00020\u00012\u00020\u0002:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\b\u0010\b\u001a\u00020\u0005H\u0014J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0005H\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\nH\u0016\u00a8\u0006\u0010"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/base/BaseActivityWithSinch;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/content/ServiceConnection;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onServiceConnected", "name", "Landroid/content/ComponentName;", "iBinder", "Landroid/os/IBinder;", "onServiceDisconnected", "componentName", "Companion", "app_debug"})
public abstract class BaseActivityWithSinch extends androidx.appcompat.app.AppCompatActivity implements android.content.ServiceConnection {
    @org.jetbrains.annotations.NotNull()
    public static final com.lifesolutions.bd.kotlinCode.ui.base.BaseActivityWithSinch.Companion Companion = null;
    @org.jetbrains.annotations.Nullable()
    private static com.lifesolutions.bd.Services.SinchService.SinchServiceInterface sinchServiceInterface;
    private java.util.HashMap _$_findViewCache;
    
    public BaseActivityWithSinch() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onServiceConnected(@org.jetbrains.annotations.NotNull()
    android.content.ComponentName name, @org.jetbrains.annotations.NotNull()
    android.os.IBinder iBinder) {
    }
    
    @java.lang.Override()
    public void onServiceDisconnected(@org.jetbrains.annotations.NotNull()
    android.content.ComponentName componentName) {
    }
    
    protected void onServiceConnected() {
    }
    
    private final void onServiceDisconnected() {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R0\u0010\u0006\u001a\b\u0018\u00010\u0004R\u00020\u00052\f\u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u0005@BX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/base/BaseActivityWithSinch$Companion;", "", "()V", "<set-?>", "Lcom/lifesolutions/bd/Services/SinchService$SinchServiceInterface;", "Lcom/lifesolutions/bd/Services/SinchService;", "sinchServiceInterface", "getSinchServiceInterface", "()Lcom/lifesolutions/bd/Services/SinchService$SinchServiceInterface;", "setSinchServiceInterface", "(Lcom/lifesolutions/bd/Services/SinchService$SinchServiceInterface;)V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.lifesolutions.bd.Services.SinchService.SinchServiceInterface getSinchServiceInterface() {
            return null;
        }
        
        private final void setSinchServiceInterface(com.lifesolutions.bd.Services.SinchService.SinchServiceInterface p0) {
        }
    }
}