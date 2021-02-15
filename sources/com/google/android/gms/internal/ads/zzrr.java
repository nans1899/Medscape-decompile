package com.google.android.gms.internal.ads;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzrr {
    private ByteArrayOutputStream zzbue = new ByteArrayOutputStream(4096);
    private Base64OutputStream zzbuf = new Base64OutputStream(this.zzbue, 10);

    public final void write(byte[] bArr) throws IOException {
        this.zzbuf.write(bArr);
    }

    public final String toString() {
        try {
            this.zzbuf.close();
        } catch (IOException e) {
            zzayp.zzc("HashManager: Unable to convert to Base64.", e);
        }
        try {
            this.zzbue.close();
            return this.zzbue.toString();
        } catch (IOException e2) {
            zzayp.zzc("HashManager: Unable to convert to Base64.", e2);
            return "";
        } finally {
            this.zzbue = null;
            this.zzbuf = null;
        }
    }
}
