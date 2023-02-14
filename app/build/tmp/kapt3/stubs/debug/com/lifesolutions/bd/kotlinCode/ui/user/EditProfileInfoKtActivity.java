package com.lifesolutions.bd.kotlinCode.ui.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ\u0012\u0010\u001b\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J \u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0002J\b\u0010\"\u001a\u00020\u0016H\u0002J\u0010\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020%H\u0002J\u000e\u0010&\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/user/EditProfileInfoKtActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "BLOODS", "", "", "[Ljava/lang/String;", "GENDERS", "TAG", "authId", "birthDate", "", "Ljava/lang/Long;", "dateSetListener", "Landroid/app/DatePickerDialog$OnDateSetListener;", "dbRef", "Lcom/google/firebase/database/DatabaseReference;", "progress", "Lcom/lifesolutions/bd/kotlinCode/utils/ViewProgressDialog;", "userPreferences", "Landroid/content/SharedPreferences;", "initializeDatePicker", "", "initializeSelectableItem", "onCancelEdit", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveUserIdToSharedPref", "uID", "firstName", "lastName", "setExistsUserData", "setUserDataToEditText", "user", "Lcom/lifesolutions/bd/kotlinCode/data/model/UserKt;", "updateUserInfo", "app_debug"})
public final class EditProfileInfoKtActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "EditProfileInfoActivity";
    private android.content.SharedPreferences userPreferences;
    private com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog progress;
    private com.google.firebase.database.DatabaseReference dbRef;
    private java.lang.String authId;
    private android.app.DatePickerDialog.OnDateSetListener dateSetListener;
    private java.lang.Long birthDate;
    private final java.lang.String[] GENDERS = {"Male", "Female", "Others"};
    private final java.lang.String[] BLOODS = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    private java.util.HashMap _$_findViewCache;
    
    public EditProfileInfoKtActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void updateUserInfo(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void setExistsUserData() {
    }
    
    private final void setUserDataToEditText(com.lifesolutions.bd.kotlinCode.data.model.UserKt user) {
    }
    
    private final void initializeDatePicker() {
    }
    
    private final void initializeSelectableItem() {
    }
    
    public final void onCancelEdit(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Update Shared Preference
     */
    private final void saveUserIdToSharedPref(java.lang.String uID, java.lang.String firstName, java.lang.String lastName) {
    }
}