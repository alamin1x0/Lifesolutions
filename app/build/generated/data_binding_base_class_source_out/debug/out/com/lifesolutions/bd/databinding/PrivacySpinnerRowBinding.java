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

public final class PrivacySpinnerRowBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView imageViewPrivacy;

  @NonNull
  public final TextView textViewPrivacy;

  private PrivacySpinnerRowBinding(@NonNull RelativeLayout rootView,
      @NonNull ImageView imageViewPrivacy, @NonNull TextView textViewPrivacy) {
    this.rootView = rootView;
    this.imageViewPrivacy = imageViewPrivacy;
    this.textViewPrivacy = textViewPrivacy;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PrivacySpinnerRowBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PrivacySpinnerRowBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.privacy_spinner_row, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PrivacySpinnerRowBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.image_view_privacy;
      ImageView imageViewPrivacy = ViewBindings.findChildViewById(rootView, id);
      if (imageViewPrivacy == null) {
        break missingId;
      }

      id = R.id.text_view_privacy;
      TextView textViewPrivacy = ViewBindings.findChildViewById(rootView, id);
      if (textViewPrivacy == null) {
        break missingId;
      }

      return new PrivacySpinnerRowBinding((RelativeLayout) rootView, imageViewPrivacy,
          textViewPrivacy);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
