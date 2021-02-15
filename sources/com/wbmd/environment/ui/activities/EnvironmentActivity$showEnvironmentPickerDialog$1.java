package com.wbmd.environment.ui.activities;

import com.wbmd.environment.data.Environment;
import com.wbmd.environment.interfaces.OnEnvironmentListener;
import com.wbmd.environment.ui.adapters.ModuleAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wbmd/environment/ui/activities/EnvironmentActivity$showEnvironmentPickerDialog$1", "Lcom/wbmd/environment/interfaces/OnEnvironmentListener;", "onChanged", "", "environment", "Lcom/wbmd/environment/data/Environment;", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EnvironmentActivity.kt */
public final class EnvironmentActivity$showEnvironmentPickerDialog$1 implements OnEnvironmentListener {
    final /* synthetic */ EnvironmentActivity this$0;

    EnvironmentActivity$showEnvironmentPickerDialog$1(EnvironmentActivity environmentActivity) {
        this.this$0 = environmentActivity;
    }

    public void onChanged(Environment environment) {
        Intrinsics.checkNotNullParameter(environment, "environment");
        ModuleAdapter access$getAdapter$p = this.this$0.adapter;
        if (access$getAdapter$p != null) {
            access$getAdapter$p.notifyDataSetChanged();
        }
    }
}
