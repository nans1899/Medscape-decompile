package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzay;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final /* synthetic */ class zzai implements RemoteCall {
    private final List zza;

    zzai(List list) {
        this.zza = list;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzay) obj).zza((List<String>) this.zza, (BaseImplementation.ResultHolder<Status>) new GeofencingClient.zza((TaskCompletionSource) obj2));
    }
}
