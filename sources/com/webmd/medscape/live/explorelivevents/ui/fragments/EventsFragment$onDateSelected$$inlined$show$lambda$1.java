package com.webmd.medscape.live.explorelivevents.ui.fragments;

import androidx.fragment.app.FragmentActivity;
import com.afollestad.materialdialogs.MaterialDialog;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.BaseFiltersView;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.medscape.live.explorelivevents.util.DateFilterManager;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.ZonedDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/afollestad/materialdialogs/MaterialDialog;", "index", "", "text", "", "invoke", "com/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragment$onDateSelected$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: EventsFragment.kt */
final class EventsFragment$onDateSelected$$inlined$show$lambda$1 extends Lambda implements Function3<MaterialDialog, Integer, CharSequence, Unit> {
    final /* synthetic */ EventsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventsFragment$onDateSelected$$inlined$show$lambda$1(EventsFragment eventsFragment) {
        super(3);
        this.this$0 = eventsFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((MaterialDialog) obj, ((Number) obj2).intValue(), (CharSequence) obj3);
        return Unit.INSTANCE;
    }

    public final void invoke(MaterialDialog materialDialog, int i, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(materialDialog, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(charSequence, "text");
        switch (i) {
            case 0:
                this.this$0.extractDateFilterUserSelectionAndLoadData(DateFilterManager.INSTANCE.getToday(), charSequence);
                return;
            case 1:
                this.this$0.extractDateFilterUserSelectionAndLoadData(DateFilterManager.INSTANCE.getTommorow(), charSequence);
                return;
            case 2:
                this.this$0.extractDateFilterUserSelectionAndLoadData(DateFilterManager.INSTANCE.getThisWeek(), charSequence);
                return;
            case 3:
                this.this$0.extractDateFilterUserSelectionAndLoadData(DateFilterManager.INSTANCE.getThisWeekend(), charSequence);
                return;
            case 4:
                this.this$0.extractDateFilterUserSelectionAndLoadData(DateFilterManager.INSTANCE.getNextWeek(DayOfWeek.SUNDAY), charSequence);
                return;
            case 5:
                this.this$0.extractDateFilterUserSelectionAndLoadData(DateFilterManager.INSTANCE.allUpcoming(), charSequence);
                return;
            case 6:
                FragmentActivity activity = this.this$0.getActivity();
                if (activity != null) {
                    ZonedDateTime now = ZonedDateTime.now();
                    Intrinsics.checkNotNullExpressionValue(now, "ZonedDateTime.now()");
                    ExtensionsKt.showDateRangePicker(activity, now, new Function1<Pair<? extends String, ? extends String>, Unit>(this) {
                        final /* synthetic */ EventsFragment$onDateSelected$$inlined$show$lambda$1 this$0;

                        {
                            this.this$0 = r1;
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((Pair<String, String>) (Pair) obj);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Pair<String, String> pair) {
                            Intrinsics.checkNotNullParameter(pair, "selectedRange");
                            this.this$0.this$0.startDate = pair.getFirst();
                            this.this$0.this$0.endDate = pair.getSecond();
                            String string = this.this$0.this$0.getString(R.string.custom_date_title, ExtensionsKt.parseServerDateToReadableFormat(this.this$0.this$0.startDate), ExtensionsKt.parseServerDateToReadableFormat(this.this$0.this$0.endDate));
                            Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                            this.this$0.this$0.dateTitle = string;
                            this.this$0.this$0.title = string;
                            BaseFiltersView.DefaultImpls.sendFilterActionCall$default(this.this$0.this$0, OmnitureConstants.OMNITURE_FILTER_DATE, this.this$0.this$0.dateTitle, false, 4, (Object) null);
                            this.this$0.this$0.updateDateFilter(this.this$0.this$0.dateTitle);
                            this.this$0.this$0.observeForEvents();
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }
}
