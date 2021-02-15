package com.wbmd.environment.ui.adapters;

import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import com.wbmd.environment.EnvironmentManager;
import com.wbmd.environment.data.Environment;
import com.wbmd.environment.data.Module;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¨\u0006\u0006"}, d2 = {"setActiveEnvironment", "", "view", "Landroid/widget/TextView;", "module", "Lcom/wbmd/environment/data/Module;", "wbmdenvironment_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: ModuleAdapter.kt */
public final class ModuleAdapterKt {
    @BindingAdapter({"activeEnvironment"})
    public static final void setActiveEnvironment(TextView textView, Module module) {
        Intrinsics.checkNotNullParameter(textView, "view");
        if (module != null) {
            Environment environment = new EnvironmentManager().getEnvironment(textView.getContext(), module.getId());
            textView.setText(environment != null ? environment.getName() : null);
        }
    }
}
