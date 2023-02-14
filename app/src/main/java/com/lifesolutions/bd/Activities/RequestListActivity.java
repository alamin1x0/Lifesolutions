package com.lifesolutions.bd.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.samehadar.iosdialog.IOSDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.Adapters.RequestListAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Models.UserId;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

public class RequestListActivity extends AppCompatActivity {

    Toolbar toolbar;
    private ArrayList<UserId> list;
    private RequestListAdapter mAdapter;
    DatabaseReference mReference,requestRef;
    private String uID;
    private RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    IOSDialog dialog1;
    private RelativeLayout emptyTextLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);


        toolbar = findViewById(R.id.toolbar_request_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView_request_list);
        emptyTextLayout = findViewById(R.id.empty_hint_layout_requestList);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(false);
        mLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(mLayoutManager);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        uID = sharedPreferences.getString("uID", null);

        list = new ArrayList<>();

        dialog1 = new IOSDialog.Builder(RequestListActivity.this)
                .setDimAmount(3)
                .setSpinnerColorRes(R.color.white)
                .setMessageColorRes(R.color.white)
                .setTitleColorRes(R.color.colorPrimary)
                .setMessageContent("Please Wait")
                .setCancelable(true)
                .setMessageContentGravity(Gravity.CENTER)
                .build();

        if (InternetCheck.checkConnection(this)) {
            dialog1.show();

            mReference = FirebaseDatabase.getInstance().getReference("Users");
            requestRef = FirebaseDatabase.getInstance().getReference("friendRequest").child(uID);

            requestRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (dataSnapshot1.child("type").exists()){
                                UserId userId = dataSnapshot1.getValue(UserId.class);
                                if (userId.getType().equals("request")) {
                                    list.add(userId);
                                }
                            }
                        }

                        if (list.size()==0) {
                            emptyTextLayout.setVisibility(View.VISIBLE);
                            Toast.makeText(RequestListActivity.this, "No Friend Requests", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();

                        } else {
                            emptyTextLayout.setVisibility(View.GONE);
                            mAdapter = new RequestListAdapter(RequestListActivity.this, list,true);
                            recyclerView.setAdapter(mAdapter);
                            dialog1.dismiss();
                        }
                    } catch (Exception e){
                        Toast.makeText(RequestListActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    dialog1.dismiss();
                    Toast.makeText(RequestListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {

            dialog1.dismiss();
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

