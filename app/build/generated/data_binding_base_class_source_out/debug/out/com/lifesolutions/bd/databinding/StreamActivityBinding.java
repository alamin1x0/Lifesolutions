// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.bambuser.broadcaster.SurfaceViewWithAutoAR;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class StreamActivityBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final Button BroadcastButton;

  @NonNull
  public final TextView ViewerCount;

  @NonNull
  public final CircleImageView ivProfileAuthorStream;

  @NonNull
  public final EditText liveTitle;

  @NonNull
  public final ImageView livegif;

  @NonNull
  public final SurfaceViewWithAutoAR previewSurface;

  @NonNull
  public final ImageView switchCamera;

  @NonNull
  public final Toolbar toolbarLiveStream;

  @NonNull
  public final TextView tvProfileAuthorNameStream;

  private StreamActivityBinding(@NonNull NestedScrollView rootView, @NonNull Button BroadcastButton,
      @NonNull TextView ViewerCount, @NonNull CircleImageView ivProfileAuthorStream,
      @NonNull EditText liveTitle, @NonNull ImageView livegif,
      @NonNull SurfaceViewWithAutoAR previewSurface, @NonNull ImageView switchCamera,
      @NonNull Toolbar toolbarLiveStream, @NonNull TextView tvProfileAuthorNameStream) {
    this.rootView = rootView;
    this.BroadcastButton = BroadcastButton;
    this.ViewerCount = ViewerCount;
    this.ivProfileAuthorStream = ivProfileAuthorStream;
    this.liveTitle = liveTitle;
    this.livegif = livegif;
    this.previewSurface = previewSurface;
    this.switchCamera = switchCamera;
    this.toolbarLiveStream = toolbarLiveStream;
    this.tvProfileAuthorNameStream = tvProfileAuthorNameStream;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static StreamActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static StreamActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.stream_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static StreamActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.BroadcastButton;
      Button BroadcastButton = ViewBindings.findChildViewById(rootView, id);
      if (BroadcastButton == null) {
        break missingId;
      }

      id = R.id.ViewerCount;
      TextView ViewerCount = ViewBindings.findChildViewById(rootView, id);
      if (ViewerCount == null) {
        break missingId;
      }

      id = R.id.iv_profile_author_stream;
      CircleImageView ivProfileAuthorStream = ViewBindings.findChildViewById(rootView, id);
      if (ivProfileAuthorStream == null) {
        break missingId;
      }

      id = R.id.live_title;
      EditText liveTitle = ViewBindings.findChildViewById(rootView, id);
      if (liveTitle == null) {
        break missingId;
      }

      id = R.id.livegif;
      ImageView livegif = ViewBindings.findChildViewById(rootView, id);
      if (livegif == null) {
        break missingId;
      }

      id = R.id.previewSurface;
      SurfaceViewWithAutoAR previewSurface = ViewBindings.findChildViewById(rootView, id);
      if (previewSurface == null) {
        break missingId;
      }

      id = R.id.switch_camera;
      ImageView switchCamera = ViewBindings.findChildViewById(rootView, id);
      if (switchCamera == null) {
        break missingId;
      }

      id = R.id.toolbar_live_stream;
      Toolbar toolbarLiveStream = ViewBindings.findChildViewById(rootView, id);
      if (toolbarLiveStream == null) {
        break missingId;
      }

      id = R.id.tv_profile_author_name_stream;
      TextView tvProfileAuthorNameStream = ViewBindings.findChildViewById(rootView, id);
      if (tvProfileAuthorNameStream == null) {
        break missingId;
      }

      return new StreamActivityBinding((NestedScrollView) rootView, BroadcastButton, ViewerCount,
          ivProfileAuthorStream, liveTitle, livegif, previewSurface, switchCamera,
          toolbarLiveStream, tvProfileAuthorNameStream);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
