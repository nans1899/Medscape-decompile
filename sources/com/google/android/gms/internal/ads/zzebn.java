package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzebn extends zzeaf<zzedx, zzedu> {
    private final /* synthetic */ zzebl zzhvs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzebn(zzebl zzebl, Class cls) {
        super(cls);
        this.zzhvs = zzebl;
    }

    public final /* synthetic */ Object zzd(zzelj zzelj) throws GeneralSecurityException {
        return (zzedu) ((zzejz) zzedu.zzbah().zzw(zzeip.zzu(zzehs.zzfo(((zzedx) zzelj).getKeySize()))).zzew(0).zzbgt());
    }

    public final /* synthetic */ zzelj zzq(zzeip zzeip) throws zzekj {
        return zzedx.zzl(zzeip, zzejm.zzbft());
    }

    public final /* synthetic */ void zzc(zzelj zzelj) throws GeneralSecurityException {
        zzeht.zzfp(((zzedx) zzelj).getKeySize());
    }
}
