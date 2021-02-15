package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbbf extends BroadcastReceiver {
    private final /* synthetic */ zzbbc zzeds;

    zzbbf(zzbbc zzbbc) {
        this.zzeds = zzbbc;
    }

    public final void onReceive(Context context, Intent intent) {
        this.zzeds.zzc(context, intent);
    }
}
