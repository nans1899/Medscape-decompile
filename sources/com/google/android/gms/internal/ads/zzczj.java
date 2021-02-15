package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbtp;
import com.google.android.gms.internal.ads.zzbys;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczj extends zzxf implements zzbwo {
    /* access modifiers changed from: private */
    public final ViewGroup zzfpw;
    /* access modifiers changed from: private */
    public final zzbix zzgpt;
    /* access modifiers changed from: private */
    public zzboq zzgsg;
    private final Context zzgsl;
    private final zzdom zzgsm = new zzdom();
    /* access modifiers changed from: private */
    public final zzczs zzgsq = new zzczs();
    /* access modifiers changed from: private */
    public final zzczp zzgsr = new zzczp();
    /* access modifiers changed from: private */
    public final zzczr zzgss = new zzczr();
    /* access modifiers changed from: private */
    public final zzczn zzgst = new zzczn();
    /* access modifiers changed from: private */
    public final zzbwk zzgsu;
    private zzvn zzgsv;
    private zzaby zzgsw;
    /* access modifiers changed from: private */
    public zzdzc<zzboq> zzgsx;

    public zzczj(zzbix zzbix, Context context, zzvn zzvn, String str) {
        this.zzfpw = new FrameLayout(context);
        this.zzgpt = zzbix;
        this.zzgsl = context;
        this.zzgsm.zze(zzvn).zzgt(str);
        zzbwk zzadm = zzbix.zzadm();
        this.zzgsu = zzadm;
        zzadm.zza(this, this.zzgpt.zzadi());
        this.zzgsv = zzvn;
    }

    public final boolean isReady() {
        return false;
    }

    public final void setImmersiveMode(boolean z) {
    }

    public final void setUserId(String str) {
    }

    public final void showInterstitial() {
    }

    public final void stopLoading() {
    }

    public final void zza(zzari zzari) {
    }

    public final void zza(zzaro zzaro, String str) {
    }

    public final void zza(zzaug zzaug) {
    }

    public final void zza(zzsh zzsh) {
    }

    public final void zza(zzvs zzvs) {
    }

    public final void zza(zzyu zzyu) {
    }

    public final void zzbp(String str) {
    }

    public final IObjectWrapper zzke() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzfpw);
    }

    public final synchronized boolean zza(zzvg zzvg) {
        zzd(this.zzgsv);
        return zzg(zzvg);
    }

    private final synchronized void zzd(zzvn zzvn) {
        this.zzgsm.zze(zzvn);
        this.zzgsm.zzbo(this.zzgsv.zzcia);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzg(com.google.android.gms.internal.ads.zzvg r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "loadAd must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)     // Catch:{ all -> 0x008c }
            com.google.android.gms.ads.internal.zzp.zzkr()     // Catch:{ all -> 0x008c }
            android.content.Context r0 = r4.zzgsl     // Catch:{ all -> 0x008c }
            boolean r0 = com.google.android.gms.internal.ads.zzayu.zzbe(r0)     // Catch:{ all -> 0x008c }
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L_0x002d
            com.google.android.gms.internal.ads.zzuy r0 = r5.zzcho     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x002d
            java.lang.String r5 = "Failed to load the ad because app ID is missing."
            com.google.android.gms.internal.ads.zzayp.zzfc(r5)     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzczs r5 = r4.zzgsq     // Catch:{ all -> 0x008c }
            if (r5 == 0) goto L_0x002b
            com.google.android.gms.internal.ads.zzczs r5 = r4.zzgsq     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzdpg r0 = com.google.android.gms.internal.ads.zzdpg.APP_ID_MISSING     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzva r0 = com.google.android.gms.internal.ads.zzdpe.zza(r0, r2, r2)     // Catch:{ all -> 0x008c }
            r5.zzk(r0)     // Catch:{ all -> 0x008c }
        L_0x002b:
            monitor-exit(r4)
            return r1
        L_0x002d:
            com.google.android.gms.internal.ads.zzdzc<com.google.android.gms.internal.ads.zzboq> r0 = r4.zzgsx     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x0033
            monitor-exit(r4)
            return r1
        L_0x0033:
            android.content.Context r0 = r4.zzgsl     // Catch:{ all -> 0x008c }
            boolean r3 = r5.zzche     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzdox.zze(r0, r3)     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzdom r0 = r4.zzgsm     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzdom r5 = r0.zzh((com.google.android.gms.internal.ads.zzvg) r5)     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzdok r5 = r5.zzatn()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzaci<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzacx.zzdbv     // Catch:{ all -> 0x008c }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x008c }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x008c }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x006d
            com.google.android.gms.internal.ads.zzdom r0 = r4.zzgsm     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzvn r0 = r0.zzkg()     // Catch:{ all -> 0x008c }
            boolean r0 = r0.zzchx     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x006d
            com.google.android.gms.internal.ads.zzczs r0 = r4.zzgsq     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x006d
            com.google.android.gms.internal.ads.zzczs r5 = r4.zzgsq     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzdpg r0 = com.google.android.gms.internal.ads.zzdpg.INVALID_AD_SIZE     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzva r0 = com.google.android.gms.internal.ads.zzdpe.zza(r0, r2, r2)     // Catch:{ all -> 0x008c }
            r5.zzk(r0)     // Catch:{ all -> 0x008c }
            monitor-exit(r4)
            return r1
        L_0x006d:
            com.google.android.gms.internal.ads.zzbpm r5 = r4.zzb((com.google.android.gms.internal.ads.zzdok) r5)     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzbrl r0 = r5.zzaev()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzdzc r0 = r0.zzajh()     // Catch:{ all -> 0x008c }
            r4.zzgsx = r0     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzczm r1 = new com.google.android.gms.internal.ads.zzczm     // Catch:{ all -> 0x008c }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzbix r5 = r4.zzgpt     // Catch:{ all -> 0x008c }
            java.util.concurrent.Executor r5 = r5.zzadi()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.ads.zzdyq.zza(r0, r1, r5)     // Catch:{ all -> 0x008c }
            r5 = 1
            monitor-exit(r4)
            return r5
        L_0x008c:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzczj.zzg(com.google.android.gms.internal.ads.zzvg):boolean");
    }

    private final synchronized zzbpm zzb(zzdok zzdok) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcye)).booleanValue()) {
            return this.zzgpt.zzadp().zzd(new zzbtp.zza().zzce(this.zzgsl).zza(zzdok).zzajv()).zzd(new zzbys.zza().zzakr()).zza(new zzcyn(this.zzgsw)).zzb(new zzccw(zzcep.zzgap, (zzwt) null)).zza(new zzbqh(this.zzgsu)).zzc(new zzbol(this.zzfpw)).zzafw();
        }
        return this.zzgpt.zzadp().zzd(new zzbtp.zza().zzce(this.zzgsl).zza(zzdok).zzajv()).zzd(new zzbys.zza().zza((zzux) this.zzgsq, this.zzgpt.zzadi()).zza((zzux) this.zzgsr, this.zzgpt.zzadi()).zza((zzbuh) this.zzgsq, this.zzgpt.zzadi()).zza((zzbvs) this.zzgsq, this.zzgpt.zzadi()).zza((zzbui) this.zzgsq, this.zzgpt.zzadi()).zza((AppEventListener) this.zzgss, this.zzgpt.zzadi()).zza((zzbwl) this.zzgst, this.zzgpt.zzadi()).zzakr()).zza(new zzcyn(this.zzgsw)).zzb(new zzccw(zzcep.zzgap, (zzwt) null)).zza(new zzbqh(this.zzgsu)).zzc(new zzbol(this.zzfpw)).zzafw();
    }

    public final synchronized void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        if (this.zzgsg != null) {
            this.zzgsg.destroy();
        }
    }

    public final synchronized void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (this.zzgsg != null) {
            this.zzgsg.zzaiw().zzca((Context) null);
        }
    }

    public final synchronized void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        if (this.zzgsg != null) {
            this.zzgsg.zzaiw().zzcb((Context) null);
        }
    }

    public final void zza(zzwt zzwt) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzgsq.zzc(zzwt);
    }

    public final zzwt zzkk() {
        return this.zzgsq.zzaqt();
    }

    public final void zza(zzxk zzxk) {
        Preconditions.checkMainThread("setAppEventListener must be called on the main UI thread.");
        this.zzgss.zzb(zzxk);
    }

    public final zzxk zzkj() {
        return this.zzgss.zzaqs();
    }

    public final synchronized void zzkf() {
        Preconditions.checkMainThread("recordManualImpression must be called on the main UI thread.");
        if (this.zzgsg != null) {
            this.zzgsg.zzkf();
        }
    }

    public final synchronized zzvn zzkg() {
        Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
        if (this.zzgsg != null) {
            return zzdoq.zzb(this.zzgsl, Collections.singletonList(this.zzgsg.zzahw()));
        }
        return this.zzgsm.zzkg();
    }

    public final synchronized void zza(zzvn zzvn) {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
        this.zzgsm.zze(zzvn);
        this.zzgsv = zzvn;
        if (this.zzgsg != null) {
            this.zzgsg.zza(this.zzfpw, zzvn);
        }
    }

    public final synchronized String getMediationAdapterClassName() {
        if (this.zzgsg == null || this.zzgsg.zzaix() == null) {
            return null;
        }
        return this.zzgsg.zzaix().getMediationAdapterClassName();
    }

    public final synchronized String zzkh() {
        if (this.zzgsg == null || this.zzgsg.zzaix() == null) {
            return null;
        }
        return this.zzgsg.zzaix().getMediationAdapterClassName();
    }

    public final synchronized zzyn zzki() {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxl)).booleanValue()) {
            return null;
        }
        if (this.zzgsg == null) {
            return null;
        }
        return this.zzgsg.zzaix();
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

    public final synchronized zzyo getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        if (this.zzgsg == null) {
            return null;
        }
        return this.zzgsg.getVideoController();
    }

    public final synchronized String getAdUnitId() {
        return this.zzgsm.zzatl();
    }

    public final synchronized void zza(zzaak zzaak) {
        Preconditions.checkMainThread("setVideoOptions must be called on the main UI thread.");
        this.zzgsm.zzc(zzaak);
    }

    public final void zza(zzwo zzwo) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzgsr.zzb(zzwo);
    }

    public final synchronized void zza(zzaby zzaby) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzgsw = zzaby;
    }

    public final synchronized void zzakb() {
        boolean z;
        ViewParent parent = this.zzfpw.getParent();
        if (!(parent instanceof View)) {
            z = false;
        } else {
            View view = (View) parent;
            z = zzp.zzkr().zza(view, view.getContext());
        }
        if (z) {
            zzvn zzkg = this.zzgsm.zzkg();
            if (!(this.zzgsg == null || this.zzgsg.zzaif() == null || !this.zzgsm.zzato())) {
                zzkg = zzdoq.zzb(this.zzgsl, Collections.singletonList(this.zzgsg.zzaif()));
            }
            zzd(zzkg);
            zzg(this.zzgsm.zzatk());
            return;
        }
        this.zzgsu.zzdw(60);
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
}
