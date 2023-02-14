// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NewRandomCallFragmentBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final MaterialButton btnMessengerCall;

  @NonNull
  public final MaterialButton btnRandomCallAction;

  @NonNull
  public final RelativeLayout groupLayoutTopRCall;

  @NonNull
  public final ImageView imageViewRandomCall;

  @NonNull
  public final LinearLayout loadingBarOnUserOnline;

  @NonNull
  public final ImageView logoImageRandomCall;

  @NonNull
  public final LottieAnimationView lottieAnimationUser;

  @NonNull
  public final LinearLayout nestedRvLayout;

  @NonNull
  public final RecyclerView rvActiveOnlineUser;

  @NonNull
  public final RecyclerView rvGroupList;

  @NonNull
  public final LinearLayout rvLayoutActiveUser;

  @NonNull
  public final LinearLayout rvLayoutGroupView;

  @NonNull
  public final TextView txtBtnAllGroup;

  @NonNull
  public final TextView txtBtnViewAllActiveList;

  @NonNull
  public final TextView txtViewGroupList;

  @NonNull
  public final TextView txtViewTitle;

  private NewRandomCallFragmentBinding(@NonNull NestedScrollView rootView,
      @NonNull MaterialButton btnMessengerCall, @NonNull MaterialButton btnRandomCallAction,
      @NonNull RelativeLayout groupLayoutTopRCall, @NonNull ImageView imageViewRandomCall,
      @NonNull LinearLayout loadingBarOnUserOnline, @NonNull ImageView logoImageRandomCall,
      @NonNull LottieAnimationView lottieAnimationUser, @NonNull LinearLayout nestedRvLayout,
      @NonNull RecyclerView rvActiveOnlineUser, @NonNull RecyclerView rvGroupList,
      @NonNull LinearLayout rvLayoutActiveUser, @NonNull LinearLayout rvLayoutGroupView,
      @NonNull TextView txtBtnAllGroup, @NonNull TextView txtBtnViewAllActiveList,
      @NonNull TextView txtViewGroupList, @NonNull TextView txtViewTitle) {
    this.rootView = rootView;
    this.btnMessengerCall = btnMessengerCall;
    this.btnRandomCallAction = btnRandomCallAction;
    this.groupLayoutTopRCall = groupLayoutTopRCall;
    this.imageViewRandomCall = imageViewRandomCall;
    this.loadingBarOnUserOnline = loadingBarOnUserOnline;
    this.logoImageRandomCall = logoImageRandomCall;
    this.lottieAnimationUser = lottieAnimationUser;
    this.nestedRvLayout = nestedRvLayout;
    this.rvActiveOnlineUser = rvActiveOnlineUser;
    this.rvGroupList = rvGroupList;
    this.rvLayoutActiveUser = rvLayoutActiveUser;
    this.rvLayoutGroupView = rvLayoutGroupView;
    this.txtBtnAllGroup = txtBtnAllGroup;
    this.txtBtnViewAllActiveList = txtBtnViewAllActiveList;
    this.txtViewGroupList = txtViewGroupList;
    this.txtViewTitle = txtViewTitle;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static NewRandomCallFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NewRandomCallFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.new_random_call_fragment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NewRandomCallFragmentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_messenger_call;
      MaterialButton btnMessengerCall = ViewBindings.findChildViewById(rootView, id);
      if (btnMessengerCall == null) {
        break missingId;
      }

      id = R.id.btn_random_call_action;
      MaterialButton btnRandomCallAction = ViewBindings.findChildViewById(rootView, id);
      if (btnRandomCallAction == null) {
        break missingId;
      }

      id = R.id.group_layout_top_r_call;
      RelativeLayout groupLayoutTopRCall = ViewBindings.findChildViewById(rootView, id);
      if (groupLayoutTopRCall == null) {
        break missingId;
      }

      id = R.id.image_view_random_call;
      ImageView imageViewRandomCall = ViewBindings.findChildViewById(rootView, id);
      if (imageViewRandomCall == null) {
        break missingId;
      }

      id = R.id.loading_bar_on_user_online;
      LinearLayout loadingBarOnUserOnline = ViewBindings.findChildViewById(rootView, id);
      if (loadingBarOnUserOnline == null) {
        break missingId;
      }

      id = R.id.logo_image_random_call;
      ImageView logoImageRandomCall = ViewBindings.findChildViewById(rootView, id);
      if (logoImageRandomCall == null) {
        break missingId;
      }

      id = R.id.lottie_animation_user;
      LottieAnimationView lottieAnimationUser = ViewBindings.findChildViewById(rootView, id);
      if (lottieAnimationUser == null) {
        break missingId;
      }

      id = R.id.nested_rv_layout;
      LinearLayout nestedRvLayout = ViewBindings.findChildViewById(rootView, id);
      if (nestedRvLayout == null) {
        break missingId;
      }

      id = R.id.rv_active_online_user;
      RecyclerView rvActiveOnlineUser = ViewBindings.findChildViewById(rootView, id);
      if (rvActiveOnlineUser == null) {
        break missingId;
      }

      id = R.id.rv_group_list;
      RecyclerView rvGroupList = ViewBindings.findChildViewById(rootView, id);
      if (rvGroupList == null) {
        break missingId;
      }

      id = R.id.rv_layout_active_user;
      LinearLayout rvLayoutActiveUser = ViewBindings.findChildViewById(rootView, id);
      if (rvLayoutActiveUser == null) {
        break missingId;
      }

      id = R.id.rv_layout_group_view;
      LinearLayout rvLayoutGroupView = ViewBindings.findChildViewById(rootView, id);
      if (rvLayoutGroupView == null) {
        break missingId;
      }

      id = R.id.txt_btn_all_group;
      TextView txtBtnAllGroup = ViewBindings.findChildViewById(rootView, id);
      if (txtBtnAllGroup == null) {
        break missingId;
      }

      id = R.id.txt_btn_view_all_active_list;
      TextView txtBtnViewAllActiveList = ViewBindings.findChildViewById(rootView, id);
      if (txtBtnViewAllActiveList == null) {
        break missingId;
      }

      id = R.id.txt_view_group_list;
      TextView txtViewGroupList = ViewBindings.findChildViewById(rootView, id);
      if (txtViewGroupList == null) {
        break missingId;
      }

      id = R.id.txt_view_title;
      TextView txtViewTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtViewTitle == null) {
        break missingId;
      }

      return new NewRandomCallFragmentBinding((NestedScrollView) rootView, btnMessengerCall,
          btnRandomCallAction, groupLayoutTopRCall, imageViewRandomCall, loadingBarOnUserOnline,
          logoImageRandomCall, lottieAnimationUser, nestedRvLayout, rvActiveOnlineUser, rvGroupList,
          rvLayoutActiveUser, rvLayoutGroupView, txtBtnAllGroup, txtBtnViewAllActiveList,
          txtViewGroupList, txtViewTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}