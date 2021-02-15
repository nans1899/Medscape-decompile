package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzamb {
    private static final zzbad<zzaki> zzdiq = new zzame();
    private static final zzbad<zzaki> zzdir = new zzamd();
    private final zzakr zzdis;

    public zzamb(Context context, zzbbx zzbbx, String str) {
        this.zzdis = new zzakr(context, zzbbx, str, zzdiq, zzdir);
    }

    public final <I, O> zzalt<I, O> zza(String str, zzaly<I> zzaly, zzalv<O> zzalv) {
        return new zzamg(this.zzdis, str, zzaly, zzalv);
    }

    public final zzamk zztp() {
        return new zzamk(this.zzdis);
    }
}
