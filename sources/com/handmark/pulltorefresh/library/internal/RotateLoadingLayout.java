package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

public class RotateLoadingLayout extends LoadingLayout {
    static final int ROTATION_ANIMATION_DURATION = 1200;
    private final Matrix mHeaderImageMatrix = new Matrix();
    private final Animation mRotateAnimation;
    private final boolean mRotateDrawableWhilePulling;
    private float mRotationPivotX;
    private float mRotationPivotY;

    /* access modifiers changed from: protected */
    public void pullToRefreshImpl() {
    }

    /* access modifiers changed from: protected */
    public void releaseToRefreshImpl() {
    }

    public RotateLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation orientation, TypedArray typedArray) {
        super(context, mode, orientation, typedArray);
        this.mRotateDrawableWhilePulling = typedArray.getBoolean(R.styleable.PullToRefresh_ptrRotateDrawableWhilePulling, true);
        this.mHeaderImage.setScaleType(ImageView.ScaleType.MATRIX);
        this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 720.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateAnimation = rotateAnimation;
        rotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        this.mRotateAnimation.setDuration(1200);
        this.mRotateAnimation.setRepeatCount(-1);
        this.mRotateAnimation.setRepeatMode(1);
    }

    public void onLoadingDrawableSet(Drawable drawable) {
        if (drawable != null) {
            this.mRotationPivotX = (float) Math.round(((float) drawable.getIntrinsicWidth()) / 2.0f);
            this.mRotationPivotY = (float) Math.round(((float) drawable.getIntrinsicHeight()) / 2.0f);
        }
    }

    /* access modifiers changed from: protected */
    public void onPullImpl(float f) {
        float f2;
        if (this.mRotateDrawableWhilePulling) {
            f2 = f * 90.0f;
        } else {
            f2 = Math.max(0.0f, Math.min(180.0f, (f * 360.0f) - 180.0f));
        }
        this.mHeaderImageMatrix.setRotate(f2, this.mRotationPivotX, this.mRotationPivotY);
        this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
    }

    /* access modifiers changed from: protected */
    public void refreshingImpl() {
        this.mHeaderImage.startAnimation(this.mRotateAnimation);
    }

    /* access modifiers changed from: protected */
    public void resetImpl() {
        this.mHeaderImage.clearAnimation();
        resetImageRotation();
    }

    private void resetImageRotation() {
        Matrix matrix = this.mHeaderImageMatrix;
        if (matrix != null) {
            matrix.reset();
            this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultDrawableResId() {
        return R.drawable.default_ptr_rotate;
    }
}
