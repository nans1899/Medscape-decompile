package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzar implements ListenerHolder.Notifier<LocationCallback> {
    private final /* synthetic */ LocationResult zza;

    zzar(zzas zzas, LocationResult locationResult) {
        this.zza = locationResult;
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((LocationCallback) obj).onLocationResult(this.zza);
    }
}
