package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpm implements zzeoy<zzdpj> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzayi> zzevj;

    private zzdpm(zzeph<Context> zzeph, zzeph<zzayi> zzeph2) {
        this.zzesu = zzeph;
        this.zzevj = zzeph2;
    }

    public static zzdpm zzbi(zzeph<Context> zzeph, zzeph<zzayi> zzeph2) {
        return new zzdpm(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzdpj(this.zzesu.get(), this.zzevj.get());
    }
}
