package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzegg extends ThreadLocal<Cipher> {
    zzegg() {
    }

    private static Cipher zzbdm() {
        try {
            return zzehb.zzicx.zzhs("AES/CTR/NoPadding");
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object initialValue() {
        return zzbdm();
    }
}
