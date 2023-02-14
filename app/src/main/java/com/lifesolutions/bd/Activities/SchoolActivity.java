package com.lifesolutions.bd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lifesolutions.bd.R;

public class SchoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        setTitle("School");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
