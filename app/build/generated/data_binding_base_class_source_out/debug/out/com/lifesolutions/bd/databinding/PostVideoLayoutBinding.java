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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PostVideoLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageButton btnMoreOnFeedVideo;

  @NonNull
  public final ImageView commentIcon2;

  @NonNull
  public final LinearLayout feedCardViewLayout;

  @NonNull
  public final ImageView imgViewLine2;

  @NonNull
  public final CircleImageView ivAuthorWithVideo;

  @NonNull
  public final JCVideoPlayerStandard ivPostImagePostVideo;

  @NonNull
  public final LinearLayout layoutBtnCommentWithVideo;

  @NonNull
  public final ImageView likeBtnVideoView;

  @NonNull
  public final TextView shareBtnText2;

  @NonNull
  public final ImageView shareButtonPostItemKt2;

  @NonNull
  public final LinearLayout shareLayoutWithVideo;

  @NonNull
  public final TextView tvAuthorNameWithVideo;

  @NonNull
  public final TextView tvCommentCountPostVideoCount;

  @NonNull
  public final TextView tvDatePostVideoView;

  @NonNull
  public final TextView tvLikeCountVideoImage;

  @NonNull
  public final TextView tvPostTitlePostVideo;

  private PostVideoLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull ImageButton btnMoreOnFeedVideo, @NonNull ImageView commentIcon2,
      @NonNull LinearLayout feedCardViewLayout, @NonNull ImageView imgViewLine2,
      @NonNull CircleImageView ivAuthorWithVideo,
      @NonNull JCVideoPlayerStandard ivPostImagePostVideo,
      @NonNull LinearLayout layoutBtnCommentWithVideo, @NonNull ImageView likeBtnVideoView,
      @NonNull TextView shareBtnText2, @NonNull ImageView shareButtonPostItemKt2,
      @NonNull LinearLayout shareLayoutWithVideo, @NonNull TextView tvAuthorNameWithVideo,
      @NonNull TextView tvCommentCountPostVideoCount, @NonNull TextView tvDatePostVideoView,
      @NonNull TextView tvLikeCountVideoImage, @NonNull TextView tvPostTitlePostVideo) {
    this.rootView = rootView;
    this.btnMoreOnFeedVideo = btnMoreOnFeedVideo;
    this.commentIcon2 = commentIcon2;
    this.feedCardViewLayout = feedCardViewLayout;
    this.imgViewLine2 = imgViewLine2;
    this.ivAuthorWithVideo = ivAuthorWithVideo;
    this.ivPostImagePostVideo = ivPostImagePostVideo;
    this.layoutBtnCommentWithVideo = layoutBtnCommentWithVideo;
    this.likeBtnVideoView = likeBtnVideoView;
    this.shareBtnText2 = shareBtnText2;
    this.shareButtonPostItemKt2 = shareButtonPostItemKt2;
    this.shareLayoutWithVideo = shareLayoutWithVideo;
    this.tvAuthorNameWithVideo = tvAuthorNameWithVideo;
    this.tvCommentCountPostVideoCount = tvCommentCountPostVideoCount;
    this.tvDatePostVideoView = tvDatePostVideoView;
    this.tvLikeCountVideoImage = tvLikeCountVideoImage;
    this.tvPostTitlePostVideo = tvPostTitlePostVideo;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PostVideoLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PostVideoLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.post_video_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PostVideoLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_more_on_feed_video;
      ImageButton btnMoreOnFeedVideo = ViewBindings.findChildViewById(rootView, id);
      if (btnMoreOnFeedVideo == null) {
        break missingId;
      }

      id = R.id.comment_icon2;
      ImageView commentIcon2 = ViewBindings.findChildViewById(rootView, id);
      if (commentIcon2 == null) {
        break missingId;
      }

      id = R.id.feed_card_view_layout;
      LinearLayout feedCardViewLayout = ViewBindings.findChildViewById(rootView, id);
      if (feedCardViewLayout == null) {
        break missingId;
      }

      id = R.id.imgView_line2;
      ImageView imgViewLine2 = ViewBindings.findChildViewById(rootView, id);
      if (imgViewLine2 == null) {
        break missingId;
      }

      id = R.id.iv_author_with_video;
      CircleImageView ivAuthorWithVideo = ViewBindings.findChildViewById(rootView, id);
      if (ivAuthorWithVideo == null) {
        break missingId;
      }

      id = R.id.iv_post_image_post_video;
      JCVideoPlayerStandard ivPostImagePostVideo = ViewBindings.findChildViewById(rootView, id);
      if (ivPostImagePostVideo == null) {
        break missingId;
      }

      id = R.id.layout_btn_comment_with_video;
      LinearLayout layoutBtnCommentWithVideo = ViewBindings.findChildViewById(rootView, id);
      if (layoutBtnCommentWithVideo == null) {
        break missingId;
      }

      id = R.id.like_btn_video_view;
      ImageView likeBtnVideoView = ViewBindings.findChildViewById(rootView, id);
      if (likeBtnVideoView == null) {
        break missingId;
      }

      id = R.id.share_btn_text2;
      TextView shareBtnText2 = ViewBindings.findChildViewById(rootView, id);
      if (shareBtnText2 == null) {
        break missingId;
      }

      id = R.id.share_button_post_item_kt2;
      ImageView shareButtonPostItemKt2 = ViewBindings.findChildViewById(rootView, id);
      if (shareButtonPostItemKt2 == null) {
        break missingId;
      }

      id = R.id.share_layout_with_video;
      LinearLayout shareLayoutWithVideo = ViewBindings.findChildViewById(rootView, id);
      if (shareLayoutWithVideo == null) {
        break missingId;
      }

      id = R.id.tv_author_name_with_video;
      TextView tvAuthorNameWithVideo = ViewBindings.findChildViewById(rootView, id);
      if (tvAuthorNameWithVideo == null) {
        break missingId;
      }

      id = R.id.tv_comment_count_post_video_count;
      TextView tvCommentCountPostVideoCount = ViewBindings.findChildViewById(rootView, id);
      if (tvCommentCountPostVideoCount == null) {
        break missingId;
      }

      id = R.id.tv_date_post_video_view;
      TextView tvDatePostVideoView = ViewBindings.findChildViewById(rootView, id);
      if (tvDatePostVideoView == null) {
        break missingId;
      }

      id = R.id.tv_like_count_video_image;
      TextView tvLikeCountVideoImage = ViewBindings.findChildViewById(rootView, id);
      if (tvLikeCountVideoImage == null) {
        break missingId;
      }

      id = R.id.tv_post_title_post_video;
      TextView tvPostTitlePostVideo = ViewBindings.findChildViewById(rootView, id);
      if (tvPostTitlePostVideo == null) {
        break missingId;
      }

      return new PostVideoLayoutBinding((LinearLayout) rootView, btnMoreOnFeedVideo, commentIcon2,
          feedCardViewLayout, imgViewLine2, ivAuthorWithVideo, ivPostImagePostVideo,
          layoutBtnCommentWithVideo, likeBtnVideoView, shareBtnText2, shareButtonPostItemKt2,
          shareLayoutWithVideo, tvAuthorNameWithVideo, tvCommentCountPostVideoCount,
          tvDatePostVideoView, tvLikeCountVideoImage, tvPostTitlePostVideo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}