package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaij implements zzdya<zzaii, ParcelFileDescriptor> {
    private final /* synthetic */ zzaic zzdfw;

    zzaij(zzaik zzaik, zzaic zzaic) {
        this.zzdfw = zzaic;
    }

    public final /* synthetic */ zzdzc zzf(Object obj) throws Exception {
        zzbcg zzbcg = new zzbcg();
        ((zzaii) obj).zza(this.zzdfw, new zzaim(this, zzbcg));
        return zzbcg;
    }
}
