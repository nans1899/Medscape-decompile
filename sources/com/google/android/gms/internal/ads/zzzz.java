package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzzz implements Runnable {
    private final /* synthetic */ zzaaa zzcla;

    zzzz(zzaaa zzaaa) {
        this.zzcla = zzaaa;
    }

    public final void run() {
        if (this.zzcla.zzclb != null) {
            try {
                this.zzcla.zzclb.onRewardedVideoAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzbbq.zzd("Could not notify onRewardedVideoAdFailedToLoad event.", e);
            }
        }
    }
}
