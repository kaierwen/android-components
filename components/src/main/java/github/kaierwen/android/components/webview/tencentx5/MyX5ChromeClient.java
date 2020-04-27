package github.kaierwen.android.components.webview.tencentx5;

import android.net.Uri;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/**
 * @author kaiyuan.zhang
 * @since 2019/5/27
 */
public class MyX5ChromeClient extends WebChromeClient {

    private MyX5WebView.Callback mCallback;
    public void setCallback(MyX5WebView.Callback callback) {
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
