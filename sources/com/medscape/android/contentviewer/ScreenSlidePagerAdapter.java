package com.medscape.android.contentviewer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.medscape.android.contentviewer.interfaces.IImageLoadedEvent;
import com.medscape.android.contentviewer.interfaces.IViewPagerClickListener;
import com.medscape.android.contentviewer.model.Slide;
import java.util.ArrayList;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private int mCount;
    private int mCurrentPosition;
    private IImageLoadedEvent mImageLoadedEvent;
    private boolean mIsFullScreenEnabled;
    private ArrayList<Slide> mSlides;
    private IViewPagerClickListener mViewPageClickListener;

    public ScreenSlidePagerAdapter(FragmentManager fragmentManager, ArrayList<Slide> arrayList, boolean z, IViewPagerClickListener iViewPagerClickListener, IImageLoadedEvent iImageLoadedEvent) {
        super(fragmentManager);
        this.mIsFullScreenEnabled = z;
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
        return ScreenSlidePageFragment.newInstance(this.mSlides.get(i), i, this.mCount, this.mIsFullScreenEnabled, this.mViewPageClickListener, this.mImageLoadedEvent);
    }

    public int getCount() {
        return this.mCount;
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }
}
