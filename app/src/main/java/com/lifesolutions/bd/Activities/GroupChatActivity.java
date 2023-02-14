//package com.stardigiinternational.starnotee.Activities;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.media.MediaRecorder;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Chronometer;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.snackbar.Snackbar;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Adapters.MessageAdapter;
//import com.stardigiinternational.starnotee.Helpers.FileUtils;
//import com.stardigiinternational.starnotee.Helpers.ImageUtils;
//import com.stardigiinternational.starnotee.Helpers.InternetCheck;
//import com.stardigiinternational.starnotee.Helpers.TimeAgo;
//import com.stardigiinternational.starnotee.ImageUpload.ImageServerClient;
//import com.stardigiinternational.starnotee.ImageUpload.ImageUpload;
//import com.stardigiinternational.starnotee.Models.Message;
//import com.stardigiinternational.starnotee.R;
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
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class GroupChatActivity extends AppCompatActivity {
//
//    RecyclerView recyclerView;
//    RelativeLayout groupItem;
//    Toolbar toolbar;
//    CircleImageView groupImage;
//    TextView tvGroupName;
//    EditText messageInput;
//    ImageView btnVoice,btnAttach;
//    ImageButton btnSend;
//    String groupId,groupName,groupImageUrl = "none";
//    DatabaseReference groupReference;
//    private static final int PReqCode = 2 ;
//    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
//    MessageAdapter mAdapter;
//    private MediaRecorder recorder;
//    private long date;
//    ProgressBar progressBar;
//    LinearLayoutManager mLayoutManager;
//    private StorageReference storageReference,msgImageRef,fileRef,audioRef;
//    Uri pickedImgUri;
//    private Bitmap compressedImageFile,compressedImageHD;
//    private static final int REQUESTCODE = 2 ;
//    private static final int reqCode = 2 ;
//    private Boolean mStart = true;
//    private ArrayList<Message> list;
//    private ProgressDialog progressDialog;
//    private String uID,fileName,strMessage,messageId,checker="",imageUrl,imageUrlHD;
//    DatabaseReference mReference,myRef,reference;
//    private static final String LOG_TAG = "RecordLog";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_group_chat);
//
//        recyclerView = findViewById(R.id.recyclerView_group_chat);
//        groupItem = findViewById(R.id.group_item_group_chat);
//        toolbar = findViewById(R.id.toolbar_group_chat);
//        groupImage = findViewById(R.id.group_picture_group_chat);
//        tvGroupName = findViewById(R.id.group_name_group_chat);
//        messageInput = findViewById(R.id.edit_text_group_chat);
//        btnVoice = findViewById(R.id.voice_message_group_chat);
//        btnSend = findViewById(R.id.send_button_group_chat);
//        btnAttach = findViewById(R.id.attach_file_group_chat);
//        progressBar = findViewById(R.id.progressbar_group_chat);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Intent myIntent = getIntent();
//        if (myIntent != null) {
//            groupId = myIntent.getStringExtra("groupId");
//        }
//
//        groupReference = FirebaseDatabase.getInstance().getReference("ChatGroups");
//        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setReverseLayout(false);
//        mLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(mLayoutManager);
//
//        storageReference = FirebaseStorage.getInstance().getReference().child("GroupImages");
//        fileRef = FirebaseStorage.getInstance().getReference().child("GroupFiles");
//        audioRef = FirebaseStorage.getInstance().getReference().child("GroupAudios");
//
//        list = new ArrayList<>();
//
//        progressDialog = new ProgressDialog(this);
//
//        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
//        uID = sharedPreferences.getString("uID",null);
//
//        mReference = FirebaseDatabase.getInstance().getReference().child("GroupMessages").child(groupId);
//        reference = FirebaseDatabase.getInstance().getReference().child("Users");
//
//
//        groupReference.child(groupId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                groupName = dataSnapshot.child("groupTitle").getValue(String.class);
//                groupImageUrl = dataSnapshot.child("groupIcon").getValue(String.class);
//
//                tvGroupName.setText(groupName);
////                if (!groupImageUrl.equals("none")){
////                    Picasso.get().load(groupImageUrl).into(groupImage);
////                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        showAllMessage();
//
//
//        groupItem.setOnClickListener(view -> {
//            startActivity(new Intent(GroupChatActivity.this,GroupViewActivity.class).putExtra("groupId",groupId));
//        });
//
//        btnSend.setOnClickListener(view -> {
//            if (messageInput.getText().toString().isEmpty()){
//                messageInput.setError("Please enter message here");
//                messageInput.requestFocus();
//            } else {
//                date = System.currentTimeMillis();
//                myRef = mReference.push();
//                messageId = myRef.getKey();
//                strMessage = messageInput.getText().toString();
//
//                Message message = new Message(uID,groupId,strMessage,messageId,"text","none",false,date);
//                myRef.setValue(message).addOnFailureListener(e -> Snackbar.make(GroupChatActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show());
//
//                messageInput.setText("");
//            }
//        });
//
//        btnVoice.setOnClickListener(view -> {
//            checker = "voice";
//            checkPermissionForAudioRecord();
//        });
//
//        btnAttach.setOnClickListener(view -> {
//            CharSequence options[] = new CharSequence[]{
//                    "Images",
//                    "PDF files",
//                    "DOCX files"
//            };
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(GroupChatActivity.this);
//            builder.setTitle("Select Option");
//            builder.setItems(options, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    if (i==0){
//                        checker = "image";
//                        pickImage();
//
//                    }else if (i == 1){
//                        checker = "pdf";
//                        pickPdfFile();
//                    }else if (i == 2){
//                        checker = "docx";
//                        pickWordFile();
//                    }
//                }
//            });
//            builder.show();
//        });
//
//    }
//
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
//
//    private void checkPermissionForAudioRecord(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//
//            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED){
//                String[] permissions = {Manifest.permission.RECORD_AUDIO};
//                requestPermissions(permissions,REQUEST_RECORD_AUDIO_PERMISSION);
//            } else {
//                recordVoiceMessage();
//            }
//        } else {
//            recordVoiceMessage();
//        }
//    }
//
//    private void recordVoiceMessage() {
//
//        fileName = getExternalCacheDir().getAbsolutePath();
//        fileName += "/star_note.3gp";
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.voice_record_dialog, null);
//        dialogBuilder.setView(dialogView);
//        Chronometer chronometer = dialogView.findViewById(R.id.chronometer_recorder_dialog);
//        ImageView recordButton = dialogView.findViewById(R.id.record_button_recorder_dialog);
//        recordButton.setOnClickListener(view -> {
//
//            if (mStart){
//                startRecording();
//                chronometer.setBase(SystemClock.elapsedRealtime());
//                chronometer.start();
//                recordButton.setImageResource(R.drawable.ic_recording_switch_start);
//                mStart = false;
//            }else {
//                stopRecording();
//                chronometer.stop();
//                recordButton.setImageResource(R.drawable.ic_recording_switch_stop);
//                mStart = true;
//            }
//        });
//        dialogBuilder.setPositiveButton("Send", (dialogInterface, i) -> {
//            if (!mStart){
//                stopRecording();
//                mStart = true;
//            }
//            sendVoiceMessage();
//
//        }).setNegativeButton("Cancel", (dialogInterface, i) -> {
//            if (!mStart){
//                stopRecording();
//                mStart = true;
//            }
//
//        });
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//        alertDialog.setCancelable(false);
//        alertDialog.show();
//
//    }
//
//    private void sendVoiceMessage() {
//
//        progressDialog.setTitle("Sending Voice Message");
//        progressDialog.setMessage("Please wait, while we are sending voice message for you");
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.show();
//
//
//        date = System.currentTimeMillis();
//        myRef = mReference.push();
//        messageId = myRef.getKey();
//
//        StorageReference filePath = fileRef.child(messageId+".3gp");
//
//        Uri uri = Uri.fromFile(new File(fileName));
//
//        filePath.putFile(uri).addOnSuccessListener(taskSnapshot -> filePath.getDownloadUrl().addOnSuccessListener(uri1 -> {
//
//            imageUrl = uri1.toString();
//
//            Message message = new Message(uID,groupId,imageUrl,messageId,checker,"none",false,date);
//            myRef.setValue(message).addOnSuccessListener(aVoid -> progressDialog.dismiss()).addOnFailureListener(e -> {
//                progressDialog.dismiss();
//                Snackbar.make(GroupChatActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//            });
//        })).addOnProgressListener(taskSnapshot -> {
//            double p = (100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//            progressDialog.setMessage((int) p + " % sent");
//        }).addOnFailureListener(e -> {
//
//        });
//    }
//
//    private void pickImage() {
//
//        if (ContextCompat.checkSelfPermission(GroupChatActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(GroupChatActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(GroupChatActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(GroupChatActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
//            }
//
//        } else {
//            Intent galleryIntent = new Intent(Intent.ACTION_PICK);
//            galleryIntent.setType("image/*");
//            startActivityForResult(galleryIntent,REQUESTCODE);
//        }
//    }
//
//    private void pickPdfFile(){
//
//        if (ContextCompat.checkSelfPermission(GroupChatActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(GroupChatActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(GroupChatActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(GroupChatActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
//            }
//
//        } else {
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.setType("application/pdf");
//            startActivityForResult(intent.createChooser(intent,"Select MS Word file"),REQUESTCODE);
//        }
//    }
//
//    private void pickWordFile(){
//
//        if (ContextCompat.checkSelfPermission(GroupChatActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(GroupChatActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(GroupChatActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(GroupChatActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
//            }
//
//        } else {
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.setType("application/msword");
//            startActivityForResult(intent.createChooser(intent,"Select MS Word file"),REQUESTCODE);
//        }
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
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//
//            if(checker.equals("image")){
//
//                pickedImgUri = data.getData();
//
//                progressDialog.setTitle("Sending image");
//                progressDialog.setMessage("Please wait, while we are sending image for you");
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.setCancelable(false);
//                progressDialog.setIndeterminate(false);
//                progressDialog.show();
//
//                date = System.currentTimeMillis();
//                myRef = mReference.push();
//                messageId = myRef.getKey();
//
//                Bitmap hdBitmab = ImageUtils.getInstant().getCompressedBitmap(getRealPathFromURI(pickedImgUri,GroupChatActivity.this),40);
//
//                File imageFile;
//                try {
//                    Uri imageUri = getImageUri(this,hdBitmab);
//                    imageFile = FileUtils.uriToFile(this,imageUri);
//
//                    RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, "message_images");
//
//                    RequestBody imageFilePart = RequestBody.create(
//                            MediaType.parse("image/jpeg"),
//                            imageFile
//                    );
//
//                    MultipartBody.Part file = MultipartBody.Part.createFormData("imageFile", date+imageFile.getName(), imageFilePart);
//
//
//                    Retrofit.Builder builder = new Retrofit.Builder()
//                            .baseUrl("https://ftp.starnotesocial.com/")
//                            .addConverterFactory(GsonConverterFactory.create());
//
//                    Retrofit retrofit = builder.build();
//
//                    ImageServerClient client = retrofit.create(ImageServerClient.class);
//
//                    retrofit2.Call<ImageUpload> call = client.uploadImage(folderPathPart, file);
//
//                    call.enqueue(new Callback<ImageUpload>() {
//                        @Override
//                        public void onResponse(retrofit2.Call<ImageUpload> call, Response<ImageUpload> response) {
//                            if (response.isSuccessful()) {
//                                imageUrl = response.body().getDownloadUrlRes();
//
//                                Message message = new Message(uID,groupId,imageUrl,messageId,checker,imageUrl,false,date);
//                                myRef.setValue(message).addOnSuccessListener(aVoid -> progressDialog.dismiss()).addOnFailureListener(e -> {
//                                    progressDialog.dismiss();
//                                    Snackbar.make(GroupChatActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//                                });
//
//                                messageInput.setText("");
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(retrofit2.Call<ImageUpload> call, Throwable t) {
//                            Toast.makeText(GroupChatActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                /*File thumb = new File(getRealPathFromURI(pickedImgUri,
//                        this));
//                try {
//
//                    compressedImageHD = new Compressor(GroupChatActivity.this)
//                            .setMaxHeight(480)
//                            .setMaxWidth(853)
//                            .setQuality(1)
//                            .compressToBitmap(thumb);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                compressedImageHD.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] thumbData = baos.toByteArray();
//
//                msgImageRef = storageReference.child(uID+groupId+date+ UUID.randomUUID().toString()+"HD.jpg");
//                msgImageRef.putBytes(thumbData).addOnSuccessListener(taskSnapshot -> msgImageRef.getDownloadUrl().addOnSuccessListener(uri -> {
//
//                    imageUrlHD = uri.toString();
//
//                    File thumb1 = new File(getRealPathFromURI(pickedImgUri,
//                            GroupChatActivity.this));
//                    try {
//
//                        compressedImageFile = new Compressor(GroupChatActivity.this)
//                                .setMaxHeight(100)
//                                .setMaxWidth(100)
//                                .setQuality(1)
//                                .compressToBitmap(thumb1);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
//                    compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
//                    byte[] thumbData1 = baos1.toByteArray();
//
//                    msgImageRef = storageReference.child(uID+groupId+date+UUID.randomUUID().toString()+"thumbnail.jpg");
//                    msgImageRef.putBytes(thumbData1).addOnSuccessListener(taskSnapshot1 -> msgImageRef.getDownloadUrl().addOnSuccessListener(uri1 -> {
//
//                        imageUrl = uri1.toString();
//
//                        Message message = new Message(uID,groupId,imageUrl,messageId,checker,imageUrlHD,false,date);
//                        myRef.setValue(message).addOnSuccessListener(aVoid -> progressDialog.dismiss()).addOnFailureListener(e -> {
//                            progressDialog.dismiss();
//                            Snackbar.make(GroupChatActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//                        });
//
//                        messageInput.setText("");
//
//                    }).addOnFailureListener(e -> {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(),"Could not sent image",Toast.LENGTH_SHORT).show();
//                    })).addOnFailureListener(e -> {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(),"Could not sent image",Toast.LENGTH_SHORT).show();
//                    });
//
//                }).addOnFailureListener(e -> {
//                    progressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(),"Could not sent image",Toast.LENGTH_SHORT).show();
//                })).addOnProgressListener(taskSnapshot -> {
//                    double p = (100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progressDialog.setMessage((int) p + " % uploaded");
//                }).addOnFailureListener(e -> {
//                    progressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(),"Could not sent image",Toast.LENGTH_SHORT).show();
//                });*/
//
//
//            }
//            else {
//                pickedImgUri = data.getData();
//
//                progressDialog.setTitle("Sending file");
//                progressDialog.setMessage("Please wait, while we are sending file for you");
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.setCancelable(false);
//                progressDialog.setIndeterminate(false);
//                progressDialog.show();
//
//                date = System.currentTimeMillis();
//                myRef = mReference.push();
//                messageId = myRef.getKey();
//
//                StorageReference filePath = fileRef.child(messageId+"."+checker);
//
//                filePath.putFile(pickedImgUri).addOnCompleteListener(task -> {
//                    if (task.isSuccessful()){
//                        filePath.getDownloadUrl().addOnSuccessListener(uri -> {
//                            imageUrl = uri.toString();
//
//                            Message message = new Message(uID,groupId,imageUrl,messageId,checker,"none",false,date);
//                            myRef.setValue(message).addOnSuccessListener(aVoid -> progressDialog.dismiss()).addOnFailureListener(e -> {
//                                progressDialog.dismiss();
//                                Snackbar.make(GroupChatActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//                            });
//
//                            messageInput.setText("");
//                        }).addOnFailureListener(e -> {
//
//                        });
//                    }
//                }).addOnProgressListener(taskSnapshot -> {
//                    double p = (100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progressDialog.setMessage((int) p + " % uploaded");
//                }).addOnFailureListener(e -> {
//
//                });
//
//            }
//        }
//
//
//    }
//
//    private String getDateTime(long timeInMilli){
//        long cDate = System.currentTimeMillis();
//        String time = TimeAgo.toDuration(cDate-timeInMilli);
//        return time;
//    }
//
//    private void showAllMessage(){
//
//        if (InternetCheck.checkConnection(this)) {
//
//            //
//            mReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    list.clear();
//
//                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
//                    {
//                        Message message = dataSnapshot1.getValue(Message.class);
//                        if (message.getReceiverId().equals(groupId)){
//                            list.add(message);
//                        }
//
//                    }
//                    if (list.size() == 0){
//                        progressBar.setVisibility(View.GONE);
//
//                    } else {
//
//                        mAdapter = new MessageAdapter(GroupChatActivity.this,list);
//                        recyclerView.setAdapter(mAdapter);
//                        progressBar.setVisibility(View.GONE);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    progressBar.setVisibility(View.GONE);
//                    Snackbar.make(GroupChatActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//                }
//            });
//
//        } else {
//
//            progressBar.setVisibility(View.GONE);
//            Snackbar.make(GroupChatActivity.this.getWindow().getDecorView().getRootView(), "No Internet connection", Snackbar.LENGTH_LONG).show();
//
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                recordVoiceMessage();
//            } else {
//                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void startRecording() {
//        recorder = new MediaRecorder();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        recorder.setOutputFile(fileName);
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//        try {
//            recorder.prepare();
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "prepare() failed");
//        }
//
//        recorder.start();
//    }
//
//    private void stopRecording() {
//        recorder.stop();
//        recorder.release();
//        recorder = null;
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//}
