package com.lifesolutions.bd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lifesolutions.bd.R;
import com.lifesolutions.bd.kotlinCode.ui.activities.StreamActivity;
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity;

public class ChooseLive extends AppCompatActivity {

    Button live,watch,feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.choose_live_updated);

        live = findViewById(R.id.button2);
        watch = findViewById(R.id.button);
        feed = findViewById(R.id.button7);

        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StreamActivity.class));
            }
        });

        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StreamViewList.class));
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}