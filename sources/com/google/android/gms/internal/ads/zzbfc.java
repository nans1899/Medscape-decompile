package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbfc implements zzbft {
    public final zzbfl zza(zzbdu zzbdu, int i, String str, zzbdv zzbdv) {
        if (i <= 0) {
            return new zzbfs(zzbdu);
        }
        int zzaaw = zzber.zzaaw();
        if (zzaaw < zzbdv.zzeiw) {
            return new zzbfw(zzbdu, zzbdv);
        }
        if (zzaaw < zzbdv.zzeiq) {
            return new zzbfx(zzbdu, zzbdv);
        }
        return new zzbfv(zzbdu);
    }
}
