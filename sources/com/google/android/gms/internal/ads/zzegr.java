package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzegr implements zzdzz {
    private static final byte[] zzhvu = new byte[0];
    private final ECPrivateKey zzicc;
    private final zzegt zzicd;
    private final String zzice;
    private final byte[] zzicf;
    private final zzegz zzicg;
    private final zzegs zzich;

    public zzegr(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzegz zzegz, zzegs zzegs) throws GeneralSecurityException {
        this.zzicc = eCPrivateKey;
        this.zzicd = new zzegt(eCPrivateKey);
        this.zzicf = bArr;
        this.zzice = str;
        this.zzicg = zzegz;
        this.zzich = zzegs;
    }
}
