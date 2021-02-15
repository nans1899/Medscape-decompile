package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeax {
    public static final String zzhve = new zzebb().getKeyType();
    public static final String zzhvf = new zzebl().getKeyType();
    private static final String zzhvg = new zzebg().getKeyType();
    private static final String zzhvh = new zzebr().getKeyType();
    private static final String zzhvi = new zzebv().getKeyType();
    private static final String zzhvj = new zzebm().getKeyType();
    private static final String zzhvk = new zzebw().getKeyType();
    @Deprecated
    private static final zzefw zzhvl;
    @Deprecated
    private static final zzefw zzhvm;
    @Deprecated
    private static final zzefw zzhvn;

    public static void zzazb() throws GeneralSecurityException {
        zzect.zzazb();
        zzear.zza(new zzebb(), true);
        zzear.zza(new zzebg(), true);
        zzear.zza(new zzebl(), true);
        zzear.zza(new zzebm(), true);
        zzear.zza(new zzebr(), true);
        zzear.zza(new zzebv(), true);
        zzear.zza(new zzebw(), true);
        zzear.zza(new zzeba());
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
