package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdec implements zzdgx<zzdeh> {
    private final Context zzaai;
    private final zzdzb zzgcz;

    zzdec(Context context, zzdzb zzdzb) {
        this.zzaai = context;
        this.zzgcz = zzdzb;
    }

    public final zzdzc<zzdeh> zzarj() {
        return this.zzgcz.zze(new zzdef(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdeh zzarq() throws Exception {
        zzp.zzkr();
        String zzba = zzayu.zzba(this.zzaai);
        String str = "";
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwf)).booleanValue()) {
            str = this.zzaai.getSharedPreferences("mobileads_consent", 0).getString("fc_consent", str);
        }
        zzp.zzkr();
        return new zzdeh(zzba, str, zzayu.zzbb(this.zzaai));
    }
}
