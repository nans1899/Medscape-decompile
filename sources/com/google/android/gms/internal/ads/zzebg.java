package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebg extends zzeag<zzedp> {
    zzebg() {
        super(zzedp.class, new zzebj(zzdzv.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.SYMMETRIC;
    }

    public final zzeaf<zzedq, zzedp> zzayr() {
        return new zzebi(this, zzedq.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzedp zzedp = (zzedp) zzelj;
        zzeht.zzy(zzedp.getVersion(), 0);
        zzeht.zzfp(zzedp.zzazd().size());
        if (zzedp.zzbab().zzazy() != 12 && zzedp.zzbab().zzazy() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzedp.zzi(zzeip, zzejm.zzbft());
    }
}
