package com.google.firebase.remoteconfig.internal;

import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class ConfigCacheClient$$Lambda$4 implements Executor {
    private static final ConfigCacheClient$$Lambda$4 instance = new ConfigCacheClient$$Lambda$4();

    private ConfigCacheClient$$Lambda$4() {
    }

    public static Executor lambdaFactory$() {
        return instance;
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
