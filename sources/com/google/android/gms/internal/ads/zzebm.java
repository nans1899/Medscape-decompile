package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebm extends zzeag<zzedy> {
    zzebm() {
        super(zzedy.class, new zzebp(zzdzv.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.SYMMETRIC;
    }

    public final zzeaf<zzeeb, zzedy> zzayr() {
        return new zzebo(this, zzeeb.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzedy zzedy = (zzedy) zzelj;
        zzeht.zzy(zzedy.getVersion(), 0);
        if (zzedy.zzazd().size() != 32) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzedy.zzm(zzeip, zzejm.zzbft());
    }
}
