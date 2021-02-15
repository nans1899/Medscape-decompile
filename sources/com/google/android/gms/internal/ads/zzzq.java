package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzzq extends zzwx {
    final /* synthetic */ zzzo zzckv;

    private zzzq(zzzo zzzo) {
        this.zzckv = zzzo;
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final String zzkh() throws RemoteException {
        return null;
    }

    public final void zzb(zzvg zzvg) throws RemoteException {
        zza(zzvg, 1);
    }

    public final void zza(zzvg zzvg, int i) throws RemoteException {
        zzbbq.zzfc("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzbbg.zzaah.post(new zzzp(this));
    }
}
