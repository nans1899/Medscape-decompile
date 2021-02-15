package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.util.Hex;
import java.io.File;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdva {
    private static final Object zzhok = new Object();
    private final SharedPreferences zzcln;
    private final String zzhoj;
    private final Context zzvr;

    public zzdva(Context context, zzgo zzgo) {
        this.zzvr = context;
        this.zzhoj = Integer.toString(zzgo.zzv());
        this.zzcln = context.getSharedPreferences("pcvmspf", 0);
    }

    public final zzdus zzp(int i) {
        synchronized (zzhok) {
            zzgr zzei = zzei(i);
            if (zzei == null) {
                return null;
            }
            File zzhg = zzhg(zzei.zzdg());
            zzdus zzdus = new zzdus(zzei, new File(zzhg, "pcam"), new File(zzhg, "pcbc"), new File(zzhg, "pcopt"));
            return zzdus;
        }
    }

    public final boolean zza(zzgq zzgq, zzdux zzdux) {
        synchronized (zzhok) {
            zzgr zzei = zzei(zzduz.zzhof);
            String zzdg = zzgq.zzdc().zzdg();
            if (zzei != null && zzei.zzdg().equals(zzdg)) {
                return false;
            }
            if (!zzhg(zzdg).mkdirs()) {
                return false;
            }
            File zzhg = zzhg(zzdg);
            File file = new File(zzhg, "pcam");
            File file2 = new File(zzhg, "pcbc");
            if (!zzduu.zza(file, zzgq.zzdd().toByteArray())) {
                return false;
            }
            if (!zzduu.zza(file2, zzgq.zzde().toByteArray())) {
                return false;
            }
            if (zzdux == null || zzdux.zzb(file)) {
                String zzb = zzb(zzgq);
                String string = this.zzcln.getString(zzawu(), (String) null);
                SharedPreferences.Editor edit = this.zzcln.edit();
                edit.putString(zzawu(), zzb);
                if (string != null) {
                    edit.putString(zzawt(), string);
                }
                if (!edit.commit()) {
                    return false;
                }
                HashSet hashSet = new HashSet();
                zzgr zzei2 = zzei(zzduz.zzhof);
                if (zzei2 != null) {
                    hashSet.add(zzei2.zzdg());
                }
                zzgr zzei3 = zzei(zzduz.zzhog);
                if (zzei3 != null) {
                    hashSet.add(zzei3.zzdg());
                }
                for (File file3 : new File(this.zzvr.getDir("pccache", 0), this.zzhoj).listFiles()) {
                    if (!hashSet.contains(file3.getName())) {
                        zzduu.zze(file3);
                    }
                }
                return true;
            }
            zzduu.zze(zzhg);
            return false;
        }
    }

    public final boolean zza(zzgq zzgq) {
        synchronized (zzhok) {
            if (!zzduu.zza(new File(zzhg(zzgq.zzdc().zzdg()), "pcbc"), zzgq.zzde().toByteArray())) {
                return false;
            }
            String zzb = zzb(zzgq);
            SharedPreferences.Editor edit = this.zzcln.edit();
            edit.putString(zzawu(), zzb);
            boolean commit = edit.commit();
            return commit;
        }
    }

    private final zzgr zzei(int i) {
        String str;
        if (i == zzduz.zzhof) {
            str = this.zzcln.getString(zzawu(), (String) null);
        } else {
            str = i == zzduz.zzhog ? this.zzcln.getString(zzawt(), (String) null) : null;
        }
        if (str == null) {
            return null;
        }
        try {
            return zzgr.zzb(zzeip.zzu(Hex.stringToBytes(str)), zzejm.zzbfu());
        } catch (zzekj unused) {
            return null;
        }
    }

    private final File zzhg(String str) {
        return new File(new File(this.zzvr.getDir("pccache", 0), this.zzhoj), str);
    }

    private final String zzawt() {
        String valueOf = String.valueOf(this.zzhoj);
        return valueOf.length() != 0 ? "FBAMTD".concat(valueOf) : new String("FBAMTD");
    }

    private final String zzawu() {
        String valueOf = String.valueOf(this.zzhoj);
        return valueOf.length() != 0 ? "LATMTD".concat(valueOf) : new String("LATMTD");
    }

    private static String zzb(zzgq zzgq) {
        return Hex.bytesToStringLowercase(((zzgr) ((zzejz) zzgr.zzdl().zzaw(zzgq.zzdc().zzdg()).zzax(zzgq.zzdc().zzdh()).zzdk(zzgq.zzdc().zzdj()).zzdl(zzgq.zzdc().zzdk()).zzdj(zzgq.zzdc().zzdi()).zzbgt())).zzbdw().toByteArray());
    }
}
