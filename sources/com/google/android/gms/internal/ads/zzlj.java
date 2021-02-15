package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzlj implements zzjv, zzke {
    private static final zzka zzapj = new zzlm();
    private static final int zzayu = zzpq.zzbl("qt  ");
    private long zzaih;
    private final zzpk zzapq = new zzpk(zzpf.zzbjr);
    private final zzpk zzapr = new zzpk(4);
    private int zzarf;
    private int zzarg;
    private zzjx zzarj;
    private final zzpk zzaxt = new zzpk(16);
    private final Stack<zzku> zzaxv = new Stack<>();
    private int zzaxx;
    private int zzaxy;
    private long zzaxz;
    private int zzaya;
    private zzpk zzayb;
    private zzll[] zzayv;
    private boolean zzayw;

    public final boolean isSeekable() {
        return true;
    }

    public final void release() {
    }

    public final boolean zza(zzjy zzjy) throws IOException, InterruptedException {
        return zzlo.zze(zzjy);
    }

    public final void zza(zzjx zzjx) {
        this.zzarj = zzjx;
    }

    public final void zzc(long j, long j2) {
        this.zzaxv.clear();
        this.zzaya = 0;
        this.zzarg = 0;
        this.zzarf = 0;
        if (j == 0) {
            zzhb();
            return;
        }
        zzll[] zzllArr = this.zzayv;
        if (zzllArr != null) {
            for (zzll zzll : zzllArr) {
                zzls zzls = zzll.zzbab;
                int zzec = zzls.zzec(j2);
                if (zzec == -1) {
                    zzec = zzls.zzed(j2);
                }
                zzll.zzaxe = zzec;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:151:0x0196 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02ac A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0006 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0006 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0193  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(com.google.android.gms.internal.ads.zzjy r24, com.google.android.gms.internal.ads.zzkb r25) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
        L_0x0006:
            int r3 = r0.zzaxx
            r4 = -1
            r5 = 8
            r6 = 1
            if (r3 == 0) goto L_0x0198
            r8 = 262144(0x40000, double:1.295163E-318)
            r10 = 2
            if (r3 == r6) goto L_0x0115
            if (r3 != r10) goto L_0x010f
            r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r3 = 0
            r5 = -1
        L_0x001d:
            com.google.android.gms.internal.ads.zzll[] r14 = r0.zzayv
            int r15 = r14.length
            if (r3 >= r15) goto L_0x003b
            r14 = r14[r3]
            int r15 = r14.zzaxe
            com.google.android.gms.internal.ads.zzls r11 = r14.zzbab
            int r11 = r11.zzaxc
            if (r15 == r11) goto L_0x0038
            com.google.android.gms.internal.ads.zzls r11 = r14.zzbab
            long[] r11 = r11.zzaoo
            r14 = r11[r15]
            int r11 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r11 >= 0) goto L_0x0038
            r5 = r3
            r12 = r14
        L_0x0038:
            int r3 = r3 + 1
            goto L_0x001d
        L_0x003b:
            if (r5 != r4) goto L_0x003e
            return r4
        L_0x003e:
            r3 = r14[r5]
            com.google.android.gms.internal.ads.zzkg r4 = r3.zzbac
            int r5 = r3.zzaxe
            com.google.android.gms.internal.ads.zzls r11 = r3.zzbab
            long[] r11 = r11.zzaoo
            r12 = r11[r5]
            com.google.android.gms.internal.ads.zzls r11 = r3.zzbab
            int[] r11 = r11.zzaon
            r11 = r11[r5]
            com.google.android.gms.internal.ads.zzln r14 = r3.zzaym
            int r14 = r14.zzbae
            if (r14 != r6) goto L_0x005b
            r14 = 8
            long r12 = r12 + r14
            int r11 = r11 + -8
        L_0x005b:
            long r14 = r24.getPosition()
            long r14 = r12 - r14
            int r10 = r0.zzarg
            long r6 = (long) r10
            long r14 = r14 + r6
            r6 = 0
            int r10 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r10 < 0) goto L_0x010b
            int r6 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r6 < 0) goto L_0x0071
            goto L_0x010b
        L_0x0071:
            int r2 = (int) r14
            r1.zzai(r2)
            com.google.android.gms.internal.ads.zzln r2 = r3.zzaym
            int r2 = r2.zzasr
            if (r2 == 0) goto L_0x00d2
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzapr
            byte[] r2 = r2.data
            r6 = 0
            r2[r6] = r6
            r7 = 1
            r2[r7] = r6
            r7 = 2
            r2[r7] = r6
            com.google.android.gms.internal.ads.zzln r2 = r3.zzaym
            int r2 = r2.zzasr
            com.google.android.gms.internal.ads.zzln r6 = r3.zzaym
            int r6 = r6.zzasr
            r7 = 4
            int r6 = 4 - r6
        L_0x0093:
            int r7 = r0.zzarg
            if (r7 >= r11) goto L_0x00e8
            int r7 = r0.zzarf
            if (r7 != 0) goto L_0x00c2
            com.google.android.gms.internal.ads.zzpk r7 = r0.zzapr
            byte[] r7 = r7.data
            r1.readFully(r7, r6, r2)
            com.google.android.gms.internal.ads.zzpk r7 = r0.zzapr
            r8 = 0
            r7.zzbo(r8)
            com.google.android.gms.internal.ads.zzpk r7 = r0.zzapr
            int r7 = r7.zzjf()
            r0.zzarf = r7
            com.google.android.gms.internal.ads.zzpk r7 = r0.zzapq
            r7.zzbo(r8)
            com.google.android.gms.internal.ads.zzpk r7 = r0.zzapq
            r9 = 4
            r4.zza(r7, r9)
            int r7 = r0.zzarg
            int r7 = r7 + r9
            r0.zzarg = r7
            int r11 = r11 + r6
            goto L_0x0093
        L_0x00c2:
            r8 = 0
            int r7 = r4.zza(r1, r7, r8)
            int r8 = r0.zzarg
            int r8 = r8 + r7
            r0.zzarg = r8
            int r8 = r0.zzarf
            int r8 = r8 - r7
            r0.zzarf = r8
            goto L_0x0093
        L_0x00d2:
            int r2 = r0.zzarg
            if (r2 >= r11) goto L_0x00e8
            int r2 = r11 - r2
            r6 = 0
            int r2 = r4.zza(r1, r2, r6)
            int r6 = r0.zzarg
            int r6 = r6 + r2
            r0.zzarg = r6
            int r6 = r0.zzarf
            int r6 = r6 - r2
            r0.zzarf = r6
            goto L_0x00d2
        L_0x00e8:
            r20 = r11
            com.google.android.gms.internal.ads.zzls r1 = r3.zzbab
            long[] r1 = r1.zzbbh
            r17 = r1[r5]
            com.google.android.gms.internal.ads.zzls r1 = r3.zzbab
            int[] r1 = r1.zzaxk
            r19 = r1[r5]
            r21 = 0
            r22 = 0
            r16 = r4
            r16.zza(r17, r19, r20, r21, r22)
            int r1 = r3.zzaxe
            r4 = 1
            int r1 = r1 + r4
            r3.zzaxe = r1
            r1 = 0
            r0.zzarg = r1
            r0.zzarf = r1
            return r1
        L_0x010b:
            r4 = 1
            r2.position = r12
            return r4
        L_0x010f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0115:
            long r3 = r0.zzaxz
            int r6 = r0.zzaya
            long r6 = (long) r6
            long r3 = r3 - r6
            long r6 = r24.getPosition()
            long r6 = r6 + r3
            com.google.android.gms.internal.ads.zzpk r10 = r0.zzayb
            if (r10 == 0) goto L_0x0175
            byte[] r8 = r10.data
            int r9 = r0.zzaya
            int r4 = (int) r3
            r1.readFully(r8, r9, r4)
            int r3 = r0.zzaxy
            int r4 = com.google.android.gms.internal.ads.zzkr.zzass
            if (r3 != r4) goto L_0x0158
            com.google.android.gms.internal.ads.zzpk r3 = r0.zzayb
            r3.zzbo(r5)
            int r4 = r3.readInt()
            int r5 = zzayu
            if (r4 != r5) goto L_0x0141
        L_0x013f:
            r3 = 1
            goto L_0x0155
        L_0x0141:
            r4 = 4
            r3.zzbp(r4)
        L_0x0145:
            int r4 = r3.zzja()
            if (r4 <= 0) goto L_0x0154
            int r4 = r3.readInt()
            int r5 = zzayu
            if (r4 != r5) goto L_0x0145
            goto L_0x013f
        L_0x0154:
            r3 = 0
        L_0x0155:
            r0.zzayw = r3
            goto L_0x017d
        L_0x0158:
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r3 = r0.zzaxv
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x017d
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r3 = r0.zzaxv
            java.lang.Object r3 = r3.peek()
            com.google.android.gms.internal.ads.zzku r3 = (com.google.android.gms.internal.ads.zzku) r3
            com.google.android.gms.internal.ads.zzkt r4 = new com.google.android.gms.internal.ads.zzkt
            int r5 = r0.zzaxy
            com.google.android.gms.internal.ads.zzpk r8 = r0.zzayb
            r4.<init>(r5, r8)
            r3.zza((com.google.android.gms.internal.ads.zzkt) r4)
            goto L_0x017d
        L_0x0175:
            int r5 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r5 >= 0) goto L_0x017f
            int r4 = (int) r3
            r1.zzai(r4)
        L_0x017d:
            r3 = 0
            goto L_0x0187
        L_0x017f:
            long r8 = r24.getPosition()
            long r8 = r8 + r3
            r2.position = r8
            r3 = 1
        L_0x0187:
            r0.zzeb(r6)
            if (r3 == 0) goto L_0x0193
            int r3 = r0.zzaxx
            r4 = 2
            if (r3 == r4) goto L_0x0193
            r7 = 1
            goto L_0x0194
        L_0x0193:
            r7 = 0
        L_0x0194:
            if (r7 == 0) goto L_0x0006
            r3 = 1
            return r3
        L_0x0198:
            r3 = 1
            int r6 = r0.zzaya
            if (r6 != 0) goto L_0x01c2
            com.google.android.gms.internal.ads.zzpk r6 = r0.zzaxt
            byte[] r6 = r6.data
            r7 = 0
            boolean r6 = r1.zza(r6, r7, r5, r3)
            if (r6 != 0) goto L_0x01ab
            r6 = 0
            goto L_0x02aa
        L_0x01ab:
            r0.zzaya = r5
            com.google.android.gms.internal.ads.zzpk r3 = r0.zzaxt
            r3.zzbo(r7)
            com.google.android.gms.internal.ads.zzpk r3 = r0.zzaxt
            long r6 = r3.zzjc()
            r0.zzaxz = r6
            com.google.android.gms.internal.ads.zzpk r3 = r0.zzaxt
            int r3 = r3.readInt()
            r0.zzaxy = r3
        L_0x01c2:
            long r6 = r0.zzaxz
            r8 = 1
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x01de
            com.google.android.gms.internal.ads.zzpk r3 = r0.zzaxt
            byte[] r3 = r3.data
            r1.readFully(r3, r5, r5)
            int r3 = r0.zzaya
            int r3 = r3 + r5
            r0.zzaya = r3
            com.google.android.gms.internal.ads.zzpk r3 = r0.zzaxt
            long r6 = r3.zzjg()
            r0.zzaxz = r6
        L_0x01de:
            int r3 = r0.zzaxy
            int r6 = com.google.android.gms.internal.ads.zzkr.zzatt
            if (r3 == r6) goto L_0x01fb
            int r6 = com.google.android.gms.internal.ads.zzkr.zzatv
            if (r3 == r6) goto L_0x01fb
            int r6 = com.google.android.gms.internal.ads.zzkr.zzatw
            if (r3 == r6) goto L_0x01fb
            int r6 = com.google.android.gms.internal.ads.zzkr.zzatx
            if (r3 == r6) goto L_0x01fb
            int r6 = com.google.android.gms.internal.ads.zzkr.zzaty
            if (r3 == r6) goto L_0x01fb
            int r6 = com.google.android.gms.internal.ads.zzkr.zzauh
            if (r3 != r6) goto L_0x01f9
            goto L_0x01fb
        L_0x01f9:
            r6 = 0
            goto L_0x01fc
        L_0x01fb:
            r6 = 1
        L_0x01fc:
            if (r6 == 0) goto L_0x0228
            long r5 = r24.getPosition()
            long r7 = r0.zzaxz
            long r5 = r5 + r7
            int r3 = r0.zzaya
            long r7 = (long) r3
            long r5 = r5 - r7
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r3 = r0.zzaxv
            com.google.android.gms.internal.ads.zzku r7 = new com.google.android.gms.internal.ads.zzku
            int r8 = r0.zzaxy
            r7.<init>(r8, r5)
            r3.add(r7)
            long r7 = r0.zzaxz
            int r3 = r0.zzaya
            long r9 = (long) r3
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x0222
            r0.zzeb(r5)
            goto L_0x0225
        L_0x0222:
            r23.zzhb()
        L_0x0225:
            r3 = 1
            goto L_0x02a9
        L_0x0228:
            int r3 = r0.zzaxy
            int r6 = com.google.android.gms.internal.ads.zzkr.zzauj
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzatu
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzauk
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzaul
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzave
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzavf
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzavg
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzaui
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzavh
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzavi
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzavj
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzavk
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzavl
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzaug
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzass
            if (r3 == r6) goto L_0x026d
            int r6 = com.google.android.gms.internal.ads.zzkr.zzavs
            if (r3 != r6) goto L_0x026b
            goto L_0x026d
        L_0x026b:
            r6 = 0
            goto L_0x026e
        L_0x026d:
            r6 = 1
        L_0x026e:
            if (r6 == 0) goto L_0x02a3
            int r3 = r0.zzaya
            if (r3 != r5) goto L_0x0276
            r6 = 1
            goto L_0x0277
        L_0x0276:
            r6 = 0
        L_0x0277:
            com.google.android.gms.internal.ads.zzoz.checkState(r6)
            long r6 = r0.zzaxz
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 > 0) goto L_0x0285
            r6 = 1
            goto L_0x0286
        L_0x0285:
            r6 = 0
        L_0x0286:
            com.google.android.gms.internal.ads.zzoz.checkState(r6)
            com.google.android.gms.internal.ads.zzpk r3 = new com.google.android.gms.internal.ads.zzpk
            long r6 = r0.zzaxz
            int r7 = (int) r6
            r3.<init>((int) r7)
            r0.zzayb = r3
            com.google.android.gms.internal.ads.zzpk r3 = r0.zzaxt
            byte[] r3 = r3.data
            com.google.android.gms.internal.ads.zzpk r6 = r0.zzayb
            byte[] r6 = r6.data
            r7 = 0
            java.lang.System.arraycopy(r3, r7, r6, r7, r5)
            r3 = 1
            r0.zzaxx = r3
            goto L_0x02a9
        L_0x02a3:
            r3 = 1
            r5 = 0
            r0.zzayb = r5
            r0.zzaxx = r3
        L_0x02a9:
            r6 = 1
        L_0x02aa:
            if (r6 != 0) goto L_0x0006
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlj.zza(com.google.android.gms.internal.ads.zzjy, com.google.android.gms.internal.ads.zzkb):int");
    }

    public final long getDurationUs() {
        return this.zzaih;
    }

    public final long zzdz(long j) {
        long j2 = Long.MAX_VALUE;
        for (zzll zzll : this.zzayv) {
            zzls zzls = zzll.zzbab;
            int zzec = zzls.zzec(j);
            if (zzec == -1) {
                zzec = zzls.zzed(j);
            }
            long j3 = zzls.zzaoo[zzec];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    private final void zzhb() {
        this.zzaxx = 0;
        this.zzaya = 0;
    }

    private final void zzeb(long j) throws zzhv {
        zzmc zzmc;
        zzjz zzjz;
        zzln zza;
        while (!this.zzaxv.isEmpty() && this.zzaxv.peek().zzawj == j) {
            zzku pop = this.zzaxv.pop();
            if (pop.type == zzkr.zzatt) {
                long j2 = -9223372036854775807L;
                ArrayList arrayList = new ArrayList();
                long j3 = Long.MAX_VALUE;
                zzmc zzmc2 = null;
                zzjz zzjz2 = new zzjz();
                zzkt zzau = pop.zzau(zzkr.zzavs);
                if (!(zzau == null || (zzmc2 = zzkw.zza(zzau, this.zzayw)) == null)) {
                    zzjz2.zzb(zzmc2);
                }
                int i = 0;
                while (i < pop.zzawl.size()) {
                    zzku zzku = pop.zzawl.get(i);
                    if (zzku.type == zzkr.zzatv && (zza = zzkw.zza(zzku, pop.zzau(zzkr.zzatu), -9223372036854775807L, (zzjn) null, this.zzayw)) != null) {
                        zzls zza2 = zzkw.zza(zza, zzku.zzav(zzkr.zzatw).zzav(zzkr.zzatx).zzav(zzkr.zzaty), zzjz2);
                        if (zza2.zzaxc != 0) {
                            zzll zzll = new zzll(zza, zza2, this.zzarj.zzc(i, zza.type));
                            zzho zzv = zza.zzahx.zzv(zza2.zzaxi + 30);
                            if (zza.type == 1) {
                                if (zzjz2.zzgs()) {
                                    zzv = zzv.zzb(zzjz2.zzahp, zzjz2.zzahq);
                                }
                                if (zzmc2 != null) {
                                    zzv = zzv.zza(zzmc2);
                                }
                            }
                            zzll.zzbac.zze(zzv);
                            zzmc = zzmc2;
                            zzjz = zzjz2;
                            j2 = Math.max(j2, zza.zzaih);
                            arrayList.add(zzll);
                            long j4 = zza2.zzaoo[0];
                            if (j4 < j3) {
                                j3 = j4;
                            }
                            i++;
                            zzjz2 = zzjz;
                            zzmc2 = zzmc;
                        }
                    }
                    zzmc = zzmc2;
                    zzjz = zzjz2;
                    i++;
                    zzjz2 = zzjz;
                    zzmc2 = zzmc;
                }
                this.zzaih = j2;
                this.zzayv = (zzll[]) arrayList.toArray(new zzll[arrayList.size()]);
                this.zzarj.zzgr();
                this.zzarj.zza(this);
                this.zzaxv.clear();
                this.zzaxx = 2;
            } else if (!this.zzaxv.isEmpty()) {
                this.zzaxv.peek().zza(pop);
            }
        }
        if (this.zzaxx != 2) {
            zzhb();
        }
    }
}
