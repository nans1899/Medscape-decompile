package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdga implements zzdvu {
    private final String zzdgt;

    zzdga(String str) {
        this.zzdgt = str;
    }

    public final Object apply(Object obj) {
        Throwable th = (Throwable) obj;
        String valueOf = String.valueOf(this.zzdgt);
        zzayp.zzfc(valueOf.length() != 0 ? "Error calling adapter: ".concat(valueOf) : new String("Error calling adapter: "));
        return null;
    }
}
