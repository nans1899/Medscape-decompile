package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzecr extends zzeaf<zzeex, zzeet> {
    private final /* synthetic */ zzecp zzhwn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzecr(zzecp zzecp, Class cls) {
        super(cls);
        this.zzhwn = zzecp;
    }

    public final /* synthetic */ Object zzd(zzelj zzelj) throws GeneralSecurityException {
        zzeex zzeex = (zzeex) zzelj;
        return (zzeet) ((zzejz) zzeet.zzbbl().zzfd(0).zzd(zzeex.zzbbk()).zzad(zzeip.zzu(zzehs.zzfo(zzeex.getKeySize()))).zzbgt());
    }

    public final /* synthetic */ zzelj zzq(zzeip zzeip) throws zzekj {
        return zzeex.zzs(zzeip, zzejm.zzbft());
    }

    public final /* synthetic */ void zzc(zzelj zzelj) throws GeneralSecurityException {
        zzeex zzeex = (zzeex) zzelj;
        if (zzeex.getKeySize() >= 16) {
            zzecp.zza(zzeex.zzbbk());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }
}
