package com.phone1000.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 小组-最新-的头部的web视图
 */
public class TeamHeadDetailsWebActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_head_details_web);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.loadUrl("http://www.dgtle.com/article-15797-1.html");
        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        mWebView.setWebViewClient(webViewClient);
    }
}
