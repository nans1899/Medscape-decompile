package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbfb implements zzom {
    private Uri uri;
    private final zzom zzele;
    private final long zzelf;
    private final zzom zzelg;
    private long zzelh;

    zzbfb(zzom zzom, int i, zzom zzom2) {
        this.zzele = zzom;
        this.zzelf = (long) i;
        this.zzelg = zzom2;
    }

    public final long zza(zzon zzon) throws IOException {
        zzon zzon2;
        long j;
        zzon zzon3 = zzon;
        this.uri = zzon3.uri;
        zzon zzon4 = null;
        if (zzon3.position >= this.zzelf) {
            zzon2 = null;
        } else {
            long j2 = zzon3.position;
            if (zzon3.zzcp != -1) {
                j = Math.min(zzon3.zzcp, this.zzelf - j2);
            } else {
                j = this.zzelf - j2;
            }
            zzon2 = new zzon(zzon3.uri, j2, j, (String) null);
        }
        if (zzon3.zzcp == -1 || zzon3.position + zzon3.zzcp > this.zzelf) {
            zzon4 = new zzon(zzon3.uri, Math.max(this.zzelf, zzon3.position), zzon3.zzcp != -1 ? Math.min(zzon3.zzcp, (zzon3.position + zzon3.zzcp) - this.zzelf) : -1, (String) null);
        }
        long j3 = 0;
        long zza = zzon2 != null ? this.zzele.zza(zzon2) : 0;
        if (zzon4 != null) {
            j3 = this.zzelg.zza(zzon4);
        }
        this.zzelh = zzon3.position;
        if (zza == -1 || j3 == -1) {
            return -1;
        }
        return zza + j3;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        long j = this.zzelh;
        long j2 = this.zzelf;
        if (j < j2) {
            i3 = this.zzele.read(bArr, i, (int) Math.min((long) i2, j2 - j));
            this.zzelh += (long) i3;
        } else {
            i3 = 0;
        }
        if (this.zzelh < this.zzelf) {
            return i3;
        }
        int read = this.zzelg.read(bArr, i + i3, i2 - i3);
        int i4 = i3 + read;
        this.zzelh += (long) read;
        return i4;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final void close() throws IOException {
        this.zzele.close();
        this.zzelg.close();
    }
}
