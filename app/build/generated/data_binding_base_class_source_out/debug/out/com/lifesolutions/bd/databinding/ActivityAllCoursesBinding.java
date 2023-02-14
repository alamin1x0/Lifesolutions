// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAllCoursesBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RecyclerView rvAllCoursesList;

  @NonNull
  public final Toolbar toolbarAllCoursesList;

  private ActivityAllCoursesBinding(@NonNull LinearLayout rootView,
      @NonNull RecyclerView rvAllCoursesList, @NonNull Toolbar toolbarAllCoursesList) {
    this.rootView = rootView;
    this.rvAllCoursesList = rvAllCoursesList;
    this.toolbarAllCoursesList = toolbarAllCoursesList;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAllCoursesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAllCoursesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_all_courses, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAllCoursesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rv_all_courses_list;
      RecyclerView rvAllCoursesList = ViewBindings.findChildViewById(rootView, id);
      if (rvAllCoursesList == null) {
        break missingId;
      }

      id = R.id.toolbar_all_courses_list;
      Toolbar toolbarAllCoursesList = ViewBindings.findChildViewById(rootView, id);
      if (toolbarAllCoursesList == null) {
        break missingId;
      }

      return new ActivityAllCoursesBinding((LinearLayout) rootView, rvAllCoursesList,
          toolbarAllCoursesList);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
