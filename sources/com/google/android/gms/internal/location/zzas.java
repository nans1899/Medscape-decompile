package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzao;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzas extends zzao {
    private final ListenerHolder<LocationCallback> zza;

    zzas(ListenerHolder<LocationCallback> listenerHolder) {
        this.zza = listenerHolder;
    }

    public final void zza(LocationResult locationResult) {
        this.zza.notifyListener(new zzar(this, locationResult));
    }

    public final void zza(LocationAvailability locationAvailability) {
        this.zza.notifyListener(new zzau(this, locationAvailability));
    }

    public final synchronized void zza() {
        this.zza.clear();
    }
}
