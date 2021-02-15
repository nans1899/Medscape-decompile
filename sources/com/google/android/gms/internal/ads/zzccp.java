package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzccp implements zzeoy<zzccq> {
    private final zzeph<zzbvb> zzfts;

    private zzccp(zzeph<zzbvb> zzeph) {
        this.zzfts = zzeph;
    }

    public static zzccp zzx(zzeph<zzbvb> zzeph) {
        return new zzccp(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzccq(this.zzfts.get());
    }
}
