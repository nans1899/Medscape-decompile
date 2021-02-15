package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzf implements zzeoy<Set<zzcab<AdMetadataListener>>> {
    private final zzbys zzfwg;

    private zzbzf(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzf zzr(zzbys zzbys) {
        return new zzbzf(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzakj(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
