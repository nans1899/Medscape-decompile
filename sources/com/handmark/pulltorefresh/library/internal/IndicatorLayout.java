package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

public class IndicatorLayout extends FrameLayout implements Animation.AnimationListener {
    static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;
    private ImageView mArrowImageView;
    private Animation mInAnim;
    private Animation mOutAnim;
    private final Animation mResetRotateAnimation;
    private final Animation mRotateAnimation;

    public void onAnimationRepeat(Animation animation) {
    }

    public IndicatorLayout(Context context, PullToRefreshBase.Mode mode) {
        super(context);
        int i;
        int i2;
        this.mArrowImageView = new ImageView(context);
        Drawable drawable = getResources().getDrawable(R.drawable.indicator_arrow);
        this.mArrowImageView.setImageDrawable(drawable);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.indicator_internal_padding);
        this.mArrowImageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        addView(this.mArrowImageView);
        if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[mode.ordinal()] != 1) {
            i = R.anim.slide_in_from_top;
            i2 = R.anim.slide_out_to_top;
            setBackgroundResource(R.drawable.indicator_bg_top);
        } else {
            i = R.anim.slide_in_from_bottom;
            int i3 = R.anim.slide_out_to_bottom;
            setBackgroundResource(R.drawable.indicator_bg_bottom);
            this.mArrowImageView.setScaleType(ImageView.ScaleType.MATRIX);
            Matrix matrix = new Matrix();
            matrix.setRotate(180.0f, ((float) drawable.getIntrinsicWidth()) / 2.0f, ((float) drawable.getIntrinsicHeight()) / 2.0f);
            this.mArrowImageView.setImageMatrix(matrix);
            i2 = i3;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(context, i);
        this.mInAnim = loadAnimation;
        loadAnimation.setAnimationListener(this);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(context, i2);
        this.mOutAnim = loadAnimation2;
        loadAnimation2.setAnimationListener(this);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateAnimation = rotateAnimation;
        rotateAnimation.setInterpolator(linearInterpolator);
        this.mRotateAnimation.setDuration(150);
        this.mRotateAnimation.setFillAfter(true);
        RotateAnimation rotateAnimation2 = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mResetRotateAnimation = rotateAnimation2;
        rotateAnimation2.setInterpolator(linearInterpolator);
        this.mResetRotateAnimation.setDuration(150);
        this.mResetRotateAnimation.setFillAfter(true);
    }

    /* renamed from: com.handmark.pulltorefresh.library.internal.IndicatorLayout$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode[] r0 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = r0
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r1 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_END     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r1 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.handmark.pulltorefresh.library.internal.IndicatorLayout.AnonymousClass1.<clinit>():void");
        }
    }

    public final boolean isVisible() {
        Animation animation = getAnimation();
        if (animation != null) {
            if (this.mInAnim == animation) {
                return true;
            }
            return false;
        } else if (getVisibility() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void hide() {
        startAnimation(this.mOutAnim);
    }

    public void show() {
        this.mArrowImageView.clearAnimation();
        startAnimation(this.mInAnim);
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.mOutAnim) {
            this.mArrowImageView.clearAnimation();
            setVisibility(8);
        } else if (animation == this.mInAnim) {
            setVisibility(0);
        }
        clearAnimation();
    }

    public void onAnimationStart(Animation animation) {
        setVisibility(0);
    }

    public void releaseToRefresh() {
        this.mArrowImageView.startAnimation(this.mRotateAnimation);
    }

    public void pullToRefresh() {
        this.mArrowImageView.startAnimation(this.mResetRotateAnimation);
    }
}
