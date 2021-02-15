package com.google.firebase.remoteconfig.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class ConfigCacheClient$$Lambda$1 implements Callable {
    private final ConfigCacheClient arg$1;
    private final ConfigContainer arg$2;

    private ConfigCacheClient$$Lambda$1(ConfigCacheClient configCacheClient, ConfigContainer configContainer) {
        this.arg$1 = configCacheClient;
        this.arg$2 = configContainer;
    }

    public static Callable lambdaFactory$(ConfigCacheClient configCacheClient, ConfigContainer configContainer) {
        return new ConfigCacheClient$$Lambda$1(configCacheClient, configContainer);
    }

    public Object call() {
        return this.arg$1.storageClient.write(this.arg$2);
    }
}
