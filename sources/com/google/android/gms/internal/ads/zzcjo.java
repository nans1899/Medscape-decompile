package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcjo implements zzeoy<zzcta<zzcjg>> {
    private final zzeph<zzcxv<zzcjg, zzdpa, zzcut>> zzfoi;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzcxv<zzcjg, zzdpa, zzcuu>> zzgfe;

    public zzcjo(zzeph<zzcxv<zzcjg, zzdpa, zzcut>> zzeph, zzeph<zzcxv<zzcjg, zzdpa, zzcuu>> zzeph2, zzeph<zzdok> zzeph3) {
        this.zzfoi = zzeph;
        this.zzgfe = zzeph2;
        this.zzfqr = zzeph3;
    }

    public final /* synthetic */ Object get() {
        zzcta zzcta;
        zzeph<zzcxv<zzcjg, zzdpa, zzcut>> zzeph = this.zzfoi;
        zzeph<zzcxv<zzcjg, zzdpa, zzcuu>> zzeph2 = this.zzgfe;
        if (zzcjl.zzgfd[this.zzfqr.get().zzhfh.zzheo - 1] != 1) {
            zzcta = zzeph2.get();
        } else {
            zzcta = zzeph.get();
        }
        return (zzcta) zzepe.zza(zzcta, "Cannot return null from a non-@Nullable @Provides method");
    }
}
