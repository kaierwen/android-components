package github.kaierwen.android.components.demos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import github.kaierwen.android.components.ui.activity.PhotoViewerActivity
import github.kaierwen.android.components.util.DeviceUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DeviceUtil.isEmulator(true)

        var imgs = arrayListOf(
            "https://images.idgesg.net/images/article/2017/08/android_robot_logo_by_ornecolorada_cc0_via_pixabay1904852_wide-100732483-large.jpg",
            "https://images.idgesg.net/images/article/2018/09/google_drive_app_ios_backup-100772120-large.jpg",
            "https://images.idgesg.net/images/article/2018/09/google_drive_app_ios_backup-100772120-large.jpg",
            "https://images.idgesg.net/images/article/2018/09/google_drive_app_ios_backup-100772120-large.jpg",
            "https://images.idgesg.net/images/article/2018/09/google_drive_app_ios_backup-100772120-large.jpg",
            "https://images.idgesg.net/images/article/2018/09/google_drive_app_ios_backup-100772120-large.jpg",
            "https://images.idgesg.net/images/article/2018/09/google_drive_app_ios_backup-100772120-large.jpg"
        )
        val intent = Intent(this, PhotoViewerActivity::class.java)
        intent.putStringArrayListExtra(PhotoViewerActivity.EXTRA_PHOTO_LIST, imgs)
        intent.putExtra(PhotoViewerActivity.EXTRA_SELECT_INDEX, 5)
        startActivity(intent)
    }
}
