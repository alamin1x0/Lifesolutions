package com.lifesolutions.bd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.airbnb.lottie.LottieAnimationView;
import com.lifesolutions.bd.R;

public class CourseWebView extends AppCompatActivity {

    WebView webView;
    Activity activity ;
  //  private ProgressDialog progDailog;
    LottieAnimationView animationView;
    String mUrl;

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_web_view);
        //initComponent();
        webView = findViewById(R.id.webViewID);
        animationView=findViewById(R.id.animationView);
        activity = this;


        String courseId = getIntent().getStringExtra("CourseID");

        if (courseId.equals("https://course.starnotesocial.com/courses")){

            mUrl="https://course.starnotesocial.com/courses";

        }else {
            mUrl="https://course.starnotesocial.com/course-details/"+courseId;

            Log.d("LINK", "Link "+mUrl);
        }

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                activity.setTitle("Loading...");
                activity.setProgress(progress * 100);

                if (progress == 100)
                    activity.setTitle(getResources().getString(R.string.app_name));
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.d("Failure Url :" , failingUrl);
            }

            //
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CourseWebView.this);
                builder.setMessage("SSL Error !!");
                builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            } //


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                animationView.setVisibility(View.INVISIBLE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(mUrl);
    }





/*
    @SuppressLint("SetJavaScriptEnabled")
    private void initComponent() {
        webView = findViewById(R.id.webViewID);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

*/
/*        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });*//*

        webView.setWebViewClient(new WebViewClient() {

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.i("TAG", "loading: deprecation");
                return  true;
                //return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            @TargetApi(Build.VERSION_CODES.N)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                Log.i("TAG", "loading: build.VERSION_CODES.N");
                return true;
                //return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(
                    WebView view, String url, Bitmap favicon) {
                Log.i("TAG", "page started:"+url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, final String url) {

                Log.i("TAG", "page finished:"+url);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
                handler.proceed();
            }

        });

    }
*/

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


}