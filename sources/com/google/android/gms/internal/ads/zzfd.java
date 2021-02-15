package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzfd extends zzcu<Integer, Long> {
    public Long zzzp;
    public Long zzzq;
    public Long zzzr;
    public Long zzzs;
    public Long zzzt;
    public Long zzzu;
    public Long zzzv;
    public Long zzzw;
    public Long zzzx;
    public Long zzzy;
    public Long zzzz;

    public zzfd() {
    }

    public zzfd(String str) {
        zzan(str);
    }

    /* access modifiers changed from: protected */
    public final void zzan(String str) {
        HashMap zzao = zzao(str);
        if (zzao != null) {
            this.zzzp = (Long) zzao.get(0);
            this.zzzq = (Long) zzao.get(1);
            this.zzzr = (Long) zzao.get(2);
            this.zzzs = (Long) zzao.get(3);
            this.zzzt = (Long) zzao.get(4);
            this.zzzu = (Long) zzao.get(5);
            this.zzzv = (Long) zzao.get(6);
            this.zzzw = (Long) zzao.get(7);
            this.zzzx = (Long) zzao.get(8);
            this.zzzy = (Long) zzao.get(9);
            this.zzzz = (Long) zzao.get(10);
        }
    }

    /* access modifiers changed from: protected */
    public final HashMap<Integer, Long> zzbn() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, this.zzzp);
        hashMap.put(1, this.zzzq);
        hashMap.put(2, this.zzzr);
        hashMap.put(3, this.zzzs);
        hashMap.put(4, this.zzzt);
        hashMap.put(5, this.zzzu);
        hashMap.put(6, this.zzzv);
        hashMap.put(7, this.zzzw);
        hashMap.put(8, this.zzzx);
        hashMap.put(9, this.zzzy);
        hashMap.put(10, this.zzzz);
        return hashMap;
    }
}
