package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.RemoteException;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdConfiguration;
import com.google.android.gms.ads.mediation.MediationNativeAdConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.mediation.rtb.RtbSignalData;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import net.media.android.bidder.base.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzapq extends zzapk {
    /* access modifiers changed from: private */
    public MediationRewardedAd zzdlj;
    private final RtbAdapter zzdmi;
    /* access modifiers changed from: private */
    public MediationInterstitialAd zzdmj;
    private String zzdmk = "";

    public zzapq(RtbAdapter rtbAdapter) {
        this.zzdmi = rtbAdapter;
    }

    public final void zza(String[] strArr, Bundle[] bundleArr) {
    }

    public final void zzy(IObjectWrapper iObjectWrapper) {
    }

    public final void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzaov zzaov, zzanh zzanh, zzvn zzvn) throws RemoteException {
        zzvg zzvg2 = zzvg;
        zzvn zzvn2 = zzvn;
        try {
            zzapp zzapp = new zzapp(this, zzaov, zzanh);
            RtbAdapter rtbAdapter = this.zzdmi;
            Bundle zzdu = zzdu(str2);
            Bundle zzd = zzd(zzvg2);
            boolean zzc = zzc(zzvg);
            Location location = zzvg2.zznb;
            int i = zzvg2.zzadm;
            int i2 = zzvg2.zzadn;
            String zza = zza(str2, zzvg);
            AdSize zza2 = zzb.zza(zzvn2.width, zzvn2.height, zzvn2.zzacy);
            String str3 = this.zzdmk;
            MediationBannerAdConfiguration mediationBannerAdConfiguration = r5;
            MediationBannerAdConfiguration mediationBannerAdConfiguration2 = new MediationBannerAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdu, zzd, zzc, location, i, i2, zza, zza2, str3);
            rtbAdapter.loadBannerAd(mediationBannerAdConfiguration, zzapp);
        } catch (Throwable th) {
            zzbbq.zzc("Adapter failed to render banner ad.", th);
            throw new RemoteException();
        }
    }

    public final void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapa zzapa, zzanh zzanh) throws RemoteException {
        zzvg zzvg2 = zzvg;
        try {
            this.zzdmi.loadInterstitialAd(new MediationInterstitialAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdu(str2), zzd(zzvg2), zzc(zzvg), zzvg2.zznb, zzvg2.zzadm, zzvg2.zzadn, zza(str2, zzvg), this.zzdmk), new zzaps(this, zzapa, zzanh));
        } catch (Throwable th) {
            zzbbq.zzc("Adapter failed to render interstitial ad.", th);
            throw new RemoteException();
        }
    }

    public final void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapg zzapg, zzanh zzanh) throws RemoteException {
        zzvg zzvg2 = zzvg;
        try {
            this.zzdmi.loadRewardedAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdu(str2), zzd(zzvg2), zzc(zzvg), zzvg2.zznb, zzvg2.zzadm, zzvg2.zzadn, zza(str2, zzvg), this.zzdmk), zza(zzapg, zzanh));
        } catch (Throwable th) {
            zzbbq.zzc("Adapter failed to render rewarded ad.", th);
            throw new RemoteException();
        }
    }

    public final void zzb(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapg zzapg, zzanh zzanh) throws RemoteException {
        zzvg zzvg2 = zzvg;
        try {
            this.zzdmi.loadRewardedInterstitialAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdu(str2), zzd(zzvg2), zzc(zzvg), zzvg2.zznb, zzvg2.zzadm, zzvg2.zzadn, zza(str2, zzvg), this.zzdmk), zza(zzapg, zzanh));
        } catch (Throwable th) {
            zzbbq.zzc("Adapter failed to render rewarded interstitial ad.", th);
            throw new RemoteException();
        }
    }

    public final void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapb zzapb, zzanh zzanh) throws RemoteException {
        zzvg zzvg2 = zzvg;
        try {
            this.zzdmi.loadNativeAd(new MediationNativeAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdu(str2), zzd(zzvg2), zzc(zzvg), zzvg2.zznb, zzvg2.zzadm, zzvg2.zzadn, zza(str2, zzvg), this.zzdmk), new zzapr(this, zzapb, zzanh));
        } catch (Throwable th) {
            zzbbq.zzc("Adapter failed to render rewarded ad.", th);
            throw new RemoteException();
        }
    }

    public final boolean zzz(IObjectWrapper iObjectWrapper) throws RemoteException {
        MediationInterstitialAd mediationInterstitialAd = this.zzdmj;
        if (mediationInterstitialAd == null) {
            return false;
        }
        try {
            mediationInterstitialAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
            return true;
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            return true;
        }
    }

    public final boolean zzaa(IObjectWrapper iObjectWrapper) throws RemoteException {
        MediationRewardedAd mediationRewardedAd = this.zzdlj;
        if (mediationRewardedAd == null) {
            return false;
        }
        try {
            mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
            return true;
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            return true;
        }
    }

    public final zzyo getVideoController() {
        RtbAdapter rtbAdapter = this.zzdmi;
        if (!(rtbAdapter instanceof zza)) {
            return null;
        }
        try {
            return ((zza) rtbAdapter).getVideoController();
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            return null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zzvn zzvn, zzapm zzapm) throws RemoteException {
        AdFormat adFormat;
        try {
            zzapu zzapu = new zzapu(this, zzapm);
            RtbAdapter rtbAdapter = this.zzdmi;
            char c = 65535;
            switch (str.hashCode()) {
                case -1396342996:
                    if (str.equals(Constants.Capabilities.BANNER)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1052618729:
                    if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                        c = 3;
                        break;
                    }
                    break;
                case -239580146:
                    if (str.equals("rewarded")) {
                        c = 2;
                        break;
                    }
                    break;
                case 604727084:
                    if (str.equals("interstitial")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                adFormat = AdFormat.BANNER;
            } else if (c == 1) {
                adFormat = AdFormat.INTERSTITIAL;
            } else if (c == 2) {
                adFormat = AdFormat.REWARDED;
            } else if (c == 3) {
                adFormat = AdFormat.NATIVE;
            } else {
                throw new IllegalArgumentException("Internal Error");
            }
            MediationConfiguration mediationConfiguration = new MediationConfiguration(adFormat, bundle2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(mediationConfiguration);
            rtbAdapter.collectSignals(new RtbSignalData((Context) ObjectWrapper.unwrap(iObjectWrapper), arrayList, bundle, zzb.zza(zzvn.width, zzvn.height, zzvn.zzacy)), zzapu);
        } catch (Throwable th) {
            zzbbq.zzc("Error generating signals for RTB", th);
            throw new RemoteException();
        }
    }

    public final zzapv zzua() throws RemoteException {
        return zzapv.zza(this.zzdmi.getVersionInfo());
    }

    public final zzapv zzub() throws RemoteException {
        return zzapv.zza(this.zzdmi.getSDKVersionInfo());
    }

    public final void zzdr(String str) {
        this.zzdmk = str;
    }

    private final MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> zza(zzapg zzapg, zzanh zzanh) {
        return new zzapt(this, zzapg, zzanh);
    }

    private static Bundle zzdu(String str) throws RemoteException {
        String valueOf = String.valueOf(str);
        zzbbq.zzfe(valueOf.length() != 0 ? "Server parameters: ".concat(valueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str == null) {
                return bundle;
            }
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle2 = new Bundle();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle2.putString(next, jSONObject.getString(next));
            }
            return bundle2;
        } catch (JSONException e) {
            zzbbq.zzc("", e);
            throw new RemoteException();
        }
    }

    private static boolean zzc(zzvg zzvg) {
        if (zzvg.zzche) {
            return true;
        }
        zzwm.zzpt();
        return zzbbg.zzym();
    }

    private static String zza(String str, zzvg zzvg) {
        try {
            return new JSONObject(str).getString("max_ad_content_rating");
        } catch (JSONException unused) {
            return zzvg.zzado;
        }
    }

    private final Bundle zzd(zzvg zzvg) {
        Bundle bundle;
        if (zzvg.zzchi == null || (bundle = zzvg.zzchi.getBundle(this.zzdmi.getClass().getName())) == null) {
            return new Bundle();
        }
        return bundle;
    }
}
