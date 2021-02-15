package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdja implements zzeoy<ApplicationInfo> {
    private final zzdiy zzgzp;

    public zzdja(zzdiy zzdiy) {
        this.zzgzp = zzdiy;
    }

    public static ApplicationInfo zzb(zzdiy zzdiy) {
        return (ApplicationInfo) zzepe.zza(zzdiy.zzask(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzb(this.zzgzp);
    }
}
