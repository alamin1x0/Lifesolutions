package com.lifesolutions.bd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.samehadar.iosdialog.IOSDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.Adapters.SearchUsersAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity;

import java.util.ArrayList;

public class SearchUsersActivity extends AppCompatActivity {

    Toolbar toolbar;
    SearchView searchView;
    DatabaseReference mReference;
    private String uID;
    private ArrayList<User> list;
    private RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    SearchUsersAdapter mAdapter;
    LinearLayout textLayout;
    FirebaseAuth mAuth;
    IOSDialog dialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_users);

        toolbar = findViewById(R.id.toolbar_search_users);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = findViewById(R.id.search_search_users);
        recyclerView = findViewById(R.id.recyclerView_search_users);
        textLayout = findViewById(R.id.text_layout_search_users);
        searchView.onActionViewExpanded();

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        list = new ArrayList<>();
        mAdapter = new SearchUsersAdapter(SearchUsersActivity.this,list);
        recyclerView.setAdapter(mAdapter);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth != null){
            uID = mAuth.getUid();
        } else {
            Intent intent = new Intent(SearchUsersActivity.this, LoginKtActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            SearchUsersActivity.this.finish();
        }

        dialog1 = new IOSDialog.Builder(SearchUsersActivity.this)
                .setDimAmount(3)
                .setSpinnerColorRes(R.color.white)
                .setMessageColorRes(R.color.white)
                .setTitleColorRes(R.color.colorPrimary)
                .setMessageContent("Please Wait")
                .setCancelable(false)
                .setMessageContentGravity(Gravity.CENTER)
                .build();


        mReference = FirebaseDatabase.getInstance().getReference("Users");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!TextUtils.isEmpty(s.trim())){
                    searchUsers(s.toLowerCase());
                } else {
                    list.clear();
                    mAdapter.notifyDataSetChanged();
                    textLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s.trim())){
                    list.clear();
                    mAdapter.notifyDataSetChanged();
                    textLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                return false;
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void searchUsers(String str) {

        if (InternetCheck.checkConnection(this)) {

            dialog1.show();

            Query searchQuery = mReference.orderByChild("searchName").startAt(str).endAt(str + "\uf8ff");

            searchQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list.clear();

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                        list.add(dataSnapshot1.getValue(User.class));
                    }

                    if (list.size() <= 0){
                        Toast.makeText(SearchUsersActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                    } else {
                        textLayout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        mAdapter.notifyDataSetChanged();
                    }

                    dialog1.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(SearchUsersActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

    }
}
