package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzavo {
    public static zzauy zzd(Context context, String str, zzanb zzanb) {
        try {
            IBinder zzd = ((zzave) zzbbt.zza(context, "com.google.android.gms.ads.rewarded.ChimeraRewardedAdCreatorImpl", zzavr.zzbxx)).zzd(ObjectWrapper.wrap(context), str, zzanb, 202510000);
            if (zzd == null) {
                return null;
            }
            IInterface queryLocalInterface = zzd.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
            if (queryLocalInterface instanceof zzauy) {
                return (zzauy) queryLocalInterface;
            }
            return new zzava(zzd);
        } catch (RemoteException | zzbbv e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }
}
