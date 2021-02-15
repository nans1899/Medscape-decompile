package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzece {
    @Deprecated
    public static final zzefw zzhvl = zzefw.zzbdg();
    @Deprecated
    private static final zzefw zzhvm = zzefw.zzbdg();
    @Deprecated
    private static final zzefw zzhvn = zzefw.zzbdg();
    private static final String zzhwc = new zzecc().getKeyType();
    private static final String zzhwd = new zzecb().getKeyType();

    static {
        try {
            zzeax.zzazb();
            zzear.zza(new zzecb(), new zzecc(), true);
            zzear.zza(new zzecg());
            zzear.zza(new zzech());
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
