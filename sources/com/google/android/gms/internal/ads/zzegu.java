package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzegu implements zzeac {
    private static final byte[] zzhvu = new byte[0];
    private final String zzice;
    private final byte[] zzicf;
    private final zzegz zzicg;
    private final zzegs zzich;
    private final zzegw zzici;

    public zzegu(ECPublicKey eCPublicKey, byte[] bArr, String str, zzegz zzegz, zzegs zzegs) throws GeneralSecurityException {
        zzegy.zza(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zzici = new zzegw(eCPublicKey);
        this.zzicf = bArr;
        this.zzice = str;
        this.zzicg = zzegz;
        this.zzich = zzegs;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzegv zza = this.zzici.zza(this.zzice, this.zzicf, bArr2, this.zzich.zzazc(), this.zzicg);
        byte[] zzc = this.zzich.zzn(zza.zzbdp()).zzc(bArr, zzhvu);
        byte[] zzbdo = zza.zzbdo();
        return ByteBuffer.allocate(zzbdo.length + zzc.length).put(zzbdo).put(zzc).array();
    }
}
