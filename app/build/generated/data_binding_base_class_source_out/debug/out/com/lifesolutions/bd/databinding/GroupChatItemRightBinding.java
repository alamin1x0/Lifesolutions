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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class GroupChatItemRightBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView fileImage;

  @NonNull
  public final LinearLayout fileMessageLayout;

  @NonNull
  public final TextView groupMessageTime;

  @NonNull
  public final LinearLayout imageMessageLayout;

  @NonNull
  public final ImageView ivSeenIcon;

  @NonNull
  public final CircleImageView ivWhoSent;

  @NonNull
  public final LinearLayout mainGroupMessageArea;

  @NonNull
  public final TextView mgsSenderName;

  @NonNull
  public final ImageView showImage;

  @NonNull
  public final TextView showMessage;

  @NonNull
  public final LinearLayout textMessageLayout;

  @NonNull
  public final LinearLayout topSenderInfo;

  @NonNull
  public final ImageView voiceIconMessage;

  @NonNull
  public final LinearLayout voiceMessageLayout;

  private GroupChatItemRightBinding(@NonNull RelativeLayout rootView, @NonNull TextView fileImage,
      @NonNull LinearLayout fileMessageLayout, @NonNull TextView groupMessageTime,
      @NonNull LinearLayout imageMessageLayout, @NonNull ImageView ivSeenIcon,
      @NonNull CircleImageView ivWhoSent, @NonNull LinearLayout mainGroupMessageArea,
      @NonNull TextView mgsSenderName, @NonNull ImageView showImage, @NonNull TextView showMessage,
      @NonNull LinearLayout textMessageLayout, @NonNull LinearLayout topSenderInfo,
      @NonNull ImageView voiceIconMessage, @NonNull LinearLayout voiceMessageLayout) {
    this.rootView = rootView;
    this.fileImage = fileImage;
    this.fileMessageLayout = fileMessageLayout;
    this.groupMessageTime = groupMessageTime;
    this.imageMessageLayout = imageMessageLayout;
    this.ivSeenIcon = ivSeenIcon;
    this.ivWhoSent = ivWhoSent;
    this.mainGroupMessageArea = mainGroupMessageArea;
    this.mgsSenderName = mgsSenderName;
    this.showImage = showImage;
    this.showMessage = showMessage;
    this.textMessageLayout = textMessageLayout;
    this.topSenderInfo = topSenderInfo;
    this.voiceIconMessage = voiceIconMessage;
    this.voiceMessageLayout = voiceMessageLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static GroupChatItemRightBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static GroupChatItemRightBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.group_chat_item_right, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static GroupChatItemRightBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.file_image;
      TextView fileImage = ViewBindings.findChildViewById(rootView, id);
      if (fileImage == null) {
        break missingId;
      }

      id = R.id.file_message_layout;
      LinearLayout fileMessageLayout = ViewBindings.findChildViewById(rootView, id);
      if (fileMessageLayout == null) {
        break missingId;
      }

      id = R.id.group_message_time;
      TextView groupMessageTime = ViewBindings.findChildViewById(rootView, id);
      if (groupMessageTime == null) {
        break missingId;
      }

      id = R.id.image_message_layout;
      LinearLayout imageMessageLayout = ViewBindings.findChildViewById(rootView, id);
      if (imageMessageLayout == null) {
        break missingId;
      }

      id = R.id.iv_seen_icon;
      ImageView ivSeenIcon = ViewBindings.findChildViewById(rootView, id);
      if (ivSeenIcon == null) {
        break missingId;
      }

      id = R.id.iv_who_sent;
      CircleImageView ivWhoSent = ViewBindings.findChildViewById(rootView, id);
      if (ivWhoSent == null) {
        break missingId;
      }

      id = R.id.main_group_message_area;
      LinearLayout mainGroupMessageArea = ViewBindings.findChildViewById(rootView, id);
      if (mainGroupMessageArea == null) {
        break missingId;
      }

      id = R.id.mgs_sender_name;
      TextView mgsSenderName = ViewBindings.findChildViewById(rootView, id);
      if (mgsSenderName == null) {
        break missingId;
      }

      id = R.id.show_image;
      ImageView showImage = ViewBindings.findChildViewById(rootView, id);
      if (showImage == null) {
        break missingId;
      }

      id = R.id.show_message;
      TextView showMessage = ViewBindings.findChildViewById(rootView, id);
      if (showMessage == null) {
        break missingId;
      }

      id = R.id.text_message_layout;
      LinearLayout textMessageLayout = ViewBindings.findChildViewById(rootView, id);
      if (textMessageLayout == null) {
        break missingId;
      }

      id = R.id.top_sender_info;
      LinearLayout topSenderInfo = ViewBindings.findChildViewById(rootView, id);
      if (topSenderInfo == null) {
        break missingId;
      }

      id = R.id.voice_icon_message;
      ImageView voiceIconMessage = ViewBindings.findChildViewById(rootView, id);
      if (voiceIconMessage == null) {
        break missingId;
      }

      id = R.id.voice_message_layout;
      LinearLayout voiceMessageLayout = ViewBindings.findChildViewById(rootView, id);
      if (voiceMessageLayout == null) {
        break missingId;
      }

      return new GroupChatItemRightBinding((RelativeLayout) rootView, fileImage, fileMessageLayout,
          groupMessageTime, imageMessageLayout, ivSeenIcon, ivWhoSent, mainGroupMessageArea,
          mgsSenderName, showImage, showMessage, textMessageLayout, topSenderInfo, voiceIconMessage,
          voiceMessageLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
