package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzafp extends zzgu implements zzafq {
    zzafp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnMediaContentChangedListener");
    }

    public final void zzsu() throws RemoteException {
        zza(2, obtainAndWriteInterfaceToken());
    }
}
