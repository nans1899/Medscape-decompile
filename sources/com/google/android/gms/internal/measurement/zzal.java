package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzag;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
final class zzal extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Bundle zze;
    private final /* synthetic */ zzag zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzal(zzag zzag, String str, String str2, Bundle bundle) {
        super(zzag);
        this.zzf = zzag;
        this.zzc = str;
        this.zzd = str2;
        this.zze = bundle;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zzf.zzm.clearConditionalUserProperty(this.zzc, this.zzd, this.zze);
    }
}
