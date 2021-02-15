package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdaa {
    /* access modifiers changed from: private */
    public zzyn zzadt;
    /* access modifiers changed from: private */
    public boolean zzafc = false;
    private final String zzbuu;
    private final zzdae<zzbqo> zzgtp;

    public zzdaa(zzdae<zzbqo> zzdae, String str) {
        this.zzgtp = zzdae;
        this.zzbuu = str;
    }

    public final synchronized boolean isLoading() throws RemoteException {
        return this.zzgtp.isLoading();
    }

    public final synchronized void zza(zzvg zzvg, int i) throws RemoteException {
        this.zzadt = null;
        this.zzafc = this.zzgtp.zza(zzvg, this.zzbuu, new zzdaf(i), new zzczz(this));
    }

    public final synchronized String getMediationAdapterClassName() {
        try {
            if (this.zzadt == null) {
                return null;
            }
            return this.zzadt.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzayp.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final synchronized String zzkh() {
        try {
            if (this.zzadt == null) {
                return null;
            }
            return this.zzadt.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzayp.zze("#007 Could not call remote method.", e);
            return null;
        }
    }
}
