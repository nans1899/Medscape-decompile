package com.tonicartos.superslim;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LayoutState;

public abstract class SectionLayoutManager {
    private static final int MARGIN_UNSET = -1;
    protected LayoutManager mLayoutManager;

    public abstract int computeHeaderOffset(int i, SectionData sectionData, LayoutState layoutState);

    public abstract int fillToEnd(int i, int i2, int i3, SectionData sectionData, LayoutState layoutState);

    public abstract int fillToStart(int i, int i2, int i3, SectionData sectionData, LayoutState layoutState);

    public abstract int finishFillToEnd(int i, View view, SectionData sectionData, LayoutState layoutState);

    public abstract int finishFillToStart(int i, View view, SectionData sectionData, LayoutState layoutState);

    public LayoutManager.LayoutParams generateLayoutParams(LayoutManager.LayoutParams layoutParams) {
        return layoutParams;
    }

    public int getAnchorPosition(LayoutState layoutState, SectionData sectionData, int i) {
        return i;
    }

    public SectionLayoutManager init(SectionData sectionData) {
        return this;
    }

    public SectionLayoutManager(LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public int findFirstCompletelyVisibleItemPosition(int i) {
        View firstCompletelyVisibleView = getFirstCompletelyVisibleView(i, false);
        if (firstCompletelyVisibleView == null) {
            return -1;
        }
        return this.mLayoutManager.getPosition(firstCompletelyVisibleView);
    }

    public int findFirstVisibleItemPosition(int i) {
        View firstVisibleView = getFirstVisibleView(i, false);
        if (firstVisibleView == null) {
            return -1;
        }
        return this.mLayoutManager.getPosition(firstVisibleView);
    }

    public int findLastCompletelyVisibleItemPosition(int i) {
        View lastCompletelyVisibleView = getLastCompletelyVisibleView(i);
        if (lastCompletelyVisibleView == null) {
            return -1;
        }
        return this.mLayoutManager.getPosition(lastCompletelyVisibleView);
    }

    public int findLastVisibleItemPosition(int i) {
        View lastVisibleView = getLastVisibleView(i);
        if (lastVisibleView == null) {
            return -1;
        }
        return this.mLayoutManager.getPosition(lastVisibleView);
    }

    public LayoutManager.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutManager.LayoutParams(context, attributeSet);
    }

    public View getFirstCompletelyVisibleView(int i, boolean z) {
        int i2;
        int paddingTop = this.mLayoutManager.getClipToPadding() ? this.mLayoutManager.getPaddingTop() : 0;
        if (this.mLayoutManager.getClipToPadding()) {
            i2 = this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
        } else {
            i2 = this.mLayoutManager.getHeight();
        }
        int childCount = this.mLayoutManager.getChildCount();
        View view = null;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.mLayoutManager.getChildAt(i3);
            boolean z2 = true;
            boolean z3 = this.mLayoutManager.getDecoratedTop(childAt) >= paddingTop;
            if (this.mLayoutManager.getDecoratedBottom(childAt) > i2) {
                z2 = false;
            }
            LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) childAt.getLayoutParams();
            if (i != layoutParams.getTestedFirstPosition()) {
                return view;
            }
            if (z3 && z2) {
                if (!layoutParams.isHeader || !z) {
                    return childAt;
                }
                view = childAt;
            }
        }
        return view;
    }

    public View getFirstVisibleView(int i, boolean z) {
        int childCount = this.mLayoutManager.getChildCount();
        int i2 = 0;
        View view = null;
        while (i2 < childCount) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) childAt.getLayoutParams();
            if (i != layoutParams.getTestedFirstPosition()) {
                return view;
            }
            if (!layoutParams.isHeader || !z) {
                return childAt;
            }
            i2++;
            view = childAt;
        }
        return view;
    }

    public int getHighestEdge(int i, int i2, int i3) {
        while (i2 < this.mLayoutManager.getChildCount()) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) childAt.getLayoutParams();
            if (layoutParams.getTestedFirstPosition() != i) {
                break;
            } else if (!layoutParams.isHeader) {
                return this.mLayoutManager.getDecoratedTop(childAt);
            } else {
                i2++;
            }
        }
        return i3;
    }

    public View getLastCompletelyVisibleView(int i) {
        int i2;
        int paddingTop = this.mLayoutManager.getClipToPadding() ? this.mLayoutManager.getPaddingTop() : 0;
        if (this.mLayoutManager.getClipToPadding()) {
            i2 = this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
        } else {
            i2 = this.mLayoutManager.getHeight();
        }
        int childCount = this.mLayoutManager.getChildCount() - 1;
        View view = null;
        while (childCount >= 0) {
            View childAt = this.mLayoutManager.getChildAt(childCount);
            boolean z = this.mLayoutManager.getDecoratedTop(childAt) >= paddingTop;
            boolean z2 = this.mLayoutManager.getDecoratedBottom(childAt) <= i2;
            LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) childAt.getLayoutParams();
            if (i == layoutParams.getTestedFirstPosition()) {
                if (z && z2) {
                    if (!layoutParams.isHeader) {
                        return childAt;
                    }
                    view = childAt;
                }
                childCount--;
            } else if (view != null) {
                return view;
            } else {
                i = layoutParams.getTestedFirstPosition();
            }
        }
        return view;
    }

    public View getLastVisibleView(int i) {
        int childCount = this.mLayoutManager.getChildCount() - 1;
        View view = null;
        while (childCount >= 0) {
            View childAt = this.mLayoutManager.getChildAt(childCount);
            LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) childAt.getLayoutParams();
            if (i != layoutParams.getTestedFirstPosition()) {
                return view;
            }
            if (!layoutParams.isHeader) {
                return childAt;
            }
            childCount--;
            view = childAt;
        }
        return view;
    }

    public int getLowestEdge(int i, int i2, int i3) {
        while (i2 >= 0) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) childAt.getLayoutParams();
            if (layoutParams.getTestedFirstPosition() != i) {
                break;
            } else if (!layoutParams.isHeader) {
                return this.mLayoutManager.getDecoratedBottom(childAt);
            } else {
                i2--;
            }
        }
        return i3;
    }

    public int howManyMissingAbove(int i, SparseArray<Boolean> sparseArray) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < sparseArray.size()) {
            if (sparseArray.get(i, false).booleanValue()) {
                i2++;
            } else {
                i3++;
            }
            i++;
        }
        return i3;
    }

    public int howManyMissingBelow(int i, SparseArray<Boolean> sparseArray) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < sparseArray.size()) {
            if (sparseArray.get(i, false).booleanValue()) {
                i2++;
            } else {
                i3++;
            }
            i--;
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    public int addView(LayoutState.View view, int i, LayoutManager.Direction direction, LayoutState layoutState) {
        int i2;
        if (direction == LayoutManager.Direction.START) {
            i2 = 0;
        } else {
            i2 = this.mLayoutManager.getChildCount();
        }
        layoutState.decacheView(i);
        this.mLayoutManager.addView(view.view, i2);
        return i2;
    }
}
