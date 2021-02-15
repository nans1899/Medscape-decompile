package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.handmark.pulltorefresh.library.internal.FlipLoadingLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import com.handmark.pulltorefresh.library.internal.RotateLoadingLayout;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.handmark.pulltorefresh.library.internal.ViewCompat;

public abstract class PullToRefreshBase<T extends View> extends LinearLayout implements IPullToRefresh<T> {
    static final boolean DEBUG = true;
    static final int DEMO_SCROLL_INTERVAL = 225;
    static final float FRICTION = 2.0f;
    static final String LOG_TAG = "PullToRefresh";
    public static final int SMOOTH_SCROLL_DURATION_MS = 200;
    public static final int SMOOTH_SCROLL_LONG_DURATION_MS = 325;
    static final String STATE_CURRENT_MODE = "ptr_current_mode";
    static final String STATE_MODE = "ptr_mode";
    static final String STATE_SCROLLING_REFRESHING_ENABLED = "ptr_disable_scrolling";
    static final String STATE_SHOW_REFRESHING_VIEW = "ptr_show_refreshing_view";
    static final String STATE_STATE = "ptr_state";
    static final String STATE_SUPER = "ptr_super";
    static final boolean USE_HW_LAYERS = false;
    private Mode mCurrentMode;
    private PullToRefreshBase<T>.SmoothScrollRunnable mCurrentSmoothScrollRunnable;
    private boolean mFilterTouchEvents = true;
    private LoadingLayout mFooterLayout;
    private LoadingLayout mHeaderLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsBeingDragged = false;
    private float mLastMotionX;
    private float mLastMotionY;
    private boolean mLayoutVisibilityChangesEnabled = true;
    private AnimationStyle mLoadingAnimationStyle = AnimationStyle.getDefault();
    private Mode mMode = Mode.getDefault();
    private OnPullEventListener<T> mOnPullEventListener;
    private OnRefreshListener<T> mOnRefreshListener;
    private OnRefreshListener2<T> mOnRefreshListener2;
    private boolean mOverScrollEnabled = true;
    T mRefreshableView;
    private FrameLayout mRefreshableViewWrapper;
    /* access modifiers changed from: private */
    public Interpolator mScrollAnimationInterpolator;
    private boolean mScrollingWhileRefreshingEnabled = false;
    private boolean mShowViewWhileRefreshing = true;
    private State mState = State.RESET;
    private int mTouchSlop;

    public interface OnLastItemVisibleListener {
        void onLastItemVisible();
    }

    public interface OnPullEventListener<V extends View> {
        void onPullEvent(PullToRefreshBase<V> pullToRefreshBase, State state, Mode mode);
    }

    public interface OnRefreshListener<V extends View> {
        void onRefresh(PullToRefreshBase<V> pullToRefreshBase);
    }

    public interface OnRefreshListener2<V extends View> {
        void onPullDownToRefresh(PullToRefreshBase<V> pullToRefreshBase);

        void onPullUpToRefresh(PullToRefreshBase<V> pullToRefreshBase);
    }

    interface OnSmoothScrollFinishedListener {
        void onSmoothScrollFinished();
    }

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    /* access modifiers changed from: protected */
    public abstract T createRefreshableView(Context context, AttributeSet attributeSet);

    public abstract Orientation getPullToRefreshScrollDirection();

    /* access modifiers changed from: protected */
    public int getPullToRefreshScrollDuration() {
        return 200;
    }

    /* access modifiers changed from: protected */
    public int getPullToRefreshScrollDurationLonger() {
        return SMOOTH_SCROLL_LONG_DURATION_MS;
    }

    /* access modifiers changed from: protected */
    public void handleStyledAttributes(TypedArray typedArray) {
    }

    /* access modifiers changed from: protected */
    public abstract boolean isReadyForPullEnd();

    /* access modifiers changed from: protected */
    public abstract boolean isReadyForPullStart();

    /* access modifiers changed from: protected */
    public void onPtrRestoreInstanceState(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void onPtrSaveInstanceState(Bundle bundle) {
    }

    public PullToRefreshBase(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public PullToRefreshBase(Context context, Mode mode) {
        super(context);
        this.mMode = mode;
        init(context, (AttributeSet) null);
    }

    public PullToRefreshBase(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context);
        this.mMode = mode;
        this.mLoadingAnimationStyle = animationStyle;
        init(context, (AttributeSet) null);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        Log.d(LOG_TAG, "addView: " + view.getClass().getSimpleName());
        View refreshableView = getRefreshableView();
        if (refreshableView instanceof ViewGroup) {
            ((ViewGroup) refreshableView).addView(view, i, layoutParams);
            return;
        }
        throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
    }

    public final boolean demo() {
        if (this.mMode.showHeaderLoadingLayout() && isReadyForPullStart()) {
            smoothScrollToAndBack((-getHeaderSize()) * 2);
            return true;
        } else if (!this.mMode.showFooterLoadingLayout() || !isReadyForPullEnd()) {
            return false;
        } else {
            smoothScrollToAndBack(getFooterSize() * 2);
            return true;
        }
    }

    public final Mode getCurrentMode() {
        return this.mCurrentMode;
    }

    public final boolean getFilterTouchEvents() {
        return this.mFilterTouchEvents;
    }

    public final ILoadingLayout getLoadingLayoutProxy() {
        return getLoadingLayoutProxy(true, true);
    }

    public final ILoadingLayout getLoadingLayoutProxy(boolean z, boolean z2) {
        return createLoadingLayoutProxy(z, z2);
    }

    public final Mode getMode() {
        return this.mMode;
    }

    public final T getRefreshableView() {
        return this.mRefreshableView;
    }

    public final boolean getShowViewWhileRefreshing() {
        return this.mShowViewWhileRefreshing;
    }

    public final State getState() {
        return this.mState;
    }

    public final boolean isDisableScrollingWhileRefreshing() {
        return !isScrollingWhileRefreshingEnabled();
    }

    public final boolean isPullToRefreshEnabled() {
        return this.mMode.permitsPullToRefresh();
    }

    public final boolean isPullToRefreshOverScrollEnabled() {
        return Build.VERSION.SDK_INT >= 9 && this.mOverScrollEnabled && OverscrollHelper.isAndroidOverScrollEnabled(this.mRefreshableView);
    }

    public final boolean isRefreshing() {
        return this.mState == State.REFRESHING || this.mState == State.MANUAL_REFRESHING;
    }

    public final boolean isScrollingWhileRefreshingEnabled() {
        return this.mScrollingWhileRefreshingEnabled;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float f;
        float f2;
        if (!isPullToRefreshEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.mIsBeingDragged = false;
            return false;
        } else if (action != 0 && this.mIsBeingDragged) {
            return true;
        } else {
            if (action != 0) {
                if (action == 2) {
                    if (!this.mScrollingWhileRefreshingEnabled && isRefreshing()) {
                        return true;
                    }
                    if (isReadyForPull()) {
                        float y = motionEvent.getY();
                        float x = motionEvent.getX();
                        if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
                            f2 = y - this.mLastMotionY;
                            f = x - this.mLastMotionX;
                        } else {
                            f2 = x - this.mLastMotionX;
                            f = y - this.mLastMotionY;
                        }
                        float abs = Math.abs(f2);
                        if (abs > ((float) this.mTouchSlop) && (!this.mFilterTouchEvents || abs > Math.abs(f))) {
                            if (this.mMode.showHeaderLoadingLayout() && f2 >= 1.0f && isReadyForPullStart()) {
                                this.mLastMotionY = y;
                                this.mLastMotionX = x;
                                this.mIsBeingDragged = true;
                                if (this.mMode == Mode.BOTH) {
                                    this.mCurrentMode = Mode.PULL_FROM_START;
                                }
                            } else if (this.mMode.showFooterLoadingLayout() && f2 <= -1.0f && isReadyForPullEnd()) {
                                this.mLastMotionY = y;
                                this.mLastMotionX = x;
                                this.mIsBeingDragged = true;
                                if (this.mMode == Mode.BOTH) {
                                    this.mCurrentMode = Mode.PULL_FROM_END;
                                }
                            }
                        }
                    }
                }
            } else if (isReadyForPull()) {
                float y2 = motionEvent.getY();
                this.mInitialMotionY = y2;
                this.mLastMotionY = y2;
                float x2 = motionEvent.getX();
                this.mInitialMotionX = x2;
                this.mLastMotionX = x2;
                this.mIsBeingDragged = false;
            }
            return this.mIsBeingDragged;
        }
    }

    public final void onRefreshComplete() {
        if (isRefreshing()) {
            setState(State.RESET, new boolean[0]);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002d, code lost:
        if (r0 != 3) goto L_0x008b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.isPullToRefreshEnabled()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            boolean r0 = r4.mScrollingWhileRefreshingEnabled
            r2 = 1
            if (r0 != 0) goto L_0x0014
            boolean r0 = r4.isRefreshing()
            if (r0 == 0) goto L_0x0014
            return r2
        L_0x0014:
            int r0 = r5.getAction()
            if (r0 != 0) goto L_0x0021
            int r0 = r5.getEdgeFlags()
            if (r0 == 0) goto L_0x0021
            return r1
        L_0x0021:
            int r0 = r5.getAction()
            if (r0 == 0) goto L_0x0074
            if (r0 == r2) goto L_0x0044
            r3 = 2
            if (r0 == r3) goto L_0x0030
            r5 = 3
            if (r0 == r5) goto L_0x0044
            goto L_0x008b
        L_0x0030:
            boolean r0 = r4.mIsBeingDragged
            if (r0 == 0) goto L_0x008b
            float r0 = r5.getY()
            r4.mLastMotionY = r0
            float r5 = r5.getX()
            r4.mLastMotionX = r5
            r4.pullEvent()
            return r2
        L_0x0044:
            boolean r5 = r4.mIsBeingDragged
            if (r5 == 0) goto L_0x008b
            r4.mIsBeingDragged = r1
            com.handmark.pulltorefresh.library.PullToRefreshBase$State r5 = r4.mState
            com.handmark.pulltorefresh.library.PullToRefreshBase$State r0 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.RELEASE_TO_REFRESH
            if (r5 != r0) goto L_0x0062
            com.handmark.pulltorefresh.library.PullToRefreshBase$OnRefreshListener<T> r5 = r4.mOnRefreshListener
            if (r5 != 0) goto L_0x0058
            com.handmark.pulltorefresh.library.PullToRefreshBase$OnRefreshListener2<T> r5 = r4.mOnRefreshListener2
            if (r5 == 0) goto L_0x0062
        L_0x0058:
            com.handmark.pulltorefresh.library.PullToRefreshBase$State r5 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.REFRESHING
            boolean[] r0 = new boolean[r2]
            r0[r1] = r2
            r4.setState(r5, r0)
            return r2
        L_0x0062:
            boolean r5 = r4.isRefreshing()
            if (r5 == 0) goto L_0x006c
            r4.smoothScrollTo(r1)
            return r2
        L_0x006c:
            com.handmark.pulltorefresh.library.PullToRefreshBase$State r5 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.RESET
            boolean[] r0 = new boolean[r1]
            r4.setState(r5, r0)
            return r2
        L_0x0074:
            boolean r0 = r4.isReadyForPull()
            if (r0 == 0) goto L_0x008b
            float r0 = r5.getY()
            r4.mInitialMotionY = r0
            r4.mLastMotionY = r0
            float r5 = r5.getX()
            r4.mInitialMotionX = r5
            r4.mLastMotionX = r5
            return r2
        L_0x008b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.handmark.pulltorefresh.library.PullToRefreshBase.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void setScrollingWhileRefreshingEnabled(boolean z) {
        this.mScrollingWhileRefreshingEnabled = z;
    }

    public void setDisableScrollingWhileRefreshing(boolean z) {
        setScrollingWhileRefreshingEnabled(!z);
    }

    public final void setFilterTouchEvents(boolean z) {
        this.mFilterTouchEvents = z;
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setLastUpdatedLabel(charSequence);
    }

    public void setLoadingDrawable(Drawable drawable) {
        getLoadingLayoutProxy().setLoadingDrawable(drawable);
    }

    public void setLoadingDrawable(Drawable drawable, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setLoadingDrawable(drawable);
    }

    public void setLongClickable(boolean z) {
        getRefreshableView().setLongClickable(z);
    }

    public final void setMode(Mode mode) {
        if (mode != this.mMode) {
            Log.d(LOG_TAG, "Setting mode to: " + mode);
            this.mMode = mode;
            updateUIForMode();
        }
    }

    public void setOnPullEventListener(OnPullEventListener<T> onPullEventListener) {
        this.mOnPullEventListener = onPullEventListener;
    }

    public final void setOnRefreshListener(OnRefreshListener<T> onRefreshListener) {
        this.mOnRefreshListener = onRefreshListener;
        this.mOnRefreshListener2 = null;
    }

    public final void setOnRefreshListener(OnRefreshListener2<T> onRefreshListener2) {
        this.mOnRefreshListener2 = onRefreshListener2;
        this.mOnRefreshListener = null;
    }

    public void setPullLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setPullLabel(charSequence);
    }

    public void setPullLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setPullLabel(charSequence);
    }

    public final void setPullToRefreshEnabled(boolean z) {
        setMode(z ? Mode.getDefault() : Mode.DISABLED);
    }

    public final void setPullToRefreshOverScrollEnabled(boolean z) {
        this.mOverScrollEnabled = z;
    }

    public final void setRefreshing() {
        setRefreshing(true);
    }

    public final void setRefreshing(boolean z) {
        if (!isRefreshing()) {
            setState(State.MANUAL_REFRESHING, z);
        }
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setRefreshingLabel(charSequence);
    }

    public void setRefreshingLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setRefreshingLabel(charSequence);
    }

    public void setReleaseLabel(CharSequence charSequence) {
        setReleaseLabel(charSequence, Mode.BOTH);
    }

    public void setReleaseLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setReleaseLabel(charSequence);
    }

    public void setScrollAnimationInterpolator(Interpolator interpolator) {
        this.mScrollAnimationInterpolator = interpolator;
    }

    public final void setShowViewWhileRefreshing(boolean z) {
        this.mShowViewWhileRefreshing = z;
    }

    /* access modifiers changed from: package-private */
    public final void setState(State state, boolean... zArr) {
        this.mState = state;
        Log.d(LOG_TAG, "State: " + this.mState.name());
        int i = AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[this.mState.ordinal()];
        if (i == 1) {
            onReset();
        } else if (i == 2) {
            onPullToRefresh();
        } else if (i == 3) {
            onReleaseToRefresh();
        } else if (i == 4 || i == 5) {
            onRefreshing(zArr[0]);
        }
        OnPullEventListener<T> onPullEventListener = this.mOnPullEventListener;
        if (onPullEventListener != null) {
            onPullEventListener.onPullEvent(this, this.mState, this.mCurrentMode);
        }
    }

    /* access modifiers changed from: protected */
    public final void addViewInternal(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void addViewInternal(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, -1, layoutParams);
    }

    /* access modifiers changed from: protected */
    public LoadingLayout createLoadingLayout(Context context, Mode mode, TypedArray typedArray) {
        LoadingLayout createLoadingLayout = this.mLoadingAnimationStyle.createLoadingLayout(context, mode, getPullToRefreshScrollDirection(), typedArray);
        createLoadingLayout.setVisibility(4);
        return createLoadingLayout;
    }

    /* access modifiers changed from: protected */
    public LoadingLayoutProxy createLoadingLayoutProxy(boolean z, boolean z2) {
        LoadingLayoutProxy loadingLayoutProxy = new LoadingLayoutProxy();
        if (z && this.mMode.showHeaderLoadingLayout()) {
            loadingLayoutProxy.addLayout(this.mHeaderLayout);
        }
        if (z2 && this.mMode.showFooterLoadingLayout()) {
            loadingLayoutProxy.addLayout(this.mFooterLayout);
        }
        return loadingLayoutProxy;
    }

    /* access modifiers changed from: protected */
    public final void disableLoadingLayoutVisibilityChanges() {
        this.mLayoutVisibilityChangesEnabled = false;
    }

    public final LoadingLayout getFooterLayout() {
        return this.mFooterLayout;
    }

    /* access modifiers changed from: protected */
    public final int getFooterSize() {
        return this.mFooterLayout.getContentSize();
    }

    /* access modifiers changed from: protected */
    public final LoadingLayout getHeaderLayout() {
        return this.mHeaderLayout;
    }

    /* access modifiers changed from: protected */
    public final int getHeaderSize() {
        return this.mHeaderLayout.getContentSize();
    }

    /* access modifiers changed from: protected */
    public FrameLayout getRefreshableViewWrapper() {
        return this.mRefreshableViewWrapper;
    }

    /* access modifiers changed from: protected */
    public void onPullToRefresh() {
        int i = AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()];
        if (i == 1) {
            this.mFooterLayout.pullToRefresh();
        } else if (i == 2) {
            this.mHeaderLayout.pullToRefresh();
        }
    }

    /* access modifiers changed from: protected */
    public void onRefreshing(boolean z) {
        if (this.mMode.showHeaderLoadingLayout()) {
            this.mHeaderLayout.refreshing();
        }
        if (this.mMode.showFooterLoadingLayout()) {
            this.mFooterLayout.refreshing();
        }
        if (!z) {
            callRefreshListener();
        } else if (this.mShowViewWhileRefreshing) {
            AnonymousClass1 r3 = new OnSmoothScrollFinishedListener() {
                public void onSmoothScrollFinished() {
                    PullToRefreshBase.this.callRefreshListener();
                }
            };
            int i = AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()];
            if (i == 1 || i == 3) {
                smoothScrollTo(getFooterSize(), (OnSmoothScrollFinishedListener) r3);
            } else {
                smoothScrollTo(-getHeaderSize(), (OnSmoothScrollFinishedListener) r3);
            }
        } else {
            smoothScrollTo(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onReleaseToRefresh() {
        int i = AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()];
        if (i == 1) {
            this.mFooterLayout.releaseToRefresh();
        } else if (i == 2) {
            this.mHeaderLayout.releaseToRefresh();
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        this.mIsBeingDragged = false;
        this.mLayoutVisibilityChangesEnabled = true;
        this.mHeaderLayout.reset();
        this.mFooterLayout.reset();
        smoothScrollTo(0);
    }

    /* access modifiers changed from: protected */
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            setMode(Mode.mapIntToValue(bundle.getInt(STATE_MODE, 0)));
            this.mCurrentMode = Mode.mapIntToValue(bundle.getInt(STATE_CURRENT_MODE, 0));
            this.mScrollingWhileRefreshingEnabled = bundle.getBoolean(STATE_SCROLLING_REFRESHING_ENABLED, false);
            this.mShowViewWhileRefreshing = bundle.getBoolean(STATE_SHOW_REFRESHING_VIEW, true);
            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER));
            State mapIntToValue = State.mapIntToValue(bundle.getInt(STATE_STATE, 0));
            if (mapIntToValue == State.REFRESHING || mapIntToValue == State.MANUAL_REFRESHING) {
                setState(mapIntToValue, true);
            }
            onPtrRestoreInstanceState(bundle);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        onPtrSaveInstanceState(bundle);
        bundle.putInt(STATE_STATE, this.mState.getIntValue());
        bundle.putInt(STATE_MODE, this.mMode.getIntValue());
        bundle.putInt(STATE_CURRENT_MODE, this.mCurrentMode.getIntValue());
        bundle.putBoolean(STATE_SCROLLING_REFRESHING_ENABLED, this.mScrollingWhileRefreshingEnabled);
        bundle.putBoolean(STATE_SHOW_REFRESHING_VIEW, this.mShowViewWhileRefreshing);
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        return bundle;
    }

    /* access modifiers changed from: protected */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        Log.d(LOG_TAG, String.format("onSizeChanged. W: %d, H: %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        super.onSizeChanged(i, i2, i3, i4);
        refreshLoadingViewsSize();
        refreshRefreshableViewSize(i, i2);
        post(new Runnable() {
            public void run() {
                PullToRefreshBase.this.requestLayout();
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void refreshLoadingViewsSize() {
        int maximumPullScroll = (int) (((float) getMaximumPullScroll()) * 1.2f);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int i = AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()];
        if (i == 1) {
            if (this.mMode.showHeaderLoadingLayout()) {
                this.mHeaderLayout.setWidth(maximumPullScroll);
                paddingLeft = -maximumPullScroll;
            } else {
                paddingLeft = 0;
            }
            if (this.mMode.showFooterLoadingLayout()) {
                this.mFooterLayout.setWidth(maximumPullScroll);
                paddingRight = -maximumPullScroll;
            } else {
                paddingRight = 0;
            }
        } else if (i == 2) {
            if (this.mMode.showHeaderLoadingLayout()) {
                this.mHeaderLayout.setHeight(maximumPullScroll);
                paddingTop = -maximumPullScroll;
            } else {
                paddingTop = 0;
            }
            if (this.mMode.showFooterLoadingLayout()) {
                this.mFooterLayout.setHeight(maximumPullScroll);
                paddingBottom = -maximumPullScroll;
            } else {
                paddingBottom = 0;
            }
        }
        Log.d(LOG_TAG, String.format("Setting Padding. L: %d, T: %d, R: %d, B: %d", new Object[]{Integer.valueOf(paddingLeft), Integer.valueOf(paddingTop), Integer.valueOf(paddingRight), Integer.valueOf(paddingBottom)}));
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /* access modifiers changed from: protected */
    public final void refreshRefreshableViewSize(int i, int i2) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mRefreshableViewWrapper.getLayoutParams();
        int i3 = AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()];
        if (i3 != 1) {
            if (i3 == 2 && layoutParams.height != i2) {
                layoutParams.height = i2;
                this.mRefreshableViewWrapper.requestLayout();
            }
        } else if (layoutParams.width != i) {
            layoutParams.width = i;
            this.mRefreshableViewWrapper.requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public final void setHeaderScroll(int i) {
        Log.d(LOG_TAG, "setHeaderScroll: " + i);
        int maximumPullScroll = getMaximumPullScroll();
        int min = Math.min(maximumPullScroll, Math.max(-maximumPullScroll, i));
        if (this.mLayoutVisibilityChangesEnabled) {
            if (min < 0) {
                this.mHeaderLayout.setVisibility(0);
            } else if (min > 0) {
                this.mFooterLayout.setVisibility(0);
            } else {
                this.mHeaderLayout.setVisibility(4);
                this.mFooterLayout.setVisibility(4);
            }
        }
        int i2 = AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()];
        if (i2 == 1) {
            scrollTo(min, 0);
        } else if (i2 == 2) {
            scrollTo(0, min);
        }
    }

    /* access modifiers changed from: protected */
    public final void smoothScrollTo(int i) {
        smoothScrollTo(i, (long) getPullToRefreshScrollDuration());
    }

    /* access modifiers changed from: protected */
    public final void smoothScrollTo(int i, OnSmoothScrollFinishedListener onSmoothScrollFinishedListener) {
        smoothScrollTo(i, (long) getPullToRefreshScrollDuration(), 0, onSmoothScrollFinishedListener);
    }

    /* access modifiers changed from: protected */
    public final void smoothScrollToLonger(int i) {
        smoothScrollTo(i, (long) getPullToRefreshScrollDurationLonger());
    }

    /* access modifiers changed from: protected */
    public void updateUIForMode() {
        LinearLayout.LayoutParams loadingLayoutLayoutParams = getLoadingLayoutLayoutParams();
        if (this == this.mHeaderLayout.getParent()) {
            removeView(this.mHeaderLayout);
        }
        if (this.mMode.showHeaderLoadingLayout()) {
            addViewInternal(this.mHeaderLayout, 0, loadingLayoutLayoutParams);
        }
        if (this == this.mFooterLayout.getParent()) {
            removeView(this.mFooterLayout);
        }
        if (this.mMode.showFooterLoadingLayout()) {
            addViewInternal(this.mFooterLayout, loadingLayoutLayoutParams);
        }
        refreshLoadingViewsSize();
        this.mCurrentMode = this.mMode != Mode.BOTH ? this.mMode : Mode.PULL_FROM_START;
    }

    private void addRefreshableView(Context context, T t) {
        FrameLayout frameLayout = new FrameLayout(context);
        this.mRefreshableViewWrapper = frameLayout;
        frameLayout.addView(t, -1, -1);
        addViewInternal(this.mRefreshableViewWrapper, new LinearLayout.LayoutParams(-1, -1));
    }

    /* access modifiers changed from: private */
    public void callRefreshListener() {
        OnRefreshListener<T> onRefreshListener = this.mOnRefreshListener;
        if (onRefreshListener != null) {
            onRefreshListener.onRefresh(this);
        } else if (this.mOnRefreshListener2 == null) {
        } else {
            if (this.mCurrentMode == Mode.PULL_FROM_START) {
                this.mOnRefreshListener2.onPullDownToRefresh(this);
            } else if (this.mCurrentMode == Mode.PULL_FROM_END) {
                this.mOnRefreshListener2.onPullUpToRefresh(this);
            }
        }
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            setOrientation(1);
        } else {
            setOrientation(0);
        }
        setGravity(17);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PullToRefresh);
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrMode)) {
            this.mMode = Mode.mapIntToValue(obtainStyledAttributes.getInteger(R.styleable.PullToRefresh_ptrMode, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrAnimationStyle)) {
            this.mLoadingAnimationStyle = AnimationStyle.mapIntToValue(obtainStyledAttributes.getInteger(R.styleable.PullToRefresh_ptrAnimationStyle, 0));
        }
        T createRefreshableView = createRefreshableView(context, attributeSet);
        this.mRefreshableView = createRefreshableView;
        addRefreshableView(context, createRefreshableView);
        this.mHeaderLayout = createLoadingLayout(context, Mode.PULL_FROM_START, obtainStyledAttributes);
        this.mFooterLayout = createLoadingLayout(context, Mode.PULL_FROM_END, obtainStyledAttributes);
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrRefreshableViewBackground)) {
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.PullToRefresh_ptrRefreshableViewBackground);
            if (drawable != null) {
                this.mRefreshableView.setBackgroundDrawable(drawable);
            }
        } else if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrAdapterViewBackground)) {
            Utils.warnDeprecation("ptrAdapterViewBackground", "ptrRefreshableViewBackground");
            Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.PullToRefresh_ptrAdapterViewBackground);
            if (drawable2 != null) {
                this.mRefreshableView.setBackgroundDrawable(drawable2);
            }
        }
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrOverScroll)) {
            this.mOverScrollEnabled = obtainStyledAttributes.getBoolean(R.styleable.PullToRefresh_ptrOverScroll, true);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
            this.mScrollingWhileRefreshingEnabled = obtainStyledAttributes.getBoolean(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled, false);
        }
        handleStyledAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        updateUIForMode();
    }

    private boolean isReadyForPull() {
        int i = AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mMode.ordinal()];
        if (i == 1) {
            return isReadyForPullEnd();
        }
        if (i == 2) {
            return isReadyForPullStart();
        }
        if (i != 4) {
            return false;
        }
        if (isReadyForPullEnd() || isReadyForPullStart()) {
            return true;
        }
        return false;
    }

    private void pullEvent() {
        float f;
        float f2;
        int i;
        int i2;
        if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            f2 = this.mInitialMotionY;
            f = this.mLastMotionY;
        } else {
            f2 = this.mInitialMotionX;
            f = this.mLastMotionX;
        }
        if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()] != 1) {
            i2 = Math.round(Math.min(f2 - f, 0.0f) / FRICTION);
            i = getHeaderSize();
        } else {
            i2 = Math.round(Math.max(f2 - f, 0.0f) / FRICTION);
            i = getFooterSize();
        }
        setHeaderScroll(i2);
        if (i2 != 0 && !isRefreshing()) {
            float abs = ((float) Math.abs(i2)) / ((float) i);
            if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()] != 1) {
                this.mHeaderLayout.onPull(abs);
            } else {
                this.mFooterLayout.onPull(abs);
            }
            if (this.mState != State.PULL_TO_REFRESH && i >= Math.abs(i2)) {
                setState(State.PULL_TO_REFRESH, new boolean[0]);
            } else if (this.mState == State.PULL_TO_REFRESH && i < Math.abs(i2)) {
                setState(State.RELEASE_TO_REFRESH, new boolean[0]);
            }
        }
    }

    private LinearLayout.LayoutParams getLoadingLayoutLayoutParams() {
        if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            return new LinearLayout.LayoutParams(-1, -2);
        }
        return new LinearLayout.LayoutParams(-2, -1);
    }

    private int getMaximumPullScroll() {
        if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            return Math.round(((float) getHeight()) / FRICTION);
        }
        return Math.round(((float) getWidth()) / FRICTION);
    }

    private final void smoothScrollTo(int i, long j) {
        smoothScrollTo(i, j, 0, (OnSmoothScrollFinishedListener) null);
    }

    /* access modifiers changed from: private */
    public final void smoothScrollTo(int i, long j, long j2, OnSmoothScrollFinishedListener onSmoothScrollFinishedListener) {
        int i2;
        PullToRefreshBase<T>.SmoothScrollRunnable smoothScrollRunnable = this.mCurrentSmoothScrollRunnable;
        if (smoothScrollRunnable != null) {
            smoothScrollRunnable.stop();
        }
        if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            i2 = getScrollY();
        } else {
            i2 = getScrollX();
        }
        int i3 = i2;
        if (i3 != i) {
            if (this.mScrollAnimationInterpolator == null) {
                this.mScrollAnimationInterpolator = new DecelerateInterpolator();
            }
            SmoothScrollRunnable smoothScrollRunnable2 = new SmoothScrollRunnable(i3, i, j, onSmoothScrollFinishedListener);
            this.mCurrentSmoothScrollRunnable = smoothScrollRunnable2;
            if (j2 > 0) {
                postDelayed(smoothScrollRunnable2, j2);
            } else {
                post(smoothScrollRunnable2);
            }
        }
    }

    private final void smoothScrollToAndBack(int i) {
        smoothScrollTo(i, 200, 0, new OnSmoothScrollFinishedListener() {
            public void onSmoothScrollFinished() {
                PullToRefreshBase.this.smoothScrollTo(0, 200, 225, (OnSmoothScrollFinishedListener) null);
            }
        });
    }

    public enum AnimationStyle {
        ROTATE,
        FLIP;

        static AnimationStyle getDefault() {
            return ROTATE;
        }

        static AnimationStyle mapIntToValue(int i) {
            if (i != 1) {
                return ROTATE;
            }
            return FLIP;
        }

        /* access modifiers changed from: package-private */
        public LoadingLayout createLoadingLayout(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
            if (AnonymousClass4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[ordinal()] != 2) {
                return new RotateLoadingLayout(context, mode, orientation, typedArray);
            }
            return new FlipLoadingLayout(context, mode, orientation, typedArray);
        }
    }

    /* renamed from: com.handmark.pulltorefresh.library.PullToRefreshBase$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle;
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode;
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation;
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|(2:15|16)|17|(2:19|20)|21|23|24|25|26|27|28|29|30|31|32|33|34|35|37|38|39|40|42) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|(2:15|16)|17|(2:19|20)|21|23|24|25|26|27|28|29|30|31|32|33|34|35|37|38|39|40|42) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|1|2|3|(2:5|6)|7|9|10|11|12|13|15|16|17|(2:19|20)|21|23|24|25|26|27|28|29|30|31|32|33|34|35|37|38|39|40|42) */
        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|5|6|7|9|10|11|12|13|15|16|17|19|20|21|23|24|25|26|27|28|29|30|31|32|33|34|35|37|38|39|40|42) */
        /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x005f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0069 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0073 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0088 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00a4 */
        static {
            /*
                com.handmark.pulltorefresh.library.PullToRefreshBase$AnimationStyle[] r0 = com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle = r0
                r1 = 1
                com.handmark.pulltorefresh.library.PullToRefreshBase$AnimationStyle r2 = com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle.ROTATE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle     // Catch:{ NoSuchFieldError -> 0x001d }
                com.handmark.pulltorefresh.library.PullToRefreshBase$AnimationStyle r3 = com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle.FLIP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode[] r2 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = r2
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r3 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_END     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r2 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r3 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                r2 = 3
                int[] r3 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r4 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.MANUAL_REFRESH_ONLY     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                r3 = 4
                int[] r4 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode     // Catch:{ NoSuchFieldError -> 0x004e }
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r5 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH     // Catch:{ NoSuchFieldError -> 0x004e }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                com.handmark.pulltorefresh.library.PullToRefreshBase$State[] r4 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State = r4
                com.handmark.pulltorefresh.library.PullToRefreshBase$State r5 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.RESET     // Catch:{ NoSuchFieldError -> 0x005f }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x005f }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x005f }
            L_0x005f:
                int[] r4 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State     // Catch:{ NoSuchFieldError -> 0x0069 }
                com.handmark.pulltorefresh.library.PullToRefreshBase$State r5 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.PULL_TO_REFRESH     // Catch:{ NoSuchFieldError -> 0x0069 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0069 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0069 }
            L_0x0069:
                int[] r4 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State     // Catch:{ NoSuchFieldError -> 0x0073 }
                com.handmark.pulltorefresh.library.PullToRefreshBase$State r5 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.RELEASE_TO_REFRESH     // Catch:{ NoSuchFieldError -> 0x0073 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0073 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0073 }
            L_0x0073:
                int[] r2 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State     // Catch:{ NoSuchFieldError -> 0x007d }
                com.handmark.pulltorefresh.library.PullToRefreshBase$State r4 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.REFRESHING     // Catch:{ NoSuchFieldError -> 0x007d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r2[r4] = r3     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r2 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State     // Catch:{ NoSuchFieldError -> 0x0088 }
                com.handmark.pulltorefresh.library.PullToRefreshBase$State r3 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.MANUAL_REFRESHING     // Catch:{ NoSuchFieldError -> 0x0088 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0088 }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0088 }
            L_0x0088:
                int[] r2 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State     // Catch:{ NoSuchFieldError -> 0x0093 }
                com.handmark.pulltorefresh.library.PullToRefreshBase$State r3 = com.handmark.pulltorefresh.library.PullToRefreshBase.State.OVERSCROLLING     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r4 = 6
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                com.handmark.pulltorefresh.library.PullToRefreshBase$Orientation[] r2 = com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation = r2
                com.handmark.pulltorefresh.library.PullToRefreshBase$Orientation r3 = com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x00a4 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a4 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x00a4 }
            L_0x00a4:
                int[] r1 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation     // Catch:{ NoSuchFieldError -> 0x00ae }
                com.handmark.pulltorefresh.library.PullToRefreshBase$Orientation r2 = com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x00ae }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ae }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x00ae }
            L_0x00ae:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.handmark.pulltorefresh.library.PullToRefreshBase.AnonymousClass4.<clinit>():void");
        }
    }

    public enum Mode {
        DISABLED(0),
        PULL_FROM_START(1),
        PULL_FROM_END(2),
        BOTH(3),
        MANUAL_REFRESH_ONLY(4);
        
        public static Mode PULL_DOWN_TO_REFRESH;
        public static Mode PULL_UP_TO_REFRESH;
        private int mIntValue;

        static {
            Mode mode;
            Mode mode2;
            PULL_DOWN_TO_REFRESH = mode;
            PULL_UP_TO_REFRESH = mode2;
        }

        static Mode mapIntToValue(int i) {
            for (Mode mode : values()) {
                if (i == mode.getIntValue()) {
                    return mode;
                }
            }
            return getDefault();
        }

        static Mode getDefault() {
            return PULL_FROM_START;
        }

        private Mode(int i) {
            this.mIntValue = i;
        }

        /* access modifiers changed from: package-private */
        public boolean permitsPullToRefresh() {
            return (this == DISABLED || this == MANUAL_REFRESH_ONLY) ? false : true;
        }

        public boolean showHeaderLoadingLayout() {
            return this == PULL_FROM_START || this == BOTH;
        }

        public boolean showFooterLoadingLayout() {
            return this == PULL_FROM_END || this == BOTH || this == MANUAL_REFRESH_ONLY;
        }

        /* access modifiers changed from: package-private */
        public int getIntValue() {
            return this.mIntValue;
        }
    }

    public enum State {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9),
        OVERSCROLLING(16);
        
        private int mIntValue;

        static State mapIntToValue(int i) {
            for (State state : values()) {
                if (i == state.getIntValue()) {
                    return state;
                }
            }
            return RESET;
        }

        private State(int i) {
            this.mIntValue = i;
        }

        /* access modifiers changed from: package-private */
        public int getIntValue() {
            return this.mIntValue;
        }
    }

    final class SmoothScrollRunnable implements Runnable {
        private boolean mContinueRunning = true;
        private int mCurrentY = -1;
        private final long mDuration;
        private final Interpolator mInterpolator;
        private OnSmoothScrollFinishedListener mListener;
        private final int mScrollFromY;
        private final int mScrollToY;
        private long mStartTime = -1;

        public SmoothScrollRunnable(int i, int i2, long j, OnSmoothScrollFinishedListener onSmoothScrollFinishedListener) {
            this.mScrollFromY = i;
            this.mScrollToY = i2;
            this.mInterpolator = PullToRefreshBase.this.mScrollAnimationInterpolator;
            this.mDuration = j;
            this.mListener = onSmoothScrollFinishedListener;
        }

        public void run() {
            if (this.mStartTime == -1) {
                this.mStartTime = System.currentTimeMillis();
            } else {
                int round = this.mScrollFromY - Math.round(((float) (this.mScrollFromY - this.mScrollToY)) * this.mInterpolator.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.mStartTime) * 1000) / this.mDuration, 1000), 0)) / 1000.0f));
                this.mCurrentY = round;
                PullToRefreshBase.this.setHeaderScroll(round);
            }
            if (!this.mContinueRunning || this.mScrollToY == this.mCurrentY) {
                OnSmoothScrollFinishedListener onSmoothScrollFinishedListener = this.mListener;
                if (onSmoothScrollFinishedListener != null) {
                    onSmoothScrollFinishedListener.onSmoothScrollFinished();
                    return;
                }
                return;
            }
            ViewCompat.postOnAnimation(PullToRefreshBase.this, this);
        }

        public void stop() {
            this.mContinueRunning = false;
            PullToRefreshBase.this.removeCallbacks(this);
        }
    }
}
