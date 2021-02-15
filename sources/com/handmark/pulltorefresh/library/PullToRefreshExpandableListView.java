package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

public class PullToRefreshExpandableListView extends PullToRefreshAdapterViewBase<ExpandableListView> {
    public PullToRefreshExpandableListView(Context context) {
        super(context);
    }

    public PullToRefreshExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshExpandableListView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshExpandableListView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /* access modifiers changed from: protected */
    public ExpandableListView createRefreshableView(Context context, AttributeSet attributeSet) {
        ExpandableListView expandableListView;
        if (Build.VERSION.SDK_INT >= 9) {
            expandableListView = new InternalExpandableListViewSDK9(context, attributeSet);
        } else {
            expandableListView = new InternalExpandableListView(context, attributeSet);
        }
        expandableListView.setId(16908298);
        return expandableListView;
    }

    class InternalExpandableListView extends ExpandableListView implements EmptyViewMethodAccessor {
        public InternalExpandableListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void setEmptyView(View view) {
            PullToRefreshExpandableListView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }

    final class InternalExpandableListViewSDK9 extends InternalExpandableListView {
        public InternalExpandableListViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshExpandableListView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }
}
