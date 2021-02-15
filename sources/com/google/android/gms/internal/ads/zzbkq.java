package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbkq implements zzdnm {
    private final /* synthetic */ zzbjv zzfbq;
    private Context zzfib;
    private String zzfic;

    private zzbkq(zzbjv zzbjv) {
        this.zzfbq = zzbjv;
    }

    public final zzdnn zzagi() {
        zzepe.zza(this.zzfib, Context.class);
        return new zzbkt(this.zzfbq, this.zzfib, this.zzfic);
    }

    public final /* synthetic */ zzdnm zzfw(String str) {
        this.zzfic = str;
        return this;
    }

    public final /* synthetic */ zzdnm zzbz(Context context) {
        this.zzfib = (Context) zzepe.checkNotNull(context);
        return this;
    }
}
