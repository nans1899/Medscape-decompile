package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.6.0 */
final class zzd implements AppMeasurementSdk.OnEventListener {
    private final /* synthetic */ zze zza;

    public zzd(zze zze) {
        this.zza = zze;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        if (this.zza.zza.contains(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(OmnitureConstants.CHANNEL, zzb.zze(str2));
            this.zza.zzb.onMessageTriggered(2, bundle2);
        }
    }
}
