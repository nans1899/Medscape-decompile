package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzbs {
    private final boolean zzhf;

    public zzbs(zzbv zzbv) {
        zzde.checkNotNull(zzbv, "BuildInfo must be non-null");
        this.zzhf = !zzbv.zzai();
    }

    public final boolean zzg(String str) {
        zzde.checkNotNull(str, "flagName must not be null");
        if (!this.zzhf) {
            return true;
        }
        return zzbu.zzhh.get().containsValue(str);
    }
}
