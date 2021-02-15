package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzek extends zzcu<Integer, Long> {
    public long zzyk;
    public long zzyl;

    public zzek() {
        this.zzyk = -1;
        this.zzyl = -1;
    }

    public zzek(String str) {
        this();
        zzan(str);
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> zzbn() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, Long.valueOf(this.zzyk));
        hashMap.put(1, Long.valueOf(this.zzyl));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public final void zzan(String str) {
        HashMap zzao = zzao(str);
        if (zzao != null) {
            this.zzyk = ((Long) zzao.get(0)).longValue();
            this.zzyl = ((Long) zzao.get(1)).longValue();
        }
    }
}
