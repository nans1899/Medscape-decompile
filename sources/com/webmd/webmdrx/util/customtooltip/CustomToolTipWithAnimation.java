package com.webmd.webmdrx.util.customtooltip;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.webmd.webmdrx.R;

public class CustomToolTipWithAnimation implements PopupWindow.OnDismissListener {
    private static final String TAG = "CustomToolTip";
    private static final int mDefaultPopupWindowStyleRes = 16842870;
    /* access modifiers changed from: private */
    public boolean dismissed;
    /* access modifiers changed from: private */
    public final View mAnchorView;
    /* access modifiers changed from: private */
    public final boolean mAnimated;
    private final float mAnimationDuration;
    /* access modifiers changed from: private */
    public final ViewTreeObserver.OnGlobalLayoutListener mAnimationLayoutListener;
    private final float mAnimationPadding;
    private AnimatorSet mAnimator;
    /* access modifiers changed from: private */
    public final int mArrowDirection;
    private final Drawable mArrowDrawable;
    private final float mArrowHeight;
    /* access modifiers changed from: private */
    public final ViewTreeObserver.OnGlobalLayoutListener mArrowLayoutListener;
    /* access modifiers changed from: private */
    public ImageView mArrowView;
    private final float mArrowWidth;
    private final ViewTreeObserver.OnGlobalLayoutListener mAutoDismissLayoutListener;
    /* access modifiers changed from: private */
    public View mContentLayout;
    /* access modifiers changed from: private */
    public final View mContentView;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final boolean mDismissOnInsideTouch;
    /* access modifiers changed from: private */
    public final boolean mDismissOnOutsideTouch;
    private final boolean mFocusable;
    private final int mGravity;
    private final ViewTreeObserver.OnGlobalLayoutListener mLocationLayoutListener;
    private final float mMargin;
    /* access modifiers changed from: private */
    public final float mMaxWidth;
    private OnDismissListener mOnDismissListener;
    /* access modifiers changed from: private */
    public OnShowListener mOnShowListener;
    private View mOverlay;
    private final View.OnTouchListener mOverlayTouchListener;
    private final float mPadding;
    /* access modifiers changed from: private */
    public PopupWindow mPopupWindow;
    private final View.OnTouchListener mPopupWindowTouchListener;
    /* access modifiers changed from: private */
    public ViewGroup mRootView;
    /* access modifiers changed from: private */
    public final boolean mShowArrow;
    /* access modifiers changed from: private */
    public final ViewTreeObserver.OnGlobalLayoutListener mShowLayoutListener;
    private final CharSequence mText;
    private final int mTextViewId;

    public interface OnDismissListener {
        void onDismiss(CustomToolTipWithAnimation customToolTipWithAnimation);
    }

    public interface OnShowListener {
        void onShow(CustomToolTipWithAnimation customToolTipWithAnimation);
    }

    private CustomToolTipWithAnimation(Builder builder) {
        this.dismissed = false;
        this.mPopupWindowTouchListener = new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getX() <= 0.0f || motionEvent.getX() >= ((float) view.getWidth()) || motionEvent.getY() <= 0.0f || motionEvent.getY() >= ((float) view.getHeight())) {
                    if (motionEvent.getAction() == 1) {
                        view.performClick();
                    }
                    return false;
                }
                if (CustomToolTipWithAnimation.this.mDismissOnInsideTouch) {
                    CustomToolTipWithAnimation.this.dismiss();
                }
                return false;
            }
        };
        this.mLocationLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PopupWindow access$2300 = CustomToolTipWithAnimation.this.mPopupWindow;
                if (access$2300 != null && !CustomToolTipWithAnimation.this.dismissed) {
                    if (CustomToolTipWithAnimation.this.mMaxWidth <= 0.0f || ((float) CustomToolTipWithAnimation.this.mContentView.getWidth()) <= CustomToolTipWithAnimation.this.mMaxWidth) {
                        if (CustomToolTipWithAnimation.this.mMaxWidth == -1.0f) {
                            CustomToolTipUtil.setWidth(CustomToolTipWithAnimation.this.mContentView, (float) CustomToolTipWithAnimation.this.mAnchorView.getWidth());
                            access$2300.update(-2, -2);
                        }
                        CustomToolTipUtil.removeOnGlobalLayoutListener(access$2300.getContentView(), this);
                        access$2300.getContentView().getViewTreeObserver().addOnGlobalLayoutListener(CustomToolTipWithAnimation.this.mArrowLayoutListener);
                        PointF access$3000 = CustomToolTipWithAnimation.this.calculePopupLocation();
                        access$2300.setClippingEnabled(true);
                        access$2300.update((int) access$3000.x, (int) access$3000.y, access$2300.getWidth(), access$2300.getHeight());
                        access$2300.getContentView().requestLayout();
                        CustomToolTipWithAnimation.this.createOverlay();
                        return;
                    }
                    CustomToolTipUtil.setWidth(CustomToolTipWithAnimation.this.mContentView, CustomToolTipWithAnimation.this.mMaxWidth);
                    access$2300.update(-2, -2);
                }
            }
        };
        this.mArrowLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                float f;
                float f2;
                PopupWindow access$2300 = CustomToolTipWithAnimation.this.mPopupWindow;
                if (access$2300 != null && !CustomToolTipWithAnimation.this.dismissed) {
                    CustomToolTipUtil.removeOnGlobalLayoutListener(access$2300.getContentView(), this);
                    access$2300.getContentView().getViewTreeObserver().addOnGlobalLayoutListener(CustomToolTipWithAnimation.this.mAnimationLayoutListener);
                    access$2300.getContentView().getViewTreeObserver().addOnGlobalLayoutListener(CustomToolTipWithAnimation.this.mShowLayoutListener);
                    if (CustomToolTipWithAnimation.this.mShowArrow) {
                        RectF calculeRectOnScreen = CustomToolTipUtil.calculeRectOnScreen(CustomToolTipWithAnimation.this.mAnchorView);
                        RectF calculeRectOnScreen2 = CustomToolTipUtil.calculeRectOnScreen(CustomToolTipWithAnimation.this.mContentLayout);
                        int i = -1;
                        if (CustomToolTipWithAnimation.this.mArrowDirection == 1 || CustomToolTipWithAnimation.this.mArrowDirection == 3) {
                            float paddingLeft = ((float) CustomToolTipWithAnimation.this.mContentLayout.getPaddingLeft()) + CustomToolTipUtil.pxFromDp(2.0f);
                            float width = ((calculeRectOnScreen2.width() / 2.0f) - (((float) CustomToolTipWithAnimation.this.mArrowView.getWidth()) / 2.0f)) - (calculeRectOnScreen2.centerX() - calculeRectOnScreen.centerX());
                            f2 = width > paddingLeft ? (((float) CustomToolTipWithAnimation.this.mArrowView.getWidth()) + width) + paddingLeft > calculeRectOnScreen2.width() ? (calculeRectOnScreen2.width() - ((float) CustomToolTipWithAnimation.this.mArrowView.getWidth())) - paddingLeft : width : paddingLeft;
                            float top = (float) CustomToolTipWithAnimation.this.mArrowView.getTop();
                            if (CustomToolTipWithAnimation.this.mArrowDirection != 3) {
                                i = 1;
                            }
                            f = ((float) i) + top;
                        } else {
                            f = ((float) CustomToolTipWithAnimation.this.mContentLayout.getPaddingTop()) + CustomToolTipUtil.pxFromDp(2.0f);
                            float height = ((calculeRectOnScreen2.height() / 2.0f) - (((float) CustomToolTipWithAnimation.this.mArrowView.getHeight()) / 2.0f)) - (calculeRectOnScreen2.centerY() - calculeRectOnScreen.centerY());
                            if (height > f) {
                                f = (((float) CustomToolTipWithAnimation.this.mArrowView.getHeight()) + height) + f > calculeRectOnScreen2.height() ? (calculeRectOnScreen2.height() - ((float) CustomToolTipWithAnimation.this.mArrowView.getHeight())) - f : height;
                            }
                            float left = (float) CustomToolTipWithAnimation.this.mArrowView.getLeft();
                            if (CustomToolTipWithAnimation.this.mArrowDirection != 2) {
                                i = 1;
                            }
                            f2 = left + ((float) i);
                        }
                        CustomToolTipUtil.setX(CustomToolTipWithAnimation.this.mArrowView, (int) f2);
                        CustomToolTipUtil.setY(CustomToolTipWithAnimation.this.mArrowView, (int) f);
                    }
                    access$2300.getContentView().requestLayout();
                }
            }
        };
        this.mShowLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PopupWindow access$2300 = CustomToolTipWithAnimation.this.mPopupWindow;
                if (access$2300 != null && !CustomToolTipWithAnimation.this.dismissed) {
                    CustomToolTipUtil.removeOnGlobalLayoutListener(access$2300.getContentView(), this);
                    if (CustomToolTipWithAnimation.this.mOnShowListener != null) {
                        CustomToolTipWithAnimation.this.mOnShowListener.onShow(CustomToolTipWithAnimation.this);
                    }
                    OnShowListener unused = CustomToolTipWithAnimation.this.mOnShowListener = null;
                    CustomToolTipWithAnimation.this.mContentLayout.setVisibility(0);
                }
            }
        };
        this.mAnimationLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PopupWindow access$2300 = CustomToolTipWithAnimation.this.mPopupWindow;
                if (access$2300 != null && !CustomToolTipWithAnimation.this.dismissed) {
                    CustomToolTipUtil.removeOnGlobalLayoutListener(access$2300.getContentView(), this);
                    if (CustomToolTipWithAnimation.this.mAnimated) {
                        CustomToolTipWithAnimation.this.startAnimation();
                    }
                    access$2300.getContentView().requestLayout();
                }
            }
        };
        this.mAutoDismissLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (CustomToolTipWithAnimation.this.mPopupWindow != null && !CustomToolTipWithAnimation.this.dismissed && !CustomToolTipWithAnimation.this.mRootView.isShown()) {
                    CustomToolTipWithAnimation.this.dismiss();
                }
            }
        };
        this.mOverlayTouchListener = new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (CustomToolTipWithAnimation.this.mDismissOnOutsideTouch) {
                    CustomToolTipWithAnimation.this.dismiss();
                }
                if (motionEvent.getAction() == 1) {
                    view.performClick();
                }
                return true;
            }
        };
        this.mContext = builder.context;
        this.mGravity = builder.gravity;
        this.mArrowDirection = builder.arrowDirection;
        this.mDismissOnInsideTouch = builder.dismissOnInsideTouch;
        this.mDismissOnOutsideTouch = builder.dismissOnOutsideTouch;
        this.mContentView = builder.contentView;
        this.mTextViewId = builder.textViewId;
        this.mText = builder.text;
        this.mAnchorView = builder.anchorView;
        this.mMaxWidth = builder.maxWidth;
        this.mShowArrow = builder.showArrow;
        this.mArrowWidth = builder.arrowWidth;
        this.mArrowHeight = builder.arrowHeight;
        this.mAnimated = builder.animated;
        this.mMargin = builder.margin;
        this.mPadding = builder.padding;
        this.mAnimationPadding = builder.animationPadding;
        this.mAnimationDuration = (float) builder.animationDuration;
        this.mOnDismissListener = builder.onDismissListener;
        this.mOnShowListener = builder.onShowListener;
        this.mFocusable = builder.focusable;
        this.mRootView = (ViewGroup) this.mAnchorView.getRootView();
        this.mArrowDrawable = builder.arrowDrawable;
        init();
    }

    private void init() {
        configPopupWindow();
        configContentView();
    }

    private void configPopupWindow() {
        PopupWindow popupWindow = new PopupWindow(this.mContext, (AttributeSet) null, mDefaultPopupWindowStyleRes);
        this.mPopupWindow = popupWindow;
        popupWindow.setOnDismissListener(this);
        this.mPopupWindow.setWidth(-2);
        this.mPopupWindow.setHeight(-2);
        this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mPopupWindow.setClippingEnabled(false);
        this.mPopupWindow.setFocusable(this.mFocusable);
    }

    public void show() {
        verifyDismissed();
        this.mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(this.mLocationLayoutListener);
        this.mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(this.mAutoDismissLayoutListener);
        this.mRootView.post(new Runnable() {
            public void run() {
                if (CustomToolTipWithAnimation.this.mRootView.isShown()) {
                    CustomToolTipWithAnimation.this.mPopupWindow.showAtLocation(CustomToolTipWithAnimation.this.mRootView, 0, CustomToolTipWithAnimation.this.mRootView.getWidth(), CustomToolTipWithAnimation.this.mRootView.getHeight());
                } else {
                    Log.e(CustomToolTipWithAnimation.TAG, "Tooltip cannot be shown, root view is invalid or has been closed.");
                }
            }
        });
    }

    private void verifyDismissed() {
        if (this.dismissed) {
            throw new IllegalArgumentException("Tooltip has ben dismissed.");
        }
    }

    /* access modifiers changed from: private */
    public PointF calculePopupLocation() {
        PointF pointF = new PointF();
        RectF calculeRectInWindow = CustomToolTipUtil.calculeRectInWindow(this.mAnchorView);
        PointF pointF2 = new PointF(calculeRectInWindow.centerX(), calculeRectInWindow.centerY());
        int i = this.mGravity;
        if (i == 17) {
            pointF.x = pointF2.x - (((float) this.mPopupWindow.getContentView().getWidth()) / 2.0f);
            pointF.y = pointF2.y - (((float) this.mPopupWindow.getContentView().getHeight()) / 2.0f);
        } else if (i == 48) {
            pointF.x = pointF2.x - (((float) this.mPopupWindow.getContentView().getWidth()) / 2.0f);
            pointF.y = (calculeRectInWindow.top - ((float) this.mPopupWindow.getContentView().getHeight())) - this.mMargin;
        } else if (i == 80) {
            pointF.x = pointF2.x - (((float) this.mPopupWindow.getContentView().getWidth()) / 2.0f);
            if (this.mMaxWidth == -1.0f) {
                pointF.x = calculeRectInWindow.left;
            }
            pointF.y = calculeRectInWindow.bottom + this.mMargin;
        } else if (i == 8388611) {
            pointF.x = (calculeRectInWindow.left - ((float) this.mPopupWindow.getContentView().getWidth())) - this.mMargin;
            pointF.y = pointF2.y - (((float) this.mPopupWindow.getContentView().getHeight()) / 2.0f);
        } else if (i == 8388613) {
            pointF.x = calculeRectInWindow.right + this.mMargin;
            pointF.y = pointF2.y - (((float) this.mPopupWindow.getContentView().getHeight()) / 2.0f);
        } else {
            throw new IllegalArgumentException("Gravity must have be CENTER, START, END, TOP or BOTTOM.");
        }
        return pointF;
    }

    private void configContentView() {
        LinearLayout.LayoutParams layoutParams;
        View view = this.mContentView;
        if (view instanceof TextView) {
            ((TextView) view).setText(this.mText);
        } else {
            TextView textView = (TextView) view.findViewById(this.mTextViewId);
            if (textView != null) {
                textView.setText(this.mText);
            }
        }
        View view2 = this.mContentView;
        float f = this.mPadding;
        view2.setPadding((int) f, (int) f, (int) f, (int) f);
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        int i = this.mArrowDirection;
        linearLayout.setOrientation((i == 0 || i == 2) ? 0 : 1);
        int i2 = (int) (this.mAnimated ? this.mAnimationPadding : 0.0f);
        linearLayout.setPadding(0, i2, 0, i2);
        if (this.mShowArrow) {
            ImageView imageView = new ImageView(this.mContext);
            this.mArrowView = imageView;
            imageView.setImageDrawable(this.mArrowDrawable);
            int i3 = this.mArrowDirection;
            if (i3 == 1 || i3 == 3) {
                layoutParams = new LinearLayout.LayoutParams((int) this.mArrowWidth, (int) this.mArrowHeight, 0.0f);
            } else {
                layoutParams = new LinearLayout.LayoutParams((int) this.mArrowHeight, (int) this.mArrowWidth, 0.0f);
            }
            layoutParams.gravity = 17;
            this.mArrowView.setLayoutParams(layoutParams);
            int i4 = this.mArrowDirection;
            if (i4 == 3 || i4 == 2) {
                linearLayout.addView(this.mContentView);
                linearLayout.addView(this.mArrowView);
            } else {
                linearLayout.addView(this.mArrowView);
                linearLayout.addView(this.mContentView);
            }
        } else {
            linearLayout.addView(this.mContentView);
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2, 0.0f);
        layoutParams2.gravity = 17;
        this.mContentView.setLayoutParams(layoutParams2);
        if (this.mDismissOnInsideTouch || this.mDismissOnOutsideTouch) {
            this.mContentView.setOnTouchListener(this.mPopupWindowTouchListener);
        }
        this.mContentLayout = linearLayout;
        linearLayout.setVisibility(4);
        this.mPopupWindow.setContentView(this.mContentLayout);
    }

    public void dismiss() {
        if (!this.dismissed) {
            this.dismissed = true;
            stopAnimation();
            this.mContentLayout.postDelayed(new Runnable() {
                public void run() {
                    if (CustomToolTipWithAnimation.this.mPopupWindow != null && CustomToolTipWithAnimation.this.mPopupWindow.isShowing()) {
                        CustomToolTipWithAnimation.this.mPopupWindow.dismiss();
                    }
                }
            }, (long) (this.mAnimationDuration / 2.0f));
        }
    }

    public void dismissImmediately() {
        if (!this.dismissed) {
            this.dismissed = true;
            stopAnimation();
            PopupWindow popupWindow = this.mPopupWindow;
            if (popupWindow != null && popupWindow.isShowing()) {
                this.mPopupWindow.dismiss();
            }
        }
    }

    public void onDismiss() {
        View view;
        AnimatorSet animatorSet;
        this.dismissed = true;
        if (Build.VERSION.SDK_INT >= 11 && (animatorSet = this.mAnimator) != null) {
            animatorSet.removeAllListeners();
            this.mAnimator.end();
            this.mAnimator.cancel();
            this.mAnimator = null;
        }
        ViewGroup viewGroup = this.mRootView;
        if (!(viewGroup == null || (view = this.mOverlay) == null)) {
            viewGroup.removeView(view);
        }
        this.mRootView = null;
        this.mOverlay = null;
        OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(this);
        }
        this.mOnDismissListener = null;
        CustomToolTipUtil.removeOnGlobalLayoutListener(this.mPopupWindow.getContentView(), this.mLocationLayoutListener);
        CustomToolTipUtil.removeOnGlobalLayoutListener(this.mPopupWindow.getContentView(), this.mArrowLayoutListener);
        CustomToolTipUtil.removeOnGlobalLayoutListener(this.mPopupWindow.getContentView(), this.mShowLayoutListener);
        CustomToolTipUtil.removeOnGlobalLayoutListener(this.mPopupWindow.getContentView(), this.mAnimationLayoutListener);
        CustomToolTipUtil.removeOnGlobalLayoutListener(this.mPopupWindow.getContentView(), this.mAutoDismissLayoutListener);
        this.mPopupWindow = null;
    }

    public boolean isShowing() {
        PopupWindow popupWindow = this.mPopupWindow;
        return popupWindow != null && popupWindow.isShowing();
    }

    public <T extends View> T findViewById(int i) {
        return this.mContentLayout.findViewById(i);
    }

    /* access modifiers changed from: private */
    public void startAnimation() {
        int i = this.mGravity;
        String str = (i == 48 || i == 80) ? "translationY" : "translationX";
        View view = this.mContentLayout;
        float f = this.mAnimationPadding;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str, new float[]{f, -f});
        ofFloat.setDuration((long) this.mAnimationDuration);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mContentLayout, "alpha", new float[]{0.2f, 1.0f});
        ofFloat2.setDuration((long) this.mAnimationDuration);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimator = animatorSet;
        animatorSet.playTogether(new Animator[]{ofFloat2, ofFloat});
        this.mAnimator.start();
    }

    private void stopAnimation() {
        int i = this.mGravity;
        String str = (i == 48 || i == 80) ? "translationY" : "translationX";
        long j = (long) (((double) this.mAnimationDuration) * 0.7d);
        View view = this.mContentLayout;
        float f = this.mAnimationPadding;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str, new float[]{-f, f});
        ofFloat.setDuration(j);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mContentLayout, "alpha", new float[]{1.0f, 0.0f});
        ofFloat2.setDuration(j);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimator = animatorSet;
        animatorSet.playTogether(new Animator[]{ofFloat2, ofFloat});
        this.mAnimator.start();
    }

    /* access modifiers changed from: private */
    public void createOverlay() {
        View view = new View(this.mContext);
        this.mOverlay = view;
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mOverlay.setOnTouchListener(this.mOverlayTouchListener);
        this.mRootView.addView(this.mOverlay);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public View anchorView;
        /* access modifiers changed from: private */
        public boolean animated = false;
        /* access modifiers changed from: private */
        public long animationDuration;
        /* access modifiers changed from: private */
        public float animationPadding = -1.0f;
        private int arrowColor;
        /* access modifiers changed from: private */
        public int arrowDirection = 4;
        /* access modifiers changed from: private */
        public Drawable arrowDrawable;
        /* access modifiers changed from: private */
        public float arrowHeight;
        /* access modifiers changed from: private */
        public float arrowWidth;
        private int backgroundColor;
        /* access modifiers changed from: private */
        public View contentView;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public boolean dismissOnInsideTouch = true;
        /* access modifiers changed from: private */
        public boolean dismissOnOutsideTouch = true;
        /* access modifiers changed from: private */
        public boolean focusable;
        /* access modifiers changed from: private */
        public int gravity = 80;
        /* access modifiers changed from: private */
        public float margin = -1.0f;
        /* access modifiers changed from: private */
        public float maxWidth;
        /* access modifiers changed from: private */
        public OnDismissListener onDismissListener;
        /* access modifiers changed from: private */
        public OnShowListener onShowListener;
        /* access modifiers changed from: private */
        public float padding = -1.0f;
        /* access modifiers changed from: private */
        public boolean showArrow = true;
        /* access modifiers changed from: private */
        public CharSequence text = "";
        private int textColor;
        /* access modifiers changed from: private */
        public int textViewId = 16908308;

        private void setTextAppearance(TextView textView, int i) {
        }

        public Builder(Context context2) {
            this.context = context2;
        }

        public CustomToolTipWithAnimation build() throws IllegalArgumentException {
            validateArguments();
            if (this.backgroundColor == 0) {
                this.backgroundColor = ContextCompat.getColor(this.context, R.color.medscapedarkblue);
            }
            if (this.textColor == 0) {
                this.textColor = ContextCompat.getColor(this.context, R.color.white);
            }
            if (this.contentView == null) {
                TextView textView = new TextView(this.context);
                setTextAppearance(textView, R.style.TextAppearance_AppCompat_Medium);
                textView.setBackgroundColor(this.backgroundColor);
                textView.setTextColor(this.textColor);
                this.contentView = textView;
            }
            if (this.arrowColor == 0) {
                this.arrowColor = ContextCompat.getColor(this.context, R.color.medscapedarkblue);
            }
            if (this.margin < 0.0f) {
                this.margin = 0.0f;
            }
            if (this.padding < 0.0f) {
                this.padding = 0.0f;
            }
            if (this.animationPadding < 0.0f) {
                this.animationPadding = 20.0f;
            }
            if (this.animationDuration == 0) {
                this.animationDuration = 2000;
            }
            if (this.showArrow) {
                if (this.arrowDirection == 4) {
                    this.arrowDirection = CustomToolTipUtil.tooltipGravityToArrowDirection(this.gravity);
                }
                if (this.arrowDrawable == null) {
                    this.arrowDrawable = new ArrowDrawable(this.arrowColor, this.arrowDirection);
                }
                if (this.arrowWidth == 0.0f) {
                    this.arrowWidth = 30.0f;
                }
                if (this.arrowHeight == 0.0f) {
                    this.arrowHeight = 15.0f;
                }
            }
            return new CustomToolTipWithAnimation(this);
        }

        private void validateArguments() throws IllegalArgumentException {
            if (this.context == null) {
                throw new IllegalArgumentException("Context not specified.");
            } else if (this.anchorView == null) {
                throw new IllegalArgumentException("Anchor view not specified.");
            }
        }

        public Builder contentView(TextView textView) {
            this.contentView = textView;
            this.textViewId = 0;
            return this;
        }

        public Builder contentView(View view, int i) {
            this.contentView = view;
            this.textViewId = i;
            return this;
        }

        public Builder contentView(int i, int i2) {
            this.contentView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(i, (ViewGroup) null, false);
            this.textViewId = i2;
            return this;
        }

        public Builder contentView(int i) {
            this.contentView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(i, (ViewGroup) null, false);
            this.textViewId = 0;
            return this;
        }

        public Builder dismissOnInsideTouch(boolean z) {
            this.dismissOnInsideTouch = z;
            return this;
        }

        public Builder dismissOnOutsideTouch(boolean z) {
            this.dismissOnOutsideTouch = z;
            return this;
        }

        public Builder text(CharSequence charSequence) {
            this.text = charSequence;
            return this;
        }

        public Builder text(int i) {
            this.text = this.context.getString(i);
            return this;
        }

        public Builder anchorView(View view) {
            this.anchorView = view;
            return this;
        }

        public Builder gravity(int i) {
            this.gravity = i;
            return this;
        }

        public Builder arrowDirection(int i) {
            this.arrowDirection = i;
            return this;
        }

        public Builder maxWidth(float f) {
            this.maxWidth = f;
            return this;
        }

        public Builder animationPadding(float f) {
            this.animationPadding = f;
            return this;
        }

        public Builder animationPadding(int i) {
            this.animationPadding = this.context.getResources().getDimension(i);
            return this;
        }

        public Builder animationDuration(long j) {
            this.animationDuration = j;
            return this;
        }

        public Builder animated(boolean z) {
            this.animated = z;
            return this;
        }

        public Builder padding(float f) {
            this.padding = f;
            return this;
        }

        public Builder padding(int i) {
            this.padding = this.context.getResources().getDimension(i);
            return this;
        }

        public Builder margin(float f) {
            this.margin = f;
            return this;
        }

        public Builder margin(int i) {
            this.margin = this.context.getResources().getDimension(i);
            return this;
        }

        public Builder textColor(int i) {
            this.textColor = i;
            return this;
        }

        public Builder backgroundColor(int i) {
            this.backgroundColor = i;
            return this;
        }

        public Builder showArrow(boolean z) {
            this.showArrow = z;
            return this;
        }

        public Builder arrowColor(int i) {
            this.arrowColor = i;
            return this;
        }

        public Builder arrowHeight(float f) {
            this.arrowHeight = f;
            return this;
        }

        public Builder arrowWidth(float f) {
            this.arrowWidth = f;
            return this;
        }

        public Builder onDismissListener(OnDismissListener onDismissListener2) {
            this.onDismissListener = onDismissListener2;
            return this;
        }

        public Builder onShowListener(OnShowListener onShowListener2) {
            this.onShowListener = onShowListener2;
            return this;
        }

        public Builder focusable(boolean z) {
            this.focusable = z;
            return this;
        }
    }
}
