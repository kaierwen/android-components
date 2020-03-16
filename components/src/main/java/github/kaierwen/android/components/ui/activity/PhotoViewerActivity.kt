package github.kaierwen.android.components.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import github.kaierwen.android.components.R
import github.kaierwen.android.components.ui.adapter.PhotoViewerAdapter
import kotlinx.android.synthetic.main.activity_photo_viewer.*

/**
 * 查看大图Activity<br/><br/>
 * <p>
 * 采用 ViewPager + PageIndicatorView + PhotoView + Glide 实现
 * <p/>
 * @author kevinzhang
 * @since 2020/3/16
 * @see <a href="https://github.com/chrisbanes/PhotoView">PhotoView<a/>
 * @see <a href="https://github.com/romandanylyk/PageIndicatorView">PageIndicatorView<a/>
 */
class PhotoViewerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PHOTO_LIST = "extra_photo_list"
        const val EXTRA_SELECT_INDEX = "extra_select_index"
    }

    private lateinit var adapter: PhotoViewerAdapter
    private lateinit var listPhoto: MutableList<String>
    private var mSelectIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)
        getData()
        setFullScreen()
        setViewPager()
    }

    private fun getData() {
        mSelectIndex = intent.getIntExtra(EXTRA_SELECT_INDEX, 0)
        listPhoto = ArrayList()
        val photos = intent.getStringArrayListExtra(EXTRA_PHOTO_LIST)
        if (photos != null && photos.size > 0) {
            listPhoto.addAll(photos)
        }
    }

    private fun setFullScreen() {
        viewPager.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private fun setViewPager() {
        adapter = PhotoViewerAdapter(this)
        if (listPhoto.isNotEmpty()) {
            adapter.setData(listPhoto)
        }
        viewPager.adapter = adapter
        viewPager.setCurrentItem(mSelectIndex, false)
    }
}
