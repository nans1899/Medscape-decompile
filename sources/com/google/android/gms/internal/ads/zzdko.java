package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdko extends zzxf implements zzp, zzbwb, zzsc {
    private final String zzbuu;
    private final zzbix zzgpt;
    private final Context zzgsl;
    private AtomicBoolean zzgzs = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final zzdkc zzgzu;
    private long zzgzv;
    private zzbnh zzgzw;
    private final zzdkm zzhay;
    protected zzboh zzhaz;

    public zzdko(zzbix zzbix, Context context, String str, zzdkm zzdkm, zzdkc zzdkc) {
        this.zzgpt = zzbix;
        this.zzgsl = context;
        this.zzbuu = str;
        this.zzhay = zzdkm;
        this.zzgzu = zzdkc;
        zzdkc.zza((zzbwb) this);
        zzdkc.zza((zzp) this);
    }

    public final boolean isReady() {
        return false;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void setImmersiveMode(boolean z) {
    }

    public final void setUserId(String str) {
    }

    public final void stopLoading() {
    }

    public final void zza(zzari zzari) {
    }

    public final void zza(zzaro zzaro, String str) {
    }

    public final void zza(zzaug zzaug) {
    }

    public final void zza(zzwo zzwo) {
    }

    public final void zza(zzwt zzwt) {
    }

    public final void zza(zzxj zzxj) {
    }

    public final void zza(zzxk zzxk) {
    }

    public final void zza(zzyi zzyi) {
    }

    public final void zza(zzyu zzyu) {
    }

    public final void zzbp(String str) {
    }

    public final IObjectWrapper zzke() {
        return null;
    }

    public final zzxk zzkj() {
        return null;
    }

    public final zzwt zzkk() {
        return null;
    }

    public final void zzun() {
    }

    public final void zza(zzvs zzvs) {
        this.zzhay.zza(zzvs);
    }

    public final synchronized boolean zza(zzvg zzvg) throws RemoteException {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        com.google.android.gms.ads.internal.zzp.zzkr();
        if (zzayu.zzbe(this.zzgsl) && zzvg.zzcho == null) {
            zzayp.zzfc("Failed to load the ad because app ID is missing.");
            this.zzgzu.zzk(zzdpe.zza(zzdpg.APP_ID_MISSING, (String) null, (zzva) null));
            return false;
        } else if (isLoading()) {
            return false;
        } else {
            this.zzgzs = new AtomicBoolean();
            return this.zzhay.zza(zzvg, this.zzbuu, new zzdkt(this), new zzdks(this));
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzboh zzboh) {
        zzboh.zza(this);
    }

    public final void zzum() {
        zzasu();
    }

    public final void zzmt() {
        zzasu();
    }

    public final synchronized void zzajx() {
        if (this.zzhaz != null) {
            this.zzgzv = com.google.android.gms.ads.internal.zzp.zzky().elapsedRealtime();
            int zzaho = this.zzhaz.zzaho();
            if (zzaho > 0) {
                zzbnh zzbnh = new zzbnh(this.zzgpt.zzadj(), com.google.android.gms.ads.internal.zzp.zzky());
                this.zzgzw = zzbnh;
                zzbnh.zza(zzaho, new zzdkq(this));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaso */
    public final synchronized void zzasu() {
        if (this.zzgzs.compareAndSet(false, true)) {
            this.zzgzu.onAdClosed();
            if (this.zzgzw != null) {
                com.google.android.gms.ads.internal.zzp.zzku().zzb(this.zzgzw);
            }
            if (this.zzhaz != null) {
                this.zzhaz.zzfd(com.google.android.gms.ads.internal.zzp.zzky().elapsedRealtime() - this.zzgzv);
            }
            destroy();
        }
    }

    public final synchronized void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        if (this.zzhaz != null) {
            this.zzhaz.destroy();
        }
    }

    public final synchronized void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    public final synchronized void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    public final void zza(zzsh zzsh) {
        this.zzgzu.zzb(zzsh);
    }

    public final synchronized void zza(zzvn zzvn) {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
    }

    public final synchronized boolean isLoading() {
        return this.zzhay.isLoading();
    }

    public final synchronized String getAdUnitId() {
        return this.zzbuu;
    }

    public final synchronized zzyn zzki() {
        return null;
    }

    public final synchronized zzvn zzkg() {
        return null;
    }

    public final synchronized void showInterstitial() {
    }

    public final synchronized String getMediationAdapterClassName() {
        return null;
    }

    public final synchronized String zzkh() {
        return null;
    }

    public final synchronized void zzkf() {
    }

    public final synchronized void zza(zzxq zzxq) {
    }

    public final synchronized void setManualImpressionsEnabled(boolean z) {
    }

    public final synchronized zzyo getVideoController() {
        return null;
    }

    public final synchronized void zza(zzaak zzaak) {
    }

    public final synchronized void zza(zzaby zzaby) {
    }

    public final Bundle getAdMetadata() {
        return new Bundle();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzast() {
        this.zzgpt.zzadi().execute(new zzdkr(this));
    }
}
