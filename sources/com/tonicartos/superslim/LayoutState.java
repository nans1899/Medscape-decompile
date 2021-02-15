package com.tonicartos.superslim;

import android.util.SparseArray;
import androidx.recyclerview.widget.RecyclerView;
import com.tonicartos.superslim.LayoutManager;

public class LayoutState {
    public final boolean isLTR;
    public final RecyclerView.Recycler recycler;
    private final RecyclerView.State recyclerState;
    public final SparseArray<android.view.View> viewCache;

    public LayoutState(RecyclerView.LayoutManager layoutManager, RecyclerView.Recycler recycler2, RecyclerView.State state) {
        this.viewCache = new SparseArray<>(layoutManager.getChildCount());
        this.recyclerState = state;
        this.recycler = recycler2;
        this.isLTR = layoutManager.getLayoutDirection() == 0;
    }

    public void cacheView(int i, android.view.View view) {
        this.viewCache.put(i, view);
    }

    public void decacheView(int i) {
        this.viewCache.remove(i);
    }

    public android.view.View getCachedView(int i) {
        return this.viewCache.get(i);
    }

    public RecyclerView.State getRecyclerState() {
        return this.recyclerState;
    }

    public View getView(int i) {
        android.view.View cachedView = getCachedView(i);
        boolean z = cachedView != null;
        if (cachedView == null) {
            cachedView = this.recycler.getViewForPosition(i);
        }
        return new View(cachedView, z);
    }

    public void recycleCache() {
        for (int i = 0; i < this.viewCache.size(); i++) {
            this.recycler.recycleView(this.viewCache.valueAt(i));
        }
    }

    public static class View {
        public final android.view.View view;
        public final boolean wasCached;

        public View(android.view.View view2, boolean z) {
            this.view = view2;
            this.wasCached = z;
        }

        public LayoutManager.LayoutParams getLayoutParams() {
            return (LayoutManager.LayoutParams) this.view.getLayoutParams();
        }
    }
}
