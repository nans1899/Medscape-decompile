package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebl extends zzeag<zzedu> {
    zzebl() {
        super(zzedu.class, new zzebk(zzdzv.class));
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public final zzefb.zza zzayo() {
        return zzefb.zza.SYMMETRIC;
    }

    public final zzeaf<zzedx, zzedu> zzayr() {
        return new zzebn(this, zzedx.class);
    }

    public final /* synthetic */ void zze(zzelj zzelj) throws GeneralSecurityException {
        zzedu zzedu = (zzedu) zzelj;
        zzeht.zzy(zzedu.getVersion(), 0);
        zzeht.zzfp(zzedu.zzazd().size());
    }

    public final /* synthetic */ zzelj zzr(zzeip zzeip) throws zzekj {
        return zzedu.zzk(zzeip, zzejm.zzbft());
    }
}
