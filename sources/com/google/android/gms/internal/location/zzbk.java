package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzbk extends LocationServices.zza<LocationSettingsResult> {
    private final /* synthetic */ LocationSettingsRequest zza;
    private final /* synthetic */ String zzb = null;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbk(zzbh zzbh, GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest, String str) {
        super(googleApiClient);
        this.zza = locationSettingsRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzay) anyClient).zza(this.zza, (BaseImplementation.ResultHolder<LocationSettingsResult>) this, (String) null);
    }

    public final /* synthetic */ Result createFailedResult(Status status) {
        return new LocationSettingsResult(status);
    }
}
