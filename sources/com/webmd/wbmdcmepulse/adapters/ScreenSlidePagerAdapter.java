package com.webmd.wbmdcmepulse.adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.webmd.wbmdcmepulse.fragments.ScreenSlidePageFragment;
import com.webmd.wbmdcmepulse.models.articles.Slide;
import com.webmd.wbmdcmepulse.models.interfaces.IImageLoadedEvent;
import com.webmd.wbmdcmepulse.models.interfaces.IViewPagerClickListener;
import java.util.ArrayList;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private String mArticleId;
    private Context mContext;
    private int mCount;
    private int mCurrentPosition;
    private IImageLoadedEvent mImageLoadedEvent;
    private boolean mIsFullScreenEnabled;
    private ArrayList<Slide> mSlides;
    private IViewPagerClickListener mViewPageClickListener;

    public ScreenSlidePagerAdapter(FragmentManager fragmentManager, ArrayList<Slide> arrayList, Context context, String str, boolean z, IViewPagerClickListener iViewPagerClickListener, IImageLoadedEvent iImageLoadedEvent) {
        super(fragmentManager);
        this.mArticleId = str;
        this.mIsFullScreenEnabled = z;
        this.mContext = context;
        if (arrayList != null) {
            this.mCount = arrayList.size();
            this.mSlides = arrayList;
        } else {
            this.mCount = 0;
        }
        if (iViewPagerClickListener != null && !this.mIsFullScreenEnabled) {
            this.mViewPageClickListener = iViewPagerClickListener;
        }
        this.mImageLoadedEvent = iImageLoadedEvent;
    }

    public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int i) {
        this.mCurrentPosition = i;
        return ScreenSlidePageFragment.newInstance(this.mSlides.get(i), i, this.mCount, this.mContext, this.mArticleId, this.mIsFullScreenEnabled, this.mViewPageClickListener, this.mImageLoadedEvent);
    }

    public int getCount() {
        return this.mCount;
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }
}
