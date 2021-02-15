package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcfc extends zzaeh implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzcgc {
    public static final String[] zzgba = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private FrameLayout zzbnw;
    private zzadz zzckt;
    private Map<String, WeakReference<View>> zzdqc = new HashMap();
    private final int zzeem;
    private boolean zzepb = false;
    private zzcdx zzgaw;
    private zzqq zzgax;
    private final String zzgaz;
    private FrameLayout zzgbb;
    private zzdzb zzgbc;
    private View zzgbd;
    private IObjectWrapper zzgbe = null;
    private boolean zzgbf;

    public zzcfc(FrameLayout frameLayout, FrameLayout frameLayout2, int i) {
        String str;
        this.zzgbb = frameLayout;
        this.zzbnw = frameLayout2;
        this.zzeem = i;
        String canonicalName = frameLayout.getClass().getCanonicalName();
        if ("com.google.android.gms.ads.formats.NativeContentAdView".equals(canonicalName)) {
            str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
        } else if ("com.google.android.gms.ads.formats.NativeAppInstallAdView".equals(canonicalName)) {
            str = NativeAppInstallAd.ASSET_ATTRIBUTION_ICON_IMAGE;
        } else {
            "com.google.android.gms.ads.formats.UnifiedNativeAdView".equals(canonicalName);
            str = "3012";
        }
        this.zzgaz = str;
        zzp.zzlo();
        zzbco.zza((View) frameLayout, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzp.zzlo();
        zzbco.zza((View) frameLayout, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzgbc = zzbbz.zzeet;
        this.zzgax = new zzqq(this.zzgbb.getContext(), this.zzgbb);
        frameLayout.setOnTouchListener(this);
        frameLayout.setOnClickListener(this);
    }

    public final synchronized void zzb(String str, IObjectWrapper iObjectWrapper) {
        zza(str, (View) ObjectWrapper.unwrap(iObjectWrapper), true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0040, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(java.lang.String r2, android.view.View r3, boolean r4) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r4 = r1.zzepb     // Catch:{ all -> 0x0041 }
            if (r4 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            if (r3 != 0) goto L_0x0010
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r3 = r1.zzdqc     // Catch:{ all -> 0x0041 }
            r3.remove(r2)     // Catch:{ all -> 0x0041 }
            monitor-exit(r1)
            return
        L_0x0010:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r1.zzdqc     // Catch:{ all -> 0x0041 }
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0041 }
            r0.<init>(r3)     // Catch:{ all -> 0x0041 }
            r4.put(r2, r0)     // Catch:{ all -> 0x0041 }
            java.lang.String r4 = "1098"
            boolean r4 = r4.equals(r2)     // Catch:{ all -> 0x0041 }
            if (r4 != 0) goto L_0x003f
            java.lang.String r4 = "3011"
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x0041 }
            if (r2 == 0) goto L_0x002b
            goto L_0x003f
        L_0x002b:
            int r2 = r1.zzeem     // Catch:{ all -> 0x0041 }
            boolean r2 = com.google.android.gms.internal.ads.zzbau.zzdi(r2)     // Catch:{ all -> 0x0041 }
            if (r2 == 0) goto L_0x0036
            r3.setOnTouchListener(r1)     // Catch:{ all -> 0x0041 }
        L_0x0036:
            r2 = 1
            r3.setClickable(r2)     // Catch:{ all -> 0x0041 }
            r3.setOnClickListener(r1)     // Catch:{ all -> 0x0041 }
            monitor-exit(r1)
            return
        L_0x003f:
            monitor-exit(r1)
            return
        L_0x0041:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcfc.zza(java.lang.String, android.view.View, boolean):void");
    }

    public final synchronized IObjectWrapper zzcs(String str) {
        return ObjectWrapper.wrap(zzgf(str));
    }

    public final synchronized View zzgf(String str) {
        if (this.zzepb) {
            return null;
        }
        WeakReference weakReference = this.zzdqc.get(str);
        if (weakReference == null) {
            return null;
        }
        return (View) weakReference.get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(com.google.android.gms.dynamic.IObjectWrapper r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.zzepb     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            java.lang.Object r2 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r2)     // Catch:{ all -> 0x0048 }
            boolean r0 = r2 instanceof com.google.android.gms.internal.ads.zzcdx     // Catch:{ all -> 0x0048 }
            if (r0 != 0) goto L_0x0016
            java.lang.String r2 = "Not an instance of native engine. This is most likely a transient error"
            com.google.android.gms.internal.ads.zzayp.zzfe(r2)     // Catch:{ all -> 0x0048 }
            monitor-exit(r1)
            return
        L_0x0016:
            com.google.android.gms.internal.ads.zzcdx r0 = r1.zzgaw     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x001f
            com.google.android.gms.internal.ads.zzcdx r0 = r1.zzgaw     // Catch:{ all -> 0x0048 }
            r0.zzb(r1)     // Catch:{ all -> 0x0048 }
        L_0x001f:
            r1.zzanh()     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzcdx r2 = (com.google.android.gms.internal.ads.zzcdx) r2     // Catch:{ all -> 0x0048 }
            r1.zzgaw = r2     // Catch:{ all -> 0x0048 }
            r2.zza((com.google.android.gms.internal.ads.zzcgc) r1)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzcdx r2 = r1.zzgaw     // Catch:{ all -> 0x0048 }
            android.widget.FrameLayout r0 = r1.zzgbb     // Catch:{ all -> 0x0048 }
            r2.zzaa(r0)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzcdx r2 = r1.zzgaw     // Catch:{ all -> 0x0048 }
            android.widget.FrameLayout r0 = r1.zzbnw     // Catch:{ all -> 0x0048 }
            r2.zzab(r0)     // Catch:{ all -> 0x0048 }
            boolean r2 = r1.zzgbf     // Catch:{ all -> 0x0048 }
            if (r2 == 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzcdx r2 = r1.zzgaw     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzced r2 = r2.zzalx()     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzadz r0 = r1.zzckt     // Catch:{ all -> 0x0048 }
            r2.zza(r0)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r1)
            return
        L_0x0048:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcfc.zza(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    private final synchronized void zzanh() {
        this.zzgbc.execute(new zzcfb(this));
    }

    public final synchronized void destroy() {
        if (!this.zzepb) {
            if (this.zzgaw != null) {
                this.zzgaw.zzb(this);
                this.zzgaw = null;
            }
            this.zzdqc.clear();
            this.zzgbb.removeAllViews();
            this.zzbnw.removeAllViews();
            this.zzdqc = null;
            this.zzgbb = null;
            this.zzbnw = null;
            this.zzgbd = null;
            this.zzgax = null;
            this.zzepb = true;
        }
    }

    public final synchronized void zzc(IObjectWrapper iObjectWrapper, int i) {
    }

    public final synchronized void onClick(View view) {
        if (this.zzgaw != null) {
            this.zzgaw.cancelUnconfirmedClick();
            this.zzgaw.zza(view, this.zzgbb, zzanc(), zzand(), false);
        }
    }

    public final synchronized boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzgaw != null) {
            this.zzgaw.zza(view, motionEvent, (View) this.zzgbb);
        }
        return false;
    }

    public final synchronized JSONObject zzsi() {
        if (this.zzgaw == null) {
            return null;
        }
        return this.zzgaw.zza((View) this.zzgbb, zzanc(), zzand());
    }

    public final synchronized void onGlobalLayout() {
        if (this.zzgaw != null) {
            this.zzgaw.zzb(this.zzgbb, zzanc(), zzand(), zzcdx.zzz(this.zzgbb));
        }
    }

    public final synchronized void onScrollChanged() {
        if (this.zzgaw != null) {
            this.zzgaw.zzb(this.zzgbb, zzanc(), zzand(), zzcdx.zzz(this.zzgbb));
        }
    }

    public final synchronized Map<String, WeakReference<View>> zzanc() {
        return this.zzdqc;
    }

    public final synchronized Map<String, WeakReference<View>> zzand() {
        return this.zzdqc;
    }

    public final synchronized Map<String, WeakReference<View>> zzane() {
        return null;
    }

    public final synchronized String zzanf() {
        return this.zzgaz;
    }

    public final FrameLayout zzana() {
        return this.zzbnw;
    }

    public final zzqq zzanb() {
        return this.zzgax;
    }

    public final synchronized void zze(IObjectWrapper iObjectWrapper) {
        this.zzgaw.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final synchronized void zzg(IObjectWrapper iObjectWrapper) {
        if (!this.zzepb) {
            this.zzgbe = iObjectWrapper;
        }
    }

    public final IObjectWrapper zzang() {
        return this.zzgbe;
    }

    public final void zzf(IObjectWrapper iObjectWrapper) {
        onTouch(this.zzgbb, (MotionEvent) ObjectWrapper.unwrap(iObjectWrapper));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(com.google.android.gms.internal.ads.zzadz r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.zzepb     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            r0 = 1
            r1.zzgbf = r0     // Catch:{ all -> 0x001b }
            r1.zzckt = r2     // Catch:{ all -> 0x001b }
            com.google.android.gms.internal.ads.zzcdx r0 = r1.zzgaw     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0019
            com.google.android.gms.internal.ads.zzcdx r0 = r1.zzgaw     // Catch:{ all -> 0x001b }
            com.google.android.gms.internal.ads.zzced r0 = r0.zzalx()     // Catch:{ all -> 0x001b }
            r0.zza(r2)     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r1)
            return
        L_0x001b:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcfc.zza(com.google.android.gms.internal.ads.zzadz):void");
    }

    public final /* synthetic */ View zzahx() {
        return this.zzgbb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzani() {
        if (this.zzgbd == null) {
            View view = new View(this.zzgbb.getContext());
            this.zzgbd = view;
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, 0));
        }
        if (this.zzgbb != this.zzgbd.getParent()) {
            this.zzgbb.addView(this.zzgbd);
        }
    }
}
