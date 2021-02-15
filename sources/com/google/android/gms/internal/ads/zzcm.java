package com.google.android.gms.internal.ads;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public enum zzcm implements zzeke {
    UNKNOWN_ENCRYPTION_METHOD(0),
    BITSLICER(1),
    TINK_HYBRID(2),
    UNENCRYPTED(3),
    DG(4),
    DG_XTEA(5);
    
    private static final zzekd<zzcm> zzes = null;
    private final int value;

    public final int zzv() {
        return this.value;
    }

    public static zzcm zzm(int i) {
        if (i == 0) {
            return UNKNOWN_ENCRYPTION_METHOD;
        }
        if (i == 1) {
            return BITSLICER;
        }
        if (i == 2) {
            return TINK_HYBRID;
        }
        if (i == 3) {
            return UNENCRYPTED;
        }
        if (i == 4) {
            return DG;
        }
        if (i != 5) {
            return null;
        }
        return DG_XTEA;
    }

    public static zzekg zzw() {
        return zzco.zzeu;
    }

    public final String toString() {
        return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
    }

    private zzcm(int i) {
        this.value = i;
    }

    static {
        zzes = new zzcl();
    }
}
