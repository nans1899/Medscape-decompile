package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzecc extends zzeag<zzeem> {
    public zzecc() {
        super(zzeem.class, new zzecf(zzeac.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.ASYMMETRIC_PUBLIC;
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzeem zzeem = (zzeem) zzelj;
        zzeht.zzy(zzeem.getVersion(), 0);
        zzecj.zza(zzeem.zzbaq());
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzeem.zzq(zzeip, zzejm.zzbft());
    }
}
