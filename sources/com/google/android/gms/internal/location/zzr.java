package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzr extends zzaa {
    private final /* synthetic */ Location zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzr(zzn zzn, GoogleApiClient googleApiClient, Location location) {
        super(googleApiClient);
        this.zza = location;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzay) anyClient).zza(this.zza);
        setResult(Status.RESULT_SUCCESS);
    }
}
