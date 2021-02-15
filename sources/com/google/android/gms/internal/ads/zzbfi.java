package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbfi implements Iterable<zzbfg> {
    private final List<zzbfg> zzell = new ArrayList();

    public static boolean zzc(zzbdu zzbdu) {
        zzbfg zzd = zzd(zzbdu);
        if (zzd == null) {
            return false;
        }
        zzd.zzelk.abort();
        return true;
    }

    static zzbfg zzd(zzbdu zzbdu) {
        Iterator<zzbfg> it = zzp.zzln().iterator();
        while (it.hasNext()) {
            zzbfg next = it.next();
            if (next.zzegh == zzbdu) {
                return next;
            }
        }
        return null;
    }

    public final void zza(zzbfg zzbfg) {
        this.zzell.add(zzbfg);
    }

    public final void zzb(zzbfg zzbfg) {
        this.zzell.remove(zzbfg);
    }

    public final Iterator<zzbfg> iterator() {
        return this.zzell.iterator();
    }
}
