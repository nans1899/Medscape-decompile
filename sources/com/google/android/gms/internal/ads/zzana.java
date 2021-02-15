package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import com.medscape.android.updater.UpdateManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.media.android.bidder.base.common.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzana {
    private final long zzdkh;
    public final List<zzamx> zzdki;
    private final List<String> zzdkj;
    private final List<String> zzdkk;
    private final List<String> zzdkl;
    private final List<String> zzdkm;
    private final List<String> zzdkn;
    private final boolean zzdko;
    private final String zzdkp;
    private final long zzdkq;
    private final String zzdkr;
    private final int zzdks;
    private final int zzdkt;
    private final long zzdku;
    private final boolean zzdkv;
    private final boolean zzdkw;
    private final boolean zzdkx;
    private final boolean zzdky;
    private int zzdkz;
    private int zzdla;
    private boolean zzdlb;

    public zzana(JSONObject jSONObject) throws JSONException {
        if (zzayp.isLoggable(2)) {
            String valueOf = String.valueOf(jSONObject.toString(2));
            zzayp.zzei(valueOf.length() != 0 ? "Mediation Response JSON: ".concat(valueOf) : new String("Mediation Response JSON: "));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                zzamx zzamx = new zzamx(jSONArray.getJSONObject(i2));
                boolean z = true;
                if (Constants.Capabilities.BANNER.equalsIgnoreCase(zzamx.zzdkg)) {
                    this.zzdlb = true;
                }
                arrayList.add(zzamx);
                if (i < 0) {
                    Iterator<String> it = zzamx.zzdjn.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next().equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        i = i2;
                    }
                }
            } catch (JSONException unused) {
            }
        }
        this.zzdkz = i;
        this.zzdla = jSONArray.length();
        this.zzdki = Collections.unmodifiableList(arrayList);
        this.zzdkp = jSONObject.optString("qdata");
        this.zzdkt = jSONObject.optInt("fs_model_type", -1);
        long j = -1;
        this.zzdku = jSONObject.optLong("timeout_ms", -1);
        JSONObject optJSONObject = jSONObject.optJSONObject(UpdateManager.SETTINGS_PREFS);
        if (optJSONObject != null) {
            this.zzdkh = optJSONObject.optLong("ad_network_timeout_millis", -1);
            zzp.zzlj();
            this.zzdkj = zzamz.zza(optJSONObject, "click_urls");
            zzp.zzlj();
            this.zzdkk = zzamz.zza(optJSONObject, "imp_urls");
            zzp.zzlj();
            this.zzdkl = zzamz.zza(optJSONObject, "downloaded_imp_urls");
            zzp.zzlj();
            this.zzdkm = zzamz.zza(optJSONObject, "nofill_urls");
            zzp.zzlj();
            this.zzdkn = zzamz.zza(optJSONObject, "remote_ping_urls");
            this.zzdko = optJSONObject.optBoolean("render_in_browser", false);
            long optLong = optJSONObject.optLong("refresh", -1);
            this.zzdkq = optLong > 0 ? 1000 * optLong : j;
            zzauv zza = zzauv.zza(optJSONObject.optJSONArray("rewards"));
            if (zza == null) {
                this.zzdkr = null;
                this.zzdks = 0;
            } else {
                this.zzdkr = zza.type;
                this.zzdks = zza.zzdvs;
            }
            this.zzdkv = optJSONObject.optBoolean("use_displayed_impression", false);
            this.zzdkw = optJSONObject.optBoolean("allow_pub_rendered_attribution", false);
            this.zzdkx = optJSONObject.optBoolean("allow_pub_owned_ad_view", false);
            this.zzdky = optJSONObject.optBoolean("allow_custom_click_gesture", false);
            return;
        }
        this.zzdkh = -1;
        this.zzdkj = null;
        this.zzdkk = null;
        this.zzdkl = null;
        this.zzdkm = null;
        this.zzdkn = null;
        this.zzdkq = -1;
        this.zzdkr = null;
        this.zzdks = 0;
        this.zzdkv = false;
        this.zzdko = false;
        this.zzdkw = false;
        this.zzdkx = false;
        this.zzdky = false;
    }
}
