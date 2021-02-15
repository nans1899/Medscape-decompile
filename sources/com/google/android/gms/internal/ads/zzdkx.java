package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdkx implements zzdya {
    private final zzdku zzhbe;
    private final zzdqk zzhbf;
    private final zzbrl zzhbg;

    zzdkx(zzdku zzdku, zzdqk zzdqk, zzbrl zzbrl) {
        this.zzhbe = zzdku;
        this.zzhbf = zzdqk;
        this.zzhbg = zzbrl;
    }

    public final zzdzc zzf(Object obj) {
        zzdqk zzdqk = this.zzhbf;
        zzbrl zzbrl = this.zzhbg;
        zzdog zzdog = (zzdog) obj;
        zzdqk.zzevd = zzdog;
        Iterator<zzdnv> it = zzdog.zzhew.zzhet.iterator();
        boolean z = false;
        boolean z2 = false;
        loop0:
        while (true) {
            if (!it.hasNext()) {
                z = z2;
                break;
            }
            Iterator<String> it2 = it.next().zzhdi.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (!it2.next().contains("FirstPartyRenderer")) {
                        break loop0;
                    }
                    z2 = true;
                }
            }
        }
        if (!z) {
            return zzdyq.zzaf(null);
        }
        return zzbrl.zzb((zzdzc<zzdog>) zzdyq.zzaf(zzdog));
    }
}
