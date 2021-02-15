package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzet extends zzcu<Integer, Object> {
    public Long zzyn;
    public Boolean zzyo;
    public Boolean zzyp;

    public zzet() {
    }

    public zzet(String str) {
        zzan(str);
    }

    /* access modifiers changed from: protected */
    public final void zzan(String str) {
        HashMap zzao = zzao(str);
        if (zzao != null) {
            this.zzyn = (Long) zzao.get(0);
            this.zzyo = (Boolean) zzao.get(1);
            this.zzyp = (Boolean) zzao.get(2);
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Object> zzbn() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(0, this.zzyn);
        hashMap.put(1, this.zzyo);
        hashMap.put(2, this.zzyp);
        return hashMap;
    }
}
