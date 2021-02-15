package com.medscape.android.consult.postupdates.views;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.medscape.android.consult.postupdates.viewmodels.ConsultPostUpdateViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/medscape/android/consult/postupdates/viewmodels/ConsultPostUpdateViewModel;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: ConsultPostUpdateActivity.kt */
final class ConsultPostUpdateActivity$viewModel$2 extends Lambda implements Function0<ConsultPostUpdateViewModel> {
    final /* synthetic */ ConsultPostUpdateActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ConsultPostUpdateActivity$viewModel$2(ConsultPostUpdateActivity consultPostUpdateActivity) {
        super(0);
        this.this$0 = consultPostUpdateActivity;
    }

    public final ConsultPostUpdateViewModel invoke() {
        return (ConsultPostUpdateViewModel) ViewModelProviders.of((FragmentActivity) this.this$0).get(ConsultPostUpdateViewModel.class);
    }
}
