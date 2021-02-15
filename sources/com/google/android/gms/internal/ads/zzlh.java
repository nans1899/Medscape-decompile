package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzlh {
    public final zzkg zzasq;
    public final zzlp zzayl = new zzlp();
    public zzln zzaym;
    public zzlb zzayn;
    public int zzayo;
    public int zzayp;
    public int zzayq;
    public zzkf zzayr;
    public zzlq zzays;

    public zzlh(zzkg zzkg) {
        this.zzasq = zzkg;
    }

    public final void zza(zzln zzln, zzlb zzlb) {
        this.zzaym = (zzln) zzoz.checkNotNull(zzln);
        this.zzayn = (zzlb) zzoz.checkNotNull(zzlb);
        this.zzasq.zze(zzln.zzahx);
        reset();
    }

    public final void reset() {
        zzlp zzlp = this.zzayl;
        zzlp.zzban = 0;
        zzlp.zzbba = 0;
        zzlp.zzbau = false;
        zzlp.zzbaz = false;
        zzlp.zzbaw = null;
        this.zzayo = 0;
        this.zzayq = 0;
        this.zzayp = 0;
        this.zzayr = null;
        this.zzays = null;
    }
}
