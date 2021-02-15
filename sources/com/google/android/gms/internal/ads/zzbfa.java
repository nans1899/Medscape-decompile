package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbfa implements zzeok {
    private final ByteBuffer zzamm;

    zzbfa(ByteBuffer byteBuffer) {
        this.zzamm = byteBuffer.duplicate();
    }

    public final void close() throws IOException {
    }

    public final int read(ByteBuffer byteBuffer) throws IOException {
        if (this.zzamm.remaining() == 0 && byteBuffer.remaining() > 0) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), this.zzamm.remaining());
        byte[] bArr = new byte[min];
        this.zzamm.get(bArr);
        byteBuffer.put(bArr);
        return min;
    }

    public final long size() throws IOException {
        return (long) this.zzamm.limit();
    }

    public final long position() throws IOException {
        return (long) this.zzamm.position();
    }

    public final void zzfc(long j) throws IOException {
        this.zzamm.position((int) j);
    }

    public final ByteBuffer zzh(long j, long j2) throws IOException {
        int position = this.zzamm.position();
        this.zzamm.position((int) j);
        ByteBuffer slice = this.zzamm.slice();
        slice.limit((int) j2);
        this.zzamm.position(position);
        return slice;
    }
}
