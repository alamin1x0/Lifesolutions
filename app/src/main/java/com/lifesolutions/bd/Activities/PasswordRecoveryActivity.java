//package com.stardigiinternational.starnotee.Activities;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.chaos.view.PinView;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.gms.tasks.TaskExecutors;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.hbb20.CountryCodePicker;
//import com.stardigiinternational.starnotee.Helpers.InternetCheck;
//import com.stardigiinternational.starnotee.Interfaces.OnUserActionListener;
//import com.stardigiinternational.starnotee.Models.SpinnerItem;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.kotlinCode.ui.auth.LoginKtActivity;
//
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//
//public class PasswordRecoveryActivity extends AppCompatActivity {
//
//    EditText edNumber,edPassword,edConPassword;
//    CardView numberSection,passwordSection,codeSection;
//    private String mVerificationId;
//    private String countryCode = "+880";
//    CountryCodePicker countryCodePicker;
//    FirebaseAuth mAuth;
//    TextView timer,login,resend;
//    private ProgressDialog progressDialog;
//    DatabaseReference mReference;
//    PinView pinView;
//    Button nextButton,verifyButton,confirmButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_password_recovery);
//
//        edNumber = findViewById(R.id.phone_number_password_recovery);
//        edPassword = findViewById(R.id.password_password_recovery);
//        edConPassword = findViewById(R.id.confirm_password__password_recovery);
//        numberSection = findViewById(R.id.phone_input_layout_registration_page);
//        passwordSection = findViewById(R.id.password_layout_password_recovery);
//        codeSection = findViewById(R.id.otp_verification_layout_password_recovery);
//        timer = findViewById(R.id.timer_otp_verification_password_recovery);
//        pinView = findViewById(R.id.pinView_otp_verification_password_recovery);
//        countryCodePicker = findViewById(R.id.spinner_password_recovery);
//        nextButton = findViewById(R.id.continue_button_password_recovery);
//        verifyButton = findViewById(R.id.continue_button_otp_verification_password_recovery);
//        confirmButton = findViewById(R.id.confirm_button_password_recovery);
//        resend = findViewById(R.id.resend_otp_verification_password_recovery);
//
//        mAuth = FirebaseAuth.getInstance();
//        mReference = FirebaseDatabase.getInstance().getReference().child("Users");
//        progressDialog = new ProgressDialog(this);
//
//        countryCodePicker.setOnCountryChangeListener(() -> countryCode = countryCodePicker.getSelectedCountryCodeWithPlus());
//
//        nextButton.setOnClickListener(view -> {
//            if (edNumber.getText().toString().isEmpty())
//            {
//                edNumber.setError("This field can't be empty");
//                edNumber.requestFocus();
//            }else {
//                if (InternetCheck.checkConnection(PasswordRecoveryActivity.this)){
//
//                    progressDialog.setTitle("Updating your password");
//                    progressDialog.setMessage("Please wait, while we are creating new account for you");
//                    progressDialog.setCanceledOnTouchOutside(false);
//                    progressDialog.show();
//
//                    userNumberExist(countryCode+edNumber.getText().toString(), exists -> {
//                        if (exists){
//                            progressDialog.dismiss();
//                            numberSection.setVisibility(View.GONE);
//                            passwordSection.setVisibility(View.GONE);
//                            codeSection.setVisibility(View.VISIBLE);
//                            timer.setVisibility(View.VISIBLE);
//
//                            new CountDownTimer(60000, 1000) {
//
//                                public void onTick(long millisUntilFinished) {
//                                    timer.setText("Wait "+millisUntilFinished / 1000+" Seconds");
//                                }
//
//                                public void onFinish() {
//                                    timer.setText("Resend");
//                                }
//                            }.start();
//                            sendVerificationCode(edNumber.getText().toString());
//                        } else {
//                            progressDialog.dismiss();
//                            Toast.makeText(PasswordRecoveryActivity.this, "This number not registered", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } else {
//                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        confirmButton.setOnClickListener(view -> {
//            if (edPassword.getText().toString().isEmpty()) {
//                edPassword.setError("This field can't be empty");
//                edPassword.requestFocus();
//            } else if (edPassword.getText().toString().length() < 6) {
//                edPassword.setError("Minimum six characters required");
//                edPassword.requestFocus();
//            } else if (edPassword.getText().toString().length() > 16) {
//                edPassword.setError("Minimum six characters required");
//                edPassword.requestFocus();
//            } else if (edConPassword.getText().toString().isEmpty()) {
//                edConPassword.setError("This field can't be empty");
//                edConPassword.requestFocus();
//            } else if (edConPassword.getText().toString().length() < 6) {
//                edConPassword.setError("Minimum six characters required");
//                edConPassword.requestFocus();
//            } else if (!edPassword.getText().toString().equals(edConPassword.getText().toString())) {
//                edConPassword.setError("Confirm password not matched");
//                edConPassword.requestFocus();
//            } else {
//                progressDialog.setTitle("Updating your password");
//                progressDialog.setMessage("Please wait, while we are updating your password");
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.show();
//
//                if (mAuth.getCurrentUser().getUid().isEmpty()){
//                    progressDialog.dismiss();
//                    Toast.makeText(PasswordRecoveryActivity.this, "Something went wrong, try again later", Toast.LENGTH_SHORT).show();
//                } else {
//                    mReference.child(mAuth.getCurrentUser().getUid()).child("password").setValue(edPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            progressDialog.dismiss();
//                            Toast.makeText(PasswordRecoveryActivity.this, "Password successfully updated", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(PasswordRecoveryActivity.this, LoginKtActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
//                            PasswordRecoveryActivity.this.finish();
//                        }
//                    }).addOnFailureListener(e -> {
//                        progressDialog.dismiss();
//                        Toast.makeText(PasswordRecoveryActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//
//                    });
//                }
//            }
//        });
//
//    }
//
//    private void sendVerificationCode(String mobile) {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                countryCode + mobile,
//                60,
//                TimeUnit.SECONDS,
//                TaskExecutors.MAIN_THREAD,
//                mCallbacks);
//    }
//
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        @Override
//        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//
//            String code = phoneAuthCredential.getSmsCode();
//            if (code != null) {
//                pinView.setText(code);
//                //verifying the code
//                verifyVerificationCode(code);
//                progressDialog.setTitle("Verify your code");
//                progressDialog.setMessage("Please wait, while we are verifying your code");
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.show();
//            }
//        }
//
//        @Override
//        public void onVerificationFailed(FirebaseException e) {
//            Toast.makeText(PasswordRecoveryActivity.this,""+e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//
//            //storing the verification id that is sent to the user
//            mVerificationId = s;
//        }
//    };
//
//    private void verifyVerificationCode(String code) {
//
//        //creating the credential
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
//
//        //signing the user
//        signInWithPhoneAuthCredential(credential);
//
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(PasswordRecoveryActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            progressDialog.dismiss();
//                            numberSection.setVisibility(View.GONE);
//                            codeSection.setVisibility(View.GONE);
//                            passwordSection.setVisibility(View.VISIBLE);
//                        } else {
//                            progressDialog.dismiss();
//                            Toast.makeText(PasswordRecoveryActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//    private void userNumberExist(final String phone, final OnUserActionListener listener) {
//        mReference.orderByChild("phone")
//                .equalTo(phone)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        listener.onExists(dataSnapshot.exists());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(PasswordRecoveryActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    public void openLogin(View view) {
//    }
//
//}
