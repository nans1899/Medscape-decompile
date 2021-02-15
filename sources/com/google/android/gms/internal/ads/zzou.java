package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzou {
    private final Map<String, String> zzbjh = new HashMap();
    private Map<String, String> zzbji;

    public final synchronized Map<String, String> zzit() {
        if (this.zzbji == null) {
            this.zzbji = Collections.unmodifiableMap(new HashMap(this.zzbjh));
        }
        return this.zzbji;
    }
}
