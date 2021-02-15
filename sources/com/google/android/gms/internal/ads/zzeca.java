package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECPrivateKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzeca extends zzeai<zzdzz, zzeel> {
    zzeca(Class cls) {
        super(cls);
    }

    public final /* synthetic */ Object zzag(Object obj) throws GeneralSecurityException {
        zzeel zzeel = (zzeel) obj;
        zzeei zzbaq = zzeel.zzbax().zzbaq();
        zzeep zzbas = zzbaq.zzbas();
        zzeha zza = zzecj.zza(zzbas.zzbbf());
        byte[] byteArray = zzeel.zzazd().toByteArray();
        ECPrivateKeySpec eCPrivateKeySpec = new ECPrivateKeySpec(new BigInteger(1, byteArray), zzegy.zza(zza));
        return new zzegr((ECPrivateKey) zzehb.zzidd.zzhs("EC").generatePrivate(eCPrivateKeySpec), zzbas.zzbbh().toByteArray(), zzecj.zza(zzbas.zzbbg()), zzecj.zza(zzbaq.zzbau()), new zzecl(zzbaq.zzbat().zzban()));
    }
}
