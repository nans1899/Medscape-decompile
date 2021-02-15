package com.medscape.android.drugs.details.views;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivityFragment.kt */
final class DrugDetailsActivityFragment$onActivityCreated$1 implements Runnable {
    final /* synthetic */ DrugDetailsActivityFragment this$0;

    DrugDetailsActivityFragment$onActivityCreated$1(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        this.this$0 = drugDetailsActivityFragment;
    }

    public final void run() {
        DrugDetailsActivityFragment drugDetailsActivityFragment = this.this$0;
        drugDetailsActivityFragment.loadAd(DrugDetailsActivityFragment.access$getViewManager$p(drugDetailsActivityFragment), DrugDetailsActivityFragment.access$getViewAdapter$p(this.this$0));
    }
}
