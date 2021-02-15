package com.tonicartos.superslim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.tonicartos.superslim.LayoutState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class LayoutManager extends RecyclerView.LayoutManager {
    public static final int INVALID_POSITON = -1;
    private static final int NO_POSITION_REQUEST = -1;
    static final int SECTION_MANAGER_CUSTOM = -1;
    static final int SECTION_MANAGER_GRID = 2;
    static final int SECTION_MANAGER_LINEAR = 1;
    static final int SECTION_MANAGER_STAGGERED_GRID = 3;
    private final SectionLayoutManager mGridSlm;
    private final SectionLayoutManager mLinearSlm = new LinearSLM(this);
    private Rect mRect = new Rect();
    private int mRequestPosition = -1;
    private int mRequestPositionOffset = 0;
    private HashMap<String, SectionLayoutManager> mSlms;
    private boolean mSmoothScrollEnabled = true;

    public enum Direction {
        START,
        END,
        NONE
    }

    public boolean canScrollVertically() {
        return true;
    }

    public LayoutManager(Context context) {
        this.mGridSlm = new GridSLM(this, context);
        this.mSlms = new HashMap<>();
    }

    LayoutManager(Builder builder) {
        this.mGridSlm = new GridSLM(this, builder.context);
        this.mSlms = builder.slms;
    }

    public void addSlm(String str, SectionLayoutManager sectionLayoutManager) {
        this.mSlms.put(str, sectionLayoutManager);
    }

    public View findFirstCompletelyVisibleItem() {
        View findAttachedHeaderOrFirstViewForSection;
        int i = 0;
        View view = null;
        SectionData sectionData = null;
        for (int i2 = 0; i2 < getChildCount() - 1; i2++) {
            sectionData = new SectionData(this, getChildAt(0));
            view = getSlm(sectionData).getFirstCompletelyVisibleView(sectionData.firstPosition, false);
            if (view != null) {
                break;
            }
        }
        if (view == null) {
            return null;
        }
        int position = getPosition(view);
        if (position != sectionData.firstPosition && position <= sectionData.firstPosition + 1 && (findAttachedHeaderOrFirstViewForSection = findAttachedHeaderOrFirstViewForSection(sectionData.firstPosition, 0, Direction.START)) != null && ((LayoutParams) findAttachedHeaderOrFirstViewForSection.getLayoutParams()).isHeader) {
            if (getClipToPadding()) {
                i = getPaddingTop();
            }
            int height = getClipToPadding() ? getHeight() - getPaddingBottom() : getHeight();
            int decoratedTop = getDecoratedTop(findAttachedHeaderOrFirstViewForSection);
            return (decoratedTop < i || height < getDecoratedBottom(findAttachedHeaderOrFirstViewForSection) || decoratedTop >= getDecoratedTop(view)) ? view : findAttachedHeaderOrFirstViewForSection;
        }
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View findFirstCompletelyVisibleItem = findFirstCompletelyVisibleItem();
        if (findFirstCompletelyVisibleItem == null) {
            return -1;
        }
        return getPosition(findFirstCompletelyVisibleItem);
    }

    public View findFirstVisibleItem() {
        View findAttachedHeaderOrFirstViewForSection;
        SectionData sectionData = new SectionData(this, getChildAt(0));
        View firstVisibleView = getSlm(sectionData).getFirstVisibleView(sectionData.firstPosition, false);
        int position = getPosition(firstVisibleView);
        if (position > sectionData.firstPosition + 1 || position == sectionData.firstPosition || (findAttachedHeaderOrFirstViewForSection = findAttachedHeaderOrFirstViewForSection(sectionData.firstPosition, 0, Direction.START)) == null) {
            return firstVisibleView;
        }
        if (getDecoratedBottom(findAttachedHeaderOrFirstViewForSection) <= getDecoratedTop(firstVisibleView)) {
            return findAttachedHeaderOrFirstViewForSection;
        }
        LayoutParams layoutParams = (LayoutParams) findAttachedHeaderOrFirstViewForSection.getLayoutParams();
        return ((!layoutParams.isHeaderInline() || layoutParams.isHeaderOverlay()) && getDecoratedTop(findAttachedHeaderOrFirstViewForSection) == getDecoratedTop(firstVisibleView)) ? findAttachedHeaderOrFirstViewForSection : firstVisibleView;
    }

    public int findFirstVisibleItemPosition() {
        View findFirstVisibleItem = findFirstVisibleItem();
        if (findFirstVisibleItem == null) {
            return -1;
        }
        return getPosition(findFirstVisibleItem);
    }

    public View findLastCompletelyVisibleItem() {
        SectionData sectionData = new SectionData(this, getChildAt(getChildCount() - 1));
        return getSlm(sectionData).getLastCompletelyVisibleView(sectionData.firstPosition);
    }

    public int findLastCompletelyVisibleItemPosition() {
        SectionData sectionData = new SectionData(this, getChildAt(getChildCount() - 1));
        return getSlm(sectionData).findLastCompletelyVisibleItemPosition(sectionData.firstPosition);
    }

    public View findLastVisibleItem() {
        SectionData sectionData = new SectionData(this, getChildAt(getChildCount() - 1));
        return getSlm(sectionData).getLastVisibleView(sectionData.firstPosition);
    }

    public int findLastVisibleItemPosition() {
        SectionData sectionData = new SectionData(this, getChildAt(getChildCount() - 1));
        return getSlm(sectionData).findLastVisibleItemPosition(sectionData.firstPosition);
    }

    public boolean isSmoothScrollEnabled() {
        return this.mSmoothScrollEnabled;
    }

    public void setSmoothScrollEnabled(boolean z) {
        this.mSmoothScrollEnabled = z;
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        int itemCount = state.getItemCount();
        if (itemCount == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }
        int i3 = this.mRequestPosition;
        int i4 = 0;
        if (i3 != -1) {
            i2 = Math.min(i3, itemCount - 1);
            this.mRequestPosition = -1;
            i = this.mRequestPositionOffset;
            this.mRequestPositionOffset = 0;
        } else {
            View anchorChild = getAnchorChild();
            if (anchorChild != null) {
                i4 = Math.min(getPosition(anchorChild), itemCount - 1);
            }
            i = getBorderLine(anchorChild, Direction.END);
            i2 = i4;
        }
        detachAndScrapAttachedViews(recycler);
        LayoutState layoutState = new LayoutState(this, recycler, state);
        fixOverscroll(layoutChildren(i2, i, layoutState), layoutState);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams from = LayoutParams.from(layoutParams);
        from.width = -1;
        from.height = -1;
        return getSlm(from).generateLayoutParams(from);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001b, code lost:
        if (r1.type == 3) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0025, code lost:
        if (r0.getType(com.tonicartos.superslim.R.styleable.superslim_LayoutManager_slm_section_sectionManager) == 3) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.recyclerview.widget.RecyclerView.LayoutParams generateLayoutParams(android.content.Context r7, android.util.AttributeSet r8) {
        /*
            r6 = this;
            int[] r0 = com.tonicartos.superslim.R.styleable.superslim_LayoutManager
            android.content.res.TypedArray r0 = r7.obtainStyledAttributes(r8, r0)
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 0
            r3 = 3
            r4 = 1
            r5 = 21
            if (r1 >= r5) goto L_0x001f
            android.util.TypedValue r1 = new android.util.TypedValue
            r1.<init>()
            int r5 = com.tonicartos.superslim.R.styleable.superslim_LayoutManager_slm_section_sectionManager
            r0.getValue(r5, r1)
            int r1 = r1.type
            if (r1 != r3) goto L_0x0028
        L_0x001d:
            r2 = 1
            goto L_0x0028
        L_0x001f:
            int r1 = com.tonicartos.superslim.R.styleable.superslim_LayoutManager_slm_section_sectionManager
            int r1 = r0.getType(r1)
            if (r1 != r3) goto L_0x0028
            goto L_0x001d
        L_0x0028:
            r1 = 0
            if (r2 == 0) goto L_0x003a
            int r1 = com.tonicartos.superslim.R.styleable.superslim_LayoutManager_slm_section_sectionManager
            java.lang.String r1 = r0.getString(r1)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x0038
            goto L_0x0040
        L_0x0038:
            r4 = -1
            goto L_0x0040
        L_0x003a:
            int r2 = com.tonicartos.superslim.R.styleable.superslim_LayoutManager_slm_section_sectionManager
            int r4 = r0.getInt(r2, r4)
        L_0x0040:
            r0.recycle()
            com.tonicartos.superslim.SectionLayoutManager r0 = r6.getSLM(r4, r1)
            com.tonicartos.superslim.LayoutManager$LayoutParams r7 = r0.generateLayoutParams(r7, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonicartos.superslim.LayoutManager.generateLayoutParams(android.content.Context, android.util.AttributeSet):androidx.recyclerview.widget.RecyclerView$LayoutParams");
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int paddingTop;
        if (getChildCount() == 0) {
            return 0;
        }
        LayoutState layoutState = new LayoutState(this, recycler, state);
        Direction direction = i > 0 ? Direction.END : Direction.START;
        boolean z = direction == Direction.END;
        int height = getHeight();
        int i2 = z ? height + i : i;
        if (z) {
            View anchorAtEnd = getAnchorAtEnd();
            LayoutParams layoutParams = (LayoutParams) anchorAtEnd.getLayoutParams();
            if (getSlm(layoutParams).getLowestEdge(layoutParams.getTestedFirstPosition(), getChildCount() - 1, getDecoratedBottom(anchorAtEnd)) < height - getPaddingBottom() && getPosition(anchorAtEnd) == state.getItemCount() - 1) {
                return 0;
            }
        }
        int fillUntil = fillUntil(i2, direction, layoutState);
        if (!z ? (paddingTop = fillUntil - getPaddingTop()) > i : (paddingTop = (fillUntil - height) + getPaddingBottom()) < i) {
            i = paddingTop;
        }
        if (i != 0) {
            offsetChildrenVertical(-i);
            trimTail(z ? Direction.START : Direction.END, layoutState);
        }
        layoutState.recycleCache();
        return i;
    }

    public void scrollToPosition(int i) {
        if (i < 0 || getItemCount() <= i) {
            Log.e("SuperSLiM.LayoutManager", "Ignored scroll to " + i + " as it is not within the item range 0 - " + getItemCount());
            return;
        }
        this.mRequestPosition = i;
        requestLayout();
    }

    public void smoothScrollToPosition(final RecyclerView recyclerView, RecyclerView.State state, final int i) {
        if (i < 0 || getItemCount() <= i) {
            Log.e("SuperSLiM.LayoutManager", "Ignored smooth scroll to " + i + " as it is not within the item range 0 - " + getItemCount());
            return;
        }
        requestLayout();
        recyclerView.getHandler().post(new Runnable() {
            public void run() {
                AnonymousClass1 r0 = new LinearSmoothScroller(recyclerView.getContext()) {
                    /* access modifiers changed from: protected */
                    public int getVerticalSnapPreference() {
                        return -1;
                    }

                    /* access modifiers changed from: protected */
                    public void onChildAttachedToWindow(View view) {
                        super.onChildAttachedToWindow(view);
                    }

                    /* access modifiers changed from: protected */
                    public void onStop() {
                        super.onStop();
                        LayoutManager.this.requestLayout();
                    }

                    public int calculateDyToMakeVisible(View view, int i) {
                        RecyclerView.LayoutManager layoutManager = getLayoutManager();
                        if (!layoutManager.canScrollVertically()) {
                            return 0;
                        }
                        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                        int calculateDtToFit = calculateDtToFit(layoutManager.getDecoratedTop(view) - layoutParams.topMargin, layoutManager.getDecoratedBottom(view) + layoutParams.bottomMargin, LayoutManager.this.getPosition(view) == 0 ? layoutManager.getPaddingTop() : 0, layoutManager.getHeight() - layoutManager.getPaddingBottom(), i);
                        if (calculateDtToFit == 0) {
                            return 1;
                        }
                        return calculateDtToFit;
                    }

                    public PointF computeScrollVectorForPosition(int i) {
                        if (getChildCount() == 0) {
                            return null;
                        }
                        return new PointF(0.0f, (float) LayoutManager.this.getDirectionToPosition(i));
                    }
                };
                r0.setTargetPosition(i);
                LayoutManager.this.startSmoothScroll(r0);
            }
        });
    }

    public int getDecoratedMeasuredWidth(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return super.getDecoratedMeasuredWidth(view) + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
    }

    public int getDecoratedMeasuredHeight(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return super.getDecoratedMeasuredHeight(view) + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public void layoutDecorated(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        super.layoutDecorated(view, i + marginLayoutParams.leftMargin, i2 + marginLayoutParams.topMargin, i3 - marginLayoutParams.rightMargin, i4 - marginLayoutParams.bottomMargin);
    }

    public int getDecoratedLeft(View view) {
        return super.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin;
    }

    public int getDecoratedTop(View view) {
        return super.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
    }

    public int getDecoratedRight(View view) {
        return super.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin;
    }

    public int getDecoratedBottom(View view) {
        return super.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        View anchorChild = getAnchorChild();
        if (anchorChild == null) {
            this.mRequestPosition = -1;
            this.mRequestPositionOffset = 0;
            return;
        }
        this.mRequestPosition = getPosition(anchorChild);
        this.mRequestPositionOffset = getDecoratedTop(anchorChild);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        View childAt = getChildAt(0);
        View childAt2 = getChildAt(getChildCount() - 1);
        if (i2 + i > getPosition(childAt) && i <= getPosition(childAt2)) {
            requestLayout();
        }
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0 || state.getItemCount() == 0) {
            return 0;
        }
        if (!this.mSmoothScrollEnabled) {
            return getChildCount();
        }
        return (int) ((((((float) getChildCount()) - getFractionOfContentAbove(state, true)) - getFractionOfContentBelow(state, true)) / ((float) state.getItemCount())) * ((float) getHeight()));
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0 || state.getItemCount() == 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        if (!this.mSmoothScrollEnabled) {
            return getPosition(childAt);
        }
        return (int) (((((float) getPosition(childAt)) + getFractionOfContentAbove(state, false)) / ((float) state.getItemCount())) * ((float) getHeight()));
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        if (!this.mSmoothScrollEnabled) {
            return state.getItemCount();
        }
        return getHeight();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        View anchorChild = getAnchorChild();
        if (anchorChild == null) {
            savedState.anchorPosition = 0;
            savedState.anchorOffset = 0;
        } else {
            savedState.anchorPosition = getPosition(anchorChild);
            savedState.anchorOffset = getDecoratedTop(anchorChild);
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        this.mRequestPosition = savedState.anchorPosition;
        this.mRequestPositionOffset = savedState.anchorOffset;
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public int getBorderLine(View view, Direction direction) {
        if (view == null) {
            if (direction == Direction.START) {
                return getPaddingBottom();
            }
            return getPaddingTop();
        } else if (direction == Direction.START) {
            return getDecoratedBottom(view);
        } else {
            return getDecoratedTop(view);
        }
    }

    /* access modifiers changed from: package-private */
    public void measureHeader(View view) {
        int i;
        int i2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int width = (getWidth() - getPaddingStart()) - getPaddingEnd();
        if (!layoutParams.isHeaderOverlay()) {
            if (layoutParams.isHeaderStartAligned() && !layoutParams.headerStartMarginIsAuto) {
                i2 = layoutParams.headerMarginStart;
            } else if (layoutParams.isHeaderEndAligned() && !layoutParams.headerEndMarginIsAuto) {
                i2 = layoutParams.headerMarginEnd;
            }
            i = width - i2;
            measureChildWithMargins(view, i, 0);
        }
        i = 0;
        measureChildWithMargins(view, i, 0);
    }

    private void attachHeaderForStart(View view, int i, SectionData sectionData, LayoutState layoutState) {
        if (layoutState.getCachedView(sectionData.firstPosition) != null && getDecoratedBottom(view) > i) {
            addView(view, findLastIndexForSection(sectionData.firstPosition) + 1);
            layoutState.decacheView(sectionData.firstPosition);
        }
    }

    private int binarySearchForLastPosition(int i, int i2, int i3) {
        if (i2 < i) {
            return -1;
        }
        int i4 = ((i2 - i) / 2) + i;
        LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
        if (layoutParams.getTestedFirstPosition() < i3) {
            return binarySearchForLastPosition(i4 + 1, i2, i3);
        }
        if (layoutParams.getTestedFirstPosition() > i3 || layoutParams.isHeader) {
            return binarySearchForLastPosition(i, i4 - 1, i3);
        }
        if (i4 == getChildCount() - 1) {
            return i4;
        }
        int i5 = i4 + 1;
        LayoutParams layoutParams2 = (LayoutParams) getChildAt(i5).getLayoutParams();
        if (layoutParams2.getTestedFirstPosition() != i3) {
            return i4;
        }
        if (!layoutParams2.isHeader || (i5 != getChildCount() - 1 && ((LayoutParams) getChildAt(i4 + 2).getLayoutParams()).getTestedFirstPosition() == i3)) {
            return binarySearchForLastPosition(i5, i2, i3);
        }
        return i4;
    }

    private int fillNextSectionToEnd(int i, int i2, LayoutState layoutState) {
        int position;
        if (i2 >= i || (position = getPosition(getAnchorAtEnd()) + 1) >= layoutState.getRecyclerState().getItemCount()) {
            return i2;
        }
        LayoutState.View view = layoutState.getView(position);
        SectionData sectionData = new SectionData(this, view.view);
        if (sectionData.hasHeader) {
            measureHeader(view.view);
            sectionData = new SectionData(this, view.view);
            i2 = layoutHeaderTowardsEnd(view.view, i2, sectionData, layoutState);
            position++;
        } else {
            layoutState.cacheView(position, view.view);
        }
        int i3 = i2;
        int i4 = position;
        if (i4 < layoutState.getRecyclerState().getItemCount()) {
            i3 = getSlm(sectionData).fillToEnd(i, i3, i4, sectionData, layoutState);
        }
        if (sectionData.hasHeader) {
            addView(view.view);
            if (view.wasCached) {
                layoutState.decacheView(sectionData.firstPosition);
            }
            i3 = Math.max(getDecoratedBottom(view.view), i3);
        }
        return fillNextSectionToEnd(i, i3, layoutState);
    }

    private int fillNextSectionToStart(int i, int i2, LayoutState layoutState) {
        int i3;
        int i4;
        View firstVisibleView;
        int i5 = i;
        int i6 = i2;
        LayoutState layoutState2 = layoutState;
        if (i6 < i5) {
            return i6;
        }
        View anchorAtStart = getAnchorAtStart();
        View findAttachedHeaderOrFirstViewForSection = findAttachedHeaderOrFirstViewForSection(((LayoutParams) anchorAtStart.getLayoutParams()).getFirstPosition(), 0, Direction.START);
        if (findAttachedHeaderOrFirstViewForSection != null) {
            i3 = getPosition(findAttachedHeaderOrFirstViewForSection);
        } else {
            i3 = getPosition(anchorAtStart);
        }
        int i7 = i3 - 1;
        if (i7 < 0) {
            return i6;
        }
        View headerOrFirstViewForSection = getHeaderOrFirstViewForSection(layoutState2.getView(i7).getLayoutParams().getTestedFirstPosition(), Direction.START, layoutState2);
        SectionData sectionData = new SectionData(this, headerOrFirstViewForSection);
        if (sectionData.hasHeader) {
            measureHeader(headerOrFirstViewForSection);
            sectionData = new SectionData(this, headerOrFirstViewForSection);
        }
        SectionData sectionData2 = sectionData;
        SectionLayoutManager slm = getSlm(sectionData2);
        int fillToStart = i7 >= 0 ? slm.fillToStart(i, i2, i7, sectionData2, layoutState) : i6;
        if (sectionData2.hasHeader) {
            if ((!sectionData2.headerParams.isHeaderInline() || sectionData2.headerParams.isHeaderOverlay()) && (firstVisibleView = slm.getFirstVisibleView(sectionData2.firstPosition, true)) != null) {
                i4 = slm.computeHeaderOffset(getPosition(firstVisibleView), sectionData2, layoutState2);
            } else {
                i4 = 0;
            }
            fillToStart = layoutHeaderTowardsStart(headerOrFirstViewForSection, i, fillToStart, i4, i2, sectionData2, layoutState);
            attachHeaderForStart(headerOrFirstViewForSection, i5, sectionData2, layoutState2);
        }
        return fillNextSectionToStart(i5, fillToStart, layoutState2);
    }

    private int fillToEnd(int i, LayoutState layoutState) {
        View anchorAtEnd = getAnchorAtEnd();
        SectionData sectionData = new SectionData(this, getHeaderOrFirstViewForSection(((LayoutParams) anchorAtEnd.getLayoutParams()).getTestedFirstPosition(), Direction.END, layoutState));
        int updateHeaderForEnd = updateHeaderForEnd(findAttachedHeaderForSectionFromEnd(sectionData.firstPosition), getSlm(sectionData).finishFillToEnd(i, anchorAtEnd, sectionData, layoutState));
        return updateHeaderForEnd <= i ? fillNextSectionToEnd(i, updateHeaderForEnd, layoutState) : updateHeaderForEnd;
    }

    private int fillToStart(int i, LayoutState layoutState) {
        int finishFillToStart;
        View anchorAtStart = getAnchorAtStart();
        View headerOrFirstViewForSection = getHeaderOrFirstViewForSection(((LayoutParams) anchorAtStart.getLayoutParams()).getTestedFirstPosition(), Direction.START, layoutState);
        SectionData sectionData = new SectionData(this, headerOrFirstViewForSection);
        SectionLayoutManager slm = getSlm(sectionData);
        int position = getPosition(anchorAtStart);
        if (position == sectionData.firstPosition) {
            finishFillToStart = getDecoratedTop(anchorAtStart);
        } else if (position - 1 != sectionData.firstPosition || !sectionData.hasHeader) {
            finishFillToStart = slm.finishFillToStart(i, anchorAtStart, sectionData, layoutState);
        } else {
            finishFillToStart = getDecoratedTop(anchorAtStart);
        }
        int updateHeaderForStart = updateHeaderForStart(headerOrFirstViewForSection, i, finishFillToStart, sectionData, layoutState);
        return updateHeaderForStart > i ? fillNextSectionToStart(i, updateHeaderForStart, layoutState) : updateHeaderForStart;
    }

    private int fillUntil(int i, Direction direction, LayoutState layoutState) {
        if (direction == Direction.START) {
            return fillToStart(i, layoutState);
        }
        return fillToEnd(i, layoutState);
    }

    private View findAttachedHeaderForSection(int i, Direction direction) {
        if (direction == Direction.END) {
            return findAttachedHeaderForSectionFromEnd(i);
        }
        return findAttachedHeaderForSectionFromStart(0, getChildCount() - 1, i);
    }

    private View findAttachedHeaderForSectionFromEnd(int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.getTestedFirstPosition() != i) {
                return null;
            }
            if (layoutParams.isHeader) {
                return childAt;
            }
        }
        return null;
    }

    private View findAttachedHeaderForSectionFromStart(int i, int i2, int i3) {
        if (i2 < i) {
            return null;
        }
        int i4 = ((i2 - i) / 2) + i;
        View childAt = getChildAt(i4);
        LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
        if (layoutParams.getTestedFirstPosition() != i3) {
            return findAttachedHeaderForSectionFromStart(i, i4 - 1, i3);
        }
        if (layoutParams.isHeader) {
            return childAt;
        }
        return findAttachedHeaderForSectionFromStart(i4 + 1, i2, i3);
    }

    private View findAttachedHeaderOrFirstViewForSection(int i, int i2, Direction direction) {
        int i3 = direction == Direction.START ? 1 : -1;
        while (i2 >= 0 && i2 < getChildCount()) {
            View childAt = getChildAt(i2);
            if (getPosition(childAt) == i) {
                return childAt;
            }
            if (((LayoutParams) childAt.getLayoutParams()).getTestedFirstPosition() != i) {
                return null;
            }
            i2 += i3;
        }
        return null;
    }

    private int findLastIndexForSection(int i) {
        return binarySearchForLastPosition(0, getChildCount() - 1, i);
    }

    private void fixOverscroll(int i, LayoutState layoutState) {
        if (isOverscrolled(layoutState)) {
            offsetChildrenVertical((getHeight() - getPaddingBottom()) - i);
            int fillToStart = fillToStart(0, layoutState);
            if (fillToStart > getPaddingTop()) {
                offsetChildrenVertical(getPaddingTop() - fillToStart);
            }
        }
    }

    private View getAnchorAtEnd() {
        if (getChildCount() == 1) {
            return getChildAt(0);
        }
        View childAt = getChildAt(getChildCount() - 1);
        LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
        if (!layoutParams.isHeader) {
            return childAt;
        }
        View childAt2 = getChildAt(getChildCount() - 2);
        return ((LayoutParams) childAt2.getLayoutParams()).getTestedFirstPosition() == layoutParams.getTestedFirstPosition() ? childAt2 : childAt;
    }

    private View getAnchorAtStart() {
        View childAt = getChildAt(0);
        LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
        int testedFirstPosition = layoutParams.getTestedFirstPosition();
        if (layoutParams.isHeader && 1 < getChildCount()) {
            View childAt2 = getChildAt(1);
            if (((LayoutParams) childAt2.getLayoutParams()).getTestedFirstPosition() == testedFirstPosition) {
                return childAt2;
            }
        }
        return childAt;
    }

    private View getAnchorChild() {
        if (getChildCount() == 0) {
            return null;
        }
        View childAt = getChildAt(0);
        int testedFirstPosition = ((LayoutParams) childAt.getLayoutParams()).getTestedFirstPosition();
        View findAttachedHeaderOrFirstViewForSection = findAttachedHeaderOrFirstViewForSection(testedFirstPosition, 0, Direction.START);
        if (findAttachedHeaderOrFirstViewForSection == null) {
            return childAt;
        }
        LayoutParams layoutParams = (LayoutParams) findAttachedHeaderOrFirstViewForSection.getLayoutParams();
        if (!layoutParams.isHeader) {
            return childAt;
        }
        return (!layoutParams.isHeaderInline() || layoutParams.isHeaderOverlay()) ? (getDecoratedTop(childAt) >= getDecoratedTop(findAttachedHeaderOrFirstViewForSection) && testedFirstPosition + 1 == getPosition(childAt)) ? findAttachedHeaderOrFirstViewForSection : childAt : getDecoratedBottom(findAttachedHeaderOrFirstViewForSection) <= getDecoratedTop(childAt) ? findAttachedHeaderOrFirstViewForSection : childAt;
    }

    /* access modifiers changed from: private */
    public int getDirectionToPosition(int i) {
        SectionData sectionData = new SectionData(this, getChildAt(0));
        if (i < getPosition(getSlm(sectionData).getFirstVisibleView(sectionData.firstPosition, true))) {
            return -1;
        }
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private float getFractionOfContentAbove(androidx.recyclerview.widget.RecyclerView.State r17, boolean r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = 0
            android.view.View r2 = r0.getChildAt(r1)
            int r3 = r0.getPosition(r2)
            int r4 = r0.getDecoratedTop(r2)
            float r4 = (float) r4
            int r5 = r0.getDecoratedBottom(r2)
            float r5 = (float) r5
            r6 = 1065353216(0x3f800000, float:1.0)
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L_0x001f
            r4 = 1065353216(0x3f800000, float:1.0)
            goto L_0x002c
        L_0x001f:
            int r5 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r5 > 0) goto L_0x0025
            r4 = 0
            goto L_0x002c
        L_0x0025:
            int r5 = r0.getDecoratedMeasuredHeight(r2)
            float r5 = (float) r5
            float r4 = -r4
            float r4 = r4 / r5
        L_0x002c:
            com.tonicartos.superslim.SectionData r5 = new com.tonicartos.superslim.SectionData
            r5.<init>(r0, r2)
            com.tonicartos.superslim.LayoutManager$LayoutParams r2 = r5.headerParams
            boolean r2 = r2.isHeader
            if (r2 == 0) goto L_0x0040
            com.tonicartos.superslim.LayoutManager$LayoutParams r2 = r5.headerParams
            boolean r2 = r2.isHeaderInline()
            if (r2 == 0) goto L_0x0040
            return r4
        L_0x0040:
            android.util.SparseArray r2 = new android.util.SparseArray
            r2.<init>()
            r8 = -1
            r10 = 1
            r11 = -1
        L_0x0048:
            int r12 = r16.getChildCount()
            if (r10 >= r12) goto L_0x0099
            android.view.View r12 = r0.getChildAt(r10)
            android.view.ViewGroup$LayoutParams r13 = r12.getLayoutParams()
            com.tonicartos.superslim.LayoutManager$LayoutParams r13 = (com.tonicartos.superslim.LayoutManager.LayoutParams) r13
            boolean r14 = r5.sameSectionManager(r13)
            if (r14 != 0) goto L_0x005f
            goto L_0x0099
        L_0x005f:
            int r14 = r0.getPosition(r12)
            if (r18 != 0) goto L_0x0069
            if (r14 >= r3) goto L_0x0069
            int r1 = r1 + 1
        L_0x0069:
            int r15 = r0.getDecoratedTop(r12)
            float r15 = (float) r15
            int r9 = r0.getDecoratedBottom(r12)
            float r9 = (float) r9
            int r9 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x0079
            float r4 = r4 + r6
            goto L_0x0087
        L_0x0079:
            int r9 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r9 > 0) goto L_0x007f
        L_0x007d:
            r9 = 1
            goto L_0x0096
        L_0x007f:
            int r9 = r0.getDecoratedMeasuredHeight(r12)
            float r9 = (float) r9
            float r12 = -r15
            float r12 = r12 / r9
            float r4 = r4 + r12
        L_0x0087:
            boolean r9 = r13.isHeader
            if (r9 != 0) goto L_0x007d
            if (r11 != r8) goto L_0x008e
            r11 = r14
        L_0x008e:
            r9 = 1
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r9)
            r2.put(r14, r12)
        L_0x0096:
            int r10 = r10 + 1
            goto L_0x0048
        L_0x0099:
            float r1 = (float) r1
            float r4 = r4 - r1
            com.tonicartos.superslim.SectionLayoutManager r1 = r0.getSlm((com.tonicartos.superslim.SectionData) r5)
            int r1 = r1.howManyMissingAbove(r11, r2)
            float r1 = (float) r1
            float r4 = r4 - r1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonicartos.superslim.LayoutManager.getFractionOfContentAbove(androidx.recyclerview.widget.RecyclerView$State, boolean):float");
    }

    private float getFractionOfContentBelow(RecyclerView.State state, boolean z) {
        float height = (float) getHeight();
        View childAt = getChildAt(getChildCount() - 1);
        int position = getPosition(childAt);
        SectionData sectionData = new SectionData(this, childAt);
        SparseArray sparseArray = new SparseArray();
        int i = 0;
        float f = 0.0f;
        int i2 = -1;
        for (int i3 = 1; i3 <= getChildCount(); i3++) {
            View childAt2 = getChildAt(getChildCount() - i3);
            LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
            if (!sectionData.sameSectionManager(layoutParams)) {
                break;
            }
            int position2 = getPosition(childAt2);
            if (!layoutParams.isHeader && !z && position2 > position) {
                i++;
            }
            float decoratedBottom = (float) getDecoratedBottom(childAt2);
            float decoratedTop = (float) getDecoratedTop(childAt2);
            if (decoratedBottom > height) {
                if (height < decoratedTop) {
                    f += 1.0f;
                } else {
                    f += (decoratedBottom - height) / ((float) getDecoratedMeasuredHeight(childAt2));
                }
                if (!layoutParams.isHeader) {
                    if (i2 == -1) {
                        i2 = position2;
                    }
                    sparseArray.put(position2, true);
                }
            }
        }
        return (f - ((float) i)) - ((float) getSlm(sectionData).howManyMissingBelow(i2, sparseArray));
    }

    private View getHeaderOrFirstViewForSection(int i, Direction direction, LayoutState layoutState) {
        int i2;
        if (direction == Direction.START) {
            i2 = 0;
        } else {
            i2 = getChildCount() - 1;
        }
        View findAttachedHeaderOrFirstViewForSection = findAttachedHeaderOrFirstViewForSection(i, i2, direction);
        if (findAttachedHeaderOrFirstViewForSection != null) {
            return findAttachedHeaderOrFirstViewForSection;
        }
        LayoutState.View view = layoutState.getView(i);
        View view2 = view.view;
        if (view.getLayoutParams().isHeader) {
            measureHeader(view.view);
        }
        layoutState.cacheView(i, view2);
        return view2;
    }

    private SectionLayoutManager getSLM(int i, String str) {
        if (i == -1) {
            return this.mSlms.get(str);
        }
        if (i == 1) {
            return this.mLinearSlm;
        }
        if (i == 2) {
            return this.mGridSlm;
        }
        throw new NotYetImplementedSlmException(i);
    }

    private SectionLayoutManager getSlm(LayoutParams layoutParams) {
        if (layoutParams.sectionManagerKind == -1) {
            return this.mSlms.get(layoutParams.sectionManager);
        }
        if (layoutParams.sectionManagerKind == 1) {
            return this.mLinearSlm;
        }
        if (layoutParams.sectionManagerKind == 2) {
            return this.mGridSlm;
        }
        throw new NotYetImplementedSlmException(layoutParams.sectionManagerKind);
    }

    private SectionLayoutManager getSlm(SectionData sectionData) {
        SectionLayoutManager sectionLayoutManager;
        if (sectionData.headerParams.sectionManagerKind == -1) {
            sectionLayoutManager = this.mSlms.get(sectionData.sectionManager);
            if (sectionLayoutManager == null) {
                throw new UnknownSectionLayoutException(sectionData.sectionManager);
            }
        } else if (sectionData.headerParams.sectionManagerKind == 1) {
            sectionLayoutManager = this.mLinearSlm;
        } else if (sectionData.headerParams.sectionManagerKind == 2) {
            sectionLayoutManager = this.mGridSlm;
        } else {
            throw new NotYetImplementedSlmException(sectionData.headerParams.sectionManagerKind);
        }
        return sectionLayoutManager.init(sectionData);
    }

    private boolean isOverscrolled(LayoutState layoutState) {
        int itemCount = layoutState.getRecyclerState().getItemCount();
        if (getChildCount() == 0) {
            return false;
        }
        View findFirstVisibleItem = findFirstVisibleItem();
        boolean z = getPosition(findFirstVisibleItem) == 0;
        boolean z2 = getDecoratedTop(findFirstVisibleItem) > getPaddingTop();
        boolean z3 = getDecoratedTop(findFirstVisibleItem) == getPaddingTop();
        if (z && z2) {
            return true;
        }
        if (z && z3) {
            return false;
        }
        View findLastVisibleItem = findLastVisibleItem();
        boolean z4 = getPosition(findLastVisibleItem) == itemCount - 1;
        boolean z5 = getDecoratedBottom(findLastVisibleItem) < getHeight() - getPaddingBottom();
        if (!z4 || !z5) {
            return false;
        }
        return true;
    }

    private int layoutChildren(int i, int i2, LayoutState layoutState) {
        int i3;
        int i4;
        int i5 = i;
        LayoutState layoutState2 = layoutState;
        int height = getHeight();
        LayoutState.View view = layoutState2.getView(i5);
        layoutState2.cacheView(i5, view.view);
        int testedFirstPosition = view.getLayoutParams().getTestedFirstPosition();
        LayoutState.View view2 = layoutState2.getView(testedFirstPosition);
        measureHeader(view2.view);
        layoutState2.cacheView(testedFirstPosition, view2.view);
        SectionData sectionData = new SectionData(this, view2.view);
        SectionLayoutManager slm = getSlm(sectionData);
        if (!sectionData.hasHeader || i5 != sectionData.firstPosition) {
            i3 = i5;
            i4 = i2;
        } else {
            i4 = layoutHeaderTowardsEnd(view2.view, i2, sectionData, layoutState2);
            i3 = i5 + 1;
        }
        int fillToEnd = slm.fillToEnd(height, i4, i3, sectionData, layoutState);
        if (!sectionData.hasHeader || i5 == sectionData.firstPosition) {
            fillToEnd = Math.max(fillToEnd, getDecoratedBottom(view2.view));
        } else {
            layoutHeaderTowardsStart(view2.view, 0, i2, slm.computeHeaderOffset(i3, sectionData, layoutState2), fillToEnd, sectionData, layoutState);
        }
        if (sectionData.hasHeader && getDecoratedBottom(view2.view) > 0) {
            addView(view2.view);
            layoutState2.decacheView(sectionData.firstPosition);
        }
        return fillNextSectionToEnd(height, fillToEnd, layoutState2);
    }

    private int layoutHeaderTowardsEnd(View view, int i, SectionData sectionData, LayoutState layoutState) {
        Rect headerRectSides = setHeaderRectSides(this.mRect, sectionData, layoutState);
        headerRectSides.top = i;
        headerRectSides.bottom = headerRectSides.top + sectionData.headerHeight;
        if (sectionData.headerParams.isHeaderInline() && !sectionData.headerParams.isHeaderOverlay()) {
            i = headerRectSides.bottom;
        }
        if (sectionData.headerParams.isHeaderSticky() && headerRectSides.top < 0) {
            headerRectSides.top = 0;
            headerRectSides.bottom = headerRectSides.top + sectionData.headerHeight;
        }
        layoutDecorated(view, headerRectSides.left, headerRectSides.top, headerRectSides.right, headerRectSides.bottom);
        return i;
    }

    private int layoutHeaderTowardsStart(View view, int i, int i2, int i3, int i4, SectionData sectionData, LayoutState layoutState) {
        Rect headerRectSides = setHeaderRectSides(this.mRect, sectionData, layoutState);
        if (sectionData.headerParams.isHeaderInline() && !sectionData.headerParams.isHeaderOverlay()) {
            headerRectSides.bottom = i2;
            headerRectSides.top = headerRectSides.bottom - sectionData.headerHeight;
        } else if (i3 <= 0) {
            headerRectSides.top = i3 + i2;
            headerRectSides.bottom = headerRectSides.top + sectionData.headerHeight;
        } else {
            headerRectSides.bottom = i;
            headerRectSides.top = headerRectSides.bottom - sectionData.headerHeight;
        }
        if (sectionData.headerParams.isHeaderSticky() && headerRectSides.top < i && sectionData.firstPosition != layoutState.getRecyclerState().getTargetScrollPosition()) {
            headerRectSides.top = i;
            headerRectSides.bottom = headerRectSides.top + sectionData.headerHeight;
            if (sectionData.headerParams.isHeaderInline() && !sectionData.headerParams.isHeaderOverlay()) {
                i2 -= sectionData.headerHeight;
            }
        }
        if (headerRectSides.bottom > i4) {
            headerRectSides.bottom = i4;
            headerRectSides.top = headerRectSides.bottom - sectionData.headerHeight;
        }
        layoutDecorated(view, headerRectSides.left, headerRectSides.top, headerRectSides.right, headerRectSides.bottom);
        return Math.min(headerRectSides.top, i2);
    }

    private Rect setHeaderRectSides(Rect rect, SectionData sectionData, LayoutState layoutState) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        if (sectionData.headerParams.isHeaderEndAligned()) {
            if (sectionData.headerParams.isHeaderOverlay() || sectionData.headerParams.headerEndMarginIsAuto || sectionData.marginEnd <= 0) {
                if (layoutState.isLTR) {
                    rect.right = getWidth() - paddingRight;
                    rect.left = rect.right - sectionData.headerWidth;
                } else {
                    rect.left = paddingLeft;
                    rect.right = rect.left + sectionData.headerWidth;
                }
            } else if (layoutState.isLTR) {
                rect.left = (getWidth() - sectionData.marginEnd) - paddingRight;
                rect.right = rect.left + sectionData.headerWidth;
            } else {
                rect.right = sectionData.marginEnd + paddingLeft;
                rect.left = rect.right - sectionData.headerWidth;
            }
        } else if (!sectionData.headerParams.isHeaderStartAligned()) {
            rect.left = paddingLeft;
            rect.right = rect.left + sectionData.headerWidth;
        } else if (sectionData.headerParams.isHeaderOverlay() || sectionData.headerParams.headerStartMarginIsAuto || sectionData.marginStart <= 0) {
            if (layoutState.isLTR) {
                rect.left = paddingLeft;
                rect.right = rect.left + sectionData.headerWidth;
            } else {
                rect.right = getWidth() - paddingRight;
                rect.left = rect.right - sectionData.headerWidth;
            }
        } else if (layoutState.isLTR) {
            rect.right = sectionData.marginStart + paddingLeft;
            rect.left = rect.right - sectionData.headerWidth;
        } else {
            rect.left = (getWidth() - sectionData.marginStart) - paddingRight;
            rect.right = rect.left + sectionData.headerWidth;
        }
        return rect;
    }

    private void trimEnd(LayoutState layoutState) {
        int height = getHeight();
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (getDecoratedTop(childAt) >= height) {
                removeAndRecycleView(childAt, layoutState.recycler);
            } else if (!((LayoutParams) childAt.getLayoutParams()).isHeader) {
                return;
            }
        }
    }

    private void trimStart(LayoutState layoutState) {
        View view;
        int i = 0;
        while (true) {
            if (i >= getChildCount()) {
                view = null;
                i = 0;
                break;
            }
            view = getChildAt(i);
            if (getDecoratedBottom(view) > 0) {
                break;
            }
            i++;
        }
        if (view == null) {
            detachAndScrapAttachedViews(layoutState.recycler);
            return;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.isHeader) {
            int i2 = i - 1;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                LayoutParams layoutParams2 = (LayoutParams) getChildAt(i2).getLayoutParams();
                if (layoutParams2.getTestedFirstPosition() == layoutParams.getTestedFirstPosition()) {
                    i = i2;
                    layoutParams = layoutParams2;
                    break;
                }
                i2--;
            }
        }
        for (int i3 = 0; i3 < i; i3++) {
            removeAndRecycleViewAt(0, layoutState.recycler);
        }
        View findAttachedHeaderForSection = findAttachedHeaderForSection(layoutParams.getTestedFirstPosition(), Direction.START);
        if (findAttachedHeaderForSection != null) {
            if (getDecoratedTop(findAttachedHeaderForSection) < 0) {
                updateHeaderForTrimFromStart(findAttachedHeaderForSection);
            }
            if (getDecoratedBottom(findAttachedHeaderForSection) <= 0) {
                removeAndRecycleView(findAttachedHeaderForSection, layoutState.recycler);
            }
        }
    }

    private void trimTail(Direction direction, LayoutState layoutState) {
        if (direction == Direction.START) {
            trimStart(layoutState);
        } else {
            trimEnd(layoutState);
        }
    }

    private int updateHeaderForEnd(View view, int i) {
        if (view == null) {
            return i;
        }
        detachView(view);
        attachView(view, -1);
        return Math.max(i, getDecoratedBottom(view));
    }

    private int updateHeaderForStart(View view, int i, int i2, SectionData sectionData, LayoutState layoutState) {
        View firstVisibleView;
        SectionData sectionData2 = sectionData;
        LayoutState layoutState2 = layoutState;
        if (!sectionData2.hasHeader) {
            return i2;
        }
        SectionLayoutManager slm = getSlm(sectionData2);
        int findLastIndexForSection = findLastIndexForSection(sectionData2.firstPosition);
        int height = getHeight();
        int i3 = 0;
        int i4 = findLastIndexForSection == -1 ? 0 : findLastIndexForSection;
        while (true) {
            if (i4 >= getChildCount()) {
                break;
            }
            View childAt = getChildAt(i4);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.getTestedFirstPosition() != sectionData2.firstPosition) {
                View findAttachedHeaderOrFirstViewForSection = findAttachedHeaderOrFirstViewForSection(layoutParams.getTestedFirstPosition(), i4, Direction.START);
                if (findAttachedHeaderOrFirstViewForSection == null) {
                    height = getDecoratedTop(childAt);
                } else {
                    height = getDecoratedTop(findAttachedHeaderOrFirstViewForSection);
                }
            } else {
                i4++;
            }
        }
        int i5 = height;
        int i6 = (findLastIndexForSection != -1 || !sectionData2.headerParams.isHeaderInline() || sectionData2.headerParams.isHeaderOverlay()) ? i2 : i5;
        if ((!sectionData2.headerParams.isHeaderInline() || sectionData2.headerParams.isHeaderOverlay()) && (firstVisibleView = slm.getFirstVisibleView(sectionData2.firstPosition, true)) != null) {
            i3 = slm.computeHeaderOffset(getPosition(firstVisibleView), sectionData2, layoutState2);
        }
        int layoutHeaderTowardsStart = layoutHeaderTowardsStart(view, i, i6, i3, i5, sectionData, layoutState);
        attachHeaderForStart(view, i, sectionData2, layoutState2);
        return layoutHeaderTowardsStart;
    }

    private void updateHeaderForTrimFromStart(View view) {
        int findLastIndexForSection;
        int i;
        int i2;
        SectionData sectionData = new SectionData(this, view);
        if (sectionData.headerParams.isHeaderSticky() && (findLastIndexForSection = findLastIndexForSection(sectionData.firstPosition)) != -1) {
            SectionLayoutManager slm = getSlm(sectionData);
            int lowestEdge = slm.getLowestEdge(sectionData.firstPosition, findLastIndexForSection, getHeight());
            int highestEdge = slm.getHighestEdge(sectionData.firstPosition, 0, 0);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(view);
            if ((sectionData.headerParams.isHeaderInline() && !sectionData.headerParams.isHeaderOverlay()) || lowestEdge - highestEdge >= decoratedMeasuredHeight) {
                int decoratedLeft = getDecoratedLeft(view);
                int decoratedRight = getDecoratedRight(view);
                int i3 = decoratedMeasuredHeight + 0;
                if (i3 > lowestEdge) {
                    i = lowestEdge;
                    i2 = lowestEdge - decoratedMeasuredHeight;
                } else {
                    i = i3;
                    i2 = 0;
                }
                layoutDecorated(view, decoratedLeft, i2, decoratedRight, i);
            }
        }
    }

    public static class Builder {
        final Context context;
        HashMap<String, SectionLayoutManager> slms = new HashMap<>();

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder addSlm(String str, SectionLayoutManager sectionLayoutManager) {
            this.slms.put(str, sectionLayoutManager);
            return this;
        }

        public LayoutManager build() {
            return new LayoutManager(this);
        }
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        private static final int DEFAULT_HEADER_DISPLAY = 17;
        private static final int DEFAULT_HEADER_MARGIN = -1;
        private static final boolean DEFAULT_IS_HEADER = false;
        public static final int HEADER_ALIGN_END = 4;
        public static final int HEADER_ALIGN_START = 2;
        public static final int HEADER_INLINE = 1;
        public static final int HEADER_OVERLAY = 8;
        @Deprecated
        public static final int HEADER_STICKY = 16;
        private static final int NO_FIRST_POSITION = -1;
        public int headerDisplay;
        public boolean headerEndMarginIsAuto;
        public int headerMarginEnd;
        public int headerMarginStart;
        public boolean headerStartMarginIsAuto;
        public boolean isHeader;
        private int mFirstPosition;
        String sectionManager;
        int sectionManagerKind;

        @Retention(RetentionPolicy.SOURCE)
        public @interface HeaderDisplayOptions {
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.sectionManagerKind = 1;
            this.isHeader = false;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            boolean z = true;
            this.sectionManagerKind = 1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.superslim_LayoutManager);
            this.isHeader = obtainStyledAttributes.getBoolean(R.styleable.superslim_LayoutManager_slm_isHeader, false);
            this.headerDisplay = obtainStyledAttributes.getInt(R.styleable.superslim_LayoutManager_slm_headerDisplay, 17);
            this.mFirstPosition = obtainStyledAttributes.getInt(R.styleable.superslim_LayoutManager_slm_section_firstPosition, -1);
            if (Build.VERSION.SDK_INT < 21) {
                TypedValue typedValue = new TypedValue();
                obtainStyledAttributes.getValue(R.styleable.superslim_LayoutManager_slm_section_headerMarginStart, typedValue);
                loadHeaderStartMargin(obtainStyledAttributes, typedValue.type == 5);
                obtainStyledAttributes.getValue(R.styleable.superslim_LayoutManager_slm_section_headerMarginEnd, typedValue);
                loadHeaderEndMargin(obtainStyledAttributes, typedValue.type == 5);
                obtainStyledAttributes.getValue(R.styleable.superslim_LayoutManager_slm_section_sectionManager, typedValue);
                loadSlm(obtainStyledAttributes, typedValue.type != 3 ? false : z);
            } else {
                loadHeaderStartMargin(obtainStyledAttributes, obtainStyledAttributes.getType(R.styleable.superslim_LayoutManager_slm_section_headerMarginStart) == 5);
                loadHeaderEndMargin(obtainStyledAttributes, obtainStyledAttributes.getType(R.styleable.superslim_LayoutManager_slm_section_headerMarginEnd) == 5);
                loadSlm(obtainStyledAttributes, obtainStyledAttributes.getType(R.styleable.superslim_LayoutManager_slm_section_sectionManager) != 3 ? false : z);
            }
            obtainStyledAttributes.recycle();
        }

        @Deprecated
        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.sectionManagerKind = 1;
            init(marginLayoutParams);
        }

        @Deprecated
        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.sectionManagerKind = 1;
            init(layoutParams);
        }

        public static LayoutParams from(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams == null) {
                Log.w("SuperSLiM", "Null value passed in call to LayoutManager.LayoutParams.from().");
                return new LayoutParams(-2, -2);
            } else if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            } else {
                return new LayoutParams(layoutParams);
            }
        }

        public boolean areHeaderFlagsSet(int i) {
            return (this.headerDisplay & i) == i;
        }

        public int getFirstPosition() {
            return this.mFirstPosition;
        }

        public void setFirstPosition(int i) {
            if (i >= 0) {
                this.mFirstPosition = i;
                return;
            }
            throw new InvalidFirstPositionException();
        }

        public int getTestedFirstPosition() {
            int i = this.mFirstPosition;
            if (i != -1) {
                return i;
            }
            throw new MissingFirstPositionException();
        }

        public boolean isHeaderEndAligned() {
            return (this.headerDisplay & 4) != 0;
        }

        public boolean isHeaderInline() {
            return (this.headerDisplay & 1) != 0;
        }

        public boolean isHeaderOverlay() {
            return (this.headerDisplay & 8) != 0;
        }

        public boolean isHeaderStartAligned() {
            return (this.headerDisplay & 2) != 0;
        }

        public boolean isHeaderSticky() {
            return (this.headerDisplay & 16) != 0;
        }

        public void setSlm(String str) {
            this.sectionManagerKind = -1;
            this.sectionManager = str;
        }

        public void setSlm(int i) {
            this.sectionManagerKind = i;
        }

        private void init(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.isHeader = layoutParams2.isHeader;
                this.headerDisplay = layoutParams2.headerDisplay;
                this.mFirstPosition = layoutParams2.mFirstPosition;
                this.sectionManager = layoutParams2.sectionManager;
                this.sectionManagerKind = layoutParams2.sectionManagerKind;
                this.headerMarginEnd = layoutParams2.headerMarginEnd;
                this.headerMarginStart = layoutParams2.headerMarginStart;
                this.headerEndMarginIsAuto = layoutParams2.headerEndMarginIsAuto;
                this.headerStartMarginIsAuto = layoutParams2.headerStartMarginIsAuto;
                return;
            }
            this.isHeader = false;
            this.headerDisplay = 17;
            this.headerMarginEnd = -1;
            this.headerMarginStart = -1;
            this.headerStartMarginIsAuto = true;
            this.headerEndMarginIsAuto = true;
            this.sectionManagerKind = 1;
        }

        private void loadHeaderEndMargin(TypedArray typedArray, boolean z) {
            if (z) {
                this.headerEndMarginIsAuto = false;
                this.headerMarginEnd = typedArray.getDimensionPixelSize(R.styleable.superslim_LayoutManager_slm_section_headerMarginEnd, 0);
                return;
            }
            this.headerEndMarginIsAuto = true;
        }

        private void loadHeaderStartMargin(TypedArray typedArray, boolean z) {
            if (z) {
                this.headerStartMarginIsAuto = false;
                this.headerMarginStart = typedArray.getDimensionPixelSize(R.styleable.superslim_LayoutManager_slm_section_headerMarginStart, 0);
                return;
            }
            this.headerStartMarginIsAuto = true;
        }

        private void loadSlm(TypedArray typedArray, boolean z) {
            if (z) {
                String string = typedArray.getString(R.styleable.superslim_LayoutManager_slm_section_sectionManager);
                this.sectionManager = string;
                if (TextUtils.isEmpty(string)) {
                    this.sectionManagerKind = 1;
                } else {
                    this.sectionManagerKind = -1;
                }
            } else {
                this.sectionManagerKind = typedArray.getInt(R.styleable.superslim_LayoutManager_slm_section_sectionManager, 1);
            }
        }

        private class MissingFirstPositionException extends RuntimeException {
            MissingFirstPositionException() {
                super("Missing section first position.");
            }
        }

        private class InvalidFirstPositionException extends RuntimeException {
            InvalidFirstPositionException() {
                super("Invalid section first position given.");
            }
        }
    }

    protected static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public int anchorOffset;
        public int anchorPosition;

        public int describeContents() {
            return 0;
        }

        protected SavedState() {
        }

        protected SavedState(Parcel parcel) {
            this.anchorPosition = parcel.readInt();
            this.anchorOffset = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.anchorPosition);
            parcel.writeInt(this.anchorOffset);
        }
    }

    private class NotYetImplementedSlmException extends RuntimeException {
        public NotYetImplementedSlmException(int i) {
            super("SLM not yet implemented " + i + ".");
        }
    }

    private class UnknownSectionLayoutException extends RuntimeException {
        public UnknownSectionLayoutException(String str) {
            super("No registered layout for id " + str + ".");
        }
    }
}
