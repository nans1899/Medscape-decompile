package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdmb implements zzeoy<zzdlv<zzbnq, zzbnv>> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdpw> zzeuw;
    private final zzeph<zzdqo> zzeux;

    public zzdmb(zzeph<Context> zzeph, zzeph<zzdpw> zzeph2, zzeph<zzdqo> zzeph3) {
        this.zzesu = zzeph;
        this.zzeuw = zzeph2;
        this.zzeux = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return (zzdlv) zzepe.zza(zzdlz.zza(this.zzesu.get(), this.zzeuw.get(), this.zzeux.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
