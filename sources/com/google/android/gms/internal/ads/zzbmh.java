package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmh implements zzbuh, zzbuv, zzbuz, zzbvs, zzux {
    private final Executor executor;
    private final View view;
    private final zzacb zzeon;
    private final ScheduledExecutorService zzfmv;
    /* access modifiers changed from: private */
    public final zzdog zzfmw;
    /* access modifiers changed from: private */
    public final zzdnv zzfmx;
    /* access modifiers changed from: private */
    public final zzdsr zzfmy;
    /* access modifiers changed from: private */
    public final zzdor zzfmz;
    private final zzeg zzfna;
    private final zzacg zzfnb;
    private boolean zzfnc;
    private boolean zzfnd;
    /* access modifiers changed from: private */
    public final Context zzvr;

    public zzbmh(Context context, Executor executor2, ScheduledExecutorService scheduledExecutorService, zzdog zzdog, zzdnv zzdnv, zzdsr zzdsr, zzdor zzdor, View view2, zzeg zzeg, zzacb zzacb, zzacg zzacg) {
        this.zzvr = context;
        this.executor = executor2;
        this.zzfmv = scheduledExecutorService;
        this.zzfmw = zzdog;
        this.zzfmx = zzdnv;
        this.zzfmy = zzdsr;
        this.zzfmz = zzdor;
        this.zzfna = zzeg;
        this.view = view2;
        this.zzeon = zzacb;
        this.zzfnb = zzacg;
    }

    public final void onAdClosed() {
    }

    public final void onAdLeftApplication() {
    }

    public final void onAdOpened() {
    }

    public final synchronized void onAdLoaded() {
        if (this.zzfnc) {
            ArrayList arrayList = new ArrayList(this.zzfmx.zzdkk);
            arrayList.addAll(this.zzfmx.zzhdl);
            this.zzfmz.zzi(this.zzfmy.zza(this.zzfmw, this.zzfmx, true, (String) null, (String) null, arrayList));
        } else {
            this.zzfmz.zzi(this.zzfmy.zza(this.zzfmw, this.zzfmx, this.zzfmx.zzhdn));
            this.zzfmz.zzi(this.zzfmy.zza(this.zzfmw, this.zzfmx, this.zzfmx.zzhdl));
        }
        this.zzfnc = true;
    }

    public final void onAdClicked() {
        if (!zzact.zzdbb.get().booleanValue()) {
            zzdor zzdor = this.zzfmz;
            zzdsr zzdsr = this.zzfmy;
            zzdog zzdog = this.zzfmw;
            zzdnv zzdnv = this.zzfmx;
            List<String> zza = zzdsr.zza(zzdog, zzdnv, zzdnv.zzdkj);
            zzp.zzkr();
            zzdor.zza(zza, zzayu.zzbf(this.zzvr) ? zzcsq.zzgne : zzcsq.zzgnd);
            return;
        }
        zzdyq.zza(zzdyl.zzg(this.zzfnb.zza(this.zzvr, (String) null, this.zzeon.zzrv(), this.zzeon.zzrw())).zza(((Long) zzwm.zzpx().zzd(zzabb.zzcor)).longValue(), TimeUnit.MILLISECONDS, this.zzfmv), new zzbmk(this), this.executor);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0082, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onAdImpression() {
        /*
            r9 = this;
            monitor-enter(r9)
            boolean r0 = r9.zzfnd     // Catch:{ all -> 0x0083 }
            if (r0 != 0) goto L_0x0081
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcro     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0083 }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x0083 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0083 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0083 }
            r1 = 0
            if (r0 == 0) goto L_0x0028
            com.google.android.gms.internal.ads.zzeg r0 = r9.zzfna     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzdw r0 = r0.zzca()     // Catch:{ all -> 0x0083 }
            android.content.Context r2 = r9.zzvr     // Catch:{ all -> 0x0083 }
            android.view.View r3 = r9.view     // Catch:{ all -> 0x0083 }
            java.lang.String r0 = r0.zza((android.content.Context) r2, (android.view.View) r3, (android.app.Activity) r1)     // Catch:{ all -> 0x0083 }
            r5 = r0
            goto L_0x0029
        L_0x0028:
            r5 = r1
        L_0x0029:
            com.google.android.gms.internal.ads.zzaci<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzact.zzdbc     // Catch:{ all -> 0x0083 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0083 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0083 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0083 }
            r8 = 1
            if (r0 != 0) goto L_0x0051
            com.google.android.gms.internal.ads.zzdor r0 = r9.zzfmz     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzdsr r1 = r9.zzfmy     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzdog r2 = r9.zzfmw     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzdnv r3 = r9.zzfmx     // Catch:{ all -> 0x0083 }
            r4 = 0
            r6 = 0
            com.google.android.gms.internal.ads.zzdnv r7 = r9.zzfmx     // Catch:{ all -> 0x0083 }
            java.util.List<java.lang.String> r7 = r7.zzdkk     // Catch:{ all -> 0x0083 }
            java.util.List r1 = r1.zza(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0083 }
            r0.zzi(r1)     // Catch:{ all -> 0x0083 }
            r9.zzfnd = r8     // Catch:{ all -> 0x0083 }
            monitor-exit(r9)
            return
        L_0x0051:
            com.google.android.gms.internal.ads.zzacg r0 = r9.zzfnb     // Catch:{ all -> 0x0083 }
            android.content.Context r2 = r9.zzvr     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzdzc r0 = r0.zzc(r2, r1)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzdyl r0 = com.google.android.gms.internal.ads.zzdyl.zzg(r0)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzaaq<java.lang.Long> r1 = com.google.android.gms.internal.ads.zzabb.zzcor     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzaax r2 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0083 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ all -> 0x0083 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ all -> 0x0083 }
            long r1 = r1.longValue()     // Catch:{ all -> 0x0083 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0083 }
            java.util.concurrent.ScheduledExecutorService r4 = r9.zzfmv     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzdyl r0 = r0.zza((long) r1, (java.util.concurrent.TimeUnit) r3, (java.util.concurrent.ScheduledExecutorService) r4)     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzbmj r1 = new com.google.android.gms.internal.ads.zzbmj     // Catch:{ all -> 0x0083 }
            r1.<init>(r9, r5)     // Catch:{ all -> 0x0083 }
            java.util.concurrent.Executor r2 = r9.executor     // Catch:{ all -> 0x0083 }
            com.google.android.gms.internal.ads.zzdyq.zza(r0, r1, r2)     // Catch:{ all -> 0x0083 }
            r9.zzfnd = r8     // Catch:{ all -> 0x0083 }
        L_0x0081:
            monitor-exit(r9)
            return
        L_0x0083:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbmh.onAdImpression():void");
    }

    public final void zzb(zzatw zzatw, String str, String str2) {
        zzdor zzdor = this.zzfmz;
        zzdsr zzdsr = this.zzfmy;
        zzdnv zzdnv = this.zzfmx;
        zzdor.zzi(zzdsr.zza(zzdnv, zzdnv.zzdte, zzatw));
    }

    public final void onRewardedVideoStarted() {
        zzdor zzdor = this.zzfmz;
        zzdsr zzdsr = this.zzfmy;
        zzdog zzdog = this.zzfmw;
        zzdnv zzdnv = this.zzfmx;
        zzdor.zzi(zzdsr.zza(zzdog, zzdnv, zzdnv.zzdtd));
    }

    public final void onRewardedVideoCompleted() {
        zzdor zzdor = this.zzfmz;
        zzdsr zzdsr = this.zzfmy;
        zzdog zzdog = this.zzfmw;
        zzdnv zzdnv = this.zzfmx;
        zzdor.zzi(zzdsr.zza(zzdog, zzdnv, zzdnv.zzhdm));
    }

    public final void zzj(zzva zzva) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqg)).booleanValue()) {
            zzdor zzdor = this.zzfmz;
            zzdsr zzdsr = this.zzfmy;
            zzdog zzdog = this.zzfmw;
            zzdnv zzdnv = this.zzfmx;
            zzdor.zzi(zzdsr.zza(zzdog, zzdnv, zzdnv.zzhdo));
        }
    }
}
