package com.wbmd.omniture.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.medscape.android.updater.UpdateManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bXD¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/wbmd/omniture/utils/SharedPreferencesManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mSettingsPREFS", "", "getSetting", "key", "defaultValue", "saveSetting", "", "value", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SharedPreferencesManager.kt */
public final class SharedPreferencesManager {
    private final Context context;
    private final String mSettingsPREFS = UpdateManager.SETTINGS_PREFS;

    public SharedPreferencesManager(Context context2) {
        this.context = context2;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void saveSetting(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Context context2 = this.context;
        if (context2 != null) {
            SharedPreferences sharedPreferences = context2.getSharedPreferences(this.mSettingsPREFS, 0);
            SharedPreferences.Editor edit = sharedPreferences != null ? sharedPreferences.edit() : null;
            if (edit != null) {
                edit.putString(str, str2);
            }
            if (edit != null) {
                edit.apply();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r0 = r0.getSharedPreferences(r3.mSettingsPREFS, 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getSetting(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            android.content.Context r0 = r3.context
            if (r0 == 0) goto L_0x001d
            java.lang.String r1 = r3.mSettingsPREFS
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            if (r0 == 0) goto L_0x001d
            boolean r1 = r0.contains(r4)
            if (r1 == 0) goto L_0x001d
            java.lang.String r4 = r0.getString(r4, r5)
            return r4
        L_0x001d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.omniture.utils.SharedPreferencesManager.getSetting(java.lang.String, java.lang.String):java.lang.String");
    }
}
