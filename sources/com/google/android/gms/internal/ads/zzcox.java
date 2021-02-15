package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcox implements zzeoy<zzcpt> {
    private final zzeph<Context> zzesu;

    private zzcox(zzeph<Context> zzeph) {
        this.zzesu = zzeph;
    }

    public static zzcox zzad(zzeph<Context> zzeph) {
        return new zzcox(zzeph);
    }

    public static zzcpt zzcg(Context context) {
        return (zzcpt) zzepe.zza(new zzcpt(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzcg(this.zzesu.get());
    }
}
