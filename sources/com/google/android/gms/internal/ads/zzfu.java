package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzfu implements Callable {
    private final zzcf.zza.C0009zza zzabc;
    private final zzex zzwg;

    public zzfu(zzex zzex, zzcf.zza.C0009zza zza) {
        this.zzwg = zzex;
        this.zzabc = zza;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzcy */
    public final Void call() throws Exception {
        if (this.zzwg.zzcp() != null) {
            this.zzwg.zzcp().get();
        }
        zzcf.zza zzco = this.zzwg.zzco();
        if (zzco == null) {
            return null;
        }
        try {
            synchronized (this.zzabc) {
                zzcf.zza.C0009zza zza = this.zzabc;
                byte[] byteArray = zzco.toByteArray();
                zza.zza(byteArray, 0, byteArray.length, zzejm.zzbfu());
            }
            return null;
        } catch (zzekj unused) {
            return null;
        }
    }
}
