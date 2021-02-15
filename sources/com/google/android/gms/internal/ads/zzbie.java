package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzp;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbie {
    public static zzbgj zza(Context context, zzbhy zzbhy, String str, boolean z, boolean z2, zzeg zzeg, zzacb zzacb, zzbbx zzbbx, zzabs zzabs, zzk zzk, zzb zzb, zzts zzts, zzsu zzsu, boolean z3, zzdnv zzdnv, zzdnw zzdnw) throws zzbgv {
        try {
            return (zzbgj) zzbay.zza(new zzbih(context, zzbhy, str, z, z2, zzeg, zzacb, zzbbx, (zzabs) null, zzk, zzb, zzts, zzsu, z3, zzdnv, zzdnw));
        } catch (Throwable th) {
            zzp.zzkv().zza(th, "AdWebViewFactory.newAdWebView2");
            throw new zzbgv("Webview initialization failed.", th);
        }
    }
}
