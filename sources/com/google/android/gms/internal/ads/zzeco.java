package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeco extends zzeag<zzecz> {
    zzeco() {
        super(zzecz.class, new zzecn(zzeam.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.SYMMETRIC;
    }

    /* access modifiers changed from: private */
    public static void zza(zzedd zzedd) throws GeneralSecurityException {
        if (zzedd.zzazi() < 10) {
            throw new GeneralSecurityException("tag size too short");
        } else if (zzedd.zzazi() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* access modifiers changed from: private */
    public static void zzeq(int i) throws GeneralSecurityException {
        if (i != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 16 bytes");
        }
    }

    public final zzeaf<zzeda, zzecz> zzayr() {
        return new zzecq(this, zzeda.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzecz zzecz = (zzecz) zzelj;
        zzeht.zzy(zzecz.getVersion(), 0);
        zzeq(zzecz.zzazd().size());
        zza(zzecz.zzaze());
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzecz.zzc(zzeip, zzejm.zzbft());
    }
}
