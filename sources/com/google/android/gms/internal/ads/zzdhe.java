package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import net.media.android.bidder.base.models.internal.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhe implements zzdgu<Bundle> {
    private final String zzdur;
    private final int zzdus;
    private final int zzdut;
    private final int zzduu;
    private final boolean zzduv;
    private final int zzduw;

    public zzdhe(String str, int i, int i2, int i3, boolean z, int i4) {
        this.zzdur = str;
        this.zzdus = i;
        this.zzdut = i2;
        this.zzduu = i3;
        this.zzduv = z;
        this.zzduw = i4;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        String str = this.zzdur;
        boolean z = true;
        zzdot.zza(bundle, "carrier", str, !TextUtils.isEmpty(str));
        Integer valueOf = Integer.valueOf(this.zzdus);
        if (this.zzdus == -2) {
            z = false;
        }
        zzdot.zza(bundle, "cnt", valueOf, z);
        bundle.putInt("gnt", this.zzdut);
        bundle.putInt(AdParameterKeys.PRIMARY_ID, this.zzduu);
        Bundle zza = zzdot.zza(bundle, "device");
        bundle.putBundle("device", zza);
        Bundle zza2 = zzdot.zza(zza, Logger.NETWORK);
        zza.putBundle(Logger.NETWORK, zza2);
        zza2.putInt("active_network_state", this.zzduw);
        zza2.putBoolean("active_network_metered", this.zzduv);
    }
}
