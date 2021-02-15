package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcik extends zzagd {
    private final String zzcib;
    private final zzcei zzfxu;
    private final zzcdx zzgaw;

    public zzcik(String str, zzcdx zzcdx, zzcei zzcei) {
        this.zzcib = str;
        this.zzgaw = zzcdx;
        this.zzfxu = zzcei;
    }

    public final IObjectWrapper zzsk() throws RemoteException {
        return ObjectWrapper.wrap(this.zzgaw);
    }

    public final String getHeadline() throws RemoteException {
        return this.zzfxu.getHeadline();
    }

    public final List<?> getImages() throws RemoteException {
        return this.zzfxu.getImages();
    }

    public final List<?> getMuteThisAdReasons() throws RemoteException {
        if (isCustomMuteThisAdEnabled()) {
            return this.zzfxu.getMuteThisAdReasons();
        }
        return Collections.emptyList();
    }

    public final boolean isCustomMuteThisAdEnabled() throws RemoteException {
        return !this.zzfxu.getMuteThisAdReasons().isEmpty() && this.zzfxu.zzamd() != null;
    }

    public final String getBody() throws RemoteException {
        return this.zzfxu.getBody();
    }

    public final zzaee zzsl() throws RemoteException {
        return this.zzfxu.zzsl();
    }

    public final String getCallToAction() throws RemoteException {
        return this.zzfxu.getCallToAction();
    }

    public final String getAdvertiser() throws RemoteException {
        return this.zzfxu.getAdvertiser();
    }

    public final double getStarRating() throws RemoteException {
        return this.zzfxu.getStarRating();
    }

    public final String getStore() throws RemoteException {
        return this.zzfxu.getStore();
    }

    public final String getPrice() throws RemoteException {
        return this.zzfxu.getPrice();
    }

    public final void destroy() throws RemoteException {
        this.zzgaw.destroy();
    }

    public final zzyo getVideoController() throws RemoteException {
        return this.zzfxu.getVideoController();
    }

    public final void performClick(Bundle bundle) throws RemoteException {
        this.zzgaw.zzg(bundle);
    }

    public final boolean recordImpression(Bundle bundle) throws RemoteException {
        return this.zzgaw.zzi(bundle);
    }

    public final void reportTouchEvent(Bundle bundle) throws RemoteException {
        this.zzgaw.zzh(bundle);
    }

    public final zzadw zzsm() throws RemoteException {
        return this.zzfxu.zzsm();
    }

    public final IObjectWrapper zzsn() throws RemoteException {
        return this.zzfxu.zzsn();
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return this.zzcib;
    }

    public final Bundle getExtras() throws RemoteException {
        return this.zzfxu.getExtras();
    }

    public final void zza(zzafz zzafz) throws RemoteException {
        this.zzgaw.zza(zzafz);
    }

    public final void zza(zzyd zzyd) throws RemoteException {
        this.zzgaw.zza(zzyd);
    }

    public final void zza(zzxz zzxz) throws RemoteException {
        this.zzgaw.zza(zzxz);
    }

    public final void zzsv() {
        this.zzgaw.zzsv();
    }

    public final void recordCustomClickGesture() {
        this.zzgaw.recordCustomClickGesture();
    }

    public final boolean isCustomClickGestureEnabled() {
        return this.zzgaw.isCustomClickGestureEnabled();
    }

    public final void cancelUnconfirmedClick() throws RemoteException {
        this.zzgaw.cancelUnconfirmedClick();
    }

    public final zzadz zzsw() throws RemoteException {
        return this.zzgaw.zzalx().zzsw();
    }

    public final zzyn zzki() throws RemoteException {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxl)).booleanValue()) {
            return null;
        }
        return this.zzgaw.zzaix();
    }

    public final void zza(zzyi zzyi) throws RemoteException {
        this.zzgaw.zza(zzyi);
    }
}
