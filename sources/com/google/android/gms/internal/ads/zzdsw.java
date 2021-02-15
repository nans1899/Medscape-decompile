package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdsw implements Runnable {
    private final String zzdgm;
    private final zzdst zzhlp;

    zzdsw(zzdst zzdst, String str) {
        this.zzhlp = zzdst;
        this.zzdgm = str;
    }

    public final void run() {
        this.zzhlp.zzgz(this.zzdgm);
    }
}
