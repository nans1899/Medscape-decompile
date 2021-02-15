package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzlg implements zzjv {
    private static final zzka zzapj = new zzlf();
    private static final int zzaxl = zzpq.zzbl("seig");
    private static final byte[] zzaxm = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, -12};
    private final int flags;
    private long zzaih;
    private final zzpk zzapq;
    private int zzarf;
    private int zzarg;
    private zzjx zzarj;
    private final zzln zzaxn;
    private final SparseArray<zzlh> zzaxo;
    private final zzpk zzaxp;
    private final zzpk zzaxq;
    private final zzpk zzaxr;
    private final zzpo zzaxs;
    private final zzpk zzaxt;
    private final byte[] zzaxu;
    private final Stack<zzku> zzaxv;
    private final LinkedList<zzli> zzaxw;
    private int zzaxx;
    private int zzaxy;
    private long zzaxz;
    private int zzaya;
    private zzpk zzayb;
    private long zzayc;
    private int zzayd;
    private long zzaye;
    private zzlh zzayf;
    private int zzayg;
    private boolean zzayh;
    private zzkg zzayi;
    private zzkg[] zzayj;
    private boolean zzayk;

    public zzlg() {
        this(0);
    }

    public final void release() {
    }

    private zzlg(int i) {
        this(0, (zzpo) null);
    }

    private zzlg(int i, zzpo zzpo) {
        this(0, (zzpo) null, (zzln) null);
    }

    private zzlg(int i, zzpo zzpo, zzln zzln) {
        this.flags = i;
        this.zzaxs = null;
        this.zzaxn = null;
        this.zzaxt = new zzpk(16);
        this.zzapq = new zzpk(zzpf.zzbjr);
        this.zzaxp = new zzpk(5);
        this.zzaxq = new zzpk();
        this.zzaxr = new zzpk(1);
        this.zzaxu = new byte[16];
        this.zzaxv = new Stack<>();
        this.zzaxw = new LinkedList<>();
        this.zzaxo = new SparseArray<>();
        this.zzaih = -9223372036854775807L;
        this.zzaye = -9223372036854775807L;
        zzhb();
    }

    public final boolean zza(zzjy zzjy) throws IOException, InterruptedException {
        return zzlo.zzd(zzjy);
    }

    public final void zza(zzjx zzjx) {
        this.zzarj = zzjx;
    }

    public final void zzc(long j, long j2) {
        int size = this.zzaxo.size();
        for (int i = 0; i < size; i++) {
            this.zzaxo.valueAt(i).reset();
        }
        this.zzaxw.clear();
        this.zzayd = 0;
        this.zzaxv.clear();
        zzhb();
    }

    /* JADX WARNING: Removed duplicated region for block: B:258:0x0299 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x05e8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0004 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x0004 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(com.google.android.gms.internal.ads.zzjy r27, com.google.android.gms.internal.ads.zzkb r28) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
        L_0x0004:
            int r2 = r0.zzaxx
            r3 = 2
            r5 = 8
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x045b
            r8 = 4
            if (r2 == r6) goto L_0x02fc
            r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r11 = 3
            if (r2 == r3) goto L_0x02a0
            if (r2 != r11) goto L_0x010a
            com.google.android.gms.internal.ads.zzlh r2 = r0.zzayf
            if (r2 != 0) goto L_0x0084
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r2 = r0.zzaxo
            int r12 = r2.size()
            r13 = 0
            r14 = 0
        L_0x0026:
            if (r13 >= r12) goto L_0x0049
            java.lang.Object r15 = r2.valueAt(r13)
            com.google.android.gms.internal.ads.zzlh r15 = (com.google.android.gms.internal.ads.zzlh) r15
            int r11 = r15.zzayq
            com.google.android.gms.internal.ads.zzlp r4 = r15.zzayl
            int r4 = r4.zzban
            if (r11 == r4) goto L_0x0045
            com.google.android.gms.internal.ads.zzlp r4 = r15.zzayl
            long[] r4 = r4.zzbao
            int r11 = r15.zzayq
            r17 = r4[r11]
            int r4 = (r17 > r9 ? 1 : (r17 == r9 ? 0 : -1))
            if (r4 >= 0) goto L_0x0045
            r14 = r15
            r9 = r17
        L_0x0045:
            int r13 = r13 + 1
            r11 = 3
            goto L_0x0026
        L_0x0049:
            if (r14 != 0) goto L_0x0067
            long r2 = r0.zzayc
            long r4 = r27.getPosition()
            long r2 = r2 - r4
            int r3 = (int) r2
            if (r3 < 0) goto L_0x005f
            r1.zzai(r3)
            r26.zzhb()
            r3 = 0
            r6 = 0
            goto L_0x0297
        L_0x005f:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "Offset to end of mdat was negative."
            r1.<init>(r2)
            throw r1
        L_0x0067:
            com.google.android.gms.internal.ads.zzlp r2 = r14.zzayl
            long[] r2 = r2.zzbao
            int r4 = r14.zzayq
            r9 = r2[r4]
            long r11 = r27.getPosition()
            long r9 = r9 - r11
            int r2 = (int) r9
            if (r2 >= 0) goto L_0x007f
            java.lang.String r2 = "FragmentedMp4Extractor"
            java.lang.String r4 = "Ignoring negative offset to sample data."
            android.util.Log.w(r2, r4)
            r2 = 0
        L_0x007f:
            r1.zzai(r2)
            r0.zzayf = r14
        L_0x0084:
            com.google.android.gms.internal.ads.zzlh r2 = r0.zzayf
            com.google.android.gms.internal.ads.zzlp r2 = r2.zzayl
            int[] r2 = r2.zzbaq
            com.google.android.gms.internal.ads.zzlh r4 = r0.zzayf
            int r4 = r4.zzayo
            r2 = r2[r4]
            r0.zzayg = r2
            com.google.android.gms.internal.ads.zzlh r2 = r0.zzayf
            com.google.android.gms.internal.ads.zzlp r2 = r2.zzayl
            boolean r2 = r2.zzbau
            if (r2 == 0) goto L_0x00f4
            com.google.android.gms.internal.ads.zzlh r2 = r0.zzayf
            com.google.android.gms.internal.ads.zzlp r4 = r2.zzayl
            com.google.android.gms.internal.ads.zzpk r9 = r4.zzbay
            com.google.android.gms.internal.ads.zzlb r10 = r4.zzbaj
            int r10 = r10.zzaxh
            com.google.android.gms.internal.ads.zzlq r11 = r4.zzbaw
            if (r11 == 0) goto L_0x00ab
            com.google.android.gms.internal.ads.zzlq r10 = r4.zzbaw
            goto L_0x00b1
        L_0x00ab:
            com.google.android.gms.internal.ads.zzln r11 = r2.zzaym
            com.google.android.gms.internal.ads.zzlq[] r11 = r11.zzbaf
            r10 = r11[r10]
        L_0x00b1:
            int r10 = r10.zzbbc
            boolean[] r4 = r4.zzbav
            int r11 = r2.zzayo
            boolean r4 = r4[r11]
            com.google.android.gms.internal.ads.zzpk r11 = r0.zzaxr
            byte[] r11 = r11.data
            if (r4 == 0) goto L_0x00c2
            r12 = 128(0x80, float:1.794E-43)
            goto L_0x00c3
        L_0x00c2:
            r12 = 0
        L_0x00c3:
            r12 = r12 | r10
            byte r12 = (byte) r12
            r11[r7] = r12
            com.google.android.gms.internal.ads.zzpk r11 = r0.zzaxr
            r11.zzbo(r7)
            com.google.android.gms.internal.ads.zzkg r2 = r2.zzasq
            com.google.android.gms.internal.ads.zzpk r11 = r0.zzaxr
            r2.zza(r11, r6)
            r2.zza(r9, r10)
            if (r4 != 0) goto L_0x00db
            int r10 = r10 + 1
            goto L_0x00ec
        L_0x00db:
            int r4 = r9.readUnsignedShort()
            r11 = -2
            r9.zzbp(r11)
            int r4 = r4 * 6
            int r4 = r4 + r3
            r2.zza(r9, r4)
            int r10 = r10 + 1
            int r10 = r10 + r4
        L_0x00ec:
            r0.zzarg = r10
            int r2 = r0.zzayg
            int r2 = r2 + r10
            r0.zzayg = r2
            goto L_0x00f6
        L_0x00f4:
            r0.zzarg = r7
        L_0x00f6:
            com.google.android.gms.internal.ads.zzlh r2 = r0.zzayf
            com.google.android.gms.internal.ads.zzln r2 = r2.zzaym
            int r2 = r2.zzbae
            if (r2 != r6) goto L_0x0106
            int r2 = r0.zzayg
            int r2 = r2 - r5
            r0.zzayg = r2
            r1.zzai(r5)
        L_0x0106:
            r0.zzaxx = r8
            r0.zzarf = r7
        L_0x010a:
            com.google.android.gms.internal.ads.zzlh r2 = r0.zzayf
            com.google.android.gms.internal.ads.zzlp r2 = r2.zzayl
            com.google.android.gms.internal.ads.zzlh r4 = r0.zzayf
            com.google.android.gms.internal.ads.zzln r4 = r4.zzaym
            com.google.android.gms.internal.ads.zzlh r5 = r0.zzayf
            com.google.android.gms.internal.ads.zzkg r9 = r5.zzasq
            com.google.android.gms.internal.ads.zzlh r5 = r0.zzayf
            int r5 = r5.zzayo
            int r10 = r4.zzasr
            r11 = 1000(0x3e8, double:4.94E-321)
            if (r10 == 0) goto L_0x01db
            com.google.android.gms.internal.ads.zzpk r10 = r0.zzaxp
            byte[] r10 = r10.data
            r10[r7] = r7
            r10[r6] = r7
            r10[r3] = r7
            int r3 = r4.zzasr
            int r3 = r3 + r6
            int r13 = r4.zzasr
            int r13 = 4 - r13
        L_0x0131:
            int r14 = r0.zzarg
            int r15 = r0.zzayg
            if (r14 >= r15) goto L_0x01ed
            int r14 = r0.zzarf
            if (r14 != 0) goto L_0x017c
            r1.readFully(r10, r13, r3)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzaxp
            r14.zzbo(r7)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzaxp
            int r14 = r14.zzjf()
            int r14 = r14 - r6
            r0.zzarf = r14
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzapq
            r14.zzbo(r7)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzapq
            r9.zza(r14, r8)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzaxp
            r9.zza(r14, r6)
            com.google.android.gms.internal.ads.zzkg[] r14 = r0.zzayj
            if (r14 == 0) goto L_0x016d
            com.google.android.gms.internal.ads.zzho r14 = r4.zzahx
            java.lang.String r14 = r14.zzahc
            byte r15 = r10[r8]
            boolean r14 = com.google.android.gms.internal.ads.zzpf.zza(r14, r15)
            if (r14 == 0) goto L_0x016d
            r14 = 1
            goto L_0x016e
        L_0x016d:
            r14 = 0
        L_0x016e:
            r0.zzayh = r14
            int r14 = r0.zzarg
            int r14 = r14 + 5
            r0.zzarg = r14
            int r14 = r0.zzayg
            int r14 = r14 + r13
            r0.zzayg = r14
            goto L_0x0131
        L_0x017c:
            boolean r15 = r0.zzayh
            if (r15 == 0) goto L_0x01c7
            com.google.android.gms.internal.ads.zzpk r15 = r0.zzaxq
            r15.reset(r14)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzaxq
            byte[] r14 = r14.data
            int r15 = r0.zzarf
            r1.readFully(r14, r7, r15)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzaxq
            int r15 = r0.zzarf
            r9.zza(r14, r15)
            int r14 = r0.zzarf
            com.google.android.gms.internal.ads.zzpk r15 = r0.zzaxq
            byte[] r15 = r15.data
            com.google.android.gms.internal.ads.zzpk r8 = r0.zzaxq
            int r8 = r8.limit()
            int r8 = com.google.android.gms.internal.ads.zzpf.zzb(r15, r8)
            com.google.android.gms.internal.ads.zzpk r15 = r0.zzaxq
            com.google.android.gms.internal.ads.zzho r6 = r4.zzahx
            java.lang.String r6 = r6.zzahc
            java.lang.String r7 = "video/hevc"
            boolean r6 = r7.equals(r6)
            r15.zzbo(r6)
            com.google.android.gms.internal.ads.zzpk r6 = r0.zzaxq
            r6.zzbn(r8)
            long r6 = r2.zzax(r5)
            long r6 = r6 * r11
            com.google.android.gms.internal.ads.zzpk r8 = r0.zzaxq
            com.google.android.gms.internal.ads.zzkg[] r15 = r0.zzayj
            com.google.android.gms.internal.ads.zznr.zza(r6, r8, r15)
            goto L_0x01cc
        L_0x01c7:
            r6 = 0
            int r14 = r9.zza(r1, r14, r6)
        L_0x01cc:
            int r6 = r0.zzarg
            int r6 = r6 + r14
            r0.zzarg = r6
            int r6 = r0.zzarf
            int r6 = r6 - r14
            r0.zzarf = r6
            r6 = 1
            r7 = 0
            r8 = 4
            goto L_0x0131
        L_0x01db:
            int r3 = r0.zzarg
            int r6 = r0.zzayg
            if (r3 >= r6) goto L_0x01ed
            int r6 = r6 - r3
            r3 = 0
            int r6 = r9.zza(r1, r6, r3)
            int r3 = r0.zzarg
            int r3 = r3 + r6
            r0.zzarg = r3
            goto L_0x01db
        L_0x01ed:
            long r6 = r2.zzax(r5)
            long r6 = r6 * r11
            com.google.android.gms.internal.ads.zzpo r3 = r0.zzaxs
            if (r3 != 0) goto L_0x029a
            boolean r3 = r2.zzbau
            if (r3 == 0) goto L_0x01fe
            r3 = 1073741824(0x40000000, float:2.0)
            goto L_0x01ff
        L_0x01fe:
            r3 = 0
        L_0x01ff:
            boolean[] r8 = r2.zzbat
            boolean r5 = r8[r5]
            r12 = r3 | r5
            boolean r3 = r2.zzbau
            if (r3 == 0) goto L_0x022d
            com.google.android.gms.internal.ads.zzlq r3 = r2.zzbaw
            if (r3 == 0) goto L_0x0210
            com.google.android.gms.internal.ads.zzlq r3 = r2.zzbaw
            goto L_0x0218
        L_0x0210:
            com.google.android.gms.internal.ads.zzlq[] r3 = r4.zzbaf
            com.google.android.gms.internal.ads.zzlb r4 = r2.zzbaj
            int r4 = r4.zzaxh
            r3 = r3[r4]
        L_0x0218:
            com.google.android.gms.internal.ads.zzlh r4 = r0.zzayf
            com.google.android.gms.internal.ads.zzlq r4 = r4.zzays
            if (r3 == r4) goto L_0x0227
            com.google.android.gms.internal.ads.zzkf r4 = new com.google.android.gms.internal.ads.zzkf
            byte[] r5 = r3.zzbbd
            r8 = 1
            r4.<init>(r8, r5)
            goto L_0x022b
        L_0x0227:
            com.google.android.gms.internal.ads.zzlh r4 = r0.zzayf
            com.google.android.gms.internal.ads.zzkf r4 = r4.zzayr
        L_0x022b:
            r15 = r4
            goto L_0x022f
        L_0x022d:
            r3 = 0
            r15 = 0
        L_0x022f:
            com.google.android.gms.internal.ads.zzlh r4 = r0.zzayf
            r4.zzayr = r15
            com.google.android.gms.internal.ads.zzlh r4 = r0.zzayf
            r4.zzays = r3
            int r13 = r0.zzayg
            r14 = 0
            r10 = r6
            r9.zza(r10, r12, r13, r14, r15)
        L_0x023e:
            java.util.LinkedList<com.google.android.gms.internal.ads.zzli> r3 = r0.zzaxw
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0265
            java.util.LinkedList<com.google.android.gms.internal.ads.zzli> r3 = r0.zzaxw
            java.lang.Object r3 = r3.removeFirst()
            com.google.android.gms.internal.ads.zzli r3 = (com.google.android.gms.internal.ads.zzli) r3
            int r4 = r0.zzayd
            int r5 = r3.size
            int r4 = r4 - r5
            r0.zzayd = r4
            com.google.android.gms.internal.ads.zzkg r8 = r0.zzayi
            long r4 = r3.zzayt
            long r9 = r6 + r4
            r11 = 1
            int r12 = r3.size
            int r13 = r0.zzayd
            r14 = 0
            r8.zza(r9, r11, r12, r13, r14)
            goto L_0x023e
        L_0x0265:
            com.google.android.gms.internal.ads.zzlh r3 = r0.zzayf
            int r4 = r3.zzayo
            r5 = 1
            int r4 = r4 + r5
            r3.zzayo = r4
            com.google.android.gms.internal.ads.zzlh r3 = r0.zzayf
            int r4 = r3.zzayp
            int r4 = r4 + r5
            r3.zzayp = r4
            com.google.android.gms.internal.ads.zzlh r3 = r0.zzayf
            int r3 = r3.zzayp
            int[] r2 = r2.zzbap
            com.google.android.gms.internal.ads.zzlh r4 = r0.zzayf
            int r4 = r4.zzayq
            r2 = r2[r4]
            if (r3 != r2) goto L_0x0292
            com.google.android.gms.internal.ads.zzlh r2 = r0.zzayf
            int r3 = r2.zzayq
            int r3 = r3 + r5
            r2.zzayq = r3
            com.google.android.gms.internal.ads.zzlh r2 = r0.zzayf
            r3 = 0
            r2.zzayp = r3
            r2 = 0
            r0.zzayf = r2
            goto L_0x0293
        L_0x0292:
            r3 = 0
        L_0x0293:
            r2 = 3
            r0.zzaxx = r2
            r6 = 1
        L_0x0297:
            if (r6 == 0) goto L_0x0004
            return r3
        L_0x029a:
            java.lang.NoSuchMethodError r1 = new java.lang.NoSuchMethodError
            r1.<init>()
            throw r1
        L_0x02a0:
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r2 = r0.zzaxo
            int r2 = r2.size()
            r3 = 0
            r4 = 0
        L_0x02a8:
            if (r3 >= r2) goto L_0x02cd
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r5 = r0.zzaxo
            java.lang.Object r5 = r5.valueAt(r3)
            com.google.android.gms.internal.ads.zzlh r5 = (com.google.android.gms.internal.ads.zzlh) r5
            com.google.android.gms.internal.ads.zzlp r5 = r5.zzayl
            boolean r6 = r5.zzbaz
            if (r6 == 0) goto L_0x02ca
            long r6 = r5.zzbam
            int r8 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r8 >= 0) goto L_0x02ca
            long r4 = r5.zzbam
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r6 = r0.zzaxo
            java.lang.Object r6 = r6.valueAt(r3)
            com.google.android.gms.internal.ads.zzlh r6 = (com.google.android.gms.internal.ads.zzlh) r6
            r9 = r4
            r4 = r6
        L_0x02ca:
            int r3 = r3 + 1
            goto L_0x02a8
        L_0x02cd:
            if (r4 != 0) goto L_0x02d4
            r2 = 3
            r0.zzaxx = r2
            goto L_0x0004
        L_0x02d4:
            long r2 = r27.getPosition()
            long r9 = r9 - r2
            int r2 = (int) r9
            if (r2 < 0) goto L_0x02f4
            r1.zzai(r2)
            com.google.android.gms.internal.ads.zzlp r2 = r4.zzayl
            com.google.android.gms.internal.ads.zzpk r3 = r2.zzbay
            byte[] r3 = r3.data
            int r4 = r2.zzbax
            r5 = 0
            r1.readFully(r3, r5, r4)
            com.google.android.gms.internal.ads.zzpk r3 = r2.zzbay
            r3.zzbo(r5)
            r2.zzbaz = r5
            goto L_0x0004
        L_0x02f4:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "Offset to encryption data was negative."
            r1.<init>(r2)
            throw r1
        L_0x02fc:
            long r6 = r0.zzaxz
            int r2 = (int) r6
            int r4 = r0.zzaya
            int r2 = r2 - r4
            com.google.android.gms.internal.ads.zzpk r4 = r0.zzayb
            if (r4 == 0) goto L_0x044f
            byte[] r4 = r4.data
            r1.readFully(r4, r5, r2)
            com.google.android.gms.internal.ads.zzkt r2 = new com.google.android.gms.internal.ads.zzkt
            int r4 = r0.zzaxy
            com.google.android.gms.internal.ads.zzpk r6 = r0.zzayb
            r2.<init>(r4, r6)
            long r6 = r27.getPosition()
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r4 = r0.zzaxv
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x032d
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r3 = r0.zzaxv
            java.lang.Object r3 = r3.peek()
            com.google.android.gms.internal.ads.zzku r3 = (com.google.android.gms.internal.ads.zzku) r3
            r3.zza((com.google.android.gms.internal.ads.zzkt) r2)
            goto L_0x0452
        L_0x032d:
            int r4 = r2.type
            int r8 = com.google.android.gms.internal.ads.zzkr.zzats
            if (r4 != r8) goto L_0x03f5
            com.google.android.gms.internal.ads.zzpk r2 = r2.zzawi
            r2.zzbo(r5)
            int r4 = r2.readInt()
            int r4 = com.google.android.gms.internal.ads.zzkr.zzaq(r4)
            r5 = 4
            r2.zzbp(r5)
            long r14 = r2.zzjc()
            if (r4 != 0) goto L_0x0353
            long r4 = r2.zzjc()
            long r8 = r2.zzjc()
            goto L_0x035b
        L_0x0353:
            long r4 = r2.zzjg()
            long r8 = r2.zzjg()
        L_0x035b:
            long r6 = r6 + r8
            r10 = 1000000(0xf4240, double:4.940656E-318)
            r8 = r4
            r12 = r14
            long r20 = com.google.android.gms.internal.ads.zzpq.zza((long) r8, (long) r10, (long) r12)
            r2.zzbp(r3)
            int r3 = r2.readUnsignedShort()
            int[] r12 = new int[r3]
            long[] r13 = new long[r3]
            long[] r10 = new long[r3]
            long[] r11 = new long[r3]
            r22 = r20
            r8 = 0
        L_0x0377:
            if (r8 >= r3) goto L_0x03cd
            int r9 = r2.readInt()
            r16 = -2147483648(0xffffffff80000000, float:-0.0)
            r16 = r9 & r16
            if (r16 != 0) goto L_0x03c5
            long r24 = r2.zzjc()
            r16 = 2147483647(0x7fffffff, float:NaN)
            r9 = r9 & r16
            r12[r8] = r9
            r13[r8] = r6
            r11[r8] = r22
            long r4 = r4 + r24
            r22 = 1000000(0xf4240, double:4.940656E-318)
            r19 = r8
            r8 = r4
            r28 = r3
            r24 = r4
            r3 = r10
            r4 = r11
            r10 = r22
            r5 = r12
            r1 = r13
            r12 = r14
            long r22 = com.google.android.gms.internal.ads.zzpq.zza((long) r8, (long) r10, (long) r12)
            r8 = r4[r19]
            long r8 = r22 - r8
            r3[r19] = r8
            r8 = 4
            r2.zzbp(r8)
            r9 = r5[r19]
            long r9 = (long) r9
            long r6 = r6 + r9
            int r9 = r19 + 1
            r13 = r1
            r10 = r3
            r11 = r4
            r12 = r5
            r8 = r9
            r4 = r24
            r1 = r27
            r3 = r28
            goto L_0x0377
        L_0x03c5:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "Unhandled indirect reference"
            r1.<init>(r2)
            throw r1
        L_0x03cd:
            r3 = r10
            r4 = r11
            r5 = r12
            r1 = r13
            java.lang.Long r2 = java.lang.Long.valueOf(r20)
            com.google.android.gms.internal.ads.zzjt r6 = new com.google.android.gms.internal.ads.zzjt
            r6.<init>(r5, r1, r3, r4)
            android.util.Pair r1 = android.util.Pair.create(r2, r6)
            java.lang.Object r2 = r1.first
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            r0.zzaye = r2
            com.google.android.gms.internal.ads.zzjx r2 = r0.zzarj
            java.lang.Object r1 = r1.second
            com.google.android.gms.internal.ads.zzke r1 = (com.google.android.gms.internal.ads.zzke) r1
            r2.zza(r1)
            r1 = 1
            r0.zzayk = r1
            goto L_0x044c
        L_0x03f5:
            int r1 = r2.type
            int r3 = com.google.android.gms.internal.ads.zzkr.zzavy
            if (r1 != r3) goto L_0x044c
            com.google.android.gms.internal.ads.zzpk r1 = r2.zzawi
            com.google.android.gms.internal.ads.zzkg r2 = r0.zzayi
            if (r2 == 0) goto L_0x044c
            r2 = 12
            r1.zzbo(r2)
            r1.zzjh()
            r1.zzjh()
            long r7 = r1.zzjc()
            long r3 = r1.zzjc()
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = com.google.android.gms.internal.ads.zzpq.zza((long) r3, (long) r5, (long) r7)
            r1.zzbo(r2)
            int r9 = r1.zzja()
            com.google.android.gms.internal.ads.zzkg r2 = r0.zzayi
            r2.zza(r1, r9)
            long r1 = r0.zzaye
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x043d
            com.google.android.gms.internal.ads.zzkg r5 = r0.zzayi
            long r6 = r1 + r3
            r8 = 1
            r10 = 0
            r11 = 0
            r5.zza(r6, r8, r9, r10, r11)
            goto L_0x044c
        L_0x043d:
            java.util.LinkedList<com.google.android.gms.internal.ads.zzli> r1 = r0.zzaxw
            com.google.android.gms.internal.ads.zzli r2 = new com.google.android.gms.internal.ads.zzli
            r2.<init>(r3, r9)
            r1.addLast(r2)
            int r1 = r0.zzayd
            int r1 = r1 + r9
            r0.zzayd = r1
        L_0x044c:
            r1 = r27
            goto L_0x0452
        L_0x044f:
            r1.zzai(r2)
        L_0x0452:
            long r2 = r27.getPosition()
            r0.zzeb(r2)
            goto L_0x0004
        L_0x045b:
            int r2 = r0.zzaya
            if (r2 != 0) goto L_0x0484
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaxt
            byte[] r2 = r2.data
            r4 = 1
            r6 = 0
            boolean r2 = r1.zza(r2, r6, r5, r4)
            if (r2 != 0) goto L_0x046d
            goto L_0x05e6
        L_0x046d:
            r0.zzaya = r5
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaxt
            r2.zzbo(r6)
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaxt
            long r6 = r2.zzjc()
            r0.zzaxz = r6
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaxt
            int r2 = r2.readInt()
            r0.zzaxy = r2
        L_0x0484:
            long r6 = r0.zzaxz
            r8 = 1
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x04a0
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaxt
            byte[] r2 = r2.data
            r1.readFully(r2, r5, r5)
            int r2 = r0.zzaya
            int r2 = r2 + r5
            r0.zzaya = r2
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaxt
            long r6 = r2.zzjg()
            r0.zzaxz = r6
        L_0x04a0:
            long r6 = r0.zzaxz
            int r2 = r0.zzaya
            long r8 = (long) r2
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x05f2
            long r6 = r27.getPosition()
            int r2 = r0.zzaya
            long r8 = (long) r2
            long r6 = r6 - r8
            int r2 = r0.zzaxy
            int r4 = com.google.android.gms.internal.ads.zzkr.zzauc
            if (r2 != r4) goto L_0x04d3
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r2 = r0.zzaxo
            int r2 = r2.size()
            r4 = 0
        L_0x04be:
            if (r4 >= r2) goto L_0x04d3
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r8 = r0.zzaxo
            java.lang.Object r8 = r8.valueAt(r4)
            com.google.android.gms.internal.ads.zzlh r8 = (com.google.android.gms.internal.ads.zzlh) r8
            com.google.android.gms.internal.ads.zzlp r8 = r8.zzayl
            r8.zzbak = r6
            r8.zzbam = r6
            r8.zzbal = r6
            int r4 = r4 + 1
            goto L_0x04be
        L_0x04d3:
            int r2 = r0.zzaxy
            int r4 = com.google.android.gms.internal.ads.zzkr.zzasz
            if (r2 != r4) goto L_0x04f9
            r2 = 0
            r0.zzayf = r2
            long r4 = r0.zzaxz
            long r6 = r6 + r4
            r0.zzayc = r6
            boolean r2 = r0.zzayk
            if (r2 != 0) goto L_0x04f4
            com.google.android.gms.internal.ads.zzjx r2 = r0.zzarj
            com.google.android.gms.internal.ads.zzkd r4 = new com.google.android.gms.internal.ads.zzkd
            long r5 = r0.zzaih
            r4.<init>(r5)
            r2.zza(r4)
            r2 = 1
            r0.zzayk = r2
        L_0x04f4:
            r0.zzaxx = r3
        L_0x04f6:
            r2 = 1
            goto L_0x05e5
        L_0x04f9:
            int r2 = r0.zzaxy
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatt
            if (r2 == r3) goto L_0x0522
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatv
            if (r2 == r3) goto L_0x0522
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatw
            if (r2 == r3) goto L_0x0522
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatx
            if (r2 == r3) goto L_0x0522
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaty
            if (r2 == r3) goto L_0x0522
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauc
            if (r2 == r3) goto L_0x0522
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaud
            if (r2 == r3) goto L_0x0522
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaue
            if (r2 == r3) goto L_0x0522
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauh
            if (r2 != r3) goto L_0x0520
            goto L_0x0522
        L_0x0520:
            r2 = 0
            goto L_0x0523
        L_0x0522:
            r2 = 1
        L_0x0523:
            if (r2 == 0) goto L_0x054c
            long r2 = r27.getPosition()
            long r4 = r0.zzaxz
            long r2 = r2 + r4
            r4 = 8
            long r2 = r2 - r4
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r4 = r0.zzaxv
            com.google.android.gms.internal.ads.zzku r5 = new com.google.android.gms.internal.ads.zzku
            int r6 = r0.zzaxy
            r5.<init>(r6, r2)
            r4.add(r5)
            long r4 = r0.zzaxz
            int r6 = r0.zzaya
            long r6 = (long) r6
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0548
            r0.zzeb(r2)
            goto L_0x04f6
        L_0x0548:
            r26.zzhb()
            goto L_0x04f6
        L_0x054c:
            int r2 = r0.zzaxy
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauk
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauj
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatu
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzats
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaul
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzato
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatp
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaug
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatq
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatr
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaum
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauu
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauv
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauz
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauy
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauw
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaux
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaui
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauf
            if (r2 == r3) goto L_0x05a1
            int r3 = com.google.android.gms.internal.ads.zzkr.zzavy
            if (r2 != r3) goto L_0x059f
            goto L_0x05a1
        L_0x059f:
            r2 = 0
            goto L_0x05a2
        L_0x05a1:
            r2 = 1
        L_0x05a2:
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            if (r2 == 0) goto L_0x05d9
            int r2 = r0.zzaya
            if (r2 != r5) goto L_0x05d1
            long r6 = r0.zzaxz
            int r2 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x05c9
            com.google.android.gms.internal.ads.zzpk r2 = new com.google.android.gms.internal.ads.zzpk
            int r3 = (int) r6
            r2.<init>((int) r3)
            r0.zzayb = r2
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaxt
            byte[] r2 = r2.data
            com.google.android.gms.internal.ads.zzpk r3 = r0.zzayb
            byte[] r3 = r3.data
            r4 = 0
            java.lang.System.arraycopy(r2, r4, r3, r4, r5)
            r2 = 1
            r0.zzaxx = r2
            goto L_0x05e5
        L_0x05c9:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "Leaf atom with length > 2147483647 (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x05d1:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "Leaf atom defines extended atom size (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x05d9:
            long r5 = r0.zzaxz
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x05ea
            r2 = 0
            r0.zzayb = r2
            r2 = 1
            r0.zzaxx = r2
        L_0x05e5:
            r6 = 1
        L_0x05e6:
            if (r6 != 0) goto L_0x0004
            r1 = -1
            return r1
        L_0x05ea:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "Skipping atom with length > 2147483647 (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x05f2:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "Atom size less than header length (unsupported)."
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlg.zza(com.google.android.gms.internal.ads.zzjy, com.google.android.gms.internal.ads.zzkb):int");
    }

    private final void zzhb() {
        this.zzaxx = 0;
        this.zzaya = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:157:0x03e0  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x03e2  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x03f0  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x03f3  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x03fb  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x0656  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzeb(long r53) throws com.google.android.gms.internal.ads.zzhv {
        /*
            r52 = this;
            r0 = r52
        L_0x0002:
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r1 = r0.zzaxv
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x06f5
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r1 = r0.zzaxv
            java.lang.Object r1 = r1.peek()
            com.google.android.gms.internal.ads.zzku r1 = (com.google.android.gms.internal.ads.zzku) r1
            long r1 = r1.zzawj
            int r3 = (r1 > r53 ? 1 : (r1 == r53 ? 0 : -1))
            if (r3 != 0) goto L_0x06f5
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r1 = r0.zzaxv
            java.lang.Object r1 = r1.pop()
            com.google.android.gms.internal.ads.zzku r1 = (com.google.android.gms.internal.ads.zzku) r1
            int r2 = r1.type
            int r3 = com.google.android.gms.internal.ads.zzkr.zzatt
            r4 = 0
            r5 = 12
            r6 = 4
            r7 = 8
            r9 = 1
            if (r2 != r3) goto L_0x01c8
            java.lang.String r2 = "Unexpected moov box."
            com.google.android.gms.internal.ads.zzoz.checkState(r9, r2)
            java.util.List<com.google.android.gms.internal.ads.zzkt> r2 = r1.zzawk
            com.google.android.gms.internal.ads.zzjn r2 = zzb(r2)
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaue
            com.google.android.gms.internal.ads.zzku r3 = r1.zzav(r3)
            android.util.SparseArray r15 = new android.util.SparseArray
            r15.<init>()
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.util.List<com.google.android.gms.internal.ads.zzkt> r12 = r3.zzawk
            int r12 = r12.size()
            r16 = r10
            r10 = 0
        L_0x0051:
            if (r10 >= r12) goto L_0x00be
            java.util.List<com.google.android.gms.internal.ads.zzkt> r11 = r3.zzawk
            java.lang.Object r11 = r11.get(r10)
            com.google.android.gms.internal.ads.zzkt r11 = (com.google.android.gms.internal.ads.zzkt) r11
            int r13 = r11.type
            int r14 = com.google.android.gms.internal.ads.zzkr.zzatq
            if (r13 != r14) goto L_0x0098
            com.google.android.gms.internal.ads.zzpk r11 = r11.zzawi
            r11.zzbo(r5)
            int r13 = r11.readInt()
            int r14 = r11.zzjf()
            int r14 = r14 - r9
            int r5 = r11.zzjf()
            int r8 = r11.zzjf()
            int r11 = r11.readInt()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            com.google.android.gms.internal.ads.zzlb r9 = new com.google.android.gms.internal.ads.zzlb
            r9.<init>(r14, r5, r8, r11)
            android.util.Pair r5 = android.util.Pair.create(r13, r9)
            java.lang.Object r8 = r5.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            java.lang.Object r5 = r5.second
            com.google.android.gms.internal.ads.zzlb r5 = (com.google.android.gms.internal.ads.zzlb) r5
            r15.put(r8, r5)
            goto L_0x00b8
        L_0x0098:
            int r5 = r11.type
            int r8 = com.google.android.gms.internal.ads.zzkr.zzauf
            if (r5 != r8) goto L_0x00b8
            com.google.android.gms.internal.ads.zzpk r5 = r11.zzawi
            r5.zzbo(r7)
            int r8 = r5.readInt()
            int r8 = com.google.android.gms.internal.ads.zzkr.zzaq(r8)
            if (r8 != 0) goto L_0x00b2
            long r8 = r5.zzjc()
            goto L_0x00b6
        L_0x00b2:
            long r8 = r5.zzjg()
        L_0x00b6:
            r16 = r8
        L_0x00b8:
            int r10 = r10 + 1
            r5 = 12
            r9 = 1
            goto L_0x0051
        L_0x00be:
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>()
            java.util.List<com.google.android.gms.internal.ads.zzku> r5 = r1.zzawl
            int r5 = r5.size()
            r8 = 0
        L_0x00ca:
            if (r8 >= r5) goto L_0x00fa
            java.util.List<com.google.android.gms.internal.ads.zzku> r9 = r1.zzawl
            java.lang.Object r9 = r9.get(r8)
            r10 = r9
            com.google.android.gms.internal.ads.zzku r10 = (com.google.android.gms.internal.ads.zzku) r10
            int r9 = r10.type
            int r11 = com.google.android.gms.internal.ads.zzkr.zzatv
            if (r9 != r11) goto L_0x00f3
            int r9 = com.google.android.gms.internal.ads.zzkr.zzatu
            com.google.android.gms.internal.ads.zzkt r11 = r1.zzau(r9)
            r9 = 0
            r12 = r16
            r14 = r2
            r7 = r15
            r15 = r9
            com.google.android.gms.internal.ads.zzln r9 = com.google.android.gms.internal.ads.zzkw.zza((com.google.android.gms.internal.ads.zzku) r10, (com.google.android.gms.internal.ads.zzkt) r11, (long) r12, (com.google.android.gms.internal.ads.zzjn) r14, (boolean) r15)
            if (r9 == 0) goto L_0x00f4
            int r10 = r9.id
            r3.put(r10, r9)
            goto L_0x00f4
        L_0x00f3:
            r7 = r15
        L_0x00f4:
            int r8 = r8 + 1
            r15 = r7
            r7 = 8
            goto L_0x00ca
        L_0x00fa:
            r7 = r15
            int r1 = r3.size()
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r2 = r0.zzaxo
            int r2 = r2.size()
            if (r2 != 0) goto L_0x0199
            r2 = 0
        L_0x0108:
            if (r2 >= r1) goto L_0x013c
            java.lang.Object r5 = r3.valueAt(r2)
            com.google.android.gms.internal.ads.zzln r5 = (com.google.android.gms.internal.ads.zzln) r5
            com.google.android.gms.internal.ads.zzlh r8 = new com.google.android.gms.internal.ads.zzlh
            com.google.android.gms.internal.ads.zzjx r9 = r0.zzarj
            int r10 = r5.type
            com.google.android.gms.internal.ads.zzkg r9 = r9.zzc(r2, r10)
            r8.<init>(r9)
            int r9 = r5.id
            java.lang.Object r9 = r7.get(r9)
            com.google.android.gms.internal.ads.zzlb r9 = (com.google.android.gms.internal.ads.zzlb) r9
            r8.zza(r5, r9)
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r9 = r0.zzaxo
            int r10 = r5.id
            r9.put(r10, r8)
            long r8 = r0.zzaih
            long r10 = r5.zzaih
            long r8 = java.lang.Math.max(r8, r10)
            r0.zzaih = r8
            int r2 = r2 + 1
            goto L_0x0108
        L_0x013c:
            int r1 = r0.flags
            r1 = r1 & r6
            if (r1 == 0) goto L_0x0161
            com.google.android.gms.internal.ads.zzkg r1 = r0.zzayi
            if (r1 != 0) goto L_0x0161
            com.google.android.gms.internal.ads.zzjx r1 = r0.zzarj
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r2 = r0.zzaxo
            int r2 = r2.size()
            com.google.android.gms.internal.ads.zzkg r1 = r1.zzc(r2, r6)
            r0.zzayi = r1
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.String r5 = "application/x-emsg"
            com.google.android.gms.internal.ads.zzho r2 = com.google.android.gms.internal.ads.zzho.zza((java.lang.String) r4, (java.lang.String) r5, (long) r2)
            r1.zze(r2)
        L_0x0161:
            int r1 = r0.flags
            r2 = 8
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0192
            com.google.android.gms.internal.ads.zzkg[] r1 = r0.zzayj
            if (r1 != 0) goto L_0x0192
            com.google.android.gms.internal.ads.zzjx r1 = r0.zzarj
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r2 = r0.zzaxo
            int r2 = r2.size()
            r3 = 1
            int r2 = r2 + r3
            r3 = 3
            com.google.android.gms.internal.ads.zzkg r1 = r1.zzc(r2, r3)
            r2 = 0
            r4 = 0
            r5 = -1
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r3 = "application/cea-608"
            com.google.android.gms.internal.ads.zzho r2 = com.google.android.gms.internal.ads.zzho.zza((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (int) r5, (int) r6, (java.lang.String) r7, (com.google.android.gms.internal.ads.zzjn) r8)
            r1.zze(r2)
            r2 = 1
            com.google.android.gms.internal.ads.zzkg[] r2 = new com.google.android.gms.internal.ads.zzkg[r2]
            r3 = 0
            r2[r3] = r1
            r0.zzayj = r2
        L_0x0192:
            com.google.android.gms.internal.ads.zzjx r1 = r0.zzarj
            r1.zzgr()
            goto L_0x0002
        L_0x0199:
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r2 = r0.zzaxo
            int r2 = r2.size()
            if (r2 != r1) goto L_0x01a3
            r9 = 1
            goto L_0x01a4
        L_0x01a3:
            r9 = 0
        L_0x01a4:
            com.google.android.gms.internal.ads.zzoz.checkState(r9)
            r8 = 0
        L_0x01a8:
            if (r8 >= r1) goto L_0x0002
            java.lang.Object r2 = r3.valueAt(r8)
            com.google.android.gms.internal.ads.zzln r2 = (com.google.android.gms.internal.ads.zzln) r2
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r4 = r0.zzaxo
            int r5 = r2.id
            java.lang.Object r4 = r4.get(r5)
            com.google.android.gms.internal.ads.zzlh r4 = (com.google.android.gms.internal.ads.zzlh) r4
            int r5 = r2.id
            java.lang.Object r5 = r7.get(r5)
            com.google.android.gms.internal.ads.zzlb r5 = (com.google.android.gms.internal.ads.zzlb) r5
            r4.zza(r2, r5)
            int r8 = r8 + 1
            goto L_0x01a8
        L_0x01c8:
            int r2 = r1.type
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauc
            if (r2 != r3) goto L_0x06de
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r2 = r0.zzaxo
            int r3 = r0.flags
            byte[] r5 = r0.zzaxu
            java.util.List<com.google.android.gms.internal.ads.zzku> r7 = r1.zzawl
            int r7 = r7.size()
            r8 = 0
        L_0x01db:
            if (r8 >= r7) goto L_0x06b2
            java.util.List<com.google.android.gms.internal.ads.zzku> r9 = r1.zzawl
            java.lang.Object r9 = r9.get(r8)
            com.google.android.gms.internal.ads.zzku r9 = (com.google.android.gms.internal.ads.zzku) r9
            int r10 = r9.type
            int r11 = com.google.android.gms.internal.ads.zzkr.zzaud
            if (r10 != r11) goto L_0x0691
            int r10 = com.google.android.gms.internal.ads.zzkr.zzatp
            com.google.android.gms.internal.ads.zzkt r10 = r9.zzau(r10)
            com.google.android.gms.internal.ads.zzpk r10 = r10.zzawi
            r11 = 8
            r10.zzbo(r11)
            int r11 = r10.readInt()
            int r11 = com.google.android.gms.internal.ads.zzkr.zzar(r11)
            int r12 = r10.readInt()
            r13 = r3 & 16
            if (r13 != 0) goto L_0x0209
            goto L_0x020a
        L_0x0209:
            r12 = 0
        L_0x020a:
            java.lang.Object r12 = r2.get(r12)
            com.google.android.gms.internal.ads.zzlh r12 = (com.google.android.gms.internal.ads.zzlh) r12
            if (r12 != 0) goto L_0x0214
            r12 = r4
            goto L_0x025f
        L_0x0214:
            r13 = r11 & 1
            if (r13 == 0) goto L_0x0224
            long r13 = r10.zzjg()
            com.google.android.gms.internal.ads.zzlp r15 = r12.zzayl
            r15.zzbal = r13
            com.google.android.gms.internal.ads.zzlp r15 = r12.zzayl
            r15.zzbam = r13
        L_0x0224:
            com.google.android.gms.internal.ads.zzlb r13 = r12.zzayn
            r14 = r11 & 2
            if (r14 == 0) goto L_0x0231
            int r14 = r10.zzjf()
            r15 = 1
            int r14 = r14 - r15
            goto L_0x0233
        L_0x0231:
            int r14 = r13.zzaxh
        L_0x0233:
            r15 = r11 & 8
            if (r15 == 0) goto L_0x023c
            int r15 = r10.zzjf()
            goto L_0x023e
        L_0x023c:
            int r15 = r13.duration
        L_0x023e:
            r16 = r11 & 16
            if (r16 == 0) goto L_0x0249
            int r16 = r10.zzjf()
            r4 = r16
            goto L_0x024b
        L_0x0249:
            int r4 = r13.size
        L_0x024b:
            r11 = r11 & 32
            if (r11 == 0) goto L_0x0254
            int r10 = r10.zzjf()
            goto L_0x0256
        L_0x0254:
            int r10 = r13.flags
        L_0x0256:
            com.google.android.gms.internal.ads.zzlp r11 = r12.zzayl
            com.google.android.gms.internal.ads.zzlb r13 = new com.google.android.gms.internal.ads.zzlb
            r13.<init>(r14, r15, r4, r10)
            r11.zzbaj = r13
        L_0x025f:
            if (r12 == 0) goto L_0x0691
            com.google.android.gms.internal.ads.zzlp r4 = r12.zzayl
            long r10 = r4.zzbba
            r12.reset()
            int r13 = com.google.android.gms.internal.ads.zzkr.zzato
            com.google.android.gms.internal.ads.zzkt r13 = r9.zzau(r13)
            if (r13 == 0) goto L_0x0295
            r13 = r3 & 2
            if (r13 != 0) goto L_0x0295
            int r10 = com.google.android.gms.internal.ads.zzkr.zzato
            com.google.android.gms.internal.ads.zzkt r10 = r9.zzau(r10)
            com.google.android.gms.internal.ads.zzpk r10 = r10.zzawi
            r11 = 8
            r10.zzbo(r11)
            int r11 = r10.readInt()
            int r11 = com.google.android.gms.internal.ads.zzkr.zzaq(r11)
            r13 = 1
            if (r11 != r13) goto L_0x0291
            long r10 = r10.zzjg()
            goto L_0x0295
        L_0x0291:
            long r10 = r10.zzjc()
        L_0x0295:
            java.util.List<com.google.android.gms.internal.ads.zzkt> r13 = r9.zzawk
            int r14 = r13.size()
            r19 = r2
            r2 = 0
            r6 = 0
            r15 = 0
        L_0x02a0:
            if (r15 >= r14) goto L_0x02ce
            java.lang.Object r20 = r13.get(r15)
            r21 = r7
            r7 = r20
            com.google.android.gms.internal.ads.zzkt r7 = (com.google.android.gms.internal.ads.zzkt) r7
            r22 = r10
            int r10 = r7.type
            int r11 = com.google.android.gms.internal.ads.zzkr.zzatr
            if (r10 != r11) goto L_0x02c5
            com.google.android.gms.internal.ads.zzpk r7 = r7.zzawi
            r10 = 12
            r7.zzbo(r10)
            int r7 = r7.zzjf()
            if (r7 <= 0) goto L_0x02c7
            int r2 = r2 + r7
            int r6 = r6 + 1
            goto L_0x02c7
        L_0x02c5:
            r10 = 12
        L_0x02c7:
            int r15 = r15 + 1
            r7 = r21
            r10 = r22
            goto L_0x02a0
        L_0x02ce:
            r21 = r7
            r22 = r10
            r7 = 0
            r10 = 12
            r12.zzayq = r7
            r12.zzayp = r7
            r12.zzayo = r7
            com.google.android.gms.internal.ads.zzlp r7 = r12.zzayl
            r7.zzban = r6
            r7.zzaxc = r2
            int[] r11 = r7.zzbap
            if (r11 == 0) goto L_0x02ea
            int[] r11 = r7.zzbap
            int r11 = r11.length
            if (r11 >= r6) goto L_0x02f2
        L_0x02ea:
            long[] r11 = new long[r6]
            r7.zzbao = r11
            int[] r6 = new int[r6]
            r7.zzbap = r6
        L_0x02f2:
            int[] r6 = r7.zzbaq
            if (r6 == 0) goto L_0x02fb
            int[] r6 = r7.zzbaq
            int r6 = r6.length
            if (r6 >= r2) goto L_0x0313
        L_0x02fb:
            int r2 = r2 * 125
            int r2 = r2 / 100
            int[] r6 = new int[r2]
            r7.zzbaq = r6
            int[] r6 = new int[r2]
            r7.zzbar = r6
            long[] r6 = new long[r2]
            r7.zzbas = r6
            boolean[] r6 = new boolean[r2]
            r7.zzbat = r6
            boolean[] r2 = new boolean[r2]
            r7.zzbav = r2
        L_0x0313:
            r2 = 0
            r6 = 0
            r7 = 0
        L_0x0316:
            r24 = 0
            if (r2 >= r14) goto L_0x04af
            java.lang.Object r18 = r13.get(r2)
            r10 = r18
            com.google.android.gms.internal.ads.zzkt r10 = (com.google.android.gms.internal.ads.zzkt) r10
            int r15 = r10.type
            int r11 = com.google.android.gms.internal.ads.zzkr.zzatr
            if (r15 != r11) goto L_0x047f
            int r11 = r6 + 1
            com.google.android.gms.internal.ads.zzpk r10 = r10.zzawi
            r15 = 8
            r10.zzbo(r15)
            int r15 = r10.readInt()
            int r15 = com.google.android.gms.internal.ads.zzkr.zzar(r15)
            r27 = r11
            com.google.android.gms.internal.ads.zzln r11 = r12.zzaym
            r28 = r13
            com.google.android.gms.internal.ads.zzlp r13 = r12.zzayl
            r29 = r14
            com.google.android.gms.internal.ads.zzlb r14 = r13.zzbaj
            int[] r0 = r13.zzbap
            int r30 = r10.zzjf()
            r0[r6] = r30
            long[] r0 = r13.zzbao
            r31 = r4
            r30 = r5
            long r4 = r13.zzbal
            r0[r6] = r4
            r0 = r15 & 1
            if (r0 == 0) goto L_0x036e
            long[] r0 = r13.zzbao
            r4 = r0[r6]
            r32 = r1
            int r1 = r10.readInt()
            r33 = r8
            r34 = r9
            long r8 = (long) r1
            long r4 = r4 + r8
            r0[r6] = r4
            goto L_0x0374
        L_0x036e:
            r32 = r1
            r33 = r8
            r34 = r9
        L_0x0374:
            r0 = r15 & 4
            if (r0 == 0) goto L_0x037a
            r0 = 1
            goto L_0x037b
        L_0x037a:
            r0 = 0
        L_0x037b:
            int r1 = r14.flags
            if (r0 == 0) goto L_0x0383
            int r1 = r10.zzjf()
        L_0x0383:
            r4 = r15 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x0389
            r4 = 1
            goto L_0x038a
        L_0x0389:
            r4 = 0
        L_0x038a:
            r5 = r15 & 512(0x200, float:7.175E-43)
            if (r5 == 0) goto L_0x0390
            r5 = 1
            goto L_0x0391
        L_0x0390:
            r5 = 0
        L_0x0391:
            r8 = r15 & 1024(0x400, float:1.435E-42)
            if (r8 == 0) goto L_0x0397
            r8 = 1
            goto L_0x0398
        L_0x0397:
            r8 = 0
        L_0x0398:
            r9 = r15 & 2048(0x800, float:2.87E-42)
            if (r9 == 0) goto L_0x039e
            r9 = 1
            goto L_0x039f
        L_0x039e:
            r9 = 0
        L_0x039f:
            long[] r15 = r11.zzbag
            if (r15 == 0) goto L_0x03c4
            long[] r15 = r11.zzbag
            int r15 = r15.length
            r35 = r1
            r1 = 1
            if (r15 != r1) goto L_0x03c6
            long[] r1 = r11.zzbag
            r15 = 0
            r36 = r1[r15]
            int r1 = (r36 > r24 ? 1 : (r36 == r24 ? 0 : -1))
            if (r1 != 0) goto L_0x03c6
            long[] r1 = r11.zzbah
            r36 = r1[r15]
            r38 = 1000(0x3e8, double:4.94E-321)
            r15 = r2
            long r1 = r11.zzdg
            r40 = r1
            long r24 = com.google.android.gms.internal.ads.zzpq.zza((long) r36, (long) r38, (long) r40)
            goto L_0x03c7
        L_0x03c4:
            r35 = r1
        L_0x03c6:
            r15 = r2
        L_0x03c7:
            int[] r1 = r13.zzbaq
            int[] r2 = r13.zzbar
            r36 = r12
            long[] r12 = r13.zzbas
            r37 = r15
            boolean[] r15 = r13.zzbat
            r38 = r15
            int r15 = r11.type
            r39 = r1
            r1 = 2
            if (r15 != r1) goto L_0x03e2
            r1 = r3 & 1
            if (r1 == 0) goto L_0x03e2
            r1 = 1
            goto L_0x03e3
        L_0x03e2:
            r1 = 0
        L_0x03e3:
            int[] r15 = r13.zzbap
            r15 = r15[r6]
            int r15 = r15 + r7
            r26 = r12
            long r11 = r11.zzdg
            r40 = r7
            if (r6 <= 0) goto L_0x03f3
            long r6 = r13.zzbba
            goto L_0x03f5
        L_0x03f3:
            r6 = r22
        L_0x03f5:
            r46 = r3
            r3 = r40
        L_0x03f9:
            if (r3 >= r15) goto L_0x0479
            if (r4 == 0) goto L_0x0406
            int r40 = r10.zzjf()
            r47 = r4
            r4 = r40
            goto L_0x040a
        L_0x0406:
            r47 = r4
            int r4 = r14.duration
        L_0x040a:
            if (r5 == 0) goto L_0x0415
            int r40 = r10.zzjf()
            r48 = r5
            r5 = r40
            goto L_0x0419
        L_0x0415:
            r48 = r5
            int r5 = r14.size
        L_0x0419:
            if (r3 != 0) goto L_0x0422
            if (r0 == 0) goto L_0x0422
            r49 = r0
            r0 = r35
            goto L_0x0431
        L_0x0422:
            if (r8 == 0) goto L_0x042d
            int r40 = r10.readInt()
            r49 = r0
            r0 = r40
            goto L_0x0431
        L_0x042d:
            r49 = r0
            int r0 = r14.flags
        L_0x0431:
            if (r9 == 0) goto L_0x0443
            r50 = r8
            int r8 = r10.readInt()
            int r8 = r8 * 1000
            r51 = r9
            long r8 = (long) r8
            long r8 = r8 / r11
            int r9 = (int) r8
            r2[r3] = r9
            goto L_0x044a
        L_0x0443:
            r50 = r8
            r51 = r9
            r8 = 0
            r2[r3] = r8
        L_0x044a:
            r42 = 1000(0x3e8, double:4.94E-321)
            r40 = r6
            r44 = r11
            long r8 = com.google.android.gms.internal.ads.zzpq.zza((long) r40, (long) r42, (long) r44)
            long r8 = r8 - r24
            r26[r3] = r8
            r39[r3] = r5
            r5 = 16
            int r0 = r0 >> r5
            r5 = 1
            r0 = r0 & r5
            if (r0 != 0) goto L_0x0467
            if (r1 == 0) goto L_0x0465
            if (r3 != 0) goto L_0x0467
        L_0x0465:
            r0 = 1
            goto L_0x0468
        L_0x0467:
            r0 = 0
        L_0x0468:
            r38[r3] = r0
            long r4 = (long) r4
            long r6 = r6 + r4
            int r3 = r3 + 1
            r4 = r47
            r5 = r48
            r0 = r49
            r8 = r50
            r9 = r51
            goto L_0x03f9
        L_0x0479:
            r13.zzbba = r6
            r7 = r15
            r6 = r27
            goto L_0x0495
        L_0x047f:
            r32 = r1
            r37 = r2
            r46 = r3
            r31 = r4
            r30 = r5
            r40 = r7
            r33 = r8
            r34 = r9
            r36 = r12
            r28 = r13
            r29 = r14
        L_0x0495:
            int r2 = r37 + 1
            r0 = r52
            r13 = r28
            r14 = r29
            r5 = r30
            r4 = r31
            r1 = r32
            r8 = r33
            r9 = r34
            r12 = r36
            r3 = r46
            r10 = 12
            goto L_0x0316
        L_0x04af:
            r32 = r1
            r46 = r3
            r31 = r4
            r30 = r5
            r33 = r8
            r34 = r9
            r36 = r12
            int r0 = com.google.android.gms.internal.ads.zzkr.zzauu
            com.google.android.gms.internal.ads.zzkt r0 = r9.zzau(r0)
            if (r0 == 0) goto L_0x0544
            r4 = r36
            com.google.android.gms.internal.ads.zzln r1 = r4.zzaym
            com.google.android.gms.internal.ads.zzlq[] r1 = r1.zzbaf
            r2 = r31
            com.google.android.gms.internal.ads.zzlb r3 = r2.zzbaj
            int r3 = r3.zzaxh
            r1 = r1[r3]
            com.google.android.gms.internal.ads.zzpk r0 = r0.zzawi
            int r1 = r1.zzbbc
            r3 = 8
            r0.zzbo(r3)
            int r4 = r0.readInt()
            int r4 = com.google.android.gms.internal.ads.zzkr.zzar(r4)
            r5 = 1
            r4 = r4 & r5
            if (r4 != r5) goto L_0x04eb
            r0.zzbp(r3)
        L_0x04eb:
            int r3 = r0.readUnsignedByte()
            int r4 = r0.zzjf()
            int r5 = r2.zzaxc
            if (r4 != r5) goto L_0x0521
            if (r3 != 0) goto L_0x050e
            boolean[] r3 = r2.zzbav
            r5 = 0
            r6 = 0
        L_0x04fd:
            if (r5 >= r4) goto L_0x051d
            int r7 = r0.readUnsignedByte()
            int r6 = r6 + r7
            if (r7 <= r1) goto L_0x0508
            r7 = 1
            goto L_0x0509
        L_0x0508:
            r7 = 0
        L_0x0509:
            r3[r5] = r7
            int r5 = r5 + 1
            goto L_0x04fd
        L_0x050e:
            if (r3 <= r1) goto L_0x0512
            r0 = 1
            goto L_0x0513
        L_0x0512:
            r0 = 0
        L_0x0513:
            int r3 = r3 * r4
            r1 = 0
            int r6 = r3 + 0
            boolean[] r3 = r2.zzbav
            java.util.Arrays.fill(r3, r1, r4, r0)
        L_0x051d:
            r2.zzaw(r6)
            goto L_0x0546
        L_0x0521:
            com.google.android.gms.internal.ads.zzhv r0 = new com.google.android.gms.internal.ads.zzhv
            int r1 = r2.zzaxc
            r2 = 41
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Length mismatch: "
            r3.append(r2)
            r3.append(r4)
            java.lang.String r2 = ", "
            r3.append(r2)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>(r1)
            throw r0
        L_0x0544:
            r2 = r31
        L_0x0546:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzauv
            com.google.android.gms.internal.ads.zzkt r0 = r9.zzau(r0)
            if (r0 == 0) goto L_0x0598
            com.google.android.gms.internal.ads.zzpk r0 = r0.zzawi
            r1 = 8
            r0.zzbo(r1)
            int r3 = r0.readInt()
            int r4 = com.google.android.gms.internal.ads.zzkr.zzar(r3)
            r5 = 1
            r4 = r4 & r5
            if (r4 != r5) goto L_0x0564
            r0.zzbp(r1)
        L_0x0564:
            int r1 = r0.zzjf()
            if (r1 != r5) goto L_0x057f
            int r1 = com.google.android.gms.internal.ads.zzkr.zzaq(r3)
            long r3 = r2.zzbam
            if (r1 != 0) goto L_0x0577
            long r0 = r0.zzjc()
            goto L_0x057b
        L_0x0577:
            long r0 = r0.zzjg()
        L_0x057b:
            long r3 = r3 + r0
            r2.zzbam = r3
            goto L_0x0598
        L_0x057f:
            com.google.android.gms.internal.ads.zzhv r0 = new com.google.android.gms.internal.ads.zzhv
            r2 = 40
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Unexpected saio entry count: "
            r3.append(r2)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>(r1)
            throw r0
        L_0x0598:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzauz
            com.google.android.gms.internal.ads.zzkt r0 = r9.zzau(r0)
            if (r0 == 0) goto L_0x05a6
            com.google.android.gms.internal.ads.zzpk r0 = r0.zzawi
            r1 = 0
            zza(r0, r1, r2)
        L_0x05a6:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzauw
            com.google.android.gms.internal.ads.zzkt r0 = r9.zzau(r0)
            int r1 = com.google.android.gms.internal.ads.zzkr.zzaux
            com.google.android.gms.internal.ads.zzkt r1 = r9.zzau(r1)
            if (r0 == 0) goto L_0x064b
            if (r1 == 0) goto L_0x064b
            com.google.android.gms.internal.ads.zzpk r0 = r0.zzawi
            com.google.android.gms.internal.ads.zzpk r1 = r1.zzawi
            r3 = 8
            r0.zzbo(r3)
            int r3 = r0.readInt()
            int r4 = r0.readInt()
            int r5 = zzaxl
            if (r4 != r5) goto L_0x064b
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaq(r3)
            r4 = 1
            if (r3 != r4) goto L_0x05d6
            r3 = 4
            r0.zzbp(r3)
        L_0x05d6:
            int r0 = r0.readInt()
            if (r0 != r4) goto L_0x0643
            r0 = 8
            r1.zzbo(r0)
            int r0 = r1.readInt()
            int r3 = r1.readInt()
            int r5 = zzaxl
            if (r3 != r5) goto L_0x0641
            int r0 = com.google.android.gms.internal.ads.zzkr.zzaq(r0)
            if (r0 != r4) goto L_0x0606
            long r3 = r1.zzjc()
            int r0 = (r3 > r24 ? 1 : (r3 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x05fe
            r0 = 4
            r3 = 2
            goto L_0x060f
        L_0x05fe:
            com.google.android.gms.internal.ads.zzhv r0 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r1 = "Variable length decription in sgpd found (unsupported)"
            r0.<init>(r1)
            throw r0
        L_0x0606:
            r3 = 2
            if (r0 < r3) goto L_0x060e
            r0 = 4
            r1.zzbp(r0)
            goto L_0x060f
        L_0x060e:
            r0 = 4
        L_0x060f:
            long r4 = r1.zzjc()
            r6 = 1
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0639
            r1.zzbp(r3)
            int r3 = r1.readUnsignedByte()
            r4 = 1
            if (r3 != r4) goto L_0x064d
            int r3 = r1.readUnsignedByte()
            r5 = 16
            byte[] r6 = new byte[r5]
            r7 = 0
            r1.zze(r6, r7, r5)
            r2.zzbau = r4
            com.google.android.gms.internal.ads.zzlq r1 = new com.google.android.gms.internal.ads.zzlq
            r1.<init>(r4, r3, r6)
            r2.zzbaw = r1
            goto L_0x064d
        L_0x0639:
            com.google.android.gms.internal.ads.zzhv r0 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r1 = "Entry count in sgpd != 1 (unsupported)."
            r0.<init>(r1)
            throw r0
        L_0x0641:
            r0 = 4
            goto L_0x064d
        L_0x0643:
            com.google.android.gms.internal.ads.zzhv r0 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r1 = "Entry count in sbgp != 1 (unsupported)."
            r0.<init>(r1)
            throw r0
        L_0x064b:
            r0 = 4
            r4 = 1
        L_0x064d:
            java.util.List<com.google.android.gms.internal.ads.zzkt> r1 = r9.zzawk
            int r1 = r1.size()
            r3 = 0
        L_0x0654:
            if (r3 >= r1) goto L_0x068b
            java.util.List<com.google.android.gms.internal.ads.zzkt> r5 = r9.zzawk
            java.lang.Object r5 = r5.get(r3)
            com.google.android.gms.internal.ads.zzkt r5 = (com.google.android.gms.internal.ads.zzkt) r5
            int r6 = r5.type
            int r7 = com.google.android.gms.internal.ads.zzkr.zzauy
            if (r6 != r7) goto L_0x067f
            com.google.android.gms.internal.ads.zzpk r5 = r5.zzawi
            r6 = 8
            r5.zzbo(r6)
            r7 = r30
            r8 = 16
            r10 = 0
            r5.zze(r7, r10, r8)
            byte[] r11 = zzaxm
            boolean r11 = java.util.Arrays.equals(r7, r11)
            if (r11 == 0) goto L_0x0686
            zza(r5, r8, r2)
            goto L_0x0686
        L_0x067f:
            r7 = r30
            r6 = 8
            r8 = 16
            r10 = 0
        L_0x0686:
            int r3 = r3 + 1
            r30 = r7
            goto L_0x0654
        L_0x068b:
            r7 = r30
            r6 = 8
            r10 = 0
            goto L_0x06a1
        L_0x0691:
            r32 = r1
            r19 = r2
            r46 = r3
            r21 = r7
            r33 = r8
            r0 = 4
            r4 = 1
            r6 = 8
            r10 = 0
            r7 = r5
        L_0x06a1:
            int r8 = r33 + 1
            r0 = r52
            r5 = r7
            r2 = r19
            r7 = r21
            r1 = r32
            r3 = r46
            r4 = 0
            r6 = 4
            goto L_0x01db
        L_0x06b2:
            r10 = 0
            java.util.List<com.google.android.gms.internal.ads.zzkt> r0 = r1.zzawk
            com.google.android.gms.internal.ads.zzjn r0 = zzb(r0)
            r2 = r52
            if (r0 == 0) goto L_0x06f2
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r1 = r2.zzaxo
            int r1 = r1.size()
            r8 = 0
        L_0x06c4:
            if (r8 >= r1) goto L_0x06f2
            android.util.SparseArray<com.google.android.gms.internal.ads.zzlh> r3 = r2.zzaxo
            java.lang.Object r3 = r3.valueAt(r8)
            com.google.android.gms.internal.ads.zzlh r3 = (com.google.android.gms.internal.ads.zzlh) r3
            com.google.android.gms.internal.ads.zzkg r4 = r3.zzasq
            com.google.android.gms.internal.ads.zzln r3 = r3.zzaym
            com.google.android.gms.internal.ads.zzho r3 = r3.zzahx
            com.google.android.gms.internal.ads.zzho r3 = r3.zza((com.google.android.gms.internal.ads.zzjn) r0)
            r4.zze(r3)
            int r8 = r8 + 1
            goto L_0x06c4
        L_0x06de:
            r2 = r0
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r0 = r2.zzaxv
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x06f2
            java.util.Stack<com.google.android.gms.internal.ads.zzku> r0 = r2.zzaxv
            java.lang.Object r0 = r0.peek()
            com.google.android.gms.internal.ads.zzku r0 = (com.google.android.gms.internal.ads.zzku) r0
            r0.zza((com.google.android.gms.internal.ads.zzku) r1)
        L_0x06f2:
            r0 = r2
            goto L_0x0002
        L_0x06f5:
            r2 = r0
            r52.zzhb()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlg.zzeb(long):void");
    }

    private static void zza(zzpk zzpk, int i, zzlp zzlp) throws zzhv {
        zzpk.zzbo(i + 8);
        int zzar = zzkr.zzar(zzpk.readInt());
        if ((zzar & 1) == 0) {
            boolean z = (zzar & 2) != 0;
            int zzjf = zzpk.zzjf();
            if (zzjf == zzlp.zzaxc) {
                Arrays.fill(zzlp.zzbav, 0, zzjf, z);
                zzlp.zzaw(zzpk.zzja());
                zzpk.zze(zzlp.zzbay.data, 0, zzlp.zzbax);
                zzlp.zzbay.zzbo(0);
                zzlp.zzbaz = false;
                return;
            }
            int i2 = zzlp.zzaxc;
            StringBuilder sb = new StringBuilder(41);
            sb.append("Length mismatch: ");
            sb.append(zzjf);
            sb.append(", ");
            sb.append(i2);
            throw new zzhv(sb.toString());
        }
        throw new zzhv("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ab  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.ads.zzjn zzb(java.util.List<com.google.android.gms.internal.ads.zzkt> r14) {
        /*
            int r0 = r14.size()
            r1 = 0
            r2 = 0
            r4 = r2
            r3 = 0
        L_0x0008:
            if (r3 >= r0) goto L_0x00b9
            java.lang.Object r5 = r14.get(r3)
            com.google.android.gms.internal.ads.zzkt r5 = (com.google.android.gms.internal.ads.zzkt) r5
            int r6 = r5.type
            int r7 = com.google.android.gms.internal.ads.zzkr.zzaum
            if (r6 != r7) goto L_0x00b5
            if (r4 != 0) goto L_0x001d
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L_0x001d:
            com.google.android.gms.internal.ads.zzpk r5 = r5.zzawi
            byte[] r5 = r5.data
            com.google.android.gms.internal.ads.zzpk r6 = new com.google.android.gms.internal.ads.zzpk
            r6.<init>((byte[]) r5)
            int r7 = r6.limit()
            r8 = 32
            if (r7 >= r8) goto L_0x0030
        L_0x002e:
            r6 = r2
            goto L_0x0099
        L_0x0030:
            r6.zzbo(r1)
            int r7 = r6.readInt()
            int r8 = r6.zzja()
            int r8 = r8 + 4
            if (r7 == r8) goto L_0x0040
            goto L_0x002e
        L_0x0040:
            int r7 = r6.readInt()
            int r8 = com.google.android.gms.internal.ads.zzkr.zzaum
            if (r7 == r8) goto L_0x0049
            goto L_0x002e
        L_0x0049:
            int r7 = r6.readInt()
            int r7 = com.google.android.gms.internal.ads.zzkr.zzaq(r7)
            r8 = 1
            if (r7 <= r8) goto L_0x006d
            r6 = 37
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r6)
            java.lang.String r6 = "Unsupported pssh version: "
            r8.append(r6)
            r8.append(r7)
            java.lang.String r6 = r8.toString()
            java.lang.String r7 = "PsshAtomUtil"
            android.util.Log.w(r7, r6)
            goto L_0x002e
        L_0x006d:
            java.util.UUID r9 = new java.util.UUID
            long r10 = r6.readLong()
            long r12 = r6.readLong()
            r9.<init>(r10, r12)
            if (r7 != r8) goto L_0x0085
            int r7 = r6.zzjf()
            int r7 = r7 << 4
            r6.zzbp(r7)
        L_0x0085:
            int r7 = r6.zzjf()
            int r8 = r6.zzja()
            if (r7 == r8) goto L_0x0090
            goto L_0x002e
        L_0x0090:
            byte[] r8 = new byte[r7]
            r6.zze(r8, r1, r7)
            android.util.Pair r6 = android.util.Pair.create(r9, r8)
        L_0x0099:
            if (r6 != 0) goto L_0x009d
            r6 = r2
            goto L_0x00a1
        L_0x009d:
            java.lang.Object r6 = r6.first
            java.util.UUID r6 = (java.util.UUID) r6
        L_0x00a1:
            if (r6 != 0) goto L_0x00ab
            java.lang.String r5 = "FragmentedMp4Extractor"
            java.lang.String r6 = "Skipped pssh atom (failed to extract uuid)"
            android.util.Log.w(r5, r6)
            goto L_0x00b5
        L_0x00ab:
            com.google.android.gms.internal.ads.zzjn$zza r7 = new com.google.android.gms.internal.ads.zzjn$zza
            java.lang.String r8 = "video/mp4"
            r7.<init>(r6, r8, r5)
            r4.add(r7)
        L_0x00b5:
            int r3 = r3 + 1
            goto L_0x0008
        L_0x00b9:
            if (r4 != 0) goto L_0x00bc
            return r2
        L_0x00bc:
            com.google.android.gms.internal.ads.zzjn r14 = new com.google.android.gms.internal.ads.zzjn
            r14.<init>((java.util.List<com.google.android.gms.internal.ads.zzjn.zza>) r4)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlg.zzb(java.util.List):com.google.android.gms.internal.ads.zzjn");
    }
}
