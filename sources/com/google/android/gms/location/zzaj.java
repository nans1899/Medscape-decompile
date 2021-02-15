package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzay;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final /* synthetic */ class zzaj implements RemoteCall {
    private final PendingIntent zza;

    zzaj(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzay) obj).zzb(this.zza, (BaseImplementation.ResultHolder<Status>) new GeofencingClient.zza((TaskCompletionSource) obj2));
    }
}
