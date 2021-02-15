package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzckp {
    private final Map<String, zzckq> zzgfu = new HashMap();

    zzckp() {
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(String str, zzdpa zzdpa) {
        if (!this.zzgfu.containsKey(str)) {
            try {
                this.zzgfu.put(str, new zzckq(str, zzdpa.zzua(), zzdpa.zzub()));
            } catch (zzdos unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(String str, zzaph zzaph) {
        if (!this.zzgfu.containsKey(str)) {
            try {
                this.zzgfu.put(str, new zzckq(str, zzaph.zzua(), zzaph.zzub()));
            } catch (Throwable unused) {
            }
        }
    }

    @Nullable
    public final synchronized zzckq zzgg(String str) {
        return this.zzgfu.get(str);
    }
}
