// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FeedFragmentBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final LinearLayout addPostLayoutHomeKt;

  @NonNull
  public final AppBarLayout appbarFragmentHome;

  @NonNull
  public final CoordinatorLayout feedMainLayout;

  @NonNull
  public final LinearLayout ibPickImage;

  @NonNull
  public final CircleImageView ivUserThumb;

  @NonNull
  public final CircleImageView profileImageHomeToolbar;

  @NonNull
  public final RecyclerView recyclerViewHomeKt;

  @NonNull
  public final RecyclerView rvActiveOnlineUser;

  @NonNull
  public final LinearLayout rvLayoutActiveUser;

  @NonNull
  public final ImageButton searchButtonHomeKt;

  @NonNull
  public final ShimmerFrameLayout shimmerLayoutHomeKt;

  @NonNull
  public final SwipeRefreshLayout swipeRefreshLayoutHomeKt;

  @NonNull
  public final Toolbar toolbarMain;

  @NonNull
  public final TextView tvViewAllActiveList;

  @NonNull
  public final TextView txtViewOnlineCountUser;

  @NonNull
  public final TextView txtViewTitle;

  private FeedFragmentBinding(@NonNull CoordinatorLayout rootView,
      @NonNull LinearLayout addPostLayoutHomeKt, @NonNull AppBarLayout appbarFragmentHome,
      @NonNull CoordinatorLayout feedMainLayout, @NonNull LinearLayout ibPickImage,
      @NonNull CircleImageView ivUserThumb, @NonNull CircleImageView profileImageHomeToolbar,
      @NonNull RecyclerView recyclerViewHomeKt, @NonNull RecyclerView rvActiveOnlineUser,
      @NonNull LinearLayout rvLayoutActiveUser, @NonNull ImageButton searchButtonHomeKt,
      @NonNull ShimmerFrameLayout shimmerLayoutHomeKt,
      @NonNull SwipeRefreshLayout swipeRefreshLayoutHomeKt, @NonNull Toolbar toolbarMain,
      @NonNull TextView tvViewAllActiveList, @NonNull TextView txtViewOnlineCountUser,
      @NonNull TextView txtViewTitle) {
    this.rootView = rootView;
    this.addPostLayoutHomeKt = addPostLayoutHomeKt;
    this.appbarFragmentHome = appbarFragmentHome;
    this.feedMainLayout = feedMainLayout;
    this.ibPickImage = ibPickImage;
    this.ivUserThumb = ivUserThumb;
    this.profileImageHomeToolbar = profileImageHomeToolbar;
    this.recyclerViewHomeKt = recyclerViewHomeKt;
    this.rvActiveOnlineUser = rvActiveOnlineUser;
    this.rvLayoutActiveUser = rvLayoutActiveUser;
    this.searchButtonHomeKt = searchButtonHomeKt;
    this.shimmerLayoutHomeKt = shimmerLayoutHomeKt;
    this.swipeRefreshLayoutHomeKt = swipeRefreshLayoutHomeKt;
    this.toolbarMain = toolbarMain;
    this.tvViewAllActiveList = tvViewAllActiveList;
    this.txtViewOnlineCountUser = txtViewOnlineCountUser;
    this.txtViewTitle = txtViewTitle;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FeedFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FeedFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.feed_fragment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FeedFragmentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_post_layout_home_kt;
      LinearLayout addPostLayoutHomeKt = ViewBindings.findChildViewById(rootView, id);
      if (addPostLayoutHomeKt == null) {
        break missingId;
      }

      id = R.id.appbar_fragment_home;
      AppBarLayout appbarFragmentHome = ViewBindings.findChildViewById(rootView, id);
      if (appbarFragmentHome == null) {
        break missingId;
      }

      CoordinatorLayout feedMainLayout = (CoordinatorLayout) rootView;

      id = R.id.ib_pick_image;
      LinearLayout ibPickImage = ViewBindings.findChildViewById(rootView, id);
      if (ibPickImage == null) {
        break missingId;
      }

      id = R.id.iv_user_thumb;
      CircleImageView ivUserThumb = ViewBindings.findChildViewById(rootView, id);
      if (ivUserThumb == null) {
        break missingId;
      }

      id = R.id.profile_image_home_toolbar;
      CircleImageView profileImageHomeToolbar = ViewBindings.findChildViewById(rootView, id);
      if (profileImageHomeToolbar == null) {
        break missingId;
      }

      id = R.id.recycler_view_home_kt;
      RecyclerView recyclerViewHomeKt = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewHomeKt == null) {
        break missingId;
      }

      id = R.id.rv_active_online_user;
      RecyclerView rvActiveOnlineUser = ViewBindings.findChildViewById(rootView, id);
      if (rvActiveOnlineUser == null) {
        break missingId;
      }

      id = R.id.rv_layout_active_user;
      LinearLayout rvLayoutActiveUser = ViewBindings.findChildViewById(rootView, id);
      if (rvLayoutActiveUser == null) {
        break missingId;
      }

      id = R.id.search_button_home_kt;
      ImageButton searchButtonHomeKt = ViewBindings.findChildViewById(rootView, id);
      if (searchButtonHomeKt == null) {
        break missingId;
      }

      id = R.id.shimmer_layout_home_kt;
      ShimmerFrameLayout shimmerLayoutHomeKt = ViewBindings.findChildViewById(rootView, id);
      if (shimmerLayoutHomeKt == null) {
        break missingId;
      }

      id = R.id.swipe_refresh_layout_home_kt;
      SwipeRefreshLayout swipeRefreshLayoutHomeKt = ViewBindings.findChildViewById(rootView, id);
      if (swipeRefreshLayoutHomeKt == null) {
        break missingId;
      }

      id = R.id.toolbar_main;
      Toolbar toolbarMain = ViewBindings.findChildViewById(rootView, id);
      if (toolbarMain == null) {
        break missingId;
      }

      id = R.id.tv_view_all_active_list;
      TextView tvViewAllActiveList = ViewBindings.findChildViewById(rootView, id);
      if (tvViewAllActiveList == null) {
        break missingId;
      }

      id = R.id.txt_view_online_count_user;
      TextView txtViewOnlineCountUser = ViewBindings.findChildViewById(rootView, id);
      if (txtViewOnlineCountUser == null) {
        break missingId;
      }

      id = R.id.txt_view_title;
      TextView txtViewTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtViewTitle == null) {
        break missingId;
      }

      return new FeedFragmentBinding((CoordinatorLayout) rootView, addPostLayoutHomeKt,
          appbarFragmentHome, feedMainLayout, ibPickImage, ivUserThumb, profileImageHomeToolbar,
          recyclerViewHomeKt, rvActiveOnlineUser, rvLayoutActiveUser, searchButtonHomeKt,
          shimmerLayoutHomeKt, swipeRefreshLayoutHomeKt, toolbarMain, tvViewAllActiveList,
          txtViewOnlineCountUser, txtViewTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
