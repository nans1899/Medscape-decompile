package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdeo implements zzeoy<zzdem> {
    private final zzeph<zzdzb> zzesr;

    private zzdeo(zzeph<zzdzb> zzeph) {
        this.zzesr = zzeph;
    }

    public static zzdeo zzan(zzeph<zzdzb> zzeph) {
        return new zzdeo(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzdem(this.zzesr.get());
    }
}
