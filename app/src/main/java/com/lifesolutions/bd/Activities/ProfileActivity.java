package com.lifesolutions.bd.Activities;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.samehadar.iosdialog.IOSDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Adapters.ProfilePostListAdapter;
import com.lifesolutions.bd.Helpers.FileUtils;
import com.lifesolutions.bd.Helpers.ImageUtils;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.ImageUpload.ImageServerClient;
import com.lifesolutions.bd.ImageUpload.ImageUpload;
import com.lifesolutions.bd.Models.PostItem;
import com.lifesolutions.bd.Models.UserId;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.data.model.UserKt;
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity;
import com.lifesolutions.bd.kotlinCode.ui.user.EditProfileInfoKtActivity;
import com.lifesolutions.bd.kotlinCode.ui.user.UserFriendListActivity;
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvAddress, tvPhone, tvEmail, tvStudentInfo, tvBlood, tvJoined, tvBirthDate, tvFriends, tvFollowers, tvBio;
    private String firstName, lastName, cover_thumb, address, phone, email, studentInfo = "none", bio,
            blood = "none", uID, imageUrl, coverImageUrlHD, coverImageUrlThumb, randomId, imageUrlHD, imageUrlThumb, profileImageUrlHD, cover_HD;
    long birthDate = 0, joined;
    LinearLayout editProfile, btnMyPosts, btnRefer, myFriends;
    private ProfilePostListAdapter mAdapter;
    DatabaseReference mReference, postReference;
    CircleImageView imageView;
    ImageView coverPhoto;
    CardView cardVideo, cardGroups, cardPages, cardSkills;
    RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<PostItem> list;
    ImageButton coverImgUpload, profileImgUpload;
    private int count = 0, myPostCount = 0;
    private IOSDialog dialog1;
    RecyclerView recyclerView;
    private StorageReference storageReference, storageReferenceP, srNormal, srHDC, srThumbP, srHDp;
    private ProgressDialog progressDialog;
    private static final int PReqCode = 2;
    private Uri pickedImgUri;
    TextView seeAllPost, tvMyPosts;
    private Bitmap profileBitmap, coverBitmap;
    private Boolean cover = false, verified = false;
    FirebaseAuth mAuth;

    private Boolean useReferCode = false;
    LinearLayout addReferCode;
    private ViewProgressDialog progressBar;
    private RelativeLayout mainProfileViewArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvAddress = findViewById(R.id.address_profile);
        tvName = findViewById(R.id.user_name_profile);
        tvPhone = findViewById(R.id.phone_profile);
        tvEmail = findViewById(R.id.email_profile);
        tvStudentInfo = findViewById(R.id.studentInfo_profile);
        tvBlood = findViewById(R.id.blood_group_profile);
        tvJoined = findViewById(R.id.joined_profile);
        tvBirthDate = findViewById(R.id.birth_date_profile);
        imageView = findViewById(R.id.profile_image_profile);
        editProfile = findViewById(R.id.edit_profile_profile);
        recyclerView = findViewById(R.id.post_recyclerView_profile);
        seeAllPost = findViewById(R.id.see_all_post_profile);
        tvMyPosts = findViewById(R.id.myPosts_profile);
        btnMyPosts = findViewById(R.id.my_post_profile);
        coverImgUpload = findViewById(R.id.btn_upload_cover_photo);
        profileImgUpload = findViewById(R.id.btn_upload_profile_photo);
        coverPhoto = findViewById(R.id.cover_picture_profile);
        btnRefer = findViewById(R.id.refer_profile);
        tvFriends = findViewById(R.id.friends_profile);
        tvFollowers = findViewById(R.id.followers_profile);
        cardVideo = findViewById(R.id.video_layout_profile);
        cardSkills = findViewById(R.id.skills_layout_profile);
        cardGroups = findViewById(R.id.groups_layout_profile);
        cardPages = findViewById(R.id.pages_layout_profile);
        tvBio = findViewById(R.id.user_designation_profile);
        myFriends = findViewById(R.id.friends_layout_profile);

        mainProfileViewArea = findViewById(R.id.main_profile_view_area);
        addReferCode = findViewById(R.id.add_refer_code);
        progressBar = new ViewProgressDialog(this);

        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth != null) {
            uID = mAuth.getUid();
        } else {
            Intent intent = new Intent(ProfileActivity.this, LoginKtActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ProfileActivity.this.finish();
        }

        mReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uID);
        // postReference = FirebaseDatabase.getInstance().getReference().child("Posts");
        postReference = FirebaseDatabase.getInstance().getReference().child("Feeds");

        storageReference = FirebaseStorage.getInstance().getReference().child("Cover_Images");
        storageReferenceP = FirebaseStorage.getInstance().getReference().child("User_Images");
        progressDialog = new ProgressDialog(this);
        randomId = UUID.randomUUID().toString();

        list = new ArrayList<>();

        dialog1 = new IOSDialog.Builder(ProfileActivity.this)
                .setDimAmount(3)
                .setSpinnerColorRes(R.color.white)
                .setMessageColorRes(R.color.white)
                .setTitleColorRes(R.color.colorPrimary)
                .setMessageContent("Please Wait")
                .setCancelable(false)
                .setMessageContentGravity(Gravity.CENTER)
                .build();


        if (InternetCheck.checkConnection(ProfileActivity.this)) {
            dialog1.show();
            mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    firstName = dataSnapshot.child("firstName").getValue(String.class);
                    lastName = dataSnapshot.child("lastName").getValue(String.class);

                    if (dataSnapshot.child("useReferCode").exists()) {
                        useReferCode = dataSnapshot.child("useReferCode").getValue(Boolean.class);
                    }

                    if (dataSnapshot.child("address").exists()) {
                        address = dataSnapshot.child("address").getValue(String.class);
                    }
                    imageUrl = dataSnapshot.child("imageThumbnail").getValue(String.class);
                    profileImageUrlHD = dataSnapshot.child("imageHD").getValue(String.class);
                    if (dataSnapshot.child("birthDate").exists()) {
                        birthDate = dataSnapshot.child("birthDate").getValue(Long.class);
                    }
                    joined = dataSnapshot.child("registeredTime").getValue(Long.class);
                    email = dataSnapshot.child("email").getValue(String.class);
                    phone = dataSnapshot.child("phone").getValue(String.class);

                    if (dataSnapshot.child("coverImageThumb").exists()) {
                        cover_thumb = dataSnapshot.child("coverImageThumb").getValue(String.class);
                        cover_HD = dataSnapshot.child("coverImageHD").getValue(String.class);
                        Picasso.get().load(cover_thumb).into(coverPhoto);
                        coverPhoto.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, ViewImageActivity.class).putExtra("imageUrl", cover_HD)));
                    }

                    if (dataSnapshot.child("isVerified").exists()) {
                        verified = dataSnapshot.child("isVerified").getValue(Boolean.class);
                    }

                    if (verified) {
                        tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_new_badge, 0, 0, 0);
                    } else {
                        tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }

                    if (!TextUtils.isEmpty(phone)) {
                        tvPhone.setVisibility(View.VISIBLE);
                        tvPhone.setText(phone);
                    } else {
                        tvPhone.setVisibility(View.GONE);
                    }
                    if (!TextUtils.isEmpty(email)) {
                        tvEmail.setVisibility(View.VISIBLE);
                        tvEmail.setText(email);
                    } else {
                        tvEmail.setVisibility(View.GONE);
                    }

                    if (dataSnapshot.child("studyInfo").exists()) {
                        studentInfo = dataSnapshot.child("studyInfo").getValue(String.class);
                    }

                    if (dataSnapshot.child("bio").exists()) {
                        bio = dataSnapshot.child("bio").getValue(String.class);
                        tvBio.setVisibility(View.VISIBLE);
                        tvBio.setText(bio);
                    } else {
                        tvBio.setVisibility(View.GONE);
                    }

                    if (dataSnapshot.child("bloodGroup").exists()) {
                        blood = dataSnapshot.child("bloodGroup").getValue(String.class);
                    }

                    dialog1.dismiss();

                    tvName.setText(firstName + " " + lastName);
                    if (!TextUtils.isEmpty(address)) {
                        tvAddress.setVisibility(View.VISIBLE);
                        tvAddress.setText(address);
                    } else {
                        tvAddress.setVisibility(View.GONE);
                    }
                    tvJoined.setText(getJoinedDate(joined));
                    if (birthDate != 0) {
                        tvBirthDate.setVisibility(View.VISIBLE);
                        tvBirthDate.setText(getBirthDate(birthDate));
                    } else {
                        tvBirthDate.setVisibility(View.GONE);
                    }
                    if (TextUtils.isEmpty(blood)) {
                        tvBlood.setVisibility(View.GONE);
                    } else {
                        tvBlood.setText(blood);
                    }
                    if (TextUtils.isEmpty(studentInfo)) {
                        tvStudentInfo.setVisibility(View.GONE);
                    } else {
                        tvStudentInfo.setText(studentInfo);
                    }
                    if (!imageUrl.equals("none")) {
                        Picasso.get().load(imageUrl).into(imageView);
                        imageView.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, ViewImageActivity.class).putExtra("imageUrl", profileImageUrlHD)));
                    }

                    randomId = firstName + lastName + UUID.randomUUID().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(ProfileActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
                }

            });

            getAllPost();
        } else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        getFriendFollowerCount(uID, tvFriends, tvFollowers);

        editProfile.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, EditProfileInfoKtActivity.class)));
        // editProfile.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class)));


        final View.OnClickListener onClickListener = view -> {
            Toast.makeText(this, "This feature is currently unavailable :)", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(ProfileActivity.this, MyPostListActivity.class));
        };
        seeAllPost.setOnClickListener(onClickListener);
        btnMyPosts.setOnClickListener(onClickListener);

        coverImgUpload.setOnClickListener(view -> {
            cover = true;
            selectImage();
        });

        profileImgUpload.setOnClickListener(view -> {
            cover = false;
            selectImage();
        });

        btnRefer.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, ReferActivity.class)));


        cardPages.setOnClickListener(view -> {
            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
        });

        cardGroups.setOnClickListener(view -> {
//            startActivity(new Intent(ProfileActivity.this,GroupChatListActivity.class));
//            finish();
        });

        cardSkills.setOnClickListener(view -> {
            Toast.makeText(this, "Not Available", Toast.LENGTH_SHORT).show();
//             startActivity(new Intent(ProfileActivity.this,RandomCallActivity.class));
//             finish();
        });

        cardVideo.setOnClickListener(view -> {
            startActivity(new Intent(ProfileActivity.this, QuizActivity.class));
            finish();
        });

        myFriends.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, UserFriendListActivity.class)));


        // On Refer Code Added
        addReferCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mainProfileViewArea, "Already Applied Refer Code", Snackbar.LENGTH_LONG).show();

//                if (useReferCode) {
//                    Snackbar.make(mainProfileViewArea, "Already Applied Refer Code", Snackbar.LENGTH_LONG).show();
//                } else {
//                    openApplyReferDialog();
//                }
            }
        });

    }

    private void openApplyReferDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Refer code");
        builder.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        View viewInflated = inflater.inflate(R.layout.apply_refer_code_dialog, null);

        final EditText input = (EditText) viewInflated.findViewById(R.id.et_input_refer_code);
        builder.setView(viewInflated);

        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String referCode = input.getText().toString();
                dialog.dismiss();
                applyReferCode(referCode);
                // Toast.makeText(ProfileActivity.this, referCode, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void applyReferCode(String referCode) {
        progressBar.showLoadingBar("Applying Refer Code..");

        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("referCode").equalTo(referCode);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    UserKt createUser = null;
                    for (DataSnapshot childDss : dataSnapshot.getChildren()) {
                        createUser = childDss.getValue(UserKt.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void BackToHome(View view) {
        finish();
    }

    private String getJoinedDate(long joined) {
        String date;
        SimpleDateFormat formatter = new SimpleDateFormat(" MMMM, yyyy", Locale.getDefault());
        date = formatter.format(joined);
        return date;
    }

    private String getBirthDate(long birthDate) {
        String date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd - MMMM - yyyy", Locale.getDefault());
        date = formatter.format(birthDate);
        return date;
    }

    private void getAllPost() {

        if (InternetCheck.checkConnection(this)) {

            Query query = postReference.orderByChild("authorID").equalTo(uID);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list.clear();
                    count = 0;
                    myPostCount = 0;

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        PostItem postItem = dataSnapshot1.getValue(PostItem.class);
                        if (postItem.getAuthorID().equals(uID) && !postItem.getPostImage().equals("none") && count < 10) {
                            list.add(postItem);
                            count++;
                        }

                        if (postItem.getAuthorID().equals(uID)) {
                            myPostCount++;
                        }

                    }

                    tvMyPosts.setText("" + myPostCount);

                    mAdapter = new ProfilePostListAdapter(ProfileActivity.this, list);
                    recyclerView.setAdapter(mAdapter);

                    if (myPostCount == 0) {
                        seeAllPost.setVisibility(View.GONE);
                    } else {
                        seeAllPost.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectImage() {

        if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(ProfileActivity.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(ProfileActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
            }
//
        } else {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(ProfileActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                pickedImgUri = result.getUri();
                if (cover) {

                    coverBitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri.getPath(), 30);

                    final Dialog dialog = new Dialog(ProfileActivity.this);
                    dialog.setContentView(R.layout.image_upload_dialog);
                    dialog.setCancelable(false);
                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ImageView imageViewD = dialog.findViewById(R.id.cover_image_image_upload_dialog);
                    ImageView imageViewP = dialog.findViewById(R.id.profile_image_image_upload_dialog);
                    imageViewP.setVisibility(View.GONE);
                    imageViewD.setVisibility(View.VISIBLE);
                    TextView ok_btn = dialog.findViewById(R.id.upload_button_image_dialog);
                    TextView cancel_btn = dialog.findViewById(R.id.quit_button_image_dialog);
                    imageViewD.setImageBitmap(coverBitmap);
                    ok_btn.setOnClickListener(v -> {
                        if (InternetCheck.checkConnection(this)) {
                            uploadCoverPhoto();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                    cancel_btn.setOnClickListener(view -> {
                        dialog.dismiss();
                    });
                    dialog.show();
                } else {

                    profileBitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri.getPath(), 45);

                    final Dialog dialog = new Dialog(ProfileActivity.this);
                    dialog.setContentView(R.layout.image_upload_dialog);
                    dialog.setCancelable(false);
                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ImageView imageViewD = dialog.findViewById(R.id.cover_image_image_upload_dialog);
                    ImageView imageViewP = dialog.findViewById(R.id.profile_image_image_upload_dialog);
                    imageViewD.setVisibility(View.GONE);
                    imageViewP.setVisibility(View.VISIBLE);
                    TextView ok_btn = dialog.findViewById(R.id.upload_button_image_dialog);
                    TextView cancel_btn = dialog.findViewById(R.id.quit_button_image_dialog);
                    imageViewP.setImageBitmap(profileBitmap);
                    ok_btn.setOnClickListener(v -> {
                        if (InternetCheck.checkConnection(this)) {
                            uploadProfilePhoto();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                    cancel_btn.setOnClickListener(view -> {
                        dialog.dismiss();
                    });
                    dialog.show();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void uploadProfilePhoto() {

        progressDialog.setTitle("Updating Profile Picture");
        progressDialog.setMessage("Please wait, while we are updating your profile picture");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        profileBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] thumbData = baos.toByteArray();

        File imageFile;
        try {
            Uri imageUri = getImageUri(this, profileBitmap);
            imageFile = FileUtils.uriToFile(this, imageUri);

            RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, "profile_images");

            RequestBody imageFilePart = RequestBody.create(
                    MediaType.parse("image/jpeg"),
                    imageFile
            );

            MultipartBody.Part file = MultipartBody.Part.createFormData("imageFile", System.currentTimeMillis() + imageFile.getName(), imageFilePart);


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
                        imageUrlThumb = response.body().getDownloadUrlRes();

                        mReference.child("imageThumbnail").setValue(imageUrlThumb).addOnSuccessListener(aVoid -> mReference.child("imageHD").setValue(imageUrlThumb).addOnSuccessListener(aVoid1 -> {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Profile picture updated", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(e -> Toast.makeText(ProfileActivity.this, "Failed to update profile picture", Toast.LENGTH_SHORT).show())).addOnFailureListener(e -> Toast.makeText(ProfileActivity.this, "failed to update profile picture", Toast.LENGTH_SHORT).show());

                    }
                }

                @Override
                public void onFailure(Call<ImageUpload> call, Throwable t) {
                    Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*srThumbP = storageReferenceP.child(System.currentTimeMillis()+"thumbnail.jpg");
        srThumbP.putBytes(thumbData).addOnSuccessListener(taskSnapshot -> srThumbP.getDownloadUrl().addOnSuccessListener(uri -> {
            imageUrlThumb = uri.toString();

            Bitmap bitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri.getPath(),50);

            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
            byte[] hdData = baos1.toByteArray();

            srHDp = storageReferenceP.child("Thumbnails").child(randomId+"thumbnail.jpg");
            srHDp.putBytes(hdData).addOnSuccessListener(taskSnapshot12 -> srHDp.getDownloadUrl().addOnSuccessListener(uri12 -> {

                imageUrlHD = uri12.toString();

                mReference.child("imageThumbnail").setValue(imageUrlThumb).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mReference.child("imageHD").setValue(imageUrlHD).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Toast.makeText(ProfileActivity.this, "Profile picture updated", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProfileActivity.this, "Failed to update profile picture", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileActivity.this, "failed to update profile picture", Toast.LENGTH_SHORT).show();
                    }
                });

            }).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failed to update profile picture",Toast.LENGTH_SHORT).show();
            })).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failed to update profile picture",Toast.LENGTH_SHORT).show();
            });
        })).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Failed to update profile picture",Toast.LENGTH_SHORT).show();
        });*/
    }

    private void uploadCoverPhoto() {

        progressDialog.setTitle("Updating Cover Photo");
        progressDialog.setMessage("Please wait, while we are updating your cover photo");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        coverBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] thumbData = baos.toByteArray();

        File imageFile;
        try {
            Uri imageUri = getImageUri(this, coverBitmap);
            imageFile = FileUtils.uriToFile(this, imageUri);

            RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, "cover_images");

            RequestBody imageFilePart = RequestBody.create(
                    MediaType.parse("image/jpeg"),
                    imageFile
            );

            MultipartBody.Part file = MultipartBody.Part.createFormData("imageFile", System.currentTimeMillis() + imageFile.getName(), imageFilePart);


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
                        coverImageUrlThumb = response.body().getDownloadUrlRes();


                        mReference.child("coverImageThumb").setValue(coverImageUrlThumb).addOnCompleteListener(task -> mReference.child("coverImageHD").setValue(coverImageUrlThumb).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                Toast.makeText(ProfileActivity.this, "Cover photo uploaded successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(e -> {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                        })).addOnFailureListener(e -> {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                        });
                    }
                }

                @Override
                public void onFailure(Call<ImageUpload> call, Throwable t) {
                    Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*srNormal = storageReference.child("Normal").child(System.currentTimeMillis()+"normal.jpg");
        srNormal.putBytes(thumbData).addOnSuccessListener(taskSnapshot -> srNormal.getDownloadUrl().addOnSuccessListener(uri -> {

            coverImageUrlThumb = uri.toString();


            Bitmap bitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri.getPath(),50);

            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
            byte[] thumbData1 = baos.toByteArray();

            srHDC = storageReference.child("HD").child(System.currentTimeMillis()+"hd.jpg");
            srHDC.putBytes(thumbData1).addOnSuccessListener(taskSnapshot1 -> srHDC.getDownloadUrl().addOnSuccessListener(uri1 -> {

                coverImageUrlHD = uri1.toString();

                mReference.child("coverImageThumb").setValue(coverImageUrlThumb).addOnCompleteListener(task -> mReference.child("coverImageHD").setValue(coverImageUrlHD).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this, "Cover photo uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                })).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                });

            })).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failed to update profile",Toast.LENGTH_SHORT).show();
            });

        }).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Failed to update profile",Toast.LENGTH_SHORT).show();
        })).addOnFailureListener(e -> {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Failed to update profile",Toast.LENGTH_SHORT).show();
        });*/
    }

    public static void getFriendFollowerCount(String userId, TextView friend, TextView follower) {

        DatabaseReference fReference = FirebaseDatabase.getInstance().getReference("friendRequest");
        fReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userId).exists()) {
                    int count = 0;
                    follower.setText("" + dataSnapshot.child(userId).getChildrenCount());
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.child(userId).getChildren()) {
                        if (dataSnapshot1.child("type").exists()) {
                            UserId userId = dataSnapshot1.getValue(UserId.class);
                            try {
                                if (userId.getType().equals("friend")) {
                                    count++;
                                }
                            } catch (Exception e) {
                                Log.d("Error", e.getMessage());
                            }
                        }
                    }

                    friend.setText("" + count);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
