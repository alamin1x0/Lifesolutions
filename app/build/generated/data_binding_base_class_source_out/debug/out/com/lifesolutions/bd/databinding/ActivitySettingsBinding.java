// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySettingsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppBarLayout appBarSettings;

  @NonNull
  public final TextView textViewAbout;

  @NonNull
  public final TextView textViewRoutine;

  @NonNull
  public final Toolbar toolbarSettings;

  private ActivitySettingsBinding(@NonNull LinearLayout rootView,
      @NonNull AppBarLayout appBarSettings, @NonNull TextView textViewAbout,
      @NonNull TextView textViewRoutine, @NonNull Toolbar toolbarSettings) {
    this.rootView = rootView;
    this.appBarSettings = appBarSettings;
    this.textViewAbout = textViewAbout;
    this.textViewRoutine = textViewRoutine;
    this.toolbarSettings = toolbarSettings;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appBar_settings;
      AppBarLayout appBarSettings = ViewBindings.findChildViewById(rootView, id);
      if (appBarSettings == null) {
        break missingId;
      }

      id = R.id.textView_about;
      TextView textViewAbout = ViewBindings.findChildViewById(rootView, id);
      if (textViewAbout == null) {
        break missingId;
      }

      id = R.id.textView_routine;
      TextView textViewRoutine = ViewBindings.findChildViewById(rootView, id);
      if (textViewRoutine == null) {
        break missingId;
      }

      id = R.id.toolbar_settings;
      Toolbar toolbarSettings = ViewBindings.findChildViewById(rootView, id);
      if (toolbarSettings == null) {
        break missingId;
      }

      return new ActivitySettingsBinding((LinearLayout) rootView, appBarSettings, textViewAbout,
          textViewRoutine, toolbarSettings);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}