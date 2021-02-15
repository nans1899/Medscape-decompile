package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

public class PullToRefreshHorizontalScrollView extends PullToRefreshBase<HorizontalScrollView> {
    public PullToRefreshHorizontalScrollView(Context context) {
        super(context);
    }

    public PullToRefreshHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshHorizontalScrollView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshHorizontalScrollView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.HORIZONTAL;
    }

    /* access modifiers changed from: protected */
    public HorizontalScrollView createRefreshableView(Context context, AttributeSet attributeSet) {
        HorizontalScrollView horizontalScrollView;
        if (Build.VERSION.SDK_INT >= 9) {
            horizontalScrollView = new InternalHorizontalScrollViewSDK9(context, attributeSet);
        } else {
            horizontalScrollView = new HorizontalScrollView(context, attributeSet);
        }
        horizontalScrollView.setId(R.id.scrollview);
        return horizontalScrollView;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullStart() {
        return ((HorizontalScrollView) this.mRefreshableView).getScrollX() == 0;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullEnd() {
        View childAt = ((HorizontalScrollView) this.mRefreshableView).getChildAt(0);
        if (childAt == null || ((HorizontalScrollView) this.mRefreshableView).getScrollX() < childAt.getWidth() - getWidth()) {
            return false;
        }
        return true;
    }

    final class InternalHorizontalScrollViewSDK9 extends HorizontalScrollView {
        public InternalHorizontalScrollViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshHorizontalScrollView.this, i, i3, i2, i4, getScrollRange(), z);
            return overScrollBy;
        }

        private int getScrollRange() {
            if (getChildCount() > 0) {
                return Math.max(0, getChildAt(0).getWidth() - ((getWidth() - getPaddingLeft()) - getPaddingRight()));
            }
            return 0;
        }
    }
}
