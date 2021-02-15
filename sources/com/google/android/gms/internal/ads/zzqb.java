package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzqb {
    private final zzqe zzbmg;
    private final boolean zzbmh;
    private final long zzbmi;
    private final long zzbmj;
    private long zzbmk;
    private long zzbml;
    private long zzbmm;
    private boolean zzbmn;
    private long zzbmo;
    private long zzbmp;
    private long zzbmq;

    public zzqb() {
        this(-1.0d);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzqb(android.content.Context r3) {
        /*
            r2 = this;
            java.lang.String r0 = "window"
            java.lang.Object r3 = r3.getSystemService(r0)
            android.view.WindowManager r3 = (android.view.WindowManager) r3
            android.view.Display r0 = r3.getDefaultDisplay()
            if (r0 == 0) goto L_0x0018
            android.view.Display r3 = r3.getDefaultDisplay()
            float r3 = r3.getRefreshRate()
            double r0 = (double) r3
            goto L_0x001a
        L_0x0018:
            r0 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x001a:
            r2.<init>((double) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqb.<init>(android.content.Context):void");
    }

    private zzqb(double d) {
        boolean z = d != -1.0d;
        this.zzbmh = z;
        if (z) {
            this.zzbmg = zzqe.zzjp();
            long j = (long) (1.0E9d / d);
            this.zzbmi = j;
            this.zzbmj = (j * 80) / 100;
            return;
        }
        this.zzbmg = null;
        this.zzbmi = -1;
        this.zzbmj = -1;
    }

    public final void enable() {
        this.zzbmn = false;
        if (this.zzbmh) {
            this.zzbmg.zzjq();
        }
    }

    public final void disable() {
        if (this.zzbmh) {
            this.zzbmg.zzjr();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0086 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zzf(long r12, long r14) {
        /*
            r11 = this;
            r0 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r12
            boolean r2 = r11.zzbmn
            if (r2 == 0) goto L_0x0042
            long r2 = r11.zzbmk
            int r4 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0019
            long r2 = r11.zzbmq
            r4 = 1
            long r2 = r2 + r4
            r11.zzbmq = r2
            long r2 = r11.zzbmm
            r11.zzbml = r2
        L_0x0019:
            long r2 = r11.zzbmq
            r4 = 6
            r6 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 < 0) goto L_0x003a
            long r4 = r11.zzbmp
            long r4 = r0 - r4
            long r4 = r4 / r2
            long r2 = r11.zzbml
            long r2 = r2 + r4
            boolean r4 = r11.zzg(r2, r14)
            if (r4 == 0) goto L_0x0033
            r11.zzbmn = r6
            goto L_0x0042
        L_0x0033:
            long r4 = r11.zzbmo
            long r4 = r4 + r2
            long r6 = r11.zzbmp
            long r4 = r4 - r6
            goto L_0x0044
        L_0x003a:
            boolean r2 = r11.zzg(r0, r14)
            if (r2 == 0) goto L_0x0042
            r11.zzbmn = r6
        L_0x0042:
            r4 = r14
            r2 = r0
        L_0x0044:
            boolean r6 = r11.zzbmn
            r7 = 0
            if (r6 != 0) goto L_0x0053
            r11.zzbmp = r0
            r11.zzbmo = r14
            r11.zzbmq = r7
            r14 = 1
            r11.zzbmn = r14
        L_0x0053:
            r11.zzbmk = r12
            r11.zzbmm = r2
            com.google.android.gms.internal.ads.zzqe r12 = r11.zzbmg
            if (r12 == 0) goto L_0x0086
            long r12 = r12.zzbms
            int r14 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r14 != 0) goto L_0x0062
            goto L_0x0086
        L_0x0062:
            com.google.android.gms.internal.ads.zzqe r12 = r11.zzbmg
            long r12 = r12.zzbms
            long r14 = r11.zzbmi
            long r0 = r4 - r12
            long r0 = r0 / r14
            long r0 = r0 * r14
            long r12 = r12 + r0
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 > 0) goto L_0x0075
            long r14 = r12 - r14
            goto L_0x0079
        L_0x0075:
            long r14 = r14 + r12
            r9 = r12
            r12 = r14
            r14 = r9
        L_0x0079:
            long r0 = r12 - r4
            long r4 = r4 - r14
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0081
            goto L_0x0082
        L_0x0081:
            r12 = r14
        L_0x0082:
            long r14 = r11.zzbmj
            long r12 = r12 - r14
            return r12
        L_0x0086:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqb.zzf(long, long):long");
    }

    private final boolean zzg(long j, long j2) {
        return Math.abs((j2 - this.zzbmo) - (j - this.zzbmp)) > 20000000;
    }
}
