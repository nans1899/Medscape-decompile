package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class ConfigCacheClient$$Lambda$2 implements SuccessContinuation {
    private final ConfigCacheClient arg$1;
    private final boolean arg$2;
    private final ConfigContainer arg$3;

    private ConfigCacheClient$$Lambda$2(ConfigCacheClient configCacheClient, boolean z, ConfigContainer configContainer) {
        this.arg$1 = configCacheClient;
        this.arg$2 = z;
        this.arg$3 = configContainer;
    }

    public static SuccessContinuation lambdaFactory$(ConfigCacheClient configCacheClient, boolean z, ConfigContainer configContainer) {
        return new ConfigCacheClient$$Lambda$2(configCacheClient, z, configContainer);
    }

    public Task then(Object obj) {
        return ConfigCacheClient.lambda$put$1(this.arg$1, this.arg$2, this.arg$3, (Void) obj);
    }
}
