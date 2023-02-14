// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityStreamViewListBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RecyclerView liveVideoList;

  @NonNull
  public final CircleImageView profileImageHomeToolbar;

  @NonNull
  public final Toolbar toolbarMain;

  private ActivityStreamViewListBinding(@NonNull LinearLayout rootView,
      @NonNull RecyclerView liveVideoList, @NonNull CircleImageView profileImageHomeToolbar,
      @NonNull Toolbar toolbarMain) {
    this.rootView = rootView;
    this.liveVideoList = liveVideoList;
    this.profileImageHomeToolbar = profileImageHomeToolbar;
    this.toolbarMain = toolbarMain;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStreamViewListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStreamViewListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_stream_view_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStreamViewListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.live_video_list;
      RecyclerView liveVideoList = ViewBindings.findChildViewById(rootView, id);
      if (liveVideoList == null) {
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

      return new ActivityStreamViewListBinding((LinearLayout) rootView, liveVideoList,
          profileImageHomeToolbar, toolbarMain);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
