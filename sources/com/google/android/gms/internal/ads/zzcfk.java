package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcfk implements zzahq {
    private final zzcfh zzgbu;
    private final zzafz zzgbv;

    zzcfk(zzcfh zzcfh, zzafz zzafz) {
        this.zzgbu = zzcfh;
        this.zzgbv = zzafz;
    }

    public final void zza(Object obj, Map map) {
        zzcfh zzcfh = this.zzgbu;
        zzafz zzafz = this.zzgbv;
        try {
            zzcfh.zzgbs = Long.valueOf(Long.parseLong((String) map.get("timestamp")));
        } catch (NumberFormatException unused) {
            zzayp.zzfc("Failed to call parse unconfirmedClickTimestamp.");
        }
        zzcfh.zzgbr = (String) map.get("id");
        String str = (String) map.get(Constants.BUNDLE_KEY_ASSET_ID);
        if (zzafz == null) {
            zzayp.zzef("Received unconfirmed click but UnconfirmedClickListener is null.");
            return;
        }
        try {
            zzafz.onUnconfirmedClickReceived(str);
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }
}
