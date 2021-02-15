package com.google.android.gms.internal.ads;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcfx implements ViewTreeObserver.OnScrollChangedListener {
    private final String zzdjk;
    private final View zzgch;
    private final zzbgj zzgci;
    private final WindowManager.LayoutParams zzgcj;
    private final int zzgck;
    private final WindowManager zzgcl;

    zzcfx(View view, zzbgj zzbgj, String str, WindowManager.LayoutParams layoutParams, int i, WindowManager windowManager) {
        this.zzgch = view;
        this.zzgci = zzbgj;
        this.zzdjk = str;
        this.zzgcj = layoutParams;
        this.zzgck = i;
        this.zzgcl = windowManager;
    }

    public final void onScrollChanged() {
        View view = this.zzgch;
        zzbgj zzbgj = this.zzgci;
        String str = this.zzdjk;
        WindowManager.LayoutParams layoutParams = this.zzgcj;
        int i = this.zzgck;
        WindowManager windowManager = this.zzgcl;
        Rect rect = new Rect();
        if (view.getGlobalVisibleRect(rect) && zzbgj.getView().getWindowToken() != null) {
            if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(str) || ExifInterface.GPS_MEASUREMENT_2D.equals(str)) {
                layoutParams.y = rect.bottom - i;
            } else {
                layoutParams.y = rect.top - i;
            }
            windowManager.updateViewLayout(zzbgj.getView(), layoutParams);
        }
    }
}
