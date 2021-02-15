package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzelj;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzeae<PrimitiveT, KeyProtoT extends zzelj> implements zzeab<PrimitiveT> {
    private final zzeag<KeyProtoT> zzhud;
    private final Class<PrimitiveT> zzhue;

    public zzeae(zzeag<KeyProtoT> zzeag, Class<PrimitiveT> cls) {
        if (zzeag.zzayp().contains(cls) || Void.class.equals(cls)) {
            this.zzhud = zzeag;
            this.zzhue = cls;
            return;
        }
        throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", new Object[]{zzeag.toString(), cls.getName()}));
    }

    public final PrimitiveT zzm(zzeip zzeip) throws GeneralSecurityException {
        try {
            return zzb(this.zzhud.zzr(zzeip));
        } catch (zzekj e) {
            String valueOf = String.valueOf(this.zzhud.zzayn().getName());
            throw new GeneralSecurityException(valueOf.length() != 0 ? "Failures parsing proto of type ".concat(valueOf) : new String("Failures parsing proto of type "), e);
        }
    }

    public final PrimitiveT zza(zzelj zzelj) throws GeneralSecurityException {
        String valueOf = String.valueOf(this.zzhud.zzayn().getName());
        String concat = valueOf.length() != 0 ? "Expected proto of type ".concat(valueOf) : new String("Expected proto of type ");
        if (this.zzhud.zzayn().isInstance(zzelj)) {
            return zzb(zzelj);
        }
        throw new GeneralSecurityException(concat);
    }

    public final zzelj zzn(zzeip zzeip) throws GeneralSecurityException {
        try {
            return zzayl().zzp(zzeip);
        } catch (zzekj e) {
            String valueOf = String.valueOf(this.zzhud.zzayr().zzaym().getName());
            throw new GeneralSecurityException(valueOf.length() != 0 ? "Failures parsing proto of type ".concat(valueOf) : new String("Failures parsing proto of type "), e);
        }
    }

    public final String getKeyType() {
        return this.zzhud.getKeyType();
    }

    public final zzefb zzo(zzeip zzeip) throws GeneralSecurityException {
        try {
            return (zzefb) ((zzejz) zzefb.zzbbw().zzhq(this.zzhud.getKeyType()).zzaf(zzayl().zzp(zzeip).zzbdw()).zzb(this.zzhud.zzayo()).zzbgt());
        } catch (zzekj e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    public final Class<PrimitiveT> zzayk() {
        return this.zzhue;
    }

    private final PrimitiveT zzb(KeyProtoT keyprotot) throws GeneralSecurityException {
        if (!Void.class.equals(this.zzhue)) {
            this.zzhud.zze(keyprotot);
            return this.zzhud.zza(keyprotot, this.zzhue);
        }
        throw new GeneralSecurityException("Cannot create a primitive for Void");
    }

    private final zzead<?, KeyProtoT> zzayl() {
        return new zzead<>(this.zzhud.zzayr());
    }
}
