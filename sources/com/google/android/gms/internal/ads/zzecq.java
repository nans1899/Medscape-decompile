package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzecq extends zzeaf<zzeda, zzecz> {
    zzecq(zzeco zzeco, Class cls) {
        super(cls);
    }

    public final /* synthetic */ Object zzd(zzelj zzelj) throws GeneralSecurityException {
        zzeda zzeda = (zzeda) zzelj;
        return (zzecz) ((zzejz) zzecz.zzazf().zzes(0).zzt(zzeip.zzu(zzehs.zzfo(zzeda.getKeySize()))).zzd(zzeda.zzaze()).zzbgt());
    }

    public final /* synthetic */ zzelj zzq(zzeip zzeip) throws zzekj {
        return zzeda.zzd(zzeip, zzejm.zzbft());
    }

    public final /* synthetic */ void zzc(zzelj zzelj) throws GeneralSecurityException {
        zzeda zzeda = (zzeda) zzelj;
        zzeco.zza(zzeda.zzaze());
        zzeco.zzeq(zzeda.getKeySize());
    }
}
