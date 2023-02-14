// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public final class AddressDialogBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MaterialButton btnUploadCancel;

  @NonNull
  public final MaterialButton btnUploadConfirm;

  @NonNull
  public final EditText enterAddress;

  @NonNull
  public final EditText enterName;

  @NonNull
  public final TextView tvPrivacy;

  private AddressDialogBinding(@NonNull LinearLayout rootView,
      @NonNull MaterialButton btnUploadCancel, @NonNull MaterialButton btnUploadConfirm,
      @NonNull EditText enterAddress, @NonNull EditText enterName, @NonNull TextView tvPrivacy) {
    this.rootView = rootView;
    this.btnUploadCancel = btnUploadCancel;
    this.btnUploadConfirm = btnUploadConfirm;
    this.enterAddress = enterAddress;
    this.enterName = enterName;
    this.tvPrivacy = tvPrivacy;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AddressDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AddressDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.address_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AddressDialogBinding bind(@NonNull View rootView) {
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

      id = R.id.enter_address;
      EditText enterAddress = ViewBindings.findChildViewById(rootView, id);
      if (enterAddress == null) {
        break missingId;
      }

      id = R.id.enter_name;
      EditText enterName = ViewBindings.findChildViewById(rootView, id);
      if (enterName == null) {
        break missingId;
      }

      id = R.id.tv_privacy;
      TextView tvPrivacy = ViewBindings.findChildViewById(rootView, id);
      if (tvPrivacy == null) {
        break missingId;
      }

      return new AddressDialogBinding((LinearLayout) rootView, btnUploadCancel, btnUploadConfirm,
          enterAddress, enterName, tvPrivacy);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}