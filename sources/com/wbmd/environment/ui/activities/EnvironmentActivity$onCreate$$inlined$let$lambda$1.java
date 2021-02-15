package com.wbmd.environment.ui.activities;

import com.wbmd.environment.data.Module;
import com.wbmd.environment.interfaces.OnModuleListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006¸\u0006\u0000"}, d2 = {"com/wbmd/environment/ui/activities/EnvironmentActivity$onCreate$1$1", "Lcom/wbmd/environment/interfaces/OnModuleListener;", "onChanged", "", "module", "Lcom/wbmd/environment/data/Module;", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EnvironmentActivity.kt */
public final class EnvironmentActivity$onCreate$$inlined$let$lambda$1 implements OnModuleListener {
    final /* synthetic */ EnvironmentActivity this$0;

    EnvironmentActivity$onCreate$$inlined$let$lambda$1(EnvironmentActivity environmentActivity) {
        this.this$0 = environmentActivity;
    }

    public void onChanged(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        this.this$0.showEnvironmentPickerDialog(module);
    }
}
