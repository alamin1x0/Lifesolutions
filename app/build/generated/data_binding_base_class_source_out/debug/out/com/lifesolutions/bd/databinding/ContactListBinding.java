// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ContactListBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView none;

  @NonNull
  public final ProgressBar simpleProgressBar;

  @NonNull
  public final RecyclerView testView;

  @NonNull
  public final Toolbar toolbarLeaderBoard;

  private ContactListBinding(@NonNull LinearLayout rootView, @NonNull TextView none,
      @NonNull ProgressBar simpleProgressBar, @NonNull RecyclerView testView,
      @NonNull Toolbar toolbarLeaderBoard) {
    this.rootView = rootView;
    this.none = none;
    this.simpleProgressBar = simpleProgressBar;
    this.testView = testView;
    this.toolbarLeaderBoard = toolbarLeaderBoard;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContactListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContactListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.contact_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContactListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.none;
      TextView none = ViewBindings.findChildViewById(rootView, id);
      if (none == null) {
        break missingId;
      }

      id = R.id.simpleProgressBar;
      ProgressBar simpleProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (simpleProgressBar == null) {
        break missingId;
      }

      id = R.id.test_view;
      RecyclerView testView = ViewBindings.findChildViewById(rootView, id);
      if (testView == null) {
        break missingId;
      }

      id = R.id.toolbar_leader_board;
      Toolbar toolbarLeaderBoard = ViewBindings.findChildViewById(rootView, id);
      if (toolbarLeaderBoard == null) {
        break missingId;
      }

      return new ContactListBinding((LinearLayout) rootView, none, simpleProgressBar, testView,
          toolbarLeaderBoard);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}