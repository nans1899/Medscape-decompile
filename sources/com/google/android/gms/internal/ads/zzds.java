package com.google.android.gms.internal.ads;

import java.io.File;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzds implements zzdux {
    private final /* synthetic */ zzdta zzwe;

    zzds(zzdp zzdp, zzdta zzdta) {
        this.zzwe = zzdta;
    }

    public final boolean zzb(File file) {
        try {
            return this.zzwe.zzb(file);
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }
}
