package com.medscape.android.activity.calc.calcsettings.viewmodels;

import android.app.Activity;
import android.content.Intent;
import androidx.lifecycle.ViewModel;
import com.medscape.android.activity.calc.calcsettings.views.DefaultUnitsActivity;
import com.wbmd.qxcalculator.model.db.DBUser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/medscape/android/activity/calc/calcsettings/viewmodels/CalcSettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "openDefaultUnitsScreen", "", "activity", "Landroid/app/Activity;", "updateAutoEnterFirstQuestion", "dbUser", "Lcom/wbmd/qxcalculator/model/db/DBUser;", "checked", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CalcSettingsViewModel.kt */
public final class CalcSettingsViewModel extends ViewModel {
    public final void openDefaultUnitsScreen(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.startActivity(new Intent(activity, DefaultUnitsActivity.class));
    }

    public final void updateAutoEnterFirstQuestion(DBUser dBUser, boolean z) {
        if (dBUser != null) {
            dBUser.setAutoEnterFirstQuestion(Boolean.valueOf(z));
        }
        if (dBUser != null) {
            dBUser.update();
        }
    }
}
