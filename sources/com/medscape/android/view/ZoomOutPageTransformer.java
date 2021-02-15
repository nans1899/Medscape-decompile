package com.medscape.android.view;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_ALPHA = 0.5f;
    private static final float MIN_SCALE = 0.85f;

    public void transformPage(View view, float f) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 1.0f) {
            float max = Math.max(MIN_SCALE, 1.0f - Math.abs(f));
            float f2 = 1.0f - max;
            float f3 = (((float) height) * f2) / 2.0f;
            float f4 = (((float) width) * f2) / 2.0f;
            if (f < 0.0f) {
                view.setTranslationX(f4 - (f3 / 2.0f));
            } else {
                view.setTranslationX((-f4) + (f3 / 2.0f));
            }
            view.setScaleX(max);
            view.setScaleY(max);
            view.setAlpha((((max - MIN_SCALE) / 0.14999998f) * MIN_ALPHA) + MIN_ALPHA);
        } else {
            view.setAlpha(0.0f);
        }
    }
}
