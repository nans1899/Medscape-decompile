package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcqf implements zzeoy<zzcqg> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzawo> zzfmk;

    private zzcqf(zzeph<Context> zzeph, zzeph<zzawo> zzeph2) {
        this.zzesu = zzeph;
        this.zzfmk = zzeph2;
    }

    public static zzcqf zzar(zzeph<Context> zzeph, zzeph<zzawo> zzeph2) {
        return new zzcqf(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzcqg(this.zzesu.get(), this.zzfmk.get());
    }
}
