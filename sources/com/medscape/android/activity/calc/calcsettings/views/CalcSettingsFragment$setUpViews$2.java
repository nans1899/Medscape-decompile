package com.medscape.android.activity.calc.calcsettings.views;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.activity.calc.calcsettings.viewmodels.CalcSettingsViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: CalcSettingsFragment.kt */
final class CalcSettingsFragment$setUpViews$2 implements View.OnClickListener {
    final /* synthetic */ CalcSettingsFragment this$0;

    CalcSettingsFragment$setUpViews$2(CalcSettingsFragment calcSettingsFragment) {
        this.this$0 = calcSettingsFragment;
    }

    public final void onClick(View view) {
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            CalcSettingsViewModel viewModel = this.this$0.getViewModel();
            Intrinsics.checkNotNullExpressionValue(activity, "it1");
            viewModel.openDefaultUnitsScreen(activity);
        }
    }
}
