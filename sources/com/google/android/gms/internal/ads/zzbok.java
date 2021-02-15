package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbok extends zzsf {
    private final zzxc zzbut;
    private final zzboh zzfpu;
    private final zzdkc zzfpv;

    public zzbok(zzboh zzboh, zzxc zzxc, zzdkc zzdkc) {
        this.zzfpu = zzboh;
        this.zzbut = zzxc;
        this.zzfpv = zzdkc;
    }

    public final void zza(zzsm zzsm) {
    }

    public final zzxc zzdw() {
        return this.zzbut;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzsq zzsq) {
        try {
            this.zzfpv.zza(zzsq);
            this.zzfpu.zza((Activity) ObjectWrapper.unwrap(iObjectWrapper), zzsq);
        } catch (RemoteException e) {
            zzayp.zze("#007 Could not call remote method.", e);
        }
    }

    public final zzyn zzki() {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxl)).booleanValue()) {
            return null;
        }
        return this.zzfpu.zzaix();
    }
}
