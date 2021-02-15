package com.wbmd.ads;

import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J \u0010\u0012\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/wbmd/ads/ScrollSpeedRecycleViewScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "maxScrollSpeedForAdInjection", "", "(I)V", "currentScrollSpeed", "getCurrentScrollSpeed", "()I", "setCurrentScrollSpeed", "previousEventTime", "", "previousFirstVisibleItem", "listNeedsRefresh", "", "onScrollStateChanged", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "onScrolled", "dx", "dy", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ScrollSpeedRecycleViewScrollListner.kt */
public abstract class ScrollSpeedRecycleViewScrollListener extends RecyclerView.OnScrollListener {
    private int currentScrollSpeed;
    private final int maxScrollSpeedForAdInjection;
    private long previousEventTime;
    private int previousFirstVisibleItem;

    public abstract void listNeedsRefresh();

    public ScrollSpeedRecycleViewScrollListener(int i) {
        this.maxScrollSpeedForAdInjection = i;
    }

    public final int getCurrentScrollSpeed() {
        return this.currentScrollSpeed;
    }

    public final void setCurrentScrollSpeed(int i) {
        this.currentScrollSpeed = i;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        int findFirstVisibleItemPosition;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, i, i2);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if ((layoutManager instanceof LinearLayoutManager) && this.previousFirstVisibleItem != (findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition())) {
            long currentTimeMillis = System.currentTimeMillis();
            this.currentScrollSpeed = (int) ((((double) 1) / ((double) (currentTimeMillis - this.previousEventTime))) * ((double) 1000));
            this.previousFirstVisibleItem = findFirstVisibleItemPosition;
            this.previousEventTime = currentTimeMillis;
            Log.d("Scroll Speed", "Speed: " + this.currentScrollSpeed + " elements/second");
        }
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0) {
            if (this.currentScrollSpeed > this.maxScrollSpeedForAdInjection) {
                listNeedsRefresh();
            }
            this.currentScrollSpeed = 0;
        }
    }
}
