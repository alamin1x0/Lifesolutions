// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ReactionLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView angryReact;

  @NonNull
  public final ImageView hahaReact;

  @NonNull
  public final ImageView likeReact;

  @NonNull
  public final ImageView loveReact;

  @NonNull
  public final ConstraintLayout reactionLayout;

  @NonNull
  public final ImageView sadReact;

  @NonNull
  public final ImageView wowReact;

  private ReactionLayoutBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView angryReact,
      @NonNull ImageView hahaReact, @NonNull ImageView likeReact, @NonNull ImageView loveReact,
      @NonNull ConstraintLayout reactionLayout, @NonNull ImageView sadReact,
      @NonNull ImageView wowReact) {
    this.rootView = rootView;
    this.angryReact = angryReact;
    this.hahaReact = hahaReact;
    this.likeReact = likeReact;
    this.loveReact = loveReact;
    this.reactionLayout = reactionLayout;
    this.sadReact = sadReact;
    this.wowReact = wowReact;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ReactionLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ReactionLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.reaction_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ReactionLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.angry_react;
      ImageView angryReact = ViewBindings.findChildViewById(rootView, id);
      if (angryReact == null) {
        break missingId;
      }

      id = R.id.haha_react;
      ImageView hahaReact = ViewBindings.findChildViewById(rootView, id);
      if (hahaReact == null) {
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

      id = R.id.wow_react;
      ImageView wowReact = ViewBindings.findChildViewById(rootView, id);
      if (wowReact == null) {
        break missingId;
      }

      return new ReactionLayoutBinding((ConstraintLayout) rootView, angryReact, hahaReact,
          likeReact, loveReact, reactionLayout, sadReact, wowReact);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
