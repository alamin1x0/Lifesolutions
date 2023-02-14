package com.lifesolutions.bd.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gmail.samehadar.iosdialog.IOSDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifesolutions.bd.Helpers.InternetCheck;
import com.lifesolutions.bd.R;


public class ReferActivity extends AppCompatActivity {

    TextView tvCode,tvCopy,tvCoin,tvReferred,mItemDescription, mItemDescription2, mItemDescription3;
    Button btnShare;
    private IOSDialog dialog1;
    DatabaseReference mReference;
    private String code,uID;
    private int coin,referCount;
    Toolbar toolbar;
    LinearLayout mDescriptionImg, mDescriptionImg2, mDescriptionImg3;
    ImageView imageView, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_refer);

        toolbar = findViewById(R.id.toolbar_refer);
        setSupportActionBar(toolbar);

        setTitle("Refer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvCode = findViewById(R.id.code_refer_code);
        tvCopy = findViewById(R.id.copy_refer_code);
        btnShare = findViewById(R.id.share_btn_refer_code);
        tvCoin = findViewById(R.id.coin_refer_code);
        tvReferred = findViewById(R.id.total_referred_refer_code);

        mReference = FirebaseDatabase.getInstance().getReference().child("Users");
        SharedPreferences sharedPreferences = getSharedPreferences("com.starnote.CurrentAuthUser",MODE_PRIVATE);
        uID = sharedPreferences.getString("uID",null);

        dialog1 = new IOSDialog.Builder(ReferActivity.this)
                .setDimAmount(3)
                .setSpinnerColorRes(R.color.white)
                .setMessageColorRes(R.color.white)
                .setTitleColorRes(R.color.colorPrimary)
                .setMessageContent("Please Wait")
                .setCancelable(false)
                .setMessageContentGravity(Gravity.CENTER)
                .build();



        if (InternetCheck.checkConnection(ReferActivity.this)){
            dialog1.show();
            mReference.child(uID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        code = dataSnapshot.child("referCode").getValue(String.class);
                        coin = dataSnapshot.child("points").getValue(Integer.class);
                        referCount = dataSnapshot.child("referred").getValue(Integer.class);
                    }

                    tvCode.setText(code);
                    tvCoin.setText(""+coin);
                    tvReferred.setText("Total Referred : "+referCount);


                    tvCopy.setOnClickListener(view -> {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Refer Code", code);
                        clipboard.setPrimaryClip(clip);

                        Snackbar snackbar = Snackbar.make(view, "Refer code has been copied to clipboard.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(ReferActivity.this, "Something went wrong.Please try again later", Toast.LENGTH_SHORT).show();
                }
            });

            dialog1.dismiss();
        }else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        btnShare.setOnClickListener(view -> {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                String shareHint = "Hey join Star Note application use my refer code: "+code+"\t and get 100 points bonus \n You can use refer code only when you complete your profile  "+"\n\n"+"https://play.google.com/store/apps/details?id=" + getPackageName();
                i.putExtra(Intent.EXTRA_TEXT, shareHint);
                startActivity(Intent.createChooser(i, "Choose one"));
            } catch(Exception e) {
                //e.toString();
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mItemDescription = findViewById(R.id.item_description);
        mDescriptionImg = findViewById(R.id.item_description_layout);
        imageView = findViewById(R.id.item_description_img);
        mItemDescription2 = findViewById(R.id.item_description2);
        mDescriptionImg2 = findViewById(R.id.item_description_layout2);
        imageView2 = findViewById(R.id.item_description_img2);
        mItemDescription3 = findViewById(R.id.item_description3);
        mDescriptionImg3 = findViewById(R.id.item_description_layout3);
        imageView3 = findViewById(R.id.item_description_img3);


        mDescriptionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseExpandTextView(true);
                collapseExpandTextView2(false);
                collapseExpandTextView3(false);
            }
        });
        mDescriptionImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseExpandTextView(false);
                collapseExpandTextView2(true);
                collapseExpandTextView3(false);
            }
        });
        mDescriptionImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseExpandTextView(false);
                collapseExpandTextView2(false);
                collapseExpandTextView3(true);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void collapseExpandTextView(boolean isOpen) {
        if (isOpen) {
            if (mItemDescription.getVisibility() == View.GONE) {
                // it's collapsed - expand it
                mItemDescription.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
            } else {
                // it's expanded - collapse it
                imageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
                mItemDescription.setVisibility(View.GONE);
            }
            ObjectAnimator animation = ObjectAnimator.ofInt(mItemDescription, "maxLines", mItemDescription.getMaxLines());
            animation.setDuration(200).start();
        } else {
            imageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
            mItemDescription.setVisibility(View.GONE);
        }
    }
    private void collapseExpandTextView2(boolean isOpen) {
        if (isOpen) {
            if (mItemDescription2.getVisibility() == View.GONE) {
                // it's collapsed - expand it
                mItemDescription2.setVisibility(View.VISIBLE);
                imageView2.setImageResource(R.drawable.ic_expand_less_black_24dp);
            } else {
                // it's expanded - collapse it
                imageView2.setImageResource(R.drawable.ic_expand_more_black_24dp);
                mItemDescription2.setVisibility(View.GONE);
            }
            ObjectAnimator animation = ObjectAnimator.ofInt(mItemDescription2, "maxLines", mItemDescription2.getMaxLines());
            animation.setDuration(200).start();
        } else {
            imageView2.setImageResource(R.drawable.ic_expand_more_black_24dp);
            mItemDescription2.setVisibility(View.GONE);
        }
    }
    private void collapseExpandTextView3(boolean isOpen) {
        if (isOpen) {
            if (mItemDescription3.getVisibility() == View.GONE) {
                // it's collapsed - expand it
                mItemDescription3.setVisibility(View.VISIBLE);
                imageView3.setImageResource(R.drawable.ic_expand_less_black_24dp);
            } else {
                // it's expanded - collapse it
                imageView3.setImageResource(R.drawable.ic_expand_more_black_24dp);
                mItemDescription3.setVisibility(View.GONE);
            }
            ObjectAnimator animation = ObjectAnimator.ofInt(mItemDescription3, "maxLines", mItemDescription3.getMaxLines());
            animation.setDuration(200).start();
        } else {
            imageView3.setImageResource(R.drawable.ic_expand_more_black_24dp);
            mItemDescription3.setVisibility(View.GONE);
        }
    }
}
