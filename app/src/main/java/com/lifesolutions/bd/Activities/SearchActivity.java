package com.lifesolutions.bd.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.Adapters.PostSearchAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Models.PostItem;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    SearchView searchView;
    DatabaseReference mReference;
    private ArrayList<PostItem> list;
    private RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    PostSearchAdapter mAdapter;
    LinearLayout textLayout;
    TextView tvTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = findViewById(R.id.search_search);
        recyclerView = findViewById(R.id.recyclerView_search);
        textLayout = findViewById(R.id.text_layout_search);
        tvTextSearch = findViewById(R.id.search_text_post_search);
        searchView.onActionViewExpanded();

        list = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        mReference = FirebaseDatabase.getInstance().getReference("Posts");

        mAdapter = new PostSearchAdapter(SearchActivity.this,list);
        recyclerView.setAdapter(mAdapter);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!TextUtils.isEmpty(s.trim())){
                    searchPosts(s);
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
                    tvTextSearch.setText("Type to search post");
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

    public void searchPosts(String str) {

        if (InternetCheck.checkConnection(this)) {

            tvTextSearch.setText("Please wait finding your result");

            Query searchQuery = mReference.orderByChild("postDescription").startAt(str).endAt(str + "\uf8ff");

            searchQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    list.clear();

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                        list.add(dataSnapshot1.getValue(PostItem.class));
                    }

                    if (list.size() <= 0){
                        Toast.makeText(SearchActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                        tvTextSearch.setText("Not found");
                    } else {
                        textLayout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(SearchActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
    }
}
