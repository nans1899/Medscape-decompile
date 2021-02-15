package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzs implements Executor {
    private final /* synthetic */ Handler val$handler;

    zzs(zzt zzt, Handler handler) {
        this.val$handler = handler;
    }

    public final void execute(Runnable runnable) {
        this.val$handler.post(runnable);
    }
}
