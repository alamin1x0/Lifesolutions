//package com.stardigiinternational.starnotee.Activities;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatSpinner;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.ActivityCompat;
//
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.StorageReference;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Adapters.PrivacyAdapterSpinner;
//import com.stardigiinternational.starnotee.Helpers.FileUtils;
//import com.stardigiinternational.starnotee.Helpers.ImageUtils;
//import com.stardigiinternational.starnotee.ImageUpload.ImageServerClient;
//import com.stardigiinternational.starnotee.ImageUpload.ImageUpload;
//import com.stardigiinternational.starnotee.Models.PostItem;
//import com.stardigiinternational.starnotee.Models.SpinnerItem;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.kotlinCode.ui.auth.LoginKtActivity;
//import com.stardigiinternational.starnotee.kotlinCode.utils.Utils;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//import static com.google.firebase.storage.FirebaseStorage.getInstance;
//import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;
//
//public class AddPostActivity extends AppCompatActivity {
//
//    private static final int REQUESTCODE = 2 ;
//    private static final int STORAGE_REQUEST_CODE = 101;
//    private EditText EdDescription;
//    PrivacyAdapterSpinner mAdapter;
//    private ArrayList<SpinnerItem> spinnerItems;
//    Toolbar toolbar;
//    TextView btnSave,authorName;
//    private int code,points = 0,count = 0 ;
//    private ProgressDialog progressDialog;
//    String imageURL = "none",firstName,lastName;
//    private ImageView imageView;
//    TextView getImage,getMore;
//    CircleImageView authorImage;
//    RelativeLayout attachmentLayout;
//    private long date;
//    AppCompatSpinner spinner;
//    private String privacy = "public",g_Description,g_ImageLink,g_Privacy,description,downloadUrl = "none",imageUrlThumb,uID,postId,I_postId;
//    private Uri pickedImgUri;
//    FirebaseDatabase database;
//    private int postCount = 0;
//    DatabaseReference reference,myRef,databaseReference,mReference;
//    StorageReference storageReference,mImagePath,srThumb;
//    Bitmap hdBitmap;
//    private String[] PERMISSIONS = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_post);
//
//        EdDescription = findViewById(R.id.description_add_post);
//        imageView = findViewById(R.id.image_add_post);
//        getImage = findViewById(R.id.get_image_add_post);
//        getMore = findViewById(R.id.get_more_add_post);
//        btnSave = findViewById(R.id.save_button_add_post);
//        authorName = findViewById(R.id.user_name_add_post);
//        authorImage = findViewById(R.id.user_image_add_post);
//        spinner = findViewById(R.id.spinner_add_post);
//        toolbar = findViewById(R.id.toolbar_add_post);
//        attachmentLayout = findViewById(R.id.attach_file_layout_add_post);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        progressDialog = new ProgressDialog(this);
//        storageReference = getInstance().getReference().child("Post_Images");
//
//        mReference = FirebaseDatabase.getInstance().getReference().child("Users");
//        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
//        if (sharedPreferences.contains("uID")){
//            uID = sharedPreferences.getString("uID",null);
//        } else {
//            startActivity(new Intent(AddPostActivity.this, LoginKtActivity.class));
//            finish();
//        }
//
//        Intent myIntent = getIntent();
//        if (myIntent != null)
//        {
//            code = myIntent.getIntExtra("code",0);
//            I_postId = myIntent.getStringExtra("postId");
//
//        }
//
//        btnSave.setVisibility(View.VISIBLE);
//
//        initList();
//
//        getUserInfo(uID);
//
//        mAdapter = new PrivacyAdapterSpinner(this, spinnerItems) {
//        };
//
//        spinner.setAdapter(mAdapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//               if (i == 0){
//                   privacy = "public";
//               }else if (i == 1){
//                   privacy = "friends";
//               } else {
//                   privacy = "onlyMe";
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        if (code == 1) {
//
//            // Edit Start
//
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts");
//
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    g_Description = dataSnapshot.child(I_postId).child("postDescription").getValue(String.class);
//                    g_ImageLink = dataSnapshot.child(I_postId).child("postImage").getValue(String.class);
//                    g_Privacy = dataSnapshot.child(I_postId).child("privacy").getValue(String.class);
//
//                    setTitle("Edit Post");
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                    EdDescription.setText(g_Description);
//                    Picasso.get().load(g_ImageLink).into(imageView);
//                    EdDescription.setEnabled(true);
//                    btnSave.setText("Save");
//                    if (g_Privacy.equals("public")){
//                        spinner.setSelection(0);
//                    } else if (g_Privacy.equals("friends")){
//                        spinner.setSelection(1);
//                    } else {
//                        spinner.setSelection(2);
//                    }
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    Toast.makeText(AddPostActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            btnSave.setOnClickListener(v -> {
//
//                final AlertDialog.Builder builder = new AlertDialog.Builder(AddPostActivity.this);
//                builder.setTitle("Save");
//                builder.setCancelable(false);
//                builder.setMessage("Are you sure to save changes this post ?");
//                builder.setPositiveButton("Yes", (dialog, which) -> {
//
//                    description = EdDescription.getText().toString();
//
//                    if (description.isEmpty()) {
//                        description = "   ";
//                    } else if (pickedImgUri == null) {
//                        progressDialog.setTitle("Updating post");
//                        progressDialog.setMessage("Please wait, while we are saving changes this post");
//                        progressDialog.setCanceledOnTouchOutside(false);
//                        progressDialog.setCancelable(false);
//                        progressDialog.setIndeterminate(false);
//                        progressDialog.show();
//
//                        databaseReference.child(I_postId).child("postDescription").setValue(description);
//                        databaseReference.child(I_postId).child("privacy").setValue(privacy);
//
//                        progressDialog.dismiss();
//                        Toast.makeText(AddPostActivity.this, "Post updated successfully", Toast.LENGTH_SHORT).show();
//                        finish();
//
//                    } else {
//                        if (g_ImageLink.equals("none")) {
//                            updatePost(privacy,description);
//                        } else {
//                            progressDialog.setTitle("Updating Post");
//                            progressDialog.setMessage("Please wait, while we are updating your post");
//                            progressDialog.setCanceledOnTouchOutside(false);
//                            progressDialog.setCancelable(false);
//                            progressDialog.setIndeterminate(false);
//                            progressDialog.show();
//
//                            StorageReference mPictureReference = getInstance().getReferenceFromUrl(g_ImageLink);
//                            mPictureReference.delete()
//                                    .addOnSuccessListener(aVoid -> {
//
//                                        date = System.currentTimeMillis();
//                                        updatePost(privacy,description);
//                                    }).addOnFailureListener(e -> Toast.makeText(AddPostActivity.this, "Failed to delete previous image", Toast.LENGTH_SHORT).show());
//                        }
//
//                    }
//
//                });
//
//                builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
//
//                builder.show();
//
//            });
//
//        }
//        else if (code == 2) {
//
//            //Delete Start
//
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts");
//
//            attachmentLayout.setVisibility(View.GONE);
//
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    g_Description = dataSnapshot.child(I_postId).child("postDescription").getValue(String.class);
//                    g_ImageLink = dataSnapshot.child(I_postId).child("postImage").getValue(String.class);
//
//                    setTitle("Delete Post");
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//                    EdDescription.setText(g_Description);
//                    Picasso.get().load(g_ImageLink).into(imageView);
//                    EdDescription.setEnabled(false);
//                    imageView.setEnabled(false);
//                    btnSave.setText("Delete");
//
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    Toast.makeText(AddPostActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
//                }
//
//
//            });
//
//            btnSave.setOnClickListener(v -> {
//                final AlertDialog.Builder builder = new AlertDialog.Builder(AddPostActivity.this);
//                builder.setTitle("Delete");
//                builder.setCancelable(false);
//                builder.setMessage("Are you sure to delete this post ?");
//                builder.setPositiveButton("Yes", (dialog, which) -> {
//
//                    databaseReference.child(I_postId).removeValue().addOnSuccessListener(aVoid -> {
//                        Toast.makeText(AddPostActivity.this, "Post deleted successfully", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }).addOnFailureListener(e -> Toast.makeText(AddPostActivity.this, "Something went wrong, Please try again later", Toast.LENGTH_LONG).show());
//
//                    /*if (g_ImageLink.equals("none")){
//
//                        databaseReference.child(I_postId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(AddPostActivity.this, "Post deleted successfully", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(AddPostActivity.this, "Something went wrong, Please try again later", Toast.LENGTH_LONG).show();
//                            }
//                        });
//
//                    } else {
//                        StorageReference mPictureReference = getInstance().getReferenceFromUrl(g_ImageLink);
//                        mPictureReference.delete()
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//
//                                        databaseReference.child(I_postId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Toast.makeText(AddPostActivity.this, "Post deleted successfully", Toast.LENGTH_SHORT).show();
//                                                finish();
//                                            }
//                                        }).addOnFailureListener(e -> Toast.makeText(AddPostActivity.this, "Something went wrong, Please try again later", Toast.LENGTH_LONG).show());
//                                    }
//                                }).addOnFailureListener(e -> Toast.makeText(AddPostActivity.this, "Failed to delete previous image", Toast.LENGTH_SHORT).show());
//                    }*/
//
//
//                });
//
//                builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
//
//                builder.show();
//
//            });
//        }
//        else {
//            // Add new Post
//            setTitle("Add Post");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//            database = FirebaseDatabase.getInstance();
//
//            btnSave.setOnClickListener(v -> {
//                btnSave.setVisibility(View.GONE);
//                postCount = 0;
//
//                progressDialog.setTitle("Adding new post");
//                progressDialog.setMessage("Please wait, while we are adding new post for you");
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.setCancelable(false);
//                progressDialog.setIndeterminate(false);
//                progressDialog.show();
//
//                description = EdDescription.getText().toString();
//
//                if (pickedImgUri == null) {
//                    if (description.isEmpty()) {
//                        EdDescription.setError("This field can't be empty");
//                        EdDescription.requestFocus();
//                    } else {
//                        date = System.currentTimeMillis();
//
//                        myRef = database.getReference("Posts").push();
//                        postId = myRef.getKey();
//
//                        PostItem postItem = new PostItem(uID,description,"post","none","none",postId,privacy,date,true);
//
//                        myRef.setValue(postItem).addOnSuccessListener(aVoid -> {
//                            progressDialog.dismiss();
//                            Toast.makeText(AddPostActivity.this, "Post Added Successfully", Toast.LENGTH_SHORT).show();
//                            givePostBonus(uID);
//                        });
//                    }
//                } else {
//
//                    if (description.isEmpty()) {
//                        description = "  ";
//                    }
//
//                    date = System.currentTimeMillis();
//
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    hdBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                    byte[] hqData = baos.toByteArray();
//
//                    File imageFile;
//                    try {
//                        Uri imageUri = getImageUri(this,hdBitmap);
//                        imageFile = FileUtils.uriToFile(this,imageUri);
//
//                        RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, "post_images");
//
//                        RequestBody imageFilePart = RequestBody.create(
//                                MediaType.parse("image/jpeg"),
//                                imageFile
//                        );
//
//                        MultipartBody.Part file = MultipartBody.Part.createFormData("imageFile", date+imageFile.getName(), imageFilePart);
//
//
//                        Retrofit.Builder builder = new Retrofit.Builder()
//                                .baseUrl("https://ftp.starnotesocial.com/")
//                                .addConverterFactory(GsonConverterFactory.create());
//
//                        Retrofit retrofit = builder.build();
//
//                        ImageServerClient client = retrofit.create(ImageServerClient.class);
//
//                        Call<ImageUpload> call = client.uploadImage(folderPathPart, file);
//
//                        call.enqueue(new Callback<ImageUpload>() {
//                            @Override
//                            public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
//                                if (response.isSuccessful()) {
//                                    imageUrlThumb = response.body().getDownloadUrlRes();
//                                    myRef = database.getReference("Posts").push();
//                                    postId = myRef.getKey();
//
//                                    if (postCount == 0){
//                                        PostItem postItem = new PostItem(uID,description,"post",imageUrlThumb,imageUrlThumb,postId,privacy,date,true);
//
//                                        myRef.setValue(postItem).addOnSuccessListener(aVoid -> {
//                                            progressDialog.dismiss();
//                                            Toast.makeText(AddPostActivity.this, "Post Added Successfully", Toast.LENGTH_SHORT).show();
//                                            postCount = 1;
//                                            givePostBonus(uID);
//                                        });
//
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ImageUpload> call, Throwable t) {
//                                Toast.makeText(AddPostActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                   /* mImagePath = storageReference.child(date+UUID.randomUUID().toString()+"HQ.jpg");
//                    mImagePath.putBytes(hqData).addOnSuccessListener(taskSnapshot -> mImagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//
//                            downloadUrl = uri.toString();
//                            Bitmap bitmap;
//
//                            if (Build.VERSION.SDK_INT > 28){
//                                bitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri.getPath(), 30);
//                            }else {
//                                bitmap = ImageUtils.getInstant().getCompressedBitmap(getRealPathFromURI(AddPostActivity.this, pickedImgUri), 30);
//                            }
//
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                            byte[] thumbData = baos.toByteArray();
//
//                            srThumb = storageReference.child(date+UUID.randomUUID().toString()+"thumbnail.jpg");
//                            srThumb.putBytes(thumbData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                                    srThumb.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//
//                                            imageUrlThumb = uri.toString();
//                                            myRef = database.getReference("Posts").push();
//                                            postId = myRef.getKey();
//
//                                            PostItem postItem = new PostItem(uID,description,"post",imageUrlThumb,downloadUrl,postId,privacy,date,true);
//
//                                            myRef.setValue(postItem).addOnSuccessListener(aVoid -> {
//                                                progressDialog.dismiss();
//                                                Toast.makeText(AddPostActivity.this, "Post Added Successfully", Toast.LENGTH_SHORT).show();
//                                                givePostBonus(uID);
//                                            });
//
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            progressDialog.dismiss();
//                                            Toast.makeText(getApplicationContext(),"Failed to publish post",Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(getApplicationContext(),"Failed to publish post",Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//                        }
//                    }).addOnFailureListener(e -> {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(),"Failed to publish post",Toast.LENGTH_SHORT).show();
//                    })).addOnFailureListener(e -> {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(),"Failed to publish post",Toast.LENGTH_SHORT).show();
//                    });*/
//
//                }
//            });
//        }
//
//        getImage.setOnClickListener(view -> pickImage());
//
//        getMore.setOnClickListener(view -> {
//            Toast.makeText(this, "This feature is coming soon", Toast.LENGTH_SHORT).show();
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUESTCODE){
//            if (resultCode == RESULT_OK) {
//
//                pickedImgUri = data.getData();
//
//                hdBitmap = ImageUtils.getInstant().getCompressedBitmap(getRealPathFromURI(AddPostActivity.this,pickedImgUri),40);
//                imageView.setVisibility(View.VISIBLE);
//                imageView.setImageBitmap(hdBitmap);
//            }
//
//        } else if (requestCode == CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                pickedImgUri = result.getUri();
//
//                hdBitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri.getPath(),40);
//                imageView.setVisibility(View.VISIBLE);
//                imageView.setImageBitmap(hdBitmap);
//            }
//        }
//    }
//
//    public void pickImage() {
//
//        if (!Utils.hasPermissions(this, PERMISSIONS)) {
//            ActivityCompat.requestPermissions(this, PERMISSIONS, STORAGE_REQUEST_CODE);
//        } else {
//            if (Build.VERSION.SDK_INT > 28) {
//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .start(AddPostActivity.this);
//            } else {
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
//                galleryIntent.setType("image/*");
//                startActivityForResult(galleryIntent, REQUESTCODE);
//            }
//        }
//
//       /* if (ContextCompat.checkSelfPermission(AddPostActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(AddPostActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                ActivityCompat.requestPermissions(AddPostActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
//            } else {
//                ActivityCompat.requestPermissions(AddPostActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
//            }
//
//        }*/
//
//    }
//
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
//    public String getRealPathFromURI(Activity context,Uri contentURI) {
//        String[] projection = { MediaStore.Images.Media.DATA };
//        @SuppressWarnings("deprecation")
//        Cursor cursor = context.managedQuery(contentURI, projection, null,
//                null, null);
//        if (cursor == null)
//            return null;
//        int column_index = cursor
//                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        if (cursor.moveToFirst()) {
//            String s = cursor.getString(column_index);
//            // cursor.close();
//            return s;
//        }
//        // cursor.close();
//        return null;
//    }
//
//    private void initList() {
//        spinnerItems = new ArrayList<>();
//        spinnerItems.add(new SpinnerItem("Public", R.drawable.ic_public_black_24dp));
//        spinnerItems.add(new SpinnerItem("Friends", R.drawable.ic_group_black_24dp));
//        spinnerItems.add(new SpinnerItem("Only me", R.drawable.ic_person_black_24dp));
//    }
//
//    private void getUserInfo(String uID){
//        reference = FirebaseDatabase.getInstance().getReference("Users").child(uID);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                imageURL = dataSnapshot.child("imageThumbnail").getValue(String.class);
//                firstName = dataSnapshot.child("firstName").getValue(String.class);
//                lastName = dataSnapshot.child("lastName").getValue(String.class);
//
//                authorName.setText(firstName+" "+lastName);
//                if (!imageURL.equals("none")){
//                    Picasso.get().load(imageURL).into(authorImage);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                Toast.makeText(AddPostActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void givePostBonus(String authorId){
//
//        DatabaseReference pointRefrence  = FirebaseDatabase.getInstance().getReference().child("Users").child(authorId);
//
//        final Dialog dialog = new Dialog(AddPostActivity.this);
//        dialog.setContentView(R.layout.dialog_bonus_layout);
//        dialog.setCancelable(false);
//        dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        TextView collect = dialog.findViewById(R.id.refresh_dialog_bonus);
//        collect.setOnClickListener(view1 -> {
//            dialog.dismiss();
//            pointRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    if (count == 0){
//                        points = dataSnapshot.child("points").getValue(Integer.class);
//                        points += 1;
//                        pointRefrence.child("points").setValue(points).addOnSuccessListener(aVoid -> finish());
//                        count++;
//                        return;
//                    } else {
//                        return;
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    Toast.makeText(AddPostActivity.this, "Something went wrong, Failed to sent bonus points", Toast.LENGTH_SHORT).show();
//                }
//            });
//        });
//        dialog.show();
//    }
//
//    private void updatePost(String mPrivacy,String mDescription){
//        progressDialog.setTitle("Updating post");
//        progressDialog.setMessage("Please wait, while we are saving changes this post");
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.show();
//
//        date = System.currentTimeMillis();
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        hdBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] hqData = baos.toByteArray();
//
//        File imageFile;
//        try {
//            Uri imageUri = getImageUri(this,hdBitmap);
//            imageFile = FileUtils.uriToFile(this,imageUri);
//
//            RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, "post_images");
//
//            RequestBody imageFilePart = RequestBody.create(
//                    MediaType.parse("image/jpeg"),
//                    imageFile
//            );
//
//            MultipartBody.Part file = MultipartBody.Part.createFormData("imageFile", System.currentTimeMillis()+imageFile.getName(), imageFilePart);
//
//
//            Retrofit.Builder builder = new Retrofit.Builder()
//                    .baseUrl("https://ftp.starnotesocial.com/")
//                    .addConverterFactory(GsonConverterFactory.create());
//
//            Retrofit retrofit = builder.build();
//
//            ImageServerClient client = retrofit.create(ImageServerClient.class);
//
//            Call<ImageUpload> call = client.uploadImage(folderPathPart, file);
//
//            call.enqueue(new Callback<ImageUpload>() {
//                @Override
//                public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
//                    if (response.isSuccessful()) {
//                        imageUrlThumb = response.body().getDownloadUrlRes();
//                        databaseReference.child(I_postId).child("postDescription").setValue(mDescription);
//                        databaseReference.child(I_postId).child("postImage").setValue(downloadUrl);
//                        databaseReference.child(I_postId).child("postThumbnail").setValue(imageUrlThumb);
//                        databaseReference.child(I_postId).child("privacy").setValue(mPrivacy);
//
//                        progressDialog.dismiss();
//
//                        Toast.makeText(AddPostActivity.this, "Post updated successfully", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ImageUpload> call, Throwable t) {
//                    Toast.makeText(AddPostActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//       /* mImagePath = storageReference.child(date+UUID.randomUUID().toString()+"HQ.jpg");
//        mImagePath.putBytes(hqData).addOnSuccessListener(taskSnapshot -> mImagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//
//                downloadUrl = uri.toString();
//
//                Bitmap bitmap;
//
//                if (Build.VERSION.SDK_INT > 28){
//                    bitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri.getPath(), 30);
//                }else {
//                    bitmap = ImageUtils.getInstant().getCompressedBitmap(getRealPathFromURI(AddPostActivity.this, pickedImgUri), 30);
//                }
//
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] thumbData = baos.toByteArray();
//
//                srThumb = storageReference.child(date+UUID.randomUUID().toString()+"thumbnail.jpg");
//                srThumb.putBytes(thumbData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        srThumb.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//
//                                imageUrlThumb = uri.toString();
//                                databaseReference.child(I_postId).child("postDescription").setValue(mDescription);
//                                databaseReference.child(I_postId).child("postImage").setValue(downloadUrl);
//                                databaseReference.child(I_postId).child("postThumbnail").setValue(imageUrlThumb);
//                                databaseReference.child(I_postId).child("privacy").setValue(mPrivacy);
//
//                                progressDialog.dismiss();
//
//                                Toast.makeText(AddPostActivity.this, "Post updated successfully", Toast.LENGTH_SHORT).show();
//                                finish();
//
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                progressDialog.dismiss();
//                                Toast.makeText(getApplicationContext(),"Failed to publish post",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(),"Failed to publish post",Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        }).addOnFailureListener(e -> {
//            progressDialog.dismiss();
//            Toast.makeText(getApplicationContext(),"Failed to publish post",Toast.LENGTH_SHORT).show();
//        })).addOnFailureListener(e -> {
//            progressDialog.dismiss();
//            Toast.makeText(getApplicationContext(),"Failed to publish post",Toast.LENGTH_SHORT).show();
//        });*/
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case STORAGE_REQUEST_CODE:{
//                if (grantResults.length > 0){
//                    boolean storageRequestAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    if (storageRequestAccepted){
//                        pickImage();
//                    } else {
//                        Toast.makeText(this, "Storage permission required", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }
//
//    }
//
//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//
//
//}
