package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzecb extends zzeas<zzeel, zzeem> {
    private static final byte[] zzhwa = new byte[0];

    zzecb() {
        super(zzeel.class, zzeem.class, new zzeca(zzdzz.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.ASYMMETRIC_PRIVATE;
    }

    public final zzeaf<zzeeh, zzeel> zzayr() {
        return new zzecd(this, zzeeh.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzeel zzeel = (zzeel) zzelj;
        if (!zzeel.zzazd().isEmpty()) {
            zzeht.zzy(zzeel.getVersion(), 0);
            zzecj.zza(zzeel.zzbax().zzbaq());
            return;
        }
        throw new GeneralSecurityException("invalid ECIES private key");
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzeel.zzp(zzeip, zzejm.zzbft());
    }
}
