package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class ConfigFetchHandler$$Lambda$1 implements Continuation {
    private final ConfigFetchHandler arg$1;
    private final long arg$2;

    private ConfigFetchHandler$$Lambda$1(ConfigFetchHandler configFetchHandler, long j) {
        this.arg$1 = configFetchHandler;
        this.arg$2 = j;
    }

    public static Continuation lambdaFactory$(ConfigFetchHandler configFetchHandler, long j) {
        return new ConfigFetchHandler$$Lambda$1(configFetchHandler, j);
    }

    public Object then(Task task) {
        return this.arg$1.fetchIfCacheExpiredAndNotThrottled(task, this.arg$2);
    }
}
