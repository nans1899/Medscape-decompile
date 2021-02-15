package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcrt implements zzeoy<zzcru> {
    private final zzeph<Context> zzesu;

    private zzcrt(zzeph<Context> zzeph) {
        this.zzesu = zzeph;
    }

    public static zzcrt zzaf(zzeph<Context> zzeph) {
        return new zzcrt(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzcru(this.zzesu.get());
    }
}
