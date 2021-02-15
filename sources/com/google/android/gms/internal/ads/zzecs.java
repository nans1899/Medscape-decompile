package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzecs extends zzeai<zzeam, zzeet> {
    zzecs(Class cls) {
        super(cls);
    }

    public final /* synthetic */ Object zzag(Object obj) throws GeneralSecurityException {
        zzeet zzeet = (zzeet) obj;
        zzeer zzbbq = zzeet.zzbbk().zzbbq();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzeet.zzazd().toByteArray(), "HMAC");
        int zzazi = zzeet.zzbbk().zzazi();
        int i = zzecu.zzhwk[zzbbq.ordinal()];
        if (i == 1) {
            return new zzehp(new zzehn("HMACSHA1", secretKeySpec), zzazi);
        }
        if (i == 2) {
            return new zzehp(new zzehn("HMACSHA256", secretKeySpec), zzazi);
        }
        if (i == 3) {
            return new zzehp(new zzehn("HMACSHA512", secretKeySpec), zzazi);
        }
        throw new GeneralSecurityException("unknown hash");
    }
}
