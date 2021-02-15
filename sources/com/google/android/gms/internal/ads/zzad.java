package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzad implements Runnable {
    private final /* synthetic */ String zzbd;
    private final /* synthetic */ long zzbe;
    private final /* synthetic */ zzaa zzbf;

    zzad(zzaa zzaa, String str, long j) {
        this.zzbf = zzaa;
        this.zzbd = str;
        this.zzbe = j;
    }

    public final void run() {
        this.zzbf.zzap.zza(this.zzbd, this.zzbe);
        this.zzbf.zzap.zzd(this.zzbf.toString());
    }
}
