package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.RemoteException;
import com.facebook.internal.AnalyticsEvents;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import net.media.android.bidder.base.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzanx extends zzanf {
    /* access modifiers changed from: private */
    public final Object zzdlf;
    private zzaoc zzdlg;
    private zzaur zzdlh;
    private IObjectWrapper zzdli;
    /* access modifiers changed from: private */
    public MediationRewardedAd zzdlj;

    public zzanx(MediationAdapter mediationAdapter) {
        this.zzdlf = mediationAdapter;
    }

    public zzanx(Adapter adapter) {
        this.zzdlf = adapter;
    }

    public final IObjectWrapper zzts() throws RemoteException {
        Object obj = this.zzdlf;
        if (obj instanceof MediationBannerAdapter) {
            try {
                return ObjectWrapper.wrap(((MediationBannerAdapter) obj).getBannerView());
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else {
            String canonicalName = MediationBannerAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvn zzvn, zzvg zzvg, String str, zzanh zzanh) throws RemoteException {
        zza(iObjectWrapper, zzvn, zzvg, str, (String) null, zzanh);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvn zzvn, zzvg zzvg, String str, String str2, zzanh zzanh) throws RemoteException {
        Date date;
        AdSize zza;
        zzvn zzvn2 = zzvn;
        zzvg zzvg2 = zzvg;
        String str3 = str;
        if (this.zzdlf instanceof MediationBannerAdapter) {
            zzbbq.zzef("Requesting banner ad from adapter.");
            try {
                MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzdlf;
                Bundle bundle = null;
                HashSet hashSet = zzvg2.zzchd != null ? new HashSet(zzvg2.zzchd) : null;
                if (zzvg2.zzchb == -1) {
                    date = null;
                } else {
                    date = new Date(zzvg2.zzchb);
                }
                zzany zzany = new zzany(date, zzvg2.zzchc, hashSet, zzvg2.zznb, zzc(zzvg), zzvg2.zzadm, zzvg2.zzchn, zzvg2.zzadn, zza(str3, zzvg2));
                if (zzvg2.zzchi != null) {
                    bundle = zzvg2.zzchi.getBundle(mediationBannerAdapter.getClass().getName());
                }
                Bundle bundle2 = bundle;
                if (zzvn2.zzcia) {
                    zza = zzb.zza(zzvn2.width, zzvn2.height);
                } else {
                    zza = zzb.zza(zzvn2.width, zzvn2.height, zzvn2.zzacy);
                }
                mediationBannerAdapter.requestBannerAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzaoc(zzanh), zza(str3, zzvg2, str2), zza, zzany, bundle2);
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else {
            String canonicalName = MediationBannerAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final Bundle zztv() {
        Object obj = this.zzdlf;
        if (obj instanceof zzbiw) {
            return ((zzbiw) obj).zztv();
        }
        String canonicalName = zzbiw.class.getCanonicalName();
        String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
        sb.append(canonicalName);
        sb.append(" #009 Class mismatch: ");
        sb.append(canonicalName2);
        zzbbq.zzfe(sb.toString());
        return new Bundle();
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException {
        zza(iObjectWrapper, zzvg, str, (String) null, zzanh);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, String str2, zzanh zzanh) throws RemoteException {
        Date date;
        zzvg zzvg2 = zzvg;
        String str3 = str;
        if (this.zzdlf instanceof MediationInterstitialAdapter) {
            zzbbq.zzef("Requesting interstitial ad from adapter.");
            try {
                MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzdlf;
                Bundle bundle = null;
                HashSet hashSet = zzvg2.zzchd != null ? new HashSet(zzvg2.zzchd) : null;
                if (zzvg2.zzchb == -1) {
                    date = null;
                } else {
                    date = new Date(zzvg2.zzchb);
                }
                zzany zzany = new zzany(date, zzvg2.zzchc, hashSet, zzvg2.zznb, zzc(zzvg), zzvg2.zzadm, zzvg2.zzchn, zzvg2.zzadn, zza(str3, zzvg2));
                if (zzvg2.zzchi != null) {
                    bundle = zzvg2.zzchi.getBundle(mediationInterstitialAdapter.getClass().getName());
                }
                mediationInterstitialAdapter.requestInterstitialAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzaoc(zzanh), zza(str3, zzvg2, str2), zzany, bundle);
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else {
            String canonicalName = MediationInterstitialAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        Object obj = this.zzdlf;
        if (obj instanceof zzbiy) {
            return ((zzbiy) obj).getInterstitialAdapterInfo();
        }
        String canonicalName = zzbiy.class.getCanonicalName();
        String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
        sb.append(canonicalName);
        sb.append(" #009 Class mismatch: ");
        sb.append(canonicalName2);
        zzbbq.zzfe(sb.toString());
        return new Bundle();
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, String str2, zzanh zzanh, zzadu zzadu, List<String> list) throws RemoteException {
        Date date;
        zzvg zzvg2 = zzvg;
        String str3 = str;
        Object obj = this.zzdlf;
        if (obj instanceof MediationNativeAdapter) {
            try {
                MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) obj;
                Bundle bundle = null;
                HashSet hashSet = zzvg2.zzchd != null ? new HashSet(zzvg2.zzchd) : null;
                if (zzvg2.zzchb == -1) {
                    date = null;
                } else {
                    date = new Date(zzvg2.zzchb);
                }
                zzaog zzaog = new zzaog(date, zzvg2.zzchc, hashSet, zzvg2.zznb, zzc(zzvg), zzvg2.zzadm, zzadu, list, zzvg2.zzchn, zzvg2.zzadn, zza(str3, zzvg2));
                if (zzvg2.zzchi != null) {
                    bundle = zzvg2.zzchi.getBundle(mediationNativeAdapter.getClass().getName());
                }
                this.zzdlg = new zzaoc(zzanh);
                mediationNativeAdapter.requestNativeAd((Context) ObjectWrapper.unwrap(iObjectWrapper), this.zzdlg, zza(str3, zzvg2, str2), zzaog, bundle);
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else {
            String canonicalName = MediationNativeAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final Bundle zztw() {
        return new Bundle();
    }

    public final zzano zztt() {
        NativeAdMapper zzug = this.zzdlg.zzug();
        if (zzug instanceof NativeAppInstallAdMapper) {
            return new zzaoe((NativeAppInstallAdMapper) zzug);
        }
        return null;
    }

    public final zzanu zztz() {
        UnifiedNativeAdMapper zzuh = this.zzdlg.zzuh();
        if (zzuh != null) {
            return new zzaow(zzuh);
        }
        return null;
    }

    public final zzanp zztu() {
        NativeAdMapper zzug = this.zzdlg.zzug();
        if (zzug instanceof NativeContentAdMapper) {
            return new zzaod((NativeContentAdMapper) zzug);
        }
        return null;
    }

    public final zzafa zzty() {
        NativeCustomTemplateAd zzui = this.zzdlg.zzui();
        if (zzui instanceof zzafb) {
            return ((zzafb) zzui).zzst();
        }
        return null;
    }

    public final boolean zztx() {
        return this.zzdlf instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzaur zzaur, String str2) throws RemoteException {
        Bundle bundle;
        zzany zzany;
        Date date;
        zzvg zzvg2 = zzvg;
        zzaur zzaur2 = zzaur;
        String str3 = str2;
        Object obj = this.zzdlf;
        if (obj instanceof MediationRewardedVideoAdAdapter) {
            zzbbq.zzef("Initialize rewarded video adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzdlf;
                Bundle zza = zza(str3, zzvg2, (String) null);
                if (zzvg2 != null) {
                    HashSet hashSet = zzvg2.zzchd != null ? new HashSet(zzvg2.zzchd) : null;
                    if (zzvg2.zzchb == -1) {
                        date = null;
                    } else {
                        date = new Date(zzvg2.zzchb);
                    }
                    zzany zzany2 = new zzany(date, zzvg2.zzchc, hashSet, zzvg2.zznb, zzc(zzvg), zzvg2.zzadm, zzvg2.zzchn, zzvg2.zzadn, zza(str3, zzvg2));
                    bundle = zzvg2.zzchi != null ? zzvg2.zzchi.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null;
                    zzany = zzany2;
                } else {
                    zzany = null;
                    bundle = null;
                }
                mediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), zzany, str, new zzaus(zzaur2), zza, bundle);
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else if (obj instanceof Adapter) {
            this.zzdli = iObjectWrapper;
            this.zzdlh = zzaur2;
            zzaur2.zzaf(ObjectWrapper.wrap(obj));
        } else {
            String canonicalName = MediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = Adapter.class.getCanonicalName();
            String canonicalName3 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 26 + String.valueOf(canonicalName2).length() + String.valueOf(canonicalName3).length());
            sb.append(canonicalName);
            sb.append(" or ");
            sb.append(canonicalName2);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName3);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaur zzaur, List<String> list) throws RemoteException {
        if (this.zzdlf instanceof InitializableMediationRewardedVideoAdAdapter) {
            zzbbq.zzef("Initialize rewarded video adapter.");
            try {
                InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzdlf;
                ArrayList arrayList = new ArrayList();
                for (String zza : list) {
                    arrayList.add(zza(zza, (zzvg) null, (String) null));
                }
                initializableMediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzaus(zzaur), arrayList);
            } catch (Throwable th) {
                zzbbq.zzd("Could not initialize rewarded video adapter.", th);
                throw new RemoteException();
            }
        } else {
            String canonicalName = InitializableMediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void zza(zzvg zzvg, String str) throws RemoteException {
        zza(zzvg, str, (String) null);
    }

    public final void zza(zzvg zzvg, String str, String str2) throws RemoteException {
        Date date;
        zzvg zzvg2 = zzvg;
        String str3 = str;
        Object obj = this.zzdlf;
        if (obj instanceof MediationRewardedVideoAdAdapter) {
            zzbbq.zzef("Requesting rewarded video ad from adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzdlf;
                Bundle bundle = null;
                HashSet hashSet = zzvg2.zzchd != null ? new HashSet(zzvg2.zzchd) : null;
                if (zzvg2.zzchb == -1) {
                    date = null;
                } else {
                    date = new Date(zzvg2.zzchb);
                }
                zzany zzany = new zzany(date, zzvg2.zzchc, hashSet, zzvg2.zznb, zzc(zzvg), zzvg2.zzadm, zzvg2.zzchn, zzvg2.zzadn, zza(str3, zzvg2));
                if (zzvg2.zzchi != null) {
                    bundle = zzvg2.zzchi.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                }
                mediationRewardedVideoAdAdapter.loadAd(zzany, zza(str3, zzvg2, str2), bundle);
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else if (obj instanceof Adapter) {
            zzb(this.zzdli, zzvg2, str3, new zzaob((Adapter) this.zzdlf, this.zzdlh));
        } else {
            String canonicalName = MediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = Adapter.class.getCanonicalName();
            String canonicalName3 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 26 + String.valueOf(canonicalName2).length() + String.valueOf(canonicalName3).length());
            sb.append(canonicalName);
            sb.append(" or ");
            sb.append(canonicalName2);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName3);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void showVideo() throws RemoteException {
        Object obj = this.zzdlf;
        if (obj instanceof MediationRewardedVideoAdAdapter) {
            zzbbq.zzef("Show rewarded video ad from adapter.");
            try {
                ((MediationRewardedVideoAdAdapter) this.zzdlf).showVideo();
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else if (obj instanceof Adapter) {
            MediationRewardedAd mediationRewardedAd = this.zzdlj;
            if (mediationRewardedAd != null) {
                mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(this.zzdli));
            } else {
                zzbbq.zzfc("Can not show null mediated rewarded ad.");
                throw new RemoteException();
            }
        } else {
            String canonicalName = MediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = Adapter.class.getCanonicalName();
            String canonicalName3 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 26 + String.valueOf(canonicalName2).length() + String.valueOf(canonicalName3).length());
            sb.append(canonicalName);
            sb.append(" or ");
            sb.append(canonicalName2);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName3);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() throws RemoteException {
        Object obj = this.zzdlf;
        if (obj instanceof MediationRewardedVideoAdAdapter) {
            zzbbq.zzef("Check if adapter is initialized.");
            try {
                return ((MediationRewardedVideoAdAdapter) this.zzdlf).isInitialized();
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else if (obj instanceof Adapter) {
            return this.zzdlh != null;
        } else {
            String canonicalName = MediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = Adapter.class.getCanonicalName();
            String canonicalName3 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 26 + String.valueOf(canonicalName2).length() + String.valueOf(canonicalName3).length());
            sb.append(canonicalName);
            sb.append(" or ");
            sb.append(canonicalName2);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName3);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException {
        zzvg zzvg2 = zzvg;
        String str2 = str;
        if (this.zzdlf instanceof Adapter) {
            zzbbq.zzef("Requesting rewarded ad from adapter.");
            try {
                MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> zza = zza(zzanh);
                Bundle zza2 = zza(str2, zzvg2, (String) null);
                Bundle zzd = zzd(zzvg2);
                boolean zzc = zzc(zzvg);
                Location location = zzvg2.zznb;
                int i = zzvg2.zzadm;
                int i2 = zzvg2.zzadn;
                String zza3 = zza(str2, zzvg2);
                MediationRewardedAdConfiguration mediationRewardedAdConfiguration = r5;
                MediationRewardedAdConfiguration mediationRewardedAdConfiguration2 = new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zza2, zzd, zzc, location, i, i2, zza3, "");
                ((Adapter) this.zzdlf).loadRewardedAd(mediationRewardedAdConfiguration, zza);
            } catch (Exception e) {
                zzbbq.zzc("", e);
                throw new RemoteException();
            }
        } else {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException {
        zzvg zzvg2 = zzvg;
        String str2 = str;
        if (this.zzdlf instanceof Adapter) {
            zzbbq.zzef("Requesting rewarded interstitial ad from adapter.");
            try {
                MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> zza = zza(zzanh);
                Bundle zza2 = zza(str2, zzvg2, (String) null);
                Bundle zzd = zzd(zzvg2);
                boolean zzc = zzc(zzvg);
                Location location = zzvg2.zznb;
                int i = zzvg2.zzadm;
                int i2 = zzvg2.zzadn;
                String zza3 = zza(str2, zzvg2);
                MediationRewardedAdConfiguration mediationRewardedAdConfiguration = r5;
                MediationRewardedAdConfiguration mediationRewardedAdConfiguration2 = new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zza2, zzd, zzc, location, i, i2, zza3, "");
                ((Adapter) this.zzdlf).loadRewardedInterstitialAd(mediationRewardedAdConfiguration, zza);
            } catch (Exception e) {
                zzbbq.zzc("", e);
                throw new RemoteException();
            }
        } else {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void zzt(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzdlf instanceof Adapter) {
            zzbbq.zzef("Show rewarded ad from adapter.");
            MediationRewardedAd mediationRewardedAd = this.zzdlj;
            if (mediationRewardedAd != null) {
                mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
            } else {
                zzbbq.zzfc("Can not show null mediation rewarded ad.");
                throw new RemoteException();
            }
        } else {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        Object obj = this.zzdlf;
        if (!(obj instanceof OnImmersiveModeUpdatedListener)) {
            String canonicalName = OnImmersiveModeUpdatedListener.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzef(sb.toString());
            return;
        }
        try {
            ((OnImmersiveModeUpdatedListener) obj).onImmersiveModeUpdated(z);
        } catch (Throwable th) {
            zzbbq.zzc("", th);
        }
    }

    public final zzyo getVideoController() {
        Object obj = this.zzdlf;
        if (!(obj instanceof zza)) {
            return null;
        }
        try {
            return ((zza) obj).getVideoController();
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            return null;
        }
    }

    public final void showInterstitial() throws RemoteException {
        if (this.zzdlf instanceof MediationInterstitialAdapter) {
            zzbbq.zzef("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzdlf).showInterstitial();
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else {
            String canonicalName = MediationInterstitialAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzbbq.zzfe(sb.toString());
            throw new RemoteException();
        }
    }

    public final void destroy() throws RemoteException {
        Object obj = this.zzdlf;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onDestroy();
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        }
    }

    public final void pause() throws RemoteException {
        Object obj = this.zzdlf;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onPause();
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        }
    }

    public final void resume() throws RemoteException {
        Object obj = this.zzdlf;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onResume();
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        }
    }

    public final void zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        Object obj = this.zzdlf;
        if (obj instanceof OnContextChangedListener) {
            ((OnContextChangedListener) obj).onContextChanged(context);
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzais zzais, List<zzaja> list) throws RemoteException {
        AdFormat adFormat;
        if (this.zzdlf instanceof Adapter) {
            zzaoa zzaoa = new zzaoa(this, zzais);
            ArrayList arrayList = new ArrayList();
            for (zzaja next : list) {
                String str = next.zzdge;
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
                    throw new RemoteException();
                }
                arrayList.add(new MediationConfiguration(adFormat, next.extras));
            }
            ((Adapter) this.zzdlf).initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), zzaoa, arrayList);
            return;
        }
        throw new RemoteException();
    }

    public final zzapv zzua() {
        Object obj = this.zzdlf;
        if (!(obj instanceof Adapter)) {
            return null;
        }
        return zzapv.zza(((Adapter) obj).getVersionInfo());
    }

    public final zzapv zzub() {
        Object obj = this.zzdlf;
        if (!(obj instanceof Adapter)) {
            return null;
        }
        return zzapv.zza(((Adapter) obj).getSDKVersionInfo());
    }

    private final MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> zza(zzanh zzanh) {
        return new zzanz(this, zzanh);
    }

    private final Bundle zza(String str, zzvg zzvg, String str2) throws RemoteException {
        String valueOf = String.valueOf(str);
        zzbbq.zzef(valueOf.length() != 0 ? "Server parameters: ".concat(valueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.zzdlf instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzvg != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzvg.zzadm);
                }
            }
            bundle.remove("max_ad_content_rating");
            return bundle;
        } catch (Throwable th) {
            zzbbq.zzc("", th);
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
        if (zzvg.zzchi == null || (bundle = zzvg.zzchi.getBundle(this.zzdlf.getClass().getName())) == null) {
            return new Bundle();
        }
        return bundle;
    }
}
