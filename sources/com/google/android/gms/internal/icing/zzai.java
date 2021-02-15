package com.google.android.gms.internal.icing;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.icing.zzaj;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzai extends zzaj.zzd<Status> {
    private final /* synthetic */ zzw[] zzat;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzai(zzaj zzaj, GoogleApiClient googleApiClient, zzw[] zzwArr) {
        super(googleApiClient);
        this.zzat = zzwArr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaa zzaa) throws RemoteException {
        zzaa.zza(new zzaj.zzc(this), (String) null, this.zzat);
    }
}
