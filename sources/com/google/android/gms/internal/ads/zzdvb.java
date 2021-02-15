package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdvb {
    private static final HashMap<String, Class<?>> zzhol = new HashMap<>();
    private final zzdve zzhnv;
    private final zzdta zzhom;
    private zzdup zzhon;
    private final Object zzhoo = new Object();
    private final Context zzvr;
    private final zzdtc zzvw;

    public zzdvb(Context context, zzdve zzdve, zzdtc zzdtc, zzdta zzdta) {
        this.zzvr = context;
        this.zzhnv = zzdve;
        this.zzvw = zzdtc;
        this.zzhom = zzdta;
    }

    private final synchronized Class<?> zza(zzdus zzdus) throws zzdvc {
        if (zzdus.zzawv() != null) {
            String zzdg = zzdus.zzawv().zzdg();
            Class<?> cls = zzhol.get(zzdg);
            if (cls != null) {
                return cls;
            }
            try {
                if (this.zzhom.zzb(zzdus.zzaww())) {
                    File zzawx = zzdus.zzawx();
                    if (!zzawx.exists()) {
                        zzawx.mkdirs();
                    }
                    Class<?> loadClass = new DexClassLoader(zzdus.zzaww().getAbsolutePath(), zzawx.getAbsolutePath(), (String) null, this.zzvr.getClassLoader()).loadClass("com.google.ccc.abuse.droidguard.DroidGuard");
                    zzhol.put(zzdg, loadClass);
                    return loadClass;
                }
                throw new zzdvc(2026, "VM did not pass signature verification");
            } catch (GeneralSecurityException e) {
                throw new zzdvc(2026, (Throwable) e);
            } catch (ClassNotFoundException | IllegalArgumentException | SecurityException e2) {
                throw new zzdvc((int) Constants.TAG_HOME_REFRESH, e2);
            }
        } else {
            throw new zzdvc((int) com.medscape.android.Constants.REQUEST_CODE_PICTURE_EDIT_SECOND, "mc");
        }
    }

    private final Object zza(Class<?> cls, zzdus zzdus) throws zzdvc {
        try {
            return cls.getDeclaredConstructor(new Class[]{Context.class, String.class, byte[].class, Object.class, Bundle.class, Integer.TYPE}).newInstance(new Object[]{this.zzvr, "msa-r", zzdus.zzawy(), null, new Bundle(), 2});
        } catch (Exception e) {
            throw new zzdvc((int) Constants.TAG_LANDING_FRAGMENT, (Throwable) e);
        }
    }

    public final void zzb(zzdus zzdus) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            zzdup zzdup = new zzdup(zza(zza(zzdus), zzdus), zzdus, this.zzhnv, this.zzvw);
            if (zzdup.zzawq()) {
                int zzawr = zzdup.zzawr();
                if (zzawr == 0) {
                    synchronized (this.zzhoo) {
                        if (this.zzhon != null) {
                            try {
                                this.zzhon.close();
                            } catch (zzdvc e) {
                                this.zzvw.zza(e.zzaxb(), -1, (Exception) e);
                            }
                        }
                        this.zzhon = zzdup;
                    }
                    this.zzvw.zzg(3000, System.currentTimeMillis() - currentTimeMillis);
                    return;
                }
                StringBuilder sb = new StringBuilder(15);
                sb.append("ci: ");
                sb.append(zzawr);
                throw new zzdvc(4001, sb.toString());
            }
            throw new zzdvc(4000, "init failed");
        } catch (zzdvc e2) {
            this.zzvw.zza(e2.zzaxb(), System.currentTimeMillis() - currentTimeMillis, (Exception) e2);
        } catch (Exception e3) {
            this.zzvw.zza((int) com.medscape.android.Constants.REQUEST_CODE_PICTURE_EDIT_SECOND, System.currentTimeMillis() - currentTimeMillis, e3);
        }
    }

    public final zzdtf zzawz() {
        zzdup zzdup;
        synchronized (this.zzhoo) {
            zzdup = this.zzhon;
        }
        return zzdup;
    }

    public final zzdus zzaxa() {
        synchronized (this.zzhoo) {
            if (this.zzhon == null) {
                return null;
            }
            zzdus zzawp = this.zzhon.zzawp();
            return zzawp;
        }
    }
}
