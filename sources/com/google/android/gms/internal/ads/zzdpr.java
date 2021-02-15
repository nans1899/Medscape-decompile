package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpr implements zzeoy<zzdpn> {
    private final zzeph<zzdpl> zzfoh;
    private final zzdpp zzhgo;
    private final zzeph<String> zzhgp;

    private zzdpr(zzdpp zzdpp, zzeph<zzdpl> zzeph, zzeph<String> zzeph2) {
        this.zzhgo = zzdpp;
        this.zzfoh = zzeph;
        this.zzhgp = zzeph2;
    }

    public static zzdpr zza(zzdpp zzdpp, zzeph<zzdpl> zzeph, zzeph<String> zzeph2) {
        return new zzdpr(zzdpp, zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzdpn) zzepe.zza(this.zzfoh.get().zzgv(this.zzhgp.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
