// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import com.google.android.material.button.MaterialButton;
import com.hbb20.CountryCodePicker;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginKtSmallBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnCheckOtp;

  @NonNull
  public final MaterialButton btnPhoneLogin;

  @NonNull
  public final CountryCodePicker countryCodePicker;

  @NonNull
  public final EditText etPhoneNumber;

  @NonNull
  public final ImageView imageViewLogin;

  @NonNull
  public final ConstraintLayout loginLayout;

  @NonNull
  public final LinearLayout phoneNumberInputArea;

  @NonNull
  public final PinView pinViewOtpInput;

  @NonNull
  public final RelativeLayout relativeLayout;

  @NonNull
  public final TextView resendOtpVerification;

  @NonNull
  public final LinearLayout termsTextLayout;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView timerOtpVerification;

  @NonNull
  public final LinearLayout viewAreaAfterOtp;

  @NonNull
  public final LinearLayout viewAreaBeforeOtp;

  private ActivityLoginKtSmallBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnCheckOtp, @NonNull MaterialButton btnPhoneLogin,
      @NonNull CountryCodePicker countryCodePicker, @NonNull EditText etPhoneNumber,
      @NonNull ImageView imageViewLogin, @NonNull ConstraintLayout loginLayout,
      @NonNull LinearLayout phoneNumberInputArea, @NonNull PinView pinViewOtpInput,
      @NonNull RelativeLayout relativeLayout, @NonNull TextView resendOtpVerification,
      @NonNull LinearLayout termsTextLayout, @NonNull TextView textView4,
      @NonNull TextView textView5, @NonNull TextView textView6,
      @NonNull TextView timerOtpVerification, @NonNull LinearLayout viewAreaAfterOtp,
      @NonNull LinearLayout viewAreaBeforeOtp) {
    this.rootView = rootView;
    this.btnCheckOtp = btnCheckOtp;
    this.btnPhoneLogin = btnPhoneLogin;
    this.countryCodePicker = countryCodePicker;
    this.etPhoneNumber = etPhoneNumber;
    this.imageViewLogin = imageViewLogin;
    this.loginLayout = loginLayout;
    this.phoneNumberInputArea = phoneNumberInputArea;
    this.pinViewOtpInput = pinViewOtpInput;
    this.relativeLayout = relativeLayout;
    this.resendOtpVerification = resendOtpVerification;
    this.termsTextLayout = termsTextLayout;
    this.textView4 = textView4;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.timerOtpVerification = timerOtpVerification;
    this.viewAreaAfterOtp = viewAreaAfterOtp;
    this.viewAreaBeforeOtp = viewAreaBeforeOtp;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginKtSmallBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginKtSmallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login_kt_small, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginKtSmallBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_check_otp;
      MaterialButton btnCheckOtp = ViewBindings.findChildViewById(rootView, id);
      if (btnCheckOtp == null) {
        break missingId;
      }

      id = R.id.btn_phone_login;
      MaterialButton btnPhoneLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnPhoneLogin == null) {
        break missingId;
      }

      id = R.id.country_code_picker;
      CountryCodePicker countryCodePicker = ViewBindings.findChildViewById(rootView, id);
      if (countryCodePicker == null) {
        break missingId;
      }

      id = R.id.et_phone_number;
      EditText etPhoneNumber = ViewBindings.findChildViewById(rootView, id);
      if (etPhoneNumber == null) {
        break missingId;
      }

      id = R.id.image_view_login;
      ImageView imageViewLogin = ViewBindings.findChildViewById(rootView, id);
      if (imageViewLogin == null) {
        break missingId;
      }

      ConstraintLayout loginLayout = (ConstraintLayout) rootView;

      id = R.id.phone_number_input_area;
      LinearLayout phoneNumberInputArea = ViewBindings.findChildViewById(rootView, id);
      if (phoneNumberInputArea == null) {
        break missingId;
      }

      id = R.id.pinView_otp_input;
      PinView pinViewOtpInput = ViewBindings.findChildViewById(rootView, id);
      if (pinViewOtpInput == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      RelativeLayout relativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.resend_otp_verification;
      TextView resendOtpVerification = ViewBindings.findChildViewById(rootView, id);
      if (resendOtpVerification == null) {
        break missingId;
      }

      id = R.id.terms_text_layout;
      LinearLayout termsTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (termsTextLayout == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.timer_otp_verification;
      TextView timerOtpVerification = ViewBindings.findChildViewById(rootView, id);
      if (timerOtpVerification == null) {
        break missingId;
      }

      id = R.id.view_area_after_otp;
      LinearLayout viewAreaAfterOtp = ViewBindings.findChildViewById(rootView, id);
      if (viewAreaAfterOtp == null) {
        break missingId;
      }

      id = R.id.view_area_before_otp;
      LinearLayout viewAreaBeforeOtp = ViewBindings.findChildViewById(rootView, id);
      if (viewAreaBeforeOtp == null) {
        break missingId;
      }

      return new ActivityLoginKtSmallBinding((ConstraintLayout) rootView, btnCheckOtp,
          btnPhoneLogin, countryCodePicker, etPhoneNumber, imageViewLogin, loginLayout,
          phoneNumberInputArea, pinViewOtpInput, relativeLayout, resendOtpVerification,
          termsTextLayout, textView4, textView5, textView6, timerOtpVerification, viewAreaAfterOtp,
          viewAreaBeforeOtp);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
