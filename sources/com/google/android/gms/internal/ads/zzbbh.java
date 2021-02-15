package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbbh {
    private final View view;
    private boolean zzbrs;
    private boolean zzbvy;
    private Activity zzeea;
    private boolean zzeeb;
    private ViewTreeObserver.OnGlobalLayoutListener zzeec;
    private ViewTreeObserver.OnScrollChangedListener zzeed = null;

    public zzbbh(Activity activity, View view2, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        this.zzeea = activity;
        this.view = view2;
        this.zzeec = onGlobalLayoutListener;
    }

    public final void zzh(Activity activity) {
        this.zzeea = activity;
    }

    public final void zzyp() {
        this.zzbvy = true;
        if (this.zzbrs) {
            zzyr();
        }
    }

    public final void zzyq() {
        this.zzbvy = false;
        zzys();
    }

    public final void onAttachedToWindow() {
        this.zzbrs = true;
        if (this.zzbvy) {
            zzyr();
        }
    }

    public final void onDetachedFromWindow() {
        this.zzbrs = false;
        zzys();
    }

    private final void zzyr() {
        ViewTreeObserver zzi;
        if (!this.zzeeb) {
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzeec;
            if (onGlobalLayoutListener != null) {
                Activity activity = this.zzeea;
                if (!(activity == null || (zzi = zzi(activity)) == null)) {
                    zzi.addOnGlobalLayoutListener(onGlobalLayoutListener);
                }
                zzp.zzlo();
                zzbco.zza(this.view, this.zzeec);
            }
            this.zzeeb = true;
        }
    }

    private final void zzys() {
        ViewTreeObserver zzi;
        Activity activity = this.zzeea;
        if (activity != null && this.zzeeb) {
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzeec;
            if (!(onGlobalLayoutListener == null || (zzi = zzi(activity)) == null)) {
                zzp.zzkt();
                zzi.removeOnGlobalLayoutListener(onGlobalLayoutListener);
            }
            this.zzeeb = false;
        }
    }

    private static ViewTreeObserver zzi(Activity activity) {
        Window window;
        View decorView;
        if (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) {
            return null;
        }
        return decorView.getViewTreeObserver();
    }
}
