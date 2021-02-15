package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcib extends zzaer {
    private final String zzcib;
    private final zzcei zzfxu;
    private final zzcdx zzgaw;

    public zzcib(String str, zzcdx zzcdx, zzcei zzcei) {
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

    public final String getBody() throws RemoteException {
        return this.zzfxu.getBody();
    }

    public final zzaee zzsl() throws RemoteException {
        return this.zzfxu.zzsl();
    }

    public final String getCallToAction() throws RemoteException {
        return this.zzfxu.getCallToAction();
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

    public final Bundle getExtras() throws RemoteException {
        return this.zzfxu.getExtras();
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
}
