// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFindFriendsBinding implements ViewBinding {
  @NonNull
  private final SwipeRefreshLayout rootView;

  @NonNull
  public final ProgressBar progressbarFindFriends;

  @NonNull
  public final RecyclerView recyclerViewFindFriends;

  @NonNull
  public final SwipeRefreshLayout swipeRefreshLayoutFindFriends;

  @NonNull
  public final Toolbar toolbarFindFriends;

  private ActivityFindFriendsBinding(@NonNull SwipeRefreshLayout rootView,
      @NonNull ProgressBar progressbarFindFriends, @NonNull RecyclerView recyclerViewFindFriends,
      @NonNull SwipeRefreshLayout swipeRefreshLayoutFindFriends,
      @NonNull Toolbar toolbarFindFriends) {
    this.rootView = rootView;
    this.progressbarFindFriends = progressbarFindFriends;
    this.recyclerViewFindFriends = recyclerViewFindFriends;
    this.swipeRefreshLayoutFindFriends = swipeRefreshLayoutFindFriends;
    this.toolbarFindFriends = toolbarFindFriends;
  }

  @Override
  @NonNull
  public SwipeRefreshLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFindFriendsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFindFriendsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_find_friends, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFindFriendsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.progressbar_find_friends;
      ProgressBar progressbarFindFriends = ViewBindings.findChildViewById(rootView, id);
      if (progressbarFindFriends == null) {
        break missingId;
      }

      id = R.id.recyclerView_find_friends;
      RecyclerView recyclerViewFindFriends = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewFindFriends == null) {
        break missingId;
      }

      SwipeRefreshLayout swipeRefreshLayoutFindFriends = (SwipeRefreshLayout) rootView;

      id = R.id.toolbar_find_friends;
      Toolbar toolbarFindFriends = ViewBindings.findChildViewById(rootView, id);
      if (toolbarFindFriends == null) {
        break missingId;
      }

      return new ActivityFindFriendsBinding((SwipeRefreshLayout) rootView, progressbarFindFriends,
          recyclerViewFindFriends, swipeRefreshLayoutFindFriends, toolbarFindFriends);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}