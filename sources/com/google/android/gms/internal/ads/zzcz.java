package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcz implements Runnable {
    private zzcz() {
    }

    public final void run() {
        try {
            MessageDigest unused = zzcx.zzns = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused2) {
        } finally {
            zzcx.zznv.countDown();
        }
    }
}
