package com.medscape.android.drugs.details.views;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivityFragment.kt */
final class DrugDetailsActivityFragment$setObservers$2<T> implements Observer<Boolean> {
    final /* synthetic */ DrugDetailsActivityFragment this$0;

    DrugDetailsActivityFragment$setObservers$2(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        this.this$0 = drugDetailsActivityFragment;
    }

    public final void onChanged(Boolean bool) {
        int i = 8;
        DrugDetailsActivityFragment.access$getLoadingSpinner$p(this.this$0).setVisibility(Intrinsics.areEqual((Object) bool, (Object) true) ? 0 : 8);
        this.this$0.getActivityViewModel().setLoadingItemsFinished(true ^ bool.booleanValue());
        RecyclerView access$getRecyclerView$p = DrugDetailsActivityFragment.access$getRecyclerView$p(this.this$0);
        if (Intrinsics.areEqual((Object) bool, (Object) false)) {
            i = 0;
        }
        access$getRecyclerView$p.setVisibility(i);
    }
}
