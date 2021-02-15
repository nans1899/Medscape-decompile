package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcoy implements zzeoy<String> {
    private final zzeph<Context> zzesu;

    private zzcoy(zzeph<Context> zzeph) {
        this.zzesu = zzeph;
    }

    public static zzcoy zzae(zzeph<Context> zzeph) {
        return new zzcoy(zzeph);
    }

    public static String zzch(Context context) {
        return (String) zzepe.zza(context.getPackageName(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzch(this.zzesu.get());
    }
}
