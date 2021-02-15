package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzrq {
    private static MessageDigest zzbud;
    protected Object mLock = new Object();

    /* access modifiers changed from: package-private */
    public abstract byte[] zzbu(String str);

    /* access modifiers changed from: protected */
    public final MessageDigest zzms() {
        synchronized (this.mLock) {
            if (zzbud != null) {
                MessageDigest messageDigest = zzbud;
                return messageDigest;
            }
            for (int i = 0; i < 2; i++) {
                try {
                    zzbud = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            MessageDigest messageDigest2 = zzbud;
            return messageDigest2;
        }
    }
}
