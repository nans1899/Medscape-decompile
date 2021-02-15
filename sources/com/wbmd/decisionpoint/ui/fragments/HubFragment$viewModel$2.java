package com.wbmd.decisionpoint.ui.fragments;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.wbmd.decisionpoint.viewmodels.HubViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/wbmd/decisionpoint/viewmodels/HubViewModel;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: HubFragment.kt */
final class HubFragment$viewModel$2 extends Lambda implements Function0<HubViewModel> {
    final /* synthetic */ HubFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HubFragment$viewModel$2(HubFragment hubFragment) {
        super(0);
        this.this$0 = hubFragment;
    }

    public final HubViewModel invoke() {
        ViewModel viewModel = ViewModelProviders.of((Fragment) this.this$0).get(HubViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProviders.of(th…HubViewModel::class.java]");
        return (HubViewModel) viewModel;
    }
}
