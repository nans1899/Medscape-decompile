package com.medscape.android.drugs.details.views;

import androidx.lifecycle.Observer;
import com.medscape.android.drugs.details.adapters.DrugSectionAdapter;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.drugs.model.DrugFindHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivityFragment.kt */
final class DrugDetailsActivityFragment$setObservers$1<T> implements Observer<List<? extends LineItem>> {
    final /* synthetic */ DrugDetailsActivityFragment this$0;

    DrugDetailsActivityFragment$setObservers$1(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        this.this$0 = drugDetailsActivityFragment;
    }

    public final void onChanged(List<? extends LineItem> list) {
        if (!this.this$0.getActivityViewModel().getGoingToNextSection()) {
            this.this$0.isInlineADcallComplete = null;
            DrugSectionAdapter access$getViewAdapter$p = DrugDetailsActivityFragment.access$getViewAdapter$p(this.this$0);
            if (list == null) {
                list = new ArrayList<>();
            }
            access$getViewAdapter$p.setMItems(list);
            DrugSectionAdapter access$getViewAdapter$p2 = DrugDetailsActivityFragment.access$getViewAdapter$p(this.this$0);
            DrugFindHelper finder = this.this$0.getDrugSectionViewModel().getFindManager().getFinder();
            access$getViewAdapter$p2.setSharethroughADLoadingDone(finder != null ? finder.isInFindMode() : false);
            DrugDetailsActivityFragment.access$getViewAdapter$p(this.this$0).notifyDataSetChanged();
            DrugDetailsActivityFragment drugDetailsActivityFragment = this.this$0;
            drugDetailsActivityFragment.loadAd(DrugDetailsActivityFragment.access$getViewManager$p(drugDetailsActivityFragment), DrugDetailsActivityFragment.access$getViewAdapter$p(this.this$0));
        }
    }
}
