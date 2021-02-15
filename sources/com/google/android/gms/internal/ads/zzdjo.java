package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.overlay.zzo;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.overlay.zzw;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdjo extends zzxf implements zzw, zzbwb, zzsc {
    /* access modifiers changed from: private */
    public final zzbbx zzbot;
    private final String zzbuu;
    /* access modifiers changed from: private */
    public final ViewGroup zzfpw;
    private final zzbix zzgpt;
    private final Context zzgsl;
    private AtomicBoolean zzgzs = new AtomicBoolean();
    private final zzdjm zzgzt;
    /* access modifiers changed from: private */
    public final zzdkc zzgzu;
    private long zzgzv;
    private zzbnh zzgzw;
    protected zzbnv zzgzx;

    public zzdjo(zzbix zzbix, Context context, String str, zzdjm zzdjm, zzdkc zzdkc, zzbbx zzbbx) {
        this.zzfpw = new FrameLayout(context);
        this.zzgpt = zzbix;
        this.zzgsl = context;
        this.zzbuu = str;
        this.zzgzt = zzdjm;
        this.zzgzu = zzdkc;
        zzdkc.zza((zzbwb) this);
        this.zzbot = zzbbx;
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

    public final zzxk zzkj() {
        return null;
    }

    public final zzwt zzkk() {
        return null;
    }

    public final IObjectWrapper zzke() {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzfpw);
    }

    public final void zza(zzvs zzvs) {
        this.zzgzt.zza(zzvs);
    }

    public final synchronized boolean zza(zzvg zzvg) throws RemoteException {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        zzp.zzkr();
        if (zzayu.zzbe(this.zzgsl) && zzvg.zzcho == null) {
            zzayp.zzfc("Failed to load the ad because app ID is missing.");
            this.zzgzu.zzk(zzdpe.zza(zzdpg.APP_ID_MISSING, (String) null, (zzva) null));
            return false;
        } else if (isLoading()) {
            return false;
        } else {
            this.zzgzs = new AtomicBoolean();
            return this.zzgzt.zza(zzvg, this.zzbuu, new zzdjt(this), new zzdjs(this));
        }
    }

    /* access modifiers changed from: private */
    public final zzo zza(zzbnv zzbnv) {
        boolean zzabc = zzbnv.zzabc();
        int intValue = ((Integer) zzwm.zzpx().zzd(zzabb.zzctv)).intValue();
        zzr zzr = new zzr();
        zzr.size = 50;
        zzr.paddingLeft = zzabc ? intValue : 0;
        zzr.paddingRight = zzabc ? 0 : intValue;
        zzr.paddingTop = 0;
        zzr.paddingBottom = intValue;
        return new zzo(this.zzgsl, zzr, this);
    }

    /* access modifiers changed from: private */
    public static RelativeLayout.LayoutParams zzb(zzbnv zzbnv) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(zzbnv.zzabc() ? 11 : 9);
        return layoutParams;
    }

    /* access modifiers changed from: private */
    public final void zzc(zzbnv zzbnv) {
        zzbnv.zza((zzsc) this);
    }

    public final void zzus() {
        zzasr();
    }

    public final void zzmt() {
        zzasr();
    }

    public final void zzajx() {
        if (this.zzgzx != null) {
            this.zzgzv = zzp.zzky().elapsedRealtime();
            int zzaho = this.zzgzx.zzaho();
            if (zzaho > 0) {
                zzbnh zzbnh = new zzbnh(this.zzgpt.zzadj(), zzp.zzky());
                this.zzgzw = zzbnh;
                zzbnh.zza(zzaho, new zzdjq(this));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaso */
    public final void zzasr() {
        if (this.zzgzs.compareAndSet(false, true)) {
            zzbnv zzbnv = this.zzgzx;
            if (!(zzbnv == null || zzbnv.zzahz() == null)) {
                this.zzgzu.zzb(this.zzgzx.zzahz());
            }
            this.zzgzu.onAdClosed();
            this.zzfpw.removeAllViews();
            zzbnh zzbnh = this.zzgzw;
            if (zzbnh != null) {
                zzp.zzku().zzb(zzbnh);
            }
            zzbnv zzbnv2 = this.zzgzx;
            if (zzbnv2 != null) {
                zzbnv2.zzfd(zzp.zzky().elapsedRealtime() - this.zzgzv);
            }
            destroy();
        }
    }

    /* access modifiers changed from: private */
    public final zzvn zzasp() {
        return zzdoq.zzb(this.zzgsl, Collections.singletonList(this.zzgzx.zzahw()));
    }

    public final synchronized void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        if (this.zzgzx != null) {
            this.zzgzx.destroy();
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

    public final synchronized zzvn zzkg() {
        Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
        if (this.zzgzx == null) {
            return null;
        }
        return zzdoq.zzb(this.zzgsl, Collections.singletonList(this.zzgzx.zzahw()));
    }

    public final synchronized void zza(zzvn zzvn) {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
    }

    public final synchronized String getMediationAdapterClassName() {
        return null;
    }

    public final synchronized String zzkh() {
        return null;
    }

    public final synchronized zzyn zzki() {
        return null;
    }

    public final synchronized boolean isLoading() {
        return this.zzgzt.isLoading();
    }

    public final synchronized String getAdUnitId() {
        return this.zzbuu;
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
    public final /* synthetic */ void zzasq() {
        this.zzgpt.zzadi().execute(new zzdjr(this));
    }
}
