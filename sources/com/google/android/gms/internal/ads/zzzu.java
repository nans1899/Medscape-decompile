package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzzu extends zzxv {
    private zzait zzcky;

    public final String getVersionString() {
        return "";
    }

    public final void setAppMuted(boolean z) throws RemoteException {
    }

    public final void setAppVolume(float f) throws RemoteException {
    }

    public final void zza(zzaae zzaae) throws RemoteException {
    }

    public final void zza(zzanb zzanb) throws RemoteException {
    }

    public final void zza(String str, IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
    }

    public final void zzch(String str) throws RemoteException {
    }

    public final void zzci(String str) throws RemoteException {
    }

    public final float zzqg() throws RemoteException {
        return 1.0f;
    }

    public final boolean zzqh() throws RemoteException {
        return false;
    }

    public final void zzqj() {
    }

    public final void initialize() throws RemoteException {
        zzbbq.zzfc("The initialization is not processed because MobileAdsSettingsManager is not created successfully.");
        zzbbg.zzaah.post(new zzzt(this));
    }

    public final void zza(zzait zzait) throws RemoteException {
        this.zzcky = zzait;
    }

    public final List<zzaiq> zzqi() throws RemoteException {
        return Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzqy() {
        zzait zzait = this.zzcky;
        if (zzait != null) {
            try {
                zzait.zze(Collections.emptyList());
            } catch (RemoteException e) {
                zzbbq.zzd("Could not notify onComplete event.", e);
            }
        }
    }
}
