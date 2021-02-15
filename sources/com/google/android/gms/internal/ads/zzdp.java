package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.medscape.android.Constants;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdp implements zzdw {
    private static zzdp zzvq;
    private final Context zzvr;
    private final zzdur zzvs;
    private final zzdva zzvt;
    private final zzdvb zzvu;
    private final zzew zzvv;
    /* access modifiers changed from: private */
    public final zzdtc zzvw;
    private final Executor zzvx;
    private final zzgo zzvy;
    private final zzdux zzvz;
    private volatile long zzwa = 0;
    /* access modifiers changed from: private */
    public final Object zzwb = new Object();
    /* access modifiers changed from: private */
    public volatile boolean zzwc;

    public static synchronized zzdp zza(String str, Context context, boolean z) {
        zzdp zzdp;
        synchronized (zzdp.class) {
            if (zzvq == null) {
                zzdtd zzavw = zzdtd.zzavv().zzhb(str).zzbq(z).zzavw();
                ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
                zzdp zza = zza(context, zzdtc.zza(context, newCachedThreadPool), zzavw, (Executor) newCachedThreadPool);
                zzvq = zza;
                zza.zzbq();
                zzvq.zzbt();
            }
            zzdp = zzvq;
        }
        return zzdp;
    }

    public final void zza(int i, int i2, int i3) {
    }

    static zzdp zza(Context context, zzdtc zzdtc, zzdtd zzdtd) {
        return zza(context, zzdtc, zzdtd, (Executor) Executors.newCachedThreadPool());
    }

    private static zzdp zza(Context context, zzdtc zzdtc, zzdtd zzdtd, Executor executor) {
        zzdtp zza = zzdtp.zza(context, executor, zzdtc, zzdtd);
        zzev zzev = new zzev(context);
        zzew zzew = new zzew(zzdtd, zza, new zzfi(context, zzev), zzev);
        zzgo zzawl = new zzduf(context, zzdtc).zzawl();
        zzdta zzdta = new zzdta();
        return new zzdp(context, zzdtc, new zzdur(context, zzawl), new zzdva(context, zzawl), new zzdvb(context, zzew, zzdtc, zzdta), zzew, executor, zzdta, zzawl);
    }

    private zzdp(Context context, zzdtc zzdtc, zzdur zzdur, zzdva zzdva, zzdvb zzdvb, zzew zzew, Executor executor, zzdta zzdta, zzgo zzgo) {
        this.zzvr = context;
        this.zzvw = zzdtc;
        this.zzvs = zzdur;
        this.zzvt = zzdva;
        this.zzvu = zzdvb;
        this.zzvv = zzew;
        this.zzvx = executor;
        this.zzvy = zzgo;
        this.zzvz = new zzds(this, zzdta);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzbq() {
        long currentTimeMillis = System.currentTimeMillis();
        zzdus zzp = zzp(zzduz.zzhof);
        if (zzp == null || zzp.zza()) {
            this.zzvw.zzg((int) Constants.REQUEST_CODE_UPLOAD_POST, System.currentTimeMillis() - currentTimeMillis);
        } else {
            this.zzvu.zzb(zzp);
        }
    }

    public final void zzbr() {
        this.zzvx.execute(new zzdr(this));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa A[Catch:{ zzekj -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b7 A[Catch:{ zzekj -> 0x0118 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzbs() {
        /*
            r11 = this;
            long r0 = java.lang.System.currentTimeMillis()
            int r2 = com.google.android.gms.internal.ads.zzduz.zzhof
            com.google.android.gms.internal.ads.zzdus r2 = r11.zzp(r2)
            r3 = 0
            if (r2 == 0) goto L_0x0020
            com.google.android.gms.internal.ads.zzgr r3 = r2.zzawv()
            java.lang.String r3 = r3.zzdg()
            com.google.android.gms.internal.ads.zzgr r2 = r2.zzawv()
            java.lang.String r2 = r2.zzdh()
            r8 = r2
            r7 = r3
            goto L_0x0022
        L_0x0020:
            r7 = r3
            r8 = r7
        L_0x0022:
            android.content.Context r4 = r11.zzvr     // Catch:{ zzekj -> 0x0118 }
            r5 = 1
            com.google.android.gms.internal.ads.zzgo r6 = r11.zzvy     // Catch:{ zzekj -> 0x0118 }
            java.lang.String r9 = "1"
            com.google.android.gms.internal.ads.zzdtc r10 = r11.zzvw     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzduv r2 = com.google.android.gms.internal.ads.zzdtl.zza(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ zzekj -> 0x0118 }
            byte[] r3 = r2.zzhoe     // Catch:{ zzekj -> 0x0118 }
            if (r3 == 0) goto L_0x010b
            byte[] r3 = r2.zzhoe     // Catch:{ zzekj -> 0x0118 }
            int r3 = r3.length     // Catch:{ zzekj -> 0x0118 }
            if (r3 != 0) goto L_0x003a
            goto L_0x010b
        L_0x003a:
            byte[] r3 = r2.zzhoe     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzeip r3 = com.google.android.gms.internal.ads.zzeip.zzu(r3)     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzejm r4 = com.google.android.gms.internal.ads.zzejm.zzbfu()     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzgq r3 = com.google.android.gms.internal.ads.zzgq.zza(r3, r4)     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzgr r4 = r3.zzdc()     // Catch:{ zzekj -> 0x0118 }
            java.lang.String r4 = r4.zzdg()     // Catch:{ zzekj -> 0x0118 }
            boolean r4 = r4.isEmpty()     // Catch:{ zzekj -> 0x0118 }
            r5 = 1
            r6 = 0
            if (r4 != 0) goto L_0x00a7
            com.google.android.gms.internal.ads.zzgr r4 = r3.zzdc()     // Catch:{ zzekj -> 0x0118 }
            java.lang.String r4 = r4.zzdh()     // Catch:{ zzekj -> 0x0118 }
            boolean r4 = r4.isEmpty()     // Catch:{ zzekj -> 0x0118 }
            if (r4 != 0) goto L_0x00a7
            com.google.android.gms.internal.ads.zzeip r4 = r3.zzde()     // Catch:{ zzekj -> 0x0118 }
            byte[] r4 = r4.toByteArray()     // Catch:{ zzekj -> 0x0118 }
            int r4 = r4.length     // Catch:{ zzekj -> 0x0118 }
            if (r4 != 0) goto L_0x0072
            goto L_0x00a7
        L_0x0072:
            int r4 = com.google.android.gms.internal.ads.zzduz.zzhof     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzdus r4 = r11.zzp(r4)     // Catch:{ zzekj -> 0x0118 }
            if (r4 != 0) goto L_0x007b
            goto L_0x00a8
        L_0x007b:
            com.google.android.gms.internal.ads.zzgr r4 = r4.zzawv()     // Catch:{ zzekj -> 0x0118 }
            if (r4 != 0) goto L_0x0082
            goto L_0x00a8
        L_0x0082:
            com.google.android.gms.internal.ads.zzgr r7 = r3.zzdc()     // Catch:{ zzekj -> 0x0118 }
            java.lang.String r7 = r7.zzdg()     // Catch:{ zzekj -> 0x0118 }
            java.lang.String r8 = r4.zzdg()     // Catch:{ zzekj -> 0x0118 }
            boolean r7 = r7.equals(r8)     // Catch:{ zzekj -> 0x0118 }
            if (r7 == 0) goto L_0x00a8
            com.google.android.gms.internal.ads.zzgr r7 = r3.zzdc()     // Catch:{ zzekj -> 0x0118 }
            java.lang.String r7 = r7.zzdh()     // Catch:{ zzekj -> 0x0118 }
            java.lang.String r4 = r4.zzdh()     // Catch:{ zzekj -> 0x0118 }
            boolean r4 = r7.equals(r4)     // Catch:{ zzekj -> 0x0118 }
            if (r4 != 0) goto L_0x00a7
            goto L_0x00a8
        L_0x00a7:
            r5 = 0
        L_0x00a8:
            if (r5 != 0) goto L_0x00b7
            com.google.android.gms.internal.ads.zzdtc r2 = r11.zzvw     // Catch:{ zzekj -> 0x0118 }
            r3 = 5010(0x1392, float:7.02E-42)
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ zzekj -> 0x0118 }
            long r4 = r4 - r0
            r2.zzg((int) r3, (long) r4)     // Catch:{ zzekj -> 0x0118 }
            return
        L_0x00b7:
            com.google.android.gms.internal.ads.zzdux r4 = r11.zzvz     // Catch:{ zzekj -> 0x0118 }
            int r2 = r2.status     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r5 = com.google.android.gms.internal.ads.zzabb.zzcqp     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzaax r7 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ zzekj -> 0x0118 }
            java.lang.Object r5 = r7.zzd(r5)     // Catch:{ zzekj -> 0x0118 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ zzekj -> 0x0118 }
            boolean r5 = r5.booleanValue()     // Catch:{ zzekj -> 0x0118 }
            if (r5 == 0) goto L_0x00e1
            r5 = 3
            if (r2 != r5) goto L_0x00d7
            com.google.android.gms.internal.ads.zzdva r2 = r11.zzvt     // Catch:{ zzekj -> 0x0118 }
            boolean r6 = r2.zza(r3)     // Catch:{ zzekj -> 0x0118 }
            goto L_0x00e7
        L_0x00d7:
            r5 = 4
            if (r2 != r5) goto L_0x00e7
            com.google.android.gms.internal.ads.zzdva r2 = r11.zzvt     // Catch:{ zzekj -> 0x0118 }
            boolean r6 = r2.zza(r3, r4)     // Catch:{ zzekj -> 0x0118 }
            goto L_0x00e7
        L_0x00e1:
            com.google.android.gms.internal.ads.zzdur r2 = r11.zzvs     // Catch:{ zzekj -> 0x0118 }
            boolean r6 = r2.zza(r3, r4)     // Catch:{ zzekj -> 0x0118 }
        L_0x00e7:
            if (r6 != 0) goto L_0x00f6
            com.google.android.gms.internal.ads.zzdtc r2 = r11.zzvw     // Catch:{ zzekj -> 0x0118 }
            r3 = 4009(0xfa9, float:5.618E-42)
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ zzekj -> 0x0118 }
            long r4 = r4 - r0
            r2.zzg((int) r3, (long) r4)     // Catch:{ zzekj -> 0x0118 }
            return
        L_0x00f6:
            com.google.android.gms.internal.ads.zzdvb r2 = r11.zzvu     // Catch:{ zzekj -> 0x0118 }
            int r3 = com.google.android.gms.internal.ads.zzduz.zzhof     // Catch:{ zzekj -> 0x0118 }
            com.google.android.gms.internal.ads.zzdus r3 = r11.zzp(r3)     // Catch:{ zzekj -> 0x0118 }
            r2.zzb(r3)     // Catch:{ zzekj -> 0x0118 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ zzekj -> 0x0118 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            r11.zzwa = r2     // Catch:{ zzekj -> 0x0118 }
            return
        L_0x010b:
            com.google.android.gms.internal.ads.zzdtc r2 = r11.zzvw     // Catch:{ zzekj -> 0x0118 }
            r3 = 5009(0x1391, float:7.019E-42)
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ zzekj -> 0x0118 }
            long r4 = r4 - r0
            r2.zzg((int) r3, (long) r4)     // Catch:{ zzekj -> 0x0118 }
            return
        L_0x0118:
            r2 = move-exception
            com.google.android.gms.internal.ads.zzdtc r3 = r11.zzvw
            r4 = 4002(0xfa2, float:5.608E-42)
            long r5 = java.lang.System.currentTimeMillis()
            long r5 = r5 - r0
            r3.zza((int) r4, (long) r5, (java.lang.Exception) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdp.zzbs():void");
    }

    public final void zza(MotionEvent motionEvent) {
        zzdtf zzawz = this.zzvu.zzawz();
        if (zzawz != null) {
            try {
                zzawz.zza((String) null, motionEvent);
            } catch (zzdvc e) {
                this.zzvw.zza(e.zzaxb(), -1, (Exception) e);
            }
        }
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        zzbt();
        zzdtf zzawz = this.zzvu.zzawz();
        if (zzawz == null) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String zza = zzawz.zza(context, (String) null, str, view, activity);
        this.zzvw.zza(5000, System.currentTimeMillis() - currentTimeMillis, zza, (Map<String, String>) null);
        return zza;
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, (Activity) null);
    }

    public final void zzb(View view) {
        this.zzvv.zzc(view);
    }

    public final String zza(Context context, View view, Activity activity) {
        zzbt();
        zzdtf zzawz = this.zzvu.zzawz();
        if (zzawz == null) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String zzb = zzawz.zzb(context, (String) null, view, activity);
        this.zzvw.zza(5002, System.currentTimeMillis() - currentTimeMillis, zzb, (Map<String, String>) null);
        return zzb;
    }

    public final String zzb(Context context) {
        zzbt();
        zzdtf zzawz = this.zzvu.zzawz();
        if (zzawz == null) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String zzv = zzawz.zzv(context, (String) null);
        this.zzvw.zza(com.webmd.wbmdcmepulse.models.utils.Constants.RESULT_CODE_EXIT_FULL_SCREEN, System.currentTimeMillis() - currentTimeMillis, zzv, (Map<String, String>) null);
        return zzv;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzbt() {
        /*
            r6 = this;
            boolean r0 = r6.zzwc
            if (r0 != 0) goto L_0x0033
            java.lang.Object r0 = r6.zzwb
            monitor-enter(r0)
            boolean r1 = r6.zzwc     // Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x002e
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0030 }
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r3
            long r3 = r6.zzwa     // Catch:{ all -> 0x0030 }
            long r1 = r1 - r3
            r3 = 3600(0xe10, double:1.7786E-320)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x001d:
            com.google.android.gms.internal.ads.zzdvb r1 = r6.zzvu     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.zzdus r1 = r1.zzaxa()     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002b
            boolean r1 = r1.zzfg(r3)     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002e
        L_0x002b:
            r6.zzbr()     // Catch:{ all -> 0x0030 }
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0030:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r1
        L_0x0033:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdp.zzbt():void");
    }

    private final zzdus zzp(int i) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqp)).booleanValue()) {
            return this.zzvt.zzp(i);
        }
        return this.zzvs.zzp(i);
    }
}
