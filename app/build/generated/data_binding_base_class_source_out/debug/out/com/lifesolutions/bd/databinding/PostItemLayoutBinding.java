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

public final class PostItemLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout backgroundLayoutPostItem;

  @NonNull
  public final RelativeLayout commentButtonPostItem;

  @NonNull
  public final TextView datePostItem;

  @NonNull
  public final ImageView imgViewLine;

  @NonNull
  public final RelativeLayout likeButtonPostItem;

  @NonNull
  public final LinearLayout likeCountLayoutPostItem;

  @NonNull
  public final ImageView likePicPostItem;

  @NonNull
  public final TextView likeTextPostItem;

  @NonNull
  public final TextView linkViewTitle;

  @NonNull
  public final ImageView postImagePostItem;

  @NonNull
  public final LinearLayout postItem;

  @NonNull
  public final TextView postTitlePostItem;

  @NonNull
  public final RelativeLayout rellay2;

  @NonNull
  public final RelativeLayout shareButtonPostItem;

  @NonNull
  public final TextView textBackgroundPostItem;

  @NonNull
  public final TextView tvCommentCountPostItem;

  @NonNull
  public final TextView tvLikeCountPostItem;

  @NonNull
  public final CircleImageView userImagePostItem;

  @NonNull
  public final TextView userNamePostItem;

  private PostItemLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout backgroundLayoutPostItem, @NonNull RelativeLayout commentButtonPostItem,
      @NonNull TextView datePostItem, @NonNull ImageView imgViewLine,
      @NonNull RelativeLayout likeButtonPostItem, @NonNull LinearLayout likeCountLayoutPostItem,
      @NonNull ImageView likePicPostItem, @NonNull TextView likeTextPostItem,
      @NonNull TextView linkViewTitle, @NonNull ImageView postImagePostItem,
      @NonNull LinearLayout postItem, @NonNull TextView postTitlePostItem,
      @NonNull RelativeLayout rellay2, @NonNull RelativeLayout shareButtonPostItem,
      @NonNull TextView textBackgroundPostItem, @NonNull TextView tvCommentCountPostItem,
      @NonNull TextView tvLikeCountPostItem, @NonNull CircleImageView userImagePostItem,
      @NonNull TextView userNamePostItem) {
    this.rootView = rootView;
    this.backgroundLayoutPostItem = backgroundLayoutPostItem;
    this.commentButtonPostItem = commentButtonPostItem;
    this.datePostItem = datePostItem;
    this.imgViewLine = imgViewLine;
    this.likeButtonPostItem = likeButtonPostItem;
    this.likeCountLayoutPostItem = likeCountLayoutPostItem;
    this.likePicPostItem = likePicPostItem;
    this.likeTextPostItem = likeTextPostItem;
    this.linkViewTitle = linkViewTitle;
    this.postImagePostItem = postImagePostItem;
    this.postItem = postItem;
    this.postTitlePostItem = postTitlePostItem;
    this.rellay2 = rellay2;
    this.shareButtonPostItem = shareButtonPostItem;
    this.textBackgroundPostItem = textBackgroundPostItem;
    this.tvCommentCountPostItem = tvCommentCountPostItem;
    this.tvLikeCountPostItem = tvLikeCountPostItem;
    this.userImagePostItem = userImagePostItem;
    this.userNamePostItem = userNamePostItem;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PostItemLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PostItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.post_item_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PostItemLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.background_layout_post_item;
      LinearLayout backgroundLayoutPostItem = ViewBindings.findChildViewById(rootView, id);
      if (backgroundLayoutPostItem == null) {
        break missingId;
      }

      id = R.id.comment_button_post_item;
      RelativeLayout commentButtonPostItem = ViewBindings.findChildViewById(rootView, id);
      if (commentButtonPostItem == null) {
        break missingId;
      }

      id = R.id.date_post_item;
      TextView datePostItem = ViewBindings.findChildViewById(rootView, id);
      if (datePostItem == null) {
        break missingId;
      }

      id = R.id.imgView_line;
      ImageView imgViewLine = ViewBindings.findChildViewById(rootView, id);
      if (imgViewLine == null) {
        break missingId;
      }

      id = R.id.like_button_post_item;
      RelativeLayout likeButtonPostItem = ViewBindings.findChildViewById(rootView, id);
      if (likeButtonPostItem == null) {
        break missingId;
      }

      id = R.id.like_count_layout_post_item;
      LinearLayout likeCountLayoutPostItem = ViewBindings.findChildViewById(rootView, id);
      if (likeCountLayoutPostItem == null) {
        break missingId;
      }

      id = R.id.like_pic_post_item;
      ImageView likePicPostItem = ViewBindings.findChildViewById(rootView, id);
      if (likePicPostItem == null) {
        break missingId;
      }

      id = R.id.like_text_post_item;
      TextView likeTextPostItem = ViewBindings.findChildViewById(rootView, id);
      if (likeTextPostItem == null) {
        break missingId;
      }

      id = R.id.link_view_title;
      TextView linkViewTitle = ViewBindings.findChildViewById(rootView, id);
      if (linkViewTitle == null) {
        break missingId;
      }

      id = R.id.post_image_post_item;
      ImageView postImagePostItem = ViewBindings.findChildViewById(rootView, id);
      if (postImagePostItem == null) {
        break missingId;
      }

      LinearLayout postItem = (LinearLayout) rootView;

      id = R.id.post_title_post_item;
      TextView postTitlePostItem = ViewBindings.findChildViewById(rootView, id);
      if (postTitlePostItem == null) {
        break missingId;
      }

      id = R.id.rellay2;
      RelativeLayout rellay2 = ViewBindings.findChildViewById(rootView, id);
      if (rellay2 == null) {
        break missingId;
      }

      id = R.id.share_button_post_item;
      RelativeLayout shareButtonPostItem = ViewBindings.findChildViewById(rootView, id);
      if (shareButtonPostItem == null) {
        break missingId;
      }

      id = R.id.text_background_post_item;
      TextView textBackgroundPostItem = ViewBindings.findChildViewById(rootView, id);
      if (textBackgroundPostItem == null) {
        break missingId;
      }

      id = R.id.tv_comment_count_post_item;
      TextView tvCommentCountPostItem = ViewBindings.findChildViewById(rootView, id);
      if (tvCommentCountPostItem == null) {
        break missingId;
      }

      id = R.id.tv_like_count_post_item;
      TextView tvLikeCountPostItem = ViewBindings.findChildViewById(rootView, id);
      if (tvLikeCountPostItem == null) {
        break missingId;
      }

      id = R.id.user_image_post_item;
      CircleImageView userImagePostItem = ViewBindings.findChildViewById(rootView, id);
      if (userImagePostItem == null) {
        break missingId;
      }

      id = R.id.user_name_post_item;
      TextView userNamePostItem = ViewBindings.findChildViewById(rootView, id);
      if (userNamePostItem == null) {
        break missingId;
      }

      return new PostItemLayoutBinding((LinearLayout) rootView, backgroundLayoutPostItem,
          commentButtonPostItem, datePostItem, imgViewLine, likeButtonPostItem,
          likeCountLayoutPostItem, likePicPostItem, likeTextPostItem, linkViewTitle,
          postImagePostItem, postItem, postTitlePostItem, rellay2, shareButtonPostItem,
          textBackgroundPostItem, tvCommentCountPostItem, tvLikeCountPostItem, userImagePostItem,
          userNamePostItem);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}