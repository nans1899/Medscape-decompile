package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzali extends zzbcn<zzalp> {
    private final Object lock = new Object();
    /* access modifiers changed from: private */
    public final zzalm zzdhy;
    private boolean zzdhz;

    public zzali(zzalm zzalm) {
        this.zzdhy = zzalm;
    }

    public final void release() {
        synchronized (this.lock) {
            if (!this.zzdhz) {
                this.zzdhz = true;
                zza(new zzalh(this), new zzbcl());
                zza(new zzalk(this), new zzalj(this));
            }
        }
    }
}
