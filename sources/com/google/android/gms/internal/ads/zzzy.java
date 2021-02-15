package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzzy extends zzavb {
    public final String getMediationAdapterClassName() throws RemoteException {
        return "";
    }

    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    public final void zza(IObjectWrapper iObjectWrapper, boolean z) {
    }

    public final void zza(zzavd zzavd) throws RemoteException {
    }

    public final void zza(zzavl zzavl) throws RemoteException {
    }

    public final void zza(zzavt zzavt) {
    }

    public final void zza(zzyh zzyh) throws RemoteException {
    }

    public final void zza(zzyi zzyi) {
    }

    public final void zzh(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final zzyn zzki() {
        return null;
    }

    public final zzaux zzqz() {
        return null;
    }

    public final void zza(zzvg zzvg, zzavg zzavg) throws RemoteException {
        zza(zzavg);
    }

    public final void zzb(zzvg zzvg, zzavg zzavg) throws RemoteException {
        zza(zzavg);
    }

    public final Bundle getAdMetadata() throws RemoteException {
        return new Bundle();
    }

    private static void zza(zzavg zzavg) {
        zzbbq.zzfc("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzbbg.zzaah.post(new zzzx(zzavg));
    }
}
