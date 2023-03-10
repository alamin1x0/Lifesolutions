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
import androidx.appcompat.widget.AppCompatButton;
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

public final class ActivityGroupMessageBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout actionAttachLayoutPersonal;

  @NonNull
  public final AppBarLayout appBarPersonalMessage;

  @NonNull
  public final AppCompatButton btnRequestInfo;

  @NonNull
  public final EditText editTextPersonalMessage;

  @NonNull
  public final TextView groupLastMessage;

  @NonNull
  public final RelativeLayout groupMessageLayoutParent;

  @NonNull
  public final TextView groupNameMessage;

  @NonNull
  public final RelativeLayout messageSection;

  @NonNull
  public final ImageView personalMessageAttach;

  @NonNull
  public final RelativeLayout personalMessageLayout;

  @NonNull
  public final ImageView personalMessagePicture;

  @NonNull
  public final ImageView personalMessageVoice;

  @NonNull
  public final CircleImageView profilePictureMessage;

  @NonNull
  public final ProgressBar progressbarGroupMessage;

  @NonNull
  public final RecyclerView recyclerViewGroupMessage;

  @NonNull
  public final ImageView sendButtonPersonalMessage;

  @NonNull
  public final Toolbar toolbarGroupMessage;

  @NonNull
  public final RelativeLayout usersItemMessage;

  private ActivityGroupMessageBinding(@NonNull RelativeLayout rootView,
      @NonNull LinearLayout actionAttachLayoutPersonal, @NonNull AppBarLayout appBarPersonalMessage,
      @NonNull AppCompatButton btnRequestInfo, @NonNull EditText editTextPersonalMessage,
      @NonNull TextView groupLastMessage, @NonNull RelativeLayout groupMessageLayoutParent,
      @NonNull TextView groupNameMessage, @NonNull RelativeLayout messageSection,
      @NonNull ImageView personalMessageAttach, @NonNull RelativeLayout personalMessageLayout,
      @NonNull ImageView personalMessagePicture, @NonNull ImageView personalMessageVoice,
      @NonNull CircleImageView profilePictureMessage, @NonNull ProgressBar progressbarGroupMessage,
      @NonNull RecyclerView recyclerViewGroupMessage, @NonNull ImageView sendButtonPersonalMessage,
      @NonNull Toolbar toolbarGroupMessage, @NonNull RelativeLayout usersItemMessage) {
    this.rootView = rootView;
    this.actionAttachLayoutPersonal = actionAttachLayoutPersonal;
    this.appBarPersonalMessage = appBarPersonalMessage;
    this.btnRequestInfo = btnRequestInfo;
    this.editTextPersonalMessage = editTextPersonalMessage;
    this.groupLastMessage = groupLastMessage;
    this.groupMessageLayoutParent = groupMessageLayoutParent;
    this.groupNameMessage = groupNameMessage;
    this.messageSection = messageSection;
    this.personalMessageAttach = personalMessageAttach;
    this.personalMessageLayout = personalMessageLayout;
    this.personalMessagePicture = personalMessagePicture;
    this.personalMessageVoice = personalMessageVoice;
    this.profilePictureMessage = profilePictureMessage;
    this.progressbarGroupMessage = progressbarGroupMessage;
    this.recyclerViewGroupMessage = recyclerViewGroupMessage;
    this.sendButtonPersonalMessage = sendButtonPersonalMessage;
    this.toolbarGroupMessage = toolbarGroupMessage;
    this.usersItemMessage = usersItemMessage;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGroupMessageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGroupMessageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_group_message, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGroupMessageBinding bind(@NonNull View rootView) {
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

      id = R.id.btn_request_info;
      AppCompatButton btnRequestInfo = ViewBindings.findChildViewById(rootView, id);
      if (btnRequestInfo == null) {
        break missingId;
      }

      id = R.id.edit_text_personal_message;
      EditText editTextPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (editTextPersonalMessage == null) {
        break missingId;
      }

      id = R.id.group_last_message;
      TextView groupLastMessage = ViewBindings.findChildViewById(rootView, id);
      if (groupLastMessage == null) {
        break missingId;
      }

      RelativeLayout groupMessageLayoutParent = (RelativeLayout) rootView;

      id = R.id.group_name_message;
      TextView groupNameMessage = ViewBindings.findChildViewById(rootView, id);
      if (groupNameMessage == null) {
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

      id = R.id.progressbar_group_message;
      ProgressBar progressbarGroupMessage = ViewBindings.findChildViewById(rootView, id);
      if (progressbarGroupMessage == null) {
        break missingId;
      }

      id = R.id.recycler_view_group_message;
      RecyclerView recyclerViewGroupMessage = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewGroupMessage == null) {
        break missingId;
      }

      id = R.id.send_button_personal_message;
      ImageView sendButtonPersonalMessage = ViewBindings.findChildViewById(rootView, id);
      if (sendButtonPersonalMessage == null) {
        break missingId;
      }

      id = R.id.toolbar_group_message;
      Toolbar toolbarGroupMessage = ViewBindings.findChildViewById(rootView, id);
      if (toolbarGroupMessage == null) {
        break missingId;
      }

      id = R.id.users_item_message;
      RelativeLayout usersItemMessage = ViewBindings.findChildViewById(rootView, id);
      if (usersItemMessage == null) {
        break missingId;
      }

      return new ActivityGroupMessageBinding((RelativeLayout) rootView, actionAttachLayoutPersonal,
          appBarPersonalMessage, btnRequestInfo, editTextPersonalMessage, groupLastMessage,
          groupMessageLayoutParent, groupNameMessage, messageSection, personalMessageAttach,
          personalMessageLayout, personalMessagePicture, personalMessageVoice,
          profilePictureMessage, progressbarGroupMessage, recyclerViewGroupMessage,
          sendButtonPersonalMessage, toolbarGroupMessage, usersItemMessage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
