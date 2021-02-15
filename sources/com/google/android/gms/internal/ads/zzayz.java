package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzayz implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzayu zzebj;

    zzayz(zzayu zzayu, Context context) {
        this.zzebj = zzayu;
        this.val$context = context;
    }

    public final void run() {
        synchronized (this.zzebj.zzebb) {
            String unused = this.zzebj.zzbij = zzayu.zzar(this.val$context);
            this.zzebj.zzebb.notifyAll();
        }
    }
}
