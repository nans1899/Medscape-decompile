package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzarr extends zzgt implements zzaro {
    public static zzaro zzal(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
        if (queryLocalInterface instanceof zzaro) {
            return (zzaro) queryLocalInterface;
        }
        return new zzarq(iBinder);
    }
}
