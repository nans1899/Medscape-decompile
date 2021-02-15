package com.google.android.gms.internal.icing;

import android.content.SharedPreferences;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final /* synthetic */ class zzbw implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final zzbt zzdu;

    zzbw(zzbt zzbt) {
        this.zzdu = zzbt;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zzdu.zza(sharedPreferences, str);
    }
}
