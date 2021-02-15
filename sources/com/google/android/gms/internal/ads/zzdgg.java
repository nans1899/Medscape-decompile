package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgg implements zzeoy<zzdge> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzawo> zzfmk;

    private zzdgg(zzeph<zzawo> zzeph, zzeph<zzdzb> zzeph2, zzeph<Context> zzeph3) {
        this.zzfmk = zzeph;
        this.zzesr = zzeph2;
        this.zzesu = zzeph3;
    }

    public static zzdgg zzv(zzeph<zzawo> zzeph, zzeph<zzdzb> zzeph2, zzeph<Context> zzeph3) {
        return new zzdgg(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzdge(this.zzfmk.get(), this.zzesr.get(), this.zzesu.get());
    }
}
