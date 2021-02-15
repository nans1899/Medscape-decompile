package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdll {
    public static <T> void zza(AtomicReference<T> atomicReference, zzdlk<T> zzdlk) {
        T t = atomicReference.get();
        if (t != null) {
            try {
                zzdlk.zzq(t);
            } catch (RemoteException e) {
                zzayp.zze("#007 Could not call remote method.", e);
            }
        }
    }
}
