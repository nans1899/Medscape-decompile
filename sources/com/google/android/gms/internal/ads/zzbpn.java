package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpn implements zzeoy<zzcta<zzboq>> {
    private final zzeph<zzcxv<zzboq, zzdpa, zzcuu>> zzezl;
    private final zzeph<Boolean> zzfjp;
    private final zzeph<zzcwd> zzfjr;

    public zzbpn(zzeph<Boolean> zzeph, zzeph<zzcwd> zzeph2, zzeph<zzcxv<zzboq, zzdpa, zzcuu>> zzeph3) {
        this.zzfjp = zzeph;
        this.zzfjr = zzeph2;
        this.zzezl = zzeph3;
    }

    public final /* synthetic */ Object get() {
        boolean booleanValue = this.zzfjp.get().booleanValue();
        zzcta zzcta = (zzcwd) this.zzfjr.get();
        zzcta zzcta2 = (zzcxv) this.zzezl.get();
        if (!booleanValue) {
            zzcta = zzcta2;
        }
        return (zzcta) zzepe.zza(zzcta, "Cannot return null from a non-@Nullable @Provides method");
    }
}
