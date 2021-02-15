package com.medscape.android.activity.calc.calcsettings.views;

import android.widget.CompoundButton;
import com.wbmd.qxcalculator.model.db.DBUser;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: CalcSettingsFragment.kt */
final class CalcSettingsFragment$setUpViews$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ DBUser $dbUser;
    final /* synthetic */ CalcSettingsFragment this$0;

    CalcSettingsFragment$setUpViews$1(CalcSettingsFragment calcSettingsFragment, DBUser dBUser) {
        this.this$0 = calcSettingsFragment;
        this.$dbUser = dBUser;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.this$0.getViewModel().updateAutoEnterFirstQuestion(this.$dbUser, z);
    }
}
