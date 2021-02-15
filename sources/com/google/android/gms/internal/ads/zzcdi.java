package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdi implements zzceq {
    private final zzbbx zzbpe;
    private final Clock zzbqa;
    private final zzeg zzeom;
    private final zzdnv zzeot;
    private final zzdst zzfnf;
    private final zzcep zzfqc;
    private final zzdok zzfsk;
    private final JSONObject zzfxs;
    private final zzcil zzfxt;
    private final zzcei zzfxu;
    /* access modifiers changed from: private */
    public final zzbuu zzfxv;
    /* access modifiers changed from: private */
    public final zzbuc zzfxw;
    private final zzbmu zzfxx;
    private final zzcfh zzfxy;
    private final zzcae zzfxz;
    private boolean zzfya = false;
    private boolean zzfyb;
    private boolean zzfyc = false;
    private boolean zzfyd = false;
    private Point zzfye = new Point();
    private Point zzfyf = new Point();
    private long zzfyg = 0;
    private long zzfyh = 0;
    private zzxz zzfyi;
    private final Context zzvr;

    public zzcdi(Context context, zzcep zzcep, JSONObject jSONObject, zzcil zzcil, zzcei zzcei, zzeg zzeg, zzbuu zzbuu, zzbuc zzbuc, zzdnv zzdnv, zzbbx zzbbx, zzdok zzdok, zzbmu zzbmu, zzcfh zzcfh, Clock clock, zzcae zzcae, zzdst zzdst) {
        this.zzvr = context;
        this.zzfqc = zzcep;
        this.zzfxs = jSONObject;
        this.zzfxt = zzcil;
        this.zzfxu = zzcei;
        this.zzeom = zzeg;
        this.zzfxv = zzbuu;
        this.zzfxw = zzbuc;
        this.zzeot = zzdnv;
        this.zzbpe = zzbbx;
        this.zzfsk = zzdok;
        this.zzfxx = zzbmu;
        this.zzfxy = zzcfh;
        this.zzbqa = clock;
        this.zzfxz = zzcae;
        this.zzfnf = zzdst;
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        this.zzfye = new Point();
        this.zzfyf = new Point();
        if (!this.zzfyb) {
            this.zzfxz.zzv(view);
            this.zzfyb = true;
        }
        view.setOnTouchListener(onTouchListener);
        view.setClickable(true);
        view.setOnClickListener(onClickListener);
        this.zzfxx.zzo(this);
        boolean zzdi = zzbau.zzdi(this.zzbpe.zzeem);
        if (map != null) {
            for (Map.Entry<String, WeakReference<View>> value : map.entrySet()) {
                View view2 = (View) ((WeakReference) value.getValue()).get();
                if (view2 != null) {
                    if (zzdi) {
                        view2.setOnTouchListener(onTouchListener);
                    }
                    view2.setClickable(true);
                    view2.setOnClickListener(onClickListener);
                }
            }
        }
        if (map2 != null) {
            for (Map.Entry<String, WeakReference<View>> value2 : map2.entrySet()) {
                View view3 = (View) ((WeakReference) value2.getValue()).get();
                if (view3 != null) {
                    if (zzdi) {
                        view3.setOnTouchListener(onTouchListener);
                    }
                    view3.setClickable(false);
                }
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map) {
        this.zzfye = new Point();
        this.zzfyf = new Point();
        this.zzfxz.zzw(view);
        this.zzfyb = false;
    }

    private final boolean zzfy(String str) {
        JSONObject optJSONObject = this.zzfxs.optJSONObject("allow_pub_event_reporting");
        if (optJSONObject == null || !optJSONObject.optBoolean(str, false)) {
            return false;
        }
        return true;
    }

    public final void zza(View view, View view2, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, boolean z) {
        View view3 = view2;
        Map<String, WeakReference<View>> map3 = map;
        JSONObject zza = zzbau.zza(this.zzvr, map, map2, view2);
        JSONObject zza2 = zzbau.zza(this.zzvr, view2);
        JSONObject zzt = zzbau.zzt(view2);
        JSONObject zzb = zzbau.zzb(this.zzvr, view2);
        View view4 = view;
        String zzb2 = zzb(view, map);
        zza(((Boolean) zzwm.zzpx().zzd(zzabb.zzcrp)).booleanValue() ? view3 : view4, zza2, zza, zzt, zzb, zzb2, zzbau.zza(zzb2, this.zzvr, this.zzfyf, this.zzfye), (JSONObject) null, z, false);
    }

    private final String zzb(View view, Map<String, WeakReference<View>> map) {
        if (!(map == null || view == null)) {
            for (Map.Entry next : map.entrySet()) {
                if (view.equals((View) ((WeakReference) next.getValue()).get())) {
                    return (String) next.getKey();
                }
            }
        }
        int zzama = this.zzfxu.zzama();
        if (zzama == 1) {
            return "1099";
        }
        if (zzama == 2) {
            return "2099";
        }
        if (zzama != 6) {
            return null;
        }
        return "3099";
    }

    public final void zzfz(String str) {
        zza((View) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, str, (JSONObject) null, (JSONObject) null, false, false);
    }

    public final void zzg(Bundle bundle) {
        if (bundle == null) {
            zzayp.zzef("Click data is null. No click is reported.");
        } else if (!zzfy("click_reporting")) {
            zzayp.zzfc("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        } else {
            Bundle bundle2 = bundle.getBundle("click_signal");
            zza((View) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, bundle2 != null ? bundle2.getString(Constants.BUNDLE_KEY_ASSET_ID) : null, (JSONObject) null, zzp.zzkr().zza(bundle, (JSONObject) null), false, false);
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, boolean z) {
        View view2 = view;
        Map<String, WeakReference<View>> map3 = map;
        if (!this.zzfyd) {
            zzayp.zzef("Custom click reporting failed. enableCustomClickGesture is not set.");
        } else if (!zzalo()) {
            zzayp.zzef("Custom click reporting failed. Ad unit id not whitelisted.");
        } else {
            Map<String, WeakReference<View>> map4 = map2;
            JSONObject zza = zzbau.zza(this.zzvr, map, map2, view);
            JSONObject zza2 = zzbau.zza(this.zzvr, view);
            JSONObject zzt = zzbau.zzt(view);
            JSONObject zzb = zzbau.zzb(this.zzvr, view);
            String zzb2 = zzb((View) null, map);
            zza(view, zza2, zza, zzt, zzb, zzb2, zzbau.zza(zzb2, this.zzvr, this.zzfyf, this.zzfye), (JSONObject) null, z, true);
        }
    }

    private final boolean zzalo() {
        return this.zzfxs.optBoolean("allow_custom_click_gesture", false);
    }

    public final void zzsv() {
        this.zzfyd = true;
    }

    public final boolean isCustomClickGestureEnabled() {
        return zzalo();
    }

    private final void zza(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6, boolean z, boolean z2) {
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zzfxs);
            jSONObject7.put("asset_view_signal", jSONObject2);
            jSONObject7.put("ad_view_signal", jSONObject);
            jSONObject7.put("click_signal", jSONObject5);
            jSONObject7.put("scroll_view_signal", jSONObject3);
            jSONObject7.put("lock_screen_signal", jSONObject4);
            boolean z3 = false;
            jSONObject7.put("has_custom_click_handler", this.zzfqc.zzge(this.zzfxu.getCustomTemplateId()) != null);
            jSONObject7.put("provided_signals", jSONObject6);
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put(Constants.BUNDLE_KEY_ASSET_ID, str);
            jSONObject8.put(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE, this.zzfxu.zzama());
            jSONObject8.put("view_aware_api_used", z);
            jSONObject8.put("custom_mute_requested", this.zzfsk.zzdly != null && this.zzfsk.zzdly.zzbns);
            jSONObject8.put("custom_mute_enabled", !this.zzfxu.getMuteThisAdReasons().isEmpty() && this.zzfxu.zzamd() != null);
            if (this.zzfxy.zzanj() != null && this.zzfxs.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject8.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject8.put("timestamp", this.zzbqa.currentTimeMillis());
            if (this.zzfyd && zzalo()) {
                jSONObject8.put("custom_click_gesture_eligible", true);
            }
            if (z2) {
                jSONObject8.put("is_custom_click_gesture", true);
            }
            if (this.zzfqc.zzge(this.zzfxu.getCustomTemplateId()) != null) {
                z3 = true;
            }
            jSONObject8.put("has_custom_click_handler", z3);
            jSONObject8.put("click_signals", zzx(view));
            jSONObject7.put("click", jSONObject8);
            JSONObject jSONObject9 = new JSONObject();
            long currentTimeMillis = this.zzbqa.currentTimeMillis();
            jSONObject9.put("time_from_last_touch_down", currentTimeMillis - this.zzfyg);
            jSONObject9.put("time_from_last_touch", currentTimeMillis - this.zzfyh);
            jSONObject7.put("touch_signal", jSONObject9);
            zzbcc.zza(this.zzfxt.zzc("google.afma.nativeAds.handleClick", jSONObject7), "Error during performing handleClick");
        } catch (JSONException e) {
            zzayp.zzc("Unable to create click JSON.", e);
        }
    }

    public final void zza(View view, MotionEvent motionEvent, View view2) {
        this.zzfye = zzbau.zza(motionEvent, view2);
        long currentTimeMillis = this.zzbqa.currentTimeMillis();
        this.zzfyh = currentTimeMillis;
        if (motionEvent.getAction() == 0) {
            this.zzfyg = currentTimeMillis;
            this.zzfyf = this.zzfye;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setLocation((float) this.zzfye.x, (float) this.zzfye.y);
        this.zzeom.zza(obtain);
        obtain.recycle();
    }

    public final void zzh(Bundle bundle) {
        if (bundle == null) {
            zzayp.zzef("Touch event data is null. No touch event is reported.");
        } else if (!zzfy("touch_reporting")) {
            zzayp.zzfc("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
        } else {
            int i = bundle.getInt("duration_ms");
            this.zzeom.zzca().zza((int) bundle.getFloat("x"), (int) bundle.getFloat("y"), i);
        }
    }

    public final JSONObject zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2) {
        JSONObject zza = zzbau.zza(this.zzvr, map, map2, view);
        JSONObject zza2 = zzbau.zza(this.zzvr, view);
        JSONObject zzt = zzbau.zzt(view);
        JSONObject zzb = zzbau.zzb(this.zzvr, view);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("asset_view_signal", zza);
            jSONObject.put("ad_view_signal", zza2);
            jSONObject.put("scroll_view_signal", zzt);
            jSONObject.put("lock_screen_signal", zzb);
            return jSONObject;
        } catch (JSONException e) {
            zzayp.zzc("Unable to create native ad view signals JSON.", e);
            return null;
        }
    }

    public final void zzalp() {
        zza((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, (String) null, (JSONObject) null, false);
    }

    public final void setClickConfirmingView(View view) {
        if (!this.zzfxs.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzayp.zzfe("setClickConfirmingView: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            return;
        }
        zzcfh zzcfh = this.zzfxy;
        if (view != null) {
            view.setOnClickListener(zzcfh);
            view.setClickable(true);
            zzcfh.zzgbt = new WeakReference<>(view);
        }
    }

    public final void zza(zzafz zzafz) {
        if (!this.zzfxs.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzayp.zzfe("setUnconfirmedClickListener: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
        } else {
            this.zzfxy.zza(zzafz);
        }
    }

    public final void cancelUnconfirmedClick() {
        if (this.zzfxs.optBoolean("custom_one_point_five_click_enabled", false)) {
            this.zzfxy.cancelUnconfirmedClick();
        }
    }

    public final void zza(zzyd zzyd) {
        try {
            if (!this.zzfyc) {
                if (zzyd != null || this.zzfxu.zzamd() == null) {
                    this.zzfyc = true;
                    this.zzfnf.zzes(zzyd.zzqk());
                    zzalq();
                    return;
                }
                this.zzfyc = true;
                this.zzfnf.zzes(this.zzfxu.zzamd().zzqk());
                zzalq();
            }
        } catch (RemoteException e) {
            zzayp.zze("#007 Could not call remote method.", e);
        }
    }

    public final void zza(zzxz zzxz) {
        this.zzfyi = zzxz;
    }

    public final void zzalq() {
        try {
            if (this.zzfyi != null) {
                this.zzfyi.onAdMuted();
            }
        } catch (RemoteException e) {
            zzayp.zze("#007 Could not call remote method.", e);
        }
    }

    public final void zzb(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2) {
        zza(zzbau.zza(this.zzvr, view), zzbau.zza(this.zzvr, map, map2, view), zzbau.zzt(view), zzbau.zzb(this.zzvr, view), zzy(view), (JSONObject) null, zzbau.zza(this.zzeot));
    }

    public final void zzalr() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on the main UI thread.");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad", this.zzfxs);
            zzbcc.zza(this.zzfxt.zzc("google.afma.nativeAds.handleDownloadedImpression", jSONObject), "Error during performing handleDownloadedImpression");
        } catch (JSONException e) {
            zzbbq.zzc("", e);
        }
    }

    public final boolean zzi(Bundle bundle) {
        if (!zzfy("impression_reporting")) {
            zzayp.zzfc("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
            return false;
        }
        return zza((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, (String) null, zzp.zzkr().zza(bundle, (JSONObject) null), false);
    }

    private final boolean zza(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, boolean z) {
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzfxs);
            jSONObject6.put("asset_view_signal", jSONObject2);
            jSONObject6.put("ad_view_signal", jSONObject);
            jSONObject6.put("scroll_view_signal", jSONObject3);
            jSONObject6.put("lock_screen_signal", jSONObject4);
            jSONObject6.put("provided_signals", jSONObject5);
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcro)).booleanValue()) {
                jSONObject6.put("view_signals", str);
            }
            jSONObject6.put("policy_validator_enabled", z);
            jSONObject6.put("screen", zzbau.zzbm(this.zzvr));
            this.zzfxt.zza("/logScionEvent", (zzahq<Object>) new zzcdk(this));
            this.zzfxt.zza("/nativeImpression", (zzahq<Object>) new zzcdj(this));
            zzbcc.zza(this.zzfxt.zzc("google.afma.nativeAds.handleImpression", jSONObject6), "Error during performing handleImpression");
            if (this.zzfya || this.zzeot.zzhdw == null) {
                return true;
            }
            this.zzfya |= zzp.zzlb().zzb(this.zzvr, this.zzbpe.zzbre, this.zzeot.zzhdw.toString(), this.zzfsk.zzhfb);
            return true;
        } catch (JSONException e) {
            zzayp.zzc("Unable to create impression JSON.", e);
            return false;
        }
    }

    private final String zzx(View view) {
        try {
            JSONObject optJSONObject = this.zzfxs.optJSONObject("tracking_urls_and_actions");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            return this.zzeom.zzca().zza(this.zzvr, optJSONObject.optString("click_string"), view);
        } catch (Exception e) {
            zzayp.zzc("Exception obtaining click signals", e);
            return null;
        }
    }

    private final String zzy(View view) {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcro)).booleanValue()) {
            return null;
        }
        try {
            return this.zzeom.zzca().zza(this.zzvr, view, (Activity) null);
        } catch (Exception unused) {
            zzayp.zzfc("Exception getting data.");
            return null;
        }
    }

    public final void destroy() {
        this.zzfxt.destroy();
    }
}
