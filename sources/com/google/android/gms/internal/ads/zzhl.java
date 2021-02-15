package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzhl implements Handler.Callback, zzna, zznc, zzof {
    private final Handler handler;
    private int repeatMode = 0;
    private int state;
    private final zzhx[] zzaep;
    private final zzog zzaeq;
    private final Handler zzaes;
    private final zzid zzaev;
    private final zzia zzaew;
    private boolean zzaey;
    private boolean zzafc;
    private zzhy zzafd;
    private zzhu zzafh;
    private zzhn zzafi;
    private final zzhw[] zzafy;
    private final zzhs zzafz;
    private zzmz zzaga;
    private final zzpl zzagc;
    private final HandlerThread zzagd;
    private final zzhc zzage;
    private zzhx zzagf;
    private zzpd zzagg;
    private zzhx[] zzagh;
    private boolean zzagi;
    private boolean zzagj;
    private int zzagk;
    private int zzagl;
    private long zzagm;
    private int zzagn;
    private zzhm zzago;
    private long zzagp;
    private zzhk zzagq;
    private zzhk zzagr;
    private zzhk zzags;

    public zzhl(zzhx[] zzhxArr, zzog zzog, zzhs zzhs, boolean z, int i, Handler handler2, zzhn zzhn, zzhc zzhc) {
        this.zzaep = zzhxArr;
        this.zzaeq = zzog;
        this.zzafz = zzhs;
        this.zzaey = z;
        this.zzaes = handler2;
        this.state = 1;
        this.zzafi = zzhn;
        this.zzage = zzhc;
        this.zzafy = new zzhw[zzhxArr.length];
        for (int i2 = 0; i2 < zzhxArr.length; i2++) {
            zzhxArr[i2].setIndex(i2);
            this.zzafy[i2] = zzhxArr[i2].zzdz();
        }
        this.zzagc = new zzpl();
        this.zzagh = new zzhx[0];
        this.zzaev = new zzid();
        this.zzaew = new zzia();
        zzog.zza(this);
        this.zzafh = zzhu.zzahz;
        HandlerThread handlerThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.zzagd = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.zzagd.getLooper(), this);
    }

    public final void zza(zzmz zzmz, boolean z) {
        this.handler.obtainMessage(0, 1, 0, zzmz).sendToTarget();
    }

    public final void zzf(boolean z) {
        this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public final void zza(zzhy zzhy, int i, long j) {
        this.handler.obtainMessage(3, new zzhm(zzhy, i, j)).sendToTarget();
    }

    public final void stop() {
        this.handler.sendEmptyMessage(5);
    }

    public final void zza(zzhh... zzhhArr) {
        if (this.zzagi) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            return;
        }
        this.zzagk++;
        this.handler.obtainMessage(11, zzhhArr).sendToTarget();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:11|12|13|14|23|20|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x001f, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0027 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzb(com.google.android.gms.internal.ads.zzhh... r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.zzagi     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x000e
            java.lang.String r4 = "ExoPlayerImplInternal"
            java.lang.String r0 = "Ignoring messages sent after release."
            android.util.Log.w(r4, r0)     // Catch:{ all -> 0x0031 }
            monitor-exit(r3)
            return
        L_0x000e:
            int r0 = r3.zzagk     // Catch:{ all -> 0x0031 }
            int r1 = r0 + 1
            r3.zzagk = r1     // Catch:{ all -> 0x0031 }
            android.os.Handler r1 = r3.handler     // Catch:{ all -> 0x0031 }
            r2 = 11
            android.os.Message r4 = r1.obtainMessage(r2, r4)     // Catch:{ all -> 0x0031 }
            r4.sendToTarget()     // Catch:{ all -> 0x0031 }
        L_0x001f:
            int r4 = r3.zzagl     // Catch:{ all -> 0x0031 }
            if (r4 > r0) goto L_0x002f
            r3.wait()     // Catch:{ InterruptedException -> 0x0027 }
            goto L_0x001f
        L_0x0027:
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0031 }
            r4.interrupt()     // Catch:{ all -> 0x0031 }
            goto L_0x001f
        L_0x002f:
            monitor-exit(r3)
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhl.zzb(com.google.android.gms.internal.ads.zzhh[]):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:10|11|12|13|23|20|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x000d, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void release() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.zzagi     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            android.os.Handler r0 = r2.handler     // Catch:{ all -> 0x0024 }
            r1 = 6
            r0.sendEmptyMessage(r1)     // Catch:{ all -> 0x0024 }
        L_0x000d:
            boolean r0 = r2.zzagi     // Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x001d
            r2.wait()     // Catch:{ InterruptedException -> 0x0015 }
            goto L_0x000d
        L_0x0015:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0024 }
            r0.interrupt()     // Catch:{ all -> 0x0024 }
            goto L_0x000d
        L_0x001d:
            android.os.HandlerThread r0 = r2.zzagd     // Catch:{ all -> 0x0024 }
            r0.quit()     // Catch:{ all -> 0x0024 }
            monitor-exit(r2)
            return
        L_0x0024:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhl.release():void");
    }

    public final void zzb(zzhy zzhy, Object obj) {
        this.handler.obtainMessage(7, Pair.create(zzhy, obj)).sendToTarget();
    }

    public final void zza(zzmx zzmx) {
        this.handler.obtainMessage(8, zzmx).sendToTarget();
    }

    public final void zzes() {
        this.handler.sendEmptyMessage(10);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0382, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0448, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0449, code lost:
        r2 = r0;
        r8.zzafi = new com.google.android.gms.internal.ads.zzhn(r3, r4);
        r3 = r8.zzaes;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x0453, code lost:
        if (r1 != false) goto L_0x0455;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x0455, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0457, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0458, code lost:
        r3.obtainMessage(4, r1, 0, r8.zzafi).sendToTarget();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x0461, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:499:0x089e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:500:0x089f, code lost:
        r1 = r0;
        r3 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:501:0x08a3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:502:0x08a4, code lost:
        r1 = r0;
        r3 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:503:0x08a8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:504:0x08a9, code lost:
        r1 = r0;
        android.util.Log.e("ExoPlayerImplInternal", "Internal runtime error.", r1);
        r8.zzaes.obtainMessage(8, com.google.android.gms.internal.ads.zzhd.zza(r1)).sendToTarget();
        zzew();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:505:0x08c3, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00c4, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00c8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00c9, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00ca, code lost:
        monitor-enter(r34);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r8.zzagl++;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00d5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0287 A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x028a A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x028e A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0350 A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x0364 A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x052c A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x0533 A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x054d A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:313:0x0550 A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x058b A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x059d A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x05b9 A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }, LOOP:8: B:331:0x05b9->B:335:0x05cb, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:404:0x0718 A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:443:0x07d1 A[Catch:{ all -> 0x0448, all -> 0x0382, all -> 0x00c8, all -> 0x00d5, all -> 0x00c4, zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:503:0x08a8 A[ExcHandler: RuntimeException (r0v2 'e' java.lang.RuntimeException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r35) {
        /*
            r34 = this;
            r8 = r34
            r1 = r35
            r10 = 1
            int r2 = r1.what     // Catch:{ zzhd -> 0x08e0, IOException -> 0x08c4, RuntimeException -> 0x08a8 }
            r11 = 7
            r3 = 0
            r14 = 3
            r5 = -1
            r6 = 0
            r15 = 4
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7 = 2
            r9 = 0
            switch(r2) {
                case 0: goto L_0x0867;
                case 1: goto L_0x083a;
                case 2: goto L_0x0462;
                case 3: goto L_0x03ab;
                case 4: goto L_0x038a;
                case 5: goto L_0x0386;
                case 6: goto L_0x036f;
                case 7: goto L_0x0213;
                case 8: goto L_0x01df;
                case 9: goto L_0x01cc;
                case 10: goto L_0x00d9;
                case 11: goto L_0x009b;
                case 12: goto L_0x0019;
                default: goto L_0x0018;
            }
        L_0x0018:
            return r9
        L_0x0019:
            int r1 = r1.arg1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.repeatMode = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0026
        L_0x0024:
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0026:
            if (r2 == 0) goto L_0x009a
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != r3) goto L_0x002e
            r3 = 1
            goto L_0x002f
        L_0x002e:
            r3 = 0
        L_0x002f:
            com.google.android.gms.internal.ads.zzhk r4 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != r4) goto L_0x0035
            r4 = 1
            goto L_0x0036
        L_0x0035:
            r4 = 0
        L_0x0036:
            com.google.android.gms.internal.ads.zzhy r11 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r12 = r2.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r13 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzid r14 = r8.zzaev     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r11 = r11.zza((int) r12, (com.google.android.gms.internal.ads.zzia) r13, (com.google.android.gms.internal.ads.zzid) r14, (int) r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r12 = r2.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r12 == 0) goto L_0x0061
            if (r11 == r5) goto L_0x0061
            com.google.android.gms.internal.ads.zzhk r12 = r2.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r12 = r12.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r12 != r11) goto L_0x0061
            com.google.android.gms.internal.ads.zzhk r2 = r2.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r11 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != r11) goto L_0x0056
            r11 = 1
            goto L_0x0057
        L_0x0056:
            r11 = 0
        L_0x0057:
            r3 = r3 | r11
            com.google.android.gms.internal.ads.zzhk r11 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != r11) goto L_0x005e
            r11 = 1
            goto L_0x005f
        L_0x005e:
            r11 = 0
        L_0x005f:
            r4 = r4 | r11
            goto L_0x0036
        L_0x0061:
            com.google.android.gms.internal.ads.zzhk r5 = r2.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r5 == 0) goto L_0x006c
            com.google.android.gms.internal.ads.zzhk r5 = r2.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            zza((com.google.android.gms.internal.ads.zzhk) r5)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zzafw = r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x006c:
            int r5 = r2.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r5 = r8.zzu(r5)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zzaft = r5     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r4 != 0) goto L_0x0078
            r8.zzagq = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0078:
            if (r3 != 0) goto L_0x0091
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x0091
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r3 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r3.zzagv     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r8.zza((int) r2, (long) r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r5 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r5.<init>(r2, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r5     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0091:
            int r2 = r8.state     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != r15) goto L_0x009a
            if (r1 == 0) goto L_0x009a
            r8.setState(r7)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x009a:
            return r10
        L_0x009b:
            java.lang.Object r1 = r1.obj     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhh[] r1 = (com.google.android.gms.internal.ads.zzhh[]) r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r1.length     // Catch:{ all -> 0x00c8 }
        L_0x00a0:
            if (r9 >= r2) goto L_0x00b0
            r3 = r1[r9]     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.internal.ads.zzhe r4 = r3.zzael     // Catch:{ all -> 0x00c8 }
            int r5 = r3.zzaem     // Catch:{ all -> 0x00c8 }
            java.lang.Object r3 = r3.zzaen     // Catch:{ all -> 0x00c8 }
            r4.zza(r5, r3)     // Catch:{ all -> 0x00c8 }
            int r9 = r9 + 1
            goto L_0x00a0
        L_0x00b0:
            com.google.android.gms.internal.ads.zzmz r1 = r8.zzaga     // Catch:{ all -> 0x00c8 }
            if (r1 == 0) goto L_0x00b9
            android.os.Handler r1 = r8.handler     // Catch:{ all -> 0x00c8 }
            r1.sendEmptyMessage(r7)     // Catch:{ all -> 0x00c8 }
        L_0x00b9:
            monitor-enter(r34)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r1 = r8.zzagl     // Catch:{ all -> 0x00c4 }
            int r1 = r1 + r10
            r8.zzagl = r1     // Catch:{ all -> 0x00c4 }
            r34.notifyAll()     // Catch:{ all -> 0x00c4 }
            monitor-exit(r34)     // Catch:{ all -> 0x00c4 }
            return r10
        L_0x00c4:
            r0 = move-exception
            r1 = r0
            monitor-exit(r34)     // Catch:{ all -> 0x00c4 }
            throw r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x00c8:
            r0 = move-exception
            r1 = r0
            monitor-enter(r34)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r8.zzagl     // Catch:{ all -> 0x00d5 }
            int r2 = r2 + r10
            r8.zzagl = r2     // Catch:{ all -> 0x00d5 }
            r34.notifyAll()     // Catch:{ all -> 0x00d5 }
            monitor-exit(r34)     // Catch:{ all -> 0x00d5 }
            throw r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x00d5:
            r0 = move-exception
            r1 = r0
            monitor-exit(r34)     // Catch:{ all -> 0x00d5 }
            throw r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x00d9:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x01cb
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2 = 1
        L_0x00e0:
            if (r1 == 0) goto L_0x01cb
            boolean r3 = r1.zzafu     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 != 0) goto L_0x00e8
            goto L_0x01cb
        L_0x00e8:
            boolean r3 = r1.zzer()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 != 0) goto L_0x00f6
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != r3) goto L_0x00f3
            r2 = 0
        L_0x00f3:
            com.google.android.gms.internal.ads.zzhk r1 = r1.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x00e0
        L_0x00f6:
            if (r2 == 0) goto L_0x0194
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == r3) goto L_0x0100
            r2 = 1
            goto L_0x0101
        L_0x0100:
            r2 = 0
        L_0x0101:
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r3.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            zza((com.google.android.gms.internal.ads.zzhk) r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.zzafw = r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzagq = r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzagr = r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhx[] r3 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r3 = r3.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean[] r3 = new boolean[r3]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r4 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r5 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r11 = r5.zzagv     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r4 = r4.zza(r11, r2, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r2 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r11 = r2.zzagv     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r2 == 0) goto L_0x0132
            com.google.android.gms.internal.ads.zzhn r2 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zzagv = r4     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzdq(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0132:
            com.google.android.gms.internal.ads.zzhx[] r2 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean[] r2 = new boolean[r2]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r4 = 0
            r5 = 0
        L_0x0139:
            com.google.android.gms.internal.ads.zzhx[] r11 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r11 = r11.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r4 >= r11) goto L_0x0185
            com.google.android.gms.internal.ads.zzhx[] r11 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r11 = r11[r4]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r12 = r11.getState()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r12 == 0) goto L_0x014a
            r12 = 1
            goto L_0x014b
        L_0x014a:
            r12 = 0
        L_0x014b:
            r2[r4] = r12     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r12 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zznm[] r12 = r12.zzafo     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r12 = r12[r4]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r12 == 0) goto L_0x0157
            int r5 = r5 + 1
        L_0x0157:
            boolean r13 = r2[r4]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r13 == 0) goto L_0x0182
            com.google.android.gms.internal.ads.zznm r13 = r11.zzeb()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r12 == r13) goto L_0x0179
            com.google.android.gms.internal.ads.zzhx r13 = r8.zzagf     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r11 != r13) goto L_0x0172
            if (r12 != 0) goto L_0x016e
            com.google.android.gms.internal.ads.zzpl r12 = r8.zzagc     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzpd r13 = r8.zzagg     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r12.zza(r13)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x016e:
            r8.zzagg = r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzagf = r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0172:
            zza((com.google.android.gms.internal.ads.zzhx) r11)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r11.disable()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0182
        L_0x0179:
            boolean r12 = r3[r4]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r12 == 0) goto L_0x0182
            long r12 = r8.zzagp     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r11.zzdo(r12)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0182:
            int r4 = r4 + 1
            goto L_0x0139
        L_0x0185:
            android.os.Handler r3 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzoi r1 = r1.zzafx     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Message r1 = r3.obtainMessage(r14, r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendToTarget()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zza((boolean[]) r2, (int) r5)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x01c0
        L_0x0194:
            r8.zzagq = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r1.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0198:
            if (r1 == 0) goto L_0x01a0
            r1.release()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r1.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0198
        L_0x01a0:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzafw = r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r1 = r1.zzafu     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x01c0
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r1 = r1.zzafs     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r4 = r8.zzagp     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r11 = r3.zzep()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r4 = r4 - r11
            long r1 = java.lang.Math.max(r1, r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.zzb(r1, r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x01c0:
            r34.zzey()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r34.zzev()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r1 = r8.handler     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendEmptyMessage(r7)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x01cb:
            return r10
        L_0x01cc:
            java.lang.Object r1 = r1.obj     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmx r1 = (com.google.android.gms.internal.ads.zzmx) r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x01de
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmx r2 = r2.zzafm     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == r1) goto L_0x01db
            goto L_0x01de
        L_0x01db:
            r34.zzey()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x01de:
            return r10
        L_0x01df:
            java.lang.Object r1 = r1.obj     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmx r1 = (com.google.android.gms.internal.ads.zzmx) r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x0212
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmx r2 = r2.zzafm     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == r1) goto L_0x01ee
            goto L_0x0212
        L_0x01ee:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzafu = r10     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzer()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r2 = r1.zzafs     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r2 = r1.zzb(r2, r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzafs = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != 0) goto L_0x020f
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzagr = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r1 = r1.zzafs     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzdq(r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzb((com.google.android.gms.internal.ads.zzhk) r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x020f:
            r34.zzey()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0212:
            return r10
        L_0x0213:
            java.lang.Object r1 = r1.obj     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.util.Pair r1 = (android.util.Pair) r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r3 = r1.first     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r3 = (com.google.android.gms.internal.ads.zzhy) r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafd = r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r1 = r1.second     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x0282
            int r3 = r8.zzagn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 <= 0) goto L_0x0252
            com.google.android.gms.internal.ads.zzhm r3 = r8.zzago     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.util.Pair r3 = r8.zza((com.google.android.gms.internal.ads.zzhm) r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r4 = r8.zzagn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzagn = r9     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzago = r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 != 0) goto L_0x023a
            r8.zza((java.lang.Object) r1, (int) r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x036e
        L_0x023a:
            com.google.android.gms.internal.ads.zzhn r7 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r11 = r3.first     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Integer r11 = (java.lang.Integer) r11     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r11 = r11.intValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r3 = r3.second     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r14 = r3.longValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r7.<init>(r11, r14)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r7     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0283
        L_0x0252:
            com.google.android.gms.internal.ads.zzhn r3 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r3.zzafs     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r7 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r7 != 0) goto L_0x0282
            com.google.android.gms.internal.ads.zzhy r3 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r3 = r3.isEmpty()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 == 0) goto L_0x0267
            r8.zza((java.lang.Object) r1, (int) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x036e
        L_0x0267:
            android.util.Pair r3 = r8.zzb((int) r9, (long) r12)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r4 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r7 = r3.first     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r7 = r7.intValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r3 = r3.second     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r14 = r3.longValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r4.<init>(r7, r14)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r4     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0282:
            r4 = 0
        L_0x0283:
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 == 0) goto L_0x028a
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x028c
        L_0x028a:
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x028c:
            if (r3 == 0) goto L_0x036b
            com.google.android.gms.internal.ads.zzhy r7 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r11 = r3.zzafn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r7 = r7.zzc(r11)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r7 != r5) goto L_0x02f0
            int r6 = r3.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r7 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r8.zza((int) r6, (com.google.android.gms.internal.ads.zzhy) r2, (com.google.android.gms.internal.ads.zzhy) r7)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != r5) goto L_0x02a7
            r8.zza((java.lang.Object) r1, (int) r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x036e
        L_0x02a7:
            com.google.android.gms.internal.ads.zzhy r6 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r7 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r6.zza((int) r2, (com.google.android.gms.internal.ads.zzia) r7, (boolean) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.util.Pair r2 = r8.zzb((int) r9, (long) r12)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r6 = r2.first     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r6 = r6.intValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r2 = r2.second     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r11 = r2.longValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r7 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zza((int) r6, (com.google.android.gms.internal.ads.zzia) r7, (boolean) r10)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r2 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r2 = r2.zzafn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.zzafr = r5     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x02cf:
            com.google.android.gms.internal.ads.zzhk r7 = r3.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r7 == 0) goto L_0x02e3
            com.google.android.gms.internal.ads.zzhk r3 = r3.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r7 = r3.zzafn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r7 = r7.equals(r2)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r7 == 0) goto L_0x02df
            r7 = r6
            goto L_0x02e0
        L_0x02df:
            r7 = -1
        L_0x02e0:
            r3.zzafr = r7     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x02cf
        L_0x02e3:
            long r2 = r8.zza((int) r6, (long) r11)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r5 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r5.<init>(r6, r2)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r5     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x036b
        L_0x02f0:
            boolean r2 = r8.zzu(r7)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.zzc(r7, r2)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 != r2) goto L_0x02fd
            r2 = 1
            goto L_0x02fe
        L_0x02fd:
            r2 = 0
        L_0x02fe:
            com.google.android.gms.internal.ads.zzhn r11 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r11 = r11.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r7 == r11) goto L_0x0317
            com.google.android.gms.internal.ads.zzhn r11 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r12 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r13 = r11.zzafs     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r12.<init>(r7, r13)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r13 = r11.zzagv     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r12.zzagv = r13     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r13 = r11.zzagw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r12.zzagw = r13     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r12     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0317:
            com.google.android.gms.internal.ads.zzhk r11 = r3.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r11 == 0) goto L_0x036b
            com.google.android.gms.internal.ads.zzhk r11 = r3.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r12 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r13 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzid r14 = r8.zzaev     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r15 = r8.repeatMode     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r7 = r12.zza((int) r7, (com.google.android.gms.internal.ads.zzia) r13, (com.google.android.gms.internal.ads.zzid) r14, (int) r15)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r7 == r5) goto L_0x034e
            java.lang.Object r12 = r11.zzafn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r13 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r14 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r13 = r13.zza((int) r7, (com.google.android.gms.internal.ads.zzia) r14, (boolean) r10)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r13 = r13.zzafn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r12 = r12.equals(r13)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r12 == 0) goto L_0x034e
            boolean r3 = r8.zzu(r7)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r11.zzc(r7, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r11 != r3) goto L_0x034a
            r3 = 1
            goto L_0x034b
        L_0x034a:
            r3 = 0
        L_0x034b:
            r2 = r2 | r3
            r3 = r11
            goto L_0x0317
        L_0x034e:
            if (r2 != 0) goto L_0x0364
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r3 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r5 = r3.zzagv     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r5 = r8.zza((int) r2, (long) r5)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r3 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.<init>(r2, r5)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x036b
        L_0x0364:
            r8.zzagq = r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.zzafw = r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            zza((com.google.android.gms.internal.ads.zzhk) r11)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x036b:
            r8.zzb((java.lang.Object) r1, (int) r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x036e:
            return r10
        L_0x036f:
            r8.zzi(r10)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhs r1 = r8.zzafz     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzfc()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.setState(r10)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            monitor-enter(r34)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzagi = r10     // Catch:{ all -> 0x0382 }
            r34.notifyAll()     // Catch:{ all -> 0x0382 }
            monitor-exit(r34)     // Catch:{ all -> 0x0382 }
            return r10
        L_0x0382:
            r0 = move-exception
            r1 = r0
            monitor-exit(r34)     // Catch:{ all -> 0x0382 }
            throw r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0386:
            r34.zzew()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            return r10
        L_0x038a:
            java.lang.Object r1 = r1.obj     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhu r1 = (com.google.android.gms.internal.ads.zzhu) r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzpd r2 = r8.zzagg     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x0399
            com.google.android.gms.internal.ads.zzpd r2 = r8.zzagg     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhu r1 = r2.zzb(r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x039f
        L_0x0399:
            com.google.android.gms.internal.ads.zzpl r2 = r8.zzagc     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhu r1 = r2.zzb(r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x039f:
            r8.zzafh = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r2 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Message r1 = r2.obtainMessage(r11, r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendToTarget()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            return r10
        L_0x03ab:
            java.lang.Object r1 = r1.obj     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhm r1 = (com.google.android.gms.internal.ads.zzhm) r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x03bc
            int r2 = r8.zzagn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2 + r10
            r8.zzagn = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzago = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0447
        L_0x03bc:
            android.util.Pair r2 = r8.zza((com.google.android.gms.internal.ads.zzhm) r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x03e0
            com.google.android.gms.internal.ads.zzhn r1 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.<init>(r9, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r2 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Message r1 = r2.obtainMessage(r15, r10, r9, r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendToTarget()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r1 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.<init>(r9, r12)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.setState(r15)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzi(r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0447
        L_0x03e0:
            long r3 = r1.zzagu     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r1 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x03e8
            r1 = 1
            goto L_0x03e9
        L_0x03e8:
            r1 = 0
        L_0x03e9:
            java.lang.Object r3 = r2.first     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r3 = r3.intValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r2 = r2.second     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r4 = r2.longValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r2 = r8.zzafi     // Catch:{ all -> 0x0448 }
            int r2 = r2.zzafr     // Catch:{ all -> 0x0448 }
            if (r3 != r2) goto L_0x0424
            r6 = 1000(0x3e8, double:4.94E-321)
            long r11 = r4 / r6
            com.google.android.gms.internal.ads.zzhn r2 = r8.zzafi     // Catch:{ all -> 0x0448 }
            long r13 = r2.zzagv     // Catch:{ all -> 0x0448 }
            long r13 = r13 / r6
            int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x0424
            com.google.android.gms.internal.ads.zzhn r2 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.<init>(r3, r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r2 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0419
            r1 = 1
            goto L_0x041a
        L_0x0419:
            r1 = 0
        L_0x041a:
            com.google.android.gms.internal.ads.zzhn r3 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Message r1 = r2.obtainMessage(r15, r1, r9, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendToTarget()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0447
        L_0x0424:
            long r6 = r8.zza((int) r3, (long) r4)     // Catch:{ all -> 0x0448 }
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x042e
            r2 = 1
            goto L_0x042f
        L_0x042e:
            r2 = 0
        L_0x042f:
            r1 = r1 | r2
            com.google.android.gms.internal.ads.zzhn r2 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.<init>(r3, r6)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r2 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x043d
            r1 = 1
            goto L_0x043e
        L_0x043d:
            r1 = 0
        L_0x043e:
            com.google.android.gms.internal.ads.zzhn r3 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Message r1 = r2.obtainMessage(r15, r1, r9, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendToTarget()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0447:
            return r10
        L_0x0448:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.ads.zzhn r6 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r6.<init>(r3, r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r6     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r3 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0457
            r1 = 1
            goto L_0x0458
        L_0x0457:
            r1 = 0
        L_0x0458:
            com.google.android.gms.internal.ads.zzhn r4 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Message r1 = r3.obtainMessage(r15, r1, r9, r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendToTarget()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            throw r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0462:
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r1 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != 0) goto L_0x0472
            com.google.android.gms.internal.ads.zzmz r1 = r8.zzaga     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzhz()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r14 = r5
            goto L_0x06c9
        L_0x0472:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != 0) goto L_0x047b
            com.google.android.gms.internal.ads.zzhn r1 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r1 = r1.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x04b9
        L_0x047b:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r1 = r1.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r2 = r2.zzaft     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x04c6
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r2 = r2.zzeq()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x04c6
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r7 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r2 = r2.zza((int) r1, (com.google.android.gms.internal.ads.zzia) r7, (boolean) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r14 = r2.zzaih     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x049c
            goto L_0x04c6
        L_0x049c:
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x04ad
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.index     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r7 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r7 = r7.index     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2 - r7
            r7 = 100
            if (r2 == r7) goto L_0x04c6
        L_0x04ad:
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r7 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzid r14 = r8.zzaev     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r15 = r8.repeatMode     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r1 = r2.zza((int) r1, (com.google.android.gms.internal.ads.zzia) r7, (com.google.android.gms.internal.ads.zzid) r14, (int) r15)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x04b9:
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.zzfg()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 < r2) goto L_0x04c9
            com.google.android.gms.internal.ads.zzmz r1 = r8.zzaga     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzhz()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x04c6:
            r14 = r5
            goto L_0x0599
        L_0x04c9:
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x04d3
            com.google.android.gms.internal.ads.zzhn r2 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r2.zzagv     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x04d1:
            r14 = r5
            goto L_0x0528
        L_0x04d3:
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r7 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zza((int) r1, (com.google.android.gms.internal.ads.zzia) r7, (boolean) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzid r7 = r8.zzaev     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zza((int) r9, (com.google.android.gms.internal.ads.zzid) r7, (boolean) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x04e4
            goto L_0x04d1
        L_0x04e4:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r1 = r1.zzep()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r7 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r14 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r14 = r14.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r15 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r7 = r7.zza((int) r14, (com.google.android.gms.internal.ads.zzia) r15, (boolean) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r14 = r7.zzaih     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r1 = r1 + r14
            long r14 = r8.zzagp     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r1 = r1 - r14
            com.google.android.gms.internal.ads.zzhy r7 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r14 = 0
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r18 = java.lang.Math.max(r3, r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1 = r34
            r2 = r7
            r3 = r14
            r14 = r5
            r4 = r16
            r6 = r18
            android.util.Pair r1 = r1.zza(r2, r3, r4, r6)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0599
            java.lang.Object r2 = r1.first     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.intValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r1 = r1.second     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r1.longValue()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1 = r2
        L_0x0528:
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x0533
            r5 = 60000000(0x3938700, double:2.96439388E-316)
            long r5 = r5 + r3
        L_0x0530:
            r23 = r5
            goto L_0x0549
        L_0x0533:
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r5 = r2.zzep()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r7 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r7 = r7.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r11 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r2 = r2.zza((int) r7, (com.google.android.gms.internal.ads.zzia) r11, (boolean) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r12 = r2.zzaih     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r5 = r5 + r12
            goto L_0x0530
        L_0x0549:
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x0550
            r29 = 0
            goto L_0x0557
        L_0x0550:
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.index     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2 + r10
            r29 = r2
        L_0x0557:
            boolean r31 = r8.zzu(r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r5 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zza((int) r1, (com.google.android.gms.internal.ads.zzia) r5, (boolean) r10)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = new com.google.android.gms.internal.ads.zzhk     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhx[] r5 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhw[] r6 = r8.zzafy     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzog r7 = r8.zzaeq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhs r11 = r8.zzafz     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmz r12 = r8.zzaga     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r13 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            java.lang.Object r13 = r13.zzafn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r20 = r2
            r21 = r5
            r22 = r6
            r25 = r7
            r26 = r11
            r27 = r12
            r28 = r13
            r30 = r1
            r32 = r3
            r20.<init>(r21, r22, r23, r25, r26, r27, r28, r29, r30, r31, r32)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x058f
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzafw = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x058f:
            r8.zzagq = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmx r1 = r2.zzafm     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zza(r8, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzh(r10)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0599:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x05b2
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r1 = r1.zzeq()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x05a6
            goto L_0x05b2
        L_0x05a6:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x05b5
            boolean r1 = r8.zzafc     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != 0) goto L_0x05b5
            r34.zzey()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x05b5
        L_0x05b2:
            r8.zzh(r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x05b5:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x06c9
        L_0x05b9:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == r2) goto L_0x05f6
            long r1 = r8.zzagp     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r3.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r3.zzafq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x05f6
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.release()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r1.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzb((com.google.android.gms.internal.ads.zzhk) r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r1 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r3.zzafs     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.<init>(r2, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r34.zzev()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r1 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2 = 5
            com.google.android.gms.internal.ads.zzhn r3 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Message r1 = r1.obtainMessage(r2, r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendToTarget()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x05b9
        L_0x05f6:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r1 = r1.zzaft     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0620
            r1 = 0
        L_0x05fd:
            com.google.android.gms.internal.ads.zzhx[] r2 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 >= r2) goto L_0x06c9
            com.google.android.gms.internal.ads.zzhx[] r2 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2 = r2[r1]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zznm[] r3 = r3.zzafo     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3 = r3[r1]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 == 0) goto L_0x061d
            com.google.android.gms.internal.ads.zznm r4 = r2.zzeb()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r4 != r3) goto L_0x061d
            boolean r3 = r2.zzec()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 == 0) goto L_0x061d
            r2.zzed()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x061d:
            int r1 = r1 + 1
            goto L_0x05fd
        L_0x0620:
            r1 = 0
        L_0x0621:
            com.google.android.gms.internal.ads.zzhx[] r2 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 >= r2) goto L_0x0643
            com.google.android.gms.internal.ads.zzhx[] r2 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2 = r2[r1]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zznm[] r3 = r3.zzafo     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3 = r3[r1]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zznm r4 = r2.zzeb()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r4 != r3) goto L_0x06c9
            if (r3 == 0) goto L_0x0640
            boolean r2 = r2.zzec()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x0640
            goto L_0x06c9
        L_0x0640:
            int r1 = r1 + 1
            goto L_0x0621
        L_0x0643:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r1.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x06c9
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r1.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r1 = r1.zzafu     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x06c9
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzoi r1 = r1.zzafx     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r2.zzafw     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzagr = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzoi r2 = r2.zzafx     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmx r3 = r3.zzafm     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r3.zzhq()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0670
            r3 = 1
            goto L_0x0671
        L_0x0670:
            r3 = 0
        L_0x0671:
            r4 = 0
        L_0x0672:
            com.google.android.gms.internal.ads.zzhx[] r5 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r5 = r5.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r4 >= r5) goto L_0x06c9
            com.google.android.gms.internal.ads.zzhx[] r5 = r8.zzaep     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r5 = r5[r4]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzod r6 = r1.zzbhx     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzob r6 = r6.zzbg(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r6 == 0) goto L_0x06c6
            if (r3 != 0) goto L_0x06c3
            boolean r6 = r5.zzee()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r6 != 0) goto L_0x06c6
            com.google.android.gms.internal.ads.zzod r6 = r2.zzbhx     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzob r6 = r6.zzbg(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhz[] r7 = r1.zzbhz     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r7 = r7[r4]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhz[] r11 = r2.zzbhz     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r11 = r11[r4]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r6 == 0) goto L_0x06c3
            boolean r7 = r11.equals(r7)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r7 == 0) goto L_0x06c3
            int r7 = r6.length()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzho[] r11 = new com.google.android.gms.internal.ads.zzho[r7]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r12 = 0
        L_0x06a8:
            if (r12 >= r7) goto L_0x06b3
            com.google.android.gms.internal.ads.zzho r13 = r6.zzbe(r12)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r11[r12] = r13     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r12 = r12 + 1
            goto L_0x06a8
        L_0x06b3:
            com.google.android.gms.internal.ads.zzhk r6 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zznm[] r6 = r6.zzafo     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r6 = r6[r4]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r7 = r8.zzagr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r12 = r7.zzep()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r5.zza(r11, r6, r12)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x06c6
        L_0x06c3:
            r5.zzed()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x06c6:
            int r4 = r4 + 1
            goto L_0x0672
        L_0x06c9:
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2 = 10
            if (r1 != 0) goto L_0x06d7
            r34.zzex()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zza((long) r14, (long) r2)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0839
        L_0x06d7:
            java.lang.String r1 = "doSomeWork"
            com.google.android.gms.internal.ads.zzpn.beginSection(r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r34.zzev()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r1 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmx r1 = r1.zzafm     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhn r4 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r4 = r4.zzagv     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.zzee(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhx[] r1 = r8.zzagh     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r4 = r1.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r5 = 0
            r6 = 1
            r7 = 1
        L_0x06f0:
            if (r5 >= r4) goto L_0x0727
            r11 = r1[r5]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r12 = r8.zzagp     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r2 = r8.zzagm     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r11.zzb(r12, r2)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r7 == 0) goto L_0x0705
            boolean r2 = r11.zzfe()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x0705
            r7 = 1
            goto L_0x0706
        L_0x0705:
            r7 = 0
        L_0x0706:
            boolean r2 = r11.isReady()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x0715
            boolean r2 = r11.zzfe()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x0713
            goto L_0x0715
        L_0x0713:
            r2 = 0
            goto L_0x0716
        L_0x0715:
            r2 = 1
        L_0x0716:
            if (r2 != 0) goto L_0x071b
            r11.zzef()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x071b:
            if (r6 == 0) goto L_0x0721
            if (r2 == 0) goto L_0x0721
            r6 = 1
            goto L_0x0722
        L_0x0721:
            r6 = 0
        L_0x0722:
            int r5 = r5 + 1
            r2 = 10
            goto L_0x06f0
        L_0x0727:
            if (r6 != 0) goto L_0x072c
            r34.zzex()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x072c:
            com.google.android.gms.internal.ads.zzpd r1 = r8.zzagg     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0751
            com.google.android.gms.internal.ads.zzpd r1 = r8.zzagg     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhu r1 = r1.zzfs()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhu r2 = r8.zzafh     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r2 = r1.equals(r2)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x0751
            r8.zzafh = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzpl r2 = r8.zzagc     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzpd r3 = r8.zzagg     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zza(r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r2 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3 = 7
            android.os.Message r1 = r2.obtainMessage(r3, r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendToTarget()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0751:
            com.google.android.gms.internal.ads.zzhy r1 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r2.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r3 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r1 = r1.zza((int) r2, (com.google.android.gms.internal.ads.zzia) r3, (boolean) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r1 = r1.zzaih     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r7 == 0) goto L_0x0782
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0772
            com.google.android.gms.internal.ads.zzhn r3 = r8.zzafi     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r3 = r3.zzagv     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0782
        L_0x0772:
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzags     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r3 = r3.zzaft     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 == 0) goto L_0x0782
            r3 = 4
            r8.setState(r3)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r34.zzeu()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r4 = 2
            goto L_0x0802
        L_0x0782:
            int r3 = r8.state     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r4 = 2
            if (r3 != r4) goto L_0x07e7
            com.google.android.gms.internal.ads.zzhx[] r3 = r8.zzagh     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r3 = r3.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 <= 0) goto L_0x07d5
            if (r6 == 0) goto L_0x07d3
            boolean r1 = r8.zzagj     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r2 = r2.zzafu     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 != 0) goto L_0x079b
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r2 = r2.zzafs     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x07a3
        L_0x079b:
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmx r2 = r2.zzafm     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r2 = r2.zzhr()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x07a3:
            r5 = -9223372036854775808
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x07bf
            com.google.android.gms.internal.ads.zzhk r2 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r2 = r2.zzaft     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r2 == 0) goto L_0x07b1
            r1 = 1
            goto L_0x07cf
        L_0x07b1:
            com.google.android.gms.internal.ads.zzhy r2 = r8.zzafd     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r3 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r3 = r3.zzafr     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r5 = r8.zzaew     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzia r2 = r2.zza((int) r3, (com.google.android.gms.internal.ads.zzia) r5, (boolean) r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r2 = r2.zzaih     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x07bf:
            com.google.android.gms.internal.ads.zzhs r5 = r8.zzafz     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhk r6 = r8.zzagq     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r11 = r8.zzagp     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r6 = r6.zzep()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            long r11 = r11 - r6
            long r2 = r2 - r11
            boolean r1 = r5.zzc(r2, r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x07cf:
            if (r1 == 0) goto L_0x07d3
            r1 = 1
            goto L_0x07d9
        L_0x07d3:
            r1 = 0
            goto L_0x07d9
        L_0x07d5:
            boolean r1 = r8.zzdr(r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x07d9:
            if (r1 == 0) goto L_0x0802
            r1 = 3
            r8.setState(r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            boolean r1 = r8.zzaey     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0802
            r34.zzet()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0802
        L_0x07e7:
            int r3 = r8.state     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r5 = 3
            if (r3 != r5) goto L_0x0802
            com.google.android.gms.internal.ads.zzhx[] r3 = r8.zzagh     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r3 = r3.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r3 <= 0) goto L_0x07f2
            goto L_0x07f6
        L_0x07f2:
            boolean r6 = r8.zzdr(r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x07f6:
            if (r6 != 0) goto L_0x0802
            boolean r1 = r8.zzaey     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzagj = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.setState(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r34.zzeu()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0802:
            int r1 = r8.state     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != r4) goto L_0x0813
            com.google.android.gms.internal.ads.zzhx[] r1 = r8.zzagh     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r2 = r1.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0809:
            if (r9 >= r2) goto L_0x0813
            r3 = r1[r9]     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.zzef()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r9 = r9 + 1
            goto L_0x0809
        L_0x0813:
            boolean r1 = r8.zzaey     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x081c
            int r1 = r8.state     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2 = 3
            if (r1 == r2) goto L_0x0820
        L_0x081c:
            int r1 = r8.state     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != r4) goto L_0x0826
        L_0x0820:
            r1 = 10
            r8.zza((long) r14, (long) r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0836
        L_0x0826:
            com.google.android.gms.internal.ads.zzhx[] r1 = r8.zzagh     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r1 = r1.length     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0831
            r1 = 1000(0x3e8, double:4.94E-321)
            r8.zza((long) r14, (long) r1)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0836
        L_0x0831:
            android.os.Handler r1 = r8.handler     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.removeMessages(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0836:
            com.google.android.gms.internal.ads.zzpn.endSection()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0839:
            return r10
        L_0x083a:
            r4 = 2
            int r1 = r1.arg1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0841
            r1 = 1
            goto L_0x0842
        L_0x0841:
            r1 = 0
        L_0x0842:
            r8.zzagj = r9     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzaey = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != 0) goto L_0x084f
            r34.zzeu()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r34.zzev()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0866
        L_0x084f:
            int r1 = r8.state     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2 = 3
            if (r1 != r2) goto L_0x085d
            r34.zzet()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r1 = r8.handler     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendEmptyMessage(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            goto L_0x0866
        L_0x085d:
            int r1 = r8.state     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 != r4) goto L_0x0866
            android.os.Handler r1 = r8.handler     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendEmptyMessage(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x0866:
            return r10
        L_0x0867:
            r4 = 2
            java.lang.Object r2 = r1.obj     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzmz r2 = (com.google.android.gms.internal.ads.zzmz) r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            int r1 = r1.arg1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x0872
            r1 = 1
            goto L_0x0873
        L_0x0872:
            r1 = 0
        L_0x0873:
            android.os.Handler r3 = r8.zzaes     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.sendEmptyMessage(r9)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzi(r10)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhs r3 = r8.zzafz     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r3.zzfb()     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            if (r1 == 0) goto L_0x088e
            com.google.android.gms.internal.ads.zzhn r1 = new com.google.android.gms.internal.ads.zzhn     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r1.<init>(r9, r5)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.zzafi = r1     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
        L_0x088e:
            r8.zzaga = r2     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            com.google.android.gms.internal.ads.zzhc r1 = r8.zzage     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r2.zza(r1, r10, r8)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r8.setState(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            android.os.Handler r1 = r8.handler     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            r1.sendEmptyMessage(r4)     // Catch:{ zzhd -> 0x08a3, IOException -> 0x089e, RuntimeException -> 0x08a8 }
            return r10
        L_0x089e:
            r0 = move-exception
            r1 = r0
            r3 = 8
            goto L_0x08c8
        L_0x08a3:
            r0 = move-exception
            r1 = r0
            r3 = 8
            goto L_0x08e4
        L_0x08a8:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "ExoPlayerImplInternal"
            java.lang.String r3 = "Internal runtime error."
            android.util.Log.e(r2, r3, r1)
            android.os.Handler r2 = r8.zzaes
            com.google.android.gms.internal.ads.zzhd r1 = com.google.android.gms.internal.ads.zzhd.zza((java.lang.RuntimeException) r1)
            r3 = 8
            android.os.Message r1 = r2.obtainMessage(r3, r1)
            r1.sendToTarget()
            r34.zzew()
            return r10
        L_0x08c4:
            r0 = move-exception
            r3 = 8
            r1 = r0
        L_0x08c8:
            java.lang.String r2 = "ExoPlayerImplInternal"
            java.lang.String r4 = "Source error."
            android.util.Log.e(r2, r4, r1)
            android.os.Handler r2 = r8.zzaes
            com.google.android.gms.internal.ads.zzhd r1 = com.google.android.gms.internal.ads.zzhd.zza((java.io.IOException) r1)
            android.os.Message r1 = r2.obtainMessage(r3, r1)
            r1.sendToTarget()
            r34.zzew()
            return r10
        L_0x08e0:
            r0 = move-exception
            r3 = 8
            r1 = r0
        L_0x08e4:
            java.lang.String r2 = "ExoPlayerImplInternal"
            java.lang.String r4 = "Renderer error."
            android.util.Log.e(r2, r4, r1)
            android.os.Handler r2 = r8.zzaes
            android.os.Message r1 = r2.obtainMessage(r3, r1)
            r1.sendToTarget()
            r34.zzew()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhl.handleMessage(android.os.Message):boolean");
    }

    private final void setState(int i) {
        if (this.state != i) {
            this.state = i;
            this.zzaes.obtainMessage(1, i, 0).sendToTarget();
        }
    }

    private final void zzh(boolean z) {
        if (this.zzafc != z) {
            this.zzafc = z;
            this.zzaes.obtainMessage(2, z ? 1 : 0, 0).sendToTarget();
        }
    }

    private final void zzet() throws zzhd {
        this.zzagj = false;
        this.zzagc.start();
        for (zzhx start : this.zzagh) {
            start.start();
        }
    }

    private final void zzeu() throws zzhd {
        this.zzagc.stop();
        for (zzhx zza : this.zzagh) {
            zza(zza);
        }
    }

    private final void zzev() throws zzhd {
        long j;
        zzhk zzhk = this.zzags;
        if (zzhk != null) {
            long zzhq = zzhk.zzafm.zzhq();
            if (zzhq != -9223372036854775807L) {
                zzdq(zzhq);
            } else {
                zzhx zzhx = this.zzagf;
                if (zzhx == null || zzhx.zzfe()) {
                    this.zzagp = this.zzagc.zzfz();
                } else {
                    long zzfz = this.zzagg.zzfz();
                    this.zzagp = zzfz;
                    this.zzagc.zzel(zzfz);
                }
                zzhq = this.zzagp - this.zzags.zzep();
            }
            this.zzafi.zzagv = zzhq;
            this.zzagm = SystemClock.elapsedRealtime() * 1000;
            if (this.zzagh.length == 0) {
                j = Long.MIN_VALUE;
            } else {
                j = this.zzags.zzafm.zzhr();
            }
            zzhn zzhn = this.zzafi;
            if (j == Long.MIN_VALUE) {
                j = this.zzafd.zza(this.zzags.zzafr, this.zzaew, false).zzaih;
            }
            zzhn.zzagw = j;
        }
    }

    private final void zza(long j, long j2) {
        this.handler.removeMessages(2);
        long elapsedRealtime = (j + j2) - SystemClock.elapsedRealtime();
        if (elapsedRealtime <= 0) {
            this.handler.sendEmptyMessage(2);
        } else {
            this.handler.sendEmptyMessageDelayed(2, elapsedRealtime);
        }
    }

    private final long zza(int i, long j) throws zzhd {
        zzhk zzhk;
        zzeu();
        this.zzagj = false;
        setState(2);
        zzhk zzhk2 = this.zzags;
        if (zzhk2 == null) {
            zzhk zzhk3 = this.zzagq;
            if (zzhk3 != null) {
                zzhk3.release();
            }
            zzhk = null;
        } else {
            zzhk = null;
            while (zzhk2 != null) {
                if (zzhk2.zzafr != i || !zzhk2.zzafu) {
                    zzhk2.release();
                } else {
                    zzhk = zzhk2;
                }
                zzhk2 = zzhk2.zzafw;
            }
        }
        zzhk zzhk4 = this.zzags;
        if (!(zzhk4 == zzhk && zzhk4 == this.zzagr)) {
            for (zzhx disable : this.zzagh) {
                disable.disable();
            }
            this.zzagh = new zzhx[0];
            this.zzagg = null;
            this.zzagf = null;
            this.zzags = null;
        }
        if (zzhk != null) {
            zzhk.zzafw = null;
            this.zzagq = zzhk;
            this.zzagr = zzhk;
            zzb(zzhk);
            if (this.zzags.zzafv) {
                j = this.zzags.zzafm.zzeg(j);
            }
            zzdq(j);
            zzey();
        } else {
            this.zzagq = null;
            this.zzagr = null;
            this.zzags = null;
            zzdq(j);
        }
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private final void zzdq(long j) throws zzhd {
        long j2;
        zzhk zzhk = this.zzags;
        if (zzhk == null) {
            j2 = 60000000;
        } else {
            j2 = zzhk.zzep();
        }
        long j3 = j + j2;
        this.zzagp = j3;
        this.zzagc.zzel(j3);
        for (zzhx zzdo : this.zzagh) {
            zzdo.zzdo(this.zzagp);
        }
    }

    private final void zzew() {
        zzi(true);
        this.zzafz.onStopped();
        setState(1);
    }

    private final void zzi(boolean z) {
        this.handler.removeMessages(2);
        this.zzagj = false;
        this.zzagc.stop();
        this.zzagg = null;
        this.zzagf = null;
        this.zzagp = 60000000;
        for (zzhx zzhx : this.zzagh) {
            try {
                zza(zzhx);
                zzhx.disable();
            } catch (zzhd | RuntimeException e) {
                Log.e("ExoPlayerImplInternal", "Stop failed.", e);
            }
        }
        this.zzagh = new zzhx[0];
        zzhk zzhk = this.zzags;
        if (zzhk == null) {
            zzhk = this.zzagq;
        }
        zza(zzhk);
        this.zzagq = null;
        this.zzagr = null;
        this.zzags = null;
        zzh(false);
        if (z) {
            zzmz zzmz = this.zzaga;
            if (zzmz != null) {
                zzmz.zzia();
                this.zzaga = null;
            }
            this.zzafd = null;
        }
    }

    private static void zza(zzhx zzhx) throws zzhd {
        if (zzhx.getState() == 2) {
            zzhx.stop();
        }
    }

    private final boolean zzdr(long j) {
        if (j == -9223372036854775807L || this.zzafi.zzagv < j) {
            return true;
        }
        return this.zzags.zzafw != null && this.zzags.zzafw.zzafu;
    }

    private final void zzex() throws IOException {
        zzhk zzhk = this.zzagq;
        if (zzhk != null && !zzhk.zzafu) {
            zzhk zzhk2 = this.zzagr;
            if (zzhk2 == null || zzhk2.zzafw == this.zzagq) {
                zzhx[] zzhxArr = this.zzagh;
                int length = zzhxArr.length;
                int i = 0;
                while (i < length) {
                    if (zzhxArr[i].zzec()) {
                        i++;
                    } else {
                        return;
                    }
                }
                this.zzagq.zzafm.zzhn();
            }
        }
    }

    private final void zza(Object obj, int i) {
        this.zzafi = new zzhn(0, 0);
        zzb(obj, i);
        this.zzafi = new zzhn(0, -9223372036854775807L);
        setState(4);
        zzi(false);
    }

    private final void zzb(Object obj, int i) {
        this.zzaes.obtainMessage(6, new zzhp(this.zzafd, obj, this.zzafi, i)).sendToTarget();
    }

    private final int zza(int i, zzhy zzhy, zzhy zzhy2) {
        int zzfg = zzhy.zzfg();
        int i2 = -1;
        for (int i3 = 0; i3 < zzfg && i2 == -1; i3++) {
            i = zzhy.zza(i, this.zzaew, this.zzaev, this.repeatMode);
            i2 = zzhy2.zzc(zzhy.zza(i, this.zzaew, true).zzafn);
        }
        return i2;
    }

    private final boolean zzu(int i) {
        this.zzafd.zza(i, this.zzaew, false);
        if (this.zzafd.zza(0, this.zzaev, false).zzait || this.zzafd.zza(i, this.zzaew, this.zzaev, this.repeatMode) != -1) {
            return false;
        }
        return true;
    }

    private final Pair<Integer, Long> zza(zzhm zzhm) {
        zzhy zzhy = zzhm.zzafd;
        if (zzhy.isEmpty()) {
            zzhy = this.zzafd;
        }
        try {
            Pair<Integer, Long> zzb = zzb(zzhy, zzhm.zzagt, zzhm.zzagu);
            zzhy zzhy2 = this.zzafd;
            if (zzhy2 == zzhy) {
                return zzb;
            }
            int zzc = zzhy2.zzc(zzhy.zza(((Integer) zzb.first).intValue(), this.zzaew, true).zzafn);
            if (zzc != -1) {
                return Pair.create(Integer.valueOf(zzc), (Long) zzb.second);
            }
            int zza = zza(((Integer) zzb.first).intValue(), zzhy, this.zzafd);
            if (zza == -1) {
                return null;
            }
            this.zzafd.zza(zza, this.zzaew, false);
            return zzb(0, -9223372036854775807L);
        } catch (IndexOutOfBoundsException unused) {
            throw new zzht(this.zzafd, zzhm.zzagt, zzhm.zzagu);
        }
    }

    private final Pair<Integer, Long> zzb(int i, long j) {
        return zzb(this.zzafd, i, -9223372036854775807L);
    }

    private final Pair<Integer, Long> zzb(zzhy zzhy, int i, long j) {
        return zza(zzhy, i, j, 0);
    }

    private final Pair<Integer, Long> zza(zzhy zzhy, int i, long j, long j2) {
        zzoz.zzc(i, 0, zzhy.zzff());
        zzhy.zza(i, this.zzaev, false, j2);
        if (j == -9223372036854775807L) {
            j = this.zzaev.zzaiw;
            if (j == -9223372036854775807L) {
                return null;
            }
        }
        long j3 = this.zzaev.zzaix + j;
        long j4 = zzhy.zza(0, this.zzaew, false).zzaih;
        if (j4 != -9223372036854775807L) {
            int i2 = (j3 > j4 ? 1 : (j3 == j4 ? 0 : -1));
        }
        return Pair.create(0, Long.valueOf(j3));
    }

    private final void zzey() {
        long j;
        if (!this.zzagq.zzafu) {
            j = 0;
        } else {
            j = this.zzagq.zzafm.zzhp();
        }
        if (j == Long.MIN_VALUE) {
            zzh(false);
            return;
        }
        long zzep = this.zzagp - this.zzagq.zzep();
        boolean zzdt = this.zzafz.zzdt(j - zzep);
        zzh(zzdt);
        if (zzdt) {
            this.zzagq.zzafm.zzef(zzep);
        }
    }

    private static void zza(zzhk zzhk) {
        while (zzhk != null) {
            zzhk.release();
            zzhk = zzhk.zzafw;
        }
    }

    private final void zzb(zzhk zzhk) throws zzhd {
        if (this.zzags != zzhk) {
            boolean[] zArr = new boolean[this.zzaep.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                zzhx[] zzhxArr = this.zzaep;
                if (i < zzhxArr.length) {
                    zzhx zzhx = zzhxArr[i];
                    zArr[i] = zzhx.getState() != 0;
                    zzob zzbg = zzhk.zzafx.zzbhx.zzbg(i);
                    if (zzbg != null) {
                        i2++;
                    }
                    if (zArr[i] && (zzbg == null || (zzhx.zzee() && zzhx.zzeb() == this.zzags.zzafo[i]))) {
                        if (zzhx == this.zzagf) {
                            this.zzagc.zza(this.zzagg);
                            this.zzagg = null;
                            this.zzagf = null;
                        }
                        zza(zzhx);
                        zzhx.disable();
                    }
                    i++;
                } else {
                    this.zzags = zzhk;
                    this.zzaes.obtainMessage(3, zzhk.zzafx).sendToTarget();
                    zza(zArr, i2);
                    return;
                }
            }
        }
    }

    private final void zza(boolean[] zArr, int i) throws zzhd {
        this.zzagh = new zzhx[i];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            zzhx[] zzhxArr = this.zzaep;
            if (i2 < zzhxArr.length) {
                zzhx zzhx = zzhxArr[i2];
                zzob zzbg = this.zzags.zzafx.zzbhx.zzbg(i2);
                if (zzbg != null) {
                    int i4 = i3 + 1;
                    this.zzagh[i3] = zzhx;
                    if (zzhx.getState() == 0) {
                        zzhz zzhz = this.zzags.zzafx.zzbhz[i2];
                        boolean z = this.zzaey && this.state == 3;
                        boolean z2 = !zArr[i2] && z;
                        int length = zzbg.length();
                        zzho[] zzhoArr = new zzho[length];
                        for (int i5 = 0; i5 < length; i5++) {
                            zzhoArr[i5] = zzbg.zzbe(i5);
                        }
                        zzhx.zza(zzhz, zzhoArr, this.zzags.zzafo[i2], this.zzagp, z2, this.zzags.zzep());
                        zzpd zzea = zzhx.zzea();
                        if (zzea != null) {
                            if (this.zzagg == null) {
                                this.zzagg = zzea;
                                this.zzagf = zzhx;
                                zzea.zzb(this.zzafh);
                            } else {
                                throw zzhd.zza((RuntimeException) new IllegalStateException("Multiple renderer media clocks enabled."));
                            }
                        }
                        if (z) {
                            zzhx.start();
                        }
                    }
                    i3 = i4;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final /* synthetic */ void zza(zznl zznl) {
        this.handler.obtainMessage(9, (zzmx) zznl).sendToTarget();
    }
}
