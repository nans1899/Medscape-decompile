package com.wbmd.environment;

import android.content.Context;
import com.wbmd.environment.data.Environment;
import com.wbmd.environment.data.Module;
import com.wbmd.environment.utils.SharedPreferencesManager;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.media.android.bidder.base.common.Constants;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u001c\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\fj\n\u0012\u0004\u0012\u00020\r\u0018\u0001`\u000eJ6\u0010\u000f\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0010j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J$\u0010\u0012\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004¨\u0006\u0016"}, d2 = {"Lcom/wbmd/environment/EnvironmentManager;", "", "()V", "getBaseUrl", "", "context", "Landroid/content/Context;", "moduleId", "getEnvironment", "Lcom/wbmd/environment/data/Environment;", "getEnvironmentWithDefault", "getModules", "Ljava/util/ArrayList;", "Lcom/wbmd/environment/data/Module;", "Lkotlin/collections/ArrayList;", "getParameters", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "setEnvironment", "", "environmentId", "Companion", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EnvironmentManager.kt */
public final class EnvironmentManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static IEnvironmentConfig config;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/wbmd/environment/EnvironmentManager$Companion;", "", "()V", "config", "Lcom/wbmd/environment/IEnvironmentConfig;", "", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EnvironmentManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void config(IEnvironmentConfig iEnvironmentConfig) {
            Intrinsics.checkNotNullParameter(iEnvironmentConfig, Constants.CONFIG_FILE_NAME);
            EnvironmentManager.config = iEnvironmentConfig;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: com.wbmd.environment.data.Environment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.wbmd.environment.data.Environment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: com.wbmd.environment.data.Environment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.wbmd.environment.data.Environment} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.wbmd.environment.data.Environment getEnvironment(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            if (r6 == 0) goto L_0x0092
            com.wbmd.environment.utils.SharedPreferencesManager r1 = new com.wbmd.environment.utils.SharedPreferencesManager
            r1.<init>(r5)
            java.lang.String r5 = r1.getString(r6, r0)
            com.wbmd.environment.IEnvironmentConfig r1 = config
            if (r1 == 0) goto L_0x003e
            java.util.ArrayList r1 = r1.getModules()
            if (r1 == 0) goto L_0x003e
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x001c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0034
            java.lang.Object r2 = r1.next()
            r3 = r2
            com.wbmd.environment.data.Module r3 = (com.wbmd.environment.data.Module) r3
            java.lang.String r3 = r3.getId()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r6)
            if (r3 == 0) goto L_0x001c
            goto L_0x0035
        L_0x0034:
            r2 = r0
        L_0x0035:
            com.wbmd.environment.data.Module r2 = (com.wbmd.environment.data.Module) r2
            if (r2 == 0) goto L_0x003e
            java.util.ArrayList r6 = r2.getEnvironments()
            goto L_0x003f
        L_0x003e:
            r6 = r0
        L_0x003f:
            if (r5 != 0) goto L_0x0070
            if (r6 == 0) goto L_0x0065
            r5 = r6
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x004a:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x005e
            java.lang.Object r1 = r5.next()
            r2 = r1
            com.wbmd.environment.data.Environment r2 = (com.wbmd.environment.data.Environment) r2
            boolean r2 = r2.isActive()
            if (r2 == 0) goto L_0x004a
            goto L_0x005f
        L_0x005e:
            r1 = r0
        L_0x005f:
            com.wbmd.environment.data.Environment r1 = (com.wbmd.environment.data.Environment) r1
            if (r1 == 0) goto L_0x0065
            r0 = r1
            goto L_0x0092
        L_0x0065:
            if (r6 == 0) goto L_0x0092
            r5 = 0
            java.lang.Object r5 = r6.get(r5)
            r0 = r5
            com.wbmd.environment.data.Environment r0 = (com.wbmd.environment.data.Environment) r0
            goto L_0x0092
        L_0x0070:
            if (r6 == 0) goto L_0x0092
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L_0x0078:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x0090
            java.lang.Object r1 = r6.next()
            r2 = r1
            com.wbmd.environment.data.Environment r2 = (com.wbmd.environment.data.Environment) r2
            java.lang.String r2 = r2.getId()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 == 0) goto L_0x0078
            r0 = r1
        L_0x0090:
            com.wbmd.environment.data.Environment r0 = (com.wbmd.environment.data.Environment) r0
        L_0x0092:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.environment.EnvironmentManager.getEnvironment(android.content.Context, java.lang.String):com.wbmd.environment.data.Environment");
    }

    public final void setEnvironment(Context context, String str, String str2) {
        if (str != null) {
            new SharedPreferencesManager(context).saveString(str, str2);
        }
    }

    public final String getBaseUrl(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "moduleId");
        Environment environment = getEnvironment(context, str);
        if (environment != null) {
            return environment.getBaseUrl();
        }
        return null;
    }

    public final HashMap<String, String> getParameters(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "moduleId");
        Environment environment = getEnvironment(context, str);
        if (environment != null) {
            return environment.getParameters();
        }
        return null;
    }

    public final ArrayList<Module> getModules() {
        IEnvironmentConfig iEnvironmentConfig = config;
        if (iEnvironmentConfig != null) {
            return iEnvironmentConfig.getModules();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0010, code lost:
        r2 = r2.getId();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getEnvironmentWithDefault(android.content.Context r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "moduleId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.wbmd.environment.data.Environment r2 = r1.getEnvironment(r2, r3)
            if (r2 == 0) goto L_0x0017
            java.lang.String r2 = r2.getId()
            if (r2 == 0) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            java.lang.String r2 = "environment_production"
        L_0x0019:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.environment.EnvironmentManager.getEnvironmentWithDefault(android.content.Context, java.lang.String):java.lang.String");
    }
}
