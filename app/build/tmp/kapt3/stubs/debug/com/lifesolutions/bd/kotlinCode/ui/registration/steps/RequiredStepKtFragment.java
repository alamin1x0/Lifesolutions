package com.lifesolutions.bd.kotlinCode.ui.registration.steps;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\fJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0012\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\"\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J&\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010.\u001a\u00020\u001fH\u0002J\b\u0010/\u001a\u00020\u001fH\u0002J\b\u00100\u001a\u00020\u001fH\u0002J\b\u00101\u001a\u00020\u001fH\u0002J\b\u00102\u001a\u00020\u001fH\u0002J\u0018\u00103\u001a\u00020\u001f2\u0006\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u0005H\u0002J\u0012\u00106\u001a\u00020\u001f2\b\u00107\u001a\u0004\u0018\u00010\u0005H\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/registration/steps/RequiredStepKtFragment;", "Landroidx/fragment/app/Fragment;", "()V", "GENDERS", "", "", "[Ljava/lang/String;", "PReqCode", "", "TAG", "authId", "bitmapThumbnail", "Landroid/graphics/Bitmap;", "currentAuthUser", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "dbRef", "Lcom/google/firebase/database/DatabaseReference;", "firstName", "gender", "lastName", "pickedImgUri", "Landroid/net/Uri;", "progress", "Lcom/lifesolutions/bd/kotlinCode/utils/ViewProgressDialog;", "getImageUri", "inContext", "Landroid/content/Context;", "inImage", "navigateToIntroActivity", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "openAlertDialog", "registerUserWithImage", "requiredFragmentToAdditionalFragment", "selectImage", "setCurrentUserInfo", "setUserReferCode", "uID", "refCode", "updateUserInfo", "imageUrl", "app_debug"})
public final class RequiredStepKtFragment extends androidx.fragment.app.Fragment {
    private final java.lang.String TAG = "RequiredStepKtFragment";
    private com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog progress;
    private com.lifesolutions.bd.kotlinCode.data.model.UserKt currentAuthUser;
    private final java.lang.String[] GENDERS = {"Male", "Female", "Others"};
    private final int PReqCode = 2;
    private android.net.Uri pickedImgUri;
    private android.graphics.Bitmap bitmapThumbnail;
    private com.google.firebase.database.DatabaseReference dbRef;
    private java.lang.String authId;
    private com.google.firebase.database.FirebaseDatabase database;
    private java.lang.String firstName;
    private java.lang.String lastName;
    private java.lang.String gender;
    private java.util.HashMap _$_findViewCache;
    
    public RequiredStepKtFragment() {
        super();
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
    
    private final void setCurrentUserInfo() {
    }
    
    private final void selectImage() {
    }
    
    private final void updateUserInfo(java.lang.String imageUrl) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void registerUserWithImage() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getImageUri(@org.jetbrains.annotations.NotNull()
    android.content.Context inContext, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap inImage) {
        return null;
    }
    
    private final void openAlertDialog() {
    }
    
    private final void requiredFragmentToAdditionalFragment() {
    }
    
    private final void setUserReferCode(java.lang.String uID, java.lang.String refCode) {
    }
    
    private final void navigateToIntroActivity() {
    }
}