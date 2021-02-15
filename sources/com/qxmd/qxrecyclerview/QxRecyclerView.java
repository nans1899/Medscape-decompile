package com.qxmd.qxrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class QxRecyclerView extends RecyclerView {
    private View emptyView;
    public boolean ignoreHeadersForEmptyViewConsideration;
    private boolean ignoreSetVisiblityCall;
    private final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            QxRecyclerView.this.checkIfEmpty();
        }

        public void onItemRangeInserted(int i, int i2) {
            QxRecyclerView.this.checkIfEmpty();
        }

        public void onItemRangeRemoved(int i, int i2) {
            QxRecyclerView.this.checkIfEmpty();
        }
    };

    public QxRecyclerView(Context context) {
        super(context);
    }

    public QxRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QxRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: private */
    public void checkIfEmpty() {
        if (this.emptyView != null && getAdapter() != null) {
            if (getVisibility() == 0 || this.emptyView.getVisibility() == 0) {
                int itemCount = getAdapter().getItemCount();
                int i = 0;
                boolean z = true;
                if (!this.ignoreHeadersForEmptyViewConsideration ? itemCount != 0 : itemCount > 1) {
                    z = false;
                }
                View view = this.emptyView;
                if (!z) {
                    i = 8;
                }
                view.setVisibility(i);
            }
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (!this.ignoreSetVisiblityCall) {
            if (i == 8 || i == 4) {
                View view = this.emptyView;
                if (view != null) {
                    view.setVisibility(8);
                }
            } else if (i == 0) {
                checkIfEmpty();
            }
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = getAdapter();
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View view) {
        this.emptyView = view;
        checkIfEmpty();
    }
}
