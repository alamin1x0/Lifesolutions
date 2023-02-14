package com.lifesolutions.bd.kotlinCode.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u0010\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u0001J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u0004\u0018\u00010\nJ\u0012\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u000fH\u0007J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001b\u001a\u00020\u001cJ%\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00132\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0 H\u0007\u00a2\u0006\u0002\u0010!J&\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/utils/Utils;", "", "()V", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "addPostToDB", "", "post", "Lcom/lifesolutions/bd/kotlinCode/data/model/PostKt;", "authId", "", "generateReferCode", "getDateTime", "timeInMilli", "getDateTimeAgo", "", "getImageUri", "Landroid/net/Uri;", "inContext", "Landroid/content/Context;", "inImage", "Landroid/graphics/Bitmap;", "getLocalIpAddress", "getNormalDate", "timeInMillies", "getRealPathFromURI", "contentURI", "context", "Landroid/app/Activity;", "hasPermissions", "", "permissions", "", "(Landroid/content/Context;[Ljava/lang/String;)Z", "sendNotification", "receiverID", "firstName", "message", "postId", "app_debug"})
public final class Utils {
    @org.jetbrains.annotations.NotNull()
    public static final com.lifesolutions.bd.kotlinCode.utils.Utils INSTANCE = null;
    private static com.google.firebase.database.FirebaseDatabase database;
    
    private Utils() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String generateReferCode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLocalIpAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @android.annotation.SuppressLint(value = {"SimpleDateFormat"})
    public final java.lang.String getNormalDate(long timeInMillies) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDateTimeAgo(long timeInMilli) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final boolean hasPermissions(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRealPathFromURI(@org.jetbrains.annotations.Nullable()
    android.net.Uri contentURI, @org.jetbrains.annotations.NotNull()
    android.app.Activity context) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getImageUri(@org.jetbrains.annotations.NotNull()
    android.content.Context inContext, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap inImage) {
        return null;
    }
    
    public final void addPostToDB(@org.jetbrains.annotations.NotNull()
    com.lifesolutions.bd.kotlinCode.data.model.PostKt post, @org.jetbrains.annotations.NotNull()
    java.lang.String authId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDateTime(@org.jetbrains.annotations.NotNull()
    java.lang.Object timeInMilli) {
        return null;
    }
    
    public final void sendNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String receiverID, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String postId) {
    }
}