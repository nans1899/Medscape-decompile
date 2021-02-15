package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzato extends zzatq {
    private final Object lock = new Object();
    private final Context zzaai;
    private SharedPreferences zzdvn;
    private final zzalt<JSONObject, JSONObject> zzdvo;

    public zzato(Context context, zzalt<JSONObject, JSONObject> zzalt) {
        this.zzaai = context.getApplicationContext();
        this.zzdvo = zzalt;
    }

    public final zzdzc<Void> zzvo() {
        synchronized (this.lock) {
            if (this.zzdvn == null) {
                this.zzdvn = this.zzaai.getSharedPreferences("google_ads_flags_meta", 0);
            }
        }
        if (zzp.zzky().currentTimeMillis() - this.zzdvn.getLong("js_last_update", 0) < zzacw.zzdbs.get().longValue()) {
            return zzdyq.zzaf(null);
        }
        return zzdyq.zzb(this.zzdvo.zzi(zzz(this.zzaai)), new zzatr(this), (Executor) zzbbz.zzeeu);
    }

    public static JSONObject zzz(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("js", zzbbx.zzyv().zzbre);
            jSONObject.put("mf", zzacw.zzdbr.get());
            jSONObject.put("cl", "330794610");
            jSONObject.put("rapid_rc", "dev");
            jSONObject.put("rapid_rollup", "HEAD");
            jSONObject.put("admob_module_version", 20360);
            jSONObject.put("dynamite_local_version", ModuleDescriptor.MODULE_VERSION);
            jSONObject.put("dynamite_version", DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID));
            jSONObject.put("container_version", 12451009);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzf(JSONObject jSONObject) {
        zzabb.zza(this.zzaai, 1, jSONObject);
        this.zzdvn.edit().putLong("js_last_update", zzp.zzky().currentTimeMillis()).apply();
        return null;
    }
}
