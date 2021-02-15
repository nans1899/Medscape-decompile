package com.medscape.android.drugs.details.viewmodels;

import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004 \u0005*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugSectionViewModel.kt */
final class DrugSectionViewModel$setUp$1<T> implements Observer<Pair<? extends Integer, ? extends Integer>> {
    final /* synthetic */ DrugSectionViewModel this$0;

    DrugSectionViewModel$setUp$1(DrugSectionViewModel drugSectionViewModel) {
        this.this$0 = drugSectionViewModel;
    }

    public final void onChanged(Pair<Integer, Integer> pair) {
        if (pair == null) {
            pair = new Pair<>(0, 0);
        }
        this.this$0.getLoading().setValue(true);
        this.this$0.getDrugSectionContentRepo().getItems(pair);
    }
}
