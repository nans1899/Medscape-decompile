package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzccu implements zzbqp<zzbqu> {
    private final zzcep zzfqc;
    private final Map<String, zzcta<zzbqu>> zzfrw;
    private final zzeph<zzbqp<zzboq>> zzfxh;
    private final Map<String, zzcta<zzcdx>> zzfxi;
    private final Map<String, zzcvl<zzcdx>> zzfxj;

    zzccu(Map<String, zzcta<zzbqu>> map, Map<String, zzcta<zzcdx>> map2, Map<String, zzcvl<zzcdx>> map3, zzeph<zzbqp<zzboq>> zzeph, zzcep zzcep) {
        this.zzfrw = map;
        this.zzfxi = map2;
        this.zzfxj = map3;
        this.zzfxh = zzeph;
        this.zzfqc = zzcep;
    }

    public final zzcta<zzbqu> zze(int i, String str) {
        zzcta zze;
        zzcta<zzbqu> zzcta = this.zzfrw.get(str);
        if (zzcta != null) {
            return zzcta;
        }
        if (i != 1) {
            if (i != 4) {
                return null;
            }
            zzcvl zzcvl = this.zzfxj.get(str);
            if (zzcvl != null) {
                return zzbqu.zza((zzcvl<? extends zzbqo>) zzcvl);
            }
            zzcta zzcta2 = this.zzfxi.get(str);
            if (zzcta2 != null) {
                return zzbqu.zza((zzcta<? extends zzbqo>) zzcta2);
            }
            return null;
        } else if (this.zzfqc.zzams() == null || (zze = this.zzfxh.get().zze(i, str)) == null) {
            return null;
        } else {
            return zzbqu.zza((zzcta<? extends zzbqo>) zze);
        }
    }
}
