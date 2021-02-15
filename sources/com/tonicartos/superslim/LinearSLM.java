package com.tonicartos.superslim;

import android.view.View;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LayoutState;

public class LinearSLM extends SectionLayoutManager {
    public static int ID = 1;

    public LinearSLM(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public int computeHeaderOffset(int i, SectionData sectionData, LayoutState layoutState) {
        int i2 = sectionData.firstPosition + 1;
        int i3 = 0;
        while (i3 < sectionData.headerHeight && i2 < i) {
            LayoutState.View view = layoutState.getView(i2);
            measureChild(view, sectionData);
            i3 += this.mLayoutManager.getDecoratedMeasuredHeight(view.view);
            layoutState.cacheView(i2, view.view);
            i2++;
        }
        if (i3 == sectionData.headerHeight) {
            return 0;
        }
        if (i3 > sectionData.headerHeight) {
            return 1;
        }
        return -i3;
    }

    public int fillToEnd(int i, int i2, int i3, SectionData sectionData, LayoutState layoutState) {
        int itemCount = layoutState.getRecyclerState().getItemCount();
        int i4 = i2;
        while (true) {
            if (i3 >= itemCount || i4 >= i) {
                break;
            }
            LayoutState.View view = layoutState.getView(i3);
            if (view.getLayoutParams().getTestedFirstPosition() != sectionData.firstPosition) {
                layoutState.cacheView(i3, view.view);
                break;
            }
            measureChild(view, sectionData);
            i4 = layoutChild(view, i4, LayoutManager.Direction.END, sectionData, layoutState);
            addView(view, i3, LayoutManager.Direction.END, layoutState);
            i3++;
        }
        return i4;
    }

    public int fillToStart(int i, int i2, int i3, SectionData sectionData, LayoutState layoutState) {
        boolean z;
        View childAt;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i5 >= layoutState.getRecyclerState().getItemCount() || (childAt = this.mLayoutManager.getChildAt(0)) == null) {
                break;
            }
            LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) childAt.getLayoutParams();
            if (layoutParams.getTestedFirstPosition() != sectionData.firstPosition) {
                z = true;
                break;
            } else if (!layoutParams.isHeader) {
                break;
            } else {
                i5++;
            }
        }
        z = false;
        int i6 = -1;
        if (z) {
            int i7 = i3;
            int i8 = 0;
            while (true) {
                if (i7 < 0) {
                    break;
                }
                LayoutState.View view = layoutState.getView(i7);
                layoutState.cacheView(i7, view.view);
                LayoutManager.LayoutParams layoutParams2 = view.getLayoutParams();
                if (layoutParams2.getTestedFirstPosition() != sectionData.firstPosition) {
                    break;
                }
                if (!layoutParams2.isHeader) {
                    measureChild(view, sectionData);
                    i8 += this.mLayoutManager.getDecoratedMeasuredHeight(view.view);
                    if (i8 >= sectionData.minimumHeight) {
                        i6 = i7;
                        break;
                    }
                    i6 = i7;
                }
                i7--;
            }
            if (i8 < sectionData.minimumHeight) {
                i4 = i8 - sectionData.minimumHeight;
                i2 += i4;
            }
        }
        int i9 = i2;
        while (true) {
            if (i3 < 0 || i9 - i4 <= i) {
                break;
            }
            LayoutState.View view2 = layoutState.getView(i3);
            LayoutManager.LayoutParams layoutParams3 = view2.getLayoutParams();
            if (layoutParams3.isHeader) {
                layoutState.cacheView(i3, view2.view);
                break;
            } else if (layoutParams3.getTestedFirstPosition() != sectionData.firstPosition) {
                layoutState.cacheView(i3, view2.view);
                break;
            } else {
                if (!z || i3 < i6) {
                    measureChild(view2, sectionData);
                } else {
                    layoutState.decacheView(i3);
                }
                i9 = layoutChild(view2, i9, LayoutManager.Direction.START, sectionData, layoutState);
                addView(view2, i3, LayoutManager.Direction.START, layoutState);
                i3--;
            }
        }
        return i9;
    }

    public int finishFillToEnd(int i, View view, SectionData sectionData, LayoutState layoutState) {
        return fillToEnd(i, this.mLayoutManager.getDecoratedBottom(view), this.mLayoutManager.getPosition(view) + 1, sectionData, layoutState);
    }

    public int finishFillToStart(int i, View view, SectionData sectionData, LayoutState layoutState) {
        return fillToStart(i, this.mLayoutManager.getDecoratedTop(view), this.mLayoutManager.getPosition(view) - 1, sectionData, layoutState);
    }

    private int layoutChild(LayoutState.View view, int i, LayoutManager.Direction direction, SectionData sectionData, LayoutState layoutState) {
        int i2;
        int i3;
        int decoratedMeasuredHeight = this.mLayoutManager.getDecoratedMeasuredHeight(view.view);
        int decoratedMeasuredWidth = this.mLayoutManager.getDecoratedMeasuredWidth(view.view);
        int i4 = layoutState.isLTR ? sectionData.contentStart : sectionData.contentEnd;
        int i5 = i4 + decoratedMeasuredWidth;
        if (direction == LayoutManager.Direction.END) {
            i3 = i;
            i2 = decoratedMeasuredHeight + i;
        } else {
            i2 = i;
            i3 = i - decoratedMeasuredHeight;
        }
        this.mLayoutManager.layoutDecorated(view.view, i4, i3, i5, i2);
        if (direction == LayoutManager.Direction.END) {
            return this.mLayoutManager.getDecoratedBottom(view.view);
        }
        return this.mLayoutManager.getDecoratedTop(view.view);
    }

    private void measureChild(LayoutState.View view, SectionData sectionData) {
        this.mLayoutManager.measureChildWithMargins(view.view, sectionData.getTotalMarginWidth(), 0);
    }
}
