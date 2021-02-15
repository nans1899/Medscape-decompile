package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzadu;
import com.google.android.gms.internal.ads.zzafe;
import com.google.android.gms.internal.ads.zzaff;
import com.google.android.gms.internal.ads.zzaft;
import com.google.android.gms.internal.ads.zzagk;
import com.google.android.gms.internal.ads.zzagn;
import com.google.android.gms.internal.ads.zzago;
import com.google.android.gms.internal.ads.zzagq;
import com.google.android.gms.internal.ads.zzags;
import com.google.android.gms.internal.ads.zzanc;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzvc;
import com.google.android.gms.internal.ads.zzvl;
import com.google.android.gms.internal.ads.zzvn;
import com.google.android.gms.internal.ads.zzwm;
import com.google.android.gms.internal.ads.zzwt;
import com.google.android.gms.internal.ads.zzwu;
import com.google.android.gms.internal.ads.zzwz;
import com.google.android.gms.internal.ads.zzyw;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class AdLoader {
    private final zzvl zzacs;
    private final zzwu zzact;
    private final Context zzvr;

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static class Builder {
        private final zzwz zzacr;
        private final Context zzvr;

        @Deprecated
        public Builder withCorrelator(Correlator correlator) {
            return this;
        }

        public Builder(Context context, String str) {
            this((Context) Preconditions.checkNotNull(context, "context cannot be null"), zzwm.zzpu().zzb(context, str, new zzanc()));
        }

        private Builder(Context context, zzwz zzwz) {
            this.zzvr = context;
            this.zzacr = zzwz;
        }

        @Deprecated
        public Builder forContentAd(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzacr.zza((zzaff) new zzagn(onContentAdLoadedListener));
            } catch (RemoteException e) {
                zzbbq.zzd("Failed to add content ad listener", e);
            }
            return this;
        }

        @Deprecated
        public Builder forAppInstallAd(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzacr.zza((zzafe) new zzago(onAppInstallAdLoadedListener));
            } catch (RemoteException e) {
                zzbbq.zzd("Failed to add app install ad listener", e);
            }
            return this;
        }

        public Builder forUnifiedNativeAd(UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
            try {
                this.zzacr.zza((zzaft) new zzags(onUnifiedNativeAdLoadedListener));
            } catch (RemoteException e) {
                zzbbq.zzd("Failed to add google native ad listener", e);
            }
            return this;
        }

        public Builder forCustomTemplateAd(String str, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
            zzagk zzagk = new zzagk(onCustomTemplateAdLoadedListener, onCustomClickListener);
            try {
                this.zzacr.zza(str, zzagk.zzsx(), zzagk.zzsy());
            } catch (RemoteException e) {
                zzbbq.zzd("Failed to add custom template ad listener", e);
            }
            return this;
        }

        public Builder forPublisherAdView(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener, AdSize... adSizeArr) {
            if (adSizeArr == null || adSizeArr.length <= 0) {
                throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
            }
            try {
                this.zzacr.zza(new zzagq(onPublisherAdViewLoadedListener), new zzvn(this.zzvr, adSizeArr));
            } catch (RemoteException e) {
                zzbbq.zzd("Failed to add publisher banner ad listener", e);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener) {
            try {
                this.zzacr.zzb((zzwt) new zzvc(adListener));
            } catch (RemoteException e) {
                zzbbq.zzd("Failed to set AdListener.", e);
            }
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions) {
            try {
                this.zzacr.zza(new zzadu(nativeAdOptions));
            } catch (RemoteException e) {
                zzbbq.zzd("Failed to specify native ad options", e);
            }
            return this;
        }

        public Builder withPublisherAdViewOptions(PublisherAdViewOptions publisherAdViewOptions) {
            try {
                this.zzacr.zza(publisherAdViewOptions);
            } catch (RemoteException e) {
                zzbbq.zzd("Failed to specify DFP banner ad options", e);
            }
            return this;
        }

        public AdLoader build() {
            try {
                return new AdLoader(this.zzvr, this.zzacr.zzqf());
            } catch (RemoteException e) {
                zzbbq.zzc("Failed to build AdLoader.", e);
                return null;
            }
        }
    }

    AdLoader(Context context, zzwu zzwu) {
        this(context, zzwu, zzvl.zzchs);
    }

    private AdLoader(Context context, zzwu zzwu, zzvl zzvl) {
        this.zzvr = context;
        this.zzact = zzwu;
        this.zzacs = zzvl;
    }

    private final void zza(zzyw zzyw) {
        try {
            this.zzact.zzb(zzvl.zza(this.zzvr, zzyw));
        } catch (RemoteException e) {
            zzbbq.zzc("Failed to load ad.", e);
        }
    }

    public void loadAd(AdRequest adRequest) {
        zza(adRequest.zzdr());
    }

    public void loadAds(AdRequest adRequest, int i) {
        try {
            this.zzact.zza(zzvl.zza(this.zzvr, adRequest.zzdr()), i);
        } catch (RemoteException e) {
            zzbbq.zzc("Failed to load ads.", e);
        }
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        zza(publisherAdRequest.zzdr());
    }

    @Deprecated
    public String getMediationAdapterClassName() {
        try {
            return this.zzact.zzkh();
        } catch (RemoteException e) {
            zzbbq.zzd("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public boolean isLoading() {
        try {
            return this.zzact.isLoading();
        } catch (RemoteException e) {
            zzbbq.zzd("Failed to check if ad is loading.", e);
            return false;
        }
    }
}
