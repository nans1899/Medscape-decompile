package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaaa extends zzauc {
    /* access modifiers changed from: private */
    public zzaug zzclb;

    public final void destroy() throws RemoteException {
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    public final void pause() throws RemoteException {
    }

    public final void resume() throws RemoteException {
    }

    public final void setAppPackageName(String str) {
    }

    public final void setCustomData(String str) throws RemoteException {
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
    }

    public final void show() throws RemoteException {
    }

    public final void zza(zzaub zzaub) throws RemoteException {
    }

    public final void zza(zzxj zzxj) throws RemoteException {
    }

    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzk(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final zzyn zzki() {
        return null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final boolean zzra() {
        return false;
    }

    public final void zza(zzaum zzaum) throws RemoteException {
        zzbbq.zzfc("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzbbg.zzaah.post(new zzzz(this));
    }

    public final void zza(zzaug zzaug) throws RemoteException {
        this.zzclb = zzaug;
    }

    public final Bundle getAdMetadata() throws RemoteException {
        return new Bundle();
    }
}
