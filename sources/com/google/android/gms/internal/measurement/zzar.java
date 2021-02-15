package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzag;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
final class zzar extends zzag.zzb {
    private final /* synthetic */ zzag zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzar(zzag zzag) {
        super(zzag);
        this.zzc = zzag;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zzc.zzm.resetAnalyticsData(this.zza);
    }
}
