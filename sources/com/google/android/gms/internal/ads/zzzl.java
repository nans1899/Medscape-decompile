package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzzl extends RemoteCreator<zzxx> {
    public zzzl() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }

    public final zzxs zzi(Context context) {
        try {
            IBinder zzb = ((zzxx) getRemoteCreatorInstance(context)).zzb(ObjectWrapper.wrap(context), 202510000);
            if (zzb == null) {
                return null;
            }
            IInterface queryLocalInterface = zzb.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (queryLocalInterface instanceof zzxs) {
                return (zzxs) queryLocalInterface;
            }
            return new zzxu(zzb);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzbbq.zzd("Could not get remote MobileAdsSettingManager.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
        if (queryLocalInterface instanceof zzxx) {
            return (zzxx) queryLocalInterface;
        }
        return new zzxw(iBinder);
    }
}
