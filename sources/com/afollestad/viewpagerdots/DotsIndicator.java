package com.afollestad.viewpagerdots;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000=\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0017*\u0001\u0017\u0018\u0000 32\u00020\u0001:\u000234B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000b2\b\b\u0001\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\bH\u0002J\u0010\u0010\"\u001a\u00020\u001e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\b\u0010#\u001a\u00020\bH\u0002J\u0010\u0010$\u001a\n %*\u0004\u0018\u00010\b0\bH\u0002J\b\u0010&\u001a\u00020\u001eH\u0002J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u000bH\u0002J\b\u0010(\u001a\u00020\u000bH\u0002J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u000bH\u0002J\b\u0010+\u001a\u00020\u001eH\u0002J\u001a\u0010,\u001a\u00020\u001e2\b\b\u0001\u0010-\u001a\u00020\u000b2\b\b\u0003\u0010.\u001a\u00020\u000bJ\u0010\u0010/\u001a\u00020\u001e2\b\b\u0001\u00100\u001a\u00020\u000bJ\u0010\u00101\u001a\u00020\u001e2\b\b\u0001\u00102\u001a\u00020\u000bR\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/afollestad/viewpagerdots/DotsIndicator;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "animatorIn", "Landroid/animation/Animator;", "animatorOut", "animatorResId", "", "animatorReverseResId", "backgroundResId", "dotTint", "immediateAnimatorIn", "immediateAnimatorOut", "indicatorBackgroundResId", "indicatorHeight", "indicatorMargin", "indicatorUnselectedBackgroundResId", "indicatorWidth", "internalPageChangeListener", "com/afollestad/viewpagerdots/DotsIndicator$internalPageChangeListener$1", "Lcom/afollestad/viewpagerdots/DotsIndicator$internalPageChangeListener$1;", "lastPosition", "unselectedBackgroundId", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "addIndicator", "", "orientation", "drawableRes", "animator", "attachViewPager", "createAnimatorIn", "createAnimatorOut", "kotlin.jvm.PlatformType", "createIndicators", "count", "currentItem", "internalPageSelected", "position", "invalidateDots", "setDotDrawable", "indicatorRes", "unselectedIndicatorRes", "setDotTint", "tint", "setDotTintRes", "tintRes", "Companion", "ReverseInterpolator", "com.afollestad.viewpagerdots"}, k = 1, mv = {1, 1, 11})
/* compiled from: DotsIndicator.kt */
public final class DotsIndicator extends LinearLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DEFAULT_INDICATOR_WIDTH = 5;
    private Animator animatorIn;
    private Animator animatorOut;
    private int animatorResId;
    private int animatorReverseResId;
    private int backgroundResId;
    private int dotTint;
    private Animator immediateAnimatorIn;
    private Animator immediateAnimatorOut;
    private int indicatorBackgroundResId;
    private int indicatorHeight;
    private int indicatorMargin;
    private int indicatorUnselectedBackgroundResId;
    private int indicatorWidth;
    private final DotsIndicator$internalPageChangeListener$1 internalPageChangeListener;
    /* access modifiers changed from: private */
    public int lastPosition;
    private int unselectedBackgroundId;
    /* access modifiers changed from: private */
    public ViewPager viewPager;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DotsIndicator(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DotsIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.indicatorMargin = -1;
        this.indicatorWidth = -1;
        this.indicatorHeight = -1;
        this.lastPosition = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DotsIndicator);
        try {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DotsIndicator_dot_width, -1);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DotsIndicator_dot_height, -1);
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DotsIndicator_dot_margin, -1);
            int i = obtainStyledAttributes.getInt(R.styleable.DotsIndicator_dots_orientation, -1);
            int i2 = obtainStyledAttributes.getInt(R.styleable.DotsIndicator_dots_gravity, -1);
            this.animatorResId = obtainStyledAttributes.getResourceId(R.styleable.DotsIndicator_dots_animator, R.animator.scale_with_alpha);
            int i3 = 0;
            this.animatorReverseResId = obtainStyledAttributes.getResourceId(R.styleable.DotsIndicator_dots_animator_reverse, 0);
            this.backgroundResId = obtainStyledAttributes.getResourceId(R.styleable.DotsIndicator_dot_drawable, R.drawable.black_dot);
            this.unselectedBackgroundId = obtainStyledAttributes.getResourceId(R.styleable.DotsIndicator_dot_drawable_unselected, this.backgroundResId);
            this.dotTint = obtainStyledAttributes.getColor(R.styleable.DotsIndicator_dot_tint, 0);
            obtainStyledAttributes.recycle();
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            int applyDimension = (int) (TypedValue.applyDimension(1, (float) 5, resources.getDisplayMetrics()) + 0.5f);
            this.indicatorWidth = dimensionPixelSize < 0 ? applyDimension : dimensionPixelSize;
            this.indicatorHeight = dimensionPixelSize2 < 0 ? applyDimension : dimensionPixelSize2;
            this.indicatorMargin = dimensionPixelSize3 < 0 ? applyDimension : dimensionPixelSize3;
            Animator createAnimatorOut = createAnimatorOut();
            Intrinsics.checkExpressionValueIsNotNull(createAnimatorOut, "createAnimatorOut()");
            this.animatorOut = createAnimatorOut;
            Animator createAnimatorOut2 = createAnimatorOut();
            Intrinsics.checkExpressionValueIsNotNull(createAnimatorOut2, "createAnimatorOut()");
            this.immediateAnimatorOut = createAnimatorOut2;
            createAnimatorOut2.setDuration(0);
            this.animatorIn = createAnimatorIn();
            Animator createAnimatorIn = createAnimatorIn();
            this.immediateAnimatorIn = createAnimatorIn;
            createAnimatorIn.setDuration(0);
            int i4 = this.backgroundResId;
            this.indicatorBackgroundResId = i4 == 0 ? R.drawable.black_dot : i4;
            int i5 = this.unselectedBackgroundId;
            this.indicatorUnselectedBackgroundResId = i5 == 0 ? this.backgroundResId : i5;
            setOrientation(i == 1 ? 1 : i3);
            setGravity(i2 < 0 ? 17 : i2);
            this.internalPageChangeListener = new DotsIndicator$internalPageChangeListener$1(this);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public static /* bridge */ /* synthetic */ void setDotDrawable$default(DotsIndicator dotsIndicator, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = i;
        }
        dotsIndicator.setDotDrawable(i, i2);
    }

    public final void setDotDrawable(int i, int i2) {
        this.backgroundResId = i;
        this.unselectedBackgroundId = i2;
        invalidateDots();
    }

    public final void setDotTint(int i) {
        this.dotTint = i;
        invalidateDots();
    }

    public final void setDotTintRes(int i) {
        setDotTint(ContextCompat.getColor(getContext(), i));
    }

    public final void attachViewPager(ViewPager viewPager2) {
        this.viewPager = viewPager2;
        if (viewPager2 != null && viewPager2.getAdapter() != null) {
            this.lastPosition = -1;
            createIndicators();
            viewPager2.removeOnPageChangeListener(this.internalPageChangeListener);
            viewPager2.addOnPageChangeListener(this.internalPageChangeListener);
            this.internalPageChangeListener.onPageSelected(viewPager2.getCurrentItem());
        }
    }

    private final void invalidateDots() {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            Drawable drawable = ContextCompat.getDrawable(getContext(), currentItem() == i ? this.indicatorBackgroundResId : this.indicatorUnselectedBackgroundResId);
            int i2 = this.dotTint;
            if (i2 != 0) {
                drawable = drawable != null ? UtilKt.tint(drawable, i2) : null;
            }
            Intrinsics.checkExpressionValueIsNotNull(childAt, "indicator");
            childAt.setBackground(drawable);
            i++;
        }
    }

    private final void createIndicators(int i) {
        int i2 = 0;
        while (i2 < i) {
            addIndicator(getOrientation(), currentItem() == i2 ? this.indicatorBackgroundResId : this.indicatorUnselectedBackgroundResId, currentItem() == i2 ? this.immediateAnimatorOut : this.immediateAnimatorIn);
            i2++;
        }
    }

    /* access modifiers changed from: private */
    public final void internalPageSelected(int i) {
        if (this.animatorIn.isRunning()) {
            this.animatorIn.end();
            this.animatorIn.cancel();
        }
        if (this.animatorOut.isRunning()) {
            this.animatorOut.end();
            this.animatorOut.cancel();
        }
        int i2 = this.lastPosition;
        View childAt = i2 >= 0 ? getChildAt(i2) : null;
        if (childAt != null) {
            childAt.setBackgroundResource(this.indicatorUnselectedBackgroundResId);
            this.animatorIn.setTarget(childAt);
            this.animatorIn.start();
        }
        View childAt2 = getChildAt(i);
        if (childAt2 != null) {
            childAt2.setBackgroundResource(this.indicatorBackgroundResId);
            this.animatorOut.setTarget(childAt2);
            this.animatorOut.start();
        }
    }

    private final void createIndicators() {
        removeAllViews();
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwNpe();
        }
        PagerAdapter adapter = viewPager2.getAdapter();
        int count = adapter != null ? adapter.getCount() : 0;
        if (count > 0) {
            createIndicators(count);
        }
    }

    private final void addIndicator(int i, int i2, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }
        View view = new View(getContext());
        Drawable drawable = ContextCompat.getDrawable(getContext(), i2);
        int i3 = this.dotTint;
        if (i3 != 0) {
            drawable = drawable != null ? UtilKt.tint(drawable, i3) : null;
        }
        view.setBackground(drawable);
        addView(view, this.indicatorWidth, this.indicatorHeight);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            if (i == 0) {
                layoutParams2.leftMargin = this.indicatorMargin;
                layoutParams2.rightMargin = this.indicatorMargin;
            } else {
                layoutParams2.topMargin = this.indicatorMargin;
                layoutParams2.bottomMargin = this.indicatorMargin;
            }
            view.setLayoutParams(layoutParams2);
            animator.setTarget(view);
            animator.start();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
    }

    private final Animator createAnimatorOut() {
        return AnimatorInflater.loadAnimator(getContext(), this.animatorResId);
    }

    private final Animator createAnimatorIn() {
        if (this.animatorReverseResId == 0) {
            Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), this.animatorResId);
            Intrinsics.checkExpressionValueIsNotNull(loadAnimator, "loadAnimator(context, this.animatorResId)");
            loadAnimator.setInterpolator(new ReverseInterpolator());
            return loadAnimator;
        }
        Animator loadAnimator2 = AnimatorInflater.loadAnimator(getContext(), this.animatorReverseResId);
        Intrinsics.checkExpressionValueIsNotNull(loadAnimator2, "loadAnimator(context, this.animatorReverseResId)");
        return loadAnimator2;
    }

    private final int currentItem() {
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            return viewPager2.getCurrentItem();
        }
        return -1;
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/afollestad/viewpagerdots/DotsIndicator$ReverseInterpolator;", "Landroid/view/animation/Interpolator;", "(Lcom/afollestad/viewpagerdots/DotsIndicator;)V", "getInterpolation", "", "value", "com.afollestad.viewpagerdots"}, k = 1, mv = {1, 1, 11})
    /* compiled from: DotsIndicator.kt */
    private final class ReverseInterpolator implements Interpolator {
        public ReverseInterpolator() {
        }

        public float getInterpolation(float f) {
            return Math.abs(1.0f - f);
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/afollestad/viewpagerdots/DotsIndicator$Companion;", "", "()V", "DEFAULT_INDICATOR_WIDTH", "", "com.afollestad.viewpagerdots"}, k = 1, mv = {1, 1, 11})
    /* compiled from: DotsIndicator.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
