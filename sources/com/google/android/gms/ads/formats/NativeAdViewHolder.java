package com.google.android.gms.ads.formats;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzael;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzwm;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class NativeAdViewHolder {
    public static WeakHashMap<View, NativeAdViewHolder> zzbnu = new WeakHashMap<>();
    private zzael zzbnt;
    private WeakReference<View> zzbnv;

    public NativeAdViewHolder(View view, Map<String, View> map, Map<String, View> map2) {
        Preconditions.checkNotNull(view, "ContainerView must not be null");
        if ((view instanceof NativeAdView) || (view instanceof UnifiedNativeAdView)) {
            zzbbq.zzfc("The provided containerView is of type of NativeAdView, which cannot be usedwith NativeAdViewHolder.");
        } else if (zzbnu.get(view) != null) {
            zzbbq.zzfc("The provided containerView is already in use with another NativeAdViewHolder.");
        } else {
            zzbnu.put(view, this);
            this.zzbnv = new WeakReference<>(view);
            this.zzbnt = zzwm.zzpu().zza(view, zzb(map), zzb(map2));
        }
    }

    private static HashMap<String, View> zzb(Map<String, View> map) {
        if (map == null) {
            return new HashMap<>();
        }
        return new HashMap<>(map);
    }

    public final void setNativeAd(NativeAd nativeAd) {
        zza((IObjectWrapper) nativeAd.zzjs());
    }

    public final void setNativeAd(UnifiedNativeAd unifiedNativeAd) {
        zza((IObjectWrapper) unifiedNativeAd.zzjs());
    }

    private final void zza(IObjectWrapper iObjectWrapper) {
        WeakReference<View> weakReference = this.zzbnv;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view == null) {
            zzbbq.zzfe("NativeAdViewHolder.setNativeAd containerView doesn't exist, returning");
            return;
        }
        if (!zzbnu.containsKey(view)) {
            zzbnu.put(view, this);
        }
        zzael zzael = this.zzbnt;
        if (zzael != null) {
            try {
                zzael.zza(iObjectWrapper);
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call setNativeAd on delegate", e);
            }
        }
    }

    public final void unregisterNativeAd() {
        zzael zzael = this.zzbnt;
        if (zzael != null) {
            try {
                zzael.unregisterNativeAd();
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call unregisterNativeAd on delegate", e);
            }
        }
        WeakReference<View> weakReference = this.zzbnv;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view != null) {
            zzbnu.remove(view);
        }
    }

    public final void setClickConfirmingView(View view) {
        try {
            this.zzbnt.zze(ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzbbq.zzc("Unable to call setClickConfirmingView on delegate", e);
        }
    }
}
