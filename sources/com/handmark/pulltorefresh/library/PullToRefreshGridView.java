package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

public class PullToRefreshGridView extends PullToRefreshAdapterViewBase<GridView> {
    public PullToRefreshGridView(Context context) {
        super(context);
    }

    public PullToRefreshGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshGridView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshGridView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /* access modifiers changed from: protected */
    public final GridView createRefreshableView(Context context, AttributeSet attributeSet) {
        GridView gridView;
        if (Build.VERSION.SDK_INT >= 9) {
            gridView = new InternalGridViewSDK9(context, attributeSet);
        } else {
            gridView = new InternalGridView(context, attributeSet);
        }
        gridView.setId(R.id.gridview);
        return gridView;
    }

    class InternalGridView extends GridView implements EmptyViewMethodAccessor {
        public InternalGridView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void setEmptyView(View view) {
            PullToRefreshGridView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }

    final class InternalGridViewSDK9 extends InternalGridView {
        public InternalGridViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshGridView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }
}
