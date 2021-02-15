package com.google.firebase.inappmessaging.display.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class SwipeDismissTouchListener implements View.OnTouchListener {
    private long mAnimationTime;
    /* access modifiers changed from: private */
    public DismissCallbacks mDismissCallbacks;
    private float mDownX;
    private float mDownY;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;
    private int mSlop;
    private boolean mSwiping;
    private int mSwipingSlop;
    /* access modifiers changed from: private */
    public Object mToken;
    private float mTranslationX;
    private VelocityTracker mVelocityTracker;
    /* access modifiers changed from: private */
    public View mView;
    private int mViewWidth = 1;

    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    public interface DismissCallbacks {
        boolean canDismiss(Object obj);

        void onDismiss(View view, Object obj);
    }

    public SwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        this.mSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity() * 16;
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mAnimationTime = (long) view.getContext().getResources().getInteger(17694720);
        this.mView = view;
        this.mToken = obj;
        this.mDismissCallbacks = dismissCallbacks;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        int i;
        motionEvent.offsetLocation(this.mTranslationX, 0.0f);
        if (this.mViewWidth < 2) {
            this.mViewWidth = this.mView.getWidth();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            boolean z2 = true;
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    if (velocityTracker != null) {
                        velocityTracker.addMovement(motionEvent);
                        float rawX = motionEvent.getRawX() - this.mDownX;
                        float rawY = motionEvent.getRawY() - this.mDownY;
                        if (Math.abs(rawX) > ((float) this.mSlop) && Math.abs(rawY) < Math.abs(rawX) / 2.0f) {
                            this.mSwiping = true;
                            this.mSwipingSlop = rawX > 0.0f ? this.mSlop : -this.mSlop;
                            this.mView.getParent().requestDisallowInterceptTouchEvent(true);
                            MotionEvent obtain = MotionEvent.obtain(motionEvent);
                            obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                            this.mView.onTouchEvent(obtain);
                            obtain.recycle();
                        }
                        if (this.mSwiping) {
                            this.mTranslationX = rawX;
                            setTranslationX(rawX - ((float) this.mSwipingSlop));
                            setAlpha(Math.max(0.0f, Math.min(1.0f, 1.0f - ((Math.abs(rawX) * 2.0f) / ((float) this.mViewWidth)))));
                            return true;
                        }
                    }
                } else if (actionMasked == 3 && this.mVelocityTracker != null) {
                    startCancelAnimation();
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    this.mTranslationX = 0.0f;
                    this.mDownX = 0.0f;
                    this.mDownY = 0.0f;
                    this.mSwiping = false;
                }
            } else if (this.mVelocityTracker != null) {
                float rawX2 = motionEvent.getRawX() - this.mDownX;
                this.mVelocityTracker.addMovement(motionEvent);
                this.mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = this.mVelocityTracker.getXVelocity();
                float abs = Math.abs(xVelocity);
                float abs2 = Math.abs(this.mVelocityTracker.getYVelocity());
                if (Math.abs(rawX2) > ((float) (this.mViewWidth / 2)) && this.mSwiping) {
                    z = rawX2 > 0.0f;
                } else if (((float) this.mMinFlingVelocity) > abs || abs > ((float) this.mMaxFlingVelocity) || abs2 >= abs || i >= 0 || !this.mSwiping) {
                    z = false;
                    z2 = false;
                } else {
                    boolean z3 = ((xVelocity > 0.0f ? 1 : (xVelocity == 0.0f ? 0 : -1)) < 0) == ((rawX2 > 0.0f ? 1 : (rawX2 == 0.0f ? 0 : -1)) < 0);
                    if (this.mVelocityTracker.getXVelocity() <= 0.0f) {
                        z2 = false;
                    }
                    boolean z4 = z2;
                    z2 = z3;
                    z = z4;
                }
                if (z2) {
                    startDismissAnimation(z);
                } else if (this.mSwiping) {
                    startCancelAnimation();
                }
                VelocityTracker velocityTracker2 = this.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                }
                this.mVelocityTracker = null;
                this.mTranslationX = 0.0f;
                this.mDownX = 0.0f;
                this.mDownY = 0.0f;
                this.mSwiping = false;
            }
            return false;
        }
        this.mDownX = motionEvent.getRawX();
        this.mDownY = motionEvent.getRawY();
        if (this.mDismissCallbacks.canDismiss(this.mToken)) {
            VelocityTracker obtain2 = VelocityTracker.obtain();
            this.mVelocityTracker = obtain2;
            obtain2.addMovement(motionEvent);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void setTranslationX(float f) {
        this.mView.setTranslationX(f);
    }

    /* access modifiers changed from: protected */
    public float getTranslationX() {
        return this.mView.getTranslationX();
    }

    /* access modifiers changed from: protected */
    public void setAlpha(float f) {
        this.mView.setAlpha(f);
    }

    /* access modifiers changed from: protected */
    public void startDismissAnimation(boolean z) {
        animateTo((float) (z ? this.mViewWidth : -this.mViewWidth), 0.0f, new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SwipeDismissTouchListener.this.performDismiss();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void startCancelAnimation() {
        animateTo(0.0f, 1.0f, (AnimatorListenerAdapter) null);
    }

    private void animateTo(float f, float f2, AnimatorListenerAdapter animatorListenerAdapter) {
        final float translationX = getTranslationX();
        final float f3 = f - translationX;
        final float alpha = this.mView.getAlpha();
        final float f4 = f2 - alpha;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(this.mAnimationTime);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = translationX + (valueAnimator.getAnimatedFraction() * f3);
                float animatedFraction2 = alpha + (valueAnimator.getAnimatedFraction() * f4);
                SwipeDismissTouchListener.this.setTranslationX(animatedFraction);
                SwipeDismissTouchListener.this.setAlpha(animatedFraction2);
            }
        });
        if (animatorListenerAdapter != null) {
            ofFloat.addListener(animatorListenerAdapter);
        }
        ofFloat.start();
    }

    /* access modifiers changed from: private */
    public void performDismiss() {
        final ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
        final int height = this.mView.getHeight();
        ValueAnimator duration = ValueAnimator.ofInt(new int[]{height, 1}).setDuration(this.mAnimationTime);
        duration.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SwipeDismissTouchListener.this.mDismissCallbacks.onDismiss(SwipeDismissTouchListener.this.mView, SwipeDismissTouchListener.this.mToken);
                SwipeDismissTouchListener.this.mView.setAlpha(1.0f);
                SwipeDismissTouchListener.this.mView.setTranslationX(0.0f);
                layoutParams.height = height;
                SwipeDismissTouchListener.this.mView.setLayoutParams(layoutParams);
            }
        });
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                SwipeDismissTouchListener.this.mView.setLayoutParams(layoutParams);
            }
        });
        duration.start();
    }
}
