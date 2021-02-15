package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaza extends BroadcastReceiver {
    private final /* synthetic */ zzayu zzebj;

    private zzaza(zzayu zzayu) {
        this.zzebj = zzayu;
    }

    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            boolean unused = this.zzebj.zzyq = true;
        } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            boolean unused2 = this.zzebj.zzyq = false;
        }
    }

    /* synthetic */ zzaza(zzayu zzayu, zzayw zzayw) {
        this(zzayu);
    }
}
