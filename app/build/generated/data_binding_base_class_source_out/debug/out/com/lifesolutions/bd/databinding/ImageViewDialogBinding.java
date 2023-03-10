// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ImageViewDialogBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MaterialButton btnUploadCancel;

  @NonNull
  public final MaterialButton btnUploadConfirm;

  @NonNull
  public final EditText etPostDesc;

  @NonNull
  public final ImageView ivPickedImage;

  @NonNull
  public final ImageView ivPickedImage2;

  @NonNull
  public final ImageView ivPickedImage22;

  @NonNull
  public final ImageView ivPickedImage3;

  @NonNull
  public final ImageView ivPickedImage32;

  @NonNull
  public final ImageView ivPickedImage33;

  @NonNull
  public final ImageView ivPickedImage41;

  @NonNull
  public final ImageView ivPickedImage42;

  @NonNull
  public final ImageView ivPickedImage43;

  @NonNull
  public final ImageView ivPickedImage44;

  @NonNull
  public final LinearLayout singleview;

  @NonNull
  public final LinearLayout singleview2;

  @NonNull
  public final LinearLayout singleview3;

  @NonNull
  public final LinearLayout singleview4;

  @NonNull
  public final TextView tvPrivacy;

  private ImageViewDialogBinding(@NonNull LinearLayout rootView,
      @NonNull MaterialButton btnUploadCancel, @NonNull MaterialButton btnUploadConfirm,
      @NonNull EditText etPostDesc, @NonNull ImageView ivPickedImage,
      @NonNull ImageView ivPickedImage2, @NonNull ImageView ivPickedImage22,
      @NonNull ImageView ivPickedImage3, @NonNull ImageView ivPickedImage32,
      @NonNull ImageView ivPickedImage33, @NonNull ImageView ivPickedImage41,
      @NonNull ImageView ivPickedImage42, @NonNull ImageView ivPickedImage43,
      @NonNull ImageView ivPickedImage44, @NonNull LinearLayout singleview,
      @NonNull LinearLayout singleview2, @NonNull LinearLayout singleview3,
      @NonNull LinearLayout singleview4, @NonNull TextView tvPrivacy) {
    this.rootView = rootView;
    this.btnUploadCancel = btnUploadCancel;
    this.btnUploadConfirm = btnUploadConfirm;
    this.etPostDesc = etPostDesc;
    this.ivPickedImage = ivPickedImage;
    this.ivPickedImage2 = ivPickedImage2;
    this.ivPickedImage22 = ivPickedImage22;
    this.ivPickedImage3 = ivPickedImage3;
    this.ivPickedImage32 = ivPickedImage32;
    this.ivPickedImage33 = ivPickedImage33;
    this.ivPickedImage41 = ivPickedImage41;
    this.ivPickedImage42 = ivPickedImage42;
    this.ivPickedImage43 = ivPickedImage43;
    this.ivPickedImage44 = ivPickedImage44;
    this.singleview = singleview;
    this.singleview2 = singleview2;
    this.singleview3 = singleview3;
    this.singleview4 = singleview4;
    this.tvPrivacy = tvPrivacy;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ImageViewDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ImageViewDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.image_view_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ImageViewDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_upload_cancel;
      MaterialButton btnUploadCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnUploadCancel == null) {
        break missingId;
      }

      id = R.id.btn_upload_confirm;
      MaterialButton btnUploadConfirm = ViewBindings.findChildViewById(rootView, id);
      if (btnUploadConfirm == null) {
        break missingId;
      }

      id = R.id.et_post_desc;
      EditText etPostDesc = ViewBindings.findChildViewById(rootView, id);
      if (etPostDesc == null) {
        break missingId;
      }

      id = R.id.iv_picked_image;
      ImageView ivPickedImage = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage == null) {
        break missingId;
      }

      id = R.id.iv_picked_image2;
      ImageView ivPickedImage2 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage2 == null) {
        break missingId;
      }

      id = R.id.iv_picked_image22;
      ImageView ivPickedImage22 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage22 == null) {
        break missingId;
      }

      id = R.id.iv_picked_image3;
      ImageView ivPickedImage3 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage3 == null) {
        break missingId;
      }

      id = R.id.iv_picked_image32;
      ImageView ivPickedImage32 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage32 == null) {
        break missingId;
      }

      id = R.id.iv_picked_image33;
      ImageView ivPickedImage33 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage33 == null) {
        break missingId;
      }

      id = R.id.iv_picked_image41;
      ImageView ivPickedImage41 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage41 == null) {
        break missingId;
      }

      id = R.id.iv_picked_image42;
      ImageView ivPickedImage42 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage42 == null) {
        break missingId;
      }

      id = R.id.iv_picked_image43;
      ImageView ivPickedImage43 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage43 == null) {
        break missingId;
      }

      id = R.id.iv_picked_image44;
      ImageView ivPickedImage44 = ViewBindings.findChildViewById(rootView, id);
      if (ivPickedImage44 == null) {
        break missingId;
      }

      id = R.id.singleview;
      LinearLayout singleview = ViewBindings.findChildViewById(rootView, id);
      if (singleview == null) {
        break missingId;
      }

      id = R.id.singleview2;
      LinearLayout singleview2 = ViewBindings.findChildViewById(rootView, id);
      if (singleview2 == null) {
        break missingId;
      }

      id = R.id.singleview3;
      LinearLayout singleview3 = ViewBindings.findChildViewById(rootView, id);
      if (singleview3 == null) {
        break missingId;
      }

      id = R.id.singleview4;
      LinearLayout singleview4 = ViewBindings.findChildViewById(rootView, id);
      if (singleview4 == null) {
        break missingId;
      }

      id = R.id.tv_privacy;
      TextView tvPrivacy = ViewBindings.findChildViewById(rootView, id);
      if (tvPrivacy == null) {
        break missingId;
      }

      return new ImageViewDialogBinding((LinearLayout) rootView, btnUploadCancel, btnUploadConfirm,
          etPostDesc, ivPickedImage, ivPickedImage2, ivPickedImage22, ivPickedImage3,
          ivPickedImage32, ivPickedImage33, ivPickedImage41, ivPickedImage42, ivPickedImage43,
          ivPickedImage44, singleview, singleview2, singleview3, singleview4, tvPrivacy);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
