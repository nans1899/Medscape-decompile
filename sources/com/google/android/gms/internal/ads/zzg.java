package com.google.android.gms.internal.ads;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzg extends zzj {
    private byte[] zzj;

    public zzg(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.zzj = bArr;
    }

    public final byte[] getEncoded() throws CertificateEncodingException {
        return this.zzj;
    }
}
