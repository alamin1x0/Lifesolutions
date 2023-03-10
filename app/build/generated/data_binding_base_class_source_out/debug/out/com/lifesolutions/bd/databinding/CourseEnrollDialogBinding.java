// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CourseEnrollDialogBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MaterialButton btnBuyCoin;

  @NonNull
  public final MaterialButton btnEnrollCancel;

  @NonNull
  public final MaterialButton btnEnrollConfirm;

  @NonNull
  public final MaterialButton closeDialog;

  @NonNull
  public final TextView ivPrice;

  @NonNull
  public final CircleImageView ivProfileImg;

  @NonNull
  public final RatingBar ratings;

  @NonNull
  public final TextView tvInsufficentCoin;

  @NonNull
  public final TextView tvPlaylistSize;

  @NonNull
  public final TextView tvTitle;

  @NonNull
  public final TextView userCoinView;

  private CourseEnrollDialogBinding(@NonNull LinearLayout rootView,
      @NonNull MaterialButton btnBuyCoin, @NonNull MaterialButton btnEnrollCancel,
      @NonNull MaterialButton btnEnrollConfirm, @NonNull MaterialButton closeDialog,
      @NonNull TextView ivPrice, @NonNull CircleImageView ivProfileImg, @NonNull RatingBar ratings,
      @NonNull TextView tvInsufficentCoin, @NonNull TextView tvPlaylistSize,
      @NonNull TextView tvTitle, @NonNull TextView userCoinView) {
    this.rootView = rootView;
    this.btnBuyCoin = btnBuyCoin;
    this.btnEnrollCancel = btnEnrollCancel;
    this.btnEnrollConfirm = btnEnrollConfirm;
    this.closeDialog = closeDialog;
    this.ivPrice = ivPrice;
    this.ivProfileImg = ivProfileImg;
    this.ratings = ratings;
    this.tvInsufficentCoin = tvInsufficentCoin;
    this.tvPlaylistSize = tvPlaylistSize;
    this.tvTitle = tvTitle;
    this.userCoinView = userCoinView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CourseEnrollDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CourseEnrollDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.course_enroll_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CourseEnrollDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_buy_coin;
      MaterialButton btnBuyCoin = ViewBindings.findChildViewById(rootView, id);
      if (btnBuyCoin == null) {
        break missingId;
      }

      id = R.id.btn_enroll_cancel;
      MaterialButton btnEnrollCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnEnrollCancel == null) {
        break missingId;
      }

      id = R.id.btn_enroll_confirm;
      MaterialButton btnEnrollConfirm = ViewBindings.findChildViewById(rootView, id);
      if (btnEnrollConfirm == null) {
        break missingId;
      }

      id = R.id.close_dialog;
      MaterialButton closeDialog = ViewBindings.findChildViewById(rootView, id);
      if (closeDialog == null) {
        break missingId;
      }

      id = R.id.iv_price;
      TextView ivPrice = ViewBindings.findChildViewById(rootView, id);
      if (ivPrice == null) {
        break missingId;
      }

      id = R.id.iv_profile_img;
      CircleImageView ivProfileImg = ViewBindings.findChildViewById(rootView, id);
      if (ivProfileImg == null) {
        break missingId;
      }

      id = R.id.ratings;
      RatingBar ratings = ViewBindings.findChildViewById(rootView, id);
      if (ratings == null) {
        break missingId;
      }

      id = R.id.tv_insufficent_coin;
      TextView tvInsufficentCoin = ViewBindings.findChildViewById(rootView, id);
      if (tvInsufficentCoin == null) {
        break missingId;
      }

      id = R.id.tv_playlist_size;
      TextView tvPlaylistSize = ViewBindings.findChildViewById(rootView, id);
      if (tvPlaylistSize == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      id = R.id.user_coin_view;
      TextView userCoinView = ViewBindings.findChildViewById(rootView, id);
      if (userCoinView == null) {
        break missingId;
      }

      return new CourseEnrollDialogBinding((LinearLayout) rootView, btnBuyCoin, btnEnrollCancel,
          btnEnrollConfirm, closeDialog, ivPrice, ivProfileImg, ratings, tvInsufficentCoin,
          tvPlaylistSize, tvTitle, userCoinView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
