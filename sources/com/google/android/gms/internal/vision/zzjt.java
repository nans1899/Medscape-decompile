package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzjt<T> implements zzkf<T> {
    private final zzjn zzaao;
    private final boolean zzaap;
    private final zzkx<?, ?> zzaay;
    private final zzhq<?> zzaaz;

    private zzjt(zzkx<?, ?> zzkx, zzhq<?> zzhq, zzjn zzjn) {
        this.zzaay = zzkx;
        this.zzaap = zzhq.zze(zzjn);
        this.zzaaz = zzhq;
        this.zzaao = zzjn;
    }

    static <T> zzjt<T> zza(zzkx<?, ?> zzkx, zzhq<?> zzhq, zzjn zzjn) {
        return new zzjt<>(zzkx, zzhq, zzjn);
    }

    public final T newInstance() {
        return this.zzaao.zzhd().zzgv();
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzaay.zzy(t).equals(this.zzaay.zzy(t2))) {
            return false;
        }
        if (this.zzaap) {
            return this.zzaaz.zzh(t).equals(this.zzaaz.zzh(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzaay.zzy(t).hashCode();
        return this.zzaap ? (hashCode * 53) + this.zzaaz.zzh(t).hashCode() : hashCode;
    }

    public final void zzd(T t, T t2) {
        zzkh.zza(this.zzaay, t, t2);
        if (this.zzaap) {
            zzkh.zza(this.zzaaz, t, t2);
        }
    }

    public final void zza(T t, zzlq zzlq) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzaaz.zzh(t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzhv zzhv = (zzhv) next.getKey();
            if (zzhv.zzgn() != zzlr.MESSAGE || zzhv.zzgo() || zzhv.zzgp()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zziq) {
                zzlq.zza(zzhv.zzak(), (Object) ((zziq) next).zzhr().zzee());
            } else {
                zzlq.zza(zzhv.zzak(), next.getValue());
            }
        }
        zzkx<?, ?> zzkx = this.zzaay;
        zzkx.zzc(zzkx.zzy(t), zzlq);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: com.google.android.gms.internal.vision.zzid$zzg} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.vision.zzgm r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.vision.zzid r0 = (com.google.android.gms.internal.vision.zzid) r0
            com.google.android.gms.internal.vision.zzkw r1 = r0.zzxz
            com.google.android.gms.internal.vision.zzkw r2 = com.google.android.gms.internal.vision.zzkw.zzja()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.vision.zzkw r1 = com.google.android.gms.internal.vision.zzkw.zzjb()
            r0.zzxz = r1
        L_0x0011:
            com.google.android.gms.internal.vision.zzid$zze r11 = (com.google.android.gms.internal.vision.zzid.zze) r11
            com.google.android.gms.internal.vision.zzht r11 = r11.zzhe()
            r0 = 0
            r2 = r0
        L_0x0019:
            if (r13 >= r14) goto L_0x00c9
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r12, r13, r15)
            int r13 = r15.zztk
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L_0x0065
            r3 = r13 & 7
            if (r3 != r5) goto L_0x0060
            com.google.android.gms.internal.vision.zzhq<?> r2 = r10.zzaaz
            com.google.android.gms.internal.vision.zzho r3 = r15.zzcu
            com.google.android.gms.internal.vision.zzjn r5 = r10.zzaao
            int r6 = r13 >>> 3
            java.lang.Object r2 = r2.zza(r3, r5, r6)
            r8 = r2
            com.google.android.gms.internal.vision.zzid$zzg r8 = (com.google.android.gms.internal.vision.zzid.zzg) r8
            if (r8 == 0) goto L_0x0055
            com.google.android.gms.internal.vision.zzkb r13 = com.google.android.gms.internal.vision.zzkb.zzik()
            com.google.android.gms.internal.vision.zzjn r2 = r8.zzyq
            java.lang.Class r2 = r2.getClass()
            com.google.android.gms.internal.vision.zzkf r13 = r13.zzf(r2)
            int r13 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r13, (byte[]) r12, (int) r4, (int) r14, (com.google.android.gms.internal.vision.zzgm) r15)
            com.google.android.gms.internal.vision.zzid$zzd r2 = r8.zzyr
            java.lang.Object r3 = r15.zztm
            r11.zza(r2, (java.lang.Object) r3)
            goto L_0x005e
        L_0x0055:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.vision.zzgk.zza((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.vision.zzkw) r6, (com.google.android.gms.internal.vision.zzgm) r7)
        L_0x005e:
            r2 = r8
            goto L_0x0019
        L_0x0060:
            int r13 = com.google.android.gms.internal.vision.zzgk.zza((int) r13, (byte[]) r12, (int) r4, (int) r14, (com.google.android.gms.internal.vision.zzgm) r15)
            goto L_0x0019
        L_0x0065:
            r13 = 0
            r3 = r0
        L_0x0067:
            if (r4 >= r14) goto L_0x00be
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r12, r4, r15)
            int r6 = r15.zztk
            int r7 = r6 >>> 3
            r8 = r6 & 7
            if (r7 == r5) goto L_0x00a0
            r9 = 3
            if (r7 == r9) goto L_0x0079
            goto L_0x00b5
        L_0x0079:
            if (r2 == 0) goto L_0x0095
            com.google.android.gms.internal.vision.zzkb r6 = com.google.android.gms.internal.vision.zzkb.zzik()
            com.google.android.gms.internal.vision.zzjn r7 = r2.zzyq
            java.lang.Class r7 = r7.getClass()
            com.google.android.gms.internal.vision.zzkf r6 = r6.zzf(r7)
            int r4 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r6, (byte[]) r12, (int) r4, (int) r14, (com.google.android.gms.internal.vision.zzgm) r15)
            com.google.android.gms.internal.vision.zzid$zzd r6 = r2.zzyr
            java.lang.Object r7 = r15.zztm
            r11.zza(r6, (java.lang.Object) r7)
            goto L_0x0067
        L_0x0095:
            if (r8 != r5) goto L_0x00b5
            int r4 = com.google.android.gms.internal.vision.zzgk.zze(r12, r4, r15)
            java.lang.Object r3 = r15.zztm
            com.google.android.gms.internal.vision.zzgs r3 = (com.google.android.gms.internal.vision.zzgs) r3
            goto L_0x0067
        L_0x00a0:
            if (r8 != 0) goto L_0x00b5
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r12, r4, r15)
            int r13 = r15.zztk
            com.google.android.gms.internal.vision.zzhq<?> r2 = r10.zzaaz
            com.google.android.gms.internal.vision.zzho r6 = r15.zzcu
            com.google.android.gms.internal.vision.zzjn r7 = r10.zzaao
            java.lang.Object r2 = r2.zza(r6, r7, r13)
            com.google.android.gms.internal.vision.zzid$zzg r2 = (com.google.android.gms.internal.vision.zzid.zzg) r2
            goto L_0x0067
        L_0x00b5:
            r7 = 12
            if (r6 == r7) goto L_0x00be
            int r4 = com.google.android.gms.internal.vision.zzgk.zza((int) r6, (byte[]) r12, (int) r4, (int) r14, (com.google.android.gms.internal.vision.zzgm) r15)
            goto L_0x0067
        L_0x00be:
            if (r3 == 0) goto L_0x00c6
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.zzb(r13, r3)
        L_0x00c6:
            r13 = r4
            goto L_0x0019
        L_0x00c9:
            if (r13 != r14) goto L_0x00cc
            return
        L_0x00cc:
            com.google.android.gms.internal.vision.zzin r11 = com.google.android.gms.internal.vision.zzin.zzhn()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjt.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzgm):void");
    }

    public final void zza(T t, zzkc zzkc, zzho zzho) throws IOException {
        boolean z;
        zzkx<?, ?> zzkx = this.zzaay;
        zzhq<?> zzhq = this.zzaaz;
        Object zzz = zzkx.zzz(t);
        zzht<?> zzi = zzhq.zzi(t);
        do {
            try {
                if (zzkc.zzeo() == Integer.MAX_VALUE) {
                    zzkx.zzg(t, zzz);
                    return;
                }
                int tag = zzkc.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzgs zzgs = null;
                    while (zzkc.zzeo() != Integer.MAX_VALUE) {
                        int tag2 = zzkc.getTag();
                        if (tag2 == 16) {
                            i = zzkc.zzey();
                            obj = zzhq.zza(zzho, this.zzaao, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzhq.zza(zzkc, obj, zzho, zzi);
                            } else {
                                zzgs = zzkc.zzex();
                            }
                        } else if (!zzkc.zzep()) {
                            break;
                        }
                    }
                    if (zzkc.getTag() != 12) {
                        throw zzin.zzhl();
                    } else if (zzgs != null) {
                        if (obj != null) {
                            zzhq.zza(zzgs, obj, zzho, zzi);
                        } else {
                            zzkx.zza(zzz, i, zzgs);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzhq.zza(zzho, this.zzaao, tag >>> 3);
                    if (zza != null) {
                        zzhq.zza(zzkc, zza, zzho, zzi);
                    } else {
                        z = zzkx.zza(zzz, zzkc);
                        continue;
                    }
                } else {
                    z = zzkc.zzep();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzkx.zzg(t, zzz);
            }
        } while (z);
    }

    public final void zzj(T t) {
        this.zzaay.zzj(t);
        this.zzaaz.zzj(t);
    }

    public final boolean zzw(T t) {
        return this.zzaaz.zzh(t).isInitialized();
    }

    public final int zzu(T t) {
        zzkx<?, ?> zzkx = this.zzaay;
        int zzaa = zzkx.zzaa(zzkx.zzy(t)) + 0;
        return this.zzaap ? zzaa + this.zzaaz.zzh(t).zzgi() : zzaa;
    }
}
