package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzza {
    private final zzvl zzacs;
    private AppEventListener zzboa;
    private boolean zzbpb;
    private zzxc zzbut;
    private String zzbuu;
    private final zzanc zzbuw;
    private zzux zzcgv;
    private AdListener zzcha;
    private AdMetadataListener zzchq;
    private OnCustomRenderedAdLoadedListener zzcjv;
    private OnPaidEventListener zzcjy;
    private RewardedVideoAdListener zzckd;
    private boolean zzcke;
    private final Context zzvr;

    public zzza(Context context) {
        this(context, zzvl.zzchs, (PublisherInterstitialAd) null);
    }

    public zzza(Context context, PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzvl.zzchs, publisherInterstitialAd);
    }

    private zzza(Context context, zzvl zzvl, PublisherInterstitialAd publisherInterstitialAd) {
        this.zzbuw = new zzanc();
        this.zzvr = context;
        this.zzacs = zzvl;
    }

    public final AdListener getAdListener() {
        return this.zzcha;
    }

    public final String getAdUnitId() {
        return this.zzbuu;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzboa;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzcjv;
    }

    public final boolean isLoaded() {
        try {
            if (this.zzbut == null) {
                return false;
            }
            return this.zzbut.isReady();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final boolean isLoading() {
        try {
            if (this.zzbut == null) {
                return false;
            }
            return this.zzbut.isLoading();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final void zza(zzyw zzyw) {
        try {
            if (this.zzbut == null) {
                if (this.zzbuu == null) {
                    zzcr("loadAd");
                }
                zzvn zzpl = this.zzcke ? zzvn.zzpl() : new zzvn();
                zzvx zzpu = zzwm.zzpu();
                Context context = this.zzvr;
                zzxc zzxc = (zzxc) new zzwh(zzpu, context, zzpl, this.zzbuu, this.zzbuw).zzd(context, false);
                this.zzbut = zzxc;
                if (this.zzcha != null) {
                    zzxc.zza((zzwt) new zzvc(this.zzcha));
                }
                if (this.zzcgv != null) {
                    this.zzbut.zza((zzwo) new zzuz(this.zzcgv));
                }
                if (this.zzchq != null) {
                    this.zzbut.zza((zzxj) new zzvh(this.zzchq));
                }
                if (this.zzboa != null) {
                    this.zzbut.zza((zzxk) new zzvt(this.zzboa));
                }
                if (this.zzcjv != null) {
                    this.zzbut.zza((zzaby) new zzabz(this.zzcjv));
                }
                if (this.zzckd != null) {
                    this.zzbut.zza((zzaug) new zzaun(this.zzckd));
                }
                this.zzbut.zza((zzyi) new zzaab(this.zzcjy));
                this.zzbut.setImmersiveMode(this.zzbpb);
            }
            if (this.zzbut.zza(zzvl.zza(this.zzvr, zzyw))) {
                this.zzbuw.zzf(zzyw.zzqq());
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        try {
            this.zzcha = adListener;
            if (this.zzbut != null) {
                this.zzbut.zza((zzwt) adListener != null ? new zzvc(adListener) : null);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
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

    public final void setAdUnitId(String str) {
        if (this.zzbuu == null) {
            this.zzbuu = str;
            return;
        }
        throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener) {
        try {
            this.zzchq = adMetadataListener;
            if (this.zzbut != null) {
                this.zzbut.zza((zzxj) adMetadataListener != null ? new zzvh(adMetadataListener) : null);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final Bundle getAdMetadata() {
        try {
            if (this.zzbut != null) {
                return this.zzbut.getAdMetadata();
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
        return new Bundle();
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
        try {
            this.zzcjv = onCustomRenderedAdLoadedListener;
            if (this.zzbut != null) {
                this.zzbut.zza((zzaby) onCustomRenderedAdLoadedListener != null ? new zzabz(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzckd = rewardedVideoAdListener;
            if (this.zzbut != null) {
                this.zzbut.zza((zzaug) rewardedVideoAdListener != null ? new zzaun(rewardedVideoAdListener) : null);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void zzd(boolean z) {
        this.zzcke = true;
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

    public final void show() {
        try {
            zzcr("show");
            this.zzbut.showInterstitial();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setImmersiveMode(boolean z) {
        try {
            this.zzbpb = z;
            if (this.zzbut != null) {
                this.zzbut.setImmersiveMode(z);
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        try {
            this.zzcjy = onPaidEventListener;
            if (this.zzbut != null) {
                this.zzbut.zza((zzyi) new zzaab(onPaidEventListener));
            }
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    private final void zzcr(String str) {
        if (this.zzbut == null) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 63);
            sb.append("The ad unit ID must be set on InterstitialAd before ");
            sb.append(str);
            sb.append(" is called.");
            throw new IllegalStateException(sb.toString());
        }
    }
}
