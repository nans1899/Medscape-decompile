package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzzr implements Runnable {
    private final /* synthetic */ zzzs zzckw;

    zzzr(zzzs zzzs) {
        this.zzckw = zzzs;
    }

    public final void run() {
        if (this.zzckw.zzbpj != null) {
            try {
                this.zzckw.zzbpj.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzbbq.zzd("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
