package com.lifesolutions.bd.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.lifesolutions.bd.Helpers.FileUtils;
import com.lifesolutions.bd.Helpers.ImageUtils;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.ImageUpload.ImageServerClient;
import com.lifesolutions.bd.ImageUpload.ImageUpload;
import com.lifesolutions.bd.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplyForTeacherActivity extends AppCompatActivity {

    private static final int PReqCode = 2 ;
    private static final int REQUESTCODE = 2 ;
    CircleImageView profileImage;
    TextInputEditText  name,address,mobileNumber,email,studyInfo,bio;
    private String uID;
    DatabaseReference mReference;
    Button apply;
    private String emailStr;
    ImageButton backButton;
    ProgressDialog progressDialog;
    StorageReference storageReference,srNormal;
    private Bitmap compressedImageFile,viewCompressedImage;
    String timeStamp;
    private Uri pickedImgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_teacher);

        profileImage = findViewById(R.id.profile_image_apply_for_teacher);
        name = findViewById(R.id.name_apply_for_teacher);
        address = findViewById(R.id.address_apply_for_teacher);
        mobileNumber = findViewById(R.id.phone_apply_for_teacher);
        studyInfo = findViewById(R.id.study_info_apply_for_teacher);
        bio = findViewById(R.id.bio_apply_for_teacher);
        email = findViewById(R.id.email_apply_for_teacher);
        apply = findViewById(R.id.save_apply_for_teacher);
        backButton = findViewById(R.id.back_button_apply_for_teacher);

        mReference = FirebaseDatabase.getInstance().getReference().child("TeacherRequest");
        storageReference = FirebaseStorage.getInstance().getReference().child("TeacherImages");
        progressDialog = new ProgressDialog(this);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        uID = sharedPreferences.getString("uID",null);

        apply.setOnClickListener(view -> {
            if (name.getText().toString().isEmpty()) {
                name.setError("This field can't be empty");
                name.requestFocus();
            } else if (address.getText().toString().isEmpty()) {
                address.setError("This field can't be empty");
                address.requestFocus();
            } else if (mobileNumber.getText().toString().isEmpty()) {
                mobileNumber.setError("This field can't be empty");
                mobileNumber.requestFocus();
            } else if (studyInfo.getText().toString().isEmpty()) {
                studyInfo.setError("Select your Date of Birth");
                studyInfo.requestFocus();
            } else if (bio.getText().toString().isEmpty()){
                bio.setError("Select your Date of Birth");
                bio.requestFocus();
            } else if (!InternetCheck.checkConnection(ApplyForTeacherActivity.this)){
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            } else {

                progressDialog.setTitle("Sending request");
                progressDialog.setMessage("Please wait, while we are sending your request");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(false);
                progressDialog.show();

                timeStamp = String.valueOf(System.currentTimeMillis());

                if (email.getText().toString().isEmpty()){
                    emailStr = "none";
                } else {
                    emailStr = email.getText().toString();
                }

                if (pickedImgUri == null){

                    HashMap<String ,String> hashMap = new HashMap<>();
                    hashMap.put("name",name.getText().toString());
                    hashMap.put("address",address.getText().toString());
                    hashMap.put("phone",mobileNumber.getText().toString());
                    hashMap.put("studyInfo",studyInfo.getText().toString());
                    hashMap.put("bio",bio.getText().toString());
                    hashMap.put("image","none");
                    hashMap.put("email",emailStr);
                    hashMap.put("timeStamp",timeStamp);
                    hashMap.put("_id",uID);

                    mReference.child(timeStamp).setValue(hashMap).addOnSuccessListener(aVoid -> {
                        progressDialog.dismiss();
                        Toast.makeText(ApplyForTeacherActivity.this, "Application successfully send", Toast.LENGTH_SHORT).show();
                        finish();
                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();;
                        Toast.makeText(ApplyForTeacherActivity.this, "Failed to send request", Toast.LENGTH_SHORT).show();
                    });

                } else {

                    ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                    compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
                    byte[] thumbData = baos1.toByteArray();

                    File imageFile;
                    try {
                        Uri imageUri = getImageUri(this,compressedImageFile);
                        imageFile = FileUtils.uriToFile(this,imageUri);

                        RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, "group_images");

                        RequestBody imageFilePart = RequestBody.create(
                                MediaType.parse("image/jpeg"),
                                imageFile
                        );

                        MultipartBody.Part file = MultipartBody.Part.createFormData("imageFile", System.currentTimeMillis()+imageFile.getName(), imageFilePart);


                        Retrofit.Builder builder = new Retrofit.Builder()
                                .baseUrl("https://ftp.starnotesocial.com/")
                                .addConverterFactory(GsonConverterFactory.create());

                        Retrofit retrofit = builder.build();

                        ImageServerClient client = retrofit.create(ImageServerClient.class);

                        Call<ImageUpload> call = client.uploadImage(folderPathPart, file);

                        call.enqueue(new Callback<ImageUpload>() {
                            @Override
                            public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
                                if (response.isSuccessful()) {

                                    HashMap<String ,String> hashMap = new HashMap<>();
                                    hashMap.put("name",name.getText().toString());
                                    hashMap.put("address",address.getText().toString());
                                    hashMap.put("phone",mobileNumber.getText().toString());
                                    hashMap.put("studyInfo",studyInfo.getText().toString());
                                    hashMap.put("bio",bio.getText().toString());
                                    hashMap.put("image",response.body().getDownloadUrlRes());
                                    hashMap.put("email",emailStr);
                                    hashMap.put("timeStamp",timeStamp);
                                    hashMap.put("_id",uID);

                                    mReference.child(timeStamp).setValue(hashMap).addOnSuccessListener(aVoid -> {
                                        progressDialog.dismiss();
                                        Toast.makeText(ApplyForTeacherActivity.this, "Application successfully send", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }).addOnFailureListener(e -> {
                                        progressDialog.dismiss();
                                        Toast.makeText(ApplyForTeacherActivity.this, "Failed to send request", Toast.LENGTH_SHORT).show();
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<ImageUpload> call, Throwable t) {
                                Toast.makeText(ApplyForTeacherActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    /*srNormal = storageReference.child(timeStamp+name.getText().toString()+".jpg");
                    srNormal.putBytes(thumbData).addOnSuccessListener(taskSnapshot -> srNormal.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            HashMap<String ,String> hashMap = new HashMap<>();
                            hashMap.put("name",name.getText().toString());
                            hashMap.put("address",address.getText().toString());
                            hashMap.put("phone",mobileNumber.getText().toString());
                            hashMap.put("studyInfo",studyInfo.getText().toString());
                            hashMap.put("bio",bio.getText().toString());
                            hashMap.put("image",uri.toString());
                            hashMap.put("email",emailStr);
                            hashMap.put("timeStamp",timeStamp);

                            mReference.child(timeStamp).setValue(hashMap).addOnSuccessListener(aVoid -> {
                                progressDialog.dismiss();
                                Toast.makeText(ApplyForTeacherActivity.this, "Application successfully send", Toast.LENGTH_SHORT).show();
                                finish();
                            }).addOnFailureListener(e -> {
                                progressDialog.dismiss();
                                Toast.makeText(ApplyForTeacherActivity.this, "Failed to send request", Toast.LENGTH_SHORT).show();
                            });

                        }
                    })).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed to send request",Toast.LENGTH_SHORT).show();
                    });*/
                }
            }
        });

        backButton.setOnClickListener(view -> {
            finish();
        });

        profileImage.setOnClickListener(view -> {
            selectImage();
        });

    }

    public void selectImage() {

        if (ContextCompat.checkSelfPermission(ApplyForTeacherActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ApplyForTeacherActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(ApplyForTeacherActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(ApplyForTeacherActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
            }

        } else {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent,REQUESTCODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            pickedImgUri = data.getData();

            compressedImageFile = ImageUtils.getInstant().getCompressedBitmap(getRealPathFromURI(pickedImgUri,ApplyForTeacherActivity.this),40);
            profileImage.setImageBitmap(compressedImageFile);

        }
    }

    public String getRealPathFromURI(Uri contentURI, Activity context) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = context.managedQuery(contentURI, projection, null,
                null, null);
        if (cursor == null)
            return null;
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        if (cursor.moveToFirst()) {
            String s = cursor.getString(column_index);
            // cursor.close();
            return s;
        }
        // cursor.close();
        return null;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
