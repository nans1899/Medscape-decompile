package com.appboy.ui.support;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;

public class AnimationUtils {
    private static Interpolator sAccelerateInterpolator = new AccelerateInterpolator();
    private static Interpolator sDecelerateInterpolator = new DecelerateInterpolator();

    public static Animation createVerticalAnimation(float f, float f2, long j, boolean z) {
        return setAnimationParams(new TranslateAnimation(2, 0.0f, 2, 0.0f, 1, f, 1, f2), j, z);
    }

    public static Animation createHorizontalAnimation(float f, float f2, long j, boolean z) {
        return setAnimationParams(new TranslateAnimation(1, f, 1, f2, 2, 0.0f, 2, 0.0f), j, z);
    }

    public static Animation setAnimationParams(Animation animation, long j, boolean z) {
        animation.setDuration(j);
        if (z) {
            animation.setInterpolator(sAccelerateInterpolator);
        } else {
            animation.setInterpolator(sDecelerateInterpolator);
        }
        return animation;
    }
}
