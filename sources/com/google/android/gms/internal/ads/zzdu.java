package com.google.android.gms.internal.ads;

import android.os.Build;
import android.os.ConditionVariable;
import com.google.android.gms.internal.ads.zzbw;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzdu {
    /* access modifiers changed from: private */
    public static final ConditionVariable zzwh = new ConditionVariable();
    protected static volatile zztx zzwi = null;
    private static volatile Random zzwk = null;
    /* access modifiers changed from: private */
    public zzex zzwg;
    protected volatile Boolean zzwj;

    public zzdu(zzex zzex) {
        this.zzwg = zzex;
        zzex.zzcg().execute(new zzdt(this));
    }

    public final void zza(int i, int i2, long j) {
        zza(i, i2, j, (String) null, (Exception) null);
    }

    public final void zza(int i, int i2, long j, String str) {
        zza(i, -1, j, str, (Exception) null);
    }

    public final void zza(int i, int i2, long j, String str, Exception exc) {
        try {
            zzwh.block();
            if (this.zzwj.booleanValue() && zzwi != null) {
                zzbw.zza.C0006zza zzc = zzbw.zza.zzs().zzk(this.zzwg.zzvr.getPackageName()).zzc(j);
                if (str != null) {
                    zzc.zzn(str);
                }
                if (exc != null) {
                    StringWriter stringWriter = new StringWriter();
                    zzehy.zza((Throwable) exc, new PrintWriter(stringWriter));
                    zzc.zzl(stringWriter.toString()).zzm(exc.getClass().getName());
                }
                zzub zzf = zzwi.zzf(((zzbw.zza) ((zzejz) zzc.zzbgt())).toByteArray());
                zzf.zzby(i);
                if (i2 != -1) {
                    zzf.zzbx(i2);
                }
                zzf.zzdx();
            }
        } catch (Exception unused) {
        }
    }

    public static int zzbu() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                return ThreadLocalRandom.current().nextInt();
            }
            return zzbv().nextInt();
        } catch (RuntimeException unused) {
            return zzbv().nextInt();
        }
    }

    private static Random zzbv() {
        if (zzwk == null) {
            synchronized (zzdu.class) {
                if (zzwk == null) {
                    zzwk = new Random();
                }
            }
        }
        return zzwk;
    }
}
