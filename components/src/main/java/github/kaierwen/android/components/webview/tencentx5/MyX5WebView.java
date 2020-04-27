package github.kaierwen.android.components.webview.tencentx5;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * {@link WebView}封装
 *
 * @author kaiyuan.zhang
 * @see <a href="https://juejin.im/post/5a94f9d15188257a63113a74">参考<a/>
 * @since 2019/5/27
 */
public class MyX5WebView extends WebView {

    private MyX5WebViewClient mWbClient;
    private MyX5ChromeClient mChromeClient;

    public MyX5WebView(Context context) {
        super(context);
        init(context);
    }

    public MyX5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyX5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        webViewSetting(context);
        //处理重定向问题
        mWbClient = new MyX5WebViewClient();
        setWebViewClient(mWbClient);
        mChromeClient = new MyX5ChromeClient();
        setWebChromeClient(mChromeClient);

        //隐藏滚动条
        //https://blog.csdn.net/qq_26914291/article/details/82999081
        if (getX5WebViewExtension() != null) {
            getX5WebViewExtension().setHorizontalScrollBarEnabled(false);
            getX5WebViewExtension().setVerticalScrollBarEnabled(false);
        }
    }

    private void webViewSetting(Context context) {
        WebSettings webSettings = this.getSettings();
        //参见https://juejin.im/post/5a94f9d15188257a63113a74
        //5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(0);
        }
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //允许js代码
        webSettings.setJavaScriptEnabled(true);
        //允许SessionStorage/LocalStorage存储
        webSettings.setDomStorageEnabled(true);
        //禁用放缩
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(false);
        //禁用文字缩放
        webSettings.setTextZoom(100);
        //10M缓存，api 18后，系统自动管理。
        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        //允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(context.getDir("webViewCache", 0).getPath());
        //允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        //不保存密码
        webSettings.setSavePassword(false);

        //TODO 设置UA
//        webSettings.setUserAgentString(webSettings.getUserAgentString() + " kaolaApp/" + AppUtils.getVersionName());

        //自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return false;
    }

    /**
     * 解决WebView无法弹出键盘的问题
     *
     * @param outAttrs
     * @return
     * @see <a href="https://medium.com/@elye.project/managing-keyboard-on-webview-d2e89109d106">link<a/>
     */
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        BaseInputConnection connection = new BaseInputConnection(this, false);
        return connection;
    }

    public void setCallback(Callback callback) {
        mWbClient.setCallback(callback);
        mChromeClient.setCallback(callback);
    }

    public void loadData(String format) {

    }

    /**
     * 统一的回调
     */
    public interface Callback {

        /**
         * 加载完成
         */
        void onPageLoadFinish(WebView view, String url);

        /**
         * 拦截URL
         *
         * @param view
         * @param url
         */
        void shouldOverrideUrlLoading(WebView view, String url);

        /**
         * 上传图片
         *
         * @param webView
         * @param valueCallback
         * @param fileChooserParams
         */
        void onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams);

        void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);

        void openFileChooser5CallBack(WebView webView, ValueCallback<Uri[]> valueCallback,
                                      android.webkit.WebChromeClient.FileChooserParams fileChooserParams);
    }
}
