package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzjn<T> implements zzjz<T> {
    private final zzjh zza;
    private final zzkr<?, ?> zzb;
    private final boolean zzc;
    private final zzho<?> zzd;

    private zzjn(zzkr<?, ?> zzkr, zzho<?> zzho, zzjh zzjh) {
        this.zzb = zzkr;
        this.zzc = zzho.zza(zzjh);
        this.zzd = zzho;
        this.zza = zzjh;
    }

    static <T> zzjn<T> zza(zzkr<?, ?> zzkr, zzho<?> zzho, zzjh zzjh) {
        return new zzjn<>(zzkr, zzho, zzjh);
    }

    public final T zza() {
        return this.zza.zzbt().zzy();
    }

    public final boolean zza(T t, T t2) {
        if (!this.zzb.zzb(t).equals(this.zzb.zzb(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza((Object) t).equals(this.zzd.zza((Object) t2));
        }
        return true;
    }

    public final int zza(T t) {
        int hashCode = this.zzb.zzb(t).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zza((Object) t).hashCode() : hashCode;
    }

    public final void zzb(T t, T t2) {
        zzkb.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzkb.zza(this.zzd, t, t2);
        }
    }

    public final void zza(T t, zzlo zzlo) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd2 = this.zzd.zza((Object) t).zzd();
        while (zzd2.hasNext()) {
            Map.Entry next = zzd2.next();
            zzhr zzhr = (zzhr) next.getKey();
            if (zzhr.zzc() != zzll.MESSAGE || zzhr.zzd() || zzhr.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzio) {
                zzlo.zza(zzhr.zza(), (Object) ((zzio) next).zza().zzc());
            } else {
                zzlo.zza(zzhr.zza(), next.getValue());
            }
        }
        zzkr<?, ?> zzkr = this.zzb;
        zzkr.zzb(zzkr.zzb(t), zzlo);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.measurement.zzhz$zzd} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.measurement.zzgm r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.measurement.zzhz r0 = (com.google.android.gms.internal.measurement.zzhz) r0
            com.google.android.gms.internal.measurement.zzku r1 = r0.zzb
            com.google.android.gms.internal.measurement.zzku r2 = com.google.android.gms.internal.measurement.zzku.zza()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.measurement.zzku r1 = com.google.android.gms.internal.measurement.zzku.zzb()
            r0.zzb = r1
        L_0x0011:
            com.google.android.gms.internal.measurement.zzhz$zzb r10 = (com.google.android.gms.internal.measurement.zzhz.zzb) r10
            r10.zza()
            r10 = 0
            r0 = r10
        L_0x0018:
            if (r12 >= r13) goto L_0x00a4
            int r4 = com.google.android.gms.internal.measurement.zzgn.zza(r11, r12, r14)
            int r2 = r14.zza
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L_0x0051
            r12 = r2 & 7
            if (r12 != r3) goto L_0x004c
            com.google.android.gms.internal.measurement.zzho<?> r12 = r9.zzd
            com.google.android.gms.internal.measurement.zzhm r0 = r14.zzd
            com.google.android.gms.internal.measurement.zzjh r3 = r9.zza
            int r5 = r2 >>> 3
            java.lang.Object r12 = r12.zza(r0, r3, r5)
            r0 = r12
            com.google.android.gms.internal.measurement.zzhz$zzd r0 = (com.google.android.gms.internal.measurement.zzhz.zzd) r0
            if (r0 != 0) goto L_0x0043
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.gms.internal.measurement.zzgn.zza((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.measurement.zzku) r6, (com.google.android.gms.internal.measurement.zzgm) r7)
            goto L_0x0018
        L_0x0043:
            com.google.android.gms.internal.measurement.zzjv.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L_0x004c:
            int r12 = com.google.android.gms.internal.measurement.zzgn.zza((int) r2, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.measurement.zzgm) r14)
            goto L_0x0018
        L_0x0051:
            r12 = 0
            r2 = r10
        L_0x0053:
            if (r4 >= r13) goto L_0x0099
            int r4 = com.google.android.gms.internal.measurement.zzgn.zza(r11, r4, r14)
            int r5 = r14.zza
            int r6 = r5 >>> 3
            r7 = r5 & 7
            if (r6 == r3) goto L_0x007b
            r8 = 3
            if (r6 == r8) goto L_0x0065
            goto L_0x0090
        L_0x0065:
            if (r0 != 0) goto L_0x0072
            if (r7 != r3) goto L_0x0090
            int r4 = com.google.android.gms.internal.measurement.zzgn.zze(r11, r4, r14)
            java.lang.Object r2 = r14.zzc
            com.google.android.gms.internal.measurement.zzgr r2 = (com.google.android.gms.internal.measurement.zzgr) r2
            goto L_0x0053
        L_0x0072:
            com.google.android.gms.internal.measurement.zzjv.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L_0x007b:
            if (r7 != 0) goto L_0x0090
            int r4 = com.google.android.gms.internal.measurement.zzgn.zza(r11, r4, r14)
            int r12 = r14.zza
            com.google.android.gms.internal.measurement.zzho<?> r0 = r9.zzd
            com.google.android.gms.internal.measurement.zzhm r5 = r14.zzd
            com.google.android.gms.internal.measurement.zzjh r6 = r9.zza
            java.lang.Object r0 = r0.zza(r5, r6, r12)
            com.google.android.gms.internal.measurement.zzhz$zzd r0 = (com.google.android.gms.internal.measurement.zzhz.zzd) r0
            goto L_0x0053
        L_0x0090:
            r6 = 12
            if (r5 == r6) goto L_0x0099
            int r4 = com.google.android.gms.internal.measurement.zzgn.zza((int) r5, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.measurement.zzgm) r14)
            goto L_0x0053
        L_0x0099:
            if (r2 == 0) goto L_0x00a1
            int r12 = r12 << 3
            r12 = r12 | r3
            r1.zza((int) r12, (java.lang.Object) r2)
        L_0x00a1:
            r12 = r4
            goto L_0x0018
        L_0x00a4:
            if (r12 != r13) goto L_0x00a7
            return
        L_0x00a7:
            com.google.android.gms.internal.measurement.zzih r10 = com.google.android.gms.internal.measurement.zzih.zzg()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjn.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzgm):void");
    }

    public final void zza(T t, zzka zzka, zzhm zzhm) throws IOException {
        boolean z;
        zzkr<?, ?> zzkr = this.zzb;
        zzho<?> zzho = this.zzd;
        Object zzc2 = zzkr.zzc(t);
        zzhp<?> zzb2 = zzho.zzb(t);
        do {
            try {
                if (zzka.zza() == Integer.MAX_VALUE) {
                    zzkr.zzb((Object) t, zzc2);
                    return;
                }
                int zzb3 = zzka.zzb();
                if (zzb3 == 11) {
                    int i = 0;
                    Object obj = null;
                    zzgr zzgr = null;
                    while (zzka.zza() != Integer.MAX_VALUE) {
                        int zzb4 = zzka.zzb();
                        if (zzb4 == 16) {
                            i = zzka.zzo();
                            obj = zzho.zza(zzhm, this.zza, i);
                        } else if (zzb4 == 26) {
                            if (obj != null) {
                                zzho.zza(zzka, obj, zzhm, zzb2);
                            } else {
                                zzgr = zzka.zzn();
                            }
                        } else if (!zzka.zzc()) {
                            break;
                        }
                    }
                    if (zzka.zzb() != 12) {
                        throw zzih.zze();
                    } else if (zzgr != null) {
                        if (obj != null) {
                            zzho.zza(zzgr, obj, zzhm, zzb2);
                        } else {
                            zzkr.zza(zzc2, i, zzgr);
                        }
                    }
                } else if ((zzb3 & 7) == 2) {
                    Object zza2 = zzho.zza(zzhm, this.zza, zzb3 >>> 3);
                    if (zza2 != null) {
                        zzho.zza(zzka, zza2, zzhm, zzb2);
                    } else {
                        z = zzkr.zza(zzc2, zzka);
                        continue;
                    }
                } else {
                    z = zzka.zzc();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzkr.zzb((Object) t, zzc2);
            }
        } while (z);
    }

    public final void zzc(T t) {
        this.zzb.zzd(t);
        this.zzd.zzc(t);
    }

    public final boolean zzd(T t) {
        return this.zzd.zza((Object) t).zzf();
    }

    public final int zzb(T t) {
        zzkr<?, ?> zzkr = this.zzb;
        int zze = zzkr.zze(zzkr.zzb(t)) + 0;
        return this.zzc ? zze + this.zzd.zza((Object) t).zzg() : zze;
    }
}
