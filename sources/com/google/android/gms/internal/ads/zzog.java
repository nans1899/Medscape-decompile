package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzog {
    private zzof zzbhv;

    public abstract zzoi zza(zzhw[] zzhwArr, zznp zznp) throws zzhd;

    public abstract void zzd(Object obj);

    public final void zza(zzof zzof) {
        this.zzbhv = zzof;
    }

    /* access modifiers changed from: protected */
    public final void invalidate() {
        zzof zzof = this.zzbhv;
        if (zzof != null) {
            zzof.zzes();
        }
    }
}
