package com.google.firebase.remoteconfig;

import com.google.firebase.remoteconfig.internal.LegacyConfigsHandler;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class RemoteConfigComponent$$Lambda$4 implements Callable {
    private final LegacyConfigsHandler arg$1;

    private RemoteConfigComponent$$Lambda$4(LegacyConfigsHandler legacyConfigsHandler) {
        this.arg$1 = legacyConfigsHandler;
    }

    public static Callable lambdaFactory$(LegacyConfigsHandler legacyConfigsHandler) {
        return new RemoteConfigComponent$$Lambda$4(legacyConfigsHandler);
    }

    public Object call() {
        return Boolean.valueOf(this.arg$1.saveLegacyConfigsIfNecessary());
    }
}
