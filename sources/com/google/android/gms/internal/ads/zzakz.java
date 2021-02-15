package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzakz implements zzahq<zzalp> {
    private final /* synthetic */ zzeg zzdhq;
    private final /* synthetic */ zzaki zzdhr;
    private final /* synthetic */ zzbaz zzdhs;
    private final /* synthetic */ zzakr zzdht;

    zzakz(zzakr zzakr, zzeg zzeg, zzaki zzaki, zzbaz zzbaz) {
        this.zzdht = zzakr;
        this.zzdhq = zzeg;
        this.zzdhr = zzaki;
        this.zzdhs = zzbaz;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzalp zzalp = (zzalp) obj;
        synchronized (this.zzdht.lock) {
            zzayp.zzfd("JS Engine is requesting an update");
            if (this.zzdht.status == 0) {
                zzayp.zzfd("Starting reload.");
                int unused = this.zzdht.status = 2;
                this.zzdht.zza(this.zzdhq);
            }
            this.zzdhr.zzb("/requestReload", (zzahq) this.zzdhs.get());
        }
    }
}
