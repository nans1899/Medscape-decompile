package com.medscape.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
    private static final float MIN_SCALE = 0.75f;

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setPageTransformer(true, new VerticalPageTransformer());
        setOverScrollMode(2);
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        private VerticalPageTransformer() {
        }

        public void transformPage(View view, float f) {
            if (f < -1.0f) {
                view.setAlpha(0.0f);
            } else if (f <= 1.0f) {
                view.setAlpha(1.0f - f);
                view.setTranslationX(((float) view.getWidth()) * (-f));
                view.setTranslationY(f * ((float) view.getHeight()));
            } else {
                view.setAlpha(0.0f);
            }
        }
    }

    private MotionEvent swapXY(MotionEvent motionEvent) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        motionEvent.setLocation((motionEvent.getY() / height) * width, (motionEvent.getX() / width) * height);
        return motionEvent;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(swapXY(motionEvent));
        swapXY(motionEvent);
        return onInterceptTouchEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(swapXY(motionEvent));
    }
}
