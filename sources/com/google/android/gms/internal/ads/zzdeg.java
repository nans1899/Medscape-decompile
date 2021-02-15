package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdeg implements zzeoy<zzdec> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzfth;

    private zzdeg(zzeph<Context> zzeph, zzeph<zzdzb> zzeph2) {
        this.zzfth = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzdeg zzaz(zzeph<Context> zzeph, zzeph<zzdzb> zzeph2) {
        return new zzdeg(zzeph, zzeph2);
    }

    public static zzdec zza(Context context, zzdzb zzdzb) {
        return new zzdec(context, zzdzb);
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfth.get(), this.zzesr.get());
    }
}
