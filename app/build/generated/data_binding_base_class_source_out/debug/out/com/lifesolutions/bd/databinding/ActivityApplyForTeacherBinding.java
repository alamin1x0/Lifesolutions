// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.lifesolutions.bd.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityApplyForTeacherBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextInputEditText addressApplyForTeacher;

  @NonNull
  public final ImageButton backButtonApplyForTeacher;

  @NonNull
  public final TextInputEditText bioApplyForTeacher;

  @NonNull
  public final TextInputEditText emailApplyForTeacher;

  @NonNull
  public final TextInputEditText nameApplyForTeacher;

  @NonNull
  public final TextInputEditText phoneApplyForTeacher;

  @NonNull
  public final CircleImageView profileImageApplyForTeacher;

  @NonNull
  public final Button saveApplyForTeacher;

  @NonNull
  public final TextInputEditText studyInfoApplyForTeacher;

  private ActivityApplyForTeacherBinding(@NonNull LinearLayout rootView,
      @NonNull TextInputEditText addressApplyForTeacher,
      @NonNull ImageButton backButtonApplyForTeacher, @NonNull TextInputEditText bioApplyForTeacher,
      @NonNull TextInputEditText emailApplyForTeacher,
      @NonNull TextInputEditText nameApplyForTeacher,
      @NonNull TextInputEditText phoneApplyForTeacher,
      @NonNull CircleImageView profileImageApplyForTeacher, @NonNull Button saveApplyForTeacher,
      @NonNull TextInputEditText studyInfoApplyForTeacher) {
    this.rootView = rootView;
    this.addressApplyForTeacher = addressApplyForTeacher;
    this.backButtonApplyForTeacher = backButtonApplyForTeacher;
    this.bioApplyForTeacher = bioApplyForTeacher;
    this.emailApplyForTeacher = emailApplyForTeacher;
    this.nameApplyForTeacher = nameApplyForTeacher;
    this.phoneApplyForTeacher = phoneApplyForTeacher;
    this.profileImageApplyForTeacher = profileImageApplyForTeacher;
    this.saveApplyForTeacher = saveApplyForTeacher;
    this.studyInfoApplyForTeacher = studyInfoApplyForTeacher;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityApplyForTeacherBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityApplyForTeacherBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_apply_for_teacher, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityApplyForTeacherBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address_apply_for_teacher;
      TextInputEditText addressApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (addressApplyForTeacher == null) {
        break missingId;
      }

      id = R.id.back_button_apply_for_teacher;
      ImageButton backButtonApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (backButtonApplyForTeacher == null) {
        break missingId;
      }

      id = R.id.bio_apply_for_teacher;
      TextInputEditText bioApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (bioApplyForTeacher == null) {
        break missingId;
      }

      id = R.id.email_apply_for_teacher;
      TextInputEditText emailApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (emailApplyForTeacher == null) {
        break missingId;
      }

      id = R.id.name_apply_for_teacher;
      TextInputEditText nameApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (nameApplyForTeacher == null) {
        break missingId;
      }

      id = R.id.phone_apply_for_teacher;
      TextInputEditText phoneApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (phoneApplyForTeacher == null) {
        break missingId;
      }

      id = R.id.profile_image_apply_for_teacher;
      CircleImageView profileImageApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (profileImageApplyForTeacher == null) {
        break missingId;
      }

      id = R.id.save_apply_for_teacher;
      Button saveApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (saveApplyForTeacher == null) {
        break missingId;
      }

      id = R.id.study_info_apply_for_teacher;
      TextInputEditText studyInfoApplyForTeacher = ViewBindings.findChildViewById(rootView, id);
      if (studyInfoApplyForTeacher == null) {
        break missingId;
      }

      return new ActivityApplyForTeacherBinding((LinearLayout) rootView, addressApplyForTeacher,
          backButtonApplyForTeacher, bioApplyForTeacher, emailApplyForTeacher, nameApplyForTeacher,
          phoneApplyForTeacher, profileImageApplyForTeacher, saveApplyForTeacher,
          studyInfoApplyForTeacher);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
