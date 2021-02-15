package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzag;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
final class zzba extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzt zzf;
    private final /* synthetic */ zzag zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzba(zzag zzag, String str, String str2, boolean z, zzt zzt) {
        super(zzag);
        this.zzg = zzag;
        this.zzc = str;
        this.zzd = str2;
        this.zze = z;
        this.zzf = zzt;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zzg.zzm.getUserProperties(this.zzc, this.zzd, this.zze, this.zzf);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzf.zza((Bundle) null);
    }
}
