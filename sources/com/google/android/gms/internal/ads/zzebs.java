package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzebs implements zzdzv {
    private static final byte[] zzhvu = new byte[0];
    private final zzefe zzhvv;
    private final zzdzv zzhvw;

    public zzebs(zzefe zzefe, zzdzv zzdzv) {
        this.zzhvv = zzefe;
        this.zzhvw = zzdzv;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] byteArray = zzear.zzb(this.zzhvv).toByteArray();
        byte[] zzc = this.zzhvw.zzc(byteArray, zzhvu);
        byte[] zzc2 = ((zzdzv) zzear.zza(this.zzhvv.zzbbt(), byteArray, zzdzv.class)).zzc(bArr, bArr2);
        return ByteBuffer.allocate(zzc.length + 4 + zzc2.length).putInt(zzc.length).put(zzc).put(zzc2).array();
    }
}
