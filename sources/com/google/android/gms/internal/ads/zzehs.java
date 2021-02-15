package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzehs {
    private static final ThreadLocal<SecureRandom> zzidr = new zzehr();

    /* access modifiers changed from: private */
    public static SecureRandom zzbdr() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] zzfo(int i) {
        byte[] bArr = new byte[i];
        zzidr.get().nextBytes(bArr);
        return bArr;
    }
}
