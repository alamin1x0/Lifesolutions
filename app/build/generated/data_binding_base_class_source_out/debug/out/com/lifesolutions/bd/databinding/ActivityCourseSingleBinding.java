// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCourseSingleBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final MaterialButton btnEnrollCourse;

  @NonNull
  public final MaterialButton btnViewEnrolled;

  @NonNull
  public final TextView courseDesc;

  @NonNull
  public final TextView coursePrice;

  @NonNull
  public final RecyclerView rvCourseVideo;

  @NonNull
  public final Toolbar toolbarCourseSingle;

  @NonNull
  public final TextView tvPlaylistSize;

  @NonNull
  public final TextView tvTitle;

  private ActivityCourseSingleBinding(@NonNull NestedScrollView rootView,
      @NonNull MaterialButton btnEnrollCourse, @NonNull MaterialButton btnViewEnrolled,
      @NonNull TextView courseDesc, @NonNull TextView coursePrice,
      @NonNull RecyclerView rvCourseVideo, @NonNull Toolbar toolbarCourseSingle,
      @NonNull TextView tvPlaylistSize, @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnEnrollCourse = btnEnrollCourse;
    this.btnViewEnrolled = btnViewEnrolled;
    this.courseDesc = courseDesc;
    this.coursePrice = coursePrice;
    this.rvCourseVideo = rvCourseVideo;
    this.toolbarCourseSingle = toolbarCourseSingle;
    this.tvPlaylistSize = tvPlaylistSize;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCourseSingleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCourseSingleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_course_single, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCourseSingleBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_enroll_course;
      MaterialButton btnEnrollCourse = ViewBindings.findChildViewById(rootView, id);
      if (btnEnrollCourse == null) {
        break missingId;
      }

      id = R.id.btn_view_enrolled;
      MaterialButton btnViewEnrolled = ViewBindings.findChildViewById(rootView, id);
      if (btnViewEnrolled == null) {
        break missingId;
      }

      id = R.id.course_desc;
      TextView courseDesc = ViewBindings.findChildViewById(rootView, id);
      if (courseDesc == null) {
        break missingId;
      }

      id = R.id.course_price;
      TextView coursePrice = ViewBindings.findChildViewById(rootView, id);
      if (coursePrice == null) {
        break missingId;
      }

      id = R.id.rv_course_video;
      RecyclerView rvCourseVideo = ViewBindings.findChildViewById(rootView, id);
      if (rvCourseVideo == null) {
        break missingId;
      }

      id = R.id.toolbar_course_single;
      Toolbar toolbarCourseSingle = ViewBindings.findChildViewById(rootView, id);
      if (toolbarCourseSingle == null) {
        break missingId;
      }

      id = R.id.tv_playlist_size;
      TextView tvPlaylistSize = ViewBindings.findChildViewById(rootView, id);
      if (tvPlaylistSize == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new ActivityCourseSingleBinding((NestedScrollView) rootView, btnEnrollCourse,
          btnViewEnrolled, courseDesc, coursePrice, rvCourseVideo, toolbarCourseSingle,
          tvPlaylistSize, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
