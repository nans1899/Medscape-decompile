package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbnv extends zzbqo {
    private final View view;
    private final zzbgj zzdgy;
    private final zzdnu zzfpe;
    private final int zzfpf;
    private final boolean zzfpg;
    private final boolean zzfph;
    private zzsm zzfpn;
    private final zzbnn zzfpo;

    zzbnv(zzbqn zzbqn, View view2, zzbgj zzbgj, zzdnu zzdnu, int i, boolean z, boolean z2, zzbnn zzbnn) {
        super(zzbqn);
        this.view = view2;
        this.zzdgy = zzbgj;
        this.zzfpe = zzdnu;
        this.zzfpf = i;
        this.zzfpg = z;
        this.zzfph = z2;
        this.zzfpo = zzbnn;
    }

    public final zzdnu zzahw() {
        return zzdoq.zza(this.zzeot.zzhdq, this.zzfpe);
    }

    public final View zzahx() {
        return this.view;
    }

    public final int zzaho() {
        return this.zzfpf;
    }

    public final boolean zzahp() {
        return this.zzfpg;
    }

    public final boolean zzahq() {
        return this.zzfph;
    }

    public final boolean zzabc() {
        zzbgj zzbgj = this.zzdgy;
        return (zzbgj == null || zzbgj.zzabw() == null || !this.zzdgy.zzabw().zzabc()) ? false : true;
    }

    public final boolean zzahy() {
        zzbgj zzbgj = this.zzdgy;
        return zzbgj != null && zzbgj.zzaby();
    }

    public final void zza(zzsc zzsc) {
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj != null) {
            zzbgj.zza(zzsc);
        }
    }

    public final void zza(zzsm zzsm) {
        this.zzfpn = zzsm;
    }

    public final zzsm zzahz() {
        return this.zzfpn;
    }

    public final void zzfd(long j) {
        this.zzfpo.zzfd(j);
    }
}
