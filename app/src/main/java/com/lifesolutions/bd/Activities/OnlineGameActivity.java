/**
package com.stardigiinternational.starnotee.Activities;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.stardigiinternational.starnotee.R;

public class OnlineGameActivity extends AppCompatActivity {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar progressBar;
    WebView mWebView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_game);

        toolbar = findViewById(R.id.toolbar_online_games);
        setSupportActionBar(toolbar);
        setTitle("Games");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressbar_online_games);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_online_games);
        mWebView = findViewById(R.id.webView_games);

        progressBar.setMax(100);

        mWebView.loadUrl("http://www.goboplay.com/");

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mSwipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

        });


        mSwipeRefreshLayout.setOnRefreshListener(() -> mWebView.reload());

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

 */