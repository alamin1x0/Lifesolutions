//package com.stardigiinternational.starnotee.Activities;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
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
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Chronometer;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.gmail.samehadar.iosdialog.IOSDialog;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.material.snackbar.Snackbar;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ServerValue;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.sinch.android.rtc.SinchError;
//import com.sinch.android.rtc.calling.Call;
//import com.sinch.gson.Gson;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Adapters.MessageAdapter;
//import com.stardigiinternational.starnotee.Helpers.FileUtils;
//import com.stardigiinternational.starnotee.Helpers.ImageUtils;
//import com.stardigiinternational.starnotee.Helpers.InternetCheck;
//import com.stardigiinternational.starnotee.Helpers.TimeAgo;
//import com.stardigiinternational.starnotee.ImageUpload.ImageServerClient;
//import com.stardigiinternational.starnotee.ImageUpload.ImageUpload;
//import com.stardigiinternational.starnotee.Models.GroupChat;
//import com.stardigiinternational.starnotee.Models.LastConversation;
//import com.stardigiinternational.starnotee.Models.Message;
//import com.stardigiinternational.starnotee.Models.User;
//import com.stardigiinternational.starnotee.Notifications.Data;
//import com.stardigiinternational.starnotee.Notifications.Sender;
//import com.stardigiinternational.starnotee.Notifications.Token;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.Services.SinchService;
//import com.stardigiinternational.starnotee.kotlinCode.data.model.CallLog;
//import com.stardigiinternational.starnotee.kotlinCode.data.model.Conversation;
//import com.stardigiinternational.starnotee.kotlinCode.ui.auth.LoginKtActivity;
//import com.stardigiinternational.starnotee.kotlinCode.utils.Utils;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
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
//
//
//public class MessageActivity extends BaseActivity implements SinchService.StartFailedListener {
//    private static String TAG = "MessageActivity";
//
//    Toolbar toolbar;
//    private static final int PReqCode = 2 ;
//    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 1,PERMISSION_AUDIO_CALL = 102;
//    TextView tvName,tvStatus;
//    CircleImageView profileImage;
//    ImageView moreButton,voiceButton,sendButton,photoButton;
//    EditText messageField;
//    MessageAdapter mAdapter;
//    private MediaRecorder recorder;
//    private long date;
//    private RecyclerView recyclerView;
//    LinearLayoutManager mLayoutManager;
//    RelativeLayout profileView;
//    private StorageReference storageReference,msgImageRef,fileRef;
//    IOSDialog dialog1;
//    Uri pickedImgUri;
//    final int ITEM_LOAD_COUNT = 5;
//    int total_item = 0,last_visible_item,pageCount = 1;
//    private static final int REQUESTCODE = 2 ;
//    private Boolean mStart = true;
//    private ArrayList<Message> list;
//    private String receiverID,uID,receiverName,fileName,receiverImage = "none",strMessage,firstName,lastName,messageId,address,checker="",imageUrl,imageUrlHD,typingStatus = "noOne";
//    DatabaseReference uReference,myRef,reference,rootReference,mReference;
//    private static final String LOG_TAG = "RecordLog";
//    private boolean notify = false,isLoading = false;
//    private RequestQueue requestQueue;
//    String lastMessageSender = null,lastMessageReceiver = null;
//    private ProgressBar progressBar;
//    FirebaseAuth mAuth;
//    private String[] PERMISSIONS = {
//            android.Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.RECORD_AUDIO
//    };
//    private int countSender = 0,countReceiver = 0;
//
//    private Boolean userBlockMe = false;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message);
//
//        Log.w(TAG, "onCreate");
//
//        toolbar = findViewById(R.id.toolbar_message);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        tvName = findViewById(R.id.name_message);
//        tvStatus = findViewById(R.id.status_message);
//        profileImage = findViewById(R.id.profile_picture_message);
//        sendButton = findViewById(R.id.send_button_message);
//        moreButton = findViewById(R.id.attach_file_message);
//        photoButton = findViewById(R.id.picture_message_message);
//        messageField = findViewById(R.id.edit_text_message);
//
//        profileView = findViewById(R.id.users_item_message);
//        voiceButton = findViewById(R.id.voice_message_message);
//        progressBar = findViewById(R.id.progressbar_message);
//
//        recyclerView = findViewById(R.id.recyclerView_message);
//
//        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setReverseLayout(false);
//        mLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(mLayoutManager);
//
//        storageReference = FirebaseStorage.getInstance().getReference().child("MessageImages");
//        fileRef = FirebaseStorage.getInstance().getReference().child("MessageFiles");
//        fileRef = FirebaseStorage.getInstance().getReference().child("MessageAudios");
//
//        requestQueue = Volley.newRequestQueue(getApplicationContext());
//        progressBar.setMax(100);
//
//        dialog1 = new IOSDialog.Builder(MessageActivity.this)
//                .setDimAmount(3)
//                .setSpinnerColorRes(R.color.white)
//                .setMessageColorRes(R.color.white)
//                .setTitleColorRes(R.color.colorPrimary)
//                .setMessageContent("Please Wait")
//                .setCancelable(true)
//                .setMessageContentGravity(Gravity.CENTER)
//                .build();
//
//        list = new ArrayList<>();
//
//        Intent myIntent = getIntent();
//        if (myIntent != null) {
//            receiverID = myIntent.getStringExtra("receiverID");
//        }
//
//        mAuth = FirebaseAuth.getInstance();
//        if(mAuth != null){
//            uID = mAuth.getUid();
//        } else {
//            Intent intent = new Intent(MessageActivity.this, LoginKtActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            MessageActivity.this.finish();
//        }
//
//        rootReference = FirebaseDatabase.getInstance().getReference();
//        mReference = FirebaseDatabase.getInstance().getReference("Messages").child(uID).child(receiverID);
//        reference = FirebaseDatabase.getInstance().getReference().child("Users");
//        uReference = FirebaseDatabase.getInstance().getReference().child("Users").child(receiverID);
//
//        uReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                firstName = dataSnapshot.child("firstName").getValue(String.class);
//                lastName = dataSnapshot.child("lastName").getValue(String.class);
//                receiverImage = dataSnapshot.child("imageThumbnail").getValue(String.class);
//                typingStatus = dataSnapshot.child("imageThumbnail").getValue(String.class);
//
//                receiverName = firstName+" "+lastName;
//                tvName.setText(receiverName);
//
//                if (!receiverImage.equals("none")){
//                    Picasso.get().load(receiverImage).into(profileImage);
//                } else {
//                    profileImage.setImageResource(R.drawable.user_low);
//                }
//
//                if (dataSnapshot.child("userActivity").exists()){
//                    if (dataSnapshot.child("userActivity").child("online").getValue(Boolean.class)){
//                        tvStatus.setText("Online");
//                    } else {
//                        long dateTime = dataSnapshot.child("userActivity").child("lastOnline").getValue(Long.class);
//                        tvStatus.setText("last seen "+getDateTime(dateTime));
//                    }
//                } else {
//                    tvStatus.setText("Offline");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(MessageActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        checkBlockByUser();
//
//        showAllMessage();
//
//        messageField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.length() > 0){
//                    moreButton.setVisibility(View.GONE);
//                    photoButton.setVisibility(View.GONE);
//                    voiceButton.setVisibility(View.GONE);
//                } else {
//                    moreButton.setVisibility(View.VISIBLE);
//                    photoButton.setVisibility(View.VISIBLE);
//                    voiceButton.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        sendButton.setOnClickListener(view -> {
//
//            if (messageField.getText().toString().isEmpty()){
//                messageField.setError("Please enter message here");
//                messageField.requestFocus();
//            } else {
//                notify = true;
//                countSender = 0;
//                countReceiver = 0;
//                date = System.currentTimeMillis();
//
//                String senderReference = "Messages/"+uID+"/"+receiverID;
//                String receiverReference = "Messages/"+receiverID+"/"+uID;
//
//                myRef = rootReference.child("Messages").child(uID).child(receiverID).push();
//                messageId = myRef.getKey();
//                strMessage = messageField.getText().toString();
//
//                Message message = new Message(uID,receiverID,strMessage,messageId,"text","none",false,date);
//                rootReference.child(senderReference).child(messageId).setValue(message).addOnFailureListener(e -> Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show());
//                rootReference.child(receiverReference).child(messageId).setValue(message).addOnFailureListener(e -> Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show());
//
//                messageField.setText("");
//
//                /*
//                  Create Conversations
//                 */
//                SharedPreferences userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
//                String cFirstName = userPreferences.getString("firstName", "");
//                String cLastName = userPreferences.getString("lastName", "");
//                String cImageThumbnail = userPreferences.getString("imageThumbnail", null);
//
//                String fullReceiverName = firstName;
//                String fullSenderName = cFirstName;
//                if (!lastName.trim().equals("")) {
//                    fullReceiverName = firstName + " " + lastName;
//                }
//
//                if (!cLastName.equals("")) {
//                    fullSenderName = cFirstName + " " + cLastName;
//                }
//                // Sender...
//                Conversation senderConversation = new Conversation(receiverID, fullReceiverName, receiverImage, strMessage, "text", ServerValue.TIMESTAMP, false , false);
//                // Receiver...
//                Conversation receiverConversation = new Conversation(uID, fullSenderName, cImageThumbnail, strMessage, "text", ServerValue.TIMESTAMP, false, false);
//
//                DatabaseReference ref = rootReference.child("StarnoteConversation").child(uID).child(receiverID);
//                DatabaseReference ref2 = rootReference.child("StarnoteConversation").child(receiverID).child(uID);
//
//                ref.setValue(senderConversation);
//                ref2.setValue(receiverConversation);
//
//                reference.child(uID).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        User user = dataSnapshot.getValue(User.class);
//                        if (notify){
//                            Log.w(TAG, "Notify");
//                            sendNotification(receiverID,user.getFirstName()+" "+user.getLastName(),strMessage);
//                        }
//
//                        notify = false;
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });
//
//        voiceButton.setOnClickListener(view -> {
//            //Toast.makeText(this, "This feature is unavailable for your device", Toast.LENGTH_SHORT).show();
//             checker = "voice"; //
//             checkPermissionForAudioRecord();
//        });
//
//        photoButton.setOnClickListener(view -> {
//            checker = "image";
//            pickImage();
//        });
//
//        moreButton.setOnClickListener(view -> {
//            CharSequence[] options = new CharSequence[]{
//                    "PDF files",
//                    "DOCX files"
//            };
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
//            builder.setTitle("Select Option");
//            builder.setItems(options, (dialogInterface, i) -> {
//                if (i == 0){
//                    checker = "pdf";
//                    pickPdfFile();
//                }else if (i == 1){
//                    checker = "docx";
//                    pickWordFile();
//                }
//            });
//            builder.show();
//        });
//
//        profileView.setOnClickListener(view -> startActivity(new Intent(MessageActivity.this,UserProfileActivity.class).putExtra("uID",receiverID)));
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                total_item = mLayoutManager.getItemCount();
//                last_visible_item = mLayoutManager.findLastVisibleItemPosition();
//
//                if (!isLoading && total_item <= ((last_visible_item + ITEM_LOAD_COUNT))){
//                    pageCount++;
//                    showAllMessage();
//                    isLoading = true;
//                }
//            }
//        });
//
//    }
//
//    private void sendNotification(String receiverID, String firstName, String message) {
//        Log.w(TAG, "sendNotification(): " + "Iam Here");
//        DatabaseReference allTokens = FirebaseDatabase.getInstance().getReference("CloudTokens");
//        Query query = allTokens.orderByKey().equalTo(receiverID);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                    Token token = dataSnapshot1.getValue(Token.class);
//                    Log.w(TAG, "token" + token.getToken());
//                    Data data = new Data(uID,firstName+" : "+message,"New message",receiverID,"message","none",R.drawable.app_icon);
//
//                    Sender sender = new Sender(data,token.getToken());
//
//                    try {
//                        JSONObject senderJsonObject = new JSONObject(new Gson().toJson(sender));
//                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://fcm.googleapis.com/fcm/send", senderJsonObject,
//                                response -> Log.d(TAG,"onResponse"+response.toString()), error -> Log.d("JSON_RESPONSE","onResponse"+error.toString())){
//                            @Override
//                            public Map<String, String> getHeaders() {
//                                Map<String,String> headers = new HashMap<>();
//                                headers.put("Content-Type","application/json");
//                                headers.put("Authorization","key=AAAAH3KuD3I:APA91bEn9xaE0KLfvVsLHxudo_SR3lRsDI4K15Mu-I0BWxQNTPvHzTG4kKofAm979uXiwvZL6UtL5zIwcErSxOTKS3QDZ5UmBWYS4W5SK0iDLLimdvqQ8bgneAkTr1jMWAVBM0qhscc6");
//                                // headers.put("Authorization","key=AAAAHQM4oAg:APA91bG8vdBeI3UxkkH2srgxVHTA6iAuUJyf-V0NdF5v6pAv7SliDhxlt8EJVs_CmbwH7-NFHUyEitAKLP7c8CUH1yIPzCqTZH23BPmNRMRQ_wacBuxG4IqX1NOR__bQ2R4j2kV_XIo3");
//
//                                return headers;
//                            }
//                        };
//
//                        requestQueue.add(jsonObjectRequest);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
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
//        fileName = getExternalCacheDir().getAbsolutePath(); //
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
//        progressBar.setVisibility(View.VISIBLE);
//        notify = true;
//
//        date = System.currentTimeMillis();
//        String senderReference = "Messages/"+uID+"/"+receiverID;
//        String receiverReference = "Messages/"+receiverID+"/"+uID;
//
//        myRef = rootReference.child("Messages").child(uID).child(receiverID).push();
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
//            Message message = new Message(uID,receiverID,imageUrl,messageId,checker,"none",false,date);
//            rootReference.child(senderReference).child(messageId).setValue(message).addOnFailureListener(e -> Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show());
//            rootReference.child(receiverReference).child(messageId).setValue(message).addOnSuccessListener(aVoid -> {
//                progressBar.setVisibility(View.GONE);
//
//
//              /*
//              Create Conversations
//              */
//                SharedPreferences userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
//                String cFirstName = userPreferences.getString("firstName", "");
//                String cLastName = userPreferences.getString("lastName", "");
//                String cImageThumbnail = userPreferences.getString("imageThumbnail", null);
//
//                String fullReceiverName = firstName;
//                String fullSenderName = cFirstName;
//                if (!lastName.trim().equals("")) {
//                    fullReceiverName = firstName + " " + lastName;
//                }
//
//                if (!cLastName.equals("")) {
//                    fullSenderName = cFirstName + " " + cLastName;
//                }
//                // Sender...
//                Conversation senderConversation = new Conversation(receiverID, fullReceiverName, receiverImage, "Voice Message", "voice", ServerValue.TIMESTAMP, false, false);
//                // Receiver...
//                Conversation receiverConversation = new Conversation(uID, fullSenderName, cImageThumbnail, "Voice Message", "voice", ServerValue.TIMESTAMP, false, false);
//
//                DatabaseReference ref = rootReference.child("StarnoteConversation").child(uID).child(receiverID);
//                DatabaseReference ref2 = rootReference.child("StarnoteConversation").child(receiverID).child(uID);
//
//                ref.setValue(senderConversation);
//                ref2.setValue(receiverConversation);
//
//                reference.child(uID).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        User user = dataSnapshot.getValue(User.class);
//                        if (notify){
//                            sendNotification(receiverID,user.getFirstName()+" "+user.getLastName(),"Voice message");
//                        }
//
//                        notify = false;
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }).addOnFailureListener(e -> {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(MessageActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        })).addOnProgressListener(taskSnapshot -> {
//            int p = (int) (100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//            progressBar.setProgress(p);
//        }).addOnFailureListener(e -> {
//            progressBar.setVisibility(View.GONE);
//            Toast.makeText(MessageActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//        });
//    }
//
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_call,menu);
//        MenuItem item  = menu.findItem(R.id.action_audio_call);
//        MenuItem item1 = menu.findItem(R.id.action_video_call);
//        MenuItem itemBlock = menu.findItem(R.id.action_user_block);
//        MenuItem itemUserInfo = menu.findItem(R.id.action_user_info);
//        MenuItem itemUserBlock= menu.findItem(R.id.action_user_unblock);
//
//        item.setOnMenuItemClickListener(menuItem -> {
//
//            if (!Utils.hasPermissions(this, PERMISSIONS)) {
//                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_AUDIO_CALL);
//            } else {
//                if (userBlockMe) {
//                    Toast.makeText(MessageActivity.this, "You are BLOCK by this user.", Toast.LENGTH_SHORT).show();
//                } else {
//                    callUserAudio();
//                }
//            }
//            return true;
//        });
//
//        item1.setOnMenuItemClickListener(menuItem -> {
//            if (userBlockMe) {
//                Toast.makeText(MessageActivity.this, "You are BLOCK by this user.", Toast.LENGTH_SHORT).show();
//            } else {
//                callUserVideo();
//            }
//            return  true;
//        });
//
//        itemBlock.setOnMenuItemClickListener(MenuItem -> {
//            Toast.makeText(this, "Block User", Toast.LENGTH_SHORT).show();
//            blockAnUser();
//            return true;
//        });
//
//        itemUserInfo.setOnMenuItemClickListener(MenuItem -> {
//            if (receiverID.equals(uID)) {
//                startActivity(new Intent(this, ProfileActivity.class));
//            } else {
//                startActivity(new Intent(this, UserProfileActivity.class).putExtra("uID", receiverID));
//            }
//            return true;
//        });
//
//        itemUserBlock.setOnMenuItemClickListener(menuItem -> {
//            unBlockAnUser();
//            return true;
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void blockAnUser() {
//        HashMap<String, String> userData = new HashMap<String, String>();
//        userData.put("uid", receiverID);
//        userData.put("firstName", firstName);
//        userData.put("lastName", lastName);
//        userData.put("profileImage", receiverImage);
//        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("BlackList").child(uID);
//        dbRef.child(receiverID).setValue(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(MessageActivity.this, "Blocked! This user add to your blacklist.", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
//    }
//
//    private void unBlockAnUser() {
//        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("BlackList").child(uID);
//        dbRef.child(receiverID).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(MessageActivity.this, "Success! Successfully unblock this user.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void callUserAudio() {
//
//        if (getSinchServiceInterface().isStarted()){
//            FirebaseDatabase.getInstance().getReference().child("Users").child(receiverID).child("userActivity").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()){
//                        if (dataSnapshot.child("online").getValue(Boolean.class)){
//                            Map<String,String> headers = new HashMap<>();
//                            headers.put("Content-Type","application/json");
//                            headers.put("Authorization","key=AAAAH3KuD3I:APA91bEn9xaE0KLfvVsLHxudo_SR3lRsDI4K15Mu-I0BWxQNTPvHzTG4kKofAm979uXiwvZL6UtL5zIwcErSxOTKS3QDZ5UmBWYS4W5SK0iDLLimdvqQ8bgneAkTr1jMWAVBM0qhscc6");
//
//                            Call call = getSinchServiceInterface().callUserAudio(receiverID,headers);
//                            String callId = call.getCallId();
//
//                            Intent callScreen = new Intent(MessageActivity.this, CallScreenActivity.class);
//                            callScreen.putExtra(SinchService.CALL_ID, callId);
//                            startActivity(callScreen);
//
//                            /*
//                            Create Conversations
//                            */
//                            SharedPreferences userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
//                            String cFirstName = userPreferences.getString("firstName", "");
//                            String cLastName = userPreferences.getString("lastName", "");
//                            String cImageThumbnail = userPreferences.getString("imageThumbnail", null);
//
//                            String fullReceiverName = firstName;
//                            String fullSenderName = cFirstName;
//                            if (!lastName.trim().equals("")) {
//                                fullReceiverName = firstName + " " + lastName;
//                            }
//
//                            if (!cLastName.equals("")) {
//                                fullSenderName = cFirstName + " " + cLastName;
//                            }
//
//                            DatabaseReference ref = rootReference.child("StarnoteCallLog").child(uID);
//                            DatabaseReference ref2 = rootReference.child("StarnoteCallLog").child(receiverID);
//
//                            DatabaseReference pushRef = ref.push();
//                            // Sender...
//                            CallLog callerLog = new CallLog(pushRef.getKey(), receiverID, fullReceiverName, receiverImage, "voice", "outgoing", 0, ServerValue.TIMESTAMP);
//                            ref.child(pushRef.getKey()).setValue(callerLog);
//
//                            DatabaseReference pushRef2 = ref2.push();
//                            CallLog receiverLog = new CallLog(pushRef.getKey(), uID, fullSenderName, cImageThumbnail, "voice", "incoming", 0, ServerValue.TIMESTAMP);
//                            ref2.child(pushRef2.getKey()).setValue(receiverLog);
//
//                        } else {
//                            Toast.makeText(MessageActivity.this, "This user is offline", Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        Toast.makeText(MessageActivity.this, "This user is offline", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(MessageActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            Toast.makeText(this, "Just a moment", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void callUserVideo() {
//        if (getSinchServiceInterface().isStarted()){
//            FirebaseDatabase.getInstance().getReference().child("Users").child(receiverID).child("userActivity").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()){
//                        if (dataSnapshot.child("online").getValue(Boolean.class)){
//                            Call call = getSinchServiceInterface().callUserVideo(receiverID);
//                            String callId = call.getCallId();
//
//                            Intent VideoCallScreen = new Intent(MessageActivity.this, VideoCallScreenActivity.class);
//                            VideoCallScreen.putExtra(SinchService.CALL_ID, callId);
//                            startActivity(VideoCallScreen);
//
//                                /*
//                                Create Conversations
//                                */
//                            SharedPreferences userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
//                            String cFirstName = userPreferences.getString("firstName", "");
//                            String cLastName = userPreferences.getString("lastName", "");
//                            String cImageThumbnail = userPreferences.getString("imageThumbnail", null);
//
//                            String fullReceiverName = firstName;
//                            String fullSenderName = cFirstName;
//                            if (!lastName.trim().equals("")) {
//                                fullReceiverName = firstName + " " + lastName;
//                            }
//
//                            if (!cLastName.equals("")) {
//                                fullSenderName = cFirstName + " " + cLastName;
//                            }
//
//                            DatabaseReference ref = rootReference.child("StarnoteCallLog").child(uID);
//                            DatabaseReference ref2 = rootReference.child("StarnoteCallLog").child(receiverID);
//
//                            DatabaseReference pushRef = ref.push();
//                            // Sender...
//                            CallLog callerLog = new CallLog(pushRef.getKey(), receiverID, fullReceiverName, receiverImage, "video", "outgoing", 0, ServerValue.TIMESTAMP);
//                            ref.child(pushRef.getKey()).setValue(callerLog);
//
//                            DatabaseReference pushRef2 = ref2.push();
//                            CallLog receiverLog = new CallLog(pushRef.getKey(), uID, fullSenderName, cImageThumbnail, "video", "incoming", 0, ServerValue.TIMESTAMP);
//                            ref2.child(pushRef2.getKey()).setValue(receiverLog);
//
//                        } else {
//                            Toast.makeText(MessageActivity.this, "This user is offline", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(MessageActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            Toast.makeText(this, "Just a moment", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void pickImage() {
//
//        if (ContextCompat.checkSelfPermission(MessageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MessageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(MessageActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(MessageActivity.this,
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
//        if (ContextCompat.checkSelfPermission(MessageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MessageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(MessageActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(MessageActivity.this,
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
//        if (ContextCompat.checkSelfPermission(MessageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MessageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(MessageActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//            } else {
//                ActivityCompat.requestPermissions(MessageActivity.this,
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
//                notify = true;
//
//                progressBar.setVisibility(View.VISIBLE);
//
//                date = System.currentTimeMillis();
//                String senderReference = "Messages/"+uID+"/"+receiverID;
//                String receiverReference = "Messages/"+receiverID+"/"+uID;
//
//                myRef = rootReference.child("Messages").child(uID).child(receiverID).push();
//                messageId = myRef.getKey();
//
//                Bitmap hdBitmab = ImageUtils.getInstant().getCompressedBitmap(getRealPathFromURI(pickedImgUri,MessageActivity.this),40);
//
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                hdBitmab.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] hqData = baos.toByteArray();
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
//                                Message message = new Message(uID,receiverID,imageUrl,messageId,checker,imageUrl,false,date);
//                                rootReference.child(senderReference).child(messageId).setValue(message).addOnFailureListener(e -> Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show());
//                                rootReference.child(receiverReference).child(messageId).setValue(message).addOnSuccessListener(aVoid -> {
//                                    progressBar.setVisibility(View.GONE);
//
//
//                                    /*
//                                      Create Conversations
//                                     */
//                                    SharedPreferences userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
//                                    String cFirstName = userPreferences.getString("firstName", "");
//                                    String cLastName = userPreferences.getString("lastName", "");
//                                    String cImageThumbnail = userPreferences.getString("imageThumbnail", null);
//
//                                    String fullReceiverName = firstName;
//                                    String fullSenderName = cFirstName;
//                                    if (!lastName.trim().equals("")) {
//                                        fullReceiverName = firstName + " " + lastName;
//                                    }
//
//                                    if (!cLastName.equals("")) {
//                                        fullSenderName = cFirstName + " " + cLastName;
//                                    }
//                                    // Sender...
//                                    Conversation senderConversation = new Conversation(receiverID, fullReceiverName, receiverImage, "Image", "image", ServerValue.TIMESTAMP, false, false);
//                                    // Receiver...
//                                    Conversation receiverConversation = new Conversation(uID, fullSenderName, cImageThumbnail, "Image", "image", ServerValue.TIMESTAMP, false, false);
//
//                                    DatabaseReference ref = rootReference.child("StarnoteConversation").child(uID).child(receiverID);
//                                    DatabaseReference ref2 = rootReference.child("StarnoteConversation").child(receiverID).child(uID);
//
//                                    ref.setValue(senderConversation);
//                                    ref2.setValue(receiverConversation);
//
//                                    reference.child(uID).addValueEventListener(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                            User user = dataSnapshot.getValue(User.class);
//                                            if (notify){
//                                                sendNotification(receiverID,user.getFirstName()+" "+user.getLastName(),"Picture");
//                                            }
//
//                                            notify = false;
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                        }
//                                    });
//                                }).addOnFailureListener(e -> {
//                                    progressBar.setVisibility(View.GONE);
//                                    Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//                                });
//
//                                messageField.setText("");
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(retrofit2.Call<ImageUpload> call, Throwable t) {
//                            Toast.makeText(MessageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//               /* msgImageRef = storageReference.child(uID+receiverID+date+UUID.randomUUID().toString()+"HD.jpg");
//                msgImageRef.putBytes(hqData).addOnSuccessListener(taskSnapshot -> msgImageRef.getDownloadUrl().addOnSuccessListener(uri -> {
//
//                    imageUrlHD = uri.toString();
//
//                    Bitmap bitmap = ImageUtils.getInstant().getCompressedBitmap(getRealPathFromURI(pickedImgUri,MessageActivity.this),20);
//
//                    ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
//                    byte[] thumbData = baos1.toByteArray();
//
//                    msgImageRef = storageReference.child(uID+receiverID+date+UUID.randomUUID().toString()+"thumbnail.jpg");
//                    msgImageRef.putBytes(thumbData).addOnSuccessListener(taskSnapshot1 -> msgImageRef.getDownloadUrl().addOnSuccessListener(uri1 -> {
//
//                        imageUrl = uri1.toString();
//
//                        Message message = new Message(uID,receiverID,imageUrl,messageId,checker,imageUrlHD,false,date);
//                        rootReference.child(senderReference).child(messageId).setValue(message).addOnFailureListener(e -> Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show());
//                        rootReference.child(receiverReference).child(messageId).setValue(message).addOnSuccessListener(aVoid -> {
//                            progressBar.setVisibility(View.GONE);
//                            reference.child(uID).addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                    User user = dataSnapshot.getValue(User.class);
//                                    if (notify){
//                                        sendNotification(receiverID,user.getFirstName()+" "+user.getLastName(),"Picture");
//                                    }
//
//                                    notify = false;
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                }
//                            });
//                        }).addOnFailureListener(e -> {
//                            progressBar.setVisibility(View.GONE);
//                            Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//                        });
//
//                        messageField.setText("");
//
//                    }).addOnFailureListener(e -> {
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(getApplicationContext(),"Could not sent image",Toast.LENGTH_SHORT).show();
//                    })).addOnFailureListener(e -> {
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(getApplicationContext(),"Could not sent image",Toast.LENGTH_SHORT).show();
//                    });
//
//                }).addOnFailureListener(e -> {
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(MessageActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                })).addOnProgressListener(taskSnapshot -> {
//                    int p = (int) (100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progressBar.setProgress(p);
//                }).addOnFailureListener(e -> {
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(MessageActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                });*/
//
//
//            } else {
//                pickedImgUri = data.getData();
//
//                progressBar.setVisibility(View.VISIBLE);
//
//                date = System.currentTimeMillis();
//                String senderReference = "Messages/"+uID+"/"+receiverID;
//                String receiverReference = "Messages/"+receiverID+"/"+uID;
//
//                myRef = rootReference.child("Messages").child(uID).child(receiverID).push();
//                messageId = myRef.getKey();
//
//                notify = true;
//
//                StorageReference filePath = fileRef.child(messageId+"."+checker);
//
//                filePath.putFile(pickedImgUri).addOnCompleteListener(task -> {
//                    if (task.isSuccessful()){
//                        filePath.getDownloadUrl().addOnSuccessListener(uri -> {
//                            imageUrl = uri.toString();
//
//                            Message message = new Message(uID,receiverID,imageUrl,messageId,checker,"none",false,date);
//                            rootReference.child(senderReference).child(messageId).setValue(message).addOnFailureListener(e -> Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show());
//                            rootReference.child(receiverReference).child(messageId).setValue(message).addOnSuccessListener(aVoid -> {
//                                progressBar.setVisibility(View.GONE);
//
//
//                               /*
//                               Create Conversations
//                               */
//                                SharedPreferences userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
//                                String cFirstName = userPreferences.getString("firstName", "");
//                                String cLastName = userPreferences.getString("lastName", "");
//                                String cImageThumbnail = userPreferences.getString("imageThumbnail", null);
//
//                                String fullReceiverName = firstName;
//                                String fullSenderName = cFirstName;
//                                if (!lastName.trim().equals("")) {
//                                    fullReceiverName = firstName + " " + lastName;
//                                }
//
//                                if (!cLastName.equals("")) {
//                                    fullSenderName = cFirstName + " " + cLastName;
//                                }
//
//                                // Sender...
//                                Conversation senderConversation = new Conversation(receiverID, fullReceiverName, receiverImage, "File", "file", ServerValue.TIMESTAMP,false, false);
//                                // Receiver...
//                                Conversation receiverConversation = new Conversation(uID, fullSenderName, cImageThumbnail, "File", "file", ServerValue.TIMESTAMP,false, false);
//
//                                DatabaseReference ref = rootReference.child("StarnoteConversation").child(uID).child(receiverID);
//                                DatabaseReference ref2 = rootReference.child("StarnoteConversation").child(receiverID).child(uID);
//
//                                ref.setValue(senderConversation);
//                                ref2.setValue(receiverConversation);
//
//                                reference.child(uID).addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        User user = dataSnapshot.getValue(User.class);
//                                        if (notify){
//                                            sendNotification(receiverID,user.getFirstName()+" "+user.getLastName(),"File");
//                                        }
//
//                                        notify = false;
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//                            }).addOnFailureListener(e -> {
//                                progressBar.setVisibility(View.GONE);
//                                Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//                            });
//
//                            messageField.setText("");
//                        }).addOnFailureListener(e -> {
//                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(MessageActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                        });
//                    }
//                }).addOnProgressListener(taskSnapshot -> {
//                    int p = (int) (100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progressBar.setProgress(p);
//                }).addOnFailureListener(e -> {
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//            }
//        }
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
//        if (InternetCheck.checkConnection(this)) {
//            try {
//                dialog1 = new IOSDialog.Builder(MessageActivity.this)
//                        .setDimAmount(3)
//                        .setSpinnerColorRes(R.color.white)
//                        .setMessageColorRes(R.color.white)
//                        .setTitleColorRes(R.color.colorPrimary)
//                        .setMessageContent("Please Wait")
//                        .setCancelable(true)
//                        .setMessageContentGravity(Gravity.CENTER)
//                        .build();
//                dialog1.show();
//            } catch (Exception e){
//
//            }
//            mReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    list.clear();
//
//                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
//                    {
//                        Message message = dataSnapshot1.getValue(Message.class);
//                        list.add(message);
//
//                    }
//                    if (list == null){
//
//                        Toast.makeText(MessageActivity.this, "Empty", Toast.LENGTH_SHORT).show();
//
//                    } else {
//
//                        mAdapter = new MessageAdapter(MessageActivity.this,list);
//                        recyclerView.setAdapter(mAdapter);
//                        dialog1.dismiss();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    dialog1.dismiss();
//                    Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "Something went wrong", Snackbar.LENGTH_LONG).show();
//                }
//            });
//
//        } else {
//
//            dialog1.dismiss();
//            Snackbar.make(MessageActivity.this.getWindow().getDecorView().getRootView(), "No Internet connection", Snackbar.LENGTH_LONG).show();
//
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode){
//            case REQUEST_RECORD_AUDIO_PERMISSION:{
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    recordVoiceMessage();
//                } else {
//                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            case PERMISSION_AUDIO_CALL:{
//                if (grantResults.length > 0) {
//                    boolean callRequestAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean recordRequestAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                    if (callRequestAccepted && recordRequestAccepted) {
//                        callUserAudio();
//                    } else {
//                        Toast.makeText(this, "Phone call and Record audio permissions are required", Toast.LENGTH_SHORT).show();
//                    }
//                }
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
//    @Override
//    public void onStartFailed(SinchError error) {
//        Toast.makeText(this, ""+error.getMessage(), Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onStarted() {
//
//    }
//    @Override
//    protected void onServiceConnected() {
//
//        getSinchServiceInterface().setStartListener(this);
//
//        //Register user
//        if (getSinchServiceInterface() != null && !getSinchServiceInterface().isStarted()) {
//            getSinchServiceInterface().startClient(uID);
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
//    private void checkBlockByUser() {
//        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("BlackList");
//        dbRef.child(receiverID).child(uID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    userBlockMe = true;
//                    Toast.makeText(MessageActivity.this, "You are BLOCK by this user.", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.d(TAG, "onCancelled: Something Wrong");
//            }
//        });
//    }
//
//}
