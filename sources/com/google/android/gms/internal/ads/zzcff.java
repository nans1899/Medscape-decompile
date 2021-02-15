package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcff implements zzads {
    private final /* synthetic */ zzcgc zzgbk;
    private final /* synthetic */ ViewGroup zzgbl;
    private final /* synthetic */ zzcfe zzgbm;

    zzcff(zzcfe zzcfe, zzcgc zzcgc, ViewGroup viewGroup) {
        this.zzgbm = zzcfe;
        this.zzgbk = zzcgc;
        this.zzgbl = viewGroup;
    }

    public final void zzsh() {
        if (zzcfe.zza(this.zzgbk, zzcfc.zzgba)) {
            this.zzgbk.onClick(this.zzgbl);
        }
    }

    public final void zzc(MotionEvent motionEvent) {
        this.zzgbk.onTouch((View) null, motionEvent);
    }

    public final JSONObject zzsi() {
        return this.zzgbk.zzsi();
    }
}
