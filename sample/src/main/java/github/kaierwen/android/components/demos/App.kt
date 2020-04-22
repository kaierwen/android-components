package github.kaierwen.android.components.demos

import android.app.Application
import github.kaierwen.android.components.debug.MyDebug

/**
 * @author kevinzhang
 * @since 2020/4/22
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MyDebug.getMyBeagle().init(this)
    }
}