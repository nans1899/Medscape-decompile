package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzald implements zzbci {
    private final /* synthetic */ zzakr zzdht;
    private final /* synthetic */ zzalm zzdhv;

    zzald(zzakr zzakr, zzalm zzalm) {
        this.zzdht = zzakr;
        this.zzdhv = zzalm;
    }

    public final void run() {
        synchronized (this.zzdht.lock) {
            int unused = this.zzdht.status = 1;
            zzayp.zzei("Failed loading new engine. Marking new engine destroyable.");
            this.zzdhv.zztm();
        }
    }
}
