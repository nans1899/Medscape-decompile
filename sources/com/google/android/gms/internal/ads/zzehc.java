package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzehc implements zzdzv {
    private final zzehl zzidh;
    private final zzeam zzidi;
    private final int zzidj;

    public zzehc(zzehl zzehl, zzeam zzeam, int i) {
        this.zzidh = zzehl;
        this.zzidi = zzeam;
        this.zzidj = i;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzo = this.zzidh.zzo(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8);
        return zzegl.zza(zzo, this.zzidi.zzm(zzegl.zza(bArr2, zzo, copyOf)));
    }
}
