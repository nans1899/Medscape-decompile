package com.google.firebase.analytics.connector;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.6.0 */
final /* synthetic */ class zzb implements EventHandler {
    static final EventHandler zza = new zzb();

    private zzb() {
    }

    public final void handle(Event event) {
        AnalyticsConnectorImpl.zza(event);
    }
}
