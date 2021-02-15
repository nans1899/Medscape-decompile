package com.google.android.gms.internal.location;

import android.os.DeadObjectException;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzk implements zzbi<zzal> {
    private final /* synthetic */ zzh zza;

    zzk(zzh zzh) {
        this.zza = zzh;
    }

    public final void zza() {
        this.zza.checkConnected();
    }

    public final /* synthetic */ IInterface zzb() throws DeadObjectException {
        return (zzal) this.zza.getService();
    }
}
