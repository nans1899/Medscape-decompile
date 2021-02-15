package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzale implements zzbck<zzaki> {
    private final /* synthetic */ zzakr zzdht;
    private final /* synthetic */ zzalm zzdhv;

    zzale(zzakr zzakr, zzalm zzalm) {
        this.zzdht = zzakr;
        this.zzdhv = zzalm;
    }

    public final /* synthetic */ void zzh(Object obj) {
        zzaki zzaki = (zzaki) obj;
        synchronized (this.zzdht.lock) {
            int unused = this.zzdht.status = 0;
            if (!(this.zzdht.zzdhi == null || this.zzdhv == this.zzdht.zzdhi)) {
                zzayp.zzei("New JS engine is loaded, marking previous one as destroyable.");
                this.zzdht.zzdhi.zztm();
            }
            zzalm unused2 = this.zzdht.zzdhi = this.zzdhv;
        }
    }
}
