//package com.stardigiinternational.starnotee.Activities;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.ActivityOptions;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.TextUtils;
//import android.util.Pair;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.widget.Toolbar;
//import androidx.cardview.widget.CardView;
//import androidx.core.app.ActivityCompat;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.facebook.login.LoginManager;
//import com.facebook.shimmer.ShimmerFrameLayout;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.sinch.android.rtc.PushTokenRegistrationCallback;
//import com.sinch.android.rtc.SinchError;
//import com.squareup.picasso.Picasso;
//import com.stardigiinternational.starnotee.Adapters.PostListAdapter;
//import com.stardigiinternational.starnotee.Helpers.InternetCheck;
//import com.stardigiinternational.starnotee.Models.NotificationInApp;
//import com.stardigiinternational.starnotee.Models.PostItem;
//import com.stardigiinternational.starnotee.Notifications.Token;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.Services.SinchService;
//import com.stardigiinternational.starnotee.Services.TempService;
//import com.stardigiinternational.starnotee.kotlinCode.KotlinBaseActivity;
//import com.stardigiinternational.starnotee.kotlinCode.ui.auth.LoginKtActivity;
//import com.stardigiinternational.starnotee.kotlinCode.ui.user.EditProfileInfoKtActivity;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, SinchService.StartFailedListener, PushTokenRegistrationCallback, PostListAdapter.PostItemClickListener {
//
//    final int ITEM_LOAD_COUNT = 5;
//    int total_item = 0,last_visible_item;
//    String last_key = "";
//    private Boolean isLoading = false;
//    Toolbar toolbar;
//    private PostListAdapter mAdapter;
//    DatabaseReference mReference,reference;
//    private String uID;
//    SwipeRefreshLayout refreshLayout;
//    ImageButton search;
//    private String firstName,lastName;
//    CircleImageView navPImage;
//    TextView nameNav,updateProfileNav;
//    Dialog dialog;
//    private int count = 0;
//    TextView refresh;
//    private RecyclerView recyclerView;
//    private boolean check = false,isReceived = false;
//    LinearLayoutManager mLayoutManager;
//    ImageView btnRandomCall,btnMessenger,btnGame,btnQuiz,btnVideo,btnNews,btnTranslate,btnEcommerce;
//    CircleImageView imageView,imageViewSecond;
//    LinearLayout write_post;
//    ShimmerFrameLayout shimmerFrameLayout;
//    ImageButton translator;
//    ImageView btnNotification;
//    boolean doubleBackToExitPressedOnce = false,mPushTokenIsRegistered = false;
//    FirebaseAuth mAuth;
//    TextView tvNotificationCount;
//    CardView badge;
//
//    int PERMISSION_ALL = 1,PERMISSION_CHECK = 2,PERMISSION_MESSENGER = 103;
//    private String[] PERMISSIONS = {
//            android.Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//    private String[] PERMISSIONS_VIDEO_CALL = {
//            android.Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.CAMERA,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//    private int notificationCount = 0;
//    private int pageCount = 1;
//    private FloatingActionButton upButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        toolbar = findViewById(R.id.toolbar_home);
//        //setSupportActionBar(toolbar);
//
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        toggle.setDrawerIndicatorEnabled(false);
//        toggle.setHomeAsUpIndicator(R.drawable.ic_hamburger_final);
//
//        toggle.setToolbarNavigationClickListener(view -> {
//            if (drawer.isDrawerOpen(GravityCompat.START)) {
//                drawer.closeDrawer(GravityCompat.START);
//            } else {
//                drawer.openDrawer(GravityCompat.START);
//            }
//        });
//
//        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setItemIconTintList(null);
//        View header = navigationView.getHeaderView(0);
//
//        recyclerView = findViewById(R.id.recyclerView_home);
//        imageView = findViewById(R.id.profile_image_home);
//        imageViewSecond = findViewById(R.id.user_profile_image_kt);
//        write_post = findViewById(R.id.add_post_layout_home);
//        search = findViewById(R.id.search_button_home);
//        btnRandomCall = findViewById(R.id.random_call_home_item);
//        btnMessenger = findViewById(R.id.messenger_home_item);
//        btnGame = findViewById(R.id.game_home_item);
//        btnQuiz = findViewById(R.id.quiz_home_item);
//        translator = findViewById(R.id.translator_home);
//        refreshLayout = findViewById(R.id.swipe_refresh_layout_home);
//        navPImage = header.findViewById(R.id.profile_picture_nav_bar);
//        nameNav = header.findViewById(R.id.name_nav_bar);
//        updateProfileNav = header.findViewById(R.id.profile_update_nav_bar);
//        shimmerFrameLayout = findViewById(R.id.shimmer_layout_home);
//        btnNotification = findViewById(R.id.notification_button_home);
//        badge = findViewById(R.id.notification_badge);
//        btnVideo = findViewById(R.id.classroom_home_item);
//        btnNews = findViewById(R.id.news_home_item);
//        btnTranslate = findViewById(R.id.translate_home_item);
//        btnEcommerce = findViewById(R.id.ecommerce_home_item);
//        upButton = findViewById(R.id.floating_up_button_home);
//        tvNotificationCount = findViewById(R.id.toolbar_badge_text);
//
//        mAuth = FirebaseAuth.getInstance();
//        if(mAuth != null){
//            uID = mAuth.getUid();
//        } else {
//            Intent intent = new Intent(HomeActivity.this, LoginKtActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            HomeActivity.this.finish();
//        }
//
//        mLayoutManager = new LinearLayoutManager(this);
//        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uID);
//        mReference = FirebaseDatabase.getInstance().getReference().child("Posts");
//
//
//        recyclerView.setLayoutManager(mLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),mLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//        mAdapter = new PostListAdapter(this,this);
//        recyclerView.setAdapter(mAdapter);
//
//        refreshLayout.setEnabled(false);
//        refreshLayout.setRefreshing(true);
//
//
//        dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.error_dialog_layout);
//        dialog.setCancelable(false);
//        dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        refresh = dialog.findViewById(R.id.refresh_dialog);
//
//        checkUpdateVersion();
//        getUserInfo();
//        showNotificationBadges(uID);
//        getPosts();
//
//        if (!hasPermissions(this, PERMISSIONS_VIDEO_CALL)) {
//            ActivityCompat.requestPermissions(this, PERMISSIONS_VIDEO_CALL, PERMISSION_CHECK);
//        }
//
//        imageView.setOnClickListener(view -> {
//            if (isReceived){
//                if (check){
//                    count ++;
//                    startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
//                }else {
//                    final Dialog dialog = new Dialog(HomeActivity.this);
//                    dialog.setContentView(R.layout.dialog_layout_warning);
//                    dialog.setCancelable(false);
//                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    TextView ok_btn = dialog.findViewById(R.id.save_dialog_warning);
//                    TextView cancel_btn = dialog.findViewById(R.id.quit_dialog_warning);
//                    dialog.show();
//                    ok_btn.setOnClickListener(view1 -> startActivity(new Intent(HomeActivity.this, EditProfileInfoKtActivity.class)));
//                    cancel_btn.setOnClickListener(view12 -> dialog.dismiss());
//                }
//            }else {
//                Toast.makeText(this, "Poor internet connection", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        updateProfileNav.setOnClickListener(view -> {
//            if (isReceived){
//                if (check){
//                    count ++;
//                    startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
//                }else {
//                    final Dialog dialog = new Dialog(HomeActivity.this);
//                    dialog.setContentView(R.layout.dialog_layout_warning);
//                    dialog.setCancelable(false);
//                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    TextView ok_btn = dialog.findViewById(R.id.save_dialog_warning);
//                    TextView cancel_btn = dialog.findViewById(R.id.quit_dialog_warning);
//                    dialog.show();
//                    ok_btn.setOnClickListener(view1 -> {
//                        startActivity(new Intent(HomeActivity.this,EditProfileInfoKtActivity.class));
//                    });
//                    cancel_btn.setOnClickListener(view12 -> dialog.dismiss());
//                }
//            } else {
//                Toast.makeText(this, "Poor internet connection", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        write_post.setOnClickListener(view -> {
//            if (isReceived){
//                if (check){
//                    startActivity(new Intent(HomeActivity.this,AddPostActivity.class));
//                }else {
//                    final Dialog dialog = new Dialog(HomeActivity.this);
//                    dialog.setContentView(R.layout.dialog_layout_warning);
//                    dialog.setCancelable(false);
//                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    TextView ok_btn = dialog.findViewById(R.id.save_dialog_warning);
//                    TextView cancel_btn = dialog.findViewById(R.id.quit_dialog_warning);
//                    dialog.show();
//                    ok_btn.setOnClickListener(view1 -> {
//                        startActivity(new Intent(HomeActivity.this,EditProfileInfoKtActivity.class));
//                    });
//                    cancel_btn.setOnClickListener(view12 -> dialog.dismiss());
//                }
//            }else {
//                Toast.makeText(this, "Poor internet connection", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        btnGame.setOnClickListener(view -> {
//            if (Build.VERSION.SDK_INT > 24){
//                startActivity(new Intent(HomeActivity.this,OnlineGameActivity.class));
//            } else {
//                Toast.makeText(this, "This feature is not supported on your device. its available soon for your device", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnNews.setOnClickListener(view -> {
//            if (Build.VERSION.SDK_INT > 24){
//                startActivity(new Intent(HomeActivity.this,NewsActivity.class));
//            } else {
//                Toast.makeText(this, "This feature is not supported on your device. its available soon for your device", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnEcommerce.setOnClickListener(view -> {
//            if (Build.VERSION.SDK_INT > 24){
//                startActivity(new Intent(HomeActivity.this,EcommerceActivity.class));
//            } else {
//                Toast.makeText(this, "This feature is not supported on your device. its available soon for your device", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnVideo.setOnClickListener(view -> {
//            if (Build.VERSION.SDK_INT > 24){
//                startActivity(new Intent(HomeActivity.this,QuizActivity.class));
//            } else {
//                Toast.makeText(this, "This feature is not supported on your device. its available soon for your device", Toast.LENGTH_SHORT).show();
//            }
//        });
//        btnTranslate.setOnClickListener(view -> {
//            if (Build.VERSION.SDK_INT > 24){
//                startActivity(new Intent(HomeActivity.this,TranslateActivity.class));
//            } else {
//                Toast.makeText(this, "This feature is not supported on your device. its available soon for your device", Toast.LENGTH_SHORT).show();
//            }
//        });
//        search.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this,SearchUsersActivity.class)));
//
//        translator.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.google.com/"))));
//
//        btnQuiz.setOnClickListener(view -> {
//
//            if (isReceived){
//
//                if (check){
//                    startActivity(new Intent(HomeActivity.this,LeaderBoardActivity.class));
//                }else {
//                    final Dialog dialog = new Dialog(HomeActivity.this);
//                    dialog.setContentView(R.layout.dialog_layout_warning);
//                    dialog.setCancelable(false);
//                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    TextView ok_btn = dialog.findViewById(R.id.save_dialog_warning);
//                    TextView cancel_btn = dialog.findViewById(R.id.quit_dialog_warning);
//                    dialog.show();
//                    ok_btn.setOnClickListener(view1 -> {
//                        startActivity(new Intent(HomeActivity.this,EditProfileInfoKtActivity.class));
//                    });
//                    cancel_btn.setOnClickListener(view12 -> dialog.dismiss());
//                }
//            }else {
//                Toast.makeText(this, "Poor internet connection", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnRandomCall.setOnClickListener(view -> {
//
//            if (!hasPermissions(this, PERMISSIONS)) {
//                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
//            } else {
//                startActivity(new Intent(HomeActivity.this,RandomCallActivity.class));
//            }
//        });
//
//        btnNotification.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this,NotificationActivity.class)));
//
//        updateToken(FirebaseInstanceId.getInstance().getToken());
//        showNotificationBadges(uID);
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @SuppressLint("RestrictedApi")
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                boolean enabled = mLayoutManager.findFirstVisibleItemPosition() == 0;
//                refreshLayout.setEnabled(enabled);
//                if (!enabled){
//                    upButton.setVisibility(View.VISIBLE);
//                }else {
//                    upButton.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                total_item = mLayoutManager.getItemCount();
//                last_visible_item = mLayoutManager.findLastVisibleItemPosition();
//
//                if (!isLoading && total_item <= ((last_visible_item + ITEM_LOAD_COUNT))){
//                    pageCount++;
//                    refreshLayout.setEnabled(true);
//                    refreshLayout.setRefreshing(true);
//                    getPosts();
//                    isLoading = true;
//                }
//            }
//        });
//
//        refreshLayout.setOnRefreshListener(() -> {
//            pageCount = 1;
//            mAdapter.removeAllItem();
//            mAdapter.notifyDataSetChanged();
//            shimmerFrameLayout.startShimmer();
//            shimmerFrameLayout.setVisibility(View.VISIBLE);
//            getPosts();
//        });
//
//        upButton.setOnClickListener(view -> recyclerView.scrollToPosition(0));
//
//
//    }
//
//    private void getPosts() {
//        if (InternetCheck.checkConnection(this)){
//            refreshLayout.setRefreshing(true);
//            Query postReference = mReference.limitToLast(ITEM_LOAD_COUNT * pageCount);
//            postReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.hasChildren()){
//                        ArrayList<PostItem> postItems = new ArrayList<>();
//                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//                            postItems.add(postSnapshot.getValue(PostItem.class));
//                        }
//                        Collections.reverse(postItems);
//                        mAdapter.removeAllItem();
//                        mAdapter.addAllPosts(postItems);
//                        mAdapter.notifyDataSetChanged();
//                        refreshLayout.setRefreshing(false);
//                        shimmerFrameLayout.stopShimmer();
//                        shimmerFrameLayout.setVisibility(View.GONE);
//                        recyclerView.setVisibility(View.VISIBLE);
//                        isLoading = false;
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    refreshLayout.setRefreshing(false);
//                }
//            });
//        } else {
//            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void getLastKeyFromFirebase(){
//        Query queryLastKey = FirebaseDatabase.getInstance().getReference()
//                .child("Posts")
//                .orderByKey()
//                .limitToLast(1);
//
//        queryLastKey.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot lastKey : dataSnapshot.getChildren()){
//                    last_key = lastKey.getKey();
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
//    private void getUserInfo(){
//
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                isReceived = true;
//
//                String imageUrl = "none";
//                imageUrl = dataSnapshot.child("imageThumbnail").getValue(String.class);
//                if (dataSnapshot.child("firstName").exists() && dataSnapshot.child("lastName").exists() && count == 0){
//                   check = true;
//                    firstName = dataSnapshot.child("firstName").getValue(String.class);
//                    lastName = dataSnapshot.child("lastName").getValue(String.class);
//                    nameNav.setText(firstName+" "+lastName);
//                }
//
//                if (imageUrl != null && !imageUrl.equals("none")){
//
//                    Picasso.get().load(imageUrl).into(imageView);
//                    Picasso.get().load(imageUrl).into(imageViewSecond);
//                    Picasso.get().load(imageUrl).into(navPImage);
//                }
//
////                if (!dataSnapshot.child("permission").getValue(Boolean.class)){
////                    FirebaseAuth.getInstance().signOut();
////                    LoginManager.getInstance().logOut();
////                    Toast.makeText(HomeActivity.this, "You are temporary blocked for unusual activity", Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
////                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                    startActivity(intent);
////                    HomeActivity.this.finish();
////                }
//
//                if (!TextUtils.isEmpty(firstName) || !TextUtils.isEmpty(lastName)){
//                    if (!dataSnapshot.child("searchName").exists()){
//                        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)){
//                            reference.child("searchName").setValue(firstName.toLowerCase()+" "+lastName.toLowerCase());
//                        } else if (!TextUtils.isEmpty(firstName)){
//                            reference.child("searchName").setValue(firstName.toLowerCase());
//                        }
//                    }
//                }
//
//                /*if (!dataSnapshot.child("randomCallUsed").exists() && isShow){
//                    isShow = false;
//                    Dialog randomCallDialog =  new Dialog(HomeActivity.this);
//                    randomCallDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                    randomCallDialog.setContentView(R.layout.error_dialog_layout);
//                    randomCallDialog.setCancelable(true);
//                    randomCallDialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                    randomCallDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    TextView btn = randomCallDialog.findViewById(R.id.refresh_dialog);
//                    TextView title = randomCallDialog.findViewById(R.id.title_dialog);
//                    ImageView image_dialog = randomCallDialog.findViewById(R.id.image_dialog);
//                    TextView description_dialog = randomCallDialog.findViewById(R.id.description_dialog);
//
//                    title.setVisibility(View.GONE);
//                    image_dialog.setImageResource(R.drawable.ic_group_black_24dp);
//                    image_dialog.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.colorMain), android.graphics.PorterDuff.Mode.SRC_IN);
//                    description_dialog.setText("Our application have special features practice english with random person");
//                    btn.setText("Try it");
//                    btn.setBackgroundColor(getResources().getColor(R.color.colorMain));
//                    btn.setOnClickListener(view ->{
//                        randomCallDialog.dismiss();
//                        if (!hasPermissions(HomeActivity.this, PERMISSIONS)) {
//
//                            ActivityCompat.requestPermissions(HomeActivity.this, PERMISSIONS, PERMISSION_ALL);
//
//                        } else {
//
//                            startActivity(new Intent(HomeActivity.this,RandomCallActivity.class));
//                        }
//                    });
//                    randomCallDialog.show();
//                }*/
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                Toast.makeText(HomeActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    public void openMessenger(View view) {
//
//        if (isReceived){
//            if (check){
//                if (!hasPermissions(this, PERMISSIONS_VIDEO_CALL)) {
//                    ActivityCompat.requestPermissions(this, PERMISSIONS_VIDEO_CALL, PERMISSION_MESSENGER);
//                } else {
//                    startActivity(new Intent(HomeActivity.this,ChatListActivity.class));
//                }
//            } else {
//                final Dialog dialog = new Dialog(HomeActivity.this);
//                dialog.setContentView(R.layout.dialog_layout_warning);
//                dialog.setCancelable(false);
//                dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                TextView ok_btn = dialog.findViewById(R.id.save_dialog_warning);
//                TextView cancel_btn = dialog.findViewById(R.id.quit_dialog_warning);
//                dialog.show();
//                ok_btn.setOnClickListener(view1 -> startActivity(new Intent(HomeActivity.this,EditProfileInfoKtActivity.class)));
//                cancel_btn.setOnClickListener(view12 -> dialog.dismiss());
//            }
//        }else {
//            Toast.makeText(this, "Poor internet connection", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        userStateChange(true);
//    }
//
//    @Override
//    protected void onDestroy() {
//        userStateChange(false);
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    private void userStateChange(Boolean state){
//        long dateTime;
//        dateTime = System.currentTimeMillis();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uID).child("userStat");
//        reference.child("online").setValue(state);
//        reference.child("date").setValue(dateTime);
//    }
//
//    @Override
//    public void onBackPressed() {
//
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Back press again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//        int id = menuItem.getItemId();
//        switch (id){
//            case R.id.log_out_nav:
//                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
//                builder.setTitle("Logout");
//                builder.setCancelable(false);
//                builder.setMessage("Are you sure to logout ?");
//                builder.setPositiveButton("Yes", (dialog, which) -> {
//                    // Clear Shared Preferences
//                    SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.clear();
//                    editor.apply();
//
//                    FirebaseAuth.getInstance().signOut();
//                    LoginManager.getInstance().logOut();
//                    Intent intent = new Intent(HomeActivity.this, LoginKtActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    HomeActivity.this.finish();
//
//                }).setNegativeButton("No", (dialog, which) -> dialog.dismiss());
//                builder.show();
//                break;
//            case R.id.action_wallet_nav:
//                if (isReceived){
//                    if (check){
//                        startActivity(new Intent(HomeActivity.this,ReferActivity.class));
//                    }else {
//                        final Dialog dialog = new Dialog(HomeActivity.this);
//                        dialog.setContentView(R.layout.dialog_layout_warning);
//                        dialog.setCancelable(false);
//                        dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        TextView ok_btn = dialog.findViewById(R.id.save_dialog_warning);
//                        TextView cancel_btn = dialog.findViewById(R.id.quit_dialog_warning);
//                        dialog.show();
//                        ok_btn.setOnClickListener(view1 -> {
//                            startActivity(new Intent(HomeActivity.this,EditProfileInfoKtActivity.class));
//                        });
//                        cancel_btn.setOnClickListener(view12 -> dialog.dismiss());
//                    }
//                }else {
//                    Toast.makeText(this, "Poor internet connection", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            case R.id.friend_requests_nav:
//                startActivity(new Intent(HomeActivity.this,RequestListActivity.class));
//                break;
//
//            /*case R.id.action_settings_nav:
//                startActivity(new Intent(HomeActivity.this,SettingsActivity.class));
//                break;*/
//
//            case R.id.action_home_nav:
//                startActivity(new Intent(HomeActivity.this,FindFriendsActivity.class));
//                break;
//
//            case R.id.action_group_nav:
//                startActivity(new Intent(HomeActivity.this, GroupChatListActivity.class));
//                break;
//
//            case R.id.action_feedback_nav:
//                try {
//                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "stardigiinternation@gmail.com"));
//                    intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
//                    intent.putExtra(Intent.EXTRA_TEXT, "Your Feedback : ");
//                    startActivity(intent);
//                } catch(Exception e) {
//                    Toast.makeText(HomeActivity.this, "Sorry. You don't have any mail app", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            case R.id.action_apply_for_teacher_nav:
//               startActivity(new Intent(HomeActivity.this,ApplyForTeacherActivity.class));
//               // startActivity(new Intent(HomeActivity.this, MoviesActivity.class));
//                break;
//
//            case R.id.action_about_us_nav:
//                // startActivity(new Intent(HomeActivity.this, TestActivity.class));
//                startActivity(new Intent(HomeActivity.this,AboutUsActivity.class));
//                break;
//
//            case R.id.action_rate_us_nav:
//                try{
//                    startActivity(new Intent(HomeActivity.this, KotlinBaseActivity.class));
//                    // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
//                }catch (Exception e) {
//                    //Exception
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
//                }
//                break;
//
//            default:
//                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
//        }
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//
//
//    }
//
//    public static boolean hasPermissions(Context context, String[] permissions) {
//        if (context != null && permissions != null) {
//            for (String permission : permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private void showNotificationBadges(String userId){
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications").child(userId);
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                notificationCount = 0;
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    NotificationInApp notificationInApp = snapshot.getValue(NotificationInApp.class);
//                    if (!notificationInApp.isSeen()){
//                        notificationCount++;
//                    }
//                }
//
//                if (notificationCount > 0){
//                    badge.setVisibility(View.VISIBLE);
//                    if (notificationCount<100){
//                        tvNotificationCount.setText(""+notificationCount);
//                    } else {
//                        tvNotificationCount.setText("99");
//                    }
//                } else {
//                    badge.setVisibility(View.GONE);
//                }
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
//    private void updateToken(String token){
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tokens");
//        Token token1 = new Token(token);
//        databaseReference.child(uID).setValue(token1);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == PERMISSION_ALL) {
//            if (grantResults.length > 0) {
//                boolean callRequestAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                boolean recordRequestAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                if (callRequestAccepted && recordRequestAccepted) {
//                    startActivity(new Intent(HomeActivity.this,RandomCallActivity.class));
//                } else {
//                    Toast.makeText(this, "Phone call and Record audio permissions are required", Toast.LENGTH_SHORT).show();
//                }
//            }
//        } else if (requestCode == PERMISSION_MESSENGER){
//            if (grantResults.length > 0) {
//                boolean callRequestAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                boolean recordRequestAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                boolean cameraRequestAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
//                if (callRequestAccepted && recordRequestAccepted && cameraRequestAccepted) {
//                    startActivity(new Intent(HomeActivity.this,ChatListActivity.class));
//                } else {
//                    Toast.makeText(this, "Phone call and Record audio permissions are required", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
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
//
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
//        FirebaseApp.initializeApp(this);
//        getApplicationContext().bindService(new Intent(this, SinchService.class), this,
//                BIND_AUTO_CREATE);
//
//        if (!mPushTokenIsRegistered) {
//            getSinchServiceInterface().getManagedPush(uID).registerPushToken(this);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(new Intent(this, TempService.class));
//        } else
//        startService(new Intent(this, TempService.class));
//
//    }
//
//    @Override
//    public void tokenRegistered() {
//        mPushTokenIsRegistered = true;
//    }
//
//    @Override
//    public void tokenRegistrationFailed(SinchError sinchError) {
//        mPushTokenIsRegistered = false;
//        Toast.makeText(this, "Push token registration failed - incoming calls can't be received!", Toast.LENGTH_LONG).show();
//    }
//
//    private void checkUpdateVersion(){
//        DatabaseReference updateReference = FirebaseDatabase.getInstance().getReference("Version");
//        updateReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    String versionNameDB = dataSnapshot.getValue(String.class);
//                    try {
//                        String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//                        if (!versionNameDB.equals(versionName)){
//                            final Dialog dialog = new Dialog(HomeActivity.this);
//                            dialog.setContentView(R.layout.dialog_bonus_layout);
//                            dialog.setCancelable(false);
//                            dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
//                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                            TextView title = dialog.findViewById(R.id.title_dialog_bonus);
//                            TextView details = dialog.findViewById(R.id.description_dialog_bonus);
//                            TextView collect = dialog.findViewById(R.id.refresh_dialog_bonus);
//                            ImageView icon = dialog.findViewById(R.id.image_dialog_bonus);
//                            title.setText("New update available");
//                            details.setText("Please update app first.");
//                            collect.setText("Update now");
//                            icon.setImageResource(R.drawable.app_icon);
//                            collect.setOnClickListener(view1 -> {
//                                try{
//                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
//                                }catch (Exception e) {
//                                    //Exception
//                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
//                                }
//                            });
//                            dialog.show();
//                        }
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }
//
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
//    @Override
//    public void onItemClick(PostItem postItem, ImageView postImage, TextView authorName, CircleImageView authorImage) {
//
//        Intent intent = new Intent(this,FullPostActivity.class);
//        intent.putExtra("Id",postItem.getPostID());
//
//        ActivityOptions options = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            Pair<View,String> pair1 = Pair.create((View) postImage,"PostImageTransition");
//            Pair<View,String> pair2 = Pair.create((View) authorName,"AuthorNameTransition");
//            Pair<View,String> pair3 = Pair.create((View) authorImage,"AuthorImageTransition");
//            options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,pair1,pair2,pair3);
//        }
//        assert options != null;
//        startActivity(intent,options.toBundle());
//    }
//}
