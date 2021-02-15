package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzau implements ListenerHolder.Notifier<LocationCallback> {
    private final /* synthetic */ LocationAvailability zza;

    zzau(zzas zzas, LocationAvailability locationAvailability) {
        this.zza = locationAvailability;
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((LocationCallback) obj).onLocationAvailability(this.zza);
    }
}
