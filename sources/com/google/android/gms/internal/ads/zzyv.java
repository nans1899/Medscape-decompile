package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzyv extends zzgu implements zzyt {
    zzyv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }

    public final void onVideoStart() throws RemoteException {
        zza(1, obtainAndWriteInterfaceToken());
    }

    public final void onVideoPlay() throws RemoteException {
        zza(2, obtainAndWriteInterfaceToken());
    }

    public final void onVideoPause() throws RemoteException {
        zza(3, obtainAndWriteInterfaceToken());
    }

    public final void onVideoEnd() throws RemoteException {
        zza(4, obtainAndWriteInterfaceToken());
    }

    public final void onVideoMute(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.writeBoolean(obtainAndWriteInterfaceToken, z);
        zza(5, obtainAndWriteInterfaceToken);
    }
}
