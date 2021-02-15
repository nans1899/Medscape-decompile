package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzk implements zzeoy<Set<zzcab<AppEventListener>>> {
    private final zzbys zzfwg;

    private zzbzk(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzk zzx(zzbys zzbys) {
        return new zzbzk(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzakk(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
