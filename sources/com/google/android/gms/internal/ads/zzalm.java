package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzalm extends zzbcn<zzaki> {
    private final Object lock = new Object();
    /* access modifiers changed from: private */
    public zzbad<zzaki> zzdhh;
    private boolean zzdic;
    private int zzdid;

    public zzalm(zzbad<zzaki> zzbad) {
        this.zzdhh = zzbad;
        this.zzdic = false;
        this.zzdid = 0;
    }

    public final zzali zztk() {
        zzali zzali = new zzali(this);
        synchronized (this.lock) {
            zza(new zzall(this, zzali), new zzalo(this, zzali));
            Preconditions.checkState(this.zzdid >= 0);
            this.zzdid++;
        }
        return zzali;
    }

    /* access modifiers changed from: protected */
    public final void zztl() {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzdid > 0);
            zzayp.zzei("Releasing 1 reference for JS Engine");
            this.zzdid--;
            zztn();
        }
    }

    public final void zztm() {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzdid >= 0);
            zzayp.zzei("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzdic = true;
            zztn();
        }
    }

    private final void zztn() {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzdid >= 0);
            if (!this.zzdic || this.zzdid != 0) {
                zzayp.zzei("There are still references to the engine. Not destroying.");
            } else {
                zzayp.zzei("No reference is left (including root). Cleaning up engine.");
                zza(new zzaln(this), new zzbcl());
            }
        }
    }
}
