package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import se.emilsjolander.stickylistheaders.AdapterWrapper;
import se.emilsjolander.stickylistheaders.WrapperViewList;

public class StickyListHeadersListView extends FrameLayout {
    private AdapterWrapper mAdapter;
    private boolean mAreHeadersSticky;
    /* access modifiers changed from: private */
    public boolean mClippingToPadding;
    private AdapterWrapperDataSetObserver mDataSetObserver;
    private Drawable mDivider;
    private int mDividerHeight;
    /* access modifiers changed from: private */
    public View mHeader;
    /* access modifiers changed from: private */
    public Long mHeaderId;
    private Integer mHeaderOffset;
    /* access modifiers changed from: private */
    public Integer mHeaderPosition;
    private boolean mIsDrawingListUnderStickyHeader;
    /* access modifiers changed from: private */
    public WrapperViewList mList;
    /* access modifiers changed from: private */
    public OnHeaderClickListener mOnHeaderClickListener;
    /* access modifiers changed from: private */
    public AbsListView.OnScrollListener mOnScrollListenerDelegate;
    private OnStickyHeaderChangedListener mOnStickyHeaderChangedListener;
    private OnStickyHeaderOffsetChangedListener mOnStickyHeaderOffsetChangedListener;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    /* access modifiers changed from: private */
    public int mPaddingTop;
    private int mStickyHeaderTopOffset;

    public interface OnHeaderClickListener {
        void onHeaderClick(StickyListHeadersListView stickyListHeadersListView, View view, int i, long j, boolean z);
    }

    public interface OnStickyHeaderChangedListener {
        void onStickyHeaderChanged(StickyListHeadersListView stickyListHeadersListView, View view, int i, long j);
    }

    public interface OnStickyHeaderOffsetChangedListener {
        void onStickyHeaderOffsetChanged(StickyListHeadersListView stickyListHeadersListView, View view, int i);
    }

    public StickyListHeadersListView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.stickyListHeadersListViewStyle);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAreHeadersSticky = true;
        this.mClippingToPadding = true;
        this.mIsDrawingListUnderStickyHeader = true;
        this.mStickyHeaderTopOffset = 0;
        this.mPaddingLeft = 0;
        this.mPaddingTop = 0;
        this.mPaddingRight = 0;
        this.mPaddingBottom = 0;
        WrapperViewList wrapperViewList = new WrapperViewList(context);
        this.mList = wrapperViewList;
        this.mDivider = wrapperViewList.getDivider();
        this.mDividerHeight = this.mList.getDividerHeight();
        this.mList.setDivider((Drawable) null);
        this.mList.setDividerHeight(0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.StickyListHeadersListView, i, 0);
            try {
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.StickyListHeadersListView_android_padding, 0);
                this.mPaddingLeft = obtainStyledAttributes.getDimensionPixelSize(R.styleable.StickyListHeadersListView_android_paddingLeft, dimensionPixelSize);
                this.mPaddingTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.StickyListHeadersListView_android_paddingTop, dimensionPixelSize);
                this.mPaddingRight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.StickyListHeadersListView_android_paddingRight, dimensionPixelSize);
                int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.StickyListHeadersListView_android_paddingBottom, dimensionPixelSize);
                this.mPaddingBottom = dimensionPixelSize2;
                setPadding(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, dimensionPixelSize2);
                this.mClippingToPadding = obtainStyledAttributes.getBoolean(R.styleable.StickyListHeadersListView_android_clipToPadding, true);
                super.setClipToPadding(true);
                this.mList.setClipToPadding(this.mClippingToPadding);
                int i2 = obtainStyledAttributes.getInt(R.styleable.StickyListHeadersListView_android_scrollbars, 512);
                this.mList.setVerticalScrollBarEnabled((i2 & 512) != 0);
                this.mList.setHorizontalScrollBarEnabled((i2 & 256) != 0);
                if (Build.VERSION.SDK_INT >= 9) {
                    this.mList.setOverScrollMode(obtainStyledAttributes.getInt(R.styleable.StickyListHeadersListView_android_overScrollMode, 0));
                }
                this.mList.setFadingEdgeLength(obtainStyledAttributes.getDimensionPixelSize(R.styleable.StickyListHeadersListView_android_fadingEdgeLength, this.mList.getVerticalFadingEdgeLength()));
                int i3 = obtainStyledAttributes.getInt(R.styleable.StickyListHeadersListView_android_requiresFadingEdge, 0);
                if (i3 == 4096) {
                    this.mList.setVerticalFadingEdgeEnabled(false);
                    this.mList.setHorizontalFadingEdgeEnabled(true);
                } else if (i3 == 8192) {
                    this.mList.setVerticalFadingEdgeEnabled(true);
                    this.mList.setHorizontalFadingEdgeEnabled(false);
                } else {
                    this.mList.setVerticalFadingEdgeEnabled(false);
                    this.mList.setHorizontalFadingEdgeEnabled(false);
                }
                this.mList.setCacheColorHint(obtainStyledAttributes.getColor(R.styleable.StickyListHeadersListView_android_cacheColorHint, this.mList.getCacheColorHint()));
                if (Build.VERSION.SDK_INT >= 11) {
                    this.mList.setChoiceMode(obtainStyledAttributes.getInt(R.styleable.StickyListHeadersListView_android_choiceMode, this.mList.getChoiceMode()));
                }
                this.mList.setDrawSelectorOnTop(obtainStyledAttributes.getBoolean(R.styleable.StickyListHeadersListView_android_drawSelectorOnTop, false));
                this.mList.setFastScrollEnabled(obtainStyledAttributes.getBoolean(R.styleable.StickyListHeadersListView_android_fastScrollEnabled, this.mList.isFastScrollEnabled()));
                if (Build.VERSION.SDK_INT >= 11) {
                    this.mList.setFastScrollAlwaysVisible(obtainStyledAttributes.getBoolean(R.styleable.StickyListHeadersListView_android_fastScrollAlwaysVisible, this.mList.isFastScrollAlwaysVisible()));
                }
                this.mList.setScrollBarStyle(obtainStyledAttributes.getInt(R.styleable.StickyListHeadersListView_android_scrollbarStyle, 0));
                if (obtainStyledAttributes.hasValue(R.styleable.StickyListHeadersListView_android_listSelector)) {
                    this.mList.setSelector(obtainStyledAttributes.getDrawable(R.styleable.StickyListHeadersListView_android_listSelector));
                }
                this.mList.setScrollingCacheEnabled(obtainStyledAttributes.getBoolean(R.styleable.StickyListHeadersListView_android_scrollingCache, this.mList.isScrollingCacheEnabled()));
                if (obtainStyledAttributes.hasValue(R.styleable.StickyListHeadersListView_android_divider)) {
                    this.mDivider = obtainStyledAttributes.getDrawable(R.styleable.StickyListHeadersListView_android_divider);
                }
                this.mList.setStackFromBottom(obtainStyledAttributes.getBoolean(R.styleable.StickyListHeadersListView_android_stackFromBottom, false));
                this.mDividerHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.StickyListHeadersListView_android_dividerHeight, this.mDividerHeight);
                this.mList.setTranscriptMode(obtainStyledAttributes.getInt(R.styleable.StickyListHeadersListView_android_transcriptMode, 0));
                this.mAreHeadersSticky = obtainStyledAttributes.getBoolean(R.styleable.StickyListHeadersListView_hasStickyHeaders, true);
                this.mIsDrawingListUnderStickyHeader = obtainStyledAttributes.getBoolean(R.styleable.StickyListHeadersListView_isDrawingListUnderStickyHeader, true);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.mList.setLifeCycleListener(new WrapperViewListLifeCycleListener());
        this.mList.setOnScrollListener(new WrapperListScrollListener());
        addView(this.mList);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureHeader(this.mHeader);
    }

    private void ensureHeaderHasCorrectLayoutParams(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        } else if (layoutParams.height == -1 || layoutParams.width == -2) {
            layoutParams.height = -2;
            layoutParams.width = -1;
            view.setLayoutParams(layoutParams);
        }
    }

    private void measureHeader(View view) {
        if (view != null) {
            measureChild(view, View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - this.mPaddingLeft) - this.mPaddingRight, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        WrapperViewList wrapperViewList = this.mList;
        wrapperViewList.layout(0, 0, wrapperViewList.getMeasuredWidth(), getHeight());
        View view = this.mHeader;
        if (view != null) {
            int i5 = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
            View view2 = this.mHeader;
            view2.layout(this.mPaddingLeft, i5, view2.getMeasuredWidth() + this.mPaddingLeft, this.mHeader.getMeasuredHeight() + i5);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mList.getVisibility() == 0 || this.mList.getAnimation() != null) {
            drawChild(canvas, this.mList, 0);
        }
    }

    /* access modifiers changed from: private */
    public void clearHeader() {
        View view = this.mHeader;
        if (view != null) {
            removeView(view);
            this.mHeader = null;
            this.mHeaderId = null;
            this.mHeaderPosition = null;
            this.mHeaderOffset = null;
            this.mList.setTopClippingLength(0);
            updateHeaderVisibilities();
        }
    }

    /* access modifiers changed from: private */
    public void updateOrClearHeader(int i) {
        AdapterWrapper adapterWrapper = this.mAdapter;
        boolean z = false;
        int count = adapterWrapper == null ? 0 : adapterWrapper.getCount();
        if (count != 0 && this.mAreHeadersSticky) {
            int headerViewsCount = i - this.mList.getHeaderViewsCount();
            if (this.mList.getChildCount() > 0 && this.mList.getChildAt(0).getBottom() < stickyHeaderTop()) {
                headerViewsCount++;
            }
            boolean z2 = this.mList.getChildCount() != 0;
            boolean z3 = z2 && this.mList.getFirstVisiblePosition() == 0 && this.mList.getChildAt(0).getTop() >= stickyHeaderTop();
            if (headerViewsCount > count - 1 || headerViewsCount < 0) {
                z = true;
            }
            if (!z2 || z || z3) {
                clearHeader();
            } else {
                updateHeader(headerViewsCount);
            }
        }
    }

    private void updateHeader(int i) {
        View childAt;
        Integer num = this.mHeaderPosition;
        if (num == null || num.intValue() != i) {
            this.mHeaderPosition = Integer.valueOf(i);
            long headerId = this.mAdapter.getHeaderId(i);
            Long l = this.mHeaderId;
            if (l == null || l.longValue() != headerId) {
                this.mHeaderId = Long.valueOf(headerId);
                View headerView = this.mAdapter.getHeaderView(this.mHeaderPosition.intValue(), this.mHeader, this);
                if (this.mHeader != headerView) {
                    if (headerView != null) {
                        swapHeader(headerView);
                    } else {
                        throw new NullPointerException("header may not be null");
                    }
                }
                ensureHeaderHasCorrectLayoutParams(this.mHeader);
                measureHeader(this.mHeader);
                OnStickyHeaderChangedListener onStickyHeaderChangedListener = this.mOnStickyHeaderChangedListener;
                if (onStickyHeaderChangedListener != null) {
                    onStickyHeaderChangedListener.onStickyHeaderChanged(this, this.mHeader, i, this.mHeaderId.longValue());
                }
                this.mHeaderOffset = null;
            }
        }
        int stickyHeaderTop = stickyHeaderTop();
        int i2 = 0;
        while (true) {
            if (i2 >= this.mList.getChildCount()) {
                break;
            }
            childAt = this.mList.getChildAt(i2);
            boolean z = (childAt instanceof WrapperView) && ((WrapperView) childAt).hasHeader();
            boolean containsFooterView = this.mList.containsFooterView(childAt);
            if (childAt.getTop() < stickyHeaderTop() || (!z && !containsFooterView)) {
                i2++;
            }
        }
        stickyHeaderTop = Math.min(childAt.getTop() - this.mHeader.getMeasuredHeight(), stickyHeaderTop);
        setHeaderOffet(stickyHeaderTop);
        if (!this.mIsDrawingListUnderStickyHeader) {
            this.mList.setTopClippingLength(this.mHeader.getMeasuredHeight() + this.mHeaderOffset.intValue());
        }
        updateHeaderVisibilities();
    }

    private void swapHeader(View view) {
        View view2 = this.mHeader;
        if (view2 != null) {
            removeView(view2);
        }
        this.mHeader = view;
        addView(view);
        if (this.mOnHeaderClickListener != null) {
            this.mHeader.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    OnHeaderClickListener access$500 = StickyListHeadersListView.this.mOnHeaderClickListener;
                    StickyListHeadersListView stickyListHeadersListView = StickyListHeadersListView.this;
                    access$500.onHeaderClick(stickyListHeadersListView, stickyListHeadersListView.mHeader, StickyListHeadersListView.this.mHeaderPosition.intValue(), StickyListHeadersListView.this.mHeaderId.longValue(), true);
                }
            });
        }
        this.mHeader.setClickable(true);
    }

    private void updateHeaderVisibilities() {
        int stickyHeaderTop = stickyHeaderTop();
        int childCount = this.mList.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mList.getChildAt(i);
            if (childAt instanceof WrapperView) {
                WrapperView wrapperView = (WrapperView) childAt;
                if (wrapperView.hasHeader()) {
                    View view = wrapperView.mHeader;
                    if (wrapperView.getTop() < stickyHeaderTop) {
                        if (view.getVisibility() != 4) {
                            view.setVisibility(4);
                        }
                    } else if (view.getVisibility() != 0) {
                        view.setVisibility(0);
                    }
                }
            }
        }
    }

    private void setHeaderOffet(int i) {
        Integer num = this.mHeaderOffset;
        if (num == null || num.intValue() != i) {
            this.mHeaderOffset = Integer.valueOf(i);
            if (Build.VERSION.SDK_INT >= 11) {
                this.mHeader.setTranslationY((float) this.mHeaderOffset.intValue());
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeader.getLayoutParams();
                marginLayoutParams.topMargin = this.mHeaderOffset.intValue();
                this.mHeader.setLayoutParams(marginLayoutParams);
            }
            OnStickyHeaderOffsetChangedListener onStickyHeaderOffsetChangedListener = this.mOnStickyHeaderOffsetChangedListener;
            if (onStickyHeaderOffsetChangedListener != null) {
                onStickyHeaderOffsetChangedListener.onStickyHeaderOffsetChanged(this, this.mHeader, -this.mHeaderOffset.intValue());
            }
        }
    }

    private class AdapterWrapperDataSetObserver extends DataSetObserver {
        private AdapterWrapperDataSetObserver() {
        }

        public void onChanged() {
            StickyListHeadersListView.this.clearHeader();
        }

        public void onInvalidated() {
            StickyListHeadersListView.this.clearHeader();
        }
    }

    private class WrapperListScrollListener implements AbsListView.OnScrollListener {
        private WrapperListScrollListener() {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (StickyListHeadersListView.this.mOnScrollListenerDelegate != null) {
                StickyListHeadersListView.this.mOnScrollListenerDelegate.onScroll(absListView, i, i2, i3);
            }
            StickyListHeadersListView stickyListHeadersListView = StickyListHeadersListView.this;
            stickyListHeadersListView.updateOrClearHeader(stickyListHeadersListView.mList.getFixedFirstVisibleItem());
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (StickyListHeadersListView.this.mOnScrollListenerDelegate != null) {
                StickyListHeadersListView.this.mOnScrollListenerDelegate.onScrollStateChanged(absListView, i);
            }
        }
    }

    private class WrapperViewListLifeCycleListener implements WrapperViewList.LifeCycleListener {
        private WrapperViewListLifeCycleListener() {
        }

        public void onDispatchDrawOccurred(Canvas canvas) {
            if (Build.VERSION.SDK_INT < 8) {
                StickyListHeadersListView stickyListHeadersListView = StickyListHeadersListView.this;
                stickyListHeadersListView.updateOrClearHeader(stickyListHeadersListView.mList.getFixedFirstVisibleItem());
            }
            if (StickyListHeadersListView.this.mHeader == null) {
                return;
            }
            if (StickyListHeadersListView.this.mClippingToPadding) {
                canvas.save();
                canvas.clipRect(0, StickyListHeadersListView.this.mPaddingTop, StickyListHeadersListView.this.getRight(), StickyListHeadersListView.this.getBottom());
                StickyListHeadersListView stickyListHeadersListView2 = StickyListHeadersListView.this;
                boolean unused = stickyListHeadersListView2.drawChild(canvas, stickyListHeadersListView2.mHeader, 0);
                canvas.restore();
                return;
            }
            StickyListHeadersListView stickyListHeadersListView3 = StickyListHeadersListView.this;
            boolean unused2 = stickyListHeadersListView3.drawChild(canvas, stickyListHeadersListView3.mHeader, 0);
        }
    }

    private class AdapterWrapperHeaderClickHandler implements AdapterWrapper.OnHeaderClickListener {
        private AdapterWrapperHeaderClickHandler() {
        }

        public void onHeaderClick(View view, int i, long j) {
            StickyListHeadersListView.this.mOnHeaderClickListener.onHeaderClick(StickyListHeadersListView.this, view, i, j, false);
        }
    }

    private boolean isStartOfSection(int i) {
        return i == 0 || this.mAdapter.getHeaderId(i) != this.mAdapter.getHeaderId(i - 1);
    }

    public int getHeaderOverlap(int i) {
        if (isStartOfSection(Math.max(0, i - getHeaderViewsCount()))) {
            return 0;
        }
        View headerView = this.mAdapter.getHeaderView(i, (View) null, this.mList);
        if (headerView != null) {
            ensureHeaderHasCorrectLayoutParams(headerView);
            measureHeader(headerView);
            return headerView.getMeasuredHeight();
        }
        throw new NullPointerException("header may not be null");
    }

    private int stickyHeaderTop() {
        return this.mStickyHeaderTopOffset + (this.mClippingToPadding ? this.mPaddingTop : 0);
    }

    public void setAreHeadersSticky(boolean z) {
        this.mAreHeadersSticky = z;
        if (!z) {
            clearHeader();
        } else {
            updateOrClearHeader(this.mList.getFixedFirstVisibleItem());
        }
        this.mList.invalidate();
    }

    public boolean areHeadersSticky() {
        return this.mAreHeadersSticky;
    }

    @Deprecated
    public boolean getAreHeadersSticky() {
        return areHeadersSticky();
    }

    public void setStickyHeaderTopOffset(int i) {
        this.mStickyHeaderTopOffset = i;
        updateOrClearHeader(this.mList.getFixedFirstVisibleItem());
    }

    public int getStickyHeaderTopOffset() {
        return this.mStickyHeaderTopOffset;
    }

    public void setDrawingListUnderStickyHeader(boolean z) {
        this.mIsDrawingListUnderStickyHeader = z;
        this.mList.setTopClippingLength(0);
    }

    public boolean isDrawingListUnderStickyHeader() {
        return this.mIsDrawingListUnderStickyHeader;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mOnHeaderClickListener = onHeaderClickListener;
        AdapterWrapper adapterWrapper = this.mAdapter;
        if (adapterWrapper == null) {
            return;
        }
        if (onHeaderClickListener != null) {
            adapterWrapper.setOnHeaderClickListener(new AdapterWrapperHeaderClickHandler());
            View view = this.mHeader;
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        OnHeaderClickListener access$500 = StickyListHeadersListView.this.mOnHeaderClickListener;
                        StickyListHeadersListView stickyListHeadersListView = StickyListHeadersListView.this;
                        access$500.onHeaderClick(stickyListHeadersListView, stickyListHeadersListView.mHeader, StickyListHeadersListView.this.mHeaderPosition.intValue(), StickyListHeadersListView.this.mHeaderId.longValue(), true);
                    }
                });
                return;
            }
            return;
        }
        adapterWrapper.setOnHeaderClickListener((AdapterWrapper.OnHeaderClickListener) null);
    }

    public void setOnStickyHeaderOffsetChangedListener(OnStickyHeaderOffsetChangedListener onStickyHeaderOffsetChangedListener) {
        this.mOnStickyHeaderOffsetChangedListener = onStickyHeaderOffsetChangedListener;
    }

    public void setOnStickyHeaderChangedListener(OnStickyHeaderChangedListener onStickyHeaderChangedListener) {
        this.mOnStickyHeaderChangedListener = onStickyHeaderChangedListener;
    }

    public View getListChildAt(int i) {
        return this.mList.getChildAt(i);
    }

    public int getListChildCount() {
        return this.mList.getChildCount();
    }

    public ListView getWrappedList() {
        return this.mList;
    }

    private boolean requireSdkVersion(int i) {
        if (Build.VERSION.SDK_INT >= i) {
            return true;
        }
        Log.e("StickyListHeaders", "Api lvl must be at least " + i + " to call this method");
        return false;
    }

    public void setAdapter(StickyListHeadersAdapter stickyListHeadersAdapter) {
        if (stickyListHeadersAdapter == null) {
            AdapterWrapper adapterWrapper = this.mAdapter;
            if (adapterWrapper instanceof SectionIndexerAdapterWrapper) {
                ((SectionIndexerAdapterWrapper) adapterWrapper).mSectionIndexerDelegate = null;
            }
            AdapterWrapper adapterWrapper2 = this.mAdapter;
            if (adapterWrapper2 != null) {
                adapterWrapper2.mDelegate = null;
            }
            this.mList.setAdapter((ListAdapter) null);
            clearHeader();
            return;
        }
        AdapterWrapper adapterWrapper3 = this.mAdapter;
        if (adapterWrapper3 != null) {
            adapterWrapper3.unregisterDataSetObserver(this.mDataSetObserver);
        }
        if (stickyListHeadersAdapter instanceof SectionIndexer) {
            this.mAdapter = new SectionIndexerAdapterWrapper(getContext(), stickyListHeadersAdapter);
        } else {
            this.mAdapter = new AdapterWrapper(getContext(), stickyListHeadersAdapter);
        }
        AdapterWrapperDataSetObserver adapterWrapperDataSetObserver = new AdapterWrapperDataSetObserver();
        this.mDataSetObserver = adapterWrapperDataSetObserver;
        this.mAdapter.registerDataSetObserver(adapterWrapperDataSetObserver);
        if (this.mOnHeaderClickListener != null) {
            this.mAdapter.setOnHeaderClickListener(new AdapterWrapperHeaderClickHandler());
        } else {
            this.mAdapter.setOnHeaderClickListener((AdapterWrapper.OnHeaderClickListener) null);
        }
        this.mAdapter.setDivider(this.mDivider, this.mDividerHeight);
        this.mList.setAdapter(this.mAdapter);
        clearHeader();
    }

    public StickyListHeadersAdapter getAdapter() {
        AdapterWrapper adapterWrapper = this.mAdapter;
        if (adapterWrapper == null) {
            return null;
        }
        return adapterWrapper.mDelegate;
    }

    public void setDivider(Drawable drawable) {
        this.mDivider = drawable;
        AdapterWrapper adapterWrapper = this.mAdapter;
        if (adapterWrapper != null) {
            adapterWrapper.setDivider(drawable, this.mDividerHeight);
        }
    }

    public void setDividerHeight(int i) {
        this.mDividerHeight = i;
        AdapterWrapper adapterWrapper = this.mAdapter;
        if (adapterWrapper != null) {
            adapterWrapper.setDivider(this.mDivider, i);
        }
    }

    public Drawable getDivider() {
        return this.mDivider;
    }

    public int getDividerHeight() {
        return this.mDividerHeight;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mOnScrollListenerDelegate = onScrollListener;
    }

    public void setOnTouchListener(final View.OnTouchListener onTouchListener) {
        if (onTouchListener != null) {
            this.mList.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return onTouchListener.onTouch(StickyListHeadersListView.this, motionEvent);
                }
            });
        } else {
            this.mList.setOnTouchListener((View.OnTouchListener) null);
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mList.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.mList.setOnItemLongClickListener(onItemLongClickListener);
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        this.mList.addHeaderView(view, obj, z);
    }

    public void addHeaderView(View view) {
        this.mList.addHeaderView(view);
    }

    public void removeHeaderView(View view) {
        this.mList.removeHeaderView(view);
    }

    public int getHeaderViewsCount() {
        return this.mList.getHeaderViewsCount();
    }

    public void addFooterView(View view, Object obj, boolean z) {
        this.mList.addFooterView(view, obj, z);
    }

    public void addFooterView(View view) {
        this.mList.addFooterView(view);
    }

    public void removeFooterView(View view) {
        this.mList.removeFooterView(view);
    }

    public int getFooterViewsCount() {
        return this.mList.getFooterViewsCount();
    }

    public void setEmptyView(View view) {
        this.mList.setEmptyView(view);
    }

    public View getEmptyView() {
        return this.mList.getEmptyView();
    }

    public boolean isVerticalScrollBarEnabled() {
        return this.mList.isVerticalScrollBarEnabled();
    }

    public boolean isHorizontalScrollBarEnabled() {
        return this.mList.isHorizontalScrollBarEnabled();
    }

    public void setVerticalScrollBarEnabled(boolean z) {
        this.mList.setVerticalScrollBarEnabled(z);
    }

    public void setHorizontalScrollBarEnabled(boolean z) {
        this.mList.setHorizontalScrollBarEnabled(z);
    }

    public int getOverScrollMode() {
        if (requireSdkVersion(9)) {
            return this.mList.getOverScrollMode();
        }
        return 0;
    }

    public void setOverScrollMode(int i) {
        WrapperViewList wrapperViewList;
        if (requireSdkVersion(9) && (wrapperViewList = this.mList) != null) {
            wrapperViewList.setOverScrollMode(i);
        }
    }

    public void smoothScrollBy(int i, int i2) {
        if (requireSdkVersion(8)) {
            this.mList.smoothScrollBy(i, i2);
        }
    }

    public void smoothScrollByOffset(int i) {
        if (requireSdkVersion(11)) {
            this.mList.smoothScrollByOffset(i);
        }
    }

    public void smoothScrollToPosition(int i) {
        if (!requireSdkVersion(8)) {
            return;
        }
        if (Build.VERSION.SDK_INT < 11) {
            this.mList.smoothScrollToPosition(i);
            return;
        }
        int i2 = 0;
        int headerOverlap = this.mAdapter == null ? 0 : getHeaderOverlap(i);
        if (!this.mClippingToPadding) {
            i2 = this.mPaddingTop;
        }
        this.mList.smoothScrollToPositionFromTop(i, headerOverlap - i2);
    }

    public void smoothScrollToPosition(int i, int i2) {
        if (requireSdkVersion(8)) {
            this.mList.smoothScrollToPosition(i, i2);
        }
    }

    public void smoothScrollToPositionFromTop(int i, int i2) {
        if (requireSdkVersion(11)) {
            int i3 = 0;
            int headerOverlap = i2 + (this.mAdapter == null ? 0 : getHeaderOverlap(i));
            if (!this.mClippingToPadding) {
                i3 = this.mPaddingTop;
            }
            this.mList.smoothScrollToPositionFromTop(i, headerOverlap - i3);
        }
    }

    public void smoothScrollToPositionFromTop(int i, int i2, int i3) {
        if (requireSdkVersion(11)) {
            int i4 = 0;
            int headerOverlap = i2 + (this.mAdapter == null ? 0 : getHeaderOverlap(i));
            if (!this.mClippingToPadding) {
                i4 = this.mPaddingTop;
            }
            this.mList.smoothScrollToPositionFromTop(i, headerOverlap - i4, i3);
        }
    }

    public void setSelection(int i) {
        setSelectionFromTop(i, 0);
    }

    public void setSelectionAfterHeaderView() {
        this.mList.setSelectionAfterHeaderView();
    }

    public void setSelectionFromTop(int i, int i2) {
        int i3 = 0;
        int headerOverlap = i2 + (this.mAdapter == null ? 0 : getHeaderOverlap(i));
        if (!this.mClippingToPadding) {
            i3 = this.mPaddingTop;
        }
        this.mList.setSelectionFromTop(i, headerOverlap - i3);
    }

    public void setSelector(Drawable drawable) {
        this.mList.setSelector(drawable);
    }

    public void setSelector(int i) {
        this.mList.setSelector(i);
    }

    public int getFirstVisiblePosition() {
        return this.mList.getFirstVisiblePosition();
    }

    public int getLastVisiblePosition() {
        return this.mList.getLastVisiblePosition();
    }

    public void setChoiceMode(int i) {
        this.mList.setChoiceMode(i);
    }

    public void setItemChecked(int i, boolean z) {
        this.mList.setItemChecked(i, z);
    }

    public int getCheckedItemCount() {
        if (requireSdkVersion(11)) {
            return this.mList.getCheckedItemCount();
        }
        return 0;
    }

    public long[] getCheckedItemIds() {
        if (requireSdkVersion(8)) {
            return this.mList.getCheckedItemIds();
        }
        return null;
    }

    public int getCheckedItemPosition() {
        return this.mList.getCheckedItemPosition();
    }

    public SparseBooleanArray getCheckedItemPositions() {
        return this.mList.getCheckedItemPositions();
    }

    public int getCount() {
        return this.mList.getCount();
    }

    public Object getItemAtPosition(int i) {
        return this.mList.getItemAtPosition(i);
    }

    public long getItemIdAtPosition(int i) {
        return this.mList.getItemIdAtPosition(i);
    }

    public void setOnCreateContextMenuListener(View.OnCreateContextMenuListener onCreateContextMenuListener) {
        this.mList.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }

    public boolean showContextMenu() {
        return this.mList.showContextMenu();
    }

    public void invalidateViews() {
        this.mList.invalidateViews();
    }

    public void setClipToPadding(boolean z) {
        WrapperViewList wrapperViewList = this.mList;
        if (wrapperViewList != null) {
            wrapperViewList.setClipToPadding(z);
        }
        this.mClippingToPadding = z;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
        WrapperViewList wrapperViewList = this.mList;
        if (wrapperViewList != null) {
            wrapperViewList.setPadding(i, i2, i3, i4);
        }
        super.setPadding(0, 0, 0, 0);
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void recomputePadding() {
        setPadding(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public void setFastScrollEnabled(boolean z) {
        this.mList.setFastScrollEnabled(z);
    }

    public void setFastScrollAlwaysVisible(boolean z) {
        if (requireSdkVersion(11)) {
            this.mList.setFastScrollAlwaysVisible(z);
        }
    }

    public boolean isFastScrollAlwaysVisible() {
        if (Build.VERSION.SDK_INT < 11) {
            return false;
        }
        return this.mList.isFastScrollAlwaysVisible();
    }

    public void setScrollBarStyle(int i) {
        this.mList.setScrollBarStyle(i);
    }

    public int getScrollBarStyle() {
        return this.mList.getScrollBarStyle();
    }

    public int getPositionForView(View view) {
        return this.mList.getPositionForView(view);
    }

    public void setMultiChoiceModeListener(AbsListView.MultiChoiceModeListener multiChoiceModeListener) {
        if (requireSdkVersion(11)) {
            this.mList.setMultiChoiceModeListener(multiChoiceModeListener);
        }
    }

    public Parcelable onSaveInstanceState() {
        if (super.onSaveInstanceState() == View.BaseSavedState.EMPTY_STATE) {
            return this.mList.onSaveInstanceState();
        }
        throw new IllegalStateException("Handling non empty state of parent class is not implemented");
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(View.BaseSavedState.EMPTY_STATE);
        this.mList.onRestoreInstanceState(parcelable);
    }

    public boolean canScrollVertically(int i) {
        return this.mList.canScrollVertically(i);
    }

    public void setTranscriptMode(int i) {
        this.mList.setTranscriptMode(i);
    }

    public void setBlockLayoutChildren(boolean z) {
        this.mList.setBlockLayoutChildren(z);
    }

    public void setStackFromBottom(boolean z) {
        this.mList.setStackFromBottom(z);
    }

    public boolean isStackFromBottom() {
        return this.mList.isStackFromBottom();
    }
}
