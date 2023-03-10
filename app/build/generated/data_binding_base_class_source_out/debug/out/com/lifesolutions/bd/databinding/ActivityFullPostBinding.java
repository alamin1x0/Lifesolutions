// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFullPostBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final AppBarLayout appBarLayoutFullPost;

  @NonNull
  public final CircleImageView authorImageFullPost;

  @NonNull
  public final CollapsingToolbarLayout collapsingLayoutFullPost;

  @NonNull
  public final ImageButton commentBtnFullPost;

  @NonNull
  public final EditText commentFullPost;

  @NonNull
  public final LinearLayout commentLayoutFullPost;

  @NonNull
  public final CircleImageView commenterPhotoFullPost;

  @NonNull
  public final TextView dateTimeFullPost;

  @NonNull
  public final TextView descriptionFullPost;

  @NonNull
  public final FloatingActionButton downloadButtonFullPost;

  @NonNull
  public final ImageView mainImageFullPost;

  @NonNull
  public final RelativeLayout messageLayout;

  @NonNull
  public final RecyclerView recyclerviewComment;

  @NonNull
  public final Toolbar toolbarFullPost;

  @NonNull
  public final View view;

  @NonNull
  public final View view2;

  private ActivityFullPostBinding(@NonNull CoordinatorLayout rootView,
      @NonNull AppBarLayout appBarLayoutFullPost, @NonNull CircleImageView authorImageFullPost,
      @NonNull CollapsingToolbarLayout collapsingLayoutFullPost,
      @NonNull ImageButton commentBtnFullPost, @NonNull EditText commentFullPost,
      @NonNull LinearLayout commentLayoutFullPost, @NonNull CircleImageView commenterPhotoFullPost,
      @NonNull TextView dateTimeFullPost, @NonNull TextView descriptionFullPost,
      @NonNull FloatingActionButton downloadButtonFullPost, @NonNull ImageView mainImageFullPost,
      @NonNull RelativeLayout messageLayout, @NonNull RecyclerView recyclerviewComment,
      @NonNull Toolbar toolbarFullPost, @NonNull View view, @NonNull View view2) {
    this.rootView = rootView;
    this.appBarLayoutFullPost = appBarLayoutFullPost;
    this.authorImageFullPost = authorImageFullPost;
    this.collapsingLayoutFullPost = collapsingLayoutFullPost;
    this.commentBtnFullPost = commentBtnFullPost;
    this.commentFullPost = commentFullPost;
    this.commentLayoutFullPost = commentLayoutFullPost;
    this.commenterPhotoFullPost = commenterPhotoFullPost;
    this.dateTimeFullPost = dateTimeFullPost;
    this.descriptionFullPost = descriptionFullPost;
    this.downloadButtonFullPost = downloadButtonFullPost;
    this.mainImageFullPost = mainImageFullPost;
    this.messageLayout = messageLayout;
    this.recyclerviewComment = recyclerviewComment;
    this.toolbarFullPost = toolbarFullPost;
    this.view = view;
    this.view2 = view2;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFullPostBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFullPostBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_full_post, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFullPostBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_bar_layout_full_post;
      AppBarLayout appBarLayoutFullPost = ViewBindings.findChildViewById(rootView, id);
      if (appBarLayoutFullPost == null) {
        break missingId;
      }

      id = R.id.author_image_full_post;
      CircleImageView authorImageFullPost = ViewBindings.findChildViewById(rootView, id);
      if (authorImageFullPost == null) {
        break missingId;
      }

      id = R.id.collapsing_layout_full_post;
      CollapsingToolbarLayout collapsingLayoutFullPost = ViewBindings.findChildViewById(rootView, id);
      if (collapsingLayoutFullPost == null) {
        break missingId;
      }

      id = R.id.comment_btn_full_post;
      ImageButton commentBtnFullPost = ViewBindings.findChildViewById(rootView, id);
      if (commentBtnFullPost == null) {
        break missingId;
      }

      id = R.id.comment_full_post;
      EditText commentFullPost = ViewBindings.findChildViewById(rootView, id);
      if (commentFullPost == null) {
        break missingId;
      }

      id = R.id.comment_layout_full_post;
      LinearLayout commentLayoutFullPost = ViewBindings.findChildViewById(rootView, id);
      if (commentLayoutFullPost == null) {
        break missingId;
      }

      id = R.id.commenter_Photo_full_post;
      CircleImageView commenterPhotoFullPost = ViewBindings.findChildViewById(rootView, id);
      if (commenterPhotoFullPost == null) {
        break missingId;
      }

      id = R.id.date_time_full_post;
      TextView dateTimeFullPost = ViewBindings.findChildViewById(rootView, id);
      if (dateTimeFullPost == null) {
        break missingId;
      }

      id = R.id.description_full_post;
      TextView descriptionFullPost = ViewBindings.findChildViewById(rootView, id);
      if (descriptionFullPost == null) {
        break missingId;
      }

      id = R.id.download_button_full_post;
      FloatingActionButton downloadButtonFullPost = ViewBindings.findChildViewById(rootView, id);
      if (downloadButtonFullPost == null) {
        break missingId;
      }

      id = R.id.main_image_full_post;
      ImageView mainImageFullPost = ViewBindings.findChildViewById(rootView, id);
      if (mainImageFullPost == null) {
        break missingId;
      }

      id = R.id.message_layout;
      RelativeLayout messageLayout = ViewBindings.findChildViewById(rootView, id);
      if (messageLayout == null) {
        break missingId;
      }

      id = R.id.recyclerview_comment;
      RecyclerView recyclerviewComment = ViewBindings.findChildViewById(rootView, id);
      if (recyclerviewComment == null) {
        break missingId;
      }

      id = R.id.toolbar_full_post;
      Toolbar toolbarFullPost = ViewBindings.findChildViewById(rootView, id);
      if (toolbarFullPost == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      id = R.id.view2;
      View view2 = ViewBindings.findChildViewById(rootView, id);
      if (view2 == null) {
        break missingId;
      }

      return new ActivityFullPostBinding((CoordinatorLayout) rootView, appBarLayoutFullPost,
          authorImageFullPost, collapsingLayoutFullPost, commentBtnFullPost, commentFullPost,
          commentLayoutFullPost, commenterPhotoFullPost, dateTimeFullPost, descriptionFullPost,
          downloadButtonFullPost, mainImageFullPost, messageLayout, recyclerviewComment,
          toolbarFullPost, view, view2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
