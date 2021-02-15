package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczu extends zzxf {
    private final Executor zzfoc;
    /* access modifiers changed from: private */
    public final zzbix zzgpt;
    private final Context zzgsl;
    private final zzdom zzgsm;
    /* access modifiers changed from: private */
    public final zzczs zzgsq = new zzczs();
    /* access modifiers changed from: private */
    public final zzczr zzgss = new zzczr();
    /* access modifiers changed from: private */
    public final zzczn zzgst = new zzczn();
    private zzaby zzgsw;
    /* access modifiers changed from: private */
    public zzdzc<zzcbe> zzgsx;
    /* access modifiers changed from: private */
    public final zzdmc zzgtk = new zzdmc(new zzdpw());
    /* access modifiers changed from: private */
    public zzcbe zzgtl;
    private boolean zzgtm;

    public zzczu(zzbix zzbix, Context context, zzvn zzvn, String str) {
        zzdom zzdom = new zzdom();
        this.zzgsm = zzdom;
        this.zzgtm = false;
        this.zzgpt = zzbix;
        zzdom.zze(zzvn).zzgt(str);
        this.zzfoc = zzbix.zzadi();
        this.zzgsl = context;
    }

    public final zzyo getVideoController() {
        return null;
    }

    public final void setUserId(String str) {
    }

    public final void stopLoading() {
    }

    public final void zza(zzari zzari) {
    }

    public final void zza(zzaro zzaro, String str) {
    }

    public final void zza(zzsh zzsh) {
    }

    public final void zza(zzvn zzvn) {
    }

    public final void zza(zzvs zzvs) {
    }

    public final void zza(zzwo zzwo) {
    }

    public final void zza(zzyu zzyu) {
    }

    public final void zzbp(String str) {
    }

    public final IObjectWrapper zzke() {
        return null;
    }

    public final void zzkf() {
    }

    public final zzvn zzkg() {
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0159, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zza(com.google.android.gms.internal.ads.zzvg r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "loadAd must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)     // Catch:{ all -> 0x015a }
            com.google.android.gms.ads.internal.zzp.zzkr()     // Catch:{ all -> 0x015a }
            android.content.Context r0 = r4.zzgsl     // Catch:{ all -> 0x015a }
            boolean r0 = com.google.android.gms.internal.ads.zzayu.zzbe(r0)     // Catch:{ all -> 0x015a }
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L_0x002d
            com.google.android.gms.internal.ads.zzuy r0 = r5.zzcho     // Catch:{ all -> 0x015a }
            if (r0 != 0) goto L_0x002d
            java.lang.String r5 = "Failed to load the ad because app ID is missing."
            com.google.android.gms.internal.ads.zzayp.zzfc(r5)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzczs r5 = r4.zzgsq     // Catch:{ all -> 0x015a }
            if (r5 == 0) goto L_0x002b
            com.google.android.gms.internal.ads.zzczs r5 = r4.zzgsq     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdpg r0 = com.google.android.gms.internal.ads.zzdpg.APP_ID_MISSING     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzva r0 = com.google.android.gms.internal.ads.zzdpe.zza(r0, r2, r2)     // Catch:{ all -> 0x015a }
            r5.zzk(r0)     // Catch:{ all -> 0x015a }
        L_0x002b:
            monitor-exit(r4)
            return r1
        L_0x002d:
            com.google.android.gms.internal.ads.zzdzc<com.google.android.gms.internal.ads.zzcbe> r0 = r4.zzgsx     // Catch:{ all -> 0x015a }
            if (r0 != 0) goto L_0x0158
            boolean r0 = r4.zzaqu()     // Catch:{ all -> 0x015a }
            if (r0 == 0) goto L_0x0039
            goto L_0x0158
        L_0x0039:
            android.content.Context r0 = r4.zzgsl     // Catch:{ all -> 0x015a }
            boolean r1 = r5.zzche     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdox.zze(r0, r1)     // Catch:{ all -> 0x015a }
            r4.zzgtl = r2     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdom r0 = r4.zzgsm     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdom r5 = r0.zzh((com.google.android.gms.internal.ads.zzvg) r5)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdok r5 = r5.zzatn()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcyh     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x015a }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x015a }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x015a }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x015a }
            if (r0 == 0) goto L_0x0099
            com.google.android.gms.internal.ads.zzbix r0 = r4.zzgpt     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzccd r0 = r0.zzadt()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbtp$zza r1 = new com.google.android.gms.internal.ads.zzbtp$zza     // Catch:{ all -> 0x015a }
            r1.<init>()     // Catch:{ all -> 0x015a }
            android.content.Context r2 = r4.zzgsl     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbtp$zza r1 = r1.zzce(r2)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbtp$zza r5 = r1.zza((com.google.android.gms.internal.ads.zzdok) r5)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbtp r5 = r5.zzajv()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzccd r5 = r0.zze((com.google.android.gms.internal.ads.zzbtp) r5)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r0 = new com.google.android.gms.internal.ads.zzbys$zza     // Catch:{ all -> 0x015a }
            r0.<init>()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys r0 = r0.zzakr()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzccd r5 = r5.zze((com.google.android.gms.internal.ads.zzbys) r0)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzcyn r0 = new com.google.android.gms.internal.ads.zzcyn     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzaby r1 = r4.zzgsw     // Catch:{ all -> 0x015a }
            r0.<init>(r1)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzccd r5 = r5.zzb(r0)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzcce r5 = r5.zzagh()     // Catch:{ all -> 0x015a }
            goto L_0x0141
        L_0x0099:
            com.google.android.gms.internal.ads.zzbys$zza r0 = new com.google.android.gms.internal.ads.zzbys$zza     // Catch:{ all -> 0x015a }
            r0.<init>()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdmc r1 = r4.zzgtk     // Catch:{ all -> 0x015a }
            if (r1 == 0) goto L_0x00c5
            com.google.android.gms.internal.ads.zzdmc r1 = r4.zzgtk     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r2 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r2 = r2.zzadi()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r1 = r0.zza((com.google.android.gms.internal.ads.zzbuh) r1, (java.util.concurrent.Executor) r2)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdmc r2 = r4.zzgtk     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r3 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r3 = r3.zzadi()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r1 = r1.zza((com.google.android.gms.internal.ads.zzbvs) r2, (java.util.concurrent.Executor) r3)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdmc r2 = r4.zzgtk     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r3 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r3 = r3.zzadi()     // Catch:{ all -> 0x015a }
            r1.zza((com.google.android.gms.internal.ads.zzbui) r2, (java.util.concurrent.Executor) r3)     // Catch:{ all -> 0x015a }
        L_0x00c5:
            com.google.android.gms.internal.ads.zzbix r1 = r4.zzgpt     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzccd r1 = r1.zzadt()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbtp$zza r2 = new com.google.android.gms.internal.ads.zzbtp$zza     // Catch:{ all -> 0x015a }
            r2.<init>()     // Catch:{ all -> 0x015a }
            android.content.Context r3 = r4.zzgsl     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbtp$zza r2 = r2.zzce(r3)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbtp$zza r5 = r2.zza((com.google.android.gms.internal.ads.zzdok) r5)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbtp r5 = r5.zzajv()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzccd r5 = r1.zze((com.google.android.gms.internal.ads.zzbtp) r5)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzczs r1 = r4.zzgsq     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r2 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r2 = r2.zzadi()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r0 = r0.zza((com.google.android.gms.internal.ads.zzbuh) r1, (java.util.concurrent.Executor) r2)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzczs r1 = r4.zzgsq     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r2 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r2 = r2.zzadi()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r0 = r0.zza((com.google.android.gms.internal.ads.zzbvs) r1, (java.util.concurrent.Executor) r2)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzczs r1 = r4.zzgsq     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r2 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r2 = r2.zzadi()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r0 = r0.zza((com.google.android.gms.internal.ads.zzbui) r1, (java.util.concurrent.Executor) r2)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzczs r1 = r4.zzgsq     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r2 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r2 = r2.zzadi()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r0 = r0.zza((com.google.android.gms.internal.ads.zzux) r1, (java.util.concurrent.Executor) r2)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzczr r1 = r4.zzgss     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r2 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r2 = r2.zzadi()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r0 = r0.zza((com.google.android.gms.ads.doubleclick.AppEventListener) r1, (java.util.concurrent.Executor) r2)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzczn r1 = r4.zzgst     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbix r2 = r4.zzgpt     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r2 = r2.zzadi()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys$zza r0 = r0.zza((com.google.android.gms.internal.ads.zzbwl) r1, (java.util.concurrent.Executor) r2)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzbys r0 = r0.zzakr()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzccd r5 = r5.zze((com.google.android.gms.internal.ads.zzbys) r0)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzcyn r0 = new com.google.android.gms.internal.ads.zzcyn     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzaby r1 = r4.zzgsw     // Catch:{ all -> 0x015a }
            r0.<init>(r1)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzccd r5 = r5.zzb(r0)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzcce r5 = r5.zzagh()     // Catch:{ all -> 0x015a }
        L_0x0141:
            com.google.android.gms.internal.ads.zzbrl r0 = r5.zzaev()     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdzc r0 = r0.zzajh()     // Catch:{ all -> 0x015a }
            r4.zzgsx = r0     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzczt r1 = new com.google.android.gms.internal.ads.zzczt     // Catch:{ all -> 0x015a }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x015a }
            java.util.concurrent.Executor r5 = r4.zzfoc     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.ads.zzdyq.zza(r0, r1, r5)     // Catch:{ all -> 0x015a }
            r5 = 1
            monitor-exit(r4)
            return r5
        L_0x0158:
            monitor-exit(r4)
            return r1
        L_0x015a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzczu.zza(com.google.android.gms.internal.ads.zzvg):boolean");
    }

    public final synchronized void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        if (this.zzgtl != null) {
            this.zzgtl.zzaiw().zzcc((Context) null);
        }
    }

    public final synchronized void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (this.zzgtl != null) {
            this.zzgtl.zzaiw().zzca((Context) null);
        }
    }

    public final synchronized void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        if (this.zzgtl != null) {
            this.zzgtl.zzaiw().zzcb((Context) null);
        }
    }

    public final void zza(zzwt zzwt) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzgsq.zzc(zzwt);
    }

    public final void zza(zzxk zzxk) {
        Preconditions.checkMainThread("setAppEventListener must be called on the main UI thread.");
        this.zzgss.zzb(zzxk);
    }

    public final synchronized void showInterstitial() {
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        if (this.zzgtl != null) {
            this.zzgtl.zzbi(this.zzgtm);
        }
    }

    public final synchronized String getMediationAdapterClassName() {
        if (this.zzgtl == null || this.zzgtl.zzaix() == null) {
            return null;
        }
        return this.zzgtl.zzaix().getMediationAdapterClassName();
    }

    public final synchronized String zzkh() {
        if (this.zzgtl == null || this.zzgtl.zzaix() == null) {
            return null;
        }
        return this.zzgtl.zzaix().getMediationAdapterClassName();
    }

    public final synchronized zzyn zzki() {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxl)).booleanValue()) {
            return null;
        }
        if (this.zzgtl == null) {
            return null;
        }
        return this.zzgtl.zzaix();
    }

    public final synchronized void zza(zzxq zzxq) {
        Preconditions.checkMainThread("setCorrelationIdProvider must be called on the main UI thread");
        this.zzgsm.zzc(zzxq);
    }

    public final synchronized void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzgsm.zzbp(z);
    }

    public final synchronized boolean isLoading() {
        return this.zzgsx != null && !this.zzgsx.isDone();
    }

    public final synchronized boolean isReady() {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return zzaqu();
    }

    public final synchronized String getAdUnitId() {
        return this.zzgsm.zzatl();
    }

    public final zzxk zzkj() {
        return this.zzgss.zzaqs();
    }

    public final zzwt zzkk() {
        return this.zzgsq.zzaqt();
    }

    public final synchronized void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzgtm = z;
    }

    public final synchronized void zza(zzaak zzaak) {
        this.zzgsm.zzc(zzaak);
    }

    public final synchronized void zza(zzaby zzaby) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzgsw = zzaby;
    }

    private final synchronized boolean zzaqu() {
        return this.zzgtl != null && !this.zzgtl.isClosed();
    }

    public final void zza(zzyi zzyi) {
        Preconditions.checkMainThread("setPaidEventListener must be called on the main UI thread.");
        this.zzgst.zzb(zzyi);
    }

    public final void zza(zzxj zzxj) {
        Preconditions.checkMainThread("setAdMetadataListener must be called on the main UI thread.");
    }

    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("getAdMetadata must be called on the main UI thread.");
        return new Bundle();
    }

    public final void zza(zzaug zzaug) {
        this.zzgtk.zzb(zzaug);
    }
}
