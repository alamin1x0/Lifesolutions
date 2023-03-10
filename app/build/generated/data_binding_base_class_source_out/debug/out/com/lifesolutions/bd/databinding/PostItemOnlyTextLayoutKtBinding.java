// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public final class PostItemOnlyTextLayoutKtBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView angryReact;

  @NonNull
  public final LinearLayout backgroundLayoutPostItemKt;

  @NonNull
  public final ImageButton btnMoreOnFeedColorText;

  @NonNull
  public final ImageView commentIcon;

  @NonNull
  public final LinearLayout feedCardViewLayout;

  @NonNull
  public final ImageView hahaReact;

  @NonNull
  public final ImageView heartBigViewWithoutImage;

  @NonNull
  public final ImageView imgViewLine;

  @NonNull
  public final CircleImageView ivAuthorWithoutImageKt;

  @NonNull
  public final LinearLayout layoutBtnCommentWithoutImage;

  @NonNull
  public final ImageView likeBtnWithoutImageBorder;

  @NonNull
  public final ImageView likeReact;

  @NonNull
  public final ImageView loveReact;

  @NonNull
  public final LinearLayout rawTextLayout;

  @NonNull
  public final ConstraintLayout reactionLayout;

  @NonNull
  public final ImageView sadReact;

  @NonNull
  public final TextView shareBtnText;

  @NonNull
  public final ImageView shareButtonPostItemKt;

  @NonNull
  public final LinearLayout shareLayoutWithoutImage;

  @NonNull
  public final TextView tvAuthorNameWithoutImageKt;

  @NonNull
  public final TextView tvCommentCountWithoutImageItemKt;

  @NonNull
  public final TextView tvDateWithoutImageItemKt;

  @NonNull
  public final TextView tvLikeCountWithoutImageItemKt;

  @NonNull
  public final TextView tvRawText;

  @NonNull
  public final TextView tvSmallTextKt;

  @NonNull
  public final ImageView wowReact;

  private PostItemOnlyTextLayoutKtBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView angryReact, @NonNull LinearLayout backgroundLayoutPostItemKt,
      @NonNull ImageButton btnMoreOnFeedColorText, @NonNull ImageView commentIcon,
      @NonNull LinearLayout feedCardViewLayout, @NonNull ImageView hahaReact,
      @NonNull ImageView heartBigViewWithoutImage, @NonNull ImageView imgViewLine,
      @NonNull CircleImageView ivAuthorWithoutImageKt,
      @NonNull LinearLayout layoutBtnCommentWithoutImage,
      @NonNull ImageView likeBtnWithoutImageBorder, @NonNull ImageView likeReact,
      @NonNull ImageView loveReact, @NonNull LinearLayout rawTextLayout,
      @NonNull ConstraintLayout reactionLayout, @NonNull ImageView sadReact,
      @NonNull TextView shareBtnText, @NonNull ImageView shareButtonPostItemKt,
      @NonNull LinearLayout shareLayoutWithoutImage, @NonNull TextView tvAuthorNameWithoutImageKt,
      @NonNull TextView tvCommentCountWithoutImageItemKt,
      @NonNull TextView tvDateWithoutImageItemKt, @NonNull TextView tvLikeCountWithoutImageItemKt,
      @NonNull TextView tvRawText, @NonNull TextView tvSmallTextKt, @NonNull ImageView wowReact) {
    this.rootView = rootView;
    this.angryReact = angryReact;
    this.backgroundLayoutPostItemKt = backgroundLayoutPostItemKt;
    this.btnMoreOnFeedColorText = btnMoreOnFeedColorText;
    this.commentIcon = commentIcon;
    this.feedCardViewLayout = feedCardViewLayout;
    this.hahaReact = hahaReact;
    this.heartBigViewWithoutImage = heartBigViewWithoutImage;
    this.imgViewLine = imgViewLine;
    this.ivAuthorWithoutImageKt = ivAuthorWithoutImageKt;
    this.layoutBtnCommentWithoutImage = layoutBtnCommentWithoutImage;
    this.likeBtnWithoutImageBorder = likeBtnWithoutImageBorder;
    this.likeReact = likeReact;
    this.loveReact = loveReact;
    this.rawTextLayout = rawTextLayout;
    this.reactionLayout = reactionLayout;
    this.sadReact = sadReact;
    this.shareBtnText = shareBtnText;
    this.shareButtonPostItemKt = shareButtonPostItemKt;
    this.shareLayoutWithoutImage = shareLayoutWithoutImage;
    this.tvAuthorNameWithoutImageKt = tvAuthorNameWithoutImageKt;
    this.tvCommentCountWithoutImageItemKt = tvCommentCountWithoutImageItemKt;
    this.tvDateWithoutImageItemKt = tvDateWithoutImageItemKt;
    this.tvLikeCountWithoutImageItemKt = tvLikeCountWithoutImageItemKt;
    this.tvRawText = tvRawText;
    this.tvSmallTextKt = tvSmallTextKt;
    this.wowReact = wowReact;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PostItemOnlyTextLayoutKtBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PostItemOnlyTextLayoutKtBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.post_item_only_text_layout_kt, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PostItemOnlyTextLayoutKtBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.angry_react;
      ImageView angryReact = ViewBindings.findChildViewById(rootView, id);
      if (angryReact == null) {
        break missingId;
      }

      id = R.id.background_layout_post_item_kt;
      LinearLayout backgroundLayoutPostItemKt = ViewBindings.findChildViewById(rootView, id);
      if (backgroundLayoutPostItemKt == null) {
        break missingId;
      }

      id = R.id.btn_more_on_feed_color_text;
      ImageButton btnMoreOnFeedColorText = ViewBindings.findChildViewById(rootView, id);
      if (btnMoreOnFeedColorText == null) {
        break missingId;
      }

      id = R.id.comment_icon;
      ImageView commentIcon = ViewBindings.findChildViewById(rootView, id);
      if (commentIcon == null) {
        break missingId;
      }

      id = R.id.feed_card_view_layout;
      LinearLayout feedCardViewLayout = ViewBindings.findChildViewById(rootView, id);
      if (feedCardViewLayout == null) {
        break missingId;
      }

      id = R.id.haha_react;
      ImageView hahaReact = ViewBindings.findChildViewById(rootView, id);
      if (hahaReact == null) {
        break missingId;
      }

      id = R.id.heart_big_view_without_image;
      ImageView heartBigViewWithoutImage = ViewBindings.findChildViewById(rootView, id);
      if (heartBigViewWithoutImage == null) {
        break missingId;
      }

      id = R.id.imgView_line;
      ImageView imgViewLine = ViewBindings.findChildViewById(rootView, id);
      if (imgViewLine == null) {
        break missingId;
      }

      id = R.id.iv_author_without_image_kt;
      CircleImageView ivAuthorWithoutImageKt = ViewBindings.findChildViewById(rootView, id);
      if (ivAuthorWithoutImageKt == null) {
        break missingId;
      }

      id = R.id.layout_btn_comment_without_image;
      LinearLayout layoutBtnCommentWithoutImage = ViewBindings.findChildViewById(rootView, id);
      if (layoutBtnCommentWithoutImage == null) {
        break missingId;
      }

      id = R.id.like_btn_without_image_border;
      ImageView likeBtnWithoutImageBorder = ViewBindings.findChildViewById(rootView, id);
      if (likeBtnWithoutImageBorder == null) {
        break missingId;
      }

      id = R.id.like_react;
      ImageView likeReact = ViewBindings.findChildViewById(rootView, id);
      if (likeReact == null) {
        break missingId;
      }

      id = R.id.love_react;
      ImageView loveReact = ViewBindings.findChildViewById(rootView, id);
      if (loveReact == null) {
        break missingId;
      }

      id = R.id.raw_text_layout;
      LinearLayout rawTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (rawTextLayout == null) {
        break missingId;
      }

      id = R.id.reaction_layout;
      ConstraintLayout reactionLayout = ViewBindings.findChildViewById(rootView, id);
      if (reactionLayout == null) {
        break missingId;
      }

      id = R.id.sad_react;
      ImageView sadReact = ViewBindings.findChildViewById(rootView, id);
      if (sadReact == null) {
        break missingId;
      }

      id = R.id.share_btn_text;
      TextView shareBtnText = ViewBindings.findChildViewById(rootView, id);
      if (shareBtnText == null) {
        break missingId;
      }

      id = R.id.share_button_post_item_kt;
      ImageView shareButtonPostItemKt = ViewBindings.findChildViewById(rootView, id);
      if (shareButtonPostItemKt == null) {
        break missingId;
      }

      id = R.id.share_layout_without_image;
      LinearLayout shareLayoutWithoutImage = ViewBindings.findChildViewById(rootView, id);
      if (shareLayoutWithoutImage == null) {
        break missingId;
      }

      id = R.id.tv_author_name_without_image_kt;
      TextView tvAuthorNameWithoutImageKt = ViewBindings.findChildViewById(rootView, id);
      if (tvAuthorNameWithoutImageKt == null) {
        break missingId;
      }

      id = R.id.tv_comment_count_without_image_item_kt;
      TextView tvCommentCountWithoutImageItemKt = ViewBindings.findChildViewById(rootView, id);
      if (tvCommentCountWithoutImageItemKt == null) {
        break missingId;
      }

      id = R.id.tv_date_without_image_item_kt;
      TextView tvDateWithoutImageItemKt = ViewBindings.findChildViewById(rootView, id);
      if (tvDateWithoutImageItemKt == null) {
        break missingId;
      }

      id = R.id.tv_like_count_without_image_item_kt;
      TextView tvLikeCountWithoutImageItemKt = ViewBindings.findChildViewById(rootView, id);
      if (tvLikeCountWithoutImageItemKt == null) {
        break missingId;
      }

      id = R.id.tv_raw_text;
      TextView tvRawText = ViewBindings.findChildViewById(rootView, id);
      if (tvRawText == null) {
        break missingId;
      }

      id = R.id.tv_small_text_kt;
      TextView tvSmallTextKt = ViewBindings.findChildViewById(rootView, id);
      if (tvSmallTextKt == null) {
        break missingId;
      }

      id = R.id.wow_react;
      ImageView wowReact = ViewBindings.findChildViewById(rootView, id);
      if (wowReact == null) {
        break missingId;
      }

      return new PostItemOnlyTextLayoutKtBinding((LinearLayout) rootView, angryReact,
          backgroundLayoutPostItemKt, btnMoreOnFeedColorText, commentIcon, feedCardViewLayout,
          hahaReact, heartBigViewWithoutImage, imgViewLine, ivAuthorWithoutImageKt,
          layoutBtnCommentWithoutImage, likeBtnWithoutImageBorder, likeReact, loveReact,
          rawTextLayout, reactionLayout, sadReact, shareBtnText, shareButtonPostItemKt,
          shareLayoutWithoutImage, tvAuthorNameWithoutImageKt, tvCommentCountWithoutImageItemKt,
          tvDateWithoutImageItemKt, tvLikeCountWithoutImageItemKt, tvRawText, tvSmallTextKt,
          wowReact);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
