package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdab implements zzbui {
    private final zzczs zzgsz;
    private final zzajk zzgtq;

    zzdab(zzczs zzczs, zzajk zzajk) {
        this.zzgsz = zzczs;
        this.zzgtq = zzajk;
    }

    public final void zzk(zzva zzva) {
        zzczs zzczs = this.zzgsz;
        zzajk zzajk = this.zzgtq;
        zzczs.zzk(zzva);
        if (zzajk != null) {
            try {
                zzajk.zzd(zzva);
            } catch (RemoteException e) {
                zzbbq.zze("#007 Could not call remote method.", e);
            }
        }
        if (zzajk != null) {
            try {
                zzajk.onInstreamAdFailedToLoad(zzva.errorCode);
            } catch (RemoteException e2) {
                zzbbq.zze("#007 Could not call remote method.", e2);
            }
        }
    }
}
