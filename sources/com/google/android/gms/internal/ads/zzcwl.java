package com.google.android.gms.internal.ads;

import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.Iterator;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwl {
    private final zzcmu zzfpk;
    private final zzckp zzgfy;
    private final zzdow zzggz;

    public zzcwl(zzdow zzdow, zzckp zzckp, zzcmu zzcmu) {
        this.zzggz = zzdow;
        this.zzgfy = zzckp;
        this.zzfpk = zzcmu;
    }

    public final void zza(zzdnw zzdnw, zzdnv zzdnv, int i, @Nullable zzcte zzcte, long j) {
        zzckq zzckq;
        zzcmt zzr = this.zzfpk.zzapf().zza(zzdnw).zzd(zzdnv).zzr("action", "adapter_status").zzr("adapter_l", String.valueOf(j));
        zzr.zzr(AdParameterKeys.CONDITION, Integer.toString(i));
        if (zzcte != null) {
            zzr.zzr("arec", Integer.toString(zzcte.zzaqj().errorCode));
            String zzgu = this.zzggz.zzgu(zzcte.getMessage());
            if (zzgu != null) {
                zzr.zzr("areec", zzgu);
            }
        }
        zzckp zzckp = this.zzgfy;
        Iterator<String> it = zzdnv.zzhds.iterator();
        while (true) {
            if (!it.hasNext()) {
                zzckq = null;
                break;
            }
            zzckq = zzckp.zzgg(it.next());
            if (zzckq != null) {
                break;
            }
        }
        if (zzckq != null) {
            zzr.zzr("ancn", zzckq.zzdfz);
            if (zzckq.zzgfv != null) {
                zzr.zzr("adapter_v", zzckq.zzgfv.toString());
            }
            if (zzckq.zzgfw != null) {
                zzr.zzr("adapter_sv", zzckq.zzgfw.toString());
            }
        }
        zzr.zzapc();
    }
}
