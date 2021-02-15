package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebf extends zzeag<zzedi> {
    zzebf() {
        super(zzedi.class, new zzebe(zzehl.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.SYMMETRIC;
    }

    public final zzeaf<zzedl, zzedi> zzayr() {
        return new zzebh(this, zzedl.class);
    }

    /* access modifiers changed from: private */
    public static void zza(zzedm zzedm) throws GeneralSecurityException {
        if (zzedm.zzazy() < 12 || zzedm.zzazy() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzedi zzedi = (zzedi) zzelj;
        zzeht.zzy(zzedi.getVersion(), 0);
        zzeht.zzfp(zzedi.zzazd().size());
        zza(zzedi.zzazs());
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzedi.zzg(zzeip, zzejm.zzbft());
    }
}
