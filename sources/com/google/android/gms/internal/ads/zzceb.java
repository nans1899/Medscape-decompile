package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzceb implements Runnable {
    private final boolean zzeha;
    private final zzcdx zzfzg;

    zzceb(zzcdx zzcdx, boolean z) {
        this.zzfzg = zzcdx;
        this.zzeha = z;
    }

    public final void run() {
        this.zzfzg.zzbj(this.zzeha);
    }
}
