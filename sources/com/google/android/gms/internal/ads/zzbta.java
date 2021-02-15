package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbta implements zzeoy<zzbsx> {
    private final zzeph<zzcnu> zzetp;
    private final zzeph<zzayr> zzevu;
    private final zzeph<zzbbx> zzfov;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<Context> zzfth;

    private zzbta(zzeph<Context> zzeph, zzeph<zzdok> zzeph2, zzeph<zzbbx> zzeph3, zzeph<zzayr> zzeph4, zzeph<zzcnu> zzeph5) {
        this.zzfth = zzeph;
        this.zzfqr = zzeph2;
        this.zzfov = zzeph3;
        this.zzevu = zzeph4;
        this.zzetp = zzeph5;
    }

    public static zzbta zzb(zzeph<Context> zzeph, zzeph<zzdok> zzeph2, zzeph<zzbbx> zzeph3, zzeph<zzayr> zzeph4, zzeph<zzcnu> zzeph5) {
        return new zzbta(zzeph, zzeph2, zzeph3, zzeph4, zzeph5);
    }

    public final /* synthetic */ Object get() {
        return new zzbsx(this.zzfth.get(), this.zzfqr.get(), this.zzfov.get(), this.zzevu.get(), this.zzetp.get());
    }
}
