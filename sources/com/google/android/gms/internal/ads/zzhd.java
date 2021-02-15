package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzhd extends Exception {
    private final int type;
    private final int zzaek;

    public static zzhd zza(Exception exc, int i) {
        return new zzhd(1, (String) null, exc, i);
    }

    public static zzhd zza(IOException iOException) {
        return new zzhd(0, (String) null, iOException, -1);
    }

    static zzhd zza(RuntimeException runtimeException) {
        return new zzhd(2, (String) null, runtimeException, -1);
    }

    private zzhd(int i, String str, Throwable th, int i2) {
        super((String) null, th);
        this.type = i;
        this.zzaek = i2;
    }
}
