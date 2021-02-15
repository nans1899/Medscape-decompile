package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzk;
import java.util.concurrent.Executor;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbgr {
    public static zzdzc<zzbgj> zza(Context context, zzbbx zzbbx, String str, zzeg zzeg, zzb zzb) {
        return zzdyq.zzb(zzdyq.zzaf(null), new zzbgq(context, zzeg, zzbbx, zzb, str), (Executor) zzbbz.zzeet);
    }

    public static zzbgj zza(Context context, zzbhy zzbhy, String str, boolean z, boolean z2, zzeg zzeg, zzacb zzacb, zzbbx zzbbx, zzabs zzabs, zzk zzk, zzb zzb, zzts zzts, zzsu zzsu, boolean z3, zzdnv zzdnv, zzdnw zzdnw) throws zzbgv {
        zzabb.initialize(context);
        if (zzadh.zzdct.get().booleanValue()) {
            return zzbie.zza(context, zzbhy, str, z, z2, zzeg, zzacb, zzbbx, (zzabs) null, zzk, zzb, zzts, zzsu, z3, zzdnv, zzdnw);
        }
        try {
            return (zzbgj) zzbay.zza(new zzbgt(context, zzbhy, str, z, z2, zzeg, zzacb, zzbbx, (zzabs) null, zzk, zzb, zzts, zzsu, z3, zzdnv, zzdnw));
        } catch (Throwable th) {
            throw new zzbgv("Webview initialization failed.", th);
        }
    }
}
