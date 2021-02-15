package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebb extends zzeag<zzede> {
    zzebb() {
        super(zzede.class, new zzebd(zzdzv.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.SYMMETRIC;
    }

    public final zzeaf<zzedh, zzede> zzayr() {
        return new zzebc(this, zzedh.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzeht.zzy(((zzede) zzelj).getVersion(), 0);
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzede.zze(zzeip, zzejm.zzbft());
    }
}
