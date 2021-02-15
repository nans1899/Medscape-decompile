package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

public class PullToRefreshScrollView extends PullToRefreshBase<StickyScrollView> {
    public PullToRefreshScrollView(Context context) {
        super(context);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshScrollView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshScrollView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /* access modifiers changed from: protected */
    public StickyScrollView createRefreshableView(Context context, AttributeSet attributeSet) {
        StickyScrollView stickyScrollView;
        if (Build.VERSION.SDK_INT >= 9) {
            stickyScrollView = new InternalScrollViewSDK9(context, attributeSet);
        } else {
            stickyScrollView = new StickyScrollView(context, attributeSet);
        }
        stickyScrollView.setId(R.id.scrollview);
        return stickyScrollView;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullStart() {
        return ((StickyScrollView) this.mRefreshableView).getScrollY() == 0;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullEnd() {
        View childAt = ((StickyScrollView) this.mRefreshableView).getChildAt(0);
        if (childAt == null || ((StickyScrollView) this.mRefreshableView).getScrollY() < childAt.getHeight() - getHeight()) {
            return false;
        }
        return true;
    }

    final class InternalScrollViewSDK9 extends StickyScrollView {
        public InternalScrollViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshScrollView.this, i, i3, i2, i4, getScrollRange(), z);
            return overScrollBy;
        }

        private int getScrollRange() {
            if (getChildCount() > 0) {
                return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
            }
            return 0;
        }
    }
}
