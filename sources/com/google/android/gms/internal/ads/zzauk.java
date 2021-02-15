package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzauk extends RemoteCreator<zzaue> {
    public zzauk() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    public final zzaud zzc(Context context, zzanb zzanb) {
        try {
            IBinder zzd = ((zzaue) getRemoteCreatorInstance(context)).zzd(ObjectWrapper.wrap(context), zzanb, 202510000);
            if (zzd == null) {
                return null;
            }
            IInterface queryLocalInterface = zzd.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
            if (queryLocalInterface instanceof zzaud) {
                return (zzaud) queryLocalInterface;
            }
            return new zzauf(zzd);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzbbq.zzd("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        if (queryLocalInterface instanceof zzaue) {
            return (zzaue) queryLocalInterface;
        }
        return new zzauh(iBinder);
    }
}
