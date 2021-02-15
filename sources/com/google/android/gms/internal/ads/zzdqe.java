package com.google.android.gms.internal.ads;

import org.apache.commons.io.IOUtils;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdqe {
    private final zzdqd zzhhj = new zzdqd();
    private int zzhhk;
    private int zzhhl;
    private int zzhhm;
    private int zzhhn;
    private int zzhho;

    zzdqe() {
    }

    public final void zzaug() {
        this.zzhhm++;
    }

    public final void zzauh() {
        this.zzhhn++;
    }

    public final void zzaui() {
        this.zzhhk++;
        this.zzhhj.zzhhh = true;
    }

    public final void zzauj() {
        this.zzhhl++;
        this.zzhhj.zzhhi = true;
    }

    public final void zzauk() {
        this.zzhho++;
    }

    public final zzdqd zzaul() {
        zzdqd zzdqd = (zzdqd) this.zzhhj.clone();
        zzdqd zzdqd2 = this.zzhhj;
        zzdqd2.zzhhh = false;
        zzdqd2.zzhhi = false;
        return zzdqd;
    }

    public final String zzaum() {
        return "\n\tPool does not exist: " + this.zzhhm + "\n\tNew pools created: " + this.zzhhk + "\n\tPools removed: " + this.zzhhl + "\n\tEntries added: " + this.zzhho + "\n\tNo entries retrieved: " + this.zzhhn + IOUtils.LINE_SEPARATOR_UNIX;
    }
}
