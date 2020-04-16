package github.kaierwen.android.components.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * 文件工具类
 *
 * @author kevinzhang
 * @since 2020/3/26
 */
public class FileUtil {

    /**
     * 让用户选择存储路径，仅支持API 21以上调用
     *
     * @param activity
     * @param reqCode
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void chooseSaveDocumentPath(Activity activity, int reqCode) {
        if (activity == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            activity.startActivityForResult(intent, reqCode);
        }
    }

    public static void saveRemoteFileTo() {

    }

    /**
     * 保存远程文件到本地文件夹，使用DownloadManager下载
     */
    public static void saveRemoteFilesTo() {

    }
}