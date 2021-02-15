package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzdq extends zzdn<zzdx.zzc> {
    zzdq() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zze(zzfh zzfh) {
        return zzfh instanceof zzdx.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zzds<zzdx.zzc> zzd(Object obj) {
        return ((zzdx.zzd) obj).zzkj;
    }

    /* access modifiers changed from: package-private */
    public final zzds<zzdx.zzc> zze(Object obj) {
        zzdx.zzd zzd = (zzdx.zzd) obj;
        if (zzd.zzkj.isImmutable()) {
            zzd.zzkj = (zzds) zzd.zzkj.clone();
        }
        return zzd.zzkj;
    }

    /* access modifiers changed from: package-private */
    public final void zzf(Object obj) {
        zzd(obj).zzai();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Map.Entry<?, ?> entry) {
        zzdx.zzc zzc = (zzdx.zzc) entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzhg zzhg, Map.Entry<?, ?> entry) throws IOException {
        zzdx.zzc zzc = (zzdx.zzc) entry.getKey();
        throw new NoSuchMethodError();
    }
}
