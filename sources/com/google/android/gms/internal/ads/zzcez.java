package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcez extends zzaeo implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzcgc {
    private final WeakReference<View> zzgas;
    private final Map<String, WeakReference<View>> zzgat = new HashMap();
    private final Map<String, WeakReference<View>> zzgau = new HashMap();
    private final Map<String, WeakReference<View>> zzgav = new HashMap();
    private zzcdx zzgaw;
    private zzqq zzgax;

    public zzcez(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        zzp.zzlo();
        zzbco.zza(view, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzp.zzlo();
        zzbco.zza(view, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzgas = new WeakReference<>(view);
        for (Map.Entry next : hashMap.entrySet()) {
            String str = (String) next.getKey();
            View view2 = (View) next.getValue();
            if (view2 != null) {
                this.zzgat.put(str, new WeakReference(view2));
                if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str) && !UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                    view2.setOnTouchListener(this);
                    view2.setClickable(true);
                    view2.setOnClickListener(this);
                }
            }
        }
        this.zzgav.putAll(this.zzgat);
        for (Map.Entry next2 : hashMap2.entrySet()) {
            View view3 = (View) next2.getValue();
            if (view3 != null) {
                this.zzgau.put((String) next2.getKey(), new WeakReference(view3));
                view3.setOnTouchListener(this);
                view3.setClickable(false);
            }
        }
        this.zzgav.putAll(this.zzgau);
        this.zzgax = new zzqq(view.getContext(), view);
    }

    public final FrameLayout zzana() {
        return null;
    }

    public final synchronized void unregisterNativeAd() {
        if (this.zzgaw != null) {
            this.zzgaw.zzb(this);
            this.zzgaw = null;
        }
    }

    public final synchronized void zza(IObjectWrapper iObjectWrapper) {
        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (!(unwrap instanceof zzcdx)) {
            zzayp.zzfe("Not an instance of InternalNativeAd. This is most likely a transient error");
            return;
        }
        if (this.zzgaw != null) {
            this.zzgaw.zzb(this);
        }
        if (((zzcdx) unwrap).zzalv()) {
            zzcdx zzcdx = (zzcdx) unwrap;
            this.zzgaw = zzcdx;
            zzcdx.zza((zzcgc) this);
            this.zzgaw.zzaa(zzahx());
            return;
        }
        zzayp.zzfc("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
    }

    public final View zzahx() {
        return (View) this.zzgas.get();
    }

    public final zzqq zzanb() {
        return this.zzgax;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(java.lang.String r2, android.view.View r3, boolean r4) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r3 != 0) goto L_0x0014
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r1.zzgav     // Catch:{ all -> 0x0047 }
            r3.remove(r2)     // Catch:{ all -> 0x0047 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r1.zzgat     // Catch:{ all -> 0x0047 }
            r3.remove(r2)     // Catch:{ all -> 0x0047 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r1.zzgau     // Catch:{ all -> 0x0047 }
            r3.remove(r2)     // Catch:{ all -> 0x0047 }
            monitor-exit(r1)
            return
        L_0x0014:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r1.zzgav     // Catch:{ all -> 0x0047 }
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0047 }
            r0.<init>(r3)     // Catch:{ all -> 0x0047 }
            r4.put(r2, r0)     // Catch:{ all -> 0x0047 }
            java.lang.String r4 = "1098"
            boolean r4 = r4.equals(r2)     // Catch:{ all -> 0x0047 }
            if (r4 != 0) goto L_0x0045
            java.lang.String r4 = "3011"
            boolean r4 = r4.equals(r2)     // Catch:{ all -> 0x0047 }
            if (r4 == 0) goto L_0x002f
            goto L_0x0045
        L_0x002f:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r1.zzgat     // Catch:{ all -> 0x0047 }
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0047 }
            r0.<init>(r3)     // Catch:{ all -> 0x0047 }
            r4.put(r2, r0)     // Catch:{ all -> 0x0047 }
            r2 = 1
            r3.setClickable(r2)     // Catch:{ all -> 0x0047 }
            r3.setOnClickListener(r1)     // Catch:{ all -> 0x0047 }
            r3.setOnTouchListener(r1)     // Catch:{ all -> 0x0047 }
            monitor-exit(r1)
            return
        L_0x0045:
            monitor-exit(r1)
            return
        L_0x0047:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcez.zza(java.lang.String, android.view.View, boolean):void");
    }

    public final synchronized Map<String, WeakReference<View>> zzanc() {
        return this.zzgav;
    }

    public final synchronized Map<String, WeakReference<View>> zzand() {
        return this.zzgat;
    }

    public final synchronized Map<String, WeakReference<View>> zzane() {
        return this.zzgau;
    }

    public final synchronized View zzgf(String str) {
        WeakReference weakReference = this.zzgav.get(str);
        if (weakReference == null) {
            return null;
        }
        return (View) weakReference.get();
    }

    public final synchronized String zzanf() {
        return NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
    }

    public final synchronized IObjectWrapper zzang() {
        return null;
    }

    public final synchronized boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzgaw != null) {
            this.zzgaw.zza(view, motionEvent, zzahx());
        }
        return false;
    }

    public final synchronized void onClick(View view) {
        if (this.zzgaw != null) {
            this.zzgaw.zza(view, zzahx(), zzanc(), zzand(), true);
        }
    }

    public final synchronized void onGlobalLayout() {
        if (this.zzgaw != null) {
            this.zzgaw.zzb(zzahx(), zzanc(), zzand(), zzcdx.zzz(zzahx()));
        }
    }

    public final synchronized void onScrollChanged() {
        if (this.zzgaw != null) {
            this.zzgaw.zzb(zzahx(), zzanc(), zzand(), zzcdx.zzz(zzahx()));
        }
    }

    public final synchronized void zze(IObjectWrapper iObjectWrapper) {
        if (this.zzgaw != null) {
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (!(unwrap instanceof View)) {
                zzayp.zzfe("Calling NativeAdViewHolderNonagonDelegate.setClickConfirmingView with wrong wrapped object");
            }
            this.zzgaw.setClickConfirmingView((View) unwrap);
        }
    }

    public final synchronized JSONObject zzsi() {
        return null;
    }
}
