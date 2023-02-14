/**
package com.stardigiinternational.starnotee.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.stardigiinternational.starnotee.R;

public class EcommerceActivity extends AppCompatActivity implements ViewTreeObserver.OnScrollChangedListener{

    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar progressBar;
    WebView mWebView;
    Toolbar toolbar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);

        toolbar = findViewById(R.id.toolbar_online_ecommerce);
        setSupportActionBar(toolbar);
        setTitle("E-Commerce");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressbar_ecommerce);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_ecommerce);
        mWebView = findViewById(R.id.webView_ecommerce);

        progressBar.setMax(100);

        mWebView.loadUrl("http://www.joruribazar.com/");

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mSwipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });

        mWebView.getViewTreeObserver().addOnScrollChangedListener(this);

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

    @Override
    public void onScrollChanged() {
        if (mWebView.getScrollY() == 0) {
            mSwipeRefreshLayout.setEnabled(true);
        } else {
            mSwipeRefreshLayout.setEnabled(false);
        }
    }
}

 */
