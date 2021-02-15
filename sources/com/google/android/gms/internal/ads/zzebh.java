package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzebh extends zzeaf<zzedl, zzedi> {
    private final /* synthetic */ zzebf zzhvq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzebh(zzebf zzebf, Class cls) {
        super(cls);
        this.zzhvq = zzebf;
    }

    public final /* synthetic */ Object zzd(zzelj zzelj) throws GeneralSecurityException {
        zzedl zzedl = (zzedl) zzelj;
        return (zzedi) ((zzejz) zzedi.zzazt().zzc(zzedl.zzazs()).zzu(zzeip.zzu(zzehs.zzfo(zzedl.getKeySize()))).zzeu(0).zzbgt());
    }

    public final /* synthetic */ zzelj zzq(zzeip zzeip) throws zzekj {
        return zzedl.zzh(zzeip, zzejm.zzbft());
    }

    public final /* synthetic */ void zzc(zzelj zzelj) throws GeneralSecurityException {
        zzedl zzedl = (zzedl) zzelj;
        zzeht.zzfp(zzedl.getKeySize());
        zzebf.zza(zzedl.zzazs());
    }
}
