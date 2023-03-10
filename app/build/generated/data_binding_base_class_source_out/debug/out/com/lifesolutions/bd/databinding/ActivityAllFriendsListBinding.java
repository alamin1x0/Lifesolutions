// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public final class ActivityAllFriendsListBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppBarLayout appbarAllFriendList;

  @NonNull
  public final RecyclerView rvFriendListOnProfile;

  @NonNull
  public final Toolbar toolbarAllFriendList;

  @NonNull
  public final TextView tvFriendsCount;

  private ActivityAllFriendsListBinding(@NonNull LinearLayout rootView,
      @NonNull AppBarLayout appbarAllFriendList, @NonNull RecyclerView rvFriendListOnProfile,
      @NonNull Toolbar toolbarAllFriendList, @NonNull TextView tvFriendsCount) {
    this.rootView = rootView;
    this.appbarAllFriendList = appbarAllFriendList;
    this.rvFriendListOnProfile = rvFriendListOnProfile;
    this.toolbarAllFriendList = toolbarAllFriendList;
    this.tvFriendsCount = tvFriendsCount;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAllFriendsListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAllFriendsListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_all_friends_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAllFriendsListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appbar_all_friend_list;
      AppBarLayout appbarAllFriendList = ViewBindings.findChildViewById(rootView, id);
      if (appbarAllFriendList == null) {
        break missingId;
      }

      id = R.id.rv_friend_list_on_profile;
      RecyclerView rvFriendListOnProfile = ViewBindings.findChildViewById(rootView, id);
      if (rvFriendListOnProfile == null) {
        break missingId;
      }

      id = R.id.toolbar_all_friend_list;
      Toolbar toolbarAllFriendList = ViewBindings.findChildViewById(rootView, id);
      if (toolbarAllFriendList == null) {
        break missingId;
      }

      id = R.id.tv_friends_count;
      TextView tvFriendsCount = ViewBindings.findChildViewById(rootView, id);
      if (tvFriendsCount == null) {
        break missingId;
      }

      return new ActivityAllFriendsListBinding((LinearLayout) rootView, appbarAllFriendList,
          rvFriendListOnProfile, toolbarAllFriendList, tvFriendsCount);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
