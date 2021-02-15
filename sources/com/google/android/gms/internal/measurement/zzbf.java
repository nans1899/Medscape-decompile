package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzag;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
final class zzbf extends zzag.zzb {
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzt zzd;
    private final /* synthetic */ zzag zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbf(zzag zzag, Bundle bundle, zzt zzt) {
        super(zzag);
        this.zze = zzag;
        this.zzc = bundle;
        this.zzd = zzt;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zze.zzm.performAction(this.zzc, this.zzd, this.zza);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}
