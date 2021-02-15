package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcxp extends zzauq {
    private final /* synthetic */ zzbwa zzgqs;
    private final /* synthetic */ zzbuc zzgqt;
    private final /* synthetic */ zzbvh zzgqu;
    private final /* synthetic */ zzcal zzgqv;

    zzcxp(zzcxl zzcxl, zzbwa zzbwa, zzbuc zzbuc, zzbvh zzbvh, zzcal zzcal) {
        this.zzgqs = zzbwa;
        this.zzgqt = zzbuc;
        this.zzgqu = zzbvh;
        this.zzgqv = zzcal;
    }

    public final void zzaf(IObjectWrapper iObjectWrapper) {
    }

    public final void zzag(IObjectWrapper iObjectWrapper) {
    }

    public final void zzb(Bundle bundle) {
    }

    public final void zzd(IObjectWrapper iObjectWrapper, int i) {
    }

    public final void zze(IObjectWrapper iObjectWrapper, int i) {
    }

    public final void zzah(IObjectWrapper iObjectWrapper) {
        this.zzgqs.zzun();
    }

    public final void zzaj(IObjectWrapper iObjectWrapper) {
        this.zzgqs.zzum();
    }

    public final void zzak(IObjectWrapper iObjectWrapper) {
        this.zzgqt.onAdClicked();
    }

    public final void zzal(IObjectWrapper iObjectWrapper) {
        this.zzgqu.onAdLeftApplication();
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzauv zzauv) {
        this.zzgqv.zza(zzauv);
    }

    public final void zzai(IObjectWrapper iObjectWrapper) {
        this.zzgqv.zzta();
    }

    public final void zzam(IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzgqu.onRewardedVideoCompleted();
    }
}
