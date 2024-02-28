package com.example.newsappkotlinmvvm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle : Bundle? = intent.extras
        val url = bundle!!.getString("URL")
        if (url != null) {
            val detailWebView : WebView = findViewById(R.id.detailWebView)
            val progressBar : ProgressBar = findViewById(R.id.progressBar)

            detailWebView.settings.javaScriptEnabled= true
            detailWebView.webViewClient = object: WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                    detailWebView.visibility = View.VISIBLE
                }
            }
        detailWebView.loadUrl(url)
        }
    }
}