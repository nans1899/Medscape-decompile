package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zza;
import com.google.firebase.messaging.Constants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcfu {
    private final zzcil zzgbo;
    private final zzcjt zzgbx;
    private ViewTreeObserver.OnScrollChangedListener zzgcc = null;

    public zzcfu(zzcjt zzcjt, zzcil zzcil) {
        this.zzgbx = zzcjt;
        this.zzgbo = zzcil;
    }

    public final View zza(View view, WindowManager windowManager) throws zzbgv {
        zzbgj zzc = this.zzgbx.zzc(zzvn.zzpk());
        zzc.getView().setVisibility(4);
        zzc.getView().setContentDescription("policy_validator");
        zzc.zza("/sendMessageToSdk", (zzahq<? super zzbgj>) new zzcft(this));
        zzc.zza("/hideValidatorOverlay", (zzahq<? super zzbgj>) new zzcfw(this, windowManager, view));
        zzc.zza("/open", (zzahq<? super zzbgj>) new zzahu((zza) null, (zzaqd) null));
        this.zzgbo.zza(new WeakReference(zzc), "/loadNativeAdPolicyViolations", new zzcfv(this, view, windowManager));
        this.zzgbo.zza(new WeakReference(zzc), "/showValidatorOverlay", zzcfy.zzdfl);
        return zzc.getView();
    }

    private static int zza(Context context, String str, int i) {
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
        zzwm.zzpt();
        return zzbbg.zzc(context, i);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(View view, WindowManager windowManager, zzbgj zzbgj, Map map) {
        int i;
        zzbgj.zzabw().zza((zzbhu) new zzcga(this, map));
        if (map != null) {
            Context context = view.getContext();
            int zza = zza(context, (String) map.get("validator_width"), ((Integer) zzwm.zzpx().zzd(zzabb.zzcya)).intValue());
            int zza2 = zza(context, (String) map.get("validator_height"), ((Integer) zzwm.zzpx().zzd(zzabb.zzcyb)).intValue());
            int zza3 = zza(context, (String) map.get("validator_x"), 0);
            int zza4 = zza(context, (String) map.get("validator_y"), 0);
            zzbgj.zza(zzbhy.zzq(zza, zza2));
            try {
                zzbgj.getWebView().getSettings().setUseWideViewPort(((Boolean) zzwm.zzpx().zzd(zzabb.zzcyc)).booleanValue());
                zzbgj.getWebView().getSettings().setLoadWithOverviewMode(((Boolean) zzwm.zzpx().zzd(zzabb.zzcyd)).booleanValue());
            } catch (NullPointerException unused) {
            }
            WindowManager.LayoutParams zzyk = zzbau.zzyk();
            zzyk.x = zza3;
            zzyk.y = zza4;
            windowManager.updateViewLayout(zzbgj.getView(), zzyk);
            String str = (String) map.get("orientation");
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(str) || ExifInterface.GPS_MEASUREMENT_2D.equals(str)) {
                    i = rect.bottom;
                } else {
                    i = rect.top;
                }
                this.zzgcc = new zzcfx(view, zzbgj, str, zzyk, i - zza4, windowManager);
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnScrollChangedListener(this.zzgcc);
                }
            }
            String str2 = (String) map.get("overlay_url");
            if (!TextUtils.isEmpty(str2)) {
                zzbgj.loadUrl(str2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Map map, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE, "validatorHtmlLoaded");
        hashMap.put("id", (String) map.get("id"));
        this.zzgbo.zza("sendMessageToNativeJs", (Map<String, ?>) hashMap);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(WindowManager windowManager, View view, zzbgj zzbgj, Map map) {
        zzayp.zzef("Hide native ad policy validator overlay.");
        zzbgj.getView().setVisibility(8);
        if (zzbgj.getView().getWindowToken() != null) {
            windowManager.removeView(zzbgj.getView());
        }
        zzbgj.destroy();
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (this.zzgcc != null && viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this.zzgcc);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zze(zzbgj zzbgj, Map map) {
        this.zzgbo.zza("sendMessageToNativeJs", (Map<String, ?>) map);
    }
}
