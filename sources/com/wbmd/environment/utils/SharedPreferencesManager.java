package com.wbmd.environment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/wbmd/environment/utils/SharedPreferencesManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getString", "", "key", "defaultValue", "saveString", "", "value", "Companion", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SharedPreferencesManager.kt */
public final class SharedPreferencesManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String PREFERENCE_NAME = "wbmd_environment_preferences";
    private final Context context;

    public SharedPreferencesManager(Context context2) {
        this.context = context2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/wbmd/environment/utils/SharedPreferencesManager$Companion;", "", "()V", "PREFERENCE_NAME", "", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SharedPreferencesManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void saveString(String str, String str2) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putString;
        Intrinsics.checkNotNullParameter(str, "key");
        Context context2 = this.context;
        if (context2 != null && (sharedPreferences = context2.getSharedPreferences(PREFERENCE_NAME, 0)) != null && (edit = sharedPreferences.edit()) != null && (putString = edit.putString(str, str2)) != null) {
            putString.apply();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r0 = r0.getSharedPreferences(PREFERENCE_NAME, 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getString(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            android.content.Context r0 = r3.context
            if (r0 == 0) goto L_0x0017
            r1 = 0
            java.lang.String r2 = "wbmd_environment_preferences"
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r2, r1)
            if (r0 == 0) goto L_0x0017
            java.lang.String r4 = r0.getString(r4, r5)
            return r4
        L_0x0017:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.environment.utils.SharedPreferencesManager.getString(java.lang.String, java.lang.String):java.lang.String");
    }
}
