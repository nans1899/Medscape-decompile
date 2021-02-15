package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzag;

@Deprecated
/* compiled from: com.google.android.gms:play-services-measurement-api@@17.6.0 */
public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver {
    public final void doStartService(Context context, Intent intent) {
    }

    public final void onReceive(Context context, Intent intent) {
        zzag.zza(context).zza(5, "Install Referrer Broadcast deprecated", (Object) null, (Object) null, (Object) null);
    }

    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }
}
