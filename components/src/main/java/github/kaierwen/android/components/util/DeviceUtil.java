package github.kaierwen.android.components.util;

import android.os.Build;
import android.util.Log;

/**
 * @author kevinzhang
 * @since 2020/4/4
 */
public class DeviceUtil {

    private static final String TAG = DeviceUtil.class.getSimpleName();

    /**
     * 检查运行的设备是否是模拟器
     *
     * @param printLog 是否需要打印日志
     * @return true为模拟器，false为真机
     * @see <a href="https://stackoverflow.com/questions/2799097/how-can-i-detect-when-an-android-application-is-running-in-the-emulator">link<a/>
     */
    public static boolean isEmulator(boolean printLog) {
        boolean result = (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator");
        if (printLog) {
            StringBuilder builder = new StringBuilder();
            builder.append("\n").append("BRAND: ").append(Build.BRAND).append("\n")
                    .append("DEVICE: ").append(Build.DEVICE).append("\n")
                    .append("FINGERPRINT: ").append(Build.FINGERPRINT).append("\n")
                    .append("HARDWARE: ").append(Build.HARDWARE).append("\n")
                    .append("MODEL: ").append(Build.MODEL).append("\n")
                    .append("MANUFACTURER: ").append(Build.MANUFACTURER).append("\n")
                    .append("PRODUCT: ").append(Build.PRODUCT).append("\n");
            Log.i(TAG, builder.toString());
            Log.i(TAG, "isEmulator: " + result);
        }
        return result;
    }
}
