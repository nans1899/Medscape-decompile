package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzegp implements zzdzv {
    private final zzegn zzica;
    private final zzegn zzicb;

    public zzegp(byte[] bArr) throws InvalidKeyException {
        this.zzica = zzf(bArr, 1);
        this.zzicb = zzf(bArr, 0);
    }

    /* access modifiers changed from: package-private */
    public abstract zzegn zzf(byte[] bArr, int i) throws InvalidKeyException;

    public byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= (Integer.MAX_VALUE - this.zzica.zzbdn()) - 16) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length + this.zzica.zzbdn() + 16);
            if (allocate.remaining() >= bArr.length + this.zzica.zzbdn() + 16) {
                int position = allocate.position();
                this.zzica.zza(allocate, bArr);
                allocate.position(position);
                byte[] bArr3 = new byte[this.zzica.zzbdn()];
                allocate.get(bArr3);
                allocate.limit(allocate.limit() - 16);
                if (bArr2 == null) {
                    bArr2 = new byte[0];
                }
                byte[] bArr4 = new byte[32];
                this.zzicb.zze(bArr3, 0).get(bArr4);
                int length = bArr2.length % 16 == 0 ? bArr2.length : (bArr2.length + 16) - (bArr2.length % 16);
                int remaining = allocate.remaining();
                int i = remaining % 16;
                int i2 = (i == 0 ? remaining : (remaining + 16) - i) + length;
                ByteBuffer order = ByteBuffer.allocate(i2 + 16).order(ByteOrder.LITTLE_ENDIAN);
                order.put(bArr2);
                order.position(length);
                order.put(allocate);
                order.position(i2);
                order.putLong((long) bArr2.length);
                order.putLong((long) remaining);
                byte[] zze = zzeho.zze(bArr4, order.array());
                allocate.limit(allocate.limit() + 16);
                allocate.put(zze);
                return allocate.array();
            }
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
