// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogReciveCallLayoutBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView addressCallDialog;

  @NonNull
  public final LinearLayout bottomLayoutCallingDialog;

  @NonNull
  public final RelativeLayout bottomLayoutReceiveDialog;

  @NonNull
  public final ImageButton callAcceptCallDialog;

  @NonNull
  public final ImageButton callRejectCallDialog;

  @NonNull
  public final ImageButton declineCallDialog;

  @NonNull
  public final ImageButton loudCallDialog;

  @NonNull
  public final ImageButton muteCallDialog;

  @NonNull
  public final TextView nameCallDialog;

  @NonNull
  public final CircleImageView profileImageCallDialog;

  @NonNull
  public final Chronometer timerCallDialog;

  private DialogReciveCallLayoutBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView addressCallDialog, @NonNull LinearLayout bottomLayoutCallingDialog,
      @NonNull RelativeLayout bottomLayoutReceiveDialog, @NonNull ImageButton callAcceptCallDialog,
      @NonNull ImageButton callRejectCallDialog, @NonNull ImageButton declineCallDialog,
      @NonNull ImageButton loudCallDialog, @NonNull ImageButton muteCallDialog,
      @NonNull TextView nameCallDialog, @NonNull CircleImageView profileImageCallDialog,
      @NonNull Chronometer timerCallDialog) {
    this.rootView = rootView;
    this.addressCallDialog = addressCallDialog;
    this.bottomLayoutCallingDialog = bottomLayoutCallingDialog;
    this.bottomLayoutReceiveDialog = bottomLayoutReceiveDialog;
    this.callAcceptCallDialog = callAcceptCallDialog;
    this.callRejectCallDialog = callRejectCallDialog;
    this.declineCallDialog = declineCallDialog;
    this.loudCallDialog = loudCallDialog;
    this.muteCallDialog = muteCallDialog;
    this.nameCallDialog = nameCallDialog;
    this.profileImageCallDialog = profileImageCallDialog;
    this.timerCallDialog = timerCallDialog;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogReciveCallLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogReciveCallLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_recive_call_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogReciveCallLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address_call_dialog;
      TextView addressCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (addressCallDialog == null) {
        break missingId;
      }

      id = R.id.bottom_layout_calling_dialog;
      LinearLayout bottomLayoutCallingDialog = ViewBindings.findChildViewById(rootView, id);
      if (bottomLayoutCallingDialog == null) {
        break missingId;
      }

      id = R.id.bottom_layout_receive_dialog;
      RelativeLayout bottomLayoutReceiveDialog = ViewBindings.findChildViewById(rootView, id);
      if (bottomLayoutReceiveDialog == null) {
        break missingId;
      }

      id = R.id.call_accept_call_dialog;
      ImageButton callAcceptCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (callAcceptCallDialog == null) {
        break missingId;
      }

      id = R.id.call_reject_call_dialog;
      ImageButton callRejectCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (callRejectCallDialog == null) {
        break missingId;
      }

      id = R.id.decline_call_dialog;
      ImageButton declineCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (declineCallDialog == null) {
        break missingId;
      }

      id = R.id.loud_call_dialog;
      ImageButton loudCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (loudCallDialog == null) {
        break missingId;
      }

      id = R.id.mute_call_dialog;
      ImageButton muteCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (muteCallDialog == null) {
        break missingId;
      }

      id = R.id.name_call_dialog;
      TextView nameCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (nameCallDialog == null) {
        break missingId;
      }

      id = R.id.profile_image_call_dialog;
      CircleImageView profileImageCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (profileImageCallDialog == null) {
        break missingId;
      }

      id = R.id.timer_call_dialog;
      Chronometer timerCallDialog = ViewBindings.findChildViewById(rootView, id);
      if (timerCallDialog == null) {
        break missingId;
      }

      return new DialogReciveCallLayoutBinding((RelativeLayout) rootView, addressCallDialog,
          bottomLayoutCallingDialog, bottomLayoutReceiveDialog, callAcceptCallDialog,
          callRejectCallDialog, declineCallDialog, loudCallDialog, muteCallDialog, nameCallDialog,
          profileImageCallDialog, timerCallDialog);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
