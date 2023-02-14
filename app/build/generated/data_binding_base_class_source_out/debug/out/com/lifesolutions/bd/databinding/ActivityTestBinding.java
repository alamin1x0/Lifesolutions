// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTestBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonUrlCheck;

  @NonNull
  public final EditText editUrl;

  @NonNull
  public final TextView responseBody;

  private ActivityTestBinding(@NonNull LinearLayout rootView, @NonNull Button buttonUrlCheck,
      @NonNull EditText editUrl, @NonNull TextView responseBody) {
    this.rootView = rootView;
    this.buttonUrlCheck = buttonUrlCheck;
    this.editUrl = editUrl;
    this.responseBody = responseBody;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTestBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_test, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTestBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_url_check;
      Button buttonUrlCheck = ViewBindings.findChildViewById(rootView, id);
      if (buttonUrlCheck == null) {
        break missingId;
      }

      id = R.id.edit_url;
      EditText editUrl = ViewBindings.findChildViewById(rootView, id);
      if (editUrl == null) {
        break missingId;
      }

      id = R.id.response_body;
      TextView responseBody = ViewBindings.findChildViewById(rootView, id);
      if (responseBody == null) {
        break missingId;
      }

      return new ActivityTestBinding((LinearLayout) rootView, buttonUrlCheck, editUrl,
          responseBody);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}