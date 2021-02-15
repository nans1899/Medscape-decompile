package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaof<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzanf {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzdlw;
    private final NETWORK_EXTRAS zzdlx;

    public zzaof(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zzdlw = mediationAdapter;
        this.zzdlx = network_extras;
    }

    public final zzyo getVideoController() {
        return null;
    }

    public final boolean isInitialized() {
        return true;
    }

    public final void setImmersiveMode(boolean z) {
    }

    public final void showVideo() {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzais zzais, List<zzaja> list) throws RemoteException {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaur zzaur, List<String> list) {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzaur zzaur, String str2) throws RemoteException {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, String str2, zzanh zzanh, zzadu zzadu, List<String> list) {
    }

    public final void zza(zzvg zzvg, String str) {
    }

    public final void zza(zzvg zzvg, String str, String str2) {
    }

    public final void zzb(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException {
    }

    public final void zzc(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException {
    }

    public final void zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzt(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final zzano zztt() {
        return null;
    }

    public final zzanp zztu() {
        return null;
    }

    public final boolean zztx() {
        return false;
    }

    public final zzafa zzty() {
        return null;
    }

    public final zzanu zztz() {
        return null;
    }

    public final zzapv zzua() {
        return null;
    }

    public final zzapv zzub() {
        return null;
    }

    public final IObjectWrapper zzts() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzdlw;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzbbq.zzfe(valueOf.length() != 0 ? "Not a MediationBannerAdapter: ".concat(valueOf) : new String("Not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap(((MediationBannerAdapter) mediationAdapter).getBannerView());
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvn zzvn, zzvg zzvg, String str, zzanh zzanh) throws RemoteException {
        zza(iObjectWrapper, zzvn, zzvg, str, (String) null, zzanh);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvn zzvn, zzvg zzvg, String str, String str2, zzanh zzanh) throws RemoteException {
        AdSize adSize;
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzdlw;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzbbq.zzfe(valueOf.length() != 0 ? "Not a MediationBannerAdapter: ".concat(valueOf) : new String("Not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzbbq.zzef("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzdlw;
            zzaoi zzaoi = new zzaoi(zzanh);
            Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
            MediationServerParameters zzdp = zzdp(str);
            int i = 0;
            AdSize[] adSizeArr = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
            while (true) {
                if (i < 6) {
                    if (adSizeArr[i].getWidth() == zzvn.width && adSizeArr[i].getHeight() == zzvn.height) {
                        adSize = adSizeArr[i];
                        break;
                    }
                    i++;
                } else {
                    adSize = new AdSize(zzb.zza(zzvn.width, zzvn.height, zzvn.zzacy));
                    break;
                }
            }
            mediationBannerAdapter.requestBannerAd(zzaoi, activity, zzdp, adSize, zzaou.zza(zzvg, zzc(zzvg)), this.zzdlx);
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            throw new RemoteException();
        }
    }

    public final Bundle zztv() {
        return new Bundle();
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException {
        zza(iObjectWrapper, zzvg, str, (String) null, zzanh);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, String str2, zzanh zzanh) throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzdlw;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzbbq.zzfe(valueOf.length() != 0 ? "Not a MediationInterstitialAdapter: ".concat(valueOf) : new String("Not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzbbq.zzef("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzdlw).requestInterstitialAd(new zzaoi(zzanh), (Activity) ObjectWrapper.unwrap(iObjectWrapper), zzdp(str), zzaou.zza(zzvg, zzc(zzvg)), this.zzdlx);
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public final void showInterstitial() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzdlw;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzbbq.zzfe(valueOf.length() != 0 ? "Not a MediationInterstitialAdapter: ".concat(valueOf) : new String("Not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzbbq.zzef("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzdlw).showInterstitial();
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            throw new RemoteException();
        }
    }

    public final Bundle zztw() {
        return new Bundle();
    }

    public final void destroy() throws RemoteException {
        try {
            this.zzdlw.destroy();
        } catch (Throwable th) {
            zzbbq.zzc("", th);
            throw new RemoteException();
        }
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void resume() throws RemoteException {
        throw new RemoteException();
    }

    private final SERVER_PARAMETERS zzdp(String str) throws RemoteException {
        HashMap hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            } catch (Throwable th) {
                zzbbq.zzc("", th);
                throw new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class<SERVER_PARAMETERS> serverParametersType = this.zzdlw.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        SERVER_PARAMETERS server_parameters = (MediationServerParameters) serverParametersType.newInstance();
        server_parameters.load(hashMap);
        return server_parameters;
    }

    private static boolean zzc(zzvg zzvg) {
        if (zzvg.zzche) {
            return true;
        }
        zzwm.zzpt();
        return zzbbg.zzym();
    }
}
