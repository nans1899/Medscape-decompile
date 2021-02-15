package com.medscape.android.landingfeed.views;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.view.PageIndicator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/medscape/android/landingfeed/views/FeedItemConsultViewHolder$setupImageViewer$1", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedItemConsultViewHolder.kt */
public final class FeedItemConsultViewHolder$setupImageViewer$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ FeedItemConsultViewHolder this$0;

    FeedItemConsultViewHolder$setupImageViewer$1(FeedItemConsultViewHolder feedItemConsultViewHolder) {
        this.this$0 = feedItemConsultViewHolder;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0) {
            View view = this.this$0.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            PageIndicator pageIndicator = (PageIndicator) view.findViewById(R.id.content_image_indicator);
            Intrinsics.checkNotNullExpressionValue(pageIndicator, "itemView.content_image_indicator");
            View view2 = this.this$0.itemView;
            Intrinsics.checkNotNullExpressionValue(view2, "itemView");
            RecyclerView recyclerView2 = (RecyclerView) view2.findViewById(R.id.content_image_viewer);
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "itemView.content_image_viewer");
            RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
            if (layoutManager != null) {
                pageIndicator.setSelectedPage(((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
    }
}
