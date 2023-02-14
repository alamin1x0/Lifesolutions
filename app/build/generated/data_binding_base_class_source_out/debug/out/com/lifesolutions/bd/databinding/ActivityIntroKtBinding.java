// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityIntroKtBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnGetStarted;

  @NonNull
  public final MaterialButton btnNext;

  @NonNull
  public final ViewPager screenViewpager;

  @NonNull
  public final TabLayout tabIndicator;

  @NonNull
  public final TextView tvSkip;

  private ActivityIntroKtBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnGetStarted, @NonNull MaterialButton btnNext,
      @NonNull ViewPager screenViewpager, @NonNull TabLayout tabIndicator,
      @NonNull TextView tvSkip) {
    this.rootView = rootView;
    this.btnGetStarted = btnGetStarted;
    this.btnNext = btnNext;
    this.screenViewpager = screenViewpager;
    this.tabIndicator = tabIndicator;
    this.tvSkip = tvSkip;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityIntroKtBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityIntroKtBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_intro_kt, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityIntroKtBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_get_started;
      MaterialButton btnGetStarted = ViewBindings.findChildViewById(rootView, id);
      if (btnGetStarted == null) {
        break missingId;
      }

      id = R.id.btn_next;
      MaterialButton btnNext = ViewBindings.findChildViewById(rootView, id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.screen_viewpager;
      ViewPager screenViewpager = ViewBindings.findChildViewById(rootView, id);
      if (screenViewpager == null) {
        break missingId;
      }

      id = R.id.tab_indicator;
      TabLayout tabIndicator = ViewBindings.findChildViewById(rootView, id);
      if (tabIndicator == null) {
        break missingId;
      }

      id = R.id.tv_skip;
      TextView tvSkip = ViewBindings.findChildViewById(rootView, id);
      if (tvSkip == null) {
        break missingId;
      }

      return new ActivityIntroKtBinding((ConstraintLayout) rootView, btnGetStarted, btnNext,
          screenViewpager, tabIndicator, tvSkip);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
