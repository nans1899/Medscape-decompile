package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzecd extends zzeaf<zzeeh, zzeel> {
    private final /* synthetic */ zzecb zzhwb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzecd(zzecb zzecb, Class cls) {
        super(cls);
        this.zzhwb = zzecb;
    }

    public final /* synthetic */ Object zzd(zzelj zzelj) throws GeneralSecurityException {
        zzeeh zzeeh = (zzeeh) zzelj;
        KeyPair zza = zzegy.zza(zzegy.zza(zzecj.zza(zzeeh.zzbaq().zzbas().zzbbf())));
        ECPoint w = ((ECPublicKey) zza.getPublic()).getW();
        return (zzeel) ((zzejz) zzeel.zzbay().zzez(0).zzb((zzeem) ((zzejz) zzeem.zzbbc().zzfa(0).zzc(zzeeh.zzbaq()).zzab(zzeip.zzu(w.getAffineX().toByteArray())).zzac(zzeip.zzu(w.getAffineY().toByteArray())).zzbgt())).zzaa(zzeip.zzu(((ECPrivateKey) zza.getPrivate()).getS().toByteArray())).zzbgt());
    }

    public final /* synthetic */ zzelj zzq(zzeip zzeip) throws zzekj {
        return zzeeh.zzo(zzeip, zzejm.zzbft());
    }

    public final /* synthetic */ void zzc(zzelj zzelj) throws GeneralSecurityException {
        zzecj.zza(((zzeeh) zzelj).zzbaq());
    }
}
