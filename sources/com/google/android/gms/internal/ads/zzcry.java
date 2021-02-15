package com.google.android.gms.internal.ads;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcry implements zzdyr<SQLiteDatabase> {
    private final /* synthetic */ zzdrp zzglz;

    zzcry(zzcrw zzcrw, zzdrp zzdrp) {
        this.zzglz = zzdrp;
    }

    public final void zzb(Throwable th) {
        String valueOf = String.valueOf(th.getMessage());
        zzayp.zzfc(valueOf.length() != 0 ? "Failed to get offline signal database: ".concat(valueOf) : new String("Failed to get offline signal database: "));
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        try {
            this.zzglz.apply((SQLiteDatabase) obj);
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzayp.zzfc(valueOf.length() != 0 ? "Error executing function on offline signal database: ".concat(valueOf) : new String("Error executing function on offline signal database: "));
        }
    }
}
