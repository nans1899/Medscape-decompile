package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcop implements zzeoy<zzdzc<String>> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzeg> zzfnq;
    private final zzeph<zzdzb> zzftu;

    private zzcop(zzeph<zzeg> zzeph, zzeph<Context> zzeph2, zzeph<zzdzb> zzeph3) {
        this.zzfnq = zzeph;
        this.zzesu = zzeph2;
        this.zzftu = zzeph3;
    }

    public static zzcop zzp(zzeph<zzeg> zzeph, zzeph<Context> zzeph2, zzeph<zzdzb> zzeph3) {
        return new zzcop(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return (zzdzc) zzepe.zza(this.zzftu.get().zze(new zzcoq(this.zzfnq.get(), this.zzesu.get())), "Cannot return null from a non-@Nullable @Provides method");
    }
}
