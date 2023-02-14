// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public final class ActivityAllGroupListBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppBarLayout appbarAllGroupList;

  @NonNull
  public final RecyclerView recyclerViewAllGroupList;

  @NonNull
  public final Toolbar toolbarAllGroupList;

  private ActivityAllGroupListBinding(@NonNull LinearLayout rootView,
      @NonNull AppBarLayout appbarAllGroupList, @NonNull RecyclerView recyclerViewAllGroupList,
      @NonNull Toolbar toolbarAllGroupList) {
    this.rootView = rootView;
    this.appbarAllGroupList = appbarAllGroupList;
    this.recyclerViewAllGroupList = recyclerViewAllGroupList;
    this.toolbarAllGroupList = toolbarAllGroupList;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAllGroupListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAllGroupListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_all_group_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAllGroupListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appbar_all_group_list;
      AppBarLayout appbarAllGroupList = ViewBindings.findChildViewById(rootView, id);
      if (appbarAllGroupList == null) {
        break missingId;
      }

      id = R.id.recycler_view_all_group_list;
      RecyclerView recyclerViewAllGroupList = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewAllGroupList == null) {
        break missingId;
      }

      id = R.id.toolbar_all_group_list;
      Toolbar toolbarAllGroupList = ViewBindings.findChildViewById(rootView, id);
      if (toolbarAllGroupList == null) {
        break missingId;
      }

      return new ActivityAllGroupListBinding((LinearLayout) rootView, appbarAllGroupList,
          recyclerViewAllGroupList, toolbarAllGroupList);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
