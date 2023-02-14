package com.lifesolutions.bd.kotlinCode.ui.auth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0002J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\'H\u0002J\u0018\u0010(\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006H\u0002J\u000e\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020,J\"\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u00042\b\u00100\u001a\u0004\u0018\u000101H\u0014J\u0012\u00102\u001a\u00020\u001e2\b\u00103\u001a\u0004\u0018\u000104H\u0014J\u000e\u00105\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020,J\u000e\u00106\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020,J\u0010\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0006H\u0002J\u000e\u0010;\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020,J\b\u0010<\u001a\u00020\u001eH\u0002J\u0010\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020?H\u0002J\b\u0010@\u001a\u00020\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006A"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/auth/LoginKtActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "RC_SIGN_IN", "", "TAG", "", "countryCode", "dbRef", "Lcom/google/firebase/database/DatabaseReference;", "editor", "Landroid/content/SharedPreferences$Editor;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "mCallbackManager", "Lcom/facebook/CallbackManager;", "mGoogleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "phoneAuthCallbacks", "Lcom/google/firebase/auth/PhoneAuthProvider$OnVerificationStateChangedCallbacks;", "pinEtView", "Lcom/chaos/view/PinView;", "preferences", "Landroid/content/SharedPreferences;", "progress", "Lcom/lifesolutions/bd/kotlinCode/utils/ViewProgressDialog;", "resendToken", "Lcom/google/firebase/auth/PhoneAuthProvider$ForceResendingToken;", "storedVerificationId", "configureGoogleSignIn", "", "createNewAccount", "uid", "registrationWith", "handleFacebookAccessToken", "token", "Lcom/facebook/AccessToken;", "handleGoogleAccessToken", "acct", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "initAuthUser", "loggedInWith", "loginWithEnteredOtp", "view", "Landroid/view/View;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFacebookLogin", "onGoogleLogin", "openCompleteProfileActivity", "user", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "saveUserIdToSharedPref", "sendOtpRequest", "signInWithGoogle", "signInWithPhoneAuthCredential", "credential", "Lcom/google/firebase/auth/PhoneAuthCredential;", "updatePhoneLoginUi", "app_debug"})
public final class LoginKtActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "LoginKtActivity";
    private com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog progress;
    private com.google.firebase.auth.FirebaseAuth firebaseAuth;
    private com.google.firebase.database.DatabaseReference dbRef;
    private android.content.SharedPreferences preferences;
    private android.content.SharedPreferences.Editor editor;
    private com.chaos.view.PinView pinEtView;
    private java.lang.String storedVerificationId;
    private com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken resendToken;
    private java.lang.String countryCode = "+880";
    private com.google.android.gms.auth.api.signin.GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN = 5858;
    private com.facebook.CallbackManager mCallbackManager;
    private final com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks phoneAuthCallbacks = null;
    private java.util.HashMap _$_findViewCache;
    
    public LoginKtActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Phone Number Auth
     * With Custom Button
     */
    public final void sendOtpRequest(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void updatePhoneLoginUi() {
    }
    
    public final void loginWithEnteredOtp(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void signInWithPhoneAuthCredential(com.google.firebase.auth.PhoneAuthCredential credential) {
    }
    
    /**
     * Facebook Auth
     * Start from Here
     * With Custom Button
     */
    public final void onFacebookLogin(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void handleFacebookAccessToken(com.facebook.AccessToken token) {
    }
    
    /**
     * Google Auth
     * Start from Here
     * With Custom Button
     */
    private final void configureGoogleSignIn() {
    }
    
    public final void onGoogleLogin(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void signInWithGoogle() {
    }
    
    private final void handleGoogleAccessToken(com.google.android.gms.auth.api.signin.GoogleSignInAccount acct) {
    }
    
    /**
     * Common On Activity Result
     * For Facebook and Google
     */
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    /**
     * Check Auth
     * Store Logged In User in Firebase
     * Check New Registration
     * Save Data
     */
    private final void initAuthUser(java.lang.String uid, java.lang.String loggedInWith) {
    }
    
    private final void createNewAccount(java.lang.String uid, java.lang.String registrationWith) {
    }
    
    /**
     * Redirect new User to Profile Complete page
     */
    private final void openCompleteProfileActivity(com.lifesolutions.bd.kotlinCode.data.model.UserKt user) {
    }
    
    /**
     * Save User ID to shared preference
     */
    private final void saveUserIdToSharedPref(java.lang.String uid) {
    }
}