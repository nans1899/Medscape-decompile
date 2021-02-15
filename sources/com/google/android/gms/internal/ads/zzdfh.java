package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfh implements zzdgx<zzdfe> {
    private final zzdzb zzguy;
    private final Set<String> zzgvm;
    private final Context zzvr;

    public zzdfh(zzdzb zzdzb, Context context, Set<String> set) {
        this.zzguy = zzdzb;
        this.zzvr = context;
        this.zzgvm = set;
    }

    public final zzdzc<zzdfe> zzarj() {
        return this.zzguy.zze(new zzdfg(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdfe zzart() throws Exception {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcuc)).booleanValue() || !zzdfe.zze(this.zzgvm)) {
            return new zzdfe((String) null);
        }
        return new zzdfe(zzp.zzlg().getVersion(this.zzvr));
    }
}
