package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzaoa implements InitializationCompleteCallback {
    private final /* synthetic */ zzais zzdlo;

    zzaoa(zzanx zzanx, zzais zzais) {
        this.zzdlo = zzais;
    }

    public final void onInitializationSucceeded() {
        try {
            this.zzdlo.onInitializationSucceeded();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void onInitializationFailed(String str) {
        try {
            this.zzdlo.onInitializationFailed(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
