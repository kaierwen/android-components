package github.kaierwen.android.components.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://github.com/romandanylyk/PageIndicatorView/blob/master/sample/src/main/java/com/rd/pageindicatorview/home/HomeAdapter.java">HomeAdapter.java<a/>
 */
public class PhotoViewerAdapter extends PagerAdapter {

    private List<String> mPhotoPaths;
    private Context mContext;

    public PhotoViewerAdapter(Context context) {
        mContext = context;
        this.mPhotoPaths = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        PhotoView photoView = new PhotoView(mContext);
        photoView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //默认单击，就关闭当前Activity
                if (mContext instanceof Activity) {
                    ((Activity) mContext).finish();
                }
            }
        });
        //load image to photoView use Glide
        Glide.with(mContext).load(mPhotoPaths.get(position)).into(photoView);
        collection.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mPhotoPaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setData(@Nullable List<String> list) {
        this.mPhotoPaths.clear();
        if (list != null && !list.isEmpty()) {
            this.mPhotoPaths.addAll(list);
        }

        notifyDataSetChanged();
    }

    @NonNull
    public List<String> getData() {
        if (mPhotoPaths == null) {
            mPhotoPaths = new ArrayList<>();
        }

        return mPhotoPaths;
    }
}