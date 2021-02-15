package com.wbmd.environment.ui.fragments;

import android.content.DialogInterface;
import com.wbmd.environment.data.Environment;
import com.wbmd.environment.interfaces.OnEnvironmentListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "which", "", "onClick", "com/wbmd/environment/ui/fragments/EnvironmentDialogFragment$onCreateDialog$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: EnvironmentDialogFragment.kt */
final class EnvironmentDialogFragment$onCreateDialog$$inlined$let$lambda$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ ArrayList $it;
    final /* synthetic */ EnvironmentDialogFragment this$0;

    EnvironmentDialogFragment$onCreateDialog$$inlined$let$lambda$1(ArrayList arrayList, EnvironmentDialogFragment environmentDialogFragment) {
        this.$it = arrayList;
        this.this$0 = environmentDialogFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.manager.setEnvironment(this.this$0.getContext(), this.this$0.moduleId, ((Environment) this.$it.get(i)).getId());
        OnEnvironmentListener access$getListener$p = EnvironmentDialogFragment.access$getListener$p(this.this$0);
        Object obj = this.$it.get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "it[which]");
        access$getListener$p.onChanged((Environment) obj);
        this.this$0.dismiss();
    }
}
