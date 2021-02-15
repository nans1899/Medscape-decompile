package com.wbmd.environment.interfaces;

import com.wbmd.environment.data.Module;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/wbmd/environment/interfaces/OnModuleListener;", "", "onChanged", "", "module", "Lcom/wbmd/environment/data/Module;", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OnModuleListener.kt */
public interface OnModuleListener {
    void onChanged(Module module);
}