// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public final class ActivityIncomingVideoCallScreenBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView addressIncomingVideoCall;

  @NonNull
  public final ImageButton callAcceptIncomingVideoCall;

  @NonNull
  public final ImageButton callRejectIncomingVideoCall;

  @NonNull
  public final ImageView fixedBgColor;

  @NonNull
  public final TextView headingIncomingVideoCall;

  @NonNull
  public final TextView nameIncomingVideoCall;

  @NonNull
  public final CircleImageView profileImageIncomingVideoCall;

  @NonNull
  public final ImageView videoCallBgProfile;

  private ActivityIncomingVideoCallScreenBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView addressIncomingVideoCall, @NonNull ImageButton callAcceptIncomingVideoCall,
      @NonNull ImageButton callRejectIncomingVideoCall, @NonNull ImageView fixedBgColor,
      @NonNull TextView headingIncomingVideoCall, @NonNull TextView nameIncomingVideoCall,
      @NonNull CircleImageView profileImageIncomingVideoCall,
      @NonNull ImageView videoCallBgProfile) {
    this.rootView = rootView;
    this.addressIncomingVideoCall = addressIncomingVideoCall;
    this.callAcceptIncomingVideoCall = callAcceptIncomingVideoCall;
    this.callRejectIncomingVideoCall = callRejectIncomingVideoCall;
    this.fixedBgColor = fixedBgColor;
    this.headingIncomingVideoCall = headingIncomingVideoCall;
    this.nameIncomingVideoCall = nameIncomingVideoCall;
    this.profileImageIncomingVideoCall = profileImageIncomingVideoCall;
    this.videoCallBgProfile = videoCallBgProfile;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityIncomingVideoCallScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityIncomingVideoCallScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_incoming_video_call_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityIncomingVideoCallScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address_incoming_video_call;
      TextView addressIncomingVideoCall = ViewBindings.findChildViewById(rootView, id);
      if (addressIncomingVideoCall == null) {
        break missingId;
      }

      id = R.id.call_accept_incoming_video_call;
      ImageButton callAcceptIncomingVideoCall = ViewBindings.findChildViewById(rootView, id);
      if (callAcceptIncomingVideoCall == null) {
        break missingId;
      }

      id = R.id.call_reject_incoming_video_call;
      ImageButton callRejectIncomingVideoCall = ViewBindings.findChildViewById(rootView, id);
      if (callRejectIncomingVideoCall == null) {
        break missingId;
      }

      id = R.id.fixed_bg_color;
      ImageView fixedBgColor = ViewBindings.findChildViewById(rootView, id);
      if (fixedBgColor == null) {
        break missingId;
      }

      id = R.id.heading_incoming_video_call;
      TextView headingIncomingVideoCall = ViewBindings.findChildViewById(rootView, id);
      if (headingIncomingVideoCall == null) {
        break missingId;
      }

      id = R.id.name_incoming_video_call;
      TextView nameIncomingVideoCall = ViewBindings.findChildViewById(rootView, id);
      if (nameIncomingVideoCall == null) {
        break missingId;
      }

      id = R.id.profile_image_incoming_video_call;
      CircleImageView profileImageIncomingVideoCall = ViewBindings.findChildViewById(rootView, id);
      if (profileImageIncomingVideoCall == null) {
        break missingId;
      }

      id = R.id.video_call_bg_profile;
      ImageView videoCallBgProfile = ViewBindings.findChildViewById(rootView, id);
      if (videoCallBgProfile == null) {
        break missingId;
      }

      return new ActivityIncomingVideoCallScreenBinding((RelativeLayout) rootView,
          addressIncomingVideoCall, callAcceptIncomingVideoCall, callRejectIncomingVideoCall,
          fixedBgColor, headingIncomingVideoCall, nameIncomingVideoCall,
          profileImageIncomingVideoCall, videoCallBgProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
