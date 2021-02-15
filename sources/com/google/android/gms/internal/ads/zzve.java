package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzve extends RemoteCreator<zzxh> {
    public zzve() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public final zzxc zza(Context context, zzvn zzvn, String str, zzanb zzanb, int i) {
        try {
            IBinder zza = ((zzxh) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), zzvn, str, zzanb, 202510000, i);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzxc) {
                return (zzxc) queryLocalInterface;
            }
            return new zzxe(zza);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzbbq.zzb("Could not create remote AdManager.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        if (queryLocalInterface instanceof zzxh) {
            return (zzxh) queryLocalInterface;
        }
        return new zzxg(iBinder);
    }
}
