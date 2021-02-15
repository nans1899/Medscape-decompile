package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.FullScreenContentCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzsd extends zzsp {
    private final FullScreenContentCallback zzbuq;

    public zzsd(FullScreenContentCallback fullScreenContentCallback) {
        this.zzbuq = fullScreenContentCallback;
    }

    public final void onAdShowedFullScreenContent() throws RemoteException {
        this.zzbuq.onAdShowedFullScreenContent();
    }

    public final void onAdDismissedFullScreenContent() throws RemoteException {
        this.zzbuq.onAdDismissedFullScreenContent();
    }

    public final void zzb(zzva zzva) throws RemoteException {
        this.zzbuq.onAdFailedToShowFullScreenContent(zzva.zzph());
    }
}
