package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzehn implements zzecx {
    private final ThreadLocal<Mac> zzidk = new zzehq(this);
    /* access modifiers changed from: private */
    public final String zzidl;
    /* access modifiers changed from: private */
    public final Key zzidm;
    private final int zzidn;

    public zzehn(String str, Key key) throws GeneralSecurityException {
        this.zzidl = str;
        this.zzidm = key;
        if (key.getEncoded().length >= 16) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1823053428) {
                if (hashCode != 392315118) {
                    if (hashCode == 392317873 && str.equals("HMACSHA512")) {
                        c = 2;
                    }
                } else if (str.equals("HMACSHA256")) {
                    c = 1;
                }
            } else if (str.equals("HMACSHA1")) {
                c = 0;
            }
            if (c == 0) {
                this.zzidn = 20;
            } else if (c == 1) {
                this.zzidn = 32;
            } else if (c != 2) {
                String valueOf = String.valueOf(str);
                throw new NoSuchAlgorithmException(valueOf.length() != 0 ? "unknown Hmac algorithm: ".concat(valueOf) : new String("unknown Hmac algorithm: "));
            } else {
                this.zzidn = 64;
            }
            this.zzidk.get();
            return;
        }
        throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
    }

    public final byte[] zzd(byte[] bArr, int i) throws GeneralSecurityException {
        if (i <= this.zzidn) {
            this.zzidk.get().update(bArr);
            return Arrays.copyOf(this.zzidk.get().doFinal(), i);
        }
        throw new InvalidAlgorithmParameterException("tag size too big");
    }
}
