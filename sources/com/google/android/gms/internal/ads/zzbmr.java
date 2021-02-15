package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmr implements zzeoy<zzbms> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzqo> zzfnv;

    private zzbmr(zzeph<Context> zzeph, zzeph<zzqo> zzeph2) {
        this.zzesu = zzeph;
        this.zzfnv = zzeph2;
    }

    public static zzbmr zza(zzeph<Context> zzeph, zzeph<zzqo> zzeph2) {
        return new zzbmr(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzbms(this.zzesu.get(), this.zzfnv.get());
    }
}
