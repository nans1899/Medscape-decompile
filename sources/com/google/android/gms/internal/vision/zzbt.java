package com.google.android.gms.internal.vision;

import android.content.SharedPreferences;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final /* synthetic */ class zzbt implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final zzbq zzhg;

    zzbt(zzbq zzbq) {
        this.zzhg = zzbq;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zzhg.zza(sharedPreferences, str);
    }
}
