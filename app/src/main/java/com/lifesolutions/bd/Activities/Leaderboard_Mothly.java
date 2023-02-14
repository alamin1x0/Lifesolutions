package com.lifesolutions.bd.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.Adapters.LeaderBoardListAdapter;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.Models.User;
import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity;
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class Leaderboard_Mothly extends AppCompatActivity {
    private String TAG = "LeaderBoardMonthly";

    CircleImageView imageUser,imageFirst,imageSecond,imageThird;
    TextView nameUser,nameFirst,nameSecond,nameThird,coinUser,coinFirst,coinSecond,coinThird,rankUser;
    RecyclerView recyclerView;
    private String uID;
    ArrayList<User> users,sortedUsers;
    DatabaseReference mReference;
    private int count,userPostCount = 0;
    private LeaderBoardListAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    FirebaseAuth mAuth;
    Toolbar toolbar;
    private AnimatedLoading animatedLoading;
    Button montlyButton;

    // private IOSDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_mothly);

        toolbar = findViewById(R.id.toolbar_leader_board);
        setSupportActionBar(toolbar);
        setTitle("Leader Board");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        animatedLoading = new AnimatedLoading(this);

        imageUser = findViewById(R.id.profile_image_leader_board);
        imageFirst = findViewById(R.id.profile_image_first_user_leader_board);
        imageSecond = findViewById(R.id.profile_image_second_user_leader_board);
        imageThird = findViewById(R.id.profile_image_third_user_leader_board);
        nameUser = findViewById(R.id.user_name_leader_board);
        nameFirst = findViewById(R.id.name_first_user_leader_board);
        nameSecond = findViewById(R.id.name_second_user_leader_board);
        nameThird = findViewById(R.id.name_third_user_leader_board);
        coinUser = findViewById(R.id.my_coins_leader_board);
        coinFirst = findViewById(R.id.coin_first_user_leader_board);
        coinSecond = findViewById(R.id.coin_second_user_leader_board);
        coinThird = findViewById(R.id.coin_third_user_leader_board);
        rankUser = findViewById(R.id.user_rank_leader_board);
        recyclerView = findViewById(R.id.recyclerView_leader_board);
        //  montlyButton=findViewById(R.id.monthlybtn);

/*
        montlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Leaderboard_Mothly.class));
            }
        });
*/



        users = new ArrayList<>();
        sortedUsers = new ArrayList<>();

        mReference = FirebaseDatabase.getInstance().getReference("Users");


        Display display = Leaderboard_Mothly.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/120);
        mLayoutManager = new GridLayoutManager(Leaderboard_Mothly.this,columns);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);


        mAuth = FirebaseAuth.getInstance();
        if(mAuth != null){
            uID = mAuth.getUid();
        } else {
            Intent intent = new Intent(Leaderboard_Mothly.this, LoginKtActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Leaderboard_Mothly.this.finish();
        }

        getUserInfo(uID);

        animatedLoading.showAnimatedLoading();

        if (InternetCheck.checkConnection(this)) {

            Query query = mReference.orderByChild("points").limitToLast(100);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    users.clear();
                    sortedUsers.clear();
                    count = 0;

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                        User user = dataSnapshot1.getValue(User.class);
                        users.add(user);
                    }

                    Log.w(TAG, "onDataChange: -> " + users.toString());

                    if (users.size() == 0){
                        Toast.makeText(Leaderboard_Mothly.this, "Empty", Toast.LENGTH_SHORT).show();
                    } else {

                        Collections.sort(users, (p1, p2) -> {
                            return p2.getPoints() - p1.getPoints(); // Descending
                        });

                        if (users.size()>3){

                            for (int i = 3; i < users.size(); i++){
                                if (users.get(i).getuID() == null) {
                                    continue;
                                }
                                sortedUsers.add(users.get(i));
                            }

                            nameFirst.setText(users.get(0).getFirstName()+users.get(0).getLastName());
                            coinFirst.setText(""+users.get(0).getPoints());
                            if (!users.get(0).getImageThumbnail().equals("none")){
                                Picasso.get().load(users.get(0).getImageThumbnail()).into(imageFirst);
                            } else {
                                imageFirst.setImageResource(R.drawable.user);
                            }

                            imageFirst.setOnClickListener(view -> showDialog(users.get(0).getuID(),users.get(0).getFirstName()+" "+users.get(0).getLastName(),users.get(0).getAddress(),users.get(0).getImageThumbnail()));


                            nameSecond.setText(users.get(1).getFirstName()+users.get(1).getLastName());
                            coinSecond.setText(""+users.get(1).getPoints());
                            if (!users.get(1).getImageThumbnail().equals("none")){
                                Picasso.get().load(users.get(1).getImageThumbnail()).into(imageSecond);
                            } else {
                                imageSecond.setImageResource(R.drawable.user);
                            }

                            imageSecond.setOnClickListener(view -> showDialog(users.get(1).getuID(),users.get(1).getFirstName()+" "+users.get(1).getLastName(),users.get(1).getAddress(),users.get(1).getImageThumbnail()));

                            nameThird.setText(users.get(2).getFirstName()+users.get(2).getLastName());
                            coinThird.setText(""+users.get(2).getPoints());
                            if (!users.get(2).getImageThumbnail().equals("none")){
                                Picasso.get().load(users.get(2).getImageThumbnail()).into(imageThird);
                            } else {
                                imageThird.setImageResource(R.drawable.user);
                            }

                            imageThird.setOnClickListener(view -> showDialog(users.get(2).getuID(),users.get(2).getFirstName()+" "+users.get(2).getLastName(),users.get(2).getAddress(),users.get(2).getImageThumbnail()));

                            Log.d(TAG, "onDataChange: -> " + users.toString());
                            Log.d(TAG, "onDataChange: -> " + users.size());

                            for (int j = 0; j < users.size(); j++){
                                Log.d(TAG, "onDataChange: -> " + users.get(j).getuID());
                                if (users.get(j).getuID() == null) {
                                    continue;
                                }
                                if (users.get(j).getuID().equals(uID)){
                                    int rank = 0;
                                    rank = j+1;
                                    rankUser.setText(String.valueOf(rank));
                                }

                            }

                        }


                        /*else if (users.size() == 3){

                            nameFirst.setText(users.get(0).getFirstName()+users.get(0).getLastName());
                            coinFirst.setText(""+users.get(0).getPoints());
                            if (!users.get(0).getImageThumbnail().equals("none")){
                                Picasso.get().load(users.get(0).getImageThumbnail()).into(imageFirst);
                            } else {
                                imageFirst.setImageResource(R.drawable.user);
                            }


                            nameSecond.setText(users.get(1).getFirstName()+users.get(1).getLastName());
                            coinSecond.setText(""+users.get(1).getPoints());
                            if (!users.get(1).getImageThumbnail().equals("none")){
                                Picasso.get().load(users.get(1).getImageThumbnail()).into(imageSecond);
                            }else {
                                imageSecond.setImageResource(R.drawable.user);
                            }

                            nameThird.setText(users.get(2).getFirstName()+users.get(2).getLastName());
                            coinThird.setText(""+users.get(2).getPoints());
                            if (!users.get(2).getImageThumbnail().equals("none")){
                                Picasso.get().load(users.get(2).getImageThumbnail()).into(imageThird);
                            } else {
                                imageThird.setImageResource(R.drawable.user);
                            }

                            for (int j = 0; j < users.size(); j++){

                                if (users.get(j).getuID().equals(uID)){
                                    int rank = 0;
                                    if (j <= 3){
                                        rank = j+1;
                                        rankUser.setText(""+rank);
                                    } else {
                                        rank = j+2;
                                        rankUser.setText(""+rank);
                                    }
                                }

                            }
                        }*/

                        mAdapter = new LeaderBoardListAdapter(Leaderboard_Mothly.this,sortedUsers);
                        mAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mAdapter);
                        animatedLoading.hideAnimatedLoading();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(Leaderboard_Mothly.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }

    private void getUserInfo(String uID){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String imageUrl = "none",firstName,lastName;
                int coin;
                imageUrl = dataSnapshot.child("imageThumbnail").getValue(String.class);
                coin = dataSnapshot.child("points").getValue(Integer.class);

                if (dataSnapshot.child("firstName").exists() && dataSnapshot.child("lastName").exists()){
                    firstName = dataSnapshot.child("firstName").getValue(String.class);
                    lastName = dataSnapshot.child("lastName").getValue(String.class);
                    nameUser.setText(firstName+" "+lastName);
                }
                if (!imageUrl.equals("none")){
                    Picasso.get().load(imageUrl).into(imageUser);
                }
                coinUser.setText(""+coin);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Leaderboard_Mothly.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void showDialog(String userId,String name,String address,String imageUrl){
        final Dialog dialog = new Dialog(Leaderboard_Mothly.this);
        dialog.setContentView(R.layout.profile_dialog_layout);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation_dialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView nameDialog = dialog.findViewById(R.id.name_dialog_leader_board);
        TextView addressDialog = dialog.findViewById(R.id.address_dialog_leader_board);
        TextView followersDialog= dialog.findViewById(R.id.friends_dialog_leader_board);
        TextView friendsDialog = dialog.findViewById(R.id.followers_dialog_leader_board);
        TextView postsDialog = dialog.findViewById(R.id.posts_dialog_leader_board);
        CircleImageView userImage = dialog.findViewById(R.id.profile_image_dialog_leader_board);
        nameDialog.setText(name);
        addressDialog.setText(address);
        ProfileActivity.getFriendFollowerCount(userId,friendsDialog,followersDialog);
        getPostCount(userId,postsDialog);
        if (!imageUrl.equals("none")){
            Picasso.get().load(imageUrl).into(userImage);
        }
        dialog.show();
    }

    private void getPostCount(String userId,TextView postCount){

        Query query = FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("authorID").equalTo(userId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    postCount.setText(""+dataSnapshot.getChildrenCount());
                } else {
                    postCount.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
