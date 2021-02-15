package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzfk extends BroadcastReceiver {
    private final /* synthetic */ zzfi zzaag;

    zzfk(zzfi zzfi) {
        this.zzaag = zzfi;
    }

    public final void onReceive(Context context, Intent intent) {
        this.zzaag.zzcv();
    }
}
