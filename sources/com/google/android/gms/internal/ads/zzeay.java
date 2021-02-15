package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import com.google.android.gms.internal.ads.zzefj;
import com.google.android.gms.internal.ads.zzefm;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzeay {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static zzefm zzb(zzefj zzefj) {
        zzefm.zzb zzfi = zzefm.zzbco().zzfi(zzefj.zzbcg());
        for (zzefj.zzb next : zzefj.zzbch()) {
            zzfi.zzb((zzefm.zza) ((zzejz) zzefm.zza.zzbcq().zzhr(next.zzbcl().zzbbt()).zzb(next.zzayu()).zzb(next.zzayv()).zzfj(next.zzbcm()).zzbgt()));
        }
        return (zzefm) ((zzejz) zzfi.zzbgt());
    }

    public static void zzc(zzefj zzefj) throws GeneralSecurityException {
        int zzbcg = zzefj.zzbcg();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzefj.zzb next : zzefj.zzbch()) {
            if (next.zzayu() == zzefc.ENABLED) {
                if (!next.zzbck()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(next.zzbcm())}));
                } else if (next.zzayv() == zzefv.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(next.zzbcm())}));
                } else if (next.zzayu() != zzefc.UNKNOWN_STATUS) {
                    if (next.zzbcm() == zzbcg) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    if (next.zzbcl().zzbbv() != zzefb.zza.ASYMMETRIC_PUBLIC) {
                        z2 = false;
                    }
                    i++;
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(next.zzbcm())}));
                }
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        } else if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
