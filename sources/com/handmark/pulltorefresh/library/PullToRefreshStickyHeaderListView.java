package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

public class PullToRefreshStickyHeaderListView extends PullToRefreshAdapterViewBase<PinnedSectionListView> {
    private LoadingLayout mFooterLoadingView;
    private LoadingLayout mHeaderLoadingView;
    private boolean mListViewExtrasEnabled;
    /* access modifiers changed from: private */
    public FrameLayout mLvFooterLoadingFrame;

    public PullToRefreshStickyHeaderListView(Context context) {
        super(context);
    }

    public PullToRefreshStickyHeaderListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshStickyHeaderListView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshStickyHeaderListView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /* access modifiers changed from: protected */
    public void onRefreshing(boolean z) {
        int i;
        int i2;
        LoadingLayout loadingLayout;
        LoadingLayout loadingLayout2;
        LoadingLayout loadingLayout3;
        ListAdapter adapter = ((PinnedSectionListView) this.mRefreshableView).getAdapter();
        if (!this.mListViewExtrasEnabled || !getShowViewWhileRefreshing() || adapter == null || adapter.isEmpty()) {
            super.onRefreshing(z);
            return;
        }
        super.onRefreshing(false);
        int i3 = AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()];
        if (i3 == 1 || i3 == 2) {
            loadingLayout3 = getFooterLayout();
            LoadingLayout loadingLayout4 = this.mFooterLoadingView;
            LoadingLayout loadingLayout5 = this.mHeaderLoadingView;
            i = ((PinnedSectionListView) this.mRefreshableView).getCount() - 1;
            LoadingLayout loadingLayout6 = loadingLayout5;
            i2 = getScrollY() - getFooterSize();
            loadingLayout2 = loadingLayout4;
            loadingLayout = loadingLayout6;
        } else {
            loadingLayout3 = getHeaderLayout();
            loadingLayout2 = this.mHeaderLoadingView;
            loadingLayout = this.mFooterLoadingView;
            i2 = getScrollY() + getHeaderSize();
            i = 0;
        }
        loadingLayout3.reset();
        loadingLayout3.hideAllViews();
        loadingLayout.setVisibility(8);
        loadingLayout2.setVisibility(0);
        loadingLayout2.refreshing();
        if (z) {
            disableLoadingLayoutVisibilityChanges();
            setHeaderScroll(i2);
            ((PinnedSectionListView) this.mRefreshableView).setSelection(i);
            smoothScrollTo(0);
        }
    }

    /* renamed from: com.handmark.pulltorefresh.library.PullToRefreshStickyHeaderListView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode[] r0 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = r0
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r1 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.MANUAL_REFRESH_ONLY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r1 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_END     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.handmark.pulltorefresh.library.PullToRefreshBase$Mode r1 = com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.handmark.pulltorefresh.library.PullToRefreshStickyHeaderListView.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        int i;
        LoadingLayout loadingLayout;
        LoadingLayout loadingLayout2;
        if (!this.mListViewExtrasEnabled) {
            super.onReset();
            return;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()];
        int i3 = 0;
        int i4 = 1;
        if (i2 == 1 || i2 == 2) {
            loadingLayout2 = getFooterLayout();
            loadingLayout = this.mFooterLoadingView;
            int count = ((PinnedSectionListView) this.mRefreshableView).getCount() - 1;
            int footerSize = getFooterSize();
            if (Math.abs(((PinnedSectionListView) this.mRefreshableView).getLastVisiblePosition() - count) <= 1) {
                i3 = 1;
            }
            i4 = i3;
            i3 = count;
            i = footerSize;
        } else {
            loadingLayout2 = getHeaderLayout();
            loadingLayout = this.mHeaderLoadingView;
            i = -getHeaderSize();
            if (Math.abs(((PinnedSectionListView) this.mRefreshableView).getFirstVisiblePosition() - 0) > 1) {
                i4 = 0;
            }
        }
        if (loadingLayout.getVisibility() == 0) {
            loadingLayout2.showInvisibleViews();
            loadingLayout.setVisibility(8);
            if (!(i4 == 0 || getState() == PullToRefreshBase.State.MANUAL_REFRESHING)) {
                ((PinnedSectionListView) this.mRefreshableView).setSelection(i3);
                setHeaderScroll(i);
            }
        }
        super.onReset();
    }

    /* access modifiers changed from: protected */
    public LoadingLayoutProxy createLoadingLayoutProxy(boolean z, boolean z2) {
        LoadingLayoutProxy createLoadingLayoutProxy = super.createLoadingLayoutProxy(z, z2);
        if (this.mListViewExtrasEnabled) {
            PullToRefreshBase.Mode mode = getMode();
            if (z && mode.showHeaderLoadingLayout()) {
                createLoadingLayoutProxy.addLayout(this.mHeaderLoadingView);
            }
            if (z2 && mode.showFooterLoadingLayout()) {
                createLoadingLayoutProxy.addLayout(this.mFooterLoadingView);
            }
        }
        return createLoadingLayoutProxy;
    }

    /* access modifiers changed from: protected */
    public PinnedSectionListView createListView(Context context, AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT >= 9) {
            return new InternalListViewSDK9(context, attributeSet);
        }
        return new PinnedSectionListView(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public PinnedSectionListView createRefreshableView(Context context, AttributeSet attributeSet) {
        PinnedSectionListView createListView = createListView(context, attributeSet);
        createListView.setId(16908298);
        return createListView;
    }

    /* access modifiers changed from: protected */
    public void handleStyledAttributes(TypedArray typedArray) {
        super.handleStyledAttributes(typedArray);
        boolean z = typedArray.getBoolean(R.styleable.PullToRefresh_ptrListViewExtrasEnabled, true);
        this.mListViewExtrasEnabled = z;
        if (z) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 1);
            FrameLayout frameLayout = new FrameLayout(getContext());
            LoadingLayout createLoadingLayout = createLoadingLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_START, typedArray);
            this.mHeaderLoadingView = createLoadingLayout;
            createLoadingLayout.setVisibility(8);
            frameLayout.addView(this.mHeaderLoadingView, layoutParams);
            ((PinnedSectionListView) this.mRefreshableView).addHeaderView(frameLayout, (Object) null, false);
            this.mLvFooterLoadingFrame = new FrameLayout(getContext());
            LoadingLayout createLoadingLayout2 = createLoadingLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_END, typedArray);
            this.mFooterLoadingView = createLoadingLayout2;
            createLoadingLayout2.setVisibility(8);
            this.mLvFooterLoadingFrame.addView(this.mFooterLoadingView, layoutParams);
            if (!typedArray.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
                setScrollingWhileRefreshingEnabled(true);
            }
        }
    }

    final class InternalListViewSDK9 extends PinnedSectionListView {
        public InternalListViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshStickyHeaderListView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    protected class InternalListView extends ListView implements EmptyViewMethodAccessor {
        private boolean mAddedLvFooter = false;

        public InternalListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            try {
                return super.dispatchTouchEvent(motionEvent);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void setAdapter(ListAdapter listAdapter) {
            if (PullToRefreshStickyHeaderListView.this.mLvFooterLoadingFrame != null && !this.mAddedLvFooter) {
                addFooterView(PullToRefreshStickyHeaderListView.this.mLvFooterLoadingFrame, (Object) null, false);
                this.mAddedLvFooter = true;
            }
            super.setAdapter(listAdapter);
        }

        public void setEmptyView(View view) {
            PullToRefreshStickyHeaderListView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }
}
