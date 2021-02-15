package com.medscape.android.drugs.details.views;

import androidx.lifecycle.Observer;
import com.medscape.android.drugs.details.datamodels.ScrollPosition;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/medscape/android/drugs/details/datamodels/ScrollPosition;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivityFragment.kt */
final class DrugDetailsActivityFragment$setObservers$3<T> implements Observer<ScrollPosition> {
    final /* synthetic */ DrugDetailsActivityFragment this$0;

    DrugDetailsActivityFragment$setObservers$3(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        this.this$0 = drugDetailsActivityFragment;
    }

    public final void onChanged(final ScrollPosition scrollPosition) {
        if (scrollPosition != null) {
            int findFirstCompletelyVisibleItemPosition = DrugDetailsActivityFragment.access$getViewManager$p(this.this$0).findFirstCompletelyVisibleItemPosition();
            if (findFirstCompletelyVisibleItemPosition == -1) {
                findFirstCompletelyVisibleItemPosition = DrugDetailsActivityFragment.access$getViewManager$p(this.this$0).findFirstVisibleItemPosition();
            }
            int findLastCompletelyVisibleItemPosition = DrugDetailsActivityFragment.access$getViewManager$p(this.this$0).findLastCompletelyVisibleItemPosition();
            int position = scrollPosition.getPosition();
            if (findFirstCompletelyVisibleItemPosition > position || findLastCompletelyVisibleItemPosition < position) {
                int position2 = scrollPosition.getPosition();
                if (scrollPosition.getPosition() > 0) {
                    position2 = scrollPosition.getPosition() - 1;
                }
                DrugDetailsActivityFragment.access$getRecyclerView$p(this.this$0).smoothScrollToPosition(position2);
                DrugDetailsActivityFragment.access$getRecyclerView$p(this.this$0).postDelayed(new Runnable(this) {
                    final /* synthetic */ DrugDetailsActivityFragment$setObservers$3 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final void run() {
                        DrugDetailsActivityFragment.access$getRecyclerView$p(this.this$0.this$0).scrollBy(0, scrollPosition.getOffset());
                    }
                }, 100);
            }
        }
    }
}
