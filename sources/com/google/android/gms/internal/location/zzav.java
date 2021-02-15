package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzav implements ListenerHolder.Notifier<LocationListener> {
    private final /* synthetic */ Location zza;

    zzav(zzaw zzaw, Location location) {
        this.zza = location;
    }

    public final void onNotifyListenerFailed() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((LocationListener) obj).onLocationChanged(this.zza);
    }
}
