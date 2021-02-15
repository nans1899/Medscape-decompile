package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcrh implements zzdyr<ParcelFileDescriptor> {
    private final /* synthetic */ zzasw zzgli;

    zzcrh(zzcqv zzcqv, zzasw zzasw) {
        this.zzgli = zzasw;
    }

    public final void zzb(Throwable th) {
        try {
            this.zzgli.zza(zzazy.zzc(th));
        } catch (RemoteException e) {
            zzayp.zza("Service can't call client", e);
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        try {
            this.zzgli.zzb((ParcelFileDescriptor) obj);
        } catch (RemoteException e) {
            zzayp.zza("Service can't call client", e);
        }
    }
}
