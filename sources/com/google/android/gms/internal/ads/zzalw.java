package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzalw {
    private final Object zzdij = new Object();
    private final Object zzdik = new Object();
    private zzamb zzdil;
    private zzamb zzdim;

    public final zzamb zza(Context context, zzbbx zzbbx) {
        zzamb zzamb;
        synchronized (this.zzdik) {
            if (this.zzdim == null) {
                this.zzdim = new zzamb(zzl(context), zzbbx, zzade.zzdcm.get());
            }
            zzamb = this.zzdim;
        }
        return zzamb;
    }

    public final zzamb zzb(Context context, zzbbx zzbbx) {
        zzamb zzamb;
        synchronized (this.zzdij) {
            if (this.zzdil == null) {
                this.zzdil = new zzamb(zzl(context), zzbbx, (String) zzwm.zzpx().zzd(zzabb.zzcls));
            }
            zzamb = this.zzdil;
        }
        return zzamb;
    }

    private static Context zzl(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }
}
