// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCreateChatGroupBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnCreateGroupNextPage;

  @NonNull
  public final MaterialButton btnPickImage;

  @NonNull
  public final LinearLayout cardLayoutCreateGroup;

  @NonNull
  public final AutoCompleteTextView etDropdownGroupCategory;

  @NonNull
  public final AutoCompleteTextView etDropdownGroupPrivacy;

  @NonNull
  public final TextInputEditText etGroupName;

  @NonNull
  public final CircleImageView groupImageView;

  @NonNull
  public final TextView signUpText;

  @NonNull
  public final Toolbar toolbarCreteGroup;

  private ActivityCreateChatGroupBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnCreateGroupNextPage, @NonNull MaterialButton btnPickImage,
      @NonNull LinearLayout cardLayoutCreateGroup,
      @NonNull AutoCompleteTextView etDropdownGroupCategory,
      @NonNull AutoCompleteTextView etDropdownGroupPrivacy, @NonNull TextInputEditText etGroupName,
      @NonNull CircleImageView groupImageView, @NonNull TextView signUpText,
      @NonNull Toolbar toolbarCreteGroup) {
    this.rootView = rootView;
    this.btnCreateGroupNextPage = btnCreateGroupNextPage;
    this.btnPickImage = btnPickImage;
    this.cardLayoutCreateGroup = cardLayoutCreateGroup;
    this.etDropdownGroupCategory = etDropdownGroupCategory;
    this.etDropdownGroupPrivacy = etDropdownGroupPrivacy;
    this.etGroupName = etGroupName;
    this.groupImageView = groupImageView;
    this.signUpText = signUpText;
    this.toolbarCreteGroup = toolbarCreteGroup;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCreateChatGroupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCreateChatGroupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_create_chat_group, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCreateChatGroupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_create_group_next_page;
      MaterialButton btnCreateGroupNextPage = ViewBindings.findChildViewById(rootView, id);
      if (btnCreateGroupNextPage == null) {
        break missingId;
      }

      id = R.id.btn_pick_image;
      MaterialButton btnPickImage = ViewBindings.findChildViewById(rootView, id);
      if (btnPickImage == null) {
        break missingId;
      }

      id = R.id.card_layout_create_group;
      LinearLayout cardLayoutCreateGroup = ViewBindings.findChildViewById(rootView, id);
      if (cardLayoutCreateGroup == null) {
        break missingId;
      }

      id = R.id.et_dropdown_group_category;
      AutoCompleteTextView etDropdownGroupCategory = ViewBindings.findChildViewById(rootView, id);
      if (etDropdownGroupCategory == null) {
        break missingId;
      }

      id = R.id.et_dropdown_group_privacy;
      AutoCompleteTextView etDropdownGroupPrivacy = ViewBindings.findChildViewById(rootView, id);
      if (etDropdownGroupPrivacy == null) {
        break missingId;
      }

      id = R.id.et_group_name;
      TextInputEditText etGroupName = ViewBindings.findChildViewById(rootView, id);
      if (etGroupName == null) {
        break missingId;
      }

      id = R.id.group_image_view;
      CircleImageView groupImageView = ViewBindings.findChildViewById(rootView, id);
      if (groupImageView == null) {
        break missingId;
      }

      id = R.id.sign_up_text;
      TextView signUpText = ViewBindings.findChildViewById(rootView, id);
      if (signUpText == null) {
        break missingId;
      }

      id = R.id.toolbar_crete_group;
      Toolbar toolbarCreteGroup = ViewBindings.findChildViewById(rootView, id);
      if (toolbarCreteGroup == null) {
        break missingId;
      }

      return new ActivityCreateChatGroupBinding((ConstraintLayout) rootView, btnCreateGroupNextPage,
          btnPickImage, cardLayoutCreateGroup, etDropdownGroupCategory, etDropdownGroupPrivacy,
          etGroupName, groupImageView, signUpText, toolbarCreteGroup);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
