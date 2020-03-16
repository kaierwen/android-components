package github.kaierwen.android.components.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.PhotoView;

/**
 * 修复{@link PhotoView}在{@link ViewPager}中因图片放大过大，导致崩溃的问题
 *
 * @author kevinzhang
 * @author <a href="https://github.com/azibug">azibug<a/>
 * @see <a href="https://github.com/chrisbanes/PhotoView/issues/31">IllegalArgumentException (pointerIndex out of range)</a>
 * @since 2020/3/17
 */
public class ViewPagerFixedPhotoView extends ViewPager {
    public ViewPagerFixedPhotoView(@NonNull Context context) {
        super(context);
    }

    public ViewPagerFixedPhotoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
