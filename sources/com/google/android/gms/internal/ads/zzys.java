package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzys extends zzgt implements zzyt {
    public zzys() {
        super("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onVideoStart();
        } else if (i == 2) {
            onVideoPlay();
        } else if (i == 3) {
            onVideoPause();
        } else if (i == 4) {
            onVideoEnd();
        } else if (i != 5) {
            return false;
        } else {
            onVideoMute(zzgw.zza(parcel));
        }
        parcel2.writeNoException();
        return true;
    }
}
