package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.Iterator;

public class StickyScrollView extends ScrollView {
    private static final int DEFAULT_SHADOW_HEIGHT = 10;
    public static final String FLAG_HASTRANSPARANCY = "-hastransparancy";
    public static final String FLAG_NONCONSTANT = "-nonconstant";
    public static final String STICKY_TAG = "sticky";
    private boolean clipToPaddingHasBeenSet;
    private boolean clippingToPadding;
    /* access modifiers changed from: private */
    public View currentlyStickingView;
    private boolean hasNotDoneActionDown;
    private final Runnable invalidateRunnable;
    private Drawable mShadowDrawable;
    private int mShadowHeight;
    private boolean redirectTouchesToStickyView;
    private int stickyViewLeftOffset;
    /* access modifiers changed from: private */
    public float stickyViewTopOffset;
    private ArrayList<View> stickyViews;

    public StickyScrollView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StickyScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842880);
    }

    public StickyScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.invalidateRunnable = new Runnable() {
            public void run() {
                if (StickyScrollView.this.currentlyStickingView != null) {
                    StickyScrollView stickyScrollView = StickyScrollView.this;
                    int access$100 = stickyScrollView.getLeftForViewRelativeOnlyChild(stickyScrollView.currentlyStickingView);
                    StickyScrollView stickyScrollView2 = StickyScrollView.this;
                    int access$200 = stickyScrollView2.getBottomForViewRelativeOnlyChild(stickyScrollView2.currentlyStickingView);
                    StickyScrollView stickyScrollView3 = StickyScrollView.this;
                    StickyScrollView.this.invalidate(access$100, access$200, stickyScrollView3.getRightForViewRelativeOnlyChild(stickyScrollView3.currentlyStickingView), (int) (((float) StickyScrollView.this.getScrollY()) + ((float) StickyScrollView.this.currentlyStickingView.getHeight()) + StickyScrollView.this.stickyViewTopOffset));
                }
                StickyScrollView.this.postDelayed(this, 16);
            }
        };
        this.hasNotDoneActionDown = true;
        setup();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StickyScrollView, i, 0);
        this.mShadowHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.StickyScrollView_stuckShadowHeight, (int) ((context.getResources().getDisplayMetrics().density * 10.0f) + 0.5f));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.StickyScrollView_stuckShadowDrawable, -1);
        if (resourceId != -1) {
            this.mShadowDrawable = context.getResources().getDrawable(resourceId);
        }
        obtainStyledAttributes.recycle();
    }

    public void setShadowHeight(int i) {
        this.mShadowHeight = i;
    }

    public void setup() {
        this.stickyViews = new ArrayList<>();
    }

    /* access modifiers changed from: private */
    public int getLeftForViewRelativeOnlyChild(View view) {
        int left = view.getLeft();
        while (view.getParent() != getChildAt(0)) {
            view = (View) view.getParent();
            left += view.getLeft();
        }
        return left;
    }

    private int getTopForViewRelativeOnlyChild(View view) {
        int top = view.getTop();
        while (view != null && view.getParent() != getChildAt(0)) {
            view = (View) view.getParent();
            if (view != null) {
                top += view.getTop();
            }
        }
        return top;
    }

    /* access modifiers changed from: private */
    public int getRightForViewRelativeOnlyChild(View view) {
        int right = view.getRight();
        while (view.getParent() != getChildAt(0)) {
            view = (View) view.getParent();
            right += view.getRight();
        }
        return right;
    }

    /* access modifiers changed from: private */
    public int getBottomForViewRelativeOnlyChild(View view) {
        int bottom = view.getBottom();
        while (view.getParent() != getChildAt(0)) {
            view = (View) view.getParent();
            bottom += view.getBottom();
        }
        return bottom;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!this.clipToPaddingHasBeenSet) {
            this.clippingToPadding = true;
        }
        notifyHierarchyChanged();
    }

    public void setClipToPadding(boolean z) {
        super.setClipToPadding(z);
        this.clippingToPadding = z;
        this.clipToPaddingHasBeenSet = true;
    }

    public void addView(View view) {
        super.addView(view);
        findStickyViews(view);
    }

    public void addView(View view, int i) {
        super.addView(view, i);
        findStickyViews(view);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        findStickyViews(view);
    }

    public void addView(View view, int i, int i2) {
        super.addView(view, i, i2);
        findStickyViews(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, layoutParams);
        findStickyViews(view);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.currentlyStickingView != null) {
            canvas.save();
            canvas.translate((float) (getPaddingLeft() + this.stickyViewLeftOffset), ((float) getScrollY()) + this.stickyViewTopOffset + ((float) (this.clippingToPadding ? getPaddingTop() : 0)));
            canvas.clipRect(0.0f, this.clippingToPadding ? -this.stickyViewTopOffset : 0.0f, (float) (getWidth() - this.stickyViewLeftOffset), (float) (this.currentlyStickingView.getHeight() + this.mShadowHeight + 1));
            if (this.mShadowDrawable != null) {
                this.mShadowDrawable.setBounds(0, this.currentlyStickingView.getHeight(), this.currentlyStickingView.getWidth(), this.currentlyStickingView.getHeight() + this.mShadowHeight);
                this.mShadowDrawable.draw(canvas);
            }
            canvas.clipRect(0.0f, this.clippingToPadding ? -this.stickyViewTopOffset : 0.0f, (float) getWidth(), (float) this.currentlyStickingView.getHeight());
            if (getStringTagForView(this.currentlyStickingView).contains(FLAG_HASTRANSPARANCY)) {
                showView(this.currentlyStickingView);
                this.currentlyStickingView.draw(canvas);
                hideView(this.currentlyStickingView);
            } else {
                this.currentlyStickingView.draw(canvas);
            }
            canvas.restore();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getAction() == 0) {
            this.redirectTouchesToStickyView = true;
        }
        if (this.redirectTouchesToStickyView) {
            boolean z2 = this.currentlyStickingView != null;
            this.redirectTouchesToStickyView = z2;
            if (z2) {
                if (motionEvent.getY() > ((float) this.currentlyStickingView.getHeight()) + this.stickyViewTopOffset || motionEvent.getX() < ((float) getLeftForViewRelativeOnlyChild(this.currentlyStickingView)) || motionEvent.getX() > ((float) getRightForViewRelativeOnlyChild(this.currentlyStickingView))) {
                    z = false;
                }
                this.redirectTouchesToStickyView = z;
            }
        } else if (this.currentlyStickingView == null) {
            this.redirectTouchesToStickyView = false;
        }
        if (this.redirectTouchesToStickyView) {
            motionEvent.offsetLocation(0.0f, ((((float) getScrollY()) + this.stickyViewTopOffset) - ((float) getTopForViewRelativeOnlyChild(this.currentlyStickingView))) * -1.0f);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.redirectTouchesToStickyView) {
            motionEvent.offsetLocation(0.0f, (((float) getScrollY()) + this.stickyViewTopOffset) - ((float) getTopForViewRelativeOnlyChild(this.currentlyStickingView)));
        }
        if (motionEvent.getAction() == 0) {
            this.hasNotDoneActionDown = false;
        }
        if (this.hasNotDoneActionDown) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(0);
            super.onTouchEvent(obtain);
            this.hasNotDoneActionDown = false;
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.hasNotDoneActionDown = true;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        doTheStickyThing();
    }

    private void doTheStickyThing() {
        float f;
        Iterator<View> it = this.stickyViews.iterator();
        View view = null;
        View view2 = null;
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                break;
            }
            View next = it.next();
            int topForViewRelativeOnlyChild = (getTopForViewRelativeOnlyChild(next) - getScrollY()) + (this.clippingToPadding ? 0 : getPaddingTop());
            if (topForViewRelativeOnlyChild <= 0) {
                if (view != null) {
                    int topForViewRelativeOnlyChild2 = getTopForViewRelativeOnlyChild(view) - getScrollY();
                    if (!this.clippingToPadding) {
                        i = getPaddingTop();
                    }
                    if (topForViewRelativeOnlyChild <= topForViewRelativeOnlyChild2 + i) {
                    }
                }
                view = next;
            } else {
                if (view2 != null) {
                    int topForViewRelativeOnlyChild3 = getTopForViewRelativeOnlyChild(view2) - getScrollY();
                    if (!this.clippingToPadding) {
                        i = getPaddingTop();
                    }
                    if (topForViewRelativeOnlyChild >= topForViewRelativeOnlyChild3 + i) {
                    }
                }
                view2 = next;
            }
        }
        if (view != null) {
            if (view2 == null) {
                f = 0.0f;
            } else {
                f = (float) Math.min(0, ((getTopForViewRelativeOnlyChild(view2) - getScrollY()) + (this.clippingToPadding ? 0 : getPaddingTop())) - view.getHeight());
            }
            this.stickyViewTopOffset = f;
            View view3 = this.currentlyStickingView;
            if (view != view3) {
                if (view3 != null) {
                    stopStickingCurrentlyStickingView();
                }
                this.stickyViewLeftOffset = getLeftForViewRelativeOnlyChild(view);
                startStickingView(view);
            }
        } else if (this.currentlyStickingView != null) {
            stopStickingCurrentlyStickingView();
        }
    }

    private void startStickingView(View view) {
        this.currentlyStickingView = view;
        if (getStringTagForView(view).contains(FLAG_HASTRANSPARANCY)) {
            hideView(this.currentlyStickingView);
        }
        if (((String) this.currentlyStickingView.getTag()).contains(FLAG_NONCONSTANT)) {
            post(this.invalidateRunnable);
        }
    }

    private void stopStickingCurrentlyStickingView() {
        if (getStringTagForView(this.currentlyStickingView).contains(FLAG_HASTRANSPARANCY)) {
            showView(this.currentlyStickingView);
        }
        this.currentlyStickingView = null;
        removeCallbacks(this.invalidateRunnable);
    }

    public void notifyStickyAttributeChanged() {
        notifyHierarchyChanged();
    }

    private void notifyHierarchyChanged() {
        if (this.currentlyStickingView != null) {
            stopStickingCurrentlyStickingView();
        }
        this.stickyViews.clear();
        findStickyViews(getChildAt(0));
        doTheStickyThing();
        invalidate();
    }

    private void findStickyViews(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                String stringTagForView = getStringTagForView(viewGroup.getChildAt(i));
                if (stringTagForView != null && stringTagForView.contains(STICKY_TAG)) {
                    this.stickyViews.add(viewGroup.getChildAt(i));
                } else if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                    findStickyViews(viewGroup.getChildAt(i));
                }
            }
            return;
        }
        String str = (String) view.getTag();
        if (str != null && str.contains(STICKY_TAG)) {
            this.stickyViews.add(view);
        }
    }

    private String getStringTagForView(View view) {
        return String.valueOf(view.getTag());
    }

    private void hideView(View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setAlpha(0.0f);
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    private void showView(View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setAlpha(1.0f);
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }
}
