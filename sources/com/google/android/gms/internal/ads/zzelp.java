package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzelp<T> implements zzemf<T> {
    private final zzelj zzils;
    private final boolean zzilt;
    private final zzemx<?, ?> zzimc;
    private final zzejo<?> zzimd;

    private zzelp(zzemx<?, ?> zzemx, zzejo<?> zzejo, zzelj zzelj) {
        this.zzimc = zzemx;
        this.zzilt = zzejo.zzj(zzelj);
        this.zzimd = zzejo;
        this.zzils = zzelj;
    }

    static <T> zzelp<T> zza(zzemx<?, ?> zzemx, zzejo<?> zzejo, zzelj zzelj) {
        return new zzelp<>(zzemx, zzejo, zzelj);
    }

    public final T newInstance() {
        return this.zzils.zzbgm().zzbgs();
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzimc.zzax(t).equals(this.zzimc.zzax(t2))) {
            return false;
        }
        if (this.zzilt) {
            return this.zzimd.zzah(t).equals(this.zzimd.zzah(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzimc.zzax(t).hashCode();
        return this.zzilt ? (hashCode * 53) + this.zzimd.zzah(t).hashCode() : hashCode;
    }

    public final void zzg(T t, T t2) {
        zzemh.zza(this.zzimc, t, t2);
        if (this.zzilt) {
            zzemh.zza(this.zzimd, t, t2);
        }
    }

    public final void zza(T t, zzenu zzenu) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzimd.zzah(t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzeju zzeju = (zzeju) next.getKey();
            if (zzeju.zzbgb() != zzenr.MESSAGE || zzeju.zzbgc() || zzeju.zzbgd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzekq) {
                zzenu.zzc(zzeju.zzv(), ((zzekq) next).zzbhi().zzbdw());
            } else {
                zzenu.zzc(zzeju.zzv(), next.getValue());
            }
        }
        zzemx<?, ?> zzemx = this.zzimc;
        zzemx.zzc(zzemx.zzax(t), zzenu);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.ads.zzejz$zzf} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.ads.zzeik r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.ads.zzejz r0 = (com.google.android.gms.internal.ads.zzejz) r0
            com.google.android.gms.internal.ads.zzena r1 = r0.zzijc
            com.google.android.gms.internal.ads.zzena r2 = com.google.android.gms.internal.ads.zzena.zzbiv()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.ads.zzena r1 = com.google.android.gms.internal.ads.zzena.zzbiw()
            r0.zzijc = r1
        L_0x0011:
            com.google.android.gms.internal.ads.zzejz$zzd r10 = (com.google.android.gms.internal.ads.zzejz.zzd) r10
            r10.zzbgu()
            r10 = 0
            r0 = r10
        L_0x0018:
            if (r12 >= r13) goto L_0x00a4
            int r4 = com.google.android.gms.internal.ads.zzeil.zza(r11, r12, r14)
            int r2 = r14.zziek
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L_0x0051
            r12 = r2 & 7
            if (r12 != r3) goto L_0x004c
            com.google.android.gms.internal.ads.zzejo<?> r12 = r9.zzimd
            com.google.android.gms.internal.ads.zzejm r0 = r14.zzien
            com.google.android.gms.internal.ads.zzelj r3 = r9.zzils
            int r5 = r2 >>> 3
            java.lang.Object r12 = r12.zza(r0, r3, r5)
            r0 = r12
            com.google.android.gms.internal.ads.zzejz$zzf r0 = (com.google.android.gms.internal.ads.zzejz.zzf) r0
            if (r0 != 0) goto L_0x0043
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.gms.internal.ads.zzeil.zza((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.ads.zzena) r6, (com.google.android.gms.internal.ads.zzeik) r7)
            goto L_0x0018
        L_0x0043:
            com.google.android.gms.internal.ads.zzely.zzbic()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L_0x004c:
            int r12 = com.google.android.gms.internal.ads.zzeil.zza((int) r2, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.ads.zzeik) r14)
            goto L_0x0018
        L_0x0051:
            r12 = 0
            r2 = r10
        L_0x0053:
            if (r4 >= r13) goto L_0x0099
            int r4 = com.google.android.gms.internal.ads.zzeil.zza(r11, r4, r14)
            int r5 = r14.zziek
            int r6 = r5 >>> 3
            r7 = r5 & 7
            if (r6 == r3) goto L_0x007b
            r8 = 3
            if (r6 == r8) goto L_0x0065
            goto L_0x0090
        L_0x0065:
            if (r0 != 0) goto L_0x0072
            if (r7 != r3) goto L_0x0090
            int r4 = com.google.android.gms.internal.ads.zzeil.zze(r11, r4, r14)
            java.lang.Object r2 = r14.zziem
            com.google.android.gms.internal.ads.zzeip r2 = (com.google.android.gms.internal.ads.zzeip) r2
            goto L_0x0053
        L_0x0072:
            com.google.android.gms.internal.ads.zzely.zzbic()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L_0x007b:
            if (r7 != 0) goto L_0x0090
            int r4 = com.google.android.gms.internal.ads.zzeil.zza(r11, r4, r14)
            int r12 = r14.zziek
            com.google.android.gms.internal.ads.zzejo<?> r0 = r9.zzimd
            com.google.android.gms.internal.ads.zzejm r5 = r14.zzien
            com.google.android.gms.internal.ads.zzelj r6 = r9.zzils
            java.lang.Object r0 = r0.zza(r5, r6, r12)
            com.google.android.gms.internal.ads.zzejz$zzf r0 = (com.google.android.gms.internal.ads.zzejz.zzf) r0
            goto L_0x0053
        L_0x0090:
            r6 = 12
            if (r5 == r6) goto L_0x0099
            int r4 = com.google.android.gms.internal.ads.zzeil.zza((int) r5, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.ads.zzeik) r14)
            goto L_0x0053
        L_0x0099:
            if (r2 == 0) goto L_0x00a1
            int r12 = r12 << 3
            r12 = r12 | r3
            r1.zzd(r12, r2)
        L_0x00a1:
            r12 = r4
            goto L_0x0018
        L_0x00a4:
            if (r12 != r13) goto L_0x00a7
            return
        L_0x00a7:
            com.google.android.gms.internal.ads.zzekj r10 = com.google.android.gms.internal.ads.zzekj.zzbhe()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzelp.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzeik):void");
    }

    public final void zza(T t, zzelz zzelz, zzejm zzejm) throws IOException {
        boolean z;
        zzemx<?, ?> zzemx = this.zzimc;
        zzejo<?> zzejo = this.zzimd;
        Object zzay = zzemx.zzay(t);
        zzejs<?> zzai = zzejo.zzai(t);
        do {
            try {
                if (zzelz.zzbfn() == Integer.MAX_VALUE) {
                    zzemx.zzj(t, zzay);
                    return;
                }
                int tag = zzelz.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzeip zzeip = null;
                    while (zzelz.zzbfn() != Integer.MAX_VALUE) {
                        int tag2 = zzelz.getTag();
                        if (tag2 == 16) {
                            i = zzelz.zzbey();
                            obj = zzejo.zza(zzejm, this.zzils, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzejo.zza(zzelz, obj, zzejm, zzai);
                            } else {
                                zzeip = zzelz.zzbex();
                            }
                        } else if (!zzelz.zzbfo()) {
                            break;
                        }
                    }
                    if (zzelz.getTag() != 12) {
                        throw zzekj.zzbhb();
                    } else if (zzeip != null) {
                        if (obj != null) {
                            zzejo.zza(zzeip, obj, zzejm, zzai);
                        } else {
                            zzemx.zza(zzay, i, zzeip);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzejo.zza(zzejm, this.zzils, tag >>> 3);
                    if (zza != null) {
                        zzejo.zza(zzelz, zza, zzejm, zzai);
                    } else {
                        z = zzemx.zza(zzay, zzelz);
                        continue;
                    }
                } else {
                    z = zzelz.zzbfo();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzemx.zzj(t, zzay);
            }
        } while (z);
    }

    public final void zzaj(T t) {
        this.zzimc.zzaj(t);
        this.zzimd.zzaj(t);
    }

    public final boolean zzav(T t) {
        return this.zzimd.zzah(t).isInitialized();
    }

    public final int zzat(T t) {
        zzemx<?, ?> zzemx = this.zzimc;
        int zzaz = zzemx.zzaz(zzemx.zzax(t)) + 0;
        return this.zzilt ? zzaz + this.zzimd.zzah(t).zzbfz() : zzaz;
    }
}
