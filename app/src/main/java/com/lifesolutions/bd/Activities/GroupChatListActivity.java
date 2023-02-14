//package com.stardigiinternational.starnotee.Activities;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.stardigiinternational.starnotee.Adapters.GroupChatListAdapter;
//import com.stardigiinternational.starnotee.Helpers.FileUtils;
//import com.stardigiinternational.starnotee.Helpers.ImageUtils;
//import com.stardigiinternational.starnotee.ImageUpload.ImageServerClient;
//import com.stardigiinternational.starnotee.ImageUpload.ImageUpload;
//import com.stardigiinternational.starnotee.Models.GroupChat;
//import com.stardigiinternational.starnotee.Models.GroupParticipant;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.kotlinCode.ui.auth.LoginKtActivity;
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
//import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;
//
//public class GroupChatListActivity extends AppCompatActivity {
//
//    private static final int CAMERA_REQUEST_CODE = 100;
//    private static final int STORAGE_REQUEST_CODE = 101;
//
//    private static final int PICK_IMAGE_CAMERA_CODE = 200;
//    private static final int PICK_IMAGE_GALLERY_CODE = 300;
//
//    private String[] cameraPermission;
//    private String[] storagePermission;
//    private Uri imageUri = null;
//
//    GroupChatListAdapter mAdapter;
//    ArrayList<GroupChat> list;
//    LinearLayoutManager mLayoutManager;
//    FloatingActionButton addGroup;
//    String uID,imageUrlNormal;
//    RecyclerView recyclerView;
//    ProgressDialog progressDialog;
//    ProgressBar progressBar;
//    Toolbar toolbar;
//    DatabaseReference databaseReference;
//    StorageReference storageReference,srNormal;
//    CircleImageView groupImage;
//    private Bitmap bitmap;
//    FirebaseUser mUser;
//    FirebaseAuth auth;
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_group_chat_list);
//
//        toolbar = findViewById(R.id.toolbar_group_chat_list);
//        recyclerView = findViewById(R.id.recyclerView_group_chat_list);
//        progressBar = findViewById(R.id.progressbar_group_chat_list);
//        addGroup = findViewById(R.id.create_group_group_chat_list);
//        setSupportActionBar(toolbar);
//        setTitle("Chat Groups");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        auth = FirebaseAuth.getInstance();
//        mUser = auth.getCurrentUser();
//
//        if (mUser != null){
//            uID = mUser.getUid();
//        } else {
//            Intent intent = new Intent(GroupChatListActivity.this, LoginKtActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            GroupChatListActivity.this.finish();
//        }
//
//
//        list = new ArrayList<>();
//        databaseReference = FirebaseDatabase.getInstance().getReference("ChatGroups");
//        storageReference = FirebaseStorage.getInstance().getReference().child("GroupImages");
//
//        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setReverseLayout(true);
//        mLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setHasFixedSize(true);
//
//        cameraPermission = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//
//        progressDialog = new ProgressDialog(this);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                list.clear();
//
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    if (ds.child("Participants").child(auth.getUid()).exists()){
//                        GroupChat groupChat = ds.getValue(GroupChat.class);
//                        list.add(groupChat);
//                    }
//                }
//
//                if (list.size() == 0){
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(GroupChatListActivity.this, "No Active Groups", Toast.LENGTH_SHORT).show();
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                    mAdapter = new GroupChatListAdapter(GroupChatListActivity.this,list);
//                    recyclerView.setAdapter(mAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        addGroup.setOnClickListener(view -> {
//            ViewGroup viewGroup = findViewById(android.R.id.content);
//            View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_create_group_layout, viewGroup, false);
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setView(dialogView);
//            builder.setCancelable(false);
//            AlertDialog alertDialog = builder.create();
//            alertDialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            groupImage = dialogView.findViewById(R.id.photo_create_group_dialog);
//            EditText groupName = dialogView.findViewById(R.id.title_create_group_dialog);
//            EditText groupDescription = dialogView.findViewById(R.id.description_create_group_dialog);
//            Button close = dialogView.findViewById(R.id.cancel_create_group_dialog);
//            Button create = dialogView.findViewById(R.id.complete_create_group_dialog);
//            close.setOnClickListener(view1 -> alertDialog.dismiss());
//            groupImage.setOnClickListener(view2 -> selectImageFromGallery());
//            create.setOnClickListener(view3 -> {
//                if (TextUtils.isEmpty(groupName.getText().toString())){
//                    groupName.setError("This field can't be empty");
//                    groupName.requestFocus();
//                } else {
//                    alertDialog.dismiss();
//                    progressDialog.setMessage("Creating group");
//                    progressDialog.setCanceledOnTouchOutside(false);
//                    progressDialog.setCancelable(false);
//                    progressDialog.setIndeterminate(false);
//                    progressDialog.show();
//                    if (imageUri == null){
//                        createGroup(System.currentTimeMillis(),groupName.getText().toString(),groupDescription.getText().toString(),"none");
//                    } else {
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                        byte[] thumbData = baos.toByteArray();
//
//                        File imageFile;
//                        try {
//                            Uri imageUri = getImageUri(this,bitmap);
//                            imageFile = FileUtils.uriToFile(this,imageUri);
//
//                            RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, "group_images");
//
//                            RequestBody imageFilePart = RequestBody.create(
//                                    MediaType.parse("image/jpeg"),
//                                    imageFile
//                            );
//
//                            MultipartBody.Part file = MultipartBody.Part.createFormData("imageFile", System.currentTimeMillis()+imageFile.getName(), imageFilePart);
//
//
//                            Retrofit.Builder rBuilder = new Retrofit.Builder()
//                                    .baseUrl("https://ftp.starnotesocial.com/")
//                                    .addConverterFactory(GsonConverterFactory.create());
//
//                            Retrofit retrofit = rBuilder.build();
//
//                            ImageServerClient client = retrofit.create(ImageServerClient.class);
//
//                            Call<ImageUpload> call = client.uploadImage(folderPathPart, file);
//
//                            call.enqueue(new Callback<ImageUpload>() {
//                                @Override
//                                public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
//                                    if (response.isSuccessful()) {
//                                        imageUrlNormal = response.body().getDownloadUrlRes();
//                                        createGroup(System.currentTimeMillis(),groupName.getText().toString(),groupDescription.getText().toString(),imageUrlNormal);
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ImageUpload> call, Throwable t) {
//                                    Toast.makeText(GroupChatListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        /*srNormal = storageReference.child("Normal").child(System.currentTimeMillis()+groupName.getText().toString() + ".jpg");
//                        srNormal.putBytes(thumbData).addOnSuccessListener(taskSnapshot -> srNormal.getDownloadUrl().addOnSuccessListener(uri -> {
//                            imageUrlNormal = uri.toString();
//                            createGroup(System.currentTimeMillis(),groupName.getText().toString(),groupDescription.getText().toString(),imageUrlNormal);
//                        })).addOnFailureListener(e -> {
//                            progressDialog.dismiss();
//                            Toast.makeText(getApplicationContext(),"Failed to upload group image",Toast.LENGTH_SHORT).show();
//                        });*/
//                    }
//                }
//            });
//            alertDialog.show();
//        });
//    }
//
//    private void createGroup(long timestamp,String title,String description,String imageUrl) {
//        String groupId = String.valueOf(timestamp);
//
//        GroupChat groupChat = new GroupChat(title,description,imageUrl,groupId,uID,timestamp);
//
//        databaseReference.child(groupId).setValue(groupChat).addOnSuccessListener(aVoid -> {
//            GroupParticipant participant = new GroupParticipant(uID,"creator",timestamp);
//            databaseReference.child(groupId).child("Participants").child(uID).setValue(participant).addOnSuccessListener(aVoid1 -> {
//                progressDialog.dismiss();
//                Toast.makeText(GroupChatListActivity.this, "Group created successfully", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(e -> {
//                progressDialog.dismiss();
//                Toast.makeText(GroupChatListActivity.this, "Failed to crate group", Toast.LENGTH_SHORT).show();
//            });
//        }).addOnFailureListener(e -> {
//            progressDialog.dismiss();
//            Toast.makeText(GroupChatListActivity.this, "Failed To create group", Toast.LENGTH_SHORT).show();
//        });
//    }
//
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
//
//    private void searchGroup(String query){
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list.clear();
//                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                    if (dataSnapshot1.child("Participants").child(uID).exists()){
//                        if (dataSnapshot1.child("groupTitle").getValue().toString().toLowerCase().contains(query.toLowerCase())){
//                            GroupChat groupChat = dataSnapshot1.getValue(GroupChat.class);
//                            list.add(groupChat);
//                        }
//                    }
//                }
//
//                if (list.size() == 0){
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(GroupChatListActivity.this, "Empty", Toast.LENGTH_SHORT).show();
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                    mAdapter = new GroupChatListAdapter(GroupChatListActivity.this,list);
//                    recyclerView.setAdapter(mAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }
//
//    private void showImagePickDialog(){
//        String[] options = {"Camera","Gallery"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Choose an options: ").setItems(options, (dialogInterface, i) -> {
//            if (i==0){
//                if (!checkCameraPermission()){
//                    requestCameraPermission();
//                } else {
//                    pickImageFromCamera();
//                }
//            } else {
//                if (!checkStoragePermission()){
//                    requestStoragePermission();
//                } else {
//                    selectImageFromGallery();
//                }
//            }
//        }).show();
//    }
//
//    public void selectImageFromGallery() {
//        if (Build.VERSION.SDK_INT > 28){
//            CropImage.activity()
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .start(GroupChatListActivity.this);
//        } else {
//            Intent galleryIntent = new Intent(Intent.ACTION_PICK);
//            galleryIntent.setType("image/*");
//            startActivityForResult(galleryIntent,PICK_IMAGE_GALLERY_CODE);
//        }
//    }
//
//    private void pickImageFromCamera(){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.Images.Media.TITLE,"Group Image Icon Title");
//        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Group Image Icon Description");
//
//        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//        startActivityForResult(intent,PICK_IMAGE_CAMERA_CODE);
//    }
//
//    private boolean checkStoragePermission(){
//        return ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == (PackageManager.PERMISSION_GRANTED);
//    }
//
//    private void requestStoragePermission(){
//        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE);
//    }
//
//    private boolean checkCameraPermission(){
//        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
//                == (PackageManager.PERMISSION_GRANTED);
//
//        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == (PackageManager.PERMISSION_GRANTED);
//
//        return result && result1;
//    }
//
//    private void requestCameraPermission(){
//        ActivityCompat.requestPermissions(this,cameraPermission,CAMERA_REQUEST_CODE);
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode){
//            case CAMERA_REQUEST_CODE: {
//                if (grantResults.length > 0){
//                    boolean cameraRequestAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean storageRequestAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                    if (cameraRequestAccepted && storageRequestAccepted){
//                        pickImageFromCamera();
//                    } else {
//                        Toast.makeText(this, "Camera and Storage permissions are required", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//            break;
//            case STORAGE_REQUEST_CODE:{
//                if (grantResults.length > 0){
//                    boolean storageRequestAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    if (storageRequestAccepted){
//                        selectImageFromGallery();
//                    } else {
//                        Toast.makeText(this, "Storage permission required", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_GALLERY_CODE){
//            if (resultCode == RESULT_OK) {
//
//                imageUri = data.getData();
//
//                bitmap = ImageUtils.getInstant().getCompressedBitmap(getRealPathFromURI(imageUri,GroupChatListActivity.this),50);
//                groupImage.setImageBitmap(bitmap);
//            }
//
//        } else if (requestCode == CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                imageUri = result.getUri();
//
//                bitmap = ImageUtils.getInstant().getCompressedBitmap(imageUri.getPath(),30);
//                groupImage.setImageBitmap(bitmap);
//            }
//        }
//        /*if (resultCode == RESULT_OK){
//            if (requestCode == PICK_IMAGE_GALLERY_CODE){
//                imageUri = data.getData();
//                File imageThumb = new File(getRealPathFromURI(imageUri,
//                        this));
//                try {
//
//                    viewCompressedImage = new Compressor(GroupChatListActivity.this)
//                            .setMaxHeight(240)
//                            .setMaxWidth(240)
//                            .setQuality(1)
//                            .compressToBitmap(imageThumb);
//
//                    groupImage.setVisibility(View.VISIBLE);
//                    groupImage.setImageBitmap(viewCompressedImage);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            } else if (requestCode == PICK_IMAGE_CAMERA_CODE){
//
//                File imageThumb = new File(getRealPathFromURI(imageUri,
//                        this));
//                try {
//
//                    viewCompressedImage = new Compressor(GroupChatListActivity.this)
//                            .setMaxHeight(240)
//                            .setMaxWidth(240)
//                            .setQuality(1)
//                            .compressToBitmap(imageThumb);
//
//                    groupImage.setVisibility(View.VISIBLE);
//                    groupImage.setImageBitmap(viewCompressedImage);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }*/
//
//    }
//
//    public String getRealPathFromURI(Uri contentURI, Activity context) {
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
//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//}
