package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcno implements zzeoy<Set<zzcab<AppEventListener>>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcns> zzggl;
    private final zzcni zzgia;

    private zzcno(zzcni zzcni, zzeph<zzcns> zzeph, zzeph<Executor> zzeph2) {
        this.zzgia = zzcni;
        this.zzggl = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzcno zzf(zzcni zzcni, zzeph<zzcns> zzeph, zzeph<Executor> zzeph2) {
        return new zzcno(zzcni, zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(zzcni.zzb(this.zzggl.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
