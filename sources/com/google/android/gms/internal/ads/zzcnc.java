package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcnc implements Runnable {
    private final String zzdgm;
    private final zzcmz zzghs;

    zzcnc(zzcmz zzcmz, String str) {
        this.zzghs = zzcmz;
        this.zzdgm = str;
    }

    public final void run() {
        this.zzghs.zzgi(this.zzdgm);
    }
}
