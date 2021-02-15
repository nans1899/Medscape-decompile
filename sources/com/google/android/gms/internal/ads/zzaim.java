package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaim extends zzaif {
    private final /* synthetic */ zzbcg zzbvs;

    zzaim(zzaij zzaij, zzbcg zzbcg) {
        this.zzbvs = zzbcg;
    }

    public final void zza(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        this.zzbvs.set(parcelFileDescriptor);
    }
}
