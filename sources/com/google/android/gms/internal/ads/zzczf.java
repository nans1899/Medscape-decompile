package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczf extends zzxf {
    private final ViewGroup zzfpw;
    private final zzdok zzfsk;
    private final zzwt zzfxl;
    private final zzboq zzgsg;
    private final Context zzvr;

    public zzczf(Context context, zzwt zzwt, zzdok zzdok, zzboq zzboq) {
        this.zzvr = context;
        this.zzfxl = zzwt;
        this.zzfsk = zzdok;
        this.zzgsg = zzboq;
        FrameLayout frameLayout = new FrameLayout(this.zzvr);
        frameLayout.removeAllViews();
        frameLayout.addView(this.zzgsg.zzahx(), zzp.zzkt().zzxx());
        frameLayout.setMinimumHeight(zzkg().heightPixels);
        frameLayout.setMinimumWidth(zzkg().widthPixels);
        this.zzfpw = frameLayout;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final boolean isReady() throws RemoteException {
        return false;
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
    }

    public final void showInterstitial() throws RemoteException {
    }

    public final void stopLoading() throws RemoteException {
    }

    public final void zza(zzari zzari) throws RemoteException {
    }

    public final void zza(zzaro zzaro, String str) throws RemoteException {
    }

    public final void zza(zzaug zzaug) throws RemoteException {
    }

    public final void zza(zzsh zzsh) throws RemoteException {
    }

    public final void zza(zzvs zzvs) throws RemoteException {
    }

    public final void zza(zzyu zzyu) throws RemoteException {
    }

    public final void zzbp(String str) throws RemoteException {
    }

    public final IObjectWrapper zzke() throws RemoteException {
        return ObjectWrapper.wrap(this.zzfpw);
    }

    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgsg.destroy();
    }

    public final boolean zza(zzvg zzvg) throws RemoteException {
        zzayp.zzfd("loadAd is not supported for a Publisher AdView returned from AdLoader.");
        return false;
    }

    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgsg.zzaiw().zzca((Context) null);
    }

    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgsg.zzaiw().zzcb((Context) null);
    }

    public final void zzkf() throws RemoteException {
        this.zzgsg.zzkf();
    }

    public final zzvn zzkg() {
        Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
        return zzdoq.zzb(this.zzvr, Collections.singletonList(this.zzgsg.zzahw()));
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        if (this.zzgsg.zzaix() != null) {
            return this.zzgsg.zzaix().getMediationAdapterClassName();
        }
        return null;
    }

    public final zzyo getVideoController() throws RemoteException {
        return this.zzgsg.getVideoController();
    }

    public final String getAdUnitId() throws RemoteException {
        return this.zzfsk.zzhfb;
    }

    public final zzxk zzkj() throws RemoteException {
        return this.zzfsk.zzhfg;
    }

    public final zzwt zzkk() throws RemoteException {
        return this.zzfxl;
    }

    public final String zzkh() throws RemoteException {
        if (this.zzgsg.zzaix() != null) {
            return this.zzgsg.zzaix().getMediationAdapterClassName();
        }
        return null;
    }

    public final zzyn zzki() {
        return this.zzgsg.zzaix();
    }

    public final void zza(zzaak zzaak) throws RemoteException {
        zzayp.zzfd("setVideoOptions is not supported in Publisher AdView returned by AdLoader.");
    }

    public final void zza(zzvn zzvn) throws RemoteException {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
        zzboq zzboq = this.zzgsg;
        if (zzboq != null) {
            zzboq.zza(this.zzfpw, zzvn);
        }
    }

    public final void zza(zzaby zzaby) throws RemoteException {
        zzayp.zzfd("setOnCustomRenderedAdLoadedListener is not supported in Publisher AdView returned by AdLoader.");
    }

    public final void zza(zzwo zzwo) throws RemoteException {
        zzayp.zzfd("setAdClickListener is not supported in Publisher AdView returned by AdLoader.");
    }

    public final void zza(zzxq zzxq) throws RemoteException {
        zzayp.zzfd("setCorrelationIdProvider is not supported in Publisher AdView returned by AdLoader.");
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        zzayp.zzfd("setManualImpressionsEnabled is not supported in Publisher AdView returned by AdLoader.");
    }

    public final void zza(zzwt zzwt) throws RemoteException {
        zzayp.zzfd("setAdListener is not supported in Publisher AdView returned by AdLoader.");
    }

    public final void zza(zzxk zzxk) throws RemoteException {
        zzayp.zzfd("setAppEventListener is not supported in Publisher AdView returned by AdLoader.");
    }

    public final void zza(zzxj zzxj) throws RemoteException {
        zzayp.zzfd("setAdMetadataListener is not supported in Publisher AdView returned by AdLoader.");
    }

    public final Bundle getAdMetadata() throws RemoteException {
        zzayp.zzfd("getAdMetadata is not supported in Publisher AdView returned by AdLoader.");
        return new Bundle();
    }

    public final void zza(zzyi zzyi) {
        zzayp.zzfd("setOnPaidEventListener is not supported in Publisher AdView returned by AdLoader.");
    }
}
