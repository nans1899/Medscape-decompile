package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddj implements zzdgx<zzddg> {
    private final zzdzb zzgcz;
    private final Context zzvr;

    public zzddj(zzdzb zzdzb, Context context) {
        this.zzgcz = zzdzb;
        this.zzvr = context;
    }

    public final zzdzc<zzddg> zzarj() {
        return this.zzgcz.zze(new zzddi(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzddg zzaro() throws Exception {
        double d;
        Intent registerReceiver = this.zzvr.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            d = ((double) registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1)) / ((double) registerReceiver.getIntExtra("scale", -1));
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
        } else {
            d = -1.0d;
        }
        return new zzddg(d, z);
    }
}
