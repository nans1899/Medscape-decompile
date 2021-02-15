package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzyy {
    private final zzvl zzacs;
    private VideoOptions zzbnr;
    private boolean zzbny;
    private AppEventListener zzboa;
    private zzxc zzbut;
    private String zzbuu;
    private final zzanc zzbuw;
    private zzux zzcgv;
    private AdListener zzcha;
    private AdSize[] zzcif;
    private final AtomicBoolean zzcjs;
    /* access modifiers changed from: private */
    public final VideoController zzcjt;
    private final zzwp zzcju;
    private OnCustomRenderedAdLoadedListener zzcjv;
    private ViewGroup zzcjw;
    private int zzcjx;
    private OnPaidEventListener zzcjy;

    private static boolean zzcz(int i) {
        return i == 1;
    }

    public zzyy(ViewGroup viewGroup) {
        this(viewGroup, (AttributeSet) null, false, zzvl.zzchs, 0);
    }

    public zzyy(ViewGroup viewGroup, int i) {
        this(viewGroup, (AttributeSet) null, false, zzvl.zzchs, i);
    }

    public zzyy(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zzvl.zzchs, 0);
    }

    public zzyy(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i) {
        this(viewGroup, attributeSet, false, zzvl.zzchs, i);
    }

    private zzyy(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzvl zzvl, zzxc zzxc, int i) {
        zzvn zzvn;
        this.zzbuw = new zzanc();
        this.zzcjt = new VideoController();
        this.zzcju = new zzzb(this);
        this.zzcjw = viewGroup;
        this.zzacs = zzvl;
        this.zzbut = null;
        this.zzcjs = new AtomicBoolean(false);
        this.zzcjx = i;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zzvu zzvu = new zzvu(context, attributeSet);
                this.zzcif = zzvu.zzy(z);
                this.zzbuu = zzvu.getAdUnitId();
                if (viewGroup.isInEditMode()) {
                    zzbbg zzpt = zzwm.zzpt();
                    AdSize adSize = this.zzcif[0];
                    int i2 = this.zzcjx;
                    if (adSize.equals(AdSize.INVALID)) {
                        zzvn = zzvn.zzpn();
                    } else {
                        zzvn zzvn2 = new zzvn(context, adSize);
                        zzvn2.zzchw = zzcz(i2);
                        zzvn = zzvn2;
                    }
                    zzpt.zza(viewGroup, zzvn, "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                zzwm.zzpt().zza(viewGroup, new zzvn(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    private zzyy(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzvl zzvl, int i) {
        this(viewGroup, attributeSet, z, zzvl, (zzxc) null, i);
    }

    public final void destroy() {
        try {
            if (this.zzbut != null) {
                this.zzbut.destroy();
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final AdListener getAdListener() {
        return this.zzcha;
    }

    public final AdSize getAdSize() {
        zzvn zzkg;
        try {
            if (!(this.zzbut == null || (zzkg = this.zzbut.zzkg()) == null)) {
                return zzkg.zzpo();
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
        AdSize[] adSizeArr = this.zzcif;
        if (adSizeArr != null) {
            return adSizeArr[0];
        }
        return null;
    }

    public final AdSize[] getAdSizes() {
        return this.zzcif;
    }

    public final String getAdUnitId() {
        zzxc zzxc;
        if (this.zzbuu == null && (zzxc = this.zzbut) != null) {
            try {
                this.zzbuu = zzxc.getAdUnitId();
            } catch (RemoteException e) {
                zzbbq.zze("#007 Could not call remote method.", e);
            }
        }
        return this.zzbuu;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzboa;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzcjv;
    }

    public final void zza(zzyw zzyw) {
        zzxc zzxc;
        try {
            if (this.zzbut == null) {
                if ((this.zzcif == null || this.zzbuu == null) && this.zzbut == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context = this.zzcjw.getContext();
                zzvn zza = zza(context, this.zzcif, this.zzcjx);
                if ("search_v2".equals(zza.zzacy)) {
                    zzxc = (zzxc) new zzwe(zzwm.zzpu(), context, zza, this.zzbuu).zzd(context, false);
                } else {
                    zzxc = (zzxc) new zzvw(zzwm.zzpu(), context, zza, this.zzbuu, this.zzbuw).zzd(context, false);
                }
                this.zzbut = zzxc;
                zzxc.zza((zzwt) new zzvc(this.zzcju));
                if (this.zzcgv != null) {
                    this.zzbut.zza((zzwo) new zzuz(this.zzcgv));
                }
                if (this.zzboa != null) {
                    this.zzbut.zza((zzxk) new zzvt(this.zzboa));
                }
                if (this.zzcjv != null) {
                    this.zzbut.zza((zzaby) new zzabz(this.zzcjv));
                }
                if (this.zzbnr != null) {
                    this.zzbut.zza(new zzaak(this.zzbnr));
                }
                this.zzbut.zza((zzyi) new zzaab(this.zzcjy));
                this.zzbut.setManualImpressionsEnabled(this.zzbny);
                try {
                    IObjectWrapper zzke = this.zzbut.zzke();
                    if (zzke != null) {
                        this.zzcjw.addView((View) ObjectWrapper.unwrap(zzke));
                    }
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
            if (this.zzbut.zza(zzvl.zza(this.zzcjw.getContext(), zzyw))) {
                this.zzbuw.zzf(zzyw.zzqq());
            }
        } catch (RemoteException e2) {
            zzbbq.zze("#007 Could not call remote method.", e2);
        }
    }

    public final void pause() {
        try {
            if (this.zzbut != null) {
                this.zzbut.pause();
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void recordManualImpression() {
        if (!this.zzcjs.getAndSet(true)) {
            try {
                if (this.zzbut != null) {
                    this.zzbut.zzkf();
                }
            } catch (RemoteException e) {
                zzbbq.zze("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        try {
            if (this.zzbut != null) {
                this.zzbut.resume();
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        this.zzcha = adListener;
        this.zzcju.zza(adListener);
    }

    public final void zza(zzux zzux) {
        try {
            this.zzcgv = zzux;
            if (this.zzbut != null) {
                this.zzbut.zza((zzwo) zzux != null ? new zzuz(zzux) : null);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (this.zzcif == null) {
            zza(adSizeArr);
            return;
        }
        throw new IllegalStateException("The ad size can only be set once on AdView.");
    }

    public final void zza(AdSize... adSizeArr) {
        this.zzcif = adSizeArr;
        try {
            if (this.zzbut != null) {
                this.zzbut.zza(zza(this.zzcjw.getContext(), this.zzcif, this.zzcjx));
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
        this.zzcjw.requestLayout();
    }

    public final void setAdUnitId(String str) {
        if (this.zzbuu == null) {
            this.zzbuu = str;
            return;
        }
        throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzboa = appEventListener;
            if (this.zzbut != null) {
                this.zzbut.zza((zzxk) appEventListener != null ? new zzvt(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzcjv = onCustomRenderedAdLoadedListener;
        try {
            if (this.zzbut != null) {
                this.zzbut.zza((zzaby) onCustomRenderedAdLoadedListener != null ? new zzabz(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzbny = z;
        try {
            if (this.zzbut != null) {
                this.zzbut.setManualImpressionsEnabled(z);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzbut != null) {
                return this.zzbut.zzkh();
            }
            return null;
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final boolean isLoading() {
        try {
            if (this.zzbut != null) {
                return this.zzbut.isLoading();
            }
            return false;
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final ResponseInfo getResponseInfo() {
        zzyn zzyn = null;
        try {
            if (this.zzbut != null) {
                zzyn = this.zzbut.zzki();
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
        return ResponseInfo.zza(zzyn);
    }

    public final void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        try {
            this.zzcjy = onPaidEventListener;
            if (this.zzbut != null) {
                this.zzbut.zza((zzyi) new zzaab(onPaidEventListener));
            }
        } catch (RemoteException e) {
            zzbbq.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final VideoController getVideoController() {
        return this.zzcjt;
    }

    public final zzyo zzdv() {
        zzxc zzxc = this.zzbut;
        if (zzxc == null) {
            return null;
        }
        try {
            return zzxc.getVideoController();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        zzaak zzaak;
        this.zzbnr = videoOptions;
        try {
            if (this.zzbut != null) {
                zzxc zzxc = this.zzbut;
                if (videoOptions == null) {
                    zzaak = null;
                } else {
                    zzaak = new zzaak(videoOptions);
                }
                zzxc.zza(zzaak);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final VideoOptions getVideoOptions() {
        return this.zzbnr;
    }

    public final boolean zza(zzxc zzxc) {
        if (zzxc == null) {
            return false;
        }
        try {
            IObjectWrapper zzke = zzxc.zzke();
            if (zzke == null || ((View) ObjectWrapper.unwrap(zzke)).getParent() != null) {
                return false;
            }
            this.zzcjw.addView((View) ObjectWrapper.unwrap(zzke));
            this.zzbut = zzxc;
            return true;
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return false;
        }
    }

    private static zzvn zza(Context context, AdSize[] adSizeArr, int i) {
        for (AdSize equals : adSizeArr) {
            if (equals.equals(AdSize.INVALID)) {
                return zzvn.zzpn();
            }
        }
        zzvn zzvn = new zzvn(context, adSizeArr);
        zzvn.zzchw = zzcz(i);
        return zzvn;
    }
}
