// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChooseLiveBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button goLiveActivity;

  @NonNull
  public final Button goWatchActivity;

  @NonNull
  public final CircleImageView profileImageHomeToolbar;

  @NonNull
  public final Toolbar toolbarMain;

  private ActivityChooseLiveBinding(@NonNull LinearLayout rootView, @NonNull Button goLiveActivity,
      @NonNull Button goWatchActivity, @NonNull CircleImageView profileImageHomeToolbar,
      @NonNull Toolbar toolbarMain) {
    this.rootView = rootView;
    this.goLiveActivity = goLiveActivity;
    this.goWatchActivity = goWatchActivity;
    this.profileImageHomeToolbar = profileImageHomeToolbar;
    this.toolbarMain = toolbarMain;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChooseLiveBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChooseLiveBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_choose_live, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChooseLiveBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.goLiveActivity;
      Button goLiveActivity = ViewBindings.findChildViewById(rootView, id);
      if (goLiveActivity == null) {
        break missingId;
      }

      id = R.id.goWatchActivity;
      Button goWatchActivity = ViewBindings.findChildViewById(rootView, id);
      if (goWatchActivity == null) {
        break missingId;
      }

      id = R.id.profile_image_home_toolbar;
      CircleImageView profileImageHomeToolbar = ViewBindings.findChildViewById(rootView, id);
      if (profileImageHomeToolbar == null) {
        break missingId;
      }

      id = R.id.toolbar_main;
      Toolbar toolbarMain = ViewBindings.findChildViewById(rootView, id);
      if (toolbarMain == null) {
        break missingId;
      }

      return new ActivityChooseLiveBinding((LinearLayout) rootView, goLiveActivity, goWatchActivity,
          profileImageHomeToolbar, toolbarMain);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
