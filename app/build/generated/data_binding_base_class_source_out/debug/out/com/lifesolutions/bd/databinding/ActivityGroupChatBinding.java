// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public final class ActivityGroupChatBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppBarLayout appBarGroupChat;

  @NonNull
  public final ImageView attachFileGroupChat;

  @NonNull
  public final EditText editTextGroupChat;

  @NonNull
  public final RelativeLayout groupItemGroupChat;

  @NonNull
  public final TextView groupNameGroupChat;

  @NonNull
  public final CircleImageView groupPictureGroupChat;

  @NonNull
  public final RelativeLayout inputLayoutGroupMessage;

  @NonNull
  public final ProgressBar progressbarGroupChat;

  @NonNull
  public final RecyclerView recyclerViewGroupChat;

  @NonNull
  public final ImageButton sendButtonGroupChat;

  @NonNull
  public final Toolbar toolbarGroupChat;

  @NonNull
  public final ImageView voiceMessageGroupChat;

  private ActivityGroupChatBinding(@NonNull RelativeLayout rootView,
      @NonNull AppBarLayout appBarGroupChat, @NonNull ImageView attachFileGroupChat,
      @NonNull EditText editTextGroupChat, @NonNull RelativeLayout groupItemGroupChat,
      @NonNull TextView groupNameGroupChat, @NonNull CircleImageView groupPictureGroupChat,
      @NonNull RelativeLayout inputLayoutGroupMessage, @NonNull ProgressBar progressbarGroupChat,
      @NonNull RecyclerView recyclerViewGroupChat, @NonNull ImageButton sendButtonGroupChat,
      @NonNull Toolbar toolbarGroupChat, @NonNull ImageView voiceMessageGroupChat) {
    this.rootView = rootView;
    this.appBarGroupChat = appBarGroupChat;
    this.attachFileGroupChat = attachFileGroupChat;
    this.editTextGroupChat = editTextGroupChat;
    this.groupItemGroupChat = groupItemGroupChat;
    this.groupNameGroupChat = groupNameGroupChat;
    this.groupPictureGroupChat = groupPictureGroupChat;
    this.inputLayoutGroupMessage = inputLayoutGroupMessage;
    this.progressbarGroupChat = progressbarGroupChat;
    this.recyclerViewGroupChat = recyclerViewGroupChat;
    this.sendButtonGroupChat = sendButtonGroupChat;
    this.toolbarGroupChat = toolbarGroupChat;
    this.voiceMessageGroupChat = voiceMessageGroupChat;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGroupChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGroupChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_group_chat, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGroupChatBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appBar_group_chat;
      AppBarLayout appBarGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (appBarGroupChat == null) {
        break missingId;
      }

      id = R.id.attach_file_group_chat;
      ImageView attachFileGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (attachFileGroupChat == null) {
        break missingId;
      }

      id = R.id.edit_text_group_chat;
      EditText editTextGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (editTextGroupChat == null) {
        break missingId;
      }

      id = R.id.group_item_group_chat;
      RelativeLayout groupItemGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (groupItemGroupChat == null) {
        break missingId;
      }

      id = R.id.group_name_group_chat;
      TextView groupNameGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (groupNameGroupChat == null) {
        break missingId;
      }

      id = R.id.group_picture_group_chat;
      CircleImageView groupPictureGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (groupPictureGroupChat == null) {
        break missingId;
      }

      id = R.id.input_layout_group_message;
      RelativeLayout inputLayoutGroupMessage = ViewBindings.findChildViewById(rootView, id);
      if (inputLayoutGroupMessage == null) {
        break missingId;
      }

      id = R.id.progressbar_group_chat;
      ProgressBar progressbarGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (progressbarGroupChat == null) {
        break missingId;
      }

      id = R.id.recyclerView_group_chat;
      RecyclerView recyclerViewGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewGroupChat == null) {
        break missingId;
      }

      id = R.id.send_button_group_chat;
      ImageButton sendButtonGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (sendButtonGroupChat == null) {
        break missingId;
      }

      id = R.id.toolbar_group_chat;
      Toolbar toolbarGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (toolbarGroupChat == null) {
        break missingId;
      }

      id = R.id.voice_message_group_chat;
      ImageView voiceMessageGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (voiceMessageGroupChat == null) {
        break missingId;
      }

      return new ActivityGroupChatBinding((RelativeLayout) rootView, appBarGroupChat,
          attachFileGroupChat, editTextGroupChat, groupItemGroupChat, groupNameGroupChat,
          groupPictureGroupChat, inputLayoutGroupMessage, progressbarGroupChat,
          recyclerViewGroupChat, sendButtonGroupChat, toolbarGroupChat, voiceMessageGroupChat);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
