package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcx implements zzeoy<zzdcv> {
    private final zzeph<zzbsa> zzevm;
    private final zzeph<zzdpj> zzewx;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<String> zzgvp;
    private final zzeph<String> zzgvz;

    private zzdcx(zzeph<String> zzeph, zzeph<String> zzeph2, zzeph<zzbsa> zzeph3, zzeph<zzdpj> zzeph4, zzeph<zzdok> zzeph5) {
        this.zzgvp = zzeph;
        this.zzgvz = zzeph2;
        this.zzevm = zzeph3;
        this.zzewx = zzeph4;
        this.zzfqr = zzeph5;
    }

    public static zzdcx zzg(zzeph<String> zzeph, zzeph<String> zzeph2, zzeph<zzbsa> zzeph3, zzeph<zzdpj> zzeph4, zzeph<zzdok> zzeph5) {
        return new zzdcx(zzeph, zzeph2, zzeph3, zzeph4, zzeph5);
    }

    public final /* synthetic */ Object get() {
        return new zzdcv(this.zzgvp.get(), this.zzgvz.get(), this.zzevm.get(), this.zzewx.get(), this.zzfqr.get());
    }
}
