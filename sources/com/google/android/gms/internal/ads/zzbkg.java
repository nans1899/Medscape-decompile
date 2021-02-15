package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbkg implements zzdjx {
    private final /* synthetic */ zzbjv zzfbq;
    private Context zzfib;
    private String zzfic;

    private zzbkg(zzbjv zzbjv) {
        this.zzfbq = zzbjv;
    }

    public final zzdju zzafu() {
        zzepe.zza(this.zzfib, Context.class);
        zzepe.zza(this.zzfic, String.class);
        return new zzbkj(this.zzfbq, this.zzfib, this.zzfic);
    }

    public final /* synthetic */ zzdjx zzfv(String str) {
        this.zzfic = (String) zzepe.checkNotNull(str);
        return this;
    }

    public final /* synthetic */ zzdjx zzby(Context context) {
        this.zzfib = (Context) zzepe.checkNotNull(context);
        return this;
    }
}
