package com.lifesolutions.bd.kotlinCode.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0005J\u0006\u0010\f\u001a\u00020\u0002J\b\u0010\r\u001a\u00020\tH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/utils/NetworkLiveState;", "Landroidx/lifecycle/LiveData;", "", "()V", "application", "Landroid/content/Context;", "networkRequest", "Landroid/net/NetworkRequest;", "getDetails", "", "init", "context", "isNetworkAvailable", "onActive", "app_debug"})
public final class NetworkLiveState extends androidx.lifecycle.LiveData<java.lang.Boolean> {
    @org.jetbrains.annotations.NotNull()
    public static final com.lifesolutions.bd.kotlinCode.utils.NetworkLiveState INSTANCE = null;
    private static android.content.Context application;
    private static android.net.NetworkRequest networkRequest;
    
    private NetworkLiveState() {
        super(null);
    }
    
    @java.lang.Override()
    protected void onActive() {
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void getDetails() {
    }
    
    public final boolean isNetworkAvailable() {
        return false;
    }
}