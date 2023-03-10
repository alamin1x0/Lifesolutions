// Generated by view binder compiler. Do not edit!
package com.lifesolutions.bd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import com.hbb20.CountryCodePicker;
import com.lifesolutions.bd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPasswordRecoveryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button confirmButtonPasswordRecovery;

  @NonNull
  public final EditText confirmPasswordPasswordRecovery;

  @NonNull
  public final Button continueButtonOtpVerificationPasswordRecovery;

  @NonNull
  public final Button continueButtonPasswordRecovery;

  @NonNull
  public final ImageView iconRegistrationPasswordRecovery;

  @NonNull
  public final CardView otpVerificationLayoutPasswordRecovery;

  @NonNull
  public final CardView passwordLayoutPasswordRecovery;

  @NonNull
  public final EditText passwordPasswordRecovery;

  @NonNull
  public final CardView phoneInputLayoutRegistrationPage;

  @NonNull
  public final EditText phoneNumberPasswordRecovery;

  @NonNull
  public final PinView pinViewOtpVerificationPasswordRecovery;

  @NonNull
  public final TextView resendOtpVerificationPasswordRecovery;

  @NonNull
  public final CountryCodePicker spinnerPasswordRecovery;

  @NonNull
  public final TextView timerOtpVerificationPasswordRecovery;

  private ActivityPasswordRecoveryBinding(@NonNull RelativeLayout rootView,
      @NonNull Button confirmButtonPasswordRecovery,
      @NonNull EditText confirmPasswordPasswordRecovery,
      @NonNull Button continueButtonOtpVerificationPasswordRecovery,
      @NonNull Button continueButtonPasswordRecovery,
      @NonNull ImageView iconRegistrationPasswordRecovery,
      @NonNull CardView otpVerificationLayoutPasswordRecovery,
      @NonNull CardView passwordLayoutPasswordRecovery, @NonNull EditText passwordPasswordRecovery,
      @NonNull CardView phoneInputLayoutRegistrationPage,
      @NonNull EditText phoneNumberPasswordRecovery,
      @NonNull PinView pinViewOtpVerificationPasswordRecovery,
      @NonNull TextView resendOtpVerificationPasswordRecovery,
      @NonNull CountryCodePicker spinnerPasswordRecovery,
      @NonNull TextView timerOtpVerificationPasswordRecovery) {
    this.rootView = rootView;
    this.confirmButtonPasswordRecovery = confirmButtonPasswordRecovery;
    this.confirmPasswordPasswordRecovery = confirmPasswordPasswordRecovery;
    this.continueButtonOtpVerificationPasswordRecovery = continueButtonOtpVerificationPasswordRecovery;
    this.continueButtonPasswordRecovery = continueButtonPasswordRecovery;
    this.iconRegistrationPasswordRecovery = iconRegistrationPasswordRecovery;
    this.otpVerificationLayoutPasswordRecovery = otpVerificationLayoutPasswordRecovery;
    this.passwordLayoutPasswordRecovery = passwordLayoutPasswordRecovery;
    this.passwordPasswordRecovery = passwordPasswordRecovery;
    this.phoneInputLayoutRegistrationPage = phoneInputLayoutRegistrationPage;
    this.phoneNumberPasswordRecovery = phoneNumberPasswordRecovery;
    this.pinViewOtpVerificationPasswordRecovery = pinViewOtpVerificationPasswordRecovery;
    this.resendOtpVerificationPasswordRecovery = resendOtpVerificationPasswordRecovery;
    this.spinnerPasswordRecovery = spinnerPasswordRecovery;
    this.timerOtpVerificationPasswordRecovery = timerOtpVerificationPasswordRecovery;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPasswordRecoveryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPasswordRecoveryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_password_recovery, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPasswordRecoveryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.confirm_button_password_recovery;
      Button confirmButtonPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (confirmButtonPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.confirm_password__password_recovery;
      EditText confirmPasswordPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (confirmPasswordPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.continue_button_otp_verification_password_recovery;
      Button continueButtonOtpVerificationPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (continueButtonOtpVerificationPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.continue_button_password_recovery;
      Button continueButtonPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (continueButtonPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.icon_registration_password_recovery;
      ImageView iconRegistrationPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (iconRegistrationPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.otp_verification_layout_password_recovery;
      CardView otpVerificationLayoutPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (otpVerificationLayoutPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.password_layout_password_recovery;
      CardView passwordLayoutPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (passwordLayoutPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.password_password_recovery;
      EditText passwordPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (passwordPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.phone_input_layout_registration_page;
      CardView phoneInputLayoutRegistrationPage = ViewBindings.findChildViewById(rootView, id);
      if (phoneInputLayoutRegistrationPage == null) {
        break missingId;
      }

      id = R.id.phone_number_password_recovery;
      EditText phoneNumberPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (phoneNumberPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.pinView_otp_verification_password_recovery;
      PinView pinViewOtpVerificationPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (pinViewOtpVerificationPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.resend_otp_verification_password_recovery;
      TextView resendOtpVerificationPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (resendOtpVerificationPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.spinner_password_recovery;
      CountryCodePicker spinnerPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (spinnerPasswordRecovery == null) {
        break missingId;
      }

      id = R.id.timer_otp_verification_password_recovery;
      TextView timerOtpVerificationPasswordRecovery = ViewBindings.findChildViewById(rootView, id);
      if (timerOtpVerificationPasswordRecovery == null) {
        break missingId;
      }

      return new ActivityPasswordRecoveryBinding((RelativeLayout) rootView,
          confirmButtonPasswordRecovery, confirmPasswordPasswordRecovery,
          continueButtonOtpVerificationPasswordRecovery, continueButtonPasswordRecovery,
          iconRegistrationPasswordRecovery, otpVerificationLayoutPasswordRecovery,
          passwordLayoutPasswordRecovery, passwordPasswordRecovery,
          phoneInputLayoutRegistrationPage, phoneNumberPasswordRecovery,
          pinViewOtpVerificationPasswordRecovery, resendOtpVerificationPasswordRecovery,
          spinnerPasswordRecovery, timerOtpVerificationPasswordRecovery);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
