package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import com.google.android.gms.internal.ads.zzefj;
import java.security.GeneralSecurityException;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeal {
    @Deprecated
    public static final zzeah zzl(byte[] bArr) throws GeneralSecurityException {
        try {
            zzefj zzc = zzefj.zzc(bArr, zzejm.zzbft());
            for (zzefj.zzb next : zzc.zzbch()) {
                if (next.zzbcl().zzbbv() == zzefb.zza.UNKNOWN_KEYMATERIAL || next.zzbcl().zzbbv() == zzefb.zza.SYMMETRIC || next.zzbcl().zzbbv() == zzefb.zza.ASYMMETRIC_PRIVATE) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
            }
            return zzeah.zza(zzc);
        } catch (zzekj unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}
