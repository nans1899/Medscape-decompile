package com.webmd.wbmdlanguages.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0011J\u0006\u0010\u0017\u001a\u00020\u0000R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/webmd/wbmdlanguages/utils/Settings;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "sInstance", "getSInstance", "()Lcom/webmd/wbmdlanguages/utils/Settings;", "setSInstance", "(Lcom/webmd/wbmdlanguages/utils/Settings;)V", "getEditor", "Landroid/content/SharedPreferences$Editor;", "getSetting", "", "key", "defaultValue", "saveSetting", "", "value", "singleton", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Settings.kt */
public final class Settings {
    private Context mContext;
    private Settings sInstance;

    public Settings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
    }

    public final Settings getSInstance() {
        return this.sInstance;
    }

    public final void setSInstance(Settings settings) {
        this.sInstance = settings;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    public final SharedPreferences.Editor getEditor() {
        Context context = this.mContext;
        if (context != null) {
            return PreferenceManager.getDefaultSharedPreferences(context).edit();
        }
        return null;
    }

    public final Settings singleton() {
        if (this.sInstance == null) {
            this.sInstance = new Settings(this.mContext);
        }
        Settings settings = this.sInstance;
        if (settings != null) {
            return settings;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.wbmdlanguages.utils.Settings");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0029, code lost:
        r3 = r0.getString(r3, r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getSetting(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "defaultValue"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r3 = "txt"
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            android.content.Context r0 = r2.mContext
            if (r0 == 0) goto L_0x0030
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            boolean r1 = r0.contains(r3)
            if (r1 == 0) goto L_0x0030
            java.lang.String r3 = r0.getString(r3, r4)
            if (r3 == 0) goto L_0x0030
            r4 = r3
        L_0x0030:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdlanguages.utils.Settings.getSetting(java.lang.String, java.lang.String):java.lang.String");
    }

    public final boolean saveSetting(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        String str3 = str + "txt";
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(str3, str2);
        return edit.commit();
    }
}
