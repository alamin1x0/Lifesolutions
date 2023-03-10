// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddGroupMemberBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppBarLayout appbarGroupAddMember;

  @NonNull
  public final ProgressBar progressbarGroupAddMember;

  @NonNull
  public final RecyclerView recyclerViewAddGroupMember;

  @NonNull
  public final Toolbar toolbarGroupAddMember;

  private ActivityAddGroupMemberBinding(@NonNull LinearLayout rootView,
      @NonNull AppBarLayout appbarGroupAddMember, @NonNull ProgressBar progressbarGroupAddMember,
      @NonNull RecyclerView recyclerViewAddGroupMember, @NonNull Toolbar toolbarGroupAddMember) {
    this.rootView = rootView;
    this.appbarGroupAddMember = appbarGroupAddMember;
    this.progressbarGroupAddMember = progressbarGroupAddMember;
    this.recyclerViewAddGroupMember = recyclerViewAddGroupMember;
    this.toolbarGroupAddMember = toolbarGroupAddMember;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddGroupMemberBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddGroupMemberBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_group_member, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddGroupMemberBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appbar_group_add_member;
      AppBarLayout appbarGroupAddMember = ViewBindings.findChildViewById(rootView, id);
      if (appbarGroupAddMember == null) {
        break missingId;
      }

      id = R.id.progressbar_group_add_member;
      ProgressBar progressbarGroupAddMember = ViewBindings.findChildViewById(rootView, id);
      if (progressbarGroupAddMember == null) {
        break missingId;
      }

      id = R.id.recyclerView_add_group_member;
      RecyclerView recyclerViewAddGroupMember = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewAddGroupMember == null) {
        break missingId;
      }

      id = R.id.toolbar_group_add_member;
      Toolbar toolbarGroupAddMember = ViewBindings.findChildViewById(rootView, id);
      if (toolbarGroupAddMember == null) {
        break missingId;
      }

      return new ActivityAddGroupMemberBinding((LinearLayout) rootView, appbarGroupAddMember,
          progressbarGroupAddMember, recyclerViewAddGroupMember, toolbarGroupAddMember);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
