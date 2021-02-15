package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebr extends zzeag<zzefn> {
    zzebr() {
        super(zzefn.class, new zzebq(zzdzv.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.REMOTE;
    }

    public final zzeaf<zzefq, zzefn> zzayr() {
        return new zzebt(this, zzefq.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzeht.zzy(((zzefn) zzelj).getVersion(), 0);
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzefn.zzt(zzeip, zzejm.zzbft());
    }
}
