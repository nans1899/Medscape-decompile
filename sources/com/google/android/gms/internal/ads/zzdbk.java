package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdbk implements zzdya {
    private final zzatc zzfvc;

    zzdbk(zzatc zzatc) {
        this.zzfvc = zzatc;
    }

    public final zzdzc zzf(Object obj) {
        return zzdyq.zzaf(new zzdbm(new JsonReader(new InputStreamReader((InputStream) obj))).zzo(this.zzfvc.zzdty));
    }
}
