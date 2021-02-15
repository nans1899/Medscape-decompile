package com.medscape.android.landingfeed.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.anko.AnkoAsyncContext;
import org.jetbrains.anko.AsyncKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lorg/jetbrains/anko/AnkoAsyncContext;", "Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: LandingFeedViewModel.kt */
final class LandingFeedViewModel$onResume$2 extends Lambda implements Function1<AnkoAsyncContext<LandingFeedViewModel>, Unit> {
    final /* synthetic */ LandingFeedViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LandingFeedViewModel$onResume$2(LandingFeedViewModel landingFeedViewModel) {
        super(1);
        this.this$0 = landingFeedViewModel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((AnkoAsyncContext<LandingFeedViewModel>) (AnkoAsyncContext) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(AnkoAsyncContext<LandingFeedViewModel> ankoAsyncContext) {
        Intrinsics.checkNotNullParameter(ankoAsyncContext, "$receiver");
        this.this$0.getFeedAdapter().updateSaveStateOfAllItems();
        AsyncKt.uiThread(ankoAsyncContext, new Function1<LandingFeedViewModel, Unit>(this) {
            final /* synthetic */ LandingFeedViewModel$onResume$2 this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((LandingFeedViewModel) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(LandingFeedViewModel landingFeedViewModel) {
                Intrinsics.checkNotNullParameter(landingFeedViewModel, "it");
                this.this$0.this$0.getFeedAdapter().notifyDataSetChanged();
            }
        });
    }
}
