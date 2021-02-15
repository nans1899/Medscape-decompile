package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zznf {
    private int length;
    private int[] zzaon = new int[1000];
    private long[] zzaoo = new long[1000];
    private long[] zzaoq = new long[1000];
    private int[] zzaxk = new int[1000];
    private int zzbfl = 1000;
    private int[] zzbfm = new int[1000];
    private zzkf[] zzbfn = new zzkf[1000];
    private zzho[] zzbfo = new zzho[1000];
    private int zzbfp;
    private int zzbfq;
    private int zzbfr;
    private long zzbfs = Long.MIN_VALUE;
    private long zzbft = Long.MIN_VALUE;
    private boolean zzbfu = true;
    private boolean zzbfv = true;
    private zzho zzbfw;

    public final void zzib() {
        this.zzbfp = 0;
        this.zzbfq = 0;
        this.zzbfr = 0;
        this.length = 0;
        this.zzbfu = true;
    }

    public final void zzic() {
        this.zzbfs = Long.MIN_VALUE;
        this.zzbft = Long.MIN_VALUE;
    }

    public final int zzid() {
        return this.zzbfp + this.length;
    }

    public final synchronized boolean zzie() {
        return this.length != 0;
    }

    public final synchronized zzho zzif() {
        if (this.zzbfv) {
            return null;
        }
        return this.zzbfw;
    }

    public final synchronized long zzhv() {
        return Math.max(this.zzbfs, this.zzbft);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0023, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int zza(com.google.android.gms.internal.ads.zzhq r5, com.google.android.gms.internal.ads.zzjk r6, boolean r7, boolean r8, com.google.android.gms.internal.ads.zzho r9, com.google.android.gms.internal.ads.zzni r10) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.zzie()     // Catch:{ all -> 0x00a4 }
            r1 = -5
            r2 = -3
            r3 = -4
            if (r0 != 0) goto L_0x0024
            if (r8 == 0) goto L_0x0012
            r5 = 4
            r6.setFlags(r5)     // Catch:{ all -> 0x00a4 }
            monitor-exit(r4)
            return r3
        L_0x0012:
            com.google.android.gms.internal.ads.zzho r6 = r4.zzbfw     // Catch:{ all -> 0x00a4 }
            if (r6 == 0) goto L_0x0022
            if (r7 != 0) goto L_0x001c
            com.google.android.gms.internal.ads.zzho r6 = r4.zzbfw     // Catch:{ all -> 0x00a4 }
            if (r6 == r9) goto L_0x0022
        L_0x001c:
            com.google.android.gms.internal.ads.zzho r6 = r4.zzbfw     // Catch:{ all -> 0x00a4 }
            r5.zzahx = r6     // Catch:{ all -> 0x00a4 }
            monitor-exit(r4)
            return r1
        L_0x0022:
            monitor-exit(r4)
            return r2
        L_0x0024:
            if (r7 != 0) goto L_0x009a
            com.google.android.gms.internal.ads.zzho[] r7 = r4.zzbfo     // Catch:{ all -> 0x00a4 }
            int r8 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            r7 = r7[r8]     // Catch:{ all -> 0x00a4 }
            if (r7 == r9) goto L_0x002f
            goto L_0x009a
        L_0x002f:
            java.nio.ByteBuffer r5 = r6.zzdd     // Catch:{ all -> 0x00a4 }
            r7 = 0
            r8 = 1
            if (r5 != 0) goto L_0x0037
            r5 = 1
            goto L_0x0038
        L_0x0037:
            r5 = 0
        L_0x0038:
            if (r5 == 0) goto L_0x003c
            monitor-exit(r4)
            return r2
        L_0x003c:
            long[] r5 = r4.zzaoq     // Catch:{ all -> 0x00a4 }
            int r9 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            r0 = r5[r9]     // Catch:{ all -> 0x00a4 }
            r6.zzaob = r0     // Catch:{ all -> 0x00a4 }
            int[] r5 = r4.zzaxk     // Catch:{ all -> 0x00a4 }
            int r9 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            r5 = r5[r9]     // Catch:{ all -> 0x00a4 }
            r6.setFlags(r5)     // Catch:{ all -> 0x00a4 }
            int[] r5 = r4.zzaon     // Catch:{ all -> 0x00a4 }
            int r9 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            r5 = r5[r9]     // Catch:{ all -> 0x00a4 }
            r10.size = r5     // Catch:{ all -> 0x00a4 }
            long[] r5 = r4.zzaoo     // Catch:{ all -> 0x00a4 }
            int r9 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            r0 = r5[r9]     // Catch:{ all -> 0x00a4 }
            r10.zzawn = r0     // Catch:{ all -> 0x00a4 }
            com.google.android.gms.internal.ads.zzkf[] r5 = r4.zzbfn     // Catch:{ all -> 0x00a4 }
            int r9 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            r5 = r5[r9]     // Catch:{ all -> 0x00a4 }
            r10.zzarq = r5     // Catch:{ all -> 0x00a4 }
            long r0 = r4.zzbfs     // Catch:{ all -> 0x00a4 }
            long r5 = r6.zzaob     // Catch:{ all -> 0x00a4 }
            long r5 = java.lang.Math.max(r0, r5)     // Catch:{ all -> 0x00a4 }
            r4.zzbfs = r5     // Catch:{ all -> 0x00a4 }
            int r5 = r4.length     // Catch:{ all -> 0x00a4 }
            int r5 = r5 - r8
            r4.length = r5     // Catch:{ all -> 0x00a4 }
            int r5 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            int r5 = r5 + r8
            r4.zzbfq = r5     // Catch:{ all -> 0x00a4 }
            int r6 = r4.zzbfp     // Catch:{ all -> 0x00a4 }
            int r6 = r6 + r8
            r4.zzbfp = r6     // Catch:{ all -> 0x00a4 }
            int r6 = r4.zzbfl     // Catch:{ all -> 0x00a4 }
            if (r5 != r6) goto L_0x0084
            r4.zzbfq = r7     // Catch:{ all -> 0x00a4 }
        L_0x0084:
            int r5 = r4.length     // Catch:{ all -> 0x00a4 }
            if (r5 <= 0) goto L_0x008f
            long[] r5 = r4.zzaoo     // Catch:{ all -> 0x00a4 }
            int r6 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            r6 = r5[r6]     // Catch:{ all -> 0x00a4 }
            goto L_0x0096
        L_0x008f:
            long r5 = r10.zzawn     // Catch:{ all -> 0x00a4 }
            int r7 = r10.size     // Catch:{ all -> 0x00a4 }
            long r7 = (long) r7     // Catch:{ all -> 0x00a4 }
            long r6 = r5 + r7
        L_0x0096:
            r10.zzbgk = r6     // Catch:{ all -> 0x00a4 }
            monitor-exit(r4)
            return r3
        L_0x009a:
            com.google.android.gms.internal.ads.zzho[] r6 = r4.zzbfo     // Catch:{ all -> 0x00a4 }
            int r7 = r4.zzbfq     // Catch:{ all -> 0x00a4 }
            r6 = r6[r7]     // Catch:{ all -> 0x00a4 }
            r5.zzahx = r6     // Catch:{ all -> 0x00a4 }
            monitor-exit(r4)
            return r1
        L_0x00a4:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznf.zza(com.google.android.gms.internal.ads.zzhq, com.google.android.gms.internal.ads.zzjk, boolean, boolean, com.google.android.gms.internal.ads.zzho, com.google.android.gms.internal.ads.zzni):int");
    }

    public final synchronized long zzig() {
        if (!zzie()) {
            return -1;
        }
        int i = ((this.zzbfq + this.length) - 1) % this.zzbfl;
        this.zzbfq = (this.zzbfq + this.length) % this.zzbfl;
        this.zzbfp += this.length;
        this.length = 0;
        return this.zzaoo[i] + ((long) this.zzaon[i]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long zzd(long r9, boolean r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.zzie()     // Catch:{ all -> 0x005e }
            r1 = -1
            if (r0 == 0) goto L_0x005c
            long[] r0 = r8.zzaoq     // Catch:{ all -> 0x005e }
            int r3 = r8.zzbfq     // Catch:{ all -> 0x005e }
            r3 = r0[r3]     // Catch:{ all -> 0x005e }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0014
            goto L_0x005c
        L_0x0014:
            long r3 = r8.zzbft     // Catch:{ all -> 0x005e }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x001e
            if (r11 != 0) goto L_0x001e
            monitor-exit(r8)
            return r1
        L_0x001e:
            r11 = 0
            int r0 = r8.zzbfq     // Catch:{ all -> 0x005e }
            r3 = -1
            r4 = -1
        L_0x0023:
            int r5 = r8.zzbfr     // Catch:{ all -> 0x005e }
            if (r0 == r5) goto L_0x0040
            long[] r5 = r8.zzaoq     // Catch:{ all -> 0x005e }
            r6 = r5[r0]     // Catch:{ all -> 0x005e }
            int r5 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r5 > 0) goto L_0x0040
            int[] r5 = r8.zzaxk     // Catch:{ all -> 0x005e }
            r5 = r5[r0]     // Catch:{ all -> 0x005e }
            r5 = r5 & 1
            if (r5 == 0) goto L_0x0038
            r4 = r11
        L_0x0038:
            int r0 = r0 + 1
            int r5 = r8.zzbfl     // Catch:{ all -> 0x005e }
            int r0 = r0 % r5
            int r11 = r11 + 1
            goto L_0x0023
        L_0x0040:
            if (r4 != r3) goto L_0x0044
            monitor-exit(r8)
            return r1
        L_0x0044:
            int r9 = r8.zzbfq     // Catch:{ all -> 0x005e }
            int r9 = r9 + r4
            int r10 = r8.zzbfl     // Catch:{ all -> 0x005e }
            int r9 = r9 % r10
            r8.zzbfq = r9     // Catch:{ all -> 0x005e }
            int r10 = r8.zzbfp     // Catch:{ all -> 0x005e }
            int r10 = r10 + r4
            r8.zzbfp = r10     // Catch:{ all -> 0x005e }
            int r10 = r8.length     // Catch:{ all -> 0x005e }
            int r10 = r10 - r4
            r8.length = r10     // Catch:{ all -> 0x005e }
            long[] r10 = r8.zzaoo     // Catch:{ all -> 0x005e }
            r9 = r10[r9]     // Catch:{ all -> 0x005e }
            monitor-exit(r8)
            return r9
        L_0x005c:
            monitor-exit(r8)
            return r1
        L_0x005e:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznf.zzd(long, boolean):long");
    }

    public final synchronized boolean zzg(zzho zzho) {
        if (zzho == null) {
            this.zzbfv = true;
            return false;
        }
        this.zzbfv = false;
        if (zzpq.zza(zzho, this.zzbfw)) {
            return false;
        }
        this.zzbfw = zzho;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(long r6, int r8, long r9, int r11, com.google.android.gms.internal.ads.zzkf r12) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.zzbfu     // Catch:{ all -> 0x00e7 }
            r1 = 0
            if (r0 == 0) goto L_0x000e
            r0 = r8 & 1
            if (r0 != 0) goto L_0x000c
            monitor-exit(r5)
            return
        L_0x000c:
            r5.zzbfu = r1     // Catch:{ all -> 0x00e7 }
        L_0x000e:
            boolean r0 = r5.zzbfv     // Catch:{ all -> 0x00e7 }
            r2 = 1
            if (r0 != 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            com.google.android.gms.internal.ads.zzoz.checkState(r0)     // Catch:{ all -> 0x00e7 }
            r5.zzei(r6)     // Catch:{ all -> 0x00e7 }
            long[] r0 = r5.zzaoq     // Catch:{ all -> 0x00e7 }
            int r3 = r5.zzbfr     // Catch:{ all -> 0x00e7 }
            r0[r3] = r6     // Catch:{ all -> 0x00e7 }
            long[] r6 = r5.zzaoo     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfr     // Catch:{ all -> 0x00e7 }
            r6[r7] = r9     // Catch:{ all -> 0x00e7 }
            int[] r6 = r5.zzaon     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfr     // Catch:{ all -> 0x00e7 }
            r6[r7] = r11     // Catch:{ all -> 0x00e7 }
            int[] r6 = r5.zzaxk     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfr     // Catch:{ all -> 0x00e7 }
            r6[r7] = r8     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzkf[] r6 = r5.zzbfn     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfr     // Catch:{ all -> 0x00e7 }
            r6[r7] = r12     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzho[] r6 = r5.zzbfo     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfr     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzho r8 = r5.zzbfw     // Catch:{ all -> 0x00e7 }
            r6[r7] = r8     // Catch:{ all -> 0x00e7 }
            int[] r6 = r5.zzbfm     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfr     // Catch:{ all -> 0x00e7 }
            r6[r7] = r1     // Catch:{ all -> 0x00e7 }
            int r6 = r5.length     // Catch:{ all -> 0x00e7 }
            int r6 = r6 + r2
            r5.length = r6     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfl     // Catch:{ all -> 0x00e7 }
            if (r6 != r7) goto L_0x00da
            int r6 = r5.zzbfl     // Catch:{ all -> 0x00e7 }
            int r6 = r6 + 1000
            int[] r7 = new int[r6]     // Catch:{ all -> 0x00e7 }
            long[] r8 = new long[r6]     // Catch:{ all -> 0x00e7 }
            long[] r9 = new long[r6]     // Catch:{ all -> 0x00e7 }
            int[] r10 = new int[r6]     // Catch:{ all -> 0x00e7 }
            int[] r11 = new int[r6]     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzkf[] r12 = new com.google.android.gms.internal.ads.zzkf[r6]     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzho[] r0 = new com.google.android.gms.internal.ads.zzho[r6]     // Catch:{ all -> 0x00e7 }
            int r2 = r5.zzbfl     // Catch:{ all -> 0x00e7 }
            int r3 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            int r2 = r2 - r3
            long[] r3 = r5.zzaoo     // Catch:{ all -> 0x00e7 }
            int r4 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r3, r4, r8, r1, r2)     // Catch:{ all -> 0x00e7 }
            long[] r3 = r5.zzaoq     // Catch:{ all -> 0x00e7 }
            int r4 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r3, r4, r9, r1, r2)     // Catch:{ all -> 0x00e7 }
            int[] r3 = r5.zzaxk     // Catch:{ all -> 0x00e7 }
            int r4 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r3, r4, r10, r1, r2)     // Catch:{ all -> 0x00e7 }
            int[] r3 = r5.zzaon     // Catch:{ all -> 0x00e7 }
            int r4 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r3, r4, r11, r1, r2)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzkf[] r3 = r5.zzbfn     // Catch:{ all -> 0x00e7 }
            int r4 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r3, r4, r12, r1, r2)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzho[] r3 = r5.zzbfo     // Catch:{ all -> 0x00e7 }
            int r4 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r3, r4, r0, r1, r2)     // Catch:{ all -> 0x00e7 }
            int[] r3 = r5.zzbfm     // Catch:{ all -> 0x00e7 }
            int r4 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r3, r4, r7, r1, r2)     // Catch:{ all -> 0x00e7 }
            int r3 = r5.zzbfq     // Catch:{ all -> 0x00e7 }
            long[] r4 = r5.zzaoo     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r4, r1, r8, r2, r3)     // Catch:{ all -> 0x00e7 }
            long[] r4 = r5.zzaoq     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r4, r1, r9, r2, r3)     // Catch:{ all -> 0x00e7 }
            int[] r4 = r5.zzaxk     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r4, r1, r10, r2, r3)     // Catch:{ all -> 0x00e7 }
            int[] r4 = r5.zzaon     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r4, r1, r11, r2, r3)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzkf[] r4 = r5.zzbfn     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r4, r1, r12, r2, r3)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.internal.ads.zzho[] r4 = r5.zzbfo     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r4, r1, r0, r2, r3)     // Catch:{ all -> 0x00e7 }
            int[] r4 = r5.zzbfm     // Catch:{ all -> 0x00e7 }
            java.lang.System.arraycopy(r4, r1, r7, r2, r3)     // Catch:{ all -> 0x00e7 }
            r5.zzaoo = r8     // Catch:{ all -> 0x00e7 }
            r5.zzaoq = r9     // Catch:{ all -> 0x00e7 }
            r5.zzaxk = r10     // Catch:{ all -> 0x00e7 }
            r5.zzaon = r11     // Catch:{ all -> 0x00e7 }
            r5.zzbfn = r12     // Catch:{ all -> 0x00e7 }
            r5.zzbfo = r0     // Catch:{ all -> 0x00e7 }
            r5.zzbfm = r7     // Catch:{ all -> 0x00e7 }
            r5.zzbfq = r1     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfl     // Catch:{ all -> 0x00e7 }
            r5.zzbfr = r7     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfl     // Catch:{ all -> 0x00e7 }
            r5.length = r7     // Catch:{ all -> 0x00e7 }
            r5.zzbfl = r6     // Catch:{ all -> 0x00e7 }
            monitor-exit(r5)
            return
        L_0x00da:
            int r6 = r5.zzbfr     // Catch:{ all -> 0x00e7 }
            int r6 = r6 + r2
            r5.zzbfr = r6     // Catch:{ all -> 0x00e7 }
            int r7 = r5.zzbfl     // Catch:{ all -> 0x00e7 }
            if (r6 != r7) goto L_0x00e5
            r5.zzbfr = r1     // Catch:{ all -> 0x00e7 }
        L_0x00e5:
            monitor-exit(r5)
            return
        L_0x00e7:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznf.zza(long, int, long, int, com.google.android.gms.internal.ads.zzkf):void");
    }

    public final synchronized void zzei(long j) {
        this.zzbft = Math.max(this.zzbft, j);
    }
}
