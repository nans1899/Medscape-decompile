package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzaom implements Runnable {
    private final /* synthetic */ zzaoi zzdmb;

    zzaom(zzaoi zzaoi) {
        this.zzdmb = zzaoi;
    }

    public final void run() {
        try {
            this.zzdmb.zzdlq.onAdClosed();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }
}
