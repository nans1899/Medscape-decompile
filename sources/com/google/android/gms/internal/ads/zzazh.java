package com.google.android.gms.internal.ads;

import android.os.Environment;
import android.os.StatFs;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzazh extends zzaze {
    public final int zzxw() {
        return 14;
    }

    public boolean isAttachedToWindow(View view) {
        return super.isAttachedToWindow(view) || view.getWindowId() != null;
    }

    public final long zzya() {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcrq)).booleanValue()) {
            return -1;
        }
        return new StatFs(Environment.getDataDirectory().getAbsolutePath()).getAvailableBytes() / 1024;
    }
}
