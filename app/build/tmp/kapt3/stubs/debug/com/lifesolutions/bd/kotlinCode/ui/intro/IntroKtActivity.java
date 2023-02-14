package com.lifesolutions.bd.kotlinCode.ui.intro;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/lifesolutions/bd/kotlinCode/ui/intro/IntroKtActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "introViewPagerAdapter", "Lcom/lifesolutions/bd/kotlinCode/ui/intro/IntroViewPagerAdapter;", "position", "", "isIntroViewed", "", "loadLastScreen", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "savePrefsIntroData", "app_debug"})
public final class IntroKtActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.lifesolutions.bd.kotlinCode.ui.intro.IntroViewPagerAdapter introViewPagerAdapter;
    private int position = 0;
    private java.util.HashMap _$_findViewCache;
    
    public IntroKtActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadLastScreen() {
    }
    
    /**
     * Save Intro View Data on Shared Preference
     */
    private final void savePrefsIntroData() {
    }
    
    private final boolean isIntroViewed() {
        return false;
    }
}