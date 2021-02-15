package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbfu {
    private final ArrayList<zzop> zzemb = new ArrayList<>();
    private long zzemc;

    zzbfu() {
    }

    /* access modifiers changed from: package-private */
    public final long zzaay() {
        Iterator<zzop> it = this.zzemb.iterator();
        while (it.hasNext()) {
            Map<String, List<String>> responseHeaders = it.next().getResponseHeaders();
            if (responseHeaders != null) {
                for (Map.Entry next : responseHeaders.entrySet()) {
                    try {
                        if ("content-length".equalsIgnoreCase((String) next.getKey())) {
                            this.zzemc = Math.max(this.zzemc, Long.parseLong((String) ((List) next.getValue()).get(0)));
                        }
                    } catch (RuntimeException unused) {
                    }
                }
                it.remove();
            }
        }
        return this.zzemc;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzop zzop) {
        this.zzemb.add(zzop);
    }
}
