package com.lifesolutions.bd.kotlinCode.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.lifesolutions.bd.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity(), OnScrollChangedListener {

    private var webUrl: String? = null
    private var webName: String? = null
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    var progressBar: ProgressBar? = null
    var mWebView: WebView? = null
    var toolbar: Toolbar? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // Set Actionbar
        setSupportActionBar(toolbar_web_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null) {
            webUrl = intent.getStringExtra("webUrl")
            webName = intent.getStringExtra("webName")
        }


        supportActionBar?.title = webName

        progressBar = findViewById(R.id.progressbar_web_view)
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_web_view)
        mWebView = findViewById(R.id.webView_area)


        progressBar?.max = 100

        mWebView?.loadUrl(webUrl!!)

        mWebView?.settings?.javaScriptEnabled = true
        mWebView?.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                mSwipeRefreshLayout?.isRefreshing = false
                super.onPageFinished(view, url)
            }
        }

        mWebView?.viewTreeObserver?.addOnScrollChangedListener(this)

        mWebView?.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressBar?.progress = newProgress
            }
        })

        mSwipeRefreshLayout?.setOnRefreshListener(OnRefreshListener { mWebView?.reload() })

    }


    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onScrollChanged() {
        mSwipeRefreshLayout!!.isEnabled = mWebView!!.scrollY == 0
    }

    override fun onBackPressed() {
        if (mWebView!!.canGoBack()) {
            mWebView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }

}