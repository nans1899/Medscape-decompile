package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzp;
import com.medscape.android.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbms implements zzaly<zzbmw> {
    private final PowerManager zzaaj;
    private final zzqo zzfnw;
    private final Context zzvr;

    public zzbms(Context context, zzqo zzqo) {
        this.zzvr = context;
        this.zzfnw = zzqo;
        this.zzaaj = (PowerManager) context.getSystemService("power");
    }

    /* renamed from: zza */
    public final JSONObject zzj(zzbmw zzbmw) throws JSONException {
        JSONObject jSONObject;
        boolean z;
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        if (zzbmw.zzfoo == null) {
            jSONObject = new JSONObject();
        } else {
            zzqr zzqr = zzbmw.zzfoo;
            if (this.zzfnw.zzlv() != null) {
                boolean z2 = zzqr.zzbrs;
                JSONObject jSONObject3 = new JSONObject();
                JSONObject put = jSONObject3.put("afmaVersion", this.zzfnw.zzlu()).put("activeViewJSON", this.zzfnw.zzlv()).put("timestamp", zzbmw.timestamp).put("adFormat", this.zzfnw.zzlt()).put("hashCode", this.zzfnw.getUniqueId()).put("isMraid", false).put("isStopped", false).put("isPaused", zzbmw.zzfol).put("isNative", this.zzfnw.isNative());
                if (Build.VERSION.SDK_INT >= 20) {
                    z = this.zzaaj.isInteractive();
                } else {
                    z = this.zzaaj.isScreenOn();
                }
                put.put("isScreenOn", z).put("appMuted", zzp.zzkw().zzqh()).put("appVolume", (double) zzp.zzkw().zzqg()).put("deviceVolume", (double) zzazm.zzbi(this.zzvr.getApplicationContext()));
                Rect rect = new Rect();
                Display defaultDisplay = ((WindowManager) this.zzvr.getSystemService("window")).getDefaultDisplay();
                rect.right = defaultDisplay.getWidth();
                rect.bottom = defaultDisplay.getHeight();
                jSONObject3.put("windowVisibility", zzqr.zzaaq).put("isAttachedToWindow", z2).put("viewBox", new JSONObject().put(Constants.CONSULT_DEEPLINK_TOP_POSTS, zzqr.zzbrt.top).put("bottom", zzqr.zzbrt.bottom).put("left", zzqr.zzbrt.left).put("right", zzqr.zzbrt.right)).put("adBox", new JSONObject().put(Constants.CONSULT_DEEPLINK_TOP_POSTS, zzqr.zzbru.top).put("bottom", zzqr.zzbru.bottom).put("left", zzqr.zzbru.left).put("right", zzqr.zzbru.right)).put("globalVisibleBox", new JSONObject().put(Constants.CONSULT_DEEPLINK_TOP_POSTS, zzqr.zzbrv.top).put("bottom", zzqr.zzbrv.bottom).put("left", zzqr.zzbrv.left).put("right", zzqr.zzbrv.right)).put("globalVisibleBoxVisible", zzqr.zzbrw).put("localVisibleBox", new JSONObject().put(Constants.CONSULT_DEEPLINK_TOP_POSTS, zzqr.zzbrx.top).put("bottom", zzqr.zzbrx.bottom).put("left", zzqr.zzbrx.left).put("right", zzqr.zzbrx.right)).put("localVisibleBoxVisible", zzqr.zzbry).put("hitBox", new JSONObject().put(Constants.CONSULT_DEEPLINK_TOP_POSTS, zzqr.zzbrz.top).put("bottom", zzqr.zzbrz.bottom).put("left", zzqr.zzbrz.left).put("right", zzqr.zzbrz.right)).put("screenDensity", (double) this.zzvr.getResources().getDisplayMetrics().density);
                jSONObject3.put("isVisible", zzbmw.zzbro);
                if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcpx)).booleanValue()) {
                    JSONArray jSONArray2 = new JSONArray();
                    if (zzqr.zzbsb != null) {
                        for (Rect next : zzqr.zzbsb) {
                            jSONArray2.put(new JSONObject().put(Constants.CONSULT_DEEPLINK_TOP_POSTS, next.top).put("bottom", next.bottom).put("left", next.left).put("right", next.right));
                        }
                    }
                    jSONObject3.put("scrollableContainerBoxes", jSONArray2);
                }
                if (!TextUtils.isEmpty(zzbmw.zzfon)) {
                    jSONObject3.put("doneReasonCode", "u");
                }
                jSONObject = jSONObject3;
            } else {
                throw new JSONException("Active view Info cannot be null.");
            }
        }
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }
}
