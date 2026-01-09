package com.strong.mobile.capacitor.v1;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onStart() {
        super.onStart();
        WebView webView = (WebView) this.bridge.getWebView();
        WebSettings settings = webView.getSettings();

        // 1. 伪装成真正的 Chrome 浏览器 (Android 10 风格)
        // 移除 Version/4.0 标识，防止被网页识别为嵌入式 Webview
        String chromeUA = "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36";
        settings.setUserAgentString(chromeUA);

        // 2. 开启核心功能：JS、DOM存储、数据库
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        
        // 3. 解决 iframe 跨域与混合内容
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);

        // 4. 允许第三方 Cookie (针对 iframe 登录同步)
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
    }
}