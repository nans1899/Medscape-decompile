package com.google.android.gms.ads.formats;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzaei;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzwm;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class NativeAdView extends FrameLayout {
    private final FrameLayout zzbnw;
    private final zzaei zzbnx = zzju();

    public NativeAdView(Context context) {
        super(context);
        this.zzbnw = zzd(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzbnw = zzd(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzbnw = zzd(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zzbnw = zzd(context);
    }

    public void setAdChoicesView(AdChoicesView adChoicesView) {
        zza(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, adChoicesView);
    }

    public AdChoicesView getAdChoicesView() {
        View zzbn = zzbn(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW);
        if (zzbn instanceof AdChoicesView) {
            return (AdChoicesView) zzbn;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, View view) {
        try {
            this.zzbnx.zzb(str, ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzbbq.zzc("Unable to call setAssetView on delegate", e);
        }
    }

    /* access modifiers changed from: protected */
    public final View zzbn(String str) {
        try {
            IObjectWrapper zzcs = this.zzbnx.zzcs(str);
            if (zzcs != null) {
                return (View) ObjectWrapper.unwrap(zzcs);
            }
            return null;
        } catch (RemoteException e) {
            zzbbq.zzc("Unable to call getAssetView on delegate", e);
            return null;
        }
    }

    public void setNativeAd(NativeAd nativeAd) {
        try {
            this.zzbnx.zza((IObjectWrapper) nativeAd.zzjs());
        } catch (RemoteException e) {
            zzbbq.zzc("Unable to call setNativeAd on delegate", e);
        }
    }

    public void destroy() {
        try {
            this.zzbnx.destroy();
        } catch (RemoteException e) {
            zzbbq.zzc("Unable to destroy native ad view", e);
        }
    }

    private final FrameLayout zzd(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        return frameLayout;
    }

    private final zzaei zzju() {
        Preconditions.checkNotNull(this.zzbnw, "createDelegate must be called after mOverlayFrame has been created");
        if (isInEditMode()) {
            return null;
        }
        return zzwm.zzpu().zza(this.zzbnw.getContext(), (FrameLayout) this, this.zzbnw);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zzbnw);
    }

    public void removeView(View view) {
        if (this.zzbnw != view) {
            super.removeView(view);
        }
    }

    public void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzbnw);
    }

    public void bringChildToFront(View view) {
        super.bringChildToFront(view);
        FrameLayout frameLayout = this.zzbnw;
        if (frameLayout != view) {
            super.bringChildToFront(frameLayout);
        }
    }

    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        zzaei zzaei = this.zzbnx;
        if (zzaei != null) {
            try {
                zzaei.zzc(ObjectWrapper.wrap(view), i);
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call onVisibilityChanged on delegate", e);
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        zzaei zzaei;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcrm)).booleanValue() && (zzaei = this.zzbnx) != null) {
            try {
                zzaei.zzf(ObjectWrapper.wrap(motionEvent));
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call handleTouchEvent on delegate", e);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
