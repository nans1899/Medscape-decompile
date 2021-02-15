package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzeba implements zzeaq<zzdzv> {
    private static final Logger logger = Logger.getLogger(zzeba.class.getName());

    zzeba() {
    }

    public final Class<zzdzv> zzayk() {
        return zzdzv.class;
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static class zza implements zzdzv {
        private final zzeao<zzdzv> zzhvo;

        private zza(zzeao<zzdzv> zzeao) {
            this.zzhvo = zzeao;
        }

        public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            return zzegl.zza(this.zzhvo.zzayx().zzayw(), this.zzhvo.zzayx().zzayt().zzc(bArr, bArr2));
        }
    }

    public final /* synthetic */ Object zza(zzeao zzeao) throws GeneralSecurityException {
        return new zza(zzeao);
    }
}
