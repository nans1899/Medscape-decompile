package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzzx implements Runnable {
    private final zzavg zzckz;

    zzzx(zzavg zzavg) {
        this.zzckz = zzavg;
    }

    public final void run() {
        zzavg zzavg = this.zzckz;
        if (zzavg != null) {
            try {
                zzavg.onRewardedAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzbbq.zze("#007 Could not call remote method.", e);
            }
        }
    }
}
