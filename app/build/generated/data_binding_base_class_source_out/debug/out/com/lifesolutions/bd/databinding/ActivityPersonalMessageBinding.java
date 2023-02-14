// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPersonalMessageBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout actionAttachLayoutPersonal;

  @NonNull
  public final AppBarLayout appBarPersonalMessage;

  @NonNull
  public final EditText editTextPersonalMessage;

  @NonNull
  public final RelativeLayout messageSection;

  @NonNull
  public final ImageView personalMessageAttach;

  @NonNull
  public final RelativeLayout personalMessageLayout;

  @NonNull
  public final RelativeLayout personalMessageLayoutParent;

  @NonNull
  public final ImageView personalMessagePicture;

  @NonNull
  public final ImageView personalMessageVoice;

  @NonNull
  public final CircleImageView profilePictureMessage;

  @NonNull
  public final ProgressBar progressbarPersonalMessage;

  @NonNull
  public final RecyclerView recyclerViewPersonalMessage;

  @NonNull
  public final ImageView sendButtonPersonalMessage;

  @NonNull
  public final Toolbar toolbarPersonalMessage;

  @NonNull
  public final TextView tvCallDisabledPersonalMessage;

  @NonNull
  public final TextView userNamePersonalMessage;

  @NonNull
  public final TextView userStatusMessage;

  @NonNull
  public final RelativeLayout usersItemMessage;

  private ActivityPersonalMessageBinding(@NonNull RelativeLayout rootView,
      @NonNull LinearLayout actionAttachLayoutPersonal, @NonNull AppBarLayout appBarPersonalMessage,
      @NonNull EditText editTextPersonalMessage, @NonNull RelativeLayout messageSection,
      @NonNull ImageView personalMessageAttach, @NonNull RelativeLayout personalMessageLayout,
      @NonNull RelativeLayout personalMessageLayoutParent,
      @NonNull ImageView personalMessagePicture, @NonNull ImageView personalMessageVoice,
      @NonNull CircleImageView profilePictureMessage,
      @NonNull ProgressBar progressbarPersonalMessage,
      @NonNull RecyclerView recyclerViewPersonalMessage,
      @NonNull ImageView sendButtonPersonalMessage, @NonNull Toolbar toolbarPersonalMessage,
      @NonNull TextView tvCallDisabledPersonalMessage, @NonNull TextView userNamePersonalMessage,
      @NonNull TextView userStatusMessage, @NonNull RelativeLayout usersItemMessage) {
    this.rootView = rootView;
    this.actionAttachLayoutPersonal = actionAttachLayoutPersonal;
    this.appBarPersonalMessage = appBarPersonalMessage;
    this.editTextPersonalMessage = editTextPersonalMessage;
    this.messageSection = messageSection;
    this.personalMessageAttach = personalMessageAttach;
    this.personalMessageLayout = personalMessageLayout;
    this.personalMessageLayoutParent = personalMessageLayoutParent;
    this.personalMessagePicture = personalMessagePicture;
    this.personalMessageVoice = personalMessageVoice;
    this.profilePictureMessage = profilePictureMessage;
    this.progressbarPersonalMessage = progressbarPersonalMessage;
    this.recyclerViewPersonalMessage = recyclerViewPersonalMessage;
    this.sendButtonPersonalMessage = sendButtonPersonalMessage;
    this.toolbarPersonalMessage = toolbarPersonalMessage;
    this.tvCallDisabledPersonalMessage = tvCallDisabledPersonalMessage;
    this.userNamePersonalMessage = userNamePersonalMessage;
    this.userStatusMessage = userStatusMessage;
    this.usersItemMessage = usersItemMessage;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPersonalMessageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPersonalMessageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_personal_message, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPersonalMessageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.action_attach_layout_personal;
      LinearLayout actionAttachLayoutPersonal = ViewBindings.findChildViewById(rootView, id);
      if (actionAttachLayoutPersonal == null) {
        break missingId;
      }

      id = R.id.app_bar_personal_message;
      AppBarLayout appBarPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (appBarPersonalMessage == null) {
        break missingId;
      }

      id = R.id.edit_text_personal_message;
      EditText editTextPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (editTextPersonalMessage == null) {
        break missingId;
      }

      id = R.id.message_section;
      RelativeLayout messageSection = ViewBindings.findChildViewById(rootView, id);
      if (messageSection == null) {
        break missingId;
      }

      id = R.id.personal_message_attach;
      ImageView personalMessageAttach = ViewBindings.findChildViewById(rootView, id);
      if (personalMessageAttach == null) {
        break missingId;
      }

      id = R.id.personal_message_layout;
      RelativeLayout personalMessageLayout = ViewBindings.findChildViewById(rootView, id);
      if (personalMessageLayout == null) {
        break missingId;
      }

      RelativeLayout personalMessageLayoutParent = (RelativeLayout) rootView;

      id = R.id.personal_message_picture;
      ImageView personalMessagePicture = ViewBindings.findChildViewById(rootView, id);
      if (personalMessagePicture == null) {
        break missingId;
      }

      id = R.id.personal_message_voice;
      ImageView personalMessageVoice = ViewBindings.findChildViewById(rootView, id);
      if (personalMessageVoice == null) {
        break missingId;
      }

      id = R.id.profile_picture_message;
      CircleImageView profilePictureMessage = ViewBindings.findChildViewById(rootView, id);
      if (profilePictureMessage == null) {
        break missingId;
      }

      id = R.id.progressbar_personal_message;
      ProgressBar progressbarPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (progressbarPersonalMessage == null) {
        break missingId;
      }

      id = R.id.recycler_view_personal_message;
      RecyclerView recyclerViewPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewPersonalMessage == null) {
        break missingId;
      }

      id = R.id.send_button_personal_message;
      ImageView sendButtonPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (sendButtonPersonalMessage == null) {
        break missingId;
      }

      id = R.id.toolbar_personal_message;
      Toolbar toolbarPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (toolbarPersonalMessage == null) {
        break missingId;
      }

      id = R.id.tv_call_disabled_personal_message;
      TextView tvCallDisabledPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (tvCallDisabledPersonalMessage == null) {
        break missingId;
      }

      id = R.id.user_name_personal_message;
      TextView userNamePersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (userNamePersonalMessage == null) {
        break missingId;
      }

      id = R.id.user_status_message;
      TextView userStatusMessage = ViewBindings.findChildViewById(rootView, id);
      if (userStatusMessage == null) {
        break missingId;
      }

      id = R.id.users_item_message;
      RelativeLayout usersItemMessage = ViewBindings.findChildViewById(rootView, id);
      if (usersItemMessage == null) {
        break missingId;
      }

      return new ActivityPersonalMessageBinding((RelativeLayout) rootView,
          actionAttachLayoutPersonal, appBarPersonalMessage, editTextPersonalMessage,
          messageSection, personalMessageAttach, personalMessageLayout, personalMessageLayoutParent,
          personalMessagePicture, personalMessageVoice, profilePictureMessage,
          progressbarPersonalMessage, recyclerViewPersonalMessage, sendButtonPersonalMessage,
          toolbarPersonalMessage, tvCallDisabledPersonalMessage, userNamePersonalMessage,
          userStatusMessage, usersItemMessage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}