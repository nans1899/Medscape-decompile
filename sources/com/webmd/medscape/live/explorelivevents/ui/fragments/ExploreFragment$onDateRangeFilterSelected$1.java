package com.webmd.medscape.live.explorelivevents.ui.fragments;

import androidx.navigation.fragment.FragmentKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "selectedRange", "Lkotlin/Pair;", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: ExploreFragment.kt */
final class ExploreFragment$onDateRangeFilterSelected$1 extends Lambda implements Function1<Pair<? extends String, ? extends String>, Unit> {
    final /* synthetic */ ExploreFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExploreFragment$onDateRangeFilterSelected$1(ExploreFragment exploreFragment) {
        super(1);
        this.this$0 = exploreFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Pair<String, String>) (Pair) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Pair<String, String> pair) {
        Intrinsics.checkNotNullParameter(pair, "selectedRange");
        FragmentKt.findNavController(this.this$0).navigate(ExploreFragmentDirections.Companion.actionExploreToEvents(ExploreFragment.access$getBaseUrl$p(this.this$0), (String) null, this.this$0.getStyleManager(), pair.getFirst(), pair.getSecond()));
    }
}
