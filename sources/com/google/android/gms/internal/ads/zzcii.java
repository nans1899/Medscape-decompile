package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcii implements zzads {
    private final /* synthetic */ zzcif zzgef;

    zzcii(zzcif zzcif) {
        this.zzgef = zzcif;
    }

    public final void zzc(MotionEvent motionEvent) {
    }

    public final JSONObject zzsi() {
        return null;
    }

    public final void zzsh() {
        if (this.zzgef.zzgaw != null) {
            this.zzgef.zzgaw.zzfz(NativeCustomTemplateAd.ASSET_NAME_VIDEO);
        }
    }
}
