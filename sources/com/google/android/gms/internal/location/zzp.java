package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzp extends zzaa {
    private final /* synthetic */ LocationCallback zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzp(zzn zzn, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
        super(googleApiClient);
        this.zza = locationCallback;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzay) anyClient).zzb((ListenerHolder.ListenerKey<LocationCallback>) ListenerHolders.createListenerKey(this.zza, LocationCallback.class.getSimpleName()), (zzai) new zzz(this));
    }
}
