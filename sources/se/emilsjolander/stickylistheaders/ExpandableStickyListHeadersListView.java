package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

public class ExpandableStickyListHeadersListView extends StickyListHeadersListView {
    public static final int ANIMATION_COLLAPSE = 1;
    public static final int ANIMATION_EXPAND = 0;
    IAnimationExecutor mDefaultAnimExecutor = new IAnimationExecutor() {
        public void executeAnim(View view, int i) {
            if (i == 0) {
                view.setVisibility(0);
            } else if (i == 1) {
                view.setVisibility(8);
            }
        }
    };
    ExpandableStickyListHeadersAdapter mExpandableStickyListHeadersAdapter;

    public interface IAnimationExecutor {
        void executeAnim(View view, int i);
    }

    public ExpandableStickyListHeadersListView(Context context) {
        super(context);
    }

    public ExpandableStickyListHeadersListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ExpandableStickyListHeadersListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ExpandableStickyListHeadersAdapter getAdapter() {
        return this.mExpandableStickyListHeadersAdapter;
    }

    public void setAdapter(StickyListHeadersAdapter stickyListHeadersAdapter) {
        ExpandableStickyListHeadersAdapter expandableStickyListHeadersAdapter = new ExpandableStickyListHeadersAdapter(stickyListHeadersAdapter);
        this.mExpandableStickyListHeadersAdapter = expandableStickyListHeadersAdapter;
        super.setAdapter(expandableStickyListHeadersAdapter);
    }

    public View findViewByItemId(long j) {
        return this.mExpandableStickyListHeadersAdapter.findViewByItemId(j);
    }

    public long findItemIdByView(View view) {
        return this.mExpandableStickyListHeadersAdapter.findItemIdByView(view);
    }

    public void expand(long j) {
        if (this.mExpandableStickyListHeadersAdapter.isHeaderCollapsed(j)) {
            this.mExpandableStickyListHeadersAdapter.expand(j);
            List<View> itemViewsByHeaderId = this.mExpandableStickyListHeadersAdapter.getItemViewsByHeaderId(j);
            if (itemViewsByHeaderId != null) {
                for (View animateView : itemViewsByHeaderId) {
                    animateView(animateView, 0);
                }
            }
        }
    }

    public void collapse(long j) {
        if (!this.mExpandableStickyListHeadersAdapter.isHeaderCollapsed(j)) {
            this.mExpandableStickyListHeadersAdapter.collapse(j);
            List<View> itemViewsByHeaderId = this.mExpandableStickyListHeadersAdapter.getItemViewsByHeaderId(j);
            if (itemViewsByHeaderId != null) {
                for (View animateView : itemViewsByHeaderId) {
                    animateView(animateView, 1);
                }
            }
        }
    }

    public boolean isHeaderCollapsed(long j) {
        return this.mExpandableStickyListHeadersAdapter.isHeaderCollapsed(j);
    }

    public void setAnimExecutor(IAnimationExecutor iAnimationExecutor) {
        this.mDefaultAnimExecutor = iAnimationExecutor;
    }

    private void animateView(View view, int i) {
        IAnimationExecutor iAnimationExecutor;
        if (i != 0 || view.getVisibility() != 0) {
            if ((1 != i || view.getVisibility() == 0) && (iAnimationExecutor = this.mDefaultAnimExecutor) != null) {
                iAnimationExecutor.executeAnim(view, i);
            }
        }
    }
}
