package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzecg implements zzeaq<zzdzz> {
    private static final Logger logger = Logger.getLogger(zzecg.class.getName());

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    static class zza implements zzdzz {
        private final zzeao<zzdzz> zzhwe;

        public zza(zzeao<zzdzz> zzeao) {
            this.zzhwe = zzeao;
        }
    }

    zzecg() {
    }

    public final Class<zzdzz> zzayk() {
        return zzdzz.class;
    }

    public final /* synthetic */ Object zza(zzeao zzeao) throws GeneralSecurityException {
        return new zza(zzeao);
    }
}
