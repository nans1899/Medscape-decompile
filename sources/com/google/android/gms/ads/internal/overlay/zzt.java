package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzayp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzt extends zze {
    public zzt(Activity activity) {
        super(activity);
    }

    public final void onCreate(Bundle bundle) {
        zzayp.zzei("AdOverlayParcel is null or does not contain valid overlay type.");
        this.zzdox = 3;
        this.zzaas.finish();
    }
}
