package com.qxmd.qxrecyclerview;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class SmoothScrollLinearLayoutManager extends LinearLayoutManager {
    private Context context;
    /* access modifiers changed from: private */
    public boolean hasSetSnapPreference = false;
    /* access modifiers changed from: private */
    public int snapPreference;

    public SmoothScrollLinearLayoutManager(Context context2) {
        super(context2);
        this.context = context2;
    }

    public SmoothScrollLinearLayoutManager(Context context2, int i, boolean z) {
        super(context2, i, z);
        this.context = context2;
    }

    public SmoothScrollLinearLayoutManager(Context context2, AttributeSet attributeSet, int i, int i2) {
        super(context2, attributeSet, i, i2);
        this.context = context2;
    }

    public void setSnapPreference(int i) {
        this.hasSetSnapPreference = true;
        this.snapPreference = i;
    }

    public void smoothScrollToPosition(final RecyclerView recyclerView, RecyclerView.State state, int i) {
        AnonymousClass1 r3 = new LinearSmoothScroller(this.context) {
            public PointF computeScrollVectorForPosition(int i) {
                return ((LinearLayoutManager) recyclerView.getLayoutManager()).computeScrollVectorForPosition(i);
            }

            /* access modifiers changed from: protected */
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 35.0f / ((float) displayMetrics.densityDpi);
            }

            /* access modifiers changed from: protected */
            public int getVerticalSnapPreference() {
                if (SmoothScrollLinearLayoutManager.this.hasSetSnapPreference) {
                    return SmoothScrollLinearLayoutManager.this.snapPreference;
                }
                return super.getVerticalSnapPreference();
            }
        };
        r3.setTargetPosition(i);
        startSmoothScroll(r3);
    }
}
