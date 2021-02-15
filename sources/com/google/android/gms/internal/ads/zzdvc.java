package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdvc extends Exception {
    private final int zzhop;

    public zzdvc(int i, String str) {
        super(str);
        this.zzhop = i;
    }

    public zzdvc(int i, Throwable th) {
        super(th);
        this.zzhop = i;
    }

    public final int zzaxb() {
        return this.zzhop;
    }
}
