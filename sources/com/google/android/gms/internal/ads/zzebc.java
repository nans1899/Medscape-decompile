package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzebc extends zzeaf<zzedh, zzede> {
    private final /* synthetic */ zzebb zzhvp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzebc(zzebb zzebb, Class cls) {
        super(cls);
        this.zzhvp = zzebb;
    }

    public final /* synthetic */ Object zzd(zzelj zzelj) throws GeneralSecurityException {
        zzedh zzedh = (zzedh) zzelj;
        return (zzede) ((zzejz) zzede.zzazn().zzb((zzedi) new zzebf().zzayr().zzd(zzedh.zzazp())).zzb((zzeet) new zzecp().zzayr().zzd(zzedh.zzazq())).zzet(0).zzbgt());
    }

    public final /* synthetic */ zzelj zzq(zzeip zzeip) throws zzekj {
        return zzedh.zzf(zzeip, zzejm.zzbft());
    }

    public final /* synthetic */ void zzc(zzelj zzelj) throws GeneralSecurityException {
        zzedh zzedh = (zzedh) zzelj;
        new zzebf().zzayr().zzc(zzedh.zzazp());
        new zzecp().zzayr().zzc(zzedh.zzazq());
        zzeht.zzfp(zzedh.zzazp().getKeySize());
    }
}
