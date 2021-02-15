package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsx implements zzeoy<zzcsy> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzcsp> zzggy;
    private final zzeph<zzbbu> zzgno;

    public zzcsx(zzeph<Context> zzeph, zzeph<zzcsp> zzeph2, zzeph<zzbbu> zzeph3) {
        this.zzesu = zzeph;
        this.zzggy = zzeph2;
        this.zzgno = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return new zzcsy(this.zzesu.get(), this.zzggy.get(), this.zzgno.get());
    }
}
