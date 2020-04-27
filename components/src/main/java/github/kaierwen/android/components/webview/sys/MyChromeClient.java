package github.kaierwen.android.components.webview.sys;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * @author kaiyuan.zhang
 * @since 2019/5/27
 */
public class MyChromeClient extends WebChromeClient {

    private MyWebView.Callback mCallback;
    public void setCallback(MyWebView.Callback callback) {
        mCallback = callback;
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
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        if (mCallback != null) {
            mCallback.onShowFileChooser(webView, valueCallback, fileChooserParams);
            return true;
        }
        //super.onShowFileChooser(webView, valueCallback, fileChooserParams)
        return false;
    }

}
