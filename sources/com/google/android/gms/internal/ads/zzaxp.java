package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzaxp extends zzgt implements zzaxm {
    public static zzaxm zzat(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.signals.ISignalGeneratorCreator");
        if (queryLocalInterface instanceof zzaxm) {
            return (zzaxm) queryLocalInterface;
        }
        return new zzaxo(iBinder);
    }
}
