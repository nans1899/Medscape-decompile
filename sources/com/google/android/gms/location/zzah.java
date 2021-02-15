package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzay;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final /* synthetic */ class zzah implements RemoteCall {
    private final GeofencingRequest zza;
    private final PendingIntent zzb;

    zzah(GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        this.zza = geofencingRequest;
        this.zzb = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzay) obj).zza(this.zza, this.zzb, (BaseImplementation.ResultHolder<Status>) new GeofencingClient.zza((TaskCompletionSource) obj2));
    }
}
