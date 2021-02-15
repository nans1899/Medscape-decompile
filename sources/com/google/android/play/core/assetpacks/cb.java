package com.google.android.play.core.assetpacks;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;

final class cb {
    private final Map<String, Double> a = new HashMap();

    cb() {
    }

    /* access modifiers changed from: package-private */
    public final synchronized double a(String str, ct ctVar) {
        double d;
        d = 1.0d;
        if (ctVar instanceof bv) {
            d = (((double) ((bv) ctVar).e) + 1.0d) / ((double) ((bv) ctVar).f);
        }
        this.a.put(str, Double.valueOf(d));
        return d;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(String str) {
        this.a.put(str, Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
    }

    /* access modifiers changed from: package-private */
    public final synchronized double b(String str) {
        Double d = this.a.get(str);
        if (d == null) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        return d.doubleValue();
    }
}
