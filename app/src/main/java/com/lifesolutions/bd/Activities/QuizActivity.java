package com.lifesolutions.bd.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.Adapters.ClassItemAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ClassItemAdapter mAdapter;
    ArrayList<String> list;
    RecyclerView.LayoutManager mLayoutManager;
    DatabaseReference mReference;
    Toolbar toolbar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        toolbar = findViewById(R.id.toolbar_quiz);
        progressBar = findViewById(R.id.progressbar_quiz);
        recyclerView = findViewById(R.id.recyclerView_quiz);

        setSupportActionBar(toolbar);
        setTitle("Digital Classroom");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mReference = FirebaseDatabase.getInstance().getReference("DigitalClassroom");

        list = new ArrayList<>();

        mLayoutManager = new GridLayoutManager(QuizActivity.this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);

        if (InternetCheck.checkConnection(this)){
            mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        list.add(snapshot.getKey());
                    }

                    if (list.size() == 0){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(QuizActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        mAdapter = new ClassItemAdapter(QuizActivity.this,list);
                        recyclerView.setAdapter(mAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(QuizActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
