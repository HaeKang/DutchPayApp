package com.example.apptest.Sign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.apptest.R;

public class SignUpActivity extends AppCompatActivity {

    //webview
    private WebView webView;

    private static final String CERT_USER_AGENT_YESSIGN_ANDROID = "\u0020yessign/wv/A-8GYT7FZ5Qb37lB4Ud4Lt;1.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        webView = findViewById(R.id.webview);
        webView.setNetworkAvailable(true);

        // 테스트
        webView.setWebContentsDebuggingEnabled(true);

        loadUrlOnWebView();

    }

    public void loadUrlOnWebView(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDefaultTextEncodingName("UTF-8");

        ////////// 브라우저 인증서 사용을 위한 옵션 시작 //////////
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        // 일부폰에서 접근성 설정에 의한 css 깨짐 발생방지
        webSettings.setTextZoom(100);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 모바일 브라우저와 웹뷰(하이브리드 앱)를 구분하기 위한 UserAgent 설정
        StringBuffer sb = new StringBuffer(webSettings.getUserAgentString()).append(CERT_USER_AGENT_YESSIGN_ANDROID);
        webSettings.setUserAgentString(sb.toString());
        ////////// 브라우저 인증서 사용을 위한 옵션 끝 //////////



    }
}