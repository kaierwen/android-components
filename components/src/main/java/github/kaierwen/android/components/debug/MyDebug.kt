package github.kaierwen.android.components.debug

import github.kaierwen.android.components.debug.beagle.MyBeagle

/**
 * 调试入口
 *
 * @author kevinzhang
 * @since 2019-12-13
 */
class MyDebug {

    companion object {
        @JvmStatic
        fun getMyBeagle(): MyBeagle {
            return MyBeagle.instance
        }
    }
}