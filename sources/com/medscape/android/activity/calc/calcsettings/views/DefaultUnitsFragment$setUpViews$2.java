package com.medscape.android.activity.calc.calcsettings.views;

import android.view.View;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBUser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: DefaultUnitsFragment.kt */
final class DefaultUnitsFragment$setUpViews$2 implements View.OnClickListener {
    final /* synthetic */ DBUser $dbUser;
    final /* synthetic */ DefaultUnitsFragment this$0;

    DefaultUnitsFragment$setUpViews$2(DefaultUnitsFragment defaultUnitsFragment, DBUser dBUser) {
        this.this$0 = defaultUnitsFragment;
        this.$dbUser = dBUser;
    }

    public final void onClick(View view) {
        DBUser dBUser = this.$dbUser;
        Intrinsics.checkNotNullExpressionValue(dBUser, "dbUser");
        dBUser.setDefaultUnits(Unit.UnitType.convertTypeToString(Unit.UnitType.US_UNITS));
        this.$dbUser.update();
        this.this$0.updateDefaultUnits(this.$dbUser);
    }
}
