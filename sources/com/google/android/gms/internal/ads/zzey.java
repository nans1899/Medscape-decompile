package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzey extends zzcu<Integer, Long> {
    public Long zzzi;
    public Long zzzj;

    public zzey() {
    }

    public zzey(String str) {
        zzan(str);
    }

    /* access modifiers changed from: protected */
    public final void zzan(String str) {
        HashMap zzao = zzao(str);
        if (zzao != null) {
            this.zzzi = (Long) zzao.get(0);
            this.zzzj = (Long) zzao.get(1);
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> zzbn() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, this.zzzi);
        hashMap.put(1, this.zzzj);
        return hashMap;
    }
}
