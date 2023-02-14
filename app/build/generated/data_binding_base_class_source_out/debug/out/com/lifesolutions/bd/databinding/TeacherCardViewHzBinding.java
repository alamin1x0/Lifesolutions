// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TeacherCardViewHzBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final View ivOnlineBadge;

  @NonNull
  public final CircleImageView ivProfileImg;

  @NonNull
  public final TextView teacherName;

  @NonNull
  public final RatingBar teacherRating;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView17;

  @NonNull
  public final LinearLayout topRightMiniute;

  @NonNull
  public final TextView tvTalkTime;

  private TeacherCardViewHzBinding(@NonNull CardView rootView,
      @NonNull ConstraintLayout constraintLayout, @NonNull View ivOnlineBadge,
      @NonNull CircleImageView ivProfileImg, @NonNull TextView teacherName,
      @NonNull RatingBar teacherRating, @NonNull TextView textView16, @NonNull TextView textView17,
      @NonNull LinearLayout topRightMiniute, @NonNull TextView tvTalkTime) {
    this.rootView = rootView;
    this.constraintLayout = constraintLayout;
    this.ivOnlineBadge = ivOnlineBadge;
    this.ivProfileImg = ivProfileImg;
    this.teacherName = teacherName;
    this.teacherRating = teacherRating;
    this.textView16 = textView16;
    this.textView17 = textView17;
    this.topRightMiniute = topRightMiniute;
    this.tvTalkTime = tvTalkTime;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static TeacherCardViewHzBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TeacherCardViewHzBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.teacher_card_view_hz, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TeacherCardViewHzBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.iv_online_badge;
      View ivOnlineBadge = ViewBindings.findChildViewById(rootView, id);
      if (ivOnlineBadge == null) {
        break missingId;
      }

      id = R.id.iv_profile_img;
      CircleImageView ivProfileImg = ViewBindings.findChildViewById(rootView, id);
      if (ivProfileImg == null) {
        break missingId;
      }

      id = R.id.teacher_name;
      TextView teacherName = ViewBindings.findChildViewById(rootView, id);
      if (teacherName == null) {
        break missingId;
      }

      id = R.id.teacher_rating;
      RatingBar teacherRating = ViewBindings.findChildViewById(rootView, id);
      if (teacherRating == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      id = R.id.textView17;
      TextView textView17 = ViewBindings.findChildViewById(rootView, id);
      if (textView17 == null) {
        break missingId;
      }

      id = R.id.top_right_miniute;
      LinearLayout topRightMiniute = ViewBindings.findChildViewById(rootView, id);
      if (topRightMiniute == null) {
        break missingId;
      }

      id = R.id.tv_talkTime;
      TextView tvTalkTime = ViewBindings.findChildViewById(rootView, id);
      if (tvTalkTime == null) {
        break missingId;
      }

      return new TeacherCardViewHzBinding((CardView) rootView, constraintLayout, ivOnlineBadge,
          ivProfileImg, teacherName, teacherRating, textView16, textView17, topRightMiniute,
          tvTalkTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
