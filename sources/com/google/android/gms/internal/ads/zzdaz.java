package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdaz implements zzdyr<zzdbm> {
    private final /* synthetic */ zzaxg zzgum;
    private final /* synthetic */ zzdaq zzgun;

    zzdaz(zzdaq zzdaq, zzaxg zzaxg) {
        this.zzgun = zzdaq;
        this.zzgum = zzaxg;
    }

    public final void zzb(Throwable th) {
        try {
            zzaxg zzaxg = this.zzgum;
            String valueOf = String.valueOf(th.getMessage());
            zzaxg.onError(valueOf.length() != 0 ? "Internal error. ".concat(valueOf) : new String("Internal error. "));
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzdbm zzdbm = (zzdbm) obj;
        try {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcxp)).booleanValue()) {
                if (this.zzgun.zzbot.zzeem >= ((Integer) zzwm.zzpx().zzd(zzabb.zzcxr)).intValue()) {
                    if (zzdbm == null) {
                        this.zzgum.zza((String) null, (String) null, (Bundle) null);
                        return;
                    } else {
                        this.zzgum.zza(zzdbm.zzguv, zzdbm.zzguw, zzdbm.zzgux);
                        return;
                    }
                }
            }
            if (zzdbm == null) {
                this.zzgum.zzk((String) null, (String) null);
            } else {
                this.zzgum.zzk(zzdbm.zzguv, zzdbm.zzguw);
            }
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
