package com.webmd.medscape.live.explorelivevents.ui.viewmodels;

import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/webmd/medscape/live/explorelivevents/data/Error;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: ExploreEventsViewModel.kt */
final class ExploreEventsViewModel$fetchLiveEvents$2 extends Lambda implements Function1<Error, Unit> {
    final /* synthetic */ ExploreEventsViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExploreEventsViewModel$fetchLiveEvents$2(ExploreEventsViewModel exploreEventsViewModel) {
        super(1);
        this.this$0 = exploreEventsViewModel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Error) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        this.this$0.liveEventsObserver.setValue(Resource.Companion.error(error.getMessage(), null));
        this.this$0.getErrorObserver().setValue(error);
    }
}
