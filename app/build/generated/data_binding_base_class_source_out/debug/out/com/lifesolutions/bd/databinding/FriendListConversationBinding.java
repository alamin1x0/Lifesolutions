// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FriendListConversationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView btnChatUser;

  @NonNull
  public final TextView userFullName;

  @NonNull
  public final CircleImageView userProfilePic;

  private FriendListConversationBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView btnChatUser, @NonNull TextView userFullName,
      @NonNull CircleImageView userProfilePic) {
    this.rootView = rootView;
    this.btnChatUser = btnChatUser;
    this.userFullName = userFullName;
    this.userProfilePic = userProfilePic;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FriendListConversationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FriendListConversationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.friend_list_conversation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FriendListConversationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_chat_user;
      ImageView btnChatUser = ViewBindings.findChildViewById(rootView, id);
      if (btnChatUser == null) {
        break missingId;
      }

      id = R.id.user_full_name;
      TextView userFullName = ViewBindings.findChildViewById(rootView, id);
      if (userFullName == null) {
        break missingId;
      }

      id = R.id.user_profile_pic;
      CircleImageView userProfilePic = ViewBindings.findChildViewById(rootView, id);
      if (userProfilePic == null) {
        break missingId;
      }

      return new FriendListConversationBinding((ConstraintLayout) rootView, btnChatUser,
          userFullName, userProfilePic);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
