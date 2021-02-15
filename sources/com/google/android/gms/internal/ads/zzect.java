package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzect {
    @Deprecated
    private static final zzefw zzhvl;
    @Deprecated
    private static final zzefw zzhvm;
    @Deprecated
    private static final zzefw zzhvn;
    private static final String zzhwo = new zzecp().getKeyType();

    public static void zzazb() throws GeneralSecurityException {
        zzear.zza(new zzecp(), true);
        zzear.zza(new zzeco(), true);
        zzear.zza(new zzecw());
    }

    static {
        zzefw zzbdg = zzefw.zzbdg();
        zzhvl = zzbdg;
        zzhvm = zzbdg;
        zzhvn = zzbdg;
        try {
            zzazb();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
