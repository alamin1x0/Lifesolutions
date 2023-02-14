//package com.stardigiinternational.starnotee.Activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.stardigiinternational.starnotee.Adapters.MyPostListAdapter;
//import com.stardigiinternational.starnotee.Helpers.InternetCheck;
//import com.stardigiinternational.starnotee.Models.PostItem;
//import com.stardigiinternational.starnotee.R;
//import com.stardigiinternational.starnotee.kotlinCode.ui.auth.LoginKtActivity;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class MyPostListActivity extends AppCompatActivity {
//
//    private MyPostListAdapter mAdapter;
//    DatabaseReference mReference;
//    private String uID;
//    private RecyclerView recyclerView;
//    LinearLayoutManager mLayoutManager;
//    private ProgressBar progressBar;
//    FirebaseAuth mAuth;
//    Toolbar toolbar;
//    final int ITEM_LOAD_COUNT = 15;
//    int total_item = 0,last_visible_item,pageCount = 1;
//    private Boolean isLoading = false;
//    private ArrayList<PostItem> list;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_post_list);
//
//        toolbar = findViewById(R.id.toolbar_my_post_list);
//        recyclerView = findViewById(R.id.recyclerView_my_post_list);
//        progressBar = findViewById(R.id.progressbar_my_post_list);
//
//        setSupportActionBar(toolbar);
//        setTitle("My Posts");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        mAuth = FirebaseAuth.getInstance();
//        mReference = FirebaseDatabase.getInstance().getReference("Posts");
//
//        if(mAuth != null){
//            uID = mAuth.getUid();
//        } else {
//            Intent intent = new Intent(MyPostListActivity.this, LoginKtActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            MyPostListActivity.this.finish();
//        }
//
//        list = new ArrayList<>();
//
//        mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(mLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),mLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//        mAdapter = new MyPostListAdapter(this);
//        recyclerView.setAdapter(mAdapter);
//
//        getMyPosts();
//
//
//    }
//
//    private void getMyPosts() {
//
//        if (InternetCheck.checkConnection(this)) {
//
//            Query query = mReference.orderByChild("authorID").equalTo(uID);
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    list.clear();
//
//                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
//                        PostItem postItem = dataSnapshot1.getValue(PostItem.class);
//                        if (postItem.getAuthorID().equals(uID)){
//                            list.add(postItem);
//                        }
//
//                    }
//
//                    Collections.reverse(list);
//                    progressBar.setVisibility(View.GONE);
//                    if (list.size() == 0){
//                        Toast.makeText(MyPostListActivity.this, "No Posts", Toast.LENGTH_SHORT).show();
//                    } else {
//                        mAdapter.addAllPosts(list);
//                        mAdapter.notifyDataSetChanged();
//                    }
//
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(MyPostListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        } else {
//
//            progressBar.setVisibility(View.GONE);
//            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
//
//}
