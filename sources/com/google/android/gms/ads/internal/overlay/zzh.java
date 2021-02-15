package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.internal.ads.zzazp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzh extends RelativeLayout {
    private zzazp zzdpf;
    boolean zzdpg;

    public zzh(Context context, String str, String str2) {
        super(context);
        zzazp zzazp = new zzazp(context, str);
        this.zzdpf = zzazp;
        zzazp.zzae(str2);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.zzdpg) {
            return false;
        }
        this.zzdpf.zzd(motionEvent);
        return false;
    }
}
