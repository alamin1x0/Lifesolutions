//package com.stardigiinternational.starnotee.Activities;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatSpinner;
//
//import android.app.DatePickerDialog;
//import android.app.ProgressDialog;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Helpers.InternetCheck;
//import com.stardigiinternational.starnotee.R;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//import static com.google.firebase.storage.FirebaseStorage.getInstance;
//
//public class EditProfileActivity extends AppCompatActivity {
//
//    TextInputEditText edFirstName,edLastName,edAddress,edPhone,edEmail,edStudy,edBio;
//    AppCompatSpinner spinner_bloodGroup;
//    TextView tvBirthDate;
//    CircleImageView profileImage;
//    private long dateNew,dateOld = 0,birthDate;
//    private String firstName,lastName,address,phone,email,studentInfo = "none",blood = "none",uID,imageUrl,bio,registeredType;
//    DatabaseReference mReference;
//    String[] st_blood_group;
//    Button save;
//    ImageButton back;
//    int mYear, mMonth, mDay;
//    private Calendar calendar;
//    DatePickerDialog.OnDateSetListener dateSetListener1;
//    private ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_profile);
//
////        edFirstName = findViewById(R.id.first_name_edit_profile);
////        edLastName = findViewById(R.id.last_name_edit_profile);
////        edAddress = findViewById(R.id.address_edit_profile);
////        edPhone = findViewById(R.id.phone_edit_profile);
////        edEmail = findViewById(R.id.email_edit_profile);
////        edStudy = findViewById(R.id.study_info_edit_profile);
////        spinner_bloodGroup = findViewById(R.id.blood_spinner_edit_profile);
////        tvBirthDate = findViewById(R.id.birth_date_edit_profile);
////        back = findViewById(R.id.back_button_edit_profile);
////        save = findViewById(R.id.save_edit_profile);
////        profileImage = findViewById(R.id.profile_image_edit_profile);
////        edBio = findViewById(R.id.bio_edit_profile);
////
////        progressDialog = new ProgressDialog(this);
////
////        st_blood_group = getResources().getStringArray(R.array.blood_group);
////        final ArrayAdapter<String> adapter_blood = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_layout,R.id.text_spinner,st_blood_group);
////        spinner_bloodGroup.setAdapter(adapter_blood);
////
////        mReference = FirebaseDatabase.getInstance().getReference().child("Users");
////        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
////        uID = sharedPreferences.getString("uID",null);
////
////        calendar = Calendar.getInstance();
////
////        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////
////                firstName = dataSnapshot.child(uID).child("firstName").getValue(String.class);
////                lastName = dataSnapshot.child(uID).child("lastName").getValue(String.class);
////                email = dataSnapshot.child(uID).child("email").getValue(String.class);
////                address = dataSnapshot.child(uID).child("address").getValue(String.class);
////                phone = dataSnapshot.child(uID).child("phone").getValue(String.class);
////                registeredType = dataSnapshot.child(uID).child("registrationType").getValue(String.class);
////                if (dataSnapshot.child(uID).child("birthDate").exists()){
////                    dateOld = dataSnapshot.child(uID).child("birthDate").getValue(Long.class);
////                }
////                imageUrl = dataSnapshot.child(uID).child("imageMedium").getValue(String.class);
////                if (dataSnapshot.child(uID).child("bloodGroup").exists()){
////                    blood = dataSnapshot.child(uID).child("bloodGroup").getValue(String.class);
////                }
////                if (dataSnapshot.child(uID).child("studyInfo").exists()){
////                    studentInfo = dataSnapshot.child(uID).child("studyInfo").getValue(String.class);
////                }
////
////                if (dataSnapshot.child(uID).child("bio").exists()){
////                    bio = dataSnapshot.child(uID).child("bio").getValue(String.class);
////                    edBio.setText(bio);
////                }
////
////                if (registeredType.equals("phone")){
////                    edEmail.setEnabled(true);
////                    edPhone.setEnabled(false);
////                } else if (registeredType.equals("email")){
////                    edEmail.setEnabled(false);
////                    edPhone.setEnabled(true);
////                } else {
////                    edEmail.setEnabled(true);
////                    edPhone.setEnabled(true);
////                }
////
////                edFirstName.setText(firstName);
////                edLastName.setText(lastName);
////                edAddress.setText(address);
////
////                if (phone != null){
////                    edPhone.setText(phone);
////                }
////
////                if (email != null){
////                    edEmail.setText(email);
////                }
////
////                if (dateOld != 0){
////                    tvBirthDate.setText(getBirthDate(dateOld));
////                    dateNew = dateOld;
////                }
////
////                if (!blood.equals("none")){
////                    spinner_bloodGroup.setSelection(adapter_blood.getPosition(blood));
////                }
////                if (!studentInfo.equals("none")){
////                    edStudy.setText(studentInfo);
////                }
////                if (!imageUrl.equals("none")){
////                    Picasso.get().load(imageUrl).into(profileImage);
////                }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                Toast.makeText(EditProfileActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
////            }
////        });
////
////        spinner_bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                if (i == 0){
////                    blood = "none";
////                }else {
////                    blood = st_blood_group[i];
////                }
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> adapterView) {
////
////            }
////        });
////
////        back.setOnClickListener(view -> finish());
////
////        save.setOnClickListener(view -> {
////
////            if (edFirstName.getText().toString().isEmpty())
////            {
////                edFirstName.setError("This field can't be empty");
////                edFirstName.requestFocus();
////            }
////            else if (edLastName.getText().toString().isEmpty())
////            {
////                edLastName.setError("This field can't be empty");
////                edLastName.requestFocus();
////            }
////            else if (edAddress.getText().toString().isEmpty())
////            {
////                edAddress.setError("This field can't be empty");
////                edAddress.requestFocus();
////
////            }
////            else if (edStudy.getText().toString().isEmpty())
////            {
////                edStudy.setError("This field can't be empty");
////                edStudy.requestFocus();
////
////            } else {
////                if (InternetCheck.checkConnection(EditProfileActivity.this)){
////
////                    progressDialog.setTitle("Updating your profile");
////                    progressDialog.setMessage("Please wait, while we are updating your profile");
////                    progressDialog.setCanceledOnTouchOutside(false);
////                    progressDialog.show();
////
////                    if (dateOld == dateNew){
////                        birthDate = dateOld;
////                    }else {
////                        birthDate = dateNew;
////                    }
////
////                    updateData(edFirstName.getText().toString(),edLastName.getText().toString(),edEmail.getText().toString(),edAddress.getText().toString(),edPhone.getText().toString(),edStudy.getText().toString(),blood,birthDate,edBio.getText().toString());
////
////                } else {
////                    Toast.makeText(EditProfileActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
////                }
////            }
////
////
////        });
////
////        tvBirthDate.setOnClickListener(view -> {
////            final Calendar c = Calendar.getInstance();
////            mYear = c.get(Calendar.YEAR);
////            mMonth = c.get(Calendar.MONTH);
////            mDay = c.get(Calendar.DAY_OF_MONTH);
////
////            DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileActivity.this,
////                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
////                    dateSetListener1,mYear,mMonth,mDay);
////
////            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////            datePickerDialog.show();
////        });
////
////        dateSetListener1 = (datePicker, i, i1, i2) -> {
////            calendar.set(i,i1,i2);
////            dateNew = calendar.getTimeInMillis();
////            tvBirthDate.setText(getBirthDate(dateNew));
////        };
//
//    }
//
//    private String getBirthDate(long birthDate){
//
//        String date = null;
//        SimpleDateFormat formatter = new SimpleDateFormat("dd - MMMM - yyyy");
//        date = formatter.format(birthDate);
//        return date;
//    }
//
//    private void updateData(String fName,String lName,String mail,String add,String number,String study,String bl,long bDate,String bioUpdate){
//
//        mReference.child(uID).child("firstName").setValue(fName);
//        mReference.child(uID).child("lastName").setValue(lName);
//        mReference.child(uID).child("email").setValue(mail);
//        mReference.child(uID).child("address").setValue(add);
//        mReference.child(uID).child("phone").setValue(number);
//        mReference.child(uID).child("birthDate").setValue(bDate);
//        mReference.child(uID).child("studyInfo").setValue(study);
//        mReference.child(uID).child("bloodGroup").setValue(bl);
//
//        if (bioUpdate != null){
//            mReference.child(uID).child("bio").setValue(bioUpdate);
//        }
//
//        progressDialog.dismiss();
//        Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
//        finish();
//    }
//
//
//}
