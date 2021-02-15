package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzail implements Runnable {
    private final /* synthetic */ zzaik zzdfy;

    zzail(zzaik zzaik) {
        this.zzdfy = zzaik;
    }

    public final void run() {
        this.zzdfy.disconnect();
    }
}
