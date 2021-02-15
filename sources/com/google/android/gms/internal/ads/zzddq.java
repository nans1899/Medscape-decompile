package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddq implements zzdgx<zzddr> {
    private final zzdok zzfsk;
    private final zzdzb zzgcz;
    private final String zzgup;
    private final zzckw zzgwl;

    public zzddq(zzdzb zzdzb, zzckw zzckw, zzdok zzdok, String str) {
        this.zzgcz = zzdzb;
        this.zzgwl = zzckw;
        this.zzfsk = zzdok;
        this.zzgup = str;
    }

    public final zzdzc<zzddr> zzarj() {
        return this.zzgcz.zze(new zzddt(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzddr zzarp() throws Exception {
        JSONObject zzq;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcsr)).booleanValue() && (zzq = this.zzgwl.zzq(this.zzfsk.zzhfb, this.zzgup)) != null) {
            return new zzddr(zzq.toString());
        }
        return null;
    }
}
