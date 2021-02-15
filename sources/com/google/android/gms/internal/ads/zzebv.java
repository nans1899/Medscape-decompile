package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebv extends zzeag<zzefr> {
    zzebv() {
        super(zzefr.class, new zzebu(zzdzv.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.REMOTE;
    }

    public final zzeaf<zzefu, zzefr> zzayr() {
        return new zzebx(this, zzefu.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzeht.zzy(((zzefr) zzelj).getVersion(), 0);
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzefr.zzv(zzeip, zzejm.zzbft());
    }
}
