package com.webmd.medscape.live.explorelivevents.ui.fragments;

import com.afollestad.materialdialogs.MaterialDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/afollestad/materialdialogs/MaterialDialog;", "invoke", "com/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragment$onDateSelected$1$3"}, k = 3, mv = {1, 4, 0})
/* compiled from: EventsFragment.kt */
final class EventsFragment$onDateSelected$$inlined$show$lambda$2 extends Lambda implements Function1<MaterialDialog, Unit> {
    final /* synthetic */ MaterialDialog $this_show;
    final /* synthetic */ EventsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventsFragment$onDateSelected$$inlined$show$lambda$2(MaterialDialog materialDialog, EventsFragment eventsFragment) {
        super(1);
        this.$this_show = materialDialog;
        this.this$0 = eventsFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MaterialDialog) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(MaterialDialog materialDialog) {
        Intrinsics.checkNotNullParameter(materialDialog, "it");
        if (this.this$0.dateTitle == null) {
            EventsFragment.access$getBinding$p(this.this$0).filters.disableFilterAtPosition(0);
        }
        this.$this_show.dismiss();
    }
}
