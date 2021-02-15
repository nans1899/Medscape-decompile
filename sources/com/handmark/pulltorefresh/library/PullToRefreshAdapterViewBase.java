package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.handmark.pulltorefresh.library.internal.IndicatorLayout;

public abstract class PullToRefreshAdapterViewBase<T extends AbsListView> extends PullToRefreshBase<T> implements AbsListView.OnScrollListener {
    private View mEmptyView;
    private IndicatorLayout mIndicatorIvBottom;
    private IndicatorLayout mIndicatorIvTop;
    private boolean mLastItemVisible;
    private PullToRefreshBase.OnLastItemVisibleListener mOnLastItemVisibleListener;
    private AbsListView.OnScrollListener mOnScrollListener;
    private boolean mScrollEmptyView = true;
    private boolean mShowIndicator;

    private static FrameLayout.LayoutParams convertEmptyViewLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return null;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            layoutParams2.gravity = ((LinearLayout.LayoutParams) layoutParams).gravity;
            return layoutParams2;
        }
        layoutParams2.gravity = 17;
        return layoutParams2;
    }

    public PullToRefreshAdapterViewBase(Context context) {
        super(context);
        ((AbsListView) this.mRefreshableView).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ((AbsListView) this.mRefreshableView).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
        ((AbsListView) this.mRefreshableView).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
        ((AbsListView) this.mRefreshableView).setOnScrollListener(this);
    }

    public boolean getShowIndicator() {
        return this.mShowIndicator;
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        Log.d("PullToRefresh", "First Visible: " + i + ". Visible Count: " + i2 + ". Total Items:" + i3);
        if (this.mOnLastItemVisibleListener != null) {
            this.mLastItemVisible = i3 > 0 && i + i2 >= i3 + -1;
        }
        if (getShowIndicatorInternal()) {
            updateIndicatorViewsVisibility();
        }
        AbsListView.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(absListView, i, i2, i3);
        }
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        PullToRefreshBase.OnLastItemVisibleListener onLastItemVisibleListener;
        if (i == 0 && (onLastItemVisibleListener = this.mOnLastItemVisibleListener) != null && this.mLastItemVisible) {
            onLastItemVisibleListener.onLastItemVisible();
        }
        AbsListView.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        ((AdapterView) this.mRefreshableView).setAdapter(listAdapter);
    }

    public final void setEmptyView(View view) {
        FrameLayout refreshableViewWrapper = getRefreshableViewWrapper();
        if (view != null) {
            view.setClickable(true);
            ViewParent parent = view.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(view);
            }
            FrameLayout.LayoutParams convertEmptyViewLayoutParams = convertEmptyViewLayoutParams(view.getLayoutParams());
            if (convertEmptyViewLayoutParams != null) {
                refreshableViewWrapper.addView(view, convertEmptyViewLayoutParams);
            } else {
                refreshableViewWrapper.addView(view);
            }
        }
        if (this.mRefreshableView instanceof EmptyViewMethodAccessor) {
            ((EmptyViewMethodAccessor) this.mRefreshableView).setEmptyViewInternal(view);
        } else {
            ((AbsListView) this.mRefreshableView).setEmptyView(view);
        }
        this.mEmptyView = view;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        ((AbsListView) this.mRefreshableView).setOnItemClickListener(onItemClickListener);
    }

    public final void setOnLastItemVisibleListener(PullToRefreshBase.OnLastItemVisibleListener onLastItemVisibleListener) {
        this.mOnLastItemVisibleListener = onLastItemVisibleListener;
    }

    public final void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public final void setScrollEmptyView(boolean z) {
        this.mScrollEmptyView = z;
    }

    public void setShowIndicator(boolean z) {
        this.mShowIndicator = z;
        if (getShowIndicatorInternal()) {
            addIndicatorViews();
        } else {
            removeIndicatorViews();
        }
    }

    /* access modifiers changed from: protected */
    public void onPullToRefresh() {
        super.onPullToRefresh();
        if (getShowIndicatorInternal()) {
            int i = AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()];
            if (i == 1) {
                this.mIndicatorIvBottom.pullToRefresh();
            } else if (i == 2) {
                this.mIndicatorIvTop.pullToRefresh();
            }
        }
    }

    /* renamed from: com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void onRefreshing(boolean z) {
        super.onRefreshing(z);
        if (getShowIndicatorInternal()) {
            updateIndicatorViewsVisibility();
        }
    }

    /* access modifiers changed from: protected */
    public void onReleaseToRefresh() {
        super.onReleaseToRefresh();
        if (getShowIndicatorInternal()) {
            int i = AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()];
            if (i == 1) {
                this.mIndicatorIvBottom.releaseToRefresh();
            } else if (i == 2) {
                this.mIndicatorIvTop.releaseToRefresh();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        if (getShowIndicatorInternal()) {
            updateIndicatorViewsVisibility();
        }
    }

    /* access modifiers changed from: protected */
    public void handleStyledAttributes(TypedArray typedArray) {
        this.mShowIndicator = typedArray.getBoolean(R.styleable.PullToRefresh_ptrShowIndicator, !isPullToRefreshOverScrollEnabled());
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullStart() {
        return isFirstItemVisible();
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullEnd() {
        return isLastItemVisible();
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        View view = this.mEmptyView;
        if (view != null && !this.mScrollEmptyView) {
            view.scrollTo(-i, -i2);
        }
    }

    /* access modifiers changed from: protected */
    public void updateUIForMode() {
        super.updateUIForMode();
        if (getShowIndicatorInternal()) {
            addIndicatorViews();
        } else {
            removeIndicatorViews();
        }
    }

    private void addIndicatorViews() {
        IndicatorLayout indicatorLayout;
        IndicatorLayout indicatorLayout2;
        PullToRefreshBase.Mode mode = getMode();
        FrameLayout refreshableViewWrapper = getRefreshableViewWrapper();
        if (mode.showHeaderLoadingLayout() && this.mIndicatorIvTop == null) {
            this.mIndicatorIvTop = new IndicatorLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_START);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.indicator_right_padding);
            layoutParams.gravity = 53;
            refreshableViewWrapper.addView(this.mIndicatorIvTop, layoutParams);
        } else if (!mode.showHeaderLoadingLayout() && (indicatorLayout2 = this.mIndicatorIvTop) != null) {
            refreshableViewWrapper.removeView(indicatorLayout2);
            this.mIndicatorIvTop = null;
        }
        if (mode.showFooterLoadingLayout() && this.mIndicatorIvBottom == null) {
            this.mIndicatorIvBottom = new IndicatorLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_END);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams2.rightMargin = getResources().getDimensionPixelSize(R.dimen.indicator_right_padding);
            layoutParams2.gravity = 85;
            refreshableViewWrapper.addView(this.mIndicatorIvBottom, layoutParams2);
        } else if (!mode.showFooterLoadingLayout() && (indicatorLayout = this.mIndicatorIvBottom) != null) {
            refreshableViewWrapper.removeView(indicatorLayout);
            this.mIndicatorIvBottom = null;
        }
    }

    private boolean getShowIndicatorInternal() {
        return this.mShowIndicator && isPullToRefreshEnabled();
    }

    private boolean isFirstItemVisible() {
        View childAt;
        Adapter adapter = ((AbsListView) this.mRefreshableView).getAdapter();
        if (adapter == null || adapter.isEmpty()) {
            Log.d("PullToRefresh", "isFirstItemVisible. Empty View.");
            return true;
        } else if (((AbsListView) this.mRefreshableView).getFirstVisiblePosition() > 1 || (childAt = ((AbsListView) this.mRefreshableView).getChildAt(0)) == null || childAt.getTop() < ((AbsListView) this.mRefreshableView).getTop()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isLastItemVisible() {
        View childAt;
        Adapter adapter = ((AbsListView) this.mRefreshableView).getAdapter();
        if (adapter == null || adapter.isEmpty()) {
            Log.d("PullToRefresh", "isLastItemVisible. Empty View.");
            return true;
        }
        int count = ((AbsListView) this.mRefreshableView).getCount() - 1;
        int lastVisiblePosition = ((AbsListView) this.mRefreshableView).getLastVisiblePosition();
        Log.d("PullToRefresh", "isLastItemVisible. Last Item Position: " + count + " Last Visible Pos: " + lastVisiblePosition);
        if (lastVisiblePosition < count - 1 || (childAt = ((AbsListView) this.mRefreshableView).getChildAt(lastVisiblePosition - ((AbsListView) this.mRefreshableView).getFirstVisiblePosition())) == null || childAt.getBottom() > ((AbsListView) this.mRefreshableView).getBottom()) {
            return false;
        }
        return true;
    }

    private void removeIndicatorViews() {
        if (this.mIndicatorIvTop != null) {
            getRefreshableViewWrapper().removeView(this.mIndicatorIvTop);
            this.mIndicatorIvTop = null;
        }
        if (this.mIndicatorIvBottom != null) {
            getRefreshableViewWrapper().removeView(this.mIndicatorIvBottom);
            this.mIndicatorIvBottom = null;
        }
    }

    private void updateIndicatorViewsVisibility() {
        if (this.mIndicatorIvTop != null) {
            if (isRefreshing() || !isReadyForPullStart()) {
                if (this.mIndicatorIvTop.isVisible()) {
                    this.mIndicatorIvTop.hide();
                }
            } else if (!this.mIndicatorIvTop.isVisible()) {
                this.mIndicatorIvTop.show();
            }
        }
        if (this.mIndicatorIvBottom == null) {
            return;
        }
        if (isRefreshing() || !isReadyForPullEnd()) {
            if (this.mIndicatorIvBottom.isVisible()) {
                this.mIndicatorIvBottom.hide();
            }
        } else if (!this.mIndicatorIvBottom.isVisible()) {
            this.mIndicatorIvBottom.show();
        }
    }
}
