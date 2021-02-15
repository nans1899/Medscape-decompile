package com.wbmd.qxcalculator.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.wbmd.qxcalculator.R;

public class ArrowView extends View {
    private static final int K_ARROW_EXPAND_DURATION = 200;
    public int color = ViewCompat.MEASURED_STATE_MASK;
    private Paint paint;
    private Path path;

    public ArrowView(Context context) {
        super(context);
    }

    public ArrowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    public ArrowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public void setColor(int i) {
        this.color = i;
        this.paint.setColor(i);
        invalidate();
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ArrowView, i, 0);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.ArrowView_arrow_color);
        if (colorStateList != null) {
            this.color = colorStateList.getColorForState(getDrawableState(), 0);
        }
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setColor(this.color);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        createPath();
    }

    private void createPath() {
        int width = getWidth();
        int height = getHeight();
        Path path2 = new Path();
        this.path = path2;
        path2.moveTo(0.0f, 0.0f);
        this.path.lineTo((float) width, (float) (height / 2));
        this.path.lineTo(0.0f, (float) height);
        this.path.lineTo(0.0f, 0.0f);
        this.path.close();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.path == null) {
            createPath();
        }
        canvas.drawPath(this.path, this.paint);
    }

    public void expandArrow(boolean z, boolean z2) {
        float f = 0.0f;
        float f2 = 90.0f;
        if (z) {
            f = 90.0f;
            f2 = 0.0f;
        }
        if (z2) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f2, f});
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ArrowView.this.setRotation(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            ofFloat.setDuration(200);
            ofFloat.start();
            return;
        }
        setRotation(f);
    }
}
