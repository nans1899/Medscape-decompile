package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbbz {
    public static final zzdzb zzeep;
    public static final zzdzb zzeeq;
    public static final zzdzb zzeer;
    public static final ScheduledExecutorService zzees = new zzbby(3, zzfh(AppEventsConstants.EVENT_NAME_SCHEDULE));
    public static final zzdzb zzeet = zza(new zzbca());
    public static final zzdzb zzeeu = zza(zzdze.zzayh());

    private static ThreadFactory zzfh(String str) {
        return new zzbcb(str);
    }

    private static zzdzb zza(Executor executor) {
        return new zzbcd(executor, (zzbby) null);
    }

    /* JADX WARNING: type inference failed for: r0v16, types: [java.util.concurrent.ExecutorService] */
    /* JADX WARNING: type inference failed for: r0v19, types: [java.util.concurrent.ExecutorService] */
    /* JADX WARNING: type inference failed for: r0v22, types: [java.util.concurrent.ExecutorService] */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            boolean r0 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()
            java.lang.String r1 = "Default"
            if (r0 == 0) goto L_0x0017
            com.google.android.gms.internal.ads.zzdvd r0 = com.google.android.gms.internal.ads.zzdvg.zzaxc()
            java.util.concurrent.ThreadFactory r1 = zzfh(r1)
            int r2 = com.google.android.gms.internal.ads.zzdvl.zzhov
            java.util.concurrent.ExecutorService r0 = r0.zza(r1, r2)
            goto L_0x002e
        L_0x0017:
            java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor
            r2 = 2
            r3 = 2147483647(0x7fffffff, float:NaN)
            r4 = 10
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.SynchronousQueue r7 = new java.util.concurrent.SynchronousQueue
            r7.<init>()
            java.util.concurrent.ThreadFactory r8 = zzfh(r1)
            r1 = r0
            r1.<init>(r2, r3, r4, r6, r7, r8)
        L_0x002e:
            com.google.android.gms.internal.ads.zzdzb r0 = zza(r0)
            zzeep = r0
            boolean r0 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()
            r1 = 1
            java.lang.String r2 = "Loader"
            if (r0 == 0) goto L_0x004d
            com.google.android.gms.internal.ads.zzdvd r0 = com.google.android.gms.internal.ads.zzdvg.zzaxc()
            r3 = 5
            java.util.concurrent.ThreadFactory r2 = zzfh(r2)
            int r4 = com.google.android.gms.internal.ads.zzdvl.zzhou
            java.util.concurrent.ExecutorService r0 = r0.zza(r3, r2, r4)
            goto L_0x0068
        L_0x004d:
            java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor
            r3 = 5
            r4 = 5
            r5 = 10
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.LinkedBlockingQueue r8 = new java.util.concurrent.LinkedBlockingQueue
            r8.<init>()
            java.util.concurrent.ThreadFactory r9 = zzfh(r2)
            r2 = r0
            r2.<init>(r3, r4, r5, r7, r8, r9)
            r2 = r0
            java.util.concurrent.ThreadPoolExecutor r2 = (java.util.concurrent.ThreadPoolExecutor) r2
            r2.allowCoreThreadTimeOut(r1)
        L_0x0068:
            com.google.android.gms.internal.ads.zzdzb r0 = zza(r0)
            zzeeq = r0
            boolean r0 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()
            java.lang.String r2 = "Activeview"
            if (r0 == 0) goto L_0x0085
            com.google.android.gms.internal.ads.zzdvd r0 = com.google.android.gms.internal.ads.zzdvg.zzaxc()
            java.util.concurrent.ThreadFactory r1 = zzfh(r2)
            int r2 = com.google.android.gms.internal.ads.zzdvl.zzhou
            java.util.concurrent.ExecutorService r0 = r0.zzb(r1, r2)
            goto L_0x00a0
        L_0x0085:
            java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor
            r3 = 1
            r4 = 1
            r5 = 10
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.LinkedBlockingQueue r8 = new java.util.concurrent.LinkedBlockingQueue
            r8.<init>()
            java.util.concurrent.ThreadFactory r9 = zzfh(r2)
            r2 = r0
            r2.<init>(r3, r4, r5, r7, r8, r9)
            r2 = r0
            java.util.concurrent.ThreadPoolExecutor r2 = (java.util.concurrent.ThreadPoolExecutor) r2
            r2.allowCoreThreadTimeOut(r1)
        L_0x00a0:
            com.google.android.gms.internal.ads.zzdzb r0 = zza(r0)
            zzeer = r0
            com.google.android.gms.internal.ads.zzbby r0 = new com.google.android.gms.internal.ads.zzbby
            r1 = 3
            java.lang.String r2 = "Schedule"
            java.util.concurrent.ThreadFactory r2 = zzfh(r2)
            r0.<init>(r1, r2)
            zzees = r0
            com.google.android.gms.internal.ads.zzbca r0 = new com.google.android.gms.internal.ads.zzbca
            r0.<init>()
            com.google.android.gms.internal.ads.zzdzb r0 = zza(r0)
            zzeet = r0
            java.util.concurrent.Executor r0 = com.google.android.gms.internal.ads.zzdze.zzayh()
            com.google.android.gms.internal.ads.zzdzb r0 = zza(r0)
            zzeeu = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbbz.<clinit>():void");
    }
}
