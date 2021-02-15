package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzckw {
    private final Executor executor;
    private final Map<String, Map<String, JSONObject>> zzggb = new ConcurrentHashMap();
    private JSONObject zzggc;
    private boolean zzxi;

    public zzckw(Executor executor2) {
        this.executor = executor2;
    }

    public final void zzaoh() {
        zzp.zzkv().zzwt().zzb(new zzckv(this));
        this.executor.execute(new zzcky(this));
    }

    @CheckForNull
    public final JSONObject zzq(String str, String str2) {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcsr)).booleanValue() || str == null || str2 == null) {
            return null;
        }
        if (!this.zzxi) {
            zzaol();
        }
        Map map = this.zzggb.get(str2);
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = (JSONObject) map.get(str);
        if (jSONObject != null) {
            return jSONObject;
        }
        String zza = zzckz.zza(this.zzggc, str, str2);
        if (zza == null) {
            return null;
        }
        return (JSONObject) map.get(zza);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaoi */
    public final synchronized void zzaol() {
        Map map;
        this.zzxi = true;
        zzaxy zzxk = zzp.zzkv().zzwt().zzxk();
        if (zzxk != null) {
            JSONObject zzwj = zzxk.zzwj();
            if (zzwj != null) {
                this.zzggc = zzwj.optJSONObject("ad_unit_patterns");
                JSONArray optJSONArray = zzwj.optJSONArray("ad_unit_id_settings");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("ad_unit_id");
                            String optString2 = optJSONObject.optString("format");
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject("request_signals");
                            if (!(optString == null || optJSONObject2 == null || optString2 == null)) {
                                if (this.zzggb.containsKey(optString2)) {
                                    map = this.zzggb.get(optString2);
                                } else {
                                    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                                    this.zzggb.put(optString2, concurrentHashMap);
                                    map = concurrentHashMap;
                                }
                                map.put(optString, optJSONObject2);
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaok() {
        this.executor.execute(new zzckx(this));
    }
}
