package com.google.android.gms.internal.ads;

import android.content.Context;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhv implements zzdgx<zzdhs> {
    private final String packageName;
    private final zzdzb zzgcz;
    private final zzatj zzgyy;
    private final Context zzvr;

    public zzdhv(zzatj zzatj, Context context, String str, zzdzb zzdzb) {
        this.zzgyy = zzatj;
        this.zzvr = context;
        this.packageName = str;
        this.zzgcz = zzdzb;
    }

    public final zzdzc<zzdhs> zzarj() {
        return this.zzgcz.zze(new zzdhu(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdhs zzasd() throws Exception {
        JSONObject jSONObject = new JSONObject();
        zzatj zzatj = this.zzgyy;
        if (zzatj != null) {
            zzatj.zza(this.zzvr, this.packageName, jSONObject);
        }
        return new zzdhs(jSONObject);
    }
}
