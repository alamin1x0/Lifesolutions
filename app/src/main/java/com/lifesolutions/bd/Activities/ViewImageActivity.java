package com.lifesolutions.bd.Activities;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.lifesolutions.bd.R;

import java.util.HashMap;

public class ViewImageActivity extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000 ;
    ImageView imageView;
    private String imageUrl;
    private String authorId;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        Toolbar toolbar = findViewById(R.id.toolbar_view_image);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.image_view_image);
        collapsingToolbarLayout = findViewById(R.id.collapsing_layout_view_image);

        collapsingToolbarLayout.setTitle("");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));

        Intent myIntent = getIntent();

        if (myIntent != null) {
            imageUrl = myIntent.getStringExtra("imageUrl");
            authorId = myIntent.getStringExtra("authorId");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.get().load(imageUrl).into(imageView);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        MenuItem item  = menu.findItem(R.id.action_download);
        MenuItem item2  = menu.findItem(R.id.report_menu);


        item.setOnMenuItemClickListener(menuItem -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions,PERMISSION_STORAGE_CODE);
                } else {
                    startDownloading(imageUrl,"jpg");
                }
            } else {
                startDownloading(imageUrl,"jpg");
            }

            return true;
        });

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                         openReportDialog();
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void openReportDialog() {

        final Dialog dialog = new Dialog(ViewImageActivity.this);
        dialog.setContentView(R.layout.report_edittext);

        Button saveBtn = dialog.findViewById(R.id.submitBtn_view);
        EditText nameEt = dialog.findViewById(R.id.nameEt_view);
        EditText reportEt = dialog.findViewById(R.id.reportEt_view);

        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        //editText.setText(info);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref_report = FirebaseDatabase.getInstance().getReference("reportList");

                String reporter_name = nameEt.getText().toString();
                String report_reason = reportEt.getText().toString();

                if (reporter_name.isEmpty() || report_reason.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter All Field !", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    HashMap<Object,String> hashMap = new HashMap<>();
                    hashMap.put("reporter_name",reporter_name);
                    hashMap.put("report_reason",report_reason);
                    hashMap.put("authorId",authorId);
                    hashMap.put("image_url",imageUrl);


                    ref_report.push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Reported Successfully!!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to Report!\n Try again after sometime...", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

    }

    private void startDownloading(String url, String type) {

        Toast.makeText(this, "Download Started", Toast.LENGTH_SHORT).show();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("file downloading ... ");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis()+"."+type);

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startDownloading(imageUrl,"jpg");
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
