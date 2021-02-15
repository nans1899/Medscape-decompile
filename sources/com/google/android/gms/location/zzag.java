package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.internal.location.zzay;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzag extends FusedLocationProviderClient.zzc {
    private final /* synthetic */ ListenerHolder zza;
    private final /* synthetic */ FusedLocationProviderClient zzb;

    zzag(FusedLocationProviderClient fusedLocationProviderClient, ListenerHolder listenerHolder) {
        this.zzb = fusedLocationProviderClient;
        this.zza = listenerHolder;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        zzay zzay = (zzay) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        if (zza()) {
            try {
                zzay.zzb((ListenerHolder.ListenerKey<LocationCallback>) this.zza.getListenerKey(), this.zzb.zza(taskCompletionSource));
            } catch (RuntimeException e) {
                taskCompletionSource.trySetException(e);
            }
        }
    }
}
