//package com.stardigiinternational.starnotee.Activities;
//
//import android.Manifest;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.material.appbar.AppBarLayout;
//import com.google.android.material.appbar.CollapsingToolbarLayout;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Adapters.GroupParticipantListAdapter;
//import com.stardigiinternational.starnotee.Helpers.FileUtils;
//import com.stardigiinternational.starnotee.Helpers.ImageUtils;
//import com.stardigiinternational.starnotee.Helpers.InternetCheck;
//import com.stardigiinternational.starnotee.ImageUpload.ImageServerClient;
//import com.stardigiinternational.starnotee.ImageUpload.ImageUpload;
//import com.stardigiinternational.starnotee.Models.User;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.kotlinCode.ui.auth.LoginKtActivity;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
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
//public class GroupViewActivity extends AppCompatActivity {
//
//    AppBarLayout appBarLayout;
//    ImageView groupImage;
//    TextView groupName,addMember;
//    private ArrayList<String> list;
//    ArrayList<String> friendUId;
//    private ArrayList<User> mUsers,friendsList;
//    Toolbar toolbar;
//    RecyclerView recyclerView;
//    CollapsingToolbarLayout collapsingToolbarLayout;
//    GroupParticipantListAdapter mAdapter;
//    private String groupID,stGroupTitle,stGroupDescription,stGroupImage,uID,imageUrl;
//    DatabaseReference reference,mReference,friendReference;
//    LinearLayoutManager mLayoutManager;
//    FirebaseUser mUser;
//    private static final int PReqCode = 2 ;
//    Bitmap groupImageBitmap;
//    Uri pickedImgUri;
//    StorageReference storageReference,srNormal;
//    ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_group_view);
//
//        appBarLayout = findViewById(R.id.app_bar_layout_group_view);
//        groupImage = findViewById(R.id.group_image_group_view);
//        groupName = findViewById(R.id.group_name_group_view);
//        addMember = findViewById(R.id.add_participants_view_group);
//        toolbar = findViewById(R.id.toolbar_view_group);
//        recyclerView = findViewById(R.id.recyclerview_view_group);
//
//        setSupportActionBar(toolbar);
//        collapsingToolbarLayout = findViewById(R.id.collapsing_layout_group_view);
//        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
//
//        mUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (mUser != null){
//            uID = mUser.getUid();
//        } else {
//            Intent intent = new Intent(GroupViewActivity.this, LoginKtActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            GroupViewActivity.this.finish();
//        }
//
//        setTitle("");
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Intent myIntent = getIntent();
//        if (myIntent != null){
//            groupID = myIntent.getStringExtra("groupId");
//        }
//
//        list = new ArrayList<>();
//        mUsers = new ArrayList<>();
//        friendUId = new ArrayList<>();
//        friendsList = new ArrayList<>();
//        progressDialog = new ProgressDialog(this);
//
//        reference = FirebaseDatabase.getInstance().getReference("ChatGroups").child(groupID);
//        mReference = FirebaseDatabase.getInstance().getReference("Users");
//        friendReference = FirebaseDatabase.getInstance().getReference("friendRequest").child(uID);
//        storageReference = FirebaseStorage.getInstance().getReference().child("GroupImages");
//
//        mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new GroupParticipantListAdapter(GroupViewActivity.this,mUsers,groupID);
//        recyclerView.setAdapter(mAdapter);
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                stGroupTitle = dataSnapshot.child("groupTitle").getValue(String.class);
//                stGroupDescription = dataSnapshot.child("groupDescription").getValue(String.class);
//                stGroupImage = dataSnapshot.child("groupIcon").getValue(String.class);
//                setTitle(stGroupTitle);
//
//                groupName.setText(stGroupTitle);
//                if(!stGroupImage.equals("none")){
//                    Picasso.get().load(stGroupImage).into(groupImage);
//                }
//                String role = dataSnapshot.child("Participants").child(uID).child("role").getValue(String.class);
//
//                if (role.equals("creator") || role.equals("admin")){
//                    addMember.setVisibility(View.VISIBLE);
//                    mAdapter.setAdmin(true);
//                } else {
//                    mAdapter.setAdmin(false);
//                    addMember.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//        reference.child("Participants").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list.clear();
//
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    list.add(dataSnapshot1.getKey());
//                }
//
//                getParticipantDetails(list);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//        addMember.setOnClickListener(view -> startActivity(new Intent(GroupViewActivity.this,AddGroupMemberActivity.class).putExtra("groupId",groupID)));
//
//    }
//
//    private void selectImage() {
//
//        if (ContextCompat.checkSelfPermission(GroupViewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(GroupViewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(GroupViewActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(GroupViewActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
//            }
//
//        } else {
//            CropImage.activity()
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .start(GroupViewActivity.this);
//        }
//    }
//
//
//    private void getParticipantDetails(List<String> list) {
//
//        mReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                mUsers.clear();
//
//                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                    for (String participant : list){
//                        if (participant.equals(dataSnapshot1.getKey())){
//                            User user = dataSnapshot1.getValue(User.class);
//                            mUsers.add(user);
//                        }
//                    }
//
//                }
//
//                mAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == CROP_IMAGE_ACTIVITY_REQUEST_CODE){
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                pickedImgUri = result.getUri();
//
//                groupImageBitmap = ImageUtils.getInstant().getCompressedBitmap(pickedImgUri.getPath(), 20);
//
//                final Dialog dialog = new Dialog(GroupViewActivity.this);
//                dialog.setContentView(R.layout.image_upload_dialog);
//                dialog.setCancelable(false);
//                dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                ImageView imageViewD = dialog.findViewById(R.id.cover_image_image_upload_dialog);
//                ImageView imageViewP = dialog.findViewById(R.id.profile_image_image_upload_dialog);
//                imageViewP.setVisibility(View.GONE);
//                imageViewD.setVisibility(View.VISIBLE);
//                TextView ok_btn = dialog.findViewById(R.id.upload_button_image_dialog);
//                TextView cancel_btn = dialog.findViewById(R.id.quit_button_image_dialog);
//                imageViewD.setImageBitmap(groupImageBitmap);
//                ok_btn.setOnClickListener(v -> {
//                    if (InternetCheck.checkConnection(this)){
//                        uploadGroupPhoto();
//                        dialog.dismiss();
//                    }else {
//                        Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                cancel_btn.setOnClickListener(view -> {
//                    dialog.dismiss();
//                });
//                dialog.show();
//
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//            }
//        }
//    }
//
//    private void uploadGroupPhoto() {
//        progressDialog.setMessage("Updating image");
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.show();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        groupImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] thumbData = baos.toByteArray();
//
//        File imageFile;
//        try {
//            Uri imageUri = getImageUri(this,groupImageBitmap);
//            imageFile = FileUtils.uriToFile(this,imageUri);
//
//            RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, "group_images");
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
//                        imageUrl = response.body().getDownloadUrlRes();
//
//                        reference.child("groupIcon").setValue(imageUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                progressDialog.dismiss();
//                                Toast.makeText(GroupViewActivity.this, "Group image uploaded successfully", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ImageUpload> call, Throwable t) {
//                    Toast.makeText(GroupViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        /*srNormal = storageReference.child("Normal").child(System.currentTimeMillis()+groupName.getText().toString() + ".jpg");
//        srNormal.putBytes(thumbData).addOnSuccessListener(taskSnapshot -> srNormal.getDownloadUrl().addOnSuccessListener(uri -> {
//            imageUrl = uri.toString();
//            reference.child("groupIcon").setValue(imageUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    progressDialog.dismiss();
//                    Toast.makeText(GroupViewActivity.this, "Group image uploaded successfully", Toast.LENGTH_SHORT).show();
//                }
//            });
//        })).addOnFailureListener(e -> {
//            progressDialog.dismiss();
//            Toast.makeText(getApplicationContext(),"Failed to upload group image",Toast.LENGTH_SHORT).show();
//        });*/
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_camera,menu);
//        MenuItem item  = menu.findItem(R.id.action_camera);
//        item.setOnMenuItemClickListener(menuItem -> {
//            selectImage();
//            return true;
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//
//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//}
