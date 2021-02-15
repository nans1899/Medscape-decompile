package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmj implements zzeoy<zzcmk> {
    private final zzeph<zzcmz> zzetn;
    private final zzeph<zzcms> zzevx;

    private zzcmj(zzeph<zzcms> zzeph, zzeph<zzcmz> zzeph2) {
        this.zzevx = zzeph;
        this.zzetn = zzeph2;
    }

    public static zzcmj zzai(zzeph<zzcms> zzeph, zzeph<zzcmz> zzeph2) {
        return new zzcmj(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzcmk(this.zzevx.get(), this.zzetn.get());
    }
}
