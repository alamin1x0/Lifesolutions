// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogRecoveryPasswordBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final EditText dialogueEmailRecoveryPassword;

  @NonNull
  public final TextView noDialogRecoveryPassword;

  @NonNull
  public final TextView yesDialogRecoveryPassword;

  private DialogRecoveryPasswordBinding(@NonNull CardView rootView,
      @NonNull EditText dialogueEmailRecoveryPassword, @NonNull TextView noDialogRecoveryPassword,
      @NonNull TextView yesDialogRecoveryPassword) {
    this.rootView = rootView;
    this.dialogueEmailRecoveryPassword = dialogueEmailRecoveryPassword;
    this.noDialogRecoveryPassword = noDialogRecoveryPassword;
    this.yesDialogRecoveryPassword = yesDialogRecoveryPassword;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogRecoveryPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogRecoveryPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_recovery_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogRecoveryPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dialogue_email_recovery_password;
      EditText dialogueEmailRecoveryPassword = ViewBindings.findChildViewById(rootView, id);
      if (dialogueEmailRecoveryPassword == null) {
        break missingId;
      }

      id = R.id.no_dialog_recovery_password;
      TextView noDialogRecoveryPassword = ViewBindings.findChildViewById(rootView, id);
      if (noDialogRecoveryPassword == null) {
        break missingId;
      }

      id = R.id.yes_dialog_recovery_password;
      TextView yesDialogRecoveryPassword = ViewBindings.findChildViewById(rootView, id);
      if (yesDialogRecoveryPassword == null) {
        break missingId;
      }

      return new DialogRecoveryPasswordBinding((CardView) rootView, dialogueEmailRecoveryPassword,
          noDialogRecoveryPassword, yesDialogRecoveryPassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
