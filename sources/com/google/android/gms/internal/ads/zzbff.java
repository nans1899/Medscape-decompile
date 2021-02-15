package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbff extends zzbm {
    static final zzbff zzelj = new zzbff();

    zzbff() {
    }

    public final zzbp zza(String str, byte[] bArr, String str2) {
        if ("moov".equals(str)) {
            return new zzbr();
        }
        if ("mvhd".equals(str)) {
            return new zzbu();
        }
        return new zzbt(str);
    }
}
