package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzoj implements zzom {
    private final byte[] data;
    private Uri uri;
    private int zzbia;
    private int zzbib;

    public zzoj(byte[] bArr) {
        zzoz.checkNotNull(bArr);
        zzoz.checkArgument(bArr.length > 0);
        this.data = bArr;
    }

    public final long zza(zzon zzon) throws IOException {
        this.uri = zzon.uri;
        this.zzbia = (int) zzon.position;
        int length = (int) (zzon.zzcp == -1 ? ((long) this.data.length) - zzon.position : zzon.zzcp);
        this.zzbib = length;
        if (length > 0 && this.zzbia + length <= this.data.length) {
            return (long) length;
        }
        int i = this.zzbia;
        long j = zzon.zzcp;
        int length2 = this.data.length;
        StringBuilder sb = new StringBuilder(77);
        sb.append("Unsatisfiable range: [");
        sb.append(i);
        sb.append(", ");
        sb.append(j);
        sb.append("], length: ");
        sb.append(length2);
        throw new IOException(sb.toString());
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.zzbib;
        if (i3 == 0) {
            return -1;
        }
        int min = Math.min(i2, i3);
        System.arraycopy(this.data, this.zzbia, bArr, i, min);
        this.zzbia += min;
        this.zzbib -= min;
        return min;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final void close() throws IOException {
        this.uri = null;
    }
}
