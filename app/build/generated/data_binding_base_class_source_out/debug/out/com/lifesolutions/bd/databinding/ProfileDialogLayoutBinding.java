// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ProfileDialogLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView addressDialogLeaderBoard;

  @NonNull
  public final TextView followersDialogLeaderBoard;

  @NonNull
  public final TextView friendsDialogLeaderBoard;

  @NonNull
  public final TextView nameDialogLeaderBoard;

  @NonNull
  public final TextView postsDialogLeaderBoard;

  @NonNull
  public final CircleImageView profileImageDialogLeaderBoard;

  private ProfileDialogLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull TextView addressDialogLeaderBoard, @NonNull TextView followersDialogLeaderBoard,
      @NonNull TextView friendsDialogLeaderBoard, @NonNull TextView nameDialogLeaderBoard,
      @NonNull TextView postsDialogLeaderBoard,
      @NonNull CircleImageView profileImageDialogLeaderBoard) {
    this.rootView = rootView;
    this.addressDialogLeaderBoard = addressDialogLeaderBoard;
    this.followersDialogLeaderBoard = followersDialogLeaderBoard;
    this.friendsDialogLeaderBoard = friendsDialogLeaderBoard;
    this.nameDialogLeaderBoard = nameDialogLeaderBoard;
    this.postsDialogLeaderBoard = postsDialogLeaderBoard;
    this.profileImageDialogLeaderBoard = profileImageDialogLeaderBoard;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ProfileDialogLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ProfileDialogLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.profile_dialog_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ProfileDialogLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address_dialog_leader_board;
      TextView addressDialogLeaderBoard = ViewBindings.findChildViewById(rootView, id);
      if (addressDialogLeaderBoard == null) {
        break missingId;
      }

      id = R.id.followers_dialog_leader_board;
      TextView followersDialogLeaderBoard = ViewBindings.findChildViewById(rootView, id);
      if (followersDialogLeaderBoard == null) {
        break missingId;
      }

      id = R.id.friends_dialog_leader_board;
      TextView friendsDialogLeaderBoard = ViewBindings.findChildViewById(rootView, id);
      if (friendsDialogLeaderBoard == null) {
        break missingId;
      }

      id = R.id.name_dialog_leader_board;
      TextView nameDialogLeaderBoard = ViewBindings.findChildViewById(rootView, id);
      if (nameDialogLeaderBoard == null) {
        break missingId;
      }

      id = R.id.posts_dialog_leader_board;
      TextView postsDialogLeaderBoard = ViewBindings.findChildViewById(rootView, id);
      if (postsDialogLeaderBoard == null) {
        break missingId;
      }

      id = R.id.profile_image_dialog_leader_board;
      CircleImageView profileImageDialogLeaderBoard = ViewBindings.findChildViewById(rootView, id);
      if (profileImageDialogLeaderBoard == null) {
        break missingId;
      }

      return new ProfileDialogLayoutBinding((LinearLayout) rootView, addressDialogLeaderBoard,
          followersDialogLeaderBoard, friendsDialogLeaderBoard, nameDialogLeaderBoard,
          postsDialogLeaderBoard, profileImageDialogLeaderBoard);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
