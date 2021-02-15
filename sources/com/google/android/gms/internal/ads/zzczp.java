package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczp implements zzux {
    private zzwo zzgtf;

    public final synchronized void zzb(zzwo zzwo) {
        this.zzgtf = zzwo;
    }

    public final synchronized void onAdClicked() {
        if (this.zzgtf != null) {
            try {
                this.zzgtf.onAdClicked();
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAdClicked.", e);
            }
        }
    }
}
