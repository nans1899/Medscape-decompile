package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqu {
    public final List<? extends zzdzc<? extends zzbqo>> zzfrx;

    public zzbqu(List<? extends zzdzc<? extends zzbqo>> list) {
        this.zzfrx = list;
    }

    public zzbqu(zzbqo zzbqo) {
        this.zzfrx = Collections.singletonList(zzdyq.zzaf(zzbqo));
    }

    public static zzcta<zzbqu> zza(zzcvl<? extends zzbqo> zzcvl) {
        return new zzcsz(zzcvl, zzbqt.zzdwz);
    }

    public static zzcta<zzbqu> zza(zzcta<? extends zzbqo> zzcta) {
        return new zzcsz(zzcta, zzbqw.zzdwz);
    }
}
