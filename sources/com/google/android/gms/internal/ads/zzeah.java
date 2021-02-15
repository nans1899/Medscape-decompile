package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeah {
    private final zzefj zzhui;

    private zzeah(zzefj zzefj) {
        this.zzhui = zzefj;
    }

    static final zzeah zza(zzefj zzefj) throws GeneralSecurityException {
        if (zzefj != null && zzefj.zzbci() > 0) {
            return new zzeah(zzefj);
        }
        throw new GeneralSecurityException("empty keyset");
    }

    /* access modifiers changed from: package-private */
    public final zzefj zzays() {
        return this.zzhui;
    }

    public final String toString() {
        return zzeay.zzb(this.zzhui).toString();
    }
}
