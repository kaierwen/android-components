package github.kaierwen.android.components.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 用于开启和管理多线程任务的工具类<br/><br/>
 *
 * <p>
 * 使用{@link ThreadPoolExecutor}管理多线程
 * </p>
 *
 * @author kevinzhang
 * @see <a href="https://developer.android.com/training/multiple-threads/create-threadpool"/>
 * @since 2020/3/14
 */
public class MultiTaskUtil {

    // <editor-fold defaultstate="collapsed" desc="singleton">
    private volatile static MultiTaskUtil sInstance;

    private MultiTaskUtil() {
    }

    public static MultiTaskUtil getInstance() {
        if (sInstance == null) {
            synchronized (MultiTaskUtil.class) {
                if (sInstance == null) {
                    sInstance = new MultiTaskUtil();
                }
            }
        }
        return sInstance;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="线程池">
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    // A queue of Runnables
    private final BlockingQueue<Runnable> decodeWorkQueue = new LinkedBlockingQueue<>();
    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 1;
    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    // Creates a thread pool manager
    ThreadPoolExecutor decodeThreadPool;
    // </editor-fold>

    public void runThread(Runnable runnable) {
        if (decodeThreadPool == null) {
            decodeThreadPool = new ThreadPoolExecutor(
                    NUMBER_OF_CORES,       // Initial pool size
                    NUMBER_OF_CORES,       // Max pool size
                    KEEP_ALIVE_TIME,
                    KEEP_ALIVE_TIME_UNIT,
                    decodeWorkQueue);
        }
        decodeThreadPool.execute(runnable);
    }
}
