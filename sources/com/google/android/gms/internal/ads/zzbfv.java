package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbfv extends zzbfl {
    public zzbfv(zzbdu zzbdu) {
        super(zzbdu);
    }

    public final void abort() {
    }

    public final boolean zzfn(String str) {
        zzbdu zzbdu = (zzbdu) this.zzels.get();
        if (zzbdu != null) {
            zzbdu.zza(zzfo(str), (zzbfl) this);
        }
        zzayp.zzfe("VideoStreamNoopCache is doing nothing.");
        zza(str, zzfo(str), "noop", "Noop cache is a noop.");
        return false;
    }
}
