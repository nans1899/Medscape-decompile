package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzag;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
final class zzas extends zzag.zzb {
    private final /* synthetic */ long zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzas(zzag zzag, long j) {
        super(zzag);
        this.zzd = zzag;
        this.zzc = j;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zzd.zzm.setSessionTimeoutDuration(this.zzc);
    }
}
