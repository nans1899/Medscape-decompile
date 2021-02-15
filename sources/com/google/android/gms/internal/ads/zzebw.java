package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebw extends zzeag<zzega> {
    zzebw() {
        super(zzega.class, new zzebz(zzdzv.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.SYMMETRIC;
    }

    public final zzeaf<zzegb, zzega> zzayr() {
        return new zzeby(this, zzegb.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzega zzega = (zzega) zzelj;
        zzeht.zzy(zzega.getVersion(), 0);
        if (zzega.zzazd().size() != 32) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzega.zzx(zzeip, zzejm.zzbft());
    }
}
