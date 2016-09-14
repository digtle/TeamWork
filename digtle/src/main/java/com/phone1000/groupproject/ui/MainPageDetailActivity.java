package com.phone1000.groupproject.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phone1000.groupproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPageDetailActivity extends AppCompatActivity {
//    @BindView(R.id.detail_web)
    WebView webView;
    private final  String BASE_URL ="http://www.baidu.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_detail);
//        ButterKnife.bind(this);
//        initView();
    }

    private void initView() {
        webView.loadUrl(BASE_URL);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(BASE_URL);
                return  true;
            }
        });
    }
}
