package com.lifesolutions.bd.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lifesolutions.bd.Api.RetrofitClient;
import com.lifesolutions.bd.Api.UrlType;
import com.lifesolutions.bd.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    EditText editUrl;
    TextView responseBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        editUrl = findViewById(R.id.edit_url);
        responseBody = findViewById(R.id.response_body);
    }

    public void onUrlCheck(View view) {
        String getUrl = editUrl.getText().toString();
        if (getUrl.trim().isEmpty()) {
            Toast.makeText(this, "Empty Input!", Toast.LENGTH_SHORT).show();
            return;
        }

        UrlType urlType = new UrlType(getUrl);


        // Retrofit API Call..
        Call<UrlType> call = RetrofitClient
                .getInstance()
                .getServerApi()
                .urlEncode(urlType);

        call.enqueue(new Callback<UrlType>() {
            @Override
            public void onResponse(Call<UrlType> call, Response<UrlType> response) {
                UrlType serverResponse = response.body();
                responseBody.setText(
                        serverResponse.getTitle() + "\n" + serverResponse.getImage()+ "\n" + serverResponse.getSource()
                );
                Toast.makeText(TestActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UrlType> call, Throwable t) {
                Toast.makeText(TestActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        Toast.makeText(this, getUrl, Toast.LENGTH_SHORT).show();
    }


    // SAZIB..
    public List<String> hasUrl(String postDescription) {
        List<String> links = new ArrayList<String>();


        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(postDescription);

        while (urlMatcher.find()) {
            links.add(postDescription.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        return links;

    }


    public void onTest(View view) {
        List<String> urls = hasUrl("Hello my link is https://google.com/ That is very good. my another website is https://softlabit.com/");

        for (String url : urls)
        {
            Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        }

    }
}
