package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdge implements zzdgx<zzdgf> {
    private final zzawo zzbqq;
    private final zzdzb zzgcz;
    private final Context zzvr;

    public zzdge(zzawo zzawo, zzdzb zzdzb, Context context) {
        this.zzbqq = zzawo;
        this.zzgcz = zzdzb;
        this.zzvr = context;
    }

    public final zzdzc<zzdgf> zzarj() {
        return this.zzgcz.zze(new zzdgh(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdgf zzarx() throws Exception {
        String str;
        String str2;
        String str3;
        if (!this.zzbqq.zzac(this.zzvr)) {
            return new zzdgf((String) null, (String) null, (String) null, (String) null, (Long) null);
        }
        String zzaf = this.zzbqq.zzaf(this.zzvr);
        String str4 = zzaf == null ? "" : zzaf;
        String zzag = this.zzbqq.zzag(this.zzvr);
        if (zzag == null) {
            str = "";
        } else {
            str = zzag;
        }
        String zzah = this.zzbqq.zzah(this.zzvr);
        if (zzah == null) {
            str2 = "";
        } else {
            str2 = zzah;
        }
        String zzai = this.zzbqq.zzai(this.zzvr);
        if (zzai == null) {
            str3 = "";
        } else {
            str3 = zzai;
        }
        return new zzdgf(str4, str, str2, str3, "TIME_OUT".equals(str) ? (Long) zzwm.zzpx().zzd(zzabb.zzcnq) : null);
    }
}
