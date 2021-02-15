package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcn implements zzdgu<Bundle> {
    private final zzbbx zzdqm;
    private final zzvs zzgvr;

    public zzdcn(zzvs zzvs, zzbbx zzbbx) {
        this.zzgvr = zzvs;
        this.zzdqm = zzbbx;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        int intValue = ((Integer) zzwm.zzpx().zzd(zzabb.zzcul)).intValue();
        zzbbx zzbbx = this.zzdqm;
        if (zzbbx != null && zzbbx.zzeem >= intValue) {
            bundle.putString("app_open_version", ExifInterface.GPS_MEASUREMENT_2D);
        }
        zzvs zzvs = this.zzgvr;
        if (zzvs == null) {
            return;
        }
        if (zzvs.orientation == 1) {
            bundle.putString("avo", "p");
        } else if (this.zzgvr.orientation == 2) {
            bundle.putString("avo", "l");
        }
    }
}
