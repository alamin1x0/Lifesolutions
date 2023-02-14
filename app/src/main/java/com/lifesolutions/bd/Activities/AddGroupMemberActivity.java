package com.lifesolutions.bd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.Adapters.GroupAddMemberAdapter;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddGroupMemberActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference reference,mReference,friendReference;
    LinearLayoutManager mLayoutManager;
    FirebaseUser mUser;
    GroupAddMemberAdapter memberAdapter;
    ArrayList<String> friendUId;
    private ArrayList<User> mUsers;
    String groupID,uID;
    Toolbar toolbar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_member);

        recyclerView = findViewById(R.id.recyclerView_add_group_member);
        toolbar = findViewById(R.id.toolbar_group_add_member);
        progressBar = findViewById(R.id.progressbar_group_add_member);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent myIntent = getIntent();
        if (myIntent != null){
            groupID = myIntent.getStringExtra("groupId");
        }
        setTitle("Add Group Member");

        mUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mUser != null){
            uID = mUser.getUid();
        } else {
            Intent intent = new Intent(AddGroupMemberActivity.this, LoginKtActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            AddGroupMemberActivity.this.finish();
        }

        mUsers = new ArrayList<>();
        friendUId = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("ChatGroups").child(groupID);
        mReference = FirebaseDatabase.getInstance().getReference("Users");
        friendReference = FirebaseDatabase.getInstance().getReference("friendRequest").child(uID);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        friendReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                friendUId.clear();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    friendUId.add(dataSnapshot1.getKey());
                }

                Set<String> set = new HashSet<>(friendUId);
                friendUId.clear();
                friendUId.addAll(set);

                if (friendUId.size() > 0){
                    showFriendList(friendUId);
                } else {
                    Toast.makeText(AddGroupMemberActivity.this, "No Friends", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void showFriendList(ArrayList<String> list1) {

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUsers.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    for (String members : list1) {
                        if (members.equals(dataSnapshot1.getKey())) {
                            User users1 = dataSnapshot1.getValue(User.class);
                            mUsers.add(users1);
                        }
                    }

                }

                if (mUsers.size() == 0){
                    Toast.makeText(AddGroupMemberActivity.this, "No Friends", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    memberAdapter = new GroupAddMemberAdapter(AddGroupMemberActivity.this, mUsers,groupID);
                    recyclerView.setAdapter(memberAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
