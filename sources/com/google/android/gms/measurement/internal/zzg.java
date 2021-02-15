package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
abstract class zzg extends zzd {
    private boolean zza;

    zzg(zzfv zzfv) {
        super(zzfv);
        this.zzy.zza(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzy();

    /* access modifiers changed from: protected */
    public void zzz() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zzu() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzv() {
        if (!zzu()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzw() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzy()) {
            this.zzy.zzae();
            this.zza = true;
        }
    }

    public final void zzx() {
        if (!this.zza) {
            zzz();
            this.zzy.zzae();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
