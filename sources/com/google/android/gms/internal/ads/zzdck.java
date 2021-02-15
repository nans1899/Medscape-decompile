package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdck implements zzeoy<zzdci> {
    private final zzeph<zzcku> zzeso;
    private final zzeph<zzdzb> zzgvb;
    private final zzeph<String> zzgvp;

    private zzdck(zzeph<String> zzeph, zzeph<zzdzb> zzeph2, zzeph<zzcku> zzeph3) {
        this.zzgvp = zzeph;
        this.zzgvb = zzeph2;
        this.zzeso = zzeph3;
    }

    public static zzdck zzr(zzeph<String> zzeph, zzeph<zzdzb> zzeph2, zzeph<zzcku> zzeph3) {
        return new zzdck(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzdci(this.zzgvp.get(), this.zzgvb.get(), this.zzeso.get());
    }
}
