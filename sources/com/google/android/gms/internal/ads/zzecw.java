package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
class zzecw implements zzeaq<zzeam> {
    private static final Logger logger = Logger.getLogger(zzecw.class.getName());

    zzecw() {
    }

    public final Class<zzeam> zzayk() {
        return zzeam.class;
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static class zza implements zzeam {
        private final zzeao<zzeam> zzhwe;
        private final byte[] zzhwp;

        private zza(zzeao<zzeam> zzeao) {
            this.zzhwp = new byte[]{0};
            this.zzhwe = zzeao;
        }

        public final byte[] zzm(byte[] bArr) throws GeneralSecurityException {
            if (this.zzhwe.zzayx().zzayv().equals(zzefv.LEGACY)) {
                return zzegl.zza(this.zzhwe.zzayx().zzayw(), this.zzhwe.zzayx().zzayt().zzm(zzegl.zza(bArr, this.zzhwp)));
            }
            return zzegl.zza(this.zzhwe.zzayx().zzayw(), this.zzhwe.zzayx().zzayt().zzm(bArr));
        }
    }

    public final /* synthetic */ Object zza(zzeao zzeao) throws GeneralSecurityException {
        return new zza(zzeao);
    }
}
