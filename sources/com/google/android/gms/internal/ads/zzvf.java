package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvf extends RemoteCreator<zzxa> {
    public zzvf() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public final zzwz zza(Context context, String str, zzanb zzanb) {
        try {
            IBinder zzc = ((zzxa) getRemoteCreatorInstance(context)).zzc(ObjectWrapper.wrap(context), str, zzanb, 202510000);
            if (zzc == null) {
                return null;
            }
            IInterface queryLocalInterface = zzc.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            if (queryLocalInterface instanceof zzwz) {
                return (zzwz) queryLocalInterface;
            }
            return new zzxb(zzc);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzbbq.zzd("Could not create remote builder for AdLoader.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        if (queryLocalInterface instanceof zzxa) {
            return (zzxa) queryLocalInterface;
        }
        return new zzxd(iBinder);
    }
}
