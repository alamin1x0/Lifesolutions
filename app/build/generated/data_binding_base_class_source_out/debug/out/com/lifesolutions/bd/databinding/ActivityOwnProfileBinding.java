// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.google.android.material.button.MaterialButton;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOwnProfileBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final TextView addressProfile;

  @NonNull
  public final ImageButton backButtonProfile;

  @NonNull
  public final RelativeLayout backgroundProfile;

  @NonNull
  public final TextView birthDateProfile;

  @NonNull
  public final TextView bloodGroupProfile;

  @NonNull
  public final MaterialButton btnApplyReferCode;

  @NonNull
  public final MaterialButton btnEditInfo;

  @NonNull
  public final MaterialButton btnPrivacy;

  @NonNull
  public final MaterialButton btnSeeAllFriend;

  @NonNull
  public final MaterialButton btnSeeAllPosts;

  @NonNull
  public final ImageButton btnUploadCoverPhoto;

  @NonNull
  public final ImageButton btnUploadProfilePhoto;

  @NonNull
  public final ImageView coverPictureProfile;

  @NonNull
  public final TextView emailProfile;

  @NonNull
  public final ImageView ivVerifiedBadge;

  @NonNull
  public final TextView joinedProfile;

  @NonNull
  public final TextView lastCallTime;

  @NonNull
  public final LinearLayout layoutOwnProfile;

  @NonNull
  public final TextView phoneProfile;

  @NonNull
  public final CircleImageView profileImageProfile;

  @NonNull
  public final RecyclerView rvFriendListOnProfile;

  @NonNull
  public final RecyclerView rvUserPostProfile;

  @NonNull
  public final TextView studentInfoProfile;

  @NonNull
  public final TextView talkTimeCount;

  @NonNull
  public final TextView tvFriendsCount;

  @NonNull
  public final TextView tvFullName;

  @NonNull
  public final TextView userBioProfile;

  private ActivityOwnProfileBinding(@NonNull NestedScrollView rootView,
      @NonNull TextView addressProfile, @NonNull ImageButton backButtonProfile,
      @NonNull RelativeLayout backgroundProfile, @NonNull TextView birthDateProfile,
      @NonNull TextView bloodGroupProfile, @NonNull MaterialButton btnApplyReferCode,
      @NonNull MaterialButton btnEditInfo, @NonNull MaterialButton btnPrivacy,
      @NonNull MaterialButton btnSeeAllFriend, @NonNull MaterialButton btnSeeAllPosts,
      @NonNull ImageButton btnUploadCoverPhoto, @NonNull ImageButton btnUploadProfilePhoto,
      @NonNull ImageView coverPictureProfile, @NonNull TextView emailProfile,
      @NonNull ImageView ivVerifiedBadge, @NonNull TextView joinedProfile,
      @NonNull TextView lastCallTime, @NonNull LinearLayout layoutOwnProfile,
      @NonNull TextView phoneProfile, @NonNull CircleImageView profileImageProfile,
      @NonNull RecyclerView rvFriendListOnProfile, @NonNull RecyclerView rvUserPostProfile,
      @NonNull TextView studentInfoProfile, @NonNull TextView talkTimeCount,
      @NonNull TextView tvFriendsCount, @NonNull TextView tvFullName,
      @NonNull TextView userBioProfile) {
    this.rootView = rootView;
    this.addressProfile = addressProfile;
    this.backButtonProfile = backButtonProfile;
    this.backgroundProfile = backgroundProfile;
    this.birthDateProfile = birthDateProfile;
    this.bloodGroupProfile = bloodGroupProfile;
    this.btnApplyReferCode = btnApplyReferCode;
    this.btnEditInfo = btnEditInfo;
    this.btnPrivacy = btnPrivacy;
    this.btnSeeAllFriend = btnSeeAllFriend;
    this.btnSeeAllPosts = btnSeeAllPosts;
    this.btnUploadCoverPhoto = btnUploadCoverPhoto;
    this.btnUploadProfilePhoto = btnUploadProfilePhoto;
    this.coverPictureProfile = coverPictureProfile;
    this.emailProfile = emailProfile;
    this.ivVerifiedBadge = ivVerifiedBadge;
    this.joinedProfile = joinedProfile;
    this.lastCallTime = lastCallTime;
    this.layoutOwnProfile = layoutOwnProfile;
    this.phoneProfile = phoneProfile;
    this.profileImageProfile = profileImageProfile;
    this.rvFriendListOnProfile = rvFriendListOnProfile;
    this.rvUserPostProfile = rvUserPostProfile;
    this.studentInfoProfile = studentInfoProfile;
    this.talkTimeCount = talkTimeCount;
    this.tvFriendsCount = tvFriendsCount;
    this.tvFullName = tvFullName;
    this.userBioProfile = userBioProfile;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOwnProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOwnProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_own_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOwnProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address_profile;
      TextView addressProfile = ViewBindings.findChildViewById(rootView, id);
      if (addressProfile == null) {
        break missingId;
      }

      id = R.id.back_button_profile;
      ImageButton backButtonProfile = ViewBindings.findChildViewById(rootView, id);
      if (backButtonProfile == null) {
        break missingId;
      }

      id = R.id.background_profile;
      RelativeLayout backgroundProfile = ViewBindings.findChildViewById(rootView, id);
      if (backgroundProfile == null) {
        break missingId;
      }

      id = R.id.birth_date_profile;
      TextView birthDateProfile = ViewBindings.findChildViewById(rootView, id);
      if (birthDateProfile == null) {
        break missingId;
      }

      id = R.id.blood_group_profile;
      TextView bloodGroupProfile = ViewBindings.findChildViewById(rootView, id);
      if (bloodGroupProfile == null) {
        break missingId;
      }

      id = R.id.btn_apply_refer_code;
      MaterialButton btnApplyReferCode = ViewBindings.findChildViewById(rootView, id);
      if (btnApplyReferCode == null) {
        break missingId;
      }

      id = R.id.btn_edit_info;
      MaterialButton btnEditInfo = ViewBindings.findChildViewById(rootView, id);
      if (btnEditInfo == null) {
        break missingId;
      }

      id = R.id.btn_privacy;
      MaterialButton btnPrivacy = ViewBindings.findChildViewById(rootView, id);
      if (btnPrivacy == null) {
        break missingId;
      }

      id = R.id.btn_see_all_friend;
      MaterialButton btnSeeAllFriend = ViewBindings.findChildViewById(rootView, id);
      if (btnSeeAllFriend == null) {
        break missingId;
      }

      id = R.id.btn_see_all_posts;
      MaterialButton btnSeeAllPosts = ViewBindings.findChildViewById(rootView, id);
      if (btnSeeAllPosts == null) {
        break missingId;
      }

      id = R.id.btn_upload_cover_photo;
      ImageButton btnUploadCoverPhoto = ViewBindings.findChildViewById(rootView, id);
      if (btnUploadCoverPhoto == null) {
        break missingId;
      }

      id = R.id.btn_upload_profile_photo;
      ImageButton btnUploadProfilePhoto = ViewBindings.findChildViewById(rootView, id);
      if (btnUploadProfilePhoto == null) {
        break missingId;
      }

      id = R.id.cover_picture_profile;
      ImageView coverPictureProfile = ViewBindings.findChildViewById(rootView, id);
      if (coverPictureProfile == null) {
        break missingId;
      }

      id = R.id.email_profile;
      TextView emailProfile = ViewBindings.findChildViewById(rootView, id);
      if (emailProfile == null) {
        break missingId;
      }

      id = R.id.iv_verified_badge;
      ImageView ivVerifiedBadge = ViewBindings.findChildViewById(rootView, id);
      if (ivVerifiedBadge == null) {
        break missingId;
      }

      id = R.id.joined_profile;
      TextView joinedProfile = ViewBindings.findChildViewById(rootView, id);
      if (joinedProfile == null) {
        break missingId;
      }

      id = R.id.last_call_time;
      TextView lastCallTime = ViewBindings.findChildViewById(rootView, id);
      if (lastCallTime == null) {
        break missingId;
      }

      id = R.id.layout_own_profile;
      LinearLayout layoutOwnProfile = ViewBindings.findChildViewById(rootView, id);
      if (layoutOwnProfile == null) {
        break missingId;
      }

      id = R.id.phone_profile;
      TextView phoneProfile = ViewBindings.findChildViewById(rootView, id);
      if (phoneProfile == null) {
        break missingId;
      }

      id = R.id.profile_image_profile;
      CircleImageView profileImageProfile = ViewBindings.findChildViewById(rootView, id);
      if (profileImageProfile == null) {
        break missingId;
      }

      id = R.id.rv_friend_list_on_profile;
      RecyclerView rvFriendListOnProfile = ViewBindings.findChildViewById(rootView, id);
      if (rvFriendListOnProfile == null) {
        break missingId;
      }

      id = R.id.rv_user_post_profile;
      RecyclerView rvUserPostProfile = ViewBindings.findChildViewById(rootView, id);
      if (rvUserPostProfile == null) {
        break missingId;
      }

      id = R.id.studentInfo_profile;
      TextView studentInfoProfile = ViewBindings.findChildViewById(rootView, id);
      if (studentInfoProfile == null) {
        break missingId;
      }

      id = R.id.talk_time_count;
      TextView talkTimeCount = ViewBindings.findChildViewById(rootView, id);
      if (talkTimeCount == null) {
        break missingId;
      }

      id = R.id.tv_friends_count;
      TextView tvFriendsCount = ViewBindings.findChildViewById(rootView, id);
      if (tvFriendsCount == null) {
        break missingId;
      }

      id = R.id.tv_full_name;
      TextView tvFullName = ViewBindings.findChildViewById(rootView, id);
      if (tvFullName == null) {
        break missingId;
      }

      id = R.id.user_bio_profile;
      TextView userBioProfile = ViewBindings.findChildViewById(rootView, id);
      if (userBioProfile == null) {
        break missingId;
      }

      return new ActivityOwnProfileBinding((NestedScrollView) rootView, addressProfile,
          backButtonProfile, backgroundProfile, birthDateProfile, bloodGroupProfile,
          btnApplyReferCode, btnEditInfo, btnPrivacy, btnSeeAllFriend, btnSeeAllPosts,
          btnUploadCoverPhoto, btnUploadProfilePhoto, coverPictureProfile, emailProfile,
          ivVerifiedBadge, joinedProfile, lastCallTime, layoutOwnProfile, phoneProfile,
          profileImageProfile, rvFriendListOnProfile, rvUserPostProfile, studentInfoProfile,
          talkTimeCount, tvFriendsCount, tvFullName, userBioProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
