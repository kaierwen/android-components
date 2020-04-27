package github.kaierwen.android.components.webview.sys;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

/**
 * @author kaiyuan.zhang
 * @since 2019/5/27
 */
public class MyWebViewClient extends WebViewClient {

    private MyWebView.Callback mCallback;
    private ValueCallback<Uri> filePathsCallback;

    public void setCallback(MyWebView.Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //解决Uncaught ReferenceError: functionName is not defined的问题
        //https://www.cnblogs.com/renhui/p/5893099.html
        super.onPageFinished(view, url);
        if (mCallback != null) {
            mCallback.onPageLoadFinish(view, url);
        }
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }
    //针对 Android版本 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        if (mCallback != null) {
            mCallback.openFileChooserCallBack(uploadMsg, acceptType);
        }
    }

    // 针对 Android版本 < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    //针对 Android版本  > 4.1.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    private boolean handleOverrideUrl(WebView view, String url) {
        if (TextUtils.isEmpty(url) || mCallback == null) {
            return false;
        }
        mCallback.shouldOverrideUrlLoading(view, url);
        return true;
    }


}
