package com.tonicartos.superslim;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LayoutState;

public class GridSLM extends SectionLayoutManager {
    private static final int AUTO_FIT = -1;
    private static final int DEFAULT_NUM_COLUMNS = 1;
    public static int ID = 2;
    private int mColumnWidth;
    private boolean mColumnsSpecified;
    private final Context mContext;
    private int mMinimumWidth = 0;
    private int mNumColumns = 0;

    public GridSLM(LayoutManager layoutManager, Context context) {
        super(layoutManager);
        this.mContext = context;
    }

    public int computeHeaderOffset(int i, SectionData sectionData, LayoutState layoutState) {
        int i2;
        int itemCount = layoutState.getRecyclerState().getItemCount();
        int i3 = sectionData.firstPosition + 1;
        int i4 = 0;
        while (i4 < sectionData.headerHeight && i3 < i) {
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.mNumColumns && (i2 = i3 + i5) < itemCount) {
                LayoutState.View view = layoutState.getView(i2);
                measureChild(view, sectionData);
                i6 = Math.max(i6, this.mLayoutManager.getDecoratedMeasuredHeight(view.view));
                layoutState.cacheView(i2, view.view);
                i5++;
            }
            i4 += i6;
            i3 += this.mNumColumns;
        }
        if (i4 == sectionData.headerHeight) {
            return 0;
        }
        if (i4 > sectionData.headerHeight) {
            return 1;
        }
        return -i4;
    }

    public int fillToEnd(int i, int i2, int i3, SectionData sectionData, LayoutState layoutState) {
        int itemCount;
        if (i2 >= i || i3 >= (itemCount = layoutState.getRecyclerState().getItemCount())) {
            return i2;
        }
        LayoutState.View view = layoutState.getView(i3);
        layoutState.cacheView(i3, view.view);
        if (view.getLayoutParams().getTestedFirstPosition() != sectionData.firstPosition) {
            return i2;
        }
        int i4 = (i3 - (sectionData.hasHeader ? sectionData.firstPosition + 1 : sectionData.firstPosition)) % this.mNumColumns;
        for (int i5 = 1; i5 <= i4; i5++) {
            int i6 = 1;
            while (true) {
                if (i6 > this.mLayoutManager.getChildCount()) {
                    break;
                }
                View childAt = this.mLayoutManager.getChildAt(this.mLayoutManager.getChildCount() - i6);
                if (this.mLayoutManager.getPosition(childAt) == i3 - i5) {
                    i2 = this.mLayoutManager.getDecoratedTop(childAt);
                    this.mLayoutManager.detachAndScrapViewAt(i6, layoutState.recycler);
                    break;
                } else if (((LayoutManager.LayoutParams) childAt.getLayoutParams()).getTestedFirstPosition() != sectionData.firstPosition) {
                    break;
                } else {
                    i6++;
                }
            }
        }
        int i7 = i3 - i4;
        while (true) {
            if (i7 >= itemCount || i2 > i) {
                break;
            }
            LayoutState.View view2 = layoutState.getView(i7);
            if (view2.getLayoutParams().getTestedFirstPosition() != sectionData.firstPosition) {
                layoutState.cacheView(i7, view2.view);
                break;
            }
            i2 += fillRow(i2, i7, LayoutManager.Direction.END, true, sectionData, layoutState);
            i7 += this.mNumColumns;
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
        r12 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int fillToStart(int r19, int r20, int r21, com.tonicartos.superslim.SectionData r22, com.tonicartos.superslim.LayoutState r23) {
        /*
            r18 = this;
            r7 = r18
            r0 = r21
            r8 = r22
            r9 = r23
            boolean r1 = r8.hasHeader
            r10 = 1
            if (r1 == 0) goto L_0x0011
            int r1 = r8.firstPosition
            int r1 = r1 + r10
            goto L_0x0013
        L_0x0011:
            int r1 = r8.firstPosition
        L_0x0013:
            r11 = 0
            r2 = 0
        L_0x0015:
            com.tonicartos.superslim.LayoutManager r3 = r7.mLayoutManager
            int r3 = r3.getChildCount()
            if (r2 >= r3) goto L_0x003b
            com.tonicartos.superslim.LayoutManager r3 = r7.mLayoutManager
            android.view.View r3 = r3.getChildAt(r11)
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            com.tonicartos.superslim.LayoutManager$LayoutParams r3 = (com.tonicartos.superslim.LayoutManager.LayoutParams) r3
            int r4 = r3.getTestedFirstPosition()
            int r5 = r8.firstPosition
            if (r4 == r5) goto L_0x0033
            r12 = 1
            goto L_0x003c
        L_0x0033:
            boolean r3 = r3.isHeader
            if (r3 != 0) goto L_0x0038
            goto L_0x003b
        L_0x0038:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x003b:
            r12 = 0
        L_0x003c:
            int r1 = r0 - r1
            int r2 = r7.mNumColumns
            int r1 = r1 % r2
            r2 = 1
        L_0x0042:
            int r3 = r7.mNumColumns
            int r3 = r3 - r1
            if (r2 >= r3) goto L_0x007d
            r3 = 0
        L_0x0048:
            com.tonicartos.superslim.LayoutManager r4 = r7.mLayoutManager
            int r4 = r4.getChildCount()
            if (r3 >= r4) goto L_0x007a
            com.tonicartos.superslim.LayoutManager r4 = r7.mLayoutManager
            android.view.View r4 = r4.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            com.tonicartos.superslim.LayoutManager$LayoutParams r5 = (com.tonicartos.superslim.LayoutManager.LayoutParams) r5
            int r5 = r5.getTestedFirstPosition()
            int r6 = r8.firstPosition
            if (r5 == r6) goto L_0x0065
            goto L_0x007a
        L_0x0065:
            com.tonicartos.superslim.LayoutManager r5 = r7.mLayoutManager
            int r4 = r5.getPosition(r4)
            int r5 = r0 + r2
            if (r4 != r5) goto L_0x0077
            com.tonicartos.superslim.LayoutManager r4 = r7.mLayoutManager
            androidx.recyclerview.widget.RecyclerView$Recycler r5 = r9.recycler
            r4.detachAndScrapViewAt(r3, r5)
            goto L_0x007a
        L_0x0077:
            int r3 = r3 + 1
            goto L_0x0048
        L_0x007a:
            int r2 = r2 + 1
            goto L_0x0042
        L_0x007d:
            int r1 = r0 - r1
            r2 = -1
            if (r12 == 0) goto L_0x00f5
            r2 = r1
            r3 = -1
            r4 = 0
        L_0x0085:
            if (r2 < 0) goto L_0x00e4
            com.tonicartos.superslim.LayoutState$View r5 = r9.getView(r2)
            android.view.View r6 = r5.view
            r9.cacheView(r2, r6)
            com.tonicartos.superslim.LayoutManager$LayoutParams r5 = r5.getLayoutParams()
            int r5 = r5.getTestedFirstPosition()
            int r6 = r8.firstPosition
            if (r5 == r6) goto L_0x009d
            goto L_0x00e4
        L_0x009d:
            r3 = 0
            r5 = 0
        L_0x009f:
            int r6 = r7.mNumColumns
            if (r3 >= r6) goto L_0x00d4
            int r6 = r2 + r3
            if (r6 > r0) goto L_0x00d4
            com.tonicartos.superslim.LayoutState$View r13 = r9.getView(r6)
            android.view.View r14 = r13.view
            r9.cacheView(r6, r14)
            com.tonicartos.superslim.LayoutManager$LayoutParams r6 = r13.getLayoutParams()
            int r14 = r6.getTestedFirstPosition()
            int r15 = r8.firstPosition
            if (r14 == r15) goto L_0x00bd
            goto L_0x00d4
        L_0x00bd:
            boolean r6 = r6.isHeader
            if (r6 == 0) goto L_0x00c2
            goto L_0x00d1
        L_0x00c2:
            r7.measureChild(r13, r8)
            com.tonicartos.superslim.LayoutManager r6 = r7.mLayoutManager
            android.view.View r13 = r13.view
            int r6 = r6.getDecoratedMeasuredHeight(r13)
            int r5 = java.lang.Math.max(r5, r6)
        L_0x00d1:
            int r3 = r3 + 1
            goto L_0x009f
        L_0x00d4:
            int r4 = r4 + r5
            int r3 = r8.minimumHeight
            if (r4 < r3) goto L_0x00da
            goto L_0x00e5
        L_0x00da:
            int r3 = r7.mNumColumns
            int r3 = r2 - r3
            r17 = r3
            r3 = r2
            r2 = r17
            goto L_0x0085
        L_0x00e4:
            r2 = r3
        L_0x00e5:
            int r0 = r8.minimumHeight
            if (r4 >= r0) goto L_0x00f1
            int r0 = r8.minimumHeight
            int r4 = r4 - r0
            int r0 = r20 + r4
            r13 = r2
            r14 = r4
            goto L_0x00f9
        L_0x00f1:
            r0 = r20
            r13 = r2
            goto L_0x00f8
        L_0x00f5:
            r0 = r20
            r13 = -1
        L_0x00f8:
            r14 = 0
        L_0x00f9:
            r15 = r0
            r6 = r1
        L_0x00fb:
            if (r6 < 0) goto L_0x013c
            int r0 = r15 - r14
            r5 = r19
            if (r0 > r5) goto L_0x0104
            goto L_0x013c
        L_0x0104:
            com.tonicartos.superslim.LayoutState$View r0 = r9.getView(r6)
            android.view.View r1 = r0.view
            r9.cacheView(r6, r1)
            com.tonicartos.superslim.LayoutManager$LayoutParams r0 = r0.getLayoutParams()
            boolean r1 = r0.isHeader
            if (r1 != 0) goto L_0x013c
            int r0 = r0.getTestedFirstPosition()
            int r1 = r8.firstPosition
            if (r0 == r1) goto L_0x011e
            goto L_0x013c
        L_0x011e:
            if (r12 == 0) goto L_0x0125
            if (r6 >= r13) goto L_0x0123
            goto L_0x0125
        L_0x0123:
            r4 = 0
            goto L_0x0126
        L_0x0125:
            r4 = 1
        L_0x0126:
            com.tonicartos.superslim.LayoutManager$Direction r3 = com.tonicartos.superslim.LayoutManager.Direction.START
            r0 = r18
            r1 = r15
            r2 = r6
            r5 = r22
            r16 = r6
            r6 = r23
            int r0 = r0.fillRow(r1, r2, r3, r4, r5, r6)
            int r15 = r15 - r0
            int r0 = r7.mNumColumns
            int r6 = r16 - r0
            goto L_0x00fb
        L_0x013c:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonicartos.superslim.GridSLM.fillToStart(int, int, int, com.tonicartos.superslim.SectionData, com.tonicartos.superslim.LayoutState):int");
    }

    public int finishFillToEnd(int i, View view, SectionData sectionData, LayoutState layoutState) {
        return fillToEnd(i, getLowestEdge(sectionData.firstPosition, this.mLayoutManager.getChildCount() - 1, this.mLayoutManager.getDecoratedBottom(view)), this.mLayoutManager.getPosition(view) + 1, sectionData, layoutState);
    }

    public int finishFillToStart(int i, View view, SectionData sectionData, LayoutState layoutState) {
        return fillToStart(i, this.mLayoutManager.getDecoratedTop(view), this.mLayoutManager.getPosition(view) - 1, sectionData, layoutState);
    }

    public LayoutManager.LayoutParams generateLayoutParams(LayoutManager.LayoutParams layoutParams) {
        return LayoutParams.from(layoutParams);
    }

    public LayoutManager.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public int getAnchorPosition(LayoutState layoutState, SectionData sectionData, int i) {
        calculateColumnWidthValues(sectionData);
        int i2 = sectionData.firstPosition;
        LayoutState.View view = layoutState.getView(i2);
        if (view.getLayoutParams().isHeader) {
            i2++;
        }
        layoutState.cacheView(sectionData.firstPosition, view.view);
        return i - ((i - i2) % this.mNumColumns);
    }

    public int getLowestEdge(int i, int i2, int i3) {
        int width = this.mLayoutManager.getWidth();
        int i4 = 0;
        boolean z = false;
        while (i2 >= 0) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) childAt.getLayoutParams();
            if (layoutParams.getTestedFirstPosition() != i) {
                break;
            }
            if (!layoutParams.isHeader) {
                if (childAt.getLeft() >= width) {
                    break;
                }
                width = childAt.getLeft();
                z = true;
                i4 = Math.max(i4, this.mLayoutManager.getDecoratedBottom(childAt));
            }
            i2--;
        }
        return z ? i4 : i3;
    }

    public GridSLM init(SectionData sectionData) {
        super.init(sectionData);
        if (sectionData.headerParams instanceof LayoutParams) {
            LayoutParams layoutParams = (LayoutParams) sectionData.headerParams;
            int columnWidth = layoutParams.getColumnWidth();
            int numColumns = layoutParams.getNumColumns();
            if (columnWidth < 0 && numColumns < 0) {
                numColumns = 1;
            }
            if (numColumns == -1) {
                setColumnWidth(columnWidth);
            } else {
                setNumColumns(numColumns);
            }
        }
        calculateColumnWidthValues(sectionData);
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int fillRow(int r19, int r20, com.tonicartos.superslim.LayoutManager.Direction r21, boolean r22, com.tonicartos.superslim.SectionData r23, com.tonicartos.superslim.LayoutState r24) {
        /*
            r18 = this;
            r7 = r18
            r8 = r21
            r9 = r23
            r10 = r24
            int r0 = r7.mNumColumns
            com.tonicartos.superslim.LayoutState$View[] r11 = new com.tonicartos.superslim.LayoutState.View[r0]
            r0 = 0
            r1 = 0
            r12 = 0
        L_0x000f:
            int r2 = r7.mNumColumns
            if (r1 >= r2) goto L_0x0050
            int r2 = r20 + r1
            androidx.recyclerview.widget.RecyclerView$State r3 = r24.getRecyclerState()
            int r3 = r3.getItemCount()
            if (r2 < r3) goto L_0x0020
            goto L_0x0050
        L_0x0020:
            com.tonicartos.superslim.LayoutState$View r3 = r10.getView(r2)
            com.tonicartos.superslim.LayoutManager$LayoutParams r4 = r3.getLayoutParams()
            int r4 = r4.getTestedFirstPosition()
            int r5 = r9.firstPosition
            if (r4 == r5) goto L_0x0036
            android.view.View r1 = r3.view
            r10.cacheView(r2, r1)
            goto L_0x0050
        L_0x0036:
            if (r22 == 0) goto L_0x003c
            r7.measureChild(r3, r9)
            goto L_0x003f
        L_0x003c:
            r10.decacheView(r2)
        L_0x003f:
            com.tonicartos.superslim.LayoutManager r2 = r7.mLayoutManager
            android.view.View r4 = r3.view
            int r2 = r2.getDecoratedMeasuredHeight(r4)
            int r12 = java.lang.Math.max(r12, r2)
            r11[r1] = r3
            int r1 = r1 + 1
            goto L_0x000f
        L_0x0050:
            com.tonicartos.superslim.LayoutManager$Direction r1 = com.tonicartos.superslim.LayoutManager.Direction.START
            r13 = 1
            if (r8 != r1) goto L_0x0057
            r14 = 1
            goto L_0x0058
        L_0x0057:
            r14 = 0
        L_0x0058:
            if (r14 == 0) goto L_0x005e
            int r1 = r19 - r12
            r15 = r1
            goto L_0x0060
        L_0x005e:
            r15 = r19
        L_0x0060:
            r6 = 0
        L_0x0061:
            int r0 = r7.mNumColumns
            if (r6 >= r0) goto L_0x00a0
            if (r14 == 0) goto L_0x006c
            int r0 = r0 - r6
            int r0 = r0 - r13
            r16 = r0
            goto L_0x006e
        L_0x006c:
            r16 = r6
        L_0x006e:
            boolean r0 = r10.isLTR
            if (r0 == 0) goto L_0x0077
            if (r14 == 0) goto L_0x0079
            int r0 = r7.mNumColumns
            goto L_0x007d
        L_0x0077:
            if (r14 == 0) goto L_0x007b
        L_0x0079:
            r0 = r6
            goto L_0x007f
        L_0x007b:
            int r0 = r7.mNumColumns
        L_0x007d:
            int r0 = r0 - r6
            int r0 = r0 - r13
        L_0x007f:
            r3 = r0
            r0 = r11[r16]
            if (r0 != 0) goto L_0x0087
            r17 = r6
            goto L_0x009d
        L_0x0087:
            r1 = r11[r16]
            r0 = r18
            r2 = r15
            r4 = r12
            r5 = r23
            r17 = r6
            r6 = r24
            r0.layoutChild(r1, r2, r3, r4, r5, r6)
            r0 = r11[r16]
            int r1 = r16 + r20
            r7.addView(r0, r1, r8, r10)
        L_0x009d:
            int r6 = r17 + 1
            goto L_0x0061
        L_0x00a0:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tonicartos.superslim.GridSLM.fillRow(int, int, com.tonicartos.superslim.LayoutManager$Direction, boolean, com.tonicartos.superslim.SectionData, com.tonicartos.superslim.LayoutState):int");
    }

    @Deprecated
    public void setColumnWidth(int i) {
        this.mMinimumWidth = i;
        this.mColumnsSpecified = false;
    }

    @Deprecated
    public void setNumColumns(int i) {
        this.mNumColumns = i;
        this.mMinimumWidth = 0;
        this.mColumnsSpecified = true;
    }

    private void calculateColumnWidthValues(SectionData sectionData) {
        int width = (this.mLayoutManager.getWidth() - sectionData.contentStart) - sectionData.contentEnd;
        if (!this.mColumnsSpecified) {
            if (this.mMinimumWidth <= 0) {
                this.mMinimumWidth = (int) TypedValue.applyDimension(1, 48.0f, this.mContext.getResources().getDisplayMetrics());
            }
            this.mNumColumns = width / Math.abs(this.mMinimumWidth);
        }
        if (this.mNumColumns < 1) {
            this.mNumColumns = 1;
        }
        int i = width / this.mNumColumns;
        this.mColumnWidth = i;
        if (i == 0) {
            Log.e("GridSection", "Too many columns (" + this.mNumColumns + ") for available width" + width + ".");
        }
    }

    private void layoutChild(LayoutState.View view, int i, int i2, int i3, SectionData sectionData, LayoutState layoutState) {
        int i4;
        if (view.getLayoutParams().height != -1) {
            i3 = this.mLayoutManager.getDecoratedMeasuredHeight(view.view);
        }
        if (i2 == this.mNumColumns - 1) {
            i4 = this.mLayoutManager.getDecoratedMeasuredWidth(view.view);
        } else {
            i4 = Math.min(this.mColumnWidth, this.mLayoutManager.getDecoratedMeasuredWidth(view.view));
        }
        int i5 = i + i3;
        int i6 = (layoutState.isLTR ? sectionData.contentStart : sectionData.contentEnd) + (i2 * this.mColumnWidth);
        this.mLayoutManager.layoutDecorated(view.view, i6, i, i6 + i4, i5);
    }

    private void measureChild(LayoutState.View view, SectionData sectionData) {
        this.mLayoutManager.measureChildWithMargins(view.view, sectionData.marginStart + sectionData.marginEnd + ((this.mNumColumns - 1) * this.mColumnWidth), 0);
    }

    public static class LayoutParams extends LayoutManager.LayoutParams {
        private int mColumnWidth;
        private int mNumColumns;

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.superslim_GridSLM);
            this.mNumColumns = obtainStyledAttributes.getInt(R.styleable.superslim_GridSLM_slm_grid_numColumns, -1);
            this.mColumnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.superslim_GridSLM_slm_grid_columnWidth, -1);
            obtainStyledAttributes.recycle();
        }

        @Deprecated
        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            init(marginLayoutParams);
        }

        @Deprecated
        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            init(layoutParams);
        }

        public static LayoutParams from(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams == null) {
                Log.w("SuperSLiM", "Null value passed in call to GridSLM.LayoutParams.from().");
                return new LayoutParams(-2, -2);
            } else if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            } else {
                return new LayoutParams(layoutParams);
            }
        }

        public int getColumnWidth() {
            return this.mColumnWidth;
        }

        public void setColumnWidth(int i) {
            this.mColumnWidth = i;
        }

        public int getNumColumns() {
            return this.mNumColumns;
        }

        public void setNumColumns(int i) {
            this.mNumColumns = i;
        }

        private void init(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.mNumColumns = layoutParams2.mNumColumns;
                this.mColumnWidth = layoutParams2.mColumnWidth;
                return;
            }
            this.mNumColumns = -1;
            this.mColumnWidth = -1;
        }
    }
}
