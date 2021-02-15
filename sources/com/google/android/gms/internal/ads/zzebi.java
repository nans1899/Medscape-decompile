package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzebi extends zzeaf<zzedq, zzedp> {
    private final /* synthetic */ zzebg zzhvr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzebi(zzebg zzebg, Class cls) {
        super(cls);
        this.zzhvr = zzebg;
    }

    public final /* synthetic */ Object zzd(zzelj zzelj) throws GeneralSecurityException {
        zzedq zzedq = (zzedq) zzelj;
        return (zzedp) ((zzejz) zzedp.zzbac().zzv(zzeip.zzu(zzehs.zzfo(zzedq.getKeySize()))).zzb(zzedq.zzbab()).zzev(0).zzbgt());
    }

    public final /* synthetic */ zzelj zzq(zzeip zzeip) throws zzekj {
        return zzedq.zzj(zzeip, zzejm.zzbft());
    }

    public final /* synthetic */ void zzc(zzelj zzelj) throws GeneralSecurityException {
        zzedq zzedq = (zzedq) zzelj;
        zzeht.zzfp(zzedq.getKeySize());
        if (zzedq.zzbab().zzazy() != 12 && zzedq.zzbab().zzazy() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
