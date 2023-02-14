// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class DialogLayoutWarningBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView descriptionDialogWarning;

  @NonNull
  public final ImageView imageDialogWarning;

  @NonNull
  public final TextView quitDialogWarning;

  @NonNull
  public final TextView saveDialogWarning;

  @NonNull
  public final TextView titleDialogWarning;

  private DialogLayoutWarningBinding(@NonNull CardView rootView,
      @NonNull TextView descriptionDialogWarning, @NonNull ImageView imageDialogWarning,
      @NonNull TextView quitDialogWarning, @NonNull TextView saveDialogWarning,
      @NonNull TextView titleDialogWarning) {
    this.rootView = rootView;
    this.descriptionDialogWarning = descriptionDialogWarning;
    this.imageDialogWarning = imageDialogWarning;
    this.quitDialogWarning = quitDialogWarning;
    this.saveDialogWarning = saveDialogWarning;
    this.titleDialogWarning = titleDialogWarning;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogLayoutWarningBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogLayoutWarningBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_layout_warning, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogLayoutWarningBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.description_dialog_warning;
      TextView descriptionDialogWarning = ViewBindings.findChildViewById(rootView, id);
      if (descriptionDialogWarning == null) {
        break missingId;
      }

      id = R.id.image_dialog_warning;
      ImageView imageDialogWarning = ViewBindings.findChildViewById(rootView, id);
      if (imageDialogWarning == null) {
        break missingId;
      }

      id = R.id.quit_dialog_warning;
      TextView quitDialogWarning = ViewBindings.findChildViewById(rootView, id);
      if (quitDialogWarning == null) {
        break missingId;
      }

      id = R.id.save_dialog_warning;
      TextView saveDialogWarning = ViewBindings.findChildViewById(rootView, id);
      if (saveDialogWarning == null) {
        break missingId;
      }

      id = R.id.title_dialog_warning;
      TextView titleDialogWarning = ViewBindings.findChildViewById(rootView, id);
      if (titleDialogWarning == null) {
        break missingId;
      }

      return new DialogLayoutWarningBinding((CardView) rootView, descriptionDialogWarning,
          imageDialogWarning, quitDialogWarning, saveDialogWarning, titleDialogWarning);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}