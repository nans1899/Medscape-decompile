package com.wbmd.qxcalculator.views.tab;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.wbmd.qxcalculator.R;

public class SlidingTabLayout extends HorizontalScrollView {
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;
    private static final int TITLE_OFFSET_DIPS = 24;
    /* access modifiers changed from: private */
    public final SlidingTabStrip mTabStrip;
    private int mTabViewImageViewId;
    private int mTabViewLayoutId;
    private int mTitleOffset;
    /* access modifiers changed from: private */
    public ViewPager mViewPager;
    /* access modifiers changed from: private */
    public ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    public interface TabColorizer {
        int getDividerColor(int i);

        int getIndicatorColor(int i);
    }

    public SlidingTabLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlidingTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setHorizontalScrollBarEnabled(false);
        setFillViewport(true);
        this.mTitleOffset = (int) (getResources().getDisplayMetrics().density * 24.0f);
        SlidingTabStrip slidingTabStrip = new SlidingTabStrip(context);
        this.mTabStrip = slidingTabStrip;
        slidingTabStrip.setBackgroundResource(R.color.action_bar_color_default);
        addView(this.mTabStrip, -1, -2);
    }

    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        this.mTabStrip.setCustomTabColorizer(tabColorizer);
    }

    public void setSelectedIndicatorColors(int... iArr) {
        this.mTabStrip.setSelectedIndicatorColors(iArr);
    }

    public void setDividerColors(int... iArr) {
        this.mTabStrip.setDividerColors(iArr);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mViewPagerPageChangeListener = onPageChangeListener;
    }

    public void setCustomTabView(int i, int i2) {
        this.mTabViewLayoutId = i;
        this.mTabViewImageViewId = i2;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mTabStrip.removeAllViews();
        this.mViewPager = viewPager;
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    /* access modifiers changed from: protected */
    public TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setTextSize(2, 12.0f);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        if (Build.VERSION.SDK_INT >= 11) {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(16843534, typedValue, true);
            textView.setBackgroundResource(typedValue.resourceId);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            textView.setAllCaps(true);
        }
        int i = (int) (getResources().getDisplayMetrics().density * 16.0f);
        textView.setPadding(i, i, i, i);
        return textView;
    }

    private void populateTabStrip() {
        ImageView imageView;
        View view;
        ImageFragmentPagerAdapter imageFragmentPagerAdapter = (ImageFragmentPagerAdapter) this.mViewPager.getAdapter();
        TabClickListener tabClickListener = new TabClickListener();
        for (int i = 0; i < imageFragmentPagerAdapter.getCount(); i++) {
            if (this.mTabViewLayoutId != 0) {
                view = LayoutInflater.from(getContext()).inflate(this.mTabViewLayoutId, this.mTabStrip, false);
                imageView = (ImageView) view.findViewById(this.mTabViewImageViewId);
            } else {
                view = null;
                imageView = null;
            }
            imageView.setImageResource(imageFragmentPagerAdapter.getTabIcon(i));
            view.setOnClickListener(tabClickListener);
            this.mTabStrip.addView(view);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            scrollToTab(viewPager.getCurrentItem(), 0);
        }
    }

    /* access modifiers changed from: private */
    public void scrollToTab(int i, int i2) {
        View childAt;
        int childCount = this.mTabStrip.getChildCount();
        if (childCount != 0 && i >= 0 && i < childCount && (childAt = this.mTabStrip.getChildAt(i)) != null) {
            int left = childAt.getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.mTitleOffset;
            }
            scrollTo(left, 0);
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        private InternalViewPagerListener() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            int childCount = SlidingTabLayout.this.mTabStrip.getChildCount();
            if (childCount != 0 && i >= 0 && i < childCount) {
                if (SlidingTabLayout.this.getResources().getBoolean(R.bool.is_rtl)) {
                    if (f > 0.0f) {
                        i = ((SlidingTabLayout.this.mTabStrip.getChildCount() - 1) - i) - 1;
                        f = 1.0f - f;
                    } else {
                        i = (SlidingTabLayout.this.mTabStrip.getChildCount() - 1) - i;
                    }
                }
                SlidingTabLayout.this.mTabStrip.onViewPagerPageChanged(i, f);
                View childAt = SlidingTabLayout.this.mTabStrip.getChildAt(i);
                SlidingTabLayout.this.scrollToTab(i, childAt != null ? (int) (((float) childAt.getWidth()) * f) : 0);
                if (SlidingTabLayout.this.mViewPagerPageChangeListener != null) {
                    SlidingTabLayout.this.mViewPagerPageChangeListener.onPageScrolled(i, f, i2);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
            this.mScrollState = i;
            if (SlidingTabLayout.this.mViewPagerPageChangeListener != null) {
                SlidingTabLayout.this.mViewPagerPageChangeListener.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            SlidingTabLayout.this.mTabStrip.updateTabSelected(i);
            if (this.mScrollState == 0) {
                SlidingTabLayout.this.mTabStrip.onViewPagerPageChanged(i, 0.0f);
                SlidingTabLayout.this.scrollToTab(i, 0);
            }
            if (SlidingTabLayout.this.mViewPagerPageChangeListener != null) {
                SlidingTabLayout.this.mViewPagerPageChangeListener.onPageSelected(i);
            }
        }
    }

    private class TabClickListener implements View.OnClickListener {
        private TabClickListener() {
        }

        public void onClick(View view) {
            for (int i = 0; i < SlidingTabLayout.this.mTabStrip.getChildCount(); i++) {
                if (view == SlidingTabLayout.this.mTabStrip.getChildAt(i)) {
                    SlidingTabLayout.this.mViewPager.setCurrentItem(i);
                    SlidingTabLayout.this.mTabStrip.updateTabSelected(i);
                    return;
                }
            }
        }
    }
}
