package com.facebook.shimmer;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public final class ShimmerDrawable extends Drawable {
    private final Rect mDrawRect = new Rect();
    private final Matrix mShaderMatrix = new Matrix();
    private Shimmer mShimmer;
    private final Paint mShimmerPaint = new Paint();
    private final ValueAnimator.AnimatorUpdateListener mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ShimmerDrawable.this.invalidateSelf();
        }
    };
    private ValueAnimator mValueAnimator;

    private float offset(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public ShimmerDrawable() {
        this.mShimmerPaint.setAntiAlias(true);
    }

    public void setShimmer(Shimmer shimmer) {
        this.mShimmer = shimmer;
        if (shimmer != null) {
            this.mShimmerPaint.setXfermode(new PorterDuffXfermode(this.mShimmer.alphaShimmer ? PorterDuff.Mode.DST_IN : PorterDuff.Mode.SRC_IN));
        }
        updateShader();
        updateValueAnimator();
        invalidateSelf();
    }

    public void startShimmer() {
        if (this.mValueAnimator != null && !isShimmerStarted() && getCallback() != null) {
            this.mValueAnimator.start();
        }
    }

    public void stopShimmer() {
        if (this.mValueAnimator != null && isShimmerStarted()) {
            this.mValueAnimator.cancel();
        }
    }

    public boolean isShimmerStarted() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        return valueAnimator != null && valueAnimator.isStarted();
    }

    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mDrawRect.set(0, 0, rect.width(), rect.height());
        updateShader();
        maybeStartShimmer();
    }

    public void draw(Canvas canvas) {
        float f;
        float offset;
        if (this.mShimmer != null && this.mShimmerPaint.getShader() != null) {
            float tan = (float) Math.tan(Math.toRadians((double) this.mShimmer.tilt));
            float height = ((float) this.mDrawRect.height()) + (((float) this.mDrawRect.width()) * tan);
            float width = ((float) this.mDrawRect.width()) + (tan * ((float) this.mDrawRect.height()));
            ValueAnimator valueAnimator = this.mValueAnimator;
            float f2 = 0.0f;
            float animatedFraction = valueAnimator != null ? valueAnimator.getAnimatedFraction() : 0.0f;
            int i = this.mShimmer.direction;
            if (i != 1) {
                if (i == 2) {
                    offset = offset(width, -width, animatedFraction);
                } else if (i != 3) {
                    offset = offset(-width, width, animatedFraction);
                } else {
                    f = offset(height, -height, animatedFraction);
                }
                f2 = offset;
                f = 0.0f;
            } else {
                f = offset(-height, height, animatedFraction);
            }
            this.mShaderMatrix.reset();
            this.mShaderMatrix.setRotate(this.mShimmer.tilt, ((float) this.mDrawRect.width()) / 2.0f, ((float) this.mDrawRect.height()) / 2.0f);
            this.mShaderMatrix.postTranslate(f2, f);
            this.mShimmerPaint.getShader().setLocalMatrix(this.mShaderMatrix);
            canvas.drawRect(this.mDrawRect, this.mShimmerPaint);
        }
    }

    public int getOpacity() {
        Shimmer shimmer = this.mShimmer;
        return (shimmer == null || (!shimmer.clipToChildren && !this.mShimmer.alphaShimmer)) ? -1 : -3;
    }

    private void updateValueAnimator() {
        boolean z;
        if (this.mShimmer != null) {
            ValueAnimator valueAnimator = this.mValueAnimator;
            if (valueAnimator != null) {
                z = valueAnimator.isStarted();
                this.mValueAnimator.cancel();
                this.mValueAnimator.removeAllUpdateListeners();
            } else {
                z = false;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, ((float) (this.mShimmer.repeatDelay / this.mShimmer.animationDuration)) + 1.0f});
            this.mValueAnimator = ofFloat;
            ofFloat.setRepeatMode(this.mShimmer.repeatMode);
            this.mValueAnimator.setRepeatCount(this.mShimmer.repeatCount);
            this.mValueAnimator.setDuration(this.mShimmer.animationDuration + this.mShimmer.repeatDelay);
            this.mValueAnimator.addUpdateListener(this.mUpdateListener);
            if (z) {
                this.mValueAnimator.start();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeStartShimmer() {
        Shimmer shimmer;
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null && !valueAnimator.isStarted() && (shimmer = this.mShimmer) != null && shimmer.autoStart && getCallback() != null) {
            this.mValueAnimator.start();
        }
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [android.graphics.Shader] */
    /* JADX WARNING: type inference failed for: r12v1, types: [android.graphics.RadialGradient] */
    /* JADX WARNING: type inference failed for: r3v13, types: [android.graphics.LinearGradient] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateShader() {
        /*
            r19 = this;
            r0 = r19
            android.graphics.Rect r1 = r19.getBounds()
            int r2 = r1.width()
            int r1 = r1.height()
            if (r2 == 0) goto L_0x0082
            if (r1 == 0) goto L_0x0082
            com.facebook.shimmer.Shimmer r3 = r0.mShimmer
            if (r3 != 0) goto L_0x0017
            goto L_0x0082
        L_0x0017:
            int r2 = r3.width(r2)
            com.facebook.shimmer.Shimmer r3 = r0.mShimmer
            int r1 = r3.height(r1)
            com.facebook.shimmer.Shimmer r3 = r0.mShimmer
            int r3 = r3.shape
            r4 = 1
            if (r3 == r4) goto L_0x0054
            com.facebook.shimmer.Shimmer r3 = r0.mShimmer
            int r3 = r3.direction
            r5 = 0
            if (r3 == r4) goto L_0x0038
            com.facebook.shimmer.Shimmer r3 = r0.mShimmer
            int r3 = r3.direction
            r6 = 3
            if (r3 != r6) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r4 = 0
        L_0x0038:
            if (r4 == 0) goto L_0x003b
            r2 = 0
        L_0x003b:
            if (r4 == 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r1 = 0
        L_0x003f:
            android.graphics.LinearGradient r11 = new android.graphics.LinearGradient
            r4 = 0
            r5 = 0
            float r6 = (float) r2
            float r7 = (float) r1
            com.facebook.shimmer.Shimmer r1 = r0.mShimmer
            int[] r8 = r1.colors
            com.facebook.shimmer.Shimmer r1 = r0.mShimmer
            float[] r9 = r1.positions
            android.graphics.Shader$TileMode r10 = android.graphics.Shader.TileMode.CLAMP
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            goto L_0x007d
        L_0x0054:
            android.graphics.RadialGradient r11 = new android.graphics.RadialGradient
            float r3 = (float) r2
            r4 = 1073741824(0x40000000, float:2.0)
            float r13 = r3 / r4
            float r3 = (float) r1
            float r14 = r3 / r4
            int r1 = java.lang.Math.max(r2, r1)
            double r1 = (double) r1
            r3 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r3 = java.lang.Math.sqrt(r3)
            double r1 = r1 / r3
            float r15 = (float) r1
            com.facebook.shimmer.Shimmer r1 = r0.mShimmer
            int[] r1 = r1.colors
            com.facebook.shimmer.Shimmer r2 = r0.mShimmer
            float[] r2 = r2.positions
            android.graphics.Shader$TileMode r18 = android.graphics.Shader.TileMode.CLAMP
            r12 = r11
            r16 = r1
            r17 = r2
            r12.<init>(r13, r14, r15, r16, r17, r18)
        L_0x007d:
            android.graphics.Paint r1 = r0.mShimmerPaint
            r1.setShader(r11)
        L_0x0082:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.shimmer.ShimmerDrawable.updateShader():void");
    }
}
