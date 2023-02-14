// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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

public final class ActivityCommentReplyBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppBarLayout appbarCommentReply;

  @NonNull
  public final ImageButton commentBtnReply;

  @NonNull
  public final EditText commentFieldReply;

  @NonNull
  public final RelativeLayout commentLayoutReply;

  @NonNull
  public final TextView commentReplyDateTime;

  @NonNull
  public final CircleImageView commenterPhotoReply;

  @NonNull
  public final CircleImageView imageCommentReply;

  @NonNull
  public final LinearLayout inputLayoutReply;

  @NonNull
  public final TextView messageCommentReply;

  @NonNull
  public final RelativeLayout messageLayoutReply;

  @NonNull
  public final TextView nameCommentReply;

  @NonNull
  public final ProgressBar progressbarCommentsReply;

  @NonNull
  public final RecyclerView recyclerViewCommentReply;

  @NonNull
  public final Toolbar toolbarCommentsReply;

  private ActivityCommentReplyBinding(@NonNull RelativeLayout rootView,
      @NonNull AppBarLayout appbarCommentReply, @NonNull ImageButton commentBtnReply,
      @NonNull EditText commentFieldReply, @NonNull RelativeLayout commentLayoutReply,
      @NonNull TextView commentReplyDateTime, @NonNull CircleImageView commenterPhotoReply,
      @NonNull CircleImageView imageCommentReply, @NonNull LinearLayout inputLayoutReply,
      @NonNull TextView messageCommentReply, @NonNull RelativeLayout messageLayoutReply,
      @NonNull TextView nameCommentReply, @NonNull ProgressBar progressbarCommentsReply,
      @NonNull RecyclerView recyclerViewCommentReply, @NonNull Toolbar toolbarCommentsReply) {
    this.rootView = rootView;
    this.appbarCommentReply = appbarCommentReply;
    this.commentBtnReply = commentBtnReply;
    this.commentFieldReply = commentFieldReply;
    this.commentLayoutReply = commentLayoutReply;
    this.commentReplyDateTime = commentReplyDateTime;
    this.commenterPhotoReply = commenterPhotoReply;
    this.imageCommentReply = imageCommentReply;
    this.inputLayoutReply = inputLayoutReply;
    this.messageCommentReply = messageCommentReply;
    this.messageLayoutReply = messageLayoutReply;
    this.nameCommentReply = nameCommentReply;
    this.progressbarCommentsReply = progressbarCommentsReply;
    this.recyclerViewCommentReply = recyclerViewCommentReply;
    this.toolbarCommentsReply = toolbarCommentsReply;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCommentReplyBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCommentReplyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_comment_reply, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCommentReplyBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appbar_comment_reply;
      AppBarLayout appbarCommentReply = ViewBindings.findChildViewById(rootView, id);
      if (appbarCommentReply == null) {
        break missingId;
      }

      id = R.id.comment_btn__reply;
      ImageButton commentBtnReply = ViewBindings.findChildViewById(rootView, id);
      if (commentBtnReply == null) {
        break missingId;
      }

      id = R.id.comment_field_reply;
      EditText commentFieldReply = ViewBindings.findChildViewById(rootView, id);
      if (commentFieldReply == null) {
        break missingId;
      }

      id = R.id.comment_layout_reply;
      RelativeLayout commentLayoutReply = ViewBindings.findChildViewById(rootView, id);
      if (commentLayoutReply == null) {
        break missingId;
      }

      id = R.id.comment_reply_date_time;
      TextView commentReplyDateTime = ViewBindings.findChildViewById(rootView, id);
      if (commentReplyDateTime == null) {
        break missingId;
      }

      id = R.id.commenter_photo_reply;
      CircleImageView commenterPhotoReply = ViewBindings.findChildViewById(rootView, id);
      if (commenterPhotoReply == null) {
        break missingId;
      }

      id = R.id.image_comment_reply;
      CircleImageView imageCommentReply = ViewBindings.findChildViewById(rootView, id);
      if (imageCommentReply == null) {
        break missingId;
      }

      id = R.id.input_layout_reply;
      LinearLayout inputLayoutReply = ViewBindings.findChildViewById(rootView, id);
      if (inputLayoutReply == null) {
        break missingId;
      }

      id = R.id.message_comment_reply;
      TextView messageCommentReply = ViewBindings.findChildViewById(rootView, id);
      if (messageCommentReply == null) {
        break missingId;
      }

      id = R.id.message_layout_reply;
      RelativeLayout messageLayoutReply = ViewBindings.findChildViewById(rootView, id);
      if (messageLayoutReply == null) {
        break missingId;
      }

      id = R.id.name_comment_reply;
      TextView nameCommentReply = ViewBindings.findChildViewById(rootView, id);
      if (nameCommentReply == null) {
        break missingId;
      }

      id = R.id.progressbar_comments_reply;
      ProgressBar progressbarCommentsReply = ViewBindings.findChildViewById(rootView, id);
      if (progressbarCommentsReply == null) {
        break missingId;
      }

      id = R.id.recyclerView_comment_reply;
      RecyclerView recyclerViewCommentReply = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewCommentReply == null) {
        break missingId;
      }

      id = R.id.toolbar_comments_reply;
      Toolbar toolbarCommentsReply = ViewBindings.findChildViewById(rootView, id);
      if (toolbarCommentsReply == null) {
        break missingId;
      }

      return new ActivityCommentReplyBinding((RelativeLayout) rootView, appbarCommentReply,
          commentBtnReply, commentFieldReply, commentLayoutReply, commentReplyDateTime,
          commenterPhotoReply, imageCommentReply, inputLayoutReply, messageCommentReply,
          messageLayoutReply, nameCommentReply, progressbarCommentsReply, recyclerViewCommentReply,
          toolbarCommentsReply);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
