package com.google.android.gms.location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final /* synthetic */ class zzw implements FusedLocationProviderClient.zza {
    private final TaskCompletionSource zza;

    zzw(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zza() {
        this.zza.trySetResult(null);
    }
}
