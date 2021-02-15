package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcoe implements Runnable {
    private final String zzdgm;
    private final zzcob zzgix;

    zzcoe(zzcob zzcob, String str) {
        this.zzgix = zzcob;
        this.zzdgm = str;
    }

    public final void run() {
        zzcob zzcob = this.zzgix;
        zzcob.zzgis.zzgl(this.zzdgm);
    }
}
