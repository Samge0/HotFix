package com.example.samge.hotfix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.samge.tools.tinkercomponent.util.TinkerUtil;


public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TinkerUtil.checkTinkerPatch();
        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int a=10 / 0;
                String a="tinker修复tips";
                Toast.makeText(MainActivity.this, a.toString(), Toast.LENGTH_LONG).show();
            }
        });
        dealWebView();
    }

    /**
     * 处理webView的展示
     */
    private void dealWebView() {
        mWebView=findViewById(R.id.webview);
        WebSettings mSettings=mWebView.getSettings();
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        mSettings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        mSettings.setSupportZoom(true);//是否可以缩放，默认true
        mSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        mSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        mSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        mSettings.setAppCacheEnabled(false);//是否使用缓存
        mSettings.setDomStorageEnabled(false);//DOM Storage
        mSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        mSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl("https://www.qichamao.com");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView != null && mWebView.canGoBack()) {
            // webview返回前一个页面
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            mWebView.goBack();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
