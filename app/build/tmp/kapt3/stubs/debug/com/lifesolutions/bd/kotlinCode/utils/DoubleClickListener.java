package com.lifesolutions.bd.kotlinCode.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/utils/DoubleClickListener;", "Landroid/view/View$OnClickListener;", "doubleClickTimeLimitMills", "", "callback", "Lcom/lifesolutions/bd/kotlinCode/utils/DoubleClickListener$Callback;", "(JLcom/lifesolutions/bd/kotlinCode/utils/DoubleClickListener$Callback;)V", "lastClicked", "getTimeDiff", "from", "to", "isDoubleClicked", "", "onClick", "", "v", "Landroid/view/View;", "Callback", "app_debug"})
public final class DoubleClickListener implements android.view.View.OnClickListener {
    private final long doubleClickTimeLimitMills = 0L;
    private final com.lifesolutions.bd.kotlinCode.utils.DoubleClickListener.Callback callback = null;
    private long lastClicked = -1L;
    
    public DoubleClickListener(long doubleClickTimeLimitMills, @org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.utils.DoubleClickListener.Callback callback) {
        super();
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    private final long getTimeDiff(long from, long to) {
        return 0L;
    }
    
    private final boolean isDoubleClicked() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/utils/DoubleClickListener$Callback;", "", "doubleClicked", "", "app_debug"})
    public static abstract interface Callback {
        
        public abstract void doubleClicked();
    }
}