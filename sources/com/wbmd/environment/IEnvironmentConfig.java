package com.wbmd.environment;

import com.wbmd.environment.data.Module;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/wbmd/environment/IEnvironmentConfig;", "", "getModules", "Ljava/util/ArrayList;", "Lcom/wbmd/environment/data/Module;", "Lkotlin/collections/ArrayList;", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: IEnvironmentConfig.kt */
public interface IEnvironmentConfig {
    ArrayList<Module> getModules();
}
