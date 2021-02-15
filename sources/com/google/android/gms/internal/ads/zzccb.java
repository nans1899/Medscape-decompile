package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzccb implements zzeoy<zzccc> {
    private final zzeph<zzbwa> zzfdj;
    private final zzeph<zzbzx> zzfhp;

    private zzccb(zzeph<zzbwa> zzeph, zzeph<zzbzx> zzeph2) {
        this.zzfdj = zzeph;
        this.zzfhp = zzeph2;
    }

    public static zzccb zzw(zzeph<zzbwa> zzeph, zzeph<zzbzx> zzeph2) {
        return new zzccb(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzccc(this.zzfdj.get(), this.zzfhp.get());
    }
}
