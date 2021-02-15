package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzff extends zzcu<Integer, Long> {
    public Long zzaab;
    public Long zzaac;
    public Long zzaad;
    public Long zzaae;

    public zzff() {
    }

    public zzff(String str) {
        zzan(str);
    }

    /* access modifiers changed from: protected */
    public final void zzan(String str) {
        HashMap zzao = zzao(str);
        if (zzao != null) {
            this.zzaab = (Long) zzao.get(0);
            this.zzaac = (Long) zzao.get(1);
            this.zzaad = (Long) zzao.get(2);
            this.zzaae = (Long) zzao.get(3);
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> zzbn() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, this.zzaab);
        hashMap.put(1, this.zzaac);
        hashMap.put(2, this.zzaad);
        hashMap.put(3, this.zzaae);
        return hashMap;
    }
}
