package github.kaierwen.android.components.demos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import github.kaierwen.android.components.demos.R
import github.kaierwen.android.components.webview.tencentx5.MyX5WebView

class TitleWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_web_view)

        var myWebView = findViewById<MyX5WebView>(R.id.myWebView)
        myWebView.loadUrl("https://www.baidu.com")
    }
}
