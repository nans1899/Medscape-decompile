package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzecj {
    public static void zza(zzeei zzeei) throws GeneralSecurityException {
        zzegy.zza(zza(zzeei.zzbas().zzbbf()));
        zza(zzeei.zzbas().zzbbg());
        if (zzeei.zzbau() != zzeec.UNKNOWN_FORMAT) {
            zzear.zza(zzeei.zzbat().zzban());
            return;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }

    public static String zza(zzeer zzeer) throws NoSuchAlgorithmException {
        int i = zzecm.zzhwk[zzeer.ordinal()];
        if (i == 1) {
            return "HmacSha1";
        }
        if (i == 2) {
            return "HmacSha256";
        }
        if (i == 3) {
            return "HmacSha512";
        }
        String valueOf = String.valueOf(zzeer);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
        sb.append("hash unsupported for HMAC: ");
        sb.append(valueOf);
        throw new NoSuchAlgorithmException(sb.toString());
    }

    public static zzeha zza(zzeeq zzeeq) throws GeneralSecurityException {
        int i = zzecm.zzhwl[zzeeq.ordinal()];
        if (i == 1) {
            return zzeha.NIST_P256;
        }
        if (i == 2) {
            return zzeha.NIST_P384;
        }
        if (i == 3) {
            return zzeha.NIST_P521;
        }
        String valueOf = String.valueOf(zzeeq);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
        sb.append("unknown curve type: ");
        sb.append(valueOf);
        throw new GeneralSecurityException(sb.toString());
    }

    public static zzegz zza(zzeec zzeec) throws GeneralSecurityException {
        int i = zzecm.zzhwm[zzeec.ordinal()];
        if (i == 1) {
            return zzegz.UNCOMPRESSED;
        }
        if (i == 2) {
            return zzegz.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
        }
        if (i == 3) {
            return zzegz.COMPRESSED;
        }
        String valueOf = String.valueOf(zzeec);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("unknown point format: ");
        sb.append(valueOf);
        throw new GeneralSecurityException(sb.toString());
    }
}
