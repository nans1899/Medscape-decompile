package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzzp implements Runnable {
    private final /* synthetic */ zzzq zzcku;

    zzzp(zzzq zzzq) {
        this.zzcku = zzzq;
    }

    public final void run() {
        if (this.zzcku.zzckv.zzbpj != null) {
            try {
                this.zzcku.zzckv.zzbpj.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzbbq.zzd("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
