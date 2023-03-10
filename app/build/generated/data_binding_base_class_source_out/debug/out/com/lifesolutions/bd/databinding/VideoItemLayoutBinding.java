// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class VideoItemLayoutBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView imageViewVideoItem;

  @NonNull
  public final RelativeLayout itemVideoItem;

  @NonNull
  public final TextView titleVideoItem;

  private VideoItemLayoutBinding(@NonNull RelativeLayout rootView,
      @NonNull ImageView imageViewVideoItem, @NonNull RelativeLayout itemVideoItem,
      @NonNull TextView titleVideoItem) {
    this.rootView = rootView;
    this.imageViewVideoItem = imageViewVideoItem;
    this.itemVideoItem = itemVideoItem;
    this.titleVideoItem = titleVideoItem;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static VideoItemLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static VideoItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.video_item_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static VideoItemLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.image_view_video_item;
      ImageView imageViewVideoItem = ViewBindings.findChildViewById(rootView, id);
      if (imageViewVideoItem == null) {
        break missingId;
      }

      RelativeLayout itemVideoItem = (RelativeLayout) rootView;

      id = R.id.title_video_item;
      TextView titleVideoItem = ViewBindings.findChildViewById(rootView, id);
      if (titleVideoItem == null) {
        break missingId;
      }

      return new VideoItemLayoutBinding((RelativeLayout) rootView, imageViewVideoItem,
          itemVideoItem, titleVideoItem);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
