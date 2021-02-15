package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzecp extends zzeag<zzeet> {
    public zzecp() {
        super(zzeet.class, new zzecs(zzeam.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.SYMMETRIC;
    }

    /* access modifiers changed from: private */
    public static void zza(zzeey zzeey) throws GeneralSecurityException {
        if (zzeey.zzazi() >= 10) {
            int i = zzecu.zzhwk[zzeey.zzbbq().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new GeneralSecurityException("unknown hash type");
                    } else if (zzeey.zzazi() > 64) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                } else if (zzeey.zzazi() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
            } else if (zzeey.zzazi() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            throw new GeneralSecurityException("tag size too small");
        }
    }

    public final zzeaf<zzeex, zzeet> zzayr() {
        return new zzecr(this, zzeex.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzeet zzeet = (zzeet) zzelj;
        zzeht.zzy(zzeet.getVersion(), 0);
        if (zzeet.zzazd().size() >= 16) {
            zza(zzeet.zzbbk());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzeet.zzr(zzeip, zzejm.zzbft());
    }
}
