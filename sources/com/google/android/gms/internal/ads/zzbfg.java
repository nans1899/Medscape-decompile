package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbfg extends zzayl {
    final zzbdu zzegh;
    private final String zzegt;
    private final String[] zzegu;
    final zzbfl zzelk;

    zzbfg(zzbdu zzbdu, zzbfl zzbfl, String str, String[] strArr) {
        this.zzegh = zzbdu;
        this.zzelk = zzbfl;
        this.zzegt = str;
        this.zzegu = strArr;
        zzp.zzln().zza(this);
    }

    public final void zzvb() {
        try {
            this.zzelk.zze(this.zzegt, this.zzegu);
        } finally {
            zzayu.zzeba.post(new zzbfj(this));
        }
    }
}
