package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdqo {
    private HashMap<zzdqf, zzdqn<? extends zzbqo>> zzhiv = new HashMap<>();

    public final <AdT extends zzbqo> zzdqn<AdT> zza(zzdqf zzdqf, Context context, zzdpw zzdpw, zzdqv<AdT> zzdqv) {
        zzdqn<AdT> zzdqn = this.zzhiv.get(zzdqf);
        if (zzdqn != null) {
            return zzdqn;
        }
        zzdqc zzdqc = new zzdqc(zzdqg.zza(zzdqf, context));
        zzdqn<AdT> zzdqn2 = new zzdqn<>(zzdqc, new zzdqs(zzdqc, zzdpw, zzdqv));
        this.zzhiv.put(zzdqf, zzdqn2);
        return zzdqn2;
    }
}
