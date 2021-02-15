package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaqz extends RemoteCreator<zzard> {
    public zzaqz() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public final zzaqy zzc(Activity activity) {
        try {
            IBinder zzae = ((zzard) getRemoteCreatorInstance(activity)).zzae(ObjectWrapper.wrap(activity));
            if (zzae == null) {
                return null;
            }
            IInterface queryLocalInterface = zzae.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            if (queryLocalInterface instanceof zzaqy) {
                return (zzaqy) queryLocalInterface;
            }
            return new zzara(zzae);
        } catch (RemoteException e) {
            zzbbq.zzd("Could not create remote AdOverlay.", e);
            return null;
        } catch (RemoteCreator.RemoteCreatorException e2) {
            zzbbq.zzd("Could not create remote AdOverlay.", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
        if (queryLocalInterface instanceof zzard) {
            return (zzard) queryLocalInterface;
        }
        return new zzarc(iBinder);
    }
}
