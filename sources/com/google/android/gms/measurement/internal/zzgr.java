package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
abstract class zzgr extends zzgo {
    private boolean zza;

    zzgr(zzfv zzfv) {
        super(zzfv);
        this.zzy.zza(this);
    }

    /* access modifiers changed from: protected */
    public void g_() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzd();

    /* access modifiers changed from: package-private */
    public final boolean zzz() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzaa() {
        if (!zzz()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzab() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzd()) {
            this.zzy.zzae();
            this.zza = true;
        }
    }

    public final void zzac() {
        if (!this.zza) {
            g_();
            this.zzy.zzae();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
