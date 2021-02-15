package com.google.firebase.analytics.connector;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.6.0 */
final /* synthetic */ class zza implements Executor {
    static final Executor zza = new zza();

    private zza() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
