package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbwi implements zzeoy<zzbwg> {
    private final zzeph<zzdnv> zzfno;
    private final zzeph<Set<zzcab<zzbwl>>> zzfqq;

    private zzbwi(zzeph<Set<zzcab<zzbwl>>> zzeph, zzeph<zzdnv> zzeph2) {
        this.zzfqq = zzeph;
        this.zzfno = zzeph2;
    }

    public static zzbwi zzu(zzeph<Set<zzcab<zzbwl>>> zzeph, zzeph<zzdnv> zzeph2) {
        return new zzbwi(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzbwg(this.zzfqq.get(), this.zzfno.get());
    }
}
