package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcou implements zzeoy<ApplicationInfo> {
    private final zzeph<Context> zzesu;

    private zzcou(zzeph<Context> zzeph) {
        this.zzesu = zzeph;
    }

    public static zzcou zzac(zzeph<Context> zzeph) {
        return new zzcou(zzeph);
    }

    public static ApplicationInfo zzcf(Context context) {
        return (ApplicationInfo) zzepe.zza(context.getApplicationInfo(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzcf(this.zzesu.get());
    }
}
